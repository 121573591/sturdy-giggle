package org.openjdk.nashorn.api.scripting;

import java.util.List;
import java.util.Objects;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import org.openjdk.nashorn.internal.parser.JSONParser;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.Version;

public final class NashornScriptEngineFactory implements ScriptEngineFactory {
  public String getEngineName() {
    return "OpenJDK Nashorn";
  }
  
  public String getEngineVersion() {
    return Version.version();
  }
  
  public List<String> getExtensions() {
    return extensions;
  }
  
  public String getLanguageName() {
    return "ECMAScript";
  }
  
  public String getLanguageVersion() {
    return "ECMA - 262 Edition 5.1";
  }
  
  public String getMethodCallSyntax(String obj, String method, String... args) {
    StringBuilder sb = (new StringBuilder()).append(Objects.<String>requireNonNull(obj)).append('.').append(Objects.<String>requireNonNull(method)).append('(');
    int len = args.length;
    if (len > 0)
      sb.append(Objects.<String>requireNonNull(args[0])); 
    for (int i = 1; i < len; i++)
      sb.append(',').append(Objects.<String>requireNonNull(args[i])); 
    sb.append(')');
    return sb.toString();
  }
  
  public List<String> getMimeTypes() {
    return mimeTypes;
  }
  
  public List<String> getNames() {
    return names;
  }
  
  public String getOutputStatement(String toDisplay) {
    return "print(" + JSONParser.quote(toDisplay) + ")";
  }
  
  public Object getParameter(String key) {
    switch (key) {
      case "javax.script.name":
        return "javascript";
      case "javax.script.engine":
        return getEngineName();
      case "javax.script.engine_version":
        return getEngineVersion();
      case "javax.script.language":
        return getLanguageName();
      case "javax.script.language_version":
        return getLanguageVersion();
      case "THREADING":
        return null;
    } 
    return null;
  }
  
  public String getProgram(String... statements) {
    Objects.requireNonNull(statements);
    StringBuilder sb = new StringBuilder();
    for (String statement : statements)
      sb.append(Objects.<String>requireNonNull(statement)).append(';'); 
    return sb.toString();
  }
  
  private static final String[] DEFAULT_OPTIONS = new String[] { "-doe" };
  
  public ScriptEngine getScriptEngine() {
    try {
      return new NashornScriptEngine(this, DEFAULT_OPTIONS, getAppClassLoader(), null);
    } catch (RuntimeException e) {
      if (Context.DEBUG)
        e.printStackTrace(); 
      throw e;
    } 
  }
  
  public ScriptEngine getScriptEngine(ClassLoader appLoader) {
    return newEngine(DEFAULT_OPTIONS, appLoader, null);
  }
  
  public ScriptEngine getScriptEngine(ClassFilter classFilter) {
    return newEngine(DEFAULT_OPTIONS, getAppClassLoader(), Objects.<ClassFilter>requireNonNull(classFilter));
  }
  
  public ScriptEngine getScriptEngine(String... args) {
    return newEngine(Objects.<String[]>requireNonNull(args), getAppClassLoader(), null);
  }
  
  public ScriptEngine getScriptEngine(String[] args, ClassLoader appLoader) {
    return newEngine(Objects.<String[]>requireNonNull(args), appLoader, null);
  }
  
  public ScriptEngine getScriptEngine(String[] args, ClassLoader appLoader, ClassFilter classFilter) {
    return newEngine(Objects.<String[]>requireNonNull(args), appLoader, Objects.<ClassFilter>requireNonNull(classFilter));
  }
  
  private ScriptEngine newEngine(String[] args, ClassLoader appLoader, ClassFilter classFilter) {
    checkConfigPermission();
    try {
      return new NashornScriptEngine(this, args, appLoader, classFilter);
    } catch (RuntimeException e) {
      if (Context.DEBUG)
        e.printStackTrace(); 
      throw e;
    } 
  }
  
  private static void checkConfigPermission() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("nashorn.setConfig")); 
  }
  
  private static final List<String> names = List.of("nashorn", "Nashorn", "js", "JS", "JavaScript", "javascript", "ECMAScript", "ecmascript");
  
  private static final List<String> mimeTypes = List.of("application/javascript", "application/ecmascript", "text/javascript", "text/ecmascript");
  
  private static final List<String> extensions = List.of("js");
  
  private static ClassLoader getAppClassLoader() {
    Objects.requireNonNull(NashornScriptEngineFactory.class);
    return Objects.<ClassLoader>requireNonNullElseGet(Thread.currentThread().getContextClassLoader(), NashornScriptEngineFactory.class::getClassLoader);
  }
}
