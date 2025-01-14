package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AnnotatedCreatorCollector extends CollectorBase {
  private final TypeResolutionContext _typeContext;
  
  private final boolean _collectAnnotations;
  
  private AnnotatedConstructor _defaultConstructor;
  
  AnnotatedCreatorCollector(AnnotationIntrospector intr, TypeResolutionContext tc, boolean collectAnnotations) {
    super(intr);
    this._typeContext = tc;
    this._collectAnnotations = collectAnnotations;
  }
  
  public static AnnotatedClass.Creators collectCreators(AnnotationIntrospector intr, TypeFactory typeFactory, TypeResolutionContext tc, JavaType type, Class<?> primaryMixIn, boolean collectAnnotations) {
    int i = collectAnnotations | ((primaryMixIn != null) ? 1 : 0);
    return (new AnnotatedCreatorCollector(intr, tc, i))
      .collect(typeFactory, type, primaryMixIn);
  }
  
  AnnotatedClass.Creators collect(TypeFactory typeFactory, JavaType type, Class<?> primaryMixIn) {
    List<AnnotatedConstructor> constructors = _findPotentialConstructors(type, primaryMixIn);
    List<AnnotatedMethod> factories = _findPotentialFactories(typeFactory, type, primaryMixIn);
    if (this._collectAnnotations) {
      if (this._defaultConstructor != null && 
        this._intr.hasIgnoreMarker(this._defaultConstructor))
        this._defaultConstructor = null; 
      int i;
      for (i = constructors.size(); --i >= 0;) {
        if (this._intr.hasIgnoreMarker(constructors.get(i)))
          constructors.remove(i); 
      } 
      for (i = factories.size(); --i >= 0;) {
        if (this._intr.hasIgnoreMarker(factories.get(i)))
          factories.remove(i); 
      } 
    } 
    return new AnnotatedClass.Creators(this._defaultConstructor, constructors, factories);
  }
  
  private List<AnnotatedConstructor> _findPotentialConstructors(JavaType type, Class<?> primaryMixIn) {
    List<AnnotatedConstructor> result;
    int ctorCount;
    ClassUtil.Ctor defaultCtor = null;
    List<ClassUtil.Ctor> ctors = null;
    if (!type.isEnumType()) {
      ClassUtil.Ctor[] declaredCtors = ClassUtil.getConstructors(type.getRawClass());
      for (ClassUtil.Ctor ctor : declaredCtors) {
        if (isIncludableConstructor(ctor.getConstructor()))
          if (ctor.getParamCount() == 0) {
            defaultCtor = ctor;
          } else {
            if (ctors == null)
              ctors = new ArrayList<>(); 
            ctors.add(ctor);
          }  
      } 
    } 
    if (ctors == null) {
      result = Collections.emptyList();
      if (defaultCtor == null)
        return result; 
      ctorCount = 0;
    } else {
      ctorCount = ctors.size();
      result = new ArrayList<>(ctorCount);
      for (int j = 0; j < ctorCount; j++)
        result.add(null); 
    } 
    if (primaryMixIn != null) {
      MemberKey[] ctorKeys = null;
      for (ClassUtil.Ctor mixinCtor : ClassUtil.getConstructors(primaryMixIn)) {
        if (mixinCtor.getParamCount() == 0) {
          if (defaultCtor != null) {
            this._defaultConstructor = constructDefaultConstructor(defaultCtor, mixinCtor);
            defaultCtor = null;
          } 
        } else if (ctors != null) {
          if (ctorKeys == null) {
            ctorKeys = new MemberKey[ctorCount];
            for (int k = 0; k < ctorCount; k++)
              ctorKeys[k] = new MemberKey(((ClassUtil.Ctor)ctors.get(k)).getConstructor()); 
          } 
          MemberKey key = new MemberKey(mixinCtor.getConstructor());
          for (int j = 0; j < ctorCount; j++) {
            if (key.equals(ctorKeys[j])) {
              result.set(j, 
                  constructNonDefaultConstructor(ctors.get(j), mixinCtor));
              break;
            } 
          } 
        } 
      } 
    } 
    if (defaultCtor != null)
      this._defaultConstructor = constructDefaultConstructor(defaultCtor, (ClassUtil.Ctor)null); 
    for (int i = 0; i < ctorCount; i++) {
      AnnotatedConstructor ctor = result.get(i);
      if (ctor == null)
        result.set(i, 
            constructNonDefaultConstructor(ctors.get(i), (ClassUtil.Ctor)null)); 
    } 
    return result;
  }
  
  private List<AnnotatedMethod> _findPotentialFactories(TypeFactory typeFactory, JavaType type, Class<?> primaryMixIn) {
    List<Method> candidates = null;
    for (Method m : ClassUtil.getClassMethods(type.getRawClass())) {
      if (_isIncludableFactoryMethod(m)) {
        if (candidates == null)
          candidates = new ArrayList<>(); 
        candidates.add(m);
      } 
    } 
    if (candidates == null)
      return Collections.emptyList(); 
    TypeResolutionContext initialTypeResCtxt = this._typeContext;
    int factoryCount = candidates.size();
    List<AnnotatedMethod> result = new ArrayList<>(factoryCount);
    int i;
    for (i = 0; i < factoryCount; i++)
      result.add(null); 
    if (primaryMixIn != null) {
      MemberKey[] methodKeys = null;
      for (Method mixinFactory : primaryMixIn.getDeclaredMethods()) {
        if (_isIncludableFactoryMethod(mixinFactory)) {
          if (methodKeys == null) {
            methodKeys = new MemberKey[factoryCount];
            for (int k = 0; k < factoryCount; k++)
              methodKeys[k] = new MemberKey(candidates.get(k)); 
          } 
          MemberKey key = new MemberKey(mixinFactory);
          for (int j = 0; j < factoryCount; j++) {
            if (key.equals(methodKeys[j])) {
              result.set(j, 
                  constructFactoryCreator(candidates.get(j), initialTypeResCtxt, mixinFactory));
              break;
            } 
          } 
        } 
      } 
    } 
    for (i = 0; i < factoryCount; i++) {
      AnnotatedMethod factory = result.get(i);
      if (factory == null) {
        Method candidate = candidates.get(i);
        TypeResolutionContext typeResCtxt = MethodGenericTypeResolver.narrowMethodTypeParameters(candidate, type, typeFactory, initialTypeResCtxt);
        result.set(i, 
            constructFactoryCreator(candidate, typeResCtxt, (Method)null));
      } 
    } 
    return result;
  }
  
  private static boolean _isIncludableFactoryMethod(Method m) {
    if (!Modifier.isStatic(m.getModifiers()))
      return false; 
    return !m.isSynthetic();
  }
  
  protected AnnotatedConstructor constructDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor mixin) {
    return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), 
        collectAnnotations(ctor, mixin), NO_ANNOTATION_MAPS);
  }
  
  protected AnnotatedConstructor constructNonDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor mixin) {
    AnnotationMap[] resolvedAnnotations;
    int paramCount = ctor.getParamCount();
    if (this._intr == null)
      return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), 
          _emptyAnnotationMap(), _emptyAnnotationMaps(paramCount)); 
    if (paramCount == 0)
      return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), 
          collectAnnotations(ctor, mixin), NO_ANNOTATION_MAPS); 
    Annotation[][] paramAnns = ctor.getParameterAnnotations();
    if (paramCount != paramAnns.length) {
      resolvedAnnotations = null;
      Class<?> dc = ctor.getDeclaringClass();
      if (ClassUtil.isEnumType(dc) && paramCount == paramAnns.length + 2) {
        Annotation[][] old = paramAnns;
        paramAnns = new Annotation[old.length + 2][];
        System.arraycopy(old, 0, paramAnns, 2, old.length);
        resolvedAnnotations = collectAnnotations(paramAnns, (Annotation[][])null);
      } else if (dc.isMemberClass()) {
        if (paramCount == paramAnns.length + 1) {
          Annotation[][] old = paramAnns;
          paramAnns = new Annotation[old.length + 1][];
          System.arraycopy(old, 0, paramAnns, 1, old.length);
          paramAnns[0] = NO_ANNOTATIONS;
          resolvedAnnotations = collectAnnotations(paramAnns, (Annotation[][])null);
        } 
      } 
      if (resolvedAnnotations == null)
        throw new IllegalStateException(String.format("Internal error: constructor for %s has mismatch: %d parameters; %d sets of annotations", new Object[] { ctor
                
                .getDeclaringClass().getName(), Integer.valueOf(paramCount), Integer.valueOf(paramAnns.length) })); 
    } else {
      resolvedAnnotations = collectAnnotations(paramAnns, (mixin == null) ? (Annotation[][])null : mixin
          .getParameterAnnotations());
    } 
    return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), 
        collectAnnotations(ctor, mixin), resolvedAnnotations);
  }
  
  protected AnnotatedMethod constructFactoryCreator(Method m, TypeResolutionContext typeResCtxt, Method mixin) {
    int paramCount = m.getParameterCount();
    if (this._intr == null)
      return new AnnotatedMethod(typeResCtxt, m, _emptyAnnotationMap(), 
          _emptyAnnotationMaps(paramCount)); 
    if (paramCount == 0)
      return new AnnotatedMethod(typeResCtxt, m, collectAnnotations(m, mixin), NO_ANNOTATION_MAPS); 
    return new AnnotatedMethod(typeResCtxt, m, collectAnnotations(m, mixin), 
        collectAnnotations(m.getParameterAnnotations(), (mixin == null) ? (Annotation[][])null : mixin
          .getParameterAnnotations()));
  }
  
  private AnnotationMap[] collectAnnotations(Annotation[][] mainAnns, Annotation[][] mixinAnns) {
    if (this._collectAnnotations) {
      int count = mainAnns.length;
      AnnotationMap[] result = new AnnotationMap[count];
      for (int i = 0; i < count; i++) {
        AnnotationCollector c = collectAnnotations(AnnotationCollector.emptyCollector(), mainAnns[i]);
        if (mixinAnns != null)
          c = collectAnnotations(c, mixinAnns[i]); 
        result[i] = c.asAnnotationMap();
      } 
      return result;
    } 
    return NO_ANNOTATION_MAPS;
  }
  
  private AnnotationMap collectAnnotations(ClassUtil.Ctor main, ClassUtil.Ctor mixin) {
    if (this._collectAnnotations) {
      AnnotationCollector c = collectAnnotations(main.getDeclaredAnnotations());
      if (mixin != null)
        c = collectAnnotations(c, mixin.getDeclaredAnnotations()); 
      return c.asAnnotationMap();
    } 
    return _emptyAnnotationMap();
  }
  
  private final AnnotationMap collectAnnotations(AnnotatedElement main, AnnotatedElement mixin) {
    AnnotationCollector c = collectAnnotations(main.getDeclaredAnnotations());
    if (mixin != null)
      c = collectAnnotations(c, mixin.getDeclaredAnnotations()); 
    return c.asAnnotationMap();
  }
  
  private static boolean isIncludableConstructor(Constructor<?> c) {
    return !c.isSynthetic();
  }
}
