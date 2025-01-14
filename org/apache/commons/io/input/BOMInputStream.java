package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;

public class BOMInputStream extends ProxyInputStream {
  private final boolean include;
  
  private final List<ByteOrderMark> boms;
  
  private ByteOrderMark byteOrderMark;
  
  private int[] firstBytes;
  
  private int fbLength;
  
  private int fbIndex;
  
  private int markFbIndex;
  
  private boolean markedAtStart;
  
  private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator;
  
  public BOMInputStream(InputStream delegate) {
    this(delegate, false, new ByteOrderMark[] { ByteOrderMark.UTF_8 });
  }
  
  public BOMInputStream(InputStream delegate, boolean include) {
    this(delegate, include, new ByteOrderMark[] { ByteOrderMark.UTF_8 });
  }
  
  public BOMInputStream(InputStream delegate, ByteOrderMark... boms) {
    this(delegate, false, boms);
  }
  
  static {
    ByteOrderMarkLengthComparator = ((bom1, bom2) -> {
        int len1 = bom1.length();
        int len2 = bom2.length();
        return Integer.compare(len2, len1);
      });
  }
  
  public BOMInputStream(InputStream delegate, boolean include, ByteOrderMark... boms) {
    super(delegate);
    if (IOUtils.length((Object[])boms) == 0)
      throw new IllegalArgumentException("No BOMs specified"); 
    this.include = include;
    List<ByteOrderMark> list = Arrays.asList(boms);
    list.sort(ByteOrderMarkLengthComparator);
    this.boms = list;
  }
  
  public boolean hasBOM() throws IOException {
    return (getBOM() != null);
  }
  
  public boolean hasBOM(ByteOrderMark bom) throws IOException {
    if (!this.boms.contains(bom))
      throw new IllegalArgumentException("Stream not configure to detect " + bom); 
    getBOM();
    return (this.byteOrderMark != null && this.byteOrderMark.equals(bom));
  }
  
  public ByteOrderMark getBOM() throws IOException {
    if (this.firstBytes == null) {
      this.fbLength = 0;
      int maxBomSize = ((ByteOrderMark)this.boms.get(0)).length();
      this.firstBytes = new int[maxBomSize];
      for (int i = 0; i < this.firstBytes.length; i++) {
        this.firstBytes[i] = this.in.read();
        this.fbLength++;
        if (this.firstBytes[i] < 0)
          break; 
      } 
      this.byteOrderMark = find();
      if (this.byteOrderMark != null && !this.include)
        if (this.byteOrderMark.length() < this.firstBytes.length) {
          this.fbIndex = this.byteOrderMark.length();
        } else {
          this.fbLength = 0;
        }  
    } 
    return this.byteOrderMark;
  }
  
  public String getBOMCharsetName() throws IOException {
    getBOM();
    return (this.byteOrderMark == null) ? null : this.byteOrderMark.getCharsetName();
  }
  
  private int readFirstBytes() throws IOException {
    getBOM();
    return (this.fbIndex < this.fbLength) ? this.firstBytes[this.fbIndex++] : -1;
  }
  
  private ByteOrderMark find() {
    for (ByteOrderMark bom : this.boms) {
      if (matches(bom))
        return bom; 
    } 
    return null;
  }
  
  private boolean matches(ByteOrderMark bom) {
    for (int i = 0; i < bom.length(); i++) {
      if (bom.get(i) != this.firstBytes[i])
        return false; 
    } 
    return true;
  }
  
  public int read() throws IOException {
    int b = readFirstBytes();
    return (b >= 0) ? b : this.in.read();
  }
  
  public int read(byte[] buf, int off, int len) throws IOException {
    int firstCount = 0;
    int b = 0;
    while (len > 0 && b >= 0) {
      b = readFirstBytes();
      if (b >= 0) {
        buf[off++] = (byte)(b & 0xFF);
        len--;
        firstCount++;
      } 
    } 
    int secondCount = this.in.read(buf, off, len);
    return (secondCount < 0) ? ((firstCount > 0) ? firstCount : -1) : (firstCount + secondCount);
  }
  
  public int read(byte[] buf) throws IOException {
    return read(buf, 0, buf.length);
  }
  
  public synchronized void mark(int readlimit) {
    this.markFbIndex = this.fbIndex;
    this.markedAtStart = (this.firstBytes == null);
    this.in.mark(readlimit);
  }
  
  public synchronized void reset() throws IOException {
    this.fbIndex = this.markFbIndex;
    if (this.markedAtStart)
      this.firstBytes = null; 
    this.in.reset();
  }
  
  public long skip(long n) throws IOException {
    int skipped = 0;
    while (n > skipped && readFirstBytes() >= 0)
      skipped++; 
    return this.in.skip(n - skipped) + skipped;
  }
}
