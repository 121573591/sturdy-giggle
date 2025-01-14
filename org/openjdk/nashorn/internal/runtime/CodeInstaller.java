package org.openjdk.nashorn.internal.runtime;

import java.util.Collection;
import java.util.Map;

public interface CodeInstaller {
  Context getContext();
  
  Class<?> install(String paramString, byte[] paramArrayOfbyte);
  
  void initialize(Collection<Class<?>> paramCollection, Source paramSource, Object[] paramArrayOfObject);
  
  void verify(byte[] paramArrayOfbyte);
  
  long getUniqueScriptId();
  
  void storeScript(String paramString1, Source paramSource, String paramString2, Map<String, byte[]> paramMap, Map<Integer, FunctionInitializer> paramMap1, Object[] paramArrayOfObject, int paramInt);
  
  StoredScript loadScript(Source paramSource, String paramString);
  
  CodeInstaller getOnDemandCompilationInstaller();
  
  CodeInstaller getMultiClassCodeInstaller();
  
  boolean isCompatibleWith(CodeInstaller paramCodeInstaller);
}
