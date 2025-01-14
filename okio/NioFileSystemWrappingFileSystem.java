package okio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000Z\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\013\n\002\020 \n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\004\b\000\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\037\020\013\032\0020\n2\006\020\007\032\0020\0062\006\020\t\032\0020\bH\026¢\006\004\b\013\020\fJ\037\020\020\032\0020\0172\006\020\r\032\0020\0062\006\020\016\032\0020\006H\026¢\006\004\b\020\020\021J\027\020\023\032\0020\0062\006\020\022\032\0020\006H\026¢\006\004\b\023\020\024J\037\020\027\032\0020\0172\006\020\025\032\0020\0062\006\020\026\032\0020\bH\026¢\006\004\b\027\020\030J\037\020\031\032\0020\0172\006\020\r\032\0020\0062\006\020\016\032\0020\006H\026¢\006\004\b\031\020\021J\037\020\032\032\0020\0172\006\020\022\032\0020\0062\006\020\t\032\0020\bH\026¢\006\004\b\032\020\030J\035\020\034\032\b\022\004\022\0020\0060\0332\006\020\025\032\0020\006H\026¢\006\004\b\034\020\035J'\020\034\032\n\022\004\022\0020\006\030\0010\0332\006\020\025\032\0020\0062\006\020\036\032\0020\bH\002¢\006\004\b\034\020\037J\037\020 \032\n\022\004\022\0020\006\030\0010\0332\006\020\025\032\0020\006H\026¢\006\004\b \020\035J\031\020\"\032\004\030\0010!2\006\020\022\032\0020\006H\026¢\006\004\b\"\020#J\027\020%\032\0020$2\006\020\007\032\0020\006H\026¢\006\004\b%\020&J'\020'\032\0020$2\006\020\007\032\0020\0062\006\020\026\032\0020\b2\006\020\t\032\0020\bH\026¢\006\004\b'\020(J\037\020)\032\0020\n2\006\020\007\032\0020\0062\006\020\026\032\0020\bH\026¢\006\004\b)\020\fJ\027\020\r\032\0020*2\006\020\007\032\0020\006H\026¢\006\004\b\r\020+J\017\020-\032\0020,H\026¢\006\004\b-\020.J\023\0200\032\0020/*\0020\006H\002¢\006\004\b0\0201R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\0202¨\0063"}, d2 = {"Lokio/NioFileSystemWrappingFileSystem;", "Lokio/NioSystemFileSystem;", "Ljava/nio/file/FileSystem;", "nioFileSystem", "<init>", "(Ljava/nio/file/FileSystem;)V", "Lokio/Path;", "file", "", "mustExist", "Lokio/Sink;", "appendingSink", "(Lokio/Path;Z)Lokio/Sink;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "dir", "mustCreate", "createDirectory", "(Lokio/Path;Z)V", "createSymlink", "delete", "", "list", "(Lokio/Path;)Ljava/util/List;", "throwOnFailure", "(Lokio/Path;Z)Ljava/util/List;", "listOrNull", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "sink", "Lokio/Source;", "(Lokio/Path;)Lokio/Source;", "", "toString", "()Ljava/lang/String;", "Ljava/nio/file/Path;", "resolve", "(Lokio/Path;)Ljava/nio/file/Path;", "Ljava/nio/file/FileSystem;", "okio"})
@SourceDebugExtension({"SMAP\nNioFileSystemWrappingFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NioFileSystemWrappingFileSystem.kt\nokio/NioFileSystemWrappingFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,192:1\n1620#2,3:193\n1#3:196\n37#4,2:197\n37#4,2:199\n37#4,2:201\n*S KotlinDebug\n*F\n+ 1 NioFileSystemWrappingFileSystem.kt\nokio/NioFileSystemWrappingFileSystem\n*L\n77#1:193,3\n104#1:197,2\n125#1:199,2\n138#1:201,2\n*E\n"})
public final class NioFileSystemWrappingFileSystem extends NioSystemFileSystem {
  @NotNull
  private final FileSystem nioFileSystem;
  
  public NioFileSystemWrappingFileSystem(@NotNull FileSystem nioFileSystem) {
    this.nioFileSystem = nioFileSystem;
  }
  
  private final Path resolve(Path $this$resolve) {
    Intrinsics.checkNotNullExpressionValue(this.nioFileSystem.getPath($this$resolve.toString(), new String[0]), "getPath(...)");
    return this.nioFileSystem.getPath($this$resolve.toString(), new String[0]);
  }
  
  @NotNull
  public Path canonicalize(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    try {
      Intrinsics.checkNotNullExpressionValue(resolve(path).toRealPath(new LinkOption[0]), "toRealPath(...)");
      return Path.Companion.get$default(Path.Companion, resolve(path).toRealPath(new LinkOption[0]), false, 1, (Object)null);
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException("no such file: " + path);
    } 
  }
  
  @Nullable
  public FileMetadata metadataOrNull(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    return metadataOrNull(resolve(path));
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
    List list1;
    Path nioDir = resolve(dir);
    try {
      list1 = PathsKt.listDirectoryEntries$default(nioDir, null, 1, null);
    } catch (Exception e) {
      if (throwOnFailure) {
        if (!Files.exists(nioDir, Arrays.<LinkOption>copyOf(new LinkOption[0], (new LinkOption[0]).length)))
          throw new FileNotFoundException("no such file: " + dir); 
        throw new IOException("failed to list " + dir);
      } 
      return null;
    } 
    List entries = list1;
    List list2 = entries;
    ArrayList<Path> arrayList = new ArrayList();
    int $i$f$mapTo = 0;
    for (Object item$iv : list2) {
      Path path = (Path)item$iv;
      ArrayList<Path> arrayList1 = arrayList;
      int $i$a$-mapTo-NioFileSystemWrappingFileSystem$list$result$1 = 0;
      arrayList1.add(Path.Companion.get$default(Path.Companion, path, false, 1, (Object)null));
    } 
    List<Path> result = arrayList;
    CollectionsKt.sort(result);
    return result;
  }
  
  @NotNull
  public FileHandle openReadOnly(@NotNull Path file) {
    FileChannel fileChannel1;
    Intrinsics.checkNotNullParameter(file, "file");
    try {
      OpenOption[] arrayOfOpenOption = new OpenOption[1];
      arrayOfOpenOption[0] = StandardOpenOption.READ;
      fileChannel1 = FileChannel.open(resolve(file), arrayOfOpenOption);
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException("no such file: " + file);
    } 
    FileChannel channel = fileChannel1;
    Intrinsics.checkNotNull(channel);
    return new NioFileSystemFileHandle(false, channel);
  }
  
  @NotNull
  public FileHandle openReadWrite(@NotNull Path file, boolean mustCreate, boolean mustExist) {
    FileChannel fileChannel1;
    Intrinsics.checkNotNullParameter(file, "file");
    if (!((!mustCreate || !mustExist) ? 1 : 0)) {
      int $i$a$-require-NioFileSystemWrappingFileSystem$openReadWrite$1 = 0;
      String str = "Cannot require mustCreate and mustExist at the same time.";
      throw new IllegalArgumentException(str.toString());
    } 
    List<StandardOpenOption> list1 = CollectionsKt.createListBuilder(), $this$openReadWrite_u24lambda_u242 = list1;
    int $i$a$-buildList-NioFileSystemWrappingFileSystem$openReadWrite$openOptions$1 = 0;
    $this$openReadWrite_u24lambda_u242.add(StandardOpenOption.READ);
    $this$openReadWrite_u24lambda_u242.add(StandardOpenOption.WRITE);
    if (mustCreate) {
      $this$openReadWrite_u24lambda_u242.add(StandardOpenOption.CREATE_NEW);
    } else if (!mustExist) {
      $this$openReadWrite_u24lambda_u242.add(StandardOpenOption.CREATE);
    } 
    List openOptions = CollectionsKt.build(list1);
    try {
      Collection $this$toTypedArray$iv = openOptions;
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = $this$toTypedArray$iv;
      StandardOpenOption[] arrayOfStandardOpenOption = (StandardOpenOption[])thisCollection$iv.toArray((Object[])new StandardOpenOption[0]);
      fileChannel1 = FileChannel.open(resolve(file), Arrays.<OpenOption>copyOf((OpenOption[])arrayOfStandardOpenOption, arrayOfStandardOpenOption.length));
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException("no such file: " + file);
    } 
    FileChannel channel = fileChannel1;
    Intrinsics.checkNotNull(channel);
    return new NioFileSystemFileHandle(true, channel);
  }
  
  @NotNull
  public Source source(@NotNull Path file) {
    Intrinsics.checkNotNullParameter(file, "file");
    try {
      Intrinsics.checkNotNullExpressionValue(Files.newInputStream(resolve(file), Arrays.copyOf(new OpenOption[0], (new OpenOption[0]).length)), "newInputStream(this, *options)");
      return Okio.source(Files.newInputStream(resolve(file), Arrays.copyOf(new OpenOption[0], (new OpenOption[0]).length)));
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException("no such file: " + file);
    } 
  }
  
  @NotNull
  public Sink sink(@NotNull Path file, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(file, "file");
    List<StandardOpenOption> list1 = CollectionsKt.createListBuilder(), $this$sink_u24lambda_u243 = list1;
    int $i$a$-buildList-NioFileSystemWrappingFileSystem$sink$openOptions$1 = 0;
    if (mustCreate)
      $this$sink_u24lambda_u243.add(StandardOpenOption.CREATE_NEW); 
    List openOptions = CollectionsKt.build(list1);
    try {
      Path path = resolve(file);
      Collection $this$toTypedArray$iv = openOptions;
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = $this$toTypedArray$iv;
      StandardOpenOption[] arrayOfStandardOpenOption = (StandardOpenOption[])thisCollection$iv.toArray((Object[])new StandardOpenOption[0]);
      OpenOption[] arrayOfOpenOption = Arrays.<OpenOption>copyOf((OpenOption[])arrayOfStandardOpenOption, arrayOfStandardOpenOption.length);
      Intrinsics.checkNotNullExpressionValue(Files.newOutputStream(path, Arrays.copyOf(arrayOfOpenOption, arrayOfOpenOption.length)), "newOutputStream(this, *options)");
      return Okio.sink(Files.newOutputStream(path, Arrays.copyOf(arrayOfOpenOption, arrayOfOpenOption.length)));
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException("no such file: " + file);
    } 
  }
  
  @NotNull
  public Sink appendingSink(@NotNull Path file, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    List<StandardOpenOption> list1 = CollectionsKt.createListBuilder(), $this$appendingSink_u24lambda_u244 = list1;
    int $i$a$-buildList-NioFileSystemWrappingFileSystem$appendingSink$openOptions$1 = 0;
    $this$appendingSink_u24lambda_u244.add(StandardOpenOption.APPEND);
    if (!mustExist)
      $this$appendingSink_u24lambda_u244.add(StandardOpenOption.CREATE); 
    List openOptions = CollectionsKt.build(list1);
    Path path = resolve(file);
    Collection $this$toTypedArray$iv = openOptions;
    int $i$f$toTypedArray = 0;
    Collection thisCollection$iv = $this$toTypedArray$iv;
    StandardOpenOption[] arrayOfStandardOpenOption = (StandardOpenOption[])thisCollection$iv.toArray((Object[])new StandardOpenOption[0]);
    OpenOption[] arrayOfOpenOption = Arrays.<OpenOption>copyOf((OpenOption[])arrayOfStandardOpenOption, arrayOfStandardOpenOption.length);
    Intrinsics.checkNotNullExpressionValue(Files.newOutputStream(path, Arrays.copyOf(arrayOfOpenOption, arrayOfOpenOption.length)), "newOutputStream(this, *options)");
    return Okio.sink(Files.newOutputStream(path, Arrays.copyOf(arrayOfOpenOption, arrayOfOpenOption.length)));
  }
  
  public void createDirectory(@NotNull Path dir, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    metadataOrNull(dir);
    boolean alreadyExist = (metadataOrNull(dir) != null) ? ((metadataOrNull(dir).isDirectory() == true)) : false;
    if (alreadyExist && mustCreate)
      throw new IOException(dir + " already exists."); 
    try {
      Intrinsics.checkNotNullExpressionValue(Files.createDirectory(resolve(dir), (FileAttribute<?>[])Arrays.copyOf(new FileAttribute[0], (new FileAttribute[0]).length)), "createDirectory(this, *attributes)");
      Files.createDirectory(resolve(dir), (FileAttribute<?>[])Arrays.copyOf(new FileAttribute[0], (new FileAttribute[0]).length));
    } catch (IOException e) {
      if (alreadyExist)
        return; 
      throw new IOException("failed to create directory: " + dir, (Throwable)e);
    } 
  }
  
  public void atomicMove(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    try {
      Path path1 = resolve(source);
      Path path2 = resolve(target);
      CopyOption[] arrayOfCopyOption = new CopyOption[2];
      arrayOfCopyOption[0] = StandardCopyOption.ATOMIC_MOVE;
      arrayOfCopyOption[1] = StandardCopyOption.REPLACE_EXISTING;
      arrayOfCopyOption = arrayOfCopyOption;
      Intrinsics.checkNotNullExpressionValue(Files.move(path1, path2, Arrays.copyOf(arrayOfCopyOption, arrayOfCopyOption.length)), "move(this, target, *options)");
      Files.move(path1, path2, Arrays.copyOf(arrayOfCopyOption, arrayOfCopyOption.length));
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException(e.getMessage());
    } catch (UnsupportedOperationException e) {
      throw new IOException("atomic move not supported");
    } 
  }
  
  public void delete(@NotNull Path path, boolean mustExist) {
    Intrinsics.checkNotNullParameter(path, "path");
    if (Thread.interrupted())
      throw new InterruptedIOException("interrupted"); 
    Path nioPath = resolve(path);
    try {
      Files.delete(nioPath);
    } catch (NoSuchFileException e) {
      if (mustExist)
        throw new FileNotFoundException("no such file: " + path); 
    } catch (IOException e) {
      if (Files.exists(nioPath, Arrays.<LinkOption>copyOf(new LinkOption[0], (new LinkOption[0]).length)))
        throw new IOException("failed to delete " + path); 
    } 
  }
  
  public void createSymlink(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    Intrinsics.checkNotNullExpressionValue(Files.createSymbolicLink(resolve(source), resolve(target), (FileAttribute<?>[])Arrays.copyOf(new FileAttribute[0], (new FileAttribute[0]).length)), "createSymbolicLink(this, target, *attributes)");
    Files.createSymbolicLink(resolve(source), resolve(target), (FileAttribute<?>[])Arrays.copyOf(new FileAttribute[0], (new FileAttribute[0]).length));
  }
  
  @NotNull
  public String toString() {
    Intrinsics.checkNotNull(Reflection.getOrCreateKotlinClass(this.nioFileSystem.getClass()).getSimpleName());
    return Reflection.getOrCreateKotlinClass(this.nioFileSystem.getClass()).getSimpleName();
  }
}
