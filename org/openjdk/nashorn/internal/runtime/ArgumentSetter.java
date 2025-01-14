package org.openjdk.nashorn.internal.runtime;

import org.openjdk.nashorn.internal.codegen.CompilerConstants;

public final class ArgumentSetter {
  public static final CompilerConstants.Call SET_ARGUMENT = CompilerConstants.staticCallNoLookup(ArgumentSetter.class, "setArgument", void.class, new Class[] { Object.class, ScriptObject.class, int.class });
  
  public static final CompilerConstants.Call SET_ARRAY_ELEMENT = CompilerConstants.staticCallNoLookup(ArgumentSetter.class, "setArrayElement", void.class, new Class[] { Object.class, Object[].class, int.class });
  
  public static void setArgument(Object value, ScriptObject arguments, int key) {
    arguments.setArgument(key, value);
  }
  
  public static void setArrayElement(Object value, Object[] arguments, int key) {
    arguments[key] = value;
  }
}
