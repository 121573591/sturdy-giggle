package okhttp3.internal.platform;

import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
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
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.CloseGuard;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.platform.android.StandardAndroidSocketAdapter;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\001\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\006\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\b\007\030\000 52\0020\001:\00256B\007¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\007\020\bJ\027\020\n\032\0020\t2\006\020\005\032\0020\004H\026¢\006\004\b\n\020\013J4\020\025\032\0020\0242\006\020\r\032\0020\f2\b\020\017\032\004\030\0010\0162\021\020\023\032\r\022\t\022\0070\021¢\006\002\b\0220\020H\026¢\006\004\b\025\020\026J'\020\035\032\0020\0242\006\020\030\032\0020\0272\006\020\032\032\0020\0312\006\020\034\032\0020\033H\026¢\006\004\b\035\020\036J\031\020\037\032\004\030\0010\0162\006\020\r\032\0020\fH\026¢\006\004\b\037\020 J\031\020#\032\004\030\0010\"2\006\020!\032\0020\016H\026¢\006\004\b#\020$J\027\020&\032\0020%2\006\020\017\032\0020\016H\026¢\006\004\b&\020'J!\020*\032\0020\0242\006\020(\032\0020\0162\b\020)\032\004\030\0010\"H\026¢\006\004\b*\020+J\031\020\005\032\004\030\0010\0042\006\020-\032\0020,H\026¢\006\004\b\005\020.R\024\0200\032\0020/8\002X\004¢\006\006\n\004\b0\0201R\032\0203\032\b\022\004\022\002020\0208\002X\004¢\006\006\n\004\b3\0204¨\0067"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Lokhttp3/internal/tls/CertificateChainCleaner;", "buildCertificateChainCleaner", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/CertificateChainCleaner;", "Lokhttp3/internal/tls/TrustRootIndex;", "buildTrustRootIndex", "(Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/TrustRootIndex;", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "hostname", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "protocols", "", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "Ljava/net/Socket;", "socket", "Ljava/net/InetSocketAddress;", "address", "", "connectTimeout", "connectSocket", "(Ljava/net/Socket;Ljava/net/InetSocketAddress;I)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "closer", "", "getStackTraceForCloseable", "(Ljava/lang/String;)Ljava/lang/Object;", "", "isCleartextTrafficPermitted", "(Ljava/lang/String;)Z", "message", "stackTrace", "logCloseableLeak", "(Ljava/lang/String;Ljava/lang/Object;)V", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;", "Lokhttp3/internal/platform/android/CloseGuard;", "closeGuard", "Lokhttp3/internal/platform/android/CloseGuard;", "Lokhttp3/internal/platform/android/SocketAdapter;", "socketAdapters", "Ljava/util/List;", "Companion", "CustomTrustRootIndex", "okhttp"})
@SuppressSignatureCheck
@SourceDebugExtension({"SMAP\nAndroidPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidPlatform.kt\nokhttp3/internal/platform/AndroidPlatform\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,163:1\n766#2:164\n857#2,2:165\n1#3:167\n*S KotlinDebug\n*F\n+ 1 AndroidPlatform.kt\nokhttp3/internal/platform/AndroidPlatform\n*L\n52#1:164\n52#1:165,2\n*E\n"})
public final class AndroidPlatform extends Platform {
  public AndroidPlatform() {
    SocketAdapter[] arrayOfSocketAdapter = new SocketAdapter[4];
    arrayOfSocketAdapter[0] = StandardAndroidSocketAdapter.Companion.buildIfSupported$default(StandardAndroidSocketAdapter.Companion, null, 1, null);
    arrayOfSocketAdapter[1] = 
      (SocketAdapter)new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory());
    arrayOfSocketAdapter[2] = (SocketAdapter)new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory());
    arrayOfSocketAdapter[3] = (SocketAdapter)new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory());
    List list1 = CollectionsKt.listOfNotNull((Object[])arrayOfSocketAdapter);
    AndroidPlatform androidPlatform = this;
    int $i$f$filter = 0;
    List list2 = list1;
    Collection<Object> destination$iv$iv = new ArrayList();
    int $i$f$filterTo = 0;
    for (Object element$iv$iv : list2) {
      SocketAdapter it = (SocketAdapter)element$iv$iv;
      int $i$a$-filter-AndroidPlatform$socketAdapters$1 = 0;
      if (it.isSupported())
        destination$iv$iv.add(element$iv$iv); 
    } 
    androidPlatform.socketAdapters = (List)destination$iv$iv;
    this.closeGuard = CloseGuard.Companion.get();
  }
  
  public void connectSocket(@NotNull Socket socket, @NotNull InetSocketAddress address, int connectTimeout) throws IOException {
    Intrinsics.checkNotNullParameter(socket, "socket");
    Intrinsics.checkNotNullParameter(address, "address");
    try {
      socket.connect(address, connectTimeout);
    } catch (ClassCastException e) {
      if (Build.VERSION.SDK_INT == 26)
        throw new IOException("Exception in connect", (Throwable)e); 
      throw e;
    } 
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
    //   #77	-> 6
    //   #76	-> 6
    //   #167	-> 48
    //   #76	-> 51
    //   #76	-> 59
    //   #76	-> 68
    //   #77	-> 72
    //   #76	-> 76
    //   #77	-> 77
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   51	8	7	$i$a$-find-AndroidPlatform$trustManager$1	I
    //   48	11	6	it	Lokhttp3/internal/platform/android/SocketAdapter;
    //   0	88	0	this	Lokhttp3/internal/platform/AndroidPlatform;
    //   0	88	1	sslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'sslSocket'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_3
    //   7: ldc_w 'protocols'
    //   10: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   13: aload_0
    //   14: getfield socketAdapters : Ljava/util/List;
    //   17: checkcast java/lang/Iterable
    //   20: astore #5
    //   22: aload #5
    //   24: invokeinterface iterator : ()Ljava/util/Iterator;
    //   29: astore #6
    //   31: aload #6
    //   33: invokeinterface hasNext : ()Z
    //   38: ifeq -> 76
    //   41: aload #6
    //   43: invokeinterface next : ()Ljava/lang/Object;
    //   48: astore #7
    //   50: aload #7
    //   52: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   55: astore #8
    //   57: iconst_0
    //   58: istore #9
    //   60: aload #8
    //   62: aload_1
    //   63: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
    //   68: ifeq -> 31
    //   71: aload #7
    //   73: goto -> 77
    //   76: aconst_null
    //   77: checkcast okhttp3/internal/platform/android/SocketAdapter
    //   80: astore #4
    //   82: aload #4
    //   84: ifnull -> 97
    //   87: aload #4
    //   89: aload_1
    //   90: aload_2
    //   91: aload_3
    //   92: invokeinterface configureTlsExtensions : (Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
    //   97: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #86	-> 13
    //   #85	-> 13
    //   #167	-> 57
    //   #85	-> 60
    //   #85	-> 68
    //   #85	-> 77
    //   #86	-> 82
    //   #85	-> 87
    //   #86	-> 89
    //   #87	-> 97
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   60	8	9	$i$a$-find-AndroidPlatform$configureTlsExtensions$1	I
    //   57	11	8	it	Lokhttp3/internal/platform/android/SocketAdapter;
    //   0	98	0	this	Lokhttp3/internal/platform/AndroidPlatform;
    //   0	98	1	sslSocket	Ljavax/net/ssl/SSLSocket;
    //   0	98	2	hostname	Ljava/lang/String;
    //   0	98	3	protocols	Ljava/util/List;
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
    //   #91	-> 6
    //   #167	-> 45
    //   #91	-> 48
    //   #91	-> 56
    //   #91	-> 65
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   48	8	6	$i$a$-find-AndroidPlatform$getSelectedProtocol$1	I
    //   45	11	5	it	Lokhttp3/internal/platform/android/SocketAdapter;
    //   0	84	0	this	Lokhttp3/internal/platform/AndroidPlatform;
    //   0	84	1	sslSocket	Ljavax/net/ssl/SSLSocket;
  }
  
  @Nullable
  public Object getStackTraceForCloseable(@NotNull String closer) {
    Intrinsics.checkNotNullParameter(closer, "closer");
    return this.closeGuard.createAndOpen(closer);
  }
  
  public void logCloseableLeak(@NotNull String message, @Nullable Object stackTrace) {
    Intrinsics.checkNotNullParameter(message, "message");
    boolean reported = this.closeGuard.warnIfOpen(stackTrace);
    if (!reported)
      Platform.log$default(this, message, 5, null, 4, null); 
  }
  
  public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
    Intrinsics.checkNotNullParameter(hostname, "hostname");
    return (Build.VERSION.SDK_INT >= 24) ? NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname) : ((Build.VERSION.SDK_INT >= 23) ? NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() : true);
  }
  
  @NotNull
  public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager);
    return (AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) != null) ? (CertificateChainCleaner)AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) : super.buildCertificateChainCleaner(trustManager);
  }
  
  @NotNull
  public TrustRootIndex buildTrustRootIndex(@NotNull X509TrustManager trustManager) {
    TrustRootIndex trustRootIndex;
    Intrinsics.checkNotNullParameter(trustManager, "trustManager");
    try {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = X509Certificate.class;
      Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", arrayOfClass);
      method.setAccessible(true);
      Intrinsics.checkNotNullExpressionValue(method, "method");
      trustRootIndex = new CustomTrustRootIndex(trustManager, method);
    } catch (NoSuchMethodException e) {
      trustRootIndex = super.buildTrustRootIndex(trustManager);
    } 
    return trustRootIndex;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\t\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\002\n\002\020\016\n\002\b\005\b\b\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\020\020\b\032\0020\002HÂ\003¢\006\004\b\b\020\tJ\020\020\n\032\0020\004HÂ\003¢\006\004\b\n\020\013J$\020\f\032\0020\0002\b\b\002\020\003\032\0020\0022\b\b\002\020\005\032\0020\004HÆ\001¢\006\004\b\f\020\rJ\032\020\021\032\0020\0202\b\020\017\032\004\030\0010\016HÖ\003¢\006\004\b\021\020\022J\031\020\025\032\004\030\0010\0232\006\020\024\032\0020\023H\026¢\006\004\b\025\020\026J\020\020\030\032\0020\027HÖ\001¢\006\004\b\030\020\031J\020\020\033\032\0020\032HÖ\001¢\006\004\b\033\020\034R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\035R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\036¨\006\037"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$CustomTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "Ljava/lang/reflect/Method;", "findByIssuerAndSignatureMethod", "<init>", "(Ljavax/net/ssl/X509TrustManager;Ljava/lang/reflect/Method;)V", "component1", "()Ljavax/net/ssl/X509TrustManager;", "component2", "()Ljava/lang/reflect/Method;", "copy", "(Ljavax/net/ssl/X509TrustManager;Ljava/lang/reflect/Method;)Lokhttp3/internal/platform/AndroidPlatform$CustomTrustRootIndex;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/security/cert/X509Certificate;", "cert", "findByIssuerAndSignature", "(Ljava/security/cert/X509Certificate;)Ljava/security/cert/X509Certificate;", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/lang/reflect/Method;", "Ljavax/net/ssl/X509TrustManager;", "okhttp"})
  public static final class CustomTrustRootIndex implements TrustRootIndex {
    @NotNull
    private final X509TrustManager trustManager;
    
    @NotNull
    private final Method findByIssuerAndSignatureMethod;
    
    public CustomTrustRootIndex(@NotNull X509TrustManager trustManager, @NotNull Method findByIssuerAndSignatureMethod) {
      this.trustManager = trustManager;
      this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
    }
    
    @Nullable
    public X509Certificate findByIssuerAndSignature(@NotNull X509Certificate cert) {
      X509Certificate x509Certificate;
      Intrinsics.checkNotNullParameter(cert, "cert");
      try {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = cert;
        Intrinsics.checkNotNull(this.findByIssuerAndSignatureMethod.invoke(this.trustManager, arrayOfObject), "null cannot be cast to non-null type java.security.cert.TrustAnchor");
        TrustAnchor trustAnchor = (TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, arrayOfObject);
        x509Certificate = trustAnchor.getTrustedCert();
      } catch (IllegalAccessException e) {
        throw new AssertionError("unable to get issues and signature", (Throwable)e);
      } catch (InvocationTargetException _) {
        x509Certificate = null;
      } 
      return x509Certificate;
    }
    
    private final X509TrustManager component1() {
      return this.trustManager;
    }
    
    private final Method component2() {
      return this.findByIssuerAndSignatureMethod;
    }
    
    @NotNull
    public final CustomTrustRootIndex copy(@NotNull X509TrustManager trustManager, @NotNull Method findByIssuerAndSignatureMethod) {
      Intrinsics.checkNotNullParameter(trustManager, "trustManager");
      Intrinsics.checkNotNullParameter(findByIssuerAndSignatureMethod, "findByIssuerAndSignatureMethod");
      return new CustomTrustRootIndex(trustManager, findByIssuerAndSignatureMethod);
    }
    
    @NotNull
    public String toString() {
      return "CustomTrustRootIndex(trustManager=" + this.trustManager + ", findByIssuerAndSignatureMethod=" + this.findByIssuerAndSignatureMethod + ')';
    }
    
    public int hashCode() {
      result = this.trustManager.hashCode();
      return result * 31 + this.findByIssuerAndSignatureMethod.hashCode();
    }
    
    public boolean equals(@Nullable Object other) {
      if (this == other)
        return true; 
      if (!(other instanceof CustomTrustRootIndex))
        return false; 
      CustomTrustRootIndex customTrustRootIndex = (CustomTrustRootIndex)other;
      return !Intrinsics.areEqual(this.trustManager, customTrustRootIndex.trustManager) ? false : (!!Intrinsics.areEqual(this.findByIssuerAndSignatureMethod, customTrustRootIndex.findByIssuerAndSignatureMethod));
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\004\030\0010\004¢\006\004\b\005\020\006R\027\020\b\032\0020\0078\006¢\006\f\n\004\b\b\020\t\032\004\b\b\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/Platform;", "buildIfSupported", "()Lokhttp3/internal/platform/Platform;", "", "isSupported", "Z", "()Z", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    public final boolean isSupported() {
      return AndroidPlatform.isSupported;
    }
    
    @Nullable
    public final Platform buildIfSupported() {
      return isSupported() ? new AndroidPlatform() : null;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final List<SocketAdapter> socketAdapters;
  
  @NotNull
  private final CloseGuard closeGuard;
  
  private static final boolean isSupported = !Platform.Companion.isAndroid() ? false : (!(Build.VERSION.SDK_INT >= 30));
  
  static {
    if (!((Build.VERSION.SDK_INT >= 21) ? 1 : 0)) {
      int $i$a$-check-AndroidPlatform$Companion$isSupported$1 = 0;
      String str = "Expected Android API level 21+ but was " + Build.VERSION.SDK_INT;
      throw new IllegalStateException(str.toString());
    } 
  }
}
