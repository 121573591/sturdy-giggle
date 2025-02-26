package cn.hutool.extra.template.engine.enjoy;

import cn.hutool.extra.template.AbstractTemplate;
import com.jfinal.template.Template;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;

public class EnjoyTemplate extends AbstractTemplate implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Template rawTemplate;
  
  public static EnjoyTemplate wrap(Template template) {
    return (null == template) ? null : new EnjoyTemplate(template);
  }
  
  public EnjoyTemplate(Template template) {
    this.rawTemplate = template;
  }
  
  public void render(Map<?, ?> bindingMap, Writer writer) {
    this.rawTemplate.render(bindingMap, writer);
  }
  
  public void render(Map<?, ?> bindingMap, OutputStream out) {
    this.rawTemplate.render(bindingMap, out);
  }
}
