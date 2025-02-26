package io.netty.handler.codec.http.multipart;

import java.io.File;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

final class DeleteFileOnExitHook {
  private static final Set<String> FILES = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
  
  static {
    Runtime.getRuntime().addShutdownHook(new Thread() {
          public void run() {
            DeleteFileOnExitHook.runHook();
          }
        });
  }
  
  public static void remove(String file) {
    FILES.remove(file);
  }
  
  public static void add(String file) {
    FILES.add(file);
  }
  
  public static boolean checkFileExist(String file) {
    return FILES.contains(file);
  }
  
  static void runHook() {
    for (String filename : FILES)
      (new File(filename)).delete(); 
  }
}
