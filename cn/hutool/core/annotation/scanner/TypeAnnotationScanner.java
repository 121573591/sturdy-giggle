package cn.hutool.core.annotation.scanner;

import cn.hutool.core.collection.CollUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class TypeAnnotationScanner extends AbstractTypeAnnotationScanner<TypeAnnotationScanner> implements AnnotationScanner {
  public TypeAnnotationScanner(boolean includeSupperClass, boolean includeInterfaces, Predicate<Class<?>> filter, Set<Class<?>> excludeTypes) {
    super(includeSupperClass, includeInterfaces, filter, excludeTypes);
  }
  
  public TypeAnnotationScanner() {
    this(true, true, t -> true, CollUtil.newLinkedHashSet((Object[])new Class[0]));
  }
  
  public boolean support(AnnotatedElement annotatedEle) {
    return annotatedEle instanceof Class;
  }
  
  protected Class<?> getClassFormAnnotatedElement(AnnotatedElement annotatedEle) {
    return (Class)annotatedEle;
  }
  
  protected Annotation[] getAnnotationsFromTargetClass(AnnotatedElement source, int index, Class<?> targetClass) {
    return targetClass.getAnnotations();
  }
  
  public TypeAnnotationScanner setIncludeSuperClass(boolean includeSuperClass) {
    return super.setIncludeSuperClass(includeSuperClass);
  }
  
  public TypeAnnotationScanner setIncludeInterfaces(boolean includeInterfaces) {
    return super.setIncludeInterfaces(includeInterfaces);
  }
  
  public static class JdkProxyClassConverter implements UnaryOperator<Class<?>> {
    public Class<?> apply(Class<?> sourceClass) {
      return Proxy.isProxyClass(sourceClass) ? apply(sourceClass.getSuperclass()) : sourceClass;
    }
  }
}
