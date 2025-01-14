package cn.hutool.core.net;

import cn.hutool.core.codec.PercentCodec;

public class RFC3986 {
  public static final PercentCodec GEN_DELIMS = PercentCodec.of(":/?#[]@");
  
  public static final PercentCodec SUB_DELIMS = PercentCodec.of("!$&'()*+,;=");
  
  public static final PercentCodec RESERVED = GEN_DELIMS.orNew(SUB_DELIMS);
  
  public static final PercentCodec UNRESERVED = PercentCodec.of(unreservedChars());
  
  public static final PercentCodec PCHAR = UNRESERVED.orNew(SUB_DELIMS).or(PercentCodec.of(":@"));
  
  public static final PercentCodec SEGMENT = PCHAR;
  
  public static final PercentCodec SEGMENT_NZ_NC = PercentCodec.of(SEGMENT).removeSafe(':');
  
  public static final PercentCodec PATH = SEGMENT.orNew(PercentCodec.of("/"));
  
  public static final PercentCodec QUERY = PCHAR.orNew(PercentCodec.of("/?"));
  
  public static final PercentCodec FRAGMENT = QUERY;
  
  public static final PercentCodec QUERY_PARAM_VALUE = PercentCodec.of(QUERY).removeSafe('&');
  
  public static final PercentCodec QUERY_PARAM_VALUE_STRICT = UNRESERVED;
  
  public static final PercentCodec QUERY_PARAM_NAME = PercentCodec.of(QUERY_PARAM_VALUE).removeSafe('=');
  
  public static final PercentCodec QUERY_PARAM_NAME_STRICT = UNRESERVED;
  
  private static StringBuilder unreservedChars() {
    StringBuilder sb = new StringBuilder();
    char c;
    for (c = 'A'; c <= 'Z'; c = (char)(c + 1))
      sb.append(c); 
    for (c = 'a'; c <= 'z'; c = (char)(c + 1))
      sb.append(c); 
    for (c = '0'; c <= '9'; c = (char)(c + 1))
      sb.append(c); 
    sb.append("_.-~");
    return sb;
  }
}
