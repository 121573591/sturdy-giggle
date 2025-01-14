package org.openjdk.nashorn.internal.runtime.logging;

import org.openjdk.nashorn.internal.runtime.Context;

public interface Loggable {
  DebugLogger initLogger(Context paramContext);
  
  DebugLogger getLogger();
}
