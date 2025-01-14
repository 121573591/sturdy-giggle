package junit.swingui;

import javax.swing.ListModel;
import junit.framework.Test;

public interface TestRunContext {
  void handleTestSelected(Test paramTest);
  
  ListModel getFailures();
}
