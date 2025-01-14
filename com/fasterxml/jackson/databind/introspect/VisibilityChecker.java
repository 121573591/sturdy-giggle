package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public interface VisibilityChecker<T extends VisibilityChecker<T>> {
  T with(JsonAutoDetect paramJsonAutoDetect);
  
  T withOverrides(JsonAutoDetect.Value paramValue);
  
  T with(JsonAutoDetect.Visibility paramVisibility);
  
  T withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility);
  
  T withGetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  T withIsGetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  T withSetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  T withCreatorVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  T withFieldVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  boolean isGetterVisible(Method paramMethod);
  
  boolean isGetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  boolean isIsGetterVisible(Method paramMethod);
  
  boolean isIsGetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  boolean isSetterVisible(Method paramMethod);
  
  boolean isSetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  boolean isCreatorVisible(Member paramMember);
  
  boolean isCreatorVisible(AnnotatedMember paramAnnotatedMember);
  
  boolean isFieldVisible(Field paramField);
  
  boolean isFieldVisible(AnnotatedField paramAnnotatedField);
  
  public static class Std implements VisibilityChecker<Std>, Serializable {
    private static final long serialVersionUID = 1L;
    
    protected static final Std DEFAULT = new Std(JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.ANY, JsonAutoDetect.Visibility.ANY, JsonAutoDetect.Visibility.PUBLIC_ONLY);
    
    protected static final Std ALL_PUBLIC = new Std(JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY);
    
    protected final JsonAutoDetect.Visibility _getterMinLevel;
    
    protected final JsonAutoDetect.Visibility _isGetterMinLevel;
    
    protected final JsonAutoDetect.Visibility _setterMinLevel;
    
    protected final JsonAutoDetect.Visibility _creatorMinLevel;
    
    protected final JsonAutoDetect.Visibility _fieldMinLevel;
    
    public static Std defaultInstance() {
      return DEFAULT;
    }
    
    public static Std allPublicInstance() {
      return ALL_PUBLIC;
    }
    
    public Std(JsonAutoDetect ann) {
      this._getterMinLevel = ann.getterVisibility();
      this._isGetterMinLevel = ann.isGetterVisibility();
      this._setterMinLevel = ann.setterVisibility();
      this._creatorMinLevel = ann.creatorVisibility();
      this._fieldMinLevel = ann.fieldVisibility();
    }
    
    public Std(JsonAutoDetect.Visibility getter, JsonAutoDetect.Visibility isGetter, JsonAutoDetect.Visibility setter, JsonAutoDetect.Visibility creator, JsonAutoDetect.Visibility field) {
      this._getterMinLevel = getter;
      this._isGetterMinLevel = isGetter;
      this._setterMinLevel = setter;
      this._creatorMinLevel = creator;
      this._fieldMinLevel = field;
    }
    
    public Std(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT) {
        this._getterMinLevel = DEFAULT._getterMinLevel;
        this._isGetterMinLevel = DEFAULT._isGetterMinLevel;
        this._setterMinLevel = DEFAULT._setterMinLevel;
        this._creatorMinLevel = DEFAULT._creatorMinLevel;
        this._fieldMinLevel = DEFAULT._fieldMinLevel;
      } else {
        this._getterMinLevel = v;
        this._isGetterMinLevel = v;
        this._setterMinLevel = v;
        this._creatorMinLevel = v;
        this._fieldMinLevel = v;
      } 
    }
    
    public static Std construct(JsonAutoDetect.Value vis) {
      return DEFAULT.withOverrides(vis);
    }
    
    protected Std _with(JsonAutoDetect.Visibility g, JsonAutoDetect.Visibility isG, JsonAutoDetect.Visibility s, JsonAutoDetect.Visibility cr, JsonAutoDetect.Visibility f) {
      if (g == this._getterMinLevel && isG == this._isGetterMinLevel && s == this._setterMinLevel && cr == this._creatorMinLevel && f == this._fieldMinLevel)
        return this; 
      return new Std(g, isG, s, cr, f);
    }
    
    public Std with(JsonAutoDetect ann) {
      Std curr = this;
      if (ann != null)
        return _with(
            _defaultOrOverride(this._getterMinLevel, ann.getterVisibility()), 
            _defaultOrOverride(this._isGetterMinLevel, ann.isGetterVisibility()), 
            _defaultOrOverride(this._setterMinLevel, ann.setterVisibility()), 
            _defaultOrOverride(this._creatorMinLevel, ann.creatorVisibility()), 
            _defaultOrOverride(this._fieldMinLevel, ann.fieldVisibility())); 
      return curr;
    }
    
    public Std withOverrides(JsonAutoDetect.Value vis) {
      Std curr = this;
      if (vis != null)
        return _with(
            _defaultOrOverride(this._getterMinLevel, vis.getGetterVisibility()), 
            _defaultOrOverride(this._isGetterMinLevel, vis.getIsGetterVisibility()), 
            _defaultOrOverride(this._setterMinLevel, vis.getSetterVisibility()), 
            _defaultOrOverride(this._creatorMinLevel, vis.getCreatorVisibility()), 
            _defaultOrOverride(this._fieldMinLevel, vis.getFieldVisibility())); 
      return curr;
    }
    
    private JsonAutoDetect.Visibility _defaultOrOverride(JsonAutoDetect.Visibility defaults, JsonAutoDetect.Visibility override) {
      if (override == JsonAutoDetect.Visibility.DEFAULT)
        return defaults; 
      return override;
    }
    
    public Std with(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT)
        return DEFAULT; 
      return new Std(v);
    }
    
    public Std withVisibility(PropertyAccessor method, JsonAutoDetect.Visibility v) {
      switch (method) {
        case GETTER:
          return withGetterVisibility(v);
        case SETTER:
          return withSetterVisibility(v);
        case CREATOR:
          return withCreatorVisibility(v);
        case FIELD:
          return withFieldVisibility(v);
        case IS_GETTER:
          return withIsGetterVisibility(v);
        case ALL:
          return with(v);
      } 
      return this;
    }
    
    public Std withGetterVisibility(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT)
        v = DEFAULT._getterMinLevel; 
      if (this._getterMinLevel == v)
        return this; 
      return new Std(v, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }
    
    public Std withIsGetterVisibility(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT)
        v = DEFAULT._isGetterMinLevel; 
      if (this._isGetterMinLevel == v)
        return this; 
      return new Std(this._getterMinLevel, v, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }
    
    public Std withSetterVisibility(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT)
        v = DEFAULT._setterMinLevel; 
      if (this._setterMinLevel == v)
        return this; 
      return new Std(this._getterMinLevel, this._isGetterMinLevel, v, this._creatorMinLevel, this._fieldMinLevel);
    }
    
    public Std withCreatorVisibility(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT)
        v = DEFAULT._creatorMinLevel; 
      if (this._creatorMinLevel == v)
        return this; 
      return new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, v, this._fieldMinLevel);
    }
    
    public Std withFieldVisibility(JsonAutoDetect.Visibility v) {
      if (v == JsonAutoDetect.Visibility.DEFAULT)
        v = DEFAULT._fieldMinLevel; 
      if (this._fieldMinLevel == v)
        return this; 
      return new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, v);
    }
    
    public boolean isCreatorVisible(Member m) {
      return this._creatorMinLevel.isVisible(m);
    }
    
    public boolean isCreatorVisible(AnnotatedMember m) {
      return isCreatorVisible(m.getMember());
    }
    
    public boolean isFieldVisible(Field f) {
      return this._fieldMinLevel.isVisible(f);
    }
    
    public boolean isFieldVisible(AnnotatedField f) {
      return isFieldVisible(f.getAnnotated());
    }
    
    public boolean isGetterVisible(Method m) {
      return this._getterMinLevel.isVisible(m);
    }
    
    public boolean isGetterVisible(AnnotatedMethod m) {
      return isGetterVisible(m.getAnnotated());
    }
    
    public boolean isIsGetterVisible(Method m) {
      return this._isGetterMinLevel.isVisible(m);
    }
    
    public boolean isIsGetterVisible(AnnotatedMethod m) {
      return isIsGetterVisible(m.getAnnotated());
    }
    
    public boolean isSetterVisible(Method m) {
      return this._setterMinLevel.isVisible(m);
    }
    
    public boolean isSetterVisible(AnnotatedMethod m) {
      return isSetterVisible(m.getAnnotated());
    }
    
    public String toString() {
      return String.format("[Visibility: getter=%s,isGetter=%s,setter=%s,creator=%s,field=%s]", new Object[] { this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel });
    }
  }
}
