package cn.hutool.setting.dialect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.getter.BasicTypeGetter;
import cn.hutool.core.getter.OptBasicTypeGetter;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.FileResource;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.io.resource.UrlResource;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.WatchUtil;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public final class Props extends Properties implements BasicTypeGetter<String>, OptBasicTypeGetter<String> {
  private static final long serialVersionUID = 1935981579709590740L;
  
  public static final String EXT_NAME = "properties";
  
  private Resource resource;
  
  private WatchMonitor watchMonitor;
  
  public static Props create() {
    return new Props();
  }
  
  private transient Charset charset = CharsetUtil.CHARSET_ISO_8859_1;
  
  public static Props getProp(String resource) {
    return new Props(resource);
  }
  
  public static Props getProp(String resource, String charsetName) {
    return new Props(resource, charsetName);
  }
  
  public static Props getProp(String resource, Charset charset) {
    return new Props(resource, charset);
  }
  
  public Props(String path) {
    this(path, CharsetUtil.CHARSET_ISO_8859_1);
  }
  
  public Props(String path, String charsetName) {
    this(path, CharsetUtil.charset(charsetName));
  }
  
  public Props(String path, Charset charset) {
    Assert.notBlank(path, "Blank properties file path !", new Object[0]);
    if (null != charset)
      this.charset = charset; 
    load(ResourceUtil.getResourceObj(path));
  }
  
  public Props(File propertiesFile) {
    this(propertiesFile, StandardCharsets.ISO_8859_1);
  }
  
  public Props(File propertiesFile, String charsetName) {
    this(propertiesFile, Charset.forName(charsetName));
  }
  
  public Props(File propertiesFile, Charset charset) {
    Assert.notNull(propertiesFile, "Null properties file!", new Object[0]);
    this.charset = charset;
    load((Resource)new FileResource(propertiesFile));
  }
  
  public Props(String path, Class<?> clazz) {
    this(path, clazz, "ISO-8859-1");
  }
  
  public Props(String path, Class<?> clazz, String charsetName) {
    this(path, clazz, CharsetUtil.charset(charsetName));
  }
  
  public Props(String path, Class<?> clazz, Charset charset) {
    Assert.notBlank(path, "Blank properties file path !", new Object[0]);
    if (null != charset)
      this.charset = charset; 
    load((Resource)new ClassPathResource(path, clazz));
  }
  
  public Props(URL propertiesUrl) {
    this(propertiesUrl, StandardCharsets.ISO_8859_1);
  }
  
  public Props(URL propertiesUrl, String charsetName) {
    this(propertiesUrl, CharsetUtil.charset(charsetName));
  }
  
  public Props(URL propertiesUrl, Charset charset) {
    Assert.notNull(propertiesUrl, "Null properties URL !", new Object[0]);
    if (null != charset)
      this.charset = charset; 
    load(propertiesUrl);
  }
  
  public Props(Properties properties) {
    if (MapUtil.isNotEmpty(properties))
      putAll(properties); 
  }
  
  public void load(URL url) {
    load((Resource)new UrlResource(url));
  }
  
  public void load(Resource resource) {
    Assert.notNull(resource, "Props resource must be not null!", new Object[0]);
    this.resource = resource;
    try (BufferedReader reader = resource.getReader(this.charset)) {
      load(reader);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public void load() {
    load(this.resource);
  }
  
  public void autoLoad(boolean autoReload) {
    if (autoReload) {
      Assert.notNull(this.resource, "Properties resource must be not null!", new Object[0]);
      if (null != this.watchMonitor)
        this.watchMonitor.close(); 
      this.watchMonitor = WatchUtil.createModify(this.resource.getUrl(), (Watcher)new SimpleWatcher() {
            public void onModify(WatchEvent<?> event, Path currentPath) {
              Props.this.load();
            }
          });
      this.watchMonitor.start();
    } else {
      IoUtil.close((Closeable)this.watchMonitor);
      this.watchMonitor = null;
    } 
  }
  
  public Object getObj(String key, Object defaultValue) {
    return getStr(key, (null == defaultValue) ? null : defaultValue.toString());
  }
  
  public Object getObj(String key) {
    return getObj(key, (Object)null);
  }
  
  public String getStr(String key, String defaultValue) {
    return getProperty(key, defaultValue);
  }
  
  public String getStr(String key) {
    return getProperty(key);
  }
  
  public Integer getInt(String key, Integer defaultValue) {
    return Convert.toInt(getStr(key), defaultValue);
  }
  
  public Integer getInt(String key) {
    return getInt(key, (Integer)null);
  }
  
  public Boolean getBool(String key, Boolean defaultValue) {
    return Convert.toBool(getStr(key), defaultValue);
  }
  
  public Boolean getBool(String key) {
    return getBool(key, (Boolean)null);
  }
  
  public Long getLong(String key, Long defaultValue) {
    return Convert.toLong(getStr(key), defaultValue);
  }
  
  public Long getLong(String key) {
    return getLong(key, (Long)null);
  }
  
  public Character getChar(String key, Character defaultValue) {
    String value = getStr(key);
    if (StrUtil.isBlank(value))
      return defaultValue; 
    return Character.valueOf(value.charAt(0));
  }
  
  public Character getChar(String key) {
    return getChar(key, (Character)null);
  }
  
  public Float getFloat(String key) {
    return getFloat(key, (Float)null);
  }
  
  public Float getFloat(String key, Float defaultValue) {
    return Convert.toFloat(getStr(key), defaultValue);
  }
  
  public Double getDouble(String key, Double defaultValue) throws NumberFormatException {
    return Convert.toDouble(getStr(key), defaultValue);
  }
  
  public Double getDouble(String key) throws NumberFormatException {
    return getDouble(key, (Double)null);
  }
  
  public Short getShort(String key, Short defaultValue) {
    return Convert.toShort(getStr(key), defaultValue);
  }
  
  public Short getShort(String key) {
    return getShort(key, (Short)null);
  }
  
  public Byte getByte(String key, Byte defaultValue) {
    return Convert.toByte(getStr(key), defaultValue);
  }
  
  public Byte getByte(String key) {
    return getByte(key, (Byte)null);
  }
  
  public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
    String valueStr = getStr(key);
    if (StrUtil.isBlank(valueStr))
      return defaultValue; 
    try {
      return new BigDecimal(valueStr);
    } catch (Exception e) {
      return defaultValue;
    } 
  }
  
  public BigDecimal getBigDecimal(String key) {
    return getBigDecimal(key, (BigDecimal)null);
  }
  
  public BigInteger getBigInteger(String key, BigInteger defaultValue) {
    String valueStr = getStr(key);
    if (StrUtil.isBlank(valueStr))
      return defaultValue; 
    try {
      return new BigInteger(valueStr);
    } catch (Exception e) {
      return defaultValue;
    } 
  }
  
  public BigInteger getBigInteger(String key) {
    return getBigInteger(key, (BigInteger)null);
  }
  
  public <E extends Enum<E>> E getEnum(Class<E> clazz, String key, E defaultValue) {
    return (E)Convert.toEnum(clazz, getStr(key), (Enum)defaultValue);
  }
  
  public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) {
    return getEnum(clazz, key, (E)null);
  }
  
  public Date getDate(String key, Date defaultValue) {
    return Convert.toDate(getStr(key), defaultValue);
  }
  
  public Date getDate(String key) {
    return getDate(key, (Date)null);
  }
  
  public String getAndRemoveStr(String... keys) {
    Object value = null;
    for (String key : keys) {
      value = remove(key);
      if (null != value)
        break; 
    } 
    return (String)value;
  }
  
  public Properties toProperties() {
    Properties properties = new Properties();
    properties.putAll(this);
    return properties;
  }
  
  public <T> T toBean(Class<T> beanClass) {
    return toBean(beanClass, (String)null);
  }
  
  public <T> T toBean(Class<T> beanClass, String prefix) {
    T bean = (T)ReflectUtil.newInstanceIfPossible(beanClass);
    return fillBean(bean, prefix);
  }
  
  public <T> T fillBean(T bean, String prefix) {
    prefix = StrUtil.nullToEmpty(StrUtil.addSuffixIfNot(prefix, "."));
    for (Map.Entry<Object, Object> entry : entrySet()) {
      String key = (String)entry.getKey();
      if (false == StrUtil.startWith(key, prefix))
        continue; 
      try {
        BeanUtil.setProperty(bean, StrUtil.subSuf(key, prefix.length()), entry.getValue());
      } catch (Exception e) {
        StaticLog.debug("Ignore property: [{}],because of: {}", new Object[] { key, e });
      } 
    } 
    return bean;
  }
  
  public void setProperty(String key, Object value) {
    setProperty(key, value.toString());
  }
  
  public void store(String absolutePath) throws IORuntimeException {
    Writer writer = null;
    try {
      writer = FileUtil.getWriter(absolutePath, this.charset, false);
      store(writer, (String)null);
    } catch (IOException e) {
      throw new IORuntimeException(e, "Store properties to [{}] error!", new Object[] { absolutePath });
    } finally {
      IoUtil.close(writer);
    } 
  }
  
  public void store(String path, Class<?> clazz) {
    store(FileUtil.getAbsolutePath(path, clazz));
  }
  
  public Props() {}
}
