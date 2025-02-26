package cn.hutool.extra.template.engine.freemarker;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.template.AbstractTemplate;
import cn.hutool.extra.template.TemplateException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

public class FreemarkerTemplate extends AbstractTemplate implements Serializable {
  private static final long serialVersionUID = -8157926902932567280L;
  
  Template rawTemplate;
  
  public static FreemarkerTemplate wrap(Template beetlTemplate) {
    return (null == beetlTemplate) ? null : new FreemarkerTemplate(beetlTemplate);
  }
  
  public FreemarkerTemplate(Template freemarkerTemplate) {
    this.rawTemplate = freemarkerTemplate;
  }
  
  public void render(Map<?, ?> bindingMap, Writer writer) {
    try {
      this.rawTemplate.process(bindingMap, writer);
    } catch (TemplateException e) {
      throw new TemplateException(e);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public void render(Map<?, ?> bindingMap, OutputStream out) {
    render(bindingMap, IoUtil.getWriter(out, Charset.forName(this.rawTemplate.getEncoding())));
  }
}
