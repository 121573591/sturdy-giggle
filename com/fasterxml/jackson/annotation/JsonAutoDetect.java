package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonAutoDetect {
  Visibility getterVisibility() default Visibility.DEFAULT;
  
  Visibility isGetterVisibility() default Visibility.DEFAULT;
  
  Visibility setterVisibility() default Visibility.DEFAULT;
  
  Visibility creatorVisibility() default Visibility.DEFAULT;
  
  Visibility fieldVisibility() default Visibility.DEFAULT;
  
  public enum Visibility {
    ANY, NON_PRIVATE, PROTECTED_AND_PUBLIC, PUBLIC_ONLY, NONE, DEFAULT;
    
    public boolean isVisible(Member m) {
      switch (this) {
        case CREATOR:
          return true;
        case FIELD:
          return false;
        case GETTER:
          return !Modifier.isPrivate(m.getModifiers());
        case IS_GETTER:
          if (Modifier.isProtected(m.getModifiers()))
            return true; 
        case NONE:
          return Modifier.isPublic(m.getModifiers());
      } 
      return false;
    }
  }
  
  public static class Value implements JacksonAnnotationValue<JsonAutoDetect>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final JsonAutoDetect.Visibility DEFAULT_FIELD_VISIBILITY = JsonAutoDetect.Visibility.PUBLIC_ONLY;
    
    protected static final Value DEFAULT = new Value(DEFAULT_FIELD_VISIBILITY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.PUBLIC_ONLY, JsonAutoDetect.Visibility.ANY, JsonAutoDetect.Visibility.PUBLIC_ONLY);
    
    protected static final Value NO_OVERRIDES = new Value(JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT);
    
    protected final JsonAutoDetect.Visibility _fieldVisibility;
    
    protected final JsonAutoDetect.Visibility _getterVisibility;
    
    protected final JsonAutoDetect.Visibility _isGetterVisibility;
    
    protected final JsonAutoDetect.Visibility _setterVisibility;
    
    protected final JsonAutoDetect.Visibility _creatorVisibility;
    
    private Value(JsonAutoDetect.Visibility fields, JsonAutoDetect.Visibility getters, JsonAutoDetect.Visibility isGetters, JsonAutoDetect.Visibility setters, JsonAutoDetect.Visibility creators) {
      this._fieldVisibility = fields;
      this._getterVisibility = getters;
      this._isGetterVisibility = isGetters;
      this._setterVisibility = setters;
      this._creatorVisibility = creators;
    }
    
    public static Value defaultVisibility() {
      return DEFAULT;
    }
    
    public static Value noOverrides() {
      return NO_OVERRIDES;
    }
    
    public static Value from(JsonAutoDetect src) {
      return construct(src.fieldVisibility(), src
          .getterVisibility(), src.isGetterVisibility(), src.setterVisibility(), src
          .creatorVisibility());
    }
    
    public static Value construct(PropertyAccessor acc, JsonAutoDetect.Visibility visibility) {
      JsonAutoDetect.Visibility fields = JsonAutoDetect.Visibility.DEFAULT;
      JsonAutoDetect.Visibility getters = JsonAutoDetect.Visibility.DEFAULT;
      JsonAutoDetect.Visibility isGetters = JsonAutoDetect.Visibility.DEFAULT;
      JsonAutoDetect.Visibility setters = JsonAutoDetect.Visibility.DEFAULT;
      JsonAutoDetect.Visibility creators = JsonAutoDetect.Visibility.DEFAULT;
      switch (acc) {
        case CREATOR:
          creators = visibility;
          break;
        case FIELD:
          fields = visibility;
          break;
        case GETTER:
          getters = visibility;
          break;
        case IS_GETTER:
          isGetters = visibility;
          break;
        case SETTER:
          setters = visibility;
          break;
        case ALL:
          fields = getters = isGetters = setters = creators = visibility;
          break;
      } 
      return construct(fields, getters, isGetters, setters, creators);
    }
    
    public static Value construct(JsonAutoDetect.Visibility fields, JsonAutoDetect.Visibility getters, JsonAutoDetect.Visibility isGetters, JsonAutoDetect.Visibility setters, JsonAutoDetect.Visibility creators) {
      Value v = _predefined(fields, getters, isGetters, setters, creators);
      if (v == null)
        v = new Value(fields, getters, isGetters, setters, creators); 
      return v;
    }
    
    public Value withFieldVisibility(JsonAutoDetect.Visibility v) {
      return construct(v, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility);
    }
    
    public Value withGetterVisibility(JsonAutoDetect.Visibility v) {
      return construct(this._fieldVisibility, v, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility);
    }
    
    public Value withIsGetterVisibility(JsonAutoDetect.Visibility v) {
      return construct(this._fieldVisibility, this._getterVisibility, v, this._setterVisibility, this._creatorVisibility);
    }
    
    public Value withSetterVisibility(JsonAutoDetect.Visibility v) {
      return construct(this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, v, this._creatorVisibility);
    }
    
    public Value withCreatorVisibility(JsonAutoDetect.Visibility v) {
      return construct(this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, v);
    }
    
    public static Value merge(Value base, Value overrides) {
      return (base == null) ? overrides : base
        .withOverrides(overrides);
    }
    
    public Value withOverrides(Value overrides) {
      if (overrides == null || overrides == NO_OVERRIDES || overrides == this)
        return this; 
      if (_equals(this, overrides))
        return this; 
      JsonAutoDetect.Visibility fields = overrides._fieldVisibility;
      if (fields == JsonAutoDetect.Visibility.DEFAULT)
        fields = this._fieldVisibility; 
      JsonAutoDetect.Visibility getters = overrides._getterVisibility;
      if (getters == JsonAutoDetect.Visibility.DEFAULT)
        getters = this._getterVisibility; 
      JsonAutoDetect.Visibility isGetters = overrides._isGetterVisibility;
      if (isGetters == JsonAutoDetect.Visibility.DEFAULT)
        isGetters = this._isGetterVisibility; 
      JsonAutoDetect.Visibility setters = overrides._setterVisibility;
      if (setters == JsonAutoDetect.Visibility.DEFAULT)
        setters = this._setterVisibility; 
      JsonAutoDetect.Visibility creators = overrides._creatorVisibility;
      if (creators == JsonAutoDetect.Visibility.DEFAULT)
        creators = this._creatorVisibility; 
      return construct(fields, getters, isGetters, setters, creators);
    }
    
    public Class<JsonAutoDetect> valueFor() {
      return JsonAutoDetect.class;
    }
    
    public JsonAutoDetect.Visibility getFieldVisibility() {
      return this._fieldVisibility;
    }
    
    public JsonAutoDetect.Visibility getGetterVisibility() {
      return this._getterVisibility;
    }
    
    public JsonAutoDetect.Visibility getIsGetterVisibility() {
      return this._isGetterVisibility;
    }
    
    public JsonAutoDetect.Visibility getSetterVisibility() {
      return this._setterVisibility;
    }
    
    public JsonAutoDetect.Visibility getCreatorVisibility() {
      return this._creatorVisibility;
    }
    
    protected Object readResolve() {
      Value v = _predefined(this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility);
      return (v == null) ? this : v;
    }
    
    public String toString() {
      return String.format("JsonAutoDetect.Value(fields=%s,getters=%s,isGetters=%s,setters=%s,creators=%s)", new Object[] { this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility });
    }
    
    public int hashCode() {
      return 1 + this._fieldVisibility.ordinal() ^ 3 * this._getterVisibility
        .ordinal() - 7 * this._isGetterVisibility
        .ordinal() + 11 * this._setterVisibility
        .ordinal() ^ 13 * this._creatorVisibility
        .ordinal();
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (o == null)
        return false; 
      return (o.getClass() == getClass() && _equals(this, (Value)o));
    }
    
    private static Value _predefined(JsonAutoDetect.Visibility fields, JsonAutoDetect.Visibility getters, JsonAutoDetect.Visibility isGetters, JsonAutoDetect.Visibility setters, JsonAutoDetect.Visibility creators) {
      if (fields == DEFAULT_FIELD_VISIBILITY) {
        if (getters == DEFAULT._getterVisibility && isGetters == DEFAULT._isGetterVisibility && setters == DEFAULT._setterVisibility && creators == DEFAULT._creatorVisibility)
          return DEFAULT; 
      } else if (fields == JsonAutoDetect.Visibility.DEFAULT && 
        getters == JsonAutoDetect.Visibility.DEFAULT && isGetters == JsonAutoDetect.Visibility.DEFAULT && setters == JsonAutoDetect.Visibility.DEFAULT && creators == JsonAutoDetect.Visibility.DEFAULT) {
        return NO_OVERRIDES;
      } 
      return null;
    }
    
    private static boolean _equals(Value a, Value b) {
      return (a._fieldVisibility == b._fieldVisibility && a._getterVisibility == b._getterVisibility && a._isGetterVisibility == b._isGetterVisibility && a._setterVisibility == b._setterVisibility && a._creatorVisibility == b._creatorVisibility);
    }
  }
}
