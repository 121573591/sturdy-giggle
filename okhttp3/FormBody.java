package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\030\002\n\002\020 \n\002\020\016\n\002\b\004\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\t\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\006\030\000 \"2\0020\001:\002#\"B%\b\000\022\f\020\004\032\b\022\004\022\0020\0030\002\022\f\020\005\032\b\022\004\022\0020\0030\002¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rJ\025\020\020\032\0020\0032\006\020\017\032\0020\016¢\006\004\b\020\020\021J\025\020\022\032\0020\0032\006\020\017\032\0020\016¢\006\004\b\022\020\021J\025\020\023\032\0020\0032\006\020\017\032\0020\016¢\006\004\b\023\020\021J\017\020\026\032\0020\016H\007¢\006\004\b\024\020\025J\025\020\027\032\0020\0032\006\020\017\032\0020\016¢\006\004\b\027\020\021J!\020\034\032\0020\b2\b\020\031\032\004\030\0010\0302\006\020\033\032\0020\032H\002¢\006\004\b\034\020\035J\027\020\037\032\0020\0362\006\020\031\032\0020\030H\026¢\006\004\b\037\020 R\032\020\004\032\b\022\004\022\0020\0030\0028\002X\004¢\006\006\n\004\b\004\020!R\032\020\005\032\b\022\004\022\0020\0030\0028\002X\004¢\006\006\n\004\b\005\020!R\021\020\026\032\0020\0168G¢\006\006\032\004\b\026\020\025¨\006$"}, d2 = {"Lokhttp3/FormBody;", "Lokhttp3/RequestBody;", "", "", "encodedNames", "encodedValues", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "", "contentLength", "()J", "Lokhttp3/MediaType;", "contentType", "()Lokhttp3/MediaType;", "", "index", "encodedName", "(I)Ljava/lang/String;", "encodedValue", "name", "-deprecated_size", "()I", "size", "value", "Lokio/BufferedSink;", "sink", "", "countBytes", "writeOrCountBytes", "(Lokio/BufferedSink;Z)J", "", "writeTo", "(Lokio/BufferedSink;)V", "Ljava/util/List;", "Companion", "Builder", "okhttp"})
public final class FormBody extends RequestBody {
  public FormBody(@NotNull List encodedNames, @NotNull List encodedValues) {
    this.encodedNames = Util.toImmutableList(encodedNames);
    this.encodedValues = Util.toImmutableList(encodedValues);
  }
  
  @JvmName(name = "size")
  public final int size() {
    return this.encodedNames.size();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_size")
  public final int -deprecated_size() {
    return size();
  }
  
  @NotNull
  public final String encodedName(int index) {
    return this.encodedNames.get(index);
  }
  
  @NotNull
  public final String name(int index) {
    return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, encodedName(index), 0, 0, true, 3, null);
  }
  
  @NotNull
  public final String encodedValue(int index) {
    return this.encodedValues.get(index);
  }
  
  @NotNull
  public final String value(int index) {
    return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, encodedValue(index), 0, 0, true, 3, null);
  }
  
  @NotNull
  public MediaType contentType() {
    return CONTENT_TYPE;
  }
  
  public long contentLength() {
    return writeOrCountBytes(null, true);
  }
  
  public void writeTo(@NotNull BufferedSink sink) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    writeOrCountBytes(sink, false);
  }
  
  private final long writeOrCountBytes(BufferedSink sink, boolean countBytes) {
    long byteCount = 0L;
    Intrinsics.checkNotNull(sink);
    Buffer buffer = countBytes ? new Buffer() : sink.getBuffer();
    for (int i = 0, j = this.encodedNames.size(); i < j; i++) {
      if (i > 0)
        buffer.writeByte(38); 
      buffer.writeUtf8(this.encodedNames.get(i));
      buffer.writeByte(61);
      buffer.writeUtf8(this.encodedValues.get(i));
    } 
    if (countBytes) {
      byteCount = buffer.size();
      buffer.clear();
    } 
    return byteCount;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020!\n\002\b\004\030\0002\0020\001B\025\b\007\022\n\b\002\020\003\032\004\030\0010\002¢\006\004\b\004\020\005J\035\020\t\032\0020\0002\006\020\007\032\0020\0062\006\020\b\032\0020\006¢\006\004\b\t\020\nJ\035\020\013\032\0020\0002\006\020\007\032\0020\0062\006\020\b\032\0020\006¢\006\004\b\013\020\nJ\r\020\r\032\0020\f¢\006\004\b\r\020\016R\026\020\003\032\004\030\0010\0028\002X\004¢\006\006\n\004\b\003\020\017R\032\020\021\032\b\022\004\022\0020\0060\0208\002X\004¢\006\006\n\004\b\021\020\022R\032\020\023\032\b\022\004\022\0020\0060\0208\002X\004¢\006\006\n\004\b\023\020\022¨\006\024"}, d2 = {"Lokhttp3/FormBody$Builder;", "", "Ljava/nio/charset/Charset;", "charset", "<init>", "(Ljava/nio/charset/Charset;)V", "", "name", "value", "add", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/FormBody$Builder;", "addEncoded", "Lokhttp3/FormBody;", "build", "()Lokhttp3/FormBody;", "Ljava/nio/charset/Charset;", "", "names", "Ljava/util/List;", "values", "okhttp"})
  public static final class Builder {
    @Nullable
    private final Charset charset;
    
    @NotNull
    private final List<String> names;
    
    @NotNull
    private final List<String> values;
    
    @JvmOverloads
    public Builder(@Nullable Charset charset) {
      this.charset = charset;
      this.names = new ArrayList<>();
      this.values = new ArrayList<>();
    }
    
    @NotNull
    public final Builder add(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$add_u24lambda_u240 = builder1;
      int $i$a$-apply-FormBody$Builder$add$1 = 0;
      $this$add_u24lambda_u240.names.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, 
            " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, 
            true, false, 
            $this$add_u24lambda_u240.charset, 91, null));
      $this$add_u24lambda_u240.values.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, 
            " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, 
            true, false, 
            $this$add_u24lambda_u240.charset, 91, null));
      return builder1;
    }
    
    @NotNull
    public final Builder addEncoded(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$addEncoded_u24lambda_u241 = builder1;
      int $i$a$-apply-FormBody$Builder$addEncoded$1 = 0;
      $this$addEncoded_u24lambda_u241.names.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, 
            " \"':;<=>@[]^`{}|/\\?#&!$(),~", 
            true, false, 
            true, false, 
            $this$addEncoded_u24lambda_u241.charset, 83, null));
      $this$addEncoded_u24lambda_u241.values.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, 
            " \"':;<=>@[]^`{}|/\\?#&!$(),~", 
            true, false, 
            true, false, 
            $this$addEncoded_u24lambda_u241.charset, 83, null));
      return builder1;
    }
    
    @NotNull
    public final FormBody build() {
      return new FormBody(this.names, this.values);
    }
    
    @JvmOverloads
    public Builder() {
      this(null, 1, null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/FormBody$Companion;", "", "<init>", "()V", "Lokhttp3/MediaType;", "CONTENT_TYPE", "Lokhttp3/MediaType;", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final List<String> encodedNames;
  
  @NotNull
  private final List<String> encodedValues;
  
  @NotNull
  private static final MediaType CONTENT_TYPE = MediaType.Companion.get("application/x-www-form-urlencoded");
}
