package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\004\n\002\020$\n\002\b\004\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\013\030\0002\0020\001B\031\b\026\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002¢\006\004\b\005\020\006B%\022\006\020\003\032\0020\002\022\024\020\b\032\020\022\006\022\004\030\0010\002\022\004\022\0020\0020\007¢\006\004\b\005\020\tJ\035\020\b\032\020\022\006\022\004\030\0010\002\022\004\022\0020\0020\007H\007¢\006\004\b\n\020\013J\017\020\017\032\0020\fH\007¢\006\004\b\r\020\016J\032\020\022\032\0020\0212\b\020\020\032\004\030\0010\001H\002¢\006\004\b\022\020\023J\017\020\025\032\0020\024H\026¢\006\004\b\025\020\026J\021\020\004\032\004\030\0010\002H\007¢\006\004\b\027\020\030J\017\020\003\032\0020\002H\007¢\006\004\b\031\020\030J\017\020\032\032\0020\002H\026¢\006\004\b\032\020\030J\025\020\033\032\0020\0002\006\020\017\032\0020\f¢\006\004\b\033\020\034R%\020\b\032\020\022\006\022\004\030\0010\002\022\004\022\0020\0020\0078G¢\006\f\n\004\b\b\020\035\032\004\b\b\020\013R\021\020\017\032\0020\f8G¢\006\006\032\004\b\017\020\016R\023\020\004\032\004\030\0010\0028G¢\006\006\032\004\b\004\020\030R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020\036\032\004\b\003\020\030¨\006\037"}, d2 = {"Lokhttp3/Challenge;", "", "", "scheme", "realm", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "authParams", "(Ljava/lang/String;Ljava/util/Map;)V", "-deprecated_authParams", "()Ljava/util/Map;", "Ljava/nio/charset/Charset;", "-deprecated_charset", "()Ljava/nio/charset/Charset;", "charset", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "-deprecated_realm", "()Ljava/lang/String;", "-deprecated_scheme", "toString", "withCharset", "(Ljava/nio/charset/Charset;)Lokhttp3/Challenge;", "Ljava/util/Map;", "Ljava/lang/String;", "okhttp"})
public final class Challenge {
  @NotNull
  private final String scheme;
  
  @NotNull
  private final Map<String, String> authParams;
  
  public Challenge(@NotNull String scheme, @NotNull Map authParams) {
    this.scheme = scheme;
    Map<Object, Object> newAuthParams = new LinkedHashMap<>();
    for (Map.Entry entry : authParams.entrySet()) {
      String key = (String)entry.getKey(), value = (String)entry.getValue();
      if (key != null) {
        String str = key;
        Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
        Intrinsics.checkNotNullExpressionValue(str.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
      } else {
        str.toLowerCase(Locale.US);
      } 
      String newKey = null;
      newAuthParams.put(newKey, value);
    } 
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableMap(newAuthParams), "unmodifiableMap<String?, String>(newAuthParams)");
    this.authParams = Collections.unmodifiableMap(newAuthParams);
  }
  
  @JvmName(name = "scheme")
  @NotNull
  public final String scheme() {
    return this.scheme;
  }
  
  @JvmName(name = "authParams")
  @NotNull
  public final Map<String, String> authParams() {
    return this.authParams;
  }
  
  @JvmName(name = "realm")
  @Nullable
  public final String realm() {
    return this.authParams.get("realm");
  }
  
  @JvmName(name = "charset")
  @NotNull
  public final Charset charset() {
    String charset = this.authParams.get("charset");
    if (charset != null)
      try {
        Intrinsics.checkNotNullExpressionValue(Charset.forName(charset), "forName(charset)");
        return Charset.forName(charset);
      } catch (Exception exception) {} 
    Intrinsics.checkNotNullExpressionValue(StandardCharsets.ISO_8859_1, "ISO_8859_1");
    return StandardCharsets.ISO_8859_1;
  }
  
  public Challenge(@NotNull String scheme, @NotNull String realm) {
    this(scheme, Collections.singletonMap("realm", realm));
  }
  
  @NotNull
  public final Challenge withCharset(@NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(charset, "charset");
    Map<String, String> authParams = MapsKt.toMutableMap(this.authParams);
    Map<String, String> map1 = authParams;
    String str1 = "charset";
    Intrinsics.checkNotNullExpressionValue(charset.name(), "charset.name()");
    String str2 = charset.name();
    map1.put(str1, str2);
    return new Challenge(this.scheme, authParams);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_scheme")
  @NotNull
  public final String -deprecated_scheme() {
    return this.scheme;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "authParams", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_authParams")
  @NotNull
  public final Map<String, String> -deprecated_authParams() {
    return this.authParams;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "realm", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_realm")
  @Nullable
  public final String -deprecated_realm() {
    return realm();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "charset", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_charset")
  @NotNull
  public final Charset -deprecated_charset() {
    return charset();
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof Challenge && 
      Intrinsics.areEqual(((Challenge)other).scheme, this.scheme) && 
      Intrinsics.areEqual(((Challenge)other).authParams, this.authParams));
  }
  
  public int hashCode() {
    int result = 29;
    result = 31 * result + this.scheme.hashCode();
    result = 31 * result + this.authParams.hashCode();
    return result;
  }
  
  @NotNull
  public String toString() {
    return this.scheme + " authParams=" + this.authParams;
  }
}
