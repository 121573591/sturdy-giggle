package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import org.apache.commons.io.IOCase;

public class PrefixFileFilter extends AbstractFileFilter implements Serializable {
  private static final long serialVersionUID = 8533897440809599867L;
  
  private final String[] prefixes;
  
  private final IOCase caseSensitivity;
  
  public PrefixFileFilter(List<String> prefixes) {
    this(prefixes, IOCase.SENSITIVE);
  }
  
  public PrefixFileFilter(List<String> prefixes, IOCase caseSensitivity) {
    if (prefixes == null)
      throw new IllegalArgumentException("The list of prefixes must not be null"); 
    this.prefixes = prefixes.<String>toArray(EMPTY_STRING_ARRAY);
    this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
  }
  
  public PrefixFileFilter(String prefix) {
    this(prefix, IOCase.SENSITIVE);
  }
  
  public PrefixFileFilter(String... prefixes) {
    this(prefixes, IOCase.SENSITIVE);
  }
  
  public PrefixFileFilter(String prefix, IOCase caseSensitivity) {
    if (prefix == null)
      throw new IllegalArgumentException("The prefix must not be null"); 
    this.prefixes = new String[] { prefix };
    this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
  }
  
  public PrefixFileFilter(String[] prefixes, IOCase caseSensitivity) {
    if (prefixes == null)
      throw new IllegalArgumentException("The array of prefixes must not be null"); 
    this.prefixes = new String[prefixes.length];
    System.arraycopy(prefixes, 0, this.prefixes, 0, prefixes.length);
    this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
  }
  
  public boolean accept(File file) {
    return accept((file == null) ? null : file.getName());
  }
  
  public boolean accept(File file, String name) {
    return accept(name);
  }
  
  public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
    Path fileName = file.getFileName();
    return toFileVisitResult(accept((fileName == null) ? null : fileName.toFile()), file);
  }
  
  private boolean accept(String name) {
    for (String prefix : this.prefixes) {
      if (this.caseSensitivity.checkStartsWith(name, prefix))
        return true; 
    } 
    return false;
  }
  
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(super.toString());
    buffer.append("(");
    if (this.prefixes != null)
      for (int i = 0; i < this.prefixes.length; i++) {
        if (i > 0)
          buffer.append(","); 
        buffer.append(this.prefixes[i]);
      }  
    buffer.append(")");
    return buffer.toString();
  }
}
