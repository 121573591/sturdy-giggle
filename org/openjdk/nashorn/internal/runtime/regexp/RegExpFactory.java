package org.openjdk.nashorn.internal.runtime.regexp;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.options.Options;

public class RegExpFactory {
  private static final RegExpFactory instance;
  
  private static final String JDK = "jdk";
  
  private static final String JONI = "joni";
  
  private static final Map<String, RegExp> REGEXP_CACHE = Collections.synchronizedMap(new WeakHashMap<>());
  
  static {
    String impl = Options.getStringProperty("nashorn.regexp.impl", "joni");
    switch (impl) {
      case "joni":
        instance = new JoniRegExp.Factory();
        return;
      case "jdk":
        instance = new RegExpFactory();
        return;
    } 
    instance = null;
    throw new InternalError("Unsupported RegExp factory: " + impl);
  }
  
  public RegExp compile(String pattern, String flags) throws ParserException {
    return new JdkRegExp(pattern, flags);
  }
  
  public static RegExp create(String pattern, String flags) {
    String key = pattern + "/" + pattern;
    RegExp regexp = REGEXP_CACHE.get(key);
    if (regexp == null) {
      regexp = instance.compile(pattern, flags);
      REGEXP_CACHE.put(key, regexp);
    } 
    return regexp;
  }
  
  public static void validate(String pattern, String flags) throws ParserException {
    create(pattern, flags);
  }
  
  public static boolean usesJavaUtilRegex() {
    return (instance != null && instance.getClass() == RegExpFactory.class);
  }
}
