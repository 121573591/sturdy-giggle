package io.netty.handler.codec.http;

import io.netty.util.CharsetUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class QueryStringEncoder {
  private final Charset charset;
  
  private final StringBuilder uriBuilder;
  
  private boolean hasParams;
  
  private static final byte WRITE_UTF_UNKNOWN = 63;
  
  private static final char[] CHAR_MAP = "0123456789ABCDEF".toCharArray();
  
  public QueryStringEncoder(String uri) {
    this(uri, HttpConstants.DEFAULT_CHARSET);
  }
  
  public QueryStringEncoder(String uri, Charset charset) {
    ObjectUtil.checkNotNull(charset, "charset");
    this.uriBuilder = new StringBuilder(uri);
    this.charset = CharsetUtil.UTF_8.equals(charset) ? null : charset;
  }
  
  public void addParam(String name, String value) {
    ObjectUtil.checkNotNull(name, "name");
    if (this.hasParams) {
      this.uriBuilder.append('&');
    } else {
      this.uriBuilder.append('?');
      this.hasParams = true;
    } 
    encodeComponent(name);
    if (value != null) {
      this.uriBuilder.append('=');
      encodeComponent(value);
    } 
  }
  
  private void encodeComponent(CharSequence s) {
    if (this.charset == null) {
      encodeUtf8Component(s);
    } else {
      encodeNonUtf8Component(s);
    } 
  }
  
  public URI toUri() throws URISyntaxException {
    return new URI(toString());
  }
  
  public String toString() {
    return this.uriBuilder.toString();
  }
  
  private void encodeNonUtf8Component(CharSequence s) {
    char[] buf = null;
    for (int i = 0, len = s.length(); i < len; ) {
      char c = s.charAt(i);
      if (dontNeedEncoding(c)) {
        this.uriBuilder.append(c);
        i++;
        continue;
      } 
      int index = 0;
      if (buf == null)
        buf = new char[s.length() - i]; 
      do {
        buf[index] = c;
        index++;
        ++i;
      } while (i < s.length() && !dontNeedEncoding(c = s.charAt(i)));
      byte[] bytes = (new String(buf, 0, index)).getBytes(this.charset);
      for (byte b : bytes)
        appendEncoded(b); 
    } 
  }
  
  private void encodeUtf8Component(CharSequence s) {
    for (int i = 0, len = s.length(); i < len; i++) {
      char c = s.charAt(i);
      if (!dontNeedEncoding(c)) {
        encodeUtf8Component(s, i, len);
        return;
      } 
    } 
    this.uriBuilder.append(s);
  }
  
  private void encodeUtf8Component(CharSequence s, int encodingStart, int len) {
    if (encodingStart > 0)
      this.uriBuilder.append(s, 0, encodingStart); 
    encodeUtf8ComponentSlow(s, encodingStart, len);
  }
  
  private void encodeUtf8ComponentSlow(CharSequence s, int start, int len) {
    for (int i = start; i < len; i++) {
      char c = s.charAt(i);
      if (c < '') {
        if (dontNeedEncoding(c)) {
          this.uriBuilder.append(c);
        } else {
          appendEncoded(c);
        } 
      } else if (c < 'ࠀ') {
        appendEncoded(0xC0 | c >> 6);
        appendEncoded(0x80 | c & 0x3F);
      } else if (StringUtil.isSurrogate(c)) {
        if (!Character.isHighSurrogate(c)) {
          appendEncoded(63);
        } else {
          if (++i == s.length()) {
            appendEncoded(63);
            break;
          } 
          writeUtf8Surrogate(c, s.charAt(i));
        } 
      } else {
        appendEncoded(0xE0 | c >> 12);
        appendEncoded(0x80 | c >> 6 & 0x3F);
        appendEncoded(0x80 | c & 0x3F);
      } 
    } 
  }
  
  private void writeUtf8Surrogate(char c, char c2) {
    if (!Character.isLowSurrogate(c2)) {
      appendEncoded(63);
      appendEncoded(Character.isHighSurrogate(c2) ? 63 : c2);
      return;
    } 
    int codePoint = Character.toCodePoint(c, c2);
    appendEncoded(0xF0 | codePoint >> 18);
    appendEncoded(0x80 | codePoint >> 12 & 0x3F);
    appendEncoded(0x80 | codePoint >> 6 & 0x3F);
    appendEncoded(0x80 | codePoint & 0x3F);
  }
  
  private void appendEncoded(int b) {
    this.uriBuilder.append('%').append(forDigit(b >> 4)).append(forDigit(b));
  }
  
  private static char forDigit(int digit) {
    return CHAR_MAP[digit & 0xF];
  }
  
  private static boolean dontNeedEncoding(char ch) {
    return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '-' || ch == '_' || ch == '.' || ch == '*' || ch == '~');
  }
}
