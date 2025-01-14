package okio;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.internal.FixedLengthSource;
import okio.internal.ZipEntry;
import okio.internal.ZipFilesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000X\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020$\n\002\030\002\n\000\n\002\020\016\n\002\b\004\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\f\n\002\020 \n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\007\b\000\030\000 62\0020\001:\0016B7\b\000\022\006\020\003\032\0020\002\022\006\020\004\032\0020\001\022\022\020\007\032\016\022\004\022\0020\002\022\004\022\0020\0060\005\022\b\020\t\032\004\030\0010\b¢\006\004\b\n\020\013J\037\020\020\032\0020\0172\006\020\f\032\0020\0022\006\020\016\032\0020\rH\026¢\006\004\b\020\020\021J\037\020\025\032\0020\0242\006\020\022\032\0020\0022\006\020\023\032\0020\002H\026¢\006\004\b\025\020\026J\027\020\030\032\0020\0022\006\020\027\032\0020\002H\026¢\006\004\b\030\020\031J\027\020\032\032\0020\0022\006\020\027\032\0020\002H\002¢\006\004\b\032\020\031J\037\020\035\032\0020\0242\006\020\033\032\0020\0022\006\020\034\032\0020\rH\026¢\006\004\b\035\020\036J\037\020\037\032\0020\0242\006\020\022\032\0020\0022\006\020\023\032\0020\002H\026¢\006\004\b\037\020\026J\037\020 \032\0020\0242\006\020\027\032\0020\0022\006\020\016\032\0020\rH\026¢\006\004\b \020\036J\035\020\"\032\b\022\004\022\0020\0020!2\006\020\033\032\0020\002H\026¢\006\004\b\"\020#J'\020\"\032\n\022\004\022\0020\002\030\0010!2\006\020\033\032\0020\0022\006\020$\032\0020\rH\002¢\006\004\b\"\020%J\037\020&\032\n\022\004\022\0020\002\030\0010!2\006\020\033\032\0020\002H\026¢\006\004\b&\020#J\031\020(\032\004\030\0010'2\006\020\027\032\0020\002H\026¢\006\004\b(\020)J\027\020+\032\0020*2\006\020\f\032\0020\002H\026¢\006\004\b+\020,J'\020-\032\0020*2\006\020\f\032\0020\0022\006\020\034\032\0020\r2\006\020\016\032\0020\rH\026¢\006\004\b-\020.J\037\020/\032\0020\0172\006\020\f\032\0020\0022\006\020\034\032\0020\rH\026¢\006\004\b/\020\021J\027\020\022\032\002002\006\020\f\032\0020\002H\026¢\006\004\b\022\0201R\026\020\t\032\004\030\0010\b8\002X\004¢\006\006\n\004\b\t\0202R \020\007\032\016\022\004\022\0020\002\022\004\022\0020\0060\0058\002X\004¢\006\006\n\004\b\007\0203R\024\020\004\032\0020\0018\002X\004¢\006\006\n\004\b\004\0204R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\0205¨\0067"}, d2 = {"Lokio/ZipFileSystem;", "Lokio/FileSystem;", "Lokio/Path;", "zipPath", "fileSystem", "", "Lokio/internal/ZipEntry;", "entries", "", "comment", "<init>", "(Lokio/Path;Lokio/FileSystem;Ljava/util/Map;Ljava/lang/String;)V", "file", "", "mustExist", "Lokio/Sink;", "appendingSink", "(Lokio/Path;Z)Lokio/Sink;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "canonicalizeInternal", "dir", "mustCreate", "createDirectory", "(Lokio/Path;Z)V", "createSymlink", "delete", "", "list", "(Lokio/Path;)Ljava/util/List;", "throwOnFailure", "(Lokio/Path;Z)Ljava/util/List;", "listOrNull", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "sink", "Lokio/Source;", "(Lokio/Path;)Lokio/Source;", "Ljava/lang/String;", "Ljava/util/Map;", "Lokio/FileSystem;", "Lokio/Path;", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nZipFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipFileSystem.kt\nokio/ZipFileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,175:1\n52#2,5:176\n52#2,21:181\n60#2,10:202\n57#2,2:212\n71#2,2:214\n52#2,21:216\n*S KotlinDebug\n*F\n+ 1 ZipFileSystem.kt\nokio/ZipFileSystem\n*L\n102#1:176,5\n103#1:181,21\n102#1:202,10\n102#1:212,2\n102#1:214,2\n132#1:216,21\n*E\n"})
public final class ZipFileSystem extends FileSystem {
  public ZipFileSystem(@NotNull Path zipPath, @NotNull FileSystem fileSystem, @NotNull Map<Path, ZipEntry> entries, @Nullable String comment) {
    this.zipPath = zipPath;
    this.fileSystem = fileSystem;
    this.entries = entries;
    this.comment = comment;
  }
  
  @NotNull
  public Path canonicalize(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    Path canonical = canonicalizeInternal(path);
    if (!this.entries.containsKey(canonical))
      throw new FileNotFoundException(String.valueOf(path)); 
    return canonical;
  }
  
  private final Path canonicalizeInternal(Path path) {
    return ROOT.resolve(path, true);
  }
  
  @Nullable
  public FileMetadata metadataOrNull(@NotNull Path path) {
    ZipEntry entry;
    Intrinsics.checkNotNullParameter(path, "path");
    Path canonicalPath = canonicalizeInternal(path);
    if ((ZipEntry)this.entries.get(canonicalPath) == null) {
      (ZipEntry)this.entries.get(canonicalPath);
      return null;
    } 
    FileMetadata basicMetadata = new FileMetadata(
        !entry.isDirectory(), 
        entry.isDirectory(), 
        null, 
        entry.isDirectory() ? null : Long.valueOf(entry.getSize()), 
        null, 
        entry.getLastModifiedAtMillis(), 
        null, null, 128, null);
    if (entry.getOffset() == -1L)
      return basicMetadata; 
    Closeable $this$use$iv = this.fileSystem.openReadOnly(this.zipPath);
    int $i$f$use = 0;
    Object result$iv = null;
    Throwable thrown$iv = null;
    try {
      FileHandle fileHandle = (FileHandle)$this$use$iv;
      int $i$a$-use-ZipFileSystem$metadataOrNull$1 = 0;
      Closeable closeable = Okio.buffer(fileHandle.source(entry.getOffset()));
      int i = 0;
      Object object = null;
      Throwable throwable = null;
      try {
        BufferedSource source = (BufferedSource)closeable;
        int $i$a$-use-ZipFileSystem$metadataOrNull$1$1 = 0;
        object = ZipFilesKt.readLocalHeader(source, basicMetadata);
        try {
          if (closeable != null) {
            closeable.close();
          } else {
          
          } 
        } catch (Throwable t$iv) {
          throwable = t$iv;
        } 
      } catch (Throwable t$iv) {
        throwable = t$iv;
      } finally {
        try {
          if (closeable != null) {
            closeable.close();
          } else {
          
          } 
        } catch (Throwable t$iv) {
          throwable = t$iv;
        } 
      } 
      Intrinsics.checkNotNull(object);
      result$iv = object;
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
    } catch (Throwable t$iv) {
      thrown$iv = t$iv;
    } finally {
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
    } 
    Intrinsics.checkNotNull(result$iv);
    return (FileMetadata)result$iv;
  }
  
  @NotNull
  public FileHandle openReadOnly(@NotNull Path file) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new UnsupportedOperationException("not implemented yet!");
  }
  
  @NotNull
  public FileHandle openReadWrite(@NotNull Path file, boolean mustCreate, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new IOException("zip entries are not writable");
  }
  
  @NotNull
  public List<Path> list(@NotNull Path dir) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    Intrinsics.checkNotNull(list(dir, true));
    return list(dir, true);
  }
  
  @Nullable
  public List<Path> listOrNull(@NotNull Path dir) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    return list(dir, false);
  }
  
  private final List<Path> list(Path dir, boolean throwOnFailure) {
    ZipEntry entry;
    Path canonicalDir = canonicalizeInternal(dir);
    if ((ZipEntry)this.entries.get(canonicalDir) == null) {
      (ZipEntry)this.entries.get(canonicalDir);
      if (throwOnFailure)
        throw new IOException("not a directory: " + dir); 
      return null;
    } 
    return CollectionsKt.toList(entry.getChildren());
  }
  
  @NotNull
  public Source source(@NotNull Path file) throws IOException {
    ZipEntry entry;
    Intrinsics.checkNotNullParameter(file, "file");
    Path canonicalPath = canonicalizeInternal(file);
    if ((ZipEntry)this.entries.get(canonicalPath) == null) {
      (ZipEntry)this.entries.get(canonicalPath);
      throw new FileNotFoundException("no such file: " + file);
    } 
    Closeable $this$use$iv = this.fileSystem.openReadOnly(this.zipPath);
    int $i$f$use = 0;
    Object result$iv = null;
    Throwable thrown$iv = null;
    try {
      FileHandle fileHandle = (FileHandle)$this$use$iv;
      int $i$a$-use-ZipFileSystem$source$source$1 = 0;
      result$iv = Okio.buffer(fileHandle.source(entry.getOffset()));
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
    } catch (Throwable t$iv) {
      thrown$iv = t$iv;
    } finally {
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
    } 
    Intrinsics.checkNotNull(result$iv);
    Object object1 = result$iv;
    ZipFilesKt.skipLocalHeader((BufferedSource)object1);
    InflaterSource inflaterSource = new InflaterSource((Source)new FixedLengthSource((Source)object1, entry.getCompressedSize(), true), new Inflater(true));
    return (entry.getCompressionMethod() == 0) ? (Source)new FixedLengthSource((Source)object1, entry.getSize(), true) : (Source)new FixedLengthSource(inflaterSource, entry.getSize(), false);
  }
  
  @NotNull
  public Sink sink(@NotNull Path file, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new IOException("zip file systems are read-only");
  }
  
  @NotNull
  public Sink appendingSink(@NotNull Path file, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new IOException("zip file systems are read-only");
  }
  
  public void createDirectory(@NotNull Path dir, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    throw new IOException("zip file systems are read-only");
  }
  
  public void atomicMove(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    throw new IOException("zip file systems are read-only");
  }
  
  public void delete(@NotNull Path path, boolean mustExist) {
    Intrinsics.checkNotNullParameter(path, "path");
    throw new IOException("zip file systems are read-only");
  }
  
  public void createSymlink(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    throw new IOException("zip file systems are read-only");
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\006\032\004\b\007\020\b¨\006\t"}, d2 = {"Lokio/ZipFileSystem$Companion;", "", "<init>", "()V", "Lokio/Path;", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "okio"})
  private static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Path getROOT() {
      return ZipFileSystem.ROOT;
    }
  }
  
  @NotNull
  private static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Path zipPath;
  
  @NotNull
  private final FileSystem fileSystem;
  
  @NotNull
  private final Map<Path, ZipEntry> entries;
  
  @Nullable
  private final String comment;
  
  @NotNull
  private static final Path ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object)null);
}
