package okhttp3;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000F\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\004\n\002\020\b\n\000\n\002\020 \n\002\b\025\n\002\020\013\n\002\b\006\n\002\030\002\n\002\b\021\n\002\020\"\n\002\b\r\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\017\030\000 W2\0020\001:\002XWBc\b\000\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\005\032\0020\002\022\006\020\006\032\0020\002\022\006\020\b\032\0020\007\022\f\020\n\032\b\022\004\022\0020\0020\t\022\020\020\013\032\f\022\006\022\004\030\0010\002\030\0010\t\022\b\020\f\032\004\030\0010\002\022\006\020\r\032\0020\002¢\006\004\b\016\020\017J\021\020\022\032\004\030\0010\002H\007¢\006\004\b\020\020\021J\017\020\024\032\0020\002H\007¢\006\004\b\023\020\021J\017\020\026\032\0020\002H\007¢\006\004\b\025\020\021J\025\020\031\032\b\022\004\022\0020\0020\tH\007¢\006\004\b\027\020\030J\021\020\033\032\004\030\0010\002H\007¢\006\004\b\032\020\021J\017\020\035\032\0020\002H\007¢\006\004\b\034\020\021J\032\020 \032\0020\0372\b\020\036\032\004\030\0010\001H\002¢\006\004\b \020!J\021\020\f\032\004\030\0010\002H\007¢\006\004\b\"\020\021J\017\020#\032\0020\007H\026¢\006\004\b#\020$J\017\020\006\032\0020\002H\007¢\006\004\b%\020\021J\r\020'\032\0020&¢\006\004\b'\020(J\027\020'\032\004\030\0010&2\006\020)\032\0020\002¢\006\004\b'\020*J\017\020\005\032\0020\002H\007¢\006\004\b+\020\021J\025\020\n\032\b\022\004\022\0020\0020\tH\007¢\006\004\b,\020\030J\017\020.\032\0020\007H\007¢\006\004\b-\020$J\017\020\b\032\0020\007H\007¢\006\004\b/\020$J\021\0201\032\004\030\0010\002H\007¢\006\004\b0\020\021J\027\0203\032\004\030\0010\0022\006\0202\032\0020\002¢\006\004\b3\0204J\025\0206\032\0020\0022\006\0205\032\0020\007¢\006\004\b6\0207J\025\020;\032\b\022\004\022\0020\00208H\007¢\006\004\b9\020:J\027\020<\032\004\030\0010\0022\006\0205\032\0020\007¢\006\004\b<\0207J\035\020=\032\n\022\006\022\004\030\0010\0020\t2\006\0202\032\0020\002¢\006\004\b=\020>J\017\020@\032\0020\007H\007¢\006\004\b?\020$J\r\020A\032\0020\002¢\006\004\bA\020\021J\027\020B\032\004\030\0010\0002\006\020)\032\0020\002¢\006\004\bB\020CJ\017\020\003\032\0020\002H\007¢\006\004\bD\020\021J\017\020E\032\0020\002H\026¢\006\004\bE\020\021J\017\020I\032\0020FH\007¢\006\004\bG\020HJ\017\020L\032\0020JH\007¢\006\004\b\r\020KJ\017\020M\032\004\030\0010\002¢\006\004\bM\020\021J\017\020G\032\0020FH\007¢\006\004\bN\020HJ\017\020\r\032\0020JH\007¢\006\004\bO\020KJ\017\020\004\032\0020\002H\007¢\006\004\bP\020\021R\023\020\022\032\004\030\0010\0028G¢\006\006\032\004\b\022\020\021R\021\020\024\032\0020\0028G¢\006\006\032\004\b\024\020\021R\021\020\026\032\0020\0028G¢\006\006\032\004\b\026\020\021R\027\020\031\032\b\022\004\022\0020\0020\t8G¢\006\006\032\004\b\031\020\030R\023\020\033\032\004\030\0010\0028G¢\006\006\032\004\b\033\020\021R\021\020\035\032\0020\0028G¢\006\006\032\004\b\035\020\021R\031\020\f\032\004\030\0010\0028\007¢\006\f\n\004\b\f\020Q\032\004\b\f\020\021R\027\020\006\032\0020\0028\007¢\006\f\n\004\b\006\020Q\032\004\b\006\020\021R\027\020R\032\0020\0378\006¢\006\f\n\004\bR\020S\032\004\bR\020TR\027\020\005\032\0020\0028\007¢\006\f\n\004\b\005\020Q\032\004\b\005\020\021R\035\020\n\032\b\022\004\022\0020\0020\t8\007¢\006\f\n\004\b\n\020U\032\004\b\n\020\030R\021\020.\032\0020\0078G¢\006\006\032\004\b.\020$R\027\020\b\032\0020\0078\007¢\006\f\n\004\b\b\020V\032\004\b\b\020$R\023\0201\032\004\030\0010\0028G¢\006\006\032\004\b1\020\021R\036\020\013\032\f\022\006\022\004\030\0010\002\030\0010\t8\002X\004¢\006\006\n\004\b\013\020UR\027\020;\032\b\022\004\022\0020\002088G¢\006\006\032\004\b;\020:R\021\020@\032\0020\0078G¢\006\006\032\004\b@\020$R\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020Q\032\004\b\003\020\021R\024\020\r\032\0020\0028\002X\004¢\006\006\n\004\b\r\020QR\027\020\004\032\0020\0028\007¢\006\f\n\004\b\004\020Q\032\004\b\004\020\021¨\006Y"}, d2 = {"Lokhttp3/HttpUrl;", "", "", "scheme", "username", "password", "host", "", "port", "", "pathSegments", "queryNamesAndValues", "fragment", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "-deprecated_encodedFragment", "()Ljava/lang/String;", "encodedFragment", "-deprecated_encodedPassword", "encodedPassword", "-deprecated_encodedPath", "encodedPath", "-deprecated_encodedPathSegments", "()Ljava/util/List;", "encodedPathSegments", "-deprecated_encodedQuery", "encodedQuery", "-deprecated_encodedUsername", "encodedUsername", "other", "", "equals", "(Ljava/lang/Object;)Z", "-deprecated_fragment", "hashCode", "()I", "-deprecated_host", "Lokhttp3/HttpUrl$Builder;", "newBuilder", "()Lokhttp3/HttpUrl$Builder;", "link", "(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;", "-deprecated_password", "-deprecated_pathSegments", "-deprecated_pathSize", "pathSize", "-deprecated_port", "-deprecated_query", "query", "name", "queryParameter", "(Ljava/lang/String;)Ljava/lang/String;", "index", "queryParameterName", "(I)Ljava/lang/String;", "", "-deprecated_queryParameterNames", "()Ljava/util/Set;", "queryParameterNames", "queryParameterValue", "queryParameterValues", "(Ljava/lang/String;)Ljava/util/List;", "-deprecated_querySize", "querySize", "redact", "resolve", "(Ljava/lang/String;)Lokhttp3/HttpUrl;", "-deprecated_scheme", "toString", "Ljava/net/URI;", "uri", "()Ljava/net/URI;", "toUri", "Ljava/net/URL;", "()Ljava/net/URL;", "toUrl", "topPrivateDomain", "-deprecated_uri", "-deprecated_url", "-deprecated_username", "Ljava/lang/String;", "isHttps", "Z", "()Z", "Ljava/util/List;", "I", "Companion", "Builder", "okhttp"})
public final class HttpUrl {
  public HttpUrl(@NotNull String scheme, @NotNull String username, @NotNull String password, @NotNull String host, int port, @NotNull List<String> pathSegments, @Nullable List<String> queryNamesAndValues, @Nullable String fragment, @NotNull String url) {
    this.scheme = scheme;
    this.username = username;
    this.password = password;
    this.host = host;
    this.port = port;
    this.pathSegments = pathSegments;
    this.queryNamesAndValues = queryNamesAndValues;
    this.fragment = fragment;
    this.url = url;
    this.isHttps = Intrinsics.areEqual(this.scheme, "https");
  }
  
  @JvmName(name = "scheme")
  @NotNull
  public final String scheme() {
    return this.scheme;
  }
  
  @JvmName(name = "username")
  @NotNull
  public final String username() {
    return this.username;
  }
  
  @JvmName(name = "password")
  @NotNull
  public final String password() {
    return this.password;
  }
  
  @JvmName(name = "host")
  @NotNull
  public final String host() {
    return this.host;
  }
  
  @JvmName(name = "port")
  public final int port() {
    return this.port;
  }
  
  @JvmName(name = "pathSegments")
  @NotNull
  public final List<String> pathSegments() {
    return this.pathSegments;
  }
  
  @JvmName(name = "fragment")
  @Nullable
  public final String fragment() {
    return this.fragment;
  }
  
  public final boolean isHttps() {
    return this.isHttps;
  }
  
  @JvmName(name = "url")
  @NotNull
  public final URL url() {
    try {
      return new URL(this.url);
    } catch (MalformedURLException e) {
      throw new RuntimeException((Throwable)e);
    } 
  }
  
  @JvmName(name = "uri")
  @NotNull
  public final URI uri() {
    URI uRI;
    String uri = newBuilder().reencodeForUri$okhttp().toString();
    try {
      uRI = new URI(uri);
    } catch (URISyntaxException e) {
      URI uRI1;
      try {
        String str1 = uri;
        Regex regex = new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]");
        String str2 = "", stripped = regex.replace(str1, str2);
        uRI1 = URI.create(stripped);
      } catch (Exception e1) {
        throw new RuntimeException((Throwable)e);
      } 
      Intrinsics.checkNotNullExpressionValue(uRI1, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
      uRI = uRI1;
    } 
    return uRI;
  }
  
  @JvmName(name = "encodedUsername")
  @NotNull
  public final String encodedUsername() {
    if ((this.username.length() == 0))
      return ""; 
    int usernameStart = this.scheme.length() + 3;
    int usernameEnd = Util.delimiterOffset(this.url, ":@", usernameStart, this.url.length());
    Intrinsics.checkNotNullExpressionValue(this.url.substring(usernameStart, usernameEnd), "this as java.lang.String…ing(startIndex, endIndex)");
    return this.url.substring(usernameStart, usernameEnd);
  }
  
  @JvmName(name = "encodedPassword")
  @NotNull
  public final String encodedPassword() {
    if ((this.password.length() == 0))
      return ""; 
    int passwordStart = StringsKt.indexOf$default(this.url, ':', this.scheme.length() + 3, false, 4, null) + 1;
    int passwordEnd = StringsKt.indexOf$default(this.url, '@', 0, false, 6, null);
    Intrinsics.checkNotNullExpressionValue(this.url.substring(passwordStart, passwordEnd), "this as java.lang.String…ing(startIndex, endIndex)");
    return this.url.substring(passwordStart, passwordEnd);
  }
  
  @JvmName(name = "pathSize")
  public final int pathSize() {
    return this.pathSegments.size();
  }
  
  @JvmName(name = "encodedPath")
  @NotNull
  public final String encodedPath() {
    int pathStart = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
    int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
    Intrinsics.checkNotNullExpressionValue(this.url.substring(pathStart, pathEnd), "this as java.lang.String…ing(startIndex, endIndex)");
    return this.url.substring(pathStart, pathEnd);
  }
  
  @JvmName(name = "encodedPathSegments")
  @NotNull
  public final List<String> encodedPathSegments() {
    int pathStart = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
    int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
    List<String> result = new ArrayList();
    int i = pathStart;
    while (i < pathEnd) {
      i++;
      int segmentEnd = Util.delimiterOffset(this.url, '/', i, pathEnd);
      Intrinsics.checkNotNullExpressionValue(this.url.substring(i, segmentEnd), "this as java.lang.String…ing(startIndex, endIndex)");
      result.add(this.url.substring(i, segmentEnd));
      i = segmentEnd;
    } 
    return result;
  }
  
  @JvmName(name = "encodedQuery")
  @Nullable
  public final String encodedQuery() {
    if (this.queryNamesAndValues == null)
      return null; 
    int queryStart = StringsKt.indexOf$default(this.url, '?', 0, false, 6, null) + 1;
    int queryEnd = Util.delimiterOffset(this.url, '#', queryStart, this.url.length());
    Intrinsics.checkNotNullExpressionValue(this.url.substring(queryStart, queryEnd), "this as java.lang.String…ing(startIndex, endIndex)");
    return this.url.substring(queryStart, queryEnd);
  }
  
  @JvmName(name = "query")
  @Nullable
  public final String query() {
    if (this.queryNamesAndValues == null)
      return null; 
    StringBuilder result = new StringBuilder();
    Companion.toQueryString$okhttp(this.queryNamesAndValues, result);
    return result.toString();
  }
  
  @JvmName(name = "querySize")
  public final int querySize() {
    return (this.queryNamesAndValues != null) ? (this.queryNamesAndValues.size() / 2) : 0;
  }
  
  @Nullable
  public final String queryParameter(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    if (this.queryNamesAndValues == null)
      return null; 
    IntProgression intProgression = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2);
    int i = intProgression.getFirst(), j = intProgression.getLast(), k = intProgression.getStep();
    if ((k > 0 && i <= j) || (k < 0 && j <= i))
      while (true) {
        if (Intrinsics.areEqual(name, this.queryNamesAndValues.get(i)))
          return this.queryNamesAndValues.get(i + 1); 
        if (i != j) {
          i += k;
          continue;
        } 
        break;
      }  
    return null;
  }
  
  @JvmName(name = "queryParameterNames")
  @NotNull
  public final Set<String> queryParameterNames() {
    if (this.queryNamesAndValues == null)
      return SetsKt.emptySet(); 
    LinkedHashSet<String> result = new LinkedHashSet();
    IntProgression intProgression = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2);
    int i = intProgression.getFirst(), j = intProgression.getLast(), k = intProgression.getStep();
    if ((k > 0 && i <= j) || (k < 0 && j <= i))
      while (true) {
        Intrinsics.checkNotNull(this.queryNamesAndValues.get(i));
        result.add(this.queryNamesAndValues.get(i));
        if (i != j) {
          i += k;
          continue;
        } 
        break;
      }  
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableSet(result), "unmodifiableSet(result)");
    return Collections.unmodifiableSet(result);
  }
  
  @NotNull
  public final List<String> queryParameterValues(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    if (this.queryNamesAndValues == null)
      return CollectionsKt.emptyList(); 
    List<?> result = new ArrayList();
    IntProgression intProgression = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2);
    int i = intProgression.getFirst(), j = intProgression.getLast(), k = intProgression.getStep();
    if ((k > 0 && i <= j) || (k < 0 && j <= i))
      while (true) {
        if (Intrinsics.areEqual(name, this.queryNamesAndValues.get(i)))
          result.add(this.queryNamesAndValues.get(i + 1)); 
        if (i != j) {
          i += k;
          continue;
        } 
        break;
      }  
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(result), "unmodifiableList(result)");
    return (List)Collections.unmodifiableList(result);
  }
  
  @NotNull
  public final String queryParameterName(int index) {
    if (this.queryNamesAndValues == null)
      throw new IndexOutOfBoundsException(); 
    Intrinsics.checkNotNull(this.queryNamesAndValues.get(index * 2));
    return this.queryNamesAndValues.get(index * 2);
  }
  
  @Nullable
  public final String queryParameterValue(int index) {
    if (this.queryNamesAndValues == null)
      throw new IndexOutOfBoundsException(); 
    return this.queryNamesAndValues.get(index * 2 + 1);
  }
  
  @JvmName(name = "encodedFragment")
  @Nullable
  public final String encodedFragment() {
    if (this.fragment == null)
      return null; 
    int fragmentStart = StringsKt.indexOf$default(this.url, '#', 0, false, 6, null) + 1;
    Intrinsics.checkNotNullExpressionValue(this.url.substring(fragmentStart), "this as java.lang.String).substring(startIndex)");
    return this.url.substring(fragmentStart);
  }
  
  @NotNull
  public final String redact() {
    Intrinsics.checkNotNull(newBuilder("/..."));
    return newBuilder("/...")
      .username("")
      .password("")
      .build()
      .toString();
  }
  
  @Nullable
  public final HttpUrl resolve(@NotNull String link) {
    Intrinsics.checkNotNullParameter(link, "link");
    newBuilder(link);
    return (newBuilder(link) != null) ? newBuilder(link).build() : null;
  }
  
  @NotNull
  public final Builder newBuilder() {
    Builder result = new Builder();
    result.setScheme$okhttp(this.scheme);
    result.setEncodedUsername$okhttp(encodedUsername());
    result.setEncodedPassword$okhttp(encodedPassword());
    result.setHost$okhttp(this.host);
    result.setPort$okhttp((this.port != Companion.defaultPort(this.scheme)) ? this.port : -1);
    result.getEncodedPathSegments$okhttp().clear();
    result.getEncodedPathSegments$okhttp().addAll(encodedPathSegments());
    result.encodedQuery(encodedQuery());
    result.setEncodedFragment$okhttp(encodedFragment());
    return result;
  }
  
  @Nullable
  public final Builder newBuilder(@NotNull String link) {
    Builder builder;
    Intrinsics.checkNotNullParameter(link, "link");
    try {
      builder = (new Builder()).parse$okhttp(this, link);
    } catch (IllegalArgumentException _) {
      builder = null;
    } 
    return builder;
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof HttpUrl && Intrinsics.areEqual(((HttpUrl)other).url, this.url));
  }
  
  public int hashCode() {
    return this.url.hashCode();
  }
  
  @NotNull
  public String toString() {
    return this.url;
  }
  
  @Nullable
  public final String topPrivateDomain() {
    return Util.canParseAsIpAddress(this.host) ? 
      null : 
      
      PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(this.host);
  }
  
  @Deprecated(message = "moved to toUrl()", replaceWith = @ReplaceWith(expression = "toUrl()", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_url")
  @NotNull
  public final URL -deprecated_url() {
    return url();
  }
  
  @Deprecated(message = "moved to toUri()", replaceWith = @ReplaceWith(expression = "toUri()", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_uri")
  @NotNull
  public final URI -deprecated_uri() {
    return uri();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_scheme")
  @NotNull
  public final String -deprecated_scheme() {
    return this.scheme;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedUsername", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_encodedUsername")
  @NotNull
  public final String -deprecated_encodedUsername() {
    return encodedUsername();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "username", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_username")
  @NotNull
  public final String -deprecated_username() {
    return this.username;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPassword", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_encodedPassword")
  @NotNull
  public final String -deprecated_encodedPassword() {
    return encodedPassword();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "password", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_password")
  @NotNull
  public final String -deprecated_password() {
    return this.password;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "host", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_host")
  @NotNull
  public final String -deprecated_host() {
    return this.host;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "port", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_port")
  public final int -deprecated_port() {
    return this.port;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSize", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_pathSize")
  public final int -deprecated_pathSize() {
    return pathSize();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPath", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_encodedPath")
  @NotNull
  public final String -deprecated_encodedPath() {
    return encodedPath();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPathSegments", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_encodedPathSegments")
  @NotNull
  public final List<String> -deprecated_encodedPathSegments() {
    return encodedPathSegments();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSegments", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_pathSegments")
  @NotNull
  public final List<String> -deprecated_pathSegments() {
    return this.pathSegments;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedQuery", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_encodedQuery")
  @Nullable
  public final String -deprecated_encodedQuery() {
    return encodedQuery();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "query", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_query")
  @Nullable
  public final String -deprecated_query() {
    return query();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "querySize", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_querySize")
  public final int -deprecated_querySize() {
    return querySize();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "queryParameterNames", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_queryParameterNames")
  @NotNull
  public final Set<String> -deprecated_queryParameterNames() {
    return queryParameterNames();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedFragment", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_encodedFragment")
  @Nullable
  public final String -deprecated_encodedFragment() {
    return encodedFragment();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "fragment", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_fragment")
  @Nullable
  public final String -deprecated_fragment() {
    return this.fragment;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\r\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\022\n\002\020\002\n\002\b$\n\002\020!\n\002\b\023\030\000 e2\0020\001:\001eB\007¢\006\004\b\002\020\003J\025\020\006\032\0020\0002\006\020\005\032\0020\004¢\006\004\b\006\020\007J\025\020\t\032\0020\0002\006\020\b\032\0020\004¢\006\004\b\t\020\007J\037\020\f\032\0020\0002\006\020\n\032\0020\0042\b\020\013\032\004\030\0010\004¢\006\004\b\f\020\rJ\025\020\017\032\0020\0002\006\020\016\032\0020\004¢\006\004\b\017\020\007J\025\020\021\032\0020\0002\006\020\020\032\0020\004¢\006\004\b\021\020\007J\037\020\021\032\0020\0002\006\020\020\032\0020\0042\006\020\023\032\0020\022H\002¢\006\004\b\021\020\024J\037\020\027\032\0020\0002\006\020\025\032\0020\0042\b\020\026\032\004\030\0010\004¢\006\004\b\027\020\rJ\r\020\031\032\0020\030¢\006\004\b\031\020\032J\017\020\034\032\0020\033H\002¢\006\004\b\034\020\035J\027\020\036\032\0020\0002\b\020\036\032\004\030\0010\004¢\006\004\b\036\020\007J\025\020\037\032\0020\0002\006\020\037\032\0020\004¢\006\004\b\037\020\007J\025\020 \032\0020\0002\006\020 \032\0020\004¢\006\004\b \020\007J\027\020!\032\0020\0002\b\020!\032\004\030\0010\004¢\006\004\b!\020\007J\025\020\"\032\0020\0002\006\020\"\032\0020\004¢\006\004\b\"\020\007J\027\020#\032\0020\0002\b\020#\032\004\030\0010\004¢\006\004\b#\020\007J\025\020$\032\0020\0002\006\020$\032\0020\004¢\006\004\b$\020\007J\027\020&\032\0020\0222\006\020%\032\0020\004H\002¢\006\004\b&\020'J\027\020(\032\0020\0222\006\020%\032\0020\004H\002¢\006\004\b(\020'J!\020,\032\0020\0002\b\020)\032\004\030\0010\0302\006\020%\032\0020\004H\000¢\006\004\b*\020+J\025\020-\032\0020\0002\006\020-\032\0020\004¢\006\004\b-\020\007J\017\020/\032\0020.H\002¢\006\004\b/\020\003J\025\0200\032\0020\0002\006\0200\032\0020\033¢\006\004\b0\0201J7\0205\032\0020.2\006\020%\032\0020\0042\006\0202\032\0020\0332\006\0203\032\0020\0332\006\0204\032\0020\0222\006\020\023\032\0020\022H\002¢\006\004\b5\0206J\027\0207\032\0020\0002\b\0207\032\004\030\0010\004¢\006\004\b7\020\007J\017\020:\032\0020\000H\000¢\006\004\b8\0209J\027\020<\032\0020.2\006\020;\032\0020\004H\002¢\006\004\b<\020=J\025\020>\032\0020\0002\006\020\n\032\0020\004¢\006\004\b>\020\007J\025\020?\032\0020\0002\006\020\025\032\0020\004¢\006\004\b?\020\007J\025\020A\032\0020\0002\006\020@\032\0020\033¢\006\004\bA\0201J'\020C\032\0020.2\006\020%\032\0020\0042\006\020B\032\0020\0332\006\0203\032\0020\033H\002¢\006\004\bC\020DJ\025\020E\032\0020\0002\006\020E\032\0020\004¢\006\004\bE\020\007J\035\020F\032\0020\0002\006\020@\032\0020\0332\006\020\005\032\0020\004¢\006\004\bF\020GJ\037\020H\032\0020\0002\006\020\n\032\0020\0042\b\020\013\032\004\030\0010\004¢\006\004\bH\020\rJ\035\020I\032\0020\0002\006\020@\032\0020\0332\006\020\016\032\0020\004¢\006\004\bI\020GJ\037\020J\032\0020\0002\006\020\025\032\0020\0042\b\020\026\032\004\030\0010\004¢\006\004\bJ\020\rJ\017\020K\032\0020\004H\026¢\006\004\bK\020LJ\025\020M\032\0020\0002\006\020M\032\0020\004¢\006\004\bM\020\007R$\020\036\032\004\030\0010\0048\000@\000X\016¢\006\022\n\004\b\036\020N\032\004\bO\020L\"\004\bP\020=R\"\020\037\032\0020\0048\000@\000X\016¢\006\022\n\004\b\037\020N\032\004\bQ\020L\"\004\bR\020=R \020\b\032\b\022\004\022\0020\0040S8\000X\004¢\006\f\n\004\b\b\020T\032\004\bU\020VR,\020W\032\f\022\006\022\004\030\0010\004\030\0010S8\000@\000X\016¢\006\022\n\004\bW\020T\032\004\bX\020V\"\004\bY\020ZR\"\020\"\032\0020\0048\000@\000X\016¢\006\022\n\004\b\"\020N\032\004\b[\020L\"\004\b\\\020=R$\020$\032\004\030\0010\0048\000@\000X\016¢\006\022\n\004\b$\020N\032\004\b]\020L\"\004\b^\020=R\"\0200\032\0020\0338\000@\000X\016¢\006\022\n\004\b0\020_\032\004\b`\020\035\"\004\ba\020bR$\020E\032\004\030\0010\0048\000@\000X\016¢\006\022\n\004\bE\020N\032\004\bc\020L\"\004\bd\020=¨\006f"}, d2 = {"Lokhttp3/HttpUrl$Builder;", "", "<init>", "()V", "", "encodedPathSegment", "addEncodedPathSegment", "(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;", "encodedPathSegments", "addEncodedPathSegments", "encodedName", "encodedValue", "addEncodedQueryParameter", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;", "pathSegment", "addPathSegment", "pathSegments", "addPathSegments", "", "alreadyEncoded", "(Ljava/lang/String;Z)Lokhttp3/HttpUrl$Builder;", "name", "value", "addQueryParameter", "Lokhttp3/HttpUrl;", "build", "()Lokhttp3/HttpUrl;", "", "effectivePort", "()I", "encodedFragment", "encodedPassword", "encodedPath", "encodedQuery", "encodedUsername", "fragment", "host", "input", "isDot", "(Ljava/lang/String;)Z", "isDotDot", "base", "parse$okhttp", "(Lokhttp3/HttpUrl;Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;", "parse", "password", "", "pop", "port", "(I)Lokhttp3/HttpUrl$Builder;", "pos", "limit", "addTrailingSlash", "push", "(Ljava/lang/String;IIZZ)V", "query", "reencodeForUri$okhttp", "()Lokhttp3/HttpUrl$Builder;", "reencodeForUri", "canonicalName", "removeAllCanonicalQueryParameters", "(Ljava/lang/String;)V", "removeAllEncodedQueryParameters", "removeAllQueryParameters", "index", "removePathSegment", "startPos", "resolvePath", "(Ljava/lang/String;II)V", "scheme", "setEncodedPathSegment", "(ILjava/lang/String;)Lokhttp3/HttpUrl$Builder;", "setEncodedQueryParameter", "setPathSegment", "setQueryParameter", "toString", "()Ljava/lang/String;", "username", "Ljava/lang/String;", "getEncodedFragment$okhttp", "setEncodedFragment$okhttp", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "", "Ljava/util/List;", "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "getHost$okhttp", "setHost$okhttp", "I", "getPort$okhttp", "setPort$okhttp", "(I)V", "getScheme$okhttp", "setScheme$okhttp", "Companion", "okhttp"})
  @SourceDebugExtension({"SMAP\nHttpUrl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpUrl.kt\nokhttp3/HttpUrl$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1869:1\n1#2:1870\n1549#3:1871\n1620#3,3:1872\n1549#3:1875\n1620#3,3:1876\n*S KotlinDebug\n*F\n+ 1 HttpUrl.kt\nokhttp3/HttpUrl$Builder\n*L\n1180#1:1871\n1180#1:1872,3\n1181#1:1875\n1181#1:1876,3\n*E\n"})
  public static final class Builder {
    @NotNull
    public static final Companion Companion = new Companion(null);
    
    @Nullable
    private String scheme;
    
    @NotNull
    private String encodedUsername;
    
    @NotNull
    private String encodedPassword;
    
    @Nullable
    private String host;
    
    private int port;
    
    @NotNull
    private final List<String> encodedPathSegments;
    
    @Nullable
    private List<String> encodedQueryNamesAndValues;
    
    @Nullable
    private String encodedFragment;
    
    @NotNull
    public static final String INVALID_HOST = "Invalid URL host";
    
    public Builder() {
      this.encodedUsername = "";
      this.encodedPassword = "";
      this.port = -1;
      this.encodedPathSegments = new ArrayList<>();
      this.encodedPathSegments.add("");
    }
    
    @Nullable
    public final String getScheme$okhttp() {
      return this.scheme;
    }
    
    public final void setScheme$okhttp(@Nullable String <set-?>) {
      this.scheme = <set-?>;
    }
    
    @NotNull
    public final String getEncodedUsername$okhttp() {
      return this.encodedUsername;
    }
    
    public final void setEncodedUsername$okhttp(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.encodedUsername = <set-?>;
    }
    
    @NotNull
    public final String getEncodedPassword$okhttp() {
      return this.encodedPassword;
    }
    
    public final void setEncodedPassword$okhttp(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.encodedPassword = <set-?>;
    }
    
    @Nullable
    public final String getHost$okhttp() {
      return this.host;
    }
    
    public final void setHost$okhttp(@Nullable String <set-?>) {
      this.host = <set-?>;
    }
    
    public final int getPort$okhttp() {
      return this.port;
    }
    
    public final void setPort$okhttp(int <set-?>) {
      this.port = <set-?>;
    }
    
    @NotNull
    public final List<String> getEncodedPathSegments$okhttp() {
      return this.encodedPathSegments;
    }
    
    @Nullable
    public final List<String> getEncodedQueryNamesAndValues$okhttp() {
      return this.encodedQueryNamesAndValues;
    }
    
    public final void setEncodedQueryNamesAndValues$okhttp(@Nullable List<String> <set-?>) {
      this.encodedQueryNamesAndValues = <set-?>;
    }
    
    @Nullable
    public final String getEncodedFragment$okhttp() {
      return this.encodedFragment;
    }
    
    public final void setEncodedFragment$okhttp(@Nullable String <set-?>) {
      this.encodedFragment = <set-?>;
    }
    
    @NotNull
    public final Builder scheme(@NotNull String scheme) {
      Intrinsics.checkNotNullParameter(scheme, "scheme");
      Builder builder1 = this, $this$scheme_u24lambda_u240 = builder1;
      int $i$a$-apply-HttpUrl$Builder$scheme$1 = 0;
      if (StringsKt.equals(scheme, "http", true)) {
        $this$scheme_u24lambda_u240.scheme = "http";
      } else if (StringsKt.equals(scheme, "https", true)) {
        $this$scheme_u24lambda_u240.scheme = "https";
      } else {
        throw new IllegalArgumentException("unexpected scheme: " + scheme);
      } 
      return builder1;
    }
    
    @NotNull
    public final Builder username(@NotNull String username) {
      Intrinsics.checkNotNullParameter(username, "username");
      Builder builder1 = this, $this$username_u24lambda_u241 = builder1;
      int $i$a$-apply-HttpUrl$Builder$username$1 = 0;
      $this$username_u24lambda_u241.encodedUsername = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
      return builder1;
    }
    
    @NotNull
    public final Builder encodedUsername(@NotNull String encodedUsername) {
      Intrinsics.checkNotNullParameter(encodedUsername, "encodedUsername");
      Builder builder1 = this, $this$encodedUsername_u24lambda_u242 = builder1;
      int $i$a$-apply-HttpUrl$Builder$encodedUsername$1 = 0;
      $this$encodedUsername_u24lambda_u242.encodedUsername = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedUsername, 0, 0, 
          " \"':;<=>@[]^`{}|/\\?#", 
          true, false, false, false, null, 243, null);
      return builder1;
    }
    
    @NotNull
    public final Builder password(@NotNull String password) {
      Intrinsics.checkNotNullParameter(password, "password");
      Builder builder1 = this, $this$password_u24lambda_u243 = builder1;
      int $i$a$-apply-HttpUrl$Builder$password$1 = 0;
      $this$password_u24lambda_u243.encodedPassword = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
      return builder1;
    }
    
    @NotNull
    public final Builder encodedPassword(@NotNull String encodedPassword) {
      Intrinsics.checkNotNullParameter(encodedPassword, "encodedPassword");
      Builder builder1 = this, $this$encodedPassword_u24lambda_u244 = builder1;
      int $i$a$-apply-HttpUrl$Builder$encodedPassword$1 = 0;
      $this$encodedPassword_u24lambda_u244.encodedPassword = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedPassword, 0, 0, 
          " \"':;<=>@[]^`{}|/\\?#", 
          true, false, false, false, null, 243, null);
      return builder1;
    }
    
    @NotNull
    public final Builder host(@NotNull String host) {
      String encoded;
      Intrinsics.checkNotNullParameter(host, "host");
      Builder builder1 = this, $this$host_u24lambda_u245 = builder1;
      int $i$a$-apply-HttpUrl$Builder$host$1 = 0;
      if (HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, host, 0, 0, false, 7, null)) == null) {
        HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, host, 0, 0, false, 7, null));
        throw new IllegalArgumentException(
            "unexpected host: " + host);
      } 
      $this$host_u24lambda_u245.host = encoded;
      return builder1;
    }
    
    @NotNull
    public final Builder port(int port) {
      Builder builder1 = this, $this$port_u24lambda_u247 = builder1;
      int $i$a$-apply-HttpUrl$Builder$port$1 = 0;
      if (!((1 <= port) ? ((port < 65536) ? 1 : 0) : 0)) {
        int $i$a$-require-HttpUrl$Builder$port$1$1 = 0;
        String str = "unexpected port: " + port;
        throw new IllegalArgumentException(str.toString());
      } 
      $this$port_u24lambda_u247.port = port;
      return builder1;
    }
    
    private final int effectivePort() {
      Intrinsics.checkNotNull(this.scheme);
      return (this.port != -1) ? this.port : HttpUrl.Companion.defaultPort(this.scheme);
    }
    
    @NotNull
    public final Builder addPathSegment(@NotNull String pathSegment) {
      Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
      Builder builder1 = this, $this$addPathSegment_u24lambda_u248 = builder1;
      int $i$a$-apply-HttpUrl$Builder$addPathSegment$1 = 0;
      $this$addPathSegment_u24lambda_u248.push(pathSegment, 0, pathSegment.length(), false, false);
      return builder1;
    }
    
    @NotNull
    public final Builder addPathSegments(@NotNull String pathSegments) {
      Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
      return addPathSegments(pathSegments, false);
    }
    
    @NotNull
    public final Builder addEncodedPathSegment(@NotNull String encodedPathSegment) {
      Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
      Builder builder1 = this, $this$addEncodedPathSegment_u24lambda_u249 = builder1;
      int $i$a$-apply-HttpUrl$Builder$addEncodedPathSegment$1 = 0;
      $this$addEncodedPathSegment_u24lambda_u249.push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
      return builder1;
    }
    
    @NotNull
    public final Builder addEncodedPathSegments(@NotNull String encodedPathSegments) {
      Intrinsics.checkNotNullParameter(encodedPathSegments, "encodedPathSegments");
      return addPathSegments(encodedPathSegments, true);
    }
    
    private final Builder addPathSegments(String pathSegments, boolean alreadyEncoded) {
      Builder builder1 = this, $this$addPathSegments_u24lambda_u2410 = builder1;
      int $i$a$-apply-HttpUrl$Builder$addPathSegments$1 = 0;
      int offset = 0;
      while (true) {
        int segmentEnd = Util.delimiterOffset(pathSegments, "/\\", offset, pathSegments.length());
        boolean addTrailingSlash = (segmentEnd < pathSegments.length());
        $this$addPathSegments_u24lambda_u2410.push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
        offset = segmentEnd + 1;
        if (offset > pathSegments.length())
          return builder1; 
      } 
    }
    
    @NotNull
    public final Builder setPathSegment(int index, @NotNull String pathSegment) {
      Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
      Builder builder1 = this, $this$setPathSegment_u24lambda_u2412 = builder1;
      int $i$a$-apply-HttpUrl$Builder$setPathSegment$1 = 0;
      String canonicalPathSegment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, pathSegment, 0, 0, " \"<>^`{}|/\\?#", false, false, false, false, null, 251, null);
      if (!((!$this$setPathSegment_u24lambda_u2412.isDot(canonicalPathSegment) && !$this$setPathSegment_u24lambda_u2412.isDotDot(canonicalPathSegment)) ? 1 : 0)) {
        int $i$a$-require-HttpUrl$Builder$setPathSegment$1$1 = 0;
        String str = "unexpected path segment: " + pathSegment;
        throw new IllegalArgumentException(str.toString());
      } 
      $this$setPathSegment_u24lambda_u2412.encodedPathSegments.set(index, canonicalPathSegment);
      return builder1;
    }
    
    @NotNull
    public final Builder setEncodedPathSegment(int index, @NotNull String encodedPathSegment) {
      Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
      Builder builder1 = this, $this$setEncodedPathSegment_u24lambda_u2414 = builder1;
      int $i$a$-apply-HttpUrl$Builder$setEncodedPathSegment$1 = 0;
      String canonicalPathSegment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedPathSegment, 0, 0, " \"<>^`{}|/\\?#", true, false, false, false, null, 243, null);
      $this$setEncodedPathSegment_u24lambda_u2414.encodedPathSegments.set(index, canonicalPathSegment);
      if (!((!$this$setEncodedPathSegment_u24lambda_u2414.isDot(canonicalPathSegment) && !$this$setEncodedPathSegment_u24lambda_u2414.isDotDot(canonicalPathSegment)) ? 1 : 0)) {
        int $i$a$-require-HttpUrl$Builder$setEncodedPathSegment$1$1 = 0;
        String str = "unexpected path segment: " + encodedPathSegment;
        throw new IllegalArgumentException(str.toString());
      } 
      return builder1;
    }
    
    @NotNull
    public final Builder removePathSegment(int index) {
      Builder builder1 = this, $this$removePathSegment_u24lambda_u2415 = builder1;
      int $i$a$-apply-HttpUrl$Builder$removePathSegment$1 = 0;
      $this$removePathSegment_u24lambda_u2415.encodedPathSegments.remove(index);
      if ($this$removePathSegment_u24lambda_u2415.encodedPathSegments.isEmpty())
        $this$removePathSegment_u24lambda_u2415.encodedPathSegments.add(""); 
      return builder1;
    }
    
    @NotNull
    public final Builder encodedPath(@NotNull String encodedPath) {
      Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
      Builder builder1 = this, $this$encodedPath_u24lambda_u2417 = builder1;
      int $i$a$-apply-HttpUrl$Builder$encodedPath$1 = 0;
      if (!StringsKt.startsWith$default(encodedPath, "/", false, 2, null)) {
        int $i$a$-require-HttpUrl$Builder$encodedPath$1$1 = 0;
        String str = "unexpected encodedPath: " + encodedPath;
        throw new IllegalArgumentException(str.toString());
      } 
      $this$encodedPath_u24lambda_u2417.resolvePath(encodedPath, 0, encodedPath.length());
      return builder1;
    }
    
    @NotNull
    public final Builder query(@Nullable String query) {
      // Byte code:
      //   0: aload_0
      //   1: astore_2
      //   2: aload_2
      //   3: checkcast okhttp3/HttpUrl$Builder
      //   6: astore_3
      //   7: iconst_0
      //   8: istore #4
      //   10: aload_3
      //   11: aload_1
      //   12: ifnull -> 54
      //   15: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
      //   18: aload_1
      //   19: iconst_0
      //   20: iconst_0
      //   21: ldc_w ' "'<>#'
      //   24: iconst_0
      //   25: iconst_0
      //   26: iconst_1
      //   27: iconst_0
      //   28: aconst_null
      //   29: sipush #219
      //   32: aconst_null
      //   33: invokestatic canonicalize$okhttp$default : (Lokhttp3/HttpUrl$Companion;Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;
      //   36: astore #5
      //   38: aload #5
      //   40: ifnull -> 54
      //   43: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
      //   46: aload #5
      //   48: invokevirtual toQueryNamesAndValues$okhttp : (Ljava/lang/String;)Ljava/util/List;
      //   51: goto -> 55
      //   54: aconst_null
      //   55: putfield encodedQueryNamesAndValues : Ljava/util/List;
      //   58: nop
      //   59: aload_2
      //   60: checkcast okhttp3/HttpUrl$Builder
      //   63: areturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #1038	-> 0
      //   #1039	-> 10
      //   #1040	-> 21
      //   #1039	-> 24
      //   #1041	-> 26
      //   #1039	-> 27
      //   #1042	-> 38
      //   #1039	-> 46
      //   #1042	-> 48
      //   #1039	-> 54
      //   #1043	-> 58
      //   #1038	-> 59
      //   #1043	-> 63
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   10	49	4	$i$a$-apply-HttpUrl$Builder$query$1	I
      //   7	52	3	$this$query_u24lambda_u2418	Lokhttp3/HttpUrl$Builder;
      //   0	64	0	this	Lokhttp3/HttpUrl$Builder;
      //   0	64	1	query	Ljava/lang/String;
    }
    
    @NotNull
    public final Builder encodedQuery(@Nullable String encodedQuery) {
      // Byte code:
      //   0: aload_0
      //   1: astore_2
      //   2: aload_2
      //   3: checkcast okhttp3/HttpUrl$Builder
      //   6: astore_3
      //   7: iconst_0
      //   8: istore #4
      //   10: aload_3
      //   11: aload_1
      //   12: ifnull -> 54
      //   15: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
      //   18: aload_1
      //   19: iconst_0
      //   20: iconst_0
      //   21: ldc_w ' "'<>#'
      //   24: iconst_1
      //   25: iconst_0
      //   26: iconst_1
      //   27: iconst_0
      //   28: aconst_null
      //   29: sipush #211
      //   32: aconst_null
      //   33: invokestatic canonicalize$okhttp$default : (Lokhttp3/HttpUrl$Companion;Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;
      //   36: astore #5
      //   38: aload #5
      //   40: ifnull -> 54
      //   43: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
      //   46: aload #5
      //   48: invokevirtual toQueryNamesAndValues$okhttp : (Ljava/lang/String;)Ljava/util/List;
      //   51: goto -> 55
      //   54: aconst_null
      //   55: putfield encodedQueryNamesAndValues : Ljava/util/List;
      //   58: nop
      //   59: aload_2
      //   60: checkcast okhttp3/HttpUrl$Builder
      //   63: areturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #1045	-> 0
      //   #1046	-> 10
      //   #1047	-> 21
      //   #1048	-> 24
      //   #1046	-> 25
      //   #1049	-> 26
      //   #1046	-> 27
      //   #1050	-> 38
      //   #1046	-> 46
      //   #1050	-> 48
      //   #1046	-> 54
      //   #1051	-> 58
      //   #1045	-> 59
      //   #1051	-> 63
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   10	49	4	$i$a$-apply-HttpUrl$Builder$encodedQuery$1	I
      //   7	52	3	$this$encodedQuery_u24lambda_u2419	Lokhttp3/HttpUrl$Builder;
      //   0	64	0	this	Lokhttp3/HttpUrl$Builder;
      //   0	64	1	encodedQuery	Ljava/lang/String;
    }
    
    @NotNull
    public final Builder addQueryParameter(@NotNull String name, @Nullable String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$addQueryParameter_u24lambda_u2420 = builder1;
      int $i$a$-apply-HttpUrl$Builder$addQueryParameter$1 = 0;
      if ($this$addQueryParameter_u24lambda_u2420.encodedQueryNamesAndValues == null)
        $this$addQueryParameter_u24lambda_u2420.encodedQueryNamesAndValues = new ArrayList<>(); 
      Intrinsics.checkNotNull($this$addQueryParameter_u24lambda_u2420.encodedQueryNamesAndValues);
      $this$addQueryParameter_u24lambda_u2420.encodedQueryNamesAndValues.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219, null));
      Intrinsics.checkNotNull($this$addQueryParameter_u24lambda_u2420.encodedQueryNamesAndValues);
      $this$addQueryParameter_u24lambda_u2420.encodedQueryNamesAndValues.add((value != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219, null) : null);
      return builder1;
    }
    
    @NotNull
    public final Builder addEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
      Intrinsics.checkNotNullParameter(encodedName, "encodedName");
      Builder builder1 = this, $this$addEncodedQueryParameter_u24lambda_u2421 = builder1;
      int $i$a$-apply-HttpUrl$Builder$addEncodedQueryParameter$1 = 0;
      if ($this$addEncodedQueryParameter_u24lambda_u2421.encodedQueryNamesAndValues == null)
        $this$addEncodedQueryParameter_u24lambda_u2421.encodedQueryNamesAndValues = new ArrayList<>(); 
      Intrinsics.checkNotNull($this$addEncodedQueryParameter_u24lambda_u2421.encodedQueryNamesAndValues);
      $this$addEncodedQueryParameter_u24lambda_u2421.encodedQueryNamesAndValues.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedName, 0, 0, " \"'<>#&=", true, false, true, false, null, 211, null));
      Intrinsics.checkNotNull($this$addEncodedQueryParameter_u24lambda_u2421.encodedQueryNamesAndValues);
      $this$addEncodedQueryParameter_u24lambda_u2421.encodedQueryNamesAndValues.add((encodedValue != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedValue, 0, 0, " \"'<>#&=", true, false, true, false, null, 211, null) : null);
      return builder1;
    }
    
    @NotNull
    public final Builder setQueryParameter(@NotNull String name, @Nullable String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$setQueryParameter_u24lambda_u2422 = builder1;
      int $i$a$-apply-HttpUrl$Builder$setQueryParameter$1 = 0;
      $this$setQueryParameter_u24lambda_u2422.removeAllQueryParameters(name);
      $this$setQueryParameter_u24lambda_u2422.addQueryParameter(name, value);
      return builder1;
    }
    
    @NotNull
    public final Builder setEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
      Intrinsics.checkNotNullParameter(encodedName, "encodedName");
      Builder builder1 = this, $this$setEncodedQueryParameter_u24lambda_u2423 = builder1;
      int $i$a$-apply-HttpUrl$Builder$setEncodedQueryParameter$1 = 0;
      $this$setEncodedQueryParameter_u24lambda_u2423.removeAllEncodedQueryParameters(encodedName);
      $this$setEncodedQueryParameter_u24lambda_u2423.addEncodedQueryParameter(encodedName, encodedValue);
      return builder1;
    }
    
    @NotNull
    public final Builder removeAllQueryParameters(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$removeAllQueryParameters_u24lambda_u2424 = builder1;
      int $i$a$-apply-HttpUrl$Builder$removeAllQueryParameters$1 = 0;
      if ($this$removeAllQueryParameters_u24lambda_u2424.encodedQueryNamesAndValues == null)
        return $this$removeAllQueryParameters_u24lambda_u2424; 
      String nameToRemove = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219, null);
      $this$removeAllQueryParameters_u24lambda_u2424.removeAllCanonicalQueryParameters(nameToRemove);
      return builder1;
    }
    
    @NotNull
    public final Builder removeAllEncodedQueryParameters(@NotNull String encodedName) {
      Intrinsics.checkNotNullParameter(encodedName, "encodedName");
      Builder builder1 = this, $this$removeAllEncodedQueryParameters_u24lambda_u2425 = builder1;
      int $i$a$-apply-HttpUrl$Builder$removeAllEncodedQueryParameters$1 = 0;
      if ($this$removeAllEncodedQueryParameters_u24lambda_u2425.encodedQueryNamesAndValues == null)
        return $this$removeAllEncodedQueryParameters_u24lambda_u2425; 
      $this$removeAllEncodedQueryParameters_u24lambda_u2425.removeAllCanonicalQueryParameters(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedName, 0, 0, " \"'<>#&=", true, false, true, false, null, 211, null));
      return builder1;
    }
    
    private final void removeAllCanonicalQueryParameters(String canonicalName) {
      Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
      int j = this.encodedQueryNamesAndValues.size() - 2, i = j, k = ProgressionUtilKt.getProgressionLastElement(j, 0, -2);
      if (k <= i)
        while (true) {
          Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
          Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
          this.encodedQueryNamesAndValues.remove(i + 1);
          Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
          this.encodedQueryNamesAndValues.remove(i);
          Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
          if (Intrinsics.areEqual(canonicalName, this.encodedQueryNamesAndValues.get(i)) && this.encodedQueryNamesAndValues.isEmpty()) {
            this.encodedQueryNamesAndValues = null;
            return;
          } 
          if (i != k) {
            i -= 2;
            continue;
          } 
          break;
        }  
    }
    
    @NotNull
    public final Builder fragment(@Nullable String fragment) {
      Builder builder1 = this, $this$fragment_u24lambda_u2426 = builder1;
      int $i$a$-apply-HttpUrl$Builder$fragment$1 = 0;
      $this$fragment_u24lambda_u2426.encodedFragment = (fragment != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, fragment, 0, 0, "", false, false, false, true, null, 187, null) : null;
      return builder1;
    }
    
    @NotNull
    public final Builder encodedFragment(@Nullable String encodedFragment) {
      Builder builder1 = this, $this$encodedFragment_u24lambda_u2427 = builder1;
      int $i$a$-apply-HttpUrl$Builder$encodedFragment$1 = 0;
      $this$encodedFragment_u24lambda_u2427.encodedFragment = (encodedFragment != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedFragment, 0, 0, "", true, false, false, true, null, 179, null) : null;
      return builder1;
    }
    
    @NotNull
    public final Builder reencodeForUri$okhttp() {
      Builder builder1 = this, $this$reencodeForUri_u24lambda_u2428 = builder1;
      int $i$a$-apply-HttpUrl$Builder$reencodeForUri$1 = 0;
      String str1 = $this$reencodeForUri_u24lambda_u2428.host;
      Regex regex = new Regex("[\"<>^`{|}]");
      String str2 = "";
      $this$reencodeForUri_u24lambda_u2428.host = ($this$reencodeForUri_u24lambda_u2428.host != null) ? regex.replace(str1, str2) : null;
      for (int i = 0, j = $this$reencodeForUri_u24lambda_u2428.encodedPathSegments.size(); i < j; i++)
        $this$reencodeForUri_u24lambda_u2428.encodedPathSegments.set(i, HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, $this$reencodeForUri_u24lambda_u2428.encodedPathSegments.get(i), 0, 0, "[]", true, true, false, false, null, 227, null)); 
      List<String> encodedQueryNamesAndValues = $this$reencodeForUri_u24lambda_u2428.encodedQueryNamesAndValues;
      if (encodedQueryNamesAndValues != null)
        for (int k = 0, m = encodedQueryNamesAndValues.size(); k < m; k++) {
          (String)encodedQueryNamesAndValues.get(k);
          encodedQueryNamesAndValues.set(k, ((String)encodedQueryNamesAndValues.get(k) != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedQueryNamesAndValues.get(k), 0, 0, "\\^`{|}", true, true, true, false, null, 195, null) : null);
        }  
      $this$reencodeForUri_u24lambda_u2428.encodedFragment = ($this$reencodeForUri_u24lambda_u2428.encodedFragment != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, $this$reencodeForUri_u24lambda_u2428.encodedFragment, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163, null) : null;
      return builder1;
    }
    
    @NotNull
    public final HttpUrl build() {
      if (this.scheme == null)
        throw new IllegalStateException("scheme == null"); 
      if (this.host == null)
        throw new IllegalStateException("host == null"); 
      List<String> list1 = this.encodedPathSegments;
      int i = effectivePort();
      String str4 = this.host, str3 = str4, str2 = HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedPassword, 0, 0, false, 7, null), str1 = HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedUsername, 0, 0, false, 7, null);
      int $i$f$map = 0;
      List<String> list2 = list1;
      Collection<String> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
      int $i$f$mapTo = 0;
      for (String item$iv$iv : list2) {
        String str11 = item$iv$iv;
        Collection<String> collection = destination$iv$iv;
        int $i$a$-map-HttpUrl$Builder$build$1 = 0;
        collection.add(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, str11, 0, 0, false, 7, null));
      } 
      List<String> list3 = (List)destination$iv$iv;
      if (this.encodedQueryNamesAndValues != null) {
        List<String> list5 = this.encodedQueryNamesAndValues;
        list3 = list3;
        i = i;
        str4 = str4;
        str3 = str3;
        str2 = str2;
        str1 = str1;
        int k = 0;
        destination$iv$iv = list5;
        Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
        int m = 0;
        for (String item$iv$iv : destination$iv$iv) {
          String str11 = item$iv$iv;
          Collection collection1 = collection;
          int $i$a$-map-HttpUrl$Builder$build$2 = 0;
        } 
        List list6 = (List)collection;
      } else {
      
      } 
      String str5 = toString(), str6 = (this.encodedFragment != null) ? HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedFragment, 0, 0, false, 7, null) : null;
      List list = null;
      List<String> list4 = list3;
      int j = i;
      String str7 = str4, str8 = str3, str9 = str2, str10 = str1;
      return new HttpUrl(str10, str9, str8, str7, j, list4, list, str6, str5);
    }
    
    @NotNull
    public String toString() {
      StringBuilder stringBuilder1 = new StringBuilder(), $this$toString_u24lambda_u2431 = stringBuilder1;
      int $i$a$-buildString-HttpUrl$Builder$toString$1 = 0;
      if (this.scheme != null) {
        $this$toString_u24lambda_u2431.append(this.scheme);
        $this$toString_u24lambda_u2431.append("://");
      } else {
        $this$toString_u24lambda_u2431.append("//");
      } 
      if (((this.encodedUsername.length() > 0)) || ((this.encodedPassword.length() > 0))) {
        $this$toString_u24lambda_u2431.append(this.encodedUsername);
        if ((this.encodedPassword.length() > 0)) {
          $this$toString_u24lambda_u2431.append(':');
          $this$toString_u24lambda_u2431.append(this.encodedPassword);
        } 
        $this$toString_u24lambda_u2431.append('@');
      } 
      if (this.host != null) {
        Intrinsics.checkNotNull(this.host);
        if (StringsKt.contains$default(this.host, ':', false, 2, null)) {
          $this$toString_u24lambda_u2431.append('[');
          $this$toString_u24lambda_u2431.append(this.host);
          $this$toString_u24lambda_u2431.append(']');
        } else {
          $this$toString_u24lambda_u2431.append(this.host);
        } 
      } 
      if (this.port != -1 || this.scheme != null) {
        int effectivePort = effectivePort();
        Intrinsics.checkNotNull(this.scheme);
        if (this.scheme == null || effectivePort != HttpUrl.Companion.defaultPort(this.scheme)) {
          $this$toString_u24lambda_u2431.append(':');
          $this$toString_u24lambda_u2431.append(effectivePort);
        } 
      } 
      HttpUrl.Companion.toPathString$okhttp(this.encodedPathSegments, $this$toString_u24lambda_u2431);
      if (this.encodedQueryNamesAndValues != null) {
        $this$toString_u24lambda_u2431.append('?');
        Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
        HttpUrl.Companion.toQueryString$okhttp(this.encodedQueryNamesAndValues, $this$toString_u24lambda_u2431);
      } 
      if (this.encodedFragment != null) {
        $this$toString_u24lambda_u2431.append('#');
        $this$toString_u24lambda_u2431.append(this.encodedFragment);
      } 
      Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
      return stringBuilder1.toString();
    }
    
    @NotNull
    public final Builder parse$okhttp(@Nullable HttpUrl base, @NotNull String input) {
      Intrinsics.checkNotNullParameter(input, "input");
      int pos = 0;
      pos = Util.indexOfFirstNonAsciiWhitespace$default(input, 0, 0, 3, null);
      int limit = Util.indexOfLastNonAsciiWhitespace$default(input, pos, 0, 2, null);
      int schemeDelimiterOffset = Companion.schemeDelimiterOffset(input, pos, limit);
      if (schemeDelimiterOffset != -1) {
        if (StringsKt.startsWith(input, "https:", pos, true)) {
          this.scheme = "https";
          pos += 6;
        } else if (StringsKt.startsWith(input, "http:", pos, true)) {
          this.scheme = "http";
          pos += 5;
        } else {
          Intrinsics.checkNotNullExpressionValue(input.substring(0, schemeDelimiterOffset), "this as java.lang.String…ing(startIndex, endIndex)");
          throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + input.substring(0, schemeDelimiterOffset) + '\'');
        } 
      } else if (base != null) {
        this.scheme = base.scheme();
      } else {
        String truncated = (input.length() > 6) ? (StringsKt.take(input, 6) + "...") : input;
        throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no scheme was found for " + truncated);
      } 
      boolean hasUsername = false;
      boolean hasPassword = false;
      int slashCount = Companion.slashCount(input, pos, limit);
      if (slashCount >= 2 || base == null || !Intrinsics.areEqual(base.scheme(), this.scheme)) {
        int componentDelimiterOffset;
        pos += slashCount;
        while (true) {
          componentDelimiterOffset = Util.delimiterOffset(input, "@/\\?#", pos, limit);
          int c = (componentDelimiterOffset != limit) ? input.charAt(componentDelimiterOffset) : -1;
          switch (c) {
            case 64:
              if (!hasPassword) {
                int passwordColonOffset = Util.delimiterOffset(input, ':', pos, componentDelimiterOffset);
                String canonicalUsername = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                this.encodedUsername = hasUsername ? (this.encodedUsername + "%40" + canonicalUsername) : canonicalUsername;
                if (passwordColonOffset != componentDelimiterOffset) {
                  hasPassword = true;
                  this.encodedPassword = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, passwordColonOffset + 1, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                } 
                hasUsername = true;
              } else {
                this.encodedPassword += "%40" + HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
              } 
              pos = componentDelimiterOffset + 1;
            case -1:
            case 35:
            case 47:
            case 63:
            case 92:
              break;
          } 
        } 
        int portColonOffset = Companion.portColonOffset(input, pos, componentDelimiterOffset);
        if (portColonOffset + 1 < componentDelimiterOffset) {
          this.host = HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, input, pos, portColonOffset, false, 4, null));
          this.port = Companion.parsePort(input, portColonOffset + 1, componentDelimiterOffset);
          if (!((this.port != -1) ? 1 : 0)) {
            int $i$a$-require-HttpUrl$Builder$parse$1 = 0;
            Intrinsics.checkNotNullExpressionValue(input.substring(portColonOffset + 1, componentDelimiterOffset), "this as java.lang.String…ing(startIndex, endIndex)");
            String str = "Invalid URL port: \"" + input.substring(portColonOffset + 1, componentDelimiterOffset) + '"';
            throw new IllegalArgumentException(str.toString());
          } 
        } else {
          this.host = HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, input, pos, portColonOffset, false, 4, null));
          Intrinsics.checkNotNull(this.scheme);
          this.port = HttpUrl.Companion.defaultPort(this.scheme);
        } 
        if (!((this.host != null) ? 1 : 0)) {
          int $i$a$-require-HttpUrl$Builder$parse$2 = 0;
          Intrinsics.checkNotNullExpressionValue(input.substring(pos, portColonOffset), "this as java.lang.String…ing(startIndex, endIndex)");
          String str = "Invalid URL host: \"" + input.substring(pos, portColonOffset) + '"';
          throw new IllegalArgumentException(str.toString());
        } 
        pos = componentDelimiterOffset;
      } else {
        this.encodedUsername = base.encodedUsername();
        this.encodedPassword = base.encodedPassword();
        this.host = base.host();
        this.port = base.port();
        this.encodedPathSegments.clear();
        this.encodedPathSegments.addAll(base.encodedPathSegments());
        if (pos == limit || input.charAt(pos) == '#')
          encodedQuery(base.encodedQuery()); 
      } 
      int pathDelimiterOffset = Util.delimiterOffset(input, "?#", pos, limit);
      resolvePath(input, pos, pathDelimiterOffset);
      pos = pathDelimiterOffset;
      if (pos < limit && input.charAt(pos) == '?') {
        int queryDelimiterOffset = Util.delimiterOffset(input, '#', pos, limit);
        this.encodedQueryNamesAndValues = HttpUrl.Companion.toQueryNamesAndValues$okhttp(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos + 1, queryDelimiterOffset, " \"'<>#", true, false, true, false, null, 208, null));
        pos = queryDelimiterOffset;
      } 
      if (pos < limit && input.charAt(pos) == '#')
        this.encodedFragment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos + 1, limit, "", true, false, false, true, null, 176, null); 
      return this;
    }
    
    private final void resolvePath(String input, int startPos, int limit) {
      int pos = startPos;
      if (pos == limit)
        return; 
      char c = input.charAt(pos);
      if (c == '/' || c == '\\') {
        this.encodedPathSegments.clear();
        this.encodedPathSegments.add("");
        pos++;
      } else {
        this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
      } 
      int i = pos;
      while (i < limit) {
        int pathSegmentDelimiterOffset = Util.delimiterOffset(input, "/\\", i, limit);
        boolean segmentHasTrailingSlash = (pathSegmentDelimiterOffset < limit);
        push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
        i = pathSegmentDelimiterOffset;
        if (segmentHasTrailingSlash)
          i++; 
      } 
    }
    
    private final void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
      String segment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, limit, " \"<>^`{}|/\\?#", alreadyEncoded, false, false, false, null, 240, null);
      if (isDot(segment))
        return; 
      if (isDotDot(segment)) {
        pop();
        return;
      } 
      if ((((CharSequence)this.encodedPathSegments.get(this.encodedPathSegments.size() - 1)).length() == 0)) {
        this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
      } else {
        this.encodedPathSegments.add(segment);
      } 
      if (addTrailingSlash)
        this.encodedPathSegments.add(""); 
    }
    
    private final boolean isDot(String input) {
      return (Intrinsics.areEqual(input, ".") || StringsKt.equals(input, "%2e", true));
    }
    
    private final boolean isDotDot(String input) {
      return (Intrinsics.areEqual(input, "..") || StringsKt.equals(input, "%2e.", true) || StringsKt.equals(input, ".%2e", true) || StringsKt.equals(input, "%2e%2e", true));
    }
    
    private final void pop() {
      String removed = this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1);
      if (((removed.length() == 0)) && (!this.encodedPathSegments.isEmpty())) {
        this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
      } else {
        this.encodedPathSegments.add("");
      } 
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\n\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J'\020\t\032\0020\0062\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\006H\002¢\006\004\b\t\020\nJ'\020\013\032\0020\0062\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\006H\002¢\006\004\b\013\020\nJ'\020\f\032\0020\0062\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\006H\002¢\006\004\b\f\020\nJ#\020\r\032\0020\006*\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\006H\002¢\006\004\b\r\020\nR\024\020\016\032\0020\0048\000XT¢\006\006\n\004\b\016\020\017¨\006\020"}, d2 = {"Lokhttp3/HttpUrl$Builder$Companion;", "", "<init>", "()V", "", "input", "", "pos", "limit", "parsePort", "(Ljava/lang/String;II)I", "portColonOffset", "schemeDelimiterOffset", "slashCount", "INVALID_HOST", "Ljava/lang/String;", "okhttp"})
    public static final class Companion {
      private Companion() {}
      
      private final int schemeDelimiterOffset(String input, int pos, int limit) {
        if (limit - pos < 2)
          return -1; 
        char c0 = input.charAt(pos);
        if ((Intrinsics.compare(c0, 97) < 0 || Intrinsics.compare(c0, 122) > 0) && (Intrinsics.compare(c0, 65) < 0 || Intrinsics.compare(c0, 90) > 0))
          return -1; 
        for (int i = pos + 1; i < limit; i++) {
          char c = input.charAt(i);
          if (!((((((('a' <= c) ? ((c < '{')) : false) ? true : (('A' <= c) ? ((c < '[')) : false)) ? true : (('0' <= c) ? ((c < ':')) : false)) ? true : ((c == '+'))) ? true : ((c == '-'))) ? 1 : ((c == '.') ? 1 : 0)))
            return (c == ':') ? i : -1; 
        } 
        return -1;
      }
      
      private final int slashCount(String $this$slashCount, int pos, int limit) {
        int slashCount = 0;
        for (int i = pos; i < limit; ) {
          char c = $this$slashCount.charAt(i);
          if (c == '\\' || c == '/') {
            slashCount++;
            i++;
          } 
        } 
        return slashCount;
      }
      
      private final int portColonOffset(String input, int pos, int limit) {
        int i = pos;
        while (i < limit) {
          char c = input.charAt(i);
          if (c == '[') {
            do {
            
            } while (++i < limit && input.charAt(i) != ']');
          } else if (c == ':') {
            return i;
          } 
          i++;
        } 
        return limit;
      }
      
      private final int parsePort(String input, int pos, int limit) {
        byte b;
        try {
          String portString = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, limit, "", false, false, false, false, null, 248, null);
          int i = Integer.parseInt(portString);
          b = ((1 <= i) ? ((i < 65536)) : false) ? i : -1;
        } catch (NumberFormatException _) {
          b = -1;
        } 
        return b;
      }
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000n\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\b\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\013\n\002\020 \n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020!\n\002\b\005\n\002\030\002\n\002\b\n\n\002\020\031\n\002\b\013\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\031\020\016\032\004\030\0010\0132\006\020\n\032\0020\tH\007¢\006\004\b\f\020\rJ\031\020\016\032\004\030\0010\0132\006\020\020\032\0020\017H\007¢\006\004\b\f\020\021J\027\020\016\032\0020\0132\006\020\020\032\0020\004H\007¢\006\004\b\f\020\022J\031\020\024\032\004\030\0010\0132\006\020\020\032\0020\004H\007¢\006\004\b\023\020\022Jc\020!\032\0020\004*\0020\0042\b\b\002\020\025\032\0020\0062\b\b\002\020\026\032\0020\0062\006\020\027\032\0020\0042\b\b\002\020\031\032\0020\0302\b\b\002\020\032\032\0020\0302\b\b\002\020\033\032\0020\0302\b\b\002\020\034\032\0020\0302\n\b\002\020\036\032\004\030\0010\035H\000¢\006\004\b\037\020 J#\020\"\032\0020\030*\0020\0042\006\020\025\032\0020\0062\006\020\026\032\0020\006H\002¢\006\004\b\"\020#J1\020&\032\0020\004*\0020\0042\b\b\002\020\025\032\0020\0062\b\b\002\020\026\032\0020\0062\b\b\002\020\033\032\0020\030H\000¢\006\004\b$\020%J\023\020'\032\0020\013*\0020\004H\007¢\006\004\b\016\020\022J\025\020(\032\004\030\0010\013*\0020\tH\007¢\006\004\b\016\020\rJ\025\020(\032\004\030\0010\013*\0020\017H\007¢\006\004\b\016\020\021J\025\020(\032\004\030\0010\013*\0020\004H\007¢\006\004\b\024\020\022J%\0200\032\0020-*\b\022\004\022\0020\0040)2\n\020,\032\0060*j\002`+H\000¢\006\004\b.\020/J\033\0204\032\n\022\006\022\004\030\0010\00401*\0020\004H\000¢\006\004\b2\0203J'\0206\032\0020-*\n\022\006\022\004\030\0010\0040)2\n\020,\032\0060*j\002`+H\000¢\006\004\b5\020/J]\0209\032\0020-*\002072\006\0208\032\0020\0042\006\020\025\032\0020\0062\006\020\026\032\0020\0062\006\020\027\032\0020\0042\006\020\031\032\0020\0302\006\020\032\032\0020\0302\006\020\033\032\0020\0302\006\020\034\032\0020\0302\b\020\036\032\004\030\0010\035H\002¢\006\004\b9\020:J3\020<\032\0020-*\002072\006\020;\032\0020\0042\006\020\025\032\0020\0062\006\020\026\032\0020\0062\006\020\033\032\0020\030H\002¢\006\004\b<\020=R\024\020>\032\0020\0048\000XT¢\006\006\n\004\b>\020?R\024\020@\032\0020\0048\000XT¢\006\006\n\004\b@\020?R\024\020A\032\0020\0048\000XT¢\006\006\n\004\bA\020?R\024\020C\032\0020B8\002X\004¢\006\006\n\004\bC\020DR\024\020E\032\0020\0048\000XT¢\006\006\n\004\bE\020?R\024\020F\032\0020\0048\000XT¢\006\006\n\004\bF\020?R\024\020G\032\0020\0048\000XT¢\006\006\n\004\bG\020?R\024\020H\032\0020\0048\000XT¢\006\006\n\004\bH\020?R\024\020I\032\0020\0048\000XT¢\006\006\n\004\bI\020?R\024\020J\032\0020\0048\000XT¢\006\006\n\004\bJ\020?R\024\020K\032\0020\0048\000XT¢\006\006\n\004\bK\020?R\024\020L\032\0020\0048\000XT¢\006\006\n\004\bL\020?¨\006M"}, d2 = {"Lokhttp3/HttpUrl$Companion;", "", "<init>", "()V", "", "scheme", "", "defaultPort", "(Ljava/lang/String;)I", "Ljava/net/URI;", "uri", "Lokhttp3/HttpUrl;", "-deprecated_get", "(Ljava/net/URI;)Lokhttp3/HttpUrl;", "get", "Ljava/net/URL;", "url", "(Ljava/net/URL;)Lokhttp3/HttpUrl;", "(Ljava/lang/String;)Lokhttp3/HttpUrl;", "-deprecated_parse", "parse", "pos", "limit", "encodeSet", "", "alreadyEncoded", "strict", "plusIsSpace", "unicodeAllowed", "Ljava/nio/charset/Charset;", "charset", "canonicalize$okhttp", "(Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;)Ljava/lang/String;", "canonicalize", "isPercentEncoded", "(Ljava/lang/String;II)Z", "percentDecode$okhttp", "(Ljava/lang/String;IIZ)Ljava/lang/String;", "percentDecode", "toHttpUrl", "toHttpUrlOrNull", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "out", "", "toPathString$okhttp", "(Ljava/util/List;Ljava/lang/StringBuilder;)V", "toPathString", "", "toQueryNamesAndValues$okhttp", "(Ljava/lang/String;)Ljava/util/List;", "toQueryNamesAndValues", "toQueryString$okhttp", "toQueryString", "Lokio/Buffer;", "input", "writeCanonicalized", "(Lokio/Buffer;Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;)V", "encoded", "writePercentDecoded", "(Lokio/Buffer;Ljava/lang/String;IIZ)V", "FORM_ENCODE_SET", "Ljava/lang/String;", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "", "HEX_DIGITS", "[C", "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "QUERY_COMPONENT_REENCODE_SET", "QUERY_ENCODE_SET", "USERNAME_ENCODE_SET", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    public final int defaultPort(@NotNull String scheme) {
      Intrinsics.checkNotNullParameter(scheme, "scheme");
      String str = scheme;
      return Intrinsics.areEqual(str, "http") ? 80 : (Intrinsics.areEqual(str, "https") ? 443 : -1);
    }
    
    public final void toPathString$okhttp(@NotNull List<String> $this$toPathString, @NotNull StringBuilder out) {
      Intrinsics.checkNotNullParameter($this$toPathString, "<this>");
      Intrinsics.checkNotNullParameter(out, "out");
      for (int i = 0, j = $this$toPathString.size(); i < j; i++) {
        out.append('/');
        out.append($this$toPathString.get(i));
      } 
    }
    
    public final void toQueryString$okhttp(@NotNull List<String> $this$toQueryString, @NotNull StringBuilder out) {
      Intrinsics.checkNotNullParameter($this$toQueryString, "<this>");
      Intrinsics.checkNotNullParameter(out, "out");
      IntProgression intProgression = RangesKt.step((IntProgression)RangesKt.until(0, $this$toQueryString.size()), 2);
      int i = intProgression.getFirst(), j = intProgression.getLast(), k = intProgression.getStep();
      if ((k > 0 && i <= j) || (k < 0 && j <= i))
        while (true) {
          String name = $this$toQueryString.get(i);
          String value = $this$toQueryString.get(i + 1);
          if (i > 0)
            out.append('&'); 
          out.append(name);
          if (value != null) {
            out.append('=');
            out.append(value);
          } 
          if (i != j) {
            i += k;
            continue;
          } 
          break;
        }  
    }
    
    @NotNull
    public final List<String> toQueryNamesAndValues$okhttp(@NotNull String $this$toQueryNamesAndValues) {
      Intrinsics.checkNotNullParameter($this$toQueryNamesAndValues, "<this>");
      List<String> result = new ArrayList();
      int pos = 0;
      while (pos <= $this$toQueryNamesAndValues.length()) {
        int ampersandOffset = StringsKt.indexOf$default($this$toQueryNamesAndValues, '&', pos, false, 4, null);
        if (ampersandOffset == -1)
          ampersandOffset = $this$toQueryNamesAndValues.length(); 
        int equalsOffset = StringsKt.indexOf$default($this$toQueryNamesAndValues, '=', pos, false, 4, null);
        if (equalsOffset == -1 || equalsOffset > ampersandOffset) {
          Intrinsics.checkNotNullExpressionValue($this$toQueryNamesAndValues.substring(pos, ampersandOffset), "this as java.lang.String…ing(startIndex, endIndex)");
          result.add($this$toQueryNamesAndValues.substring(pos, ampersandOffset));
          result.add(null);
        } else {
          Intrinsics.checkNotNullExpressionValue($this$toQueryNamesAndValues.substring(pos, equalsOffset), "this as java.lang.String…ing(startIndex, endIndex)");
          result.add($this$toQueryNamesAndValues.substring(pos, equalsOffset));
          Intrinsics.checkNotNullExpressionValue($this$toQueryNamesAndValues.substring(equalsOffset + 1, ampersandOffset), "this as java.lang.String…ing(startIndex, endIndex)");
          result.add($this$toQueryNamesAndValues.substring(equalsOffset + 1, ampersandOffset));
        } 
        pos = ampersandOffset + 1;
      } 
      return result;
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    public final HttpUrl get(@NotNull String $this$toHttpUrl) {
      Intrinsics.checkNotNullParameter($this$toHttpUrl, "<this>");
      return (new HttpUrl.Builder()).parse$okhttp(null, $this$toHttpUrl).build();
    }
    
    @JvmStatic
    @JvmName(name = "parse")
    @Nullable
    public final HttpUrl parse(@NotNull String $this$toHttpUrlOrNull) {
      HttpUrl httpUrl;
      Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "<this>");
      try {
        httpUrl = get($this$toHttpUrlOrNull);
      } catch (IllegalArgumentException _) {
        httpUrl = null;
      } 
      return httpUrl;
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @Nullable
    public final HttpUrl get(@NotNull URL $this$toHttpUrlOrNull) {
      Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "<this>");
      Intrinsics.checkNotNullExpressionValue($this$toHttpUrlOrNull.toString(), "toString()");
      return parse($this$toHttpUrlOrNull.toString());
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @Nullable
    public final HttpUrl get(@NotNull URI $this$toHttpUrlOrNull) {
      Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "<this>");
      Intrinsics.checkNotNullExpressionValue($this$toHttpUrlOrNull.toString(), "toString()");
      return parse($this$toHttpUrlOrNull.toString());
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrl()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrl"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_get")
    @NotNull
    public final HttpUrl -deprecated_get(@NotNull String url) {
      Intrinsics.checkNotNullParameter(url, "url");
      return get(url);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_parse")
    @Nullable
    public final HttpUrl -deprecated_parse(@NotNull String url) {
      Intrinsics.checkNotNullParameter(url, "url");
      return parse(url);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_get")
    @Nullable
    public final HttpUrl -deprecated_get(@NotNull URL url) {
      Intrinsics.checkNotNullParameter(url, "url");
      return get(url);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "uri.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_get")
    @Nullable
    public final HttpUrl -deprecated_get(@NotNull URI uri) {
      Intrinsics.checkNotNullParameter(uri, "uri");
      return get(uri);
    }
    
    @NotNull
    public final String percentDecode$okhttp(@NotNull String $this$percentDecode, int pos, int limit, boolean plusIsSpace) {
      Intrinsics.checkNotNullParameter($this$percentDecode, "<this>");
      for (int i = pos; i < limit; i++) {
        char c = $this$percentDecode.charAt(i);
        if (c == '%' || (c == '+' && plusIsSpace)) {
          Buffer out = new Buffer();
          out.writeUtf8($this$percentDecode, pos, i);
          writePercentDecoded(out, $this$percentDecode, i, limit, plusIsSpace);
          return out.readUtf8();
        } 
      } 
      Intrinsics.checkNotNullExpressionValue($this$percentDecode.substring(pos, limit), "this as java.lang.String…ing(startIndex, endIndex)");
      return $this$percentDecode.substring(pos, limit);
    }
    
    private final void writePercentDecoded(Buffer $this$writePercentDecoded, String encoded, int pos, int limit, boolean plusIsSpace) {
      int codePoint = 0, i = pos;
      while (i < limit) {
        codePoint = encoded.codePointAt(i);
        if (codePoint == 37 && i + 2 < limit) {
          int d1 = Util.parseHexDigit(encoded.charAt(i + 1));
          int d2 = Util.parseHexDigit(encoded.charAt(i + 2));
          if (d1 != -1 && d2 != -1) {
            $this$writePercentDecoded.writeByte((d1 << 4) + d2);
            i += 2;
            i += Character.charCount(codePoint);
            continue;
          } 
        } else if (codePoint == 43 && plusIsSpace) {
          $this$writePercentDecoded.writeByte(32);
          i++;
          continue;
        } 
        $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
        i += Character.charCount(codePoint);
      } 
    }
    
    private final boolean isPercentEncoded(String $this$isPercentEncoded, int pos, int limit) {
      return (pos + 2 < limit && $this$isPercentEncoded.charAt(pos) == '%' && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 1)) != -1 && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 2)) != -1);
    }
    
    @NotNull
    public final String canonicalize$okhttp(@NotNull String $this$canonicalize, int pos, int limit, @NotNull String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, @Nullable Charset charset) {
      Intrinsics.checkNotNullParameter($this$canonicalize, "<this>");
      Intrinsics.checkNotNullParameter(encodeSet, "encodeSet");
      int codePoint = 0, i = pos;
      while (i < limit) {
        codePoint = $this$canonicalize.codePointAt(i);
        if (codePoint < 32 || codePoint == 127 || (codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default(encodeSet, (char)codePoint, false, 2, null) || (codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded($this$canonicalize, i, limit)))) || (codePoint == 43 && plusIsSpace)) {
          Buffer out = new Buffer();
          out.writeUtf8($this$canonicalize, pos, i);
          writeCanonicalized(out, $this$canonicalize, i, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, unicodeAllowed, charset);
          return out.readUtf8();
        } 
        i += Character.charCount(codePoint);
      } 
      Intrinsics.checkNotNullExpressionValue($this$canonicalize.substring(pos, limit), "this as java.lang.String…ing(startIndex, endIndex)");
      return $this$canonicalize.substring(pos, limit);
    }
    
    private final void writeCanonicalized(Buffer $this$writeCanonicalized, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, Charset charset) {
      Buffer encodedCharBuffer = null;
      int codePoint = 0;
      int i = pos;
      while (i < limit) {
        codePoint = input.codePointAt(i);
        if (!alreadyEncoded || (codePoint != 9 && codePoint != 10 && codePoint != 12 && codePoint != 13))
          if (codePoint == 43 && plusIsSpace) {
            $this$writeCanonicalized.writeUtf8(alreadyEncoded ? "+" : "%2B");
          } else if (codePoint < 32 || codePoint == 127 || (codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default(encodeSet, (char)codePoint, false, 2, null) || (codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded(input, i, limit))))) {
            if (encodedCharBuffer == null)
              encodedCharBuffer = new Buffer(); 
            if (charset == null || Intrinsics.areEqual(charset, StandardCharsets.UTF_8)) {
              encodedCharBuffer.writeUtf8CodePoint(codePoint);
            } else {
              encodedCharBuffer.writeString(input, i, i + Character.charCount(codePoint), charset);
            } 
            while (!encodedCharBuffer.exhausted()) {
              int b = encodedCharBuffer.readByte() & 0xFF;
              $this$writeCanonicalized.writeByte(37);
              $this$writeCanonicalized.writeByte(HttpUrl.HEX_DIGITS[b >> 4 & 0xF]);
              $this$writeCanonicalized.writeByte(HttpUrl.HEX_DIGITS[b & 0xF]);
            } 
          } else {
            $this$writeCanonicalized.writeUtf8CodePoint(codePoint);
          }  
        i += Character.charCount(codePoint);
      } 
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final String scheme;
  
  @NotNull
  private final String username;
  
  @NotNull
  private final String password;
  
  @NotNull
  private final String host;
  
  private final int port;
  
  @NotNull
  private final List<String> pathSegments;
  
  @Nullable
  private final List<String> queryNamesAndValues;
  
  @Nullable
  private final String fragment;
  
  @NotNull
  private final String url;
  
  private final boolean isHttps;
  
  @NotNull
  private static final char[] HEX_DIGITS;
  
  @NotNull
  public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
  
  @NotNull
  public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
  
  @NotNull
  public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
  
  @NotNull
  public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
  
  @NotNull
  public static final String QUERY_ENCODE_SET = " \"'<>#";
  
  @NotNull
  public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
  
  @NotNull
  public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
  
  @NotNull
  public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
  
  @NotNull
  public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
  
  @NotNull
  public static final String FRAGMENT_ENCODE_SET = "";
  
  @NotNull
  public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
  
  static {
    char[] arrayOfChar = new char[16];
    arrayOfChar[0] = '0';
    arrayOfChar[1] = '1';
    arrayOfChar[2] = '2';
    arrayOfChar[3] = '3';
    arrayOfChar[4] = '4';
    arrayOfChar[5] = '5';
    arrayOfChar[6] = '6';
    arrayOfChar[7] = '7';
    arrayOfChar[8] = '8';
    arrayOfChar[9] = '9';
    arrayOfChar[10] = 'A';
    arrayOfChar[11] = 'B';
    arrayOfChar[12] = 'C';
    arrayOfChar[13] = 'D';
    arrayOfChar[14] = 'E';
    arrayOfChar[15] = 'F';
    HEX_DIGITS = arrayOfChar;
  }
  
  @JvmStatic
  public static final int defaultPort(@NotNull String scheme) {
    return Companion.defaultPort(scheme);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @NotNull
  public static final HttpUrl get(@NotNull String $this$get) {
    return Companion.get($this$get);
  }
  
  @JvmStatic
  @JvmName(name = "parse")
  @Nullable
  public static final HttpUrl parse(@NotNull String $this$parse) {
    return Companion.parse($this$parse);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @Nullable
  public static final HttpUrl get(@NotNull URL $this$get) {
    return Companion.get($this$get);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @Nullable
  public static final HttpUrl get(@NotNull URI $this$get) {
    return Companion.get($this$get);
  }
}
