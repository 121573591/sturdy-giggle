package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;

class CollectorBase {
  protected static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
  
  protected static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
  
  protected final AnnotationIntrospector _intr;
  
  protected CollectorBase(AnnotationIntrospector intr) {
    this._intr = intr;
  }
  
  protected final AnnotationCollector collectAnnotations(Annotation[] anns) {
    AnnotationCollector c = AnnotationCollector.emptyCollector();
    for (int i = 0, end = anns.length; i < end; i++) {
      Annotation ann = anns[i];
      c = c.addOrOverride(ann);
      if (this._intr.isAnnotationBundle(ann))
        c = collectFromBundle(c, ann); 
    } 
    return c;
  }
  
  protected final AnnotationCollector collectAnnotations(AnnotationCollector c, Annotation[] anns) {
    for (int i = 0, end = anns.length; i < end; i++) {
      Annotation ann = anns[i];
      c = c.addOrOverride(ann);
      if (this._intr.isAnnotationBundle(ann))
        c = collectFromBundle(c, ann); 
    } 
    return c;
  }
  
  protected final AnnotationCollector collectFromBundle(AnnotationCollector c, Annotation bundle) {
    Annotation[] anns = ClassUtil.findClassAnnotations(bundle.annotationType());
    for (int i = 0, end = anns.length; i < end; i++) {
      Annotation ann = anns[i];
      if (!_ignorableAnnotation(ann))
        if (this._intr.isAnnotationBundle(ann)) {
          if (!c.isPresent(ann)) {
            c = c.addOrOverride(ann);
            c = collectFromBundle(c, ann);
          } 
        } else {
          c = c.addOrOverride(ann);
        }  
    } 
    return c;
  }
  
  protected final AnnotationCollector collectDefaultAnnotations(AnnotationCollector c, Annotation[] anns) {
    for (int i = 0, end = anns.length; i < end; i++) {
      Annotation ann = anns[i];
      if (!c.isPresent(ann)) {
        c = c.addOrOverride(ann);
        if (this._intr.isAnnotationBundle(ann))
          c = collectDefaultFromBundle(c, ann); 
      } 
    } 
    return c;
  }
  
  protected final AnnotationCollector collectDefaultFromBundle(AnnotationCollector c, Annotation bundle) {
    Annotation[] anns = ClassUtil.findClassAnnotations(bundle.annotationType());
    for (int i = 0, end = anns.length; i < end; i++) {
      Annotation ann = anns[i];
      if (!_ignorableAnnotation(ann))
        if (!c.isPresent(ann)) {
          c = c.addOrOverride(ann);
          if (this._intr.isAnnotationBundle(ann))
            c = collectFromBundle(c, ann); 
        }  
    } 
    return c;
  }
  
  protected static final boolean _ignorableAnnotation(Annotation a) {
    return (a instanceof java.lang.annotation.Target || a instanceof java.lang.annotation.Retention);
  }
  
  static AnnotationMap _emptyAnnotationMap() {
    return new AnnotationMap();
  }
  
  static AnnotationMap[] _emptyAnnotationMaps(int count) {
    if (count == 0)
      return NO_ANNOTATION_MAPS; 
    AnnotationMap[] maps = new AnnotationMap[count];
    for (int i = 0; i < count; i++)
      maps[i] = _emptyAnnotationMap(); 
    return maps;
  }
}
