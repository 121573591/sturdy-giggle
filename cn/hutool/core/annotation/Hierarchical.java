package cn.hutool.core.annotation;

import java.util.Comparator;

public interface Hierarchical extends Comparable<Hierarchical> {
  public static final Comparator<Hierarchical> DEFAULT_HIERARCHICAL_COMPARATOR = Comparator.comparing(Hierarchical::getVerticalDistance)
    .thenComparing(Hierarchical::getHorizontalDistance);
  
  int getHorizontalDistance();
  
  int getVerticalDistance();
  
  Object getRoot();
  
  default int compareTo(Hierarchical o) {
    return DEFAULT_HIERARCHICAL_COMPARATOR.compare(this, o);
  }
  
  @FunctionalInterface
  public static interface Selector {
    public static final Selector NEAREST_AND_OLDEST_PRIORITY = new NearestAndOldestPrioritySelector();
    
    public static final Selector NEAREST_AND_NEWEST_PRIORITY = new NearestAndNewestPrioritySelector();
    
    public static final Selector FARTHEST_AND_OLDEST_PRIORITY = new FarthestAndOldestPrioritySelector();
    
    public static final Selector FARTHEST_AND_NEWEST_PRIORITY = new FarthestAndNewestPrioritySelector();
    
    <T extends Hierarchical> T choose(T param1T1, T param1T2);
    
    public static class NearestAndOldestPrioritySelector implements Selector {
      public <T extends Hierarchical> T choose(T oldAnnotation, T newAnnotation) {
        return (newAnnotation.getVerticalDistance() < oldAnnotation.getVerticalDistance()) ? newAnnotation : oldAnnotation;
      }
    }
    
    public static class NearestAndNewestPrioritySelector implements Selector {
      public <T extends Hierarchical> T choose(T oldAnnotation, T newAnnotation) {
        return (newAnnotation.getVerticalDistance() <= oldAnnotation.getVerticalDistance()) ? newAnnotation : oldAnnotation;
      }
    }
    
    public static class FarthestAndOldestPrioritySelector implements Selector {
      public <T extends Hierarchical> T choose(T oldAnnotation, T newAnnotation) {
        return (newAnnotation.getVerticalDistance() > oldAnnotation.getVerticalDistance()) ? newAnnotation : oldAnnotation;
      }
    }
    
    public static class FarthestAndNewestPrioritySelector implements Selector {
      public <T extends Hierarchical> T choose(T oldAnnotation, T newAnnotation) {
        return (newAnnotation.getVerticalDistance() >= oldAnnotation.getVerticalDistance()) ? newAnnotation : oldAnnotation;
      }
    }
  }
}
