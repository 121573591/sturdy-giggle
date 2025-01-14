package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\003\n\002\020\t\n\002\b\004\n\002\020\b\n\002\b\t\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\n\030\000 +2\0020\001:\003,+-B'\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\f\020\b\032\b\022\004\022\0020\0070\006¢\006\004\b\t\020\nJ\017\020\016\032\0020\013H\007¢\006\004\b\f\020\rJ\017\020\020\032\0020\017H\026¢\006\004\b\020\020\021J\017\020\022\032\0020\004H\026¢\006\004\b\022\020\023J\025\020\026\032\0020\0072\006\020\025\032\0020\024¢\006\004\b\026\020\027J\025\020\b\032\b\022\004\022\0020\0070\006H\007¢\006\004\b\030\020\031J\017\020\034\032\0020\024H\007¢\006\004\b\032\020\033J\017\020\005\032\0020\004H\007¢\006\004\b\035\020\023J!\020\"\032\0020\0172\b\020\037\032\004\030\0010\0362\006\020!\032\0020 H\002¢\006\004\b\"\020#J\027\020%\032\0020$2\006\020\037\032\0020\036H\026¢\006\004\b%\020&R\021\020\016\032\0020\0138G¢\006\006\032\004\b\016\020\rR\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020'R\026\020\020\032\0020\0178\002@\002X\016¢\006\006\n\004\b\020\020(R\024\020\022\032\0020\0048\002X\004¢\006\006\n\004\b\022\020)R\035\020\b\032\b\022\004\022\0020\0070\0068\007¢\006\f\n\004\b\b\020*\032\004\b\b\020\031R\021\020\034\032\0020\0248G¢\006\006\032\004\b\034\020\033R\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\020)\032\004\b\005\020\023¨\006."}, d2 = {"Lokhttp3/MultipartBody;", "Lokhttp3/RequestBody;", "Lokio/ByteString;", "boundaryByteString", "Lokhttp3/MediaType;", "type", "", "Lokhttp3/MultipartBody$Part;", "parts", "<init>", "(Lokio/ByteString;Lokhttp3/MediaType;Ljava/util/List;)V", "", "-deprecated_boundary", "()Ljava/lang/String;", "boundary", "", "contentLength", "()J", "contentType", "()Lokhttp3/MediaType;", "", "index", "part", "(I)Lokhttp3/MultipartBody$Part;", "-deprecated_parts", "()Ljava/util/List;", "-deprecated_size", "()I", "size", "-deprecated_type", "Lokio/BufferedSink;", "sink", "", "countBytes", "writeOrCountBytes", "(Lokio/BufferedSink;Z)J", "", "writeTo", "(Lokio/BufferedSink;)V", "Lokio/ByteString;", "J", "Lokhttp3/MediaType;", "Ljava/util/List;", "Companion", "Builder", "Part", "okhttp"})
public final class MultipartBody extends RequestBody {
  @JvmName(name = "type")
  @NotNull
  public final MediaType type() {
    return this.type;
  }
  
  @JvmName(name = "parts")
  @NotNull
  public final List<Part> parts() {
    return this.parts;
  }
  
  public MultipartBody(@NotNull ByteString boundaryByteString, @NotNull MediaType type, @NotNull List<Part> parts) {
    this.boundaryByteString = boundaryByteString;
    this.type = type;
    this.parts = parts;
    this.contentType = MediaType.Companion.get(this.type + "; boundary=" + boundary());
    this.contentLength = -1L;
  }
  
  @JvmName(name = "boundary")
  @NotNull
  public final String boundary() {
    return this.boundaryByteString.utf8();
  }
  
  @JvmName(name = "size")
  public final int size() {
    return this.parts.size();
  }
  
  @NotNull
  public final Part part(int index) {
    return this.parts.get(index);
  }
  
  @NotNull
  public MediaType contentType() {
    return this.contentType;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_type")
  @NotNull
  public final MediaType -deprecated_type() {
    return this.type;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "boundary", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_boundary")
  @NotNull
  public final String -deprecated_boundary() {
    return boundary();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_size")
  public final int -deprecated_size() {
    return size();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "parts", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_parts")
  @NotNull
  public final List<Part> -deprecated_parts() {
    return this.parts;
  }
  
  public long contentLength() throws IOException {
    long result = this.contentLength;
    if (result == -1L) {
      result = writeOrCountBytes(null, true);
      this.contentLength = result;
    } 
    return result;
  }
  
  public void writeTo(@NotNull BufferedSink sink) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    writeOrCountBytes(sink, false);
  }
  
  private final long writeOrCountBytes(BufferedSink sink, boolean countBytes) throws IOException {
    BufferedSink bufferedSink = sink;
    long byteCount = 0L;
    Buffer byteCountBuffer = null;
    if (countBytes) {
      byteCountBuffer = new Buffer();
      bufferedSink = (BufferedSink)byteCountBuffer;
    } 
    for (int p = 0, i = this.parts.size(); p < i; p++) {
      Part part = this.parts.get(p);
      Headers headers = part.headers();
      RequestBody body = part.body();
      Intrinsics.checkNotNull(bufferedSink);
      bufferedSink.write(DASHDASH);
      bufferedSink.write(this.boundaryByteString);
      bufferedSink.write(CRLF);
      if (headers != null)
        for (int h = 0, j = headers.size(); h < j; h++)
          bufferedSink.writeUtf8(headers.name(h))
            .write(COLONSPACE)
            .writeUtf8(headers.value(h))
            .write(CRLF);  
      MediaType contentType = body.contentType();
      if (contentType != null)
        bufferedSink.writeUtf8("Content-Type: ")
          .writeUtf8(contentType.toString())
          .write(CRLF); 
      long contentLength = body.contentLength();
      if (contentLength != -1L) {
        bufferedSink.writeUtf8("Content-Length: ")
          .writeDecimalLong(contentLength)
          .write(CRLF);
      } else if (countBytes) {
        Intrinsics.checkNotNull(byteCountBuffer);
        byteCountBuffer.clear();
        return -1L;
      } 
      bufferedSink.write(CRLF);
      if (countBytes) {
        byteCount += contentLength;
      } else {
        body.writeTo(bufferedSink);
      } 
      bufferedSink.write(CRLF);
    } 
    Intrinsics.checkNotNull(bufferedSink);
    bufferedSink.write(DASHDASH);
    bufferedSink.write(this.boundaryByteString);
    bufferedSink.write(DASHDASH);
    bufferedSink.write(CRLF);
    if (countBytes) {
      Intrinsics.checkNotNull(byteCountBuffer);
      byteCount += byteCountBuffer.size();
      byteCountBuffer.clear();
    } 
    return byteCount;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\013\030\000 \0162\0020\001:\001\016B\033\b\002\022\b\020\003\032\004\030\0010\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\005\032\0020\004H\007¢\006\004\b\b\020\tJ\021\020\003\032\004\030\0010\002H\007¢\006\004\b\n\020\013R\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\020\f\032\004\b\005\020\tR\031\020\003\032\004\030\0010\0028\007¢\006\f\n\004\b\003\020\r\032\004\b\003\020\013¨\006\017"}, d2 = {"Lokhttp3/MultipartBody$Part;", "", "Lokhttp3/Headers;", "headers", "Lokhttp3/RequestBody;", "body", "<init>", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)V", "-deprecated_body", "()Lokhttp3/RequestBody;", "-deprecated_headers", "()Lokhttp3/Headers;", "Lokhttp3/RequestBody;", "Lokhttp3/Headers;", "Companion", "okhttp"})
  public static final class Part {
    @NotNull
    public static final Companion Companion = new Companion(null);
    
    @Nullable
    private final Headers headers;
    
    @NotNull
    private final RequestBody body;
    
    private Part(Headers headers, RequestBody body) {
      this.headers = headers;
      this.body = body;
    }
    
    @JvmName(name = "headers")
    @Nullable
    public final Headers headers() {
      return this.headers;
    }
    
    @JvmName(name = "body")
    @NotNull
    public final RequestBody body() {
      return this.body;
    }
    
    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_headers")
    @Nullable
    public final Headers -deprecated_headers() {
      return this.headers;
    }
    
    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_body")
    @NotNull
    public final RequestBody -deprecated_body() {
      return this.body;
    }
    
    @JvmStatic
    @NotNull
    public static final Part create(@NotNull RequestBody body) {
      return Companion.create(body);
    }
    
    @JvmStatic
    @NotNull
    public static final Part create(@Nullable Headers headers, @NotNull RequestBody body) {
      return Companion.create(headers, body);
    }
    
    @JvmStatic
    @NotNull
    public static final Part createFormData(@NotNull String name, @NotNull String value) {
      return Companion.createFormData(name, value);
    }
    
    @JvmStatic
    @NotNull
    public static final Part createFormData(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
      return Companion.createFormData(name, filename, body);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\007\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J!\020\t\032\0020\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\006H\007¢\006\004\b\t\020\nJ\027\020\t\032\0020\b2\006\020\007\032\0020\006H\007¢\006\004\b\t\020\013J\037\020\017\032\0020\b2\006\020\r\032\0020\f2\006\020\016\032\0020\fH\007¢\006\004\b\017\020\020J)\020\017\032\0020\b2\006\020\r\032\0020\f2\b\020\021\032\004\030\0010\f2\006\020\007\032\0020\006H\007¢\006\004\b\017\020\022¨\006\023"}, d2 = {"Lokhttp3/MultipartBody$Part$Companion;", "", "<init>", "()V", "Lokhttp3/Headers;", "headers", "Lokhttp3/RequestBody;", "body", "Lokhttp3/MultipartBody$Part;", "create", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Part;", "", "name", "value", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Part;", "filename", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Part;", "okhttp"})
    @SourceDebugExtension({"SMAP\nMultipartBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartBody.kt\nokhttp3/MultipartBody$Part$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,345:1\n1#2:346\n*E\n"})
    public static final class Companion {
      private Companion() {}
      
      @JvmStatic
      @NotNull
      public final MultipartBody.Part create(@NotNull RequestBody body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return create(null, body);
      }
      
      @JvmStatic
      @NotNull
      public final MultipartBody.Part create(@Nullable Headers headers, @NotNull RequestBody body) {
        Intrinsics.checkNotNullParameter(body, "body");
        if (!((((headers != null) ? headers.get("Content-Type") : null) == null) ? 1 : 0)) {
          int $i$a$-require-MultipartBody$Part$Companion$create$1 = 0;
          String str = "Unexpected header: Content-Type";
          throw new IllegalArgumentException(str.toString());
        } 
        if (!((((headers != null) ? headers.get("Content-Length") : null) == null) ? 1 : 0)) {
          int $i$a$-require-MultipartBody$Part$Companion$create$2 = 0;
          String str = "Unexpected header: Content-Length";
          throw new IllegalArgumentException(str.toString());
        } 
        return new MultipartBody.Part(headers, body, null);
      }
      
      @JvmStatic
      @NotNull
      public final MultipartBody.Part createFormData(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return createFormData(name, null, RequestBody.Companion.create$default(RequestBody.Companion, value, (MediaType)null, 1, (Object)null));
      }
      
      @JvmStatic
      @NotNull
      public final MultipartBody.Part createFormData(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        StringBuilder stringBuilder1 = new StringBuilder(), $this$createFormData_u24lambda_u242 = stringBuilder1;
        int $i$a$-buildString-MultipartBody$Part$Companion$createFormData$disposition$1 = 0;
        $this$createFormData_u24lambda_u242.append("form-data; name=");
        MultipartBody.Companion.appendQuotedString$okhttp($this$createFormData_u24lambda_u242, name);
        if (filename != null) {
          $this$createFormData_u24lambda_u242.append("; filename=");
          MultipartBody.Companion.appendQuotedString$okhttp($this$createFormData_u24lambda_u242, filename);
        } 
        Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
        String disposition = stringBuilder1.toString();
        Headers headers = (new Headers.Builder()).addUnsafeNonAscii("Content-Disposition", disposition).build();
        return create(headers, body);
      }
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000F\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\b\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020!\n\002\b\004\030\0002\0020\001B\023\b\007\022\b\b\002\020\003\032\0020\002¢\006\004\b\004\020\005J\035\020\b\032\0020\0002\006\020\006\032\0020\0022\006\020\007\032\0020\002¢\006\004\b\b\020\tJ'\020\b\032\0020\0002\006\020\006\032\0020\0022\b\020\n\032\004\030\0010\0022\006\020\f\032\0020\013¢\006\004\b\b\020\rJ\037\020\020\032\0020\0002\b\020\017\032\004\030\0010\0162\006\020\f\032\0020\013¢\006\004\b\020\020\021J\025\020\020\032\0020\0002\006\020\023\032\0020\022¢\006\004\b\020\020\024J\025\020\020\032\0020\0002\006\020\f\032\0020\013¢\006\004\b\020\020\025J\r\020\027\032\0020\026¢\006\004\b\027\020\030J\025\020\033\032\0020\0002\006\020\032\032\0020\031¢\006\004\b\033\020\034R\024\020\003\032\0020\0358\002X\004¢\006\006\n\004\b\003\020\036R\032\020 \032\b\022\004\022\0020\0220\0378\002X\004¢\006\006\n\004\b \020!R\026\020\032\032\0020\0318\002@\002X\016¢\006\006\n\004\b\032\020\"¨\006#"}, d2 = {"Lokhttp3/MultipartBody$Builder;", "", "", "boundary", "<init>", "(Ljava/lang/String;)V", "name", "value", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", "filename", "Lokhttp3/RequestBody;", "body", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", "Lokhttp3/Headers;", "headers", "addPart", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", "Lokhttp3/MultipartBody$Part;", "part", "(Lokhttp3/MultipartBody$Part;)Lokhttp3/MultipartBody$Builder;", "(Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", "Lokhttp3/MultipartBody;", "build", "()Lokhttp3/MultipartBody;", "Lokhttp3/MediaType;", "type", "setType", "(Lokhttp3/MediaType;)Lokhttp3/MultipartBody$Builder;", "Lokio/ByteString;", "Lokio/ByteString;", "", "parts", "Ljava/util/List;", "Lokhttp3/MediaType;", "okhttp"})
  @SourceDebugExtension({"SMAP\nMultipartBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartBody.kt\nokhttp3/MultipartBody$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,345:1\n1#2:346\n*E\n"})
  public static final class Builder {
    @NotNull
    private final ByteString boundary;
    
    @NotNull
    private MediaType type;
    
    @NotNull
    private final List<MultipartBody.Part> parts;
    
    @JvmOverloads
    public Builder(@NotNull String boundary) {
      this.boundary = ByteString.Companion.encodeUtf8(boundary);
      this.type = MultipartBody.MIXED;
      this.parts = new ArrayList<>();
    }
    
    @NotNull
    public final Builder setType(@NotNull MediaType type) {
      Intrinsics.checkNotNullParameter(type, "type");
      Builder builder1 = this, $this$setType_u24lambda_u241 = builder1;
      int $i$a$-apply-MultipartBody$Builder$setType$1 = 0;
      if (!Intrinsics.areEqual(type.type(), "multipart")) {
        int $i$a$-require-MultipartBody$Builder$setType$1$1 = 0;
        String str = "multipart != " + type;
        throw new IllegalArgumentException(str.toString());
      } 
      $this$setType_u24lambda_u241.type = type;
      return builder1;
    }
    
    @NotNull
    public final Builder addPart(@NotNull RequestBody body) {
      Intrinsics.checkNotNullParameter(body, "body");
      Builder builder1 = this, $this$addPart_u24lambda_u242 = builder1;
      int $i$a$-apply-MultipartBody$Builder$addPart$1 = 0;
      $this$addPart_u24lambda_u242.addPart(MultipartBody.Part.Companion.create(body));
      return builder1;
    }
    
    @NotNull
    public final Builder addPart(@Nullable Headers headers, @NotNull RequestBody body) {
      Intrinsics.checkNotNullParameter(body, "body");
      Builder builder1 = this, $this$addPart_u24lambda_u243 = builder1;
      int $i$a$-apply-MultipartBody$Builder$addPart$2 = 0;
      $this$addPart_u24lambda_u243.addPart(MultipartBody.Part.Companion.create(headers, body));
      return builder1;
    }
    
    @NotNull
    public final Builder addFormDataPart(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$addFormDataPart_u24lambda_u244 = builder1;
      int $i$a$-apply-MultipartBody$Builder$addFormDataPart$1 = 0;
      $this$addFormDataPart_u24lambda_u244.addPart(MultipartBody.Part.Companion.createFormData(name, value));
      return builder1;
    }
    
    @NotNull
    public final Builder addFormDataPart(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(body, "body");
      Builder builder1 = this, $this$addFormDataPart_u24lambda_u245 = builder1;
      int $i$a$-apply-MultipartBody$Builder$addFormDataPart$2 = 0;
      $this$addFormDataPart_u24lambda_u245.addPart(MultipartBody.Part.Companion.createFormData(name, filename, body));
      return builder1;
    }
    
    @NotNull
    public final Builder addPart(@NotNull MultipartBody.Part part) {
      Intrinsics.checkNotNullParameter(part, "part");
      Builder builder1 = this, $this$addPart_u24lambda_u246 = builder1;
      int $i$a$-apply-MultipartBody$Builder$addPart$3 = 0;
      $this$addPart_u24lambda_u246.parts.add(part);
      return builder1;
    }
    
    @NotNull
    public final MultipartBody build() {
      if (!(!this.parts.isEmpty() ? 1 : 0)) {
        int $i$a$-check-MultipartBody$Builder$build$1 = 0;
        String str = "Multipart body must have at least one part.";
        throw new IllegalStateException(str.toString());
      } 
      return new MultipartBody(this.boundary, this.type, Util.toImmutableList(this.parts));
    }
    
    @JvmOverloads
    public Builder() {
      this(null, 1, null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\002\020\016\n\000\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\022\n\002\b\t\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\037\020\013\032\0020\b*\0060\004j\002`\0052\006\020\007\032\0020\006H\000¢\006\004\b\t\020\nR\024\020\r\032\0020\f8\006X\004¢\006\006\n\004\b\r\020\016R\024\020\020\032\0020\0178\002X\004¢\006\006\n\004\b\020\020\021R\024\020\022\032\0020\0178\002X\004¢\006\006\n\004\b\022\020\021R\024\020\023\032\0020\0178\002X\004¢\006\006\n\004\b\023\020\021R\024\020\024\032\0020\f8\006X\004¢\006\006\n\004\b\024\020\016R\024\020\025\032\0020\f8\006X\004¢\006\006\n\004\b\025\020\016R\024\020\026\032\0020\f8\006X\004¢\006\006\n\004\b\026\020\016R\024\020\027\032\0020\f8\006X\004¢\006\006\n\004\b\027\020\016¨\006\030"}, d2 = {"Lokhttp3/MultipartBody$Companion;", "", "<init>", "()V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "key", "", "appendQuotedString$okhttp", "(Ljava/lang/StringBuilder;Ljava/lang/String;)V", "appendQuotedString", "Lokhttp3/MediaType;", "ALTERNATIVE", "Lokhttp3/MediaType;", "", "COLONSPACE", "[B", "CRLF", "DASHDASH", "DIGEST", "FORM", "MIXED", "PARALLEL", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final void appendQuotedString$okhttp(@NotNull StringBuilder $this$appendQuotedString, @NotNull String key) {
      Intrinsics.checkNotNullParameter($this$appendQuotedString, "<this>");
      Intrinsics.checkNotNullParameter(key, "key");
      $this$appendQuotedString.append('"');
      for (int i = 0, j = key.length(); i < j; i++) {
        char ch = key.charAt(i);
        if (ch == '\n') {
          $this$appendQuotedString.append("%0A");
        } else if (ch == '\r') {
          $this$appendQuotedString.append("%0D");
        } else if (ch == '"') {
          $this$appendQuotedString.append("%22");
        } else {
          $this$appendQuotedString.append(ch);
        } 
      } 
      $this$appendQuotedString.append('"');
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final ByteString boundaryByteString;
  
  @NotNull
  private final MediaType type;
  
  @NotNull
  private final List<Part> parts;
  
  @NotNull
  private final MediaType contentType;
  
  private long contentLength;
  
  @JvmField
  @NotNull
  public static final MediaType MIXED = MediaType.Companion.get("multipart/mixed");
  
  @JvmField
  @NotNull
  public static final MediaType ALTERNATIVE = MediaType.Companion.get("multipart/alternative");
  
  @JvmField
  @NotNull
  public static final MediaType DIGEST = MediaType.Companion.get("multipart/digest");
  
  @JvmField
  @NotNull
  public static final MediaType PARALLEL = MediaType.Companion.get("multipart/parallel");
  
  @JvmField
  @NotNull
  public static final MediaType FORM = MediaType.Companion.get("multipart/form-data");
  
  @NotNull
  private static final byte[] COLONSPACE;
  
  @NotNull
  private static final byte[] CRLF;
  
  @NotNull
  private static final byte[] DASHDASH;
  
  static {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = 58;
    arrayOfByte[1] = 32;
    COLONSPACE = arrayOfByte;
    arrayOfByte = new byte[2];
    arrayOfByte[0] = 13;
    arrayOfByte[1] = 10;
    CRLF = arrayOfByte;
    arrayOfByte = new byte[2];
    arrayOfByte[0] = 45;
    arrayOfByte[1] = 45;
    DASHDASH = arrayOfByte;
  }
}
