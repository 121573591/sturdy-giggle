package okio;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import okio.internal.-FileSystem;
import okio.internal.ResourceFileSystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000h\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\n\002\020\002\n\002\b\022\n\002\020 \n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\002\b\006\b&\030\000 C2\0020\001:\001CB\007¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bJ!\020\007\032\0020\0062\006\020\005\032\0020\0042\b\b\002\020\n\032\0020\tH&¢\006\004\b\007\020\013J\037\020\017\032\0020\0162\006\020\f\032\0020\0042\006\020\r\032\0020\004H&¢\006\004\b\017\020\020J\027\020\022\032\0020\0042\006\020\021\032\0020\004H&¢\006\004\b\022\020\023J\037\020\024\032\0020\0162\006\020\f\032\0020\0042\006\020\r\032\0020\004H\026¢\006\004\b\024\020\020J\025\020\026\032\0020\0162\006\020\025\032\0020\004¢\006\004\b\026\020\027J\037\020\026\032\0020\0162\006\020\025\032\0020\0042\b\b\002\020\030\032\0020\t¢\006\004\b\026\020\031J\025\020\032\032\0020\0162\006\020\025\032\0020\004¢\006\004\b\032\020\027J!\020\032\032\0020\0162\006\020\025\032\0020\0042\b\b\002\020\030\032\0020\tH&¢\006\004\b\032\020\031J\037\020\033\032\0020\0162\006\020\f\032\0020\0042\006\020\r\032\0020\004H&¢\006\004\b\033\020\020J\025\020\034\032\0020\0162\006\020\021\032\0020\004¢\006\004\b\034\020\027J!\020\034\032\0020\0162\006\020\021\032\0020\0042\b\b\002\020\n\032\0020\tH&¢\006\004\b\034\020\031J\025\020\036\032\0020\0162\006\020\035\032\0020\004¢\006\004\b\036\020\027J!\020\036\032\0020\0162\006\020\035\032\0020\0042\b\b\002\020\n\032\0020\tH\026¢\006\004\b\036\020\031J\025\020\037\032\0020\t2\006\020\021\032\0020\004¢\006\004\b\037\020 J\035\020\"\032\b\022\004\022\0020\0040!2\006\020\025\032\0020\004H&¢\006\004\b\"\020#J\037\020$\032\n\022\004\022\0020\004\030\0010!2\006\020\025\032\0020\004H&¢\006\004\b$\020#J\033\020&\032\b\022\004\022\0020\0040%2\006\020\025\032\0020\004¢\006\004\b&\020'J'\020&\032\b\022\004\022\0020\0040%2\006\020\025\032\0020\0042\b\b\002\020(\032\0020\tH\026¢\006\004\b&\020)J\025\020+\032\0020*2\006\020\021\032\0020\004¢\006\004\b+\020,J\031\020-\032\004\030\0010*2\006\020\021\032\0020\004H&¢\006\004\b-\020,J\027\020/\032\0020.2\006\020\005\032\0020\004H&¢\006\004\b/\0200J\025\0201\032\0020.2\006\020\005\032\0020\004¢\006\004\b1\0200J+\0201\032\0020.2\006\020\005\032\0020\0042\b\b\002\020\030\032\0020\t2\b\b\002\020\n\032\0020\tH&¢\006\004\b1\0202J:\020:\032\0028\000\"\004\b\000\02032\006\020\005\032\0020\0042\027\0207\032\023\022\004\022\00205\022\004\022\0028\00004¢\006\002\b6H\bø\001\000¢\006\004\b8\0209J\025\020;\032\0020\0062\006\020\005\032\0020\004¢\006\004\b;\020\bJ!\020;\032\0020\0062\006\020\005\032\0020\0042\b\b\002\020\030\032\0020\tH&¢\006\004\b;\020\013J\027\020\f\032\0020<2\006\020\005\032\0020\004H&¢\006\004\b\f\020=JD\020B\032\0028\000\"\004\b\000\02032\006\020\005\032\0020\0042\b\b\002\020\030\032\0020\t2\027\020?\032\023\022\004\022\0020>\022\004\022\0028\00004¢\006\002\b6H\bø\001\000¢\006\004\b@\020A\002\007\n\005\b20\001¨\006D"}, d2 = {"Lokio/FileSystem;", "", "<init>", "()V", "Lokio/Path;", "file", "Lokio/Sink;", "appendingSink", "(Lokio/Path;)Lokio/Sink;", "", "mustExist", "(Lokio/Path;Z)Lokio/Sink;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "copy", "dir", "createDirectories", "(Lokio/Path;)V", "mustCreate", "(Lokio/Path;Z)V", "createDirectory", "createSymlink", "delete", "fileOrDirectory", "deleteRecursively", "exists", "(Lokio/Path;)Z", "", "list", "(Lokio/Path;)Ljava/util/List;", "listOrNull", "Lkotlin/sequences/Sequence;", "listRecursively", "(Lokio/Path;)Lkotlin/sequences/Sequence;", "followSymlinks", "(Lokio/Path;Z)Lkotlin/sequences/Sequence;", "Lokio/FileMetadata;", "metadata", "(Lokio/Path;)Lokio/FileMetadata;", "metadataOrNull", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "T", "Lkotlin/Function1;", "Lokio/BufferedSource;", "Lkotlin/ExtensionFunctionType;", "readerAction", "-read", "(Lokio/Path;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "read", "sink", "Lokio/Source;", "(Lokio/Path;)Lokio/Source;", "Lokio/BufferedSink;", "writerAction", "-write", "(Lokio/Path;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "write", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystem.kt\nokio/FileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,165:1\n52#2,21:166\n52#2,21:187\n*S KotlinDebug\n*F\n+ 1 FileSystem.kt\nokio/FileSystem\n*L\n67#1:166,21\n81#1:187,21\n*E\n"})
public abstract class FileSystem {
  @NotNull
  public static final Companion Companion;
  
  @NotNull
  public final FileMetadata metadata(@NotNull Path path) throws IOException {
    Intrinsics.checkNotNullParameter(path, "path");
    return -FileSystem.commonMetadata(this, path);
  }
  
  public final boolean exists(@NotNull Path path) throws IOException {
    Intrinsics.checkNotNullParameter(path, "path");
    return -FileSystem.commonExists(this, path);
  }
  
  @NotNull
  public Sequence<Path> listRecursively(@NotNull Path dir, boolean followSymlinks) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    return -FileSystem.commonListRecursively(this, dir, followSymlinks);
  }
  
  @NotNull
  public final Sequence<Path> listRecursively(@NotNull Path dir) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    return listRecursively(dir, false);
  }
  
  @NotNull
  public final FileHandle openReadWrite(@NotNull Path file) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    return openReadWrite(file, false, false);
  }
  
  @NotNull
  public final Sink sink(@NotNull Path file) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    return sink(file, false);
  }
  
  @NotNull
  public final Sink appendingSink(@NotNull Path file) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    return appendingSink(file, false);
  }
  
  public final void createDirectory(@NotNull Path dir) throws IOException {
    Intrinsics.checkNotNullParameter(dir, "dir");
    createDirectory(dir, false);
  }
  
  public final void createDirectories(@NotNull Path dir, boolean mustCreate) throws IOException {
    Intrinsics.checkNotNullParameter(dir, "dir");
    -FileSystem.commonCreateDirectories(this, dir, mustCreate);
  }
  
  public final void createDirectories(@NotNull Path dir) throws IOException {
    Intrinsics.checkNotNullParameter(dir, "dir");
    createDirectories(dir, false);
  }
  
  public void copy(@NotNull Path source, @NotNull Path target) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    -FileSystem.commonCopy(this, source, target);
  }
  
  public final void delete(@NotNull Path path) throws IOException {
    Intrinsics.checkNotNullParameter(path, "path");
    delete(path, false);
  }
  
  public void deleteRecursively(@NotNull Path fileOrDirectory, boolean mustExist) throws IOException {
    Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
    -FileSystem.commonDeleteRecursively(this, fileOrDirectory, mustExist);
  }
  
  public final void deleteRecursively(@NotNull Path fileOrDirectory) throws IOException {
    Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
    deleteRecursively(fileOrDirectory, false);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\023\020\b\032\0020\005*\0020\004H\007¢\006\004\b\006\020\007R\024\020\t\032\0020\0058\006X\004¢\006\006\n\004\b\t\020\nR\024\020\013\032\0020\0058\006X\004¢\006\006\n\004\b\013\020\nR\024\020\r\032\0020\f8\006X\004¢\006\006\n\004\b\r\020\016¨\006\017"}, d2 = {"Lokio/FileSystem$Companion;", "", "<init>", "()V", "Ljava/nio/file/FileSystem;", "Lokio/FileSystem;", "get", "(Ljava/nio/file/FileSystem;)Lokio/FileSystem;", "asOkioFileSystem", "RESOURCES", "Lokio/FileSystem;", "SYSTEM", "Lokio/Path;", "SYSTEM_TEMPORARY_DIRECTORY", "Lokio/Path;", "okio"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    public final FileSystem get(@NotNull java.nio.file.FileSystem $this$asOkioFileSystem) {
      Intrinsics.checkNotNullParameter($this$asOkioFileSystem, "<this>");
      return new NioFileSystemWrappingFileSystem($this$asOkioFileSystem);
    }
  }
  
  static {
    Companion $this$SYSTEM_u24lambda_u242 = Companion = new Companion(null);
    int $i$a$-run-FileSystem$Companion$SYSTEM$1 = 0;
    try {
      Class.forName("java.nio.file.Files");
    } catch (ClassNotFoundException e) {}
  }
  
  @JvmField
  @NotNull
  public static final FileSystem SYSTEM = new JvmSystemFileSystem();
  
  @JvmField
  @NotNull
  public static final Path SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(Path.Companion, System.getProperty("java.io.tmpdir"), false, 1, (Object)null);
  
  static {
    Intrinsics.checkNotNullExpressionValue(System.getProperty("java.io.tmpdir"), "getProperty(...)");
  }
  
  @JvmField
  @NotNull
  public static final FileSystem RESOURCES = (FileSystem)new ResourceFileSystem(ResourceFileSystem.class.getClassLoader(), false, null, 4, null);
  
  static {
    Intrinsics.checkNotNullExpressionValue(ResourceFileSystem.class.getClassLoader(), "getClassLoader(...)");
  }
  
  @JvmName(name = "-read")
  public final <T> T -read(@NotNull Path file, @NotNull Function1 readerAction) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Intrinsics.checkNotNullParameter(readerAction, "readerAction");
    int $i$f$-read = 0;
    Closeable $this$use$iv = Okio.buffer(source(file));
    int $i$f$use = 0;
    Object result$iv = null;
    Throwable thrown$iv = null;
    try {
      BufferedSource it = (BufferedSource)$this$use$iv;
      int $i$a$-use-FileSystem$read$1 = 0;
      result$iv = readerAction.invoke(it);
    } catch (Throwable t$iv) {
      thrown$iv = t$iv;
    } finally {
      InlineMarker.finallyStart(1);
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
      InlineMarker.finallyEnd(1);
    } 
    if (thrown$iv != null)
      throw thrown$iv; 
    Intrinsics.checkNotNull(result$iv);
    return (T)result$iv;
  }
  
  @JvmName(name = "-write")
  public final <T> T -write(@NotNull Path file, boolean mustCreate, @NotNull Function1 writerAction) throws IOException {
    Intrinsics.checkNotNullParameter(file, "file");
    Intrinsics.checkNotNullParameter(writerAction, "writerAction");
    int $i$f$-write = 0;
    Closeable $this$use$iv = Okio.buffer(sink(file, mustCreate));
    int $i$f$use = 0;
    Object result$iv = null;
    Throwable thrown$iv = null;
    try {
      BufferedSink it = (BufferedSink)$this$use$iv;
      int $i$a$-use-FileSystem$write$1 = 0;
      result$iv = writerAction.invoke(it);
    } catch (Throwable t$iv) {
      thrown$iv = t$iv;
    } finally {
      InlineMarker.finallyStart(1);
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
      InlineMarker.finallyEnd(1);
    } 
    if (thrown$iv != null)
      throw thrown$iv; 
    Intrinsics.checkNotNull(result$iv);
    return (T)result$iv;
  }
  
  @NotNull
  public abstract Path canonicalize(@NotNull Path paramPath) throws IOException;
  
  @Nullable
  public abstract FileMetadata metadataOrNull(@NotNull Path paramPath) throws IOException;
  
  @NotNull
  public abstract List<Path> list(@NotNull Path paramPath) throws IOException;
  
  @Nullable
  public abstract List<Path> listOrNull(@NotNull Path paramPath);
  
  @NotNull
  public abstract FileHandle openReadOnly(@NotNull Path paramPath) throws IOException;
  
  @NotNull
  public abstract FileHandle openReadWrite(@NotNull Path paramPath, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
  
  @NotNull
  public abstract Source source(@NotNull Path paramPath) throws IOException;
  
  @NotNull
  public abstract Sink sink(@NotNull Path paramPath, boolean paramBoolean) throws IOException;
  
  @NotNull
  public abstract Sink appendingSink(@NotNull Path paramPath, boolean paramBoolean) throws IOException;
  
  public abstract void createDirectory(@NotNull Path paramPath, boolean paramBoolean) throws IOException;
  
  public abstract void atomicMove(@NotNull Path paramPath1, @NotNull Path paramPath2) throws IOException;
  
  public abstract void delete(@NotNull Path paramPath, boolean paramBoolean) throws IOException;
  
  public abstract void createSymlink(@NotNull Path paramPath1, @NotNull Path paramPath2) throws IOException;
  
  @JvmStatic
  @JvmName(name = "get")
  @NotNull
  public static final FileSystem get(@NotNull java.nio.file.FileSystem $this$get) {
    return Companion.get($this$get);
  }
}
