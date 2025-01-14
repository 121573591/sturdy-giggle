package cn.hutool.core.annotation.scanner;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MethodAnnotationScanner extends AbstractTypeAnnotationScanner<MethodAnnotationScanner> implements AnnotationScanner {
  public MethodAnnotationScanner() {
    this(false);
  }
  
  public MethodAnnotationScanner(boolean scanSameSignatureMethod) {
    this(scanSameSignatureMethod, targetClass -> true, CollUtil.newLinkedHashSet((Object[])new Class[0]));
  }
  
  public MethodAnnotationScanner(boolean scanSameSignatureMethod, Predicate<Class<?>> filter, Set<Class<?>> excludeTypes) {
    super(scanSameSignatureMethod, scanSameSignatureMethod, filter, excludeTypes);
  }
  
  public MethodAnnotationScanner(boolean includeSuperClass, boolean includeInterfaces, Predicate<Class<?>> filter, Set<Class<?>> excludeTypes) {
    super(includeSuperClass, includeInterfaces, filter, excludeTypes);
  }
  
  public boolean support(AnnotatedElement annotatedEle) {
    return annotatedEle instanceof Method;
  }
  
  protected Class<?> getClassFormAnnotatedElement(AnnotatedElement annotatedElement) {
    return ((Method)annotatedElement).getDeclaringClass();
  }
  
  protected Annotation[] getAnnotationsFromTargetClass(AnnotatedElement source, int index, Class<?> targetClass) {
    Method sourceMethod = (Method)source;
    return (Annotation[])Stream.<Method>of(ClassUtil.getDeclaredMethods(targetClass))
      .filter(superMethod -> !superMethod.isBridge())
      .filter(superMethod -> hasSameSignature(sourceMethod, superMethod))
      .map(AnnotatedElement::getAnnotations)
      .flatMap(Stream::of)
      .toArray(x$0 -> new Annotation[x$0]);
  }
  
  public MethodAnnotationScanner setScanSameSignatureMethod(boolean scanSuperMethodIfOverride) {
    setIncludeInterfaces(scanSuperMethodIfOverride);
    setIncludeSuperClass(scanSuperMethodIfOverride);
    return this;
  }
  
  private boolean hasSameSignature(Method sourceMethod, Method superMethod) {
    if (false == StrUtil.equals(sourceMethod.getName(), superMethod.getName()))
      return false; 
    Class<?>[] sourceParameterTypes = sourceMethod.getParameterTypes();
    Class<?>[] targetParameterTypes = superMethod.getParameterTypes();
    if (sourceParameterTypes.length != targetParameterTypes.length)
      return false; 
    if (!ArrayUtil.containsAll((Object[])sourceParameterTypes, (Object[])targetParameterTypes))
      return false; 
    return ClassUtil.isAssignable(superMethod.getReturnType(), sourceMethod.getReturnType());
  }
}
