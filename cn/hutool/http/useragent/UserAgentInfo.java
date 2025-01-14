package cn.hutool.http.useragent;

import cn.hutool.core.util.ReUtil;
import java.io.Serializable;
import java.util.regex.Pattern;

public class UserAgentInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final String NameUnknown = "Unknown";
  
  private final String name;
  
  private final Pattern pattern;
  
  public UserAgentInfo(String name, String regex) {
    this(name, (null == regex) ? null : Pattern.compile(regex, 2));
  }
  
  public UserAgentInfo(String name, Pattern pattern) {
    this.name = name;
    this.pattern = pattern;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Pattern getPattern() {
    return this.pattern;
  }
  
  public boolean isMatch(String content) {
    return ReUtil.contains(this.pattern, content);
  }
  
  public boolean isUnknown() {
    return "Unknown".equals(this.name);
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    UserAgentInfo other = (UserAgentInfo)obj;
    if (this.name == null)
      return (other.name == null); 
    return this.name.equals(other.name);
  }
  
  public String toString() {
    return this.name;
  }
}
