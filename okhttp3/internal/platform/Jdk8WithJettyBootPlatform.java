package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\n\030\000 \0332\0020\001:\002\034\033B7\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\005\032\0020\002\022\n\020\007\032\006\022\002\b\0030\006\022\n\020\b\032\006\022\002\b\0030\006¢\006\004\b\t\020\nJ\027\020\016\032\0020\r2\006\020\f\032\0020\013H\026¢\006\004\b\016\020\017J/\020\025\032\0020\r2\006\020\f\032\0020\0132\b\020\021\032\004\030\0010\0202\f\020\024\032\b\022\004\022\0020\0230\022H\026¢\006\004\b\025\020\026J\031\020\027\032\004\030\0010\0202\006\020\f\032\0020\013H\026¢\006\004\b\027\020\030R\030\020\007\032\006\022\002\b\0030\0068\002X\004¢\006\006\n\004\b\007\020\031R\024\020\004\032\0020\0028\002X\004¢\006\006\n\004\b\004\020\032R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\032R\024\020\005\032\0020\0028\002X\004¢\006\006\n\004\b\005\020\032R\030\020\b\032\006\022\002\b\0030\0068\002X\004¢\006\006\n\004\b\b\020\031¨\006\035"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform;", "Lokhttp3/internal/platform/Platform;", "Ljava/lang/reflect/Method;", "putMethod", "getMethod", "removeMethod", "Ljava/lang/Class;", "clientProviderClass", "serverProviderClass", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V", "Ljavax/net/ssl/SSLSocket;", "sslSocket", "", "afterHandshake", "(Ljavax/net/ssl/SSLSocket;)V", "", "hostname", "", "Lokhttp3/Protocol;", "protocols", "configureTlsExtensions", "(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V", "getSelectedProtocol", "(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;", "Ljava/lang/Class;", "Ljava/lang/reflect/Method;", "Companion", "AlpnProvider", "okhttp"})
public final class Jdk8WithJettyBootPlatform extends Platform {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final Method putMethod;
  
  @NotNull
  private final Method getMethod;
  
  @NotNull
  private final Method removeMethod;
  
  @NotNull
  private final Class<?> clientProviderClass;
  
  @NotNull
  private final Class<?> serverProviderClass;
  
  public Jdk8WithJettyBootPlatform(@NotNull Method putMethod, @NotNull Method getMethod, @NotNull Method removeMethod, @NotNull Class<?> clientProviderClass, @NotNull Class<?> serverProviderClass) {
    this.putMethod = putMethod;
    this.getMethod = getMethod;
    this.removeMethod = removeMethod;
    this.clientProviderClass = clientProviderClass;
    this.serverProviderClass = serverProviderClass;
  }
  
  public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    Intrinsics.checkNotNullParameter(protocols, "protocols");
    List<String> names = Platform.Companion.alpnProtocolNames(protocols);
    try {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = this.clientProviderClass;
      arrayOfClass[1] = this.serverProviderClass;
      Object alpnProvider = Proxy.newProxyInstance(Platform.class.getClassLoader(), arrayOfClass, new AlpnProvider(names));
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = sslSocket;
      arrayOfObject[1] = alpnProvider;
      this.putMethod.invoke(null, arrayOfObject);
    } catch (InvocationTargetException e) {
      throw new AssertionError("failed to set ALPN", (Throwable)e);
    } catch (IllegalAccessException e) {
      throw new AssertionError("failed to set ALPN", (Throwable)e);
    } 
  }
  
  public void afterHandshake(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    try {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = sslSocket;
      this.removeMethod.invoke(null, arrayOfObject);
    } catch (IllegalAccessException e) {
      throw new AssertionError("failed to remove ALPN", (Throwable)e);
    } catch (InvocationTargetException e) {
      throw new AssertionError("failed to remove ALPN", (Throwable)e);
    } 
  }
  
  @Nullable
  public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    try {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = sslSocket;
      Intrinsics.checkNotNull(Proxy.getInvocationHandler(this.getMethod.invoke(null, arrayOfObject)), "null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
      AlpnProvider provider = (AlpnProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, arrayOfObject));
      if (!provider.getUnsupported() && provider.getSelected() == null) {
        Platform.log$default(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
        return null;
      } 
      return provider.getUnsupported() ? null : provider.getSelected();
    } catch (InvocationTargetException e) {
      throw new AssertionError("failed to get ALPN selected protocol", (Throwable)e);
    } catch (IllegalAccessException e) {
      throw new AssertionError("failed to get ALPN selected protocol", (Throwable)e);
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\030\002\n\002\020 \n\002\020\016\n\002\b\003\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\021\n\002\b\n\n\002\020\013\n\002\b\007\b\002\030\0002\0020\001B\025\022\f\020\004\032\b\022\004\022\0020\0030\002¢\006\004\b\005\020\006J2\020\r\032\004\030\0010\0072\006\020\b\032\0020\0072\006\020\n\032\0020\t2\016\020\f\032\n\022\004\022\0020\007\030\0010\013H\002¢\006\004\b\r\020\016R\032\020\004\032\b\022\004\022\0020\0030\0028\002X\004¢\006\006\n\004\b\004\020\017R$\020\020\032\004\030\0010\0038\006@\006X\016¢\006\022\n\004\b\020\020\021\032\004\b\022\020\023\"\004\b\024\020\025R\"\020\027\032\0020\0268\006@\006X\016¢\006\022\n\004\b\027\020\030\032\004\b\031\020\032\"\004\b\033\020\034¨\006\035"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$AlpnProvider;", "Ljava/lang/reflect/InvocationHandler;", "", "", "protocols", "<init>", "(Ljava/util/List;)V", "", "proxy", "Ljava/lang/reflect/Method;", "method", "", "args", "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "Ljava/util/List;", "selected", "Ljava/lang/String;", "getSelected", "()Ljava/lang/String;", "setSelected", "(Ljava/lang/String;)V", "", "unsupported", "Z", "getUnsupported", "()Z", "setUnsupported", "(Z)V", "okhttp"})
  private static final class AlpnProvider implements InvocationHandler {
    @NotNull
    private final List<String> protocols;
    
    private boolean unsupported;
    
    @Nullable
    private String selected;
    
    public AlpnProvider(@NotNull List<String> protocols) {
      this.protocols = protocols;
    }
    
    public final boolean getUnsupported() {
      return this.unsupported;
    }
    
    public final void setUnsupported(boolean <set-?>) {
      this.unsupported = <set-?>;
    }
    
    @Nullable
    public final String getSelected() {
      return this.selected;
    }
    
    public final void setSelected(@Nullable String <set-?>) {
      this.selected = <set-?>;
    }
    
    @Nullable
    public Object invoke(@NotNull Object proxy, @NotNull Method method, @Nullable Object[] args) throws Throwable {
      Intrinsics.checkNotNullParameter(proxy, "proxy");
      Intrinsics.checkNotNullParameter(method, "method");
      if (args == null);
      Object[] callArgs = new Object[0];
      String methodName = method.getName();
      Class<?> returnType = method.getReturnType();
      if (Intrinsics.areEqual(methodName, "supports") && Intrinsics.areEqual(boolean.class, returnType))
        return Boolean.valueOf(true); 
      if (Intrinsics.areEqual(methodName, "unsupported") && Intrinsics.areEqual(void.class, returnType)) {
        this.unsupported = true;
        return null;
      } 
      if (Intrinsics.areEqual(methodName, "protocols") && ((callArgs.length == 0)))
        return this.protocols; 
      if ((Intrinsics.areEqual(methodName, "selectProtocol") || Intrinsics.areEqual(methodName, "select")) && 
        Intrinsics.areEqual(String.class, returnType) && callArgs.length == 1 && callArgs[0] instanceof List) {
        Intrinsics.checkNotNull(callArgs[0], "null cannot be cast to non-null type kotlin.collections.List<*>");
        List peerProtocols = (List)callArgs[0];
        int i = 0, j = peerProtocols.size();
        if (i <= j)
          while (true) {
            Intrinsics.checkNotNull(peerProtocols.get(i), "null cannot be cast to non-null type kotlin.String");
            String protocol = (String)peerProtocols.get(i);
            if (this.protocols.contains(protocol)) {
              this.selected = protocol;
              return this.selected;
            } 
            if (i != j) {
              i++;
              continue;
            } 
            break;
          }  
        this.selected = this.protocols.get(0);
        return this.selected;
      } 
      if ((Intrinsics.areEqual(methodName, "protocolSelected") || Intrinsics.areEqual(methodName, "selected")) && callArgs.length == 1) {
        Intrinsics.checkNotNull(callArgs[0], "null cannot be cast to non-null type kotlin.String");
        this.selected = (String)callArgs[0];
        return null;
      } 
      return method.invoke(this, Arrays.copyOf(callArgs, callArgs.length));
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\017\020\005\032\004\030\0010\004¢\006\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/Platform;", "buildIfSupported", "()Lokhttp3/internal/platform/Platform;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @Nullable
    public final Platform buildIfSupported() {
      String jvmVersion = System.getProperty("java.specification.version", "unknown");
      try {
        Intrinsics.checkNotNullExpressionValue(jvmVersion, "jvmVersion");
        int version = Integer.parseInt(jvmVersion);
        if (version >= 9)
          return null; 
      } catch (NumberFormatException numberFormatException) {}
      try {
        String alpnClassName = "org.eclipse.jetty.alpn.ALPN";
        Class<?> alpnClass = Class.forName(alpnClassName, true, (ClassLoader)null);
        Class<?> providerClass = Class.forName(alpnClassName + "$Provider", true, (ClassLoader)null);
        Class<?> clientProviderClass = Class.forName(alpnClassName + "$ClientProvider", true, (ClassLoader)null);
        Class<?> serverProviderClass = Class.forName(alpnClassName + "$ServerProvider", true, (ClassLoader)null);
        Class[] arrayOfClass1 = new Class[2];
        arrayOfClass1[0] = SSLSocket.class;
        arrayOfClass1[1] = providerClass;
        Method putMethod = alpnClass.getMethod("put", arrayOfClass1);
        Class[] arrayOfClass2 = new Class[1];
        arrayOfClass2[0] = SSLSocket.class;
        Method getMethod = alpnClass.getMethod("get", arrayOfClass2);
        Class[] arrayOfClass3 = new Class[1];
        arrayOfClass3[0] = SSLSocket.class;
        Method removeMethod = alpnClass.getMethod("remove", arrayOfClass3);
        Intrinsics.checkNotNullExpressionValue(putMethod, "putMethod");
        Intrinsics.checkNotNullExpressionValue(getMethod, "getMethod");
        Intrinsics.checkNotNullExpressionValue(removeMethod, "removeMethod");
        Intrinsics.checkNotNullExpressionValue(clientProviderClass, "clientProviderClass");
        Intrinsics.checkNotNullExpressionValue(serverProviderClass, "serverProviderClass");
        return new Jdk8WithJettyBootPlatform(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass);
      } catch (ClassNotFoundException classNotFoundException) {
      
      } catch (NoSuchMethodException noSuchMethodException) {}
      return null;
    }
  }
}
