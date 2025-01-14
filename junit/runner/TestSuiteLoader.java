package junit.runner;

public interface TestSuiteLoader {
  Class load(String paramString) throws ClassNotFoundException;
  
  Class reload(Class paramClass) throws ClassNotFoundException;
}
