package cn.hutool.extra.servlet;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.collection.ArrayIter;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.net.multipart.MultipartFormData;
import cn.hutool.core.net.multipart.UploadSetting;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
  public static final String METHOD_DELETE = "DELETE";
  
  public static final String METHOD_HEAD = "HEAD";
  
  public static final String METHOD_GET = "GET";
  
  public static final String METHOD_OPTIONS = "OPTIONS";
  
  public static final String METHOD_POST = "POST";
  
  public static final String METHOD_PUT = "PUT";
  
  public static final String METHOD_TRACE = "TRACE";
  
  public static Map<String, String[]> getParams(ServletRequest request) {
    Map<String, String[]> map = request.getParameterMap();
    return (Map)Collections.unmodifiableMap((Map)map);
  }
  
  public static Map<String, String> getParamMap(ServletRequest request) {
    Map<String, String> params = new HashMap<>();
    for (Map.Entry<String, String[]> entry : getParams(request).entrySet())
      params.put(entry.getKey(), ArrayUtil.join((Object[])entry.getValue(), ",")); 
    return params;
  }
  
  public static String getBody(ServletRequest request) {
    try (BufferedReader reader = request.getReader()) {
      return IoUtil.read(reader);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static byte[] getBodyBytes(ServletRequest request) {
    try {
      return IoUtil.readBytes((InputStream)request.getInputStream());
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static <T> T fillBean(final ServletRequest request, T bean, CopyOptions copyOptions) {
    final String beanName = StrUtil.lowerFirst(bean.getClass().getSimpleName());
    return (T)BeanUtil.fillBean(bean, new ValueProvider<String>() {
          public Object value(String key, Type valueType) {
            String[] values = request.getParameterValues(key);
            if (ArrayUtil.isEmpty((Object[])values)) {
              values = request.getParameterValues(beanName + "." + key);
              if (ArrayUtil.isEmpty((Object[])values))
                return null; 
            } 
            if (1 == values.length)
              return values[0]; 
            return values;
          }
          
          public boolean containsKey(String key) {
            return (null != request.getParameter(key) || null != request.getParameter(beanName + "." + key));
          }
        },  copyOptions);
  }
  
  public static <T> T fillBean(ServletRequest request, T bean, boolean isIgnoreError) {
    return fillBean(request, bean, CopyOptions.create().setIgnoreError(isIgnoreError));
  }
  
  public static <T> T toBean(ServletRequest request, Class<T> beanClass, boolean isIgnoreError) {
    return fillBean(request, (T)ReflectUtil.newInstanceIfPossible(beanClass), isIgnoreError);
  }
  
  public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
    String[] headers = { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
    if (ArrayUtil.isNotEmpty((Object[])otherHeaderNames))
      headers = (String[])ArrayUtil.addAll((Object[][])new String[][] { headers, otherHeaderNames }); 
    return getClientIPByHeader(request, headers);
  }
  
  public static String getClientIPByHeader(HttpServletRequest request, String... headerNames) {
    for (String header : headerNames) {
      String str1 = request.getHeader(header);
      if (false == NetUtil.isUnknown(str1))
        return NetUtil.getMultistageReverseProxyIp(str1); 
    } 
    String ip = request.getRemoteAddr();
    return NetUtil.getMultistageReverseProxyIp(ip);
  }
  
  public static MultipartFormData getMultipart(ServletRequest request) throws IORuntimeException {
    return getMultipart(request, new UploadSetting());
  }
  
  public static MultipartFormData getMultipart(ServletRequest request, UploadSetting uploadSetting) throws IORuntimeException {
    MultipartFormData formData = new MultipartFormData(uploadSetting);
    try {
      formData.parseRequestStream((InputStream)request.getInputStream(), CharsetUtil.charset(request.getCharacterEncoding()));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return formData;
  }
  
  public static Map<String, String> getHeaderMap(HttpServletRequest request) {
    Map<String, String> headerMap = new HashMap<>();
    Enumeration<String> names = request.getHeaderNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      headerMap.put(name, request.getHeader(name));
    } 
    return headerMap;
  }
  
  public static Map<String, List<String>> getHeadersMap(HttpServletRequest request) {
    Map<String, List<String>> headerMap = new LinkedHashMap<>();
    Enumeration<String> names = request.getHeaderNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      headerMap.put(name, ListUtil.list(false, request.getHeaders(name)));
    } 
    return headerMap;
  }
  
  public static Map<String, Collection<String>> getHeadersMap(HttpServletResponse response) {
    Map<String, Collection<String>> headerMap = new HashMap<>();
    Collection<String> names = response.getHeaderNames();
    for (String name : names)
      headerMap.put(name, response.getHeaders(name)); 
    return headerMap;
  }
  
  public static String getHeaderIgnoreCase(HttpServletRequest request, String nameIgnoreCase) {
    Enumeration<String> names = request.getHeaderNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      if (name != null && name.equalsIgnoreCase(nameIgnoreCase))
        return request.getHeader(name); 
    } 
    return null;
  }
  
  public static String getHeader(HttpServletRequest request, String name, String charsetName) {
    return getHeader(request, name, CharsetUtil.charset(charsetName));
  }
  
  public static String getHeader(HttpServletRequest request, String name, Charset charset) {
    String header = request.getHeader(name);
    if (null != header)
      return CharsetUtil.convert(header, CharsetUtil.CHARSET_ISO_8859_1, charset); 
    return null;
  }
  
  public static boolean isIE(HttpServletRequest request) {
    String userAgent = getHeaderIgnoreCase(request, "User-Agent");
    if (StrUtil.isNotBlank(userAgent)) {
      userAgent = userAgent.toUpperCase();
      return (userAgent.contains("MSIE") || userAgent.contains("TRIDENT"));
    } 
    return false;
  }
  
  public static boolean isGetMethod(HttpServletRequest request) {
    return "GET".equalsIgnoreCase(request.getMethod());
  }
  
  public static boolean isPostMethod(HttpServletRequest request) {
    return "POST".equalsIgnoreCase(request.getMethod());
  }
  
  public static boolean isMultipart(HttpServletRequest request) {
    if (false == isPostMethod(request))
      return false; 
    String contentType = request.getContentType();
    if (StrUtil.isBlank(contentType))
      return false; 
    return contentType.toLowerCase().startsWith("multipart/");
  }
  
  public static Cookie getCookie(HttpServletRequest httpServletRequest, String name) {
    return readCookieMap(httpServletRequest).get(name);
  }
  
  public static Map<String, Cookie> readCookieMap(HttpServletRequest httpServletRequest) {
    Cookie[] cookies = httpServletRequest.getCookies();
    if (ArrayUtil.isEmpty((Object[])cookies))
      return MapUtil.empty(); 
    return IterUtil.toMap((Iterator)new ArrayIter((Object[])httpServletRequest
          .getCookies()), (Map)new CaseInsensitiveMap(), Cookie::getName);
  }
  
  public static void addCookie(HttpServletResponse response, Cookie cookie) {
    response.addCookie(cookie);
  }
  
  public static void addCookie(HttpServletResponse response, String name, String value) {
    response.addCookie(new Cookie(name, value));
  }
  
  public static void addCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain) {
    Cookie cookie = new Cookie(name, value);
    if (domain != null)
      cookie.setDomain(domain); 
    cookie.setMaxAge(maxAgeInSeconds);
    cookie.setPath(path);
    addCookie(response, cookie);
  }
  
  public static void addCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
    addCookie(response, name, value, maxAgeInSeconds, "/", null);
  }
  
  public static PrintWriter getWriter(HttpServletResponse response) throws IORuntimeException {
    try {
      return response.getWriter();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static void write(HttpServletResponse response, String text, String contentType) {
    response.setContentType(contentType);
    Writer writer = null;
    try {
      writer = response.getWriter();
      writer.write(text);
      writer.flush();
    } catch (IOException e) {
      throw new UtilException(e);
    } finally {
      IoUtil.close(writer);
    } 
  }
  
  public static void write(HttpServletResponse response, File file) {
    String fileName = file.getName();
    String contentType = (String)ObjectUtil.defaultIfNull(FileUtil.getMimeType(fileName), "application/octet-stream");
    BufferedInputStream in = null;
    try {
      in = FileUtil.getInputStream(file);
      write(response, in, contentType, fileName);
    } finally {
      IoUtil.close(in);
    } 
  }
  
  public static void write(HttpServletResponse response, InputStream in, String contentType, String fileName) {
    String charset = (String)ObjectUtil.defaultIfNull(response.getCharacterEncoding(), "UTF-8");
    String encodeText = URLUtil.encodeAll(fileName, CharsetUtil.charset(charset));
    response.setHeader("Content-Disposition", 
        StrUtil.format("attachment;filename=\"{}\";filename*={}''{}", new Object[] { encodeText, charset, encodeText }));
    response.setContentType(contentType);
    write(response, in);
  }
  
  public static void write(HttpServletResponse response, InputStream in, String contentType) {
    response.setContentType(contentType);
    write(response, in);
  }
  
  public static void write(HttpServletResponse response, InputStream in) {
    write(response, in, 8192);
  }
  
  public static void write(HttpServletResponse response, InputStream in, int bufferSize) {
    ServletOutputStream out = null;
    try {
      out = response.getOutputStream();
      IoUtil.copy(in, (OutputStream)out, bufferSize);
    } catch (IOException e) {
      throw new UtilException(e);
    } finally {
      IoUtil.close((Closeable)out);
      IoUtil.close(in);
    } 
  }
  
  public static void setHeader(HttpServletResponse response, String name, Object value) {
    if (value instanceof String) {
      response.setHeader(name, (String)value);
    } else if (Date.class.isAssignableFrom(value.getClass())) {
      response.setDateHeader(name, ((Date)value).getTime());
    } else if (value instanceof Integer || "int".equalsIgnoreCase(value.getClass().getSimpleName())) {
      response.setIntHeader(name, ((Integer)value).intValue());
    } else {
      response.setHeader(name, value.toString());
    } 
  }
}
