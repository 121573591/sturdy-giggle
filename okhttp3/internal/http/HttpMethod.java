package okhttp3.internal.http;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\013\n\002\b\007\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bJ\027\020\t\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\t\020\bJ\025\020\n\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\n\020\bJ\025\020\013\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\013\020\bJ\027\020\f\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\f\020\b¨\006\r"}, d2 = {"Lokhttp3/internal/http/HttpMethod;", "", "<init>", "()V", "", "method", "", "invalidatesCache", "(Ljava/lang/String;)Z", "permitsRequestBody", "redirectsToGet", "redirectsWithBody", "requiresRequestBody", "okhttp"})
public final class HttpMethod {
  @NotNull
  public static final HttpMethod INSTANCE = new HttpMethod();
  
  public final boolean invalidatesCache(@NotNull String method) {
    Intrinsics.checkNotNullParameter(method, "method");
    return (Intrinsics.areEqual(method, "POST") || 
      Intrinsics.areEqual(method, "PATCH") || 
      Intrinsics.areEqual(method, "PUT") || 
      Intrinsics.areEqual(method, "DELETE") || 
      Intrinsics.areEqual(method, "MOVE"));
  }
  
  @JvmStatic
  public static final boolean requiresRequestBody(@NotNull String method) {
    Intrinsics.checkNotNullParameter(method, "method");
    return (Intrinsics.areEqual(method, "POST") || 
      Intrinsics.areEqual(method, "PUT") || 
      Intrinsics.areEqual(method, "PATCH") || 
      Intrinsics.areEqual(method, "PROPPATCH") || 
      Intrinsics.areEqual(method, "REPORT"));
  }
  
  @JvmStatic
  public static final boolean permitsRequestBody(@NotNull String method) {
    Intrinsics.checkNotNullParameter(method, "method");
    return (!Intrinsics.areEqual(method, "GET") && !Intrinsics.areEqual(method, "HEAD"));
  }
  
  public final boolean redirectsWithBody(@NotNull String method) {
    Intrinsics.checkNotNullParameter(method, "method");
    return Intrinsics.areEqual(method, "PROPFIND");
  }
  
  public final boolean redirectsToGet(@NotNull String method) {
    Intrinsics.checkNotNullParameter(method, "method");
    return !Intrinsics.areEqual(method, "PROPFIND");
  }
}
