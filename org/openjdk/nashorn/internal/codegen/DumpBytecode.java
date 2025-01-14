package org.openjdk.nashorn.internal.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;

public final class DumpBytecode {
  public static void dumpBytecode(ScriptEnvironment env, DebugLogger logger, byte[] bytecode, String className) {
    File dir = null;
    try {
      if (env._print_code) {
        StringBuilder sb = new StringBuilder();
        sb.append("class: ").append(className)
          .append('\n')
          .append(ClassEmitter.disassemble(bytecode))
          .append("=====");
        if (env._print_code_dir != null) {
          File file;
          String name = className;
          int dollar = name.lastIndexOf('$');
          if (dollar != -1)
            name = name.substring(dollar + 1); 
          dir = new File(env._print_code_dir);
          if (!dir.exists() && !dir.mkdirs())
            throw new IOException(dir.toString()); 
          int uniqueId = 0;
          do {
            String fileName = name + name + ".bytecode";
            file = new File(env._print_code_dir, fileName);
            uniqueId++;
          } while (file.exists());
          PrintWriter pw = new PrintWriter(new FileOutputStream(file));
          try {
            pw.print(sb.toString());
            pw.flush();
            pw.close();
          } catch (Throwable throwable) {
            try {
              pw.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            } 
            throw throwable;
          } 
        } else {
          env.getErr().println(sb);
        } 
      } 
      if (env._dest_dir != null) {
        String fileName = className.replace('.', File.separatorChar) + ".class";
        int index = fileName.lastIndexOf(File.separatorChar);
        if (index != -1) {
          dir = new File(env._dest_dir, fileName.substring(0, index));
        } else {
          dir = new File(env._dest_dir);
        } 
        if (!dir.exists() && !dir.mkdirs())
          throw new IOException(dir.toString()); 
        File file = new File(env._dest_dir, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        try {
          fos.write(bytecode);
          fos.close();
        } catch (Throwable throwable) {
          try {
            fos.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          } 
          throw throwable;
        } 
        logger.info("Wrote class to '" + file.getAbsolutePath() + "'");
      } 
    } catch (IOException e) {
      logger.warning(new Object[] { "Skipping class dump for ", className, ": ", 
            
            ECMAErrors.getMessage("io.error.cant.write", new String[] { String.valueOf(dir) }) });
    } 
  }
}
