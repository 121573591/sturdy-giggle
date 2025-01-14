package cn.hutool.extra.template.engine.freemarker;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateException;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import java.io.IOException;

public class FreemarkerEngine implements TemplateEngine {
  private Configuration cfg;
  
  public FreemarkerEngine() {}
  
  public FreemarkerEngine(TemplateConfig config) {
    init(config);
  }
  
  public FreemarkerEngine(Configuration freemarkerCfg) {
    init(freemarkerCfg);
  }
  
  public TemplateEngine init(TemplateConfig config) {
    if (null == config)
      config = TemplateConfig.DEFAULT; 
    init(createCfg(config));
    return this;
  }
  
  private void init(Configuration freemarkerCfg) {
    this.cfg = freemarkerCfg;
  }
  
  public Template getTemplate(String resource) {
    if (null == this.cfg)
      init(TemplateConfig.DEFAULT); 
    try {
      return (Template)FreemarkerTemplate.wrap(this.cfg.getTemplate(resource));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } catch (Exception e) {
      throw new TemplateException(e);
    } 
  }
  
  public Configuration getConfiguration() {
    return this.cfg;
  }
  
  private static Configuration createCfg(TemplateConfig config) {
    if (null == config)
      config = new TemplateConfig(); 
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
    cfg.setLocalizedLookup(false);
    cfg.setDefaultEncoding(config.getCharset().toString());
    switch (config.getResourceMode()) {
      case CLASSPATH:
        cfg.setTemplateLoader((TemplateLoader)new ClassTemplateLoader(ClassUtil.getClassLoader(), config.getPath()));
        break;
      case FILE:
        try {
          cfg.setTemplateLoader((TemplateLoader)new FileTemplateLoader(FileUtil.file(config.getPath())));
        } catch (IOException e) {
          throw new IORuntimeException(e);
        } 
        break;
      case WEB_ROOT:
        try {
          cfg.setTemplateLoader((TemplateLoader)new FileTemplateLoader(FileUtil.file(FileUtil.getWebRoot(), config.getPath())));
        } catch (IOException e) {
          throw new IORuntimeException(e);
        } 
        break;
      case STRING:
        cfg.setTemplateLoader(new SimpleStringTemplateLoader());
        break;
    } 
    return cfg;
  }
}
