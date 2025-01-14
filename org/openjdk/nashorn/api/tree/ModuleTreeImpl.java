package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.Module;
import org.openjdk.nashorn.internal.ir.Node;

final class ModuleTreeImpl extends TreeImpl implements ModuleTree {
  private final Module mod;
  
  private final List<? extends ImportEntryTree> imports;
  
  private final List<? extends ExportEntryTree> localExports;
  
  private final List<? extends ExportEntryTree> indirectExports;
  
  private final List<? extends ExportEntryTree> starExports;
  
  private ModuleTreeImpl(FunctionNode func, List<? extends ImportEntryTree> imports, List<? extends ExportEntryTree> localExports, List<? extends ExportEntryTree> indirectExports, List<? extends ExportEntryTree> starExports) {
    super((Node)func);
    assert func.getKind() == FunctionNode.Kind.MODULE : "module function node expected";
    this.mod = func.getModule();
    this.imports = imports;
    this.localExports = localExports;
    this.indirectExports = indirectExports;
    this.starExports = starExports;
  }
  
  static ModuleTreeImpl create(FunctionNode func) {
    Module mod = func.getModule();
    return new ModuleTreeImpl(func, 
        (List)ImportEntryTreeImpl.createImportList(mod.getImportEntries()), 
        (List)ExportEntryTreeImpl.createExportList(mod.getLocalExportEntries()), 
        (List)ExportEntryTreeImpl.createExportList(mod.getIndirectExportEntries()), 
        (List)ExportEntryTreeImpl.createExportList(mod.getStarExportEntries()));
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.MODULE;
  }
  
  public List<? extends ImportEntryTree> getImportEntries() {
    return this.imports;
  }
  
  public List<? extends ExportEntryTree> getLocalExportEntries() {
    return this.localExports;
  }
  
  public List<? extends ExportEntryTree> getIndirectExportEntries() {
    return this.indirectExports;
  }
  
  public List<? extends ExportEntryTree> getStarExportEntries() {
    return this.starExports;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitModule(this, data);
  }
  
  static IdentifierTree identOrNull(IdentNode node) {
    return (node != null) ? new IdentifierTreeImpl(node) : null;
  }
}
