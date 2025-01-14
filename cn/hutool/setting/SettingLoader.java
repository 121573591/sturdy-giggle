package cn.hutool.setting;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.SystemPropsUtil;
import cn.hutool.log.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SettingLoader {
  private static final Log log = Log.get();
  
  private static final char COMMENT_FLAG_PRE = '#';
  
  private char assignFlag = '=';
  
  private String varRegex = "\\$\\{(.*?)\\}";
  
  private final Charset charset;
  
  private final boolean isUseVariable;
  
  private final GroupedMap groupedMap;
  
  public SettingLoader(GroupedMap groupedMap) {
    this(groupedMap, CharsetUtil.CHARSET_UTF_8, false);
  }
  
  public SettingLoader(GroupedMap groupedMap, Charset charset, boolean isUseVariable) {
    this.groupedMap = groupedMap;
    this.charset = charset;
    this.isUseVariable = isUseVariable;
  }
  
  public boolean load(Resource resource) {
    if (resource == null)
      throw new NullPointerException("Null setting url define!"); 
    log.debug("Load setting file [{}]", new Object[] { resource });
    InputStream settingStream = null;
    try {
      settingStream = resource.getStream();
      load(settingStream);
    } catch (Exception e) {
      log.error(e, "Load setting error!", new Object[0]);
      return false;
    } finally {
      IoUtil.close(settingStream);
    } 
    return true;
  }
  
  public synchronized boolean load(InputStream settingStream) throws IOException {
    this.groupedMap.clear();
    BufferedReader reader = null;
    try {
      reader = IoUtil.getReader(settingStream, this.charset);
      String group = null;
      while (true) {
        String line = reader.readLine();
        if (line == null)
          break; 
        line = StrUtil.trim(line);
        if (StrUtil.isBlank(line) || StrUtil.startWith(line, '#'))
          continue; 
        if (StrUtil.isSurround(line, '[', ']')) {
          group = StrUtil.trim(line.substring(1, line.length() - 1));
          continue;
        } 
        String[] keyValue = StrUtil.splitToArray(line, this.assignFlag, 2);
        if (keyValue.length < 2)
          continue; 
        String value = StrUtil.trim(keyValue[1]);
        if (this.isUseVariable)
          value = replaceVar(group, value); 
        this.groupedMap.put(group, StrUtil.trim(keyValue[0]), value);
      } 
    } finally {
      IoUtil.close(reader);
    } 
    return true;
  }
  
  public void setVarRegex(String regex) {
    this.varRegex = regex;
  }
  
  public void setAssignFlag(char assignFlag) {
    this.assignFlag = assignFlag;
  }
  
  public void store(String absolutePath) {
    store(FileUtil.touch(absolutePath));
  }
  
  public void store(File file) {
    Assert.notNull(file, "File to store must be not null !", new Object[0]);
    log.debug("Store Setting to [{}]...", new Object[] { file.getAbsolutePath() });
    PrintWriter writer = null;
    try {
      writer = FileUtil.getPrintWriter(file, this.charset, false);
      store(writer);
    } finally {
      IoUtil.close(writer);
    } 
  }
  
  private synchronized void store(PrintWriter writer) {
    for (Map.Entry<String, LinkedHashMap<String, String>> groupEntry : this.groupedMap.entrySet()) {
      writer.println(StrUtil.format("{}{}{}", new Object[] { Character.valueOf('['), groupEntry.getKey(), Character.valueOf(']') }));
      for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)((LinkedHashMap)groupEntry.getValue()).entrySet()) {
        writer.println(StrUtil.format("{} {} {}", new Object[] { entry.getKey(), Character.valueOf(this.assignFlag), entry.getValue() }));
      } 
    } 
  }
  
  private String replaceVar(String group, String value) {
    Set<String> vars = (Set<String>)ReUtil.findAll(this.varRegex, value, 0, new HashSet());
    for (String var : vars) {
      String key = ReUtil.get(this.varRegex, var, 1);
      if (StrUtil.isNotBlank(key)) {
        String varValue = this.groupedMap.get(group, key);
        if (null == varValue) {
          List<String> groupAndKey = StrUtil.split(key, '.', 2);
          if (groupAndKey.size() > 1)
            varValue = this.groupedMap.get(groupAndKey.get(0), groupAndKey.get(1)); 
        } 
        if (null == varValue)
          varValue = SystemPropsUtil.get(key); 
        if (null != varValue)
          value = value.replace(var, varValue); 
      } 
    } 
    return value;
  }
}
