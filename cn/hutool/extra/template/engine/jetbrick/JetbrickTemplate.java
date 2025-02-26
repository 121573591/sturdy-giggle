package cn.hutool.extra.template.engine.jetbrick;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.extra.template.AbstractTemplate;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import jetbrick.template.JetTemplate;

public class JetbrickTemplate extends AbstractTemplate implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final JetTemplate rawTemplate;
  
  public static JetbrickTemplate wrap(JetTemplate jetTemplate) {
    return (null == jetTemplate) ? null : new JetbrickTemplate(jetTemplate);
  }
  
  public JetbrickTemplate(JetTemplate jetTemplate) {
    this.rawTemplate = jetTemplate;
  }
  
  public void render(Map<?, ?> bindingMap, Writer writer) {
    Map<String, Object> map = (Map<String, Object>)Convert.convert(new TypeReference<Map<String, Object>>() {
        
        },  bindingMap);
    this.rawTemplate.render(map, writer);
  }
  
  public void render(Map<?, ?> bindingMap, OutputStream out) {
    Map<String, Object> map = (Map<String, Object>)Convert.convert(new TypeReference<Map<String, Object>>() {
        
        },  bindingMap);
    this.rawTemplate.render(map, out);
  }
}
