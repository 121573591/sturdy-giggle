package junit.swingui;

import javax.swing.JTextField;

public class MacProgressBar extends ProgressBar {
  private JTextField component;
  
  public MacProgressBar(JTextField component) {
    this.component = component;
  }
  
  protected void updateBarColor() {
    this.component.setBackground(getStatusColor());
  }
}
