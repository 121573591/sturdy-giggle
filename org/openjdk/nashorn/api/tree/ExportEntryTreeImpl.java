package org.openjdk.nashorn.api.tree;

import java.util.List;
import java.util.stream.Collectors;
import org.openjdk.nashorn.internal.ir.Module;

final class ExportEntryTreeImpl extends TreeImpl implements ExportEntryTree {
  private final long startPos;
  
  private final long endPos;
  
  private final IdentifierTree exportName;
  
  private final IdentifierTree moduleRequest;
  
  private final IdentifierTree importName;
  
  private final IdentifierTree localName;
  
  private ExportEntryTreeImpl(long startPos, long endPos, IdentifierTree exportName, IdentifierTree moduleRequest, IdentifierTree importName, IdentifierTree localName) {
    super(null);
    this.startPos = startPos;
    this.endPos = endPos;
    this.exportName = exportName;
    this.moduleRequest = moduleRequest;
    this.importName = importName;
    this.localName = localName;
  }
  
  private static ExportEntryTreeImpl createExportEntry(Module.ExportEntry entry) {
    return new ExportEntryTreeImpl(entry.getStartPosition(), entry
        .getEndPosition(), 
        ModuleTreeImpl.identOrNull(entry.getExportName()), 
        ModuleTreeImpl.identOrNull(entry.getModuleRequest()), 
        ModuleTreeImpl.identOrNull(entry.getImportName()), 
        ModuleTreeImpl.identOrNull(entry.getLocalName()));
  }
  
  static List<ExportEntryTreeImpl> createExportList(List<Module.ExportEntry> exportList) {
    return (List<ExportEntryTreeImpl>)exportList.stream()
      .map(ExportEntryTreeImpl::createExportEntry)
      .collect(Collectors.toList());
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.EXPORT_ENTRY;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitExportEntry(this, data);
  }
  
  public long getStartPosition() {
    return this.startPos;
  }
  
  public long getEndPosition() {
    return this.endPos;
  }
  
  public IdentifierTree getExportName() {
    return this.exportName;
  }
  
  public IdentifierTree getModuleRequest() {
    return this.moduleRequest;
  }
  
  public IdentifierTree getImportName() {
    return this.importName;
  }
  
  public IdentifierTree getLocalName() {
    return this.localName;
  }
}
