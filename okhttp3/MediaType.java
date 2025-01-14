package okhttp3;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\003\n\002\020\021\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\r\030\000 \0362\0020\001:\001\036B/\b\002\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\005\032\0020\002\022\f\020\007\032\b\022\004\022\0020\0020\006¢\006\004\b\b\020\tJ\035\020\f\032\004\030\0010\n2\n\b\002\020\013\032\004\030\0010\nH\007¢\006\004\b\f\020\rJ\032\020\020\032\0020\0172\b\020\016\032\004\030\0010\001H\002¢\006\004\b\020\020\021J\017\020\023\032\0020\022H\026¢\006\004\b\023\020\024J\027\020\026\032\004\030\0010\0022\006\020\025\032\0020\002¢\006\004\b\026\020\027J\017\020\005\032\0020\002H\007¢\006\004\b\030\020\031J\017\020\032\032\0020\002H\026¢\006\004\b\032\020\031J\017\020\004\032\0020\002H\007¢\006\004\b\033\020\031R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\034R\032\020\007\032\b\022\004\022\0020\0020\0068\002X\004¢\006\006\n\004\b\007\020\035R\027\020\005\032\0020\0028\007¢\006\f\n\004\b\005\020\034\032\004\b\005\020\031R\027\020\004\032\0020\0028\007¢\006\f\n\004\b\004\020\034\032\004\b\004\020\031¨\006\037"}, d2 = {"Lokhttp3/MediaType;", "", "", "mediaType", "type", "subtype", "", "parameterNamesAndValues", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "Ljava/nio/charset/Charset;", "defaultValue", "charset", "(Ljava/nio/charset/Charset;)Ljava/nio/charset/Charset;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "name", "parameter", "(Ljava/lang/String;)Ljava/lang/String;", "-deprecated_subtype", "()Ljava/lang/String;", "toString", "-deprecated_type", "Ljava/lang/String;", "[Ljava/lang/String;", "Companion", "okhttp"})
public final class MediaType {
  private MediaType(String mediaType, String type, String subtype, String[] parameterNamesAndValues) {
    this.mediaType = mediaType;
    this.type = type;
    this.subtype = subtype;
    this.parameterNamesAndValues = parameterNamesAndValues;
  }
  
  @JvmName(name = "type")
  @NotNull
  public final String type() {
    return this.type;
  }
  
  @JvmName(name = "subtype")
  @NotNull
  public final String subtype() {
    return this.subtype;
  }
  
  @JvmOverloads
  @Nullable
  public final Charset charset(@Nullable Charset defaultValue) {
    String charset;
    Charset charset1;
    if (parameter("charset") == null) {
      parameter("charset");
      return defaultValue;
    } 
    try {
      charset1 = Charset.forName(charset);
    } catch (IllegalArgumentException _) {
      charset1 = defaultValue;
    } 
    return charset1;
  }
  
  @Nullable
  public final String parameter(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    int j = this.parameterNamesAndValues.length + -1, i = 0, k = ProgressionUtilKt.getProgressionLastElement(0, j, 2);
    if (i <= k)
      while (true) {
        if (StringsKt.equals(this.parameterNamesAndValues[i], name, true))
          return this.parameterNamesAndValues[i + 1]; 
        if (i != k) {
          i += 2;
          continue;
        } 
        break;
      }  
    return null;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_type")
  @NotNull
  public final String -deprecated_type() {
    return this.type;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "subtype", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_subtype")
  @NotNull
  public final String -deprecated_subtype() {
    return this.subtype;
  }
  
  @NotNull
  public String toString() {
    return this.mediaType;
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof MediaType && Intrinsics.areEqual(((MediaType)other).mediaType, this.mediaType));
  }
  
  public int hashCode() {
    return this.mediaType.hashCode();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\b\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\t\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\031\020\013\032\004\030\0010\0062\006\020\005\032\0020\004H\007¢\006\004\b\n\020\bJ\023\020\f\032\0020\006*\0020\004H\007¢\006\004\b\t\020\bJ\025\020\r\032\004\030\0010\006*\0020\004H\007¢\006\004\b\013\020\bR\034\020\020\032\n \017*\004\030\0010\0160\0168\002X\004¢\006\006\n\004\b\020\020\021R\024\020\022\032\0020\0048\002XT¢\006\006\n\004\b\022\020\023R\024\020\024\032\0020\0048\002XT¢\006\006\n\004\b\024\020\023R\034\020\025\032\n \017*\004\030\0010\0160\0168\002X\004¢\006\006\n\004\b\025\020\021¨\006\026"}, d2 = {"Lokhttp3/MediaType$Companion;", "", "<init>", "()V", "", "mediaType", "Lokhttp3/MediaType;", "-deprecated_get", "(Ljava/lang/String;)Lokhttp3/MediaType;", "get", "-deprecated_parse", "parse", "toMediaType", "toMediaTypeOrNull", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "PARAMETER", "Ljava/util/regex/Pattern;", "QUOTED", "Ljava/lang/String;", "TOKEN", "TYPE_SUBTYPE", "okhttp"})
  @SourceDebugExtension({"SMAP\nMediaType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaType.kt\nokhttp3/MediaType$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,181:1\n1#2:182\n37#3,2:183\n*S KotlinDebug\n*F\n+ 1 MediaType.kt\nokhttp3/MediaType$Companion\n*L\n148#1:183,2\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    public final MediaType get(@NotNull String $this$toMediaType) {
      Intrinsics.checkNotNullParameter($this$toMediaType, "<this>");
      Matcher typeSubtype = MediaType.TYPE_SUBTYPE.matcher($this$toMediaType);
      if (!typeSubtype.lookingAt()) {
        int $i$a$-require-MediaType$Companion$toMediaType$1 = 0;
        String str = "No subtype found for: \"" + $this$toMediaType + '"';
        throw new IllegalArgumentException(str.toString());
      } 
      Intrinsics.checkNotNullExpressionValue(typeSubtype.group(1), "typeSubtype.group(1)");
      String str1 = typeSubtype.group(1);
      Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
      Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
      String type = str1.toLowerCase(Locale.US);
      Intrinsics.checkNotNullExpressionValue(typeSubtype.group(2), "typeSubtype.group(2)");
      String str2 = typeSubtype.group(2);
      Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
      Intrinsics.checkNotNullExpressionValue(str2.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
      String subtype = str2.toLowerCase(Locale.US);
      List<String> parameterNamesAndValues = new ArrayList();
      Matcher parameter = MediaType.PARAMETER.matcher($this$toMediaType);
      int s = 0;
      s = typeSubtype.end();
      while (s < $this$toMediaType.length()) {
        parameter.region(s, $this$toMediaType.length());
        if (!parameter.lookingAt()) {
          int $i$a$-require-MediaType$Companion$toMediaType$2 = 0;
          Intrinsics.checkNotNullExpressionValue($this$toMediaType.substring(s), "this as java.lang.String).substring(startIndex)");
          String str = "Parameter is not formatted correctly: \"" + $this$toMediaType.substring(s) + "\" for: \"" + $this$toMediaType + '"';
          throw new IllegalArgumentException(str.toString());
        } 
        String name = parameter.group(1);
        if (name == null) {
          s = parameter.end();
          continue;
        } 
        String token = parameter.group(2);
        Intrinsics.checkNotNullExpressionValue(token.substring(1, token.length() - 1), "this as java.lang.String…ing(startIndex, endIndex)");
        String value = (token == null) ? parameter.group(3) : ((StringsKt.startsWith$default(token, "'", false, 2, null) && StringsKt.endsWith$default(token, "'", false, 2, null) && token.length() > 2) ? token.substring(1, token.length() - 1) : token);
        parameterNamesAndValues.add(name);
        parameterNamesAndValues.add(value);
        s = parameter.end();
      } 
      Collection<String> $this$toTypedArray$iv = parameterNamesAndValues;
      int $i$f$toTypedArray = 0;
      Collection<String> thisCollection$iv = $this$toTypedArray$iv;
      return new MediaType($this$toMediaType, type, subtype, thisCollection$iv.<String>toArray(new String[0]), null);
    }
    
    @JvmStatic
    @JvmName(name = "parse")
    @Nullable
    public final MediaType parse(@NotNull String $this$toMediaTypeOrNull) {
      MediaType mediaType;
      Intrinsics.checkNotNullParameter($this$toMediaTypeOrNull, "<this>");
      try {
        mediaType = get($this$toMediaTypeOrNull);
      } catch (IllegalArgumentException _) {
        mediaType = null;
      } 
      return mediaType;
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaType()", imports = {"okhttp3.MediaType.Companion.toMediaType"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_get")
    @NotNull
    public final MediaType -deprecated_get(@NotNull String mediaType) {
      Intrinsics.checkNotNullParameter(mediaType, "mediaType");
      return get(mediaType);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaTypeOrNull()", imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_parse")
    @Nullable
    public final MediaType -deprecated_parse(@NotNull String mediaType) {
      Intrinsics.checkNotNullParameter(mediaType, "mediaType");
      return parse(mediaType);
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final String mediaType;
  
  @NotNull
  private final String type;
  
  @NotNull
  private final String subtype;
  
  @NotNull
  private final String[] parameterNamesAndValues;
  
  @NotNull
  private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
  
  @NotNull
  private static final String QUOTED = "\"([^\"]*)\"";
  
  private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
  
  private static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
  
  @JvmOverloads
  @Nullable
  public final Charset charset() {
    return charset$default(this, null, 1, null);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @NotNull
  public static final MediaType get(@NotNull String $this$get) {
    return Companion.get($this$get);
  }
  
  @JvmStatic
  @JvmName(name = "parse")
  @Nullable
  public static final MediaType parse(@NotNull String $this$parse) {
    return Companion.parse($this$parse);
  }
}
