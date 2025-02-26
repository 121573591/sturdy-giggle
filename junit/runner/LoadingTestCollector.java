package junit.runner;

import java.lang.reflect.Modifier;
import junit.framework.Test;
import junit.framework.TestSuite;

public class LoadingTestCollector extends ClassPathTestCollector {
  TestCaseClassLoader fLoader = new TestCaseClassLoader();
  
  protected boolean isTestClass(String classFileName) {
    try {
      if (classFileName.endsWith(".class")) {
        Class testClass = classFromFile(classFileName);
        return (testClass != null && isTestClass(testClass));
      } 
    } catch (ClassNotFoundException expected) {
    
    } catch (NoClassDefFoundError notFatal) {}
    return false;
  }
  
  Class classFromFile(String classFileName) throws ClassNotFoundException {
    String className = classNameFromFile(classFileName);
    if (!this.fLoader.isExcluded(className))
      return this.fLoader.loadClass(className, false); 
    return null;
  }
  
  boolean isTestClass(Class testClass) {
    if (hasSuiteMethod(testClass))
      return true; 
    if (Test.class.isAssignableFrom(testClass) && Modifier.isPublic(testClass.getModifiers()) && hasPublicConstructor(testClass))
      return true; 
    return false;
  }
  
  boolean hasSuiteMethod(Class testClass) {
    try {
      testClass.getMethod("suite", new Class[0]);
    } catch (Exception e) {
      return false;
    } 
    return true;
  }
  
  boolean hasPublicConstructor(Class testClass) {
    try {
      TestSuite.getTestConstructor(testClass);
    } catch (NoSuchMethodException e) {
      return false;
    } 
    return true;
  }
}
