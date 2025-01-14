package org.openjdk.nashorn.internal.objects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.MethodHandle;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.LinkRequest;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SpecializedFunction {
  String name() default "";
  
  Class<?> linkLogic() default LinkLogic.Empty.class;
  
  boolean isConstructor() default false;
  
  boolean isOptimistic() default false;
  
  boolean convertsNumericArgs() default true;
  
  public static abstract class LinkLogic {
    public static final LinkLogic EMPTY_INSTANCE = new Empty();
    
    private static final class Empty extends LinkLogic {
      public boolean canLink(Object self, CallSiteDescriptor desc, LinkRequest request) {
        return true;
      }
      
      public boolean isEmpty() {
        return true;
      }
    }
    
    public static Class<? extends LinkLogic> getEmptyLinkLogicClass() {
      return (Class)Empty.class;
    }
    
    public Class<? extends Throwable> getRelinkException() {
      return null;
    }
    
    public static boolean isEmpty(Class<? extends LinkLogic> clazz) {
      return (clazz == Empty.class);
    }
    
    public boolean isEmpty() {
      return false;
    }
    
    public abstract boolean canLink(Object param1Object, CallSiteDescriptor param1CallSiteDescriptor, LinkRequest param1LinkRequest);
    
    public boolean needsGuard(Object self) {
      return true;
    }
    
    public boolean needsGuard(Object self, Object... args) {
      return true;
    }
    
    public MethodHandle getGuard() {
      return null;
    }
    
    public boolean checkLinkable(Object self, CallSiteDescriptor desc, LinkRequest request) {
      return canLink(self, desc, request);
    }
  }
  
  private static final class Empty extends LinkLogic {
    public boolean canLink(Object self, CallSiteDescriptor desc, LinkRequest request) {
      return true;
    }
    
    public boolean isEmpty() {
      return true;
    }
  }
}
