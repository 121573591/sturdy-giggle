package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\004\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J)\020\t\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\0042\b\b\002\020\b\032\0020\007H\007¢\006\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/Credentials;", "", "<init>", "()V", "", "username", "password", "Ljava/nio/charset/Charset;", "charset", "basic", "(Ljava/lang/String;Ljava/lang/String;Ljava/nio/charset/Charset;)Ljava/lang/String;", "okhttp"})
public final class Credentials {
  @NotNull
  public static final Credentials INSTANCE = new Credentials();
  
  @JvmStatic
  @JvmOverloads
  @NotNull
  public static final String basic(@NotNull String username, @NotNull String password) {
    Intrinsics.checkNotNullParameter(username, "username");
    Intrinsics.checkNotNullParameter(password, "password");
    return basic$default(username, password, null, 4, null);
  }
  
  @JvmStatic
  @JvmOverloads
  @NotNull
  public static final String basic(@NotNull String username, @NotNull String password, @NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(username, "username");
    Intrinsics.checkNotNullParameter(password, "password");
    Intrinsics.checkNotNullParameter(charset, "charset");
    String usernameAndPassword = username + ':' + password;
    String encoded = ByteString.Companion.encodeString(usernameAndPassword, charset).base64();
    return "Basic " + encoded;
  }
}
