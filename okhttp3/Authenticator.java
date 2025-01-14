package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.authenticator.JavaNetAuthenticator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\bæ\001\030\000 \t2\0020\001:\001\tJ#\020\007\032\004\030\0010\0062\b\020\003\032\004\030\0010\0022\006\020\005\032\0020\004H&¢\006\004\b\007\020\b¨\006\n"}, d2 = {"Lokhttp3/Authenticator;", "", "Lokhttp3/Route;", "route", "Lokhttp3/Response;", "response", "Lokhttp3/Request;", "authenticate", "(Lokhttp3/Route;Lokhttp3/Response;)Lokhttp3/Request;", "Companion", "okhttp"})
public interface Authenticator {
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001:\001\bB\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\001R\027\020\007\032\0020\0048\006X\004¢\006\006\n\004\b\007\020\006¨\006\001¨\006\t"}, d2 = {"Lokhttp3/Authenticator$Companion;", "", "<init>", "()V", "Lokhttp3/Authenticator;", "JAVA_NET_AUTHENTICATOR", "Lokhttp3/Authenticator;", "NONE", "AuthenticatorNone", "okhttp"})
  public static final class Companion {
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J#\020\t\032\004\030\0010\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\006H\026¢\006\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/Authenticator$Companion$AuthenticatorNone;", "Lokhttp3/Authenticator;", "<init>", "()V", "Lokhttp3/Route;", "route", "Lokhttp3/Response;", "response", "Lokhttp3/Request;", "authenticate", "(Lokhttp3/Route;Lokhttp3/Response;)Lokhttp3/Request;", "okhttp"})
    private static final class AuthenticatorNone implements Authenticator {
      @Nullable
      public Request authenticate(@Nullable Route route, @NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return null;
      }
    }
  }
  
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  @JvmField
  @NotNull
  public static final Authenticator NONE = new Companion.AuthenticatorNone();
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J#\020\t\032\004\030\0010\b2\b\020\005\032\004\030\0010\0042\006\020\007\032\0020\006H\026¢\006\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/Authenticator$Companion$AuthenticatorNone;", "Lokhttp3/Authenticator;", "<init>", "()V", "Lokhttp3/Route;", "route", "Lokhttp3/Response;", "response", "Lokhttp3/Request;", "authenticate", "(Lokhttp3/Route;Lokhttp3/Response;)Lokhttp3/Request;", "okhttp"})
  private static final class AuthenticatorNone implements Authenticator {
    @Nullable
    public Request authenticate(@Nullable Route route, @NotNull Response response) {
      Intrinsics.checkNotNullParameter(response, "response");
      return null;
    }
  }
  
  @JvmField
  @NotNull
  public static final Authenticator JAVA_NET_AUTHENTICATOR = (Authenticator)new JavaNetAuthenticator(null, 1, null);
  
  @Nullable
  Request authenticate(@Nullable Route paramRoute, @NotNull Response paramResponse) throws IOException;
}
