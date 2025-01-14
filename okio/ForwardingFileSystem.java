package okio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000X\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\013\n\002\020 \n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\006\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\b&\030\0002\0020\001B\017\022\006\020\002\032\0020\001¢\006\004\b\003\020\004J\037\020\n\032\0020\t2\006\020\006\032\0020\0052\006\020\b\032\0020\007H\026¢\006\004\b\n\020\013J\037\020\017\032\0020\0162\006\020\f\032\0020\0052\006\020\r\032\0020\005H\026¢\006\004\b\017\020\020J\027\020\022\032\0020\0052\006\020\021\032\0020\005H\026¢\006\004\b\022\020\023J\037\020\026\032\0020\0162\006\020\024\032\0020\0052\006\020\025\032\0020\007H\026¢\006\004\b\026\020\027J\037\020\030\032\0020\0162\006\020\f\032\0020\0052\006\020\r\032\0020\005H\026¢\006\004\b\030\020\020J\037\020\031\032\0020\0162\006\020\021\032\0020\0052\006\020\b\032\0020\007H\026¢\006\004\b\031\020\027J\035\020\033\032\b\022\004\022\0020\0050\0322\006\020\024\032\0020\005H\026¢\006\004\b\033\020\034J\037\020\035\032\n\022\004\022\0020\005\030\0010\0322\006\020\024\032\0020\005H\026¢\006\004\b\035\020\034J%\020 \032\b\022\004\022\0020\0050\0372\006\020\024\032\0020\0052\006\020\036\032\0020\007H\026¢\006\004\b \020!J\031\020#\032\004\030\0010\"2\006\020\021\032\0020\005H\026¢\006\004\b#\020$J'\020(\032\0020\0052\006\020\021\032\0020\0052\006\020&\032\0020%2\006\020'\032\0020%H\026¢\006\004\b(\020)J\037\020*\032\0020\0052\006\020\021\032\0020\0052\006\020&\032\0020%H\026¢\006\004\b*\020+J\027\020-\032\0020,2\006\020\006\032\0020\005H\026¢\006\004\b-\020.J'\020/\032\0020,2\006\020\006\032\0020\0052\006\020\025\032\0020\0072\006\020\b\032\0020\007H\026¢\006\004\b/\0200J\037\0201\032\0020\t2\006\020\006\032\0020\0052\006\020\025\032\0020\007H\026¢\006\004\b1\020\013J\027\020\f\032\002022\006\020\006\032\0020\005H\026¢\006\004\b\f\0203J\017\0204\032\0020%H\026¢\006\004\b4\0205R\027\020\002\032\0020\0018\007¢\006\f\n\004\b\002\0206\032\004\b\002\0207¨\0068"}, d2 = {"Lokio/ForwardingFileSystem;", "Lokio/FileSystem;", "delegate", "<init>", "(Lokio/FileSystem;)V", "Lokio/Path;", "file", "", "mustExist", "Lokio/Sink;", "appendingSink", "(Lokio/Path;Z)Lokio/Sink;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "dir", "mustCreate", "createDirectory", "(Lokio/Path;Z)V", "createSymlink", "delete", "", "list", "(Lokio/Path;)Ljava/util/List;", "listOrNull", "followSymlinks", "Lkotlin/sequences/Sequence;", "listRecursively", "(Lokio/Path;Z)Lkotlin/sequences/Sequence;", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "", "functionName", "parameterName", "onPathParameter", "(Lokio/Path;Ljava/lang/String;Ljava/lang/String;)Lokio/Path;", "onPathResult", "(Lokio/Path;Ljava/lang/String;)Lokio/Path;", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "sink", "Lokio/Source;", "(Lokio/Path;)Lokio/Source;", "toString", "()Ljava/lang/String;", "Lokio/FileSystem;", "()Lokio/FileSystem;", "okio"})
@SourceDebugExtension({"SMAP\nForwardingFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardingFileSystem.kt\nokio/ForwardingFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,243:1\n1620#2,3:244\n1620#2,3:247\n*S KotlinDebug\n*F\n+ 1 ForwardingFileSystem.kt\nokio/ForwardingFileSystem\n*L\n166#1:244,3\n174#1:247,3\n*E\n"})
public abstract class ForwardingFileSystem extends FileSystem {
  @NotNull
  private final FileSystem delegate;
  
  @JvmName(name = "delegate")
  @NotNull
  public final FileSystem delegate() {
    return this.delegate;
  }
  
  public ForwardingFileSystem(@NotNull FileSystem delegate) {
    this.delegate = delegate;
  }
  
  @NotNull
  public Path onPathParameter(@NotNull Path path, @NotNull String functionName, @NotNull String parameterName) {
    Intrinsics.checkNotNullParameter(path, "path");
    Intrinsics.checkNotNullParameter(functionName, "functionName");
    Intrinsics.checkNotNullParameter(parameterName, "parameterName");
    return path;
  }
  
  @NotNull
  public Path onPathResult(@NotNull Path path, @NotNull String functionName) {
    Intrinsics.checkNotNullParameter(path, "path");
    Intrinsics.checkNotNullParameter(functionName, "functionName");
    return path;
  }
  
  @NotNull
  public Path canonicalize(@NotNull Path path) throws IOException {
    Intrinsics.checkNotNullParameter(path, "path");
    Path path1 = onPathParameter(path, "canonicalize", "path");
    Path result = this.delegate.canonicalize(path1);
    return onPathResult(result, "canonicalize");
  }
  
  @Nullable
  public FileMetadata metadataOrNull(@NotNull Path path) throws IOException {
    FileMetadata metadataOrNull;
    Intrinsics.checkNotNullParameter(path, "path");
    Path path1 = onPathParameter(path, "metadataOrNull", "path");
    if (this.delegate.metadataOrNull(path1) == null) {
      this.delegate.metadataOrNull(path1);
      return null;
    } 
    if (metadataOrNull.getSymlinkTarget() == null)
      return metadataOrNull; 
    Path symlinkTarget = onPathResult(metadataOrNull.getSymlinkTarget(), "metadataOrNull");
    return FileMetadata.copy$default(metadataOrNull, false, false, symlinkTarget, null, null, null, null, null, 251, null);
  }
  
  @NotNull
  public List<Path> list(@NotNull Path dir) throws IOException {
    Intrinsics.checkNotNullParameter(dir, "dir");
    Path path = onPathParameter(dir, "list", "dir");
    List<Path> result = this.delegate.list(path);
    List<Path> list1 = result;
    ArrayList<Path> arrayList = new ArrayList();
    int $i$f$mapTo = 0;
    for (Path item$iv : list1) {
      Path path1 = item$iv;
      ArrayList<Path> arrayList1 = arrayList;
      int $i$a$-mapTo-ForwardingFileSystem$list$paths$1 = 0;
      arrayList1.add(onPathResult(path1, "list"));
    } 
    List<Path> paths = arrayList;
    CollectionsKt.sort(paths);
    return paths;
  }
  
  @Nullable
  public List<Path> listOrNull(@NotNull Path dir) {
    List<Path> result;
    Intrinsics.checkNotNullParameter(dir, "dir");
    Path path = onPathParameter(dir, "listOrNull", "dir");
    if (this.delegate.listOrNull(path) == null) {
      this.delegate.listOrNull(path);
      return null;
    } 
    List<Path> list1 = result;
    ArrayList<Path> arrayList = new ArrayList();
    int $i$f$mapTo = 0;
    for (Path item$iv : list1) {
      Path path1 = item$iv;
      ArrayList<Path> arrayList1 = arrayList;
      int $i$a$-mapTo-ForwardingFileSystem$listOrNull$paths$1 = 0;
      arrayList1.add(onPathResult(path1, "listOrNull"));
    } 
    List<Path> paths = arrayList;
    CollectionsKt.sort(paths);
    return paths;
  }
  
  @NotNull
  public Sequence<Path> listRecursively(@NotNull Path dir, boolean followSymlinks) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    Path path = onPathParameter(dir, "listRecursively", "dir");
    Sequence<Path> result = this.delegate.listRecursively(path, followSymlinks);
    return SequencesKt.map(result, new ForwardingFileSystem$listRecursively$1());
  }
  
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\b\n\002\030\002\n\002\b\004\020\004\032\0020\0002\006\020\001\032\0020\000H\n¢\006\004\b\002\020\003"}, d2 = {"Lokio/Path;", "it", "invoke", "(Lokio/Path;)Lokio/Path;", "<anonymous>"})
  static final class ForwardingFileSystem$listRecursively$1 extends Lambda implements Function1<Path, Path> {
    ForwardingFileSystem$listRecursively$1() {
      super(1);
    }
    
    @NotNull
    public final Path invoke(@NotNull Path it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return ForwardingFileSystem.this.onPathResult(it, "listRecursively");
    }
  }
  
  @NotNull
  public FileHandle openReadOnly(@NotNull Path file) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Path path = onPathParameter(file, "openReadOnly", "file");
    return this.delegate.openReadOnly(path);
  }
  
  @NotNull
  public FileHandle openReadWrite(@NotNull Path file, boolean mustCreate, boolean mustExist) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Path path = onPathParameter(file, "openReadWrite", "file");
    return this.delegate.openReadWrite(path, mustCreate, mustExist);
  }
  
  @NotNull
  public Source source(@NotNull Path file) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Path path = onPathParameter(file, "source", "file");
    return this.delegate.source(path);
  }
  
  @NotNull
  public Sink sink(@NotNull Path file, boolean mustCreate) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Path path = onPathParameter(file, "sink", "file");
    return this.delegate.sink(path, mustCreate);
  }
  
  @NotNull
  public Sink appendingSink(@NotNull Path file, boolean mustExist) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Path path = onPathParameter(file, "appendingSink", "file");
    return this.delegate.appendingSink(path, mustExist);
  }
  
  public void createDirectory(@NotNull Path dir, boolean mustCreate) throws IOException {
    Intrinsics.checkNotNullParameter(dir, "dir");
    Path path = onPathParameter(dir, "createDirectory", "dir");
    this.delegate.createDirectory(path, mustCreate);
  }
  
  public void atomicMove(@NotNull Path source, @NotNull Path target) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    Path path1 = onPathParameter(source, "atomicMove", "source");
    Path path2 = onPathParameter(target, "atomicMove", "target");
    this.delegate.atomicMove(path1, path2);
  }
  
  public void delete(@NotNull Path path, boolean mustExist) throws IOException {
    Intrinsics.checkNotNullParameter(path, "path");
    Path path1 = onPathParameter(path, "delete", "path");
    this.delegate.delete(path1, mustExist);
  }
  
  public void createSymlink(@NotNull Path source, @NotNull Path target) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    Path path1 = onPathParameter(source, "createSymlink", "source");
    Path path2 = onPathParameter(target, "createSymlink", "target");
    this.delegate.createSymlink(path1, path2);
  }
  
  @NotNull
  public String toString() {
    return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + '(' + this.delegate + ')';
  }
}
