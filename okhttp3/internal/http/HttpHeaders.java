package okhttp3.internal.http;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000R\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\030\002\n\002\020!\n\000\n\002\020\002\n\002\b\005\n\002\030\002\n\002\030\002\n\002\b\006\n\002\020\005\n\002\b\003\n\002\030\002\n\002\b\004\032\027\020\003\032\0020\0022\006\020\001\032\0020\000H\007¢\006\004\b\003\020\004\032\037\020\n\032\b\022\004\022\0020\t0\b*\0020\0052\006\020\007\032\0020\006¢\006\004\b\n\020\013\032\021\020\f\032\0020\002*\0020\000¢\006\004\b\f\020\004\032!\020\021\032\0020\020*\0020\r2\f\020\017\032\b\022\004\022\0020\t0\016H\002¢\006\004\b\021\020\022\032\025\020\023\032\004\030\0010\006*\0020\rH\002¢\006\004\b\023\020\024\032\025\020\025\032\004\030\0010\006*\0020\rH\002¢\006\004\b\025\020\024\032!\020\032\032\0020\020*\0020\0262\006\020\030\032\0020\0272\006\020\031\032\0020\005¢\006\004\b\032\020\033\032\023\020\034\032\0020\002*\0020\rH\002¢\006\004\b\034\020\035\032\033\020 \032\0020\002*\0020\r2\006\020\037\032\0020\036H\002¢\006\004\b \020!\"\024\020#\032\0020\"8\002X\004¢\006\006\n\004\b#\020$\"\024\020%\032\0020\"8\002X\004¢\006\006\n\004\b%\020$¨\006&"}, d2 = {"Lokhttp3/Response;", "response", "", "hasBody", "(Lokhttp3/Response;)Z", "Lokhttp3/Headers;", "", "headerName", "", "Lokhttp3/Challenge;", "parseChallenges", "(Lokhttp3/Headers;Ljava/lang/String;)Ljava/util/List;", "promisesBody", "Lokio/Buffer;", "", "result", "", "readChallengeHeader", "(Lokio/Buffer;Ljava/util/List;)V", "readQuotedString", "(Lokio/Buffer;)Ljava/lang/String;", "readToken", "Lokhttp3/CookieJar;", "Lokhttp3/HttpUrl;", "url", "headers", "receiveHeaders", "(Lokhttp3/CookieJar;Lokhttp3/HttpUrl;Lokhttp3/Headers;)V", "skipCommasAndWhitespace", "(Lokio/Buffer;)Z", "", "prefix", "startsWith", "(Lokio/Buffer;B)Z", "Lokio/ByteString;", "QUOTED_STRING_DELIMITERS", "Lokio/ByteString;", "TOKEN_DELIMITERS", "okhttp"})
@JvmName(name = "HttpHeaders")
public final class HttpHeaders {
  @NotNull
  private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.Companion.encodeUtf8("\"\\");
  
  @NotNull
  private static final ByteString TOKEN_DELIMITERS = ByteString.Companion.encodeUtf8("\t ,=");
  
  @NotNull
  public static final List<Challenge> parseChallenges(@NotNull Headers $this$parseChallenges, @NotNull String headerName) {
    Intrinsics.checkNotNullParameter($this$parseChallenges, "<this>");
    Intrinsics.checkNotNullParameter(headerName, "headerName");
    List<Challenge> result = new ArrayList();
    for (int h = 0, i = $this$parseChallenges.size(); h < i; h++) {
      if (StringsKt.equals(headerName, $this$parseChallenges.name(h), true)) {
        Buffer header = (new Buffer()).writeUtf8($this$parseChallenges.value(h));
        try {
          readChallengeHeader(header, result);
        } catch (EOFException e) {
          Platform.Companion.get().log("Unable to parse challenge", 5, e);
        } 
      } 
    } 
    return result;
  }
  
  private static final void readChallengeHeader(Buffer $this$readChallengeHeader, List<Challenge> result) throws EOFException {
    String peek = null;
    while (true) {
      if (peek == null) {
        skipCommasAndWhitespace($this$readChallengeHeader);
        peek = readToken($this$readChallengeHeader);
        if (peek == null)
          return; 
      } 
      String schemeName = peek;
      boolean commaPrefixed = skipCommasAndWhitespace($this$readChallengeHeader);
      peek = readToken($this$readChallengeHeader);
      if (peek == null) {
        if (!$this$readChallengeHeader.exhausted())
          return; 
        result.add(new Challenge(schemeName, MapsKt.emptyMap()));
        return;
      } 
      int eqCount = Util.skipAll($this$readChallengeHeader, (byte)61);
      boolean commaSuffixed = skipCommasAndWhitespace($this$readChallengeHeader);
      if (!commaPrefixed && (commaSuffixed || $this$readChallengeHeader.exhausted())) {
        Intrinsics.checkNotNullExpressionValue(Collections.singletonMap(null, peek + StringsKt.repeat("=", eqCount)), "singletonMap<String, Str…ek + \"=\".repeat(eqCount))");
        result.add(new Challenge(schemeName, Collections.singletonMap(null, peek + StringsKt.repeat("=", eqCount))));
        peek = null;
        continue;
      } 
      Map<Object, Object> parameters = new LinkedHashMap<>();
      eqCount += Util.skipAll($this$readChallengeHeader, (byte)61);
      while (true) {
        if (peek == null) {
          peek = readToken($this$readChallengeHeader);
          if (!skipCommasAndWhitespace($this$readChallengeHeader)) {
            eqCount = Util.skipAll($this$readChallengeHeader, (byte)61);
          } else {
            break;
          } 
        } 
        if (eqCount != 0) {
          Object object;
          if (eqCount > 1)
            return; 
          if (skipCommasAndWhitespace($this$readChallengeHeader))
            return; 
          if ((startsWith($this$readChallengeHeader, (byte)34) ? readQuotedString($this$readChallengeHeader) : 
            readToken($this$readChallengeHeader)) == null) {
            startsWith($this$readChallengeHeader, (byte)34) ? readQuotedString($this$readChallengeHeader) : readToken($this$readChallengeHeader);
            return;
          } 
          String replaced = (String)parameters.put(peek, object);
          peek = null;
          if (replaced != null)
            return; 
          if (!skipCommasAndWhitespace($this$readChallengeHeader) && !$this$readChallengeHeader.exhausted())
            return; 
          continue;
        } 
        break;
      } 
      result.add(new Challenge(schemeName, parameters));
    } 
  }
  
  private static final boolean skipCommasAndWhitespace(Buffer $this$skipCommasAndWhitespace) {
    boolean commaFound = false;
    while (!$this$skipCommasAndWhitespace.exhausted()) {
      byte b = $this$skipCommasAndWhitespace.getByte(0L);
      if (b == 44) {
        $this$skipCommasAndWhitespace.readByte();
        commaFound = true;
        continue;
      } 
      if ((b == 32) ? true : ((b == 9)))
        $this$skipCommasAndWhitespace.readByte(); 
    } 
    return commaFound;
  }
  
  private static final boolean startsWith(Buffer $this$startsWith, byte prefix) {
    return (!$this$startsWith.exhausted() && $this$startsWith.getByte(0L) == prefix);
  }
  
  private static final String readQuotedString(Buffer $this$readQuotedString) throws EOFException {
    if (!(($this$readQuotedString.readByte() == 34) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer result = new Buffer();
    while (true) {
      long i = $this$readQuotedString.indexOfElement(QUOTED_STRING_DELIMITERS);
      if (i == -1L)
        return null; 
      if ($this$readQuotedString.getByte(i) == 34) {
        result.write($this$readQuotedString, i);
        $this$readQuotedString.readByte();
        return result.readUtf8();
      } 
      if ($this$readQuotedString.size() == i + 1L)
        return null; 
      result.write($this$readQuotedString, i);
      $this$readQuotedString.readByte();
      result.write($this$readQuotedString, 1L);
    } 
  }
  
  private static final String readToken(Buffer $this$readToken) {
    long tokenSize = $this$readToken.indexOfElement(TOKEN_DELIMITERS);
    if (tokenSize == -1L)
      tokenSize = $this$readToken.size(); 
    return 
      (tokenSize != 0L) ? $this$readToken.readUtf8(tokenSize) : 
      null;
  }
  
  public static final void receiveHeaders(@NotNull CookieJar $this$receiveHeaders, @NotNull HttpUrl url, @NotNull Headers headers) {
    Intrinsics.checkNotNullParameter($this$receiveHeaders, "<this>");
    Intrinsics.checkNotNullParameter(url, "url");
    Intrinsics.checkNotNullParameter(headers, "headers");
    if ($this$receiveHeaders == CookieJar.NO_COOKIES)
      return; 
    List cookies = Cookie.Companion.parseAll(url, headers);
    if (cookies.isEmpty())
      return; 
    $this$receiveHeaders.saveFromResponse(url, cookies);
  }
  
  public static final boolean promisesBody(@NotNull Response $this$promisesBody) {
    Intrinsics.checkNotNullParameter($this$promisesBody, "<this>");
    if (Intrinsics.areEqual($this$promisesBody.request().method(), "HEAD"))
      return false; 
    int responseCode = $this$promisesBody.code();
    if ((responseCode < 100 || responseCode >= 200) && 
      responseCode != 204 && 
      responseCode != 304)
      return true; 
    if (Util.headersContentLength($this$promisesBody) != -1L || 
      StringsKt.equals("chunked", Response.header$default($this$promisesBody, "Transfer-Encoding", null, 2, null), true))
      return true; 
    return false;
  }
  
  @Deprecated(message = "No longer supported", replaceWith = @ReplaceWith(expression = "response.promisesBody()", imports = {}), level = DeprecationLevel.ERROR)
  public static final boolean hasBody(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    return promisesBody(response);
  }
}
