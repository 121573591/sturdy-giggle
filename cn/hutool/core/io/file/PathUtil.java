package cn.hutool.core.io.file;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.visitor.CopyVisitor;
import cn.hutool.core.io.file.visitor.DelVisitor;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.AccessDeniedException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PathUtil {
  public static boolean isDirEmpty(Path dirPath) {
    try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath)) {
      return (false == dirStream.iterator().hasNext());
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static List<File> loopFiles(Path path, FileFilter fileFilter) {
    return loopFiles(path, -1, fileFilter);
  }
  
  public static List<File> loopFiles(Path path, int maxDepth, FileFilter fileFilter) {
    return loopFiles(path, maxDepth, false, fileFilter);
  }
  
  public static List<File> loopFiles(Path path, int maxDepth, boolean isFollowLinks, final FileFilter fileFilter) {
    final List<File> fileList = new ArrayList<>();
    if (!exists(path, isFollowLinks))
      return fileList; 
    if (!isDirectory(path, isFollowLinks)) {
      File file = path.toFile();
      if (null == fileFilter || fileFilter.accept(file))
        fileList.add(file); 
      return fileList;
    } 
    walkFiles(path, maxDepth, isFollowLinks, new SimpleFileVisitor<Path>() {
          public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            File file = path.toFile();
            if (null == fileFilter || fileFilter.accept(file))
              fileList.add(file); 
            return FileVisitResult.CONTINUE;
          }
        });
    return fileList;
  }
  
  public static void walkFiles(Path start, FileVisitor<? super Path> visitor) {
    walkFiles(start, -1, visitor);
  }
  
  public static void walkFiles(Path start, int maxDepth, FileVisitor<? super Path> visitor) {
    walkFiles(start, maxDepth, false, visitor);
  }
  
  public static void walkFiles(Path start, int maxDepth, boolean isFollowLinks, FileVisitor<? super Path> visitor) {
    if (maxDepth < 0)
      maxDepth = Integer.MAX_VALUE; 
    try {
      Files.walkFileTree(start, getFileVisitOption(isFollowLinks), maxDepth, visitor);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static boolean del(Path path) throws IORuntimeException {
    if (Files.notExists(path, new LinkOption[0]))
      return true; 
    try {
      if (isDirectory(path)) {
        Files.walkFileTree(path, (FileVisitor<? super Path>)DelVisitor.INSTANCE);
      } else {
        delFile(path);
      } 
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return true;
  }
  
  public static Path copyFile(Path src, Path dest, StandardCopyOption... options) throws IORuntimeException {
    return copyFile(src, dest, (CopyOption[])options);
  }
  
  public static Path copyFile(Path src, Path target, CopyOption... options) throws IORuntimeException {
    Assert.notNull(src, "Source File is null !", new Object[0]);
    Assert.notNull(target, "Destination File or directory is null !", new Object[0]);
    Path targetPath = isDirectory(target) ? target.resolve(src.getFileName()) : target;
    mkParentDirs(targetPath);
    try {
      return Files.copy(src, targetPath, options);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static Path copy(Path src, Path target, CopyOption... options) throws IORuntimeException {
    Assert.notNull(src, "Src path must be not null !", new Object[0]);
    Assert.notNull(target, "Target path must be not null !", new Object[0]);
    if (isDirectory(src))
      return copyContent(src, target.resolve(src.getFileName()), options); 
    return copyFile(src, target, options);
  }
  
  public static Path copyContent(Path src, Path target, CopyOption... options) throws IORuntimeException {
    Assert.notNull(src, "Src path must be not null !", new Object[0]);
    Assert.notNull(target, "Target path must be not null !", new Object[0]);
    try {
      Files.walkFileTree(src, (FileVisitor<? super Path>)new CopyVisitor(src, target, options));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return target;
  }
  
  public static boolean isDirectory(Path path) {
    return isDirectory(path, false);
  }
  
  public static boolean isDirectory(Path path, boolean isFollowLinks) {
    if (null == path)
      return false; 
    return Files.isDirectory(path, getLinkOptions(isFollowLinks));
  }
  
  public static Path getPathEle(Path path, int index) {
    return subPath(path, index, (index == -1) ? path.getNameCount() : (index + 1));
  }
  
  public static Path getLastPathEle(Path path) {
    return getPathEle(path, path.getNameCount() - 1);
  }
  
  public static Path subPath(Path path, int fromIndex, int toIndex) {
    if (null == path)
      return null; 
    int len = path.getNameCount();
    if (fromIndex < 0) {
      fromIndex = len + fromIndex;
      if (fromIndex < 0)
        fromIndex = 0; 
    } else if (fromIndex > len) {
      fromIndex = len;
    } 
    if (toIndex < 0) {
      toIndex = len + toIndex;
      if (toIndex < 0)
        toIndex = len; 
    } else if (toIndex > len) {
      toIndex = len;
    } 
    if (toIndex < fromIndex) {
      int tmp = fromIndex;
      fromIndex = toIndex;
      toIndex = tmp;
    } 
    if (fromIndex == toIndex)
      return null; 
    return path.subpath(fromIndex, toIndex);
  }
  
  public static BasicFileAttributes getAttributes(Path path, boolean isFollowLinks) throws IORuntimeException {
    if (null == path)
      return null; 
    try {
      return Files.readAttributes(path, BasicFileAttributes.class, getLinkOptions(isFollowLinks));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static BufferedInputStream getInputStream(Path path) throws IORuntimeException {
    InputStream in;
    try {
      in = Files.newInputStream(path, new java.nio.file.OpenOption[0]);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return IoUtil.toBuffered(in);
  }
  
  public static BufferedReader getUtf8Reader(Path path) throws IORuntimeException {
    return getReader(path, CharsetUtil.CHARSET_UTF_8);
  }
  
  public static BufferedReader getReader(Path path, Charset charset) throws IORuntimeException {
    return IoUtil.getReader(getInputStream(path), charset);
  }
  
  public static byte[] readBytes(Path path) {
    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static BufferedOutputStream getOutputStream(Path path) throws IORuntimeException {
    OutputStream in;
    try {
      in = Files.newOutputStream(path, new java.nio.file.OpenOption[0]);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return IoUtil.toBuffered(in);
  }
  
  public static Path rename(Path path, String newName, boolean isOverride) {
    return move(path, path.resolveSibling(newName), isOverride);
  }
  
  public static Path move(Path src, Path target, boolean isOverride) {
    return PathMover.of(src, target, isOverride).move();
  }
  
  public static Path moveContent(Path src, Path target, boolean isOverride) {
    return PathMover.of(src, target, isOverride).moveContent();
  }
  
  public static boolean equals(Path file1, Path file2) throws IORuntimeException {
    try {
      return Files.isSameFile(file1, file2);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static boolean isFile(Path path, boolean isFollowLinks) {
    if (null == path)
      return false; 
    return Files.isRegularFile(path, getLinkOptions(isFollowLinks));
  }
  
  public static boolean isSymlink(Path path) {
    return Files.isSymbolicLink(path);
  }
  
  public static boolean exists(Path path, boolean isFollowLinks) {
    return Files.exists(path, getLinkOptions(isFollowLinks));
  }
  
  public static boolean isExistsAndNotDirectory(Path path, boolean isFollowLinks) {
    return (exists(path, isFollowLinks) && false == isDirectory(path, isFollowLinks));
  }
  
  public static boolean isSub(Path parent, Path sub) {
    return toAbsNormal(sub).startsWith(toAbsNormal(parent));
  }
  
  public static Path toAbsNormal(Path path) {
    Assert.notNull(path);
    return path.toAbsolutePath().normalize();
  }
  
  public static String getMimeType(Path file) {
    try {
      return Files.probeContentType(file);
    } catch (IOException ignore) {
      return null;
    } 
  }
  
  public static Path mkdir(Path dir) {
    if (null != dir && false == exists(dir, false))
      try {
        Files.createDirectories(dir, (FileAttribute<?>[])new FileAttribute[0]);
      } catch (IOException e) {
        throw new IORuntimeException(e);
      }  
    return dir;
  }
  
  public static Path mkParentDirs(Path path) {
    return mkdir(path.getParent());
  }
  
  public static String getName(Path path) {
    if (null == path)
      return null; 
    return path.getFileName().toString();
  }
  
  public static Path createTempFile(String prefix, String suffix, Path dir) throws IORuntimeException {
    int exceptionsCount = 0;
    while (true) {
      try {
        if (null == dir)
          return Files.createTempFile(prefix, suffix, (FileAttribute<?>[])new FileAttribute[0]); 
        return Files.createTempFile(mkdir(dir), prefix, suffix, (FileAttribute<?>[])new FileAttribute[0]);
      } catch (IOException ioex) {
        if (++exceptionsCount >= 50)
          throw new IORuntimeException(ioex); 
      } 
    } 
  }
  
  protected static void delFile(Path path) throws IOException {
    try {
      Files.delete(path);
    } catch (AccessDeniedException e) {
      if (false == path.toFile().delete())
        throw e; 
    } 
  }
  
  public static LinkOption[] getLinkOptions(boolean isFollowLinks) {
    (new LinkOption[1])[0] = LinkOption.NOFOLLOW_LINKS;
    return isFollowLinks ? new LinkOption[0] : new LinkOption[1];
  }
  
  public static Set<FileVisitOption> getFileVisitOption(boolean isFollowLinks) {
    return isFollowLinks ? EnumSet.<FileVisitOption>of(FileVisitOption.FOLLOW_LINKS) : 
      EnumSet.<FileVisitOption>noneOf(FileVisitOption.class);
  }
}
