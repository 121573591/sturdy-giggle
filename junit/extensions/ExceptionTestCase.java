package junit.extensions;

import junit.framework.TestCase;

public class ExceptionTestCase extends TestCase {
  Class fExpected;
  
  public ExceptionTestCase(String name, Class exception) {
    super(name);
    this.fExpected = exception;
  }
  
  protected void runTest() throws Throwable {
    try {
      super.runTest();
    } catch (Exception e) {
      if (this.fExpected.isAssignableFrom(e.getClass()))
        return; 
      throw e;
    } 
    fail("Expected exception " + this.fExpected);
  }
}
