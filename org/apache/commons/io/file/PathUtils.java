package org.apache.commons.io.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOUtils;

public final class PathUtils {
  private static class RelativeSortedPaths {
    final boolean equals;
    
    final List<Path> relativeFileList1;
    
    final List<Path> relativeFileList2;
    
    private RelativeSortedPaths(Path dir1, Path dir2, int maxDepth, LinkOption[] linkOptions, FileVisitOption[] fileVisitOptions) throws IOException {
      List<Path> tmpRelativeFileList1 = null;
      List<Path> tmpRelativeFileList2 = null;
      if (dir1 == null && dir2 == null) {
        this.equals = true;
      } else if ((((dir1 == null) ? 1 : 0) ^ ((dir2 == null) ? 1 : 0)) != 0) {
        this.equals = false;
      } else {
        boolean parentDirNotExists1 = Files.notExists(dir1, linkOptions);
        boolean parentDirNotExists2 = Files.notExists(dir2, linkOptions);
        if (parentDirNotExists1 || parentDirNotExists2) {
          this.equals = (parentDirNotExists1 && parentDirNotExists2);
        } else {
          AccumulatorPathVisitor visitor1 = PathUtils.accumulate(dir1, maxDepth, fileVisitOptions);
          AccumulatorPathVisitor visitor2 = PathUtils.accumulate(dir2, maxDepth, fileVisitOptions);
          if (visitor1.getDirList().size() != visitor2.getDirList().size() || visitor1
            .getFileList().size() != visitor2.getFileList().size()) {
            this.equals = false;
          } else {
            List<Path> tmpRelativeDirList1 = visitor1.relativizeDirectories(dir1, true, null);
            List<Path> tmpRelativeDirList2 = visitor2.relativizeDirectories(dir2, true, null);
            if (!tmpRelativeDirList1.equals(tmpRelativeDirList2)) {
              this.equals = false;
            } else {
              tmpRelativeFileList1 = visitor1.relativizeFiles(dir1, true, null);
              tmpRelativeFileList2 = visitor2.relativizeFiles(dir2, true, null);
              this.equals = tmpRelativeFileList1.equals(tmpRelativeFileList2);
            } 
          } 
        } 
      } 
      this.relativeFileList1 = tmpRelativeFileList1;
      this.relativeFileList2 = tmpRelativeFileList2;
    }
  }
  
  public static final CopyOption[] EMPTY_COPY_OPTIONS = new CopyOption[0];
  
  public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
  
  public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
  
  public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];
  
  public static final LinkOption[] NOFOLLOW_LINK_OPTION_ARRAY = new LinkOption[] { LinkOption.NOFOLLOW_LINKS };
  
  public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];
  
  public static final Path[] EMPTY_PATH_ARRAY = new Path[0];
  
  private static AccumulatorPathVisitor accumulate(Path directory, int maxDepth, FileVisitOption[] fileVisitOptions) throws IOException {
    return visitFileTree(AccumulatorPathVisitor.withLongCounters(), directory, 
        toFileVisitOptionSet(fileVisitOptions), maxDepth);
  }
  
  public static Counters.PathCounters cleanDirectory(Path directory) throws IOException {
    return cleanDirectory(directory, EMPTY_DELETE_OPTION_ARRAY);
  }
  
  public static Counters.PathCounters cleanDirectory(Path directory, DeleteOption... deleteOptions) throws IOException {
    return ((CleaningPathVisitor)visitFileTree(new CleaningPathVisitor(Counters.longPathCounters(), deleteOptions, new String[0]), directory))
      .getPathCounters();
  }
  
  public static Counters.PathCounters copyDirectory(Path sourceDirectory, Path targetDirectory, CopyOption... copyOptions) throws IOException {
    Path absoluteSource = sourceDirectory.toAbsolutePath();
    return ((CopyDirectoryVisitor)visitFileTree(new CopyDirectoryVisitor(
          Counters.longPathCounters(), absoluteSource, targetDirectory, copyOptions), absoluteSource))
      .getPathCounters();
  }
  
  public static Path copyFile(URL sourceFile, Path targetFile, CopyOption... copyOptions) throws IOException {
    try (InputStream inputStream = sourceFile.openStream()) {
      Files.copy(inputStream, targetFile, copyOptions);
      return targetFile;
    } 
  }
  
  public static Path copyFileToDirectory(Path sourceFile, Path targetDirectory, CopyOption... copyOptions) throws IOException {
    return Files.copy(sourceFile, targetDirectory.resolve(sourceFile.getFileName()), copyOptions);
  }
  
  public static Path copyFileToDirectory(URL sourceFile, Path targetDirectory, CopyOption... copyOptions) throws IOException {
    try (InputStream inputStream = sourceFile.openStream()) {
      Files.copy(inputStream, targetDirectory.resolve(sourceFile.getFile()), copyOptions);
      return targetDirectory;
    } 
  }
  
  public static Counters.PathCounters countDirectory(Path directory) throws IOException {
    return ((CountingPathVisitor)visitFileTree(new CountingPathVisitor(Counters.longPathCounters()), directory)).getPathCounters();
  }
  
  public static Path createParentDirectories(Path path, FileAttribute<?>... attrs) throws IOException {
    Path parent = path.getParent();
    if (parent == null)
      return null; 
    return Files.createDirectories(parent, attrs);
  }
  
  public static Path current() {
    return Paths.get("", new String[0]);
  }
  
  public static Counters.PathCounters delete(Path path) throws IOException {
    return delete(path, EMPTY_DELETE_OPTION_ARRAY);
  }
  
  public static Counters.PathCounters delete(Path path, DeleteOption... deleteOptions) throws IOException {
    return Files.isDirectory(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS }) ? deleteDirectory(path, deleteOptions) : 
      deleteFile(path, deleteOptions);
  }
  
  public static Counters.PathCounters delete(Path path, LinkOption[] linkOptions, DeleteOption... deleteOptions) throws IOException {
    return Files.isDirectory(path, linkOptions) ? deleteDirectory(path, linkOptions, deleteOptions) : 
      deleteFile(path, linkOptions, deleteOptions);
  }
  
  public static Counters.PathCounters deleteDirectory(Path directory) throws IOException {
    return deleteDirectory(directory, EMPTY_DELETE_OPTION_ARRAY);
  }
  
  public static Counters.PathCounters deleteDirectory(Path directory, DeleteOption... deleteOptions) throws IOException {
    return ((DeletingPathVisitor)visitFileTree(new DeletingPathVisitor(
          Counters.longPathCounters(), NOFOLLOW_LINK_OPTION_ARRAY, deleteOptions, new String[0]), directory))
      .getPathCounters();
  }
  
  public static Counters.PathCounters deleteDirectory(Path directory, LinkOption[] linkOptions, DeleteOption... deleteOptions) throws IOException {
    return ((DeletingPathVisitor)visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), linkOptions, deleteOptions, new String[0]), directory))
      .getPathCounters();
  }
  
  public static Counters.PathCounters deleteFile(Path file) throws IOException {
    return deleteFile(file, EMPTY_DELETE_OPTION_ARRAY);
  }
  
  public static Counters.PathCounters deleteFile(Path file, DeleteOption... deleteOptions) throws IOException {
    return deleteFile(file, NOFOLLOW_LINK_OPTION_ARRAY, deleteOptions);
  }
  
  public static Counters.PathCounters deleteFile(Path file, LinkOption[] linkOptions, DeleteOption... deleteOptions) throws NoSuchFileException, IOException {
    if (Files.isDirectory(file, linkOptions))
      throw new NoSuchFileException(file.toString()); 
    Counters.PathCounters pathCounts = Counters.longPathCounters();
    boolean exists = Files.exists(file, linkOptions);
    long size = (exists && !Files.isSymbolicLink(file)) ? Files.size(file) : 0L;
    if (overrideReadOnly(deleteOptions) && exists)
      setReadOnly(file, false, linkOptions); 
    if (Files.deleteIfExists(file)) {
      pathCounts.getFileCounter().increment();
      pathCounts.getByteCounter().add(size);
    } 
    return pathCounts;
  }
  
  public static boolean directoryAndFileContentEquals(Path path1, Path path2) throws IOException {
    return directoryAndFileContentEquals(path1, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
  }
  
  public static boolean directoryAndFileContentEquals(Path path1, Path path2, LinkOption[] linkOptions, OpenOption[] openOptions, FileVisitOption[] fileVisitOption) throws IOException {
    if (path1 == null && path2 == null)
      return true; 
    if (path1 == null || path2 == null)
      return false; 
    if (Files.notExists(path1, new LinkOption[0]) && Files.notExists(path2, new LinkOption[0]))
      return true; 
    RelativeSortedPaths relativeSortedPaths = new RelativeSortedPaths(path1, path2, 2147483647, linkOptions, fileVisitOption);
    if (!relativeSortedPaths.equals)
      return false; 
    List<Path> fileList1 = relativeSortedPaths.relativeFileList1;
    List<Path> fileList2 = relativeSortedPaths.relativeFileList2;
    for (Path path : fileList1) {
      int binarySearch = Collections.binarySearch((List)fileList2, path);
      if (binarySearch <= -1)
        throw new IllegalStateException("Unexpected mismatch."); 
      if (!fileContentEquals(path1.resolve(path), path2.resolve(path), linkOptions, openOptions))
        return false; 
    } 
    return true;
  }
  
  public static boolean directoryContentEquals(Path path1, Path path2) throws IOException {
    return directoryContentEquals(path1, path2, 2147483647, EMPTY_LINK_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
  }
  
  public static boolean directoryContentEquals(Path path1, Path path2, int maxDepth, LinkOption[] linkOptions, FileVisitOption[] fileVisitOptions) throws IOException {
    return (new RelativeSortedPaths(path1, path2, maxDepth, linkOptions, fileVisitOptions)).equals;
  }
  
  public static boolean fileContentEquals(Path path1, Path path2) throws IOException {
    return fileContentEquals(path1, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY);
  }
  
  public static boolean fileContentEquals(Path path1, Path path2, LinkOption[] linkOptions, OpenOption[] openOptions) throws IOException {
    if (path1 == null && path2 == null)
      return true; 
    if (path1 == null || path2 == null)
      return false; 
    Path nPath1 = path1.normalize();
    Path nPath2 = path2.normalize();
    boolean path1Exists = Files.exists(nPath1, linkOptions);
    if (path1Exists != Files.exists(nPath2, linkOptions))
      return false; 
    if (!path1Exists)
      return true; 
    if (Files.isDirectory(nPath1, linkOptions))
      throw new IOException("Can't compare directories, only files: " + nPath1); 
    if (Files.isDirectory(nPath2, linkOptions))
      throw new IOException("Can't compare directories, only files: " + nPath2); 
    if (Files.size(nPath1) != Files.size(nPath2))
      return false; 
    if (path1.equals(path2))
      return true; 
    try(InputStream inputStream1 = Files.newInputStream(nPath1, openOptions); 
        InputStream inputStream2 = Files.newInputStream(nPath2, openOptions)) {
      return IOUtils.contentEquals(inputStream1, inputStream2);
    } 
  }
  
  public static Path[] filter(PathFilter filter, Path... paths) {
    Objects.requireNonNull(filter, "filter");
    if (paths == null)
      return EMPTY_PATH_ARRAY; 
    return (Path[])((List)filterPaths(filter, Stream.of(paths), (Collector)Collectors.toList())).toArray((Object[])EMPTY_PATH_ARRAY);
  }
  
  private static <R, A> R filterPaths(PathFilter filter, Stream<Path> stream, Collector<? super Path, A, R> collector) {
    Objects.requireNonNull(filter, "filter");
    Objects.requireNonNull(collector, "collector");
    if (stream == null)
      return Stream.<Path>empty().collect(collector); 
    return stream.filter(p -> {
          try {
            return (p != null && filter.accept(p, readBasicFileAttributes(p)) == FileVisitResult.CONTINUE);
          } catch (IOException e) {
            return false;
          } 
        }).collect(collector);
  }
  
  public static List<AclEntry> getAclEntryList(Path sourcePath) throws IOException {
    AclFileAttributeView fileAttributeView = Files.<AclFileAttributeView>getFileAttributeView(sourcePath, AclFileAttributeView.class, new LinkOption[0]);
    return (fileAttributeView == null) ? null : fileAttributeView.getAcl();
  }
  
  public static boolean isDirectory(Path path, LinkOption... options) {
    return (path != null && Files.isDirectory(path, options));
  }
  
  public static boolean isEmpty(Path path) throws IOException {
    return Files.isDirectory(path, new LinkOption[0]) ? isEmptyDirectory(path) : isEmptyFile(path);
  }
  
  public static boolean isEmptyDirectory(Path directory) throws IOException {
    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
      return !directoryStream.iterator().hasNext();
    } 
  }
  
  public static boolean isEmptyFile(Path file) throws IOException {
    return (Files.size(file) <= 0L);
  }
  
  public static boolean isNewer(Path file, long timeMillis, LinkOption... options) throws IOException {
    Objects.requireNonNull(file, "file");
    if (Files.notExists(file, new LinkOption[0]))
      return false; 
    return (Files.getLastModifiedTime(file, options).toMillis() > timeMillis);
  }
  
  public static boolean isRegularFile(Path path, LinkOption... options) {
    return (path != null && Files.isRegularFile(path, options));
  }
  
  public static DirectoryStream<Path> newDirectoryStream(Path dir, PathFilter pathFilter) throws IOException {
    return Files.newDirectoryStream(dir, new DirectoryStreamFilter(pathFilter));
  }
  
  private static boolean overrideReadOnly(DeleteOption... deleteOptions) {
    if (deleteOptions == null)
      return false; 
    return Stream.<DeleteOption>of(deleteOptions).anyMatch(e -> (e == StandardDeleteOption.OVERRIDE_READ_ONLY));
  }
  
  public static BasicFileAttributes readBasicFileAttributes(Path path) throws IOException {
    return Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[0]);
  }
  
  public static BasicFileAttributes readBasicFileAttributesUnchecked(Path path) {
    try {
      return readBasicFileAttributes(path);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    } 
  }
  
  static List<Path> relativize(Collection<Path> collection, Path parent, boolean sort, Comparator<? super Path> comparator) {
    Stream<Path> stream = collection.stream().map(parent::relativize);
    if (sort)
      stream = (comparator == null) ? stream.sorted() : stream.sorted(comparator); 
    return stream.collect((Collector)Collectors.toList());
  }
  
  public static Path setReadOnly(Path path, boolean readOnly, LinkOption... linkOptions) throws IOException {
    List<Exception> causeList = new ArrayList<>(2);
    DosFileAttributeView fileAttributeView = Files.<DosFileAttributeView>getFileAttributeView(path, DosFileAttributeView.class, linkOptions);
    if (fileAttributeView != null)
      try {
        fileAttributeView.setReadOnly(readOnly);
        return path;
      } catch (IOException e) {
        causeList.add(e);
      }  
    PosixFileAttributeView posixFileAttributeView = Files.<PosixFileAttributeView>getFileAttributeView(path, PosixFileAttributeView.class, linkOptions);
    if (posixFileAttributeView != null) {
      PosixFileAttributes readAttributes = posixFileAttributeView.readAttributes();
      Set<PosixFilePermission> permissions = readAttributes.permissions();
      permissions.remove(PosixFilePermission.OWNER_WRITE);
      permissions.remove(PosixFilePermission.GROUP_WRITE);
      permissions.remove(PosixFilePermission.OTHERS_WRITE);
      try {
        return Files.setPosixFilePermissions(path, permissions);
      } catch (IOException e) {
        causeList.add(e);
      } 
    } 
    if (!causeList.isEmpty())
      throw new IOExceptionList(path.toString(), causeList); 
    throw new IOException(
        String.format("No DosFileAttributeView or PosixFileAttributeView for '%s' (linkOptions=%s)", new Object[] { path, Arrays.toString(linkOptions) }));
  }
  
  static Set<FileVisitOption> toFileVisitOptionSet(FileVisitOption... fileVisitOptions) {
    return (fileVisitOptions == null) ? EnumSet.<FileVisitOption>noneOf(FileVisitOption.class) : 
      (Set<FileVisitOption>)Stream.<FileVisitOption>of(fileVisitOptions).collect(Collectors.toSet());
  }
  
  public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, Path directory) throws IOException {
    Files.walkFileTree(directory, (FileVisitor<? super Path>)visitor);
    return visitor;
  }
  
  public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, Path start, Set<FileVisitOption> options, int maxDepth) throws IOException {
    Files.walkFileTree(start, options, maxDepth, (FileVisitor<? super Path>)visitor);
    return visitor;
  }
  
  public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, String first, String... more) throws IOException {
    return visitFileTree(visitor, Paths.get(first, more));
  }
  
  public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, URI uri) throws IOException {
    return visitFileTree(visitor, Paths.get(uri));
  }
  
  public static Stream<Path> walk(Path start, PathFilter pathFilter, int maxDepth, boolean readAttributes, FileVisitOption... options) throws IOException {
    return Files.walk(start, maxDepth, options).filter(path -> (pathFilter.accept(path, readAttributes ? readBasicFileAttributesUnchecked(path) : null) == FileVisitResult.CONTINUE));
  }
}
