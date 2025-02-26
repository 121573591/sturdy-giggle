package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FalseFileFilter implements IOFileFilter, Serializable {
  private static final String TO_STRING = Boolean.FALSE.toString();
  
  public static final IOFileFilter FALSE = new FalseFileFilter();
  
  public static final IOFileFilter INSTANCE = FALSE;
  
  private static final long serialVersionUID = 6210271677940926200L;
  
  public boolean accept(File file) {
    return false;
  }
  
  public boolean accept(File dir, String name) {
    return false;
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    return FileVisitResult.TERMINATE;
  }
  
  public IOFileFilter negate() {
    return TrueFileFilter.INSTANCE;
  }
  
  public String toString() {
    return TO_STRING;
  }
  
  public IOFileFilter and(IOFileFilter fileFilter) {
    return INSTANCE;
  }
  
  public IOFileFilter or(IOFileFilter fileFilter) {
    return fileFilter;
  }
}
