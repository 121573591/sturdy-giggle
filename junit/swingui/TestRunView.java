package junit.swingui;

import javax.swing.JTabbedPane;
import junit.framework.Test;
import junit.framework.TestResult;

interface TestRunView {
  Test getSelectedTest();
  
  void activate();
  
  void revealFailure(Test paramTest);
  
  void addTab(JTabbedPane paramJTabbedPane);
  
  void aboutToStart(Test paramTest, TestResult paramTestResult);
  
  void runFinished(Test paramTest, TestResult paramTestResult);
}
