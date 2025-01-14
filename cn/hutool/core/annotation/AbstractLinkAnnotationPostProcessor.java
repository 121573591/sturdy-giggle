package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLinkAnnotationPostProcessor implements SynthesizedAnnotationPostProcessor {
  public void process(SynthesizedAnnotation synthesizedAnnotation, AnnotationSynthesizer synthesizer) {
    Map<String, AnnotationAttribute> attributeMap = new HashMap<>(synthesizedAnnotation.getAttributes());
    attributeMap.forEach((originalAttributeName, originalAttribute) -> {
          Link link = getLinkAnnotation(originalAttribute, processTypes());
          if (ObjectUtil.isNull(link))
            return; 
          SynthesizedAnnotation linkedAnnotation = getLinkedAnnotation(link, synthesizer, synthesizedAnnotation.annotationType());
          if (ObjectUtil.isNull(linkedAnnotation))
            return; 
          AnnotationAttribute linkedAttribute = linkedAnnotation.getAttributes().get(link.attribute());
          processLinkedAttribute(synthesizer, link, synthesizedAnnotation, synthesizedAnnotation.getAttributes().get(originalAttributeName), linkedAnnotation, linkedAttribute);
        });
  }
  
  protected Link getLinkAnnotation(AnnotationAttribute attribute, RelationType... relationTypes) {
    return (Link)Opt.ofNullable(attribute)
      .map(t -> (Link)AnnotationUtil.<Link>getSynthesizedAnnotation(attribute.getAttribute(), Link.class))
      .filter(a -> ArrayUtil.contains((Object[])relationTypes, a.type()))
      .get();
  }
  
  protected SynthesizedAnnotation getLinkedAnnotation(Link annotation, AnnotationSynthesizer synthesizer, Class<? extends Annotation> defaultType) {
    Class<?> targetAnnotationType = getLinkedAnnotationType(annotation, defaultType);
    return synthesizer.getSynthesizedAnnotation(targetAnnotationType);
  }
  
  protected Class<?> getLinkedAnnotationType(Link annotation, Class<?> defaultType) {
    return ObjectUtil.equals(annotation.annotation(), Annotation.class) ? defaultType : annotation
      .annotation();
  }
  
  protected void checkAttributeType(AnnotationAttribute original, AnnotationAttribute alias) {
    Assert.equals(original
        .getAttributeType(), alias.getAttributeType(), "return type of the linked attribute [{}] is inconsistent with the original [{}]", new Object[] { original
          
          .getAttribute(), alias.getAttribute() });
  }
  
  protected void checkLinkedSelf(AnnotationAttribute original, AnnotationAttribute linked) {
    boolean linkSelf = (original == linked || ObjectUtil.equals(original.getAttribute(), linked.getAttribute()));
    Assert.isFalse(linkSelf, "cannot link self [{}]", new Object[] { original.getAttribute() });
  }
  
  protected void checkLinkedAttributeNotNull(AnnotationAttribute original, AnnotationAttribute linkedAttribute, Link annotation) {
    Assert.notNull(linkedAttribute, "cannot find linked attribute [{}] of original [{}] in [{}]", new Object[] { original
          .getAttribute(), annotation.attribute(), 
          getLinkedAnnotationType(annotation, original.getAnnotationType()) });
  }
  
  protected abstract RelationType[] processTypes();
  
  protected abstract void processLinkedAttribute(AnnotationSynthesizer paramAnnotationSynthesizer, Link paramLink, SynthesizedAnnotation paramSynthesizedAnnotation1, AnnotationAttribute paramAnnotationAttribute1, SynthesizedAnnotation paramSynthesizedAnnotation2, AnnotationAttribute paramAnnotationAttribute2);
}
