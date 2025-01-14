package junit.swingui;

import java.awt.Color;
import javax.swing.JProgressBar;

class ProgressBar extends JProgressBar {
  boolean fError = false;
  
  public ProgressBar() {
    setForeground(getStatusColor());
  }
  
  protected Color getStatusColor() {
    if (this.fError)
      return Color.red; 
    return Color.green;
  }
  
  public void reset() {
    this.fError = false;
    updateBarColor();
    setValue(0);
  }
  
  public void start(int total) {
    setMaximum(total);
    reset();
  }
  
  public void step(int value, boolean successful) {
    setValue(value);
    if (!this.fError && !successful) {
      this.fError = true;
      updateBarColor();
    } 
  }
  
  protected void updateBarColor() {
    setForeground(getStatusColor());
  }
}
