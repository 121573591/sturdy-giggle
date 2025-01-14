package org.openjdk.nashorn.internal.scripts;

final class ModuleGraphManipulator {
  private static final Module MY_MODULE;
  
  private static final String MY_PKG_NAME;
  
  static {
    Class<?> myClass = ModuleGraphManipulator.class;
    MY_MODULE = myClass.getModule();
    String myName = myClass.getName();
    MY_PKG_NAME = myName.substring(0, myName.lastIndexOf('.'));
    Module nashornModule = myClass.getClassLoader().getClass().getModule();
    if (MY_MODULE == nashornModule)
      throw new IllegalStateException("" + myClass + " loaded by wrong loader!"); 
    MY_MODULE.addOpens(MY_PKG_NAME, nashornModule);
  }
  
  private static void addExport(Module otherMod) {
    MY_MODULE.addExports(MY_PKG_NAME, otherMod);
  }
}
