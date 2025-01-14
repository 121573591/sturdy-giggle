package org.openjdk.nashorn.internal.codegen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.PrivilegedAction;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.options.Options;

public final class OptimisticTypesPersistence {
  private static final int DEFAULT_MAX_FILES = 0;
  
  private static final int UNLIMITED_FILES = -1;
  
  private static final int MAX_FILES = getMaxFiles();
  
  private static final int DEFAULT_CLEANUP_DELAY = 20;
  
  private static final int CLEANUP_DELAY = Math.max(0, Options.getIntProperty("nashorn.typeInfo.cleanupDelaySeconds", 20));
  
  private static final String DEFAULT_CACHE_SUBDIR_NAME = "com.oracle.java.NashornTypeInfo";
  
  private static final File baseCacheDir = createBaseCacheDir();
  
  private static final File cacheDir = createCacheDir(baseCacheDir);
  
  private static final Object[] locks = (cacheDir == null) ? null : createLockArray();
  
  private static final long ERROR_REPORT_THRESHOLD = 60000L;
  
  private static volatile long lastReportedError;
  
  private static final AtomicBoolean scheduledCleanup;
  
  private static final Timer cleanupTimer;
  
  private static final String ANCHOR_PROPS = "anchor.properties";
  
  private static final String JRT_NASHORN_DIR = "/modules/org.openjdk.nashorn";
  
  static {
    if (baseCacheDir == null || MAX_FILES == -1) {
      scheduledCleanup = null;
      cleanupTimer = null;
    } else {
      scheduledCleanup = new AtomicBoolean();
      cleanupTimer = new Timer(true);
    } 
  }
  
  public static Object getLocationDescriptor(Source source, int functionId, Type[] paramTypes) {
    if (cacheDir == null)
      return null; 
    StringBuilder b = new StringBuilder(48);
    b.append(source.getDigest()).append('-').append(functionId);
    if (paramTypes != null && paramTypes.length > 0) {
      b.append('-');
      for (Type t : paramTypes)
        b.append(Type.getShortSignatureDescriptor(t)); 
    } 
    return new LocationDescriptor(new File(cacheDir, b.toString()));
  }
  
  private static final class LocationDescriptor {
    private final File file;
    
    LocationDescriptor(File file) {
      this.file = file;
    }
  }
  
  public static void store(Object locationDescriptor, final Map<Integer, Type> optimisticTypes) {
    if (locationDescriptor == null || optimisticTypes.isEmpty())
      return; 
    final File file = ((LocationDescriptor)locationDescriptor).file;
    AccessController.doPrivileged(new PrivilegedAction<Void>() {
          public Void run() {
            synchronized (OptimisticTypesPersistence.getFileLock(file)) {
              if (!file.exists())
                OptimisticTypesPersistence.scheduleCleanup(); 
              try {
                FileOutputStream out = new FileOutputStream(file);
                try {
                  out.getChannel().lock();
                  DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(out));
                  Type.writeTypeMap(optimisticTypes, dout);
                  dout.flush();
                  out.close();
                } catch (Throwable throwable) {
                  try {
                    out.close();
                  } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                  } 
                  throw throwable;
                } 
              } catch (Exception e) {
                OptimisticTypesPersistence.reportError("write", file, e);
              } 
            } 
            return null;
          }
        });
  }
  
  public static Map<Integer, Type> load(Object locationDescriptor) {
    if (locationDescriptor == null)
      return null; 
    final File file = ((LocationDescriptor)locationDescriptor).file;
    return AccessController.<Map<Integer, Type>>doPrivileged(new PrivilegedAction<Map<Integer, Type>>() {
          public Map<Integer, Type> run() {
            try {
              if (!file.isFile())
                return null; 
              synchronized (OptimisticTypesPersistence.getFileLock(file)) {
                FileInputStream in = new FileInputStream(file);
                try {
                  in.getChannel().lock(0L, Long.MAX_VALUE, true);
                  DataInputStream din = new DataInputStream(new BufferedInputStream(in));
                  Map<Integer, Type> map = Type.readTypeMap(din);
                  in.close();
                  return map;
                } catch (Throwable throwable) {
                  try {
                    in.close();
                  } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                  } 
                  throw throwable;
                } 
              } 
            } catch (Exception e) {
              OptimisticTypesPersistence.reportError("read", file, e);
              return null;
            } 
          }
        });
  }
  
  private static void reportError(String msg, File file, Exception e) {
    long now = System.currentTimeMillis();
    if (now - lastReportedError > 60000L) {
      reportError(String.format("Failed to %s %s", new Object[] { msg, file }), e);
      lastReportedError = now;
    } 
  }
  
  private static void reportError(String msg, Exception e) {
    getLogger().warning(new Object[] { msg, "\n", exceptionToString(e) });
  }
  
  private static String exceptionToString(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw, false);
    e.printStackTrace(pw);
    pw.flush();
    return sw.toString();
  }
  
  private static File createBaseCacheDir() {
    if (MAX_FILES == 0 || Options.getBooleanProperty("nashorn.typeInfo.disabled"))
      return null; 
    try {
      return createBaseCacheDirPrivileged();
    } catch (Exception e) {
      reportError("Failed to create cache dir", e);
      return null;
    } 
  }
  
  private static File createBaseCacheDirPrivileged() {
    return AccessController.<File>doPrivileged(new PrivilegedAction<File>() {
          public File run() {
            File dir;
            String explicitDir = System.getProperty("nashorn.typeInfo.cacheDir");
            if (explicitDir != null) {
              dir = new File(explicitDir);
            } else {
              File systemCacheDir = OptimisticTypesPersistence.getSystemCacheDir();
              dir = new File(systemCacheDir, "com.oracle.java.NashornTypeInfo");
              if (OptimisticTypesPersistence.isSymbolicLink(dir))
                return null; 
            } 
            return dir;
          }
        });
  }
  
  private static File createCacheDir(File baseDir) {
    if (baseDir == null)
      return null; 
    try {
      return createCacheDirPrivileged(baseDir);
    } catch (Exception e) {
      reportError("Failed to create cache dir", e);
      return null;
    } 
  }
  
  private static File createCacheDirPrivileged(final File baseDir) {
    return AccessController.<File>doPrivileged(new PrivilegedAction<File>() {
          public File run() {
            String versionDirName;
            try {
              versionDirName = OptimisticTypesPersistence.getVersionDirName();
            } catch (Exception e) {
              OptimisticTypesPersistence.reportError("Failed to calculate version dir name", e);
              return null;
            } 
            File versionDir = new File(baseDir, versionDirName);
            if (OptimisticTypesPersistence.isSymbolicLink(versionDir))
              return null; 
            versionDir.mkdirs();
            if (versionDir.isDirectory()) {
              OptimisticTypesPersistence.getLogger().info("Optimistic type persistence directory is " + versionDir);
              return versionDir;
            } 
            OptimisticTypesPersistence.getLogger().warning("Could not create optimistic type persistence directory " + versionDir);
            return null;
          }
        });
  }
  
  private static File getSystemCacheDir() {
    String os = System.getProperty("os.name", "generic");
    if ("Mac OS X".equals(os))
      return new File(new File(System.getProperty("user.home"), "Library"), "Caches"); 
    if (os.startsWith("Windows"))
      return new File(System.getProperty("java.io.tmpdir")); 
    return new File(System.getProperty("user.home"), ".cache");
  }
  
  public static String getVersionDirName() throws Exception {
    URL url = OptimisticTypesPersistence.class.getResource("anchor.properties");
    String protocol = url.getProtocol();
    if (protocol.equals("jar")) {
      String jarUrlFile = url.getFile();
      String filePath = jarUrlFile.substring(0, jarUrlFile.indexOf('!'));
      URL file = new URL(filePath);
      InputStream in = file.openStream();
      try {
        byte[] buf = new byte[131072];
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        while (true) {
          int l = in.read(buf);
          if (l == -1) {
            String str = Base64.getUrlEncoder().withoutPadding().encodeToString(digest.digest());
            if (in != null)
              in.close(); 
            return str;
          } 
          digest.update(buf, 0, l);
        } 
      } catch (Throwable throwable) {
        if (in != null)
          try {
            in.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } 
    if (protocol.equals("file")) {
      String fileStr = url.getFile();
      String className = OptimisticTypesPersistence.class.getName();
      int packageNameLen = className.lastIndexOf('.');
      String dirStr = fileStr.substring(0, fileStr.length() - packageNameLen - 1 - "anchor.properties".length());
      File dir = new File(dirStr);
      return "dev-" + (new SimpleDateFormat("yyyyMMdd-HHmmss")).format(new Date(getLastModifiedClassFile(dir, 0L)));
    } 
    if (protocol.equals("jrt"))
      return getJrtVersionDirName(); 
    throw new AssertionError("unknown protocol");
  }
  
  private static long getLastModifiedClassFile(File dir, long max) {
    long currentMax = max;
    for (File f : dir.listFiles()) {
      if (f.getName().endsWith(".class")) {
        long lastModified = f.lastModified();
        if (lastModified > currentMax)
          currentMax = lastModified; 
      } else if (f.isDirectory()) {
        long lastModified = getLastModifiedClassFile(f, currentMax);
        if (lastModified > currentMax)
          currentMax = lastModified; 
      } 
    } 
    return currentMax;
  }
  
  private static boolean isSymbolicLink(File file) {
    if (Files.isSymbolicLink(file.toPath())) {
      getLogger().warning("Directory " + file + " is a symlink");
      return true;
    } 
    return false;
  }
  
  private static Object[] createLockArray() {
    Object[] lockArray = new Object[Runtime.getRuntime().availableProcessors() * 2];
    for (int i = 0; i < lockArray.length; i++)
      lockArray[i] = new Object(); 
    return lockArray;
  }
  
  private static Object getFileLock(File file) {
    return locks[(file.hashCode() & Integer.MAX_VALUE) % locks.length];
  }
  
  private static DebugLogger getLogger() {
    try {
      return Context.getContext().getLogger(RecompilableScriptFunctionData.class);
    } catch (NullPointerException nullPointerException) {
    
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return DebugLogger.DISABLED_LOGGER;
  }
  
  private static void scheduleCleanup() {
    if (MAX_FILES != -1 && scheduledCleanup.compareAndSet(false, true))
      cleanupTimer.schedule(new TimerTask() {
            public void run() {
              OptimisticTypesPersistence.scheduledCleanup.set(false);
              try {
                OptimisticTypesPersistence.doCleanup();
              } catch (IOException iOException) {}
            }
          },  TimeUnit.SECONDS.toMillis(CLEANUP_DELAY)); 
  }
  
  private static void doCleanup() throws IOException {
    Path[] files = getAllRegularFilesInLastModifiedOrder();
    int nFiles = files.length;
    int filesToDelete = Math.max(0, nFiles - MAX_FILES);
    int filesDeleted = 0;
    for (int i = 0; i < nFiles && filesDeleted < filesToDelete; i++) {
      try {
        Files.deleteIfExists(files[i]);
        filesDeleted++;
      } catch (Exception exception) {}
      files[i] = null;
    } 
  }
  
  private static Path[] getAllRegularFilesInLastModifiedOrder() throws IOException {
    Stream<Path> filesStream = Files.walk(baseCacheDir.toPath(), new java.nio.file.FileVisitOption[0]);
    try {
      Path[] arrayOfPath = (Path[])filesStream.filter(new Predicate<Path>() {
            public boolean test(Path path) {
              return !Files.isDirectory(path, new java.nio.file.LinkOption[0]);
            }
          }).map(new Function<Path, PathAndTime>() {
            public OptimisticTypesPersistence.PathAndTime apply(Path path) {
              return new OptimisticTypesPersistence.PathAndTime(path);
            }
          }).sorted().map(new Function<PathAndTime, Path>() {
            public Path apply(OptimisticTypesPersistence.PathAndTime pathAndTime) {
              return pathAndTime.path;
            }
          }).toArray(new IntFunction<Path[]>() {
            public Path[] apply(int length) {
              return new Path[length];
            }
          });
      if (filesStream != null)
        filesStream.close(); 
      return arrayOfPath;
    } catch (Throwable throwable) {
      if (filesStream != null)
        try {
          filesStream.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
  }
  
  private static class PathAndTime implements Comparable<PathAndTime> {
    private final Path path;
    
    private final long time;
    
    PathAndTime(Path path) {
      this.path = path;
      this.time = getTime(path);
    }
    
    public int compareTo(PathAndTime other) {
      return Long.compare(this.time, other.time);
    }
    
    private static long getTime(Path path) {
      try {
        return Files.getLastModifiedTime(path, new java.nio.file.LinkOption[0]).toMillis();
      } catch (IOException e) {
        return -1L;
      } 
    }
  }
  
  private static int getMaxFiles() {
    String str = Options.getStringProperty("nashorn.typeInfo.maxFiles", null);
    if (str == null)
      return 0; 
    if ("unlimited".equals(str))
      return -1; 
    return Math.max(0, Integer.parseInt(str));
  }
  
  private static String getJrtVersionDirName() throws Exception {
    FileSystem fs = getJrtFileSystem();
    Path nashorn = fs.getPath("/modules/org.openjdk.nashorn", new String[0]);
    if (!Files.isDirectory(nashorn, new java.nio.file.LinkOption[0]))
      throw new FileNotFoundException("missing /modules/org.openjdk.nashorn dir in jrt fs"); 
    final MessageDigest digest = MessageDigest.getInstance("SHA-1");
    Files.walk(nashorn, new java.nio.file.FileVisitOption[0]).forEach(new Consumer<Path>() {
          public void accept(Path p) {
            if (Files.isRegularFile(p, new java.nio.file.LinkOption[0]) && p.toString().endsWith(".class"))
              try {
                digest.update(Files.readAllBytes(p));
              } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
              }  
          }
        });
    return Base64.getUrlEncoder().withoutPadding().encodeToString(digest.digest());
  }
  
  private static FileSystem getJrtFileSystem() {
    return AccessController.<FileSystem>doPrivileged(new PrivilegedAction<FileSystem>() {
          public FileSystem run() {
            return FileSystems.getFileSystem(URI.create("jrt:/"));
          }
        });
  }
}
