package cn.hutool.extra.template.engine.jetbrick;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import java.util.Properties;
import jetbrick.template.JetEngine;

public class JetbrickEngine implements TemplateEngine {
  private JetEngine engine;
  
  public JetbrickEngine() {}
  
  public JetbrickEngine(TemplateConfig config) {
    init(config);
  }
  
  public JetbrickEngine(JetEngine engine) {
    init(engine);
  }
  
  public TemplateEngine init(TemplateConfig config) {
    init(createEngine(config));
    return this;
  }
  
  private void init(JetEngine engine) {
    this.engine = engine;
  }
  
  public Template getTemplate(String resource) {
    if (null == this.engine)
      init(TemplateConfig.DEFAULT); 
    return (Template)JetbrickTemplate.wrap(this.engine.getTemplate(resource));
  }
  
  public JetEngine getRawEngine() {
    return this.engine;
  }
  
  private static JetEngine createEngine(TemplateConfig config) {
    if (null == config)
      config = TemplateConfig.DEFAULT; 
    Properties props = new Properties();
    props.setProperty("jetx.input.encoding", config.getCharsetStr());
    props.setProperty("jetx.output.encoding", config.getCharsetStr());
    props.setProperty("jetx.template.loaders", "$loader");
    switch (config.getResourceMode()) {
      case CLASSPATH:
        props.setProperty("$loader", "jetbrick.template.loader.ClasspathResourceLoader");
        props.setProperty("$loader.root", config.getPath());
        return JetEngine.create(props);
      case FILE:
        props.setProperty("$loader", "jetbrick.template.loader.FileSystemResourceLoader");
        props.setProperty("$loader.root", config.getPath());
        return JetEngine.create(props);
      case WEB_ROOT:
        props.setProperty("$loader", "jetbrick.template.loader.ServletResourceLoader");
        props.setProperty("$loader.root", config.getPath());
        return JetEngine.create(props);
      case STRING:
        props.setProperty("$loader", "cn.hutool.extra.template.engine.jetbrick.loader.StringResourceLoader");
        props.setProperty("$loader.charset", config.getCharsetStr());
        return JetEngine.create(props);
    } 
    return JetEngine.create();
  }
}
