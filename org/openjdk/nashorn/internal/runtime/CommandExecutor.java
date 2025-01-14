package org.openjdk.nashorn.internal.runtime;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

class CommandExecutor {
  private static final int BUFFER_SIZE = 1024;
  
  private static final boolean IS_WINDOWS = ((Boolean)AccessController.<Boolean>doPrivileged(() -> Boolean.valueOf(System.getProperty("os.name").contains("Windows")))).booleanValue();
  
  private static final String CYGDRIVE = "/cygdrive/";
  
  private static final String HOME_DIRECTORY = AccessController.<String>doPrivileged(() -> System.getProperty("user.home"));
  
  enum RedirectType {
    NO_REDIRECT, REDIRECT_INPUT, REDIRECT_OUTPUT, REDIRECT_OUTPUT_APPEND, REDIRECT_ERROR, REDIRECT_ERROR_APPEND, REDIRECT_OUTPUT_ERROR_APPEND, REDIRECT_ERROR_TO_OUTPUT;
  }
  
  private static final String[] redirectPrefixes = new String[] { "<", "0<", ">", "1>", ">>", "1>>", "2>", "2>>", "&>", "2>&1" };
  
  private static final RedirectType[] redirects = new RedirectType[] { RedirectType.REDIRECT_INPUT, RedirectType.REDIRECT_INPUT, RedirectType.REDIRECT_OUTPUT, RedirectType.REDIRECT_OUTPUT, RedirectType.REDIRECT_OUTPUT_APPEND, RedirectType.REDIRECT_OUTPUT_APPEND, RedirectType.REDIRECT_ERROR, RedirectType.REDIRECT_ERROR_APPEND, RedirectType.REDIRECT_OUTPUT_ERROR_APPEND, RedirectType.REDIRECT_ERROR_TO_OUTPUT };
  
  static final int EXIT_SUCCESS = 0;
  
  static final int EXIT_FAILURE = 1;
  
  private Map<String, String> environment;
  
  private String inputString;
  
  private String outputString;
  
  private String errorString;
  
  private int exitCode;
  
  private InputStream inputStream;
  
  private OutputStream outputStream;
  
  private OutputStream errorStream;
  
  private static class RedirectInfo {
    private boolean hasRedirects = false;
    
    private ProcessBuilder.Redirect inputRedirect = ProcessBuilder.Redirect.PIPE;
    
    private ProcessBuilder.Redirect outputRedirect = ProcessBuilder.Redirect.PIPE;
    
    private ProcessBuilder.Redirect errorRedirect = ProcessBuilder.Redirect.PIPE;
    
    private boolean mergeError = false;
    
    boolean check(String token, Iterator<String> iterator, String cwd) {
      for (int i = 0; i < CommandExecutor.redirectPrefixes.length; i++) {
        String prefix = CommandExecutor.redirectPrefixes[i];
        if (token.startsWith(prefix)) {
          this.hasRedirects = true;
          CommandExecutor.RedirectType redirect = CommandExecutor.redirects[i];
          token = token.substring(prefix.length());
          File file = null;
          if (redirect != CommandExecutor.RedirectType.REDIRECT_ERROR_TO_OUTPUT) {
            if (token.length() == 0)
              if (iterator.hasNext()) {
                token = iterator.next();
              } else {
                token = CommandExecutor.IS_WINDOWS ? "NUL:" : "/dev/null";
              }  
            file = CommandExecutor.resolvePath(cwd, token).toFile();
          } 
          switch (redirect) {
            case REDIRECT_INPUT:
              this.inputRedirect = ProcessBuilder.Redirect.from(file);
              return true;
            case REDIRECT_OUTPUT:
              this.outputRedirect = ProcessBuilder.Redirect.to(file);
              return true;
            case REDIRECT_OUTPUT_APPEND:
              this.outputRedirect = ProcessBuilder.Redirect.appendTo(file);
              return true;
            case REDIRECT_ERROR:
              this.errorRedirect = ProcessBuilder.Redirect.to(file);
              return true;
            case REDIRECT_ERROR_APPEND:
              this.errorRedirect = ProcessBuilder.Redirect.appendTo(file);
              return true;
            case REDIRECT_OUTPUT_ERROR_APPEND:
              this.outputRedirect = ProcessBuilder.Redirect.to(file);
              this.errorRedirect = ProcessBuilder.Redirect.to(file);
              this.mergeError = true;
              return true;
            case REDIRECT_ERROR_TO_OUTPUT:
              this.mergeError = true;
              return true;
          } 
          return false;
        } 
      } 
      return false;
    }
    
    void apply(ProcessBuilder pb) {
      if (this.hasRedirects) {
        File outputFile = this.outputRedirect.file();
        File errorFile = this.errorRedirect.file();
        if (outputFile != null && outputFile.equals(errorFile))
          this.mergeError = true; 
        pb.redirectInput(this.inputRedirect);
        pb.redirectOutput(this.outputRedirect);
        pb.redirectError(this.errorRedirect);
        pb.redirectErrorStream(this.mergeError);
      } 
    }
  }
  
  private static class Piper implements Runnable {
    private final InputStream input;
    
    private final OutputStream output;
    
    private final Thread thread;
    
    Piper(InputStream input, OutputStream output) {
      this.input = input;
      this.output = output;
      this.thread = new Thread(this, "$EXEC Piper");
    }
    
    Piper start() {
      this.thread.setDaemon(true);
      this.thread.start();
      return this;
    }
    
    public void run() {
      try {
        byte[] b = new byte[1024];
        int read;
        while (-1 < (read = this.input.read(b, 0, b.length)))
          this.output.write(b, 0, read); 
      } catch (Exception e) {
        throw new RuntimeException("Broken pipe", e);
      } finally {
        try {
          this.input.close();
        } catch (IOException iOException) {}
        try {
          this.output.close();
        } catch (IOException iOException) {}
      } 
    }
    
    public void join() throws InterruptedException {
      this.thread.join();
    }
  }
  
  private final List<ProcessBuilder> processBuilders = new ArrayList<>();
  
  CommandExecutor() {
    this.environment = new HashMap<>();
    this.inputString = "";
    this.outputString = "";
    this.errorString = "";
    this.exitCode = 0;
    this.inputStream = null;
    this.outputStream = null;
    this.errorStream = null;
  }
  
  private String envVarValue(String key, String deflt) {
    return this.environment.getOrDefault(key, deflt);
  }
  
  private long envVarLongValue(String key) {
    try {
      return Long.parseLong(envVarValue(key, "0"));
    } catch (NumberFormatException ex) {
      return 0L;
    } 
  }
  
  private boolean envVarBooleanValue(String key) {
    return (envVarLongValue(key) != 0L);
  }
  
  private static String stripQuotes(String token) {
    if ((token.startsWith("\"") && token.endsWith("\"")) || (token
      .startsWith("'") && token.endsWith("'")))
      token = token.substring(1, token.length() - 1); 
    return token;
  }
  
  private static Path resolvePath(String cwd, String fileName) {
    return Paths.get(sanitizePath(cwd), new String[0]).resolve(fileName).normalize();
  }
  
  private boolean builtIn(List<String> cmd, String cwd) {
    boolean cygpath;
    String newCWD;
    Path cwdPath;
    File file;
    String scwd;
    switch ((String)cmd.get(0)) {
      case "cd":
        cygpath = (IS_WINDOWS && cwd.startsWith("/cygdrive/"));
        newCWD = (cmd.size() < 2) ? HOME_DIRECTORY : cmd.get(1);
        cwdPath = resolvePath(cwd, newCWD);
        file = cwdPath.toFile();
        if (!file.exists()) {
          reportError("file.not.exist", file.toString());
          return true;
        } 
        if (!file.isDirectory()) {
          reportError("not.directory", file.toString());
          return true;
        } 
        scwd = cwdPath.toString();
        if (cygpath && scwd.length() >= 2 && 
          Character.isLetter(scwd.charAt(0)) && scwd.charAt(1) == ':')
          scwd = "/cygdrive/" + Character.toLowerCase(scwd.charAt(0)) + "/" + scwd.substring(2); 
        this.environment.put("PWD", scwd);
        return true;
      case "setenv":
        if (3 <= cmd.size()) {
          String key = cmd.get(1);
          String value = cmd.get(2);
          this.environment.put(key, value);
        } 
        return true;
      case "unsetenv":
        if (2 <= cmd.size()) {
          String key = cmd.get(1);
          this.environment.remove(key);
        } 
        return true;
    } 
    return false;
  }
  
  private List<String> preprocessCommand(List<String> tokens, String cwd, RedirectInfo redirectInfo) {
    List<String> command = new ArrayList<>();
    Iterator<String> iterator = tokens.iterator();
    while (iterator.hasNext()) {
      String token = iterator.next();
      if (redirectInfo.check(token, iterator, cwd))
        continue; 
      command.add(stripQuotes(token));
    } 
    if (command.size() > 0)
      command.set(0, sanitizePath(command.get(0))); 
    return command;
  }
  
  private static String sanitizePath(String d) {
    if (!IS_WINDOWS || !d.startsWith("/cygdrive/"))
      return d; 
    String pd = d.substring("/cygdrive/".length());
    if (pd.length() >= 2 && pd.charAt(1) == '/')
      return "" + pd.charAt(0) + ":" + pd.charAt(0); 
    if (pd.length() == 1)
      return "" + pd.charAt(0) + ":"; 
    return d;
  }
  
  private void createProcessBuilder(List<String> command, String cwd, RedirectInfo redirectInfo) {
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File(sanitizePath(cwd)));
    Map<String, String> processEnvironment = pb.environment();
    processEnvironment.clear();
    processEnvironment.putAll(this.environment);
    redirectInfo.apply(pb);
    this.processBuilders.add(pb);
  }
  
  private void command(List<String> tokens, boolean isPiped) {
    if (envVarBooleanValue("JJS_ECHO"))
      System.out.println(String.join(" ", (Iterable)tokens)); 
    String cwd = envVarValue("PWD", HOME_DIRECTORY);
    RedirectInfo redirectInfo = new RedirectInfo();
    List<String> command = preprocessCommand(tokens, cwd, redirectInfo);
    if (command.isEmpty() || builtIn(command, cwd))
      return; 
    createProcessBuilder(command, cwd, redirectInfo);
    if (isPiped)
      return; 
    ProcessBuilder firstProcessBuilder = this.processBuilders.get(0);
    ProcessBuilder lastProcessBuilder = this.processBuilders.get(this.processBuilders.size() - 1);
    boolean inputIsPipe = (firstProcessBuilder.redirectInput() == ProcessBuilder.Redirect.PIPE);
    boolean outputIsPipe = (lastProcessBuilder.redirectOutput() == ProcessBuilder.Redirect.PIPE);
    boolean errorIsPipe = (lastProcessBuilder.redirectError() == ProcessBuilder.Redirect.PIPE);
    boolean inheritIO = envVarBooleanValue("JJS_INHERIT_IO");
    if (inputIsPipe && (inheritIO || this.inputStream == System.in)) {
      firstProcessBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
      inputIsPipe = false;
    } 
    if (outputIsPipe && (inheritIO || this.outputStream == System.out)) {
      lastProcessBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
      outputIsPipe = false;
    } 
    if (errorIsPipe && (inheritIO || this.errorStream == System.err)) {
      lastProcessBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
      errorIsPipe = false;
    } 
    List<Process> processes = new ArrayList<>();
    for (ProcessBuilder pb : this.processBuilders) {
      try {
        processes.add(pb.start());
      } catch (IOException ex) {
        reportError("unknown.command", String.join(" ", (Iterable)pb.command()));
        return;
      } 
    } 
    this.processBuilders.clear();
    Process firstProcess = processes.get(0);
    Process lastProcess = processes.get(processes.size() - 1);
    ByteArrayOutputStream byteOutputStream = null;
    ByteArrayOutputStream byteErrorStream = null;
    List<Piper> piperThreads = new ArrayList<>();
    if (inputIsPipe)
      piperThreads.add((new Piper(
            Objects.<InputStream>requireNonNullElseGet(this.inputStream, () -> new ByteArrayInputStream(this.inputString.getBytes())), firstProcess
            .getOutputStream())).start()); 
    if (outputIsPipe)
      if (this.outputStream != null) {
        piperThreads.add((new Piper(lastProcess.getInputStream(), this.outputStream)).start());
      } else {
        byteOutputStream = new ByteArrayOutputStream(1024);
        piperThreads.add((new Piper(lastProcess.getInputStream(), byteOutputStream)).start());
      }  
    if (errorIsPipe)
      if (this.errorStream != null) {
        piperThreads.add((new Piper(lastProcess.getErrorStream(), this.errorStream)).start());
      } else {
        byteErrorStream = new ByteArrayOutputStream(1024);
        piperThreads.add((new Piper(lastProcess.getErrorStream(), byteErrorStream)).start());
      }  
    for (int i = 0, n = processes.size() - 1; i < n; i++) {
      Process prev = processes.get(i);
      Process next = processes.get(i + 1);
      piperThreads.add((new Piper(prev.getInputStream(), next.getOutputStream())).start());
    } 
    try {
      long timeout = envVarLongValue("JJS_TIMEOUT");
      if (timeout != 0L) {
        if (lastProcess.waitFor(timeout, TimeUnit.MILLISECONDS)) {
          this.exitCode = lastProcess.exitValue();
        } else {
          reportError("timeout", Long.toString(timeout));
        } 
      } else {
        this.exitCode = lastProcess.waitFor();
      } 
      for (Piper piper : piperThreads)
        piper.join(); 
      this.outputString += this.outputString;
      this.errorString += this.errorString;
    } catch (InterruptedException ex) {
      processes.forEach(p -> {
            if (p.isAlive())
              p.destroy(); 
            this.exitCode = (this.exitCode == 0) ? p.exitValue() : this.exitCode;
          });
    } 
    if (this.exitCode != 0 && envVarBooleanValue("JJS_THROW_ON_EXIT"))
      throw ECMAErrors.rangeError("exec.returned.non.zero", new String[] { ScriptRuntime.safeToString(Integer.valueOf(this.exitCode)) }); 
  }
  
  private static StreamTokenizer createTokenizer(String script) {
    StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(script));
    tokenizer.resetSyntax();
    tokenizer.wordChars(0, 255);
    tokenizer.whitespaceChars(0, 32);
    tokenizer.commentChar(35);
    tokenizer.quoteChar(34);
    tokenizer.quoteChar(39);
    tokenizer.eolIsSignificant(true);
    tokenizer.ordinaryChar(59);
    tokenizer.ordinaryChar(124);
    return tokenizer;
  }
  
  void process(String script) {
    StreamTokenizer tokenizer = createTokenizer(script);
    List<String> command = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    try {
      while (tokenizer.nextToken() != -1) {
        String token = tokenizer.sval;
        if (token == null) {
          if (sb.length() != 0) {
            command.add(sb.append(token).toString());
            sb.setLength(0);
          } 
          command(command, (tokenizer.ttype == 124));
          if (this.exitCode != 0)
            return; 
          command.clear();
          continue;
        } 
        if (token.endsWith("\\")) {
          sb.append(token, 0, token.length() - 1).append(' ');
          continue;
        } 
        if (sb.length() == 0) {
          if (tokenizer.ttype != -3) {
            sb.append((char)tokenizer.ttype);
            sb.append(token);
            sb.append((char)tokenizer.ttype);
            token = sb.toString();
            sb.setLength(0);
          } 
          command.add(token);
          continue;
        } 
        command.add(sb.append(token).toString());
        sb.setLength(0);
      } 
    } catch (IOException iOException) {}
    if (sb.length() != 0)
      command.add(sb.toString()); 
    command(command, false);
  }
  
  void process(List<String> tokens) {
    List<String> command = new ArrayList<>();
    Iterator<String> iterator = tokens.iterator();
    while (iterator.hasNext() && this.exitCode == 0) {
      String token = iterator.next();
      if (token == null)
        continue; 
      switch (token) {
        case "|":
          command(command, true);
          command.clear();
          continue;
        case ";":
          command(command, false);
          command.clear();
          continue;
      } 
      command.add(token);
    } 
    command(command, false);
  }
  
  void reportError(String msg, String object) {
    this.errorString += this.errorString;
    this.exitCode = 1;
  }
  
  String getOutputString() {
    return this.outputString;
  }
  
  String getErrorString() {
    return this.errorString;
  }
  
  int getExitCode() {
    return this.exitCode;
  }
  
  void setEnvironment(Map<String, String> environment) {
    this.environment = environment;
  }
  
  void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }
  
  void setInputString(String inputString) {
    this.inputString = inputString;
  }
  
  void setOutputStream(OutputStream outputStream) {
    this.outputStream = outputStream;
  }
  
  void setErrorStream(OutputStream errorStream) {
    this.errorStream = errorStream;
  }
}
