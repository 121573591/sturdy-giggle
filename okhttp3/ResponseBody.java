package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000j\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\022\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\000\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\003\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\016\n\002\b\007\b&\030\000 +2\0020\001:\002,+B\007¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\006J\r\020\b\032\0020\007¢\006\004\b\b\020\tJ\r\020\013\032\0020\n¢\006\004\b\013\020\fJ\r\020\016\032\0020\r¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\002¢\006\004\b\021\020\022J\017\020\024\032\0020\023H\026¢\006\004\b\024\020\003JB\020\034\032\0028\000\"\b\b\000\020\026*\0020\0252\022\020\031\032\016\022\004\022\0020\030\022\004\022\0028\0000\0272\022\020\033\032\016\022\004\022\0028\000\022\004\022\0020\0320\027H\b¢\006\004\b\034\020\035J\017\020\037\032\0020\036H&¢\006\004\b\037\020 J\021\020\"\032\004\030\0010!H&¢\006\004\b\"\020#J\017\020$\032\0020\030H&¢\006\004\b$\020%J\r\020'\032\0020&¢\006\004\b'\020(R\030\020)\032\004\030\0010\r8\002@\002X\016¢\006\006\n\004\b)\020*¨\006-"}, d2 = {"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "<init>", "()V", "Ljava/io/InputStream;", "byteStream", "()Ljava/io/InputStream;", "Lokio/ByteString;", "byteString", "()Lokio/ByteString;", "", "bytes", "()[B", "Ljava/io/Reader;", "charStream", "()Ljava/io/Reader;", "Ljava/nio/charset/Charset;", "charset", "()Ljava/nio/charset/Charset;", "", "close", "", "T", "Lkotlin/Function1;", "Lokio/BufferedSource;", "consumer", "", "sizeMapper", "consumeSource", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "source", "()Lokio/BufferedSource;", "", "string", "()Ljava/lang/String;", "reader", "Ljava/io/Reader;", "Companion", "BomAwareReader", "okhttp"})
@SourceDebugExtension({"SMAP\nResponseBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResponseBody.kt\nokhttp3/ResponseBody\n*L\n1#1,321:1\n140#1,11:322\n140#1,11:333\n*S KotlinDebug\n*F\n+ 1 ResponseBody.kt\nokhttp3/ResponseBody\n*L\n124#1:322,11\n134#1:333,11\n*E\n"})
public abstract class ResponseBody implements Closeable {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private Reader reader;
  
  @Nullable
  public abstract MediaType contentType();
  
  public abstract long contentLength();
  
  @NotNull
  public final InputStream byteStream() {
    return source().inputStream();
  }
  
  @NotNull
  public abstract BufferedSource source();
  
  @NotNull
  public final byte[] bytes() throws IOException {
    ResponseBody this_$iv = this;
    int $i$f$consumeSource = 0;
    long contentLength$iv = this_$iv.contentLength();
    if (contentLength$iv > 2147483647L)
      throw new IOException("Cannot buffer entire body for content length: " + contentLength$iv); 
    Closeable closeable = (Closeable)this_$iv.source();
    Throwable throwable = null;
    try {
      BufferedSource p0 = (BufferedSource)closeable;
      int $i$a$-consumeSource-ResponseBody$bytes$1 = 0;
      byte[] arrayOfByte = p0.readByteArray();
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
    Object bytes$iv = arrayOfByte;
    Object object1 = bytes$iv;
    int $i$a$-consumeSource-ResponseBody$bytes$2 = 0, size$iv = object1.length;
    if (contentLength$iv != -1L && contentLength$iv != size$iv)
      throw new IOException("Content-Length (" + contentLength$iv + ") and stream length (" + size$iv + ") disagree"); 
    return (byte[])bytes$iv;
  }
  
  @NotNull
  public final ByteString byteString() throws IOException {
    ResponseBody this_$iv = this;
    int $i$f$consumeSource = 0;
    long contentLength$iv = this_$iv.contentLength();
    if (contentLength$iv > 2147483647L)
      throw new IOException("Cannot buffer entire body for content length: " + contentLength$iv); 
    Closeable closeable = (Closeable)this_$iv.source();
    Throwable throwable = null;
    try {
      BufferedSource p0 = (BufferedSource)closeable;
      int $i$a$-consumeSource-ResponseBody$byteString$1 = 0;
      ByteString byteString = p0.readByteString();
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
    Object bytes$iv = byteString;
    Object object1 = bytes$iv;
    int $i$a$-consumeSource-ResponseBody$byteString$2 = 0, size$iv = object1.size();
    if (contentLength$iv != -1L && contentLength$iv != size$iv)
      throw new IOException("Content-Length (" + contentLength$iv + ") and stream length (" + size$iv + ") disagree"); 
    return (ByteString)bytes$iv;
  }
  
  private final <T> T consumeSource(Function1 consumer, Function1 sizeMapper) {
    int $i$f$consumeSource = 0;
    long contentLength = contentLength();
    if (contentLength > 2147483647L)
      throw new IOException("Cannot buffer entire body for content length: " + contentLength); 
    Closeable closeable = (Closeable)source();
    Throwable throwable = null;
    try {
      Object object = consumer.invoke(closeable);
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(closeable, throwable);
      InlineMarker.finallyEnd(1);
    } 
    Object bytes = object;
    int size = ((Number)sizeMapper.invoke(bytes)).intValue();
    if (contentLength != -1L && contentLength != size)
      throw new IOException("Content-Length (" + contentLength + ") and stream length (" + size + ") disagree"); 
    return (T)bytes;
  }
  
  @NotNull
  public final Reader charStream() {
    if (this.reader == null) {
      BomAwareReader bomAwareReader1 = new BomAwareReader(source(), charset()), it = bomAwareReader1;
      int $i$a$-also-ResponseBody$charStream$1 = 0;
      this.reader = it;
    } 
    return bomAwareReader1;
  }
  
  @NotNull
  public final String string() throws IOException {
    Closeable closeable = (Closeable)source();
    Throwable throwable = null;
    try {
      BufferedSource source = (BufferedSource)closeable;
      int $i$a$-use-ResponseBody$string$1 = 0;
      String str = source.readString(Util.readBomAsCharset(source, charset()));
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
    return str;
  }
  
  private final Charset charset() {
    if (contentType() == null || contentType().charset(Charsets.UTF_8) == null)
      contentType().charset(Charsets.UTF_8); 
    return Charsets.UTF_8;
  }
  
  public void close() {
    Util.closeQuietly((Closeable)source());
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final ResponseBody create(@NotNull String $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final ResponseBody create(@NotNull byte[] $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final ResponseBody create(@NotNull ByteString $this$create, @Nullable MediaType contentType) {
    return Companion.create($this$create, contentType);
  }
  
  @JvmStatic
  @JvmName(name = "create")
  @NotNull
  public static final ResponseBody create(@NotNull BufferedSource $this$create, @Nullable MediaType contentType, long contentLength) {
    return Companion.create($this$create, contentType, contentLength);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final ResponseBody create(@Nullable MediaType contentType, @NotNull String content) {
    return Companion.create(contentType, content);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final ResponseBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
    return Companion.create(contentType, content);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final ResponseBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
    return Companion.create(contentType, content);
  }
  
  @JvmStatic
  @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}), level = DeprecationLevel.WARNING)
  @NotNull
  public static final ResponseBody create(@Nullable MediaType contentType, long contentLength, @NotNull BufferedSource content) {
    return Companion.create(contentType, contentLength, content);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\031\n\000\n\002\020\b\n\002\b\005\n\002\020\013\n\002\b\006\b\000\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ'\020\020\032\0020\r2\006\020\f\032\0020\0132\006\020\016\032\0020\r2\006\020\017\032\0020\rH\026¢\006\004\b\020\020\021R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\022R\026\020\024\032\0020\0238\002@\002X\016¢\006\006\n\004\b\024\020\025R\030\020\026\032\004\030\0010\0018\002@\002X\016¢\006\006\n\004\b\026\020\027R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\030¨\006\031"}, d2 = {"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "Lokio/BufferedSource;", "source", "Ljava/nio/charset/Charset;", "charset", "<init>", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "", "close", "()V", "", "cbuf", "", "off", "len", "read", "([CII)I", "Ljava/nio/charset/Charset;", "", "closed", "Z", "delegate", "Ljava/io/Reader;", "Lokio/BufferedSource;", "okhttp"})
  @SourceDebugExtension({"SMAP\nResponseBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResponseBody.kt\nokhttp3/ResponseBody$BomAwareReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,321:1\n1#2:322\n*E\n"})
  public static final class BomAwareReader extends Reader {
    @NotNull
    private final BufferedSource source;
    
    @NotNull
    private final Charset charset;
    
    private boolean closed;
    
    @Nullable
    private Reader delegate;
    
    public BomAwareReader(@NotNull BufferedSource source, @NotNull Charset charset) {
      this.source = source;
      this.charset = charset;
    }
    
    public int read(@NotNull char[] cbuf, int off, int len) throws IOException {
      Intrinsics.checkNotNullParameter(cbuf, "cbuf");
      if (this.closed)
        throw new IOException("Stream closed"); 
      if (this.delegate == null) {
        InputStreamReader inputStreamReader1 = new InputStreamReader(this.source.inputStream(), Util.readBomAsCharset(this.source, this.charset));
        InputStreamReader it = inputStreamReader1;
        int $i$a$-also-ResponseBody$BomAwareReader$read$finalDelegate$1 = 0;
        this.delegate = it;
      } 
      Reader finalDelegate = inputStreamReader1;
      return finalDelegate.read(cbuf, off, len);
    }
    
    public void close() throws IOException {
      this.closed = true;
      this.delegate.close();
      if (((this.delegate != null) ? Unit.INSTANCE : null) == null) {
        BomAwareReader $this$close_u24lambda_u241 = this;
        int $i$a$-run-ResponseBody$BomAwareReader$close$1 = 0;
        $this$close_u24lambda_u241.source.close();
      } else {
      
      } 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\022\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\b\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\006H\007¢\006\004\b\t\020\nJ)\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\f\032\0020\0132\006\020\007\032\0020\rH\007¢\006\004\b\t\020\016J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\017H\007¢\006\004\b\t\020\020J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\021H\007¢\006\004\b\t\020\022J)\020\024\032\0020\b*\0020\r2\n\b\002\020\005\032\004\030\0010\0042\b\b\002\020\f\032\0020\013H\007¢\006\004\b\t\020\023J\037\020\026\032\0020\b*\0020\0062\n\b\002\020\005\032\004\030\0010\004H\007¢\006\004\b\t\020\025J\037\020\026\032\0020\b*\0020\0172\n\b\002\020\005\032\004\030\0010\004H\007¢\006\004\b\t\020\027J\037\020\026\032\0020\b*\0020\0212\n\b\002\020\005\032\004\030\0010\004H\007¢\006\004\b\t\020\030¨\006\031"}, d2 = {"Lokhttp3/ResponseBody$Companion;", "", "<init>", "()V", "Lokhttp3/MediaType;", "contentType", "", "content", "Lokhttp3/ResponseBody;", "create", "(Lokhttp3/MediaType;[B)Lokhttp3/ResponseBody;", "", "contentLength", "Lokio/BufferedSource;", "(Lokhttp3/MediaType;JLokio/BufferedSource;)Lokhttp3/ResponseBody;", "", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/ResponseBody;", "Lokio/ByteString;", "(Lokhttp3/MediaType;Lokio/ByteString;)Lokhttp3/ResponseBody;", "(Lokio/BufferedSource;Lokhttp3/MediaType;J)Lokhttp3/ResponseBody;", "asResponseBody", "([BLokhttp3/MediaType;)Lokhttp3/ResponseBody;", "toResponseBody", "(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/ResponseBody;", "(Lokio/ByteString;Lokhttp3/MediaType;)Lokhttp3/ResponseBody;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final ResponseBody create(@NotNull String $this$toResponseBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$toResponseBody, "<this>");
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
      Buffer buffer = (new Buffer()).writeString($this$toResponseBody, charset);
      return create((BufferedSource)buffer, finalContentType, buffer.size());
    }
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final ResponseBody create(@NotNull byte[] $this$toResponseBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$toResponseBody, "<this>");
      return create((BufferedSource)(new Buffer()).write($this$toResponseBody), contentType, $this$toResponseBody.length);
    }
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final ResponseBody create(@NotNull ByteString $this$toResponseBody, @Nullable MediaType contentType) {
      Intrinsics.checkNotNullParameter($this$toResponseBody, "<this>");
      return create((BufferedSource)(new Buffer()).write($this$toResponseBody), contentType, $this$toResponseBody.size());
    }
    
    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public final ResponseBody create(@NotNull BufferedSource $this$asResponseBody, @Nullable MediaType contentType, long contentLength) {
      Intrinsics.checkNotNullParameter($this$asResponseBody, "<this>");
      return new ResponseBody$Companion$asResponseBody$1(contentType, contentLength, $this$asResponseBody);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000!\n\000\n\002\030\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\021\020\006\032\004\030\0010\005H\026¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\n¨\006\013"}, d2 = {"okhttp3/ResponseBody.Companion.asResponseBody.1", "Lokhttp3/ResponseBody;", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "Lokio/BufferedSource;", "source", "()Lokio/BufferedSource;", "okhttp"})
    public static final class ResponseBody$Companion$asResponseBody$1 extends ResponseBody {
      ResponseBody$Companion$asResponseBody$1(MediaType $contentType, long $contentLength, BufferedSource $receiver) {}
      
      @Nullable
      public MediaType contentType() {
        return this.$contentType;
      }
      
      public long contentLength() {
        return this.$contentLength;
      }
      
      @NotNull
      public BufferedSource source() {
        return this.$this_asResponseBody;
      }
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final ResponseBody create(@Nullable MediaType contentType, @NotNull String content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final ResponseBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final ResponseBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType);
    }
    
    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}), level = DeprecationLevel.WARNING)
    @NotNull
    public final ResponseBody create(@Nullable MediaType contentType, long contentLength, @NotNull BufferedSource content) {
      Intrinsics.checkNotNullParameter(content, "content");
      return create(content, contentType, contentLength);
    }
  }
}
