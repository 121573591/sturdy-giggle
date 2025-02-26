package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class HiddenFileFilter extends AbstractFileFilter implements Serializable {
  public static final IOFileFilter HIDDEN = new HiddenFileFilter();
  
  private static final long serialVersionUID = 8930842316112759062L;
  
  public static final IOFileFilter VISIBLE = HIDDEN.negate();
  
  public boolean accept(File file) {
    return file.isHidden();
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    try {
      return toFileVisitResult(Files.isHidden(file), file);
    } catch (IOException e) {
      return handle(e);
    } 
  }
}
