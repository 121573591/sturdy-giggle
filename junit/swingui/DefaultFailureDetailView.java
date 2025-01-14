package junit.swingui;

import java.awt.Component;
import java.awt.Font;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import junit.framework.TestFailure;
import junit.runner.BaseTestRunner;
import junit.runner.FailureDetailView;

public class DefaultFailureDetailView implements FailureDetailView {
  JList fList;
  
  static class StackTraceListModel extends AbstractListModel {
    private Vector fLines = new Vector(20);
    
    public Object getElementAt(int index) {
      return this.fLines.elementAt(index);
    }
    
    public int getSize() {
      return this.fLines.size();
    }
    
    public void setTrace(String trace) {
      scan(trace);
      fireContentsChanged(this, 0, this.fLines.size());
    }
    
    public void clear() {
      this.fLines.removeAllElements();
      fireContentsChanged(this, 0, this.fLines.size());
    }
    
    private void scan(String trace) {
      this.fLines.removeAllElements();
      StringTokenizer st = new StringTokenizer(trace, "\n\r", false);
      while (st.hasMoreTokens())
        this.fLines.addElement(st.nextToken()); 
    }
  }
  
  static class StackEntryRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int modelIndex, boolean isSelected, boolean cellHasFocus) {
      String text = ((String)value).replace('\t', ' ');
      Component c = super.getListCellRendererComponent(list, text, modelIndex, isSelected, cellHasFocus);
      setText(text);
      setToolTipText(text);
      return c;
    }
  }
  
  public Component getComponent() {
    if (this.fList == null) {
      this.fList = new JList(new StackTraceListModel());
      this.fList.setFont(new Font("Dialog", 0, 12));
      this.fList.setSelectionMode(0);
      this.fList.setVisibleRowCount(5);
      this.fList.setCellRenderer(new StackEntryRenderer());
    } 
    return this.fList;
  }
  
  public void showFailure(TestFailure failure) {
    getModel().setTrace(BaseTestRunner.getFilteredTrace(failure.trace()));
  }
  
  public void clear() {
    getModel().clear();
  }
  
  private StackTraceListModel getModel() {
    return (StackTraceListModel)this.fList.getModel();
  }
}
