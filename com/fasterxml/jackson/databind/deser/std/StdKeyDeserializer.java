package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.EnumFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@JacksonStdImpl
public class StdKeyDeserializer extends KeyDeserializer implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final int TYPE_BOOLEAN = 1;
  
  public static final int TYPE_BYTE = 2;
  
  public static final int TYPE_SHORT = 3;
  
  public static final int TYPE_CHAR = 4;
  
  public static final int TYPE_INT = 5;
  
  public static final int TYPE_LONG = 6;
  
  public static final int TYPE_FLOAT = 7;
  
  public static final int TYPE_DOUBLE = 8;
  
  public static final int TYPE_LOCALE = 9;
  
  public static final int TYPE_DATE = 10;
  
  public static final int TYPE_CALENDAR = 11;
  
  public static final int TYPE_UUID = 12;
  
  public static final int TYPE_URI = 13;
  
  public static final int TYPE_URL = 14;
  
  public static final int TYPE_CLASS = 15;
  
  public static final int TYPE_CURRENCY = 16;
  
  public static final int TYPE_BYTE_ARRAY = 17;
  
  protected final int _kind;
  
  protected final Class<?> _keyClass;
  
  protected final FromStringDeserializer<?> _deser;
  
  protected StdKeyDeserializer(int kind, Class<?> cls) {
    this(kind, cls, null);
  }
  
  protected StdKeyDeserializer(int kind, Class<?> cls, FromStringDeserializer<?> deser) {
    this._kind = kind;
    this._keyClass = cls;
    this._deser = deser;
  }
  
  public static StdKeyDeserializer forType(Class<?> raw) {
    int kind;
    if (raw == String.class || raw == Object.class || raw == CharSequence.class || raw == Serializable.class)
      return StringKD.forType(raw); 
    if (raw == UUID.class) {
      kind = 12;
    } else if (raw == Integer.class) {
      kind = 5;
    } else if (raw == Long.class) {
      kind = 6;
    } else if (raw == Date.class) {
      kind = 10;
    } else if (raw == Calendar.class) {
      kind = 11;
    } else if (raw == Boolean.class) {
      kind = 1;
    } else if (raw == Byte.class) {
      kind = 2;
    } else if (raw == Character.class) {
      kind = 4;
    } else if (raw == Short.class) {
      kind = 3;
    } else if (raw == Float.class) {
      kind = 7;
    } else if (raw == Double.class) {
      kind = 8;
    } else if (raw == URI.class) {
      kind = 13;
    } else if (raw == URL.class) {
      kind = 14;
    } else if (raw == Class.class) {
      kind = 15;
    } else {
      if (raw == Locale.class) {
        FromStringDeserializer<?> deser = FromStringDeserializer.findDeserializer(Locale.class);
        return new StdKeyDeserializer(9, raw, deser);
      } 
      if (raw == Currency.class) {
        FromStringDeserializer<?> deser = FromStringDeserializer.findDeserializer(Currency.class);
        return new StdKeyDeserializer(16, raw, deser);
      } 
      if (raw == byte[].class) {
        kind = 17;
      } else {
        return null;
      } 
    } 
    return new StdKeyDeserializer(kind, raw);
  }
  
  public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
    if (key == null)
      return null; 
    try {
      Object result = _parse(key, ctxt);
      if (result != null)
        return result; 
    } catch (Exception re) {
      return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation, problem: (%s) %s", new Object[] { re
            .getClass().getName(), 
            ClassUtil.exceptionMessage(re) });
    } 
    if (ClassUtil.isEnumType(this._keyClass) && ctxt
      .getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
      return null; 
    return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation", new Object[0]);
  }
  
  public Class<?> getKeyClass() {
    return this._keyClass;
  }
  
  protected Object _parse(String key, DeserializationContext ctxt) throws Exception {
    int value;
    switch (this._kind) {
      case 1:
        if ("true".equals(key))
          return Boolean.TRUE; 
        if ("false".equals(key))
          return Boolean.FALSE; 
        return ctxt.handleWeirdKey(this._keyClass, key, "value not 'true' or 'false'", new Object[0]);
      case 2:
        value = _parseInt(key);
        if (value < -128 || value > 255)
          return ctxt.handleWeirdKey(this._keyClass, key, "overflow, value cannot be represented as 8-bit value", new Object[0]); 
        return Byte.valueOf((byte)value);
      case 3:
        value = _parseInt(key);
        if (value < -32768 || value > 32767)
          return ctxt.handleWeirdKey(this._keyClass, key, "overflow, value cannot be represented as 16-bit value", new Object[0]); 
        return Short.valueOf((short)value);
      case 4:
        if (key.length() == 1)
          return Character.valueOf(key.charAt(0)); 
        return ctxt.handleWeirdKey(this._keyClass, key, "can only convert 1-character Strings", new Object[0]);
      case 5:
        return Integer.valueOf(_parseInt(key));
      case 6:
        return Long.valueOf(_parseLong(key));
      case 7:
        return Float.valueOf((float)_parseDouble(key));
      case 8:
        return Double.valueOf(_parseDouble(key));
      case 9:
        try {
          return this._deser._deserialize(key, ctxt);
        } catch (IllegalArgumentException e) {
          return _weirdKey(ctxt, key, e);
        } 
      case 16:
        try {
          return this._deser._deserialize(key, ctxt);
        } catch (IllegalArgumentException e) {
          return _weirdKey(ctxt, key, e);
        } 
      case 10:
        return ctxt.parseDate(key);
      case 11:
        return ctxt.constructCalendar(ctxt.parseDate(key));
      case 12:
        try {
          return UUID.fromString(key);
        } catch (Exception e) {
          return _weirdKey(ctxt, key, e);
        } 
      case 13:
        try {
          return URI.create(key);
        } catch (Exception e) {
          return _weirdKey(ctxt, key, e);
        } 
      case 14:
        try {
          return new URL(key);
        } catch (MalformedURLException e) {
          return _weirdKey(ctxt, key, e);
        } 
      case 15:
        try {
          return ctxt.findClass(key);
        } catch (Exception e) {
          return ctxt.handleWeirdKey(this._keyClass, key, "unable to parse key as Class", new Object[0]);
        } 
      case 17:
        try {
          return ctxt.getConfig().getBase64Variant().decode(key);
        } catch (IllegalArgumentException e) {
          return _weirdKey(ctxt, key, e);
        } 
    } 
    throw new IllegalStateException("Internal error: unknown key type " + this._keyClass);
  }
  
  protected int _parseInt(String key) throws IllegalArgumentException {
    return NumberInput.parseInt(key);
  }
  
  protected long _parseLong(String key) throws IllegalArgumentException {
    return NumberInput.parseLong(key);
  }
  
  protected double _parseDouble(String key) throws IllegalArgumentException {
    return NumberInput.parseDouble(key);
  }
  
  protected Object _weirdKey(DeserializationContext ctxt, String key, Exception e) throws IOException {
    return ctxt.handleWeirdKey(this._keyClass, key, "problem: %s", new Object[] { ClassUtil.exceptionMessage(e) });
  }
  
  @JacksonStdImpl
  static final class StringKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1L;
    
    private static final StringKD sString = new StringKD(String.class);
    
    private static final StringKD sObject = new StringKD(Object.class);
    
    private StringKD(Class<?> nominalType) {
      super(-1, nominalType);
    }
    
    public static StringKD forType(Class<?> nominalType) {
      if (nominalType == String.class)
        return sString; 
      if (nominalType == Object.class)
        return sObject; 
      return new StringKD(nominalType);
    }
    
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
      return key;
    }
  }
  
  static final class DelegatingKD extends KeyDeserializer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected final Class<?> _keyClass;
    
    protected final JsonDeserializer<?> _delegate;
    
    protected DelegatingKD(Class<?> cls, JsonDeserializer<?> deser) {
      this._keyClass = cls;
      this._delegate = deser;
    }
    
    public final Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
      if (key == null)
        return null; 
      TokenBuffer tb = ctxt.bufferForInputBuffering();
      tb.writeString(key);
      try {
        JsonParser p = tb.asParser();
        p.nextToken();
        Object result = this._delegate.deserialize(p, ctxt);
        if (result != null)
          return result; 
        return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation", new Object[0]);
      } catch (Exception re) {
        return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation: %s", new Object[] { re.getMessage() });
      } 
    }
    
    public Class<?> getKeyClass() {
      return this._keyClass;
    }
  }
  
  @JacksonStdImpl
  static final class EnumKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1L;
    
    protected final EnumResolver _byNameResolver;
    
    protected final AnnotatedMethod _factory;
    
    protected volatile EnumResolver _byToStringResolver;
    
    protected volatile EnumResolver _byIndexResolver;
    
    protected final EnumResolver _byEnumNamingResolver;
    
    protected final Enum<?> _enumDefaultValue;
    
    protected EnumKD(EnumResolver er, AnnotatedMethod factory) {
      super(-1, er.getEnumClass());
      this._byNameResolver = er;
      this._factory = factory;
      this._enumDefaultValue = er.getDefaultValue();
      this._byEnumNamingResolver = null;
      this._byToStringResolver = null;
    }
    
    protected EnumKD(EnumResolver er, AnnotatedMethod factory, EnumResolver byEnumNamingResolver, EnumResolver byToStringResolver, EnumResolver byIndexResolver) {
      super(-1, er.getEnumClass());
      this._byNameResolver = er;
      this._factory = factory;
      this._enumDefaultValue = er.getDefaultValue();
      this._byEnumNamingResolver = byEnumNamingResolver;
      this._byToStringResolver = byToStringResolver;
      this._byIndexResolver = byIndexResolver;
    }
    
    public Object _parse(String key, DeserializationContext ctxt) throws IOException {
      if (this._factory != null)
        try {
          return this._factory.call1(key);
        } catch (Exception exception) {
          ClassUtil.unwrapAndThrowAsIAE(exception);
        }  
      EnumResolver res = _resolveCurrentResolver(ctxt);
      Enum<?> e = res.findEnum(key);
      if (e == null && ctxt.isEnabled((DatatypeFeature)EnumFeature.READ_ENUM_KEYS_USING_INDEX)) {
        res = _getIndexResolver(ctxt);
        e = res.findEnum(key);
      } 
      if (e == null)
        if (this._enumDefaultValue != null && ctxt
          .isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
          e = this._enumDefaultValue;
        } else if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
          return ctxt.handleWeirdKey(this._keyClass, key, "not one of the values accepted for Enum class: %s", new Object[] { res
                .getEnumIds() });
        }  
      return e;
    }
    
    protected EnumResolver _resolveCurrentResolver(DeserializationContext ctxt) {
      if (this._byEnumNamingResolver != null)
        return this._byEnumNamingResolver; 
      return ctxt.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? 
        _getToStringResolver(ctxt) : this._byNameResolver;
    }
    
    @Deprecated
    private EnumResolver _getToStringResolver(DeserializationContext ctxt) {
      EnumResolver res = this._byToStringResolver;
      if (res == null)
        synchronized (this) {
          res = this._byToStringResolver;
          if (res == null) {
            res = EnumResolver.constructUsingToString(ctxt.getConfig(), this._byNameResolver
                .getEnumClass());
            this._byToStringResolver = res;
          } 
        }  
      return res;
    }
    
    @Deprecated
    private EnumResolver _getIndexResolver(DeserializationContext ctxt) {
      EnumResolver res = this._byIndexResolver;
      if (res == null)
        synchronized (this) {
          res = this._byIndexResolver;
          if (res == null) {
            res = EnumResolver.constructUsingIndex(ctxt.getConfig(), this._byNameResolver
                .getEnumClass());
            this._byIndexResolver = res;
          } 
        }  
      return res;
    }
  }
  
  static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
    private static final long serialVersionUID = 1L;
    
    protected final Constructor<?> _ctor;
    
    public StringCtorKeyDeserializer(Constructor<?> ctor) {
      super(-1, ctor.getDeclaringClass());
      this._ctor = ctor;
    }
    
    public Object _parse(String key, DeserializationContext ctxt) throws Exception {
      return this._ctor.newInstance(new Object[] { key });
    }
  }
  
  static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
    private static final long serialVersionUID = 1L;
    
    final Method _factoryMethod;
    
    public StringFactoryKeyDeserializer(Method fm) {
      super(-1, fm.getDeclaringClass());
      this._factoryMethod = fm;
    }
    
    public Object _parse(String key, DeserializationContext ctxt) throws Exception {
      return this._factoryMethod.invoke(null, new Object[] { key });
    }
  }
}
