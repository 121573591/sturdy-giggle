package junit.swingui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CounterPanel extends JPanel {
  private JTextField fNumberOfErrors;
  
  private JTextField fNumberOfFailures;
  
  private JTextField fNumberOfRuns;
  
  private Icon fFailureIcon = TestRunner.getIconResource(getClass(), "icons/failure.gif");
  
  private Icon fErrorIcon = TestRunner.getIconResource(getClass(), "icons/error.gif");
  
  private int fTotal;
  
  public CounterPanel() {
    super(new GridBagLayout());
    this.fNumberOfErrors = createOutputField(5);
    this.fNumberOfFailures = createOutputField(5);
    this.fNumberOfRuns = createOutputField(9);
    addToGrid(new JLabel("Runs:", 0), 0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0));
    addToGrid(this.fNumberOfRuns, 1, 0, 1, 1, 0.33D, 0.0D, 10, 2, new Insets(0, 8, 0, 0));
    addToGrid(new JLabel("Errors:", this.fErrorIcon, 2), 2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 8, 0, 0));
    addToGrid(this.fNumberOfErrors, 3, 0, 1, 1, 0.33D, 0.0D, 10, 2, new Insets(0, 8, 0, 0));
    addToGrid(new JLabel("Failures:", this.fFailureIcon, 2), 4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 8, 0, 0));
    addToGrid(this.fNumberOfFailures, 5, 0, 1, 1, 0.33D, 0.0D, 10, 2, new Insets(0, 8, 0, 0));
  }
  
  private JTextField createOutputField(int width) {
    JTextField field = new JTextField("0", width);
    field.setMinimumSize(field.getPreferredSize());
    field.setMaximumSize(field.getPreferredSize());
    field.setHorizontalAlignment(2);
    field.setFont(StatusLine.BOLD_FONT);
    field.setEditable(false);
    field.setBorder(BorderFactory.createEmptyBorder());
    return field;
  }
  
  public void addToGrid(Component comp, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = gridx;
    constraints.gridy = gridy;
    constraints.gridwidth = gridwidth;
    constraints.gridheight = gridheight;
    constraints.weightx = weightx;
    constraints.weighty = weighty;
    constraints.anchor = anchor;
    constraints.fill = fill;
    constraints.insets = insets;
    add(comp, constraints);
  }
  
  public void reset() {
    setLabelValue(this.fNumberOfErrors, 0);
    setLabelValue(this.fNumberOfFailures, 0);
    setLabelValue(this.fNumberOfRuns, 0);
    this.fTotal = 0;
  }
  
  public void setTotal(int value) {
    this.fTotal = value;
  }
  
  public void setRunValue(int value) {
    this.fNumberOfRuns.setText(Integer.toString(value) + "/" + this.fTotal);
  }
  
  public void setErrorValue(int value) {
    setLabelValue(this.fNumberOfErrors, value);
  }
  
  public void setFailureValue(int value) {
    setLabelValue(this.fNumberOfFailures, value);
  }
  
  private void setLabelValue(JTextField label, int value) {
    label.setText(Integer.toString(value));
  }
}
