package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import java.util.function.BinaryOperator;

public class AliasLinkAnnotationPostProcessor extends AbstractLinkAnnotationPostProcessor {
  private static final RelationType[] PROCESSED_RELATION_TYPES = new RelationType[] { RelationType.ALIAS_FOR, RelationType.FORCE_ALIAS_FOR };
  
  public int order() {
    return -2147483646;
  }
  
  protected RelationType[] processTypes() {
    return PROCESSED_RELATION_TYPES;
  }
  
  protected void processLinkedAttribute(AnnotationSynthesizer synthesizer, Link annotation, SynthesizedAnnotation originalAnnotation, AnnotationAttribute originalAttribute, SynthesizedAnnotation linkedAnnotation, AnnotationAttribute linkedAttribute) {
    checkAliasRelation(annotation, originalAttribute, linkedAttribute);
    if (RelationType.ALIAS_FOR.equals(annotation.type())) {
      wrappingLinkedAttribute(synthesizer, originalAttribute, linkedAttribute, AliasedAnnotationAttribute::new);
      return;
    } 
    wrappingLinkedAttribute(synthesizer, originalAttribute, linkedAttribute, ForceAliasedAnnotationAttribute::new);
  }
  
  private void wrappingLinkedAttribute(AnnotationSynthesizer synthesizer, AnnotationAttribute originalAttribute, AnnotationAttribute aliasAttribute, BinaryOperator<AnnotationAttribute> wrapping) {
    if (!aliasAttribute.isWrapped()) {
      processAttribute(synthesizer, originalAttribute, aliasAttribute, wrapping);
      return;
    } 
    AbstractWrappedAnnotationAttribute wrapper = (AbstractWrappedAnnotationAttribute)aliasAttribute;
    wrapper.getAllLinkedNonWrappedAttributes().forEach(t -> processAttribute(synthesizer, originalAttribute, t, wrapping));
  }
  
  private void processAttribute(AnnotationSynthesizer synthesizer, AnnotationAttribute originalAttribute, AnnotationAttribute target, BinaryOperator<AnnotationAttribute> wrapping) {
    Opt.ofNullable(target.getAnnotationType())
      .map(synthesizer::getSynthesizedAnnotation)
      .ifPresent(t -> t.replaceAttribute(target.getAttributeName(), ()));
  }
  
  private void checkAliasRelation(Link annotation, AnnotationAttribute originalAttribute, AnnotationAttribute linkedAttribute) {
    checkLinkedAttributeNotNull(originalAttribute, linkedAttribute, annotation);
    checkAttributeType(originalAttribute, linkedAttribute);
    checkCircularDependency(originalAttribute, linkedAttribute);
  }
  
  private void checkCircularDependency(AnnotationAttribute original, AnnotationAttribute alias) {
    checkLinkedSelf(original, alias);
    Link annotation = getLinkAnnotation(alias, new RelationType[] { RelationType.ALIAS_FOR, RelationType.FORCE_ALIAS_FOR });
    if (ObjectUtil.isNull(annotation))
      return; 
    Class<?> aliasAnnotationType = getLinkedAnnotationType(annotation, alias.getAnnotationType());
    if (ObjectUtil.notEqual(aliasAnnotationType, original.getAnnotationType()))
      return; 
    Assert.notEquals(annotation
        .attribute(), original.getAttributeName(), "circular reference between the alias attribute [{}] and the original attribute [{}]", new Object[] { alias
          
          .getAttribute(), original.getAttribute() });
  }
}
