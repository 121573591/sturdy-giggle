package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonTypeInfo {
  Id use();
  
  As include() default As.PROPERTY;
  
  String property() default "";
  
  Class<?> defaultImpl() default JsonTypeInfo.class;
  
  boolean visible() default false;
  
  OptBoolean requireTypeIdForSubtypes() default OptBoolean.DEFAULT;
  
  public enum Id {
    NONE(null),
    CLASS("@class"),
    MINIMAL_CLASS("@c"),
    NAME("@type"),
    SIMPLE_NAME("@type"),
    DEDUCTION(null),
    CUSTOM(null);
    
    private final String _defaultPropertyName;
    
    Id(String defProp) {
      this._defaultPropertyName = defProp;
    }
    
    public String getDefaultPropertyName() {
      return this._defaultPropertyName;
    }
  }
  
  public enum As {
    PROPERTY, WRAPPER_OBJECT, WRAPPER_ARRAY, EXTERNAL_PROPERTY, EXISTING_PROPERTY;
  }
  
  @Deprecated
  public static abstract class None {}
  
  public static class Value implements JacksonAnnotationValue<JsonTypeInfo>, Serializable {
    private static final long serialVersionUID = 1L;
    
    protected static final Value EMPTY = new Value(JsonTypeInfo.Id.NONE, JsonTypeInfo.As.PROPERTY, null, null, false, null);
    
    protected final JsonTypeInfo.Id _idType;
    
    protected final JsonTypeInfo.As _inclusionType;
    
    protected final String _propertyName;
    
    protected final Class<?> _defaultImpl;
    
    protected final boolean _idVisible;
    
    protected final Boolean _requireTypeIdForSubtypes;
    
    protected Value(JsonTypeInfo.Id idType, JsonTypeInfo.As inclusionType, String propertyName, Class<?> defaultImpl, boolean idVisible, Boolean requireTypeIdForSubtypes) {
      this._defaultImpl = defaultImpl;
      this._idType = idType;
      this._inclusionType = inclusionType;
      this._propertyName = propertyName;
      this._idVisible = idVisible;
      this._requireTypeIdForSubtypes = requireTypeIdForSubtypes;
    }
    
    public static Value construct(JsonTypeInfo.Id idType, JsonTypeInfo.As inclusionType, String propertyName, Class<?> defaultImpl, boolean idVisible, Boolean requireTypeIdForSubtypes) {
      if (propertyName == null || propertyName.isEmpty())
        if (idType != null) {
          propertyName = idType.getDefaultPropertyName();
        } else {
          propertyName = "";
        }  
      if (defaultImpl == null || defaultImpl.isAnnotation())
        defaultImpl = null; 
      return new Value(idType, inclusionType, propertyName, defaultImpl, idVisible, requireTypeIdForSubtypes);
    }
    
    public static Value from(JsonTypeInfo src) {
      if (src == null)
        return null; 
      return construct(src.use(), src.include(), src
          .property(), src.defaultImpl(), src.visible(), src.requireTypeIdForSubtypes().asBoolean());
    }
    
    public Value withDefaultImpl(Class<?> impl) {
      return (impl == this._defaultImpl) ? this : new Value(this._idType, this._inclusionType, this._propertyName, impl, this._idVisible, this._requireTypeIdForSubtypes);
    }
    
    public Value withIdType(JsonTypeInfo.Id idType) {
      return (idType == this._idType) ? this : new Value(idType, this._inclusionType, this._propertyName, this._defaultImpl, this._idVisible, this._requireTypeIdForSubtypes);
    }
    
    public Value withInclusionType(JsonTypeInfo.As inclusionType) {
      return (inclusionType == this._inclusionType) ? this : new Value(this._idType, inclusionType, this._propertyName, this._defaultImpl, this._idVisible, this._requireTypeIdForSubtypes);
    }
    
    public Value withPropertyName(String propName) {
      return (propName == this._propertyName) ? this : new Value(this._idType, this._inclusionType, propName, this._defaultImpl, this._idVisible, this._requireTypeIdForSubtypes);
    }
    
    public Value withIdVisible(boolean visible) {
      return (visible == this._idVisible) ? this : new Value(this._idType, this._inclusionType, this._propertyName, this._defaultImpl, visible, this._requireTypeIdForSubtypes);
    }
    
    public Value withRequireTypeIdForSubtypes(Boolean requireTypeIdForSubtypes) {
      return (this._requireTypeIdForSubtypes == requireTypeIdForSubtypes) ? this : new Value(this._idType, this._inclusionType, this._propertyName, this._defaultImpl, this._idVisible, requireTypeIdForSubtypes);
    }
    
    public Class<JsonTypeInfo> valueFor() {
      return JsonTypeInfo.class;
    }
    
    public Class<?> getDefaultImpl() {
      return this._defaultImpl;
    }
    
    public JsonTypeInfo.Id getIdType() {
      return this._idType;
    }
    
    public JsonTypeInfo.As getInclusionType() {
      return this._inclusionType;
    }
    
    public String getPropertyName() {
      return this._propertyName;
    }
    
    public boolean getIdVisible() {
      return this._idVisible;
    }
    
    public Boolean getRequireTypeIdForSubtypes() {
      return this._requireTypeIdForSubtypes;
    }
    
    public static boolean isEnabled(Value v) {
      return (v != null && v._idType != null && v._idType != JsonTypeInfo.Id.NONE);
    }
    
    public String toString() {
      return String.format("JsonTypeInfo.Value(idType=%s,includeAs=%s,propertyName=%s,defaultImpl=%s,idVisible=%s,requireTypeIdForSubtypes=%s)", new Object[] { this._idType, this._inclusionType, this._propertyName, (this._defaultImpl == null) ? "NULL" : this._defaultImpl
            
            .getName(), 
            Boolean.valueOf(this._idVisible), this._requireTypeIdForSubtypes });
    }
    
    public int hashCode() {
      int hashCode = 1;
      hashCode = 31 * hashCode + ((this._idType != null) ? this._idType.hashCode() : 0);
      hashCode = 31 * hashCode + ((this._inclusionType != null) ? this._inclusionType.hashCode() : 0);
      hashCode = 31 * hashCode + ((this._propertyName != null) ? this._propertyName.hashCode() : 0);
      hashCode = 31 * hashCode + ((this._defaultImpl != null) ? this._defaultImpl.hashCode() : 0);
      hashCode = 31 * hashCode + (this._requireTypeIdForSubtypes.booleanValue() ? 11 : -17);
      hashCode = 31 * hashCode + (this._idVisible ? 11 : -17);
      return hashCode;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (o == null)
        return false; 
      return (o.getClass() == getClass() && 
        _equals(this, (Value)o));
    }
    
    private static boolean _equals(Value a, Value b) {
      return (a._idType == b._idType && a._inclusionType == b._inclusionType && a._defaultImpl == b._defaultImpl && a._idVisible == b._idVisible && 
        
        _equal(a._propertyName, b._propertyName) && 
        _equal(a._requireTypeIdForSubtypes, b._requireTypeIdForSubtypes));
    }
    
    private static <T> boolean _equal(T value1, T value2) {
      if (value1 == null)
        return (value2 == null); 
      if (value2 == null)
        return false; 
      return value1.equals(value2);
    }
  }
}
