package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jdk14.JDK14Util;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultAccessorNamingStrategy extends AccessorNamingStrategy {
  protected final MapperConfig<?> _config;
  
  protected final AnnotatedClass _forClass;
  
  protected final BaseNameValidator _baseNameValidator;
  
  protected final boolean _stdBeanNaming;
  
  protected final boolean _isGettersNonBoolean;
  
  protected final String _getterPrefix;
  
  protected final String _isGetterPrefix;
  
  protected final String _mutatorPrefix;
  
  protected DefaultAccessorNamingStrategy(MapperConfig<?> config, AnnotatedClass forClass, String mutatorPrefix, String getterPrefix, String isGetterPrefix, BaseNameValidator baseNameValidator) {
    this._config = config;
    this._forClass = forClass;
    this._stdBeanNaming = config.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
    this._isGettersNonBoolean = config.isEnabled(MapperFeature.ALLOW_IS_GETTERS_FOR_NON_BOOLEAN);
    this._mutatorPrefix = mutatorPrefix;
    this._getterPrefix = getterPrefix;
    this._isGetterPrefix = isGetterPrefix;
    this._baseNameValidator = baseNameValidator;
  }
  
  public String findNameForIsGetter(AnnotatedMethod am, String name) {
    if (this._isGetterPrefix != null && (
      this._isGettersNonBoolean || _booleanType(am.getType())) && 
      name.startsWith(this._isGetterPrefix))
      return this._stdBeanNaming ? 
        stdManglePropertyName(name, this._isGetterPrefix.length()) : 
        legacyManglePropertyName(name, this._isGetterPrefix.length()); 
    return null;
  }
  
  private boolean _booleanType(JavaType type) {
    if (type.isReferenceType())
      type = type.getReferencedType(); 
    if (type.hasRawClass(boolean.class) || type
      .hasRawClass(Boolean.class) || type
      .hasRawClass(AtomicBoolean.class))
      return true; 
    return false;
  }
  
  public String findNameForRegularGetter(AnnotatedMethod am, String name) {
    if (this._getterPrefix != null && name.startsWith(this._getterPrefix)) {
      if ("getCallbacks".equals(name)) {
        if (_isCglibGetCallbacks(am))
          return null; 
      } else if ("getMetaClass".equals(name)) {
        if (_isGroovyMetaClassGetter(am))
          return null; 
      } 
      return this._stdBeanNaming ? 
        stdManglePropertyName(name, this._getterPrefix.length()) : 
        legacyManglePropertyName(name, this._getterPrefix.length());
    } 
    return null;
  }
  
  public String findNameForMutator(AnnotatedMethod am, String name) {
    if (this._mutatorPrefix != null && name.startsWith(this._mutatorPrefix))
      return this._stdBeanNaming ? 
        stdManglePropertyName(name, this._mutatorPrefix.length()) : 
        legacyManglePropertyName(name, this._mutatorPrefix.length()); 
    return null;
  }
  
  public String modifyFieldName(AnnotatedField field, String name) {
    return name;
  }
  
  protected String legacyManglePropertyName(String basename, int offset) {
    int end = basename.length();
    if (end == offset)
      return null; 
    char c = basename.charAt(offset);
    if (this._baseNameValidator != null && 
      !this._baseNameValidator.accept(c, basename, offset))
      return null; 
    char d = Character.toLowerCase(c);
    if (c == d)
      return basename.substring(offset); 
    StringBuilder sb = new StringBuilder(end - offset);
    sb.append(d);
    int i = offset + 1;
    for (; i < end; i++) {
      c = basename.charAt(i);
      d = Character.toLowerCase(c);
      if (c == d) {
        sb.append(basename, i, end);
        break;
      } 
      sb.append(d);
    } 
    return sb.toString();
  }
  
  protected String stdManglePropertyName(String basename, int offset) {
    int end = basename.length();
    if (end == offset)
      return null; 
    char c0 = basename.charAt(offset);
    if (this._baseNameValidator != null && 
      !this._baseNameValidator.accept(c0, basename, offset))
      return null; 
    char c1 = Character.toLowerCase(c0);
    if (c0 == c1)
      return basename.substring(offset); 
    if (offset + 1 < end && 
      Character.isUpperCase(basename.charAt(offset + 1)))
      return basename.substring(offset); 
    StringBuilder sb = new StringBuilder(end - offset);
    sb.append(c1);
    sb.append(basename, offset + 1, end);
    return sb.toString();
  }
  
  protected boolean _isCglibGetCallbacks(AnnotatedMethod am) {
    Class<?> rt = am.getRawType();
    if (rt.isArray()) {
      Class<?> compType = rt.getComponentType();
      String className = compType.getName();
      if (className.contains(".cglib"))
        return (className.startsWith("net.sf.cglib") || className
          
          .startsWith("org.hibernate.repackage.cglib") || className
          
          .startsWith("org.springframework.cglib")); 
    } 
    return false;
  }
  
  protected boolean _isGroovyMetaClassGetter(AnnotatedMethod am) {
    return am.getRawType().getName().startsWith("groovy.lang");
  }
  
  public static interface BaseNameValidator {
    boolean accept(char param1Char, String param1String, int param1Int);
  }
  
  public static class Provider extends AccessorNamingStrategy.Provider implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected final String _setterPrefix;
    
    protected final String _withPrefix;
    
    protected final String _getterPrefix;
    
    protected final String _isGetterPrefix;
    
    protected final DefaultAccessorNamingStrategy.BaseNameValidator _baseNameValidator;
    
    public Provider() {
      this("set", "with", "get", "is", (DefaultAccessorNamingStrategy.BaseNameValidator)null);
    }
    
    protected Provider(Provider p, String setterPrefix, String withPrefix, String getterPrefix, String isGetterPrefix) {
      this(setterPrefix, withPrefix, getterPrefix, isGetterPrefix, p._baseNameValidator);
    }
    
    protected Provider(Provider p, DefaultAccessorNamingStrategy.BaseNameValidator vld) {
      this(p._setterPrefix, p._withPrefix, p._getterPrefix, p._isGetterPrefix, vld);
    }
    
    protected Provider(String setterPrefix, String withPrefix, String getterPrefix, String isGetterPrefix, DefaultAccessorNamingStrategy.BaseNameValidator vld) {
      this._setterPrefix = setterPrefix;
      this._withPrefix = withPrefix;
      this._getterPrefix = getterPrefix;
      this._isGetterPrefix = isGetterPrefix;
      this._baseNameValidator = vld;
    }
    
    public Provider withSetterPrefix(String prefix) {
      return new Provider(this, prefix, this._withPrefix, this._getterPrefix, this._isGetterPrefix);
    }
    
    public Provider withBuilderPrefix(String prefix) {
      return new Provider(this, this._setterPrefix, prefix, this._getterPrefix, this._isGetterPrefix);
    }
    
    public Provider withGetterPrefix(String prefix) {
      return new Provider(this, this._setterPrefix, this._withPrefix, prefix, this._isGetterPrefix);
    }
    
    public Provider withIsGetterPrefix(String prefix) {
      return new Provider(this, this._setterPrefix, this._withPrefix, this._getterPrefix, prefix);
    }
    
    public Provider withFirstCharAcceptance(boolean allowLowerCaseFirstChar, boolean allowNonLetterFirstChar) {
      return withBaseNameValidator(
          DefaultAccessorNamingStrategy.FirstCharBasedValidator.forFirstNameRule(allowLowerCaseFirstChar, allowNonLetterFirstChar));
    }
    
    public Provider withBaseNameValidator(DefaultAccessorNamingStrategy.BaseNameValidator vld) {
      return new Provider(this, vld);
    }
    
    public AccessorNamingStrategy forPOJO(MapperConfig<?> config, AnnotatedClass targetClass) {
      return new DefaultAccessorNamingStrategy(config, targetClass, this._setterPrefix, this._getterPrefix, this._isGetterPrefix, this._baseNameValidator);
    }
    
    public AccessorNamingStrategy forBuilder(MapperConfig<?> config, AnnotatedClass builderClass, BeanDescription valueTypeDesc) {
      AnnotationIntrospector ai = config.isAnnotationProcessingEnabled() ? config.getAnnotationIntrospector() : null;
      JsonPOJOBuilder.Value builderConfig = (ai == null) ? null : ai.findPOJOBuilderConfig(builderClass);
      String mutatorPrefix = (builderConfig == null) ? this._withPrefix : builderConfig.withPrefix;
      return new DefaultAccessorNamingStrategy(config, builderClass, mutatorPrefix, this._getterPrefix, this._isGetterPrefix, this._baseNameValidator);
    }
    
    public AccessorNamingStrategy forRecord(MapperConfig<?> config, AnnotatedClass recordClass) {
      return new DefaultAccessorNamingStrategy.RecordNaming(config, recordClass);
    }
  }
  
  public static class FirstCharBasedValidator implements BaseNameValidator {
    private final boolean _allowLowerCaseFirstChar;
    
    private final boolean _allowNonLetterFirstChar;
    
    protected FirstCharBasedValidator(boolean allowLowerCaseFirstChar, boolean allowNonLetterFirstChar) {
      this._allowLowerCaseFirstChar = allowLowerCaseFirstChar;
      this._allowNonLetterFirstChar = allowNonLetterFirstChar;
    }
    
    public static DefaultAccessorNamingStrategy.BaseNameValidator forFirstNameRule(boolean allowLowerCaseFirstChar, boolean allowNonLetterFirstChar) {
      if (!allowLowerCaseFirstChar && !allowNonLetterFirstChar)
        return null; 
      return new FirstCharBasedValidator(allowLowerCaseFirstChar, allowNonLetterFirstChar);
    }
    
    public boolean accept(char firstChar, String basename, int offset) {
      if (Character.isLetter(firstChar))
        return (this._allowLowerCaseFirstChar || !Character.isLowerCase(firstChar)); 
      return this._allowNonLetterFirstChar;
    }
  }
  
  public static class RecordNaming extends DefaultAccessorNamingStrategy {
    protected final Set<String> _fieldNames;
    
    public RecordNaming(MapperConfig<?> config, AnnotatedClass forClass) {
      super(config, forClass, null, "get", "is", null);
      String[] recordFieldNames = JDK14Util.getRecordFieldNames(forClass.getRawType());
      this
        
        ._fieldNames = (recordFieldNames == null) ? Collections.<String>emptySet() : new HashSet<>(Arrays.asList(recordFieldNames));
    }
    
    public String findNameForRegularGetter(AnnotatedMethod am, String name) {
      if (this._fieldNames.contains(name))
        return name; 
      return super.findNameForRegularGetter(am, name);
    }
  }
}
