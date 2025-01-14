package org.openjdk.nashorn.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.debug.ASTWriter;
import org.openjdk.nashorn.internal.ir.debug.PrintVisitor;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.openjdk.nashorn.internal.runtime.options.Options;

public class Shell implements PartialParser {
  private static final String MESSAGE_RESOURCE = "org.openjdk.nashorn.tools.resources.Shell";
  
  protected static final ResourceBundle bundle = ResourceBundle.getBundle("org.openjdk.nashorn.tools.resources.Shell", Locale.getDefault());
  
  public static final int SUCCESS = 0;
  
  public static final int COMMANDLINE_ERROR = 100;
  
  public static final int COMPILATION_ERROR = 101;
  
  public static final int RUNTIME_ERROR = 102;
  
  public static final int IO_ERROR = 103;
  
  public static final int INTERNAL_ERROR = 104;
  
  public static void main(String[] args) {
    try {
      int exitCode = main(System.in, System.out, System.err, args);
      if (exitCode != 0)
        System.exit(exitCode); 
    } catch (IOException e) {
      System.err.println(e);
      System.exit(103);
    } 
  }
  
  public static int main(InputStream in, OutputStream out, OutputStream err, String[] args) throws IOException {
    return (new Shell()).run(in, out, err, args);
  }
  
  protected final int run(InputStream in, OutputStream out, OutputStream err, String[] args) throws IOException {
    Context context = makeContext(in, out, err, args);
    if (context == null)
      return 100; 
    Global global = context.createGlobal();
    ScriptEnvironment env = context.getEnv();
    List<String> files = env.getFiles();
    if (files.isEmpty())
      return readEvalPrint(context, global); 
    if (env._compile_only)
      return compileScripts(context, global, files); 
    if (env._fx)
      return runFXScripts(context, global, files); 
    return runScripts(context, global, files);
  }
  
  private static Context makeContext(InputStream in, OutputStream out, OutputStream err, String[] args) {
    PrintStream pout = (out instanceof PrintStream) ? (PrintStream)out : new PrintStream(out);
    PrintStream perr = (err instanceof PrintStream) ? (PrintStream)err : new PrintStream(err);
    PrintWriter wout = new PrintWriter(pout, true);
    PrintWriter werr = new PrintWriter(perr, true);
    ErrorManager errors = new ErrorManager(werr);
    Options options = new Options("nashorn", werr);
    if (args != null)
      try {
        String[] prepArgs = preprocessArgs(args);
        options.process(prepArgs);
      } catch (IllegalArgumentException e) {
        werr.println(bundle.getString("shell.usage"));
        options.displayHelp(e);
        return null;
      }  
    if (!options.getBoolean("scripting"))
      for (String fileName : options.getFiles()) {
        File firstFile = new File(fileName);
        if (firstFile.isFile())
          try {
            FileReader fr = new FileReader(firstFile);
            try {
              int firstChar = fr.read();
              if (firstChar == 35) {
                options.set("scripting", true);
                fr.close();
                break;
              } 
              fr.close();
            } catch (Throwable throwable) {
              try {
                fr.close();
              } catch (Throwable throwable1) {
                throwable.addSuppressed(throwable1);
              } 
              throw throwable;
            } 
          } catch (IOException iOException) {} 
      }  
    return new Context(options, errors, wout, werr, Thread.currentThread().getContextClassLoader());
  }
  
  private static String[] preprocessArgs(String[] args) {
    if (args.length == 0)
      return args; 
    List<String> processedArgs = new ArrayList<>();
    processedArgs.addAll(Arrays.asList(args));
    if (args[0].startsWith("-") && !System.getProperty("os.name", "generic").startsWith("Mac OS X"))
      processedArgs.addAll(0, tokenizeString(processedArgs.remove(0))); 
    int shebangFilePos = -1;
    for (int i = 0; i < processedArgs.size(); i++) {
      String a = processedArgs.get(i);
      if (!a.startsWith("-")) {
        Path p = Paths.get(a, new String[0]);
        String l = "";
        try {
          BufferedReader r = Files.newBufferedReader(p);
          try {
            l = r.readLine();
            if (r != null)
              r.close(); 
          } catch (Throwable throwable) {
            if (r != null)
              try {
                r.close();
              } catch (Throwable throwable1) {
                throwable.addSuppressed(throwable1);
              }  
            throw throwable;
          } 
        } catch (IOException iOException) {}
        if (l != null && l.startsWith("#!"))
          shebangFilePos = i; 
        break;
      } 
    } 
    if (shebangFilePos != -1)
      processedArgs.add(shebangFilePos + 1, "--"); 
    return (String[])processedArgs.stream().toArray(x$0 -> new String[x$0]);
  }
  
  public static List<String> tokenizeString(String str) {
    StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(str));
    tokenizer.resetSyntax();
    tokenizer.wordChars(0, 255);
    tokenizer.whitespaceChars(0, 32);
    tokenizer.commentChar(35);
    tokenizer.quoteChar(34);
    tokenizer.quoteChar(39);
    List<String> tokenList = new ArrayList<>();
    StringBuilder toAppend = new StringBuilder();
    while (nextToken(tokenizer) != -1) {
      String s = tokenizer.sval;
      if (s.endsWith("\\")) {
        toAppend.append(s.substring(0, s.length() - 1)).append(' ');
        continue;
      } 
      tokenList.add(toAppend.append(s).toString());
      toAppend.setLength(0);
    } 
    if (toAppend.length() != 0)
      tokenList.add(toAppend.toString()); 
    return tokenList;
  }
  
  private static int nextToken(StreamTokenizer tokenizer) {
    try {
      return tokenizer.nextToken();
    } catch (IOException ioe) {
      return -1;
    } 
  }
  
  private static int compileScripts(Context context, Global global, List<String> files) throws IOException {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != global);
    ScriptEnvironment env = context.getEnv();
    try {
      if (globalChanged)
        Context.setGlobal(global); 
      ErrorManager errors = context.getErrorManager();
      for (String fileName : files) {
        FunctionNode functionNode = (new Parser(env, Source.sourceFor(fileName, new File(fileName)), errors, env._strict, 0, context.getLogger(Parser.class))).parse();
        if (errors.getNumberOfErrors() != 0)
          return 101; 
        Compiler.forNoInstallerCompilation(context, functionNode
            
            .getSource(), env._strict | functionNode
            .isStrict())
          .compile(functionNode, Compiler.CompilationPhases.COMPILE_ALL_NO_INSTALL);
        if (env._print_ast)
          context.getErr().println(new ASTWriter((Node)functionNode)); 
        if (env._print_parse)
          context.getErr().println(new PrintVisitor((Node)functionNode)); 
        if (errors.getNumberOfErrors() != 0)
          return 101; 
      } 
    } finally {
      env.getOut().flush();
      env.getErr().flush();
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
    return 0;
  }
  
  private int runScripts(Context context, Global global, List<String> files) throws IOException {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != global);
    try {
      if (globalChanged)
        Context.setGlobal(global); 
      ErrorManager errors = context.getErrorManager();
      for (String fileName : files) {
        if ("-".equals(fileName)) {
          int res = readEvalPrint(context, global);
          if (res != 0)
            return res; 
          continue;
        } 
        File file = new File(fileName);
        ScriptFunction script = context.compileScript(Source.sourceFor(fileName, file), (ScriptObject)global);
        if (script == null || errors.getNumberOfErrors() != 0) {
          if ((context.getEnv())._parse_only && !errors.hasErrors())
            continue; 
          return 101;
        } 
        try {
          apply(script, global);
        } catch (NashornException e) {
          errors.error(e.toString());
          if ((context.getEnv())._dump_on_error)
            e.printStackTrace(context.getErr()); 
          return 102;
        } 
      } 
    } finally {
      context.getOut().flush();
      context.getErr().flush();
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
    return 0;
  }
  
  private static int runFXScripts(Context context, Global global, List<String> files) throws IOException {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != global);
    try {
      if (globalChanged)
        Context.setGlobal(global); 
      global.addOwnProperty("$GLOBAL", 2, global);
      global.addOwnProperty("$SCRIPTS", 2, files);
      context.load(global, "fx:bootstrap.js");
    } catch (NashornException e) {
      context.getErrorManager().error(e.toString());
      if ((context.getEnv())._dump_on_error)
        e.printStackTrace(context.getErr()); 
      return 102;
    } finally {
      context.getOut().flush();
      context.getErr().flush();
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
    return 0;
  }
  
  protected Object apply(ScriptFunction target, Object self) {
    return ScriptRuntime.apply(target, self, new Object[0]);
  }
  
  public final int getLastExpressionStart(Context context, String code) {
    final int[] exprStart = { -1 };
    Parser p = new Parser(context.getEnv(), Source.sourceFor("<partial_code>", code), (ErrorManager)new Context.ThrowErrorManager()) {
        protected Expression expression() {
          exprStart[0] = this.start;
          return super.expression();
        }
        
        protected Expression assignmentExpression(boolean noIn) {
          exprStart[0] = this.start;
          return super.assignmentExpression(noIn);
        }
      };
    try {
      p.parse();
    } catch (Exception exception) {}
    return exprStart[0];
  }
  
  protected int readEvalPrint(Context context, Global global) {
    String prompt = bundle.getString("shell.prompt");
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter err = context.getErr();
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != global);
    ScriptEnvironment env = context.getEnv();
    try {
      if (globalChanged)
        Context.setGlobal(global); 
      global.addShellBuiltins();
      while (true) {
        err.print(prompt);
        err.flush();
        String source = "";
        try {
          source = in.readLine();
        } catch (IOException ioe) {
          err.println(ioe.toString());
        } 
        if (source == null)
          break; 
        if (source.isEmpty())
          continue; 
        try {
          Object res = context.eval((ScriptObject)global, source, global, "<shell>");
          if (res != ScriptRuntime.UNDEFINED)
            err.println(toString(res, global)); 
        } catch (Exception e) {
          err.println(e);
          if (env._dump_on_error)
            e.printStackTrace(err); 
        } 
      } 
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
    return 0;
  }
  
  protected static String toString(Object result, Global global) {
    if (result instanceof org.openjdk.nashorn.internal.runtime.Symbol)
      return result.toString(); 
    if (result instanceof org.openjdk.nashorn.internal.objects.NativeSymbol)
      return JSType.toPrimitive(result).toString(); 
    if (isArrayWithDefaultToString(result, global)) {
      StringBuilder sb = new StringBuilder();
      ArrayLikeIterator arrayLikeIterator = ArrayLikeIterator.arrayLikeIterator(result, true);
      while (arrayLikeIterator.hasNext()) {
        Object obj = arrayLikeIterator.next();
        if (obj != null && obj != ScriptRuntime.UNDEFINED)
          sb.append(toString(obj, global)); 
        if (arrayLikeIterator.hasNext())
          sb.append(','); 
      } 
      return sb.toString();
    } 
    return JSType.toString(result);
  }
  
  private static boolean isArrayWithDefaultToString(Object result, Global global) {
    if (result instanceof ScriptObject) {
      ScriptObject sobj = (ScriptObject)result;
      return (sobj.isArray() && sobj.get("toString") == global.getArrayPrototype().get("toString"));
    } 
    return false;
  }
}
