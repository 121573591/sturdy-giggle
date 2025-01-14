package cn.hutool.core.annotation;

import cn.hutool.core.comparator.CompareUtil;
import java.util.Comparator;

public interface SynthesizedAnnotationPostProcessor extends Comparable<SynthesizedAnnotationPostProcessor> {
  public static final AliasAnnotationPostProcessor ALIAS_ANNOTATION_POST_PROCESSOR = new AliasAnnotationPostProcessor();
  
  public static final MirrorLinkAnnotationPostProcessor MIRROR_LINK_ANNOTATION_POST_PROCESSOR = new MirrorLinkAnnotationPostProcessor();
  
  public static final AliasLinkAnnotationPostProcessor ALIAS_LINK_ANNOTATION_POST_PROCESSOR = new AliasLinkAnnotationPostProcessor();
  
  default int order() {
    return Integer.MAX_VALUE;
  }
  
  default int compareTo(SynthesizedAnnotationPostProcessor o) {
    return CompareUtil.compare(this, o, Comparator.comparing(SynthesizedAnnotationPostProcessor::order));
  }
  
  void process(SynthesizedAnnotation paramSynthesizedAnnotation, AnnotationSynthesizer paramAnnotationSynthesizer);
}
