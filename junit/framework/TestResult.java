package junit.framework;

import java.util.Enumeration;
import java.util.Vector;

public class TestResult {
  protected Vector fFailures = new Vector();
  
  protected Vector fErrors = new Vector();
  
  protected Vector fListeners = new Vector();
  
  protected int fRunTests = 0;
  
  private boolean fStop = false;
  
  public synchronized void addError(Test test, Throwable t) {
    this.fErrors.addElement(new TestFailure(test, t));
    for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();)
      ((TestListener)e.nextElement()).addError(test, t); 
  }
  
  public synchronized void addFailure(Test test, AssertionFailedError t) {
    this.fFailures.addElement(new TestFailure(test, t));
    for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();)
      ((TestListener)e.nextElement()).addFailure(test, t); 
  }
  
  public synchronized void addListener(TestListener listener) {
    this.fListeners.addElement(listener);
  }
  
  public synchronized void removeListener(TestListener listener) {
    this.fListeners.removeElement(listener);
  }
  
  private synchronized Vector cloneListeners() {
    return (Vector)this.fListeners.clone();
  }
  
  public void endTest(Test test) {
    for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();)
      ((TestListener)e.nextElement()).endTest(test); 
  }
  
  public synchronized int errorCount() {
    return this.fErrors.size();
  }
  
  public synchronized Enumeration errors() {
    return this.fErrors.elements();
  }
  
  public synchronized int failureCount() {
    return this.fFailures.size();
  }
  
  public synchronized Enumeration failures() {
    return this.fFailures.elements();
  }
  
  protected void run(TestCase test) {
    startTest(test);
    Protectable p = new Protectable(this, test) {
        private final TestCase val$test;
        
        private final TestResult this$0;
        
        public void protect() throws Throwable {
          this.val$test.runBare();
        }
      };
    runProtected(test, p);
    endTest(test);
  }
  
  public synchronized int runCount() {
    return this.fRunTests;
  }
  
  public void runProtected(Test test, Protectable p) {
    try {
      p.protect();
    } catch (AssertionFailedError e) {
      addFailure(test, e);
    } catch (ThreadDeath e) {
      throw e;
    } catch (Throwable e) {
      addError(test, e);
    } 
  }
  
  public synchronized boolean shouldStop() {
    return this.fStop;
  }
  
  public void startTest(Test test) {
    int count = test.countTestCases();
    synchronized (this) {
      this.fRunTests += count;
    } 
    for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();)
      ((TestListener)e.nextElement()).startTest(test); 
  }
  
  public synchronized void stop() {
    this.fStop = true;
  }
  
  public synchronized boolean wasSuccessful() {
    return (failureCount() == 0 && errorCount() == 0);
  }
}
