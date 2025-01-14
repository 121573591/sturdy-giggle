package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class BasicPolymorphicTypeValidator extends PolymorphicTypeValidator.Base implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected final Set<Class<?>> _invalidBaseTypes;
  
  protected final TypeMatcher[] _baseTypeMatchers;
  
  protected final NameMatcher[] _subTypeNameMatchers;
  
  protected final TypeMatcher[] _subClassMatchers;
  
  public static abstract class TypeMatcher {
    public abstract boolean match(MapperConfig<?> param1MapperConfig, Class<?> param1Class);
  }
  
  public static abstract class NameMatcher {
    public abstract boolean match(MapperConfig<?> param1MapperConfig, String param1String);
  }
  
  public static class Builder {
    protected Set<Class<?>> _invalidBaseTypes;
    
    protected List<BasicPolymorphicTypeValidator.TypeMatcher> _baseTypeMatchers;
    
    protected List<BasicPolymorphicTypeValidator.NameMatcher> _subTypeNameMatchers;
    
    protected List<BasicPolymorphicTypeValidator.TypeMatcher> _subTypeClassMatchers;
    
    public Builder allowIfBaseType(final Class<?> baseOfBase) {
      return _appendBaseMatcher(new BasicPolymorphicTypeValidator.TypeMatcher() {
            public boolean match(MapperConfig<?> config, Class<?> clazz) {
              return baseOfBase.isAssignableFrom(clazz);
            }
          });
    }
    
    public Builder allowIfBaseType(final Pattern patternForBase) {
      return _appendBaseMatcher(new BasicPolymorphicTypeValidator.TypeMatcher() {
            public boolean match(MapperConfig<?> config, Class<?> clazz) {
              return patternForBase.matcher(clazz.getName()).matches();
            }
          });
    }
    
    public Builder allowIfBaseType(final String prefixForBase) {
      return _appendBaseMatcher(new BasicPolymorphicTypeValidator.TypeMatcher() {
            public boolean match(MapperConfig<?> config, Class<?> clazz) {
              return clazz.getName().startsWith(prefixForBase);
            }
          });
    }
    
    public Builder allowIfBaseType(BasicPolymorphicTypeValidator.TypeMatcher matcher) {
      return _appendBaseMatcher(matcher);
    }
    
    public Builder denyForExactBaseType(Class<?> baseTypeToDeny) {
      if (this._invalidBaseTypes == null)
        this._invalidBaseTypes = new HashSet<>(); 
      this._invalidBaseTypes.add(baseTypeToDeny);
      return this;
    }
    
    public Builder allowIfSubType(final Class<?> subTypeBase) {
      return _appendSubClassMatcher(new BasicPolymorphicTypeValidator.TypeMatcher() {
            public boolean match(MapperConfig<?> config, Class<?> clazz) {
              return subTypeBase.isAssignableFrom(clazz);
            }
          });
    }
    
    public Builder allowIfSubType(final Pattern patternForSubType) {
      return _appendSubNameMatcher(new BasicPolymorphicTypeValidator.NameMatcher() {
            public boolean match(MapperConfig<?> config, String clazzName) {
              return patternForSubType.matcher(clazzName).matches();
            }
          });
    }
    
    public Builder allowIfSubType(final String prefixForSubType) {
      return _appendSubNameMatcher(new BasicPolymorphicTypeValidator.NameMatcher() {
            public boolean match(MapperConfig<?> config, String clazzName) {
              return clazzName.startsWith(prefixForSubType);
            }
          });
    }
    
    public Builder allowIfSubType(BasicPolymorphicTypeValidator.TypeMatcher matcher) {
      return _appendSubClassMatcher(matcher);
    }
    
    public Builder allowIfSubTypeIsArray() {
      return _appendSubClassMatcher(new BasicPolymorphicTypeValidator.TypeMatcher() {
            public boolean match(MapperConfig<?> config, Class<?> clazz) {
              return clazz.isArray();
            }
          });
    }
    
    public BasicPolymorphicTypeValidator build() {
      return new BasicPolymorphicTypeValidator(this._invalidBaseTypes, (this._baseTypeMatchers == null) ? null : this._baseTypeMatchers
          .<BasicPolymorphicTypeValidator.TypeMatcher>toArray(new BasicPolymorphicTypeValidator.TypeMatcher[0]), (this._subTypeNameMatchers == null) ? null : this._subTypeNameMatchers
          .<BasicPolymorphicTypeValidator.NameMatcher>toArray(new BasicPolymorphicTypeValidator.NameMatcher[0]), (this._subTypeClassMatchers == null) ? null : this._subTypeClassMatchers
          .<BasicPolymorphicTypeValidator.TypeMatcher>toArray(new BasicPolymorphicTypeValidator.TypeMatcher[0]));
    }
    
    protected Builder _appendBaseMatcher(BasicPolymorphicTypeValidator.TypeMatcher matcher) {
      if (this._baseTypeMatchers == null)
        this._baseTypeMatchers = new ArrayList<>(); 
      this._baseTypeMatchers.add(matcher);
      return this;
    }
    
    protected Builder _appendSubNameMatcher(BasicPolymorphicTypeValidator.NameMatcher matcher) {
      if (this._subTypeNameMatchers == null)
        this._subTypeNameMatchers = new ArrayList<>(); 
      this._subTypeNameMatchers.add(matcher);
      return this;
    }
    
    protected Builder _appendSubClassMatcher(BasicPolymorphicTypeValidator.TypeMatcher matcher) {
      if (this._subTypeClassMatchers == null)
        this._subTypeClassMatchers = new ArrayList<>(); 
      this._subTypeClassMatchers.add(matcher);
      return this;
    }
  }
  
  protected BasicPolymorphicTypeValidator(Set<Class<?>> invalidBaseTypes, TypeMatcher[] baseTypeMatchers, NameMatcher[] subTypeNameMatchers, TypeMatcher[] subClassMatchers) {
    this._invalidBaseTypes = invalidBaseTypes;
    this._baseTypeMatchers = baseTypeMatchers;
    this._subTypeNameMatchers = subTypeNameMatchers;
    this._subClassMatchers = subClassMatchers;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public PolymorphicTypeValidator.Validity validateBaseType(MapperConfig<?> ctxt, JavaType baseType) {
    Class<?> rawBase = baseType.getRawClass();
    if (this._invalidBaseTypes != null && 
      this._invalidBaseTypes.contains(rawBase))
      return PolymorphicTypeValidator.Validity.DENIED; 
    if (this._baseTypeMatchers != null)
      for (TypeMatcher m : this._baseTypeMatchers) {
        if (m.match(ctxt, rawBase))
          return PolymorphicTypeValidator.Validity.ALLOWED; 
      }  
    return PolymorphicTypeValidator.Validity.INDETERMINATE;
  }
  
  public PolymorphicTypeValidator.Validity validateSubClassName(MapperConfig<?> ctxt, JavaType baseType, String subClassName) throws JsonMappingException {
    if (this._subTypeNameMatchers != null)
      for (NameMatcher m : this._subTypeNameMatchers) {
        if (m.match(ctxt, subClassName))
          return PolymorphicTypeValidator.Validity.ALLOWED; 
      }  
    return PolymorphicTypeValidator.Validity.INDETERMINATE;
  }
  
  public PolymorphicTypeValidator.Validity validateSubType(MapperConfig<?> ctxt, JavaType baseType, JavaType subType) throws JsonMappingException {
    if (this._subClassMatchers != null) {
      Class<?> subClass = subType.getRawClass();
      for (TypeMatcher m : this._subClassMatchers) {
        if (m.match(ctxt, subClass))
          return PolymorphicTypeValidator.Validity.ALLOWED; 
      } 
    } 
    return PolymorphicTypeValidator.Validity.INDETERMINATE;
  }
}
