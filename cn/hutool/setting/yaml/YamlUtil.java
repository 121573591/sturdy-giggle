package cn.hutool.setting.yaml;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Dict;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class YamlUtil {
  public static Dict loadByPath(String path) {
    return loadByPath(path, Dict.class);
  }
  
  public static <T> T loadByPath(String path, Class<T> type) {
    return load(ResourceUtil.getStream(path), type);
  }
  
  public static <T> T load(InputStream in, Class<T> type) {
    return load((Reader)IoUtil.getBomReader(in), type);
  }
  
  public static Dict load(Reader reader) {
    return load(reader, Dict.class);
  }
  
  public static <T> T load(Reader reader, Class<T> type) {
    return load(reader, type, true);
  }
  
  public static <T> T load(Reader reader, Class<T> type, boolean isCloseReader) {
    Class<Object> clazz;
    Assert.notNull(reader, "Reader must be not null !", new Object[0]);
    if (null == type)
      clazz = Object.class; 
    Yaml yaml = new Yaml();
    try {
      return (T)yaml.loadAs(reader, clazz);
    } finally {
      if (isCloseReader)
        IoUtil.close(reader); 
    } 
  }
  
  public static void dump(Object object, Writer writer) {
    DumperOptions options = new DumperOptions();
    options.setIndent(2);
    options.setPrettyFlow(true);
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    dump(object, writer, options);
  }
  
  public static void dump(Object object, Writer writer, DumperOptions dumperOptions) {
    if (null == dumperOptions)
      dumperOptions = new DumperOptions(); 
    Yaml yaml = new Yaml(dumperOptions);
    yaml.dump(object, writer);
  }
}
