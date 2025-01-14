package okhttp3.internal.platform;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.android.Android10SocketAdapter;
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.tls.CertificateChainCleaner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000P\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\b\007\030\000 \0362\0020\001:\001\036B\007¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\007\020\bJ/\020\021\032\0020\0202\006\020\n\032\0020\t2\b\020\f\032\004\030\0010\0132\f\020\017\032\b\022\004\022\0020\0160\rH\026¢\006\004\b\021\020\022J\031\020\023\032\004\030\0010\0132\006\020\n\032\0020\tH\026¢\006\004\b\023\020\024J\027\020\026\032\0020\0252\006\020\f\032\0020\013H\027¢\006\004\b\026\020\027J\031\020\005\032\004\030\0010\0042\006\020\031\032\0020\030H\026¢\006\004\b\005\020\032R\032\020\034\032\b\022\004\022\0020\0330\r8\002X\004¢\006\006\n\004\b\034\020\035¨\006\037"}, d2 = {"Lokhttp3/internal/platform/Android10Platform;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Lokhttp3/internal/tls/CertificateChainCleaner;", "buildCertificateChainCleaner", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/CertificateChainCleaner;", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "", "isCleartextTrafficPermitted", "(Ljava/lang/String;)Z", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Lokhttp3/internal/platform/android/SocketAdapter;", "socketAdapters", "Ljava/util/List;", "Companion", "okhttp"})
@SuppressSignatureCheck
@SourceDebugExtension({"SMAP\nAndroid10Platform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Android10Platform.kt\nokhttp3/internal/platform/Android10Platform\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,72:1\n766#2:73\n857#2,2:74\n1#3:76\n*S KotlinDebug\n*F\n+ 1 Android10Platform.kt\nokhttp3/internal/platform/Android10Platform\n*L\n43#1:73\n43#1:74,2\n*E\n"})
public final class Android10Platform extends Platform {
  public Android10Platform() {
    SocketAdapter[] arrayOfSocketAdapter = new SocketAdapter[4];
    arrayOfSocketAdapter[0] = Android10SocketAdapter.Companion.buildIfSupported();
    arrayOfSocketAdapter[1] = 
      (SocketAdapter)new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory());
    arrayOfSocketAdapter[2] = (SocketAdapter)new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory());
    arrayOfSocketAdapter[3] = (SocketAdapter)new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory());
    List list1 = CollectionsKt.listOfNotNull((Object[])arrayOfSocketAdapter);
    Android10Platform android10Platform = this;
    int $i$f$filter = 0;
    List list2 = list1;
    Collection<Object> destination$iv$iv = new ArrayList();
    int $i$f$filterTo = 0;
    for (Object element$iv$iv : list2) {
      SocketAdapter it = (SocketAdapter)element$iv$iv;
      int $i$a$-filter-Android10Platform$socketAdapters$1 = 0;
      if (it.isSupported())
        destination$iv$iv.add(element$iv$iv); 
    } 
    android10Platform.socketAdapters = (List)destination$iv$iv;
  }
  
  @Nullable
  public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'sslSocketFactory'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: getfield socketAdapters : Ljava/util/List;
    //   10: checkcast java/lang/Iterable
    //   13: astore_3
    //   14: aload_3
    //   15: invokeinterface iterator : ()Ljava/util/Iterator;
    //   20: astore #4
    //   22: aload #4
    //   24: invokeinterface hasNext : ()Z
    //   29: ifeq -> 67
    //   32: aload #4
    //   34: invokeinterface next : ()Ljava/lang/Object;
    //   39: astore #5
    //   41: aload #5
    //   43: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   46: astore #6
    //   48: iconst_0
    //   49: istore #7
    //   51: aload #6
    //   53: aload_1
    //   54: invokeinterface matchesSocketFactory : (Ljavax/net/ssl/SSLSocketFactory;)Z
    //   59: ifeq -> 22
    //   62: aload #5
    //   64: goto -> 68
    //   67: aconst_null
    //   68: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   71: astore_2
    //   72: aload_2
    //   73: ifnull -> 86
    //   76: aload_2
    //   77: aload_1
    //   78: invokeinterface trustManager : (Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;
    //   83: goto -> 87
    //   86: aconst_null
    //   87: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #47	-> 6
    //   #46	-> 6
    //   #76	-> 48
    //   #46	-> 51
    //   #46	-> 59
    //   #46	-> 68
    //   #47	-> 72
    //   #46	-> 76
    //   #47	-> 77
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   51	8	7	$i$a$-find-Android10Platform$trustManager$1	I
    //   48	11	6	it	Lokhttp3/internal/platform/android/SocketAdapter;
    //   0	88	0	this	Lokhttp3/internal/platform/Android10Platform;
    //   0	88	1	sslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'sslSocket'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_3
    //   7: ldc 'protocols'
    //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_0
    //   13: getfield socketAdapters : Ljava/util/List;
    //   16: checkcast java/lang/Iterable
    //   19: astore #5
    //   21: aload #5
    //   23: invokeinterface iterator : ()Ljava/util/Iterator;
    //   28: astore #6
    //   30: aload #6
    //   32: invokeinterface hasNext : ()Z
    //   37: ifeq -> 75
    //   40: aload #6
    //   42: invokeinterface next : ()Ljava/lang/Object;
    //   47: astore #7
    //   49: aload #7
    //   51: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   54: astore #8
    //   56: iconst_0
    //   57: istore #9
    //   59: aload #8
    //   61: aload_1
    //   62: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
    //   67: ifeq -> 30
    //   70: aload #7
    //   72: goto -> 76
    //   75: aconst_null
    //   76: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   79: astore #4
    //   81: aload #4
    //   83: ifnull -> 96
    //   86: aload #4
    //   88: aload_1
    //   89: aload_2
    //   90: aload_3
    //   91: invokeinterface configureTlsExtensions : (Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
    //   96: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #52	-> 12
    //   #51	-> 12
    //   #76	-> 56
    //   #51	-> 59
    //   #51	-> 67
    //   #51	-> 76
    //   #52	-> 81
    //   #51	-> 86
    //   #52	-> 88
    //   #53	-> 96
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   59	8	9	$i$a$-find-Android10Platform$configureTlsExtensions$1	I
    //   56	11	8	it	Lokhttp3/internal/platform/android/SocketAdapter;
    //   0	97	0	this	Lokhttp3/internal/platform/Android10Platform;
    //   0	97	1	sslSocket	Ljavax/net/ssl/SSLSocket;
    //   0	97	2	hostname	Ljava/lang/String;
    //   0	97	3	protocols	Ljava/util/List;
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'sslSocket'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: getfield socketAdapters : Ljava/util/List;
    //   10: checkcast java/lang/Iterable
    //   13: astore_2
    //   14: aload_2
    //   15: invokeinterface iterator : ()Ljava/util/Iterator;
    //   20: astore_3
    //   21: aload_3
    //   22: invokeinterface hasNext : ()Z
    //   27: ifeq -> 64
    //   30: aload_3
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: astore #4
    //   38: aload #4
    //   40: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   43: astore #5
    //   45: iconst_0
    //   46: istore #6
    //   48: aload #5
    //   50: aload_1
    //   51: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
    //   56: ifeq -> 21
    //   59: aload #4
    //   61: goto -> 65
    //   64: aconst_null
    //   65: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   68: dup
    //   69: ifnull -> 81
    //   72: aload_1
    //   73: invokeinterface getSelectedProtocol : (Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
    //   78: goto -> 83
    //   81: pop
    //   82: aconst_null
    //   83: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #57	-> 6
    //   #76	-> 45
    //   #57	-> 48
    //   #57	-> 56
    //   #57	-> 65
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   48	8	6	$i$a$-find-Android10Platform$getSelectedProtocol$1	I
    //   45	11	5	it	Lokhttp3/internal/platform/android/SocketAdapter;
    //   0	84	0	this	Lokhttp3/internal/platform/Android10Platform;
    //   0	84	1	sslSocket	Ljavax/net/ssl/SSLSocket;
  }
  
  @SuppressLint({"NewApi"})
  public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
  }
  
  @NotNull
  public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager);
    return (AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) != null) ? (CertificateChainCleaner)AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) : super.buildCertificateChainCleaner(trustManager);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\004\030\0010\004¢\006\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\b\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/platform/Android10Platform$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/Platform;", "buildIfSupported", "()Lokhttp3/internal/platform/Platform;", "", "isSupported", "Z", "()Z", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final boolean isSupported() {
      return Android10Platform.isSupported;
    }
    
    @Nullable
    public final Platform buildIfSupported() {
      return isSupported() ? new Android10Platform() : null;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final List<SocketAdapter> socketAdapters;
  
  private static final boolean isSupported = (Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29);
}
