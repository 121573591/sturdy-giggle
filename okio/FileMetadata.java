package okio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\020$\n\002\030\002\n\002\b\t\n\002\020\016\n\002\b\021\030\0002\0020\001Bq\022\b\b\002\020\003\032\0020\002\022\b\b\002\020\004\032\0020\002\022\n\b\002\020\006\032\004\030\0010\005\022\n\b\002\020\b\032\004\030\0010\007\022\n\b\002\020\t\032\004\030\0010\007\022\n\b\002\020\n\032\004\030\0010\007\022\n\b\002\020\013\032\004\030\0010\007\022\030\b\002\020\016\032\022\022\b\022\006\022\002\b\0030\r\022\004\022\0020\0010\f¢\006\004\b\017\020\020Jw\020\021\032\0020\0002\b\b\002\020\003\032\0020\0022\b\b\002\020\004\032\0020\0022\n\b\002\020\006\032\004\030\0010\0052\n\b\002\020\b\032\004\030\0010\0072\n\b\002\020\t\032\004\030\0010\0072\n\b\002\020\n\032\004\030\0010\0072\n\b\002\020\013\032\004\030\0010\0072\030\b\002\020\016\032\022\022\b\022\006\022\002\b\0030\r\022\004\022\0020\0010\f¢\006\004\b\021\020\022J)\020\025\032\004\030\0018\000\"\b\b\000\020\023*\0020\0012\016\020\024\032\n\022\006\b\001\022\0028\0000\r¢\006\004\b\025\020\026J\017\020\030\032\0020\027H\026¢\006\004\b\030\020\031R\031\020\t\032\004\030\0010\0078\006¢\006\f\n\004\b\t\020\032\032\004\b\033\020\034R'\020\016\032\022\022\b\022\006\022\002\b\0030\r\022\004\022\0020\0010\f8\006¢\006\f\n\004\b\016\020\035\032\004\b\036\020\037R\027\020\004\032\0020\0028\006¢\006\f\n\004\b\004\020 \032\004\b\004\020!R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020 \032\004\b\003\020!R\031\020\013\032\004\030\0010\0078\006¢\006\f\n\004\b\013\020\032\032\004\b\"\020\034R\031\020\n\032\004\030\0010\0078\006¢\006\f\n\004\b\n\020\032\032\004\b#\020\034R\031\020\b\032\004\030\0010\0078\006¢\006\f\n\004\b\b\020\032\032\004\b$\020\034R\031\020\006\032\004\030\0010\0058\006¢\006\f\n\004\b\006\020%\032\004\b&\020'¨\006("}, d2 = {"Lokio/FileMetadata;", "", "", "isRegularFile", "isDirectory", "Lokio/Path;", "symlinkTarget", "", "size", "createdAtMillis", "lastModifiedAtMillis", "lastAccessedAtMillis", "", "Lkotlin/reflect/KClass;", "extras", "<init>", "(ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;)V", "copy", "(ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;)Lokio/FileMetadata;", "T", "type", "extra", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "Ljava/lang/Long;", "getCreatedAtMillis", "()Ljava/lang/Long;", "Ljava/util/Map;", "getExtras", "()Ljava/util/Map;", "Z", "()Z", "getLastAccessedAtMillis", "getLastModifiedAtMillis", "getSize", "Lokio/Path;", "getSymlinkTarget", "()Lokio/Path;", "okio"})
public final class FileMetadata {
  private final boolean isRegularFile;
  
  private final boolean isDirectory;
  
  @Nullable
  private final Path symlinkTarget;
  
  @Nullable
  private final Long size;
  
  @Nullable
  private final Long createdAtMillis;
  
  @Nullable
  private final Long lastModifiedAtMillis;
  
  @Nullable
  private final Long lastAccessedAtMillis;
  
  @NotNull
  private final Map<KClass<?>, Object> extras;
  
  public FileMetadata(boolean isRegularFile, boolean isDirectory, @Nullable Path symlinkTarget, @Nullable Long size, @Nullable Long createdAtMillis, @Nullable Long lastModifiedAtMillis, @Nullable Long lastAccessedAtMillis, @NotNull Map extras) {
    this.isRegularFile = isRegularFile;
    this.isDirectory = isDirectory;
    this.symlinkTarget = symlinkTarget;
    this.size = size;
    this.createdAtMillis = createdAtMillis;
    this.lastModifiedAtMillis = lastModifiedAtMillis;
    this.lastAccessedAtMillis = lastAccessedAtMillis;
    this.extras = MapsKt.toMap(extras);
  }
  
  public final boolean isRegularFile() {
    return this.isRegularFile;
  }
  
  public final boolean isDirectory() {
    return this.isDirectory;
  }
  
  @Nullable
  public final Path getSymlinkTarget() {
    return this.symlinkTarget;
  }
  
  @Nullable
  public final Long getSize() {
    return this.size;
  }
  
  @Nullable
  public final Long getCreatedAtMillis() {
    return this.createdAtMillis;
  }
  
  @Nullable
  public final Long getLastModifiedAtMillis() {
    return this.lastModifiedAtMillis;
  }
  
  @Nullable
  public final Long getLastAccessedAtMillis() {
    return this.lastAccessedAtMillis;
  }
  
  @NotNull
  public final Map<KClass<?>, Object> getExtras() {
    return this.extras;
  }
  
  @Nullable
  public final <T> T extra(@NotNull KClass type) {
    Object value;
    Intrinsics.checkNotNullParameter(type, "type");
    if (this.extras.get(type) == null) {
      this.extras.get(type);
      return null;
    } 
    return (T)KClasses.cast(type, value);
  }
  
  @NotNull
  public final FileMetadata copy(boolean isRegularFile, boolean isDirectory, @Nullable Path symlinkTarget, @Nullable Long size, @Nullable Long createdAtMillis, @Nullable Long lastModifiedAtMillis, @Nullable Long lastAccessedAtMillis, @NotNull Map<KClass<?>, ? extends Object> extras) {
    Intrinsics.checkNotNullParameter(extras, "extras");
    return new FileMetadata(
        isRegularFile, 
        isDirectory, 
        symlinkTarget, 
        size, 
        createdAtMillis, 
        
        lastModifiedAtMillis, lastAccessedAtMillis, 
        extras);
  }
  
  @NotNull
  public String toString() {
    List<String> fields = new ArrayList();
    if (this.isRegularFile)
      fields.add("isRegularFile"); 
    if (this.isDirectory)
      fields.add("isDirectory"); 
    if (this.size != null)
      fields.add("byteCount=" + this.size); 
    if (this.createdAtMillis != null)
      fields.add("createdAt=" + this.createdAtMillis); 
    if (this.lastModifiedAtMillis != null)
      fields.add("lastModifiedAt=" + this.lastModifiedAtMillis); 
    if (this.lastAccessedAtMillis != null)
      fields.add("lastAccessedAt=" + this.lastAccessedAtMillis); 
    if (!this.extras.isEmpty())
      fields.add("extras=" + this.extras); 
    return CollectionsKt.joinToString$default(fields, ", ", "FileMetadata(", ")", 0, null, null, 56, null);
  }
  
  public FileMetadata() {
    this(false, false, null, null, null, null, null, null, 255, null);
  }
}
