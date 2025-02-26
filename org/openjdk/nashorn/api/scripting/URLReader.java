package org.openjdk.nashorn.api.scripting;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;
import org.openjdk.nashorn.internal.runtime.Source;

public final class URLReader extends Reader {
  private final URL url;
  
  private final Charset cs;
  
  private Reader reader;
  
  public URLReader(URL url) {
    this(url, (Charset)null);
  }
  
  public URLReader(URL url, String charsetName) {
    this(url, Charset.forName(charsetName));
  }
  
  public URLReader(URL url, Charset cs) {
    this.url = Objects.<URL>requireNonNull(url);
    this.cs = cs;
  }
  
  public int read(char[] cbuf, int off, int len) throws IOException {
    return getReader().read(cbuf, off, len);
  }
  
  public void close() throws IOException {
    getReader().close();
  }
  
  public URL getURL() {
    return this.url;
  }
  
  public Charset getCharset() {
    return this.cs;
  }
  
  private Reader getReader() throws IOException {
    synchronized (this.lock) {
      if (this.reader == null)
        this.reader = new CharArrayReader(Source.readFully(this.url, this.cs)); 
    } 
    return this.reader;
  }
}
