package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okio.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\016\n\000\n\002\020\t\n\002\b\003\n\002\020\b\n\002\b\b\n\002\020!\n\002\b\026\b\000\030\0002\0020\001Ba\022\006\020\003\032\0020\002\022\b\b\002\020\005\032\0020\004\022\b\b\002\020\007\032\0020\006\022\b\b\002\020\t\032\0020\b\022\b\b\002\020\n\032\0020\b\022\b\b\002\020\013\032\0020\b\022\b\b\002\020\r\032\0020\f\022\n\b\002\020\016\032\004\030\0010\b\022\b\b\002\020\017\032\0020\b¢\006\004\b\020\020\021R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\022\032\004\b\023\020\024R\035\020\026\032\b\022\004\022\0020\0020\0258\006¢\006\f\n\004\b\026\020\027\032\004\b\030\020\031R\027\020\007\032\0020\0068\006¢\006\f\n\004\b\007\020\032\032\004\b\033\020\034R\027\020\n\032\0020\b8\006¢\006\f\n\004\b\n\020\035\032\004\b\036\020\037R\027\020\r\032\0020\f8\006¢\006\f\n\004\b\r\020 \032\004\b!\020\"R\027\020\t\032\0020\b8\006¢\006\f\n\004\b\t\020\035\032\004\b#\020\037R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020$\032\004\b\005\020%R\031\020\016\032\004\030\0010\b8\006¢\006\f\n\004\b\016\020&\032\004\b'\020(R\027\020\017\032\0020\b8\006¢\006\f\n\004\b\017\020\035\032\004\b)\020\037R\027\020\013\032\0020\b8\006¢\006\f\n\004\b\013\020\035\032\004\b*\020\037¨\006+"}, d2 = {"Lokio/internal/ZipEntry;", "", "Lokio/Path;", "canonicalPath", "", "isDirectory", "", "comment", "", "crc", "compressedSize", "size", "", "compressionMethod", "lastModifiedAtMillis", "offset", "<init>", "(Lokio/Path;ZLjava/lang/String;JJJILjava/lang/Long;J)V", "Lokio/Path;", "getCanonicalPath", "()Lokio/Path;", "", "children", "Ljava/util/List;", "getChildren", "()Ljava/util/List;", "Ljava/lang/String;", "getComment", "()Ljava/lang/String;", "J", "getCompressedSize", "()J", "I", "getCompressionMethod", "()I", "getCrc", "Z", "()Z", "Ljava/lang/Long;", "getLastModifiedAtMillis", "()Ljava/lang/Long;", "getOffset", "getSize", "okio"})
public final class ZipEntry {
  @NotNull
  private final Path canonicalPath;
  
  private final boolean isDirectory;
  
  @NotNull
  private final String comment;
  
  private final long crc;
  
  private final long compressedSize;
  
  private final long size;
  
  private final int compressionMethod;
  
  @Nullable
  private final Long lastModifiedAtMillis;
  
  private final long offset;
  
  @NotNull
  private final List<Path> children;
  
  public ZipEntry(@NotNull Path canonicalPath, boolean isDirectory, @NotNull String comment, long crc, long compressedSize, long size, int compressionMethod, @Nullable Long lastModifiedAtMillis, long offset) {
    this.canonicalPath = canonicalPath;
    this.isDirectory = isDirectory;
    this.comment = comment;
    this.crc = crc;
    this.compressedSize = compressedSize;
    this.size = size;
    this.compressionMethod = compressionMethod;
    this.lastModifiedAtMillis = lastModifiedAtMillis;
    this.offset = offset;
    this.children = new ArrayList<>();
  }
  
  @NotNull
  public final Path getCanonicalPath() {
    return this.canonicalPath;
  }
  
  public final boolean isDirectory() {
    return this.isDirectory;
  }
  
  @NotNull
  public final String getComment() {
    return this.comment;
  }
  
  public final long getCrc() {
    return this.crc;
  }
  
  public final long getCompressedSize() {
    return this.compressedSize;
  }
  
  public final long getSize() {
    return this.size;
  }
  
  public final int getCompressionMethod() {
    return this.compressionMethod;
  }
  
  @Nullable
  public final Long getLastModifiedAtMillis() {
    return this.lastModifiedAtMillis;
  }
  
  public final long getOffset() {
    return this.offset;
  }
  
  @NotNull
  public final List<Path> getChildren() {
    return this.children;
  }
}
