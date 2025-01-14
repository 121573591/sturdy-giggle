package org.openjdk.nashorn.internal.runtime;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.WeakHashMap;
import org.openjdk.nashorn.api.scripting.URLReader;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "source")
public final class Source implements Loggable {
  private static final int BUF_SIZE = 8192;
  
  private static final Cache CACHE = new Cache();
  
  private static final Base64.Encoder BASE64 = Base64.getUrlEncoder().withoutPadding();
  
  private final String name;
  
  private final String base;
  
  private final Data data;
  
  private int hash;
  
  private volatile byte[] digest;
  
  private String explicitURL;
  
  private Source(String name, String base, Data data) {
    this.name = name;
    this.base = base;
    this.data = data;
  }
  
  private static synchronized Source sourceFor(String name, String base, URLData data) throws IOException {
    try {
      Source newSource = new Source(name, base, data);
      Source existingSource = CACHE.get(newSource);
      if (existingSource != null) {
        data.checkPermissionAndClose();
        return existingSource;
      } 
      data.load();
      CACHE.put(newSource, newSource);
      return newSource;
    } catch (RuntimeException e) {
      Throwable cause = e.getCause();
      if (cause instanceof IOException)
        throw (IOException)cause; 
      throw e;
    } 
  }
  
  private static class Cache extends WeakHashMap<Source, WeakReference<Source>> {
    public Source get(Source key) {
      WeakReference<Source> ref = (WeakReference<Source>)get(key);
      return (ref == null) ? null : ref.get();
    }
    
    public void put(Source key, Source value) {
      assert !(value.data instanceof Source.RawData);
      put(key, new WeakReference<>(value));
    }
  }
  
  DebuggerSupport.SourceInfo getSourceInfo() {
    return new DebuggerSupport.SourceInfo(getName(), this.data.hashCode(), this.data.url(), this.data.array());
  }
  
  private static interface Data {
    URL url();
    
    int length();
    
    long lastModified();
    
    char[] array();
    
    boolean isEvalCode();
  }
  
  private static class RawData implements Data {
    private final char[] array;
    
    private final boolean evalCode;
    
    private int hash;
    
    private RawData(char[] array, boolean evalCode) {
      this.array = Objects.<char[]>requireNonNull(array);
      this.evalCode = evalCode;
    }
    
    private RawData(String source, boolean evalCode) {
      this.array = ((String)Objects.<String>requireNonNull(source)).toCharArray();
      this.evalCode = evalCode;
    }
    
    private RawData(Reader reader) throws IOException {
      this(Source.readFully(reader), false);
    }
    
    public int hashCode() {
      int h = this.hash;
      if (h == 0)
        h = this.hash = Arrays.hashCode(this.array) ^ (this.evalCode ? 1 : 0); 
      return h;
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj instanceof RawData) {
        RawData other = (RawData)obj;
        return (Arrays.equals(this.array, other.array) && this.evalCode == other.evalCode);
      } 
      return false;
    }
    
    public String toString() {
      return new String(array());
    }
    
    public URL url() {
      return null;
    }
    
    public int length() {
      return this.array.length;
    }
    
    public long lastModified() {
      return 0L;
    }
    
    public char[] array() {
      return this.array;
    }
    
    public boolean isEvalCode() {
      return this.evalCode;
    }
  }
  
  private static class URLData implements Data {
    private final URL url;
    
    protected final Charset cs;
    
    private int hash;
    
    protected char[] array;
    
    protected int length;
    
    protected long lastModified;
    
    private URLData(URL url, Charset cs) {
      this.url = Objects.<URL>requireNonNull(url);
      this.cs = cs;
    }
    
    public int hashCode() {
      int h = this.hash;
      if (h == 0)
        h = this.hash = this.url.hashCode(); 
      return h;
    }
    
    public boolean equals(Object other) {
      if (this == other)
        return true; 
      if (!(other instanceof URLData))
        return false; 
      URLData otherData = (URLData)other;
      if (this.url.equals(otherData.url)) {
        try {
          if (isDeferred()) {
            assert !otherData.isDeferred();
            loadMeta();
          } else if (otherData.isDeferred()) {
            otherData.loadMeta();
          } 
        } catch (IOException e) {
          throw new RuntimeException(e);
        } 
        return (this.length == otherData.length && this.lastModified == otherData.lastModified);
      } 
      return false;
    }
    
    public String toString() {
      return new String(array());
    }
    
    public URL url() {
      return this.url;
    }
    
    public int length() {
      return this.length;
    }
    
    public long lastModified() {
      return this.lastModified;
    }
    
    public char[] array() {
      assert !isDeferred();
      return this.array;
    }
    
    public boolean isEvalCode() {
      return false;
    }
    
    boolean isDeferred() {
      return (this.array == null);
    }
    
    protected void checkPermissionAndClose() throws IOException {
      InputStream in = this.url.openStream();
      if (in != null)
        in.close(); 
      Source.debug(new Object[] { "permission checked for ", this.url });
    }
    
    protected void load() throws IOException {
      if (this.array == null) {
        URLConnection c = this.url.openConnection();
        InputStream in = c.getInputStream();
        try {
          this.array = (this.cs == null) ? Source.readFully(in) : Source.readFully(in, this.cs);
          this.length = this.array.length;
          this.lastModified = c.getLastModified();
          Source.debug(new Object[] { "loaded content for ", this.url });
          if (in != null)
            in.close(); 
        } catch (Throwable throwable) {
          if (in != null)
            try {
              in.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
      } 
    }
    
    protected void loadMeta() throws IOException {
      if (this.length == 0 && this.lastModified == 0L) {
        URLConnection c = this.url.openConnection();
        InputStream in = c.getInputStream();
        try {
          this.length = c.getContentLength();
          this.lastModified = c.getLastModified();
          Source.debug(new Object[] { "loaded metadata for ", this.url });
          if (in != null)
            in.close(); 
        } catch (Throwable throwable) {
          if (in != null)
            try {
              in.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
      } 
    }
  }
  
  private static class FileData extends URLData {
    private final File file;
    
    private FileData(File file, Charset cs) {
      super(Source.getURLFromFile(file), cs);
      this.file = file;
    }
    
    protected void checkPermissionAndClose() throws IOException {
      if (!this.file.canRead())
        throw new FileNotFoundException("" + this.file + " (Permission Denied)"); 
      Source.debug(new Object[] { "permission checked for ", this.file });
    }
    
    protected void loadMeta() {
      if (this.length == 0 && this.lastModified == 0L) {
        this.length = (int)this.file.length();
        this.lastModified = this.file.lastModified();
        Source.debug(new Object[] { "loaded metadata for ", this.file });
      } 
    }
    
    protected void load() throws IOException {
      if (this.array == null) {
        this.array = (this.cs == null) ? Source.readFully(this.file) : Source.readFully(this.file, this.cs);
        this.length = this.array.length;
        this.lastModified = this.file.lastModified();
        Source.debug(new Object[] { "loaded content for ", this.file });
      } 
    }
  }
  
  private static void debug(Object... msg) {
    DebugLogger logger = getLoggerStatic();
    if (logger != null)
      logger.info(msg); 
  }
  
  private char[] data() {
    return this.data.array();
  }
  
  public static Source sourceFor(String name, char[] content, boolean isEval) {
    return new Source(name, baseName(name), new RawData(content, isEval));
  }
  
  public static Source sourceFor(String name, char[] content) {
    return sourceFor(name, content, false);
  }
  
  public static Source sourceFor(String name, String content, boolean isEval) {
    return new Source(name, baseName(name), new RawData(content, isEval));
  }
  
  public static Source sourceFor(String name, String content) {
    return sourceFor(name, content, false);
  }
  
  public static Source sourceFor(String name, URL url) throws IOException {
    return sourceFor(name, url, (Charset)null);
  }
  
  public static Source sourceFor(String name, URL url, Charset cs) throws IOException {
    return sourceFor(name, baseURL(url), new URLData(url, cs));
  }
  
  public static Source sourceFor(String name, File file) throws IOException {
    return sourceFor(name, file, (Charset)null);
  }
  
  public static Source sourceFor(String name, Path path) throws IOException {
    File file = null;
    try {
      file = path.toFile();
    } catch (UnsupportedOperationException unsupportedOperationException) {}
    if (file != null)
      return sourceFor(name, file); 
    return sourceFor(name, Files.newBufferedReader(path));
  }
  
  public static Source sourceFor(String name, File file, Charset cs) throws IOException {
    File absFile = file.getAbsoluteFile();
    return sourceFor(name, dirName(absFile, null), new FileData(file, cs));
  }
  
  public static Source sourceFor(String name, Reader reader) throws IOException {
    if (reader instanceof URLReader) {
      URLReader urlReader = (URLReader)reader;
      return sourceFor(name, urlReader.getURL(), urlReader.getCharset());
    } 
    return new Source(name, baseName(name), new RawData(reader));
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof Source))
      return false; 
    Source other = (Source)obj;
    return (Objects.equals(this.name, other.name) && this.data.equals(other.data));
  }
  
  public int hashCode() {
    int h = this.hash;
    if (h == 0)
      h = this.hash = this.data.hashCode() ^ Objects.hashCode(this.name); 
    return h;
  }
  
  public String getString() {
    return this.data.toString();
  }
  
  public String getName() {
    return this.name;
  }
  
  public long getLastModified() {
    return this.data.lastModified();
  }
  
  public String getBase() {
    return this.base;
  }
  
  public String getString(int start, int len) {
    return new String(data(), start, len);
  }
  
  public String getString(long token) {
    return getString(Token.descPosition(token), Token.descLength(token));
  }
  
  public URL getURL() {
    return this.data.url();
  }
  
  public String getExplicitURL() {
    return this.explicitURL;
  }
  
  public void setExplicitURL(String explicitURL) {
    this.explicitURL = explicitURL;
  }
  
  public boolean isEvalCode() {
    return this.data.isEvalCode();
  }
  
  private int findBOLN(int position) {
    char[] d = data();
    for (int i = position - 1; i > 0; i--) {
      char ch = d[i];
      if (ch == '\n' || ch == '\r')
        return i + 1; 
    } 
    return 0;
  }
  
  private int findEOLN(int position) {
    char[] d = data();
    int length = d.length;
    for (int i = position; i < length; i++) {
      char ch = d[i];
      if (ch == '\n' || ch == '\r')
        return i - 1; 
    } 
    return length - 1;
  }
  
  public int getLine(int position) {
    char[] d = data();
    int line = 1;
    for (int i = 0; i < position; i++) {
      char ch = d[i];
      if (ch == '\n')
        line++; 
    } 
    return line;
  }
  
  public int getColumn(int position) {
    return position - findBOLN(position);
  }
  
  public String getSourceLine(int position) {
    int first = findBOLN(position);
    int last = findEOLN(position);
    return getString(first, last - first + 1);
  }
  
  public char[] getContent() {
    return data();
  }
  
  public int getLength() {
    return this.data.length();
  }
  
  public static char[] readFully(Reader reader) throws IOException {
    char[] arr = new char[8192];
    StringBuilder sb = new StringBuilder();
    Reader reader1 = reader;
    try {
      int numChars;
      while ((numChars = reader.read(arr, 0, arr.length)) > 0)
        sb.append(arr, 0, numChars); 
      if (reader1 != null)
        reader1.close(); 
    } catch (Throwable throwable) {
      if (reader1 != null)
        try {
          reader1.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
    return sb.toString().toCharArray();
  }
  
  public static char[] readFully(File file) throws IOException {
    if (!file.isFile())
      throw new IOException("" + file + " is not a file"); 
    return byteToCharArray(Files.readAllBytes(file.toPath()));
  }
  
  public static char[] readFully(File file, Charset cs) throws IOException {
    if (!file.isFile())
      throw new IOException("" + file + " is not a file"); 
    byte[] buf = Files.readAllBytes(file.toPath());
    return (cs != null) ? (new String(buf, cs)).toCharArray() : byteToCharArray(buf);
  }
  
  public static char[] readFully(URL url) throws IOException {
    return readFully(url.openStream());
  }
  
  public static char[] readFully(URL url, Charset cs) throws IOException {
    return readFully(url.openStream(), cs);
  }
  
  public String getDigest() {
    return new String(getDigestBytes(), StandardCharsets.US_ASCII);
  }
  
  private byte[] getDigestBytes() {
    byte[] ldigest = this.digest;
    if (ldigest == null) {
      char[] content = data();
      byte[] bytes = new byte[content.length * 2];
      for (int i = 0; i < content.length; i++) {
        bytes[i * 2] = (byte)(content[i] & 0xFF);
        bytes[i * 2 + 1] = (byte)((content[i] & 0xFF00) >> 8);
      } 
      try {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        if (this.name != null)
          md.update(this.name.getBytes(StandardCharsets.UTF_8)); 
        if (this.base != null)
          md.update(this.base.getBytes(StandardCharsets.UTF_8)); 
        if (getURL() != null)
          md.update(getURL().toString().getBytes(StandardCharsets.UTF_8)); 
        this.digest = ldigest = BASE64.encode(md.digest(bytes));
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      } 
    } 
    return ldigest;
  }
  
  public static String baseURL(URL url) {
    try {
      URI uri = url.toURI();
      if (uri.getScheme().equals("file")) {
        Path path = Paths.get(uri);
        Path parent = path.getParent();
        return (parent != null) ? ("" + parent + parent) : null;
      } 
      if (uri.isOpaque() || uri.getPath() == null || uri.getPath().isEmpty())
        return null; 
      return uri.resolve("").toString();
    } catch (SecurityException|java.net.URISyntaxException|java.io.IOError e) {
      return null;
    } 
  }
  
  private static String dirName(File file, String DEFAULT_BASE_NAME) {
    String res = file.getParent();
    return (res != null) ? (res + res) : DEFAULT_BASE_NAME;
  }
  
  private static String baseName(String name) {
    int idx = name.lastIndexOf('/');
    if (idx == -1)
      idx = name.lastIndexOf('\\'); 
    return (idx != -1) ? name.substring(0, idx + 1) : null;
  }
  
  private static char[] readFully(InputStream is, Charset cs) throws IOException {
    return (cs != null) ? (new String(readBytes(is), cs)).toCharArray() : readFully(is);
  }
  
  public static char[] readFully(InputStream is) throws IOException {
    return byteToCharArray(readBytes(is));
  }
  
  private static char[] byteToCharArray(byte[] bytes) {
    Charset cs = StandardCharsets.UTF_8;
    int start = 0;
    if (bytes.length > 1 && bytes[0] == -2 && bytes[1] == -1) {
      start = 2;
      cs = StandardCharsets.UTF_16BE;
    } else if (bytes.length > 1 && bytes[0] == -1 && bytes[1] == -2) {
      if (bytes.length > 3 && bytes[2] == 0 && bytes[3] == 0) {
        start = 4;
        cs = Charset.forName("UTF-32LE");
      } else {
        start = 2;
        cs = StandardCharsets.UTF_16LE;
      } 
    } else if (bytes.length > 2 && bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) {
      start = 3;
      cs = StandardCharsets.UTF_8;
    } else if (bytes.length > 3 && bytes[0] == 0 && bytes[1] == 0 && bytes[2] == -2 && bytes[3] == -1) {
      start = 4;
      cs = Charset.forName("UTF-32BE");
    } 
    return (new String(bytes, start, bytes.length - start, cs)).toCharArray();
  }
  
  static byte[] readBytes(InputStream is) throws IOException {
    byte[] arr = new byte[8192];
    InputStream inputStream = is;
    try {
      ByteArrayOutputStream buf = new ByteArrayOutputStream();
      try {
        int numBytes;
        while ((numBytes = is.read(arr, 0, arr.length)) > 0)
          buf.write(arr, 0, numBytes); 
        byte[] arrayOfByte = buf.toByteArray();
        buf.close();
        if (inputStream != null)
          inputStream.close(); 
        return arrayOfByte;
      } catch (Throwable throwable) {
        try {
          buf.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (Throwable throwable) {
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
  }
  
  public String toString() {
    return getName();
  }
  
  private static URL getURLFromFile(File file) {
    try {
      return file.toURI().toURL();
    } catch (SecurityException|java.net.MalformedURLException ignored) {
      return null;
    } 
  }
  
  private static DebugLogger getLoggerStatic() {
    Context context = Context.getContextTrustedOrNull();
    return (context == null) ? null : context.getLogger((Class)Source.class);
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger((Class)getClass());
  }
  
  public DebugLogger getLogger() {
    return initLogger(Context.getContextTrusted());
  }
  
  private File dumpFile(File dirFile) {
    URL u = getURL();
    StringBuilder buf = new StringBuilder();
    buf.append(LocalDateTime.now().toString());
    buf.append('_');
    if (u != null) {
      buf.append(u.toString()
          .replace('/', '_')
          .replace('\\', '_'));
    } else {
      buf.append(getName());
    } 
    return new File(dirFile, buf.toString());
  }
  
  void dump(String dir) {
    File dirFile = new File(dir);
    File file = dumpFile(dirFile);
    if (!dirFile.exists() && !dirFile.mkdirs()) {
      debug(new Object[] { "Skipping source dump for " + this.name });
      return;
    } 
    try {
      FileOutputStream fos = new FileOutputStream(file);
      try {
        PrintWriter pw = new PrintWriter(fos);
        pw.print(this.data.toString());
        pw.flush();
        fos.close();
      } catch (Throwable throwable) {
        try {
          fos.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (IOException ioExp) {
      debug(new Object[] { "Skipping source dump for " + this.name + ": " + 
            
            ECMAErrors.getMessage("io.error.cant.write", new String[] { dir + " : " + dir }) });
    } 
  }
}
