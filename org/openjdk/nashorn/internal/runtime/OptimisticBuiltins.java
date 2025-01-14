package org.openjdk.nashorn.internal.runtime;

import org.openjdk.nashorn.internal.objects.annotations.SpecializedFunction;

public interface OptimisticBuiltins {
  SpecializedFunction.LinkLogic getLinkLogic(Class<? extends SpecializedFunction.LinkLogic> paramClass);
  
  boolean hasPerInstanceAssumptions();
}
