package org.openjdk.nashorn.api.scripting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Formatter {
  private static final String formatSpecifier = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
  
  static String format(String format, Object[] args) {
    Matcher m = FS_PATTERN.matcher(format);
    int positionalParameter = 1;
    while (m.find()) {
      int index = index(m.group(1));
      boolean previous = isPreviousArgument(m.group(2));
      char conversion = m.group(6).charAt(0);
      if (index < 0 || previous || conversion == 'n' || conversion == '%')
        continue; 
      if (index == 0)
        index = positionalParameter++; 
      if (index > args.length)
        continue; 
      Object arg = args[index - 1];
      if (m.group(5) != null) {
        if (arg instanceof Double)
          args[index - 1] = Long.valueOf(((Double)arg).longValue()); 
        continue;
      } 
      switch (conversion) {
        case 'X':
        case 'd':
        case 'o':
        case 'x':
          if (arg instanceof Double) {
            args[index - 1] = Long.valueOf(((Double)arg).longValue());
            continue;
          } 
          if (arg instanceof String && ((String)arg)
            .length() > 0)
            args[index - 1] = Integer.valueOf(((String)arg).charAt(0)); 
        case 'A':
        case 'E':
        case 'G':
        case 'a':
        case 'e':
        case 'f':
        case 'g':
          if (arg instanceof Integer)
            args[index - 1] = Double.valueOf(((Integer)arg).doubleValue()); 
        case 'c':
          if (arg instanceof Double) {
            args[index - 1] = Integer.valueOf(((Double)arg).intValue());
            continue;
          } 
          if (arg instanceof String && ((String)arg)
            .length() > 0)
            args[index - 1] = Integer.valueOf(((String)arg).charAt(0)); 
      } 
    } 
    return String.format(format, args);
  }
  
  private static int index(String s) {
    int index = -1;
    if (s != null) {
      try {
        index = Integer.parseInt(s.substring(0, s.length() - 1));
      } catch (NumberFormatException numberFormatException) {}
    } else {
      index = 0;
    } 
    return index;
  }
  
  private static boolean isPreviousArgument(String s) {
    return (s != null && s.indexOf('<') >= 0);
  }
  
  private static final Pattern FS_PATTERN = Pattern.compile("%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])");
}
