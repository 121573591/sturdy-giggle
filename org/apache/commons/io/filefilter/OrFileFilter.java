package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrFileFilter extends AbstractFileFilter implements ConditionalFileFilter, Serializable {
  private static final long serialVersionUID = 5767770777065432721L;
  
  private final List<IOFileFilter> fileFilters;
  
  public OrFileFilter() {
    this(0);
  }
  
  private OrFileFilter(ArrayList<IOFileFilter> initialList) {
    this.fileFilters = Objects.<List<IOFileFilter>>requireNonNull(initialList, "initialList");
  }
  
  private OrFileFilter(int initialCapacity) {
    this(new ArrayList<>(initialCapacity));
  }
  
  public OrFileFilter(IOFileFilter... fileFilters) {
    this(((IOFileFilter[])Objects.requireNonNull((T)fileFilters, "fileFilters")).length);
    addFileFilter(fileFilters);
  }
  
  public OrFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
    this(2);
    addFileFilter(filter1);
    addFileFilter(filter2);
  }
  
  public OrFileFilter(List<IOFileFilter> fileFilters) {
    this(new ArrayList<>(Objects.<Collection<? extends IOFileFilter>>requireNonNull(fileFilters, "fileFilters")));
  }
  
  public boolean accept(File file) {
    for (IOFileFilter fileFilter : this.fileFilters) {
      if (fileFilter.accept(file))
        return true; 
    } 
    return false;
  }
  
  public boolean accept(File file, String name) {
    for (IOFileFilter fileFilter : this.fileFilters) {
      if (fileFilter.accept(file, name))
        return true; 
    } 
    return false;
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    for (IOFileFilter fileFilter : this.fileFilters) {
      if (fileFilter.accept(file, attributes) == FileVisitResult.CONTINUE)
        return FileVisitResult.CONTINUE; 
    } 
    return FileVisitResult.TERMINATE;
  }
  
  public void addFileFilter(IOFileFilter fileFilter) {
    this.fileFilters.add(Objects.requireNonNull(fileFilter, "fileFilter"));
  }
  
  public void addFileFilter(IOFileFilter... fileFilters) {
    for (IOFileFilter fileFilter : (IOFileFilter[])Objects.<IOFileFilter[]>requireNonNull(fileFilters, "fileFilters"))
      addFileFilter(fileFilter); 
  }
  
  public List<IOFileFilter> getFileFilters() {
    return Collections.unmodifiableList(this.fileFilters);
  }
  
  public boolean removeFileFilter(IOFileFilter fileFilter) {
    return this.fileFilters.remove(fileFilter);
  }
  
  public void setFileFilters(List<IOFileFilter> fileFilters) {
    this.fileFilters.clear();
    this.fileFilters.addAll(Objects.<Collection<? extends IOFileFilter>>requireNonNull(fileFilters, "fileFilters"));
  }
  
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(super.toString());
    buffer.append("(");
    if (this.fileFilters != null)
      for (int i = 0; i < this.fileFilters.size(); i++) {
        if (i > 0)
          buffer.append(","); 
        buffer.append(this.fileFilters.get(i));
      }  
    buffer.append(")");
    return buffer.toString();
  }
}
