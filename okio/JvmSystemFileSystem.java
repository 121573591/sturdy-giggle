package okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\013\n\002\020 \n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\016\n\002\b\006\b\020\030\0002\0020\001B\007¢\006\004\b\002\020\003J\037\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\026¢\006\004\b\t\020\nJ\037\020\016\032\0020\r2\006\020\013\032\0020\0042\006\020\f\032\0020\004H\026¢\006\004\b\016\020\017J\027\020\021\032\0020\0042\006\020\020\032\0020\004H\026¢\006\004\b\021\020\022J\037\020\025\032\0020\r2\006\020\023\032\0020\0042\006\020\024\032\0020\006H\026¢\006\004\b\025\020\026J\037\020\027\032\0020\r2\006\020\013\032\0020\0042\006\020\f\032\0020\004H\026¢\006\004\b\027\020\017J\037\020\030\032\0020\r2\006\020\020\032\0020\0042\006\020\007\032\0020\006H\026¢\006\004\b\030\020\026J\035\020\032\032\b\022\004\022\0020\0040\0312\006\020\023\032\0020\004H\026¢\006\004\b\032\020\033J'\020\032\032\n\022\004\022\0020\004\030\0010\0312\006\020\023\032\0020\0042\006\020\034\032\0020\006H\002¢\006\004\b\032\020\035J\037\020\036\032\n\022\004\022\0020\004\030\0010\0312\006\020\023\032\0020\004H\026¢\006\004\b\036\020\033J\031\020 \032\004\030\0010\0372\006\020\020\032\0020\004H\026¢\006\004\b \020!J\027\020#\032\0020\"2\006\020\005\032\0020\004H\026¢\006\004\b#\020$J'\020%\032\0020\"2\006\020\005\032\0020\0042\006\020\024\032\0020\0062\006\020\007\032\0020\006H\026¢\006\004\b%\020&J\037\020'\032\0020\b2\006\020\005\032\0020\0042\006\020\024\032\0020\006H\026¢\006\004\b'\020\nJ\027\020\013\032\0020(2\006\020\005\032\0020\004H\026¢\006\004\b\013\020)J\017\020+\032\0020*H\026¢\006\004\b+\020,J\023\020-\032\0020\r*\0020\004H\002¢\006\004\b-\020.J\023\020/\032\0020\r*\0020\004H\002¢\006\004\b/\020.¨\0060"}, d2 = {"Lokio/JvmSystemFileSystem;", "Lokio/FileSystem;", "<init>", "()V", "Lokio/Path;", "file", "", "mustExist", "Lokio/Sink;", "appendingSink", "(Lokio/Path;Z)Lokio/Sink;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "dir", "mustCreate", "createDirectory", "(Lokio/Path;Z)V", "createSymlink", "delete", "", "list", "(Lokio/Path;)Ljava/util/List;", "throwOnFailure", "(Lokio/Path;Z)Ljava/util/List;", "listOrNull", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "sink", "Lokio/Source;", "(Lokio/Path;)Lokio/Source;", "", "toString", "()Ljava/lang/String;", "requireCreate", "(Lokio/Path;)V", "requireExist", "okio"})
@SourceDebugExtension({"SMAP\nJvmSystemFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmSystemFileSystem.kt\nokio/JvmSystemFileSystem\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,158:1\n11400#2,3:159\n*S KotlinDebug\n*F\n+ 1 JvmSystemFileSystem.kt\nokio/JvmSystemFileSystem\n*L\n77#1:159,3\n*E\n"})
public class JvmSystemFileSystem extends FileSystem {
  @NotNull
  public Path canonicalize(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    File canonicalFile = path.toFile().getCanonicalFile();
    if (!canonicalFile.exists())
      throw new FileNotFoundException("no such file"); 
    Intrinsics.checkNotNull(canonicalFile);
    return Path.Companion.get$default(Path.Companion, canonicalFile, false, 1, (Object)null);
  }
  
  @Nullable
  public FileMetadata metadataOrNull(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    File file = path.toFile();
    boolean isRegularFile = file.isFile();
    boolean isDirectory = file.isDirectory();
    long lastModifiedAtMillis = file.lastModified();
    long size = file.length();
    if (!isRegularFile && 
      !isDirectory && 
      lastModifiedAtMillis == 0L && 
      size == 0L && 
      !file.exists())
      return null; 
    return new FileMetadata(
        isRegularFile, 
        isDirectory, 
        null, 
        Long.valueOf(size), 
        null, 
        Long.valueOf(lastModifiedAtMillis), 
        null, null, 128, null);
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
    File file = dir.toFile();
    String[] entries = file.list();
    if (entries == null) {
      if (throwOnFailure) {
        if (!file.exists())
          throw new FileNotFoundException("no such file: " + dir); 
        throw new IOException("failed to list " + dir);
      } 
      return null;
    } 
    String[] arrayOfString1 = entries;
    ArrayList<Path> arrayList = new ArrayList();
    int $i$f$mapTo = 0;
    byte b;
    int i;
    for (b = 0, i = arrayOfString1.length; b < i; ) {
      Object item$iv = arrayOfString1[b];
      Object object1 = item$iv;
      ArrayList<Path> arrayList1 = arrayList;
      int $i$a$-mapTo-JvmSystemFileSystem$list$result$1 = 0;
      Intrinsics.checkNotNull(object1);
      arrayList1.add(dir.resolve((String)object1));
    } 
    List<Path> result = arrayList;
    CollectionsKt.sort(result);
    return result;
  }
  
  @NotNull
  public FileHandle openReadOnly(@NotNull Path file) {
    Intrinsics.checkNotNullParameter(file, "file");
    return new JvmFileHandle(false, new RandomAccessFile(file.toFile(), "r"));
  }
  
  @NotNull
  public FileHandle openReadWrite(@NotNull Path file, boolean mustCreate, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    if (!((!mustCreate || !mustExist) ? 1 : 0)) {
      int $i$a$-require-JvmSystemFileSystem$openReadWrite$1 = 0;
      String str = "Cannot require mustCreate and mustExist at the same time.";
      throw new IllegalArgumentException(str.toString());
    } 
    if (mustCreate)
      requireCreate(file); 
    if (mustExist)
      requireExist(file); 
    return new JvmFileHandle(true, new RandomAccessFile(file.toFile(), "rw"));
  }
  
  @NotNull
  public Source source(@NotNull Path file) {
    Intrinsics.checkNotNullParameter(file, "file");
    return Okio.source(file.toFile());
  }
  
  @NotNull
  public Sink sink(@NotNull Path file, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(file, "file");
    if (mustCreate)
      requireCreate(file); 
    return Okio.sink$default(file.toFile(), false, 1, null);
  }
  
  @NotNull
  public Sink appendingSink(@NotNull Path file, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    if (mustExist)
      requireExist(file); 
    return Okio.sink(file.toFile(), true);
  }
  
  public void createDirectory(@NotNull Path dir, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    if (!dir.toFile().mkdir()) {
      metadataOrNull(dir);
      boolean alreadyExist = (metadataOrNull(dir) != null) ? ((metadataOrNull(dir).isDirectory() == true)) : false;
      if (alreadyExist) {
        if (mustCreate)
          throw new IOException(dir + " already exists."); 
        return;
      } 
      throw new IOException("failed to create directory: " + dir);
    } 
  }
  
  public void atomicMove(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    boolean renamed = source.toFile().renameTo(target.toFile());
    if (!renamed)
      throw new IOException("failed to move " + source + " to " + target); 
  }
  
  public void delete(@NotNull Path path, boolean mustExist) {
    Intrinsics.checkNotNullParameter(path, "path");
    if (Thread.interrupted())
      throw new InterruptedIOException("interrupted"); 
    File file = path.toFile();
    boolean deleted = file.delete();
    if (!deleted) {
      if (file.exists())
        throw new IOException("failed to delete " + path); 
      if (mustExist)
        throw new FileNotFoundException("no such file: " + path); 
    } 
  }
  
  public void createSymlink(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    throw new IOException("unsupported");
  }
  
  @NotNull
  public String toString() {
    return "JvmSystemFileSystem";
  }
  
  private final void requireExist(Path $this$requireExist) {
    if (!exists($this$requireExist))
      throw new IOException($this$requireExist + " doesn't exist."); 
  }
  
  private final void requireCreate(Path $this$requireCreate) {
    if (exists($this$requireCreate))
      throw new IOException($this$requireCreate + " already exists."); 
  }
}
