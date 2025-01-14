package cn.hutool.extra.template.engine.beetl;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import java.io.IOException;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.CompositeResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;

public class BeetlEngine implements TemplateEngine {
  private GroupTemplate engine;
  
  public BeetlEngine() {}
  
  public BeetlEngine(TemplateConfig config) {
    init(config);
  }
  
  public BeetlEngine(GroupTemplate engine) {
    init(engine);
  }
  
  public TemplateEngine init(TemplateConfig config) {
    init(createEngine(config));
    return this;
  }
  
  private void init(GroupTemplate engine) {
    this.engine = engine;
  }
  
  public Template getTemplate(String resource) {
    if (null == this.engine)
      init(TemplateConfig.DEFAULT); 
    return (Template)BeetlTemplate.wrap(this.engine.getTemplate(resource));
  }
  
  public GroupTemplate getRawEngine() {
    return this.engine;
  }
  
  private static GroupTemplate createEngine(TemplateConfig config) {
    if (null == config)
      config = TemplateConfig.DEFAULT; 
    switch (config.getResourceMode()) {
      case CLASSPATH:
        return createGroupTemplate((ResourceLoader<?>)new ClasspathResourceLoader(config.getPath(), config.getCharsetStr()));
      case FILE:
        return createGroupTemplate((ResourceLoader<?>)new FileResourceLoader(config.getPath(), config.getCharsetStr()));
      case WEB_ROOT:
        return createGroupTemplate((ResourceLoader<?>)new WebAppResourceLoader(config.getPath(), config.getCharsetStr()));
      case STRING:
        return createGroupTemplate((ResourceLoader<?>)new StringTemplateResourceLoader());
      case COMPOSITE:
        return createGroupTemplate((ResourceLoader<?>)new CompositeResourceLoader());
    } 
    return new GroupTemplate();
  }
  
  private static GroupTemplate createGroupTemplate(ResourceLoader<?> loader) {
    try {
      return createGroupTemplate(loader, Configuration.defaultConfiguration());
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  private static GroupTemplate createGroupTemplate(ResourceLoader<?> loader, Configuration conf) {
    return new GroupTemplate(loader, conf);
  }
}
