package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;

public class MirrorLinkAnnotationPostProcessor extends AbstractLinkAnnotationPostProcessor {
  private static final RelationType[] PROCESSED_RELATION_TYPES = new RelationType[] { RelationType.MIRROR_FOR };
  
  public int order() {
    return -2147483647;
  }
  
  protected RelationType[] processTypes() {
    return PROCESSED_RELATION_TYPES;
  }
  
  protected void processLinkedAttribute(AnnotationSynthesizer synthesizer, Link annotation, SynthesizedAnnotation originalAnnotation, AnnotationAttribute originalAttribute, SynthesizedAnnotation linkedAnnotation, AnnotationAttribute linkedAttribute) {
    if (originalAttribute instanceof MirroredAnnotationAttribute || linkedAttribute instanceof MirroredAnnotationAttribute) {
      checkMirrored(originalAttribute, linkedAttribute);
      return;
    } 
    checkMirrorRelation(annotation, originalAttribute, linkedAttribute);
    AnnotationAttribute mirroredOriginalAttribute = new MirroredAnnotationAttribute(originalAttribute, linkedAttribute);
    originalAnnotation.setAttribute(originalAttribute.getAttributeName(), mirroredOriginalAttribute);
    AnnotationAttribute mirroredTargetAttribute = new MirroredAnnotationAttribute(linkedAttribute, originalAttribute);
    linkedAnnotation.setAttribute(annotation.attribute(), mirroredTargetAttribute);
  }
  
  private void checkMirrored(AnnotationAttribute original, AnnotationAttribute mirror) {
    String errorMsg;
    boolean originalAttributeMirrored = original instanceof MirroredAnnotationAttribute;
    boolean mirrorAttributeMirrored = mirror instanceof MirroredAnnotationAttribute;
    boolean passed = (originalAttributeMirrored && mirrorAttributeMirrored && ObjectUtil.equals(((MirroredAnnotationAttribute)original).getLinked(), ((MirroredAnnotationAttribute)mirror).getOriginal()));
    if (passed)
      return; 
    if (originalAttributeMirrored && !mirrorAttributeMirrored) {
      errorMsg = CharSequenceUtil.format("attribute [{}] cannot mirror for [{}], because it's already mirrored for [{}]", new Object[] { original
            
            .getAttribute(), mirror.getAttribute(), ((MirroredAnnotationAttribute)original).getLinked() });
    } else if (!originalAttributeMirrored && mirrorAttributeMirrored) {
      errorMsg = CharSequenceUtil.format("attribute [{}] cannot mirror for [{}], because it's already mirrored for [{}]", new Object[] { mirror
            
            .getAttribute(), original.getAttribute(), ((MirroredAnnotationAttribute)mirror).getLinked() });
    } else {
      errorMsg = CharSequenceUtil.format("attribute [{}] cannot mirror for [{}], because [{}] already mirrored for [{}] and  [{}] already mirrored for [{}]", new Object[] { mirror
            
            .getAttribute(), original.getAttribute(), mirror
            .getAttribute(), ((MirroredAnnotationAttribute)mirror).getLinked(), original
            .getAttribute(), ((MirroredAnnotationAttribute)original).getLinked() });
    } 
    throw new IllegalArgumentException(errorMsg);
  }
  
  private void checkMirrorRelation(Link annotation, AnnotationAttribute original, AnnotationAttribute mirror) {
    checkLinkedAttributeNotNull(original, mirror, annotation);
    checkAttributeType(original, mirror);
    Link mirrorAttributeAnnotation = getLinkAnnotation(mirror, new RelationType[] { RelationType.MIRROR_FOR });
    Assert.isTrue((
        ObjectUtil.isNotNull(mirrorAttributeAnnotation) && RelationType.MIRROR_FOR.equals(mirrorAttributeAnnotation.type())), "mirror attribute [{}] of original attribute [{}] must marked by @Link, and also @LinkType.type() must is [{}]", new Object[] { mirror
          
          .getAttribute(), original.getAttribute(), RelationType.MIRROR_FOR });
    checkLinkedSelf(original, mirror);
  }
}
