package org.openjdk.nashorn.api.scripting;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

public abstract class NashornException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  private String fileName;
  
  private int line;
  
  private boolean lineAndFileNameUnknown;
  
  private int column;
  
  private Object ecmaError;
  
  protected NashornException(String msg, String fileName, int line, int column) {
    this(msg, null, fileName, line, column);
  }
  
  protected NashornException(String msg, Throwable cause, String fileName, int line, int column) {
    super(msg, cause);
    this.fileName = fileName;
    this.line = line;
    this.column = column;
  }
  
  protected NashornException(String msg, Throwable cause) {
    super(msg, cause);
    this.column = -1;
    this.lineAndFileNameUnknown = true;
  }
  
  public final String getFileName() {
    ensureLineAndFileName();
    return this.fileName;
  }
  
  public final void setFileName(String fileName) {
    this.fileName = fileName;
    this.lineAndFileNameUnknown = false;
  }
  
  public final int getLineNumber() {
    ensureLineAndFileName();
    return this.line;
  }
  
  public final void setLineNumber(int line) {
    this.lineAndFileNameUnknown = false;
    this.line = line;
  }
  
  public final int getColumnNumber() {
    return this.column;
  }
  
  public final void setColumnNumber(int column) {
    this.column = column;
  }
  
  public static StackTraceElement[] getScriptFrames(Throwable exception) {
    StackTraceElement[] frames = exception.getStackTrace();
    List<StackTraceElement> filtered = new ArrayList<>();
    for (StackTraceElement st : frames) {
      if (ECMAErrors.isScriptFrame(st)) {
        String className = "<" + st.getFileName() + ">";
        String methodName = st.getMethodName();
        if (methodName.equals(CompilerConstants.PROGRAM.symbolName())) {
          methodName = "<program>";
        } else {
          methodName = stripMethodName(methodName);
        } 
        filtered.add(new StackTraceElement(className, methodName, st
              .getFileName(), st.getLineNumber()));
      } 
    } 
    return filtered.<StackTraceElement>toArray(new StackTraceElement[0]);
  }
  
  private static String stripMethodName(String methodName) {
    String name = methodName;
    int nestedSeparator = name.lastIndexOf(CompilerConstants.NESTED_FUNCTION_SEPARATOR.symbolName());
    if (nestedSeparator >= 0)
      name = name.substring(nestedSeparator + 1); 
    int idSeparator = name.indexOf(CompilerConstants.ID_FUNCTION_SEPARATOR.symbolName());
    if (idSeparator >= 0)
      name = name.substring(0, idSeparator); 
    return name.contains(CompilerConstants.ANON_FUNCTION_PREFIX.symbolName()) ? "<anonymous>" : name;
  }
  
  public static String getScriptStackString(Throwable exception) {
    StringBuilder buf = new StringBuilder();
    StackTraceElement[] frames = getScriptFrames(exception);
    for (StackTraceElement st : frames) {
      buf.append("\tat ");
      buf.append(st.getMethodName());
      buf.append(" (");
      buf.append(st.getFileName());
      buf.append(':');
      buf.append(st.getLineNumber());
      buf.append(")\n");
    } 
    int len = buf.length();
    if (len > 0) {
      assert buf.charAt(len - 1) == '\n';
      buf.deleteCharAt(len - 1);
    } 
    return buf.toString();
  }
  
  protected Object getThrown() {
    return null;
  }
  
  NashornException initEcmaError(ScriptObject global) {
    if (this.ecmaError != null)
      return this; 
    Object thrown = getThrown();
    if (thrown instanceof ScriptObject) {
      setEcmaError(ScriptObjectMirror.wrap(thrown, global));
    } else {
      setEcmaError(thrown);
    } 
    return this;
  }
  
  public Object getEcmaError() {
    return this.ecmaError;
  }
  
  public void setEcmaError(Object ecmaError) {
    this.ecmaError = ecmaError;
  }
  
  private void ensureLineAndFileName() {
    if (this.lineAndFileNameUnknown) {
      for (StackTraceElement ste : getStackTrace()) {
        if (ECMAErrors.isScriptFrame(ste)) {
          this.fileName = ste.getFileName();
          this.line = ste.getLineNumber();
          return;
        } 
      } 
      this.lineAndFileNameUnknown = false;
    } 
  }
}
