package org.tritonus.share;

import java.security.AccessControlException;

public class TSettings {
  public static boolean SHOW_ACCESS_CONTROL_EXCEPTIONS = false;
  
  private static final String PROPERTY_PREFIX = "tritonus.";
  
  public static boolean AlsaUsePlughw = getBooleanProperty("AlsaUsePlughw");
  
  private static boolean getBooleanProperty(String strName) {
    String strPropertyName = "tritonus." + strName;
    String strValue = "false";
    try {
      strValue = System.getProperty(strPropertyName, "false");
    } catch (AccessControlException e) {
      if (SHOW_ACCESS_CONTROL_EXCEPTIONS)
        TDebug.out(e); 
    } 
    boolean bValue = strValue.toLowerCase().equals("true");
    return bValue;
  }
}
