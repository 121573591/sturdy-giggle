package cn.hutool.core.util;

public class DesensitizedUtil {
  public enum DesensitizedType {
    USER_ID, CHINESE_NAME, ID_CARD, FIXED_PHONE, MOBILE_PHONE, ADDRESS, EMAIL, PASSWORD, CAR_LICENSE, BANK_CARD, IPV4, IPV6, FIRST_MASK, CLEAR_TO_NULL, CLEAR_TO_EMPTY;
  }
  
  public static String desensitized(CharSequence str, DesensitizedType desensitizedType) {
    if (StrUtil.isBlank(str))
      return ""; 
    String newStr = String.valueOf(str);
    switch (desensitizedType) {
      case USER_ID:
        newStr = String.valueOf(userId());
        break;
      case CHINESE_NAME:
        newStr = chineseName(String.valueOf(str));
        break;
      case ID_CARD:
        newStr = idCardNum(String.valueOf(str), 1, 2);
        break;
      case FIXED_PHONE:
        newStr = fixedPhone(String.valueOf(str));
        break;
      case MOBILE_PHONE:
        newStr = mobilePhone(String.valueOf(str));
        break;
      case ADDRESS:
        newStr = address(String.valueOf(str), 8);
        break;
      case EMAIL:
        newStr = email(String.valueOf(str));
        break;
      case PASSWORD:
        newStr = password(String.valueOf(str));
        break;
      case CAR_LICENSE:
        newStr = carLicense(String.valueOf(str));
        break;
      case BANK_CARD:
        newStr = bankCard(String.valueOf(str));
        break;
      case IPV4:
        newStr = ipv4(String.valueOf(str));
        break;
      case IPV6:
        newStr = ipv6(String.valueOf(str));
        break;
      case FIRST_MASK:
        newStr = firstMask(String.valueOf(str));
        break;
      case CLEAR_TO_EMPTY:
        newStr = clear();
        break;
      case CLEAR_TO_NULL:
        newStr = clearToNull();
        break;
    } 
    return newStr;
  }
  
  public static String clear() {
    return "";
  }
  
  public static String clearToNull() {
    return null;
  }
  
  public static Long userId() {
    return Long.valueOf(0L);
  }
  
  public static String firstMask(String str) {
    if (StrUtil.isBlank(str))
      return ""; 
    return StrUtil.hide(str, 1, str.length());
  }
  
  public static String chineseName(String fullName) {
    return firstMask(fullName);
  }
  
  public static String idCardNum(String idCardNum, int front, int end) {
    if (StrUtil.isBlank(idCardNum))
      return ""; 
    if (front + end > idCardNum.length())
      return ""; 
    if (front < 0 || end < 0)
      return ""; 
    return StrUtil.hide(idCardNum, front, idCardNum.length() - end);
  }
  
  public static String fixedPhone(String num) {
    if (StrUtil.isBlank(num))
      return ""; 
    return StrUtil.hide(num, 4, num.length() - 2);
  }
  
  public static String mobilePhone(String num) {
    if (StrUtil.isBlank(num))
      return ""; 
    return StrUtil.hide(num, 3, num.length() - 4);
  }
  
  public static String address(String address, int sensitiveSize) {
    if (StrUtil.isBlank(address))
      return ""; 
    int length = address.length();
    return StrUtil.hide(address, length - sensitiveSize, length);
  }
  
  public static String email(String email) {
    if (StrUtil.isBlank(email))
      return ""; 
    int index = StrUtil.indexOf(email, '@');
    if (index <= 1)
      return email; 
    return StrUtil.hide(email, 1, index);
  }
  
  public static String password(String password) {
    if (StrUtil.isBlank(password))
      return ""; 
    return StrUtil.repeat('*', password.length());
  }
  
  public static String carLicense(String carLicense) {
    if (StrUtil.isBlank(carLicense))
      return ""; 
    if (carLicense.length() == 7) {
      carLicense = StrUtil.hide(carLicense, 3, 6);
    } else if (carLicense.length() == 8) {
      carLicense = StrUtil.hide(carLicense, 3, 7);
    } 
    return carLicense;
  }
  
  public static String bankCard(String bankCardNo) {
    if (StrUtil.isBlank(bankCardNo))
      return bankCardNo; 
    bankCardNo = StrUtil.cleanBlank(bankCardNo);
    if (bankCardNo.length() < 9)
      return bankCardNo; 
    int length = bankCardNo.length();
    int endLength = (length % 4 == 0) ? 4 : (length % 4);
    int midLength = length - 4 - endLength;
    StringBuilder buf = new StringBuilder();
    buf.append(bankCardNo, 0, 4);
    for (int i = 0; i < midLength; i++) {
      if (i % 4 == 0)
        buf.append(' '); 
      buf.append('*');
    } 
    buf.append(' ').append(bankCardNo, length - endLength, length);
    return buf.toString();
  }
  
  public static String ipv4(String ipv4) {
    return StrUtil.subBefore(ipv4, '.', false) + ".*.*.*";
  }
  
  public static String ipv6(String ipv6) {
    return StrUtil.subBefore(ipv6, ':', false) + ":*:*:*:*:*:*:*";
  }
}
