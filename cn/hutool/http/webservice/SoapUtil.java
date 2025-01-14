package cn.hutool.http.webservice;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.XmlUtil;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SoapUtil {
  public static SoapClient createClient(String url) {
    return SoapClient.create(url);
  }
  
  public static SoapClient createClient(String url, SoapProtocol protocol) {
    return SoapClient.create(url, protocol);
  }
  
  public static SoapClient createClient(String url, SoapProtocol protocol, String namespaceURI) {
    return SoapClient.create(url, protocol, namespaceURI);
  }
  
  public static String toString(SOAPMessage message, boolean pretty) {
    return toString(message, pretty, CharsetUtil.CHARSET_UTF_8);
  }
  
  public static String toString(SOAPMessage message, boolean pretty, Charset charset) {
    String messageToString;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      message.writeTo(out);
    } catch (SOAPException|java.io.IOException e) {
      throw new SoapRuntimeException(e);
    } 
    try {
      messageToString = out.toString(charset.toString());
    } catch (UnsupportedEncodingException e) {
      throw new UtilException(e);
    } 
    return pretty ? XmlUtil.format(messageToString) : messageToString;
  }
}
