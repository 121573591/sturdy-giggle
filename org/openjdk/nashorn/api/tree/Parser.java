package org.openjdk.nashorn.api.tree;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;

public interface Parser {
  CompilationUnitTree parse(File paramFile, DiagnosticListener paramDiagnosticListener) throws IOException, NashornException;
  
  CompilationUnitTree parse(Path paramPath, DiagnosticListener paramDiagnosticListener) throws IOException, NashornException;
  
  CompilationUnitTree parse(URL paramURL, DiagnosticListener paramDiagnosticListener) throws IOException, NashornException;
  
  CompilationUnitTree parse(String paramString, Reader paramReader, DiagnosticListener paramDiagnosticListener) throws IOException, NashornException;
  
  CompilationUnitTree parse(String paramString1, String paramString2, DiagnosticListener paramDiagnosticListener) throws NashornException;
  
  CompilationUnitTree parse(ScriptObjectMirror paramScriptObjectMirror, DiagnosticListener paramDiagnosticListener) throws NashornException;
  
  static Parser create(String... options) throws IllegalArgumentException {
    options.getClass();
    for (String opt : options) {
      switch (opt) {
        case "--const-as-var":
        case "-dump-on-error":
        case "-doe":
        case "--empty-statements":
        case "--no-syntax-extensions":
        case "-nse":
        case "-scripting":
        case "-strict":
        case "--language=es6":
        case "--es6-module":
          break;
        default:
          throw new IllegalArgumentException(opt);
      } 
    } 
    return new ParserImpl(options);
  }
}
