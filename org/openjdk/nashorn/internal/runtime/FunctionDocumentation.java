package org.openjdk.nashorn.internal.runtime;

import java.util.Locale;
import java.util.ResourceBundle;

final class FunctionDocumentation {
  private static final String DOCS_RESOURCE = "org.openjdk.nashorn.internal.runtime.resources.Functions";
  
  private static final ResourceBundle FUNC_DOCS = ResourceBundle.getBundle("org.openjdk.nashorn.internal.runtime.resources.Functions", Locale.getDefault());
  
  static String getDoc(String docKey) {
    try {
      return FUNC_DOCS.getString(docKey);
    } catch (RuntimeException ignored) {
      return null;
    } 
  }
}
