package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers extends Deserializers.Base {
  static final DatatypeFactory _dataTypeFactory;
  
  protected static final int TYPE_DURATION = 1;
  
  protected static final int TYPE_G_CALENDAR = 2;
  
  protected static final int TYPE_QNAME = 3;
  
  static {
    try {
      _dataTypeFactory = DatatypeFactory.newInstance();
    } catch (DatatypeConfigurationException e) {
      throw new RuntimeException(e);
    } 
  }
  
  public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) {
    Class<?> raw = type.getRawClass();
    if (raw == QName.class)
      return (JsonDeserializer<?>)new Std(raw, 3); 
    if (raw == XMLGregorianCalendar.class)
      return (JsonDeserializer<?>)new Std(raw, 2); 
    if (raw == Duration.class)
      return (JsonDeserializer<?>)new Std(raw, 1); 
    return null;
  }
  
  public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {
    return (valueType == QName.class || valueType == XMLGregorianCalendar.class || valueType == Duration.class);
  }
  
  public static class Std extends FromStringDeserializer<Object> {
    private static final long serialVersionUID = 1L;
    
    protected final int _kind;
    
    public Std(Class<?> raw, int kind) {
      super(raw);
      this._kind = kind;
    }
    
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (this._kind == 2 && 
        p.hasToken(JsonToken.VALUE_NUMBER_INT))
        return _gregorianFromDate(ctxt, _parseDate(p, ctxt)); 
      return super.deserialize(p, ctxt);
    }
    
    protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
      Date d;
      switch (this._kind) {
        case 1:
          return CoreXMLDeserializers._dataTypeFactory.newDuration(value);
        case 3:
          return QName.valueOf(value);
        case 2:
          try {
            d = _parseDate(value, ctxt);
          } catch (JsonMappingException e) {
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(value);
          } 
          return _gregorianFromDate(ctxt, d);
      } 
      throw new IllegalStateException();
    }
    
    protected XMLGregorianCalendar _gregorianFromDate(DeserializationContext ctxt, Date d) {
      if (d == null)
        return null; 
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(d);
      TimeZone tz = ctxt.getTimeZone();
      if (tz != null)
        calendar.setTimeZone(tz); 
      return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(calendar);
    }
  }
}
