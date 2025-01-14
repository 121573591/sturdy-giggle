package cn.hutool.core.lang.ansi;

public abstract class AnsiEncoder {
  private static final String ENCODE_JOIN = ";";
  
  private static final String ENCODE_START = "\033[";
  
  private static final String ENCODE_END = "m";
  
  private static final String RESET = "0;" + AnsiColor.DEFAULT;
  
  public static String encode(Object... elements) {
    StringBuilder sb = new StringBuilder();
    buildEnabled(sb, elements);
    return sb.toString();
  }
  
  private static void buildEnabled(StringBuilder sb, Object[] elements) {
    boolean writingAnsi = false;
    boolean containsEncoding = false;
    for (Object element : elements) {
      if (null != element) {
        if (element instanceof AnsiElement) {
          containsEncoding = true;
          if (writingAnsi) {
            sb.append(";");
          } else {
            sb.append("\033[");
            writingAnsi = true;
          } 
        } else if (writingAnsi) {
          sb.append("m");
          writingAnsi = false;
        } 
        sb.append(element);
      } 
    } 
    if (containsEncoding) {
      sb.append(writingAnsi ? ";" : "\033[");
      sb.append(RESET);
      sb.append("m");
    } 
  }
}
