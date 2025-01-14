package cn.hutool.core.convert;

import cn.hutool.core.convert.impl.CollectionConverter;
import cn.hutool.core.convert.impl.EnumConverter;
import cn.hutool.core.convert.impl.MapConverter;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Convert {
  public static String toStr(Object value, String defaultValue) {
    return convertQuietly(String.class, value, defaultValue);
  }
  
  public static String toStr(Object value) {
    return toStr(value, null);
  }
  
  public static String[] toStrArray(Object value) {
    return convert((Class)String[].class, value);
  }
  
  public static Character toChar(Object value, Character defaultValue) {
    return convertQuietly(Character.class, value, defaultValue);
  }
  
  public static Character toChar(Object value) {
    return toChar(value, null);
  }
  
  public static Character[] toCharArray(Object value) {
    return convert((Class)Character[].class, value);
  }
  
  public static Byte toByte(Object value, Byte defaultValue) {
    return convertQuietly(Byte.class, value, defaultValue);
  }
  
  public static Byte toByte(Object value) {
    return toByte(value, null);
  }
  
  public static Byte[] toByteArray(Object value) {
    return convert((Class)Byte[].class, value);
  }
  
  public static byte[] toPrimitiveByteArray(Object value) {
    return convert((Class)byte[].class, value);
  }
  
  public static Short toShort(Object value, Short defaultValue) {
    return convertQuietly(Short.class, value, defaultValue);
  }
  
  public static Short toShort(Object value) {
    return toShort(value, null);
  }
  
  public static Short[] toShortArray(Object value) {
    return convert((Class)Short[].class, value);
  }
  
  public static Number toNumber(Object value, Number defaultValue) {
    return convertQuietly(Number.class, value, defaultValue);
  }
  
  public static Number toNumber(Object value) {
    return toNumber(value, null);
  }
  
  public static Number[] toNumberArray(Object value) {
    return convert((Class)Number[].class, value);
  }
  
  public static Integer toInt(Object value, Integer defaultValue) {
    return convertQuietly(Integer.class, value, defaultValue);
  }
  
  public static Integer toInt(Object value) {
    return toInt(value, null);
  }
  
  public static Integer[] toIntArray(Object value) {
    return convert((Class)Integer[].class, value);
  }
  
  public static Long toLong(Object value, Long defaultValue) {
    return convertQuietly(Long.class, value, defaultValue);
  }
  
  public static Long toLong(Object value) {
    return toLong(value, null);
  }
  
  public static Long[] toLongArray(Object value) {
    return convert((Class)Long[].class, value);
  }
  
  public static Double toDouble(Object value, Double defaultValue) {
    return convertQuietly(Double.class, value, defaultValue);
  }
  
  public static Double toDouble(Object value) {
    return toDouble(value, null);
  }
  
  public static Double[] toDoubleArray(Object value) {
    return convert((Class)Double[].class, value);
  }
  
  public static Float toFloat(Object value, Float defaultValue) {
    return convertQuietly(Float.class, value, defaultValue);
  }
  
  public static Float toFloat(Object value) {
    return toFloat(value, null);
  }
  
  public static Float[] toFloatArray(Object value) {
    return convert((Class)Float[].class, value);
  }
  
  public static Boolean toBool(Object value, Boolean defaultValue) {
    return convertQuietly(Boolean.class, value, defaultValue);
  }
  
  public static Boolean toBool(Object value) {
    return toBool(value, null);
  }
  
  public static Boolean[] toBooleanArray(Object value) {
    return convert((Class)Boolean[].class, value);
  }
  
  public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
    return convertQuietly(BigInteger.class, value, defaultValue);
  }
  
  public static BigInteger toBigInteger(Object value) {
    return toBigInteger(value, null);
  }
  
  public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
    return convertQuietly(BigDecimal.class, value, defaultValue);
  }
  
  public static BigDecimal toBigDecimal(Object value) {
    return toBigDecimal(value, null);
  }
  
  public static Date toDate(Object value, Date defaultValue) {
    return convertQuietly(Date.class, value, defaultValue);
  }
  
  public static LocalDateTime toLocalDateTime(Object value, LocalDateTime defaultValue) {
    return convertQuietly(LocalDateTime.class, value, defaultValue);
  }
  
  public static LocalDateTime toLocalDateTime(Object value) {
    return toLocalDateTime(value, null);
  }
  
  public static Date toInstant(Object value, Date defaultValue) {
    return convertQuietly(Instant.class, value, defaultValue);
  }
  
  public static Date toDate(Object value) {
    return toDate(value, null);
  }
  
  public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
    return (E)(new EnumConverter(clazz)).convertQuietly(value, defaultValue);
  }
  
  public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
    return toEnum(clazz, value, null);
  }
  
  public static Collection<?> toCollection(Class<?> collectionType, Class<?> elementType, Object value) {
    return (new CollectionConverter(collectionType, elementType)).convert(value, null);
  }
  
  public static List<?> toList(Object value) {
    return convert(List.class, value);
  }
  
  public static <T> List<T> toList(Class<T> elementType, Object value) {
    return (List)toCollection(ArrayList.class, elementType, value);
  }
  
  public static <T> Set<T> toSet(Class<T> elementType, Object value) {
    return (Set)toCollection(HashSet.class, elementType, value);
  }
  
  public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object value) {
    if (value instanceof Map)
      return toMap((Class)value.getClass(), keyType, valueType, value); 
    return toMap((Class)HashMap.class, keyType, valueType, value);
  }
  
  public static <K, V> Map<K, V> toMap(Class<? extends Map> mapType, Class<K> keyType, Class<V> valueType, Object value) {
    return (Map<K, V>)(new MapConverter(mapType, keyType, valueType)).convert(value, null);
  }
  
  public static <T> T convertByClassName(String className, Object value) throws ConvertException {
    return convert(ClassUtil.loadClass(className), value);
  }
  
  public static <T> T convert(Class<T> type, Object value) throws ConvertException {
    return convert(type, value);
  }
  
  public static <T> T convert(TypeReference<T> reference, Object value) throws ConvertException {
    return convert(reference.getType(), value, (T)null);
  }
  
  public static <T> T convert(Type type, Object value) throws ConvertException {
    return convert(type, value, (T)null);
  }
  
  public static <T> T convert(Class<T> type, Object value, T defaultValue) throws ConvertException {
    return convert(type, value, defaultValue);
  }
  
  public static <T> T convert(Type type, Object value, T defaultValue) throws ConvertException {
    return convertWithCheck(type, value, defaultValue, false);
  }
  
  public static <T> T convertQuietly(Type type, Object value) {
    return convertQuietly(type, value, null);
  }
  
  public static <T> T convertQuietly(Type type, Object value, T defaultValue) {
    return convertWithCheck(type, value, defaultValue, true);
  }
  
  public static <T> T convertWithCheck(Type type, Object value, T defaultValue, boolean quietly) {
    ConverterRegistry registry = ConverterRegistry.getInstance();
    try {
      return registry.convert(type, value, defaultValue);
    } catch (Exception e) {
      if (quietly)
        return defaultValue; 
      throw e;
    } 
  }
  
  public static String toSBC(String input) {
    return toSBC(input, null);
  }
  
  public static String toSBC(String input, Set<Character> notConvertSet) {
    if (StrUtil.isEmpty(input))
      return input; 
    char[] c = input.toCharArray();
    for (int i = 0; i < c.length; i++) {
      if (null == notConvertSet || !notConvertSet.contains(Character.valueOf(c[i])))
        if (c[i] == ' ') {
          c[i] = '　';
        } else if (c[i] < '') {
          c[i] = (char)(c[i] + 65248);
        }  
    } 
    return new String(c);
  }
  
  public static String toDBC(String input) {
    return toDBC(input, null);
  }
  
  public static String toDBC(String text, Set<Character> notConvertSet) {
    if (StrUtil.isBlank(text))
      return text; 
    char[] c = text.toCharArray();
    for (int i = 0; i < c.length; i++) {
      if (null == notConvertSet || !notConvertSet.contains(Character.valueOf(c[i])))
        if (c[i] == '　' || c[i] == ' ' || c[i] == ' ' || c[i] == ' ') {
          c[i] = ' ';
        } else if (c[i] > '＀' && c[i] < '｟') {
          c[i] = (char)(c[i] - 65248);
        }  
    } 
    return new String(c);
  }
  
  public static String toHex(String str, Charset charset) {
    return HexUtil.encodeHexStr(str, charset);
  }
  
  public static String toHex(byte[] bytes) {
    return HexUtil.encodeHexStr(bytes);
  }
  
  public static byte[] hexToBytes(String src) {
    return HexUtil.decodeHex(src.toCharArray());
  }
  
  public static String hexToStr(String hexStr, Charset charset) {
    return HexUtil.decodeHexStr(hexStr, charset);
  }
  
  public static String strToUnicode(String strText) {
    return UnicodeUtil.toUnicode(strText);
  }
  
  public static String unicodeToStr(String unicode) {
    return UnicodeUtil.toString(unicode);
  }
  
  public static String convertCharset(String str, String sourceCharset, String destCharset) {
    if (StrUtil.hasBlank(new CharSequence[] { str, sourceCharset, destCharset }))
      return str; 
    return CharsetUtil.convert(str, sourceCharset, destCharset);
  }
  
  public static long convertTime(long sourceDuration, TimeUnit sourceUnit, TimeUnit destUnit) {
    Assert.notNull(sourceUnit, "sourceUnit is null !", new Object[0]);
    Assert.notNull(destUnit, "destUnit is null !", new Object[0]);
    return destUnit.convert(sourceDuration, sourceUnit);
  }
  
  public static Class<?> wrap(Class<?> clazz) {
    return BasicType.wrap(clazz);
  }
  
  public static Class<?> unWrap(Class<?> clazz) {
    return BasicType.unWrap(clazz);
  }
  
  public static String numberToWord(Number number) {
    return NumberWordFormatter.format(number);
  }
  
  public static String numberToSimple(Number number) {
    return NumberWordFormatter.formatSimple(number.longValue());
  }
  
  public static String numberToChinese(double number, boolean isUseTraditional) {
    return NumberChineseFormatter.format(number, isUseTraditional);
  }
  
  public static int chineseToNumber(String number) {
    return NumberChineseFormatter.chineseToNumber(number);
  }
  
  public static String digitToChinese(Number n) {
    if (null == n)
      return "零"; 
    return NumberChineseFormatter.format(n.doubleValue(), true, true);
  }
  
  public static BigDecimal chineseMoneyToNumber(String chineseMoneyAmount) {
    return NumberChineseFormatter.chineseMoneyToNumber(chineseMoneyAmount);
  }
  
  public static byte intToByte(int intValue) {
    return (byte)intValue;
  }
  
  public static int byteToUnsignedInt(byte byteValue) {
    return byteValue & 0xFF;
  }
  
  public static short bytesToShort(byte[] bytes) {
    return ByteUtil.bytesToShort(bytes);
  }
  
  public static byte[] shortToBytes(short shortValue) {
    return ByteUtil.shortToBytes(shortValue);
  }
  
  public static int bytesToInt(byte[] bytes) {
    return ByteUtil.bytesToInt(bytes);
  }
  
  public static byte[] intToBytes(int intValue) {
    return ByteUtil.intToBytes(intValue);
  }
  
  public static byte[] longToBytes(long longValue) {
    return ByteUtil.longToBytes(longValue);
  }
  
  public static long bytesToLong(byte[] bytes) {
    return ByteUtil.bytesToLong(bytes);
  }
}
