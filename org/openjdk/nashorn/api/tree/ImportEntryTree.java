package org.openjdk.nashorn.api.tree;

public interface ImportEntryTree extends Tree {
  IdentifierTree getModuleRequest();
  
  IdentifierTree getImportName();
  
  IdentifierTree getLocalName();
}
