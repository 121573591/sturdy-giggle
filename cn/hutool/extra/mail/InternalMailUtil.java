package cn.hutool.extra.mail;

import cn.hutool.core.util.ArrayUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class InternalMailUtil {
  public static InternetAddress[] parseAddressFromStrs(String[] addrStrs, Charset charset) {
    List<InternetAddress> resultList = new ArrayList<>(addrStrs.length);
    for (String addrStr : addrStrs) {
      InternetAddress[] addrs = parseAddress(addrStr, charset);
      if (ArrayUtil.isNotEmpty((Object[])addrs))
        Collections.addAll(resultList, addrs); 
    } 
    return resultList.<InternetAddress>toArray(new InternetAddress[0]);
  }
  
  public static InternetAddress parseFirstAddress(String address, Charset charset) {
    InternetAddress[] internetAddresses = parseAddress(address, charset);
    if (ArrayUtil.isEmpty((Object[])internetAddresses))
      try {
        return new InternetAddress(address);
      } catch (AddressException e) {
        throw new MailException(e);
      }  
    return internetAddresses[0];
  }
  
  public static InternetAddress[] parseAddress(String address, Charset charset) {
    InternetAddress[] addresses;
    try {
      addresses = InternetAddress.parse(address);
    } catch (AddressException e) {
      throw new MailException(e);
    } 
    if (ArrayUtil.isNotEmpty((Object[])addresses)) {
      String charsetStr = (null == charset) ? null : charset.name();
      for (InternetAddress internetAddress : addresses) {
        try {
          internetAddress.setPersonal(internetAddress.getPersonal(), charsetStr);
        } catch (UnsupportedEncodingException e) {
          throw new MailException(e);
        } 
      } 
    } 
    return addresses;
  }
  
  public static String encodeText(String text, Charset charset) {
    try {
      return MimeUtility.encodeText(text, charset.name(), null);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      return text;
    } 
  }
}
