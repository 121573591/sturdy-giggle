package cn.hutool.core.util;

public class JdkUtil {
  public static final int JVM_VERSION = _getJvmVersion();
  
  public static final boolean IS_JDK8 = (8 == JVM_VERSION);
  
  public static final boolean IS_AT_LEAST_JDK17 = (JVM_VERSION >= 17);
  
  public static final boolean IS_ANDROID;
  
  static {
    String jvmName = _getJvmName();
    IS_ANDROID = jvmName.equals("Dalvik");
  }
  
  private static String _getJvmName() {
    return System.getProperty("java.vm.name");
  }
  
  private static int _getJvmVersion() {
    int jvmVersion = -1;
    try {
      String javaSpecVer = System.getProperty("java.specification.version");
      if (StrUtil.isNotBlank(javaSpecVer)) {
        if (javaSpecVer.startsWith("1."))
          javaSpecVer = javaSpecVer.substring(2); 
        if (javaSpecVer.indexOf('.') == -1)
          jvmVersion = Integer.parseInt(javaSpecVer); 
      } 
    } catch (Throwable ignore) {
      jvmVersion = 8;
    } 
    return jvmVersion;
  }
}
