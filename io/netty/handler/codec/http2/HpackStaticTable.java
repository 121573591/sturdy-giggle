package io.netty.handler.codec.http2;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.AsciiString;
import io.netty.util.internal.PlatformDependent;
import java.util.Arrays;
import java.util.List;

final class HpackStaticTable {
  static final int NOT_FOUND = -1;
  
  private static final List<HpackHeaderField> STATIC_TABLE = Arrays.asList(new HpackHeaderField[] { 
        newEmptyPseudoHeaderField(Http2Headers.PseudoHeaderName.AUTHORITY), 
        newPseudoHeaderMethodField(HttpMethod.GET), 
        newPseudoHeaderMethodField(HttpMethod.POST), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.PATH, "/"), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.PATH, "/index.html"), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.SCHEME, "http"), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.SCHEME, "https"), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.OK.codeAsText()), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.NO_CONTENT.codeAsText()), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.PARTIAL_CONTENT.codeAsText()), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.NOT_MODIFIED.codeAsText()), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.BAD_REQUEST.codeAsText()), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.NOT_FOUND.codeAsText()), 
        newPseudoHeaderField(Http2Headers.PseudoHeaderName.STATUS, HttpResponseStatus.INTERNAL_SERVER_ERROR.codeAsText()), 
        newEmptyHeaderField(HttpHeaderNames.ACCEPT_CHARSET), 
        newHeaderField(HttpHeaderNames.ACCEPT_ENCODING, "gzip, deflate"), 
        newEmptyHeaderField(HttpHeaderNames.ACCEPT_LANGUAGE), 
        newEmptyHeaderField(HttpHeaderNames.ACCEPT_RANGES), 
        newEmptyHeaderField(HttpHeaderNames.ACCEPT), 
        newEmptyHeaderField(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN), 
        newEmptyHeaderField(HttpHeaderNames.AGE), 
        newEmptyHeaderField(HttpHeaderNames.ALLOW), 
        newEmptyHeaderField(HttpHeaderNames.AUTHORIZATION), 
        newEmptyHeaderField(HttpHeaderNames.CACHE_CONTROL), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_DISPOSITION), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_ENCODING), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_LANGUAGE), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_LENGTH), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_LOCATION), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_RANGE), 
        newEmptyHeaderField(HttpHeaderNames.CONTENT_TYPE), 
        newEmptyHeaderField(HttpHeaderNames.COOKIE), 
        newEmptyHeaderField(HttpHeaderNames.DATE), 
        newEmptyHeaderField(HttpHeaderNames.ETAG), 
        newEmptyHeaderField(HttpHeaderNames.EXPECT), 
        newEmptyHeaderField(HttpHeaderNames.EXPIRES), 
        newEmptyHeaderField(HttpHeaderNames.FROM), 
        newEmptyHeaderField(HttpHeaderNames.HOST), 
        newEmptyHeaderField(HttpHeaderNames.IF_MATCH), 
        newEmptyHeaderField(HttpHeaderNames.IF_MODIFIED_SINCE), 
        newEmptyHeaderField(HttpHeaderNames.IF_NONE_MATCH), 
        newEmptyHeaderField(HttpHeaderNames.IF_RANGE), 
        newEmptyHeaderField(HttpHeaderNames.IF_UNMODIFIED_SINCE), 
        newEmptyHeaderField(HttpHeaderNames.LAST_MODIFIED), 
        newEmptyHeaderField("link"), 
        newEmptyHeaderField(HttpHeaderNames.LOCATION), 
        newEmptyHeaderField(HttpHeaderNames.MAX_FORWARDS), 
        newEmptyHeaderField(HttpHeaderNames.PROXY_AUTHENTICATE), 
        newEmptyHeaderField(HttpHeaderNames.PROXY_AUTHORIZATION), 
        newEmptyHeaderField(HttpHeaderNames.RANGE), 
        newEmptyHeaderField(HttpHeaderNames.REFERER), 
        newEmptyHeaderField("refresh"), 
        newEmptyHeaderField(HttpHeaderNames.RETRY_AFTER), 
        newEmptyHeaderField(HttpHeaderNames.SERVER), 
        newEmptyHeaderField(HttpHeaderNames.SET_COOKIE), 
        newEmptyHeaderField("strict-transport-security"), 
        newEmptyHeaderField(HttpHeaderNames.TRANSFER_ENCODING), 
        newEmptyHeaderField(HttpHeaderNames.USER_AGENT), 
        newEmptyHeaderField(HttpHeaderNames.VARY), 
        newEmptyHeaderField(HttpHeaderNames.VIA), 
        newEmptyHeaderField(HttpHeaderNames.WWW_AUTHENTICATE) });
  
  private static final int HEADER_NAMES_TABLE_SIZE = 512;
  
  private static HpackHeaderField newEmptyHeaderField(AsciiString name) {
    return new HpackHeaderField((CharSequence)name, (CharSequence)AsciiString.EMPTY_STRING);
  }
  
  private static HpackHeaderField newEmptyHeaderField(String name) {
    return new HpackHeaderField((CharSequence)AsciiString.cached(name), (CharSequence)AsciiString.EMPTY_STRING);
  }
  
  private static HpackHeaderField newHeaderField(AsciiString name, String value) {
    return new HpackHeaderField((CharSequence)name, (CharSequence)AsciiString.cached(value));
  }
  
  private static HpackHeaderField newPseudoHeaderMethodField(HttpMethod method) {
    return new HpackHeaderField((CharSequence)Http2Headers.PseudoHeaderName.METHOD.value(), (CharSequence)method.asciiName());
  }
  
  private static HpackHeaderField newPseudoHeaderField(Http2Headers.PseudoHeaderName name, AsciiString value) {
    return new HpackHeaderField((CharSequence)name.value(), (CharSequence)value);
  }
  
  private static HpackHeaderField newPseudoHeaderField(Http2Headers.PseudoHeaderName name, String value) {
    return new HpackHeaderField((CharSequence)name.value(), (CharSequence)AsciiString.cached(value));
  }
  
  private static HpackHeaderField newEmptyPseudoHeaderField(Http2Headers.PseudoHeaderName name) {
    return new HpackHeaderField((CharSequence)name.value(), (CharSequence)AsciiString.EMPTY_STRING);
  }
  
  private static final int HEADER_NAMES_TABLE_SHIFT = PlatformDependent.BIG_ENDIAN_NATIVE_ORDER ? 22 : 18;
  
  private static final HeaderNameIndex[] HEADER_NAMES = new HeaderNameIndex[512];
  
  private static final int HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SIZE = 64;
  
  static {
    int index;
    for (index = STATIC_TABLE.size(); index > 0; index--) {
      HpackHeaderField entry = getEntry(index);
      int bucket = headerNameBucket(entry.name);
      HeaderNameIndex tableEntry = HEADER_NAMES[bucket];
      if (tableEntry != null && !HpackUtil.equalsVariableTime(tableEntry.name, entry.name))
        throw new IllegalStateException("Hash bucket collision between " + tableEntry.name + " and " + entry.name); 
      HEADER_NAMES[bucket] = new HeaderNameIndex(entry.name, index, (entry.value.length() == 0));
    } 
  }
  
  private static final int HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SHIFT = PlatformDependent.BIG_ENDIAN_NATIVE_ORDER ? 0 : 6;
  
  private static final HeaderIndex[] HEADERS_WITH_NON_EMPTY_VALUES = new HeaderIndex[64];
  
  static {
    for (index = STATIC_TABLE.size(); index > 0; index--) {
      HpackHeaderField entry = getEntry(index);
      if (entry.value.length() > 0) {
        int bucket = headerBucket(entry.value);
        HeaderIndex tableEntry = HEADERS_WITH_NON_EMPTY_VALUES[bucket];
        if (tableEntry != null)
          throw new IllegalStateException("Hash bucket collision between " + tableEntry.value + " and " + entry.value); 
        HEADERS_WITH_NON_EMPTY_VALUES[bucket] = new HeaderIndex(entry.name, entry.value, index);
      } 
    } 
  }
  
  static final int length = STATIC_TABLE.size();
  
  static HpackHeaderField getEntry(int index) {
    return STATIC_TABLE.get(index - 1);
  }
  
  static int getIndex(CharSequence name) {
    HeaderNameIndex entry = getEntry(name);
    return (entry == null) ? -1 : entry.index;
  }
  
  static int getIndexInsensitive(CharSequence name, CharSequence value) {
    if (value.length() == 0) {
      HeaderNameIndex entry = getEntry(name);
      return (entry == null || !entry.emptyValue) ? -1 : entry.index;
    } 
    int bucket = headerBucket(value);
    HeaderIndex header = HEADERS_WITH_NON_EMPTY_VALUES[bucket];
    if (header == null)
      return -1; 
    if (HpackUtil.equalsVariableTime(header.name, name) && HpackUtil.equalsVariableTime(header.value, value))
      return header.index; 
    return -1;
  }
  
  private static HeaderNameIndex getEntry(CharSequence name) {
    int bucket = headerNameBucket(name);
    HeaderNameIndex entry = HEADER_NAMES[bucket];
    if (entry == null)
      return null; 
    return HpackUtil.equalsVariableTime(entry.name, name) ? entry : null;
  }
  
  private static int headerNameBucket(CharSequence name) {
    return bucket(name, HEADER_NAMES_TABLE_SHIFT, 511);
  }
  
  private static int headerBucket(CharSequence value) {
    return bucket(value, HEADERS_WITH_NON_EMPTY_VALUES_TABLE_SHIFT, 63);
  }
  
  private static int bucket(CharSequence s, int shift, int mask) {
    return AsciiString.hashCode(s) >> shift & mask;
  }
  
  private static final class HeaderNameIndex {
    final CharSequence name;
    
    final int index;
    
    final boolean emptyValue;
    
    HeaderNameIndex(CharSequence name, int index, boolean emptyValue) {
      this.name = name;
      this.index = index;
      this.emptyValue = emptyValue;
    }
  }
  
  private static final class HeaderIndex {
    final CharSequence name;
    
    final CharSequence value;
    
    final int index;
    
    HeaderIndex(CharSequence name, CharSequence value, int index) {
      this.name = name;
      this.value = value;
      this.index = index;
    }
  }
}
