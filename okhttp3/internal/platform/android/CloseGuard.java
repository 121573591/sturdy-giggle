package okhttp3.internal.platform.android;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\005\n\002\020\016\n\002\b\004\n\002\020\013\n\002\b\005\b\000\030\000 \0212\0020\001:\001\021B%\022\b\020\003\032\004\030\0010\002\022\b\020\004\032\004\030\0010\002\022\b\020\005\032\004\030\0010\002¢\006\004\b\006\020\007J\027\020\n\032\004\030\0010\0012\006\020\t\032\0020\b¢\006\004\b\n\020\013J\027\020\016\032\0020\r2\b\020\f\032\004\030\0010\001¢\006\004\b\016\020\017R\026\020\003\032\004\030\0010\0028\002X\004¢\006\006\n\004\b\003\020\020R\026\020\004\032\004\030\0010\0028\002X\004¢\006\006\n\004\b\004\020\020R\026\020\005\032\004\030\0010\0028\002X\004¢\006\006\n\004\b\005\020\020¨\006\022"}, d2 = {"Lokhttp3/internal/platform/android/CloseGuard;", "", "Ljava/lang/reflect/Method;", "getMethod", "openMethod", "warnIfOpenMethod", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "", "closer", "createAndOpen", "(Ljava/lang/String;)Ljava/lang/Object;", "closeGuardInstance", "", "warnIfOpen", "(Ljava/lang/Object;)Z", "Ljava/lang/reflect/Method;", "Companion", "okhttp"})
public final class CloseGuard {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private final Method getMethod;
  
  @Nullable
  private final Method openMethod;
  
  @Nullable
  private final Method warnIfOpenMethod;
  
  public CloseGuard(@Nullable Method getMethod, @Nullable Method openMethod, @Nullable Method warnIfOpenMethod) {
    this.getMethod = getMethod;
    this.openMethod = openMethod;
    this.warnIfOpenMethod = warnIfOpenMethod;
  }
  
  @Nullable
  public final Object createAndOpen(@NotNull String closer) {
    Intrinsics.checkNotNullParameter(closer, "closer");
    if (this.getMethod != null)
      try {
        Object closeGuardInstance = this.getMethod.invoke(null, new Object[0]);
        Intrinsics.checkNotNull(this.openMethod);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = closer;
        this.openMethod.invoke(closeGuardInstance, arrayOfObject);
        return closeGuardInstance;
      } catch (Exception exception) {} 
    return null;
  }
  
  public final boolean warnIfOpen(@Nullable Object closeGuardInstance) {
    boolean reported = false;
    if (closeGuardInstance != null)
      try {
        Intrinsics.checkNotNull(this.warnIfOpenMethod);
        this.warnIfOpenMethod.invoke(closeGuardInstance, new Object[0]);
        reported = true;
      } catch (Exception exception) {} 
    return reported;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/platform/android/CloseGuard$Companion;", "", "<init>", "()V", "Lokhttp3/internal/platform/android/CloseGuard;", "get", "()Lokhttp3/internal/platform/android/CloseGuard;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final CloseGuard get() {
      Method getMethod = null, openMethod = null, warnIfOpenMethod = null;
      try {
        Class<?> closeGuardClass = Class.forName("dalvik.system.CloseGuard");
        getMethod = closeGuardClass.getMethod("get", new Class[0]);
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = String.class;
        openMethod = closeGuardClass.getMethod("open", arrayOfClass);
        warnIfOpenMethod = closeGuardClass.getMethod("warnIfOpen", new Class[0]);
      } catch (Exception _) {
        getMethod = null;
        openMethod = null;
        warnIfOpenMethod = null;
      } 
      return new CloseGuard(getMethod, openMethod, warnIfOpenMethod);
    }
  }
}
