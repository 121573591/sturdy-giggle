package junit.swingui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import junit.runner.Sorter;
import junit.runner.TestCollector;

public class TestSelector extends JDialog {
  private JButton fCancel;
  
  private JButton fOk;
  
  private JList fList;
  
  private JScrollPane fScrolledList;
  
  private JLabel fDescription;
  
  private String fSelectedItem;
  
  static class TestCellRenderer extends DefaultListCellRenderer {
    Icon fLeafIcon = UIManager.getIcon("Tree.leafIcon");
    
    Icon fSuiteIcon = UIManager.getIcon("Tree.closedIcon");
    
    public Component getListCellRendererComponent(JList list, Object value, int modelIndex, boolean isSelected, boolean cellHasFocus) {
      Component c = super.getListCellRendererComponent(list, value, modelIndex, isSelected, cellHasFocus);
      String displayString = displayString((String)value);
      if (displayString.startsWith("AllTests")) {
        setIcon(this.fSuiteIcon);
      } else {
        setIcon(this.fLeafIcon);
      } 
      setText(displayString);
      return c;
    }
    
    public static String displayString(String className) {
      int typeIndex = className.lastIndexOf('.');
      if (typeIndex < 0)
        return className; 
      return className.substring(typeIndex + 1) + " - " + className.substring(0, typeIndex);
    }
    
    public static boolean matchesKey(String s, char ch) {
      return (ch == Character.toUpperCase(s.charAt(typeIndex(s))));
    }
    
    private static int typeIndex(String s) {
      int typeIndex = s.lastIndexOf('.');
      int i = 0;
      if (typeIndex > 0)
        i = typeIndex + 1; 
      return i;
    }
  }
  
  protected class DoubleClickListener extends MouseAdapter {
    private final TestSelector this$0;
    
    protected DoubleClickListener(TestSelector this$0) {
      this.this$0 = this$0;
    }
    
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2)
        this.this$0.okSelected(); 
    }
  }
  
  protected class KeySelectListener extends KeyAdapter {
    private final TestSelector this$0;
    
    protected KeySelectListener(TestSelector this$0) {
      this.this$0 = this$0;
    }
    
    public void keyTyped(KeyEvent e) {
      this.this$0.keySelectTestClass(e.getKeyChar());
    }
  }
  
  public TestSelector(Frame parent, TestCollector testCollector) {
    super(parent, true);
    setSize(350, 300);
    setResizable(false);
    try {
      setLocationRelativeTo(parent);
    } catch (NoSuchMethodError e) {
      centerWindow(this);
    } 
    setTitle("Test Selector");
    Vector list = null;
    try {
      parent.setCursor(Cursor.getPredefinedCursor(3));
      list = createTestList(testCollector);
    } finally {
      parent.setCursor(Cursor.getDefaultCursor());
    } 
    this.fList = new JList(list);
    this.fList.setSelectionMode(0);
    this.fList.setCellRenderer(new TestCellRenderer());
    this.fScrolledList = new JScrollPane(this.fList);
    this.fCancel = new JButton("Cancel");
    this.fDescription = new JLabel("Select the Test class:");
    this.fOk = new JButton("OK");
    this.fOk.setEnabled(false);
    getRootPane().setDefaultButton(this.fOk);
    defineLayout();
    addListeners();
  }
  
  public static void centerWindow(Component c) {
    Dimension paneSize = c.getSize();
    Dimension screenSize = c.getToolkit().getScreenSize();
    c.setLocation((screenSize.width - paneSize.width) / 2, (screenSize.height - paneSize.height) / 2);
  }
  
  private void addListeners() {
    this.fCancel.addActionListener(new ActionListener(this) {
          private final TestSelector this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.dispose();
          }
        });
    this.fOk.addActionListener(new ActionListener(this) {
          private final TestSelector this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.okSelected();
          }
        });
    this.fList.addMouseListener(new DoubleClickListener(this));
    this.fList.addKeyListener(new KeySelectListener(this));
    this.fList.addListSelectionListener(new ListSelectionListener(this) {
          private final TestSelector this$0;
          
          public void valueChanged(ListSelectionEvent e) {
            this.this$0.checkEnableOK(e);
          }
        });
    addWindowListener(new WindowAdapter(this) {
          private final TestSelector this$0;
          
          public void windowClosing(WindowEvent e) {
            this.this$0.dispose();
          }
        });
  }
  
  private void defineLayout() {
    getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints labelConstraints = new GridBagConstraints();
    labelConstraints.gridx = 0;
    labelConstraints.gridy = 0;
    labelConstraints.gridwidth = 1;
    labelConstraints.gridheight = 1;
    labelConstraints.fill = 1;
    labelConstraints.anchor = 17;
    labelConstraints.weightx = 1.0D;
    labelConstraints.weighty = 0.0D;
    labelConstraints.insets = new Insets(8, 8, 0, 8);
    getContentPane().add(this.fDescription, labelConstraints);
    GridBagConstraints listConstraints = new GridBagConstraints();
    listConstraints.gridx = 0;
    listConstraints.gridy = 1;
    listConstraints.gridwidth = 4;
    listConstraints.gridheight = 1;
    listConstraints.fill = 1;
    listConstraints.anchor = 10;
    listConstraints.weightx = 1.0D;
    listConstraints.weighty = 1.0D;
    listConstraints.insets = new Insets(8, 8, 8, 8);
    getContentPane().add(this.fScrolledList, listConstraints);
    GridBagConstraints okConstraints = new GridBagConstraints();
    okConstraints.gridx = 2;
    okConstraints.gridy = 2;
    okConstraints.gridwidth = 1;
    okConstraints.gridheight = 1;
    okConstraints.anchor = 13;
    okConstraints.insets = new Insets(0, 8, 8, 8);
    getContentPane().add(this.fOk, okConstraints);
    GridBagConstraints cancelConstraints = new GridBagConstraints();
    cancelConstraints.gridx = 3;
    cancelConstraints.gridy = 2;
    cancelConstraints.gridwidth = 1;
    cancelConstraints.gridheight = 1;
    cancelConstraints.anchor = 13;
    cancelConstraints.insets = new Insets(0, 8, 8, 8);
    getContentPane().add(this.fCancel, cancelConstraints);
  }
  
  public void checkEnableOK(ListSelectionEvent e) {
    this.fOk.setEnabled((this.fList.getSelectedIndex() != -1));
  }
  
  public void okSelected() {
    this.fSelectedItem = this.fList.getSelectedValue();
    dispose();
  }
  
  public boolean isEmpty() {
    return (this.fList.getModel().getSize() == 0);
  }
  
  public void keySelectTestClass(char ch) {
    ListModel model = this.fList.getModel();
    if (!Character.isJavaIdentifierStart(ch))
      return; 
    for (int i = 0; i < model.getSize(); i++) {
      String s = model.getElementAt(i);
      if (TestCellRenderer.matchesKey(s, Character.toUpperCase(ch))) {
        this.fList.setSelectedIndex(i);
        this.fList.ensureIndexIsVisible(i);
        return;
      } 
    } 
    Toolkit.getDefaultToolkit().beep();
  }
  
  public String getSelectedItem() {
    return this.fSelectedItem;
  }
  
  private Vector createTestList(TestCollector collector) {
    Enumeration each = collector.collectTests();
    Vector v = new Vector(200);
    Vector displayVector = new Vector(v.size());
    while (each.hasMoreElements()) {
      String s = each.nextElement();
      v.addElement(s);
      displayVector.addElement(TestCellRenderer.displayString(s));
    } 
    if (v.size() > 0)
      Sorter.sortStrings(displayVector, 0, displayVector.size() - 1, new ParallelSwapper(this, v)); 
    return v;
  }
  
  private class ParallelSwapper implements Sorter.Swapper {
    Vector fOther;
    
    private final TestSelector this$0;
    
    ParallelSwapper(TestSelector this$0, Vector other) {
      this.this$0 = this$0;
      this.fOther = other;
    }
    
    public void swap(Vector values, int left, int right) {
      Object tmp = values.elementAt(left);
      values.setElementAt(values.elementAt(right), left);
      values.setElementAt(tmp, right);
      Object tmp2 = this.fOther.elementAt(left);
      this.fOther.setElementAt(this.fOther.elementAt(right), left);
      this.fOther.setElementAt(tmp2, right);
    }
  }
}
