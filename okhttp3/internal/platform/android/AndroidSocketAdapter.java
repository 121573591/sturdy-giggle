package okhttp3.internal.platform.android;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.platform.AndroidPlatform;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\004\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\t\b\026\030\000 \0372\0020\001:\001\037B\027\022\016\020\004\032\n\022\006\b\000\022\0020\0030\002¢\006\004\b\005\020\006J/\020\016\032\0020\r2\006\020\007\032\0020\0032\b\020\t\032\004\030\0010\b2\f\020\f\032\b\022\004\022\0020\0130\nH\026¢\006\004\b\016\020\017J\031\020\020\032\004\030\0010\b2\006\020\007\032\0020\003H\026¢\006\004\b\020\020\021J\017\020\023\032\0020\022H\026¢\006\004\b\023\020\024J\027\020\025\032\0020\0222\006\020\007\032\0020\003H\026¢\006\004\b\025\020\026R\034\020\031\032\n \030*\004\030\0010\0270\0278\002X\004¢\006\006\n\004\b\031\020\032R\034\020\033\032\n \030*\004\030\0010\0270\0278\002X\004¢\006\006\n\004\b\033\020\032R\034\020\034\032\n \030*\004\030\0010\0270\0278\002X\004¢\006\006\n\004\b\034\020\032R\024\020\035\032\0020\0278\002X\004¢\006\006\n\004\b\035\020\032R\034\020\004\032\n\022\006\b\000\022\0020\0030\0028\002X\004¢\006\006\n\004\b\004\020\036¨\006 "}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "sslSocketClass", "<init>", "(Ljava/lang/Class;)V", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "", "isSupported", "()Z", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "getAlpnSelectedProtocol", "Ljava/lang/reflect/Method;", "setAlpnProtocols", "setHostname", "setUseSessionTickets", "Ljava/lang/Class;", "Companion", "okhttp"})
public class AndroidSocketAdapter implements SocketAdapter {
  public AndroidSocketAdapter(@NotNull Class<? super SSLSocket> sslSocketClass) {
    this.sslSocketClass = sslSocketClass;
    Class[] arrayOfClass2 = new Class[1];
    arrayOfClass2[0] = boolean.class;
    Intrinsics.checkNotNullExpressionValue(this.sslSocketClass.getDeclaredMethod("setUseSessionTickets", arrayOfClass2), "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
    this.setUseSessionTickets = this.sslSocketClass.getDeclaredMethod("setUseSessionTickets", arrayOfClass2);
    Class[] arrayOfClass1 = new Class[1];
    arrayOfClass1[0] = String.class;
    this.setHostname = this.sslSocketClass.getMethod("setHostname", arrayOfClass1);
    this.getAlpnSelectedProtocol = this.sslSocketClass.getMethod("getAlpnSelectedProtocol", new Class[0]);
    arrayOfClass1 = new Class[1];
    arrayOfClass1[0] = byte[].class;
    this.setAlpnProtocols = this.sslSocketClass.getMethod("setAlpnProtocols", arrayOfClass1);
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
  }
  
  public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
    return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory);
  }
  
  public boolean isSupported() {
    return AndroidPlatform.Companion.isSupported();
  }
  
  public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    return this.sslSocketClass.isInstance(sslSocket);
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    if (matchesSocket(sslSocket))
      try {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(true);
        this.setUseSessionTickets.invoke(sslSocket, arrayOfObject);
        if (hostname != null) {
          arrayOfObject = new Object[1];
          arrayOfObject[0] = hostname;
          this.setHostname.invoke(sslSocket, arrayOfObject);
        } 
        arrayOfObject = new Object[1];
        arrayOfObject[0] = Platform.Companion.concatLengthPrefixed(protocols);
        this.setAlpnProtocols.invoke(sslSocket, arrayOfObject);
      } catch (IllegalAccessException e) {
        throw new AssertionError(e);
      } catch (InvocationTargetException e) {
        throw new AssertionError(e);
      }  
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'sslSocket'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: aload_1
    //   8: invokevirtual matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
    //   11: ifne -> 16
    //   14: aconst_null
    //   15: areturn
    //   16: nop
    //   17: aload_0
    //   18: getfield getAlpnSelectedProtocol : Ljava/lang/reflect/Method;
    //   21: aload_1
    //   22: iconst_0
    //   23: anewarray java/lang/Object
    //   26: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   29: checkcast [B
    //   32: astore_2
    //   33: aload_2
    //   34: ifnull -> 51
    //   37: new java/lang/String
    //   40: dup
    //   41: aload_2
    //   42: getstatic kotlin/text/Charsets.UTF_8 : Ljava/nio/charset/Charset;
    //   45: invokespecial <init> : ([BLjava/nio/charset/Charset;)V
    //   48: goto -> 52
    //   51: aconst_null
    //   52: astore_2
    //   53: goto -> 112
    //   56: astore_3
    //   57: new java/lang/AssertionError
    //   60: dup
    //   61: aload_3
    //   62: invokespecial <init> : (Ljava/lang/Object;)V
    //   65: athrow
    //   66: astore_3
    //   67: aload_3
    //   68: invokevirtual getCause : ()Ljava/lang/Throwable;
    //   71: astore #4
    //   73: nop
    //   74: aload #4
    //   76: instanceof java/lang/NullPointerException
    //   79: ifeq -> 102
    //   82: aload #4
    //   84: checkcast java/lang/NullPointerException
    //   87: invokevirtual getMessage : ()Ljava/lang/String;
    //   90: ldc 'ssl == null'
    //   92: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   95: ifeq -> 102
    //   98: aconst_null
    //   99: goto -> 111
    //   102: new java/lang/AssertionError
    //   105: dup
    //   106: aload_3
    //   107: invokespecial <init> : (Ljava/lang/Object;)V
    //   110: athrow
    //   111: astore_2
    //   112: aload_2
    //   113: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #75	-> 6
    //   #76	-> 14
    //   #79	-> 16
    //   #80	-> 17
    //   #81	-> 33
    //   #81	-> 51
    //   #82	-> 56
    //   #83	-> 57
    //   #84	-> 66
    //   #86	-> 67
    //   #87	-> 73
    //   #88	-> 74
    //   #89	-> 102
    //   #79	-> 113
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   33	19	2	alpnResult	[B
    //   57	9	3	e	Ljava/lang/IllegalAccessException;
    //   73	38	4	cause	Ljava/lang/Throwable;
    //   67	45	3	e	Ljava/lang/reflect/InvocationTargetException;
    //   0	114	0	this	Lokhttp3/internal/platform/android/AndroidSocketAdapter;
    //   0	114	1	sslSocket	Ljavax/net/ssl/SSLSocket;
    // Exception table:
    //   from	to	target	type
    //   16	53	56	java/lang/IllegalAccessException
    //   16	53	66	java/lang/reflect/InvocationTargetException
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\037\020\b\032\0020\0072\016\020\006\032\n\022\006\b\000\022\0020\0050\004H\002¢\006\004\b\b\020\tJ\025\020\r\032\0020\f2\006\020\013\032\0020\n¢\006\004\b\r\020\016R\027\020\017\032\0020\f8\006¢\006\f\n\004\b\017\020\020\032\004\b\021\020\022¨\006\023"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter$Companion;", "", "<init>", "()V", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "actualSSLSocketClass", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "build", "(Ljava/lang/Class;)Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "", "packageName", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "factory", "(Ljava/lang/String;)Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "playProviderFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getPlayProviderFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final DeferredSocketAdapter.Factory getPlayProviderFactory() {
      return AndroidSocketAdapter.playProviderFactory;
    }
    
    private final AndroidSocketAdapter build(Class<? super SSLSocket> actualSSLSocketClass) {
      Class<? super SSLSocket> possibleClass = actualSSLSocketClass;
      while (possibleClass != null && !Intrinsics.areEqual(possibleClass.getSimpleName(), "OpenSSLSocketImpl")) {
        possibleClass = possibleClass.getSuperclass();
        if (possibleClass == null)
          throw new AssertionError(
              "No OpenSSLSocketImpl superclass of socket of type " + actualSSLSocketClass); 
      } 
      Intrinsics.checkNotNull(possibleClass);
      return new AndroidSocketAdapter(possibleClass);
    }
    
    @NotNull
    public final DeferredSocketAdapter.Factory factory(@NotNull String packageName) {
      Intrinsics.checkNotNullParameter(packageName, "packageName");
      return new AndroidSocketAdapter$Companion$factory$1(packageName);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\037\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003*\001\000\b\n\030\0002\0020\001J\027\020\005\032\0020\0042\006\020\003\032\0020\002H\026¢\006\004\b\005\020\006J\027\020\b\032\0020\0072\006\020\003\032\0020\002H\026¢\006\004\b\b\020\t¨\006\n"}, d2 = {"okhttp3/internal/platform/android/AndroidSocketAdapter.Companion.factory.1", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "Lokhttp3/internal/platform/android/SocketAdapter;", "create", "(Ljavax/net/ssl/SSLSocket;)Lokhttp3/internal/platform/android/SocketAdapter;", "", "matchesSocket", "(Ljavax/net/ssl/SSLSocket;)Z", "okhttp"})
    public static final class AndroidSocketAdapter$Companion$factory$1 implements DeferredSocketAdapter.Factory {
      AndroidSocketAdapter$Companion$factory$1(String $packageName) {}
      
      public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullExpressionValue(sslSocket.getClass().getName(), "sslSocket.javaClass.name");
        return StringsKt.startsWith$default(sslSocket.getClass().getName(), this.$packageName + '.', false, 2, null);
      }
      
      @NotNull
      public SocketAdapter create(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return AndroidSocketAdapter.Companion.build((Class)sslSocket.getClass());
      }
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Class<? super SSLSocket> sslSocketClass;
  
  @NotNull
  private final Method setUseSessionTickets;
  
  private final Method setHostname;
  
  private final Method getAlpnSelectedProtocol;
  
  private final Method setAlpnProtocols;
  
  @NotNull
  private static final DeferredSocketAdapter.Factory playProviderFactory = Companion.factory("com.google.android.gms.org.conscrypt");
}
