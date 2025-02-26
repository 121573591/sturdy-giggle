package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;

@Deprecated
public class WildcardFilter extends AbstractFileFilter implements Serializable {
  private static final long serialVersionUID = -5037645902506953517L;
  
  private final String[] wildcards;
  
  public WildcardFilter(List<String> wildcards) {
    if (wildcards == null)
      throw new IllegalArgumentException("The wildcard list must not be null"); 
    this.wildcards = wildcards.<String>toArray(EMPTY_STRING_ARRAY);
  }
  
  public WildcardFilter(String wildcard) {
    if (wildcard == null)
      throw new IllegalArgumentException("The wildcard must not be null"); 
    this.wildcards = new String[] { wildcard };
  }
  
  public WildcardFilter(String... wildcards) {
    if (wildcards == null)
      throw new IllegalArgumentException("The wildcard array must not be null"); 
    this.wildcards = new String[wildcards.length];
    System.arraycopy(wildcards, 0, this.wildcards, 0, wildcards.length);
  }
  
  public boolean accept(File file) {
    if (file.isDirectory())
      return false; 
    for (String wildcard : this.wildcards) {
      if (FilenameUtils.wildcardMatch(file.getName(), wildcard))
        return true; 
    } 
    return false;
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    if (Files.isDirectory(file, new java.nio.file.LinkOption[0]))
      return FileVisitResult.TERMINATE; 
    for (String wildcard : this.wildcards) {
      if (FilenameUtils.wildcardMatch(Objects.toString(file.getFileName(), null), wildcard))
        return FileVisitResult.CONTINUE; 
    } 
    return FileVisitResult.TERMINATE;
  }
  
  public boolean accept(File dir, String name) {
    if (dir != null && (new File(dir, name)).isDirectory())
      return false; 
    for (String wildcard : this.wildcards) {
      if (FilenameUtils.wildcardMatch(name, wildcard))
        return true; 
    } 
    return false;
  }
}
