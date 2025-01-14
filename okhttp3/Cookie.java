package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\002\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\r\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\022\030\000 -2\0020\001:\002.-BQ\b\002\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\006\032\0020\005\022\006\020\007\032\0020\002\022\006\020\b\032\0020\002\022\006\020\n\032\0020\t\022\006\020\013\032\0020\t\022\006\020\f\032\0020\t\022\006\020\r\032\0020\t¢\006\004\b\016\020\017J\017\020\007\032\0020\002H\007¢\006\004\b\020\020\021J\032\020\023\032\0020\t2\b\020\022\032\004\030\0010\001H\002¢\006\004\b\023\020\024J\017\020\006\032\0020\005H\007¢\006\004\b\025\020\026J\017\020\030\032\0020\027H\027¢\006\004\b\030\020\031J\017\020\r\032\0020\tH\007¢\006\004\b\032\020\033J\017\020\013\032\0020\tH\007¢\006\004\b\034\020\033J\025\020\037\032\0020\t2\006\020\036\032\0020\035¢\006\004\b\037\020 J\017\020\003\032\0020\002H\007¢\006\004\b!\020\021J\017\020\b\032\0020\002H\007¢\006\004\b\"\020\021J\017\020\f\032\0020\tH\007¢\006\004\b#\020\033J\017\020\n\032\0020\tH\007¢\006\004\b$\020\033J\017\020%\032\0020\002H\026¢\006\004\b%\020\021J\027\020%\032\0020\0022\006\020&\032\0020\tH\000¢\006\004\b'\020(J\017\020\004\032\0020\002H\007¢\006\004\b)\020\021R\027\020\007\032\0020\0028\007¢\006\f\n\004\b\007\020*\032\004\b\007\020\021R\027\020\006\032\0020\0058\007¢\006\f\n\004\b\006\020+\032\004\b\006\020\026R\027\020\r\032\0020\t8\007¢\006\f\n\004\b\r\020,\032\004\b\r\020\033R\027\020\013\032\0020\t8\007¢\006\f\n\004\b\013\020,\032\004\b\013\020\033R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020*\032\004\b\003\020\021R\027\020\b\032\0020\0028\007¢\006\f\n\004\b\b\020*\032\004\b\b\020\021R\027\020\f\032\0020\t8\007¢\006\f\n\004\b\f\020,\032\004\b\f\020\033R\027\020\n\032\0020\t8\007¢\006\f\n\004\b\n\020,\032\004\b\n\020\033R\027\020\004\032\0020\0028\007¢\006\f\n\004\b\004\020*\032\004\b\004\020\021¨\006/"}, d2 = {"Lokhttp3/Cookie;", "", "", "name", "value", "", "expiresAt", "domain", "path", "", "secure", "httpOnly", "persistent", "hostOnly", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V", "-deprecated_domain", "()Ljava/lang/String;", "other", "equals", "(Ljava/lang/Object;)Z", "-deprecated_expiresAt", "()J", "", "hashCode", "()I", "-deprecated_hostOnly", "()Z", "-deprecated_httpOnly", "Lokhttp3/HttpUrl;", "url", "matches", "(Lokhttp3/HttpUrl;)Z", "-deprecated_name", "-deprecated_path", "-deprecated_persistent", "-deprecated_secure", "toString", "forObsoleteRfc2965", "toString$okhttp", "(Z)Ljava/lang/String;", "-deprecated_value", "Ljava/lang/String;", "J", "Z", "Companion", "Builder", "okhttp"})
public final class Cookie {
  private Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean persistent, boolean hostOnly) {
    this.name = name;
    this.value = value;
    this.expiresAt = expiresAt;
    this.domain = domain;
    this.path = path;
    this.secure = secure;
    this.httpOnly = httpOnly;
    this.persistent = persistent;
    this.hostOnly = hostOnly;
  }
  
  @JvmName(name = "name")
  @NotNull
  public final String name() {
    return this.name;
  }
  
  @JvmName(name = "value")
  @NotNull
  public final String value() {
    return this.value;
  }
  
  @JvmName(name = "expiresAt")
  public final long expiresAt() {
    return this.expiresAt;
  }
  
  @JvmName(name = "domain")
  @NotNull
  public final String domain() {
    return this.domain;
  }
  
  @JvmName(name = "path")
  @NotNull
  public final String path() {
    return this.path;
  }
  
  @JvmName(name = "secure")
  public final boolean secure() {
    return this.secure;
  }
  
  @JvmName(name = "httpOnly")
  public final boolean httpOnly() {
    return this.httpOnly;
  }
  
  @JvmName(name = "persistent")
  public final boolean persistent() {
    return this.persistent;
  }
  
  @JvmName(name = "hostOnly")
  public final boolean hostOnly() {
    return this.hostOnly;
  }
  
  public final boolean matches(@NotNull HttpUrl url) {
    Intrinsics.checkNotNullParameter(url, "url");
    boolean domainMatch = this.hostOnly ? 
      Intrinsics.areEqual(url.host(), this.domain) : 
      
      Companion.domainMatch(url.host(), this.domain);
    if (!domainMatch)
      return false; 
    if (!Companion.pathMatch(url, this.path))
      return false; 
    return (!this.secure || url.isHttps());
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof Cookie && 
      Intrinsics.areEqual(((Cookie)other).name, this.name) && 
      Intrinsics.areEqual(((Cookie)other).value, this.value) && 
      ((Cookie)other).expiresAt == this.expiresAt && 
      Intrinsics.areEqual(((Cookie)other).domain, this.domain) && 
      Intrinsics.areEqual(((Cookie)other).path, this.path) && 
      ((Cookie)other).secure == this.secure && 
      ((Cookie)other).httpOnly == this.httpOnly && 
      ((Cookie)other).persistent == this.persistent && 
      ((Cookie)other).hostOnly == this.hostOnly);
  }
  
  @IgnoreJRERequirement
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.name.hashCode();
    result = 31 * result + this.value.hashCode();
    result = 31 * result + Long.hashCode(this.expiresAt);
    result = 31 * result + this.domain.hashCode();
    result = 31 * result + this.path.hashCode();
    result = 31 * result + Boolean.hashCode(this.secure);
    result = 31 * result + Boolean.hashCode(this.httpOnly);
    result = 31 * result + Boolean.hashCode(this.persistent);
    result = 31 * result + Boolean.hashCode(this.hostOnly);
    return result;
  }
  
  @NotNull
  public String toString() {
    return toString$okhttp(false);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "name", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_name")
  @NotNull
  public final String -deprecated_name() {
    return this.name;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "value", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_value")
  @NotNull
  public final String -deprecated_value() {
    return this.value;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "persistent", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_persistent")
  public final boolean -deprecated_persistent() {
    return this.persistent;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "expiresAt", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_expiresAt")
  public final long -deprecated_expiresAt() {
    return this.expiresAt;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "hostOnly", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_hostOnly")
  public final boolean -deprecated_hostOnly() {
    return this.hostOnly;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "domain", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_domain")
  @NotNull
  public final String -deprecated_domain() {
    return this.domain;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "path", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_path")
  @NotNull
  public final String -deprecated_path() {
    return this.path;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "httpOnly", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_httpOnly")
  public final boolean -deprecated_httpOnly() {
    return this.httpOnly;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "secure", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_secure")
  public final boolean -deprecated_secure() {
    return this.secure;
  }
  
  @NotNull
  public final String toString$okhttp(boolean forObsoleteRfc2965) {
    StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
    int $i$a$-buildString-Cookie$toString$1 = 0;
    $this$toString_u24lambda_u240.append(this.name);
    $this$toString_u24lambda_u240.append('=');
    $this$toString_u24lambda_u240.append(this.value);
    if (this.persistent)
      if (this.expiresAt == Long.MIN_VALUE) {
        $this$toString_u24lambda_u240.append("; max-age=0");
      } else {
        $this$toString_u24lambda_u240.append("; expires=").append(DatesKt.toHttpDateString(new Date(this.expiresAt)));
      }  
    if (!this.hostOnly) {
      $this$toString_u24lambda_u240.append("; domain=");
      if (forObsoleteRfc2965)
        $this$toString_u24lambda_u240.append("."); 
      $this$toString_u24lambda_u240.append(this.domain);
    } 
    $this$toString_u24lambda_u240.append("; path=").append(this.path);
    if (this.secure)
      $this$toString_u24lambda_u240.append("; secure"); 
    if (this.httpOnly)
      $this$toString_u24lambda_u240.append("; httponly"); 
    Intrinsics.checkNotNullExpressionValue($this$toString_u24lambda_u240.toString(), "toString()");
    return $this$toString_u24lambda_u240.toString();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\t\n\002\b\016\030\0002\0020\001B\007¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\006J\025\020\b\032\0020\0002\006\020\b\032\0020\007¢\006\004\b\b\020\tJ\037\020\b\032\0020\0002\006\020\b\032\0020\0072\006\020\013\032\0020\nH\002¢\006\004\b\b\020\fJ\025\020\016\032\0020\0002\006\020\016\032\0020\r¢\006\004\b\016\020\017J\025\020\020\032\0020\0002\006\020\b\032\0020\007¢\006\004\b\020\020\tJ\r\020\021\032\0020\000¢\006\004\b\021\020\022J\025\020\023\032\0020\0002\006\020\023\032\0020\007¢\006\004\b\023\020\tJ\025\020\024\032\0020\0002\006\020\024\032\0020\007¢\006\004\b\024\020\tJ\r\020\025\032\0020\000¢\006\004\b\025\020\022J\025\020\026\032\0020\0002\006\020\026\032\0020\007¢\006\004\b\026\020\tR\030\020\b\032\004\030\0010\0078\002@\002X\016¢\006\006\n\004\b\b\020\027R\026\020\016\032\0020\r8\002@\002X\016¢\006\006\n\004\b\016\020\030R\026\020\013\032\0020\n8\002@\002X\016¢\006\006\n\004\b\013\020\031R\026\020\021\032\0020\n8\002@\002X\016¢\006\006\n\004\b\021\020\031R\030\020\023\032\004\030\0010\0078\002@\002X\016¢\006\006\n\004\b\023\020\027R\026\020\024\032\0020\0078\002@\002X\016¢\006\006\n\004\b\024\020\027R\026\020\032\032\0020\n8\002@\002X\016¢\006\006\n\004\b\032\020\031R\026\020\025\032\0020\n8\002@\002X\016¢\006\006\n\004\b\025\020\031R\030\020\026\032\004\030\0010\0078\002@\002X\016¢\006\006\n\004\b\026\020\027¨\006\033"}, d2 = {"Lokhttp3/Cookie$Builder;", "", "<init>", "()V", "Lokhttp3/Cookie;", "build", "()Lokhttp3/Cookie;", "", "domain", "(Ljava/lang/String;)Lokhttp3/Cookie$Builder;", "", "hostOnly", "(Ljava/lang/String;Z)Lokhttp3/Cookie$Builder;", "", "expiresAt", "(J)Lokhttp3/Cookie$Builder;", "hostOnlyDomain", "httpOnly", "()Lokhttp3/Cookie$Builder;", "name", "path", "secure", "value", "Ljava/lang/String;", "J", "Z", "persistent", "okhttp"})
  @SourceDebugExtension({"SMAP\nCookie.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cookie.kt\nokhttp3/Cookie$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,614:1\n1#2:615\n*E\n"})
  public static final class Builder {
    @Nullable
    private String name;
    
    @Nullable
    private String value;
    
    private long expiresAt = 253402300799999L;
    
    @Nullable
    private String domain;
    
    @NotNull
    private String path = "/";
    
    private boolean secure;
    
    private boolean httpOnly;
    
    private boolean persistent;
    
    private boolean hostOnly;
    
    @NotNull
    public final Builder name(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$name_u24lambda_u241 = builder1;
      int $i$a$-apply-Cookie$Builder$name$1 = 0;
      if (!Intrinsics.areEqual(StringsKt.trim(name).toString(), name)) {
        int $i$a$-require-Cookie$Builder$name$1$1 = 0;
        String str = "name is not trimmed";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$name_u24lambda_u241.name = name;
      return builder1;
    }
    
    @NotNull
    public final Builder value(@NotNull String value) {
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$value_u24lambda_u243 = builder1;
      int $i$a$-apply-Cookie$Builder$value$1 = 0;
      if (!Intrinsics.areEqual(StringsKt.trim(value).toString(), value)) {
        int $i$a$-require-Cookie$Builder$value$1$1 = 0;
        String str = "value is not trimmed";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$value_u24lambda_u243.value = value;
      return builder1;
    }
    
    @NotNull
    public final Builder expiresAt(long expiresAt) {
      Builder builder1 = this, $this$expiresAt_u24lambda_u244 = builder1;
      int $i$a$-apply-Cookie$Builder$expiresAt$1 = 0;
      long l = expiresAt;
      if (l <= 0L)
        l = Long.MIN_VALUE; 
      if (l > 253402300799999L)
        l = 253402300799999L; 
      $this$expiresAt_u24lambda_u244.expiresAt = l;
      $this$expiresAt_u24lambda_u244.persistent = true;
      return builder1;
    }
    
    @NotNull
    public final Builder domain(@NotNull String domain) {
      Intrinsics.checkNotNullParameter(domain, "domain");
      return domain(domain, false);
    }
    
    @NotNull
    public final Builder hostOnlyDomain(@NotNull String domain) {
      Intrinsics.checkNotNullParameter(domain, "domain");
      return domain(domain, true);
    }
    
    private final Builder domain(String domain, boolean hostOnly) {
      String canonicalDomain;
      Builder builder1 = this, $this$domain_u24lambda_u245 = builder1;
      int $i$a$-apply-Cookie$Builder$domain$1 = 0;
      if (HostnamesKt.toCanonicalHost(domain) == null) {
        HostnamesKt.toCanonicalHost(domain);
        throw new IllegalArgumentException("unexpected domain: " + domain);
      } 
      $this$domain_u24lambda_u245.domain = canonicalDomain;
      $this$domain_u24lambda_u245.hostOnly = hostOnly;
      return builder1;
    }
    
    @NotNull
    public final Builder path(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      Builder builder1 = this, $this$path_u24lambda_u247 = builder1;
      int $i$a$-apply-Cookie$Builder$path$1 = 0;
      if (!StringsKt.startsWith$default(path, "/", false, 2, null)) {
        int $i$a$-require-Cookie$Builder$path$1$1 = 0;
        String str = "path must start with '/'";
        throw new IllegalArgumentException(str.toString());
      } 
      $this$path_u24lambda_u247.path = path;
      return builder1;
    }
    
    @NotNull
    public final Builder secure() {
      Builder builder1 = this, $this$secure_u24lambda_u248 = builder1;
      int $i$a$-apply-Cookie$Builder$secure$1 = 0;
      $this$secure_u24lambda_u248.secure = true;
      return builder1;
    }
    
    @NotNull
    public final Builder httpOnly() {
      Builder builder1 = this, $this$httpOnly_u24lambda_u249 = builder1;
      int $i$a$-apply-Cookie$Builder$httpOnly$1 = 0;
      $this$httpOnly_u24lambda_u249.httpOnly = true;
      return builder1;
    }
    
    @NotNull
    public final Cookie build() {
      if (this.name == null)
        throw new NullPointerException("builder.name == null"); 
      if (this.value == null)
        throw new NullPointerException("builder.value == null"); 
      if (this.domain == null)
        throw new NullPointerException("builder.domain == null"); 
      super(this.value, this.expiresAt, this.domain, this.domain, this.path, this.secure, this.httpOnly, this.persistent, this.hostOnly, null);
      return (Cookie)this.name;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\002\n\002\020\013\n\002\b\007\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\020 \n\002\b\f\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J/\020\013\032\0020\0062\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\0062\006\020\n\032\0020\tH\002¢\006\004\b\013\020\fJ\037\020\017\032\0020\t2\006\020\r\032\0020\0042\006\020\016\032\0020\004H\002¢\006\004\b\017\020\020J)\020\031\032\004\030\0010\0262\006\020\022\032\0020\0212\006\020\024\032\0020\0232\006\020\025\032\0020\004H\000¢\006\004\b\027\020\030J!\020\031\032\004\030\0010\0262\006\020\024\032\0020\0232\006\020\025\032\0020\004H\007¢\006\004\b\031\020\032J%\020\036\032\b\022\004\022\0020\0260\0352\006\020\024\032\0020\0232\006\020\034\032\0020\033H\007¢\006\004\b\036\020\037J\027\020!\032\0020\0042\006\020 \032\0020\004H\002¢\006\004\b!\020\"J'\020#\032\0020\0212\006\020 \032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\006H\002¢\006\004\b#\020$J\027\020%\032\0020\0212\006\020 \032\0020\004H\002¢\006\004\b%\020&J\037\020(\032\0020\t2\006\020\024\032\0020\0232\006\020'\032\0020\004H\002¢\006\004\b(\020)R\034\020,\032\n +*\004\030\0010*0*8\002X\004¢\006\006\n\004\b,\020-R\034\020.\032\n +*\004\030\0010*0*8\002X\004¢\006\006\n\004\b.\020-R\034\020/\032\n +*\004\030\0010*0*8\002X\004¢\006\006\n\004\b/\020-R\034\0200\032\n +*\004\030\0010*0*8\002X\004¢\006\006\n\004\b0\020-¨\0061"}, d2 = {"Lokhttp3/Cookie$Companion;", "", "<init>", "()V", "", "input", "", "pos", "limit", "", "invert", "dateCharacterOffset", "(Ljava/lang/String;IIZ)I", "urlHost", "domain", "domainMatch", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "currentTimeMillis", "Lokhttp3/HttpUrl;", "url", "setCookie", "Lokhttp3/Cookie;", "parse$okhttp", "(JLokhttp3/HttpUrl;Ljava/lang/String;)Lokhttp3/Cookie;", "parse", "(Lokhttp3/HttpUrl;Ljava/lang/String;)Lokhttp3/Cookie;", "Lokhttp3/Headers;", "headers", "", "parseAll", "(Lokhttp3/HttpUrl;Lokhttp3/Headers;)Ljava/util/List;", "s", "parseDomain", "(Ljava/lang/String;)Ljava/lang/String;", "parseExpires", "(Ljava/lang/String;II)J", "parseMaxAge", "(Ljava/lang/String;)J", "path", "pathMatch", "(Lokhttp3/HttpUrl;Ljava/lang/String;)Z", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "DAY_OF_MONTH_PATTERN", "Ljava/util/regex/Pattern;", "MONTH_PATTERN", "TIME_PATTERN", "YEAR_PATTERN", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    private final boolean domainMatch(String urlHost, String domain) {
      if (Intrinsics.areEqual(urlHost, domain))
        return true; 
      return (StringsKt.endsWith$default(urlHost, domain, false, 2, null) && urlHost.charAt(urlHost.length() - domain.length() - 1) == '.' && !Util.canParseAsIpAddress(urlHost));
    }
    
    private final boolean pathMatch(HttpUrl url, String path) {
      String urlPath = url.encodedPath();
      if (Intrinsics.areEqual(urlPath, path))
        return true; 
      if (StringsKt.startsWith$default(urlPath, path, false, 2, null)) {
        if (StringsKt.endsWith$default(path, "/", false, 2, null))
          return true; 
        if (urlPath.charAt(path.length()) == '/')
          return true; 
      } 
      return false;
    }
    
    @JvmStatic
    @Nullable
    public final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(setCookie, "setCookie");
      return parse$okhttp(System.currentTimeMillis(), url, setCookie);
    }
    
    @Nullable
    public final Cookie parse$okhttp(long currentTimeMillis, @NotNull HttpUrl url, @NotNull String setCookie) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(setCookie, "setCookie");
      int cookiePairEnd = Util.delimiterOffset$default(setCookie, ';', 0, 0, 6, null);
      int pairEqualsSign = Util.delimiterOffset$default(setCookie, '=', 0, cookiePairEnd, 2, null);
      if (pairEqualsSign == cookiePairEnd)
        return null; 
      String cookieName = Util.trimSubstring$default(setCookie, 0, pairEqualsSign, 1, null);
      if (((((CharSequence)cookieName).length() == 0)) || Util.indexOfControlOrNonAscii(cookieName) != -1)
        return null; 
      String cookieValue = Util.trimSubstring(setCookie, pairEqualsSign + 1, cookiePairEnd);
      if (Util.indexOfControlOrNonAscii(cookieValue) != -1)
        return null; 
      long expiresAt = 253402300799999L;
      long deltaSeconds = -1L;
      String domain = null;
      String path = null;
      boolean secureOnly = false;
      boolean httpOnly = false;
      boolean hostOnly = true;
      boolean persistent = false;
      int pos = cookiePairEnd + 1;
      int limit = setCookie.length();
      while (pos < limit) {
        int attributePairEnd = Util.delimiterOffset(setCookie, ';', pos, limit);
        int attributeEqualsSign = Util.delimiterOffset(setCookie, '=', pos, attributePairEnd);
        String attributeName = Util.trimSubstring(setCookie, pos, attributeEqualsSign);
        String attributeValue = (attributeEqualsSign < attributePairEnd) ? Util.trimSubstring(setCookie, attributeEqualsSign + 1, attributePairEnd) : "";
        if (StringsKt.equals(attributeName, "expires", true)) {
          try {
            expiresAt = parseExpires(attributeValue, 0, attributeValue.length());
            persistent = true;
          } catch (IllegalArgumentException illegalArgumentException) {}
        } else if (StringsKt.equals(attributeName, "max-age", true)) {
          try {
            deltaSeconds = parseMaxAge(attributeValue);
            persistent = true;
          } catch (NumberFormatException numberFormatException) {}
        } else if (StringsKt.equals(attributeName, "domain", true)) {
          try {
            domain = parseDomain(attributeValue);
            hostOnly = false;
          } catch (IllegalArgumentException illegalArgumentException) {}
        } else if (StringsKt.equals(attributeName, "path", true)) {
          path = attributeValue;
        } else if (StringsKt.equals(attributeName, "secure", true)) {
          secureOnly = true;
        } else if (StringsKt.equals(attributeName, "httponly", true)) {
          httpOnly = true;
        } 
        pos = attributePairEnd + 1;
      } 
      if (deltaSeconds == Long.MIN_VALUE) {
        expiresAt = Long.MIN_VALUE;
      } else if (deltaSeconds != -1L) {
        long deltaMilliseconds = (deltaSeconds <= 9223372036854775L) ? (deltaSeconds * 1000L) : Long.MAX_VALUE;
        expiresAt = currentTimeMillis + deltaMilliseconds;
        if (expiresAt < currentTimeMillis || expiresAt > 253402300799999L)
          expiresAt = 253402300799999L; 
      } 
      String urlHost = url.host();
      if (domain == null) {
        domain = urlHost;
      } else if (!domainMatch(urlHost, domain)) {
        return null;
      } 
      if (urlHost.length() != domain.length() && PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(domain) == null)
        return null; 
      if (path == null || !StringsKt.startsWith$default(path, "/", false, 2, null)) {
        String encodedPath = url.encodedPath();
        int lastSlash = StringsKt.lastIndexOf$default(encodedPath, '/', 0, false, 6, null);
        Intrinsics.checkNotNullExpressionValue(encodedPath.substring(0, lastSlash), "this as java.lang.String…ing(startIndex, endIndex)");
        path = (lastSlash != 0) ? encodedPath.substring(0, lastSlash) : "/";
      } 
      return new Cookie(cookieName, cookieValue, expiresAt, domain, path, secureOnly, httpOnly, persistent, hostOnly, null);
    }
    
    private final long parseExpires(String s, int pos, int limit) {
      int i = pos;
      i = dateCharacterOffset(s, i, limit, false);
      int hour = 0;
      hour = -1;
      int minute = 0;
      minute = -1;
      int second = 0;
      second = -1;
      int dayOfMonth = 0;
      dayOfMonth = -1;
      int month = 0;
      month = -1;
      int year = 0;
      year = -1;
      Matcher matcher = Cookie.TIME_PATTERN.matcher(s);
      while (i < limit) {
        int end = dateCharacterOffset(s, i + 1, limit, true);
        matcher.region(i, end);
        if (hour == -1 && matcher.usePattern(Cookie.TIME_PATTERN).matches()) {
          Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
          hour = Integer.parseInt(matcher.group(1));
          Intrinsics.checkNotNullExpressionValue(matcher.group(2), "matcher.group(2)");
          minute = Integer.parseInt(matcher.group(2));
          Intrinsics.checkNotNullExpressionValue(matcher.group(3), "matcher.group(3)");
          second = Integer.parseInt(matcher.group(3));
        } else if (dayOfMonth == -1 && matcher.usePattern(Cookie.DAY_OF_MONTH_PATTERN).matches()) {
          Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
          dayOfMonth = Integer.parseInt(matcher.group(1));
        } else if (month == -1 && matcher.usePattern(Cookie.MONTH_PATTERN).matches()) {
          Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
          String str1 = matcher.group(1);
          Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
          Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
          String monthString = str1.toLowerCase(Locale.US);
          Intrinsics.checkNotNullExpressionValue(Cookie.MONTH_PATTERN.pattern(), "MONTH_PATTERN.pattern()");
          month = StringsKt.indexOf$default(Cookie.MONTH_PATTERN.pattern(), monthString, 0, false, 6, null) / 4;
        } else if (year == -1 && matcher.usePattern(Cookie.YEAR_PATTERN).matches()) {
          Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
          year = Integer.parseInt(matcher.group(1));
        } 
        i = dateCharacterOffset(s, end + 1, limit, false);
      } 
      if ((70 <= year) ? ((year < 100)) : false)
        year += 1900; 
      if ((0 <= year) ? ((year < 70)) : false)
        year += 2000; 
      if (!((year >= 1601) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((month != -1) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((1 <= dayOfMonth) ? ((dayOfMonth < 32) ? 1 : 0) : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((0 <= hour) ? ((hour < 24) ? 1 : 0) : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((0 <= minute) ? ((minute < 60) ? 1 : 0) : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((0 <= second) ? ((second < 60) ? 1 : 0) : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      GregorianCalendar $this$parseExpires_u24lambda_u240 = new GregorianCalendar(Util.UTC);
      int $i$a$-apply-Cookie$Companion$parseExpires$1 = 0;
      $this$parseExpires_u24lambda_u240.setLenient(false);
      $this$parseExpires_u24lambda_u240.set(1, year);
      $this$parseExpires_u24lambda_u240.set(2, month - 1);
      $this$parseExpires_u24lambda_u240.set(5, dayOfMonth);
      $this$parseExpires_u24lambda_u240.set(11, hour);
      $this$parseExpires_u24lambda_u240.set(12, minute);
      $this$parseExpires_u24lambda_u240.set(13, second);
      $this$parseExpires_u24lambda_u240.set(14, 0);
      return $this$parseExpires_u24lambda_u240.getTimeInMillis();
    }
    
    private final int dateCharacterOffset(String input, int pos, int limit, boolean invert) {
      // Byte code:
      //   0: iload_2
      //   1: istore #5
      //   3: iload #5
      //   5: iload_3
      //   6: if_icmpge -> 154
      //   9: aload_1
      //   10: iload #5
      //   12: invokevirtual charAt : (I)C
      //   15: istore #6
      //   17: iload #6
      //   19: bipush #32
      //   21: if_icmpge -> 31
      //   24: iload #6
      //   26: bipush #9
      //   28: if_icmpne -> 123
      //   31: iload #6
      //   33: bipush #127
      //   35: if_icmpge -> 123
      //   38: bipush #48
      //   40: iload #6
      //   42: if_icmpgt -> 60
      //   45: iload #6
      //   47: bipush #58
      //   49: if_icmpge -> 56
      //   52: iconst_1
      //   53: goto -> 61
      //   56: iconst_0
      //   57: goto -> 61
      //   60: iconst_0
      //   61: ifne -> 123
      //   64: bipush #97
      //   66: iload #6
      //   68: if_icmpgt -> 86
      //   71: iload #6
      //   73: bipush #123
      //   75: if_icmpge -> 82
      //   78: iconst_1
      //   79: goto -> 87
      //   82: iconst_0
      //   83: goto -> 87
      //   86: iconst_0
      //   87: ifne -> 123
      //   90: bipush #65
      //   92: iload #6
      //   94: if_icmpgt -> 112
      //   97: iload #6
      //   99: bipush #91
      //   101: if_icmpge -> 108
      //   104: iconst_1
      //   105: goto -> 113
      //   108: iconst_0
      //   109: goto -> 113
      //   112: iconst_0
      //   113: ifne -> 123
      //   116: iload #6
      //   118: bipush #58
      //   120: if_icmpne -> 127
      //   123: iconst_1
      //   124: goto -> 128
      //   127: iconst_0
      //   128: istore #7
      //   130: iload #7
      //   132: iload #4
      //   134: ifne -> 141
      //   137: iconst_1
      //   138: goto -> 142
      //   141: iconst_0
      //   142: if_icmpne -> 148
      //   145: iload #5
      //   147: ireturn
      //   148: iinc #5, 1
      //   151: goto -> 3
      //   154: iload_3
      //   155: ireturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #554	-> 0
      //   #555	-> 9
      //   #556	-> 17
      //   #557	-> 38
      //   #558	-> 64
      //   #559	-> 90
      //   #560	-> 116
      //   #556	-> 128
      //   #561	-> 130
      //   #554	-> 148
      //   #563	-> 154
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   17	131	6	c	I
      //   130	18	7	dateCharacter	Z
      //   3	151	5	i	I
      //   0	156	0	this	Lokhttp3/Cookie$Companion;
      //   0	156	1	input	Ljava/lang/String;
      //   0	156	2	pos	I
      //   0	156	3	limit	I
      //   0	156	4	invert	Z
    }
    
    private final long parseMaxAge(String s) {
      try {
        long parsed = Long.parseLong(s);
        return (parsed <= 0L) ? Long.MIN_VALUE : parsed;
      } catch (NumberFormatException e) {
        CharSequence charSequence = s;
        if ((new Regex("-?\\d+")).matches(charSequence))
          return StringsKt.startsWith$default(s, "-", false, 2, null) ? Long.MIN_VALUE : Long.MAX_VALUE; 
        throw e;
      } 
    }
    
    private final String parseDomain(String s) {
      if (!(!StringsKt.endsWith$default(s, ".", false, 2, null) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      if (HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, ".")) == null) {
        HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, "."));
        throw new IllegalArgumentException();
      } 
      return HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, "."));
    }
    
    @JvmStatic
    @NotNull
    public final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(headers, "headers");
      List<String> cookieStrings = headers.values("Set-Cookie");
      List<Cookie> cookies = null;
      for (int i = 0, j = cookieStrings.size(); i < j; i++) {
        if (parse(url, cookieStrings.get(i)) == null) {
          parse(url, cookieStrings.get(i));
        } else {
          Cookie cookie;
          if (cookies == null)
            cookies = new ArrayList(); 
          cookies.add(cookie);
        } 
      } 
      if (cookies != null) {
        Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(cookies), "{\n        Collections.un…ableList(cookies)\n      }");
      } else {
      
      } 
      return CollectionsKt.emptyList();
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final String name;
  
  @NotNull
  private final String value;
  
  private final long expiresAt;
  
  @NotNull
  private final String domain;
  
  @NotNull
  private final String path;
  
  private final boolean secure;
  
  private final boolean httpOnly;
  
  private final boolean persistent;
  
  private final boolean hostOnly;
  
  private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
  
  private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
  
  private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
  
  private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
  
  @JvmStatic
  @Nullable
  public static final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
    return Companion.parse(url, setCookie);
  }
  
  @JvmStatic
  @NotNull
  public static final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
    return Companion.parseAll(url, headers);
  }
}
