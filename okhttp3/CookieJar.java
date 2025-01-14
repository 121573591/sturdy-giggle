package okhttp3;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\004\bf\030\000 \f2\0020\001:\001\fJ\035\020\006\032\b\022\004\022\0020\0050\0042\006\020\003\032\0020\002H&¢\006\004\b\006\020\007J%\020\n\032\0020\t2\006\020\003\032\0020\0022\f\020\b\032\b\022\004\022\0020\0050\004H&¢\006\004\b\n\020\013¨\006\r"}, d2 = {"Lokhttp3/CookieJar;", "", "Lokhttp3/HttpUrl;", "url", "", "Lokhttp3/Cookie;", "loadForRequest", "(Lokhttp3/HttpUrl;)Ljava/util/List;", "cookies", "", "saveFromResponse", "(Lokhttp3/HttpUrl;Ljava/util/List;)V", "Companion", "okhttp"})
public interface CookieJar {
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001:\001\007B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\001¨\006\b"}, d2 = {"Lokhttp3/CookieJar$Companion;", "", "<init>", "()V", "Lokhttp3/CookieJar;", "NO_COOKIES", "Lokhttp3/CookieJar;", "NoCookies", "okhttp"})
  public static final class Companion {
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\035\020\b\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\004H\026¢\006\004\b\b\020\tJ%\020\f\032\0020\0132\006\020\005\032\0020\0042\f\020\n\032\b\022\004\022\0020\0070\006H\026¢\006\004\b\f\020\r¨\006\016"}, d2 = {"Lokhttp3/CookieJar$Companion$NoCookies;", "Lokhttp3/CookieJar;", "<init>", "()V", "Lokhttp3/HttpUrl;", "url", "", "Lokhttp3/Cookie;", "loadForRequest", "(Lokhttp3/HttpUrl;)Ljava/util/List;", "cookies", "", "saveFromResponse", "(Lokhttp3/HttpUrl;Ljava/util/List;)V", "okhttp"})
    private static final class NoCookies implements CookieJar {
      public void saveFromResponse(@NotNull HttpUrl url, @NotNull List cookies) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(cookies, "cookies");
      }
      
      @NotNull
      public List<Cookie> loadForRequest(@NotNull HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return CollectionsKt.emptyList();
      }
    }
  }
  
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  @JvmField
  @NotNull
  public static final CookieJar NO_COOKIES = new Companion.NoCookies();
  
  void saveFromResponse(@NotNull HttpUrl paramHttpUrl, @NotNull List<Cookie> paramList);
  
  @NotNull
  List<Cookie> loadForRequest(@NotNull HttpUrl paramHttpUrl);
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000&\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\035\020\b\032\b\022\004\022\0020\0070\0062\006\020\005\032\0020\004H\026¢\006\004\b\b\020\tJ%\020\f\032\0020\0132\006\020\005\032\0020\0042\f\020\n\032\b\022\004\022\0020\0070\006H\026¢\006\004\b\f\020\r¨\006\016"}, d2 = {"Lokhttp3/CookieJar$Companion$NoCookies;", "Lokhttp3/CookieJar;", "<init>", "()V", "Lokhttp3/HttpUrl;", "url", "", "Lokhttp3/Cookie;", "loadForRequest", "(Lokhttp3/HttpUrl;)Ljava/util/List;", "cookies", "", "saveFromResponse", "(Lokhttp3/HttpUrl;Ljava/util/List;)V", "okhttp"})
  private static final class NoCookies implements CookieJar {
    public void saveFromResponse(@NotNull HttpUrl url, @NotNull List cookies) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(cookies, "cookies");
    }
    
    @NotNull
    public List<Cookie> loadForRequest(@NotNull HttpUrl url) {
      Intrinsics.checkNotNullParameter(url, "url");
      return CollectionsKt.emptyList();
    }
  }
}
