package cn.hutool.setting;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GroupedSet extends HashMap<String, LinkedHashSet<String>> {
  private static final long serialVersionUID = -8430706353275835496L;
  
  private static final String COMMENT_FLAG_PRE = "#";
  
  private static final char[] GROUP_SURROUND = new char[] { '[', ']' };
  
  private Charset charset;
  
  private URL groupedSetUrl;
  
  public GroupedSet(Charset charset) {
    this.charset = charset;
  }
  
  public GroupedSet(String pathBaseClassLoader, Charset charset) {
    if (null == pathBaseClassLoader)
      pathBaseClassLoader = ""; 
    URL url = URLUtil.getURL(pathBaseClassLoader);
    if (url == null)
      throw new RuntimeException(StrUtil.format("Can not find GroupSet file: [{}]", new Object[] { pathBaseClassLoader })); 
    init(url, charset);
  }
  
  public GroupedSet(File configFile, Charset charset) {
    if (configFile == null)
      throw new RuntimeException("Null GroupSet file!"); 
    URL url = URLUtil.getURL(configFile);
    init(url, charset);
  }
  
  public GroupedSet(String path, Class<?> clazz, Charset charset) {
    URL url = URLUtil.getURL(path, clazz);
    if (url == null)
      throw new RuntimeException(StrUtil.format("Can not find GroupSet file: [{}]", new Object[] { path })); 
    init(url, charset);
  }
  
  public GroupedSet(URL url, Charset charset) {
    if (url == null)
      throw new RuntimeException("Null url define!"); 
    init(url, charset);
  }
  
  public GroupedSet(String pathBaseClassLoader) {
    this(pathBaseClassLoader, CharsetUtil.CHARSET_UTF_8);
  }
  
  public boolean init(URL groupedSetUrl, Charset charset) {
    if (groupedSetUrl == null)
      throw new RuntimeException("Null GroupSet url or charset define!"); 
    this.charset = charset;
    this.groupedSetUrl = groupedSetUrl;
    return load(groupedSetUrl);
  }
  
  public synchronized boolean load(URL groupedSetUrl) {
    if (groupedSetUrl == null)
      throw new RuntimeException("Null GroupSet url define!"); 
    InputStream settingStream = null;
    try {
      settingStream = groupedSetUrl.openStream();
      load(settingStream);
    } catch (IOException e) {
      return false;
    } finally {
      IoUtil.close(settingStream);
    } 
    return true;
  }
  
  public void reload() {
    load(this.groupedSetUrl);
  }
  
  public boolean load(InputStream settingStream) throws IOException {
    clear();
    BufferedReader reader = null;
    try {
      reader = IoUtil.getReader(settingStream, this.charset);
      LinkedHashSet<String> valueSet = null;
      while (true) {
        String line = reader.readLine();
        if (line == null)
          break; 
        line = line.trim();
        if (StrUtil.isBlank(line) || line.startsWith("#"))
          continue; 
        if (line.startsWith("\\#"))
          line = line.substring(1); 
        if (line.charAt(0) == GROUP_SURROUND[0] && line.charAt(line.length() - 1) == GROUP_SURROUND[1]) {
          String group = line.substring(1, line.length() - 1).trim();
          valueSet = (LinkedHashSet<String>)get(group);
          if (null == valueSet)
            valueSet = new LinkedHashSet<>(); 
          put((K)group, (V)valueSet);
          continue;
        } 
        if (null == valueSet) {
          valueSet = new LinkedHashSet<>();
          put((K)"", (V)valueSet);
        } 
        valueSet.add(line);
      } 
    } finally {
      IoUtil.close(reader);
    } 
    return true;
  }
  
  public String getPath() {
    return this.groupedSetUrl.getPath();
  }
  
  public Set<String> getGroups() {
    return (Set)keySet();
  }
  
  public LinkedHashSet<String> getValues(String group) {
    if (group == null)
      group = ""; 
    return (LinkedHashSet<String>)get(group);
  }
  
  public boolean contains(String group, String value, String... otherValues) {
    if (ArrayUtil.isNotEmpty((Object[])otherValues)) {
      List<String> valueList = ListUtil.toList((Object[])otherValues);
      valueList.add(value);
      return contains(group, valueList);
    } 
    LinkedHashSet<String> valueSet = getValues(group);
    if (CollectionUtil.isEmpty(valueSet))
      return false; 
    return valueSet.contains(value);
  }
  
  public boolean contains(String group, Collection<String> values) {
    LinkedHashSet<String> valueSet = getValues(group);
    if (CollectionUtil.isEmpty(values) || CollectionUtil.isEmpty(valueSet))
      return false; 
    return valueSet.containsAll(values);
  }
}
