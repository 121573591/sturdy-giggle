package cn.hutool.extra.template;

public interface TemplateEngine {
  TemplateEngine init(TemplateConfig paramTemplateConfig);
  
  Template getTemplate(String paramString);
}
