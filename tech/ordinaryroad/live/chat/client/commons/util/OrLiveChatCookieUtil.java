package tech.ordinaryroad.live.chat.client.commons.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OrLiveChatCookieUtil {
  public static String toString(List<HttpCookie> cookies) {
    if (CollUtil.isEmpty(cookies))
      return ""; 
    return cookies.stream().map(httpCookie -> {
          httpCookie.setVersion(0);
          return httpCookie.toString();
        }).collect(Collectors.joining("; "));
  }
  
  public static Map<String, String> parseCookieString(String cookies) {
    Map<String, String> map = new HashMap<>();
    if (StrUtil.isNotBlank(cookies) && !StrUtil.isNullOrUndefined(cookies))
      try {
        String[] split = cookies.split("; ");
        for (String s : split) {
          String key = StrUtil.subBefore(s, '=', false);
          String value = StrUtil.subAfter(s, '=', false);
          map.put(key, value);
        } 
      } catch (Exception e) {
        throw new RuntimeException("cookie解析失败 " + cookies, e);
      }  
    return map;
  }
  
  public static String toCookieString(Map<String, String> cookies) {
    if (cookies.isEmpty())
      return null; 
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> stringStringEntry : cookies.entrySet()) {
      String key = stringStringEntry.getKey();
      String value = stringStringEntry.getValue();
      sb.append(key)
        .append("=")
        .append(value)
        .append("; ");
    } 
    int length = sb.length();
    sb.delete(length - 2, length);
    return sb.toString();
  }
  
  public static String getCookieByName(Map<String, String> cookieMap, String name, Supplier<String> supplier) {
    String str = MapUtil.getStr(cookieMap, name);
    return (str == null) ? supplier.get() : str;
  }
  
  public static String getCookieByName(String cookie, String name, Supplier<String> supplier) {
    String str = MapUtil.getStr(parseCookieString(cookie), name);
    return (str == null) ? supplier.get() : str;
  }
}
