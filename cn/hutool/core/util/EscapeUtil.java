package cn.hutool.core.util;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.text.escape.Html4Escape;
import cn.hutool.core.text.escape.Html4Unescape;
import cn.hutool.core.text.escape.XmlEscape;
import cn.hutool.core.text.escape.XmlUnescape;

public class EscapeUtil {
  private static final String NOT_ESCAPE_CHARS = "*@-_+./";
  
  private static final Filter<Character> JS_ESCAPE_FILTER;
  
  static {
    JS_ESCAPE_FILTER = (c -> (false == ((Character.isDigit(c.charValue()) || Character.isLowerCase(c.charValue()) || Character.isUpperCase(c.charValue()) || StrUtil.contains("*@-_+./", c.charValue())) ? true : false)));
  }
  
  public static String escapeXml(CharSequence xml) {
    XmlEscape escape = new XmlEscape();
    return escape.replace(xml).toString();
  }
  
  public static String unescapeXml(CharSequence xml) {
    XmlUnescape unescape = new XmlUnescape();
    return unescape.replace(xml).toString();
  }
  
  public static String escapeHtml4(CharSequence html) {
    Html4Escape escape = new Html4Escape();
    return escape.replace(html).toString();
  }
  
  public static String unescapeHtml4(CharSequence html) {
    Html4Unescape unescape = new Html4Unescape();
    return unescape.replace(html).toString();
  }
  
  public static String escape(CharSequence content) {
    return escape(content, JS_ESCAPE_FILTER);
  }
  
  public static String escapeAll(CharSequence content) {
    return escape(content, c -> true);
  }
  
  public static String escape(CharSequence content, Filter<Character> filter) {
    if (StrUtil.isEmpty(content))
      return StrUtil.str(content); 
    StringBuilder tmp = new StringBuilder(content.length() * 6);
    for (int i = 0; i < content.length(); i++) {
      char c = content.charAt(i);
      if (false == filter.accept(Character.valueOf(c))) {
        tmp.append(c);
      } else if (c < 'Ā') {
        tmp.append("%");
        if (c < '\020')
          tmp.append("0"); 
        tmp.append(Integer.toString(c, 16));
      } else {
        tmp.append("%u");
        if (c <= '࿿')
          tmp.append("0"); 
        tmp.append(Integer.toString(c, 16));
      } 
    } 
    return tmp.toString();
  }
  
  public static String unescape(String content) {
    if (StrUtil.isBlank(content))
      return content; 
    StringBuilder tmp = new StringBuilder(content.length());
    int lastPos = 0;
    while (lastPos < content.length()) {
      int pos = content.indexOf("%", lastPos);
      if (pos == lastPos) {
        if (content.charAt(pos + 1) == 'u') {
          char c = (char)Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
          tmp.append(c);
          lastPos = pos + 6;
          continue;
        } 
        char ch = (char)Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
        tmp.append(ch);
        lastPos = pos + 3;
        continue;
      } 
      if (pos == -1) {
        tmp.append(content.substring(lastPos));
        lastPos = content.length();
        continue;
      } 
      tmp.append(content, lastPos, pos);
      lastPos = pos;
    } 
    return tmp.toString();
  }
  
  public static String safeUnescape(String content) {
    try {
      return unescape(content);
    } catch (Exception exception) {
      return content;
    } 
  }
}
