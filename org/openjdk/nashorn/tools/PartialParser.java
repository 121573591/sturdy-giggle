package org.openjdk.nashorn.tools;

import org.openjdk.nashorn.internal.runtime.Context;

public interface PartialParser {
  int getLastExpressionStart(Context paramContext, String paramString);
}
