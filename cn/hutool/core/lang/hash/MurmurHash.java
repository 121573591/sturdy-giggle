package cn.hutool.core.lang.hash;

import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class MurmurHash implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final int C1_32 = -862048943;
  
  private static final int C2_32 = 461845907;
  
  private static final int R1_32 = 15;
  
  private static final int R2_32 = 13;
  
  private static final int M_32 = 5;
  
  private static final int N_32 = -430675100;
  
  private static final long C1 = -8663945395140668459L;
  
  private static final long C2 = 5545529020109919103L;
  
  private static final int R1 = 31;
  
  private static final int R2 = 27;
  
  private static final int R3 = 33;
  
  private static final int M = 5;
  
  private static final int N1 = 1390208809;
  
  private static final int N2 = 944331445;
  
  private static final int DEFAULT_SEED = 0;
  
  private static final Charset DEFAULT_CHARSET = CharsetUtil.CHARSET_UTF_8;
  
  private static final ByteOrder DEFAULT_ORDER = ByteOrder.LITTLE_ENDIAN;
  
  public static int hash32(CharSequence data) {
    return hash32(StrUtil.bytes(data, DEFAULT_CHARSET));
  }
  
  public static int hash32(byte[] data) {
    return hash32(data, data.length, 0);
  }
  
  public static int hash32(byte[] data, int length, int seed) {
    return hash32(data, 0, length, seed);
  }
  
  public static int hash32(byte[] data, int offset, int length, int seed) {
    int hash = seed;
    int nblocks = length >> 2;
    for (int i = 0; i < nblocks; i++) {
      int i4 = offset + (i << 2);
      int k = ByteUtil.bytesToInt(data, i4, DEFAULT_ORDER);
      hash = mix32(k, hash);
    } 
    int idx = offset + (nblocks << 2);
    int k1 = 0;
    switch (offset + length - idx) {
      case 3:
        k1 ^= (data[idx + 2] & 0xFF) << 16;
      case 2:
        k1 ^= (data[idx + 1] & 0xFF) << 8;
      case 1:
        k1 ^= data[idx] & 0xFF;
        k1 *= -862048943;
        k1 = Integer.rotateLeft(k1, 15);
        k1 *= 461845907;
        hash ^= k1;
        break;
    } 
    hash ^= length;
    return fmix32(hash);
  }
  
  public static long hash64(CharSequence data) {
    return hash64(StrUtil.bytes(data, DEFAULT_CHARSET));
  }
  
  public static long hash64(byte[] data) {
    return hash64(data, data.length, 0);
  }
  
  public static long hash64(byte[] data, int length, int seed) {
    long hash = seed;
    int nblocks = length >> 3;
    for (int i = 0; i < nblocks; i++) {
      int i8 = i << 3;
      long k = ByteUtil.bytesToLong(data, i8, DEFAULT_ORDER);
      k *= -8663945395140668459L;
      k = Long.rotateLeft(k, 31);
      k *= 5545529020109919103L;
      hash ^= k;
      hash = Long.rotateLeft(hash, 27) * 5L + 1390208809L;
    } 
    long k1 = 0L;
    int tailStart = nblocks << 3;
    switch (length - tailStart) {
      case 7:
        k1 ^= (data[tailStart + 6] & 0xFFL) << 48L;
      case 6:
        k1 ^= (data[tailStart + 5] & 0xFFL) << 40L;
      case 5:
        k1 ^= (data[tailStart + 4] & 0xFFL) << 32L;
      case 4:
        k1 ^= (data[tailStart + 3] & 0xFFL) << 24L;
      case 3:
        k1 ^= (data[tailStart + 2] & 0xFFL) << 16L;
      case 2:
        k1 ^= (data[tailStart + 1] & 0xFFL) << 8L;
      case 1:
        k1 ^= data[tailStart] & 0xFFL;
        k1 *= -8663945395140668459L;
        k1 = Long.rotateLeft(k1, 31);
        k1 *= 5545529020109919103L;
        hash ^= k1;
        break;
    } 
    hash ^= length;
    hash = fmix64(hash);
    return hash;
  }
  
  public static long[] hash128(CharSequence data) {
    return hash128(StrUtil.bytes(data, DEFAULT_CHARSET));
  }
  
  public static long[] hash128(byte[] data) {
    return hash128(data, data.length, 0);
  }
  
  public static long[] hash128(byte[] data, int length, int seed) {
    return hash128(data, 0, length, seed);
  }
  
  public static long[] hash128(byte[] data, int offset, int length, int seed) {
    seed = (int)(seed & 0xFFFFFFFFL);
    long h1 = seed;
    long h2 = seed;
    int nblocks = length >> 4;
    for (int i = 0; i < nblocks; i++) {
      int i16 = offset + (i << 4);
      long l1 = ByteUtil.bytesToLong(data, i16, DEFAULT_ORDER);
      long l2 = ByteUtil.bytesToLong(data, i16 + 8, DEFAULT_ORDER);
      l1 *= -8663945395140668459L;
      l1 = Long.rotateLeft(l1, 31);
      l1 *= 5545529020109919103L;
      h1 ^= l1;
      h1 = Long.rotateLeft(h1, 27);
      h1 += h2;
      h1 = h1 * 5L + 1390208809L;
      l2 *= 5545529020109919103L;
      l2 = Long.rotateLeft(l2, 33);
      l2 *= -8663945395140668459L;
      h2 ^= l2;
      h2 = Long.rotateLeft(h2, 31);
      h2 += h1;
      h2 = h2 * 5L + 944331445L;
    } 
    long k1 = 0L;
    long k2 = 0L;
    int tailStart = offset + (nblocks << 4);
    switch (offset + length - tailStart) {
      case 15:
        k2 ^= (data[tailStart + 14] & 0xFF) << 48L;
      case 14:
        k2 ^= (data[tailStart + 13] & 0xFF) << 40L;
      case 13:
        k2 ^= (data[tailStart + 12] & 0xFF) << 32L;
      case 12:
        k2 ^= (data[tailStart + 11] & 0xFF) << 24L;
      case 11:
        k2 ^= (data[tailStart + 10] & 0xFF) << 16L;
      case 10:
        k2 ^= (data[tailStart + 9] & 0xFF) << 8L;
      case 9:
        k2 ^= (data[tailStart + 8] & 0xFF);
        k2 *= 5545529020109919103L;
        k2 = Long.rotateLeft(k2, 33);
        k2 *= -8663945395140668459L;
        h2 ^= k2;
      case 8:
        k1 ^= (data[tailStart + 7] & 0xFF) << 56L;
      case 7:
        k1 ^= (data[tailStart + 6] & 0xFF) << 48L;
      case 6:
        k1 ^= (data[tailStart + 5] & 0xFF) << 40L;
      case 5:
        k1 ^= (data[tailStart + 4] & 0xFF) << 32L;
      case 4:
        k1 ^= (data[tailStart + 3] & 0xFF) << 24L;
      case 3:
        k1 ^= (data[tailStart + 2] & 0xFF) << 16L;
      case 2:
        k1 ^= (data[tailStart + 1] & 0xFF) << 8L;
      case 1:
        k1 ^= (data[tailStart] & 0xFF);
        k1 *= -8663945395140668459L;
        k1 = Long.rotateLeft(k1, 31);
        k1 *= 5545529020109919103L;
        h1 ^= k1;
        break;
    } 
    h1 ^= length;
    h2 ^= length;
    h1 += h2;
    h2 += h1;
    h1 = fmix64(h1);
    h2 = fmix64(h2);
    h1 += h2;
    h2 += h1;
    return new long[] { h1, h2 };
  }
  
  private static int mix32(int k, int hash) {
    k *= -862048943;
    k = Integer.rotateLeft(k, 15);
    k *= 461845907;
    hash ^= k;
    return Integer.rotateLeft(hash, 13) * 5 + -430675100;
  }
  
  private static int fmix32(int hash) {
    hash ^= hash >>> 16;
    hash *= -2048144789;
    hash ^= hash >>> 13;
    hash *= -1028477387;
    hash ^= hash >>> 16;
    return hash;
  }
  
  private static long fmix64(long h) {
    h ^= h >>> 33L;
    h *= -49064778989728563L;
    h ^= h >>> 33L;
    h *= -4265267296055464877L;
    h ^= h >>> 33L;
    return h;
  }
}
