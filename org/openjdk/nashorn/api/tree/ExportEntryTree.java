package org.openjdk.nashorn.api.tree;

public interface ExportEntryTree extends Tree {
  IdentifierTree getExportName();
  
  IdentifierTree getModuleRequest();
  
  IdentifierTree getImportName();
  
  IdentifierTree getLocalName();
}
