package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SynthesizedAnnotationProxy implements InvocationHandler {
  private final AnnotationAttributeValueProvider annotationAttributeValueProvider;
  
  private final SynthesizedAnnotation annotation;
  
  private final Map<String, BiFunction<Method, Object[], Object>> methods;
  
  public static <T extends java.lang.annotation.Annotation> T create(Class<T> annotationType, AnnotationAttributeValueProvider annotationAttributeValueProvider, SynthesizedAnnotation annotation) {
    if (ObjectUtil.isNull(annotation))
      return null; 
    SynthesizedAnnotationProxy proxyHandler = new SynthesizedAnnotationProxy(annotationAttributeValueProvider, annotation);
    if (ObjectUtil.isNull(annotation))
      return null; 
    return (T)Proxy.newProxyInstance(annotationType
        .getClassLoader(), new Class[] { annotationType, SyntheticProxyAnnotation.class }, proxyHandler);
  }
  
  public static <T extends java.lang.annotation.Annotation> T create(Class<T> annotationType, SynthesizedAnnotation annotation) {
    return create(annotationType, annotation, annotation);
  }
  
  public static boolean isProxyAnnotation(Class<?> annotationType) {
    return ClassUtil.isAssignable(SyntheticProxyAnnotation.class, annotationType);
  }
  
  SynthesizedAnnotationProxy(AnnotationAttributeValueProvider annotationAttributeValueProvider, SynthesizedAnnotation annotation) {
    Assert.notNull(annotationAttributeValueProvider, "annotationAttributeValueProvider must not null", new Object[0]);
    Assert.notNull(annotation, "annotation must not null", new Object[0]);
    this.annotationAttributeValueProvider = annotationAttributeValueProvider;
    this.annotation = annotation;
    this.methods = new HashMap<>(9);
    loadMethods();
  }
  
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return Opt.ofNullable(this.methods.get(method.getName()))
      .map(m -> m.apply(method, args))
      .orElseGet(() -> ReflectUtil.invoke(this.annotation.getAnnotation(), method, args));
  }
  
  void loadMethods() {
    this.methods.put("toString", (method, args) -> proxyToString());
    this.methods.put("hashCode", (method, args) -> Integer.valueOf(proxyHashCode()));
    this.methods.put("getSynthesizedAnnotation", (method, args) -> proxyGetSynthesizedAnnotation());
    this.methods.put("getRoot", (method, args) -> this.annotation.getRoot());
    this.methods.put("getVerticalDistance", (method, args) -> Integer.valueOf(this.annotation.getVerticalDistance()));
    this.methods.put("getHorizontalDistance", (method, args) -> Integer.valueOf(this.annotation.getHorizontalDistance()));
    this.methods.put("hasAttribute", (method, args) -> Boolean.valueOf(this.annotation.hasAttribute((String)args[0], (Class)args[1])));
    this.methods.put("getAttributes", (method, args) -> this.annotation.getAttributes());
    this.methods.put("setAttribute", (method, args) -> {
          throw new UnsupportedOperationException("proxied annotation can not reset attributes");
        });
    this.methods.put("getAttributeValue", (method, args) -> this.annotation.getAttributeValue((String)args[0]));
    this.methods.put("annotationType", (method, args) -> this.annotation.annotationType());
    Stream.<Method>of(ClassUtil.getDeclaredMethods(this.annotation.getAnnotation().annotationType()))
      .filter(m -> !this.methods.containsKey(m.getName()))
      .forEach(m -> (BiFunction)this.methods.put(m.getName(), ()));
  }
  
  private String proxyToString() {
    String attributes = Stream.<Method>of(ClassUtil.getDeclaredMethods(this.annotation.getAnnotation().annotationType())).filter(AnnotationUtil::isAttributeMethod).map(method -> CharSequenceUtil.format("{}={}", new Object[] { method.getName(), proxyAttributeValue(method) })).collect(Collectors.joining(", "));
    return CharSequenceUtil.format("@{}({})", new Object[] { this.annotation.annotationType().getName(), attributes });
  }
  
  private int proxyHashCode() {
    return Objects.hash(new Object[] { this.annotationAttributeValueProvider, this.annotation });
  }
  
  private Object proxyGetSynthesizedAnnotation() {
    return this.annotation;
  }
  
  private Object proxyAttributeValue(Method attributeMethod) {
    return this.annotationAttributeValueProvider.getAttributeValue(attributeMethod.getName(), attributeMethod.getReturnType());
  }
  
  static interface SyntheticProxyAnnotation extends SynthesizedAnnotation {
    SynthesizedAnnotation getSynthesizedAnnotation();
  }
}
