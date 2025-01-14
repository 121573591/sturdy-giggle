package junit.runner;

public class SimpleTestCollector extends ClassPathTestCollector {
  protected boolean isTestClass(String classFileName) {
    return (classFileName.endsWith(".class") && classFileName.indexOf('$') < 0 && classFileName.indexOf("Test") > 0);
  }
}
