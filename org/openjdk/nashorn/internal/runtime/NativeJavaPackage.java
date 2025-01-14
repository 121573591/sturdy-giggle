package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.beans.BeansLinker;
import jdk.dynalink.beans.StaticClass;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.support.Guards;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.lookup.MethodHandleFunctionality;
import org.openjdk.nashorn.internal.objects.annotations.Function;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;

public final class NativeJavaPackage extends ScriptObject {
  private static final MethodHandleFunctionality MH = MethodHandleFactory.getFunctionality();
  
  private static final MethodHandle CLASS_NOT_FOUND = findOwnMH("classNotFound", void.class, new Class[] { NativeJavaPackage.class });
  
  private static final MethodHandle TYPE_GUARD = Guards.getClassGuard(NativeJavaPackage.class);
  
  private final String name;
  
  public NativeJavaPackage(String name, ScriptObject proto) {
    super(proto, null);
    Context.checkPackageAccess(name);
    this.name = name;
  }
  
  public String getClassName() {
    return "JavaPackage";
  }
  
  public boolean equals(Object other) {
    if (other instanceof NativeJavaPackage)
      return this.name.equals(((NativeJavaPackage)other).name); 
    return false;
  }
  
  public int hashCode() {
    return (this.name == null) ? 0 : this.name.hashCode();
  }
  
  public String getName() {
    return this.name;
  }
  
  public String safeToString() {
    return toString();
  }
  
  public String toString() {
    return "[JavaPackage " + this.name + "]";
  }
  
  public Object getDefaultValue(Class<?> hint) {
    if (hint == String.class)
      return toString(); 
    return super.getDefaultValue(hint);
  }
  
  protected GuardedInvocation findNewMethod(CallSiteDescriptor desc, LinkRequest request) {
    return createClassNotFoundInvocation(desc);
  }
  
  protected GuardedInvocation findCallMethod(CallSiteDescriptor desc, LinkRequest request) {
    return createClassNotFoundInvocation(desc);
  }
  
  private static GuardedInvocation createClassNotFoundInvocation(CallSiteDescriptor desc) {
    MethodType type = desc.getMethodType();
    return new GuardedInvocation(MH
        .dropArguments(CLASS_NOT_FOUND, 1, type.parameterList().subList(1, type.parameterCount())), 
        (type.parameterType(0) == NativeJavaPackage.class) ? null : TYPE_GUARD);
  }
  
  private static void classNotFound(NativeJavaPackage pkg) throws ClassNotFoundException {
    throw new ClassNotFoundException(pkg.name);
  }
  
  @Function(attributes = 2)
  public static Object __noSuchProperty__(Object self, Object name) {
    throw new AssertionError("__noSuchProperty__ placeholder called");
  }
  
  @Function(attributes = 2)
  public static Object __noSuchMethod__(Object self, Object... args) {
    throw new AssertionError("__noSuchMethod__ placeholder called");
  }
  
  public GuardedInvocation noSuchProperty(CallSiteDescriptor desc, LinkRequest request) {
    String propertyName = NashornCallSiteDescriptor.getOperand(desc);
    createProperty(propertyName);
    return lookup(desc, request);
  }
  
  protected Object invokeNoSuchProperty(Object key, boolean isScope, int programPoint) {
    if (!(key instanceof String))
      return super.invokeNoSuchProperty(key, isScope, programPoint); 
    Object retval = createProperty((String)key);
    if (UnwarrantedOptimismException.isValid(programPoint))
      throw new UnwarrantedOptimismException(retval, programPoint); 
    return retval;
  }
  
  public GuardedInvocation noSuchMethod(CallSiteDescriptor desc, LinkRequest request) {
    return noSuchProperty(desc, request);
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return MH.findStatic(MethodHandles.lookup(), NativeJavaPackage.class, name, MH.type(rtype, types));
  }
  
  private Object createProperty(String propertyName) {
    Object propertyValue;
    String fullName = this.name.isEmpty() ? propertyName : (this.name + "." + this.name);
    Context context = Context.getContextTrusted();
    Class<?> javaClass = null;
    try {
      javaClass = context.findClass(fullName);
    } catch (NoClassDefFoundError|ClassNotFoundException noClassDefFoundError) {}
    int openBrace = propertyName.indexOf('(');
    int closeBrace = propertyName.lastIndexOf(')');
    if (openBrace != -1 || closeBrace != -1) {
      int lastChar = propertyName.length() - 1;
      if (openBrace == -1 || closeBrace != lastChar)
        throw ECMAErrors.typeError("improper.constructor.signature", new String[] { propertyName }); 
      String className = this.name + "." + this.name;
      try {
        javaClass = context.findClass(className);
      } catch (NoClassDefFoundError|ClassNotFoundException e) {
        throw ECMAErrors.typeError(e, "no.such.java.class", new String[] { className });
      } 
      Object constructor = BeansLinker.getConstructorMethod(javaClass, propertyName
          .substring(openBrace + 1, lastChar));
      if (constructor != null) {
        set(propertyName, constructor, 0);
        return constructor;
      } 
      throw ECMAErrors.typeError("no.such.java.constructor", new String[] { propertyName });
    } 
    if (javaClass == null) {
      propertyValue = new NativeJavaPackage(fullName, getProto());
    } else {
      propertyValue = StaticClass.forClass(javaClass);
    } 
    set(propertyName, propertyValue, 0);
    return propertyValue;
  }
}
