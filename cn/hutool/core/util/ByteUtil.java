package cn.hutool.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class ByteUtil {
  public static final ByteOrder DEFAULT_ORDER = ByteOrder.LITTLE_ENDIAN;
  
  public static final ByteOrder CPU_ENDIAN = "little".equals(System.getProperty("sun.cpu.endian")) ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
  
  public static byte intToByte(int intValue) {
    return (byte)intValue;
  }
  
  public static int byteToUnsignedInt(byte byteValue) {
    return byteValue & 0xFF;
  }
  
  public static short bytesToShort(byte[] bytes) {
    return bytesToShort(bytes, DEFAULT_ORDER);
  }
  
  public static short bytesToShort(byte[] bytes, ByteOrder byteOrder) {
    return bytesToShort(bytes, 0, byteOrder);
  }
  
  public static short bytesToShort(byte[] bytes, int start, ByteOrder byteOrder) {
    if (ByteOrder.LITTLE_ENDIAN == byteOrder)
      return (short)(bytes[start] & 0xFF | (bytes[start + 1] & 0xFF) << 8); 
    return (short)(bytes[start + 1] & 0xFF | (bytes[start] & 0xFF) << 8);
  }
  
  public static byte[] shortToBytes(short shortValue) {
    return shortToBytes(shortValue, DEFAULT_ORDER);
  }
  
  public static byte[] shortToBytes(short shortValue, ByteOrder byteOrder) {
    byte[] b = new byte[2];
    if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
      b[0] = (byte)(shortValue & 0xFF);
      b[1] = (byte)(shortValue >> 8 & 0xFF);
    } else {
      b[1] = (byte)(shortValue & 0xFF);
      b[0] = (byte)(shortValue >> 8 & 0xFF);
    } 
    return b;
  }
  
  public static int bytesToInt(byte[] bytes) {
    return bytesToInt(bytes, DEFAULT_ORDER);
  }
  
  public static int bytesToInt(byte[] bytes, ByteOrder byteOrder) {
    return bytesToInt(bytes, 0, byteOrder);
  }
  
  public static int bytesToInt(byte[] bytes, int start, ByteOrder byteOrder) {
    if (ByteOrder.LITTLE_ENDIAN == byteOrder)
      return bytes[start] & 0xFF | (bytes[1 + start] & 0xFF) << 8 | (bytes[2 + start] & 0xFF) << 16 | (bytes[3 + start] & 0xFF) << 24; 
    return bytes[3 + start] & 0xFF | (bytes[2 + start] & 0xFF) << 8 | (bytes[1 + start] & 0xFF) << 16 | (bytes[start] & 0xFF) << 24;
  }
  
  public static byte[] intToBytes(int intValue) {
    return intToBytes(intValue, DEFAULT_ORDER);
  }
  
  public static byte[] intToBytes(int intValue, ByteOrder byteOrder) {
    if (ByteOrder.LITTLE_ENDIAN == byteOrder)
      return new byte[] { (byte)(intValue & 0xFF), (byte)(intValue >> 8 & 0xFF), (byte)(intValue >> 16 & 0xFF), (byte)(intValue >> 24 & 0xFF) }; 
    return new byte[] { (byte)(intValue >> 24 & 0xFF), (byte)(intValue >> 16 & 0xFF), (byte)(intValue >> 8 & 0xFF), (byte)(intValue & 0xFF) };
  }
  
  public static byte[] longToBytes(long longValue) {
    return longToBytes(longValue, DEFAULT_ORDER);
  }
  
  public static byte[] longToBytes(long longValue, ByteOrder byteOrder) {
    byte[] result = new byte[8];
    if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
      for (int i = 0; i < result.length; i++) {
        result[i] = (byte)(int)(longValue & 0xFFL);
        longValue >>= 8L;
      } 
    } else {
      for (int i = result.length - 1; i >= 0; i--) {
        result[i] = (byte)(int)(longValue & 0xFFL);
        longValue >>= 8L;
      } 
    } 
    return result;
  }
  
  public static long bytesToLong(byte[] bytes) {
    return bytesToLong(bytes, DEFAULT_ORDER);
  }
  
  public static long bytesToLong(byte[] bytes, ByteOrder byteOrder) {
    return bytesToLong(bytes, 0, byteOrder);
  }
  
  public static long bytesToLong(byte[] bytes, int start, ByteOrder byteOrder) {
    long values = 0L;
    if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
      for (int i = 7; i >= 0; i--) {
        values <<= 8L;
        values |= (bytes[i + start] & 0xFF);
      } 
    } else {
      for (int i = 0; i < 8; i++) {
        values <<= 8L;
        values |= (bytes[i + start] & 0xFF);
      } 
    } 
    return values;
  }
  
  public static byte[] floatToBytes(float floatValue) {
    return floatToBytes(floatValue, DEFAULT_ORDER);
  }
  
  public static byte[] floatToBytes(float floatValue, ByteOrder byteOrder) {
    return intToBytes(Float.floatToIntBits(floatValue), byteOrder);
  }
  
  public static float bytesToFloat(byte[] bytes) {
    return bytesToFloat(bytes, DEFAULT_ORDER);
  }
  
  public static float bytesToFloat(byte[] bytes, ByteOrder byteOrder) {
    return Float.intBitsToFloat(bytesToInt(bytes, byteOrder));
  }
  
  public static byte[] doubleToBytes(double doubleValue) {
    return doubleToBytes(doubleValue, DEFAULT_ORDER);
  }
  
  public static byte[] doubleToBytes(double doubleValue, ByteOrder byteOrder) {
    return longToBytes(Double.doubleToLongBits(doubleValue), byteOrder);
  }
  
  public static double bytesToDouble(byte[] bytes) {
    return bytesToDouble(bytes, DEFAULT_ORDER);
  }
  
  public static double bytesToDouble(byte[] bytes, ByteOrder byteOrder) {
    return Double.longBitsToDouble(bytesToLong(bytes, byteOrder));
  }
  
  public static byte[] numberToBytes(Number number) {
    return numberToBytes(number, DEFAULT_ORDER);
  }
  
  public static byte[] numberToBytes(Number number, ByteOrder byteOrder) {
    if (number instanceof Byte)
      return new byte[] { number.byteValue() }; 
    if (number instanceof Double)
      return doubleToBytes(((Double)number).doubleValue(), byteOrder); 
    if (number instanceof Long)
      return longToBytes(((Long)number).longValue(), byteOrder); 
    if (number instanceof Integer)
      return intToBytes(((Integer)number).intValue(), byteOrder); 
    if (number instanceof Short)
      return shortToBytes(((Short)number).shortValue(), byteOrder); 
    if (number instanceof Float)
      return floatToBytes(((Float)number).floatValue(), byteOrder); 
    return doubleToBytes(number.doubleValue(), byteOrder);
  }
  
  public static <T extends Number> T bytesToNumber(byte[] bytes, Class<T> targetClass, ByteOrder byteOrder) throws IllegalArgumentException {
    Number number;
    if (Byte.class == targetClass) {
      number = Byte.valueOf(bytes[0]);
    } else if (Short.class == targetClass) {
      number = Short.valueOf(bytesToShort(bytes, byteOrder));
    } else if (Integer.class == targetClass) {
      number = Integer.valueOf(bytesToInt(bytes, byteOrder));
    } else if (AtomicInteger.class == targetClass) {
      number = new AtomicInteger(bytesToInt(bytes, byteOrder));
    } else if (Long.class == targetClass) {
      number = Long.valueOf(bytesToLong(bytes, byteOrder));
    } else if (AtomicLong.class == targetClass) {
      number = new AtomicLong(bytesToLong(bytes, byteOrder));
    } else if (LongAdder.class == targetClass) {
      LongAdder longValue = new LongAdder();
      longValue.add(bytesToLong(bytes, byteOrder));
      number = longValue;
    } else if (Float.class == targetClass) {
      number = Float.valueOf(bytesToFloat(bytes, byteOrder));
    } else if (Double.class == targetClass) {
      number = Double.valueOf(bytesToDouble(bytes, byteOrder));
    } else if (DoubleAdder.class == targetClass) {
      DoubleAdder doubleAdder = new DoubleAdder();
      doubleAdder.add(bytesToDouble(bytes, byteOrder));
      number = doubleAdder;
    } else if (BigDecimal.class == targetClass) {
      number = NumberUtil.toBigDecimal(Double.valueOf(bytesToDouble(bytes, byteOrder)));
    } else if (BigInteger.class == targetClass) {
      number = BigInteger.valueOf(bytesToLong(bytes, byteOrder));
    } else if (Number.class == targetClass) {
      number = Double.valueOf(bytesToDouble(bytes, byteOrder));
    } else {
      throw new IllegalArgumentException("Unsupported Number type: " + targetClass.getName());
    } 
    return (T)number;
  }
}
