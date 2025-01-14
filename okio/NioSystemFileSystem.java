package okio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000>\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\016\n\002\b\002\n\002\030\002\n\002\020\t\n\002\b\003\b\020\030\0002\0020\001B\007¢\006\004\b\002\020\003J\037\020\b\032\0020\0072\006\020\005\032\0020\0042\006\020\006\032\0020\004H\026¢\006\004\b\b\020\tJ\037\020\n\032\0020\0072\006\020\005\032\0020\0042\006\020\006\032\0020\004H\026¢\006\004\b\n\020\tJ\031\020\016\032\004\030\0010\r2\006\020\f\032\0020\013H\004¢\006\004\b\016\020\017J\031\020\016\032\004\030\0010\r2\006\020\020\032\0020\004H\026¢\006\004\b\016\020\021J\017\020\023\032\0020\022H\026¢\006\004\b\023\020\024J\025\020\027\032\004\030\0010\026*\0020\025H\002¢\006\004\b\027\020\030¨\006\031"}, d2 = {"Lokio/NioSystemFileSystem;", "Lokio/JvmSystemFileSystem;", "<init>", "()V", "Lokio/Path;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "createSymlink", "Ljava/nio/file/Path;", "nioPath", "Lokio/FileMetadata;", "metadataOrNull", "(Ljava/nio/file/Path;)Lokio/FileMetadata;", "path", "(Lokio/Path;)Lokio/FileMetadata;", "", "toString", "()Ljava/lang/String;", "Ljava/nio/file/attribute/FileTime;", "", "zeroToNull", "(Ljava/nio/file/attribute/FileTime;)Ljava/lang/Long;", "okio"})
@SourceDebugExtension({"SMAP\nNioSystemFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NioSystemFileSystem.kt\nokio/NioSystemFileSystem\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,92:1\n1#2:93\n*E\n"})
public class NioSystemFileSystem extends JvmSystemFileSystem {
  @Nullable
  public FileMetadata metadataOrNull(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    return metadataOrNull(path.toNioPath());
  }
  
  @Nullable
  protected final FileMetadata metadataOrNull(@NotNull Path nioPath) {
    LinkOption[] arrayOfLinkOption2;
    Intrinsics.checkNotNullParameter(nioPath, "nioPath");
    try {
      arrayOfLinkOption2 = new LinkOption[1];
      arrayOfLinkOption2[0] = LinkOption.NOFOLLOW_LINKS;
      arrayOfLinkOption2 = Files.readAttributes(nioPath, BasicFileAttributes.class, arrayOfLinkOption2);
    } catch (NoSuchFileException _) {
      return null;
    } catch (FileSystemException _) {
      return null;
    } 
    LinkOption[] arrayOfLinkOption1 = arrayOfLinkOption2;
    Path symlinkTarget = arrayOfLinkOption1.isSymbolicLink() ? 
      Files.readSymbolicLink(nioPath) : 
      
      null;
    arrayOfLinkOption1.creationTime();
    arrayOfLinkOption1.lastModifiedTime();
    arrayOfLinkOption1.lastAccessTime();
    return new FileMetadata(arrayOfLinkOption1.isRegularFile(), arrayOfLinkOption1.isDirectory(), (symlinkTarget != null) ? Path.Companion.get$default(Path.Companion, symlinkTarget, false, 1, (Object)null) : null, Long.valueOf(arrayOfLinkOption1.size()), (arrayOfLinkOption1.creationTime() != null) ? zeroToNull(arrayOfLinkOption1.creationTime()) : null, (arrayOfLinkOption1.lastModifiedTime() != null) ? zeroToNull(arrayOfLinkOption1.lastModifiedTime()) : null, (arrayOfLinkOption1.lastAccessTime() != null) ? zeroToNull(arrayOfLinkOption1.lastAccessTime()) : null, null, 128, null);
  }
  
  private final Long zeroToNull(FileTime $this$zeroToNull) {
    Long long_ = Long.valueOf($this$zeroToNull.toMillis());
    long it = long_.longValue();
    int $i$a$-takeIf-NioSystemFileSystem$zeroToNull$1 = 0;
    return ((it != 0L)) ? long_ : null;
  }
  
  public void atomicMove(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    try {
      CopyOption[] arrayOfCopyOption = new CopyOption[2];
      arrayOfCopyOption[0] = StandardCopyOption.ATOMIC_MOVE;
      arrayOfCopyOption[1] = StandardCopyOption.REPLACE_EXISTING;
      Files.move(source.toNioPath(), target.toNioPath(), arrayOfCopyOption);
    } catch (NoSuchFileException e) {
      throw new FileNotFoundException(e.getMessage());
    } catch (UnsupportedOperationException e) {
      throw new IOException("atomic move not supported");
    } 
  }
  
  public void createSymlink(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    Files.createSymbolicLink(source.toNioPath(), target.toNioPath(), (FileAttribute<?>[])new FileAttribute[0]);
  }
  
  @NotNull
  public String toString() {
    return "NioSystemFileSystem";
  }
}
