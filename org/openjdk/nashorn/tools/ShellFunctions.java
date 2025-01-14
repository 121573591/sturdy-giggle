package org.openjdk.nashorn.tools;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.ScriptingFunctions;

public final class ShellFunctions {
  public static final MethodHandle INPUT = findOwnMH("input", Object.class, new Class[] { Object.class, Object.class, Object.class });
  
  public static final MethodHandle EVALINPUT = findOwnMH("evalinput", Object.class, new Class[] { Object.class, Object.class, Object.class });
  
  public static Object input(Object self, Object endMarker, Object prompt) throws IOException {
    String endMarkerStr = (endMarker != ScriptRuntime.UNDEFINED) ? JSType.toString(endMarker) : "";
    String promptStr = (prompt != ScriptRuntime.UNDEFINED) ? JSType.toString(prompt) : ">> ";
    StringBuilder buf = new StringBuilder();
    while (true) {
      String line = ScriptingFunctions.readLine(promptStr);
      if (line == null || line.equals(endMarkerStr))
        break; 
      buf.append(line);
      buf.append('\n');
    } 
    return buf.toString();
  }
  
  public static Object evalinput(Object self, Object endMarker, Object prompt) throws IOException {
    return Global.eval(self, input(self, endMarker, prompt));
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), ShellFunctions.class, name, Lookup.MH.type(rtype, types));
  }
}
