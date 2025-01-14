package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\002\b\004\b&\030\000 \0232\0020\001:\001\023B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006J\021\020\b\032\004\030\0010\007H&¢\006\004\b\b\020\tJ\017\020\013\032\0020\nH\026¢\006\004\b\013\020\fJ\017\020\r\032\0020\nH\026¢\006\004\b\r\020\fJ\027\020\021\032\0020\0202\006\020\017\032\0020\016H&¢\006\004\b\021\020\022¨\006\024"}, d2 = {"Lokhttp3/RequestBody;", "", "<init>", "()V", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "", "isDuplex", "()Z", "isOneShot", "Lokio/BufferedSink;", "sink", "", "writeTo", "(Lokio/BufferedSink;)V", "Companion", "okhttp"})
public abstract class RequestBody {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  public abstract MediaType contentType();
  
  public long contentLength() throws IOException {
    return -1L;
  }
  
  public abstract void writeTo(@NotNull BufferedSink paramBufferedSink) throws IOException;
  
  public boolean isDuplex() {
    return false;
  }
  
  public boolean isOneShot() {
    return false;
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull String $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull ByteString $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @JvmOverloads
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull byte[] $this$create, @Nullable MediaType contentType, int offset, int byteCount) {
    return Companion.create($this$create, contentType, offset, byteCount);
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull File $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
    return Companion.create(contentType, content);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
    return Companion.create(contentType, content);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
  @JvmOverloads
  @NotNull
  public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
    return Companion.create(contentType, content, offset, byteCount);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
    return Companion.create(contentType, file);
  }
  
  @JvmStatic
  @JvmOverloads
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull byte[] $this$create, @Nullable MediaType contentType, int offset) {
    return Companion.create($this$create, contentType, offset);
  }
  
  @JvmStatic
  @JvmOverloads
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull byte[] $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @JvmOverloads
  @JvmName(name = "create")
  @NotNull
  public static final RequestBody create(@NotNull byte[] $this$create) {
    return Companion.create($this$create);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
  @JvmOverloads
  @NotNull
  public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
    return Companion.create(contentType, content, offset);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
  @JvmOverloads
  @NotNull
  public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
    return Companion.create(contentType, content);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\022\n\000\n\002\020\b\n\002\b\003\n\002\020\016\n\000\n\002\030\002\n\002\b\b\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\006H\007¢\006\004\b\t\020\nJ5\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\f\032\0020\0132\b\b\002\020\016\032\0020\r2\b\b\002\020\017\032\0020\rH\007¢\006\004\b\t\020\020J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\f\032\0020\021H\007¢\006\004\b\t\020\022J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\f\032\0020\023H\007¢\006\004\b\t\020\024J\037\020\026\032\0020\b*\0020\0062\n\b\002\020\005\032\004\030\0010\004H\007¢\006\004\b\t\020\025J3\020\030\032\0020\b*\0020\0132\n\b\002\020\005\032\004\030\0010\0042\b\b\002\020\016\032\0020\r2\b\b\002\020\017\032\0020\rH\007¢\006\004\b\t\020\027J\037\020\030\032\0020\b*\0020\0212\n\b\002\020\005\032\004\030\0010\004H\007¢\006\004\b\t\020\031J\037\020\030\032\0020\b*\0020\0232\n\b\002\020\005\032\004\030\0010\004H\007¢\006\004\b\t\020\032¨\006\033"}, d2 = {"Lokhttp3/RequestBody$Companion;", "", "<init>", "()V", "Lokhttp3/MediaType;", "contentType", "Ljava/io/File;", "file", "Lokhttp3/RequestBody;", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", "", "content", "", "offset", "byteCount", "(Lokhttp3/MediaType;[BII)Lokhttp3/RequestBody;", "", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", "Lokio/ByteString;", "(Lokhttp3/MediaType;Lokio/ByteString;)Lokhttp3/RequestBody;", "(Ljava/io/File;Lokhttp3/MediaType;)Lokhttp3/RequestBody;", "asRequestBody", "([BLokhttp3/MediaType;II)Lokhttp3/RequestBody;", "toRequestBody", "(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;", "(Lokio/ByteString;Lokhttp3/MediaType;)Lokhttp3/RequestBody;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull String $this$toRequestBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$toRequestBody, "<this>");
      Charset charset = Charsets.UTF_8;
      MediaType finalContentType = contentType;
      if (contentType != null) {
        Charset resolvedCharset = MediaType.charset$default(contentType, null, 1, null);
        if (resolvedCharset == null) {
          charset = Charsets.UTF_8;
          finalContentType = MediaType.Companion.parse(contentType + "; charset=utf-8");
        } else {
          charset = resolvedCharset;
        } 
      } 
      Intrinsics.checkNotNullExpressionValue($this$toRequestBody.getBytes(charset), "this as java.lang.String).getBytes(charset)");
      byte[] bytes = $this$toRequestBody.getBytes(charset);
      return create(bytes, finalContentType, 0, bytes.length);
    }
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull ByteString $this$toRequestBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$toRequestBody, "<this>");
      return new RequestBody$Companion$toRequestBody$1(contentType, $this$toRequestBody);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\021\020\006\032\004\030\0010\005H\026¢\006\004\b\006\020\007J\027\020\013\032\0020\n2\006\020\t\032\0020\bH\026¢\006\004\b\013\020\f¨\006\r"}, d2 = {"okhttp3/RequestBody.Companion.toRequestBody.1", "Lokhttp3/RequestBody;", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "Lokio/BufferedSink;", "sink", "", "writeTo", "(Lokio/BufferedSink;)V", "okhttp"})
    public static final class RequestBody$Companion$toRequestBody$1 extends RequestBody {
      RequestBody$Companion$toRequestBody$1(MediaType $contentType, ByteString $receiver) {}
      
      @Nullable
      public MediaType contentType() {
        return this.$contentType;
      }
      
      public long contentLength() {
        return this.$this_toRequestBody.size();
      }
      
      public void writeTo(@NotNull BufferedSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        sink.write(this.$this_toRequestBody);
      }
    }
    
    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter($this$toRequestBody, "<this>");
      Util.checkOffsetAndCount($this$toRequestBody.length, offset, byteCount);
      return new RequestBody$Companion$toRequestBody$2(contentType, byteCount, $this$toRequestBody, offset);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\021\020\006\032\004\030\0010\005H\026¢\006\004\b\006\020\007J\027\020\013\032\0020\n2\006\020\t\032\0020\bH\026¢\006\004\b\013\020\f¨\006\r"}, d2 = {"okhttp3/RequestBody.Companion.toRequestBody.2", "Lokhttp3/RequestBody;", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "Lokio/BufferedSink;", "sink", "", "writeTo", "(Lokio/BufferedSink;)V", "okhttp"})
    public static final class RequestBody$Companion$toRequestBody$2 extends RequestBody {
      RequestBody$Companion$toRequestBody$2(MediaType $contentType, int $byteCount, byte[] $receiver, int $offset) {}
      
      @Nullable
      public MediaType contentType() {
        return this.$contentType;
      }
      
      public long contentLength() {
        return this.$byteCount;
      }
      
      public void writeTo(@NotNull BufferedSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        sink.write(this.$this_toRequestBody, this.$offset, this.$byteCount);
      }
    }
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull File $this$asRequestBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$asRequestBody, "<this>");
      return new RequestBody$Companion$asRequestBody$1(contentType, $this$asRequestBody);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\021\020\006\032\004\030\0010\005H\026¢\006\004\b\006\020\007J\027\020\013\032\0020\n2\006\020\t\032\0020\bH\026¢\006\004\b\013\020\f¨\006\r"}, d2 = {"okhttp3/RequestBody.Companion.asRequestBody.1", "Lokhttp3/RequestBody;", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "Lokio/BufferedSink;", "sink", "", "writeTo", "(Lokio/BufferedSink;)V", "okhttp"})
    @SourceDebugExtension({"SMAP\nRequestBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestBody.kt\nokhttp3/RequestBody$Companion$asRequestBody$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,222:1\n1#2:223\n*E\n"})
    public static final class RequestBody$Companion$asRequestBody$1 extends RequestBody {
      RequestBody$Companion$asRequestBody$1(MediaType $contentType, File $receiver) {}
      
      @Nullable
      public MediaType contentType() {
        return this.$contentType;
      }
      
      public long contentLength() {
        return this.$this_asRequestBody.length();
      }
      
      public void writeTo(@NotNull BufferedSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Closeable closeable = (Closeable)Okio.source(this.$this_asRequestBody);
        Throwable throwable = null;
        try {
          Source source = (Source)closeable;
          int $i$a$-use-RequestBody$Companion$asRequestBody$1$writeTo$1 = 0;
          long l = sink.writeAll(source);
        } catch (Throwable throwable1) {
          throwable = throwable1 = null;
          throw throwable1;
        } finally {
          CloseableKt.closeFinally(closeable, throwable);
        } 
      }
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
    @JvmOverloads
    @NotNull
    public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType, offset, byteCount);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      return create(file, contentType);
    }
    
    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) {
      Intrinsics.checkNotNullParameter($this$toRequestBody, "<this>");
      return create$default(this, $this$toRequestBody, contentType, offset, 0, 4, (Object)null);
    }
    
    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$toRequestBody, "<this>");
      return create$default(this, $this$toRequestBody, contentType, 0, 0, 6, (Object)null);
    }
    
    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    @NotNull
    public final RequestBody create(@NotNull byte[] $this$toRequestBody) {
      Intrinsics.checkNotNullParameter($this$toRequestBody, "<this>");
      return create$default(this, $this$toRequestBody, (MediaType)null, 0, 0, 7, (Object)null);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
    @JvmOverloads
    @NotNull
    public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create$default(this, contentType, content, offset, 0, 8, (Object)null);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}), level = DeprecationLevel.WARNING)
    @JvmOverloads
    @NotNull
    public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create$default(this, contentType, content, 0, 0, 12, (Object)null);
    }
  }
}
