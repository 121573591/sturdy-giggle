package junit.awtui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import junit.runner.Version;

class AboutDialog extends Dialog {
  public AboutDialog(Frame parent) {
    super(parent);
    setResizable(false);
    setLayout(new GridBagLayout());
    setSize(330, 138);
    setTitle("About");
    Button button = new Button("Close");
    button.addActionListener(new ActionListener(this) {
          private final AboutDialog this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.dispose();
          }
        });
    Label label1 = new Label("JUnit");
    label1.setFont(new Font("dialog", 0, 36));
    Label label2 = new Label("JUnit " + Version.id() + " by Kent Beck and Erich Gamma");
    label2.setFont(new Font("dialog", 0, 14));
    Logo logo = new Logo();
    GridBagConstraints constraintsLabel1 = new GridBagConstraints();
    constraintsLabel1.gridx = 3;
    constraintsLabel1.gridy = 0;
    constraintsLabel1.gridwidth = 1;
    constraintsLabel1.gridheight = 1;
    constraintsLabel1.anchor = 10;
    add(label1, constraintsLabel1);
    GridBagConstraints constraintsLabel2 = new GridBagConstraints();
    constraintsLabel2.gridx = 2;
    constraintsLabel2.gridy = 1;
    constraintsLabel2.gridwidth = 2;
    constraintsLabel2.gridheight = 1;
    constraintsLabel2.anchor = 10;
    add(label2, constraintsLabel2);
    GridBagConstraints constraintsButton1 = new GridBagConstraints();
    constraintsButton1.gridx = 2;
    constraintsButton1.gridy = 2;
    constraintsButton1.gridwidth = 2;
    constraintsButton1.gridheight = 1;
    constraintsButton1.anchor = 10;
    constraintsButton1.insets = new Insets(8, 0, 8, 0);
    add(button, constraintsButton1);
    GridBagConstraints constraintsLogo1 = new GridBagConstraints();
    constraintsLogo1.gridx = 2;
    constraintsLogo1.gridy = 0;
    constraintsLogo1.gridwidth = 1;
    constraintsLogo1.gridheight = 1;
    constraintsLogo1.anchor = 10;
    add(logo, constraintsLogo1);
    addWindowListener(new WindowAdapter(this) {
          private final AboutDialog this$0;
          
          public void windowClosing(WindowEvent e) {
            this.this$0.dispose();
          }
        });
  }
}
