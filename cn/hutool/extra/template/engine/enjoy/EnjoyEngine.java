package cn.hutool.extra.template.engine.enjoy;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import com.jfinal.template.Engine;
import com.jfinal.template.source.FileSourceFactory;
import com.jfinal.template.source.ISourceFactory;
import java.io.File;

public class EnjoyEngine implements TemplateEngine {
  private Engine engine;
  
  private TemplateConfig.ResourceMode resourceMode;
  
  public EnjoyEngine() {}
  
  public EnjoyEngine(TemplateConfig config) {
    init(config);
  }
  
  public EnjoyEngine(Engine engine) {
    init(engine);
  }
  
  public TemplateEngine init(TemplateConfig config) {
    if (null == config)
      config = TemplateConfig.DEFAULT; 
    this.resourceMode = config.getResourceMode();
    init(createEngine(config));
    return this;
  }
  
  private void init(Engine engine) {
    this.engine = engine;
  }
  
  public Template getTemplate(String resource) {
    if (null == this.engine)
      init(TemplateConfig.DEFAULT); 
    if (ObjectUtil.equal(TemplateConfig.ResourceMode.STRING, this.resourceMode))
      return (Template)EnjoyTemplate.wrap(this.engine.getTemplateByString(resource)); 
    return (Template)EnjoyTemplate.wrap(this.engine.getTemplate(resource));
  }
  
  public Engine getRawEngine() {
    return this.engine;
  }
  
  private static Engine createEngine(TemplateConfig config) {
    File root;
    Engine engine = Engine.create("Hutool-Enjoy-Engine-" + IdUtil.fastSimpleUUID());
    engine.setEncoding(config.getCharsetStr());
    switch (config.getResourceMode()) {
      case CLASSPATH:
        engine.setToClassPathSourceFactory();
        engine.setBaseTemplatePath(config.getPath());
        break;
      case FILE:
        engine.setSourceFactory((ISourceFactory)new FileSourceFactory());
        engine.setBaseTemplatePath(config.getPath());
        break;
      case WEB_ROOT:
        engine.setSourceFactory((ISourceFactory)new FileSourceFactory());
        root = FileUtil.file(FileUtil.getWebRoot(), config.getPath());
        engine.setBaseTemplatePath(FileUtil.getAbsolutePath(root));
        break;
    } 
    return engine;
  }
}
