package org.apache.commons.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.io.file.AccumulatorPathVisitor;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.DeleteOption;
import org.apache.commons.io.file.PathFilter;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.StandardDeleteOption;
import org.apache.commons.io.filefilter.FileEqualsFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class FileUtils {
  public static final long ONE_KB = 1024L;
  
  public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
  
  public static final long ONE_MB = 1048576L;
  
  public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
  
  public static final long ONE_GB = 1073741824L;
  
  public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
  
  public static final long ONE_TB = 1099511627776L;
  
  public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
  
  public static final long ONE_PB = 1125899906842624L;
  
  public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
  
  public static final long ONE_EB = 1152921504606846976L;
  
  public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
  
  public static final BigInteger ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
  
  public static final BigInteger ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
  
  public static final File[] EMPTY_FILE_ARRAY = new File[0];
  
  private static CopyOption[] addCopyAttributes(CopyOption... copyOptions) {
    CopyOption[] actual = Arrays.<CopyOption>copyOf(copyOptions, copyOptions.length + 1);
    Arrays.sort((Object[])actual, 0, copyOptions.length);
    if (Arrays.binarySearch((Object[])copyOptions, 0, copyOptions.length, StandardCopyOption.COPY_ATTRIBUTES) >= 0)
      return copyOptions; 
    actual[actual.length - 1] = StandardCopyOption.COPY_ATTRIBUTES;
    return actual;
  }
  
  public static String byteCountToDisplaySize(BigInteger size) {
    String displaySize;
    Objects.requireNonNull(size, "size");
    if (size.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0) {
      displaySize = size.divide(ONE_EB_BI) + " EB";
    } else if (size.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0) {
      displaySize = size.divide(ONE_PB_BI) + " PB";
    } else if (size.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
      displaySize = size.divide(ONE_TB_BI) + " TB";
    } else if (size.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
      displaySize = size.divide(ONE_GB_BI) + " GB";
    } else if (size.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
      displaySize = size.divide(ONE_MB_BI) + " MB";
    } else if (size.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
      displaySize = size.divide(ONE_KB_BI) + " KB";
    } else {
      displaySize = size + " bytes";
    } 
    return displaySize;
  }
  
  public static String byteCountToDisplaySize(long size) {
    return byteCountToDisplaySize(BigInteger.valueOf(size));
  }
  
  public static Checksum checksum(File file, Checksum checksum) throws IOException {
    requireExistsChecked(file, "file");
    requireFile(file, "file");
    Objects.requireNonNull(checksum, "checksum");
    try (InputStream inputStream = new CheckedInputStream(Files.newInputStream(file.toPath(), new java.nio.file.OpenOption[0]), checksum)) {
      IOUtils.consume(inputStream);
    } 
    return checksum;
  }
  
  public static long checksumCRC32(File file) throws IOException {
    return checksum(file, new CRC32()).getValue();
  }
  
  public static void cleanDirectory(File directory) throws IOException {
    File[] files = listFiles(directory, null);
    List<Exception> causeList = new ArrayList<>();
    for (File file : files) {
      try {
        forceDelete(file);
      } catch (IOException ioe) {
        causeList.add(ioe);
      } 
    } 
    if (!causeList.isEmpty())
      throw new IOExceptionList(directory.toString(), causeList); 
  }
  
  private static void cleanDirectoryOnExit(File directory) throws IOException {
    File[] files = listFiles(directory, null);
    List<Exception> causeList = new ArrayList<>();
    for (File file : files) {
      try {
        forceDeleteOnExit(file);
      } catch (IOException ioe) {
        causeList.add(ioe);
      } 
    } 
    if (!causeList.isEmpty())
      throw new IOExceptionList(causeList); 
  }
  
  public static boolean contentEquals(File file1, File file2) throws IOException {
    if (file1 == null && file2 == null)
      return true; 
    if (file1 == null || file2 == null)
      return false; 
    boolean file1Exists = file1.exists();
    if (file1Exists != file2.exists())
      return false; 
    if (!file1Exists)
      return true; 
    requireFile(file1, "file1");
    requireFile(file2, "file2");
    if (file1.length() != file2.length())
      return false; 
    if (file1.getCanonicalFile().equals(file2.getCanonicalFile()))
      return true; 
    try(InputStream input1 = Files.newInputStream(file1.toPath(), new java.nio.file.OpenOption[0]); InputStream input2 = Files.newInputStream(file2.toPath(), new java.nio.file.OpenOption[0])) {
      return IOUtils.contentEquals(input1, input2);
    } 
  }
  
  public static boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName) throws IOException {
    if (file1 == null && file2 == null)
      return true; 
    if (file1 == null || file2 == null)
      return false; 
    boolean file1Exists = file1.exists();
    if (file1Exists != file2.exists())
      return false; 
    if (!file1Exists)
      return true; 
    requireFile(file1, "file1");
    requireFile(file2, "file2");
    if (file1.getCanonicalFile().equals(file2.getCanonicalFile()))
      return true; 
    Charset charset = Charsets.toCharset(charsetName);
    try(Reader input1 = new InputStreamReader(Files.newInputStream(file1.toPath(), new java.nio.file.OpenOption[0]), charset); 
        Reader input2 = new InputStreamReader(Files.newInputStream(file2.toPath(), new java.nio.file.OpenOption[0]), charset)) {
      return IOUtils.contentEqualsIgnoreEOL(input1, input2);
    } 
  }
  
  public static File[] convertFileCollectionToFileArray(Collection<File> files) {
    return files.<File>toArray(EMPTY_FILE_ARRAY);
  }
  
  public static void copyDirectory(File srcDir, File destDir) throws IOException {
    copyDirectory(srcDir, destDir, true);
  }
  
  public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
    copyDirectory(srcDir, destDir, null, preserveFileDate);
  }
  
  public static void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
    copyDirectory(srcDir, destDir, filter, true);
  }
  
  public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
    copyDirectory(srcDir, destDir, filter, preserveFileDate, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
  }
  
  public static void copyDirectory(File srcDir, File destDir, FileFilter fileFilter, boolean preserveFileDate, CopyOption... copyOptions) throws IOException {
    requireFileCopy(srcDir, destDir);
    requireDirectory(srcDir, "srcDir");
    requireCanonicalPathsNotEquals(srcDir, destDir);
    List<String> exclusionList = null;
    String srcDirCanonicalPath = srcDir.getCanonicalPath();
    String destDirCanonicalPath = destDir.getCanonicalPath();
    if (destDirCanonicalPath.startsWith(srcDirCanonicalPath)) {
      File[] srcFiles = listFiles(srcDir, fileFilter);
      if (srcFiles.length > 0) {
        exclusionList = new ArrayList<>(srcFiles.length);
        for (File srcFile : srcFiles) {
          File copiedFile = new File(destDir, srcFile.getName());
          exclusionList.add(copiedFile.getCanonicalPath());
        } 
      } 
    } 
    doCopyDirectory(srcDir, destDir, fileFilter, exclusionList, preserveFileDate, preserveFileDate ? 
        addCopyAttributes(copyOptions) : copyOptions);
  }
  
  public static void copyDirectoryToDirectory(File sourceDir, File destinationDir) throws IOException {
    requireDirectoryIfExists(sourceDir, "sourceDir");
    requireDirectoryIfExists(destinationDir, "destinationDir");
    copyDirectory(sourceDir, new File(destinationDir, sourceDir.getName()), true);
  }
  
  public static void copyFile(File srcFile, File destFile) throws IOException {
    copyFile(srcFile, destFile, new CopyOption[] { StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING });
  }
  
  public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
    (new CopyOption[2])[0] = StandardCopyOption.COPY_ATTRIBUTES;
    (new CopyOption[2])[1] = StandardCopyOption.REPLACE_EXISTING;
    (new CopyOption[1])[0] = StandardCopyOption.REPLACE_EXISTING;
    copyFile(srcFile, destFile, preserveFileDate ? new CopyOption[2] : new CopyOption[1]);
  }
  
  public static void copyFile(File srcFile, File destFile, boolean preserveFileDate, CopyOption... copyOptions) throws IOException {
    copyFile(srcFile, destFile, preserveFileDate ? addCopyAttributes(copyOptions) : copyOptions);
  }
  
  public static void copyFile(File srcFile, File destFile, CopyOption... copyOptions) throws IOException {
    requireFileCopy(srcFile, destFile);
    requireFile(srcFile, "srcFile");
    requireCanonicalPathsNotEquals(srcFile, destFile);
    createParentDirectories(destFile);
    requireFileIfExists(destFile, "destFile");
    if (destFile.exists())
      requireCanWrite(destFile, "destFile"); 
    Files.copy(srcFile.toPath(), destFile.toPath(), copyOptions);
    requireEqualSizes(srcFile, destFile, srcFile.length(), destFile.length());
  }
  
  public static long copyFile(File input, OutputStream output) throws IOException {
    try (InputStream fis = Files.newInputStream(input.toPath(), new java.nio.file.OpenOption[0])) {
      return IOUtils.copyLarge(fis, output);
    } 
  }
  
  public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
    copyFileToDirectory(srcFile, destDir, true);
  }
  
  public static void copyFileToDirectory(File sourceFile, File destinationDir, boolean preserveFileDate) throws IOException {
    Objects.requireNonNull(sourceFile, "sourceFile");
    requireDirectoryIfExists(destinationDir, "destinationDir");
    copyFile(sourceFile, new File(destinationDir, sourceFile.getName()), preserveFileDate);
  }
  
  public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
    try (InputStream inputStream = source) {
      copyToFile(inputStream, destination);
    } 
  }
  
  public static void copyToDirectory(File sourceFile, File destinationDir) throws IOException {
    Objects.requireNonNull(sourceFile, "sourceFile");
    if (sourceFile.isFile()) {
      copyFileToDirectory(sourceFile, destinationDir);
    } else if (sourceFile.isDirectory()) {
      copyDirectoryToDirectory(sourceFile, destinationDir);
    } else {
      throw new FileNotFoundException("The source " + sourceFile + " does not exist");
    } 
  }
  
  public static void copyToDirectory(Iterable<File> sourceIterable, File destinationDir) throws IOException {
    Objects.requireNonNull(sourceIterable, "sourceIterable");
    for (File src : sourceIterable)
      copyFileToDirectory(src, destinationDir); 
  }
  
  public static void copyToFile(InputStream inputStream, File file) throws IOException {
    try (OutputStream out = openOutputStream(file)) {
      IOUtils.copy(inputStream, out);
    } 
  }
  
  public static void copyURLToFile(URL source, File destination) throws IOException {
    try (InputStream stream = source.openStream()) {
      copyInputStreamToFile(stream, destination);
    } 
  }
  
  public static void copyURLToFile(URL source, File destination, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
    URLConnection connection = source.openConnection();
    connection.setConnectTimeout(connectionTimeoutMillis);
    connection.setReadTimeout(readTimeoutMillis);
    try (InputStream stream = connection.getInputStream()) {
      copyInputStreamToFile(stream, destination);
    } 
  }
  
  public static File createParentDirectories(File file) throws IOException {
    return mkdirs(getParentFile(file));
  }
  
  static String decodeUrl(String url) {
    String decoded = url;
    if (url != null && url.indexOf('%') >= 0) {
      int n = url.length();
      StringBuilder buffer = new StringBuilder();
      ByteBuffer bytes = ByteBuffer.allocate(n);
      for (int i = 0; i < n; ) {
        if (url.charAt(i) == '%')
          try {
            do {
              byte octet = (byte)Integer.parseInt(url.substring(i + 1, i + 3), 16);
              bytes.put(octet);
              i += 3;
            } while (i < n && url.charAt(i) == '%');
            continue;
          } catch (RuntimeException runtimeException) {
          
          } finally {
            if (bytes.position() > 0) {
              bytes.flip();
              buffer.append(StandardCharsets.UTF_8.decode(bytes).toString());
              bytes.clear();
            } 
          }  
        buffer.append(url.charAt(i++));
      } 
      decoded = buffer.toString();
    } 
    return decoded;
  }
  
  public static File delete(File file) throws IOException {
    Objects.requireNonNull(file, "file");
    Files.delete(file.toPath());
    return file;
  }
  
  public static void deleteDirectory(File directory) throws IOException {
    Objects.requireNonNull(directory, "directory");
    if (!directory.exists())
      return; 
    if (!isSymlink(directory))
      cleanDirectory(directory); 
    delete(directory);
  }
  
  private static void deleteDirectoryOnExit(File directory) throws IOException {
    if (!directory.exists())
      return; 
    directory.deleteOnExit();
    if (!isSymlink(directory))
      cleanDirectoryOnExit(directory); 
  }
  
  public static boolean deleteQuietly(File file) {
    if (file == null)
      return false; 
    try {
      if (file.isDirectory())
        cleanDirectory(file); 
    } catch (Exception exception) {}
    try {
      return file.delete();
    } catch (Exception ignored) {
      return false;
    } 
  }
  
  public static boolean directoryContains(File directory, File child) throws IOException {
    requireDirectoryExists(directory, "directory");
    if (child == null)
      return false; 
    if (!directory.exists() || !child.exists())
      return false; 
    return FilenameUtils.directoryContains(directory.getCanonicalPath(), child.getCanonicalPath());
  }
  
  private static void doCopyDirectory(File srcDir, File destDir, FileFilter fileFilter, List<String> exclusionList, boolean preserveDirDate, CopyOption... copyOptions) throws IOException {
    File[] srcFiles = listFiles(srcDir, fileFilter);
    requireDirectoryIfExists(destDir, "destDir");
    mkdirs(destDir);
    requireCanWrite(destDir, "destDir");
    for (File srcFile : srcFiles) {
      File dstFile = new File(destDir, srcFile.getName());
      if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath()))
        if (srcFile.isDirectory()) {
          doCopyDirectory(srcFile, dstFile, fileFilter, exclusionList, preserveDirDate, copyOptions);
        } else {
          copyFile(srcFile, dstFile, copyOptions);
        }  
    } 
    if (preserveDirDate)
      setLastModified(srcDir, destDir); 
  }
  
  public static void forceDelete(File file) throws IOException {
    Counters.PathCounters deleteCounters;
    Objects.requireNonNull(file, "file");
    try {
      deleteCounters = PathUtils.delete(file.toPath(), PathUtils.EMPTY_LINK_OPTION_ARRAY, new DeleteOption[] { (DeleteOption)StandardDeleteOption.OVERRIDE_READ_ONLY });
    } catch (IOException e) {
      throw new IOException("Cannot delete file: " + file, e);
    } 
    if (deleteCounters.getFileCounter().get() < 1L && deleteCounters.getDirectoryCounter().get() < 1L)
      throw new FileNotFoundException("File does not exist: " + file); 
  }
  
  public static void forceDeleteOnExit(File file) throws IOException {
    Objects.requireNonNull(file, "file");
    if (file.isDirectory()) {
      deleteDirectoryOnExit(file);
    } else {
      file.deleteOnExit();
    } 
  }
  
  public static void forceMkdir(File directory) throws IOException {
    mkdirs(directory);
  }
  
  public static void forceMkdirParent(File file) throws IOException {
    Objects.requireNonNull(file, "file");
    File parent = getParentFile(file);
    if (parent == null)
      return; 
    forceMkdir(parent);
  }
  
  public static File getFile(File directory, String... names) {
    Objects.requireNonNull(directory, "directory");
    Objects.requireNonNull(names, "names");
    File file = directory;
    for (String name : names)
      file = new File(file, name); 
    return file;
  }
  
  public static File getFile(String... names) {
    Objects.requireNonNull(names, "names");
    File file = null;
    for (String name : names) {
      if (file == null) {
        file = new File(name);
      } else {
        file = new File(file, name);
      } 
    } 
    return file;
  }
  
  private static File getParentFile(File file) {
    return (file == null) ? null : file.getParentFile();
  }
  
  public static File getTempDirectory() {
    return new File(getTempDirectoryPath());
  }
  
  public static String getTempDirectoryPath() {
    return System.getProperty("java.io.tmpdir");
  }
  
  public static File getUserDirectory() {
    return new File(getUserDirectoryPath());
  }
  
  public static String getUserDirectoryPath() {
    return System.getProperty("user.home");
  }
  
  public static boolean isDirectory(File file, LinkOption... options) {
    return (file != null && Files.isDirectory(file.toPath(), options));
  }
  
  public static boolean isEmptyDirectory(File directory) throws IOException {
    return PathUtils.isEmptyDirectory(directory.toPath());
  }
  
  public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate) {
    return isFileNewer(file, chronoLocalDate, LocalTime.now());
  }
  
  public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
    Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
    Objects.requireNonNull(localTime, "localTime");
    return isFileNewer(file, chronoLocalDate.atTime(localTime));
  }
  
  public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
    return isFileNewer(file, chronoLocalDateTime, ZoneId.systemDefault());
  }
  
  public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
    Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
    Objects.requireNonNull(zoneId, "zoneId");
    return isFileNewer(file, chronoLocalDateTime.atZone(zoneId));
  }
  
  public static boolean isFileNewer(File file, ChronoZonedDateTime<?> chronoZonedDateTime) {
    Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
    return isFileNewer(file, chronoZonedDateTime.toInstant());
  }
  
  public static boolean isFileNewer(File file, Date date) {
    Objects.requireNonNull(date, "date");
    return isFileNewer(file, date.getTime());
  }
  
  public static boolean isFileNewer(File file, File reference) {
    requireExists(reference, "reference");
    return isFileNewer(file, lastModifiedUnchecked(reference));
  }
  
  public static boolean isFileNewer(File file, Instant instant) {
    Objects.requireNonNull(instant, "instant");
    return isFileNewer(file, instant.toEpochMilli());
  }
  
  public static boolean isFileNewer(File file, long timeMillis) {
    Objects.requireNonNull(file, "file");
    return (file.exists() && lastModifiedUnchecked(file) > timeMillis);
  }
  
  public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate) {
    return isFileOlder(file, chronoLocalDate, LocalTime.now());
  }
  
  public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
    Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
    Objects.requireNonNull(localTime, "localTime");
    return isFileOlder(file, chronoLocalDate.atTime(localTime));
  }
  
  public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
    return isFileOlder(file, chronoLocalDateTime, ZoneId.systemDefault());
  }
  
  public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
    Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
    Objects.requireNonNull(zoneId, "zoneId");
    return isFileOlder(file, chronoLocalDateTime.atZone(zoneId));
  }
  
  public static boolean isFileOlder(File file, ChronoZonedDateTime<?> chronoZonedDateTime) {
    Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
    return isFileOlder(file, chronoZonedDateTime.toInstant());
  }
  
  public static boolean isFileOlder(File file, Date date) {
    Objects.requireNonNull(date, "date");
    return isFileOlder(file, date.getTime());
  }
  
  public static boolean isFileOlder(File file, File reference) {
    requireExists(reference, "reference");
    return isFileOlder(file, lastModifiedUnchecked(reference));
  }
  
  public static boolean isFileOlder(File file, Instant instant) {
    Objects.requireNonNull(instant, "instant");
    return isFileOlder(file, instant.toEpochMilli());
  }
  
  public static boolean isFileOlder(File file, long timeMillis) {
    Objects.requireNonNull(file, "file");
    return (file.exists() && lastModifiedUnchecked(file) < timeMillis);
  }
  
  public static boolean isRegularFile(File file, LinkOption... options) {
    return (file != null && Files.isRegularFile(file.toPath(), options));
  }
  
  public static boolean isSymlink(File file) {
    return (file != null && Files.isSymbolicLink(file.toPath()));
  }
  
  public static Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
    return listFiles(directory, fileFilter, dirFilter).iterator();
  }
  
  public static Iterator<File> iterateFiles(File directory, String[] extensions, boolean recursive) {
    try {
      return StreamIterator.iterator(streamFiles(directory, recursive, extensions));
    } catch (IOException e) {
      throw new UncheckedIOException(directory.toString(), e);
    } 
  }
  
  public static Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
    return listFilesAndDirs(directory, fileFilter, dirFilter).iterator();
  }
  
  public static long lastModified(File file) throws IOException {
    return Files.getLastModifiedTime(Objects.<Path>requireNonNull(file.toPath(), "file"), new LinkOption[0]).toMillis();
  }
  
  public static long lastModifiedUnchecked(File file) {
    try {
      return lastModified(file);
    } catch (IOException e) {
      throw new UncheckedIOException(file.toString(), e);
    } 
  }
  
  public static LineIterator lineIterator(File file) throws IOException {
    return lineIterator(file, null);
  }
  
  public static LineIterator lineIterator(File file, String charsetName) throws IOException {
    InputStream inputStream = null;
    try {
      inputStream = openInputStream(file);
      return IOUtils.lineIterator(inputStream, charsetName);
    } catch (IOException|RuntimeException ex) {
      IOUtils.closeQuietly(inputStream, ex::addSuppressed);
      throw ex;
    } 
  }
  
  private static AccumulatorPathVisitor listAccumulate(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) throws IOException {
    boolean isDirFilterSet = (dirFilter != null);
    FileEqualsFileFilter rootDirFilter = new FileEqualsFileFilter(directory);
    PathFilter dirPathFilter = isDirFilterSet ? (PathFilter)rootDirFilter.or(dirFilter) : (PathFilter)rootDirFilter;
    AccumulatorPathVisitor visitor = new AccumulatorPathVisitor(Counters.noopPathCounters(), (PathFilter)fileFilter, dirPathFilter);
    Files.walkFileTree(directory.toPath(), Collections.emptySet(), toMaxDepth(isDirFilterSet), (FileVisitor<? super Path>)visitor);
    return visitor;
  }
  
  private static File[] listFiles(File directory, FileFilter fileFilter) throws IOException {
    requireDirectoryExists(directory, "directory");
    File[] files = (fileFilter == null) ? directory.listFiles() : directory.listFiles(fileFilter);
    if (files == null)
      throw new IOException("Unknown I/O error listing contents of directory: " + directory); 
    return files;
  }
  
  public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
    try {
      AccumulatorPathVisitor visitor = listAccumulate(directory, fileFilter, dirFilter);
      return (Collection<File>)visitor.getFileList().stream().map(Path::toFile).collect(Collectors.toList());
    } catch (IOException e) {
      throw new UncheckedIOException(directory.toString(), e);
    } 
  }
  
  public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {
    try {
      return toList(streamFiles(directory, recursive, extensions));
    } catch (IOException e) {
      throw new UncheckedIOException(directory.toString(), e);
    } 
  }
  
  public static Collection<File> listFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
    try {
      AccumulatorPathVisitor visitor = listAccumulate(directory, fileFilter, dirFilter);
      List<Path> list = visitor.getFileList();
      list.addAll(visitor.getDirList());
      return (Collection<File>)list.stream().map(Path::toFile).collect(Collectors.toList());
    } catch (IOException e) {
      throw new UncheckedIOException(directory.toString(), e);
    } 
  }
  
  private static File mkdirs(File directory) throws IOException {
    if (directory != null && !directory.mkdirs() && !directory.isDirectory())
      throw new IOException("Cannot create directory '" + directory + "'."); 
    return directory;
  }
  
  public static void moveDirectory(File srcDir, File destDir) throws IOException {
    validateMoveParameters(srcDir, destDir);
    requireDirectory(srcDir, "srcDir");
    requireAbsent(destDir, "destDir");
    if (!srcDir.renameTo(destDir)) {
      if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath() + File.separator))
        throw new IOException("Cannot move directory: " + srcDir + " to a subdirectory of itself: " + destDir); 
      copyDirectory(srcDir, destDir);
      deleteDirectory(srcDir);
      if (srcDir.exists())
        throw new IOException("Failed to delete original directory '" + srcDir + "' after copy to '" + destDir + "'"); 
    } 
  }
  
  public static void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
    validateMoveParameters(src, destDir);
    if (!destDir.isDirectory()) {
      if (destDir.exists())
        throw new IOException("Destination '" + destDir + "' is not a directory"); 
      if (!createDestDir)
        throw new FileNotFoundException("Destination directory '" + destDir + "' does not exist [createDestDir=" + Character.MIN_VALUE + "]"); 
      mkdirs(destDir);
    } 
    moveDirectory(src, new File(destDir, src.getName()));
  }
  
  public static void moveFile(File srcFile, File destFile) throws IOException {
    moveFile(srcFile, destFile, new CopyOption[] { StandardCopyOption.COPY_ATTRIBUTES });
  }
  
  public static void moveFile(File srcFile, File destFile, CopyOption... copyOptions) throws IOException {
    validateMoveParameters(srcFile, destFile);
    requireFile(srcFile, "srcFile");
    requireAbsent(destFile, null);
    boolean rename = srcFile.renameTo(destFile);
    if (!rename) {
      copyFile(srcFile, destFile, copyOptions);
      if (!srcFile.delete()) {
        deleteQuietly(destFile);
        throw new IOException("Failed to delete original file '" + srcFile + "' after copy to '" + destFile + "'");
      } 
    } 
  }
  
  public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws IOException {
    validateMoveParameters(srcFile, destDir);
    if (!destDir.exists() && createDestDir)
      mkdirs(destDir); 
    requireExistsChecked(destDir, "destDir");
    requireDirectory(destDir, "destDir");
    moveFile(srcFile, new File(destDir, srcFile.getName()));
  }
  
  public static void moveToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
    validateMoveParameters(src, destDir);
    if (src.isDirectory()) {
      moveDirectoryToDirectory(src, destDir, createDestDir);
    } else {
      moveFileToDirectory(src, destDir, createDestDir);
    } 
  }
  
  public static FileInputStream openInputStream(File file) throws IOException {
    Objects.requireNonNull(file, "file");
    return new FileInputStream(file);
  }
  
  public static FileOutputStream openOutputStream(File file) throws IOException {
    return openOutputStream(file, false);
  }
  
  public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
    Objects.requireNonNull(file, "file");
    if (file.exists()) {
      requireFile(file, "file");
      requireCanWrite(file, "file");
    } else {
      createParentDirectories(file);
    } 
    return new FileOutputStream(file, append);
  }
  
  public static byte[] readFileToByteArray(File file) throws IOException {
    try (InputStream inputStream = openInputStream(file)) {
      long fileLength = file.length();
      return (fileLength > 0L) ? IOUtils.toByteArray(inputStream, fileLength) : IOUtils.toByteArray(inputStream);
    } 
  }
  
  @Deprecated
  public static String readFileToString(File file) throws IOException {
    return readFileToString(file, Charset.defaultCharset());
  }
  
  public static String readFileToString(File file, Charset charsetName) throws IOException {
    try (InputStream inputStream = openInputStream(file)) {
      return IOUtils.toString(inputStream, Charsets.toCharset(charsetName));
    } 
  }
  
  public static String readFileToString(File file, String charsetName) throws IOException {
    return readFileToString(file, Charsets.toCharset(charsetName));
  }
  
  @Deprecated
  public static List<String> readLines(File file) throws IOException {
    return readLines(file, Charset.defaultCharset());
  }
  
  public static List<String> readLines(File file, Charset charset) throws IOException {
    try (InputStream inputStream = openInputStream(file)) {
      return IOUtils.readLines(inputStream, Charsets.toCharset(charset));
    } 
  }
  
  public static List<String> readLines(File file, String charsetName) throws IOException {
    return readLines(file, Charsets.toCharset(charsetName));
  }
  
  private static void requireAbsent(File file, String name) throws FileExistsException {
    if (file.exists())
      throw new FileExistsException(
          String.format("File element in parameter '%s' already exists: '%s'", new Object[] { name, file })); 
  }
  
  private static void requireCanonicalPathsNotEquals(File file1, File file2) throws IOException {
    String canonicalPath = file1.getCanonicalPath();
    if (canonicalPath.equals(file2.getCanonicalPath()))
      throw new IllegalArgumentException(
          String.format("File canonical paths are equal: '%s' (file1='%s', file2='%s')", new Object[] { canonicalPath, file1, file2 })); 
  }
  
  private static void requireCanWrite(File file, String name) {
    Objects.requireNonNull(file, "file");
    if (!file.canWrite())
      throw new IllegalArgumentException("File parameter '" + name + " is not writable: '" + file + "'"); 
  }
  
  private static File requireDirectory(File directory, String name) {
    Objects.requireNonNull(directory, name);
    if (!directory.isDirectory())
      throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'"); 
    return directory;
  }
  
  private static File requireDirectoryExists(File directory, String name) {
    requireExists(directory, name);
    requireDirectory(directory, name);
    return directory;
  }
  
  private static File requireDirectoryIfExists(File directory, String name) {
    Objects.requireNonNull(directory, name);
    if (directory.exists())
      requireDirectory(directory, name); 
    return directory;
  }
  
  private static void requireEqualSizes(File srcFile, File destFile, long srcLen, long dstLen) throws IOException {
    if (srcLen != dstLen)
      throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "' Expected length: " + srcLen + " Actual: " + dstLen); 
  }
  
  private static File requireExists(File file, String fileParamName) {
    Objects.requireNonNull(file, fileParamName);
    if (!file.exists())
      throw new IllegalArgumentException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'"); 
    return file;
  }
  
  private static File requireExistsChecked(File file, String fileParamName) throws FileNotFoundException {
    Objects.requireNonNull(file, fileParamName);
    if (!file.exists())
      throw new FileNotFoundException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'"); 
    return file;
  }
  
  private static File requireFile(File file, String name) {
    Objects.requireNonNull(file, name);
    if (!file.isFile())
      throw new IllegalArgumentException("Parameter '" + name + "' is not a file: " + file); 
    return file;
  }
  
  private static void requireFileCopy(File source, File destination) throws FileNotFoundException {
    requireExistsChecked(source, "source");
    Objects.requireNonNull(destination, "destination");
  }
  
  private static File requireFileIfExists(File file, String name) {
    Objects.requireNonNull(file, name);
    return file.exists() ? requireFile(file, name) : file;
  }
  
  private static void setLastModified(File sourceFile, File targetFile) throws IOException {
    Objects.requireNonNull(sourceFile, "sourceFile");
    setLastModified(targetFile, lastModified(sourceFile));
  }
  
  private static void setLastModified(File file, long timeMillis) throws IOException {
    Objects.requireNonNull(file, "file");
    if (!file.setLastModified(timeMillis))
      throw new IOException(String.format("Failed setLastModified(%s) on '%s'", new Object[] { Long.valueOf(timeMillis), file })); 
  }
  
  public static long sizeOf(File file) {
    requireExists(file, "file");
    return file.isDirectory() ? sizeOfDirectory0(file) : file.length();
  }
  
  private static long sizeOf0(File file) {
    Objects.requireNonNull(file, "file");
    if (file.isDirectory())
      return sizeOfDirectory0(file); 
    return file.length();
  }
  
  public static BigInteger sizeOfAsBigInteger(File file) {
    requireExists(file, "file");
    return file.isDirectory() ? sizeOfDirectoryBig0(file) : BigInteger.valueOf(file.length());
  }
  
  private static BigInteger sizeOfBig0(File file) {
    Objects.requireNonNull(file, "fileOrDir");
    return file.isDirectory() ? sizeOfDirectoryBig0(file) : BigInteger.valueOf(file.length());
  }
  
  public static long sizeOfDirectory(File directory) {
    return sizeOfDirectory0(requireDirectoryExists(directory, "directory"));
  }
  
  private static long sizeOfDirectory0(File directory) {
    Objects.requireNonNull(directory, "directory");
    File[] files = directory.listFiles();
    if (files == null)
      return 0L; 
    long size = 0L;
    for (File file : files) {
      if (!isSymlink(file)) {
        size += sizeOf0(file);
        if (size < 0L)
          break; 
      } 
    } 
    return size;
  }
  
  public static BigInteger sizeOfDirectoryAsBigInteger(File directory) {
    return sizeOfDirectoryBig0(requireDirectoryExists(directory, "directory"));
  }
  
  private static BigInteger sizeOfDirectoryBig0(File directory) {
    Objects.requireNonNull(directory, "directory");
    File[] files = directory.listFiles();
    if (files == null)
      return BigInteger.ZERO; 
    BigInteger size = BigInteger.ZERO;
    for (File file : files) {
      if (!isSymlink(file))
        size = size.add(sizeOfBig0(file)); 
    } 
    return size;
  }
  
  public static Stream<File> streamFiles(File directory, boolean recursive, String... extensions) throws IOException {
    IOFileFilter filter = (extensions == null) ? FileFileFilter.INSTANCE : FileFileFilter.INSTANCE.and((IOFileFilter)new SuffixFileFilter(toSuffixes(extensions)));
    return PathUtils.walk(directory.toPath(), (PathFilter)filter, toMaxDepth(recursive), false, new FileVisitOption[] { FileVisitOption.FOLLOW_LINKS }).map(Path::toFile);
  }
  
  public static File toFile(URL url) {
    if (url == null || !"file".equalsIgnoreCase(url.getProtocol()))
      return null; 
    String filename = url.getFile().replace('/', File.separatorChar);
    return new File(decodeUrl(filename));
  }
  
  public static File[] toFiles(URL... urls) {
    if (IOUtils.length((Object[])urls) == 0)
      return EMPTY_FILE_ARRAY; 
    File[] files = new File[urls.length];
    for (int i = 0; i < urls.length; i++) {
      URL url = urls[i];
      if (url != null) {
        if (!"file".equalsIgnoreCase(url.getProtocol()))
          throw new IllegalArgumentException("Can only convert file URL to a File: " + url); 
        files[i] = toFile(url);
      } 
    } 
    return files;
  }
  
  private static List<File> toList(Stream<File> stream) {
    return stream.collect((Collector)Collectors.toList());
  }
  
  private static int toMaxDepth(boolean recursive) {
    return recursive ? Integer.MAX_VALUE : 1;
  }
  
  private static String[] toSuffixes(String... extensions) {
    Objects.requireNonNull(extensions, "extensions");
    String[] suffixes = new String[extensions.length];
    for (int i = 0; i < extensions.length; i++)
      suffixes[i] = "." + extensions[i]; 
    return suffixes;
  }
  
  public static void touch(File file) throws IOException {
    Objects.requireNonNull(file, "file");
    if (!file.exists())
      openOutputStream(file).close(); 
    setLastModified(file, System.currentTimeMillis());
  }
  
  public static URL[] toURLs(File... files) throws IOException {
    Objects.requireNonNull(files, "files");
    URL[] urls = new URL[files.length];
    for (int i = 0; i < urls.length; i++)
      urls[i] = files[i].toURI().toURL(); 
    return urls;
  }
  
  private static void validateMoveParameters(File source, File destination) throws FileNotFoundException {
    Objects.requireNonNull(source, "source");
    Objects.requireNonNull(destination, "destination");
    if (!source.exists())
      throw new FileNotFoundException("Source '" + source + "' does not exist"); 
  }
  
  public static boolean waitFor(File file, int seconds) {
    Objects.requireNonNull(file, "file");
    long finishAtMillis = System.currentTimeMillis() + seconds * 1000L;
    boolean wasInterrupted = false;
    try {
      while (!file.exists()) {
        long remainingMillis = finishAtMillis - System.currentTimeMillis();
        if (remainingMillis < 0L)
          return false; 
        try {
          Thread.sleep(Math.min(100L, remainingMillis));
        } catch (InterruptedException ignore) {
          wasInterrupted = true;
        } catch (Exception ex) {
          break;
        } 
      } 
    } finally {
      if (wasInterrupted)
        Thread.currentThread().interrupt(); 
    } 
    return true;
  }
  
  @Deprecated
  public static void write(File file, CharSequence data) throws IOException {
    write(file, data, Charset.defaultCharset(), false);
  }
  
  @Deprecated
  public static void write(File file, CharSequence data, boolean append) throws IOException {
    write(file, data, Charset.defaultCharset(), append);
  }
  
  public static void write(File file, CharSequence data, Charset charset) throws IOException {
    write(file, data, charset, false);
  }
  
  public static void write(File file, CharSequence data, Charset charset, boolean append) throws IOException {
    writeStringToFile(file, Objects.toString(data, null), charset, append);
  }
  
  public static void write(File file, CharSequence data, String charsetName) throws IOException {
    write(file, data, charsetName, false);
  }
  
  public static void write(File file, CharSequence data, String charsetName, boolean append) throws IOException {
    write(file, data, Charsets.toCharset(charsetName), append);
  }
  
  public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
    writeByteArrayToFile(file, data, false);
  }
  
  public static void writeByteArrayToFile(File file, byte[] data, boolean append) throws IOException {
    writeByteArrayToFile(file, data, 0, data.length, append);
  }
  
  public static void writeByteArrayToFile(File file, byte[] data, int off, int len) throws IOException {
    writeByteArrayToFile(file, data, off, len, false);
  }
  
  public static void writeByteArrayToFile(File file, byte[] data, int off, int len, boolean append) throws IOException {
    try (OutputStream out = openOutputStream(file, append)) {
      out.write(data, off, len);
    } 
  }
  
  public static void writeLines(File file, Collection<?> lines) throws IOException {
    writeLines(file, null, lines, null, false);
  }
  
  public static void writeLines(File file, Collection<?> lines, boolean append) throws IOException {
    writeLines(file, null, lines, null, append);
  }
  
  public static void writeLines(File file, Collection<?> lines, String lineEnding) throws IOException {
    writeLines(file, null, lines, lineEnding, false);
  }
  
  public static void writeLines(File file, Collection<?> lines, String lineEnding, boolean append) throws IOException {
    writeLines(file, null, lines, lineEnding, append);
  }
  
  public static void writeLines(File file, String charsetName, Collection<?> lines) throws IOException {
    writeLines(file, charsetName, lines, null, false);
  }
  
  public static void writeLines(File file, String charsetName, Collection<?> lines, boolean append) throws IOException {
    writeLines(file, charsetName, lines, null, append);
  }
  
  public static void writeLines(File file, String charsetName, Collection<?> lines, String lineEnding) throws IOException {
    writeLines(file, charsetName, lines, lineEnding, false);
  }
  
  public static void writeLines(File file, String charsetName, Collection<?> lines, String lineEnding, boolean append) throws IOException {
    try (OutputStream out = new BufferedOutputStream(openOutputStream(file, append))) {
      IOUtils.writeLines(lines, lineEnding, out, charsetName);
    } 
  }
  
  @Deprecated
  public static void writeStringToFile(File file, String data) throws IOException {
    writeStringToFile(file, data, Charset.defaultCharset(), false);
  }
  
  @Deprecated
  public static void writeStringToFile(File file, String data, boolean append) throws IOException {
    writeStringToFile(file, data, Charset.defaultCharset(), append);
  }
  
  public static void writeStringToFile(File file, String data, Charset charset) throws IOException {
    writeStringToFile(file, data, charset, false);
  }
  
  public static void writeStringToFile(File file, String data, Charset charset, boolean append) throws IOException {
    try (OutputStream out = openOutputStream(file, append)) {
      IOUtils.write(data, out, charset);
    } 
  }
  
  public static void writeStringToFile(File file, String data, String charsetName) throws IOException {
    writeStringToFile(file, data, charsetName, false);
  }
  
  public static void writeStringToFile(File file, String data, String charsetName, boolean append) throws IOException {
    writeStringToFile(file, data, Charsets.toCharset(charsetName), append);
  }
}
