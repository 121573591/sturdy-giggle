package cn.hutool.core.annotation;

@FunctionalInterface
public interface SynthesizedAnnotationSelector {
  public static final SynthesizedAnnotationSelector NEAREST_AND_OLDEST_PRIORITY = new NearestAndOldestPrioritySelector();
  
  public static final SynthesizedAnnotationSelector NEAREST_AND_NEWEST_PRIORITY = new NearestAndNewestPrioritySelector();
  
  public static final SynthesizedAnnotationSelector FARTHEST_AND_OLDEST_PRIORITY = new FarthestAndOldestPrioritySelector();
  
  public static final SynthesizedAnnotationSelector FARTHEST_AND_NEWEST_PRIORITY = new FarthestAndNewestPrioritySelector();
  
  <T extends SynthesizedAnnotation> T choose(T paramT1, T paramT2);
  
  public static class NearestAndOldestPrioritySelector implements SynthesizedAnnotationSelector {
    public <T extends SynthesizedAnnotation> T choose(T oldAnnotation, T newAnnotation) {
      return (T)Hierarchical.Selector.NEAREST_AND_OLDEST_PRIORITY.<SynthesizedAnnotation>choose((SynthesizedAnnotation)oldAnnotation, (SynthesizedAnnotation)newAnnotation);
    }
  }
  
  public static class NearestAndNewestPrioritySelector implements SynthesizedAnnotationSelector {
    public <T extends SynthesizedAnnotation> T choose(T oldAnnotation, T newAnnotation) {
      return (T)Hierarchical.Selector.NEAREST_AND_NEWEST_PRIORITY.<SynthesizedAnnotation>choose((SynthesizedAnnotation)oldAnnotation, (SynthesizedAnnotation)newAnnotation);
    }
  }
  
  public static class FarthestAndOldestPrioritySelector implements SynthesizedAnnotationSelector {
    public <T extends SynthesizedAnnotation> T choose(T oldAnnotation, T newAnnotation) {
      return (T)Hierarchical.Selector.FARTHEST_AND_OLDEST_PRIORITY.<SynthesizedAnnotation>choose((SynthesizedAnnotation)oldAnnotation, (SynthesizedAnnotation)newAnnotation);
    }
  }
  
  public static class FarthestAndNewestPrioritySelector implements SynthesizedAnnotationSelector {
    public <T extends SynthesizedAnnotation> T choose(T oldAnnotation, T newAnnotation) {
      return (T)Hierarchical.Selector.FARTHEST_AND_NEWEST_PRIORITY.<SynthesizedAnnotation>choose((SynthesizedAnnotation)oldAnnotation, (SynthesizedAnnotation)newAnnotation);
    }
  }
}
