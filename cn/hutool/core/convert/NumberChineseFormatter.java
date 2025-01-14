package cn.hutool.core.convert;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberChineseFormatter {
  private static final char[] DIGITS = new char[] { 
      '零', '一', '壹', '二', '贰', '三', '叁', '四', '肆', '五', 
      '伍', '六', '陆', '七', '柒', '八', '捌', '九', '玖' };
  
  private static final ChineseUnit[] CHINESE_NAME_VALUE = new ChineseUnit[] { new ChineseUnit(' ', 1, false), new ChineseUnit('十', 10, false), new ChineseUnit('拾', 10, false), new ChineseUnit('百', 100, false), new ChineseUnit('佰', 100, false), new ChineseUnit('千', 1000, false), new ChineseUnit('仟', 1000, false), new ChineseUnit('万', 10000, true), new ChineseUnit('亿', 100000000, true) };
  
  public static String format(double amount, boolean isUseTraditional) {
    return format(amount, isUseTraditional, false);
  }
  
  public static String format(double amount, boolean isUseTraditional, boolean isMoneyMode, String negativeName, String unitName) {
    if (0.0D == amount)
      return "零"; 
    Assert.checkBetween(amount, -9.999999999999998E13D, 9.999999999999998E13D, "Number support only: (-99999999999999.99 ~ 99999999999999.99)！", new Object[0]);
    StringBuilder chineseStr = new StringBuilder();
    if (amount < 0.0D) {
      chineseStr.append(StrUtil.isNullOrUndefined(negativeName) ? "负" : negativeName);
      amount = -amount;
    } 
    long yuan = Math.round(amount * 100.0D);
    int fen = (int)(yuan % 10L);
    yuan /= 10L;
    int jiao = (int)(yuan % 10L);
    yuan /= 10L;
    if (false == isMoneyMode || 0L != yuan) {
      chineseStr.append(longToChinese(yuan, isUseTraditional));
      if (isMoneyMode)
        chineseStr.append(StrUtil.isNullOrUndefined(unitName) ? "元" : unitName); 
    } 
    if (0 == jiao && 0 == fen) {
      if (isMoneyMode)
        chineseStr.append("整"); 
      return chineseStr.toString();
    } 
    if (false == isMoneyMode)
      chineseStr.append("点"); 
    if (0L == yuan && 0 == jiao) {
      if (false == isMoneyMode)
        chineseStr.append("零"); 
    } else {
      chineseStr.append(numberToChinese(jiao, isUseTraditional));
      if (isMoneyMode && 0 != jiao)
        chineseStr.append("角"); 
    } 
    if (0 != fen) {
      chineseStr.append(numberToChinese(fen, isUseTraditional));
      if (isMoneyMode)
        chineseStr.append("分"); 
    } 
    return chineseStr.toString();
  }
  
  public static String format(double amount, boolean isUseTraditional, boolean isMoneyMode) {
    return format(amount, isUseTraditional, isMoneyMode, "负", "元");
  }
  
  public static String format(long amount, boolean isUseTraditional) {
    if (0L == amount)
      return "零"; 
    Assert.checkBetween(amount, -9.999999999999998E13D, 9.999999999999998E13D, "Number support only: (-99999999999999.99 ~ 99999999999999.99)！", new Object[0]);
    StringBuilder chineseStr = new StringBuilder();
    if (amount < 0L) {
      chineseStr.append("负");
      amount = -amount;
    } 
    chineseStr.append(longToChinese(amount, isUseTraditional));
    return chineseStr.toString();
  }
  
  public static String formatSimple(long amount) {
    String res;
    if (amount < 10000L && amount > -10000L)
      return String.valueOf(amount); 
    if (amount < 100000000L && amount > -100000000L) {
      res = NumberUtil.div((float)amount, 10000.0F, 2) + "万";
    } else if (amount < 1000000000000L && amount > -1000000000000L) {
      res = NumberUtil.div((float)amount, 1.0E8F, 2) + "亿";
    } else {
      res = NumberUtil.div((float)amount, 1.0E12F, 2) + "万亿";
    } 
    return res;
  }
  
  public static String formatThousand(int amount, boolean isUseTraditional) {
    Assert.checkBetween(amount, -999, 999, "Number support only: (-999 ~ 999)！", new Object[0]);
    String chinese = thousandToChinese(amount, isUseTraditional);
    if (amount < 20 && amount >= 10)
      return chinese.substring(1); 
    return chinese;
  }
  
  public static String numberCharToChinese(char c, boolean isUseTraditional) {
    if (c < '0' || c > '9')
      return String.valueOf(c); 
    return String.valueOf(numberToChinese(c - 48, isUseTraditional));
  }
  
  public static BigDecimal chineseMoneyToNumber(String chineseMoneyAmount) {
    if (StrUtil.isBlank(chineseMoneyAmount))
      return null; 
    int yi = chineseMoneyAmount.indexOf("元");
    if (yi == -1)
      yi = chineseMoneyAmount.indexOf("圆"); 
    int ji = chineseMoneyAmount.indexOf("角");
    int fi = chineseMoneyAmount.indexOf("分");
    String yStr = null;
    if (yi > 0)
      yStr = chineseMoneyAmount.substring(0, yi); 
    String jStr = null;
    if (ji > 0)
      if (yi >= 0) {
        if (ji > yi)
          jStr = chineseMoneyAmount.substring(yi + 1, ji); 
      } else {
        jStr = chineseMoneyAmount.substring(0, ji);
      }  
    String fStr = null;
    if (fi > 0)
      if (ji >= 0) {
        if (fi > ji)
          fStr = chineseMoneyAmount.substring(ji + 1, fi); 
      } else if (yi > 0) {
        if (fi > yi)
          fStr = chineseMoneyAmount.substring(yi + 1, fi); 
      } else {
        fStr = chineseMoneyAmount.substring(0, fi);
      }  
    int y = 0, j = 0, f = 0;
    if (StrUtil.isNotBlank(yStr))
      y = chineseToNumber(yStr); 
    if (StrUtil.isNotBlank(jStr))
      j = chineseToNumber(jStr); 
    if (StrUtil.isNotBlank(fStr))
      f = chineseToNumber(fStr); 
    BigDecimal amount = new BigDecimal(y);
    amount = amount.add(BigDecimal.valueOf(j).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
    amount = amount.add(BigDecimal.valueOf(f).divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP));
    return amount;
  }
  
  private static String longToChinese(long amount, boolean isUseTraditional) {
    if (0L == amount)
      return "零"; 
    int[] parts = new int[4];
    for (int i = 0; amount != 0L; i++) {
      parts[i] = (int)(amount % 10000L);
      amount /= 10000L;
    } 
    StringBuilder chineseStr = new StringBuilder();
    int partValue = parts[0];
    if (partValue > 0) {
      String partChinese = thousandToChinese(partValue, isUseTraditional);
      chineseStr.insert(0, partChinese);
      if (partValue < 1000)
        addPreZero(chineseStr); 
    } 
    partValue = parts[1];
    if (partValue > 0) {
      if (partValue % 10 == 0 && parts[0] > 0)
        addPreZero(chineseStr); 
      String partChinese = thousandToChinese(partValue, isUseTraditional);
      chineseStr.insert(0, partChinese + "万");
      if (partValue < 1000)
        addPreZero(chineseStr); 
    } else {
      addPreZero(chineseStr);
    } 
    partValue = parts[2];
    if (partValue > 0) {
      if (partValue % 10 == 0 && parts[1] > 0)
        addPreZero(chineseStr); 
      String partChinese = thousandToChinese(partValue, isUseTraditional);
      chineseStr.insert(0, partChinese + "亿");
      if (partValue < 1000)
        addPreZero(chineseStr); 
    } else {
      addPreZero(chineseStr);
    } 
    partValue = parts[3];
    if (partValue > 0) {
      if (parts[2] == 0)
        chineseStr.insert(0, "亿"); 
      String partChinese = thousandToChinese(partValue, isUseTraditional);
      chineseStr.insert(0, partChinese + "万");
    } 
    if (StrUtil.isNotEmpty(chineseStr) && '零' == chineseStr.charAt(0))
      return chineseStr.substring(1); 
    return chineseStr.toString();
  }
  
  private static String thousandToChinese(int amountPart, boolean isUseTraditional) {
    if (amountPart == 0)
      return String.valueOf(DIGITS[0]); 
    int temp = amountPart;
    StringBuilder chineseStr = new StringBuilder();
    boolean lastIsZero = true;
    for (int i = 0; temp > 0; i++) {
      int digit = temp % 10;
      if (digit == 0) {
        if (false == lastIsZero)
          chineseStr.insert(0, "零"); 
        lastIsZero = true;
      } else {
        chineseStr.insert(0, numberToChinese(digit, isUseTraditional) + getUnitName(i, isUseTraditional));
        lastIsZero = false;
      } 
      temp /= 10;
    } 
    return chineseStr.toString();
  }
  
  public static int chineseToNumber(String chinese) {
    int length = chinese.length();
    int result = 0;
    int section = 0;
    int number = 0;
    ChineseUnit unit = null;
    for (int i = 0; i < length; i++) {
      char c = chinese.charAt(i);
      int num = chineseToNumber(c);
      if (num >= 0) {
        if (num == 0) {
          if (number > 0 && null != unit)
            section += number * unit.value / 10; 
          unit = null;
        } else if (number > 0) {
          throw new IllegalArgumentException(StrUtil.format("Bad number '{}{}' at: {}", new Object[] { Character.valueOf(chinese.charAt(i - 1)), Character.valueOf(c), Integer.valueOf(i) }));
        } 
        number = num;
      } else {
        unit = chineseToUnit(c);
        if (null == unit)
          throw new IllegalArgumentException(StrUtil.format("Unknown unit '{}' at: {}", new Object[] { Character.valueOf(c), Integer.valueOf(i) })); 
        if (unit.secUnit) {
          section = (section + number) * unit.value;
          result += section;
          section = 0;
        } else {
          int unitNumber = number;
          if (0 == number && 0 == i)
            unitNumber = 1; 
          section += unitNumber * unit.value;
        } 
        number = 0;
      } 
    } 
    if (number > 0 && null != unit)
      number *= unit.value / 10; 
    return result + section + number;
  }
  
  private static ChineseUnit chineseToUnit(char chinese) {
    for (ChineseUnit chineseNameValue : CHINESE_NAME_VALUE) {
      if (chineseNameValue.name == chinese)
        return chineseNameValue; 
    } 
    return null;
  }
  
  private static int chineseToNumber(char chinese) {
    if ('两' == chinese)
      chinese = '二'; 
    int i = ArrayUtil.indexOf(DIGITS, chinese);
    if (i > 0)
      return (i + 1) / 2; 
    return i;
  }
  
  private static char numberToChinese(int number, boolean isUseTraditional) {
    if (0 == number)
      return DIGITS[0]; 
    return DIGITS[number * 2 - (isUseTraditional ? 0 : 1)];
  }
  
  private static String getUnitName(int index, boolean isUseTraditional) {
    if (0 == index)
      return ""; 
    return String.valueOf((CHINESE_NAME_VALUE[index * 2 - (isUseTraditional ? 0 : 1)]).name);
  }
  
  private static class ChineseUnit {
    private final char name;
    
    private final int value;
    
    private final boolean secUnit;
    
    public ChineseUnit(char name, int value, boolean secUnit) {
      this.name = name;
      this.value = value;
      this.secUnit = secUnit;
    }
  }
  
  private static void addPreZero(StringBuilder chineseStr) {
    if (StrUtil.isEmpty(chineseStr))
      return; 
    char c = chineseStr.charAt(0);
    if ('零' != c)
      chineseStr.insert(0, '零'); 
  }
}
