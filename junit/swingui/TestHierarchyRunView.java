package junit.swingui;

import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import junit.framework.Test;
import junit.framework.TestResult;

public class TestHierarchyRunView implements TestRunView {
  TestSuitePanel fTreeBrowser;
  
  TestRunContext fTestContext;
  
  public TestHierarchyRunView(TestRunContext context) {
    this.fTestContext = context;
    this.fTreeBrowser = new TestSuitePanel();
    this.fTreeBrowser.getTree().addTreeSelectionListener(new TreeSelectionListener(this) {
          private final TestHierarchyRunView this$0;
          
          public void valueChanged(TreeSelectionEvent e) {
            this.this$0.testSelected();
          }
        });
  }
  
  public void addTab(JTabbedPane pane) {
    Icon treeIcon = TestRunner.getIconResource(getClass(), "icons/hierarchy.gif");
    pane.addTab("Test Hierarchy", treeIcon, this.fTreeBrowser, "The test hierarchy");
  }
  
  public Test getSelectedTest() {
    return this.fTreeBrowser.getSelectedTest();
  }
  
  public void activate() {
    testSelected();
  }
  
  public void revealFailure(Test failure) {
    JTree tree = this.fTreeBrowser.getTree();
    TestTreeModel model = (TestTreeModel)tree.getModel();
    Vector vpath = new Vector();
    int index = model.findTest(failure, (Test)model.getRoot(), vpath);
    if (index >= 0) {
      Object[] path = new Object[vpath.size() + 1];
      vpath.copyInto(path);
      Object last = path[vpath.size() - 1];
      path[vpath.size()] = model.getChild(last, index);
      TreePath selectionPath = new TreePath(path);
      tree.setSelectionPath(selectionPath);
      tree.makeVisible(selectionPath);
    } 
  }
  
  public void aboutToStart(Test suite, TestResult result) {
    this.fTreeBrowser.showTestTree(suite);
    result.addListener(this.fTreeBrowser);
  }
  
  public void runFinished(Test suite, TestResult result) {
    result.removeListener(this.fTreeBrowser);
  }
  
  protected void testSelected() {
    this.fTestContext.handleTestSelected(getSelectedTest());
  }
}
