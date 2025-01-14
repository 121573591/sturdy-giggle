package io.netty.handler.codec.http2;

import io.netty.handler.codec.Headers;
import io.netty.util.AsciiString;
import java.util.Iterator;
import java.util.Map;

public interface Http2Headers extends Headers<CharSequence, CharSequence, Http2Headers> {
  Iterator<Map.Entry<CharSequence, CharSequence>> iterator();
  
  Iterator<CharSequence> valueIterator(CharSequence paramCharSequence);
  
  Http2Headers method(CharSequence paramCharSequence);
  
  Http2Headers scheme(CharSequence paramCharSequence);
  
  Http2Headers authority(CharSequence paramCharSequence);
  
  Http2Headers path(CharSequence paramCharSequence);
  
  Http2Headers status(CharSequence paramCharSequence);
  
  CharSequence method();
  
  CharSequence scheme();
  
  CharSequence authority();
  
  CharSequence path();
  
  CharSequence status();
  
  boolean contains(CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean);
  
  public enum PseudoHeaderName {
    METHOD(":method", true),
    SCHEME(":scheme", true),
    AUTHORITY(":authority", true),
    PATH(":path", true),
    STATUS(":status", false),
    PROTOCOL(":protocol", true);
    
    private static final char PSEUDO_HEADER_PREFIX = ':';
    
    private static final byte PSEUDO_HEADER_PREFIX_BYTE = 58;
    
    private final AsciiString value;
    
    private final boolean requestOnly;
    
    PseudoHeaderName(String value, boolean requestOnly) {
      this.value = AsciiString.cached(value);
      this.requestOnly = requestOnly;
    }
    
    public AsciiString value() {
      return this.value;
    }
    
    public static boolean hasPseudoHeaderFormat(CharSequence headerName) {
      if (headerName instanceof AsciiString) {
        AsciiString asciiHeaderName = (AsciiString)headerName;
        return (asciiHeaderName.length() > 0 && asciiHeaderName.byteAt(0) == 58);
      } 
      return (headerName.length() > 0 && headerName.charAt(0) == ':');
    }
    
    public static boolean isPseudoHeader(CharSequence header) {
      return (getPseudoHeader(header) != null);
    }
    
    public static boolean isPseudoHeader(AsciiString header) {
      return (getPseudoHeader(header) != null);
    }
    
    public static boolean isPseudoHeader(String header) {
      return (getPseudoHeader(header) != null);
    }
    
    public static PseudoHeaderName getPseudoHeader(CharSequence header) {
      if (header instanceof AsciiString)
        return getPseudoHeader((AsciiString)header); 
      return getPseudoHeaderName(header);
    }
    
    private static PseudoHeaderName getPseudoHeaderName(CharSequence header) {
      int length = header.length();
      if (length > 0 && header.charAt(0) == ':')
        switch (length) {
          case 5:
            return ":path".contentEquals(header) ? PATH : null;
          case 7:
            if (":method" == header)
              return METHOD; 
            if (":scheme" == header)
              return SCHEME; 
            if (":status" == header)
              return STATUS; 
            if (":method".contentEquals(header))
              return METHOD; 
            if (":scheme".contentEquals(header))
              return SCHEME; 
            return ":status".contentEquals(header) ? STATUS : null;
          case 9:
            return ":protocol".contentEquals(header) ? PROTOCOL : null;
          case 10:
            return ":authority".contentEquals(header) ? AUTHORITY : null;
        }  
      return null;
    }
    
    public static PseudoHeaderName getPseudoHeader(AsciiString header) {
      int length = header.length();
      if (length > 0 && header.charAt(0) == ':')
        switch (length) {
          case 5:
            return PATH.value().equals(header) ? PATH : null;
          case 7:
            if (header == METHOD.value())
              return METHOD; 
            if (header == SCHEME.value())
              return SCHEME; 
            if (header == STATUS.value())
              return STATUS; 
            if (METHOD.value().equals(header))
              return METHOD; 
            if (SCHEME.value().equals(header))
              return SCHEME; 
            return STATUS.value().equals(header) ? STATUS : null;
          case 9:
            return PROTOCOL.value().equals(header) ? PROTOCOL : null;
          case 10:
            return AUTHORITY.value().equals(header) ? AUTHORITY : null;
        }  
      return null;
    }
    
    public boolean isRequestOnly() {
      return this.requestOnly;
    }
  }
}
