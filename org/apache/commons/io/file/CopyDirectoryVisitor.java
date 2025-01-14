package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Objects;

public class CopyDirectoryVisitor extends CountingPathVisitor {
  private final CopyOption[] copyOptions;
  
  private final Path sourceDirectory;
  
  private final Path targetDirectory;
  
  public CopyDirectoryVisitor(Counters.PathCounters pathCounter, Path sourceDirectory, Path targetDirectory, CopyOption... copyOptions) {
    super(pathCounter);
    this.sourceDirectory = sourceDirectory;
    this.targetDirectory = targetDirectory;
    this.copyOptions = (copyOptions == null) ? PathUtils.EMPTY_COPY_OPTIONS : (CopyOption[])copyOptions.clone();
  }
  
  public CopyDirectoryVisitor(Counters.PathCounters pathCounter, PathFilter fileFilter, PathFilter dirFilter, Path sourceDirectory, Path targetDirectory, CopyOption... copyOptions) {
    super(pathCounter, fileFilter, dirFilter);
    this.sourceDirectory = sourceDirectory;
    this.targetDirectory = targetDirectory;
    this.copyOptions = (copyOptions == null) ? PathUtils.EMPTY_COPY_OPTIONS : (CopyOption[])copyOptions.clone();
  }
  
  protected void copy(Path sourceFile, Path targetFile) throws IOException {
    Files.copy(sourceFile, targetFile, this.copyOptions);
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!super.equals(obj))
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    CopyDirectoryVisitor other = (CopyDirectoryVisitor)obj;
    return (Arrays.equals((Object[])this.copyOptions, (Object[])other.copyOptions) && Objects.equals(this.sourceDirectory, other.sourceDirectory) && 
      Objects.equals(this.targetDirectory, other.targetDirectory));
  }
  
  public CopyOption[] getCopyOptions() {
    return (CopyOption[])this.copyOptions.clone();
  }
  
  public Path getSourceDirectory() {
    return this.sourceDirectory;
  }
  
  public Path getTargetDirectory() {
    return this.targetDirectory;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = super.hashCode();
    result = 31 * result + Arrays.hashCode((Object[])this.copyOptions);
    result = 31 * result + Objects.hash(new Object[] { this.sourceDirectory, this.targetDirectory });
    return result;
  }
  
  public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) throws IOException {
    Path newTargetDir = resolveRelativeAsString(directory);
    if (Files.notExists(newTargetDir, new java.nio.file.LinkOption[0]))
      Files.createDirectory(newTargetDir, (FileAttribute<?>[])new FileAttribute[0]); 
    return super.preVisitDirectory(directory, attributes);
  }
  
  private Path resolveRelativeAsString(Path directory) {
    return this.targetDirectory.resolve(this.sourceDirectory.relativize(directory).toString());
  }
  
  public FileVisitResult visitFile(Path sourceFile, BasicFileAttributes attributes) throws IOException {
    Path targetFile = resolveRelativeAsString(sourceFile);
    copy(sourceFile, targetFile);
    return super.visitFile(targetFile, attributes);
  }
}
