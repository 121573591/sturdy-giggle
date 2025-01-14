package cn.hutool.extra.template.engine.thymeleaf;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.DefaultTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

public class ThymeleafEngine implements TemplateEngine {
  TemplateEngine engine;
  
  TemplateConfig config;
  
  public ThymeleafEngine() {}
  
  public ThymeleafEngine(TemplateConfig config) {
    init(config);
  }
  
  public ThymeleafEngine(TemplateEngine engine) {
    init(engine);
  }
  
  public TemplateEngine init(TemplateConfig config) {
    if (null == config)
      config = TemplateConfig.DEFAULT; 
    this.config = config;
    init(createEngine(config));
    return this;
  }
  
  private void init(TemplateEngine engine) {
    this.engine = engine;
  }
  
  public Template getTemplate(String resource) {
    if (null == this.engine)
      init(TemplateConfig.DEFAULT); 
    return (Template)ThymeleafTemplate.wrap(this.engine, resource, (null == this.config) ? null : this.config.getCharset());
  }
  
  public TemplateEngine getRawEngine() {
    return this.engine;
  }
  
  private static TemplateEngine createEngine(TemplateConfig config) {
    ClassLoaderTemplateResolver classLoaderTemplateResolver1;
    FileTemplateResolver fileTemplateResolver2, fileTemplateResolver1;
    StringTemplateResolver stringTemplateResolver;
    ClassLoaderTemplateResolver classLoaderResolver;
    FileTemplateResolver fileResolver, webRootResolver;
    if (null == config)
      config = new TemplateConfig(); 
    switch (config.getResourceMode()) {
      case CLASSPATH:
        classLoaderResolver = new ClassLoaderTemplateResolver();
        classLoaderResolver.setCharacterEncoding(config.getCharsetStr());
        classLoaderResolver.setTemplateMode(TemplateMode.HTML);
        classLoaderResolver.setPrefix(StrUtil.addSuffixIfNot(config.getPath(), "/"));
        classLoaderTemplateResolver1 = classLoaderResolver;
        engine = new TemplateEngine();
        engine.setTemplateResolver((ITemplateResolver)classLoaderTemplateResolver1);
        return engine;
      case FILE:
        fileResolver = new FileTemplateResolver();
        fileResolver.setCharacterEncoding(config.getCharsetStr());
        fileResolver.setTemplateMode(TemplateMode.HTML);
        fileResolver.setPrefix(StrUtil.addSuffixIfNot(config.getPath(), "/"));
        fileTemplateResolver2 = fileResolver;
        engine = new TemplateEngine();
        engine.setTemplateResolver((ITemplateResolver)fileTemplateResolver2);
        return engine;
      case WEB_ROOT:
        webRootResolver = new FileTemplateResolver();
        webRootResolver.setCharacterEncoding(config.getCharsetStr());
        webRootResolver.setTemplateMode(TemplateMode.HTML);
        webRootResolver.setPrefix(StrUtil.addSuffixIfNot(FileUtil.getAbsolutePath(FileUtil.file(FileUtil.getWebRoot(), config.getPath())), "/"));
        fileTemplateResolver1 = webRootResolver;
        engine = new TemplateEngine();
        engine.setTemplateResolver((ITemplateResolver)fileTemplateResolver1);
        return engine;
      case STRING:
        stringTemplateResolver = new StringTemplateResolver();
        engine = new TemplateEngine();
        engine.setTemplateResolver((ITemplateResolver)stringTemplateResolver);
        return engine;
    } 
    DefaultTemplateResolver defaultTemplateResolver = new DefaultTemplateResolver();
    TemplateEngine engine = new TemplateEngine();
    engine.setTemplateResolver((ITemplateResolver)defaultTemplateResolver);
    return engine;
  }
}
