package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface ModuleTree extends Tree {
  List<? extends ImportEntryTree> getImportEntries();
  
  List<? extends ExportEntryTree> getLocalExportEntries();
  
  List<? extends ExportEntryTree> getIndirectExportEntries();
  
  List<? extends ExportEntryTree> getStarExportEntries();
}
