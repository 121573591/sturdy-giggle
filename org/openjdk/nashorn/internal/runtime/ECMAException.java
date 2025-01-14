package org.openjdk.nashorn.internal.runtime;

import javax.script.ScriptException;
import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;

public final class ECMAException extends NashornException {
  public static final CompilerConstants.Call CREATE = CompilerConstants.staticCallNoLookup(ECMAException.class, "create", ECMAException.class, new Class[] { Object.class, String.class, int.class, int.class });
  
  public static final CompilerConstants.FieldAccess THROWN = CompilerConstants.virtualField(ECMAException.class, "thrown", Object.class);
  
  private static final String EXCEPTION_PROPERTY = "nashornException";
  
  public final Object thrown;
  
  private ECMAException(Object thrown, String fileName, int line, int column) {
    super(ScriptRuntime.safeToString(thrown), asThrowable(thrown), fileName, line, column);
    this.thrown = thrown;
    setExceptionToThrown();
  }
  
  public ECMAException(Object thrown, Throwable cause) {
    super(ScriptRuntime.safeToString(thrown), cause);
    this.thrown = thrown;
    setExceptionToThrown();
  }
  
  public static ECMAException create(Object thrown, String fileName, int line, int column) {
    if (thrown instanceof ScriptObject) {
      Object exception = getException((ScriptObject)thrown);
      if (exception instanceof ECMAException) {
        ECMAException ee = (ECMAException)exception;
        if (ee.getThrown() == thrown) {
          ee.setFileName(fileName);
          ee.setLineNumber(line);
          ee.setColumnNumber(column);
          return ee;
        } 
      } 
    } 
    return new ECMAException(thrown, fileName, line, column);
  }
  
  public Object getThrown() {
    return this.thrown;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String fileName = getFileName();
    int line = getLineNumber();
    int column = getColumnNumber();
    if (fileName != null) {
      sb.append(fileName);
      if (line >= 0) {
        sb.append(':');
        sb.append(line);
      } 
      if (column >= 0) {
        sb.append(':');
        sb.append(column);
      } 
      sb.append(' ');
    } else {
      sb.append("ECMAScript Exception: ");
    } 
    sb.append(getMessage());
    return sb.toString();
  }
  
  public static Object getException(ScriptObject errObj) {
    if (errObj.hasOwnProperty("nashornException"))
      return errObj.get("nashornException"); 
    return null;
  }
  
  public static Object printStackTrace(ScriptObject errObj) {
    Object exception = getException(errObj);
    if (exception instanceof Throwable) {
      ((Throwable)exception).printStackTrace(Context.getCurrentErr());
    } else {
      Context.err("<stack trace not available>");
    } 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object getLineNumber(ScriptObject errObj) {
    Object e = getException(errObj);
    if (e instanceof NashornException)
      return Integer.valueOf(((NashornException)e).getLineNumber()); 
    if (e instanceof ScriptException)
      return Integer.valueOf(((ScriptException)e).getLineNumber()); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object getColumnNumber(ScriptObject errObj) {
    Object e = getException(errObj);
    if (e instanceof NashornException)
      return Integer.valueOf(((NashornException)e).getColumnNumber()); 
    if (e instanceof ScriptException)
      return Integer.valueOf(((ScriptException)e).getColumnNumber()); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static Object getFileName(ScriptObject errObj) {
    Object e = getException(errObj);
    if (e instanceof NashornException)
      return ((NashornException)e).getFileName(); 
    if (e instanceof ScriptException)
      return ((ScriptException)e).getFileName(); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static String safeToString(ScriptObject errObj) {
    Object name = ScriptRuntime.UNDEFINED;
    try {
      name = errObj.get("name");
    } catch (Exception exception) {}
    if (name == ScriptRuntime.UNDEFINED) {
      name = "Error";
    } else {
      name = ScriptRuntime.safeToString(name);
    } 
    Object msg = ScriptRuntime.UNDEFINED;
    try {
      msg = errObj.get("message");
    } catch (Exception exception) {}
    if (msg == ScriptRuntime.UNDEFINED) {
      msg = "";
    } else {
      msg = ScriptRuntime.safeToString(msg);
    } 
    if (((String)name).isEmpty())
      return (String)msg; 
    if (((String)msg).isEmpty())
      return (String)name; 
    return "" + name + ": " + name;
  }
  
  private static Throwable asThrowable(Object obj) {
    return (obj instanceof Throwable) ? (Throwable)obj : null;
  }
  
  private void setExceptionToThrown() {
    if (this.thrown instanceof ScriptObject) {
      ScriptObject sobj = (ScriptObject)this.thrown;
      if (!sobj.has("nashornException")) {
        sobj.addOwnProperty("nashornException", 2, this);
      } else {
        sobj.set("nashornException", this, 0);
      } 
    } 
  }
}
