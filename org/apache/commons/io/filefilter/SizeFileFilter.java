package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class SizeFileFilter extends AbstractFileFilter implements Serializable {
  private static final long serialVersionUID = 7388077430788600069L;
  
  private final boolean acceptLarger;
  
  private final long size;
  
  public SizeFileFilter(long size) {
    this(size, true);
  }
  
  public SizeFileFilter(long size, boolean acceptLarger) {
    if (size < 0L)
      throw new IllegalArgumentException("The size must be non-negative"); 
    this.size = size;
    this.acceptLarger = acceptLarger;
  }
  
  public boolean accept(File file) {
    return accept(file.length());
  }
  
  private boolean accept(long length) {
    return (this.acceptLarger != ((length < this.size)));
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    try {
      return toFileVisitResult(accept(Files.size(file)), file);
    } catch (IOException e) {
      return handle(e);
    } 
  }
  
  public String toString() {
    String condition = this.acceptLarger ? ">=" : "<";
    return super.toString() + "(" + condition + this.size + ")";
  }
  
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    return toFileVisitResult(accept(Files.size(file)), file);
  }
}
