package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CanReadFileFilter extends AbstractFileFilter implements Serializable {
  public static final IOFileFilter CAN_READ = new CanReadFileFilter();
  
  public static final IOFileFilter CANNOT_READ = CAN_READ.negate();
  
  public static final IOFileFilter READ_ONLY = CAN_READ.and(CanWriteFileFilter.CANNOT_WRITE);
  
  private static final long serialVersionUID = 3179904805251622989L;
  
  public boolean accept(File file) {
    return file.canRead();
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    return toFileVisitResult(Files.isReadable(file), file);
  }
}
