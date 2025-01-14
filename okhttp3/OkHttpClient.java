package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000ô\001\n\002\030\002\n\002\020\032\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\006\n\002\030\002\n\002\b\f\n\002\020\t\n\002\b\005\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\006\b\026\030\000 \0012\0020\0012\0020\0022\0020\003:\004\001\001B\t\b\026¢\006\004\b\004\020\005B\021\b\000\022\006\020\007\032\0020\006¢\006\004\b\004\020\bJ\017\020\f\032\0020\tH\007¢\006\004\b\n\020\013J\021\020\020\032\004\030\0010\rH\007¢\006\004\b\016\020\017J\017\020\024\032\0020\021H\007¢\006\004\b\022\020\023J\017\020\030\032\0020\025H\007¢\006\004\b\026\020\027J\017\020\032\032\0020\021H\007¢\006\004\b\031\020\023J\017\020\036\032\0020\033H\007¢\006\004\b\034\020\035J\025\020#\032\b\022\004\022\0020 0\037H\007¢\006\004\b!\020\"J\017\020'\032\0020$H\007¢\006\004\b%\020&J\017\020+\032\0020(H\007¢\006\004\b)\020*J\017\020/\032\0020,H\007¢\006\004\b-\020.J\017\0203\032\00200H\007¢\006\004\b1\0202J\017\0207\032\00204H\007¢\006\004\b5\0206J\017\0209\032\00204H\007¢\006\004\b8\0206J\017\020=\032\0020:H\007¢\006\004\b;\020<J\025\020@\032\b\022\004\022\0020>0\037H\007¢\006\004\b?\020\"J\025\020B\032\b\022\004\022\0020>0\037H\007¢\006\004\bA\020\"J\017\020C\032\0020\006H\026¢\006\004\bC\020DJ\027\020H\032\0020G2\006\020F\032\0020EH\026¢\006\004\bH\020IJ\037\020M\032\0020L2\006\020F\032\0020E2\006\020K\032\0020JH\026¢\006\004\bM\020NJ\017\020P\032\0020\021H\007¢\006\004\bO\020\023J\025\020S\032\b\022\004\022\0020Q0\037H\007¢\006\004\bR\020\"J\021\020W\032\004\030\0010TH\007¢\006\004\bU\020VJ\017\020Y\032\0020\tH\007¢\006\004\bX\020\013J\017\020]\032\0020ZH\007¢\006\004\b[\020\\J\017\020_\032\0020\021H\007¢\006\004\b^\020\023J\017\020a\032\00204H\007¢\006\004\b`\0206J\017\020e\032\0020bH\007¢\006\004\bc\020dJ\017\020i\032\0020fH\007¢\006\004\bg\020hJ\017\020k\032\0020jH\002¢\006\004\bk\020\005J\017\020m\032\0020\021H\007¢\006\004\bl\020\023R\027\020\f\032\0020\t8G¢\006\f\n\004\b\f\020n\032\004\b\f\020\013R\031\020\020\032\004\030\0010\r8G¢\006\f\n\004\b\020\020o\032\004\b\020\020\017R\027\020\024\032\0020\0218G¢\006\f\n\004\b\024\020p\032\004\b\024\020\023R\031\020r\032\004\030\0010q8G¢\006\f\n\004\br\020s\032\004\br\020tR\027\020\030\032\0020\0258G¢\006\f\n\004\b\030\020u\032\004\b\030\020\027R\027\020\032\032\0020\0218G¢\006\f\n\004\b\032\020p\032\004\b\032\020\023R\027\020\036\032\0020\0338G¢\006\f\n\004\b\036\020v\032\004\b\036\020\035R\035\020#\032\b\022\004\022\0020 0\0378G¢\006\f\n\004\b#\020w\032\004\b#\020\"R\027\020'\032\0020$8G¢\006\f\n\004\b'\020x\032\004\b'\020&R\027\020+\032\0020(8G¢\006\f\n\004\b+\020y\032\004\b+\020*R\027\020/\032\0020,8G¢\006\f\n\004\b/\020z\032\004\b/\020.R\027\0203\032\002008G¢\006\f\n\004\b3\020{\032\004\b3\0202R\027\0207\032\002048G¢\006\f\n\004\b7\020|\032\004\b7\0206R\027\0209\032\002048G¢\006\f\n\004\b9\020|\032\004\b9\0206R\027\020=\032\0020:8G¢\006\f\n\004\b=\020}\032\004\b=\020<R\035\020@\032\b\022\004\022\0020>0\0378G¢\006\f\n\004\b@\020w\032\004\b@\020\"R\031\020\032\0020~8G¢\006\016\n\005\b\020\001\032\005\b\020\001R\035\020B\032\b\022\004\022\0020>0\0378G¢\006\f\n\004\bB\020w\032\004\bB\020\"R\027\020P\032\0020\0218G¢\006\f\n\004\bP\020p\032\004\bP\020\023R\035\020S\032\b\022\004\022\0020Q0\0378G¢\006\f\n\004\bS\020w\032\004\bS\020\"R\032\020W\032\004\030\0010T8G¢\006\r\n\005\bW\020\001\032\004\bW\020VR\027\020Y\032\0020\t8G¢\006\f\n\004\bY\020n\032\004\bY\020\013R\030\020]\032\0020Z8G¢\006\r\n\005\b]\020\001\032\004\b]\020\\R\027\020_\032\0020\0218G¢\006\f\n\004\b_\020p\032\004\b_\020\023R\027\020a\032\002048G¢\006\f\n\004\ba\020|\032\004\ba\0206R\035\020\001\032\0030\0018\006¢\006\020\n\006\b\001\020\001\032\006\b\001\020\001R\030\020e\032\0020b8G¢\006\r\n\005\be\020\001\032\004\be\020dR\021\020i\032\0020f8G¢\006\006\032\004\bi\020hR\031\020\001\032\004\030\0010f8\002X\004¢\006\b\n\006\b\001\020\001R\027\020m\032\0020\0218G¢\006\f\n\004\bm\020p\032\004\bm\020\023R\037\020\001\032\005\030\0010\0018G¢\006\020\n\006\b\001\020\001\032\006\b\001\020\001¨\006\001"}, d2 = {"Lokhttp3/OkHttpClient;", "", "Lokhttp3/Call$Factory;", "Lokhttp3/WebSocket$Factory;", "<init>", "()V", "Lokhttp3/OkHttpClient$Builder;", "builder", "(Lokhttp3/OkHttpClient$Builder;)V", "Lokhttp3/Authenticator;", "-deprecated_authenticator", "()Lokhttp3/Authenticator;", "authenticator", "Lokhttp3/Cache;", "-deprecated_cache", "()Lokhttp3/Cache;", "cache", "", "-deprecated_callTimeoutMillis", "()I", "callTimeoutMillis", "Lokhttp3/CertificatePinner;", "-deprecated_certificatePinner", "()Lokhttp3/CertificatePinner;", "certificatePinner", "-deprecated_connectTimeoutMillis", "connectTimeoutMillis", "Lokhttp3/ConnectionPool;", "-deprecated_connectionPool", "()Lokhttp3/ConnectionPool;", "connectionPool", "", "Lokhttp3/ConnectionSpec;", "-deprecated_connectionSpecs", "()Ljava/util/List;", "connectionSpecs", "Lokhttp3/CookieJar;", "-deprecated_cookieJar", "()Lokhttp3/CookieJar;", "cookieJar", "Lokhttp3/Dispatcher;", "-deprecated_dispatcher", "()Lokhttp3/Dispatcher;", "dispatcher", "Lokhttp3/Dns;", "-deprecated_dns", "()Lokhttp3/Dns;", "dns", "Lokhttp3/EventListener$Factory;", "-deprecated_eventListenerFactory", "()Lokhttp3/EventListener$Factory;", "eventListenerFactory", "", "-deprecated_followRedirects", "()Z", "followRedirects", "-deprecated_followSslRedirects", "followSslRedirects", "Ljavax/net/ssl/HostnameVerifier;", "-deprecated_hostnameVerifier", "()Ljavax/net/ssl/HostnameVerifier;", "hostnameVerifier", "Lokhttp3/Interceptor;", "-deprecated_interceptors", "interceptors", "-deprecated_networkInterceptors", "networkInterceptors", "newBuilder", "()Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/Request;", "request", "Lokhttp3/Call;", "newCall", "(Lokhttp3/Request;)Lokhttp3/Call;", "Lokhttp3/WebSocketListener;", "listener", "Lokhttp3/WebSocket;", "newWebSocket", "(Lokhttp3/Request;Lokhttp3/WebSocketListener;)Lokhttp3/WebSocket;", "-deprecated_pingIntervalMillis", "pingIntervalMillis", "Lokhttp3/Protocol;", "-deprecated_protocols", "protocols", "Ljava/net/Proxy;", "-deprecated_proxy", "()Ljava/net/Proxy;", "proxy", "-deprecated_proxyAuthenticator", "proxyAuthenticator", "Ljava/net/ProxySelector;", "-deprecated_proxySelector", "()Ljava/net/ProxySelector;", "proxySelector", "-deprecated_readTimeoutMillis", "readTimeoutMillis", "-deprecated_retryOnConnectionFailure", "retryOnConnectionFailure", "Ljavax/net/SocketFactory;", "-deprecated_socketFactory", "()Ljavax/net/SocketFactory;", "socketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "-deprecated_sslSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "", "verifyClientState", "-deprecated_writeTimeoutMillis", "writeTimeoutMillis", "Lokhttp3/Authenticator;", "Lokhttp3/Cache;", "I", "Lokhttp3/internal/tls/CertificateChainCleaner;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "Lokhttp3/CertificatePinner;", "Lokhttp3/ConnectionPool;", "Ljava/util/List;", "Lokhttp3/CookieJar;", "Lokhttp3/Dispatcher;", "Lokhttp3/Dns;", "Lokhttp3/EventListener$Factory;", "Z", "Ljavax/net/ssl/HostnameVerifier;", "", "minWebSocketMessageToCompress", "J", "()J", "Ljava/net/Proxy;", "Ljava/net/ProxySelector;", "Lokhttp3/internal/connection/RouteDatabase;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase", "()Lokhttp3/internal/connection/RouteDatabase;", "Ljavax/net/SocketFactory;", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "Ljavax/net/ssl/X509TrustManager;", "x509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "()Ljavax/net/ssl/X509TrustManager;", "Companion", "Builder", "okhttp"})
@SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1079:1\n2624#2,3:1080\n2624#2,3:1083\n1#3:1086\n*S KotlinDebug\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient\n*L\n225#1:1080,3\n255#1:1083,3\n*E\n"})
public class OkHttpClient implements Cloneable, Call.Factory, WebSocket.Factory {
  public OkHttpClient(@NotNull Builder builder) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'builder'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual getDispatcher$okhttp : ()Lokhttp3/Dispatcher;
    //   15: putfield dispatcher : Lokhttp3/Dispatcher;
    //   18: aload_0
    //   19: aload_1
    //   20: invokevirtual getConnectionPool$okhttp : ()Lokhttp3/ConnectionPool;
    //   23: putfield connectionPool : Lokhttp3/ConnectionPool;
    //   26: aload_0
    //   27: aload_1
    //   28: invokevirtual getInterceptors$okhttp : ()Ljava/util/List;
    //   31: invokestatic toImmutableList : (Ljava/util/List;)Ljava/util/List;
    //   34: putfield interceptors : Ljava/util/List;
    //   37: aload_0
    //   38: aload_1
    //   39: invokevirtual getNetworkInterceptors$okhttp : ()Ljava/util/List;
    //   42: invokestatic toImmutableList : (Ljava/util/List;)Ljava/util/List;
    //   45: putfield networkInterceptors : Ljava/util/List;
    //   48: aload_0
    //   49: aload_1
    //   50: invokevirtual getEventListenerFactory$okhttp : ()Lokhttp3/EventListener$Factory;
    //   53: putfield eventListenerFactory : Lokhttp3/EventListener$Factory;
    //   56: aload_0
    //   57: aload_1
    //   58: invokevirtual getRetryOnConnectionFailure$okhttp : ()Z
    //   61: putfield retryOnConnectionFailure : Z
    //   64: aload_0
    //   65: aload_1
    //   66: invokevirtual getAuthenticator$okhttp : ()Lokhttp3/Authenticator;
    //   69: putfield authenticator : Lokhttp3/Authenticator;
    //   72: aload_0
    //   73: aload_1
    //   74: invokevirtual getFollowRedirects$okhttp : ()Z
    //   77: putfield followRedirects : Z
    //   80: aload_0
    //   81: aload_1
    //   82: invokevirtual getFollowSslRedirects$okhttp : ()Z
    //   85: putfield followSslRedirects : Z
    //   88: aload_0
    //   89: aload_1
    //   90: invokevirtual getCookieJar$okhttp : ()Lokhttp3/CookieJar;
    //   93: putfield cookieJar : Lokhttp3/CookieJar;
    //   96: aload_0
    //   97: aload_1
    //   98: invokevirtual getCache$okhttp : ()Lokhttp3/Cache;
    //   101: putfield cache : Lokhttp3/Cache;
    //   104: aload_0
    //   105: aload_1
    //   106: invokevirtual getDns$okhttp : ()Lokhttp3/Dns;
    //   109: putfield dns : Lokhttp3/Dns;
    //   112: aload_0
    //   113: aload_1
    //   114: invokevirtual getProxy$okhttp : ()Ljava/net/Proxy;
    //   117: putfield proxy : Ljava/net/Proxy;
    //   120: aload_0
    //   121: aload_1
    //   122: invokevirtual getProxy$okhttp : ()Ljava/net/Proxy;
    //   125: ifnull -> 137
    //   128: getstatic okhttp3/internal/proxy/NullProxySelector.INSTANCE : Lokhttp3/internal/proxy/NullProxySelector;
    //   131: checkcast java/net/ProxySelector
    //   134: goto -> 160
    //   137: aload_1
    //   138: invokevirtual getProxySelector$okhttp : ()Ljava/net/ProxySelector;
    //   141: dup
    //   142: ifnonnull -> 149
    //   145: pop
    //   146: invokestatic getDefault : ()Ljava/net/ProxySelector;
    //   149: dup
    //   150: ifnonnull -> 160
    //   153: pop
    //   154: getstatic okhttp3/internal/proxy/NullProxySelector.INSTANCE : Lokhttp3/internal/proxy/NullProxySelector;
    //   157: checkcast java/net/ProxySelector
    //   160: putfield proxySelector : Ljava/net/ProxySelector;
    //   163: aload_0
    //   164: aload_1
    //   165: invokevirtual getProxyAuthenticator$okhttp : ()Lokhttp3/Authenticator;
    //   168: putfield proxyAuthenticator : Lokhttp3/Authenticator;
    //   171: aload_0
    //   172: aload_1
    //   173: invokevirtual getSocketFactory$okhttp : ()Ljavax/net/SocketFactory;
    //   176: putfield socketFactory : Ljavax/net/SocketFactory;
    //   179: aload_0
    //   180: aload_1
    //   181: invokevirtual getConnectionSpecs$okhttp : ()Ljava/util/List;
    //   184: putfield connectionSpecs : Ljava/util/List;
    //   187: aload_0
    //   188: aload_1
    //   189: invokevirtual getProtocols$okhttp : ()Ljava/util/List;
    //   192: putfield protocols : Ljava/util/List;
    //   195: aload_0
    //   196: aload_1
    //   197: invokevirtual getHostnameVerifier$okhttp : ()Ljavax/net/ssl/HostnameVerifier;
    //   200: putfield hostnameVerifier : Ljavax/net/ssl/HostnameVerifier;
    //   203: aload_0
    //   204: aload_1
    //   205: invokevirtual getCallTimeout$okhttp : ()I
    //   208: putfield callTimeoutMillis : I
    //   211: aload_0
    //   212: aload_1
    //   213: invokevirtual getConnectTimeout$okhttp : ()I
    //   216: putfield connectTimeoutMillis : I
    //   219: aload_0
    //   220: aload_1
    //   221: invokevirtual getReadTimeout$okhttp : ()I
    //   224: putfield readTimeoutMillis : I
    //   227: aload_0
    //   228: aload_1
    //   229: invokevirtual getWriteTimeout$okhttp : ()I
    //   232: putfield writeTimeoutMillis : I
    //   235: aload_0
    //   236: aload_1
    //   237: invokevirtual getPingInterval$okhttp : ()I
    //   240: putfield pingIntervalMillis : I
    //   243: aload_0
    //   244: aload_1
    //   245: invokevirtual getMinWebSocketMessageToCompress$okhttp : ()J
    //   248: putfield minWebSocketMessageToCompress : J
    //   251: aload_0
    //   252: aload_1
    //   253: invokevirtual getRouteDatabase$okhttp : ()Lokhttp3/internal/connection/RouteDatabase;
    //   256: dup
    //   257: ifnonnull -> 268
    //   260: pop
    //   261: new okhttp3/internal/connection/RouteDatabase
    //   264: dup
    //   265: invokespecial <init> : ()V
    //   268: putfield routeDatabase : Lokhttp3/internal/connection/RouteDatabase;
    //   271: nop
    //   272: aload_0
    //   273: getfield connectionSpecs : Ljava/util/List;
    //   276: checkcast java/lang/Iterable
    //   279: astore_2
    //   280: iconst_0
    //   281: istore_3
    //   282: aload_2
    //   283: instanceof java/util/Collection
    //   286: ifeq -> 305
    //   289: aload_2
    //   290: checkcast java/util/Collection
    //   293: invokeinterface isEmpty : ()Z
    //   298: ifeq -> 305
    //   301: iconst_1
    //   302: goto -> 355
    //   305: aload_2
    //   306: invokeinterface iterator : ()Ljava/util/Iterator;
    //   311: astore #4
    //   313: aload #4
    //   315: invokeinterface hasNext : ()Z
    //   320: ifeq -> 354
    //   323: aload #4
    //   325: invokeinterface next : ()Ljava/lang/Object;
    //   330: astore #5
    //   332: aload #5
    //   334: checkcast okhttp3/ConnectionSpec
    //   337: astore #6
    //   339: iconst_0
    //   340: istore #7
    //   342: aload #6
    //   344: invokevirtual isTls : ()Z
    //   347: ifeq -> 313
    //   350: iconst_0
    //   351: goto -> 355
    //   354: iconst_1
    //   355: ifeq -> 383
    //   358: aload_0
    //   359: aconst_null
    //   360: putfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
    //   363: aload_0
    //   364: aconst_null
    //   365: putfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   368: aload_0
    //   369: aconst_null
    //   370: putfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   373: aload_0
    //   374: getstatic okhttp3/CertificatePinner.DEFAULT : Lokhttp3/CertificatePinner;
    //   377: putfield certificatePinner : Lokhttp3/CertificatePinner;
    //   380: goto -> 515
    //   383: aload_1
    //   384: invokevirtual getSslSocketFactoryOrNull$okhttp : ()Ljavax/net/ssl/SSLSocketFactory;
    //   387: ifnull -> 444
    //   390: aload_0
    //   391: aload_1
    //   392: invokevirtual getSslSocketFactoryOrNull$okhttp : ()Ljavax/net/ssl/SSLSocketFactory;
    //   395: putfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
    //   398: aload_0
    //   399: aload_1
    //   400: invokevirtual getCertificateChainCleaner$okhttp : ()Lokhttp3/internal/tls/CertificateChainCleaner;
    //   403: dup
    //   404: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   407: putfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   410: aload_0
    //   411: aload_1
    //   412: invokevirtual getX509TrustManagerOrNull$okhttp : ()Ljavax/net/ssl/X509TrustManager;
    //   415: dup
    //   416: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   419: putfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   422: aload_0
    //   423: aload_1
    //   424: invokevirtual getCertificatePinner$okhttp : ()Lokhttp3/CertificatePinner;
    //   427: aload_0
    //   428: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   431: dup
    //   432: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   435: invokevirtual withCertificateChainCleaner$okhttp : (Lokhttp3/internal/tls/CertificateChainCleaner;)Lokhttp3/CertificatePinner;
    //   438: putfield certificatePinner : Lokhttp3/CertificatePinner;
    //   441: goto -> 515
    //   444: aload_0
    //   445: getstatic okhttp3/internal/platform/Platform.Companion : Lokhttp3/internal/platform/Platform$Companion;
    //   448: invokevirtual get : ()Lokhttp3/internal/platform/Platform;
    //   451: invokevirtual platformTrustManager : ()Ljavax/net/ssl/X509TrustManager;
    //   454: putfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   457: aload_0
    //   458: getstatic okhttp3/internal/platform/Platform.Companion : Lokhttp3/internal/platform/Platform$Companion;
    //   461: invokevirtual get : ()Lokhttp3/internal/platform/Platform;
    //   464: aload_0
    //   465: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   468: dup
    //   469: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   472: invokevirtual newSslSocketFactory : (Ljavax/net/ssl/X509TrustManager;)Ljavax/net/ssl/SSLSocketFactory;
    //   475: putfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
    //   478: aload_0
    //   479: getstatic okhttp3/internal/tls/CertificateChainCleaner.Companion : Lokhttp3/internal/tls/CertificateChainCleaner$Companion;
    //   482: aload_0
    //   483: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   486: dup
    //   487: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   490: invokevirtual get : (Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/CertificateChainCleaner;
    //   493: putfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   496: aload_0
    //   497: aload_1
    //   498: invokevirtual getCertificatePinner$okhttp : ()Lokhttp3/CertificatePinner;
    //   501: aload_0
    //   502: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   505: dup
    //   506: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   509: invokevirtual withCertificateChainCleaner$okhttp : (Lokhttp3/internal/tls/CertificateChainCleaner;)Lokhttp3/CertificatePinner;
    //   512: putfield certificatePinner : Lokhttp3/CertificatePinner;
    //   515: aload_0
    //   516: invokespecial verifyClientState : ()V
    //   519: nop
    //   520: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #121	-> 6
    //   #125	-> 10
    //   #127	-> 18
    //   #135	-> 26
    //   #143	-> 37
    //   #146	-> 48
    //   #149	-> 56
    //   #151	-> 64
    //   #153	-> 72
    //   #155	-> 80
    //   #157	-> 88
    //   #159	-> 96
    //   #161	-> 104
    //   #163	-> 112
    //   #166	-> 120
    //   #168	-> 121
    //   #169	-> 137
    //   #166	-> 160
    //   #173	-> 163
    //   #175	-> 171
    //   #185	-> 179
    //   #187	-> 187
    //   #189	-> 195
    //   #199	-> 203
    //   #202	-> 211
    //   #205	-> 219
    //   #208	-> 227
    //   #211	-> 235
    //   #218	-> 243
    //   #220	-> 251
    //   #224	-> 271
    //   #225	-> 272
    //   #1080	-> 282
    //   #1081	-> 305
    //   #225	-> 342
    //   #1081	-> 347
    //   #1082	-> 354
    //   #225	-> 355
    //   #226	-> 358
    //   #227	-> 363
    //   #228	-> 368
    //   #229	-> 373
    //   #230	-> 383
    //   #231	-> 390
    //   #232	-> 398
    //   #233	-> 410
    //   #234	-> 422
    //   #235	-> 427
    //   #234	-> 438
    //   #237	-> 444
    //   #238	-> 457
    //   #239	-> 478
    //   #240	-> 496
    //   #241	-> 501
    //   #240	-> 512
    //   #244	-> 515
    //   #245	-> 519
    //   #121	-> 520
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   342	5	7	$i$a$-none-OkHttpClient$1	I
    //   339	8	6	it	Lokhttp3/ConnectionSpec;
    //   332	22	5	element$iv	Ljava/lang/Object;
    //   282	73	3	$i$f$none	I
    //   280	75	2	$this$none$iv	Ljava/lang/Iterable;
    //   0	521	0	this	Lokhttp3/OkHttpClient;
    //   0	521	1	builder	Lokhttp3/OkHttpClient$Builder;
  }
  
  @NotNull
  public Object clone() {
    return super.clone();
  }
  
  @JvmName(name = "dispatcher")
  @NotNull
  public final Dispatcher dispatcher() {
    return this.dispatcher;
  }
  
  @JvmName(name = "connectionPool")
  @NotNull
  public final ConnectionPool connectionPool() {
    return this.connectionPool;
  }
  
  @JvmName(name = "interceptors")
  @NotNull
  public final List<Interceptor> interceptors() {
    return this.interceptors;
  }
  
  @JvmName(name = "networkInterceptors")
  @NotNull
  public final List<Interceptor> networkInterceptors() {
    return this.networkInterceptors;
  }
  
  @JvmName(name = "eventListenerFactory")
  @NotNull
  public final EventListener.Factory eventListenerFactory() {
    return this.eventListenerFactory;
  }
  
  @JvmName(name = "retryOnConnectionFailure")
  public final boolean retryOnConnectionFailure() {
    return this.retryOnConnectionFailure;
  }
  
  @JvmName(name = "authenticator")
  @NotNull
  public final Authenticator authenticator() {
    return this.authenticator;
  }
  
  @JvmName(name = "followRedirects")
  public final boolean followRedirects() {
    return this.followRedirects;
  }
  
  @JvmName(name = "followSslRedirects")
  public final boolean followSslRedirects() {
    return this.followSslRedirects;
  }
  
  @JvmName(name = "cookieJar")
  @NotNull
  public final CookieJar cookieJar() {
    return this.cookieJar;
  }
  
  @JvmName(name = "cache")
  @Nullable
  public final Cache cache() {
    return this.cache;
  }
  
  @JvmName(name = "dns")
  @NotNull
  public final Dns dns() {
    return this.dns;
  }
  
  @JvmName(name = "proxy")
  @Nullable
  public final Proxy proxy() {
    return this.proxy;
  }
  
  @JvmName(name = "proxySelector")
  @NotNull
  public final ProxySelector proxySelector() {
    return this.proxySelector;
  }
  
  @JvmName(name = "proxyAuthenticator")
  @NotNull
  public final Authenticator proxyAuthenticator() {
    return this.proxyAuthenticator;
  }
  
  @JvmName(name = "socketFactory")
  @NotNull
  public final SocketFactory socketFactory() {
    return this.socketFactory;
  }
  
  @JvmName(name = "sslSocketFactory")
  @NotNull
  public final SSLSocketFactory sslSocketFactory() {
    if (this.sslSocketFactoryOrNull == null)
      throw new IllegalStateException("CLEARTEXT-only client"); 
    return this.sslSocketFactoryOrNull;
  }
  
  @JvmName(name = "x509TrustManager")
  @Nullable
  public final X509TrustManager x509TrustManager() {
    return this.x509TrustManager;
  }
  
  @JvmName(name = "connectionSpecs")
  @NotNull
  public final List<ConnectionSpec> connectionSpecs() {
    return this.connectionSpecs;
  }
  
  @JvmName(name = "protocols")
  @NotNull
  public final List<Protocol> protocols() {
    return this.protocols;
  }
  
  @JvmName(name = "hostnameVerifier")
  @NotNull
  public final HostnameVerifier hostnameVerifier() {
    return this.hostnameVerifier;
  }
  
  @JvmName(name = "certificatePinner")
  @NotNull
  public final CertificatePinner certificatePinner() {
    return this.certificatePinner;
  }
  
  @JvmName(name = "certificateChainCleaner")
  @Nullable
  public final CertificateChainCleaner certificateChainCleaner() {
    return this.certificateChainCleaner;
  }
  
  @JvmName(name = "callTimeoutMillis")
  public final int callTimeoutMillis() {
    return this.callTimeoutMillis;
  }
  
  @JvmName(name = "connectTimeoutMillis")
  public final int connectTimeoutMillis() {
    return this.connectTimeoutMillis;
  }
  
  @JvmName(name = "readTimeoutMillis")
  public final int readTimeoutMillis() {
    return this.readTimeoutMillis;
  }
  
  @JvmName(name = "writeTimeoutMillis")
  public final int writeTimeoutMillis() {
    return this.writeTimeoutMillis;
  }
  
  @JvmName(name = "pingIntervalMillis")
  public final int pingIntervalMillis() {
    return this.pingIntervalMillis;
  }
  
  @JvmName(name = "minWebSocketMessageToCompress")
  public final long minWebSocketMessageToCompress() {
    return this.minWebSocketMessageToCompress;
  }
  
  @NotNull
  public final RouteDatabase getRouteDatabase() {
    return this.routeDatabase;
  }
  
  public OkHttpClient() {
    this(new Builder());
  }
  
  private final void verifyClientState() {
    // Byte code:
    //   0: aload_0
    //   1: getfield interceptors : Ljava/util/List;
    //   4: dup
    //   5: ldc_w 'null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>'
    //   8: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   11: aconst_null
    //   12: invokeinterface contains : (Ljava/lang/Object;)Z
    //   17: ifne -> 24
    //   20: iconst_1
    //   21: goto -> 25
    //   24: iconst_0
    //   25: ifne -> 66
    //   28: iconst_0
    //   29: istore_2
    //   30: new java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: ldc_w 'Null interceptor: '
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: aload_0
    //   44: getfield interceptors : Ljava/util/List;
    //   47: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   50: invokevirtual toString : ()Ljava/lang/String;
    //   53: astore_2
    //   54: new java/lang/IllegalStateException
    //   57: dup
    //   58: aload_2
    //   59: invokevirtual toString : ()Ljava/lang/String;
    //   62: invokespecial <init> : (Ljava/lang/String;)V
    //   65: athrow
    //   66: aload_0
    //   67: getfield networkInterceptors : Ljava/util/List;
    //   70: dup
    //   71: ldc_w 'null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>'
    //   74: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   77: aconst_null
    //   78: invokeinterface contains : (Ljava/lang/Object;)Z
    //   83: ifne -> 90
    //   86: iconst_1
    //   87: goto -> 91
    //   90: iconst_0
    //   91: ifne -> 132
    //   94: iconst_0
    //   95: istore_2
    //   96: new java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial <init> : ()V
    //   103: ldc_w 'Null network interceptor: '
    //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: getfield networkInterceptors : Ljava/util/List;
    //   113: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   116: invokevirtual toString : ()Ljava/lang/String;
    //   119: astore_2
    //   120: new java/lang/IllegalStateException
    //   123: dup
    //   124: aload_2
    //   125: invokevirtual toString : ()Ljava/lang/String;
    //   128: invokespecial <init> : (Ljava/lang/String;)V
    //   131: athrow
    //   132: aload_0
    //   133: getfield connectionSpecs : Ljava/util/List;
    //   136: checkcast java/lang/Iterable
    //   139: astore_1
    //   140: iconst_0
    //   141: istore_2
    //   142: aload_1
    //   143: instanceof java/util/Collection
    //   146: ifeq -> 165
    //   149: aload_1
    //   150: checkcast java/util/Collection
    //   153: invokeinterface isEmpty : ()Z
    //   158: ifeq -> 165
    //   161: iconst_1
    //   162: goto -> 212
    //   165: aload_1
    //   166: invokeinterface iterator : ()Ljava/util/Iterator;
    //   171: astore_3
    //   172: aload_3
    //   173: invokeinterface hasNext : ()Z
    //   178: ifeq -> 211
    //   181: aload_3
    //   182: invokeinterface next : ()Ljava/lang/Object;
    //   187: astore #4
    //   189: aload #4
    //   191: checkcast okhttp3/ConnectionSpec
    //   194: astore #5
    //   196: iconst_0
    //   197: istore #6
    //   199: aload #5
    //   201: invokevirtual isTls : ()Z
    //   204: ifeq -> 172
    //   207: iconst_0
    //   208: goto -> 212
    //   211: iconst_1
    //   212: ifeq -> 337
    //   215: aload_0
    //   216: getfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
    //   219: ifnonnull -> 226
    //   222: iconst_1
    //   223: goto -> 227
    //   226: iconst_0
    //   227: ifne -> 246
    //   230: ldc_w 'Check failed.'
    //   233: astore_2
    //   234: new java/lang/IllegalStateException
    //   237: dup
    //   238: aload_2
    //   239: invokevirtual toString : ()Ljava/lang/String;
    //   242: invokespecial <init> : (Ljava/lang/String;)V
    //   245: athrow
    //   246: aload_0
    //   247: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   250: ifnonnull -> 257
    //   253: iconst_1
    //   254: goto -> 258
    //   257: iconst_0
    //   258: ifne -> 277
    //   261: ldc_w 'Check failed.'
    //   264: astore_2
    //   265: new java/lang/IllegalStateException
    //   268: dup
    //   269: aload_2
    //   270: invokevirtual toString : ()Ljava/lang/String;
    //   273: invokespecial <init> : (Ljava/lang/String;)V
    //   276: athrow
    //   277: aload_0
    //   278: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   281: ifnonnull -> 288
    //   284: iconst_1
    //   285: goto -> 289
    //   288: iconst_0
    //   289: ifne -> 308
    //   292: ldc_w 'Check failed.'
    //   295: astore_2
    //   296: new java/lang/IllegalStateException
    //   299: dup
    //   300: aload_2
    //   301: invokevirtual toString : ()Ljava/lang/String;
    //   304: invokespecial <init> : (Ljava/lang/String;)V
    //   307: athrow
    //   308: aload_0
    //   309: getfield certificatePinner : Lokhttp3/CertificatePinner;
    //   312: getstatic okhttp3/CertificatePinner.DEFAULT : Lokhttp3/CertificatePinner;
    //   315: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   318: ifne -> 421
    //   321: ldc_w 'Check failed.'
    //   324: astore_2
    //   325: new java/lang/IllegalStateException
    //   328: dup
    //   329: aload_2
    //   330: invokevirtual toString : ()Ljava/lang/String;
    //   333: invokespecial <init> : (Ljava/lang/String;)V
    //   336: athrow
    //   337: aload_0
    //   338: getfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
    //   341: dup
    //   342: ifnonnull -> 364
    //   345: pop
    //   346: iconst_0
    //   347: istore_2
    //   348: ldc_w 'sslSocketFactory == null'
    //   351: astore_2
    //   352: new java/lang/IllegalStateException
    //   355: dup
    //   356: aload_2
    //   357: invokevirtual toString : ()Ljava/lang/String;
    //   360: invokespecial <init> : (Ljava/lang/String;)V
    //   363: athrow
    //   364: pop
    //   365: aload_0
    //   366: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
    //   369: dup
    //   370: ifnonnull -> 392
    //   373: pop
    //   374: iconst_0
    //   375: istore_2
    //   376: ldc_w 'certificateChainCleaner == null'
    //   379: astore_2
    //   380: new java/lang/IllegalStateException
    //   383: dup
    //   384: aload_2
    //   385: invokevirtual toString : ()Ljava/lang/String;
    //   388: invokespecial <init> : (Ljava/lang/String;)V
    //   391: athrow
    //   392: pop
    //   393: aload_0
    //   394: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
    //   397: dup
    //   398: ifnonnull -> 420
    //   401: pop
    //   402: iconst_0
    //   403: istore_2
    //   404: ldc_w 'x509TrustManager == null'
    //   407: astore_2
    //   408: new java/lang/IllegalStateException
    //   411: dup
    //   412: aload_2
    //   413: invokevirtual toString : ()Ljava/lang/String;
    //   416: invokespecial <init> : (Ljava/lang/String;)V
    //   419: athrow
    //   420: pop
    //   421: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #248	-> 0
    //   #249	-> 30
    //   #248	-> 53
    //   #251	-> 66
    //   #252	-> 96
    //   #251	-> 119
    //   #255	-> 132
    //   #1083	-> 142
    //   #1084	-> 165
    //   #255	-> 199
    //   #1084	-> 204
    //   #1085	-> 211
    //   #255	-> 212
    //   #256	-> 215
    //   #257	-> 246
    //   #258	-> 277
    //   #259	-> 308
    //   #261	-> 337
    //   #1086	-> 346
    //   #261	-> 348
    //   #261	-> 351
    //   #262	-> 365
    //   #1086	-> 374
    //   #262	-> 376
    //   #262	-> 379
    //   #263	-> 393
    //   #1086	-> 402
    //   #263	-> 404
    //   #263	-> 407
    //   #265	-> 421
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   30	23	2	$i$a$-check-OkHttpClient$verifyClientState$1	I
    //   96	23	2	$i$a$-check-OkHttpClient$verifyClientState$2	I
    //   199	5	6	$i$a$-none-OkHttpClient$verifyClientState$3	I
    //   196	8	5	it	Lokhttp3/ConnectionSpec;
    //   189	22	4	element$iv	Ljava/lang/Object;
    //   142	70	2	$i$f$none	I
    //   140	72	1	$this$none$iv	Ljava/lang/Iterable;
    //   348	3	2	$i$a$-checkNotNull-OkHttpClient$verifyClientState$4	I
    //   376	3	2	$i$a$-checkNotNull-OkHttpClient$verifyClientState$5	I
    //   404	3	2	$i$a$-checkNotNull-OkHttpClient$verifyClientState$6	I
    //   0	422	0	this	Lokhttp3/OkHttpClient;
  }
  
  @NotNull
  public Call newCall(@NotNull Request request) {
    Intrinsics.checkNotNullParameter(request, "request");
    return (Call)new RealCall(this, request, false);
  }
  
  @NotNull
  public WebSocket newWebSocket(@NotNull Request request, @NotNull WebSocketListener listener) {
    Intrinsics.checkNotNullParameter(request, "request");
    Intrinsics.checkNotNullParameter(listener, "listener");
    RealWebSocket webSocket = new RealWebSocket(
        TaskRunner.INSTANCE, 
        request, 
        listener, 
        new Random(), 
        this.pingIntervalMillis, 
        null, 
        this.minWebSocketMessageToCompress);
    webSocket.connect(this);
    return (WebSocket)webSocket;
  }
  
  @NotNull
  public Builder newBuilder() {
    return new Builder(this);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "dispatcher", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_dispatcher")
  @NotNull
  public final Dispatcher -deprecated_dispatcher() {
    return this.dispatcher;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionPool", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_connectionPool")
  @NotNull
  public final ConnectionPool -deprecated_connectionPool() {
    return this.connectionPool;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "interceptors", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_interceptors")
  @NotNull
  public final List<Interceptor> -deprecated_interceptors() {
    return this.interceptors;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "networkInterceptors", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_networkInterceptors")
  @NotNull
  public final List<Interceptor> -deprecated_networkInterceptors() {
    return this.networkInterceptors;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "eventListenerFactory", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_eventListenerFactory")
  @NotNull
  public final EventListener.Factory -deprecated_eventListenerFactory() {
    return this.eventListenerFactory;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "retryOnConnectionFailure", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_retryOnConnectionFailure")
  public final boolean -deprecated_retryOnConnectionFailure() {
    return this.retryOnConnectionFailure;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "authenticator", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_authenticator")
  @NotNull
  public final Authenticator -deprecated_authenticator() {
    return this.authenticator;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "followRedirects", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_followRedirects")
  public final boolean -deprecated_followRedirects() {
    return this.followRedirects;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "followSslRedirects", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_followSslRedirects")
  public final boolean -deprecated_followSslRedirects() {
    return this.followSslRedirects;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cookieJar", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cookieJar")
  @NotNull
  public final CookieJar -deprecated_cookieJar() {
    return this.cookieJar;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "cache", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_cache")
  @Nullable
  public final Cache -deprecated_cache() {
    return this.cache;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "dns", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_dns")
  @NotNull
  public final Dns -deprecated_dns() {
    return this.dns;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxy")
  @Nullable
  public final Proxy -deprecated_proxy() {
    return this.proxy;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxySelector", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxySelector")
  @NotNull
  public final ProxySelector -deprecated_proxySelector() {
    return this.proxySelector;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxyAuthenticator", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_proxyAuthenticator")
  @NotNull
  public final Authenticator -deprecated_proxyAuthenticator() {
    return this.proxyAuthenticator;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "socketFactory", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_socketFactory")
  @NotNull
  public final SocketFactory -deprecated_socketFactory() {
    return this.socketFactory;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "sslSocketFactory", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_sslSocketFactory")
  @NotNull
  public final SSLSocketFactory -deprecated_sslSocketFactory() {
    return sslSocketFactory();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionSpecs", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_connectionSpecs")
  @NotNull
  public final List<ConnectionSpec> -deprecated_connectionSpecs() {
    return this.connectionSpecs;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "protocols", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_protocols")
  @NotNull
  public final List<Protocol> -deprecated_protocols() {
    return this.protocols;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "hostnameVerifier", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_hostnameVerifier")
  @NotNull
  public final HostnameVerifier -deprecated_hostnameVerifier() {
    return this.hostnameVerifier;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "certificatePinner", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_certificatePinner")
  @NotNull
  public final CertificatePinner -deprecated_certificatePinner() {
    return this.certificatePinner;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "callTimeoutMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_callTimeoutMillis")
  public final int -deprecated_callTimeoutMillis() {
    return this.callTimeoutMillis;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "connectTimeoutMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_connectTimeoutMillis")
  public final int -deprecated_connectTimeoutMillis() {
    return this.connectTimeoutMillis;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "readTimeoutMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_readTimeoutMillis")
  public final int -deprecated_readTimeoutMillis() {
    return this.readTimeoutMillis;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "writeTimeoutMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_writeTimeoutMillis")
  public final int -deprecated_writeTimeoutMillis() {
    return this.writeTimeoutMillis;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "pingIntervalMillis", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_pingIntervalMillis")
  public final int -deprecated_pingIntervalMillis() {
    return this.pingIntervalMillis;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000ø\001\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020!\n\002\b\b\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\r\n\002\020\b\n\002\b\005\n\002\030\002\n\002\bQ\n\002\030\002\n\002\b\032\030\0002\0020\001B\021\b\020\022\006\020\003\032\0020\002¢\006\004\b\004\020\005B\007¢\006\004\b\004\020\006J8\020\020\032\0020\0002#\b\004\020\r\032\035\022\023\022\0210\b¢\006\f\b\t\022\b\b\n\022\004\b\b(\013\022\004\022\0020\f0\007H\bø\001\000¢\006\004\b\016\020\017J\025\020\020\032\0020\0002\006\020\022\032\0020\021¢\006\004\b\020\020\023J8\020\025\032\0020\0002#\b\004\020\r\032\035\022\023\022\0210\b¢\006\f\b\t\022\b\b\n\022\004\b\b(\013\022\004\022\0020\f0\007H\bø\001\000¢\006\004\b\024\020\017J\025\020\025\032\0020\0002\006\020\022\032\0020\021¢\006\004\b\025\020\023J\025\020\027\032\0020\0002\006\020\027\032\0020\026¢\006\004\b\027\020\030J\r\020\031\032\0020\002¢\006\004\b\031\020\032J\027\020\034\032\0020\0002\b\020\034\032\004\030\0010\033¢\006\004\b\034\020\035J\027\020 \032\0020\0002\006\020\037\032\0020\036H\007¢\006\004\b \020!J\035\020 \032\0020\0002\006\020#\032\0020\"2\006\020%\032\0020$¢\006\004\b \020&J\025\020(\032\0020\0002\006\020(\032\0020'¢\006\004\b(\020)J\027\020*\032\0020\0002\006\020\037\032\0020\036H\007¢\006\004\b*\020!J\035\020*\032\0020\0002\006\020#\032\0020\"2\006\020%\032\0020$¢\006\004\b*\020&J\025\020,\032\0020\0002\006\020,\032\0020+¢\006\004\b,\020-J\033\0200\032\0020\0002\f\0200\032\b\022\004\022\0020/0.¢\006\004\b0\0201J\025\0203\032\0020\0002\006\0203\032\00202¢\006\004\b3\0204J\025\0206\032\0020\0002\006\0206\032\00205¢\006\004\b6\0207J\025\0209\032\0020\0002\006\0209\032\00208¢\006\004\b9\020:J\025\020<\032\0020\0002\006\020<\032\0020;¢\006\004\b<\020=J\025\020?\032\0020\0002\006\020?\032\0020>¢\006\004\b?\020@J\025\020B\032\0020\0002\006\020B\032\0020A¢\006\004\bB\020CJ\025\020E\032\0020\0002\006\020D\032\0020A¢\006\004\bE\020CJ\025\020G\032\0020\0002\006\020G\032\0020F¢\006\004\bG\020HJ\023\020J\032\b\022\004\022\0020\0210I¢\006\004\bJ\020KJ\025\020M\032\0020\0002\006\020L\032\0020\"¢\006\004\bM\020NJ\023\020O\032\b\022\004\022\0020\0210I¢\006\004\bO\020KJ\027\020P\032\0020\0002\006\020\037\032\0020\036H\007¢\006\004\bP\020!J\035\020P\032\0020\0002\006\020Q\032\0020\"2\006\020%\032\0020$¢\006\004\bP\020&J\033\020S\032\0020\0002\f\020S\032\b\022\004\022\0020R0.¢\006\004\bS\0201J\027\020U\032\0020\0002\b\020U\032\004\030\0010T¢\006\004\bU\020VJ\025\020W\032\0020\0002\006\020W\032\0020\026¢\006\004\bW\020\030J\025\020Y\032\0020\0002\006\020Y\032\0020X¢\006\004\bY\020ZJ\027\020[\032\0020\0002\006\020\037\032\0020\036H\007¢\006\004\b[\020!J\035\020[\032\0020\0002\006\020#\032\0020\"2\006\020%\032\0020$¢\006\004\b[\020&J\025\020\\\032\0020\0002\006\020\\\032\0020A¢\006\004\b\\\020CJ\025\020^\032\0020\0002\006\020^\032\0020]¢\006\004\b^\020_J\027\020a\032\0020\0002\006\020a\032\0020`H\007¢\006\004\ba\020bJ\035\020a\032\0020\0002\006\020a\032\0020`2\006\020d\032\0020c¢\006\004\ba\020eJ\027\020f\032\0020\0002\006\020\037\032\0020\036H\007¢\006\004\bf\020!J\035\020f\032\0020\0002\006\020#\032\0020\"2\006\020%\032\0020$¢\006\004\bf\020&R\"\020\027\032\0020\0268\000@\000X\016¢\006\022\n\004\b\027\020g\032\004\bh\020i\"\004\bj\020kR$\020\034\032\004\030\0010\0338\000@\000X\016¢\006\022\n\004\b\034\020l\032\004\bm\020n\"\004\bo\020pR\"\020 \032\0020q8\000@\000X\016¢\006\022\n\004\b \020r\032\004\bs\020t\"\004\bu\020vR$\020x\032\004\030\0010w8\000@\000X\016¢\006\022\n\004\bx\020y\032\004\bz\020{\"\004\b|\020}R%\020(\032\0020'8\000@\000X\016¢\006\025\n\004\b(\020~\032\005\b\020\001\"\006\b\001\020\001R$\020*\032\0020q8\000@\000X\016¢\006\024\n\004\b*\020r\032\005\b\001\020t\"\005\b\001\020vR'\020,\032\0020+8\000@\000X\016¢\006\027\n\005\b,\020\001\032\006\b\001\020\001\"\006\b\001\020\001R,\0200\032\b\022\004\022\0020/0.8\000@\000X\016¢\006\026\n\005\b0\020\001\032\005\b\001\020K\"\006\b\001\020\001R'\0203\032\002028\000@\000X\016¢\006\027\n\005\b3\020\001\032\006\b\001\020\001\"\006\b\001\020\001R'\0206\032\002058\000@\000X\016¢\006\027\n\005\b6\020\001\032\006\b\001\020\001\"\006\b\001\020\001R'\0209\032\002088\000@\000X\016¢\006\027\n\005\b9\020\001\032\006\b\001\020\001\"\006\b\001\020\001R'\020?\032\0020>8\000@\000X\016¢\006\027\n\005\b?\020\001\032\006\b\001\020\001\"\006\b \001\020¡\001R'\020B\032\0020A8\000@\000X\016¢\006\027\n\005\bB\020¢\001\032\006\b£\001\020¤\001\"\006\b¥\001\020¦\001R'\020E\032\0020A8\000@\000X\016¢\006\027\n\005\bE\020¢\001\032\006\b§\001\020¤\001\"\006\b¨\001\020¦\001R'\020G\032\0020F8\000@\000X\016¢\006\027\n\005\bG\020©\001\032\006\bª\001\020«\001\"\006\b¬\001\020­\001R\"\020J\032\b\022\004\022\0020\0210I8\000X\004¢\006\016\n\005\bJ\020\001\032\005\b®\001\020KR'\020M\032\0020\"8\000@\000X\016¢\006\027\n\005\bM\020¯\001\032\006\b°\001\020±\001\"\006\b²\001\020³\001R\"\020O\032\b\022\004\022\0020\0210I8\000X\004¢\006\016\n\005\bO\020\001\032\005\b´\001\020KR$\020P\032\0020q8\000@\000X\016¢\006\024\n\004\bP\020r\032\005\bµ\001\020t\"\005\b¶\001\020vR,\020S\032\b\022\004\022\0020R0.8\000@\000X\016¢\006\026\n\005\bS\020\001\032\005\b·\001\020K\"\006\b¸\001\020\001R)\020U\032\004\030\0010T8\000@\000X\016¢\006\027\n\005\bU\020¹\001\032\006\bº\001\020»\001\"\006\b¼\001\020½\001R$\020W\032\0020\0268\000@\000X\016¢\006\024\n\004\bW\020g\032\005\b¾\001\020i\"\005\b¿\001\020kR)\020Y\032\004\030\0010X8\000@\000X\016¢\006\027\n\005\bY\020À\001\032\006\bÁ\001\020Â\001\"\006\bÃ\001\020Ä\001R$\020[\032\0020q8\000@\000X\016¢\006\024\n\004\b[\020r\032\005\bÅ\001\020t\"\005\bÆ\001\020vR'\020\\\032\0020A8\000@\000X\016¢\006\027\n\005\b\\\020¢\001\032\006\bÇ\001\020¤\001\"\006\bÈ\001\020¦\001R,\020Ê\001\032\005\030\0010É\0018\000@\000X\016¢\006\030\n\006\bÊ\001\020Ë\001\032\006\bÌ\001\020Í\001\"\006\bÎ\001\020Ï\001R'\020^\032\0020]8\000@\000X\016¢\006\027\n\005\b^\020Ð\001\032\006\bÑ\001\020Ò\001\"\006\bÓ\001\020Ô\001R+\020Õ\001\032\004\030\0010`8\000@\000X\016¢\006\030\n\006\bÕ\001\020Ö\001\032\006\b×\001\020Ø\001\"\006\bÙ\001\020Ú\001R$\020f\032\0020q8\000@\000X\016¢\006\024\n\004\bf\020r\032\005\bÛ\001\020t\"\005\bÜ\001\020vR+\020Ý\001\032\004\030\0010c8\000@\000X\016¢\006\030\n\006\bÝ\001\020Þ\001\032\006\bß\001\020à\001\"\006\bá\001\020â\001\002\007\n\005\b20\001¨\006ã\001"}, d2 = {"Lokhttp3/OkHttpClient$Builder;", "", "Lokhttp3/OkHttpClient;", "okHttpClient", "<init>", "(Lokhttp3/OkHttpClient;)V", "()V", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "block", "-addInterceptor", "(Lkotlin/jvm/functions/Function1;)Lokhttp3/OkHttpClient$Builder;", "addInterceptor", "Lokhttp3/Interceptor;", "interceptor", "(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder;", "-addNetworkInterceptor", "addNetworkInterceptor", "Lokhttp3/Authenticator;", "authenticator", "(Lokhttp3/Authenticator;)Lokhttp3/OkHttpClient$Builder;", "build", "()Lokhttp3/OkHttpClient;", "Lokhttp3/Cache;", "cache", "(Lokhttp3/Cache;)Lokhttp3/OkHttpClient$Builder;", "Ljava/time/Duration;", "duration", "callTimeout", "(Ljava/time/Duration;)Lokhttp3/OkHttpClient$Builder;", "", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/CertificatePinner;", "certificatePinner", "(Lokhttp3/CertificatePinner;)Lokhttp3/OkHttpClient$Builder;", "connectTimeout", "Lokhttp3/ConnectionPool;", "connectionPool", "(Lokhttp3/ConnectionPool;)Lokhttp3/OkHttpClient$Builder;", "", "Lokhttp3/ConnectionSpec;", "connectionSpecs", "(Ljava/util/List;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/CookieJar;", "cookieJar", "(Lokhttp3/CookieJar;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/Dispatcher;", "dispatcher", "(Lokhttp3/Dispatcher;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/Dns;", "dns", "(Lokhttp3/Dns;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/EventListener;", "eventListener", "(Lokhttp3/EventListener;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/EventListener$Factory;", "eventListenerFactory", "(Lokhttp3/EventListener$Factory;)Lokhttp3/OkHttpClient$Builder;", "", "followRedirects", "(Z)Lokhttp3/OkHttpClient$Builder;", "followProtocolRedirects", "followSslRedirects", "Ljavax/net/ssl/HostnameVerifier;", "hostnameVerifier", "(Ljavax/net/ssl/HostnameVerifier;)Lokhttp3/OkHttpClient$Builder;", "", "interceptors", "()Ljava/util/List;", "bytes", "minWebSocketMessageToCompress", "(J)Lokhttp3/OkHttpClient$Builder;", "networkInterceptors", "pingInterval", "interval", "Lokhttp3/Protocol;", "protocols", "Ljava/net/Proxy;", "proxy", "(Ljava/net/Proxy;)Lokhttp3/OkHttpClient$Builder;", "proxyAuthenticator", "Ljava/net/ProxySelector;", "proxySelector", "(Ljava/net/ProxySelector;)Lokhttp3/OkHttpClient$Builder;", "readTimeout", "retryOnConnectionFailure", "Ljavax/net/SocketFactory;", "socketFactory", "(Ljavax/net/SocketFactory;)Lokhttp3/OkHttpClient$Builder;", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Lokhttp3/OkHttpClient$Builder;", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "(Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/X509TrustManager;)Lokhttp3/OkHttpClient$Builder;", "writeTimeout", "Lokhttp3/Authenticator;", "getAuthenticator$okhttp", "()Lokhttp3/Authenticator;", "setAuthenticator$okhttp", "(Lokhttp3/Authenticator;)V", "Lokhttp3/Cache;", "getCache$okhttp", "()Lokhttp3/Cache;", "setCache$okhttp", "(Lokhttp3/Cache;)V", "", "I", "getCallTimeout$okhttp", "()I", "setCallTimeout$okhttp", "(I)V", "Lokhttp3/internal/tls/CertificateChainCleaner;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "setCertificateChainCleaner$okhttp", "(Lokhttp3/internal/tls/CertificateChainCleaner;)V", "Lokhttp3/CertificatePinner;", "getCertificatePinner$okhttp", "()Lokhttp3/CertificatePinner;", "setCertificatePinner$okhttp", "(Lokhttp3/CertificatePinner;)V", "getConnectTimeout$okhttp", "setConnectTimeout$okhttp", "Lokhttp3/ConnectionPool;", "getConnectionPool$okhttp", "()Lokhttp3/ConnectionPool;", "setConnectionPool$okhttp", "(Lokhttp3/ConnectionPool;)V", "Ljava/util/List;", "getConnectionSpecs$okhttp", "setConnectionSpecs$okhttp", "(Ljava/util/List;)V", "Lokhttp3/CookieJar;", "getCookieJar$okhttp", "()Lokhttp3/CookieJar;", "setCookieJar$okhttp", "(Lokhttp3/CookieJar;)V", "Lokhttp3/Dispatcher;", "getDispatcher$okhttp", "()Lokhttp3/Dispatcher;", "setDispatcher$okhttp", "(Lokhttp3/Dispatcher;)V", "Lokhttp3/Dns;", "getDns$okhttp", "()Lokhttp3/Dns;", "setDns$okhttp", "(Lokhttp3/Dns;)V", "Lokhttp3/EventListener$Factory;", "getEventListenerFactory$okhttp", "()Lokhttp3/EventListener$Factory;", "setEventListenerFactory$okhttp", "(Lokhttp3/EventListener$Factory;)V", "Z", "getFollowRedirects$okhttp", "()Z", "setFollowRedirects$okhttp", "(Z)V", "getFollowSslRedirects$okhttp", "setFollowSslRedirects$okhttp", "Ljavax/net/ssl/HostnameVerifier;", "getHostnameVerifier$okhttp", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier$okhttp", "(Ljavax/net/ssl/HostnameVerifier;)V", "getInterceptors$okhttp", "J", "getMinWebSocketMessageToCompress$okhttp", "()J", "setMinWebSocketMessageToCompress$okhttp", "(J)V", "getNetworkInterceptors$okhttp", "getPingInterval$okhttp", "setPingInterval$okhttp", "getProtocols$okhttp", "setProtocols$okhttp", "Ljava/net/Proxy;", "getProxy$okhttp", "()Ljava/net/Proxy;", "setProxy$okhttp", "(Ljava/net/Proxy;)V", "getProxyAuthenticator$okhttp", "setProxyAuthenticator$okhttp", "Ljava/net/ProxySelector;", "getProxySelector$okhttp", "()Ljava/net/ProxySelector;", "setProxySelector$okhttp", "(Ljava/net/ProxySelector;)V", "getReadTimeout$okhttp", "setReadTimeout$okhttp", "getRetryOnConnectionFailure$okhttp", "setRetryOnConnectionFailure$okhttp", "Lokhttp3/internal/connection/RouteDatabase;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "setRouteDatabase$okhttp", "(Lokhttp3/internal/connection/RouteDatabase;)V", "Ljavax/net/SocketFactory;", "getSocketFactory$okhttp", "()Ljavax/net/SocketFactory;", "setSocketFactory$okhttp", "(Ljavax/net/SocketFactory;)V", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "getSslSocketFactoryOrNull$okhttp", "()Ljavax/net/ssl/SSLSocketFactory;", "setSslSocketFactoryOrNull$okhttp", "(Ljavax/net/ssl/SSLSocketFactory;)V", "getWriteTimeout$okhttp", "setWriteTimeout$okhttp", "x509TrustManagerOrNull", "Ljavax/net/ssl/X509TrustManager;", "getX509TrustManagerOrNull$okhttp", "()Ljavax/net/ssl/X509TrustManager;", "setX509TrustManagerOrNull$okhttp", "(Ljavax/net/ssl/X509TrustManager;)V", "okhttp"})
  @SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1079:1\n1#2:1080\n*E\n"})
  public static final class Builder {
    @NotNull
    private Dispatcher dispatcher;
    
    @NotNull
    private ConnectionPool connectionPool;
    
    @NotNull
    private final List<Interceptor> interceptors;
    
    @NotNull
    private final List<Interceptor> networkInterceptors;
    
    @NotNull
    private EventListener.Factory eventListenerFactory;
    
    private boolean retryOnConnectionFailure;
    
    @NotNull
    private Authenticator authenticator;
    
    private boolean followRedirects;
    
    private boolean followSslRedirects;
    
    @NotNull
    private CookieJar cookieJar;
    
    @Nullable
    private Cache cache;
    
    @NotNull
    private Dns dns;
    
    @Nullable
    private Proxy proxy;
    
    @Nullable
    private ProxySelector proxySelector;
    
    @NotNull
    private Authenticator proxyAuthenticator;
    
    @NotNull
    private SocketFactory socketFactory;
    
    @Nullable
    private SSLSocketFactory sslSocketFactoryOrNull;
    
    @Nullable
    private X509TrustManager x509TrustManagerOrNull;
    
    @NotNull
    private List<ConnectionSpec> connectionSpecs;
    
    @NotNull
    private List<? extends Protocol> protocols;
    
    @NotNull
    private HostnameVerifier hostnameVerifier;
    
    @NotNull
    private CertificatePinner certificatePinner;
    
    @Nullable
    private CertificateChainCleaner certificateChainCleaner;
    
    private int callTimeout;
    
    private int connectTimeout;
    
    private int readTimeout;
    
    private int writeTimeout;
    
    private int pingInterval;
    
    private long minWebSocketMessageToCompress;
    
    @Nullable
    private RouteDatabase routeDatabase;
    
    public Builder() {
      this.dispatcher = new Dispatcher();
      this.connectionPool = new ConnectionPool();
      this.interceptors = new ArrayList<>();
      this.networkInterceptors = new ArrayList<>();
      this.eventListenerFactory = Util.asFactory(EventListener.NONE);
      this.retryOnConnectionFailure = true;
      this.authenticator = Authenticator.NONE;
      this.followRedirects = true;
      this.followSslRedirects = true;
      this.cookieJar = CookieJar.NO_COOKIES;
      this.dns = Dns.SYSTEM;
      this.proxyAuthenticator = Authenticator.NONE;
      Intrinsics.checkNotNullExpressionValue(SocketFactory.getDefault(), "getDefault()");
      this.socketFactory = SocketFactory.getDefault();
      this.connectionSpecs = OkHttpClient.Companion.getDEFAULT_CONNECTION_SPECS$okhttp();
      this.protocols = OkHttpClient.Companion.getDEFAULT_PROTOCOLS$okhttp();
      this.hostnameVerifier = (HostnameVerifier)OkHostnameVerifier.INSTANCE;
      this.certificatePinner = CertificatePinner.DEFAULT;
      this.connectTimeout = 10000;
      this.readTimeout = 10000;
      this.writeTimeout = 10000;
      this.minWebSocketMessageToCompress = 1024L;
    }
    
    @NotNull
    public final Dispatcher getDispatcher$okhttp() {
      return this.dispatcher;
    }
    
    public final void setDispatcher$okhttp(@NotNull Dispatcher <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.dispatcher = <set-?>;
    }
    
    @NotNull
    public final ConnectionPool getConnectionPool$okhttp() {
      return this.connectionPool;
    }
    
    public final void setConnectionPool$okhttp(@NotNull ConnectionPool <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.connectionPool = <set-?>;
    }
    
    @NotNull
    public final List<Interceptor> getInterceptors$okhttp() {
      return this.interceptors;
    }
    
    @NotNull
    public final List<Interceptor> getNetworkInterceptors$okhttp() {
      return this.networkInterceptors;
    }
    
    @NotNull
    public final EventListener.Factory getEventListenerFactory$okhttp() {
      return this.eventListenerFactory;
    }
    
    public final void setEventListenerFactory$okhttp(@NotNull EventListener.Factory <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.eventListenerFactory = <set-?>;
    }
    
    public final boolean getRetryOnConnectionFailure$okhttp() {
      return this.retryOnConnectionFailure;
    }
    
    public final void setRetryOnConnectionFailure$okhttp(boolean <set-?>) {
      this.retryOnConnectionFailure = <set-?>;
    }
    
    @NotNull
    public final Authenticator getAuthenticator$okhttp() {
      return this.authenticator;
    }
    
    public final void setAuthenticator$okhttp(@NotNull Authenticator <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.authenticator = <set-?>;
    }
    
    public final boolean getFollowRedirects$okhttp() {
      return this.followRedirects;
    }
    
    public final void setFollowRedirects$okhttp(boolean <set-?>) {
      this.followRedirects = <set-?>;
    }
    
    public final boolean getFollowSslRedirects$okhttp() {
      return this.followSslRedirects;
    }
    
    public final void setFollowSslRedirects$okhttp(boolean <set-?>) {
      this.followSslRedirects = <set-?>;
    }
    
    @NotNull
    public final CookieJar getCookieJar$okhttp() {
      return this.cookieJar;
    }
    
    public final void setCookieJar$okhttp(@NotNull CookieJar <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.cookieJar = <set-?>;
    }
    
    @Nullable
    public final Cache getCache$okhttp() {
      return this.cache;
    }
    
    public final void setCache$okhttp(@Nullable Cache <set-?>) {
      this.cache = <set-?>;
    }
    
    @NotNull
    public final Dns getDns$okhttp() {
      return this.dns;
    }
    
    public final void setDns$okhttp(@NotNull Dns <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.dns = <set-?>;
    }
    
    @Nullable
    public final Proxy getProxy$okhttp() {
      return this.proxy;
    }
    
    public final void setProxy$okhttp(@Nullable Proxy <set-?>) {
      this.proxy = <set-?>;
    }
    
    @Nullable
    public final ProxySelector getProxySelector$okhttp() {
      return this.proxySelector;
    }
    
    public final void setProxySelector$okhttp(@Nullable ProxySelector <set-?>) {
      this.proxySelector = <set-?>;
    }
    
    @NotNull
    public final Authenticator getProxyAuthenticator$okhttp() {
      return this.proxyAuthenticator;
    }
    
    public final void setProxyAuthenticator$okhttp(@NotNull Authenticator <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.proxyAuthenticator = <set-?>;
    }
    
    @NotNull
    public final SocketFactory getSocketFactory$okhttp() {
      return this.socketFactory;
    }
    
    public final void setSocketFactory$okhttp(@NotNull SocketFactory <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.socketFactory = <set-?>;
    }
    
    @Nullable
    public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() {
      return this.sslSocketFactoryOrNull;
    }
    
    public final void setSslSocketFactoryOrNull$okhttp(@Nullable SSLSocketFactory <set-?>) {
      this.sslSocketFactoryOrNull = <set-?>;
    }
    
    @Nullable
    public final X509TrustManager getX509TrustManagerOrNull$okhttp() {
      return this.x509TrustManagerOrNull;
    }
    
    public final void setX509TrustManagerOrNull$okhttp(@Nullable X509TrustManager <set-?>) {
      this.x509TrustManagerOrNull = <set-?>;
    }
    
    @NotNull
    public final List<ConnectionSpec> getConnectionSpecs$okhttp() {
      return this.connectionSpecs;
    }
    
    public final void setConnectionSpecs$okhttp(@NotNull List<ConnectionSpec> <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.connectionSpecs = <set-?>;
    }
    
    @NotNull
    public final List<Protocol> getProtocols$okhttp() {
      return (List)this.protocols;
    }
    
    public final void setProtocols$okhttp(@NotNull List<? extends Protocol> <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.protocols = <set-?>;
    }
    
    @NotNull
    public final HostnameVerifier getHostnameVerifier$okhttp() {
      return this.hostnameVerifier;
    }
    
    public final void setHostnameVerifier$okhttp(@NotNull HostnameVerifier <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.hostnameVerifier = <set-?>;
    }
    
    @NotNull
    public final CertificatePinner getCertificatePinner$okhttp() {
      return this.certificatePinner;
    }
    
    public final void setCertificatePinner$okhttp(@NotNull CertificatePinner <set-?>) {
      Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
      this.certificatePinner = <set-?>;
    }
    
    @Nullable
    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
      return this.certificateChainCleaner;
    }
    
    public final void setCertificateChainCleaner$okhttp(@Nullable CertificateChainCleaner <set-?>) {
      this.certificateChainCleaner = <set-?>;
    }
    
    public final int getCallTimeout$okhttp() {
      return this.callTimeout;
    }
    
    public final void setCallTimeout$okhttp(int <set-?>) {
      this.callTimeout = <set-?>;
    }
    
    public final int getConnectTimeout$okhttp() {
      return this.connectTimeout;
    }
    
    public final void setConnectTimeout$okhttp(int <set-?>) {
      this.connectTimeout = <set-?>;
    }
    
    public final int getReadTimeout$okhttp() {
      return this.readTimeout;
    }
    
    public final void setReadTimeout$okhttp(int <set-?>) {
      this.readTimeout = <set-?>;
    }
    
    public final int getWriteTimeout$okhttp() {
      return this.writeTimeout;
    }
    
    public final void setWriteTimeout$okhttp(int <set-?>) {
      this.writeTimeout = <set-?>;
    }
    
    public final int getPingInterval$okhttp() {
      return this.pingInterval;
    }
    
    public final void setPingInterval$okhttp(int <set-?>) {
      this.pingInterval = <set-?>;
    }
    
    public final long getMinWebSocketMessageToCompress$okhttp() {
      return this.minWebSocketMessageToCompress;
    }
    
    public final void setMinWebSocketMessageToCompress$okhttp(long <set-?>) {
      this.minWebSocketMessageToCompress = <set-?>;
    }
    
    @Nullable
    public final RouteDatabase getRouteDatabase$okhttp() {
      return this.routeDatabase;
    }
    
    public final void setRouteDatabase$okhttp(@Nullable RouteDatabase <set-?>) {
      this.routeDatabase = <set-?>;
    }
    
    public Builder(@NotNull OkHttpClient okHttpClient) {
      this();
      this.dispatcher = okHttpClient.dispatcher();
      this.connectionPool = okHttpClient.connectionPool();
      CollectionsKt.addAll(this.interceptors, okHttpClient.interceptors());
      CollectionsKt.addAll(this.networkInterceptors, okHttpClient.networkInterceptors());
      this.eventListenerFactory = okHttpClient.eventListenerFactory();
      this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure();
      this.authenticator = okHttpClient.authenticator();
      this.followRedirects = okHttpClient.followRedirects();
      this.followSslRedirects = okHttpClient.followSslRedirects();
      this.cookieJar = okHttpClient.cookieJar();
      this.cache = okHttpClient.cache();
      this.dns = okHttpClient.dns();
      this.proxy = okHttpClient.proxy();
      this.proxySelector = okHttpClient.proxySelector();
      this.proxyAuthenticator = okHttpClient.proxyAuthenticator();
      this.socketFactory = okHttpClient.socketFactory();
      this.sslSocketFactoryOrNull = okHttpClient.sslSocketFactoryOrNull;
      this.x509TrustManagerOrNull = okHttpClient.x509TrustManager();
      this.connectionSpecs = okHttpClient.connectionSpecs();
      this.protocols = okHttpClient.protocols();
      this.hostnameVerifier = okHttpClient.hostnameVerifier();
      this.certificatePinner = okHttpClient.certificatePinner();
      this.certificateChainCleaner = okHttpClient.certificateChainCleaner();
      this.callTimeout = okHttpClient.callTimeoutMillis();
      this.connectTimeout = okHttpClient.connectTimeoutMillis();
      this.readTimeout = okHttpClient.readTimeoutMillis();
      this.writeTimeout = okHttpClient.writeTimeoutMillis();
      this.pingInterval = okHttpClient.pingIntervalMillis();
      this.minWebSocketMessageToCompress = okHttpClient.minWebSocketMessageToCompress();
      this.routeDatabase = okHttpClient.getRouteDatabase();
    }
    
    @NotNull
    public final Builder dispatcher(@NotNull Dispatcher dispatcher) {
      Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
      Builder builder1 = this, $this$dispatcher_u24lambda_u240 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$dispatcher$1 = 0;
      $this$dispatcher_u24lambda_u240.dispatcher = dispatcher;
      return builder1;
    }
    
    @NotNull
    public final Builder connectionPool(@NotNull ConnectionPool connectionPool) {
      Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
      Builder builder1 = this, $this$connectionPool_u24lambda_u241 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$connectionPool$1 = 0;
      $this$connectionPool_u24lambda_u241.connectionPool = connectionPool;
      return builder1;
    }
    
    @NotNull
    public final List<Interceptor> interceptors() {
      return this.interceptors;
    }
    
    @NotNull
    public final Builder addInterceptor(@NotNull Interceptor interceptor) {
      Intrinsics.checkNotNullParameter(interceptor, "interceptor");
      Builder builder1 = this, $this$addInterceptor_u24lambda_u242 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$addInterceptor$1 = 0;
      $this$addInterceptor_u24lambda_u242.interceptors.add(interceptor);
      return builder1;
    }
    
    @JvmName(name = "-addInterceptor")
    @NotNull
    public final Builder -addInterceptor(@NotNull Function1<? super Interceptor.Chain, Response> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$-addInterceptor = 0;
      return addInterceptor(new OkHttpClient$Builder$addInterceptor$2(block));
    }
    
    @Metadata(mv = {1, 8, 0}, k = 3, xi = 176, d1 = {"\000\016\n\002\030\002\n\000\n\002\030\002\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Lokhttp3/Interceptor$Chain;", "chain", "Lokhttp3/Response;", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "<anonymous>"})
    @SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder$addInterceptor$2\n*L\n1#1,1079:1\n*E\n"})
    public static final class OkHttpClient$Builder$addInterceptor$2 implements Interceptor {
      public OkHttpClient$Builder$addInterceptor$2(Function1<Interceptor.Chain, Response> $block) {}
      
      @NotNull
      public final Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return (Response)this.$block.invoke(chain);
      }
    }
    
    @NotNull
    public final List<Interceptor> networkInterceptors() {
      return this.networkInterceptors;
    }
    
    @NotNull
    public final Builder addNetworkInterceptor(@NotNull Interceptor interceptor) {
      Intrinsics.checkNotNullParameter(interceptor, "interceptor");
      Builder builder1 = this, $this$addNetworkInterceptor_u24lambda_u243 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$addNetworkInterceptor$1 = 0;
      $this$addNetworkInterceptor_u24lambda_u243.networkInterceptors.add(interceptor);
      return builder1;
    }
    
    @JvmName(name = "-addNetworkInterceptor")
    @NotNull
    public final Builder -addNetworkInterceptor(@NotNull Function1<? super Interceptor.Chain, Response> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$-addNetworkInterceptor = 0;
      return addNetworkInterceptor(new OkHttpClient$Builder$addNetworkInterceptor$2(block));
    }
    
    @Metadata(mv = {1, 8, 0}, k = 3, xi = 176, d1 = {"\000\016\n\002\030\002\n\000\n\002\030\002\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Lokhttp3/Interceptor$Chain;", "chain", "Lokhttp3/Response;", "intercept", "(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;", "<anonymous>"})
    @SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder$addNetworkInterceptor$2\n*L\n1#1,1079:1\n*E\n"})
    public static final class OkHttpClient$Builder$addNetworkInterceptor$2 implements Interceptor {
      public OkHttpClient$Builder$addNetworkInterceptor$2(Function1<Interceptor.Chain, Response> $block) {}
      
      @NotNull
      public final Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return (Response)this.$block.invoke(chain);
      }
    }
    
    @NotNull
    public final Builder eventListener(@NotNull EventListener eventListener) {
      Intrinsics.checkNotNullParameter(eventListener, "eventListener");
      Builder builder1 = this, $this$eventListener_u24lambda_u244 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$eventListener$1 = 0;
      $this$eventListener_u24lambda_u244.eventListenerFactory = Util.asFactory(eventListener);
      return builder1;
    }
    
    @NotNull
    public final Builder eventListenerFactory(@NotNull EventListener.Factory eventListenerFactory) {
      Intrinsics.checkNotNullParameter(eventListenerFactory, "eventListenerFactory");
      Builder builder1 = this, $this$eventListenerFactory_u24lambda_u245 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$eventListenerFactory$1 = 0;
      $this$eventListenerFactory_u24lambda_u245.eventListenerFactory = eventListenerFactory;
      return builder1;
    }
    
    @NotNull
    public final Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
      Builder builder1 = this, $this$retryOnConnectionFailure_u24lambda_u246 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$retryOnConnectionFailure$1 = 0;
      $this$retryOnConnectionFailure_u24lambda_u246.retryOnConnectionFailure = retryOnConnectionFailure;
      return builder1;
    }
    
    @NotNull
    public final Builder authenticator(@NotNull Authenticator authenticator) {
      Intrinsics.checkNotNullParameter(authenticator, "authenticator");
      Builder builder1 = this, $this$authenticator_u24lambda_u247 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$authenticator$1 = 0;
      $this$authenticator_u24lambda_u247.authenticator = authenticator;
      return builder1;
    }
    
    @NotNull
    public final Builder followRedirects(boolean followRedirects) {
      Builder builder1 = this, $this$followRedirects_u24lambda_u248 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$followRedirects$1 = 0;
      $this$followRedirects_u24lambda_u248.followRedirects = followRedirects;
      return builder1;
    }
    
    @NotNull
    public final Builder followSslRedirects(boolean followProtocolRedirects) {
      Builder builder1 = this, $this$followSslRedirects_u24lambda_u249 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$followSslRedirects$1 = 0;
      $this$followSslRedirects_u24lambda_u249.followSslRedirects = followProtocolRedirects;
      return builder1;
    }
    
    @NotNull
    public final Builder cookieJar(@NotNull CookieJar cookieJar) {
      Intrinsics.checkNotNullParameter(cookieJar, "cookieJar");
      Builder builder1 = this, $this$cookieJar_u24lambda_u2410 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$cookieJar$1 = 0;
      $this$cookieJar_u24lambda_u2410.cookieJar = cookieJar;
      return builder1;
    }
    
    @NotNull
    public final Builder cache(@Nullable Cache cache) {
      Builder builder1 = this, $this$cache_u24lambda_u2411 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$cache$1 = 0;
      $this$cache_u24lambda_u2411.cache = cache;
      return builder1;
    }
    
    @NotNull
    public final Builder dns(@NotNull Dns dns) {
      Intrinsics.checkNotNullParameter(dns, "dns");
      Builder builder1 = this, $this$dns_u24lambda_u2412 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$dns$1 = 0;
      if (!Intrinsics.areEqual(dns, $this$dns_u24lambda_u2412.dns))
        $this$dns_u24lambda_u2412.routeDatabase = null; 
      $this$dns_u24lambda_u2412.dns = dns;
      return builder1;
    }
    
    @NotNull
    public final Builder proxy(@Nullable Proxy proxy) {
      Builder builder1 = this, $this$proxy_u24lambda_u2413 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$proxy$1 = 0;
      if (!Intrinsics.areEqual(proxy, $this$proxy_u24lambda_u2413.proxy))
        $this$proxy_u24lambda_u2413.routeDatabase = null; 
      $this$proxy_u24lambda_u2413.proxy = proxy;
      return builder1;
    }
    
    @NotNull
    public final Builder proxySelector(@NotNull ProxySelector proxySelector) {
      Intrinsics.checkNotNullParameter(proxySelector, "proxySelector");
      Builder builder1 = this, $this$proxySelector_u24lambda_u2414 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$proxySelector$1 = 0;
      if (!Intrinsics.areEqual(proxySelector, $this$proxySelector_u24lambda_u2414.proxySelector))
        $this$proxySelector_u24lambda_u2414.routeDatabase = null; 
      $this$proxySelector_u24lambda_u2414.proxySelector = proxySelector;
      return builder1;
    }
    
    @NotNull
    public final Builder proxyAuthenticator(@NotNull Authenticator proxyAuthenticator) {
      Intrinsics.checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator");
      Builder builder1 = this, $this$proxyAuthenticator_u24lambda_u2415 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$proxyAuthenticator$1 = 0;
      if (!Intrinsics.areEqual(proxyAuthenticator, $this$proxyAuthenticator_u24lambda_u2415.proxyAuthenticator))
        $this$proxyAuthenticator_u24lambda_u2415.routeDatabase = null; 
      $this$proxyAuthenticator_u24lambda_u2415.proxyAuthenticator = proxyAuthenticator;
      return builder1;
    }
    
    @NotNull
    public final Builder socketFactory(@NotNull SocketFactory socketFactory) {
      Intrinsics.checkNotNullParameter(socketFactory, "socketFactory");
      Builder builder1 = this, $this$socketFactory_u24lambda_u2417 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$socketFactory$1 = 0;
      if (!(!(socketFactory instanceof SSLSocketFactory) ? 1 : 0)) {
        int $i$a$-require-OkHttpClient$Builder$socketFactory$1$1 = 0;
        String str = "socketFactory instanceof SSLSocketFactory";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!Intrinsics.areEqual(socketFactory, $this$socketFactory_u24lambda_u2417.socketFactory))
        $this$socketFactory_u24lambda_u2417.routeDatabase = null; 
      $this$socketFactory_u24lambda_u2417.socketFactory = socketFactory;
      return builder1;
    }
    
    @Deprecated(message = "Use the sslSocketFactory overload that accepts a X509TrustManager.", level = DeprecationLevel.ERROR)
    @NotNull
    public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
      Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
      Builder builder1 = this, $this$sslSocketFactory_u24lambda_u2418 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$sslSocketFactory$1 = 0;
      if (!Intrinsics.areEqual(sslSocketFactory, $this$sslSocketFactory_u24lambda_u2418.sslSocketFactoryOrNull))
        $this$sslSocketFactory_u24lambda_u2418.routeDatabase = null; 
      $this$sslSocketFactory_u24lambda_u2418.sslSocketFactoryOrNull = sslSocketFactory;
      if (Platform.Companion.get().trustManager(sslSocketFactory) == null) {
        Platform.Companion.get().trustManager(sslSocketFactory);
        throw new IllegalStateException("Unable to extract the trust manager on " + Platform.Companion.get() + ", sslSocketFactory is " + sslSocketFactory.getClass());
      } 
      ((Builder)Platform.Companion.get().trustManager(sslSocketFactory)).x509TrustManagerOrNull = Platform.Companion.get().trustManager(sslSocketFactory);
      Intrinsics.checkNotNull($this$sslSocketFactory_u24lambda_u2418.x509TrustManagerOrNull);
      $this$sslSocketFactory_u24lambda_u2418.certificateChainCleaner = Platform.Companion.get().buildCertificateChainCleaner($this$sslSocketFactory_u24lambda_u2418.x509TrustManagerOrNull);
      return builder1;
    }
    
    @NotNull
    public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory, @NotNull X509TrustManager trustManager) {
      Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
      Intrinsics.checkNotNullParameter(trustManager, "trustManager");
      Builder builder1 = this, $this$sslSocketFactory_u24lambda_u2419 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$sslSocketFactory$2 = 0;
      if (!Intrinsics.areEqual(sslSocketFactory, $this$sslSocketFactory_u24lambda_u2419.sslSocketFactoryOrNull) || !Intrinsics.areEqual(trustManager, $this$sslSocketFactory_u24lambda_u2419.x509TrustManagerOrNull))
        $this$sslSocketFactory_u24lambda_u2419.routeDatabase = null; 
      $this$sslSocketFactory_u24lambda_u2419.sslSocketFactoryOrNull = sslSocketFactory;
      $this$sslSocketFactory_u24lambda_u2419.certificateChainCleaner = CertificateChainCleaner.Companion.get(trustManager);
      $this$sslSocketFactory_u24lambda_u2419.x509TrustManagerOrNull = trustManager;
      return builder1;
    }
    
    @NotNull
    public final Builder connectionSpecs(@NotNull List connectionSpecs) {
      Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
      Builder builder1 = this, $this$connectionSpecs_u24lambda_u2420 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$connectionSpecs$1 = 0;
      if (!Intrinsics.areEqual(connectionSpecs, $this$connectionSpecs_u24lambda_u2420.connectionSpecs))
        $this$connectionSpecs_u24lambda_u2420.routeDatabase = null; 
      $this$connectionSpecs_u24lambda_u2420.connectionSpecs = Util.toImmutableList(connectionSpecs);
      return builder1;
    }
    
    @NotNull
    public final Builder protocols(@NotNull List protocols) {
      Intrinsics.checkNotNullParameter(protocols, "protocols");
      Builder builder1 = this, $this$protocols_u24lambda_u2425 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$protocols$1 = 0;
      List<?> protocolsCopy = CollectionsKt.toMutableList(protocols);
      if (!((protocolsCopy.contains(Protocol.H2_PRIOR_KNOWLEDGE) || protocolsCopy.contains(Protocol.HTTP_1_1)) ? 1 : 0)) {
        int $i$a$-require-OkHttpClient$Builder$protocols$1$1 = 0;
        String str = "protocols must contain h2_prior_knowledge or http/1.1: " + protocolsCopy;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((!protocolsCopy.contains(Protocol.H2_PRIOR_KNOWLEDGE) || protocolsCopy.size() <= 1) ? 1 : 0)) {
        int $i$a$-require-OkHttpClient$Builder$protocols$1$2 = 0;
        String str = "protocols containing h2_prior_knowledge cannot use other protocols: " + protocolsCopy;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!protocolsCopy.contains(Protocol.HTTP_1_0) ? 1 : 0)) {
        int $i$a$-require-OkHttpClient$Builder$protocols$1$3 = 0;
        String str = "protocols must not contain http/1.0: " + protocolsCopy;
        throw new IllegalArgumentException(str.toString());
      } 
      Intrinsics.checkNotNull(protocolsCopy, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Protocol?>");
      if (!(!protocolsCopy.contains(null) ? 1 : 0)) {
        int $i$a$-require-OkHttpClient$Builder$protocols$1$4 = 0;
        String str = "protocols must not contain null";
        throw new IllegalArgumentException(str.toString());
      } 
      protocolsCopy.remove(Protocol.SPDY_3);
      if (!Intrinsics.areEqual(protocolsCopy, $this$protocols_u24lambda_u2425.protocols))
        $this$protocols_u24lambda_u2425.routeDatabase = null; 
      Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(protocolsCopy), "unmodifiableList(protocolsCopy)");
      $this$protocols_u24lambda_u2425.protocols = Collections.unmodifiableList(protocolsCopy);
      return builder1;
    }
    
    @NotNull
    public final Builder hostnameVerifier(@NotNull HostnameVerifier hostnameVerifier) {
      Intrinsics.checkNotNullParameter(hostnameVerifier, "hostnameVerifier");
      Builder builder1 = this, $this$hostnameVerifier_u24lambda_u2426 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$hostnameVerifier$1 = 0;
      if (!Intrinsics.areEqual(hostnameVerifier, $this$hostnameVerifier_u24lambda_u2426.hostnameVerifier))
        $this$hostnameVerifier_u24lambda_u2426.routeDatabase = null; 
      $this$hostnameVerifier_u24lambda_u2426.hostnameVerifier = hostnameVerifier;
      return builder1;
    }
    
    @NotNull
    public final Builder certificatePinner(@NotNull CertificatePinner certificatePinner) {
      Intrinsics.checkNotNullParameter(certificatePinner, "certificatePinner");
      Builder builder1 = this, $this$certificatePinner_u24lambda_u2427 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$certificatePinner$1 = 0;
      if (!Intrinsics.areEqual(certificatePinner, $this$certificatePinner_u24lambda_u2427.certificatePinner))
        $this$certificatePinner_u24lambda_u2427.routeDatabase = null; 
      $this$certificatePinner_u24lambda_u2427.certificatePinner = certificatePinner;
      return builder1;
    }
    
    @NotNull
    public final Builder callTimeout(long timeout, @NotNull TimeUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      Builder builder1 = this, $this$callTimeout_u24lambda_u2428 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$callTimeout$1 = 0;
      $this$callTimeout_u24lambda_u2428.callTimeout = Util.checkDuration("timeout", timeout, unit);
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder callTimeout(@NotNull Duration duration) {
      Intrinsics.checkNotNullParameter(duration, "duration");
      Builder builder1 = this, $this$callTimeout_u24lambda_u2429 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$callTimeout$2 = 0;
      $this$callTimeout_u24lambda_u2429.callTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
      return builder1;
    }
    
    @NotNull
    public final Builder connectTimeout(long timeout, @NotNull TimeUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      Builder builder1 = this, $this$connectTimeout_u24lambda_u2430 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$connectTimeout$1 = 0;
      $this$connectTimeout_u24lambda_u2430.connectTimeout = Util.checkDuration("timeout", timeout, unit);
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder connectTimeout(@NotNull Duration duration) {
      Intrinsics.checkNotNullParameter(duration, "duration");
      Builder builder1 = this, $this$connectTimeout_u24lambda_u2431 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$connectTimeout$2 = 0;
      $this$connectTimeout_u24lambda_u2431.connectTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
      return builder1;
    }
    
    @NotNull
    public final Builder readTimeout(long timeout, @NotNull TimeUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      Builder builder1 = this, $this$readTimeout_u24lambda_u2432 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$readTimeout$1 = 0;
      $this$readTimeout_u24lambda_u2432.readTimeout = Util.checkDuration("timeout", timeout, unit);
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder readTimeout(@NotNull Duration duration) {
      Intrinsics.checkNotNullParameter(duration, "duration");
      Builder builder1 = this, $this$readTimeout_u24lambda_u2433 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$readTimeout$2 = 0;
      $this$readTimeout_u24lambda_u2433.readTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
      return builder1;
    }
    
    @NotNull
    public final Builder writeTimeout(long timeout, @NotNull TimeUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      Builder builder1 = this, $this$writeTimeout_u24lambda_u2434 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$writeTimeout$1 = 0;
      $this$writeTimeout_u24lambda_u2434.writeTimeout = Util.checkDuration("timeout", timeout, unit);
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder writeTimeout(@NotNull Duration duration) {
      Intrinsics.checkNotNullParameter(duration, "duration");
      Builder builder1 = this, $this$writeTimeout_u24lambda_u2435 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$writeTimeout$2 = 0;
      $this$writeTimeout_u24lambda_u2435.writeTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
      return builder1;
    }
    
    @NotNull
    public final Builder pingInterval(long interval, @NotNull TimeUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      Builder builder1 = this, $this$pingInterval_u24lambda_u2436 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$pingInterval$1 = 0;
      $this$pingInterval_u24lambda_u2436.pingInterval = Util.checkDuration("interval", interval, unit);
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder pingInterval(@NotNull Duration duration) {
      Intrinsics.checkNotNullParameter(duration, "duration");
      Builder builder1 = this, $this$pingInterval_u24lambda_u2437 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$pingInterval$2 = 0;
      $this$pingInterval_u24lambda_u2437.pingInterval(duration.toMillis(), TimeUnit.MILLISECONDS);
      return builder1;
    }
    
    @NotNull
    public final Builder minWebSocketMessageToCompress(long bytes) {
      Builder builder1 = this, $this$minWebSocketMessageToCompress_u24lambda_u2439 = builder1;
      int $i$a$-apply-OkHttpClient$Builder$minWebSocketMessageToCompress$1 = 0;
      if (!((bytes >= 0L) ? 1 : 0)) {
        int $i$a$-require-OkHttpClient$Builder$minWebSocketMessageToCompress$1$1 = 0;
        String str = "minWebSocketMessageToCompress must be positive: " + bytes;
        throw new IllegalArgumentException(str.toString());
      } 
      $this$minWebSocketMessageToCompress_u24lambda_u2439.minWebSocketMessageToCompress = bytes;
      return builder1;
    }
    
    @NotNull
    public final OkHttpClient build() {
      return new OkHttpClient(this);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R \020\006\032\b\022\004\022\0020\0050\0048\000X\004¢\006\f\n\004\b\006\020\007\032\004\b\b\020\tR \020\013\032\b\022\004\022\0020\n0\0048\000X\004¢\006\f\n\004\b\013\020\007\032\004\b\f\020\t¨\006\r"}, d2 = {"Lokhttp3/OkHttpClient$Companion;", "", "<init>", "()V", "", "Lokhttp3/ConnectionSpec;", "DEFAULT_CONNECTION_SPECS", "Ljava/util/List;", "getDEFAULT_CONNECTION_SPECS$okhttp", "()Ljava/util/List;", "Lokhttp3/Protocol;", "DEFAULT_PROTOCOLS", "getDEFAULT_PROTOCOLS$okhttp", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final List<Protocol> getDEFAULT_PROTOCOLS$okhttp() {
      return OkHttpClient.DEFAULT_PROTOCOLS;
    }
    
    @NotNull
    public final List<ConnectionSpec> getDEFAULT_CONNECTION_SPECS$okhttp() {
      return OkHttpClient.DEFAULT_CONNECTION_SPECS;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Dispatcher dispatcher;
  
  @NotNull
  private final ConnectionPool connectionPool;
  
  @NotNull
  private final List<Interceptor> interceptors;
  
  @NotNull
  private final List<Interceptor> networkInterceptors;
  
  @NotNull
  private final EventListener.Factory eventListenerFactory;
  
  private final boolean retryOnConnectionFailure;
  
  @NotNull
  private final Authenticator authenticator;
  
  private final boolean followRedirects;
  
  private final boolean followSslRedirects;
  
  @NotNull
  private final CookieJar cookieJar;
  
  @Nullable
  private final Cache cache;
  
  @NotNull
  private final Dns dns;
  
  @Nullable
  private final Proxy proxy;
  
  @NotNull
  private final ProxySelector proxySelector;
  
  @NotNull
  private final Authenticator proxyAuthenticator;
  
  @NotNull
  private final SocketFactory socketFactory;
  
  @Nullable
  private final SSLSocketFactory sslSocketFactoryOrNull;
  
  @Nullable
  private final X509TrustManager x509TrustManager;
  
  @NotNull
  private final List<ConnectionSpec> connectionSpecs;
  
  @NotNull
  private final List<Protocol> protocols;
  
  @NotNull
  private final HostnameVerifier hostnameVerifier;
  
  @NotNull
  private final CertificatePinner certificatePinner;
  
  @Nullable
  private final CertificateChainCleaner certificateChainCleaner;
  
  private final int callTimeoutMillis;
  
  private final int connectTimeoutMillis;
  
  private final int readTimeoutMillis;
  
  private final int writeTimeoutMillis;
  
  private final int pingIntervalMillis;
  
  private final long minWebSocketMessageToCompress;
  
  @NotNull
  private final RouteDatabase routeDatabase;
  
  @NotNull
  private static final List<Protocol> DEFAULT_PROTOCOLS;
  
  @NotNull
  private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
  
  static {
    Protocol[] arrayOfProtocol = new Protocol[2];
    arrayOfProtocol[0] = Protocol.HTTP_2;
    arrayOfProtocol[1] = Protocol.HTTP_1_1;
    DEFAULT_PROTOCOLS = Util.immutableListOf((Object[])arrayOfProtocol);
    ConnectionSpec[] arrayOfConnectionSpec = new ConnectionSpec[2];
    arrayOfConnectionSpec[0] = ConnectionSpec.MODERN_TLS;
    arrayOfConnectionSpec[1] = ConnectionSpec.CLEARTEXT;
    DEFAULT_CONNECTION_SPECS = Util.immutableListOf((Object[])arrayOfConnectionSpec);
  }
}
