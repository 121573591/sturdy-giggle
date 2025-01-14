package junit.extensions;

import junit.framework.Protectable;
import junit.framework.Test;
import junit.framework.TestResult;

public class TestSetup extends TestDecorator {
  public TestSetup(Test test) {
    super(test);
  }
  
  public void run(TestResult result) {
    Protectable p = new Protectable(this, result) {
        private final TestResult val$result;
        
        private final TestSetup this$0;
        
        public void protect() throws Exception {
          this.this$0.setUp();
          this.this$0.basicRun(this.val$result);
          this.this$0.tearDown();
        }
      };
    result.runProtected(this, p);
  }
  
  protected void setUp() throws Exception {}
  
  protected void tearDown() throws Exception {}
}
