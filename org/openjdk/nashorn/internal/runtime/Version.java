package org.openjdk.nashorn.internal.runtime;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class Version {
  private static final String VERSION_RB_NAME = "org.openjdk.nashorn.internal.runtime.resources.version";
  
  private static ResourceBundle versionRB;
  
  public static String version() {
    return version("version_short");
  }
  
  public static String fullVersion() {
    return version("version_string");
  }
  
  private static String version(String key) {
    if (versionRB == null)
      try {
        versionRB = ResourceBundle.getBundle("org.openjdk.nashorn.internal.runtime.resources.version");
      } catch (MissingResourceException e) {
        return "version not available";
      }  
    try {
      return versionRB.getString(key);
    } catch (MissingResourceException e) {
      return "version not available";
    } 
  }
}
