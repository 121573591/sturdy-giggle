package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonIgnoreProperties {
  String[] value() default {};
  
  boolean ignoreUnknown() default false;
  
  boolean allowGetters() default false;
  
  boolean allowSetters() default false;
  
  public static class Value implements JacksonAnnotationValue<JsonIgnoreProperties>, Serializable {
    private static final long serialVersionUID = 1L;
    
    protected static final Value EMPTY = new Value(Collections.emptySet(), false, false, false, true);
    
    protected final Set<String> _ignored;
    
    protected final boolean _ignoreUnknown;
    
    protected final boolean _allowGetters;
    
    protected final boolean _allowSetters;
    
    protected final boolean _merge;
    
    protected Value(Set<String> ignored, boolean ignoreUnknown, boolean allowGetters, boolean allowSetters, boolean merge) {
      if (ignored == null) {
        this._ignored = Collections.emptySet();
      } else {
        this._ignored = ignored;
      } 
      this._ignoreUnknown = ignoreUnknown;
      this._allowGetters = allowGetters;
      this._allowSetters = allowSetters;
      this._merge = merge;
    }
    
    public static Value from(JsonIgnoreProperties src) {
      if (src == null)
        return EMPTY; 
      return construct(_asSet(src.value()), src
          .ignoreUnknown(), src.allowGetters(), src.allowSetters(), false);
    }
    
    public static Value construct(Set<String> ignored, boolean ignoreUnknown, boolean allowGetters, boolean allowSetters, boolean merge) {
      if (_empty(ignored, ignoreUnknown, allowGetters, allowSetters, merge))
        return EMPTY; 
      return new Value(ignored, ignoreUnknown, allowGetters, allowSetters, merge);
    }
    
    public static Value empty() {
      return EMPTY;
    }
    
    public static Value merge(Value base, Value overrides) {
      return (base == null) ? overrides : base
        .withOverrides(overrides);
    }
    
    public static Value mergeAll(Value... values) {
      Value result = null;
      for (Value curr : values) {
        if (curr != null)
          result = (result == null) ? curr : result.withOverrides(curr); 
      } 
      return result;
    }
    
    public static Value forIgnoredProperties(Set<String> propNames) {
      return EMPTY.withIgnored(propNames);
    }
    
    public static Value forIgnoredProperties(String... propNames) {
      if (propNames.length == 0)
        return EMPTY; 
      return EMPTY.withIgnored(_asSet(propNames));
    }
    
    public static Value forIgnoreUnknown(boolean state) {
      return state ? EMPTY.withIgnoreUnknown() : EMPTY
        .withoutIgnoreUnknown();
    }
    
    public Value withOverrides(Value overrides) {
      if (overrides == null || overrides == EMPTY)
        return this; 
      if (!overrides._merge)
        return overrides; 
      if (_equals(this, overrides))
        return this; 
      Set<String> ignored = _merge(this._ignored, overrides._ignored);
      boolean ignoreUnknown = (this._ignoreUnknown || overrides._ignoreUnknown);
      boolean allowGetters = (this._allowGetters || overrides._allowGetters);
      boolean allowSetters = (this._allowSetters || overrides._allowSetters);
      return construct(ignored, ignoreUnknown, allowGetters, allowSetters, true);
    }
    
    public Value withIgnored(Set<String> ignored) {
      return construct(ignored, this._ignoreUnknown, this._allowGetters, this._allowSetters, this._merge);
    }
    
    public Value withIgnored(String... ignored) {
      return construct(_asSet(ignored), this._ignoreUnknown, this._allowGetters, this._allowSetters, this._merge);
    }
    
    public Value withoutIgnored() {
      return construct(null, this._ignoreUnknown, this._allowGetters, this._allowSetters, this._merge);
    }
    
    public Value withIgnoreUnknown() {
      return this._ignoreUnknown ? this : 
        construct(this._ignored, true, this._allowGetters, this._allowSetters, this._merge);
    }
    
    public Value withoutIgnoreUnknown() {
      return !this._ignoreUnknown ? this : 
        construct(this._ignored, false, this._allowGetters, this._allowSetters, this._merge);
    }
    
    public Value withAllowGetters() {
      return this._allowGetters ? this : 
        construct(this._ignored, this._ignoreUnknown, true, this._allowSetters, this._merge);
    }
    
    public Value withoutAllowGetters() {
      return !this._allowGetters ? this : 
        construct(this._ignored, this._ignoreUnknown, false, this._allowSetters, this._merge);
    }
    
    public Value withAllowSetters() {
      return this._allowSetters ? this : 
        construct(this._ignored, this._ignoreUnknown, this._allowGetters, true, this._merge);
    }
    
    public Value withoutAllowSetters() {
      return !this._allowSetters ? this : 
        construct(this._ignored, this._ignoreUnknown, this._allowGetters, false, this._merge);
    }
    
    public Value withMerge() {
      return this._merge ? this : 
        construct(this._ignored, this._ignoreUnknown, this._allowGetters, this._allowSetters, true);
    }
    
    public Value withoutMerge() {
      return !this._merge ? this : 
        construct(this._ignored, this._ignoreUnknown, this._allowGetters, this._allowSetters, false);
    }
    
    public Class<JsonIgnoreProperties> valueFor() {
      return JsonIgnoreProperties.class;
    }
    
    protected Object readResolve() {
      if (_empty(this._ignored, this._ignoreUnknown, this._allowGetters, this._allowSetters, this._merge))
        return EMPTY; 
      return this;
    }
    
    public Set<String> getIgnored() {
      return this._ignored;
    }
    
    public Set<String> findIgnoredForSerialization() {
      if (this._allowGetters)
        return Collections.emptySet(); 
      return this._ignored;
    }
    
    public Set<String> findIgnoredForDeserialization() {
      if (this._allowSetters)
        return Collections.emptySet(); 
      return this._ignored;
    }
    
    public boolean getIgnoreUnknown() {
      return this._ignoreUnknown;
    }
    
    public boolean getAllowGetters() {
      return this._allowGetters;
    }
    
    public boolean getAllowSetters() {
      return this._allowSetters;
    }
    
    public boolean getMerge() {
      return this._merge;
    }
    
    public String toString() {
      return String.format("JsonIgnoreProperties.Value(ignored=%s,ignoreUnknown=%s,allowGetters=%s,allowSetters=%s,merge=%s)", new Object[] { this._ignored, 
            Boolean.valueOf(this._ignoreUnknown), Boolean.valueOf(this._allowGetters), Boolean.valueOf(this._allowSetters), Boolean.valueOf(this._merge) });
    }
    
    public int hashCode() {
      return this._ignored.size() + (this._ignoreUnknown ? 1 : -3) + (this._allowGetters ? 3 : -7) + (this._allowSetters ? 7 : -11) + (this._merge ? 11 : -13);
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
      return (a._ignoreUnknown == b._ignoreUnknown && a._merge == b._merge && a._allowGetters == b._allowGetters && a._allowSetters == b._allowSetters && a._ignored
        
        .equals(b._ignored));
    }
    
    private static Set<String> _asSet(String[] v) {
      if (v == null || v.length == 0)
        return Collections.emptySet(); 
      Set<String> s = new HashSet<String>(v.length);
      for (String str : v)
        s.add(str); 
      return s;
    }
    
    private static Set<String> _merge(Set<String> s1, Set<String> s2) {
      if (s1.isEmpty())
        return s2; 
      if (s2.isEmpty())
        return s1; 
      HashSet<String> result = new HashSet<String>(s1.size() + s2.size());
      result.addAll(s1);
      result.addAll(s2);
      return result;
    }
    
    private static boolean _empty(Set<String> ignored, boolean ignoreUnknown, boolean allowGetters, boolean allowSetters, boolean merge) {
      if (ignoreUnknown == EMPTY._ignoreUnknown && allowGetters == EMPTY._allowGetters && allowSetters == EMPTY._allowSetters && merge == EMPTY._merge)
        return (ignored == null || ignored.size() == 0); 
      return false;
    }
  }
}
