package javazoom.spi.mpeg.sampled.file.tag;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IcyInputStream extends BufferedInputStream implements MP3MetadataParser {
  public static boolean DEBUG = false;
  
  MP3TagParseSupport tagParseSupport;
  
  protected static final String INLINE_TAG_SEPARATORS = ";\000";
  
  HashMap tags;
  
  protected byte[] crlfBuffer = new byte[1024];
  
  protected int metaint = -1;
  
  protected int bytesUntilNextMetadata = -1;
  
  public IcyInputStream(InputStream in) throws IOException {
    super(in);
    this.tags = new HashMap<Object, Object>();
    this.tagParseSupport = new MP3TagParseSupport();
    readInitialHeaders();
    IcyTag metaIntTag = (IcyTag)getTag("icy-metaint");
    if (DEBUG)
      System.out.println("METATAG:" + metaIntTag); 
    if (metaIntTag != null) {
      String metaIntString = metaIntTag.getValueAsString();
      try {
        this.metaint = Integer.parseInt(metaIntString.trim());
        if (DEBUG)
          System.out.println("METAINT:" + this.metaint); 
        this.bytesUntilNextMetadata = this.metaint;
      } catch (NumberFormatException numberFormatException) {}
    } 
  }
  
  public IcyInputStream(InputStream in, String metaIntString) throws IOException {
    super(in);
    this.tags = new HashMap<Object, Object>();
    this.tagParseSupport = new MP3TagParseSupport();
    try {
      this.metaint = Integer.parseInt(metaIntString.trim());
      if (DEBUG)
        System.out.println("METAINT:" + this.metaint); 
      this.bytesUntilNextMetadata = this.metaint;
    } catch (NumberFormatException numberFormatException) {}
  }
  
  protected void readInitialHeaders() throws IOException {
    String line = null;
    while (!(line = readCRLFLine()).equals("")) {
      int colonIndex = line.indexOf(':');
      if (colonIndex == -1)
        continue; 
      IcyTag tag = new IcyTag(line.substring(0, colonIndex), line.substring(colonIndex + 1));
      addTag(tag);
    } 
  }
  
  protected String readCRLFLine() throws IOException {
    int i = 0;
    for (; i < this.crlfBuffer.length; i++) {
      byte aByte = (byte)read();
      if (aByte == 13) {
        byte anotherByte = (byte)read();
        i++;
        if (anotherByte == 10)
          break; 
        this.crlfBuffer[i - 1] = aByte;
        this.crlfBuffer[i] = anotherByte;
      } else {
        this.crlfBuffer[i] = aByte;
      } 
    } 
    return new String(this.crlfBuffer, 0, i - 1);
  }
  
  public int read() throws IOException {
    if (this.bytesUntilNextMetadata > 0) {
      this.bytesUntilNextMetadata--;
      return super.read();
    } 
    if (this.bytesUntilNextMetadata == 0) {
      readMetadata();
      this.bytesUntilNextMetadata = this.metaint - 1;
      return super.read();
    } 
    return super.read();
  }
  
  public int read(byte[] buf, int offset, int length) throws IOException {
    if (this.bytesUntilNextMetadata > 0) {
      int adjLength = Math.min(length, this.bytesUntilNextMetadata);
      int got = super.read(buf, offset, adjLength);
      this.bytesUntilNextMetadata -= got;
      return got;
    } 
    if (this.bytesUntilNextMetadata == 0) {
      readMetadata();
      this.bytesUntilNextMetadata = this.metaint;
      int adjLength = Math.min(length, this.bytesUntilNextMetadata);
      int got = super.read(buf, offset, adjLength);
      this.bytesUntilNextMetadata -= got;
      return got;
    } 
    return super.read(buf, offset, length);
  }
  
  public int read(byte[] buf) throws IOException {
    return read(buf, 0, buf.length);
  }
  
  protected void readMetadata() throws IOException {
    int blockCount = super.read();
    if (DEBUG)
      System.out.println("BLOCKCOUNT:" + blockCount); 
    int byteCount = blockCount * 16;
    if (byteCount < 0)
      return; 
    byte[] metadataBlock = new byte[byteCount];
    int index = 0;
    while (byteCount > 0) {
      int bytesRead = super.read(metadataBlock, index, byteCount);
      index += bytesRead;
      byteCount -= bytesRead;
    } 
    if (blockCount > 0)
      parseInlineIcyTags(metadataBlock); 
  }
  
  protected void parseInlineIcyTags(byte[] tagBlock) {
    String blockString = null;
    try {
      blockString = new String(tagBlock, "ISO-8859-1");
    } catch (UnsupportedEncodingException e) {
      blockString = new String(tagBlock);
    } 
    if (DEBUG)
      System.out.println("BLOCKSTR:" + blockString); 
    StringTokenizer izer = new StringTokenizer(blockString, ";\000");
    int i = 0;
    while (izer.hasMoreTokens()) {
      String tagString = izer.nextToken();
      int separatorIdx = tagString.indexOf('=');
      if (separatorIdx == -1)
        continue; 
      int valueStartIdx = (tagString.charAt(separatorIdx + 1) == '\'') ? (separatorIdx + 2) : (separatorIdx + 1);
      int valueEndIdx = (tagString.charAt(tagString.length() - 1) == '\'') ? (tagString.length() - 1) : tagString.length();
      String name = tagString.substring(0, separatorIdx);
      String value = tagString.substring(valueStartIdx, valueEndIdx);
      IcyTag tag = new IcyTag(name, value);
      addTag(tag);
    } 
  }
  
  protected void addTag(IcyTag tag) {
    this.tags.put(tag.getName(), tag);
    this.tagParseSupport.fireTagParsed(this, tag);
  }
  
  public MP3Tag getTag(String tagName) {
    return (MP3Tag)this.tags.get(tagName);
  }
  
  public MP3Tag[] getTags() {
    return (MP3Tag[])this.tags.values().toArray((Object[])new MP3Tag[0]);
  }
  
  public HashMap getTagHash() {
    return this.tags;
  }
  
  public void addTagParseListener(TagParseListener tpl) {
    this.tagParseSupport.addTagParseListener(tpl);
  }
  
  public void removeTagParseListener(TagParseListener tpl) {
    this.tagParseSupport.removeTagParseListener(tpl);
  }
  
  public static void main(String[] args) {
    byte[] chow = new byte[200];
    if (args.length != 1)
      return; 
    try {
      URL url = new URL(args[0]);
      URLConnection conn = url.openConnection();
      conn.setRequestProperty("Icy-Metadata", "1");
      IcyInputStream icy = new IcyInputStream(new BufferedInputStream(conn.getInputStream()));
      while (icy.available() > -1)
        icy.read(chow, 0, chow.length); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
