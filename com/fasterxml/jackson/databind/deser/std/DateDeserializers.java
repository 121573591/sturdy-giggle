package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializers {
  private static final HashSet<String> _utilClasses = new HashSet<>();
  
  static {
    _utilClasses.add("java.util.Calendar");
    _utilClasses.add("java.util.GregorianCalendar");
    _utilClasses.add("java.util.Date");
  }
  
  public static JsonDeserializer<?> find(Class<?> rawType, String clsName) {
    if (_utilClasses.contains(clsName)) {
      if (rawType == Calendar.class)
        return new CalendarDeserializer(); 
      if (rawType == Date.class)
        return DateDeserializer.instance; 
      if (rawType == GregorianCalendar.class)
        return new CalendarDeserializer((Class)GregorianCalendar.class); 
    } 
    return null;
  }
  
  public static boolean hasDeserializerFor(Class<?> rawType) {
    return _utilClasses.contains(rawType.getName());
  }
  
  protected static abstract class DateBasedDeserializer<T> extends StdScalarDeserializer<T> implements ContextualDeserializer {
    protected final DateFormat _customFormat;
    
    protected final String _formatString;
    
    protected DateBasedDeserializer(Class<?> clz) {
      super(clz);
      this._customFormat = null;
      this._formatString = null;
    }
    
    protected DateBasedDeserializer(DateBasedDeserializer<T> base, DateFormat format, String formatStr) {
      super(base._valueClass);
      this._customFormat = format;
      this._formatString = formatStr;
    }
    
    protected abstract DateBasedDeserializer<T> withDateFormat(DateFormat param1DateFormat, String param1String);
    
    public LogicalType logicalType() {
      return LogicalType.DateTime;
    }
    
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      JsonFormat.Value format = findFormatOverrides(ctxt, property, 
          handledType());
      if (format != null) {
        TimeZone tz = format.getTimeZone();
        Boolean lenient = format.getLenient();
        if (format.hasPattern()) {
          String pattern = format.getPattern();
          Locale loc = format.hasLocale() ? format.getLocale() : ctxt.getLocale();
          SimpleDateFormat df = new SimpleDateFormat(pattern, loc);
          if (tz == null)
            tz = ctxt.getTimeZone(); 
          df.setTimeZone(tz);
          if (lenient != null)
            df.setLenient(lenient.booleanValue()); 
          return withDateFormat(df, pattern);
        } 
        if (tz != null) {
          StdDateFormat stdDateFormat;
          DateFormat dateFormat1, df = ctxt.getConfig().getDateFormat();
          if (df.getClass() == StdDateFormat.class) {
            Locale loc = format.hasLocale() ? format.getLocale() : ctxt.getLocale();
            StdDateFormat std = (StdDateFormat)df;
            std = std.withTimeZone(tz);
            std = std.withLocale(loc);
            if (lenient != null)
              std = std.withLenient(lenient); 
            stdDateFormat = std;
          } else {
            dateFormat1 = (DateFormat)stdDateFormat.clone();
            dateFormat1.setTimeZone(tz);
            if (lenient != null)
              dateFormat1.setLenient(lenient.booleanValue()); 
          } 
          return withDateFormat(dateFormat1, this._formatString);
        } 
        if (lenient != null) {
          StdDateFormat stdDateFormat;
          DateFormat dateFormat1, df = ctxt.getConfig().getDateFormat();
          String pattern = this._formatString;
          if (df.getClass() == StdDateFormat.class) {
            StdDateFormat std = (StdDateFormat)df;
            std = std.withLenient(lenient);
            stdDateFormat = std;
            pattern = std.toPattern();
          } else {
            dateFormat1 = (DateFormat)stdDateFormat.clone();
            dateFormat1.setLenient(lenient.booleanValue());
            if (dateFormat1 instanceof SimpleDateFormat)
              ((SimpleDateFormat)dateFormat1).toPattern(); 
          } 
          if (pattern == null)
            pattern = "[unknown]"; 
          return withDateFormat(dateFormat1, pattern);
        } 
      } 
      return this;
    }
    
    protected Date _parseDate(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (this._customFormat != null && 
        p.hasToken(JsonToken.VALUE_STRING)) {
        String str = p.getText().trim();
        if (str.isEmpty()) {
          CoercionAction act = _checkFromStringCoercion(ctxt, str);
          switch (act) {
            case AsEmpty:
              return new Date(0L);
          } 
          return null;
        } 
        synchronized (this._customFormat) {
          return this._customFormat.parse(str);
        } 
      } 
      return super._parseDate(p, ctxt);
    }
  }
  
  @JacksonStdImpl
  public static class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
    protected final Constructor<Calendar> _defaultCtor;
    
    public CalendarDeserializer() {
      super(Calendar.class);
      this._defaultCtor = null;
    }
    
    public CalendarDeserializer(Class<? extends Calendar> cc) {
      super(cc);
      this._defaultCtor = ClassUtil.findConstructor(cc, false);
    }
    
    public CalendarDeserializer(CalendarDeserializer src, DateFormat df, String formatString) {
      super(src, df, formatString);
      this._defaultCtor = src._defaultCtor;
    }
    
    protected CalendarDeserializer withDateFormat(DateFormat df, String formatString) {
      return new CalendarDeserializer(this, df, formatString);
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTimeInMillis(0L);
      return cal;
    }
    
    public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      Date d = _parseDate(p, ctxt);
      if (d == null)
        return null; 
      if (this._defaultCtor == null)
        return ctxt.constructCalendar(d); 
      try {
        Calendar c = this._defaultCtor.newInstance(new Object[0]);
        c.setTimeInMillis(d.getTime());
        TimeZone tz = ctxt.getTimeZone();
        if (tz != null)
          c.setTimeZone(tz); 
        return c;
      } catch (Exception e) {
        return (Calendar)ctxt.handleInstantiationProblem(handledType(), d, e);
      } 
    }
  }
  
  @JacksonStdImpl
  public static class DateDeserializer extends DateBasedDeserializer<Date> {
    public static final DateDeserializer instance = new DateDeserializer();
    
    public DateDeserializer() {
      super(Date.class);
    }
    
    public DateDeserializer(DateDeserializer base, DateFormat df, String formatString) {
      super(base, df, formatString);
    }
    
    protected DateDeserializer withDateFormat(DateFormat df, String formatString) {
      return new DateDeserializer(this, df, formatString);
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) {
      return new Date(0L);
    }
    
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      return _parseDate(p, ctxt);
    }
  }
  
  public static class SqlDateDeserializer extends DateBasedDeserializer<Date> {
    public SqlDateDeserializer() {
      super(Date.class);
    }
    
    public SqlDateDeserializer(SqlDateDeserializer src, DateFormat df, String formatString) {
      super(src, df, formatString);
    }
    
    protected SqlDateDeserializer withDateFormat(DateFormat df, String formatString) {
      return new SqlDateDeserializer(this, df, formatString);
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) {
      return new Date(0L);
    }
    
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      Date d = _parseDate(p, ctxt);
      return (d == null) ? null : new Date(d.getTime());
    }
  }
  
  public static class TimestampDeserializer extends DateBasedDeserializer<Timestamp> {
    public TimestampDeserializer() {
      super(Timestamp.class);
    }
    
    public TimestampDeserializer(TimestampDeserializer src, DateFormat df, String formatString) {
      super(src, df, formatString);
    }
    
    protected TimestampDeserializer withDateFormat(DateFormat df, String formatString) {
      return new TimestampDeserializer(this, df, formatString);
    }
    
    public Object getEmptyValue(DeserializationContext ctxt) {
      return new Timestamp(0L);
    }
    
    public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      Date d = _parseDate(p, ctxt);
      return (d == null) ? null : new Timestamp(d.getTime());
    }
  }
}
