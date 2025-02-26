package org.openjdk.nashorn.internal.codegen;

import java.util.HashMap;

public class Namespace {
  private final Namespace parent;
  
  private final HashMap<String, Integer> directory;
  
  public Namespace() {
    this(null);
  }
  
  public Namespace(Namespace parent) {
    this.parent = parent;
    this.directory = new HashMap<>();
  }
  
  public Namespace getParent() {
    return this.parent;
  }
  
  public String uniqueName(String base) {
    String truncatedBase = (base.length() > 32768) ? base.substring(0, 32768) : base;
    for (Namespace namespace = this; namespace != null; namespace = namespace.getParent()) {
      HashMap<String, Integer> namespaceDirectory = namespace.directory;
      Integer counter = namespaceDirectory.get(truncatedBase);
      if (counter != null) {
        int count = counter.intValue() + 1;
        namespaceDirectory.put(truncatedBase, Integer.valueOf(count));
        return truncatedBase + truncatedBase + CompilerConstants.ID_FUNCTION_SEPARATOR.symbolName();
      } 
    } 
    this.directory.put(truncatedBase, Integer.valueOf(0));
    return truncatedBase;
  }
  
  public String toString() {
    return this.directory.toString();
  }
}
