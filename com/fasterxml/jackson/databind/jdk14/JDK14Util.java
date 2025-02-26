package com.fasterxml.jackson.databind.jdk14;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NativeImageUtil;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class JDK14Util {
  public static String[] getRecordFieldNames(Class<?> recordType) {
    return RecordAccessor.instance().getRecordFieldNames(recordType);
  }
  
  public static AnnotatedConstructor findRecordConstructor(DeserializationContext ctxt, BeanDescription beanDesc, List<String> names) {
    return findRecordConstructor(beanDesc.getClassInfo(), ctxt.getAnnotationIntrospector(), (MapperConfig<?>)ctxt.getConfig(), names);
  }
  
  public static AnnotatedConstructor findRecordConstructor(AnnotatedClass recordClass, AnnotationIntrospector intr, MapperConfig<?> config, List<String> names) {
    return (new CreatorLocator(recordClass, intr, config))
      .locate(names);
  }
  
  static class RecordAccessor {
    private final Method RECORD_GET_RECORD_COMPONENTS;
    
    private final Method RECORD_COMPONENT_GET_NAME;
    
    private final Method RECORD_COMPONENT_GET_TYPE;
    
    private static final RecordAccessor INSTANCE;
    
    private static final RuntimeException PROBLEM;
    
    static {
      RuntimeException prob = null;
      RecordAccessor inst = null;
      try {
        inst = new RecordAccessor();
      } catch (RuntimeException e) {
        prob = e;
      } 
      INSTANCE = inst;
      PROBLEM = prob;
    }
    
    private RecordAccessor() throws RuntimeException {
      try {
        this.RECORD_GET_RECORD_COMPONENTS = Class.class.getMethod("getRecordComponents", new Class[0]);
        Class<?> c = Class.forName("java.lang.reflect.RecordComponent");
        this.RECORD_COMPONENT_GET_NAME = c.getMethod("getName", new Class[0]);
        this.RECORD_COMPONENT_GET_TYPE = c.getMethod("getType", new Class[0]);
      } catch (Exception e) {
        throw new RuntimeException(String.format("Failed to access Methods needed to support `java.lang.Record`: (%s) %s", new Object[] { e
                
                .getClass().getName(), e.getMessage() }), e);
      } 
    }
    
    public static RecordAccessor instance() {
      if (PROBLEM != null)
        throw PROBLEM; 
      return INSTANCE;
    }
    
    public String[] getRecordFieldNames(Class<?> recordType) throws IllegalArgumentException {
      Object[] components = recordComponents(recordType);
      if (components == null)
        return null; 
      String[] names = new String[components.length];
      for (int i = 0; i < components.length; i++) {
        try {
          names[i] = (String)this.RECORD_COMPONENT_GET_NAME.invoke(components[i], new Object[0]);
        } catch (Exception e) {
          throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", new Object[] { Integer.valueOf(i), Integer.valueOf(components.length), ClassUtil.nameOf(recordType) }), e);
        } 
      } 
      return names;
    }
    
    public JDK14Util.RawTypeName[] getRecordFields(Class<?> recordType) throws IllegalArgumentException {
      Object[] components = recordComponents(recordType);
      if (components == null)
        return null; 
      JDK14Util.RawTypeName[] results = new JDK14Util.RawTypeName[components.length];
      for (int i = 0; i < components.length; i++) {
        String name;
        Class<?> type;
        try {
          name = (String)this.RECORD_COMPONENT_GET_NAME.invoke(components[i], new Object[0]);
        } catch (Exception e) {
          throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", new Object[] { Integer.valueOf(i), Integer.valueOf(components.length), ClassUtil.nameOf(recordType) }), e);
        } 
        try {
          type = (Class)this.RECORD_COMPONENT_GET_TYPE.invoke(components[i], new Object[0]);
        } catch (Exception e) {
          throw new IllegalArgumentException(String.format("Failed to access type of field #%d (of %d) of Record type %s", new Object[] { Integer.valueOf(i), Integer.valueOf(components.length), ClassUtil.nameOf(recordType) }), e);
        } 
        results[i] = new JDK14Util.RawTypeName(type, name);
      } 
      return results;
    }
    
    protected Object[] recordComponents(Class<?> recordType) throws IllegalArgumentException {
      try {
        return (Object[])this.RECORD_GET_RECORD_COMPONENTS.invoke(recordType, new Object[0]);
      } catch (Exception e) {
        if (NativeImageUtil.isUnsupportedFeatureError(e))
          return null; 
        throw new IllegalArgumentException("Failed to access RecordComponents of type " + 
            ClassUtil.nameOf(recordType));
      } 
    }
  }
  
  static class RawTypeName {
    public final Class<?> rawType;
    
    public final String name;
    
    public RawTypeName(Class<?> rt, String n) {
      this.rawType = rt;
      this.name = n;
    }
  }
  
  static class CreatorLocator {
    protected final AnnotatedClass _recordClass;
    
    protected final MapperConfig<?> _config;
    
    protected final AnnotationIntrospector _intr;
    
    protected final List<AnnotatedConstructor> _constructors;
    
    protected final AnnotatedConstructor _primaryConstructor;
    
    protected final JDK14Util.RawTypeName[] _recordFields;
    
    CreatorLocator(AnnotatedClass recordClass, AnnotationIntrospector intr, MapperConfig<?> config) {
      this._recordClass = recordClass;
      this._intr = intr;
      this._config = config;
      this._recordFields = JDK14Util.RecordAccessor.instance().getRecordFields(recordClass.getRawType());
      if (this._recordFields == null) {
        this._constructors = recordClass.getConstructors();
        this._primaryConstructor = null;
      } else {
        int argCount = this._recordFields.length;
        AnnotatedConstructor primary = null;
        if (argCount == 0) {
          primary = recordClass.getDefaultConstructor();
          this._constructors = Collections.singletonList(primary);
        } else {
          this._constructors = recordClass.getConstructors();
          label28: for (AnnotatedConstructor ctor : this._constructors) {
            if (ctor.getParameterCount() != argCount)
              continue; 
            for (int i = 0; i < argCount; i++) {
              if (!ctor.getRawParameterType(i).equals((this._recordFields[i]).rawType))
                continue label28; 
            } 
            primary = ctor;
          } 
        } 
        if (primary == null)
          throw new IllegalArgumentException("Failed to find the canonical Record constructor of type " + 
              ClassUtil.getTypeDescription(this._recordClass.getType())); 
        this._primaryConstructor = primary;
      } 
    }
    
    public AnnotatedConstructor locate(List<String> names) {
      for (AnnotatedConstructor ctor : this._constructors) {
        JsonCreator.Mode creatorMode = this._intr.findCreatorAnnotation(this._config, (Annotated)ctor);
        if (null == creatorMode || JsonCreator.Mode.DISABLED == creatorMode)
          continue; 
        if (JsonCreator.Mode.DELEGATING == creatorMode)
          return null; 
        if (ctor != this._primaryConstructor)
          return null; 
      } 
      if (this._recordFields == null)
        return null; 
      for (JDK14Util.RawTypeName field : this._recordFields)
        names.add(field.name); 
      return this._primaryConstructor;
    }
  }
}
