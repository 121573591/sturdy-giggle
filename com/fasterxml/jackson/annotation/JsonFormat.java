package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonFormat {
  public static final String DEFAULT_LOCALE = "##default";
  
  public static final String DEFAULT_TIMEZONE = "##default";
  
  String pattern() default "";
  
  Shape shape() default Shape.ANY;
  
  String locale() default "##default";
  
  String timezone() default "##default";
  
  OptBoolean lenient() default OptBoolean.DEFAULT;
  
  Feature[] with() default {};
  
  Feature[] without() default {};
  
  public enum Shape {
    ANY, NATURAL, SCALAR, ARRAY, OBJECT, NUMBER, NUMBER_FLOAT, NUMBER_INT, STRING, BOOLEAN, BINARY;
    
    public boolean isNumeric() {
      return (this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT);
    }
    
    public boolean isStructured() {
      return (this == OBJECT || this == ARRAY);
    }
  }
  
  public enum Feature {
    ACCEPT_SINGLE_VALUE_AS_ARRAY, ACCEPT_CASE_INSENSITIVE_PROPERTIES, READ_UNKNOWN_ENUM_VALUES_AS_NULL, READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, READ_DATE_TIMESTAMPS_AS_NANOSECONDS, ACCEPT_CASE_INSENSITIVE_VALUES, WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, WRITE_DATES_WITH_ZONE_ID, WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, WRITE_SORTED_MAP_ENTRIES, ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
  }
  
  public static class Features {
    private final int _enabled;
    
    private final int _disabled;
    
    private static final Features EMPTY = new Features(0, 0);
    
    private Features(int e, int d) {
      this._enabled = e;
      this._disabled = d;
    }
    
    public static Features empty() {
      return EMPTY;
    }
    
    public static Features construct(JsonFormat f) {
      return construct(f.with(), f.without());
    }
    
    public static Features construct(JsonFormat.Feature[] enabled, JsonFormat.Feature[] disabled) {
      int e = 0;
      for (JsonFormat.Feature f : enabled)
        e |= 1 << f.ordinal(); 
      int d = 0;
      for (JsonFormat.Feature f : disabled)
        d |= 1 << f.ordinal(); 
      return new Features(e, d);
    }
    
    public Features withOverrides(Features overrides) {
      if (overrides == null)
        return this; 
      int overrideD = overrides._disabled;
      int overrideE = overrides._enabled;
      if (overrideD == 0 && overrideE == 0)
        return this; 
      if (this._enabled == 0 && this._disabled == 0)
        return overrides; 
      int newE = this._enabled & (overrideD ^ 0xFFFFFFFF) | overrideE;
      int newD = this._disabled & (overrideE ^ 0xFFFFFFFF) | overrideD;
      if (newE == this._enabled && newD == this._disabled)
        return this; 
      return new Features(newE, newD);
    }
    
    public Features with(JsonFormat.Feature... features) {
      int e = this._enabled;
      for (JsonFormat.Feature f : features)
        e |= 1 << f.ordinal(); 
      return (e == this._enabled) ? this : new Features(e, this._disabled);
    }
    
    public Features without(JsonFormat.Feature... features) {
      int d = this._disabled;
      for (JsonFormat.Feature f : features)
        d |= 1 << f.ordinal(); 
      return (d == this._disabled) ? this : new Features(this._enabled, d);
    }
    
    public Boolean get(JsonFormat.Feature f) {
      int mask = 1 << f.ordinal();
      if ((this._disabled & mask) != 0)
        return Boolean.FALSE; 
      if ((this._enabled & mask) != 0)
        return Boolean.TRUE; 
      return null;
    }
    
    public String toString() {
      if (this == EMPTY)
        return "EMPTY"; 
      return String.format("(enabled=0x%x,disabled=0x%x)", new Object[] { Integer.valueOf(this._enabled), Integer.valueOf(this._disabled) });
    }
    
    public int hashCode() {
      return this._disabled + this._enabled;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (o == null)
        return false; 
      if (o.getClass() != getClass())
        return false; 
      Features other = (Features)o;
      return (other._enabled == this._enabled && other._disabled == this._disabled);
    }
  }
  
  public static class Value implements JacksonAnnotationValue<JsonFormat>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final Value EMPTY = new Value();
    
    private final String _pattern;
    
    private final JsonFormat.Shape _shape;
    
    private final Locale _locale;
    
    private final String _timezoneStr;
    
    private final Boolean _lenient;
    
    private final JsonFormat.Features _features;
    
    private transient TimeZone _timezone;
    
    public Value() {
      this("", JsonFormat.Shape.ANY, "", "", JsonFormat.Features.empty(), (Boolean)null);
    }
    
    public Value(JsonFormat ann) {
      this(ann.pattern(), ann.shape(), ann.locale(), ann.timezone(), 
          JsonFormat.Features.construct(ann), ann.lenient().asBoolean());
    }
    
    public Value(String p, JsonFormat.Shape sh, String localeStr, String tzStr, JsonFormat.Features f, Boolean lenient) {
      this(p, sh, (localeStr == null || localeStr
          .length() == 0 || "##default".equals(localeStr)) ? null : new Locale(localeStr), (tzStr == null || tzStr
          
          .length() == 0 || "##default".equals(tzStr)) ? null : tzStr, null, f, lenient);
    }
    
    public Value(String p, JsonFormat.Shape sh, Locale l, TimeZone tz, JsonFormat.Features f, Boolean lenient) {
      this._pattern = (p == null) ? "" : p;
      this._shape = (sh == null) ? JsonFormat.Shape.ANY : sh;
      this._locale = l;
      this._timezone = tz;
      this._timezoneStr = null;
      this._features = (f == null) ? JsonFormat.Features.empty() : f;
      this._lenient = lenient;
    }
    
    public Value(String p, JsonFormat.Shape sh, Locale l, String tzStr, TimeZone tz, JsonFormat.Features f, Boolean lenient) {
      this._pattern = (p == null) ? "" : p;
      this._shape = (sh == null) ? JsonFormat.Shape.ANY : sh;
      this._locale = l;
      this._timezone = tz;
      this._timezoneStr = tzStr;
      this._features = (f == null) ? JsonFormat.Features.empty() : f;
      this._lenient = lenient;
    }
    
    @Deprecated
    public Value(String p, JsonFormat.Shape sh, Locale l, String tzStr, TimeZone tz, JsonFormat.Features f) {
      this(p, sh, l, tzStr, tz, f, null);
    }
    
    @Deprecated
    public Value(String p, JsonFormat.Shape sh, String localeStr, String tzStr, JsonFormat.Features f) {
      this(p, sh, localeStr, tzStr, f, (Boolean)null);
    }
    
    @Deprecated
    public Value(String p, JsonFormat.Shape sh, Locale l, TimeZone tz, JsonFormat.Features f) {
      this(p, sh, l, tz, f, (Boolean)null);
    }
    
    public static final Value empty() {
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
    
    public static final Value from(JsonFormat ann) {
      return (ann == null) ? EMPTY : new Value(ann);
    }
    
    public final Value withOverrides(Value overrides) {
      TimeZone tz;
      if (overrides == null || overrides == EMPTY || overrides == this)
        return this; 
      if (this == EMPTY)
        return overrides; 
      String p = overrides._pattern;
      if (p == null || p.isEmpty())
        p = this._pattern; 
      JsonFormat.Shape sh = overrides._shape;
      if (sh == JsonFormat.Shape.ANY)
        sh = this._shape; 
      Locale l = overrides._locale;
      if (l == null)
        l = this._locale; 
      JsonFormat.Features f = this._features;
      if (f == null) {
        f = overrides._features;
      } else {
        f = f.withOverrides(overrides._features);
      } 
      Boolean lenient = overrides._lenient;
      if (lenient == null)
        lenient = this._lenient; 
      String tzStr = overrides._timezoneStr;
      if (tzStr == null || tzStr.isEmpty()) {
        tzStr = this._timezoneStr;
        tz = this._timezone;
      } else {
        tz = overrides._timezone;
      } 
      return new Value(p, sh, l, tzStr, tz, f, lenient);
    }
    
    public static Value forPattern(String p) {
      return new Value(p, null, null, null, null, JsonFormat.Features.empty(), null);
    }
    
    public static Value forShape(JsonFormat.Shape sh) {
      return new Value("", sh, null, null, null, JsonFormat.Features.empty(), null);
    }
    
    public static Value forLeniency(boolean lenient) {
      return new Value("", null, null, null, null, JsonFormat.Features.empty(), 
          Boolean.valueOf(lenient));
    }
    
    public Value withPattern(String p) {
      return new Value(p, this._shape, this._locale, this._timezoneStr, this._timezone, this._features, this._lenient);
    }
    
    public Value withShape(JsonFormat.Shape s) {
      if (s == this._shape)
        return this; 
      return new Value(this._pattern, s, this._locale, this._timezoneStr, this._timezone, this._features, this._lenient);
    }
    
    public Value withLocale(Locale l) {
      return new Value(this._pattern, this._shape, l, this._timezoneStr, this._timezone, this._features, this._lenient);
    }
    
    public Value withTimeZone(TimeZone tz) {
      return new Value(this._pattern, this._shape, this._locale, null, tz, this._features, this._lenient);
    }
    
    public Value withLenient(Boolean lenient) {
      if (lenient == this._lenient)
        return this; 
      return new Value(this._pattern, this._shape, this._locale, this._timezoneStr, this._timezone, this._features, lenient);
    }
    
    public Value withFeature(JsonFormat.Feature f) {
      JsonFormat.Features newFeats = this._features.with(new JsonFormat.Feature[] { f });
      return (newFeats == this._features) ? this : new Value(this._pattern, this._shape, this._locale, this._timezoneStr, this._timezone, newFeats, this._lenient);
    }
    
    public Value withoutFeature(JsonFormat.Feature f) {
      JsonFormat.Features newFeats = this._features.without(new JsonFormat.Feature[] { f });
      return (newFeats == this._features) ? this : new Value(this._pattern, this._shape, this._locale, this._timezoneStr, this._timezone, newFeats, this._lenient);
    }
    
    public Class<JsonFormat> valueFor() {
      return JsonFormat.class;
    }
    
    public String getPattern() {
      return this._pattern;
    }
    
    public JsonFormat.Shape getShape() {
      return this._shape;
    }
    
    public Locale getLocale() {
      return this._locale;
    }
    
    public Boolean getLenient() {
      return this._lenient;
    }
    
    public boolean isLenient() {
      return Boolean.TRUE.equals(this._lenient);
    }
    
    public String timeZoneAsString() {
      if (this._timezone != null)
        return this._timezone.getID(); 
      return this._timezoneStr;
    }
    
    public TimeZone getTimeZone() {
      TimeZone tz = this._timezone;
      if (tz == null) {
        if (this._timezoneStr == null)
          return null; 
        tz = TimeZone.getTimeZone(this._timezoneStr);
        this._timezone = tz;
      } 
      return tz;
    }
    
    public boolean hasShape() {
      return (this._shape != JsonFormat.Shape.ANY);
    }
    
    public boolean hasPattern() {
      return (this._pattern != null && this._pattern.length() > 0);
    }
    
    public boolean hasLocale() {
      return (this._locale != null);
    }
    
    public boolean hasTimeZone() {
      return (this._timezone != null || (this._timezoneStr != null && !this._timezoneStr.isEmpty()));
    }
    
    public boolean hasLenient() {
      return (this._lenient != null);
    }
    
    public Boolean getFeature(JsonFormat.Feature f) {
      return this._features.get(f);
    }
    
    public JsonFormat.Features getFeatures() {
      return this._features;
    }
    
    public String toString() {
      return String.format("JsonFormat.Value(pattern=%s,shape=%s,lenient=%s,locale=%s,timezone=%s,features=%s)", new Object[] { this._pattern, this._shape, this._lenient, this._locale, this._timezoneStr, this._features });
    }
    
    public int hashCode() {
      int hash = (this._timezoneStr == null) ? 1 : this._timezoneStr.hashCode();
      if (this._pattern != null)
        hash ^= this._pattern.hashCode(); 
      hash += this._shape.hashCode();
      if (this._lenient != null)
        hash ^= this._lenient.hashCode(); 
      if (this._locale != null)
        hash += this._locale.hashCode(); 
      hash ^= this._features.hashCode();
      return hash;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (o == null)
        return false; 
      if (o.getClass() != getClass())
        return false; 
      Value other = (Value)o;
      if (this._shape != other._shape || 
        !this._features.equals(other._features))
        return false; 
      return (_equal(this._lenient, other._lenient) && 
        _equal(this._timezoneStr, other._timezoneStr) && 
        _equal(this._pattern, other._pattern) && 
        _equal(this._timezone, other._timezone) && 
        _equal(this._locale, other._locale));
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
