package cn.hutool.setting;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.StrUtil;
import java.util.Map;

public class SettingUtil {
  private static final Map<String, Setting> SETTING_MAP = (Map<String, Setting>)new SafeConcurrentHashMap();
  
  public static Setting get(String name) {
    return SETTING_MAP.computeIfAbsent(name, filePath -> {
          String extName = FileNameUtil.extName(filePath);
          if (StrUtil.isEmpty(extName))
            filePath = filePath + "." + "setting"; 
          return new Setting(filePath, true);
        });
  }
  
  public static Setting getFirstFound(String... names) {
    for (String name : names) {
      try {
        return get(name);
      } catch (NoResourceException noResourceException) {}
    } 
    return null;
  }
}
