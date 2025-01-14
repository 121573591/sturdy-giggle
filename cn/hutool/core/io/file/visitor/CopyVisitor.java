package cn.hutool.core.io.file.visitor;

import cn.hutool.core.io.file.PathUtil;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyVisitor extends SimpleFileVisitor<Path> {
  private final Path source;
  
  private final Path target;
  
  private final CopyOption[] copyOptions;
  
  private boolean isTargetCreated;
  
  public CopyVisitor(Path source, Path target, CopyOption... copyOptions) {
    if (PathUtil.exists(target, false) && false == PathUtil.isDirectory(target))
      throw new IllegalArgumentException("Target must be a directory"); 
    this.source = source;
    this.target = target;
    this.copyOptions = copyOptions;
  }
  
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    initTargetDir();
    Path targetDir = resolveTarget(dir);
    try {
      Files.copy(dir, targetDir, this.copyOptions);
    } catch (FileAlreadyExistsException e) {
      if (false == Files.isDirectory(targetDir, new java.nio.file.LinkOption[0]))
        throw e; 
    } 
    return FileVisitResult.CONTINUE;
  }
  
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    initTargetDir();
    Files.copy(file, resolveTarget(file), this.copyOptions);
    return FileVisitResult.CONTINUE;
  }
  
  private Path resolveTarget(Path file) {
    return this.target.resolve(this.source.relativize(file));
  }
  
  private void initTargetDir() {
    if (false == this.isTargetCreated) {
      PathUtil.mkdir(this.target);
      this.isTargetCreated = true;
    } 
  }
}
