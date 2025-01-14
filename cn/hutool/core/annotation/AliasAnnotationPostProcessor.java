package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.map.ForestMap;
import cn.hutool.core.map.LinkedForestMap;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import java.util.Map;

public class AliasAnnotationPostProcessor implements SynthesizedAnnotationPostProcessor {
  public int order() {
    return Integer.MIN_VALUE;
  }
  
  public void process(SynthesizedAnnotation synthesizedAnnotation, AnnotationSynthesizer synthesizer) {
    Map<String, AnnotationAttribute> attributeMap = synthesizedAnnotation.getAttributes();
    LinkedForestMap linkedForestMap = new LinkedForestMap(false);
    attributeMap.forEach((attributeName, attribute) -> {
          String alias = (String)Opt.ofNullable(attribute.getAnnotation(Alias.class)).map(Alias::value).orElse(null);
          if (ObjectUtil.isNull(alias))
            return; 
          AnnotationAttribute aliasAttribute = (AnnotationAttribute)attributeMap.get(alias);
          Assert.notNull(aliasAttribute, "no method for alias: [{}]", new Object[] { alias });
          attributeAliasMappings.putLinkedNodes(alias, aliasAttribute, attributeName, attribute);
        });
    attributeMap.forEach((attributeName, attribute) -> {
          AnnotationAttribute resolvedAttribute = (AnnotationAttribute)Opt.ofNullable(attributeName).map(attributeAliasMappings::getRootNode).map(Map.Entry::getValue).orElse(attribute);
          Assert.isTrue((ObjectUtil.isNull(resolvedAttribute) || ClassUtil.isAssignable(attribute.getAttributeType(), resolvedAttribute.getAttributeType())), "return type of the root alias method [{}] is inconsistent with the original [{}]", new Object[] { resolvedAttribute.getClass(), attribute.getAttributeType() });
          if (attribute != resolvedAttribute)
            attributeMap.put(attributeName, new ForceAliasedAnnotationAttribute(attribute, resolvedAttribute)); 
        });
    synthesizedAnnotation.setAttributes(attributeMap);
  }
}
