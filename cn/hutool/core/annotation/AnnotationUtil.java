package cn.hutool.core.annotation;

import cn.hutool.core.annotation.scanner.AnnotationScanner;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnnotationUtil {
  static final Set<Class<? extends Annotation>> META_ANNOTATIONS = CollUtil.newHashSet((Object[])new Class[] { Target.class, Retention.class, Inherited.class, Documented.class, SuppressWarnings.class, Override.class, Deprecated.class });
  
  public static boolean isJdkMetaAnnotation(Class<? extends Annotation> annotationType) {
    return META_ANNOTATIONS.contains(annotationType);
  }
  
  public static boolean isNotJdkMateAnnotation(Class<? extends Annotation> annotationType) {
    return (false == isJdkMetaAnnotation(annotationType));
  }
  
  public static CombinationAnnotationElement toCombination(AnnotatedElement annotationEle) {
    if (annotationEle instanceof CombinationAnnotationElement)
      return (CombinationAnnotationElement)annotationEle; 
    return new CombinationAnnotationElement(annotationEle);
  }
  
  public static Annotation[] getAnnotations(AnnotatedElement annotationEle, boolean isToCombination) {
    return getAnnotations(annotationEle, isToCombination, (Predicate<Annotation>)null);
  }
  
  public static <T> T[] getCombinationAnnotations(AnnotatedElement annotationEle, Class<T> annotationType) {
    return getAnnotations(annotationEle, true, annotationType);
  }
  
  public static <T> T[] getAnnotations(AnnotatedElement annotationEle, boolean isToCombination, Class<T> annotationType) {
    Annotation[] annotations = getAnnotations(annotationEle, isToCombination, annotation -> 
        (null == annotationType || annotationType.isAssignableFrom(annotation.getClass())));
    T[] result = (T[])ArrayUtil.newArray(annotationType, annotations.length);
    for (int i = 0; i < annotations.length; i++)
      result[i] = (T)annotations[i]; 
    return result;
  }
  
  public static Annotation[] getAnnotations(AnnotatedElement annotationEle, boolean isToCombination, Predicate<Annotation> predicate) {
    if (null == annotationEle)
      return null; 
    if (isToCombination) {
      if (null == predicate)
        return toCombination(annotationEle).getAnnotations(); 
      return CombinationAnnotationElement.of(annotationEle, predicate).getAnnotations();
    } 
    Annotation[] result = annotationEle.getAnnotations();
    if (null == predicate)
      return result; 
    return (Annotation[])ArrayUtil.filter((Object[])result, predicate::test);
  }
  
  public static <A extends Annotation> A getAnnotation(AnnotatedElement annotationEle, Class<A> annotationType) {
    return (null == annotationEle) ? null : toCombination(annotationEle).<A>getAnnotation(annotationType);
  }
  
  public static boolean hasAnnotation(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) {
    return (null != getAnnotation(annotationEle, (Class)annotationType));
  }
  
  public static <T> T getAnnotationValue(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) throws UtilException {
    return getAnnotationValue(annotationEle, annotationType, "value");
  }
  
  public static <T> T getAnnotationValue(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType, String propertyName) throws UtilException {
    Annotation annotation = getAnnotation(annotationEle, (Class)annotationType);
    if (null == annotation)
      return null; 
    Method method = ReflectUtil.getMethodOfObj(annotation, propertyName, new Object[0]);
    if (null == method)
      return null; 
    return (T)ReflectUtil.invoke(annotation, method, new Object[0]);
  }
  
  public static <A extends Annotation, R> R getAnnotationValue(AnnotatedElement annotationEle, Func1<A, R> propertyName) {
    if (propertyName == null)
      return null; 
    SerializedLambda lambda = LambdaUtil.resolve(propertyName);
    String instantiatedMethodType = lambda.getInstantiatedMethodType();
    Class<A> annotationClass = ClassUtil.loadClass(StrUtil.sub(instantiatedMethodType, 2, StrUtil.indexOf(instantiatedMethodType, ';')));
    return getAnnotationValue(annotationEle, annotationClass, lambda.getImplMethodName());
  }
  
  public static Map<String, Object> getAnnotationValueMap(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) throws UtilException {
    Annotation annotation = getAnnotation(annotationEle, (Class)annotationType);
    if (null == annotation)
      return null; 
    Method[] methods = ReflectUtil.getMethods(annotationType, t -> {
          if (ArrayUtil.isEmpty((Object[])t.getParameterTypes())) {
            String name = t.getName();
            return (false == "hashCode".equals(name) && false == "toString".equals(name) && false == "annotationType".equals(name));
          } 
          return false;
        });
    HashMap<String, Object> result = new HashMap<>(methods.length, 1.0F);
    for (Method method : methods)
      result.put(method.getName(), ReflectUtil.invoke(annotation, method, new Object[0])); 
    return result;
  }
  
  public static RetentionPolicy getRetentionPolicy(Class<? extends Annotation> annotationType) {
    Retention retention = annotationType.<Retention>getAnnotation(Retention.class);
    if (null == retention)
      return RetentionPolicy.CLASS; 
    return retention.value();
  }
  
  public static ElementType[] getTargetType(Class<? extends Annotation> annotationType) {
    Target target = annotationType.<Target>getAnnotation(Target.class);
    if (null == target)
      return new ElementType[] { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.PACKAGE }; 
    return target.value();
  }
  
  public static boolean isDocumented(Class<? extends Annotation> annotationType) {
    return annotationType.isAnnotationPresent((Class)Documented.class);
  }
  
  public static boolean isInherited(Class<? extends Annotation> annotationType) {
    return annotationType.isAnnotationPresent((Class)Inherited.class);
  }
  
  public static List<Annotation> scanMetaAnnotation(Class<? extends Annotation> annotationType) {
    return AnnotationScanner.DIRECTLY_AND_META_ANNOTATION.getAnnotationsIfSupport(annotationType);
  }
  
  public static List<Annotation> scanClass(Class<?> targetClass) {
    return AnnotationScanner.TYPE_HIERARCHY.getAnnotationsIfSupport(targetClass);
  }
  
  public static List<Annotation> scanMethod(Method method) {
    return AnnotationScanner.TYPE_HIERARCHY.getAnnotationsIfSupport(method);
  }
  
  public static void setValue(Annotation annotation, String annotationField, Object value) {
    Map<String, Object> memberValues = (Map)ReflectUtil.getFieldValue(Proxy.getInvocationHandler(annotation), "memberValues");
    memberValues.put(annotationField, value);
  }
  
  public static boolean isSynthesizedAnnotation(Annotation annotation) {
    return SynthesizedAnnotationProxy.isProxyAnnotation(annotation.getClass());
  }
  
  public static <T extends Annotation> T getAnnotationAlias(AnnotatedElement annotationEle, Class<T> annotationType) {
    T annotation = getAnnotation(annotationEle, annotationType);
    if (null == annotation)
      return null; 
    return aggregatingFromAnnotation(new Annotation[] { (Annotation)annotation }).synthesize(annotationType);
  }
  
  public static <T extends Annotation> T getSynthesizedAnnotation(Class<T> annotationType, Annotation... annotations) {
    return (T)Opt.ofNullable(annotations)
      .filter(ArrayUtil::isNotEmpty)
      .map(AnnotationUtil::aggregatingFromAnnotationWithMeta)
      .map(a -> a.synthesize(annotationType))
      .get();
  }
  
  public static <T extends Annotation> T getSynthesizedAnnotation(AnnotatedElement annotatedEle, Class<T> annotationType) {
    T target = annotatedEle.getAnnotation(annotationType);
    if (ObjectUtil.isNotNull(target))
      return target; 
    return (T)AnnotationScanner.DIRECTLY
      .getAnnotationsIfSupport(annotatedEle).stream()
      .map(annotation -> getSynthesizedAnnotation(annotationType, new Annotation[] { annotation })).filter(Objects::nonNull)
      .findFirst()
      .orElse(null);
  }
  
  public static <T extends Annotation> List<T> getAllSynthesizedAnnotations(AnnotatedElement annotatedEle, Class<T> annotationType) {
    return (List<T>)AnnotationScanner.DIRECTLY
      .getAnnotationsIfSupport(annotatedEle).stream()
      .map(annotation -> getSynthesizedAnnotation(annotationType, new Annotation[] { annotation })).filter(Objects::nonNull)
      .collect(Collectors.toList());
  }
  
  public static SynthesizedAggregateAnnotation aggregatingFromAnnotation(Annotation... annotations) {
    return new GenericSynthesizedAggregateAnnotation(Arrays.asList(annotations), AnnotationScanner.NOTHING);
  }
  
  public static SynthesizedAggregateAnnotation aggregatingFromAnnotationWithMeta(Annotation... annotations) {
    return new GenericSynthesizedAggregateAnnotation(Arrays.asList(annotations), AnnotationScanner.DIRECTLY_AND_META_ANNOTATION);
  }
  
  static boolean isAttributeMethod(Method method) {
    return (method.getParameterCount() == 0 && method.getReturnType() != void.class);
  }
}
