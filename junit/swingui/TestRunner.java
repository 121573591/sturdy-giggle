package junit.swingui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;
import junit.runner.FailureDetailView;
import junit.runner.SimpleTestCollector;
import junit.runner.TestCollector;
import junit.runner.Version;

public class TestRunner extends BaseTestRunner implements TestRunContext {
  private static final int GAP = 4;
  
  private static final int HISTORY_LENGTH = 5;
  
  protected JFrame fFrame;
  
  private Thread fRunner;
  
  private TestResult fTestResult;
  
  private JComboBox fSuiteCombo;
  
  private ProgressBar fProgressIndicator;
  
  private DefaultListModel fFailures;
  
  private JLabel fLogo;
  
  private CounterPanel fCounterPanel;
  
  private JButton fRun;
  
  private JButton fQuitButton;
  
  private JButton fRerunButton;
  
  private StatusLine fStatusLine;
  
  private FailureDetailView fFailureView;
  
  private JTabbedPane fTestViewTab;
  
  private JCheckBox fUseLoadingRunner;
  
  private Vector fTestRunViews = new Vector();
  
  private static final String TESTCOLLECTOR_KEY = "TestCollectorClass";
  
  private static final String FAILUREDETAILVIEW_KEY = "FailureViewClass";
  
  public static void main(String[] args) {
    (new TestRunner()).start(args);
  }
  
  public static void run(Class test) {
    String[] args = { test.getName() };
    main(args);
  }
  
  public void testFailed(int status, Test test, Throwable t) {
    SwingUtilities.invokeLater(new Runnable(this, status, test, t) {
          private final int val$status;
          
          private final Test val$test;
          
          private final Throwable val$t;
          
          private final TestRunner this$0;
          
          public void run() {
            switch (this.val$status) {
              case 1:
                this.this$0.fCounterPanel.setErrorValue(this.this$0.fTestResult.errorCount());
                this.this$0.appendFailure(this.val$test, this.val$t);
                break;
              case 2:
                this.this$0.fCounterPanel.setFailureValue(this.this$0.fTestResult.failureCount());
                this.this$0.appendFailure(this.val$test, this.val$t);
                break;
            } 
          }
        });
  }
  
  public void testStarted(String testName) {
    postInfo("Running: " + testName);
  }
  
  public void testEnded(String stringName) {
    synchUI();
    SwingUtilities.invokeLater(new Runnable(this) {
          private final TestRunner this$0;
          
          public void run() {
            if (this.this$0.fTestResult != null) {
              this.this$0.fCounterPanel.setRunValue(this.this$0.fTestResult.runCount());
              this.this$0.fProgressIndicator.step(this.this$0.fTestResult.runCount(), this.this$0.fTestResult.wasSuccessful());
            } 
          }
        });
  }
  
  public void setSuite(String suiteName) {
    this.fSuiteCombo.getEditor().setItem(suiteName);
  }
  
  private void addToHistory(String suite) {
    for (int i = 0; i < this.fSuiteCombo.getItemCount(); i++) {
      if (suite.equals(this.fSuiteCombo.getItemAt(i))) {
        this.fSuiteCombo.removeItemAt(i);
        this.fSuiteCombo.insertItemAt(suite, 0);
        this.fSuiteCombo.setSelectedIndex(0);
        return;
      } 
    } 
    this.fSuiteCombo.insertItemAt(suite, 0);
    this.fSuiteCombo.setSelectedIndex(0);
    pruneHistory();
  }
  
  private void pruneHistory() {
    int historyLength = getPreference("maxhistory", 5);
    if (historyLength < 1)
      historyLength = 1; 
    for (int i = this.fSuiteCombo.getItemCount() - 1; i > historyLength - 1; i--)
      this.fSuiteCombo.removeItemAt(i); 
  }
  
  private void appendFailure(Test test, Throwable t) {
    this.fFailures.addElement(new TestFailure(test, t));
    if (this.fFailures.size() == 1)
      revealFailure(test); 
  }
  
  private void revealFailure(Test test) {
    for (Enumeration e = this.fTestRunViews.elements(); e.hasMoreElements(); ) {
      TestRunView v = e.nextElement();
      v.revealFailure(test);
    } 
  }
  
  protected void aboutToStart(Test testSuite) {
    for (Enumeration e = this.fTestRunViews.elements(); e.hasMoreElements(); ) {
      TestRunView v = e.nextElement();
      v.aboutToStart(testSuite, this.fTestResult);
    } 
  }
  
  protected void runFinished(Test testSuite) {
    SwingUtilities.invokeLater(new Runnable(this, testSuite) {
          private final Test val$testSuite;
          
          private final TestRunner this$0;
          
          public void run() {
            for (Enumeration e = this.this$0.fTestRunViews.elements(); e.hasMoreElements(); ) {
              TestRunView v = e.nextElement();
              v.runFinished(this.val$testSuite, this.this$0.fTestResult);
            } 
          }
        });
  }
  
  protected CounterPanel createCounterPanel() {
    return new CounterPanel();
  }
  
  protected JPanel createFailedPanel() {
    JPanel failedPanel = new JPanel(new GridLayout(0, 1, 0, 2));
    this.fRerunButton = new JButton("Run");
    this.fRerunButton.setEnabled(false);
    this.fRerunButton.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.rerun();
          }
        });
    failedPanel.add(this.fRerunButton);
    return failedPanel;
  }
  
  protected FailureDetailView createFailureDetailView() {
    String className = BaseTestRunner.getPreference("FailureViewClass");
    if (className != null) {
      Class viewClass = null;
      try {
        viewClass = Class.forName(className);
        return (FailureDetailView)viewClass.newInstance();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this.fFrame, "Could not create Failure DetailView - using default view");
      } 
    } 
    return new DefaultFailureDetailView();
  }
  
  protected JMenu createJUnitMenu() {
    JMenu menu = new JMenu("JUnit");
    menu.setMnemonic('J');
    JMenuItem mi1 = new JMenuItem("About...");
    mi1.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent event) {
            this.this$0.about();
          }
        });
    mi1.setMnemonic('A');
    menu.add(mi1);
    menu.addSeparator();
    JMenuItem mi2 = new JMenuItem(" Exit ");
    mi2.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent event) {
            this.this$0.terminate();
          }
        });
    mi2.setMnemonic('x');
    menu.add(mi2);
    return menu;
  }
  
  protected JFrame createFrame() {
    JFrame frame = new JFrame("JUnit");
    Image icon = loadFrameIcon();
    if (icon != null)
      frame.setIconImage(icon); 
    frame.getContentPane().setLayout(new BorderLayout(0, 0));
    frame.addWindowListener(new WindowAdapter(this) {
          private final TestRunner this$0;
          
          public void windowClosing(WindowEvent e) {
            this.this$0.terminate();
          }
        });
    return frame;
  }
  
  protected JLabel createLogo() {
    JLabel label;
    Icon icon = getIconResource(BaseTestRunner.class, "logo.gif");
    if (icon != null) {
      label = new JLabel(icon);
    } else {
      label = new JLabel("JV");
    } 
    label.setToolTipText("JUnit Version " + Version.id());
    return label;
  }
  
  protected void createMenus(JMenuBar mb) {
    mb.add(createJUnitMenu());
  }
  
  protected JCheckBox createUseLoaderCheckBox() {
    boolean useLoader = useReloadingTestSuiteLoader();
    JCheckBox box = new JCheckBox("Reload classes every run", useLoader);
    box.setToolTipText("Use a custom class loader to reload the classes for every run");
    if (inVAJava())
      box.setVisible(false); 
    return box;
  }
  
  protected JButton createQuitButton() {
    JButton quit = new JButton(" Exit ");
    quit.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.terminate();
          }
        });
    return quit;
  }
  
  protected JButton createRunButton() {
    JButton run = new JButton("Run");
    run.setEnabled(true);
    run.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.runSuite();
          }
        });
    return run;
  }
  
  protected Component createBrowseButton() {
    JButton browse = new JButton("...");
    browse.setToolTipText("Select a Test class");
    browse.addActionListener(new ActionListener(this) {
          private final TestRunner this$0;
          
          public void actionPerformed(ActionEvent e) {
            this.this$0.browseTestClasses();
          }
        });
    return browse;
  }
  
  protected StatusLine createStatusLine() {
    return new StatusLine(380);
  }
  
  protected JComboBox createSuiteCombo() {
    JComboBox combo = new JComboBox();
    combo.setEditable(true);
    combo.setLightWeightPopupEnabled(false);
    combo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(this) {
          private final TestRunner this$0;
          
          public void keyTyped(KeyEvent e) {
            this.this$0.textChanged();
            if (e.getKeyChar() == '\n')
              this.this$0.runSuite(); 
          }
        });
    try {
      loadHistory(combo);
    } catch (IOException e) {}
    combo.addItemListener(new ItemListener(this) {
          private final TestRunner this$0;
          
          public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == 1)
              this.this$0.textChanged(); 
          }
        });
    return combo;
  }
  
  protected JTabbedPane createTestRunViews() {
    JTabbedPane pane = new JTabbedPane(3);
    FailureRunView lv = new FailureRunView(this);
    this.fTestRunViews.addElement(lv);
    lv.addTab(pane);
    TestHierarchyRunView tv = new TestHierarchyRunView(this);
    this.fTestRunViews.addElement(tv);
    tv.addTab(pane);
    pane.addChangeListener(new ChangeListener(this) {
          private final TestRunner this$0;
          
          public void stateChanged(ChangeEvent e) {
            this.this$0.testViewChanged();
          }
        });
    return pane;
  }
  
  public void testViewChanged() {
    TestRunView view = this.fTestRunViews.elementAt(this.fTestViewTab.getSelectedIndex());
    view.activate();
  }
  
  protected TestResult createTestResult() {
    return new TestResult();
  }
  
  protected JFrame createUI(String suiteName) {
    JFrame frame = createFrame();
    JMenuBar mb = new JMenuBar();
    createMenus(mb);
    frame.setJMenuBar(mb);
    JLabel suiteLabel = new JLabel("Test class name:");
    this.fSuiteCombo = createSuiteCombo();
    this.fRun = createRunButton();
    frame.getRootPane().setDefaultButton(this.fRun);
    Component browseButton = createBrowseButton();
    this.fUseLoadingRunner = createUseLoaderCheckBox();
    this.fStatusLine = createStatusLine();
    if (inMac()) {
      this.fProgressIndicator = new MacProgressBar(this.fStatusLine);
    } else {
      this.fProgressIndicator = new ProgressBar();
    } 
    this.fCounterPanel = createCounterPanel();
    this.fFailures = new DefaultListModel();
    this.fTestViewTab = createTestRunViews();
    JPanel failedPanel = createFailedPanel();
    this.fFailureView = createFailureDetailView();
    JScrollPane tracePane = new JScrollPane(this.fFailureView.getComponent(), 22, 32);
    this.fQuitButton = createQuitButton();
    this.fLogo = createLogo();
    JPanel panel = new JPanel(new GridBagLayout());
    addGrid(panel, suiteLabel, 0, 0, 2, 2, 1.0D, 17);
    addGrid(panel, this.fSuiteCombo, 0, 1, 1, 2, 1.0D, 17);
    addGrid(panel, browseButton, 1, 1, 1, 0, 0.0D, 17);
    addGrid(panel, this.fRun, 2, 1, 1, 2, 0.0D, 10);
    addGrid(panel, this.fUseLoadingRunner, 0, 2, 3, 0, 1.0D, 17);
    addGrid(panel, this.fProgressIndicator, 0, 3, 2, 2, 1.0D, 17);
    addGrid(panel, this.fLogo, 2, 3, 1, 0, 0.0D, 11);
    addGrid(panel, this.fCounterPanel, 0, 4, 2, 0, 0.0D, 17);
    addGrid(panel, new JSeparator(), 0, 5, 2, 2, 1.0D, 17);
    addGrid(panel, new JLabel("Results:"), 0, 6, 2, 2, 1.0D, 17);
    JSplitPane splitter = new JSplitPane(0, this.fTestViewTab, tracePane);
    addGrid(panel, splitter, 0, 7, 2, 1, 1.0D, 17);
    addGrid(panel, failedPanel, 2, 7, 1, 2, 0.0D, 11);
    addGrid(panel, this.fStatusLine, 0, 9, 2, 2, 1.0D, 10);
    addGrid(panel, this.fQuitButton, 2, 9, 1, 2, 0.0D, 10);
    frame.setContentPane(panel);
    frame.pack();
    frame.setLocation(200, 200);
    return frame;
  }
  
  private void addGrid(JPanel p, Component co, int x, int y, int w, int fill, double wx, int anchor) {
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = x;
    c.gridy = y;
    c.gridwidth = w;
    c.anchor = anchor;
    c.weightx = wx;
    c.fill = fill;
    if (fill == 1 || fill == 3)
      c.weighty = 1.0D; 
    c.insets = new Insets((y == 0) ? 10 : 0, (x == 0) ? 10 : 4, 4, 4);
    p.add(co, c);
  }
  
  protected String getSuiteText() {
    if (this.fSuiteCombo == null)
      return ""; 
    return (String)this.fSuiteCombo.getEditor().getItem();
  }
  
  public ListModel getFailures() {
    return this.fFailures;
  }
  
  public void insertUpdate(DocumentEvent event) {
    textChanged();
  }
  
  protected Object instanciateClass(String fullClassName, Object param) {
    try {
      Class clazz = Class.forName(fullClassName);
      if (param == null)
        return clazz.newInstance(); 
      Class[] clazzParam = { param.getClass() };
      Constructor clazzConstructor = clazz.getConstructor(clazzParam);
      Object[] objectParam = { param };
      return clazzConstructor.newInstance(objectParam);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public void browseTestClasses() {
    TestCollector collector = createTestCollector();
    TestSelector selector = new TestSelector(this.fFrame, collector);
    if (selector.isEmpty()) {
      JOptionPane.showMessageDialog(this.fFrame, "No Test Cases found.\nCheck that the configured 'TestCollector' is supported on this platform.");
      return;
    } 
    selector.show();
    String className = selector.getSelectedItem();
    if (className != null)
      setSuite(className); 
  }
  
  TestCollector createTestCollector() {
    String className = BaseTestRunner.getPreference("TestCollectorClass");
    if (className != null) {
      Class collectorClass = null;
      try {
        collectorClass = Class.forName(className);
        return (TestCollector)collectorClass.newInstance();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this.fFrame, "Could not create TestCollector - using default collector");
      } 
    } 
    return (TestCollector)new SimpleTestCollector();
  }
  
  private Image loadFrameIcon() {
    ImageIcon icon = (ImageIcon)getIconResource(BaseTestRunner.class, "smalllogo.gif");
    if (icon != null)
      return icon.getImage(); 
    return null;
  }
  
  private void loadHistory(JComboBox combo) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(getSettingsFile()));
    int itemCount = 0;
    try {
      String line;
      while ((line = br.readLine()) != null) {
        combo.addItem(line);
        itemCount++;
      } 
      if (itemCount > 0)
        combo.setSelectedIndex(0); 
    } finally {
      br.close();
    } 
  }
  
  private File getSettingsFile() {
    String home = System.getProperty("user.home");
    return new File(home, ".junitsession");
  }
  
  private void postInfo(String message) {
    SwingUtilities.invokeLater(new Runnable(this, message) {
          private final String val$message;
          
          private final TestRunner this$0;
          
          public void run() {
            this.this$0.showInfo(this.val$message);
          }
        });
  }
  
  private void postStatus(String status) {
    SwingUtilities.invokeLater(new Runnable(this, status) {
          private final String val$status;
          
          private final TestRunner this$0;
          
          public void run() {
            this.this$0.showStatus(this.val$status);
          }
        });
  }
  
  public void removeUpdate(DocumentEvent event) {
    textChanged();
  }
  
  private void rerun() {
    TestRunView view = this.fTestRunViews.elementAt(this.fTestViewTab.getSelectedIndex());
    Test rerunTest = view.getSelectedTest();
    if (rerunTest != null)
      rerunTest(rerunTest); 
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
    this.fCounterPanel.reset();
    this.fProgressIndicator.reset();
    this.fRerunButton.setEnabled(false);
    this.fFailureView.clear();
    this.fFailures.clear();
  }
  
  protected void runFailed(String message) {
    showStatus(message);
    this.fRun.setText("Run");
    this.fRunner = null;
  }
  
  public synchronized void runSuite() {
    if (this.fRunner != null) {
      this.fTestResult.stop();
    } else {
      setLoading(shouldReload());
      reset();
      showInfo("Load Test Case...");
      String suiteName = getSuiteText();
      Test testSuite = getTest(suiteName);
      if (testSuite != null) {
        addToHistory(suiteName);
        doRunTest(testSuite);
      } 
    } 
  }
  
  private boolean shouldReload() {
    return (!inVAJava() && this.fUseLoadingRunner.isSelected());
  }
  
  protected synchronized void runTest(Test testSuite) {
    if (this.fRunner != null) {
      this.fTestResult.stop();
    } else {
      reset();
      if (testSuite != null)
        doRunTest(testSuite); 
    } 
  }
  
  private void doRunTest(Test testSuite) {
    setButtonLabel(this.fRun, "Stop");
    this.fRunner = new Thread(this, "TestRunner-Thread", testSuite) {
        private final Test val$testSuite;
        
        private final TestRunner this$0;
        
        public void run() {
          this.this$0.start(this.val$testSuite);
          this.this$0.postInfo("Running...");
          long startTime = System.currentTimeMillis();
          this.val$testSuite.run(this.this$0.fTestResult);
          if (this.this$0.fTestResult.shouldStop()) {
            this.this$0.postStatus("Stopped");
          } else {
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;
            this.this$0.postInfo("Finished: " + this.this$0.elapsedTimeAsString(runTime) + " seconds");
          } 
          this.this$0.runFinished(this.val$testSuite);
          this.this$0.setButtonLabel(this.this$0.fRun, "Run");
          this.this$0.fRunner = null;
          System.gc();
        }
      };
    this.fTestResult = createTestResult();
    this.fTestResult.addListener((TestListener)this);
    aboutToStart(testSuite);
    this.fRunner.start();
  }
  
  private void saveHistory() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(getSettingsFile()));
    try {
      for (int i = 0; i < this.fSuiteCombo.getItemCount(); i++) {
        String testsuite = this.fSuiteCombo.getItemAt(i).toString();
        bw.write(testsuite, 0, testsuite.length());
        bw.newLine();
      } 
    } finally {
      bw.close();
    } 
  }
  
  private void setButtonLabel(JButton button, String label) {
    SwingUtilities.invokeLater(new Runnable(this, button, label) {
          private final JButton val$button;
          
          private final String val$label;
          
          private final TestRunner this$0;
          
          public void run() {
            this.val$button.setText(this.val$label);
          }
        });
  }
  
  public void handleTestSelected(Test test) {
    this.fRerunButton.setEnabled((test != null && test instanceof TestCase));
    showFailureDetail(test);
  }
  
  private void showFailureDetail(Test test) {
    if (test != null) {
      ListModel failures = getFailures();
      for (int i = 0; i < failures.getSize(); i++) {
        TestFailure failure = failures.getElementAt(i);
        if (failure.failedTest() == test) {
          this.fFailureView.showFailure(failure);
          return;
        } 
      } 
    } 
    this.fFailureView.clear();
  }
  
  private void showInfo(String message) {
    this.fStatusLine.showInfo(message);
  }
  
  private void showStatus(String status) {
    this.fStatusLine.showError(status);
  }
  
  public void start(String[] args) {
    String suiteName = processArguments(args);
    this.fFrame = createUI(suiteName);
    this.fFrame.pack();
    this.fFrame.setVisible(true);
    if (suiteName != null) {
      setSuite(suiteName);
      runSuite();
    } 
  }
  
  private void start(Test test) {
    SwingUtilities.invokeLater(new Runnable(this, test) {
          private final Test val$test;
          
          private final TestRunner this$0;
          
          public void run() {
            int total = this.val$test.countTestCases();
            this.this$0.fProgressIndicator.start(total);
            this.this$0.fCounterPanel.setTotal(total);
          }
        });
  }
  
  private void synchUI() {
    try {
      SwingUtilities.invokeAndWait(new Runnable(this) {
            private final TestRunner this$0;
            
            public void run() {}
          });
    } catch (Exception e) {}
  }
  
  public void terminate() {
    this.fFrame.dispose();
    try {
      saveHistory();
    } catch (IOException e) {
      System.out.println("Couldn't save test run history");
    } 
    System.exit(0);
  }
  
  public void textChanged() {
    this.fRun.setEnabled((getSuiteText().length() > 0));
    clearStatus();
  }
  
  protected void clearStatus() {
    this.fStatusLine.clear();
  }
  
  public static Icon getIconResource(Class clazz, String name) {
    URL url = clazz.getResource(name);
    if (url == null) {
      System.err.println("Warning: could not load \"" + name + "\" icon");
      return null;
    } 
    return new ImageIcon(url);
  }
  
  private void about() {
    AboutDialog about = new AboutDialog(this.fFrame);
    about.show();
  }
}
