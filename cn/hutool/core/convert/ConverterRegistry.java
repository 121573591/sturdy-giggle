package cn.hutool.core.convert;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.impl.ArrayConverter;
import cn.hutool.core.convert.impl.AtomicBooleanConverter;
import cn.hutool.core.convert.impl.AtomicIntegerArrayConverter;
import cn.hutool.core.convert.impl.AtomicLongArrayConverter;
import cn.hutool.core.convert.impl.AtomicReferenceConverter;
import cn.hutool.core.convert.impl.BeanConverter;
import cn.hutool.core.convert.impl.BooleanConverter;
import cn.hutool.core.convert.impl.CalendarConverter;
import cn.hutool.core.convert.impl.CharacterConverter;
import cn.hutool.core.convert.impl.CharsetConverter;
import cn.hutool.core.convert.impl.ClassConverter;
import cn.hutool.core.convert.impl.CollectionConverter;
import cn.hutool.core.convert.impl.CurrencyConverter;
import cn.hutool.core.convert.impl.DateConverter;
import cn.hutool.core.convert.impl.DurationConverter;
import cn.hutool.core.convert.impl.EntryConverter;
import cn.hutool.core.convert.impl.EnumConverter;
import cn.hutool.core.convert.impl.LocaleConverter;
import cn.hutool.core.convert.impl.MapConverter;
import cn.hutool.core.convert.impl.NumberConverter;
import cn.hutool.core.convert.impl.OptConverter;
import cn.hutool.core.convert.impl.OptionalConverter;
import cn.hutool.core.convert.impl.PairConverter;
import cn.hutool.core.convert.impl.PathConverter;
import cn.hutool.core.convert.impl.PeriodConverter;
import cn.hutool.core.convert.impl.PrimitiveConverter;
import cn.hutool.core.convert.impl.ReferenceConverter;
import cn.hutool.core.convert.impl.StackTraceElementConverter;
import cn.hutool.core.convert.impl.StringConverter;
import cn.hutool.core.convert.impl.TemporalAccessorConverter;
import cn.hutool.core.convert.impl.TimeZoneConverter;
import cn.hutool.core.convert.impl.URIConverter;
import cn.hutool.core.convert.impl.URLConverter;
import cn.hutool.core.convert.impl.UUIDConverter;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.ServiceLoaderUtil;
import cn.hutool.core.util.TypeUtil;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class ConverterRegistry implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Map<Class<?>, Converter<?>> defaultConverterMap;
  
  private volatile Map<Type, Converter<?>> customConverterMap;
  
  private static class SingletonHolder {
    private static final ConverterRegistry INSTANCE = new ConverterRegistry();
  }
  
  public static ConverterRegistry getInstance() {
    return SingletonHolder.INSTANCE;
  }
  
  public ConverterRegistry() {
    defaultConverter();
    putCustomBySpi();
  }
  
  private void putCustomBySpi() {
    ServiceLoaderUtil.load(Converter.class).forEach(converter -> {
          try {
            Type type = TypeUtil.getTypeArgument(ClassUtil.getClass(converter));
            if (null != type)
              putCustom(type, converter); 
          } catch (Exception exception) {}
        });
  }
  
  public ConverterRegistry putCustom(Type type, Class<? extends Converter<?>> converterClass) {
    return putCustom(type, (Converter)ReflectUtil.newInstance(converterClass, new Object[0]));
  }
  
  public ConverterRegistry putCustom(Type type, Converter<?> converter) {
    if (null == this.customConverterMap)
      synchronized (this) {
        if (null == this.customConverterMap)
          this.customConverterMap = (Map<Type, Converter<?>>)new SafeConcurrentHashMap(); 
      }  
    this.customConverterMap.put(type, converter);
    return this;
  }
  
  public <T> Converter<T> getConverter(Type type, boolean isCustomFirst) {
    Converter<T> converter;
    if (isCustomFirst) {
      converter = getCustomConverter(type);
      if (null == converter)
        converter = getDefaultConverter(type); 
    } else {
      converter = getDefaultConverter(type);
      if (null == converter)
        converter = getCustomConverter(type); 
    } 
    return converter;
  }
  
  public <T> Converter<T> getDefaultConverter(Type type) {
    return (null == this.defaultConverterMap) ? null : (Converter<T>)this.defaultConverterMap.get(TypeUtil.getClass(type));
  }
  
  public <T> Converter<T> getCustomConverter(Type type) {
    return (null == this.customConverterMap) ? null : (Converter<T>)this.customConverterMap.get(type);
  }
  
  public <T> T convert(Type<?> type, Object value, T defaultValue, boolean isCustomFirst) throws ConvertException {
    if (TypeUtil.isUnknown(type) && null == defaultValue)
      return (T)value; 
    if (ObjectUtil.isNull(value))
      return defaultValue; 
    if (TypeUtil.isUnknown(type))
      type = defaultValue.getClass(); 
    if (value instanceof Opt) {
      value = ((Opt)value).get();
      if (ObjUtil.isNull(value))
        return defaultValue; 
    } 
    if (value instanceof Optional) {
      value = ((Optional)value).orElse(null);
      if (ObjUtil.isNull(value))
        return defaultValue; 
    } 
    if (type instanceof TypeReference)
      type = ((TypeReference)type).getType(); 
    if (value instanceof TypeConverter)
      return (T)ObjUtil.defaultIfNull(((TypeConverter)value).convert(type, value), defaultValue); 
    Converter<T> converter = getConverter(type, isCustomFirst);
    if (null != converter)
      return converter.convert(value, defaultValue); 
    Class<T> rowType = TypeUtil.getClass(type);
    if (null == rowType)
      if (null != defaultValue) {
        rowType = (Class)defaultValue.getClass();
      } else {
        return (T)value;
      }  
    T result = convertSpecial(type, rowType, value, defaultValue);
    if (null != result)
      return result; 
    if (BeanUtil.isBean(rowType))
      return (T)(new BeanConverter(type)).convert(value, defaultValue); 
    throw new ConvertException("Can not Converter from [{}] to [{}]", new Object[] { value.getClass().getName(), type.getTypeName() });
  }
  
  public <T> T convert(Type type, Object value, T defaultValue) throws ConvertException {
    return convert(type, value, defaultValue, true);
  }
  
  public <T> T convert(Type type, Object value) throws ConvertException {
    return convert(type, value, null);
  }
  
  private <T> T convertSpecial(Type type, Class<T> rowType, Object value, T defaultValue) {
    if (null == rowType)
      return null; 
    if (Collection.class.isAssignableFrom(rowType)) {
      CollectionConverter collectionConverter = new CollectionConverter(type);
      return (T)collectionConverter.convert(value, (Collection)defaultValue);
    } 
    if (Map.class.isAssignableFrom(rowType)) {
      MapConverter mapConverter = new MapConverter(type);
      return (T)mapConverter.convert(value, (Map)defaultValue);
    } 
    if (Map.Entry.class.isAssignableFrom(rowType)) {
      EntryConverter mapConverter = new EntryConverter(type);
      return (T)mapConverter.convert(value, (Map.Entry)defaultValue);
    } 
    if (rowType.isInstance(value))
      return (T)value; 
    if (rowType.isEnum())
      return (T)(new EnumConverter(rowType)).convert(value, defaultValue); 
    if (rowType.isArray()) {
      ArrayConverter arrayConverter = new ArrayConverter(rowType);
      return (T)arrayConverter.convert(value, defaultValue);
    } 
    if ("java.lang.Class".equals(rowType.getName())) {
      ClassConverter converter = new ClassConverter();
      return (T)converter.convert(value, (Class)defaultValue);
    } 
    return null;
  }
  
  private ConverterRegistry defaultConverter() {
    this.defaultConverterMap = (Map<Class<?>, Converter<?>>)new SafeConcurrentHashMap();
    this.defaultConverterMap.put(int.class, new PrimitiveConverter(int.class));
    this.defaultConverterMap.put(long.class, new PrimitiveConverter(long.class));
    this.defaultConverterMap.put(byte.class, new PrimitiveConverter(byte.class));
    this.defaultConverterMap.put(short.class, new PrimitiveConverter(short.class));
    this.defaultConverterMap.put(float.class, new PrimitiveConverter(float.class));
    this.defaultConverterMap.put(double.class, new PrimitiveConverter(double.class));
    this.defaultConverterMap.put(char.class, new PrimitiveConverter(char.class));
    this.defaultConverterMap.put(boolean.class, new PrimitiveConverter(boolean.class));
    this.defaultConverterMap.put(Number.class, new NumberConverter());
    this.defaultConverterMap.put(Integer.class, new NumberConverter(Integer.class));
    this.defaultConverterMap.put(AtomicInteger.class, new NumberConverter(AtomicInteger.class));
    this.defaultConverterMap.put(Long.class, new NumberConverter(Long.class));
    this.defaultConverterMap.put(LongAdder.class, new NumberConverter(LongAdder.class));
    this.defaultConverterMap.put(AtomicLong.class, new NumberConverter(AtomicLong.class));
    this.defaultConverterMap.put(Byte.class, new NumberConverter(Byte.class));
    this.defaultConverterMap.put(Short.class, new NumberConverter(Short.class));
    this.defaultConverterMap.put(Float.class, new NumberConverter(Float.class));
    this.defaultConverterMap.put(Double.class, new NumberConverter(Double.class));
    this.defaultConverterMap.put(DoubleAdder.class, new NumberConverter(DoubleAdder.class));
    this.defaultConverterMap.put(Character.class, new CharacterConverter());
    this.defaultConverterMap.put(Boolean.class, new BooleanConverter());
    this.defaultConverterMap.put(AtomicBoolean.class, new AtomicBooleanConverter());
    this.defaultConverterMap.put(BigDecimal.class, new NumberConverter(BigDecimal.class));
    this.defaultConverterMap.put(BigInteger.class, new NumberConverter(BigInteger.class));
    this.defaultConverterMap.put(CharSequence.class, new StringConverter());
    this.defaultConverterMap.put(String.class, new StringConverter());
    this.defaultConverterMap.put(URI.class, new URIConverter());
    this.defaultConverterMap.put(URL.class, new URLConverter());
    this.defaultConverterMap.put(Calendar.class, new CalendarConverter());
    this.defaultConverterMap.put(Date.class, new DateConverter(Date.class));
    this.defaultConverterMap.put(DateTime.class, new DateConverter(DateTime.class));
    this.defaultConverterMap.put(Date.class, new DateConverter(Date.class));
    this.defaultConverterMap.put(Time.class, new DateConverter(Time.class));
    this.defaultConverterMap.put(Timestamp.class, new DateConverter(Timestamp.class));
    this.defaultConverterMap.put(TemporalAccessor.class, new TemporalAccessorConverter(Instant.class));
    this.defaultConverterMap.put(Instant.class, new TemporalAccessorConverter(Instant.class));
    this.defaultConverterMap.put(LocalDateTime.class, new TemporalAccessorConverter(LocalDateTime.class));
    this.defaultConverterMap.put(LocalDate.class, new TemporalAccessorConverter(LocalDate.class));
    this.defaultConverterMap.put(LocalTime.class, new TemporalAccessorConverter(LocalTime.class));
    this.defaultConverterMap.put(ZonedDateTime.class, new TemporalAccessorConverter(ZonedDateTime.class));
    this.defaultConverterMap.put(OffsetDateTime.class, new TemporalAccessorConverter(OffsetDateTime.class));
    this.defaultConverterMap.put(OffsetTime.class, new TemporalAccessorConverter(OffsetTime.class));
    this.defaultConverterMap.put(DayOfWeek.class, new TemporalAccessorConverter(DayOfWeek.class));
    this.defaultConverterMap.put(Month.class, new TemporalAccessorConverter(Month.class));
    this.defaultConverterMap.put(MonthDay.class, new TemporalAccessorConverter(MonthDay.class));
    this.defaultConverterMap.put(Period.class, new PeriodConverter());
    this.defaultConverterMap.put(Duration.class, new DurationConverter());
    this.defaultConverterMap.put(WeakReference.class, new ReferenceConverter(WeakReference.class));
    this.defaultConverterMap.put(SoftReference.class, new ReferenceConverter(SoftReference.class));
    this.defaultConverterMap.put(AtomicReference.class, new AtomicReferenceConverter());
    this.defaultConverterMap.put(AtomicIntegerArray.class, new AtomicIntegerArrayConverter());
    this.defaultConverterMap.put(AtomicLongArray.class, new AtomicLongArrayConverter());
    this.defaultConverterMap.put(TimeZone.class, new TimeZoneConverter());
    this.defaultConverterMap.put(Locale.class, new LocaleConverter());
    this.defaultConverterMap.put(Charset.class, new CharsetConverter());
    this.defaultConverterMap.put(Path.class, new PathConverter());
    this.defaultConverterMap.put(Currency.class, new CurrencyConverter());
    this.defaultConverterMap.put(UUID.class, new UUIDConverter());
    this.defaultConverterMap.put(StackTraceElement.class, new StackTraceElementConverter());
    this.defaultConverterMap.put(Optional.class, new OptionalConverter());
    this.defaultConverterMap.put(Opt.class, new OptConverter());
    this.defaultConverterMap.put(Pair.class, new PairConverter(Pair.class));
    return this;
  }
}
