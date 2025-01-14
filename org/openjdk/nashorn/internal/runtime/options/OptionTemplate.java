package org.openjdk.nashorn.internal.runtime.options;

import java.util.Locale;
import java.util.TimeZone;
import org.openjdk.nashorn.internal.runtime.QuotedStringTokenizer;

public final class OptionTemplate implements Comparable<OptionTemplate> {
  private final String resource;
  
  private final String key;
  
  private final boolean isHelp;
  
  private final boolean isXHelp;
  
  private String name;
  
  private String shortName;
  
  private String params;
  
  private String type;
  
  private String defaultValue;
  
  private String dependency;
  
  private String conflict;
  
  private boolean isUndocumented;
  
  private String description;
  
  private boolean valueNextArg;
  
  private boolean repeated;
  
  private static final int LINE_BREAK = 64;
  
  OptionTemplate(String resource, String key, String value, boolean isHelp, boolean isXHelp) {
    this.resource = resource;
    this.key = key;
    this.isHelp = isHelp;
    this.isXHelp = isXHelp;
    parse(value);
  }
  
  public boolean isHelp() {
    return this.isHelp;
  }
  
  public boolean isXHelp() {
    return this.isXHelp;
  }
  
  public String getResource() {
    return this.resource;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public String getDefaultValue() {
    switch (getType()) {
      case "boolean":
        if (this.defaultValue == null)
          this.defaultValue = "false"; 
        break;
      case "integer":
        if (this.defaultValue == null)
          this.defaultValue = "0"; 
        break;
      case "timezone":
        this.defaultValue = TimeZone.getDefault().getID();
        break;
      case "locale":
        this.defaultValue = Locale.getDefault().toLanguageTag();
        break;
    } 
    return this.defaultValue;
  }
  
  public String getDependency() {
    return this.dependency;
  }
  
  public String getConflict() {
    return this.conflict;
  }
  
  public boolean isUndocumented() {
    return this.isUndocumented;
  }
  
  public String getShortName() {
    return this.shortName;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public boolean isValueNextArg() {
    return this.valueNextArg;
  }
  
  public boolean isRepeated() {
    return this.repeated;
  }
  
  private static String strip(String value, char start, char end) {
    int len = value.length();
    if (len > 1 && value.charAt(0) == start && value.charAt(len - 1) == end)
      return value.substring(1, len - 1); 
    return null;
  }
  
  private void parse(String origValue) {
    String value = origValue.trim();
    try {
      value = strip(value, '{', '}');
      QuotedStringTokenizer keyValuePairs = new QuotedStringTokenizer(value, ",");
      while (keyValuePairs.hasMoreTokens()) {
        String keyValue = keyValuePairs.nextToken();
        QuotedStringTokenizer st = new QuotedStringTokenizer(keyValue, "=");
        String keyToken = st.nextToken();
        String arg = st.nextToken();
        switch (keyToken) {
          case "is_undocumented":
            this.isUndocumented = Boolean.parseBoolean(arg);
            continue;
          case "name":
            if (!arg.startsWith("-"))
              throw new IllegalArgumentException(arg); 
            this.name = arg;
            continue;
          case "short_name":
            if (!arg.startsWith("-"))
              throw new IllegalArgumentException(arg); 
            this.shortName = arg;
            continue;
          case "desc":
            this.description = arg;
            continue;
          case "params":
            this.params = arg;
            continue;
          case "type":
            this.type = arg.toLowerCase(Locale.ENGLISH);
            continue;
          case "default":
            this.defaultValue = arg;
            continue;
          case "dependency":
            this.dependency = arg;
            continue;
          case "conflict":
            this.conflict = arg;
            continue;
          case "value_next_arg":
            this.valueNextArg = Boolean.parseBoolean(arg);
            continue;
          case "repeated":
            this.repeated = true;
            continue;
        } 
        throw new IllegalArgumentException(keyToken);
      } 
      if (this.type == null)
        this.type = "boolean"; 
      if (this.params == null && "boolean".equals(this.type))
        this.params = "[true|false]"; 
    } catch (Exception e) {
      throw new IllegalArgumentException(origValue, e);
    } 
    if (this.name == null && this.shortName == null)
      throw new IllegalArgumentException(origValue); 
    if (this.repeated && !"string".equals(this.type))
      throw new IllegalArgumentException("repeated option should be of type string: " + this.name); 
  }
  
  boolean nameMatches(String aName) {
    return (aName.equals(this.shortName) || aName.equals(this.name));
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('\t');
    if (this.shortName != null) {
      sb.append(this.shortName);
      if (this.name != null)
        sb.append(", "); 
    } 
    if (this.name != null)
      sb.append(this.name); 
    if (this.description != null) {
      int indent = sb.length();
      sb.append(' ');
      sb.append('(');
      int pos = 0;
      for (char c : this.description.toCharArray()) {
        sb.append(c);
        pos++;
        if (pos >= 64 && Character.isWhitespace(c)) {
          pos = 0;
          sb.append("\n\t");
          for (int i = 0; i < indent; i++)
            sb.append(' '); 
        } 
      } 
      sb.append(')');
    } 
    if (this.params != null) {
      sb.append('\n');
      sb.append('\t');
      sb.append('\t');
      sb.append(Options.getMsg("nashorn.options.param", new String[0])).append(": ");
      sb.append(this.params);
      sb.append("   ");
      Object def = getDefaultValue();
      if (def != null) {
        sb.append(Options.getMsg("nashorn.options.default", new String[0])).append(": ");
        sb.append(getDefaultValue());
      } 
    } 
    return sb.toString();
  }
  
  public int compareTo(OptionTemplate o) {
    return getKey().compareTo(o.getKey());
  }
}
