package org.openjdk.nashorn.api.tree;

@FunctionalInterface
public interface DiagnosticListener {
  void report(Diagnostic paramDiagnostic);
}
