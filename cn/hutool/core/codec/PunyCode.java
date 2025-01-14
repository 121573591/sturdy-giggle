package cn.hutool.core.codec;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import java.util.List;

public class PunyCode {
  private static final int TMIN = 1;
  
  private static final int TMAX = 26;
  
  private static final int BASE = 36;
  
  private static final int INITIAL_N = 128;
  
  private static final int INITIAL_BIAS = 72;
  
  private static final int DAMP = 700;
  
  private static final int SKEW = 38;
  
  private static final char DELIMITER = '-';
  
  public static final String PUNY_CODE_PREFIX = "xn--";
  
  public static String encodeDomain(String domain) throws UtilException {
    Assert.notNull(domain, "domain must not be null!", new Object[0]);
    List<String> split = StrUtil.split(domain, '.');
    StringBuilder result = new StringBuilder(domain.length() * 4);
    for (String str : split) {
      if (result.length() != 0)
        result.append('.'); 
      result.append(encode(str, true));
    } 
    return result.toString();
  }
  
  public static String encode(CharSequence input) throws UtilException {
    return encode(input, false);
  }
  
  public static String encode(CharSequence input, boolean withPrefix) throws UtilException {
    Assert.notNull(input, "input must not be null!", new Object[0]);
    int n = 128;
    int delta = 0;
    int bias = 72;
    StringBuilder output = new StringBuilder();
    int length = input.length();
    int b = 0;
    for (int i = 0; i < length; i++) {
      char c = input.charAt(i);
      if (isBasic(c)) {
        output.append(c);
        b++;
      } 
    } 
    if (b > 0) {
      if (b == length)
        return output.toString(); 
      output.append('-');
    } 
    int h = b;
    while (h < length) {
      int m = Integer.MAX_VALUE;
      for (int k = 0; k < length; k++) {
        char c = input.charAt(k);
        if (c >= n && c < m)
          m = c; 
      } 
      if (m - n > (Integer.MAX_VALUE - delta) / (h + 1))
        throw new UtilException("OVERFLOW"); 
      delta += (m - n) * (h + 1);
      n = m;
      for (int j = 0; j < length; j++) {
        int c = input.charAt(j);
        if (c < n) {
          delta++;
          if (0 == delta)
            throw new UtilException("OVERFLOW"); 
        } 
        if (c == n) {
          int q = delta;
          for (int i1 = 36;; i1 += 36) {
            int t;
            if (i1 <= bias) {
              t = 1;
            } else if (i1 >= bias + 26) {
              t = 26;
            } else {
              t = i1 - bias;
            } 
            if (q < t)
              break; 
            output.append((char)digit2codepoint(t + (q - t) % (36 - t)));
            q = (q - t) / (36 - t);
          } 
          output.append((char)digit2codepoint(q));
          bias = adapt(delta, h + 1, (h == b));
          delta = 0;
          h++;
        } 
      } 
      delta++;
      n++;
    } 
    if (withPrefix)
      output.insert(0, "xn--"); 
    return output.toString();
  }
  
  public static String decodeDomain(String domain) throws UtilException {
    Assert.notNull(domain, "domain must not be null!", new Object[0]);
    List<String> split = StrUtil.split(domain, '.');
    StringBuilder result = new StringBuilder(domain.length() / 4 + 1);
    for (String str : split) {
      if (result.length() != 0)
        result.append('.'); 
      result.append(StrUtil.startWithIgnoreEquals(str, "xn--") ? decode(str) : str);
    } 
    return result.toString();
  }
  
  public static String decode(String input) throws UtilException {
    Assert.notNull(input, "input must not be null!", new Object[0]);
    input = StrUtil.removePrefixIgnoreCase(input, "xn--");
    int n = 128;
    int i = 0;
    int bias = 72;
    StringBuilder output = new StringBuilder();
    int d = input.lastIndexOf('-');
    if (d > 0) {
      for (int j = 0; j < d; j++) {
        char c = input.charAt(j);
        if (isBasic(c))
          output.append(c); 
      } 
      d++;
    } else {
      d = 0;
    } 
    int length = input.length();
    while (d < length) {
      int oldi = i;
      int w = 1;
      for (int k = 36;; k += 36) {
        int t;
        if (d == length)
          throw new UtilException("BAD_INPUT"); 
        int c = input.charAt(d++);
        int digit = codepoint2digit(c);
        if (digit > (Integer.MAX_VALUE - i) / w)
          throw new UtilException("OVERFLOW"); 
        i += digit * w;
        if (k <= bias) {
          t = 1;
        } else if (k >= bias + 26) {
          t = 26;
        } else {
          t = k - bias;
        } 
        if (digit < t)
          break; 
        w *= 36 - t;
      } 
      bias = adapt(i - oldi, output.length() + 1, (oldi == 0));
      if (i / (output.length() + 1) > Integer.MAX_VALUE - n)
        throw new UtilException("OVERFLOW"); 
      n += i / (output.length() + 1);
      i %= output.length() + 1;
      output.insert(i, (char)n);
      i++;
    } 
    return output.toString();
  }
  
  private static int adapt(int delta, int numpoints, boolean first) {
    if (first) {
      delta /= 700;
    } else {
      delta /= 2;
    } 
    delta += delta / numpoints;
    int k = 0;
    while (delta > 455) {
      delta /= 35;
      k += 36;
    } 
    return k + 36 * delta / (delta + 38);
  }
  
  private static boolean isBasic(char c) {
    return (c < '');
  }
  
  private static int digit2codepoint(int d) throws UtilException {
    Assert.checkBetween(d, 0, 35);
    if (d < 26)
      return d + 97; 
    if (d < 36)
      return d - 26 + 48; 
    throw new UtilException("BAD_INPUT");
  }
  
  private static int codepoint2digit(int c) throws UtilException {
    if (c - 48 < 10)
      return c - 48 + 26; 
    if (c - 97 < 26)
      return c - 97; 
    throw new UtilException("BAD_INPUT");
  }
}
