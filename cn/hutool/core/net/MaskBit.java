package cn.hutool.core.net;

import cn.hutool.core.map.BiMap;
import java.util.HashMap;

public class MaskBit {
  private static final BiMap<Integer, String> MASK_BIT_MAP = new BiMap(new HashMap<>(32));
  
  static {
    MASK_BIT_MAP.put(Integer.valueOf(1), "128.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(2), "192.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(3), "224.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(4), "240.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(5), "248.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(6), "252.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(7), "254.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(8), "255.0.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(9), "255.128.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(10), "255.192.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(11), "255.224.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(12), "255.240.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(13), "255.248.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(14), "255.252.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(15), "255.254.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(16), "255.255.0.0");
    MASK_BIT_MAP.put(Integer.valueOf(17), "255.255.128.0");
    MASK_BIT_MAP.put(Integer.valueOf(18), "255.255.192.0");
    MASK_BIT_MAP.put(Integer.valueOf(19), "255.255.224.0");
    MASK_BIT_MAP.put(Integer.valueOf(20), "255.255.240.0");
    MASK_BIT_MAP.put(Integer.valueOf(21), "255.255.248.0");
    MASK_BIT_MAP.put(Integer.valueOf(22), "255.255.252.0");
    MASK_BIT_MAP.put(Integer.valueOf(23), "255.255.254.0");
    MASK_BIT_MAP.put(Integer.valueOf(24), "255.255.255.0");
    MASK_BIT_MAP.put(Integer.valueOf(25), "255.255.255.128");
    MASK_BIT_MAP.put(Integer.valueOf(26), "255.255.255.192");
    MASK_BIT_MAP.put(Integer.valueOf(27), "255.255.255.224");
    MASK_BIT_MAP.put(Integer.valueOf(28), "255.255.255.240");
    MASK_BIT_MAP.put(Integer.valueOf(29), "255.255.255.248");
    MASK_BIT_MAP.put(Integer.valueOf(30), "255.255.255.252");
    MASK_BIT_MAP.put(Integer.valueOf(31), "255.255.255.254");
    MASK_BIT_MAP.put(Integer.valueOf(32), "255.255.255.255");
  }
  
  public static String get(int maskBit) {
    return (String)MASK_BIT_MAP.get(Integer.valueOf(maskBit));
  }
  
  public static Integer getMaskBit(String mask) {
    return (Integer)MASK_BIT_MAP.getKey(mask);
  }
}
