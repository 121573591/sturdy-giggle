package cn.hutool.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.UniqueKeySet;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.Matcher;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrJoiner;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayUtil extends PrimitiveArrayUtil {
  public static <T> boolean isEmpty(T[] array) {
    return (array == null || array.length == 0);
  }
  
  public static <T> T[] defaultIfEmpty(T[] array, T[] defaultArray) {
    return isEmpty(array) ? defaultArray : array;
  }
  
  public static boolean isEmpty(Object array) {
    if (array != null) {
      if (isArray(array))
        return (0 == Array.getLength(array)); 
      return false;
    } 
    return true;
  }
  
  public static <T> boolean isNotEmpty(T[] array) {
    return (null != array && array.length != 0);
  }
  
  public static boolean isNotEmpty(Object array) {
    return !isEmpty(array);
  }
  
  public static <T> boolean hasNull(T... array) {
    if (isNotEmpty(array))
      for (T element : array) {
        if (ObjectUtil.isNull(element))
          return true; 
      }  
    return (array == null);
  }
  
  public static <T> boolean isAllNull(T... array) {
    return (null == firstNonNull((Object[])array));
  }
  
  public static <T> T firstNonNull(T... array) {
    return firstMatch(ObjectUtil::isNotNull, array);
  }
  
  public static <T> T firstMatch(Matcher<T> matcher, T... array) {
    int index = matchIndex(matcher, array);
    if (index < 0)
      return null; 
    return array[index];
  }
  
  public static <T> int matchIndex(Matcher<T> matcher, T... array) {
    return matchIndex(matcher, 0, array);
  }
  
  public static <T> int matchIndex(Matcher<T> matcher, int beginIndexInclude, T... array) {
    Assert.notNull(matcher, "Matcher must be not null !", new Object[0]);
    if (isNotEmpty(array))
      for (int i = beginIndexInclude; i < array.length; i++) {
        if (matcher.match(array[i]))
          return i; 
      }  
    return -1;
  }
  
  public static <T> T[] newArray(Class<?> componentType, int newSize) {
    return (T[])Array.newInstance(componentType, newSize);
  }
  
  public static Object[] newArray(int newSize) {
    return new Object[newSize];
  }
  
  public static Class<?> getComponentType(Object array) {
    return (null == array) ? null : array.getClass().getComponentType();
  }
  
  public static Class<?> getComponentType(Class<?> arrayClass) {
    return (null == arrayClass) ? null : arrayClass.getComponentType();
  }
  
  public static Class<?> getArrayType(Class<?> componentType) {
    return Array.newInstance(componentType, 0).getClass();
  }
  
  public static Object[] cast(Class<?> type, Object arrayObj) throws NullPointerException, IllegalArgumentException {
    if (null == arrayObj)
      throw new NullPointerException("Argument [arrayObj] is null !"); 
    if (false == arrayObj.getClass().isArray())
      throw new IllegalArgumentException("Argument [arrayObj] is not array !"); 
    if (null == type)
      return (Object[])arrayObj; 
    Class<?> componentType = type.isArray() ? type.getComponentType() : type;
    Object[] array = (Object[])arrayObj;
    Object[] result = newArray(componentType, array.length);
    System.arraycopy(array, 0, result, 0, array.length);
    return result;
  }
  
  @SafeVarargs
  public static <T> T[] append(T[] buffer, T... newElements) {
    if (isEmpty(buffer))
      return newElements; 
    return insert(buffer, buffer.length, newElements);
  }
  
  @SafeVarargs
  public static <T> Object append(Object array, T... newElements) {
    if (isEmpty(array))
      return newElements; 
    return insert(array, length(array), newElements);
  }
  
  public static <T> T[] setOrAppend(T[] buffer, int index, T value) {
    if (index < buffer.length) {
      Array.set(buffer, index, value);
      return buffer;
    } 
    if (isEmpty(buffer)) {
      T[] values = newArray(value.getClass(), 1);
      values[0] = value;
      return append(buffer, values);
    } 
    return append(buffer, (T[])new Object[] { value });
  }
  
  public static Object setOrAppend(Object array, int index, Object value) {
    if (index < length(array)) {
      Array.set(array, index, value);
      return array;
    } 
    return append(array, new Object[] { value });
  }
  
  public static <T> T[] replace(T[] buffer, int index, T... values) {
    if (isEmpty(values))
      return buffer; 
    if (isEmpty(buffer))
      return values; 
    if (index < 0)
      return insert(buffer, 0, values); 
    if (index >= buffer.length)
      return append(buffer, values); 
    if (buffer.length >= values.length + index) {
      System.arraycopy(values, 0, buffer, index, values.length);
      return buffer;
    } 
    int newArrayLength = index + values.length;
    T[] result = newArray(buffer.getClass().getComponentType(), newArrayLength);
    System.arraycopy(buffer, 0, result, 0, index);
    System.arraycopy(values, 0, result, index, values.length);
    return result;
  }
  
  public static <T> T[] insert(T[] buffer, int index, T... newElements) {
    return (T[])insert(buffer, index, newElements);
  }
  
  public static <T> Object insert(Object array, int index, T... newElements) {
    if (isEmpty(newElements))
      return array; 
    if (isEmpty(array))
      return newElements; 
    int len = length(array);
    if (index < 0)
      index = index % len + len; 
    Class<?> originComponentType = array.getClass().getComponentType();
    Object newEleArr = newElements;
    if (originComponentType.isPrimitive())
      newEleArr = Convert.convert(array.getClass(), newElements); 
    Object result = Array.newInstance(originComponentType, Math.max(len, index) + newElements.length);
    System.arraycopy(array, 0, result, 0, Math.min(len, index));
    System.arraycopy(newEleArr, 0, result, index, newElements.length);
    if (index < len)
      System.arraycopy(array, index, result, index + newElements.length, len - index); 
    return result;
  }
  
  public static <T> T[] resize(T[] data, int newSize, Class<?> componentType) {
    if (newSize < 0)
      return data; 
    T[] newArray = newArray(componentType, newSize);
    if (newSize > 0 && isNotEmpty(data))
      System.arraycopy(data, 0, newArray, 0, Math.min(data.length, newSize)); 
    return newArray;
  }
  
  public static Object resize(Object array, int newSize) {
    if (newSize < 0)
      return array; 
    if (null == array)
      return null; 
    int length = length(array);
    Object newArray = Array.newInstance(array.getClass().getComponentType(), newSize);
    if (newSize > 0 && isNotEmpty(array))
      System.arraycopy(array, 0, newArray, 0, Math.min(length, newSize)); 
    return newArray;
  }
  
  public static <T> T[] resize(T[] buffer, int newSize) {
    return resize(buffer, newSize, buffer.getClass().getComponentType());
  }
  
  @SafeVarargs
  public static <T> T[] addAll(T[]... arrays) {
    if (arrays.length == 1)
      return arrays[0]; 
    int length = 0;
    for (T[] array : arrays) {
      if (isNotEmpty(array))
        length += array.length; 
    } 
    T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);
    length = 0;
    for (T[] array : arrays) {
      if (isNotEmpty(array)) {
        System.arraycopy(array, 0, result, length, array.length);
        length += array.length;
      } 
    } 
    return result;
  }
  
  public static Object copy(Object src, int srcPos, Object dest, int destPos, int length) {
    System.arraycopy(src, srcPos, dest, destPos, length);
    return dest;
  }
  
  public static Object copy(Object src, Object dest, int length) {
    System.arraycopy(src, 0, dest, 0, length);
    return dest;
  }
  
  public static <T> T[] clone(T[] array) {
    if (array == null)
      return null; 
    return (T[])array.clone();
  }
  
  public static <T> T clone(T obj) {
    if (null == obj)
      return null; 
    if (isArray(obj)) {
      Object result;
      Class<?> componentType = obj.getClass().getComponentType();
      if (componentType.isPrimitive()) {
        int length = Array.getLength(obj);
        result = Array.newInstance(componentType, length);
        while (length-- > 0)
          Array.set(result, length, Array.get(obj, length)); 
      } else {
        result = ((Object[])obj).clone();
      } 
      return (T)result;
    } 
    return null;
  }
  
  public static <T> T[] edit(T[] array, Editor<T> editor) {
    if (null == editor)
      return array; 
    ArrayList<T> list = new ArrayList<>(array.length);
    for (T t : array) {
      T modified = (T)editor.edit(t);
      if (null != modified)
        list.add(modified); 
    } 
    T[] result = newArray(array.getClass().getComponentType(), list.size());
    return list.toArray(result);
  }
  
  public static <T> T[] filter(T[] array, Filter<T> filter) {
    if (null == array || null == filter)
      return array; 
    return edit(array, t -> filter.accept(t) ? t : null);
  }
  
  public static <T> T[] removeNull(T[] array) {
    return edit(array, t -> t);
  }
  
  public static <T extends CharSequence> T[] removeEmpty(T[] array) {
    return (T[])filter((CharSequence[])array, CharSequenceUtil::isNotEmpty);
  }
  
  public static <T extends CharSequence> T[] removeBlank(T[] array) {
    return (T[])filter((CharSequence[])array, CharSequenceUtil::isNotBlank);
  }
  
  public static String[] nullToEmpty(String[] array) {
    return edit(array, t -> (null == t) ? "" : t);
  }
  
  public static <K, V> Map<K, V> zip(K[] keys, V[] values, boolean isOrder) {
    if (isEmpty(keys) || isEmpty(values))
      return null; 
    int size = Math.min(keys.length, values.length);
    Map<K, V> map = MapUtil.newHashMap(size, isOrder);
    for (int i = 0; i < size; i++)
      map.put(keys[i], values[i]); 
    return map;
  }
  
  public static <K, V> Map<K, V> zip(K[] keys, V[] values) {
    return zip(keys, values, false);
  }
  
  public static <T> int indexOf(T[] array, Object value, int beginIndexInclude) {
    return matchIndex(obj -> ObjectUtil.equal(value, obj), beginIndexInclude, array);
  }
  
  public static <T> int indexOf(T[] array, Object value) {
    return matchIndex(obj -> ObjectUtil.equal(value, obj), array);
  }
  
  public static int indexOfIgnoreCase(CharSequence[] array, CharSequence value) {
    if (null != array)
      for (int i = 0; i < array.length; i++) {
        if (StrUtil.equalsIgnoreCase(array[i], value))
          return i; 
      }  
    return -1;
  }
  
  public static <T> int lastIndexOf(T[] array, Object value) {
    if (isEmpty(array))
      return -1; 
    return lastIndexOf(array, value, array.length - 1);
  }
  
  public static <T> int lastIndexOf(T[] array, Object value, int endInclude) {
    if (isNotEmpty(array))
      for (int i = endInclude; i >= 0; i--) {
        if (ObjectUtil.equal(value, array[i]))
          return i; 
      }  
    return -1;
  }
  
  public static <T> boolean contains(T[] array, T value) {
    return (indexOf(array, value) > -1);
  }
  
  public static <T> boolean containsAny(T[] array, T... values) {
    for (T value : values) {
      if (contains(array, value))
        return true; 
    } 
    return false;
  }
  
  public static <T> boolean containsAll(T[] array, T... values) {
    for (T value : values) {
      if (false == contains(array, value))
        return false; 
    } 
    return true;
  }
  
  public static boolean containsIgnoreCase(CharSequence[] array, CharSequence value) {
    return (indexOfIgnoreCase(array, value) > -1);
  }
  
  public static Object[] wrap(Object obj) {
    if (null == obj)
      return null; 
    if (isArray(obj))
      try {
        return (Object[])obj;
      } catch (Exception e) {
        String className = obj.getClass().getComponentType().getName();
        switch (className) {
          case "long":
            return (Object[])wrap((long[])obj);
          case "int":
            return (Object[])wrap((int[])obj);
          case "short":
            return (Object[])wrap((short[])obj);
          case "char":
            return (Object[])wrap((char[])obj);
          case "byte":
            return (Object[])wrap((byte[])obj);
          case "boolean":
            return (Object[])wrap((boolean[])obj);
          case "float":
            return (Object[])wrap((float[])obj);
          case "double":
            return (Object[])wrap((double[])obj);
        } 
        throw new UtilException(e);
      }  
    throw new UtilException(StrUtil.format("[{}] is not Array!", new Object[] { obj.getClass() }));
  }
  
  public static boolean isArray(Object obj) {
    return (null != obj && obj.getClass().isArray());
  }
  
  public static <T> T get(Object array, int index) {
    if (null == array)
      return null; 
    if (index < 0)
      index += Array.getLength(array); 
    try {
      return (T)Array.get(array, index);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    } 
  }
  
  public static <T> T[] getAny(Object array, int... indexes) {
    if (null == array)
      return null; 
    if (null == indexes)
      return newArray(array.getClass().getComponentType(), 0); 
    T[] result = newArray(array.getClass().getComponentType(), indexes.length);
    for (int i = 0; i < indexes.length; i++)
      result[i] = get(array, indexes[i]); 
    return result;
  }
  
  public static <T> T[] sub(T[] array, int start, int end) {
    int length = length(array);
    if (start < 0)
      start += length; 
    if (end < 0)
      end += length; 
    if (start == length)
      return newArray(array.getClass().getComponentType(), 0); 
    if (start > end) {
      int tmp = start;
      start = end;
      end = tmp;
    } 
    if (end > length) {
      if (start >= length)
        return newArray(array.getClass().getComponentType(), 0); 
      end = length;
    } 
    return Arrays.copyOfRange(array, start, end);
  }
  
  public static Object[] sub(Object array, int start, int end) {
    return sub(array, start, end, 1);
  }
  
  public static Object[] sub(Object array, int start, int end, int step) {
    int length = length(array);
    if (start < 0)
      start += length; 
    if (end < 0)
      end += length; 
    if (start == length)
      return new Object[0]; 
    if (start > end) {
      int tmp = start;
      start = end;
      end = tmp;
    } 
    if (end > length) {
      if (start >= length)
        return new Object[0]; 
      end = length;
    } 
    if (step <= 1)
      step = 1; 
    ArrayList<Object> list = new ArrayList();
    int i;
    for (i = start; i < end; i += step)
      list.add(get(array, i)); 
    return list.toArray();
  }
  
  public static String toString(Object obj) {
    if (null == obj)
      return null; 
    if (obj instanceof long[])
      return Arrays.toString((long[])obj); 
    if (obj instanceof int[])
      return Arrays.toString((int[])obj); 
    if (obj instanceof short[])
      return Arrays.toString((short[])obj); 
    if (obj instanceof char[])
      return Arrays.toString((char[])obj); 
    if (obj instanceof byte[])
      return Arrays.toString((byte[])obj); 
    if (obj instanceof boolean[])
      return Arrays.toString((boolean[])obj); 
    if (obj instanceof float[])
      return Arrays.toString((float[])obj); 
    if (obj instanceof double[])
      return Arrays.toString((double[])obj); 
    if (isArray(obj))
      try {
        return Arrays.deepToString((Object[])obj);
      } catch (Exception exception) {} 
    return obj.toString();
  }
  
  public static int length(Object array) throws IllegalArgumentException {
    if (null == array)
      return 0; 
    return Array.getLength(array);
  }
  
  public static <T> String join(T[] array, CharSequence conjunction) {
    return join(array, conjunction, (String)null, (String)null);
  }
  
  public static <T> String join(T[] array, CharSequence delimiter, String prefix, String suffix) {
    if (null == array)
      return null; 
    return StrJoiner.of(delimiter, prefix, suffix)
      
      .setWrapElement(true)
      .append((Object[])array)
      .toString();
  }
  
  public static <T> String join(T[] array, CharSequence conjunction, Editor<T> editor) {
    return StrJoiner.of(conjunction).append((Object[])array, t -> String.valueOf(editor.edit(t))).toString();
  }
  
  public static String join(Object array, CharSequence conjunction) {
    if (null == array)
      return null; 
    if (false == isArray(array))
      throw new IllegalArgumentException(StrUtil.format("[{}] is not a Array!", new Object[] { array.getClass() })); 
    return StrJoiner.of(conjunction).append(array).toString();
  }
  
  public static byte[] toArray(ByteBuffer bytebuffer) {
    if (bytebuffer.hasArray())
      return Arrays.copyOfRange(bytebuffer.array(), bytebuffer.position(), bytebuffer.limit()); 
    int oldPosition = bytebuffer.position();
    bytebuffer.position(0);
    int size = bytebuffer.limit();
    byte[] buffers = new byte[size];
    bytebuffer.get(buffers);
    bytebuffer.position(oldPosition);
    return buffers;
  }
  
  public static <T> T[] toArray(Iterator<T> iterator, Class<T> componentType) {
    return toArray(CollUtil.newArrayList(iterator), componentType);
  }
  
  public static <T> T[] toArray(Iterable<T> iterable, Class<T> componentType) {
    return toArray(CollectionUtil.toCollection(iterable), componentType);
  }
  
  public static <T> T[] toArray(Collection<T> collection, Class<T> componentType) {
    return collection.toArray(newArray(componentType, 0));
  }
  
  public static <T> T[] remove(T[] array, int index) throws IllegalArgumentException {
    return (T[])remove(array, index);
  }
  
  public static <T> T[] removeEle(T[] array, T element) throws IllegalArgumentException {
    return remove(array, indexOf(array, element));
  }
  
  public static <T> T[] reverse(T[] array, int startIndexInclusive, int endIndexExclusive) {
    if (isEmpty(array))
      return array; 
    int i = Math.max(startIndexInclusive, 0);
    int j = Math.min(array.length, endIndexExclusive) - 1;
    while (j > i) {
      T tmp = array[j];
      array[j] = array[i];
      array[i] = tmp;
      j--;
      i++;
    } 
    return array;
  }
  
  public static <T> T[] reverse(T[] array) {
    return reverse(array, 0, array.length);
  }
  
  public static <T extends Comparable<? super T>> T min(T[] numberArray) {
    return min(numberArray, (Comparator<T>)null);
  }
  
  public static <T extends Comparable<? super T>> T min(T[] numberArray, Comparator<T> comparator) {
    if (isEmpty(numberArray))
      throw new IllegalArgumentException("Number array must not empty !"); 
    T min = numberArray[0];
    for (T t : numberArray) {
      if (CompareUtil.compare(min, t, comparator) > 0)
        min = t; 
    } 
    return min;
  }
  
  public static <T extends Comparable<? super T>> T max(T[] numberArray) {
    return max(numberArray, (Comparator<T>)null);
  }
  
  public static <T extends Comparable<? super T>> T max(T[] numberArray, Comparator<T> comparator) {
    if (isEmpty(numberArray))
      throw new IllegalArgumentException("Number array must not empty !"); 
    T max = numberArray[0];
    for (int i = 1; i < numberArray.length; i++) {
      if (CompareUtil.compare(max, numberArray[i], comparator) < 0)
        max = numberArray[i]; 
    } 
    return max;
  }
  
  public static <T> T[] shuffle(T[] array) {
    return shuffle(array, RandomUtil.getRandom());
  }
  
  public static <T> T[] shuffle(T[] array, Random random) {
    if (array == null || random == null || array.length <= 1)
      return array; 
    for (int i = array.length; i > 1; i--)
      swap(array, i - 1, random.nextInt(i)); 
    return array;
  }
  
  public static <T> T[] swap(T[] array, int index1, int index2) {
    if (isEmpty(array))
      throw new IllegalArgumentException("Array must not empty !"); 
    T tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
    return array;
  }
  
  public static Object swap(Object array, int index1, int index2) {
    if (isEmpty(array))
      throw new IllegalArgumentException("Array must not empty !"); 
    Object tmp = get(array, index1);
    Array.set(array, index1, Array.get(array, index2));
    Array.set(array, index2, tmp);
    return array;
  }
  
  public static int emptyCount(Object... args) {
    int count = 0;
    if (isNotEmpty(args))
      for (Object element : args) {
        if (ObjectUtil.isEmpty(element))
          count++; 
      }  
    return count;
  }
  
  public static boolean hasEmpty(Object... args) {
    if (isNotEmpty(args))
      for (Object element : args) {
        if (ObjectUtil.isEmpty(element))
          return true; 
      }  
    return false;
  }
  
  public static boolean isAllEmpty(Object... args) {
    for (Object obj : args) {
      if (false == ObjectUtil.isEmpty(obj))
        return false; 
    } 
    return true;
  }
  
  public static boolean isAllNotEmpty(Object... args) {
    return (false == hasEmpty(args));
  }
  
  public static <T> boolean isAllNotNull(T... array) {
    return (false == hasNull(array));
  }
  
  public static <T> T[] distinct(T[] array) {
    if (isEmpty(array))
      return array; 
    Set<T> set = new LinkedHashSet<>(array.length, 1.0F);
    Collections.addAll(set, array);
    return toArray(set, (Class)getComponentType(array));
  }
  
  public static <T, K> T[] distinct(T[] array, Function<T, K> uniqueGenerator, boolean override) {
    if (isEmpty(array))
      return array; 
    UniqueKeySet<K, T> set = new UniqueKeySet(true, uniqueGenerator);
    if (override) {
      Collections.addAll((Collection)set, (K[])array);
    } else {
      for (T t : array)
        set.addIfAbsent(t); 
    } 
    return toArray((Collection)set, (Class)getComponentType(array));
  }
  
  public static <T, R> R[] map(T[] array, Class<R> targetComponentType, Function<? super T, ? extends R> func) {
    R[] result = newArray(targetComponentType, array.length);
    for (int i = 0; i < array.length; i++)
      result[i] = func.apply(array[i]); 
    return result;
  }
  
  public static <T, R> R[] map(Object array, Class<R> targetComponentType, Function<? super T, ? extends R> func) {
    int length = length(array);
    R[] result = newArray(targetComponentType, length);
    for (int i = 0; i < length; i++)
      result[i] = func.apply(get(array, i)); 
    return result;
  }
  
  public static <T, R> List<R> map(T[] array, Function<? super T, ? extends R> func) {
    return (List<R>)Arrays.<T>stream(array).<R>map(func).collect(Collectors.toList());
  }
  
  public static <T, R> Set<R> mapToSet(T[] array, Function<? super T, ? extends R> func) {
    return (Set<R>)Arrays.<T>stream(array).<R>map(func).collect(Collectors.toSet());
  }
  
  public static boolean equals(Object array1, Object array2) {
    if (array1 == array2)
      return true; 
    if (hasNull(new Object[] { array1, array2 }))
      return false; 
    Assert.isTrue(isArray(array1), "First is not a Array !", new Object[0]);
    Assert.isTrue(isArray(array2), "Second is not a Array !", new Object[0]);
    if (array1 instanceof long[])
      return Arrays.equals((long[])array1, (long[])array2); 
    if (array1 instanceof int[])
      return Arrays.equals((int[])array1, (int[])array2); 
    if (array1 instanceof short[])
      return Arrays.equals((short[])array1, (short[])array2); 
    if (array1 instanceof char[])
      return Arrays.equals((char[])array1, (char[])array2); 
    if (array1 instanceof byte[])
      return Arrays.equals((byte[])array1, (byte[])array2); 
    if (array1 instanceof double[])
      return Arrays.equals((double[])array1, (double[])array2); 
    if (array1 instanceof float[])
      return Arrays.equals((float[])array1, (float[])array2); 
    if (array1 instanceof boolean[])
      return Arrays.equals((boolean[])array1, (boolean[])array2); 
    return Arrays.deepEquals((Object[])array1, (Object[])array2);
  }
  
  public static <T> boolean isSub(T[] array, T[] subArray) {
    return (indexOfSub(array, subArray) > -1);
  }
  
  public static <T> int indexOfSub(T[] array, T[] subArray) {
    return indexOfSub(array, 0, subArray);
  }
  
  public static <T> int indexOfSub(T[] array, int beginInclude, T[] subArray) {
    if (isEmpty(array) || isEmpty(subArray) || subArray.length > array.length)
      return -1; 
    int firstIndex = indexOf(array, subArray[0], beginInclude);
    if (firstIndex < 0 || firstIndex + subArray.length > array.length)
      return -1; 
    for (int i = 0; i < subArray.length; i++) {
      if (false == ObjectUtil.equal(array[i + firstIndex], subArray[i]))
        return indexOfSub(array, firstIndex + 1, subArray); 
    } 
    return firstIndex;
  }
  
  public static <T> int lastIndexOfSub(T[] array, T[] subArray) {
    if (isEmpty(array) || isEmpty(subArray))
      return -1; 
    return lastIndexOfSub(array, array.length - 1, subArray);
  }
  
  public static <T> int lastIndexOfSub(T[] array, int endInclude, T[] subArray) {
    if (isEmpty(array) || isEmpty(subArray) || subArray.length > array.length || endInclude < 0)
      return -1; 
    int firstIndex = lastIndexOf(array, subArray[0]);
    if (firstIndex < 0 || firstIndex + subArray.length > array.length)
      return -1; 
    for (int i = 0; i < subArray.length; i++) {
      if (false == ObjectUtil.equal(array[i + firstIndex], subArray[i]))
        return lastIndexOfSub(array, firstIndex - 1, subArray); 
    } 
    return firstIndex;
  }
  
  public static <T> boolean isSorted(T[] array, Comparator<? super T> comparator) {
    if (array == null || comparator == null)
      return false; 
    for (int i = 0; i < array.length - 1; i++) {
      if (comparator.compare(array[i], array[i + 1]) > 0)
        return false; 
    } 
    return true;
  }
  
  public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
    return isSortedASC(array);
  }
  
  public static <T extends Comparable<? super T>> boolean isSortedASC(T[] array) {
    if (array == null)
      return false; 
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i].compareTo(array[i + 1]) > 0)
        return false; 
    } 
    return true;
  }
  
  public static <T extends Comparable<? super T>> boolean isSortedDESC(T[] array) {
    if (array == null)
      return false; 
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i].compareTo(array[i + 1]) < 0)
        return false; 
    } 
    return true;
  }
}
