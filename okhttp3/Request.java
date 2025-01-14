package okhttp3;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000L\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020$\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\b\n\002\020 \n\002\b\003\n\002\030\002\n\002\b\f\n\002\020\013\n\002\b\013\030\0002\0020\001:\0015BC\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\b\020\t\032\004\030\0010\b\022\026\020\f\032\022\022\b\022\006\022\002\b\0030\013\022\004\022\0020\0010\n¢\006\004\b\r\020\016J\021\020\t\032\004\030\0010\bH\007¢\006\004\b\017\020\020J\017\020\024\032\0020\021H\007¢\006\004\b\022\020\023J\027\020\026\032\004\030\0010\0042\006\020\025\032\0020\004¢\006\004\b\026\020\027J\017\020\007\032\0020\006H\007¢\006\004\b\030\020\031J\033\020\007\032\b\022\004\022\0020\0040\0322\006\020\025\032\0020\004¢\006\004\b\007\020\033J\017\020\005\032\0020\004H\007¢\006\004\b\034\020\035J\r\020\037\032\0020\036¢\006\004\b\037\020 J\017\020!\032\004\030\0010\001¢\006\004\b!\020\"J%\020!\032\004\030\0018\000\"\004\b\000\020#2\016\020$\032\n\022\006\b\001\022\0028\0000\013¢\006\004\b!\020%J\017\020&\032\0020\004H\026¢\006\004\b&\020\035J\017\020\003\032\0020\002H\007¢\006\004\b'\020(R\031\020\t\032\004\030\0010\b8\007¢\006\f\n\004\b\t\020)\032\004\b\t\020\020R\021\020\024\032\0020\0218G¢\006\006\032\004\b\024\020\023R\027\020\007\032\0020\0068\007¢\006\f\n\004\b\007\020*\032\004\b\007\020\031R\021\020,\032\0020+8F¢\006\006\032\004\b,\020-R\030\020.\032\004\030\0010\0218\002@\002X\016¢\006\006\n\004\b.\020/R\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\0200\032\004\b\005\020\035R*\020\f\032\022\022\b\022\006\022\002\b\0030\013\022\004\022\0020\0010\n8\000X\004¢\006\f\n\004\b\f\0201\032\004\b2\0203R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\0204\032\004\b\003\020(¨\0066"}, d2 = {"Lokhttp3/Request;", "", "Lokhttp3/HttpUrl;", "url", "", "method", "Lokhttp3/Headers;", "headers", "Lokhttp3/RequestBody;", "body", "", "Ljava/lang/Class;", "tags", "<init>", "(Lokhttp3/HttpUrl;Ljava/lang/String;Lokhttp3/Headers;Lokhttp3/RequestBody;Ljava/util/Map;)V", "-deprecated_body", "()Lokhttp3/RequestBody;", "Lokhttp3/CacheControl;", "-deprecated_cacheControl", "()Lokhttp3/CacheControl;", "cacheControl", "name", "header", "(Ljava/lang/String;)Ljava/lang/String;", "-deprecated_headers", "()Lokhttp3/Headers;", "", "(Ljava/lang/String;)Ljava/util/List;", "-deprecated_method", "()Ljava/lang/String;", "Lokhttp3/Request$Builder;", "newBuilder", "()Lokhttp3/Request$Builder;", "tag", "()Ljava/lang/Object;", "T", "type", "(Ljava/lang/Class;)Ljava/lang/Object;", "toString", "-deprecated_url", "()Lokhttp3/HttpUrl;", "Lokhttp3/RequestBody;", "Lokhttp3/Headers;", "", "isHttps", "()Z", "lazyCacheControl", "Lokhttp3/CacheControl;", "Ljava/lang/String;", "Ljava/util/Map;", "getTags$okhttp", "()Ljava/util/Map;", "Lokhttp3/HttpUrl;", "Builder", "okhttp"})
@SourceDebugExtension({"SMAP\nRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Request.kt\nokhttp3/Request\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,298:1\n1864#2,3:299\n*S KotlinDebug\n*F\n+ 1 Request.kt\nokhttp3/Request\n*L\n119#1:299,3\n*E\n"})
public final class Request {
  @NotNull
  private final HttpUrl url;
  
  @NotNull
  private final String method;
  
  @NotNull
  private final Headers headers;
  
  @Nullable
  private final RequestBody body;
  
  @NotNull
  private final Map<Class<?>, Object> tags;
  
  @Nullable
  private CacheControl lazyCacheControl;
  
  public Request(@NotNull HttpUrl url, @NotNull String method, @NotNull Headers headers, @Nullable RequestBody body, @NotNull Map<Class<?>, Object> tags) {
    this.url = url;
    this.method = method;
    this.headers = headers;
    this.body = body;
    this.tags = tags;
  }
  
  @JvmName(name = "url")
  @NotNull
  public final HttpUrl url() {
    return this.url;
  }
  
  @JvmName(name = "method")
  @NotNull
  public final String method() {
    return this.method;
  }
  
  @JvmName(name = "headers")
  @NotNull
  public final Headers headers() {
    return this.headers;
  }
  
  @JvmName(name = "body")
  @Nullable
  public final RequestBody body() {
    return this.body;
  }
  
  @NotNull
  public final Map<Class<?>, Object> getTags$okhttp() {
    return this.tags;
  }
  
  public final boolean isHttps() {
    return this.url.isHttps();
  }
  
  @Nullable
  public final String header(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    return this.headers.get(name);
  }
  
  @NotNull
  public final List<String> headers(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    return this.headers.values(name);
  }
  
  @Nullable
  public final Object tag() {
    return tag(Object.class);
  }
  
  @Nullable
  public final <T> T tag(@NotNull Class<T> type) {
    Intrinsics.checkNotNullParameter(type, "type");
    return type.cast(this.tags.get(type));
  }
  
  @NotNull
  public final Builder newBuilder() {
    return new Builder(this);
  }
  
  @JvmName(name = "cacheControl")
  @NotNull
  public final CacheControl cacheControl() {
    CacheControl result = this.lazyCacheControl;
    if (result == null) {
      result = CacheControl.Companion.parse(this.headers);
      this.lazyCacheControl = result;
    } 
    return result;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_url")
  @NotNull
  public final HttpUrl -deprecated_url() {
    return this.url;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "method", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_method")
  @NotNull
  public final String -deprecated_method() {
    return this.method;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_headers")
  @NotNull
  public final Headers -deprecated_headers() {
    return this.headers;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_body")
  @Nullable
  public final RequestBody -deprecated_body() {
    return this.body;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cacheControl")
  @NotNull
  public final CacheControl -deprecated_cacheControl() {
    return cacheControl();
  }
  
  @NotNull
  public String toString() {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_1
    //   9: astore_2
    //   10: iconst_0
    //   11: istore_3
    //   12: aload_2
    //   13: ldc 'Request{method='
    //   15: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: pop
    //   19: aload_2
    //   20: aload_0
    //   21: getfield method : Ljava/lang/String;
    //   24: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload_2
    //   29: ldc ', url='
    //   31: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_2
    //   36: aload_0
    //   37: getfield url : Lokhttp3/HttpUrl;
    //   40: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_0
    //   45: getfield headers : Lokhttp3/Headers;
    //   48: invokevirtual size : ()I
    //   51: ifeq -> 199
    //   54: aload_2
    //   55: ldc ', headers=['
    //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload_0
    //   62: getfield headers : Lokhttp3/Headers;
    //   65: checkcast java/lang/Iterable
    //   68: astore #4
    //   70: iconst_0
    //   71: istore #5
    //   73: iconst_0
    //   74: istore #6
    //   76: aload #4
    //   78: invokeinterface iterator : ()Ljava/util/Iterator;
    //   83: astore #7
    //   85: aload #7
    //   87: invokeinterface hasNext : ()Z
    //   92: ifeq -> 191
    //   95: aload #7
    //   97: invokeinterface next : ()Ljava/lang/Object;
    //   102: astore #8
    //   104: iload #6
    //   106: iinc #6, 1
    //   109: istore #9
    //   111: iload #9
    //   113: ifge -> 119
    //   116: invokestatic throwIndexOverflow : ()V
    //   119: iload #9
    //   121: aload #8
    //   123: checkcast kotlin/Pair
    //   126: astore #10
    //   128: istore #11
    //   130: iconst_0
    //   131: istore #12
    //   133: aload #10
    //   135: invokevirtual component1 : ()Ljava/lang/Object;
    //   138: checkcast java/lang/String
    //   141: astore #13
    //   143: aload #10
    //   145: invokevirtual component2 : ()Ljava/lang/Object;
    //   148: checkcast java/lang/String
    //   151: astore #14
    //   153: iload #11
    //   155: ifle -> 165
    //   158: aload_2
    //   159: ldc ', '
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload_2
    //   166: aload #13
    //   168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload_2
    //   173: bipush #58
    //   175: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload_2
    //   180: aload #14
    //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: pop
    //   186: nop
    //   187: nop
    //   188: goto -> 85
    //   191: nop
    //   192: aload_2
    //   193: bipush #93
    //   195: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   198: pop
    //   199: aload_0
    //   200: getfield tags : Ljava/util/Map;
    //   203: invokeinterface isEmpty : ()Z
    //   208: ifne -> 215
    //   211: iconst_1
    //   212: goto -> 216
    //   215: iconst_0
    //   216: ifeq -> 235
    //   219: aload_2
    //   220: ldc ', tags='
    //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload_2
    //   227: aload_0
    //   228: getfield tags : Ljava/util/Map;
    //   231: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload_2
    //   236: bipush #125
    //   238: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: nop
    //   243: aload_1
    //   244: invokevirtual toString : ()Ljava/lang/String;
    //   247: dup
    //   248: ldc 'StringBuilder().apply(builderAction).toString()'
    //   250: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   253: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #112	-> 0
    //   #113	-> 12
    //   #114	-> 19
    //   #115	-> 28
    //   #116	-> 35
    //   #117	-> 44
    //   #118	-> 54
    //   #119	-> 61
    //   #299	-> 73
    //   #300	-> 76
    //   #300	-> 121
    //   #119	-> 133
    //   #120	-> 153
    //   #121	-> 158
    //   #123	-> 165
    //   #124	-> 172
    //   #125	-> 179
    //   #126	-> 186
    //   #300	-> 187
    //   #301	-> 191
    //   #127	-> 192
    //   #129	-> 199
    //   #129	-> 216
    //   #130	-> 219
    //   #131	-> 226
    //   #133	-> 235
    //   #134	-> 242
    //   #112	-> 243
    //   #134	-> 253
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   133	54	12	$i$a$-forEachIndexed-Request$toString$1$1	I
    //   143	44	13	name	Ljava/lang/String;
    //   153	34	14	value	Ljava/lang/String;
    //   130	57	11	index	I
    //   104	84	8	item$iv	Ljava/lang/Object;
    //   73	119	5	$i$f$forEachIndexed	I
    //   76	116	6	index$iv	I
    //   70	122	4	$this$forEachIndexed$iv	Ljava/lang/Iterable;
    //   12	231	3	$i$a$-buildString-Request$toString$1	I
    //   10	233	2	$this$toString_u24lambda_u241	Ljava/lang/StringBuilder;
    //   0	254	0	this	Lokhttp3/Request;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\\\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\n\n\002\020%\n\002\b\f\b\026\030\0002\0020\001B\t\b\026¢\006\004\b\002\020\003B\021\b\020\022\006\020\005\032\0020\004¢\006\004\b\002\020\006J\037\020\n\032\0020\0002\006\020\b\032\0020\0072\006\020\t\032\0020\007H\026¢\006\004\b\n\020\013J\017\020\f\032\0020\004H\026¢\006\004\b\f\020\rJ\027\020\017\032\0020\0002\006\020\017\032\0020\016H\026¢\006\004\b\017\020\020J\033\020\023\032\0020\0002\n\b\002\020\022\032\004\030\0010\021H\027¢\006\004\b\023\020\024J\017\020\025\032\0020\000H\026¢\006\004\b\025\020\026J\017\020\027\032\0020\000H\026¢\006\004\b\027\020\026J\037\020\030\032\0020\0002\006\020\b\032\0020\0072\006\020\t\032\0020\007H\026¢\006\004\b\030\020\013J\027\020\032\032\0020\0002\006\020\032\032\0020\031H\026¢\006\004\b\032\020\033J!\020\034\032\0020\0002\006\020\034\032\0020\0072\b\020\022\032\004\030\0010\021H\026¢\006\004\b\034\020\035J\027\020\036\032\0020\0002\006\020\022\032\0020\021H\026¢\006\004\b\036\020\024J\027\020\037\032\0020\0002\006\020\022\032\0020\021H\026¢\006\004\b\037\020\024J\027\020 \032\0020\0002\006\020\022\032\0020\021H\026¢\006\004\b \020\024J\027\020!\032\0020\0002\006\020\b\032\0020\007H\026¢\006\004\b!\020\"J/\020&\032\0020\000\"\004\b\000\020#2\016\020%\032\n\022\006\b\000\022\0028\0000$2\b\020&\032\004\030\0018\000H\026¢\006\004\b&\020'J\031\020&\032\0020\0002\b\020&\032\004\030\0010\001H\026¢\006\004\b&\020(J\027\020*\032\0020\0002\006\020*\032\0020)H\026¢\006\004\b*\020+J\027\020*\032\0020\0002\006\020*\032\0020\007H\026¢\006\004\b*\020\"J\027\020*\032\0020\0002\006\020*\032\0020,H\026¢\006\004\b*\020-R$\020\022\032\004\030\0010\0218\000@\000X\016¢\006\022\n\004\b\022\020.\032\004\b/\0200\"\004\b1\0202R\"\020\032\032\002038\000@\000X\016¢\006\022\n\004\b\032\0204\032\004\b5\0206\"\004\b7\0208R\"\020\034\032\0020\0078\000@\000X\016¢\006\022\n\004\b\034\0209\032\004\b:\020;\"\004\b<\020=R2\020?\032\022\022\b\022\006\022\002\b\0030$\022\004\022\0020\0010>8\000@\000X\016¢\006\022\n\004\b?\020@\032\004\bA\020B\"\004\bC\020DR$\020*\032\004\030\0010,8\000@\000X\016¢\006\022\n\004\b*\020E\032\004\bF\020G\"\004\bH\020I¨\006J"}, d2 = {"Lokhttp3/Request$Builder;", "", "<init>", "()V", "Lokhttp3/Request;", "request", "(Lokhttp3/Request;)V", "", "name", "value", "addHeader", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;", "build", "()Lokhttp3/Request;", "Lokhttp3/CacheControl;", "cacheControl", "(Lokhttp3/CacheControl;)Lokhttp3/Request$Builder;", "Lokhttp3/RequestBody;", "body", "delete", "(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;", "get", "()Lokhttp3/Request$Builder;", "head", "header", "Lokhttp3/Headers;", "headers", "(Lokhttp3/Headers;)Lokhttp3/Request$Builder;", "method", "(Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;", "patch", "post", "put", "removeHeader", "(Ljava/lang/String;)Lokhttp3/Request$Builder;", "T", "Ljava/lang/Class;", "type", "tag", "(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "(Ljava/lang/Object;)Lokhttp3/Request$Builder;", "Ljava/net/URL;", "url", "(Ljava/net/URL;)Lokhttp3/Request$Builder;", "Lokhttp3/HttpUrl;", "(Lokhttp3/HttpUrl;)Lokhttp3/Request$Builder;", "Lokhttp3/RequestBody;", "getBody$okhttp", "()Lokhttp3/RequestBody;", "setBody$okhttp", "(Lokhttp3/RequestBody;)V", "Lokhttp3/Headers$Builder;", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "Ljava/lang/String;", "getMethod$okhttp", "()Ljava/lang/String;", "setMethod$okhttp", "(Ljava/lang/String;)V", "", "tags", "Ljava/util/Map;", "getTags$okhttp", "()Ljava/util/Map;", "setTags$okhttp", "(Ljava/util/Map;)V", "Lokhttp3/HttpUrl;", "getUrl$okhttp", "()Lokhttp3/HttpUrl;", "setUrl$okhttp", "(Lokhttp3/HttpUrl;)V", "okhttp"})
  @SourceDebugExtension({"SMAP\nRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Request.kt\nokhttp3/Request$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,298:1\n1#2:299\n*E\n"})
  public static class Builder {
    @Nullable
    private HttpUrl url;
    
    @NotNull
    private String method;
    
    @NotNull
    private Headers.Builder headers;
    
    @Nullable
    private RequestBody body;
    
    @Nullable
    public final HttpUrl getUrl$okhttp() {
      return this.url;
    }
    
    public final void setUrl$okhttp(@Nullable HttpUrl <set-?>) {
      this.url = <set-?>;
    }
    
    @NotNull
    public final String getMethod$okhttp() {
      return this.method;
    }
    
    public final void setMethod$okhttp(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.method = <set-?>;
    }
    
    @NotNull
    public final Headers.Builder getHeaders$okhttp() {
      return this.headers;
    }
    
    public final void setHeaders$okhttp(@NotNull Headers.Builder <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.headers = <set-?>;
    }
    
    @Nullable
    public final RequestBody getBody$okhttp() {
      return this.body;
    }
    
    public final void setBody$okhttp(@Nullable RequestBody <set-?>) {
      this.body = <set-?>;
    }
    
    @NotNull
    private Map<Class<?>, Object> tags = new LinkedHashMap<>();
    
    @NotNull
    public final Map<Class<?>, Object> getTags$okhttp() {
      return this.tags;
    }
    
    public final void setTags$okhttp(@NotNull Map<Class<?>, Object> <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.tags = <set-?>;
    }
    
    public Builder() {
      this.method = "GET";
      this.headers = new Headers.Builder();
    }
    
    public Builder(@NotNull Request request) {
      this.url = request.url();
      this.method = request.method();
      this.body = request.body();
      this.tags = request.getTags$okhttp().isEmpty() ? 
        new LinkedHashMap<>() : 
        
        MapsKt.toMutableMap(request.getTags$okhttp());
      this.headers = request.headers().newBuilder();
    }
    
    @NotNull
    public Builder url(@NotNull HttpUrl url) {
      Intrinsics.checkNotNullParameter(url, "url");
      Builder builder1 = this, $this$url_u24lambda_u240 = builder1;
      int $i$a$-apply-Request$Builder$url$1 = 0;
      $this$url_u24lambda_u240.url = url;
      return builder1;
    }
    
    @NotNull
    public Builder url(@NotNull String url) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullExpressionValue(url.substring(3), "this as java.lang.String).substring(startIndex)");
      Intrinsics.checkNotNullExpressionValue(url.substring(4), "this as java.lang.String).substring(startIndex)");
      String finalUrl = StringsKt.startsWith(url, "ws:", true) ? ("http:" + url.substring(3)) : (StringsKt.startsWith(url, "wss:", true) ? ("https:" + url.substring(4)) : 
        
        url);
      return url(HttpUrl.Companion.get(finalUrl));
    }
    
    @NotNull
    public Builder url(@NotNull URL url) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullExpressionValue(url.toString(), "url.toString()");
      return url(HttpUrl.Companion.get(url.toString()));
    }
    
    @NotNull
    public Builder header(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$header_u24lambda_u241 = builder1;
      int $i$a$-apply-Request$Builder$header$1 = 0;
      $this$header_u24lambda_u241.headers.set(name, value);
      return builder1;
    }
    
    @NotNull
    public Builder addHeader(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$addHeader_u24lambda_u242 = builder1;
      int $i$a$-apply-Request$Builder$addHeader$1 = 0;
      $this$addHeader_u24lambda_u242.headers.add(name, value);
      return builder1;
    }
    
    @NotNull
    public Builder removeHeader(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$removeHeader_u24lambda_u243 = builder1;
      int $i$a$-apply-Request$Builder$removeHeader$1 = 0;
      $this$removeHeader_u24lambda_u243.headers.removeAll(name);
      return builder1;
    }
    
    @NotNull
    public Builder headers(@NotNull Headers headers) {
      Intrinsics.checkNotNullParameter(headers, "headers");
      Builder builder1 = this, $this$headers_u24lambda_u244 = builder1;
      int $i$a$-apply-Request$Builder$headers$1 = 0;
      $this$headers_u24lambda_u244.headers = headers.newBuilder();
      return builder1;
    }
    
    @NotNull
    public Builder cacheControl(@NotNull CacheControl cacheControl) {
      Intrinsics.checkNotNullParameter(cacheControl, "cacheControl");
      String value = cacheControl.toString();
      return 
        ((value.length() == 0)) ? removeHeader("Cache-Control") : 
        header("Cache-Control", value);
    }
    
    @NotNull
    public Builder get() {
      return method("GET", null);
    }
    
    @NotNull
    public Builder head() {
      return method("HEAD", null);
    }
    
    @NotNull
    public Builder post(@NotNull RequestBody body) {
      Intrinsics.checkNotNullParameter(body, "body");
      return method("POST", body);
    }
    
    @JvmOverloads
    @NotNull
    public Builder delete(@Nullable RequestBody body) {
      return method("DELETE", body);
    }
    
    @NotNull
    public Builder put(@NotNull RequestBody body) {
      Intrinsics.checkNotNullParameter(body, "body");
      return method("PUT", body);
    }
    
    @NotNull
    public Builder patch(@NotNull RequestBody body) {
      Intrinsics.checkNotNullParameter(body, "body");
      return method("PATCH", body);
    }
    
    @NotNull
    public Builder method(@NotNull String method, @Nullable RequestBody body) {
      Intrinsics.checkNotNullParameter(method, "method");
      Builder builder1 = this, $this$method_u24lambda_u248 = builder1;
      int $i$a$-apply-Request$Builder$method$1 = 0;
      if (!((method.length() > 0) ? 1 : 0)) {
        int $i$a$-require-Request$Builder$method$1$1 = 0;
        String str = 
          "method.isEmpty() == true";
        throw new IllegalArgumentException(str.toString());
      } 
      if (body == null) {
        if (!(!HttpMethod.requiresRequestBody(method) ? 1 : 0)) {
          int $i$a$-require-Request$Builder$method$1$2 = 0;
          String str = 
            "method " + method + " must have a request body.";
          throw new IllegalArgumentException(str.toString());
        } 
      } else if (!HttpMethod.permitsRequestBody(method)) {
        int $i$a$-require-Request$Builder$method$1$3 = 0;
        String str = 
          "method " + method + " must not have a request body.";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$method_u24lambda_u248.method = method;
      $this$method_u24lambda_u248.body = body;
      return builder1;
    }
    
    @NotNull
    public Builder tag(@Nullable Object tag) {
      return tag(Object.class, tag);
    }
    
    @NotNull
    public <T> Builder tag(@NotNull Class<?> type, @Nullable Object tag) {
      Intrinsics.checkNotNullParameter(type, "type");
      Builder builder1 = this, $this$tag_u24lambda_u249 = builder1;
      int $i$a$-apply-Request$Builder$tag$1 = 0;
      if (tag == null) {
        $this$tag_u24lambda_u249.tags.remove(type);
      } else {
        if ($this$tag_u24lambda_u249.tags.isEmpty())
          $this$tag_u24lambda_u249.tags = new LinkedHashMap<>(); 
        Intrinsics.checkNotNull(type.cast(tag));
        $this$tag_u24lambda_u249.tags.put(type, type.cast(tag));
      } 
      return builder1;
    }
    
    @NotNull
    public Request build() {
      HttpUrl httpUrl;
      if (this.url == null) {
        int $i$a$-checkNotNull-Request$Builder$build$1 = 0;
        String str1 = "url == null";
        throw new IllegalStateException(str1.toString());
      } 
      Map<Class<?>, ? extends Object> map = Util.toImmutableMap(this.tags);
      RequestBody requestBody = this.body;
      Headers headers = this.headers.build();
      String str = this.method;
      return new Request(httpUrl, str, headers, requestBody, map);
    }
    
    @JvmOverloads
    @NotNull
    public final Builder delete() {
      return delete$default(this, null, 1, null);
    }
  }
}
