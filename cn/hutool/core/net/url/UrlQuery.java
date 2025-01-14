package cn.hutool.core.net.url;

import cn.hutool.core.codec.PercentCodec;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.map.TableMap;
import cn.hutool.core.net.FormUrlencoded;
import cn.hutool.core.net.RFC3986;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.StrUtil;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

public class UrlQuery {
  private final TableMap<CharSequence, CharSequence> query;
  
  private final boolean isFormUrlEncoded;
  
  private boolean isStrict;
  
  public static UrlQuery of(Map<? extends CharSequence, ?> queryMap) {
    return new UrlQuery(queryMap);
  }
  
  public static UrlQuery of(Map<? extends CharSequence, ?> queryMap, boolean isFormUrlEncoded) {
    return new UrlQuery(queryMap, isFormUrlEncoded);
  }
  
  public static UrlQuery of(String queryStr, Charset charset) {
    return of(queryStr, charset, true);
  }
  
  public static UrlQuery of(String queryStr, Charset charset, boolean autoRemovePath) {
    return of(queryStr, charset, autoRemovePath, false);
  }
  
  public static UrlQuery of(String queryStr, Charset charset, boolean autoRemovePath, boolean isFormUrlEncoded) {
    return (new UrlQuery(isFormUrlEncoded)).parse(queryStr, charset, autoRemovePath);
  }
  
  public UrlQuery() {
    this((Map<? extends CharSequence, ?>)null);
  }
  
  public UrlQuery(boolean isFormUrlEncoded) {
    this(null, isFormUrlEncoded);
  }
  
  public UrlQuery(Map<? extends CharSequence, ?> queryMap) {
    this(queryMap, false);
  }
  
  public UrlQuery(Map<? extends CharSequence, ?> queryMap, boolean isFormUrlEncoded) {
    if (MapUtil.isNotEmpty(queryMap)) {
      this.query = new TableMap(queryMap.size());
      addAll(queryMap);
    } else {
      this.query = new TableMap(16);
    } 
    this.isFormUrlEncoded = isFormUrlEncoded;
  }
  
  public UrlQuery setStrict(boolean strict) {
    this.isStrict = strict;
    return this;
  }
  
  public UrlQuery add(CharSequence key, Object value) {
    this.query.put(key, toStr(value));
    return this;
  }
  
  public UrlQuery addAll(Map<? extends CharSequence, ?> queryMap) {
    if (MapUtil.isNotEmpty(queryMap))
      queryMap.forEach(this::add); 
    return this;
  }
  
  public UrlQuery parse(String queryStr, Charset charset) {
    return parse(queryStr, charset, true);
  }
  
  public UrlQuery parse(String queryStr, Charset charset, boolean autoRemovePath) {
    if (StrUtil.isBlank(queryStr))
      return this; 
    if (autoRemovePath) {
      int pathEndPos = queryStr.indexOf('?');
      if (pathEndPos > -1) {
        queryStr = StrUtil.subSuf(queryStr, pathEndPos + 1);
        if (StrUtil.isBlank(queryStr))
          return this; 
      } 
    } 
    return doParse(queryStr, charset);
  }
  
  public Map<CharSequence, CharSequence> getQueryMap() {
    return MapUtil.unmodifiable((Map)this.query);
  }
  
  public CharSequence get(CharSequence key) {
    if (MapUtil.isEmpty((Map)this.query))
      return null; 
    return (CharSequence)this.query.get(key);
  }
  
  public String build(Charset charset) {
    return build(charset, true);
  }
  
  public String build(Charset charset, boolean encodePercent) {
    if (this.isFormUrlEncoded)
      return build(FormUrlencoded.ALL, FormUrlencoded.ALL, charset, encodePercent); 
    if (this.isStrict)
      return build(RFC3986.QUERY_PARAM_NAME_STRICT, RFC3986.QUERY_PARAM_VALUE_STRICT, charset, encodePercent); 
    return build(RFC3986.QUERY_PARAM_NAME, RFC3986.QUERY_PARAM_VALUE, charset, encodePercent);
  }
  
  public String build(PercentCodec keyCoder, PercentCodec valueCoder, Charset charset) {
    return build(keyCoder, valueCoder, charset, true);
  }
  
  public String build(PercentCodec keyCoder, PercentCodec valueCoder, Charset charset, boolean encodePercent) {
    if (MapUtil.isEmpty((Map)this.query))
      return ""; 
    (new char[1])[0] = '%';
    char[] safeChars = encodePercent ? null : new char[1];
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<CharSequence, CharSequence> entry : this.query) {
      CharSequence name = entry.getKey();
      if (null != name) {
        if (sb.length() > 0)
          sb.append("&"); 
        sb.append(keyCoder.encode(name, charset, safeChars));
        CharSequence value = entry.getValue();
        if (null != value)
          sb.append("=").append(valueCoder.encode(value, charset, safeChars)); 
      } 
    } 
    return sb.toString();
  }
  
  public String toString() {
    return build(null);
  }
  
  private UrlQuery doParse(String queryStr, Charset charset) {
    int len = queryStr.length();
    String name = null;
    int pos = 0;
    int i;
    for (i = 0; i < len; i++) {
      char c = queryStr.charAt(i);
      switch (c) {
        case '=':
          if (null == name) {
            name = queryStr.substring(pos, i);
            pos = i + 1;
          } 
          break;
        case '&':
          addParam(name, queryStr.substring(pos, i), charset);
          name = null;
          if (i + 4 < len && "amp;".equals(queryStr.substring(i + 1, i + 5)))
            i += 4; 
          pos = i + 1;
          break;
      } 
    } 
    addParam(name, queryStr.substring(pos, i), charset);
    return this;
  }
  
  private static String toStr(Object value) {
    String result;
    if (value instanceof Iterable) {
      result = CollUtil.join((Iterable)value, ",");
    } else if (value instanceof Iterator) {
      result = IterUtil.join((Iterator)value, ",");
    } else {
      result = Convert.toStr(value);
    } 
    return result;
  }
  
  private void addParam(String key, String value, Charset charset) {
    if (null != key) {
      String actualKey = URLDecoder.decode(key, charset, this.isFormUrlEncoded);
      this.query.put(actualKey, StrUtil.nullToEmpty(URLDecoder.decode(value, charset, this.isFormUrlEncoded)));
    } else if (null != value) {
      this.query.put(URLDecoder.decode(value, charset, this.isFormUrlEncoded), null);
    } 
  }
}
