package junit.swingui;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import junit.extensions.TestDecorator;
import junit.framework.Test;
import junit.framework.TestSuite;

class TestTreeModel implements TreeModel {
  private Test fRoot;
  
  private Vector fModelListeners = new Vector();
  
  private Hashtable fFailures = new Hashtable();
  
  private Hashtable fErrors = new Hashtable();
  
  private Hashtable fRunTests = new Hashtable();
  
  public TestTreeModel(Test root) {
    this.fRoot = root;
  }
  
  public void addTreeModelListener(TreeModelListener l) {
    if (!this.fModelListeners.contains(l))
      this.fModelListeners.addElement(l); 
  }
  
  public void removeTreeModelListener(TreeModelListener l) {
    this.fModelListeners.removeElement(l);
  }
  
  public int findTest(Test target, Test node, Vector path) {
    if (target.equals(node))
      return 0; 
    TestSuite suite = isTestSuite(node);
    for (int i = 0; i < getChildCount(node); i++) {
      Test t = suite.testAt(i);
      int index = findTest(target, t, path);
      if (index >= 0) {
        path.insertElementAt(node, 0);
        if (path.size() == 1)
          return i; 
        return index;
      } 
    } 
    return -1;
  }
  
  public void fireNodeChanged(TreePath path, int index) {
    int[] indices = { index };
    Object[] changedChildren = { getChild(path.getLastPathComponent(), index) };
    TreeModelEvent event = new TreeModelEvent(this, path, indices, changedChildren);
    Enumeration e = this.fModelListeners.elements();
    while (e.hasMoreElements()) {
      TreeModelListener l = e.nextElement();
      l.treeNodesChanged(event);
    } 
  }
  
  public Object getChild(Object parent, int index) {
    TestSuite suite = isTestSuite(parent);
    if (suite != null)
      return suite.testAt(index); 
    return null;
  }
  
  public int getChildCount(Object parent) {
    TestSuite suite = isTestSuite(parent);
    if (suite != null)
      return suite.testCount(); 
    return 0;
  }
  
  public int getIndexOfChild(Object parent, Object child) {
    TestSuite suite = isTestSuite(parent);
    if (suite != null) {
      int i = 0;
      for (Enumeration e = suite.tests(); e.hasMoreElements(); i++) {
        if (child.equals(e.nextElement()))
          return i; 
      } 
    } 
    return -1;
  }
  
  public Object getRoot() {
    return this.fRoot;
  }
  
  public boolean isLeaf(Object node) {
    return (isTestSuite(node) == null);
  }
  
  TestSuite isTestSuite(Object node) {
    if (node instanceof TestSuite)
      return (TestSuite)node; 
    if (node instanceof TestDecorator) {
      Test baseTest = ((TestDecorator)node).getTest();
      return isTestSuite(baseTest);
    } 
    return null;
  }
  
  public void valueForPathChanged(TreePath path, Object newValue) {
    System.out.println("TreeModel.valueForPathChanged: not implemented");
  }
  
  void addFailure(Test t) {
    this.fFailures.put(t, t);
  }
  
  void addError(Test t) {
    this.fErrors.put(t, t);
  }
  
  void addRunTest(Test t) {
    this.fRunTests.put(t, t);
  }
  
  boolean wasRun(Test t) {
    return (this.fRunTests.get(t) != null);
  }
  
  boolean isError(Test t) {
    return (this.fErrors != null && this.fErrors.get(t) != null);
  }
  
  boolean isFailure(Test t) {
    return (this.fFailures != null && this.fFailures.get(t) != null);
  }
  
  void resetResults() {
    this.fFailures = new Hashtable();
    this.fRunTests = new Hashtable();
    this.fErrors = new Hashtable();
  }
}
