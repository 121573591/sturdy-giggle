package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;

public class DeletingPathVisitor extends CountingPathVisitor {
  private final String[] skip;
  
  private final boolean overrideReadOnly;
  
  private final LinkOption[] linkOptions;
  
  public static DeletingPathVisitor withBigIntegerCounters() {
    return new DeletingPathVisitor(Counters.bigIntegerPathCounters(), new String[0]);
  }
  
  public static DeletingPathVisitor withLongCounters() {
    return new DeletingPathVisitor(Counters.longPathCounters(), new String[0]);
  }
  
  public DeletingPathVisitor(Counters.PathCounters pathCounter, DeleteOption[] deleteOption, String... skip) {
    this(pathCounter, PathUtils.NOFOLLOW_LINK_OPTION_ARRAY, deleteOption, skip);
  }
  
  public DeletingPathVisitor(Counters.PathCounters pathCounter, LinkOption[] linkOptions, DeleteOption[] deleteOption, String... skip) {
    super(pathCounter);
    String[] temp = (skip != null) ? (String[])skip.clone() : EMPTY_STRING_ARRAY;
    Arrays.sort((Object[])temp);
    this.skip = temp;
    this.overrideReadOnly = StandardDeleteOption.overrideReadOnly(deleteOption);
    this.linkOptions = (linkOptions == null) ? PathUtils.NOFOLLOW_LINK_OPTION_ARRAY : (LinkOption[])linkOptions.clone();
  }
  
  public DeletingPathVisitor(Counters.PathCounters pathCounter, String... skip) {
    this(pathCounter, PathUtils.EMPTY_DELETE_OPTION_ARRAY, skip);
  }
  
  private boolean accept(Path path) {
    return (Arrays.binarySearch((Object[])this.skip, Objects.toString(path.getFileName(), null)) < 0);
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!super.equals(obj))
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    DeletingPathVisitor other = (DeletingPathVisitor)obj;
    return (this.overrideReadOnly == other.overrideReadOnly && Arrays.equals((Object[])this.skip, (Object[])other.skip));
  }
  
  public int hashCode() {
    int prime = 31;
    int result = super.hashCode();
    result = 31 * result + Arrays.hashCode((Object[])this.skip);
    result = 31 * result + Objects.hash(new Object[] { Boolean.valueOf(this.overrideReadOnly) });
    return result;
  }
  
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    if (PathUtils.isEmptyDirectory(dir))
      Files.deleteIfExists(dir); 
    return super.postVisitDirectory(dir, exc);
  }
  
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    super.preVisitDirectory(dir, attrs);
    return accept(dir) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
  }
  
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    if (accept(file)) {
      if (Files.exists(file, this.linkOptions)) {
        if (this.overrideReadOnly)
          PathUtils.setReadOnly(file, false, this.linkOptions); 
        Files.deleteIfExists(file);
      } 
      if (Files.isSymbolicLink(file))
        try {
          Files.delete(file);
        } catch (NoSuchFileException noSuchFileException) {} 
    } 
    updateFileCounters(file, attrs);
    return FileVisitResult.CONTINUE;
  }
}
