package org.openjdk.nashorn.internal.parser;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.Module;

class ParserContextModuleNode extends ParserContextBaseNode {
  private final String name;
  
  private final List<String> requestedModules = new ArrayList<>();
  
  private final List<Module.ImportEntry> importEntries = new ArrayList<>();
  
  private final List<Module.ExportEntry> localExportEntries = new ArrayList<>();
  
  private final List<Module.ExportEntry> indirectExportEntries = new ArrayList<>();
  
  private final List<Module.ExportEntry> starExportEntries = new ArrayList<>();
  
  ParserContextModuleNode(String name) {
    this.name = name;
  }
  
  public String getModuleName() {
    return this.name;
  }
  
  public void addModuleRequest(IdentNode moduleRequest) {
    this.requestedModules.add(moduleRequest.getName());
  }
  
  public void addImportEntry(Module.ImportEntry importEntry) {
    this.importEntries.add(importEntry);
  }
  
  public void addLocalExportEntry(Module.ExportEntry exportEntry) {
    this.localExportEntries.add(exportEntry);
  }
  
  public void addIndirectExportEntry(Module.ExportEntry exportEntry) {
    this.indirectExportEntries.add(exportEntry);
  }
  
  public void addStarExportEntry(Module.ExportEntry exportEntry) {
    this.starExportEntries.add(exportEntry);
  }
  
  public Module createModule() {
    return new Module(this.requestedModules, this.importEntries, this.localExportEntries, this.indirectExportEntries, this.starExportEntries);
  }
}
