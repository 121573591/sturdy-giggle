package junit.extensions;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class ActiveTestSuite extends TestSuite {
  private volatile int fActiveTestDeathCount;
  
  public ActiveTestSuite() {}
  
  public ActiveTestSuite(Class theClass) {
    super(theClass);
  }
  
  public ActiveTestSuite(String name) {
    super(name);
  }
  
  public ActiveTestSuite(Class theClass, String name) {
    super(theClass, name);
  }
  
  public void run(TestResult result) {
    this.fActiveTestDeathCount = 0;
    super.run(result);
    waitUntilFinished();
  }
  
  public void runTest(Test test, TestResult result) {
    Thread t = new Thread(this, test, result) {
        private final Test val$test;
        
        private final TestResult val$result;
        
        private final ActiveTestSuite this$0;
        
        public void run() {
          try {
            this.val$test.run(this.val$result);
          } finally {
            this.this$0.runFinished();
          } 
        }
      };
    t.start();
  }
  
  synchronized void waitUntilFinished() {
    while (this.fActiveTestDeathCount < testCount()) {
      try {
        wait();
      } catch (InterruptedException e) {
        return;
      } 
    } 
  }
  
  public synchronized void runFinished() {
    this.fActiveTestDeathCount++;
    notifyAll();
  }
}
