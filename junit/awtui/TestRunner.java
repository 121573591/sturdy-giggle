package junit.awtui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.Vector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;

public class TestRunner extends BaseTestRunner {
  protected Frame fFrame;
  
  protected Vector fExceptions;
  
  protected Vector fFailedTests;
  
  protected Thread fRunner;
  
  protected TestResult fTestResult;
  
  protected TextArea fTraceArea;
  
  protected TextField fSuiteField;
  
  protected Button fRun;
  
  protected ProgressBar fProgressIndicator;
  
  protected List fFailureList;
  
  protected Logo fLogo;
  
  protected Label fNumberOfErrors;
  
  protected Label fNumberOfFailures;
  
  protected Label fNumberOfRuns;
  
  protected Button fQuitButton;
  
  protected Button fRerunButton;
  
  protected TextField fStatusLine;
  
  protected Checkbox fUseLoadingRunner;
  
  protected static final Font PLAIN_FONT = new Font("dialog", 0, 12);
  
  private static final int GAP = 4;
  
  private void about() {
    AboutDialog about = new AboutDialog(this.fFrame);
    about.setModal(true);
    about.setLocation(300, 300);
    about.setVisible(true);
  }
  
  public void testStarted(String testName) {
    showInfo("Running: " + testName);
  }
  
  public void testEnded(String testName) {
    setLabelValue(this.fNumberOfRuns, this.fTestResult.runCount());
    synchronized (this) {
      this.fProgressIndicator.step(this.fTestResult.wasSuccessful());
    } 
  }
  
  public void testFailed(int status, Test test, Throwable t) {
    switch (status) {
      case 1:
        this.fNumberOfErrors.setText(Integer.toString(this.fTestResult.errorCount()));
        appendFailure("Error", test, t);
        break;
      case 2:
        this.fNumberOfFailures.setText(Integer.toString(this.fTestResult.failureCount()));
        appendFailure("Failure", test, t);
        break;
    } 
  }
  
  protected void addGrid(Panel p, Component co, int x, int y, int w, int fill, double wx, int anchor) {
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = x;
    c.gridy = y;
    c.gridwidth = w;
    c.anchor = anchor;
    c.weightx = wx;
    c.fill = fill;
    if (fill == 1 || fill == 3)
      c.weighty = 1.0D; 
    c.insets = new Insets((y == 0) ? 4 : 0, (x == 0) ? 4 : 0, 4, 4);
    p.add(co, c);
  }
  
  private void appendFailure(String kind, Test test, Throwable t) {
    kind = kind + ": " + test;
    String msg = t.getMessage();
    if (msg != null)
      kind = kind + ":" + truncate(msg); 
    this.fFailureList.add(kind);
    this.fExceptions.addElement(t);
    this.fFailedTests.addElement(test);
    if (this.fFailureList.getItemCount() == 1) {
      this.fFailureList.select(0);
      failureSelected();
    } 
  }
  
  protected Menu createJUnitMenu() {
    Menu menu = new Menu("JUnit");
    MenuItem mi = new MenuItem("About...");
    mi.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent event) {
            this.this$0.about();
          }
        });
    menu.add(mi);
    menu.addSeparator();
    mi = new MenuItem("Exit");
    mi.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent event) {
            System.exit(0);
          }
        });
    menu.add(mi);
    return menu;
  }
  
  protected void createMenus(MenuBar mb) {
    mb.add(createJUnitMenu());
  }
  
  protected TestResult createTestResult() {
    return new TestResult();
  }
  
  protected Frame createUI(String suiteName) {
    Frame frame = new Frame("JUnit");
    Image icon = loadFrameIcon();
    if (icon != null)
      frame.setIconImage(icon); 
    frame.setLayout(new BorderLayout(0, 0));
    frame.setBackground(SystemColor.control);
    Frame finalFrame = frame;
    frame.addWindowListener(new WindowAdapter(this, finalFrame) {
          private final Frame val$finalFrame;
          
          private final TestRunner this$0;
          
          public void windowClosing(WindowEvent e) {
            this.val$finalFrame.dispose();
            System.exit(0);
          }
        });
    MenuBar mb = new MenuBar();
    createMenus(mb);
    frame.setMenuBar(mb);
    Label suiteLabel = new Label("Test class name:");
    this.fSuiteField = new TextField((suiteName != null) ? suiteName : "");
    this.fSuiteField.selectAll();
    this.fSuiteField.requestFocus();
    this.fSuiteField.setFont(PLAIN_FONT);
    this.fSuiteField.setColumns(40);
    this.fSuiteField.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.runSuite();
          }
        });
    this.fSuiteField.addTextListener(new TextListener(this) {
          private final TestRunner this$0;
          
          public void textValueChanged(TextEvent e) {
            this.this$0.fRun.setEnabled((this.this$0.fSuiteField.getText().length() > 0));
            this.this$0.fStatusLine.setText("");
          }
        });
    this.fRun = new Button("Run");
    this.fRun.setEnabled(false);
    this.fRun.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.runSuite();
          }
        });
    boolean useLoader = useReloadingTestSuiteLoader();
    this.fUseLoadingRunner = new Checkbox("Reload classes every run", useLoader);
    if (inVAJava())
      this.fUseLoadingRunner.setVisible(false); 
    this.fProgressIndicator = new ProgressBar();
    this.fNumberOfErrors = new Label("0000", 2);
    this.fNumberOfErrors.setText("0");
    this.fNumberOfErrors.setFont(PLAIN_FONT);
    this.fNumberOfFailures = new Label("0000", 2);
    this.fNumberOfFailures.setText("0");
    this.fNumberOfFailures.setFont(PLAIN_FONT);
    this.fNumberOfRuns = new Label("0000", 2);
    this.fNumberOfRuns.setText("0");
    this.fNumberOfRuns.setFont(PLAIN_FONT);
    Panel numbersPanel = createCounterPanel();
    Label failureLabel = new Label("Errors and Failures:");
    this.fFailureList = new List(5);
    this.fFailureList.addItemListener(new ItemListener(this) {
          private final TestRunner this$0;
          
          public void itemStateChanged(ItemEvent e) {
            this.this$0.failureSelected();
          }
        });
    this.fRerunButton = new Button("Run");
    this.fRerunButton.setEnabled(false);
    this.fRerunButton.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.rerun();
          }
        });
    Panel failedPanel = new Panel(new GridLayout(0, 1, 0, 2));
    failedPanel.add(this.fRerunButton);
    this.fTraceArea = new TextArea();
    this.fTraceArea.setRows(5);
    this.fTraceArea.setColumns(60);
    this.fStatusLine = new TextField();
    this.fStatusLine.setFont(PLAIN_FONT);
    this.fStatusLine.setEditable(false);
    this.fStatusLine.setForeground(Color.red);
    this.fQuitButton = new Button("Exit");
    this.fQuitButton.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });
    this.fLogo = new Logo();
    Panel panel = new Panel(new GridBagLayout());
    addGrid(panel, suiteLabel, 0, 0, 2, 2, 1.0D, 17);
    addGrid(panel, this.fSuiteField, 0, 1, 2, 2, 1.0D, 17);
    addGrid(panel, this.fRun, 2, 1, 1, 2, 0.0D, 10);
    addGrid(panel, this.fUseLoadingRunner, 0, 2, 2, 0, 1.0D, 17);
    addGrid(panel, this.fProgressIndicator, 0, 3, 2, 2, 1.0D, 17);
    addGrid(panel, this.fLogo, 2, 3, 1, 0, 0.0D, 11);
    addGrid(panel, numbersPanel, 0, 4, 2, 0, 0.0D, 17);
    addGrid(panel, failureLabel, 0, 5, 2, 2, 1.0D, 17);
    addGrid(panel, this.fFailureList, 0, 6, 2, 1, 1.0D, 17);
    addGrid(panel, failedPanel, 2, 6, 1, 2, 0.0D, 10);
    addGrid(panel, this.fTraceArea, 0, 7, 2, 1, 1.0D, 17);
    addGrid(panel, this.fStatusLine, 0, 8, 2, 2, 1.0D, 10);
    addGrid(panel, this.fQuitButton, 2, 8, 1, 2, 0.0D, 10);
    frame.add(panel, "Center");
    frame.pack();
    return frame;
  }
  
  protected Panel createCounterPanel() {
    Panel numbersPanel = new Panel(new GridBagLayout());
    addToCounterPanel(numbersPanel, new Label("Runs:"), 0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0));
    addToCounterPanel(numbersPanel, this.fNumberOfRuns, 1, 0, 1, 1, 0.33D, 0.0D, 10, 2, new Insets(0, 8, 0, 40));
    addToCounterPanel(numbersPanel, new Label("Errors:"), 2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 8, 0, 0));
    addToCounterPanel(numbersPanel, this.fNumberOfErrors, 3, 0, 1, 1, 0.33D, 0.0D, 10, 2, new Insets(0, 8, 0, 40));
    addToCounterPanel(numbersPanel, new Label("Failures:"), 4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 8, 0, 0));
    addToCounterPanel(numbersPanel, this.fNumberOfFailures, 5, 0, 1, 1, 0.33D, 0.0D, 10, 2, new Insets(0, 8, 0, 0));
    return numbersPanel;
  }
  
  private void addToCounterPanel(Panel counter, Component comp, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
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
    counter.add(comp, constraints);
  }
  
  public void failureSelected() {
    this.fRerunButton.setEnabled(isErrorSelected());
    showErrorTrace();
  }
  
  private boolean isErrorSelected() {
    return (this.fFailureList.getSelectedIndex() != -1);
  }
  
  private Image loadFrameIcon() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    try {
      URL url = BaseTestRunner.class.getResource("smalllogo.gif");
      return toolkit.createImage((ImageProducer)url.getContent());
    } catch (Exception ex) {
      return null;
    } 
  }
  
  public Thread getRunner() {
    return this.fRunner;
  }
  
  public static void main(String[] args) {
    (new TestRunner()).start(args);
  }
  
  public static void run(Class test) {
    String[] args = { test.getName() };
    main(args);
  }
  
  public void rerun() {
    int index = this.fFailureList.getSelectedIndex();
    if (index == -1)
      return; 
    Test test = this.fFailedTests.elementAt(index);
    rerunTest(test);
  }
  
  private void rerunTest(Test test) {
    if (!(test instanceof TestCase)) {
      showInfo("Could not reload " + test.toString());
      return;
    } 
    Test reloadedTest = null;
    TestCase rerunTest = (TestCase)test;
    try {
      Class reloadedTestClass = getLoader().reload(test.getClass());
      reloadedTest = TestSuite.createTest(reloadedTestClass, rerunTest.getName());
    } catch (Exception e) {
      showInfo("Could not reload " + test.toString());
      return;
    } 
    TestResult result = new TestResult();
    reloadedTest.run(result);
    String message = reloadedTest.toString();
    if (result.wasSuccessful()) {
      showInfo(message + " was successful");
    } else if (result.errorCount() == 1) {
      showStatus(message + " had an error");
    } else {
      showStatus(message + " had a failure");
    } 
  }
  
  protected void reset() {
    setLabelValue(this.fNumberOfErrors, 0);
    setLabelValue(this.fNumberOfFailures, 0);
    setLabelValue(this.fNumberOfRuns, 0);
    this.fProgressIndicator.reset();
    this.fRerunButton.setEnabled(false);
    this.fFailureList.removeAll();
    this.fExceptions = new Vector(10);
    this.fFailedTests = new Vector(10);
    this.fTraceArea.setText("");
  }
  
  protected void runFailed(String message) {
    showStatus(message);
    this.fRun.setLabel("Run");
    this.fRunner = null;
  }
  
  public synchronized void runSuite() {
    if (this.fRunner != null && this.fTestResult != null) {
      this.fTestResult.stop();
    } else {
      setLoading(shouldReload());
      this.fRun.setLabel("Stop");
      showInfo("Initializing...");
      reset();
      showInfo("Load Test Case...");
      Test testSuite = getTest(this.fSuiteField.getText());
      if (testSuite != null) {
        this.fRunner = new Thread(this, testSuite) {
            private final Test val$testSuite;
            
            private final TestRunner this$0;
            
            public void run() {
              this.this$0.fTestResult = this.this$0.createTestResult();
              this.this$0.fTestResult.addListener((TestListener)this.this$0);
              this.this$0.fProgressIndicator.start(this.val$testSuite.countTestCases());
              this.this$0.showInfo("Running...");
              long startTime = System.currentTimeMillis();
              this.val$testSuite.run(this.this$0.fTestResult);
              if (this.this$0.fTestResult.shouldStop()) {
                this.this$0.showStatus("Stopped");
              } else {
                long endTime = System.currentTimeMillis();
                long runTime = endTime - startTime;
                this.this$0.showInfo("Finished: " + this.this$0.elapsedTimeAsString(runTime) + " seconds");
              } 
              this.this$0.fTestResult = null;
              this.this$0.fRun.setLabel("Run");
              this.this$0.fRunner = null;
              System.gc();
            }
          };
        this.fRunner.start();
      } 
    } 
  }
  
  private boolean shouldReload() {
    return (!inVAJava() && this.fUseLoadingRunner.getState());
  }
  
  private void setLabelValue(Label label, int value) {
    label.setText(Integer.toString(value));
    label.invalidate();
    label.getParent().validate();
  }
  
  public void setSuiteName(String suite) {
    this.fSuiteField.setText(suite);
  }
  
  private void showErrorTrace() {
    int index = this.fFailureList.getSelectedIndex();
    if (index == -1)
      return; 
    Throwable t = this.fExceptions.elementAt(index);
    this.fTraceArea.setText(getFilteredTrace(t));
  }
  
  private void showInfo(String message) {
    this.fStatusLine.setFont(PLAIN_FONT);
    this.fStatusLine.setForeground(Color.black);
    this.fStatusLine.setText(message);
  }
  
  protected void clearStatus() {
    showStatus("");
  }
  
  private void showStatus(String status) {
    this.fStatusLine.setFont(PLAIN_FONT);
    this.fStatusLine.setForeground(Color.red);
    this.fStatusLine.setText(status);
  }
  
  public void start(String[] args) {
    String suiteName = processArguments(args);
    this.fFrame = createUI(suiteName);
    this.fFrame.setLocation(200, 200);
    this.fFrame.setVisible(true);
    if (suiteName != null) {
      setSuiteName(suiteName);
      runSuite();
    } 
  }
}
