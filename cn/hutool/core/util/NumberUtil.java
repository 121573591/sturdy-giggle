package cn.hutool.core.util;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.text.CharSequenceUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NumberUtil {
  private static final int DEFAULT_DIV_SCALE = 10;
  
  private static final long[] FACTORIALS = new long[] { 
      1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 
      3628800L, 39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 
      2432902008176640000L };
  
  public static double add(float v1, float v2) {
    return add(new String[] { Float.toString(v1), Float.toString(v2) }).doubleValue();
  }
  
  public static double add(float v1, double v2) {
    return add(new String[] { Float.toString(v1), Double.toString(v2) }).doubleValue();
  }
  
  public static double add(double v1, float v2) {
    return add(new String[] { Double.toString(v1), Float.toString(v2) }).doubleValue();
  }
  
  public static double add(double v1, double v2) {
    return add(new String[] { Double.toString(v1), Double.toString(v2) }).doubleValue();
  }
  
  public static double add(Double v1, Double v2) {
    return add(v1, v2).doubleValue();
  }
  
  public static BigDecimal add(Number v1, Number v2) {
    return add(new Number[] { v1, v2 });
  }
  
  public static BigDecimal add(Number... values) {
    if (ArrayUtil.isEmpty(values))
      return BigDecimal.ZERO; 
    Number value = values[0];
    BigDecimal result = toBigDecimal(value);
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      if (null != value)
        result = result.add(toBigDecimal(value)); 
    } 
    return result;
  }
  
  public static BigDecimal add(String... values) {
    if (ArrayUtil.isEmpty(values))
      return BigDecimal.ZERO; 
    String value = values[0];
    BigDecimal result = toBigDecimal(value);
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      if (StrUtil.isNotBlank(value))
        result = result.add(toBigDecimal(value)); 
    } 
    return result;
  }
  
  public static BigDecimal add(BigDecimal... values) {
    if (ArrayUtil.isEmpty(values))
      return BigDecimal.ZERO; 
    BigDecimal value = values[0];
    BigDecimal result = toBigDecimal(value);
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      if (null != value)
        result = result.add(value); 
    } 
    return result;
  }
  
  public static double sub(float v1, float v2) {
    return sub(new String[] { Float.toString(v1), Float.toString(v2) }).doubleValue();
  }
  
  public static double sub(float v1, double v2) {
    return sub(new String[] { Float.toString(v1), Double.toString(v2) }).doubleValue();
  }
  
  public static double sub(double v1, float v2) {
    return sub(new String[] { Double.toString(v1), Float.toString(v2) }).doubleValue();
  }
  
  public static double sub(double v1, double v2) {
    return sub(new String[] { Double.toString(v1), Double.toString(v2) }).doubleValue();
  }
  
  public static double sub(Double v1, Double v2) {
    return sub(v1, v2).doubleValue();
  }
  
  public static BigDecimal sub(Number v1, Number v2) {
    return sub(new Number[] { v1, v2 });
  }
  
  public static BigDecimal sub(Number... values) {
    if (ArrayUtil.isEmpty(values))
      return BigDecimal.ZERO; 
    Number value = values[0];
    BigDecimal result = toBigDecimal(value);
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      if (null != value)
        result = result.subtract(toBigDecimal(value)); 
    } 
    return result;
  }
  
  public static BigDecimal sub(String... values) {
    if (ArrayUtil.isEmpty(values))
      return BigDecimal.ZERO; 
    String value = values[0];
    BigDecimal result = toBigDecimal(value);
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      if (StrUtil.isNotBlank(value))
        result = result.subtract(toBigDecimal(value)); 
    } 
    return result;
  }
  
  public static BigDecimal sub(BigDecimal... values) {
    if (ArrayUtil.isEmpty(values))
      return BigDecimal.ZERO; 
    BigDecimal value = values[0];
    BigDecimal result = toBigDecimal(value);
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      if (null != value)
        result = result.subtract(value); 
    } 
    return result;
  }
  
  public static double mul(float v1, float v2) {
    return mul(Float.toString(v1), Float.toString(v2)).doubleValue();
  }
  
  public static double mul(float v1, double v2) {
    return mul(Float.toString(v1), Double.toString(v2)).doubleValue();
  }
  
  public static double mul(double v1, float v2) {
    return mul(Double.toString(v1), Float.toString(v2)).doubleValue();
  }
  
  public static double mul(double v1, double v2) {
    return mul(Double.toString(v1), Double.toString(v2)).doubleValue();
  }
  
  public static double mul(Double v1, Double v2) {
    return mul(v1, v2).doubleValue();
  }
  
  public static BigDecimal mul(Number v1, Number v2) {
    return mul(new Number[] { v1, v2 });
  }
  
  public static BigDecimal mul(Number... values) {
    if (ArrayUtil.isEmpty(values) || ArrayUtil.hasNull(values))
      return BigDecimal.ZERO; 
    Number value = values[0];
    BigDecimal result = toBigDecimal(value.toString());
    for (int i = 1; i < values.length; i++) {
      value = values[i];
      result = result.multiply(toBigDecimal(value.toString()));
    } 
    return result;
  }
  
  public static BigDecimal mul(String v1, String v2) {
    return mul(toBigDecimal(v1), toBigDecimal(v2));
  }
  
  public static BigDecimal mul(String... values) {
    if (ArrayUtil.isEmpty(values) || ArrayUtil.hasNull(values))
      return BigDecimal.ZERO; 
    BigDecimal result = toBigDecimal(values[0]);
    for (int i = 1; i < values.length; i++)
      result = result.multiply(toBigDecimal(values[i])); 
    return result;
  }
  
  public static BigDecimal mul(BigDecimal... values) {
    if (ArrayUtil.isEmpty(values) || ArrayUtil.hasNull(values))
      return BigDecimal.ZERO; 
    BigDecimal result = values[0];
    for (int i = 1; i < values.length; i++)
      result = result.multiply(values[i]); 
    return result;
  }
  
  public static double div(float v1, float v2) {
    return div(v1, v2, 10);
  }
  
  public static double div(float v1, double v2) {
    return div(v1, v2, 10);
  }
  
  public static double div(double v1, float v2) {
    return div(v1, v2, 10);
  }
  
  public static double div(double v1, double v2) {
    return div(v1, v2, 10);
  }
  
  public static double div(Double v1, Double v2) {
    return div(v1, v2, 10);
  }
  
  public static BigDecimal div(Number v1, Number v2) {
    return div(v1, v2, 10);
  }
  
  public static BigDecimal div(String v1, String v2) {
    return div(v1, v2, 10);
  }
  
  public static double div(float v1, float v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static double div(float v1, double v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static double div(double v1, float v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static double div(double v1, double v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static double div(Double v1, Double v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static BigDecimal div(Number v1, Number v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static BigDecimal div(String v1, String v2, int scale) {
    return div(v1, v2, scale, RoundingMode.HALF_UP);
  }
  
  public static double div(float v1, float v2, int scale, RoundingMode roundingMode) {
    return div(Float.toString(v1), Float.toString(v2), scale, roundingMode).doubleValue();
  }
  
  public static double div(float v1, double v2, int scale, RoundingMode roundingMode) {
    return div(Float.toString(v1), Double.toString(v2), scale, roundingMode).doubleValue();
  }
  
  public static double div(double v1, float v2, int scale, RoundingMode roundingMode) {
    return div(Double.toString(v1), Float.toString(v2), scale, roundingMode).doubleValue();
  }
  
  public static double div(double v1, double v2, int scale, RoundingMode roundingMode) {
    return div(Double.toString(v1), Double.toString(v2), scale, roundingMode).doubleValue();
  }
  
  public static double div(Double v1, Double v2, int scale, RoundingMode roundingMode) {
    return div(v1, v2, scale, roundingMode).doubleValue();
  }
  
  public static BigDecimal div(Number v1, Number v2, int scale, RoundingMode roundingMode) {
    if (v1 instanceof BigDecimal && v2 instanceof BigDecimal)
      return div((BigDecimal)v1, (BigDecimal)v2, scale, roundingMode); 
    return div(StrUtil.toStringOrNull(v1), StrUtil.toStringOrNull(v2), scale, roundingMode);
  }
  
  public static BigDecimal div(String v1, String v2, int scale, RoundingMode roundingMode) {
    return div(toBigDecimal(v1), toBigDecimal(v2), scale, roundingMode);
  }
  
  public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale, RoundingMode roundingMode) {
    Assert.notNull(v2, "Divisor must be not null !", new Object[0]);
    if (null == v1)
      return BigDecimal.ZERO; 
    if (scale < 0)
      scale = -scale; 
    return v1.divide(v2, scale, roundingMode);
  }
  
  public static int ceilDiv(int v1, int v2) {
    return (int)Math.ceil(v1 / v2);
  }
  
  public static BigDecimal round(double v, int scale) {
    return round(v, scale, RoundingMode.HALF_UP);
  }
  
  public static String roundStr(double v, int scale) {
    return round(v, scale).toPlainString();
  }
  
  public static BigDecimal round(String numberStr, int scale) {
    return round(numberStr, scale, RoundingMode.HALF_UP);
  }
  
  public static BigDecimal round(BigDecimal number, int scale) {
    return round(number, scale, RoundingMode.HALF_UP);
  }
  
  public static String roundStr(String numberStr, int scale) {
    return round(numberStr, scale).toPlainString();
  }
  
  public static BigDecimal round(double v, int scale, RoundingMode roundingMode) {
    return round(Double.toString(v), scale, roundingMode);
  }
  
  public static String roundStr(double v, int scale, RoundingMode roundingMode) {
    return round(v, scale, roundingMode).toPlainString();
  }
  
  public static BigDecimal round(String numberStr, int scale, RoundingMode roundingMode) {
    Assert.notBlank(numberStr);
    if (scale < 0)
      scale = 0; 
    return round(toBigDecimal(numberStr), scale, roundingMode);
  }
  
  public static BigDecimal round(BigDecimal number, int scale, RoundingMode roundingMode) {
    if (null == number)
      number = BigDecimal.ZERO; 
    if (scale < 0)
      scale = 0; 
    if (null == roundingMode)
      roundingMode = RoundingMode.HALF_UP; 
    return number.setScale(scale, roundingMode);
  }
  
  public static String roundStr(String numberStr, int scale, RoundingMode roundingMode) {
    return round(numberStr, scale, roundingMode).toPlainString();
  }
  
  public static BigDecimal roundHalfEven(Number number, int scale) {
    return roundHalfEven(toBigDecimal(number), scale);
  }
  
  public static BigDecimal roundHalfEven(BigDecimal value, int scale) {
    return round(value, scale, RoundingMode.HALF_EVEN);
  }
  
  public static BigDecimal roundDown(Number number, int scale) {
    return roundDown(toBigDecimal(number), scale);
  }
  
  public static BigDecimal roundDown(BigDecimal value, int scale) {
    return round(value, scale, RoundingMode.DOWN);
  }
  
  public static String decimalFormat(String pattern, double value) {
    Assert.isTrue(isValid(value), "value is NaN or Infinite!", new Object[0]);
    return (new DecimalFormat(pattern)).format(value);
  }
  
  public static String decimalFormat(String pattern, long value) {
    return (new DecimalFormat(pattern)).format(value);
  }
  
  public static String decimalFormat(String pattern, Object value) {
    return decimalFormat(pattern, value, null);
  }
  
  public static String decimalFormat(String pattern, Object value, RoundingMode roundingMode) {
    if (value instanceof Number)
      Assert.isTrue(isValidNumber((Number)value), "value is NaN or Infinite!", new Object[0]); 
    DecimalFormat decimalFormat = new DecimalFormat(pattern);
    if (null != roundingMode)
      decimalFormat.setRoundingMode(roundingMode); 
    return decimalFormat.format(value);
  }
  
  public static String decimalFormatMoney(double value) {
    return decimalFormat(",##0.00", value);
  }
  
  public static String formatPercent(double number, int scale) {
    NumberFormat format = NumberFormat.getPercentInstance();
    format.setMaximumFractionDigits(scale);
    return format.format(number);
  }
  
  public static boolean isNumber(CharSequence str) {
    if (StrUtil.isBlank(str))
      return false; 
    char[] chars = str.toString().toCharArray();
    int sz = chars.length;
    boolean hasExp = false;
    boolean hasDecPoint = false;
    boolean allowSigns = false;
    boolean foundDigit = false;
    int start = (chars[0] == '-' || chars[0] == '+') ? 1 : 0;
    if (sz > start + 1 && 
      chars[start] == '0' && (chars[start + 1] == 'x' || chars[start + 1] == 'X')) {
      int j = start + 2;
      if (j == sz)
        return false; 
      for (; j < chars.length; j++) {
        if ((chars[j] < '0' || chars[j] > '9') && (chars[j] < 'a' || chars[j] > 'f') && (chars[j] < 'A' || chars[j] > 'F'))
          return false; 
      } 
      return true;
    } 
    sz--;
    int i = start;
    while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
      if (chars[i] >= '0' && chars[i] <= '9') {
        foundDigit = true;
        allowSigns = false;
      } else if (chars[i] == '.') {
        if (hasDecPoint || hasExp)
          return false; 
        hasDecPoint = true;
      } else if (chars[i] == 'e' || chars[i] == 'E') {
        if (hasExp)
          return false; 
        if (false == foundDigit)
          return false; 
        hasExp = true;
        allowSigns = true;
      } else if (chars[i] == '+' || chars[i] == '-') {
        if (!allowSigns)
          return false; 
        allowSigns = false;
        foundDigit = false;
      } else {
        return false;
      } 
      i++;
    } 
    if (i < chars.length) {
      if (chars[i] >= '0' && chars[i] <= '9')
        return true; 
      if (chars[i] == 'e' || chars[i] == 'E')
        return false; 
      if (chars[i] == '.') {
        if (hasDecPoint || hasExp)
          return false; 
        return foundDigit;
      } 
      if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F'))
        return foundDigit; 
      if (chars[i] == 'l' || chars[i] == 'L')
        return (foundDigit && !hasExp); 
      return false;
    } 
    return (false == allowSigns && foundDigit);
  }
  
  public static boolean isInteger(String s) {
    if (StrUtil.isBlank(s))
      return false; 
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    } 
    return true;
  }
  
  public static boolean isLong(String s) {
    if (StrUtil.isBlank(s))
      return false; 
    try {
      Long.parseLong(s);
    } catch (NumberFormatException e) {
      return false;
    } 
    return true;
  }
  
  public static boolean isDouble(String s) {
    if (StrUtil.isBlank(s))
      return false; 
    try {
      Double.parseDouble(s);
    } catch (NumberFormatException ignore) {
      return false;
    } 
    return s.contains(".");
  }
  
  public static boolean isPrimes(int n) {
    Assert.isTrue((n > 1), "The number must be > 1", new Object[0]);
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0)
        return false; 
    } 
    return true;
  }
  
  public static int[] generateRandomNumber(int begin, int end, int size) {
    int[] seed = ArrayUtil.range(begin, end);
    return generateRandomNumber(begin, end, size, seed);
  }
  
  public static int[] generateRandomNumber(int begin, int end, int size, int[] seed) {
    if (begin > end) {
      int temp = begin;
      begin = end;
      end = temp;
    } 
    Assert.isTrue((end - begin >= size), "Size is larger than range between begin and end!", new Object[0]);
    Assert.isTrue((seed.length >= size), "Size is larger than seed size!", new Object[0]);
    int[] ranArr = new int[size];
    for (int i = 0; i < size; i++) {
      int j = RandomUtil.randomInt(seed.length - i);
      ranArr[i] = seed[j];
      seed[j] = seed[seed.length - 1 - i];
    } 
    return ranArr;
  }
  
  public static Integer[] generateBySet(int begin, int end, int size) {
    if (begin > end) {
      int temp = begin;
      begin = end;
      end = temp;
    } 
    if (end - begin < size)
      throw new UtilException("Size is larger than range between begin and end!"); 
    Set<Integer> set = new HashSet<>(size, 1.0F);
    while (set.size() < size)
      set.add(Integer.valueOf(begin + RandomUtil.randomInt(end - begin))); 
    return set.<Integer>toArray(new Integer[0]);
  }
  
  public static int[] range(int stop) {
    return range(0, stop);
  }
  
  public static int[] range(int start, int stop) {
    return range(start, stop, 1);
  }
  
  public static int[] range(int start, int stop, int step) {
    if (start < stop) {
      step = Math.abs(step);
    } else if (start > stop) {
      step = -Math.abs(step);
    } else {
      return new int[] { start };
    } 
    int size = Math.abs((stop - start) / step) + 1;
    int[] values = new int[size];
    int index = 0;
    int i;
    for (i = start; (step > 0) ? (i <= stop) : (i >= stop); i += step) {
      values[index] = i;
      index++;
    } 
    return values;
  }
  
  public static Collection<Integer> appendRange(int start, int stop, Collection<Integer> values) {
    return appendRange(start, stop, 1, values);
  }
  
  public static Collection<Integer> appendRange(int start, int stop, int step, Collection<Integer> values) {
    if (start < stop) {
      step = Math.abs(step);
    } else if (start > stop) {
      step = -Math.abs(step);
    } else {
      values.add(Integer.valueOf(start));
      return values;
    } 
    int i;
    for (i = start; (step > 0) ? (i <= stop) : (i >= stop); i += step)
      values.add(Integer.valueOf(i)); 
    return values;
  }
  
  public static BigInteger factorial(BigInteger n) {
    if (n.equals(BigInteger.ZERO))
      return BigInteger.ONE; 
    return factorial(n, BigInteger.ZERO);
  }
  
  public static BigInteger factorial(BigInteger start, BigInteger end) {
    Assert.notNull(start, "Factorial start must be not null!", new Object[0]);
    Assert.notNull(end, "Factorial end must be not null!", new Object[0]);
    if (start.compareTo(BigInteger.ZERO) < 0 || end.compareTo(BigInteger.ZERO) < 0)
      throw new IllegalArgumentException(StrUtil.format("Factorial start and end both must be > 0, but got start={}, end={}", new Object[] { start, end })); 
    if (start.equals(BigInteger.ZERO))
      start = BigInteger.ONE; 
    if (end.compareTo(BigInteger.ONE) < 0)
      end = BigInteger.ONE; 
    BigInteger result = start;
    end = end.add(BigInteger.ONE);
    while (start.compareTo(end) > 0) {
      start = start.subtract(BigInteger.ONE);
      result = result.multiply(start);
    } 
    return result;
  }
  
  public static long factorial(long start, long end) {
    if (start < 0L || end < 0L)
      throw new IllegalArgumentException(StrUtil.format("Factorial start and end both must be >= 0, but got start={}, end={}", new Object[] { Long.valueOf(start), Long.valueOf(end) })); 
    if (0L == start || start == end)
      return 1L; 
    if (start < end)
      return 0L; 
    return factorialMultiplyAndCheck(start, factorial(start - 1L, end));
  }
  
  private static long factorialMultiplyAndCheck(long a, long b) {
    if (a <= Long.MAX_VALUE / b)
      return a * b; 
    throw new IllegalArgumentException(StrUtil.format("Overflow in multiplication: {} * {}", new Object[] { Long.valueOf(a), Long.valueOf(b) }));
  }
  
  public static long factorial(long n) {
    if (n < 0L || n > 20L)
      throw new IllegalArgumentException(StrUtil.format("Factorial must have n >= 0 and n <= 20 for n!, but got n = {}", new Object[] { Long.valueOf(n) })); 
    return FACTORIALS[(int)n];
  }
  
  public static long sqrt(long x) {
    long y = 0L;
    long b = 4611686018427387904L;
    while (b > 0L) {
      if (x >= y + b) {
        x -= y + b;
        y >>= 1L;
        y += b;
      } else {
        y >>= 1L;
      } 
      b >>= 2L;
    } 
    return y;
  }
  
  public static int processMultiple(int selectNum, int minNum) {
    int result = mathSubNode(selectNum, minNum) / mathNode(selectNum - minNum);
    return result;
  }
  
  public static int divisor(int m, int n) {
    while (m % n != 0) {
      int temp = m % n;
      m = n;
      n = temp;
    } 
    return n;
  }
  
  public static int multiple(int m, int n) {
    return m * n / divisor(m, n);
  }
  
  public static String getBinaryStr(Number number) {
    if (number instanceof Long)
      return Long.toBinaryString(((Long)number).longValue()); 
    if (number instanceof Integer)
      return Integer.toBinaryString(((Integer)number).intValue()); 
    return Long.toBinaryString(number.longValue());
  }
  
  public static int binaryToInt(String binaryStr) {
    return Integer.parseInt(binaryStr, 2);
  }
  
  public static long binaryToLong(String binaryStr) {
    return Long.parseLong(binaryStr, 2);
  }
  
  public static int compare(char x, char y) {
    return Character.compare(x, y);
  }
  
  public static int compare(double x, double y) {
    return Double.compare(x, y);
  }
  
  public static int compare(int x, int y) {
    return Integer.compare(x, y);
  }
  
  public static int compare(long x, long y) {
    return Long.compare(x, y);
  }
  
  public static int compare(short x, short y) {
    return Short.compare(x, y);
  }
  
  public static int compare(byte x, byte y) {
    return Byte.compare(x, y);
  }
  
  public static boolean isGreater(BigDecimal bigNum1, BigDecimal bigNum2) {
    Assert.notNull(bigNum1);
    Assert.notNull(bigNum2);
    return (bigNum1.compareTo(bigNum2) > 0);
  }
  
  public static boolean isGreaterOrEqual(BigDecimal bigNum1, BigDecimal bigNum2) {
    Assert.notNull(bigNum1);
    Assert.notNull(bigNum2);
    return (bigNum1.compareTo(bigNum2) >= 0);
  }
  
  public static boolean isLess(BigDecimal bigNum1, BigDecimal bigNum2) {
    Assert.notNull(bigNum1);
    Assert.notNull(bigNum2);
    return (bigNum1.compareTo(bigNum2) < 0);
  }
  
  public static boolean isLessOrEqual(BigDecimal bigNum1, BigDecimal bigNum2) {
    Assert.notNull(bigNum1);
    Assert.notNull(bigNum2);
    return (bigNum1.compareTo(bigNum2) <= 0);
  }
  
  public static boolean isIn(BigDecimal value, BigDecimal minInclude, BigDecimal maxInclude) {
    Assert.notNull(value);
    Assert.notNull(minInclude);
    Assert.notNull(maxInclude);
    return (isGreaterOrEqual(value, minInclude) && isLessOrEqual(value, maxInclude));
  }
  
  public static boolean equals(double num1, double num2) {
    return (Double.doubleToLongBits(num1) == Double.doubleToLongBits(num2));
  }
  
  public static boolean equals(float num1, float num2) {
    return (Float.floatToIntBits(num1) == Float.floatToIntBits(num2));
  }
  
  public static boolean equals(long num1, long num2) {
    return (num1 == num2);
  }
  
  public static boolean equals(Number number1, Number number2) {
    if (number1 instanceof BigDecimal && number2 instanceof BigDecimal)
      return equals((BigDecimal)number1, (BigDecimal)number2); 
    return Objects.equals(number1, number2);
  }
  
  public static boolean equals(BigDecimal bigNum1, BigDecimal bigNum2) {
    if (bigNum1 == bigNum2)
      return true; 
    if (bigNum1 == null || bigNum2 == null)
      return false; 
    return (0 == bigNum1.compareTo(bigNum2));
  }
  
  public static boolean equals(char c1, char c2, boolean ignoreCase) {
    return CharUtil.equals(c1, c2, ignoreCase);
  }
  
  public static <T extends Comparable<? super T>> T min(T[] numberArray) {
    return ArrayUtil.min(numberArray);
  }
  
  public static long min(long... numberArray) {
    return ArrayUtil.min(numberArray);
  }
  
  public static int min(int... numberArray) {
    return ArrayUtil.min(numberArray);
  }
  
  public static short min(short... numberArray) {
    return ArrayUtil.min(numberArray);
  }
  
  public static double min(double... numberArray) {
    return ArrayUtil.min(numberArray);
  }
  
  public static float min(float... numberArray) {
    return ArrayUtil.min(numberArray);
  }
  
  public static BigDecimal min(BigDecimal... numberArray) {
    return ArrayUtil.<BigDecimal>min(numberArray);
  }
  
  public static <T extends Comparable<? super T>> T max(T[] numberArray) {
    return ArrayUtil.max(numberArray);
  }
  
  public static long max(long... numberArray) {
    return ArrayUtil.max(numberArray);
  }
  
  public static int max(int... numberArray) {
    return ArrayUtil.max(numberArray);
  }
  
  public static short max(short... numberArray) {
    return ArrayUtil.max(numberArray);
  }
  
  public static double max(double... numberArray) {
    return ArrayUtil.max(numberArray);
  }
  
  public static float max(float... numberArray) {
    return ArrayUtil.max(numberArray);
  }
  
  public static BigDecimal max(BigDecimal... numberArray) {
    return ArrayUtil.<BigDecimal>max(numberArray);
  }
  
  public static String toStr(Number number, String defaultValue) {
    return (null == number) ? defaultValue : toStr(number);
  }
  
  public static String toStr(Number number) {
    return toStr(number, true);
  }
  
  public static String toStr(Number number, boolean isStripTrailingZeros) {
    Assert.notNull(number, "Number is null !", new Object[0]);
    if (number instanceof BigDecimal)
      return toStr((BigDecimal)number, isStripTrailingZeros); 
    Assert.isTrue(isValidNumber(number), "Number is non-finite!", new Object[0]);
    String string = number.toString();
    if (isStripTrailingZeros && 
      string.indexOf('.') > 0 && string.indexOf('e') < 0 && string.indexOf('E') < 0) {
      while (string.endsWith("0"))
        string = string.substring(0, string.length() - 1); 
      if (string.endsWith("."))
        string = string.substring(0, string.length() - 1); 
    } 
    return string;
  }
  
  public static String toStr(BigDecimal bigDecimal) {
    return toStr(bigDecimal, true);
  }
  
  public static String toStr(BigDecimal bigDecimal, boolean isStripTrailingZeros) {
    Assert.notNull(bigDecimal, "BigDecimal is null !", new Object[0]);
    if (isStripTrailingZeros)
      bigDecimal = bigDecimal.stripTrailingZeros(); 
    return bigDecimal.toPlainString();
  }
  
  public static BigDecimal toBigDecimal(Number number) {
    if (null == number)
      return BigDecimal.ZERO; 
    Assert.isTrue(isValidNumber(number), "Number is invalid!", new Object[0]);
    if (number instanceof BigDecimal)
      return (BigDecimal)number; 
    if (number instanceof Long)
      return new BigDecimal(((Long)number).longValue()); 
    if (number instanceof Integer)
      return new BigDecimal(((Integer)number).intValue()); 
    if (number instanceof BigInteger)
      return new BigDecimal((BigInteger)number); 
    return new BigDecimal(number.toString());
  }
  
  public static BigDecimal toBigDecimal(String numberStr) {
    if (StrUtil.isBlank(numberStr))
      return BigDecimal.ZERO; 
    try {
      return new BigDecimal(numberStr);
    } catch (Exception exception) {
      Number number = parseNumber(numberStr);
      return toBigDecimal(number);
    } 
  }
  
  public static BigInteger toBigInteger(Number number) {
    if (null == number)
      return BigInteger.ZERO; 
    if (number instanceof BigInteger)
      return (BigInteger)number; 
    if (number instanceof Long)
      return BigInteger.valueOf(((Long)number).longValue()); 
    Assert.isTrue(isValidNumber(number), "Number is invalid!", new Object[0]);
    return toBigInteger(Long.valueOf(number.longValue()));
  }
  
  public static BigInteger toBigInteger(String number) {
    return StrUtil.isBlank(number) ? BigInteger.ZERO : new BigInteger(number);
  }
  
  public static int count(int total, int part) {
    return (total % part == 0) ? (total / part) : (total / part + 1);
  }
  
  @Deprecated
  public static BigDecimal null2Zero(BigDecimal decimal) {
    return (decimal == null) ? BigDecimal.ZERO : decimal;
  }
  
  public static int zero2One(int value) {
    return (0 == value) ? 1 : value;
  }
  
  public static int nullToZero(Integer number) {
    return (number == null) ? 0 : number.intValue();
  }
  
  public static long nullToZero(Long number) {
    return (number == null) ? 0L : number.longValue();
  }
  
  public static double nullToZero(Double number) {
    return (number == null) ? 0.0D : number.doubleValue();
  }
  
  public static float nullToZero(Float number) {
    return (number == null) ? 0.0F : number.floatValue();
  }
  
  public static short nullToZero(Short number) {
    return (number == null) ? 0 : number.shortValue();
  }
  
  public static byte nullToZero(Byte number) {
    return (number == null) ? 0 : number.byteValue();
  }
  
  public static BigDecimal nullToZero(BigDecimal number) {
    return (number == null) ? BigDecimal.ZERO : number;
  }
  
  public static BigInteger nullToZero(BigInteger number) {
    return (number == null) ? BigInteger.ZERO : number;
  }
  
  public static BigInteger newBigInteger(String str) {
    str = StrUtil.trimToNull(str);
    if (null == str)
      return null; 
    int pos = 0;
    int radix = 10;
    boolean negate = false;
    if (str.startsWith("-")) {
      negate = true;
      pos = 1;
    } 
    if (str.startsWith("0x", pos) || str.startsWith("0X", pos)) {
      radix = 16;
      pos += 2;
    } else if (str.startsWith("#", pos)) {
      radix = 16;
      pos++;
    } else if (str.startsWith("0", pos) && str.length() > pos + 1) {
      radix = 8;
      pos++;
    } 
    if (pos > 0)
      str = str.substring(pos); 
    BigInteger value = new BigInteger(str, radix);
    return negate ? value.negate() : value;
  }
  
  public static boolean isBeside(long number1, long number2) {
    return (Math.abs(number1 - number2) == 1L);
  }
  
  public static boolean isBeside(int number1, int number2) {
    return (Math.abs(number1 - number2) == 1);
  }
  
  public static int partValue(int total, int partCount) {
    return partValue(total, partCount, true);
  }
  
  public static int partValue(int total, int partCount, boolean isPlusOneWhenHasRem) {
    int partValue = total / partCount;
    if (isPlusOneWhenHasRem && total % partCount > 0)
      partValue++; 
    return partValue;
  }
  
  public static BigDecimal pow(Number number, int n) {
    return pow(toBigDecimal(number), n);
  }
  
  public static BigDecimal pow(BigDecimal number, int n) {
    return number.pow(n);
  }
  
  public static boolean isPowerOfTwo(long n) {
    return (n > 0L && (n & n - 1L) == 0L);
  }
  
  public static int parseInt(String number) throws NumberFormatException {
    if (StrUtil.isBlank(number))
      return 0; 
    if (StrUtil.startWithIgnoreCase(number, "0x"))
      return Integer.parseInt(number.substring(2), 16); 
    if (StrUtil.containsIgnoreCase(number, "E"))
      throw new NumberFormatException(StrUtil.format("Unsupported int format: [{}]", new Object[] { number })); 
    try {
      return Integer.parseInt(number);
    } catch (NumberFormatException e) {
      return parseNumber(number).intValue();
    } 
  }
  
  public static long parseLong(String number) {
    if (StrUtil.isBlank(number))
      return 0L; 
    if (number.startsWith("0x"))
      return Long.parseLong(number.substring(2), 16); 
    try {
      return Long.parseLong(number);
    } catch (NumberFormatException e) {
      return parseNumber(number).longValue();
    } 
  }
  
  public static float parseFloat(String number) {
    if (StrUtil.isBlank(number))
      return 0.0F; 
    try {
      return Float.parseFloat(number);
    } catch (NumberFormatException e) {
      return parseNumber(number).floatValue();
    } 
  }
  
  public static double parseDouble(String number) {
    if (StrUtil.isBlank(number))
      return 0.0D; 
    try {
      return Double.parseDouble(number);
    } catch (NumberFormatException e) {
      return parseNumber(number).doubleValue();
    } 
  }
  
  public static Number parseNumber(String numberStr) throws NumberFormatException {
    if (StrUtil.startWithIgnoreCase(numberStr, "0x"))
      return Long.valueOf(Long.parseLong(numberStr.substring(2), 16)); 
    if (StrUtil.startWith(numberStr, '+'))
      numberStr = StrUtil.subSuf(numberStr, 1); 
    try {
      NumberFormat format = NumberFormat.getInstance();
      if (format instanceof DecimalFormat)
        ((DecimalFormat)format).setParseBigDecimal(true); 
      return format.parse(numberStr);
    } catch (ParseException e) {
      NumberFormatException nfe = new NumberFormatException(e.getMessage());
      nfe.initCause(e);
      throw nfe;
    } 
  }
  
  public static Integer parseInt(String numberStr, Integer defaultValue) {
    if (CharSequenceUtil.isBlank(numberStr))
      return defaultValue; 
    try {
      return Integer.valueOf(parseInt(numberStr));
    } catch (NumberFormatException numberFormatException) {
      return defaultValue;
    } 
  }
  
  public static Long parseLong(String numberStr, Long defaultValue) {
    if (CharSequenceUtil.isBlank(numberStr))
      return defaultValue; 
    try {
      return Long.valueOf(parseLong(numberStr));
    } catch (NumberFormatException numberFormatException) {
      return defaultValue;
    } 
  }
  
  public static Float parseFloat(String numberStr, Float defaultValue) {
    if (CharSequenceUtil.isBlank(numberStr))
      return defaultValue; 
    try {
      return Float.valueOf(parseFloat(numberStr));
    } catch (NumberFormatException numberFormatException) {
      return defaultValue;
    } 
  }
  
  public static Double parseDouble(String numberStr, Double defaultValue) {
    if (CharSequenceUtil.isBlank(numberStr))
      return defaultValue; 
    try {
      return Double.valueOf(parseDouble(numberStr));
    } catch (NumberFormatException numberFormatException) {
      return defaultValue;
    } 
  }
  
  public static Number parseNumber(String numberStr, Number defaultValue) {
    if (CharSequenceUtil.isBlank(numberStr))
      return defaultValue; 
    try {
      return parseNumber(numberStr);
    } catch (NumberFormatException numberFormatException) {
      return defaultValue;
    } 
  }
  
  public static byte[] toBytes(int value) {
    byte[] result = new byte[4];
    result[0] = (byte)(value >> 24);
    result[1] = (byte)(value >> 16);
    result[2] = (byte)(value >> 8);
    result[3] = (byte)value;
    return result;
  }
  
  public static int toInt(byte[] bytes) {
    return (bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | bytes[3] & 0xFF;
  }
  
  public static byte[] toUnsignedByteArray(BigInteger value) {
    byte[] bytes = value.toByteArray();
    if (bytes[0] == 0) {
      byte[] tmp = new byte[bytes.length - 1];
      System.arraycopy(bytes, 1, tmp, 0, tmp.length);
      return tmp;
    } 
    return bytes;
  }
  
  public static byte[] toUnsignedByteArray(int length, BigInteger value) {
    byte[] bytes = value.toByteArray();
    if (bytes.length == length)
      return bytes; 
    int start = (bytes[0] == 0) ? 1 : 0;
    int count = bytes.length - start;
    if (count > length)
      throw new IllegalArgumentException("standard length exceeded for value"); 
    byte[] tmp = new byte[length];
    System.arraycopy(bytes, start, tmp, tmp.length - count, count);
    return tmp;
  }
  
  public static BigInteger fromUnsignedByteArray(byte[] buf) {
    return new BigInteger(1, buf);
  }
  
  public static BigInteger fromUnsignedByteArray(byte[] buf, int off, int length) {
    byte[] mag = buf;
    if (off != 0 || length != buf.length) {
      mag = new byte[length];
      System.arraycopy(buf, off, mag, 0, length);
    } 
    return new BigInteger(1, mag);
  }
  
  public static boolean isValidNumber(Number number) {
    if (null == number)
      return false; 
    if (number instanceof Double)
      return (false == ((Double)number).isInfinite() && false == ((Double)number).isNaN()); 
    if (number instanceof Float)
      return (false == ((Float)number).isInfinite() && false == ((Float)number).isNaN()); 
    return true;
  }
  
  public static boolean isValid(double number) {
    return (false == ((Double.isNaN(number) || Double.isInfinite(number)) ? true : false));
  }
  
  public static boolean isValid(float number) {
    return (false == ((Float.isNaN(number) || Float.isInfinite(number)) ? true : false));
  }
  
  public static double calculate(String expression) {
    return Calculator.conversion(expression);
  }
  
  public static double toDouble(Number value) {
    if (value instanceof Float)
      return Double.parseDouble(value.toString()); 
    return value.doubleValue();
  }
  
  public static boolean isOdd(int num) {
    return ((num & 0x1) == 1);
  }
  
  public static boolean isEven(int num) {
    return (false == isOdd(num));
  }
  
  private static int mathSubNode(int selectNum, int minNum) {
    if (selectNum == minNum)
      return 1; 
    return selectNum * mathSubNode(selectNum - 1, minNum);
  }
  
  private static int mathNode(int selectNum) {
    if (selectNum == 0)
      return 1; 
    return selectNum * mathNode(selectNum - 1);
  }
}
