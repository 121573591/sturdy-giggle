package org.openjdk.nashorn.api.tree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.options.Options;

final class ParserImpl implements Parser {
  private final ScriptEnvironment env;
  
  private final boolean moduleMode;
  
  ParserImpl(String... args) throws IllegalArgumentException {
    Objects.requireNonNull(args);
    boolean seenModuleOption = false;
    for (int idx = 0; idx < args.length; idx++) {
      String opt = args[idx];
      if (opt.equals("--es6-module")) {
        seenModuleOption = true;
        args[idx] = "--language=es6";
        break;
      } 
    } 
    this.moduleMode = seenModuleOption;
    String[] newArgs = Arrays.<String, String>copyOf(args, args.length + 1, String[].class);
    newArgs[args.length] = "--parse-only";
    Options options = new Options("nashorn");
    options.process(newArgs);
    this.env = new ScriptEnvironment(options, new PrintWriter(System.out), new PrintWriter(System.err));
  }
  
  public CompilationUnitTree parse(File file, DiagnosticListener listener) throws IOException, NashornException {
    if (this.moduleMode)
      return parseModule(file, listener); 
    Source src = Source.sourceFor(((File)Objects.<File>requireNonNull(file)).getName(), file);
    return translate(makeParser(src, listener).parse());
  }
  
  public CompilationUnitTree parse(Path path, DiagnosticListener listener) throws IOException, NashornException {
    if (this.moduleMode)
      return parseModule(path, listener); 
    Source src = Source.sourceFor(((Path)Objects.<Path>requireNonNull(path)).toString(), path);
    return translate(makeParser(src, listener).parse());
  }
  
  public CompilationUnitTree parse(URL url, DiagnosticListener listener) throws IOException, NashornException {
    if (this.moduleMode)
      return parseModule(url, listener); 
    Source src = Source.sourceFor(url.toString(), url);
    return translate(makeParser(src, listener).parse());
  }
  
  public CompilationUnitTree parse(String name, Reader reader, DiagnosticListener listener) throws IOException, NashornException {
    if (this.moduleMode)
      return parseModule(name, reader, listener); 
    Source src = Source.sourceFor(Objects.<String>requireNonNull(name), Objects.<Reader>requireNonNull(reader));
    return translate(makeParser(src, listener).parse());
  }
  
  public CompilationUnitTree parse(String name, String code, DiagnosticListener listener) throws NashornException {
    if (this.moduleMode)
      return parseModule(name, code, listener); 
    Source src = Source.sourceFor(name, code);
    return translate(makeParser(src, listener).parse());
  }
  
  public CompilationUnitTree parse(ScriptObjectMirror scriptObj, DiagnosticListener listener) throws NashornException {
    if (this.moduleMode)
      return parseModule(scriptObj, listener); 
    Map<?, ?> map = (Map<?, ?>)Objects.requireNonNull(scriptObj);
    if (map.containsKey("script") && map.containsKey("name")) {
      String script = JSType.toString(map.get("script"));
      String name = JSType.toString(map.get("name"));
      Source src = Source.sourceFor(name, script);
      return translate(makeParser(src, listener).parse());
    } 
    throw new IllegalArgumentException("can't find 'script' and 'name' properties");
  }
  
  private CompilationUnitTree parseModule(File file, DiagnosticListener listener) throws IOException, NashornException {
    Source src = Source.sourceFor(((File)Objects.<File>requireNonNull(file)).getName(), file);
    return makeModule(src, listener);
  }
  
  private CompilationUnitTree parseModule(Path path, DiagnosticListener listener) throws IOException, NashornException {
    Source src = Source.sourceFor(((Path)Objects.<Path>requireNonNull(path)).toString(), path);
    return makeModule(src, listener);
  }
  
  private CompilationUnitTree parseModule(URL url, DiagnosticListener listener) throws IOException, NashornException {
    Source src = Source.sourceFor(url.toString(), url);
    return makeModule(src, listener);
  }
  
  private CompilationUnitTree parseModule(String name, Reader reader, DiagnosticListener listener) throws IOException, NashornException {
    Source src = Source.sourceFor(Objects.<String>requireNonNull(name), Objects.<Reader>requireNonNull(reader));
    return makeModule(src, listener);
  }
  
  private CompilationUnitTree parseModule(String name, String code, DiagnosticListener listener) throws NashornException {
    Source src = Source.sourceFor(name, code);
    return makeModule(src, listener);
  }
  
  private CompilationUnitTree parseModule(ScriptObjectMirror scriptObj, DiagnosticListener listener) throws NashornException {
    Map<?, ?> map = (Map<?, ?>)Objects.requireNonNull(scriptObj);
    if (map.containsKey("script") && map.containsKey("name")) {
      String script = JSType.toString(map.get("script"));
      String name = JSType.toString(map.get("name"));
      Source src = Source.sourceFor(name, script);
      return makeModule(src, listener);
    } 
    throw new IllegalArgumentException("can't find 'script' and 'name' properties");
  }
  
  private CompilationUnitTree makeModule(Source src, DiagnosticListener listener) {
    FunctionNode modFunc = makeParser(src, listener).parseModule(src.getName());
    return (new IRTranslator()).translate(modFunc);
  }
  
  private Parser makeParser(Source source, DiagnosticListener listener) {
    ErrorManager errMgr = (listener != null) ? new ListenerErrorManager(listener) : (ErrorManager)new Context.ThrowErrorManager();
    return new Parser(this.env, source, errMgr);
  }
  
  private static class ListenerErrorManager extends ErrorManager {
    private final DiagnosticListener listener;
    
    ListenerErrorManager(DiagnosticListener listener) {
      listener.getClass();
      this.listener = listener;
    }
    
    public void error(String msg) {
      error(new ParserException(msg));
    }
    
    public void error(ParserException e) {
      this.listener.report(new DiagnosticImpl(e, Diagnostic.Kind.ERROR));
    }
    
    public void warning(String msg) {
      warning(new ParserException(msg));
    }
    
    public void warning(ParserException e) {
      this.listener.report(new DiagnosticImpl(e, Diagnostic.Kind.WARNING));
    }
  }
  
  private static CompilationUnitTree translate(FunctionNode node) {
    return (new IRTranslator()).translate(node);
  }
}
