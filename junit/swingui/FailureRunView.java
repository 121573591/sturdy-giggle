package junit.swingui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.runner.BaseTestRunner;

public class FailureRunView implements TestRunView {
  JList fFailureList;
  
  TestRunContext fRunContext;
  
  static class FailureListCellRenderer extends DefaultListCellRenderer {
    private Icon fFailureIcon;
    
    private Icon fErrorIcon;
    
    FailureListCellRenderer() {
      loadIcons();
    }
    
    void loadIcons() {
      this.fFailureIcon = TestRunner.getIconResource(getClass(), "icons/failure.gif");
      this.fErrorIcon = TestRunner.getIconResource(getClass(), "icons/error.gif");
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int modelIndex, boolean isSelected, boolean cellHasFocus) {
      Component c = super.getListCellRendererComponent(list, value, modelIndex, isSelected, cellHasFocus);
      TestFailure failure = (TestFailure)value;
      String text = failure.failedTest().toString();
      String msg = failure.exceptionMessage();
      if (msg != null)
        text = text + ":" + BaseTestRunner.truncate(msg); 
      if (failure.isFailure()) {
        if (this.fFailureIcon != null)
          setIcon(this.fFailureIcon); 
      } else if (this.fErrorIcon != null) {
        setIcon(this.fErrorIcon);
      } 
      setText(text);
      setToolTipText(text);
      return c;
    }
  }
  
  public FailureRunView(TestRunContext context) {
    this.fRunContext = context;
    this.fFailureList = new JList(this.fRunContext.getFailures());
    this.fFailureList.setFont(new Font("Dialog", 0, 12));
    this.fFailureList.setSelectionMode(0);
    this.fFailureList.setCellRenderer(new FailureListCellRenderer());
    this.fFailureList.setVisibleRowCount(5);
    this.fFailureList.addListSelectionListener(new ListSelectionListener(this) {
          private final FailureRunView this$0;
          
          public void valueChanged(ListSelectionEvent e) {
            this.this$0.testSelected();
          }
        });
  }
  
  public Test getSelectedTest() {
    int index = this.fFailureList.getSelectedIndex();
    if (index == -1)
      return null; 
    ListModel model = this.fFailureList.getModel();
    TestFailure failure = model.getElementAt(index);
    return failure.failedTest();
  }
  
  public void activate() {
    testSelected();
  }
  
  public void addTab(JTabbedPane pane) {
    JScrollPane scrollPane = new JScrollPane(this.fFailureList, 22, 32);
    Icon errorIcon = TestRunner.getIconResource(getClass(), "icons/error.gif");
    pane.addTab("Failures", errorIcon, scrollPane, "The list of failed tests");
  }
  
  public void revealFailure(Test failure) {
    this.fFailureList.setSelectedIndex(0);
  }
  
  public void aboutToStart(Test suite, TestResult result) {}
  
  public void runFinished(Test suite, TestResult result) {}
  
  protected void testSelected() {
    this.fRunContext.handleTestSelected(getSelectedTest());
  }
}
