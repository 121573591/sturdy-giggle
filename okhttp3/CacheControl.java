package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\n\n\002\020\016\n\002\b\027\030\000 %2\0020\001:\002&%Bs\b\002\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\006\032\0020\005\022\006\020\007\032\0020\005\022\006\020\b\032\0020\002\022\006\020\t\032\0020\002\022\006\020\n\032\0020\002\022\006\020\013\032\0020\005\022\006\020\f\032\0020\005\022\006\020\r\032\0020\002\022\006\020\016\032\0020\002\022\006\020\017\032\0020\002\022\b\020\021\032\004\030\0010\020¢\006\004\b\022\020\023J\017\020\017\032\0020\002H\007¢\006\004\b\024\020\025J\017\020\006\032\0020\005H\007¢\006\004\b\026\020\027J\017\020\013\032\0020\005H\007¢\006\004\b\030\020\027J\017\020\f\032\0020\005H\007¢\006\004\b\031\020\027J\017\020\n\032\0020\002H\007¢\006\004\b\032\020\025J\017\020\003\032\0020\002H\007¢\006\004\b\033\020\025J\017\020\004\032\0020\002H\007¢\006\004\b\034\020\025J\017\020\016\032\0020\002H\007¢\006\004\b\035\020\025J\017\020\r\032\0020\002H\007¢\006\004\b\036\020\025J\017\020\007\032\0020\005H\007¢\006\004\b\037\020\027J\017\020 \032\0020\020H\026¢\006\004\b \020!R\030\020\021\032\004\030\0010\0208\002@\002X\016¢\006\006\n\004\b\021\020\"R\027\020\017\032\0020\0028\007¢\006\f\n\004\b\017\020#\032\004\b\017\020\025R\027\020\b\032\0020\0028\006¢\006\f\n\004\b\b\020#\032\004\b\b\020\025R\027\020\t\032\0020\0028\006¢\006\f\n\004\b\t\020#\032\004\b\t\020\025R\027\020\006\032\0020\0058\007¢\006\f\n\004\b\006\020$\032\004\b\006\020\027R\027\020\013\032\0020\0058\007¢\006\f\n\004\b\013\020$\032\004\b\013\020\027R\027\020\f\032\0020\0058\007¢\006\f\n\004\b\f\020$\032\004\b\f\020\027R\027\020\n\032\0020\0028\007¢\006\f\n\004\b\n\020#\032\004\b\n\020\025R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020#\032\004\b\003\020\025R\027\020\004\032\0020\0028\007¢\006\f\n\004\b\004\020#\032\004\b\004\020\025R\027\020\016\032\0020\0028\007¢\006\f\n\004\b\016\020#\032\004\b\016\020\025R\027\020\r\032\0020\0028\007¢\006\f\n\004\b\r\020#\032\004\b\r\020\025R\027\020\007\032\0020\0058\007¢\006\f\n\004\b\007\020$\032\004\b\007\020\027¨\006'"}, d2 = {"Lokhttp3/CacheControl;", "", "", "noCache", "noStore", "", "maxAgeSeconds", "sMaxAgeSeconds", "isPrivate", "isPublic", "mustRevalidate", "maxStaleSeconds", "minFreshSeconds", "onlyIfCached", "noTransform", "immutable", "", "headerValue", "<init>", "(ZZIIZZZIIZZZLjava/lang/String;)V", "-deprecated_immutable", "()Z", "-deprecated_maxAgeSeconds", "()I", "-deprecated_maxStaleSeconds", "-deprecated_minFreshSeconds", "-deprecated_mustRevalidate", "-deprecated_noCache", "-deprecated_noStore", "-deprecated_noTransform", "-deprecated_onlyIfCached", "-deprecated_sMaxAgeSeconds", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "Z", "I", "Companion", "Builder", "okhttp"})
public final class CacheControl {
  private CacheControl(boolean noCache, boolean noStore, int maxAgeSeconds, int sMaxAgeSeconds, boolean isPrivate, boolean isPublic, boolean mustRevalidate, int maxStaleSeconds, int minFreshSeconds, boolean onlyIfCached, boolean noTransform, boolean immutable, String headerValue) {
    this.noCache = noCache;
    this.noStore = noStore;
    this.maxAgeSeconds = maxAgeSeconds;
    this.sMaxAgeSeconds = sMaxAgeSeconds;
    this.isPrivate = isPrivate;
    this.isPublic = isPublic;
    this.mustRevalidate = mustRevalidate;
    this.maxStaleSeconds = maxStaleSeconds;
    this.minFreshSeconds = minFreshSeconds;
    this.onlyIfCached = onlyIfCached;
    this.noTransform = noTransform;
    this.immutable = immutable;
    this.headerValue = headerValue;
  }
  
  @JvmName(name = "noCache")
  public final boolean noCache() {
    return this.noCache;
  }
  
  @JvmName(name = "noStore")
  public final boolean noStore() {
    return this.noStore;
  }
  
  @JvmName(name = "maxAgeSeconds")
  public final int maxAgeSeconds() {
    return this.maxAgeSeconds;
  }
  
  @JvmName(name = "sMaxAgeSeconds")
  public final int sMaxAgeSeconds() {
    return this.sMaxAgeSeconds;
  }
  
  public final boolean isPrivate() {
    return this.isPrivate;
  }
  
  public final boolean isPublic() {
    return this.isPublic;
  }
  
  @JvmName(name = "mustRevalidate")
  public final boolean mustRevalidate() {
    return this.mustRevalidate;
  }
  
  @JvmName(name = "maxStaleSeconds")
  public final int maxStaleSeconds() {
    return this.maxStaleSeconds;
  }
  
  @JvmName(name = "minFreshSeconds")
  public final int minFreshSeconds() {
    return this.minFreshSeconds;
  }
  
  @JvmName(name = "onlyIfCached")
  public final boolean onlyIfCached() {
    return this.onlyIfCached;
  }
  
  @JvmName(name = "noTransform")
  public final boolean noTransform() {
    return this.noTransform;
  }
  
  @JvmName(name = "immutable")
  public final boolean immutable() {
    return this.immutable;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "noCache", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_noCache")
  public final boolean -deprecated_noCache() {
    return this.noCache;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "noStore", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_noStore")
  public final boolean -deprecated_noStore() {
    return this.noStore;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "maxAgeSeconds", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_maxAgeSeconds")
  public final int -deprecated_maxAgeSeconds() {
    return this.maxAgeSeconds;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "sMaxAgeSeconds", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_sMaxAgeSeconds")
  public final int -deprecated_sMaxAgeSeconds() {
    return this.sMaxAgeSeconds;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "mustRevalidate", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_mustRevalidate")
  public final boolean -deprecated_mustRevalidate() {
    return this.mustRevalidate;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "maxStaleSeconds", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_maxStaleSeconds")
  public final int -deprecated_maxStaleSeconds() {
    return this.maxStaleSeconds;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "minFreshSeconds", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_minFreshSeconds")
  public final int -deprecated_minFreshSeconds() {
    return this.minFreshSeconds;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "onlyIfCached", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_onlyIfCached")
  public final boolean -deprecated_onlyIfCached() {
    return this.onlyIfCached;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "noTransform", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_noTransform")
  public final boolean -deprecated_noTransform() {
    return this.noTransform;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "immutable", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_immutable")
  public final boolean -deprecated_immutable() {
    return this.immutable;
  }
  
  @NotNull
  public String toString() {
    String result = this.headerValue;
    if (result == null) {
      StringBuilder stringBuilder1 = new StringBuilder(), $this$toString_u24lambda_u240 = stringBuilder1;
      int $i$a$-buildString-CacheControl$toString$1 = 0;
      if (this.noCache)
        $this$toString_u24lambda_u240.append("no-cache, "); 
      if (this.noStore)
        $this$toString_u24lambda_u240.append("no-store, "); 
      if (this.maxAgeSeconds != -1)
        $this$toString_u24lambda_u240.append("max-age=").append(this.maxAgeSeconds).append(", "); 
      if (this.sMaxAgeSeconds != -1)
        $this$toString_u24lambda_u240.append("s-maxage=").append(this.sMaxAgeSeconds).append(", "); 
      if (this.isPrivate)
        $this$toString_u24lambda_u240.append("private, "); 
      if (this.isPublic)
        $this$toString_u24lambda_u240.append("public, "); 
      if (this.mustRevalidate)
        $this$toString_u24lambda_u240.append("must-revalidate, "); 
      if (this.maxStaleSeconds != -1)
        $this$toString_u24lambda_u240.append("max-stale=").append(this.maxStaleSeconds).append(", "); 
      if (this.minFreshSeconds != -1)
        $this$toString_u24lambda_u240.append("min-fresh=").append(this.minFreshSeconds).append(", "); 
      if (this.onlyIfCached)
        $this$toString_u24lambda_u240.append("only-if-cached, "); 
      if (this.noTransform)
        $this$toString_u24lambda_u240.append("no-transform, "); 
      if (this.immutable)
        $this$toString_u24lambda_u240.append("immutable, "); 
      if (($this$toString_u24lambda_u240.length() == 0))
        return ""; 
      $this$toString_u24lambda_u240.delete($this$toString_u24lambda_u240.length() - 2, $this$toString_u24lambda_u240.length());
      Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
      result = stringBuilder1.toString();
      this.headerValue = result;
    } 
    return result;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\b\n\000\n\002\030\002\n\002\b\b\n\002\020\t\n\002\b\002\n\002\020\013\n\002\b\006\030\0002\0020\001B\007¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\006J\r\020\007\032\0020\000¢\006\004\b\007\020\bJ\035\020\n\032\0020\0002\006\020\n\032\0020\t2\006\020\f\032\0020\013¢\006\004\b\n\020\rJ\035\020\016\032\0020\0002\006\020\016\032\0020\t2\006\020\f\032\0020\013¢\006\004\b\016\020\rJ\035\020\017\032\0020\0002\006\020\017\032\0020\t2\006\020\f\032\0020\013¢\006\004\b\017\020\rJ\r\020\020\032\0020\000¢\006\004\b\020\020\bJ\r\020\021\032\0020\000¢\006\004\b\021\020\bJ\r\020\022\032\0020\000¢\006\004\b\022\020\bJ\r\020\023\032\0020\000¢\006\004\b\023\020\bJ\023\020\025\032\0020\t*\0020\024H\002¢\006\004\b\025\020\026R\026\020\007\032\0020\0278\002@\002X\016¢\006\006\n\004\b\007\020\030R\026\020\031\032\0020\t8\002@\002X\016¢\006\006\n\004\b\031\020\032R\026\020\033\032\0020\t8\002@\002X\016¢\006\006\n\004\b\033\020\032R\026\020\034\032\0020\t8\002@\002X\016¢\006\006\n\004\b\034\020\032R\026\020\020\032\0020\0278\002@\002X\016¢\006\006\n\004\b\020\020\030R\026\020\021\032\0020\0278\002@\002X\016¢\006\006\n\004\b\021\020\030R\026\020\022\032\0020\0278\002@\002X\016¢\006\006\n\004\b\022\020\030R\026\020\023\032\0020\0278\002@\002X\016¢\006\006\n\004\b\023\020\030¨\006\035"}, d2 = {"Lokhttp3/CacheControl$Builder;", "", "<init>", "()V", "Lokhttp3/CacheControl;", "build", "()Lokhttp3/CacheControl;", "immutable", "()Lokhttp3/CacheControl$Builder;", "", "maxAge", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "(ILjava/util/concurrent/TimeUnit;)Lokhttp3/CacheControl$Builder;", "maxStale", "minFresh", "noCache", "noStore", "noTransform", "onlyIfCached", "", "clampToInt", "(J)I", "", "Z", "maxAgeSeconds", "I", "maxStaleSeconds", "minFreshSeconds", "okhttp"})
  @SourceDebugExtension({"SMAP\nCacheControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CacheControl.kt\nokhttp3/CacheControl$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,416:1\n1#2:417\n*E\n"})
  public static final class Builder {
    private boolean noCache;
    
    private boolean noStore;
    
    private int maxAgeSeconds = -1;
    
    private int maxStaleSeconds = -1;
    
    private int minFreshSeconds = -1;
    
    private boolean onlyIfCached;
    
    private boolean noTransform;
    
    private boolean immutable;
    
    @NotNull
    public final Builder noCache() {
      Builder builder1 = this, $this$noCache_u24lambda_u240 = builder1;
      int $i$a$-apply-CacheControl$Builder$noCache$1 = 0;
      $this$noCache_u24lambda_u240.noCache = true;
      return builder1;
    }
    
    @NotNull
    public final Builder noStore() {
      Builder builder1 = this, $this$noStore_u24lambda_u241 = builder1;
      int $i$a$-apply-CacheControl$Builder$noStore$1 = 0;
      $this$noStore_u24lambda_u241.noStore = true;
      return builder1;
    }
    
    @NotNull
    public final Builder maxAge(int maxAge, @NotNull TimeUnit timeUnit) {
      Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
      Builder builder1 = this, $this$maxAge_u24lambda_u243 = builder1;
      int $i$a$-apply-CacheControl$Builder$maxAge$1 = 0;
      if (!((maxAge >= 0) ? 1 : 0)) {
        int $i$a$-require-CacheControl$Builder$maxAge$1$1 = 0;
        String str = "maxAge < 0: " + maxAge;
        throw new IllegalArgumentException(str.toString());
      } 
      long maxAgeSecondsLong = timeUnit.toSeconds(maxAge);
      $this$maxAge_u24lambda_u243.maxAgeSeconds = $this$maxAge_u24lambda_u243.clampToInt(maxAgeSecondsLong);
      return builder1;
    }
    
    @NotNull
    public final Builder maxStale(int maxStale, @NotNull TimeUnit timeUnit) {
      Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
      Builder builder1 = this, $this$maxStale_u24lambda_u245 = builder1;
      int $i$a$-apply-CacheControl$Builder$maxStale$1 = 0;
      if (!((maxStale >= 0) ? 1 : 0)) {
        int $i$a$-require-CacheControl$Builder$maxStale$1$1 = 0;
        String str = "maxStale < 0: " + maxStale;
        throw new IllegalArgumentException(str.toString());
      } 
      long maxStaleSecondsLong = timeUnit.toSeconds(maxStale);
      $this$maxStale_u24lambda_u245.maxStaleSeconds = $this$maxStale_u24lambda_u245.clampToInt(maxStaleSecondsLong);
      return builder1;
    }
    
    @NotNull
    public final Builder minFresh(int minFresh, @NotNull TimeUnit timeUnit) {
      Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
      Builder builder1 = this, $this$minFresh_u24lambda_u247 = builder1;
      int $i$a$-apply-CacheControl$Builder$minFresh$1 = 0;
      if (!((minFresh >= 0) ? 1 : 0)) {
        int $i$a$-require-CacheControl$Builder$minFresh$1$1 = 0;
        String str = "minFresh < 0: " + minFresh;
        throw new IllegalArgumentException(str.toString());
      } 
      long minFreshSecondsLong = timeUnit.toSeconds(minFresh);
      $this$minFresh_u24lambda_u247.minFreshSeconds = $this$minFresh_u24lambda_u247.clampToInt(minFreshSecondsLong);
      return builder1;
    }
    
    @NotNull
    public final Builder onlyIfCached() {
      Builder builder1 = this, $this$onlyIfCached_u24lambda_u248 = builder1;
      int $i$a$-apply-CacheControl$Builder$onlyIfCached$1 = 0;
      $this$onlyIfCached_u24lambda_u248.onlyIfCached = true;
      return builder1;
    }
    
    @NotNull
    public final Builder noTransform() {
      Builder builder1 = this, $this$noTransform_u24lambda_u249 = builder1;
      int $i$a$-apply-CacheControl$Builder$noTransform$1 = 0;
      $this$noTransform_u24lambda_u249.noTransform = true;
      return builder1;
    }
    
    @NotNull
    public final Builder immutable() {
      Builder builder1 = this, $this$immutable_u24lambda_u2410 = builder1;
      int $i$a$-apply-CacheControl$Builder$immutable$1 = 0;
      $this$immutable_u24lambda_u2410.immutable = true;
      return builder1;
    }
    
    private final int clampToInt(long $this$clampToInt) {
      return ($this$clampToInt > 2147483647L) ? Integer.MAX_VALUE : (int)$this$clampToInt;
    }
    
    @NotNull
    public final CacheControl build() {
      return new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, null, null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\007\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ%\020\r\032\0020\013*\0020\t2\006\020\n\032\0020\t2\b\b\002\020\f\032\0020\013H\002¢\006\004\b\r\020\016R\024\020\017\032\0020\0068\006X\004¢\006\006\n\004\b\017\020\020R\024\020\021\032\0020\0068\006X\004¢\006\006\n\004\b\021\020\020¨\006\022"}, d2 = {"Lokhttp3/CacheControl$Companion;", "", "<init>", "()V", "Lokhttp3/Headers;", "headers", "Lokhttp3/CacheControl;", "parse", "(Lokhttp3/Headers;)Lokhttp3/CacheControl;", "", "characters", "", "startIndex", "indexOfElement", "(Ljava/lang/String;Ljava/lang/String;I)I", "FORCE_CACHE", "Lokhttp3/CacheControl;", "FORCE_NETWORK", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final CacheControl parse(@NotNull Headers headers) {
      Intrinsics.checkNotNullParameter(headers, "headers");
      boolean noCache = false;
      boolean noStore = false;
      int maxAgeSeconds = -1;
      int sMaxAgeSeconds = -1;
      boolean isPrivate = false;
      boolean isPublic = false;
      boolean mustRevalidate = false;
      int maxStaleSeconds = -1;
      int minFreshSeconds = -1;
      boolean onlyIfCached = false;
      boolean noTransform = false;
      boolean immutable = false;
      boolean canUseHeaderValue = true;
      String headerValue = null;
      for (int i = 0, j = headers.size(); i < j; i++) {
        String name = headers.name(i);
        String value = headers.value(i);
        if (StringsKt.equals(name, "Cache-Control", true)) {
          if (headerValue != null) {
            canUseHeaderValue = false;
          } else {
            headerValue = value;
          } 
        } else if (StringsKt.equals(name, "Pragma", true)) {
          canUseHeaderValue = false;
        } else {
          continue;
        } 
        int pos = 0;
        while (pos < value.length()) {
          int tokenStart = pos;
          pos = indexOfElement(value, "=,;", pos);
          Intrinsics.checkNotNullExpressionValue(value.substring(tokenStart, pos), "this as java.lang.String…ing(startIndex, endIndex)");
          String directive = StringsKt.trim(value.substring(tokenStart, pos)).toString(), parameter = null;
          if (pos == value.length() || value.charAt(pos) == ',' || value.charAt(pos) == ';') {
            pos++;
            parameter = null;
          } else {
            pos++;
            pos = Util.indexOfNonWhitespace(value, pos);
            if (pos < value.length() && value.charAt(pos) == '"') {
              int parameterStart = ++pos;
              pos = StringsKt.indexOf$default(value, '"', pos, false, 4, null);
              Intrinsics.checkNotNullExpressionValue(value.substring(parameterStart, pos), "this as java.lang.String…ing(startIndex, endIndex)");
              parameter = value.substring(parameterStart, pos);
              pos++;
            } else {
              int parameterStart = pos;
              pos = indexOfElement(value, ",;", pos);
              Intrinsics.checkNotNullExpressionValue(value.substring(parameterStart, pos), "this as java.lang.String…ing(startIndex, endIndex)");
              parameter = StringsKt.trim(value.substring(parameterStart, pos)).toString();
            } 
          } 
          if (StringsKt.equals("no-cache", directive, true)) {
            noCache = true;
            continue;
          } 
          if (StringsKt.equals("no-store", directive, true)) {
            noStore = true;
            continue;
          } 
          if (StringsKt.equals("max-age", directive, true)) {
            maxAgeSeconds = Util.toNonNegativeInt(parameter, -1);
            continue;
          } 
          if (StringsKt.equals("s-maxage", directive, true)) {
            sMaxAgeSeconds = Util.toNonNegativeInt(parameter, -1);
            continue;
          } 
          if (StringsKt.equals("private", directive, true)) {
            isPrivate = true;
            continue;
          } 
          if (StringsKt.equals("public", directive, true)) {
            isPublic = true;
            continue;
          } 
          if (StringsKt.equals("must-revalidate", directive, true)) {
            mustRevalidate = true;
            continue;
          } 
          if (StringsKt.equals("max-stale", directive, true)) {
            maxStaleSeconds = Util.toNonNegativeInt(parameter, 2147483647);
            continue;
          } 
          if (StringsKt.equals("min-fresh", directive, true)) {
            minFreshSeconds = Util.toNonNegativeInt(parameter, -1);
            continue;
          } 
          if (StringsKt.equals("only-if-cached", directive, true)) {
            onlyIfCached = true;
            continue;
          } 
          if (StringsKt.equals("no-transform", directive, true)) {
            noTransform = true;
            continue;
          } 
          if (StringsKt.equals("immutable", directive, true))
            immutable = true; 
        } 
        continue;
      } 
      if (!canUseHeaderValue)
        headerValue = null; 
      return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic, mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, headerValue, null);
    }
    
    private final int indexOfElement(String $this$indexOfElement, String characters, int startIndex) {
      for (int i = startIndex, j = $this$indexOfElement.length(); i < j; i++) {
        if (StringsKt.contains$default(characters, $this$indexOfElement.charAt(i), false, 2, null))
          return i; 
      } 
      return $this$indexOfElement.length();
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private final boolean noCache;
  
  private final boolean noStore;
  
  private final int maxAgeSeconds;
  
  private final int sMaxAgeSeconds;
  
  private final boolean isPrivate;
  
  private final boolean isPublic;
  
  private final boolean mustRevalidate;
  
  private final int maxStaleSeconds;
  
  private final int minFreshSeconds;
  
  private final boolean onlyIfCached;
  
  private final boolean noTransform;
  
  private final boolean immutable;
  
  @Nullable
  private String headerValue;
  
  @JvmField
  @NotNull
  public static final CacheControl FORCE_NETWORK = (new Builder()).noCache().build();
  
  @JvmField
  @NotNull
  public static final CacheControl FORCE_CACHE = (new Builder()).onlyIfCached().maxStale(2147483647, TimeUnit.SECONDS).build();
  
  @JvmStatic
  @NotNull
  public static final CacheControl parse(@NotNull Headers headers) {
    return Companion.parse(headers);
  }
}
