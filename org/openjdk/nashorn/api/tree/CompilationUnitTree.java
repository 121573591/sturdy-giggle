package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface CompilationUnitTree extends Tree {
  List<? extends Tree> getSourceElements();
  
  String getSourceName();
  
  boolean isStrict();
  
  LineMap getLineMap();
  
  ModuleTree getModule();
}
