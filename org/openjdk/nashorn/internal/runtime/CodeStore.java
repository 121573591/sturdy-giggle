package org.openjdk.nashorn.internal.runtime;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Map;
import org.openjdk.nashorn.internal.codegen.OptimisticTypesPersistence;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.Options;

@Logger(name = "codestore")
public abstract class CodeStore implements Loggable {
  private DebugLogger log;
  
  public DebugLogger initLogger(Context context) {
    this.log = context.getLogger((Class)getClass());
    return this.log;
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public static CodeStore newCodeStore(Context context) {
    try {
      CodeStore store = new DirectoryCodeStore(context);
      store.initLogger(context);
      return store;
    } catch (IOException e) {
      context.getLogger((Class)CodeStore.class).warning(new Object[] { "failed to create cache directory ", e });
      return null;
    } 
  }
  
  public StoredScript store(String functionKey, Source source, String mainClassName, Map<String, byte[]> classBytes, Map<Integer, FunctionInitializer> initializers, Object[] constants, int compilationId) {
    return store(functionKey, source, storedScriptFor(source, mainClassName, classBytes, initializers, constants, compilationId));
  }
  
  public abstract StoredScript store(String paramString, Source paramSource, StoredScript paramStoredScript);
  
  public abstract StoredScript load(Source paramSource, String paramString);
  
  public StoredScript storedScriptFor(Source source, String mainClassName, Map<String, byte[]> classBytes, Map<Integer, FunctionInitializer> initializers, Object[] constants, int compilationId) {
    for (Object constant : constants) {
      if (!(constant instanceof java.io.Serializable)) {
        getLogger().warning(new Object[] { "cannot store ", source, " non serializable constant ", constant });
        return null;
      } 
    } 
    return new StoredScript(compilationId, mainClassName, classBytes, initializers, constants);
  }
  
  public static String getCacheKey(Object functionId, Type[] paramTypes) {
    StringBuilder b = (new StringBuilder()).append(functionId);
    if (paramTypes != null && paramTypes.length > 0) {
      b.append('-');
      for (Type t : paramTypes)
        b.append(Type.getShortSignatureDescriptor(t)); 
    } 
    return b.toString();
  }
  
  public static class DirectoryCodeStore extends CodeStore {
    private static final int DEFAULT_MIN_SIZE = 1000;
    
    private final File dir;
    
    private final boolean readOnly;
    
    private final int minSize;
    
    public DirectoryCodeStore(Context context) throws IOException {
      this(context, Options.getStringProperty("nashorn.persistent.code.cache", "nashorn_code_cache"), false, 1000);
    }
    
    public DirectoryCodeStore(Context context, String path, boolean readOnly, int minSize) throws IOException {
      this.dir = checkDirectory(path, context.getEnv(), readOnly);
      this.readOnly = readOnly;
      this.minSize = minSize;
    }
    
    private static File checkDirectory(String path, ScriptEnvironment env, boolean readOnly) throws IOException {
      try {
        return AccessController.<File>doPrivileged(() -> {
              File dir = (new File(path, getVersionDir(env))).getAbsoluteFile();
              if (readOnly) {
                if (!dir.exists() || !dir.isDirectory())
                  throw new IOException("Not a directory: " + dir.getPath()); 
                if (!dir.canRead())
                  throw new IOException("Directory not readable: " + dir.getPath()); 
              } else {
                if (!dir.exists() && !dir.mkdirs())
                  throw new IOException("Could not create directory: " + dir.getPath()); 
                if (!dir.isDirectory())
                  throw new IOException("Not a directory: " + dir.getPath()); 
                if (!dir.canRead() || !dir.canWrite())
                  throw new IOException("Directory not readable or writable: " + dir.getPath()); 
              } 
              return dir;
            });
      } catch (PrivilegedActionException e) {
        throw (IOException)e.getException();
      } 
    }
    
    private static String getVersionDir(ScriptEnvironment env) throws IOException {
      try {
        String versionDir = OptimisticTypesPersistence.getVersionDirName();
        return env._optimistic_types ? (versionDir + "_opt") : versionDir;
      } catch (Exception e) {
        throw new IOException(e);
      } 
    }
    
    public StoredScript load(Source source, String functionKey) {
      if (belowThreshold(source))
        return null; 
      File file = getCacheFile(source, functionKey);
      try {
        return AccessController.<StoredScript>doPrivileged(() -> {
              if (!file.exists())
                return null; 
              ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
              try {
                StoredScript storedScript = (StoredScript)in.readObject();
                getLogger().info(new Object[] { "loaded ", source, "-", functionKey });
                StoredScript storedScript1 = storedScript;
                in.close();
                return storedScript1;
              } catch (Throwable throwable) {
                try {
                  in.close();
                } catch (Throwable throwable1) {
                  throwable.addSuppressed(throwable1);
                } 
                throw throwable;
              } 
            });
      } catch (PrivilegedActionException e) {
        getLogger().warning(new Object[] { "failed to load ", source, "-", functionKey, ": ", e.getException() });
        return null;
      } 
    }
    
    public StoredScript store(String functionKey, Source source, StoredScript script) {
      if (this.readOnly || script == null || belowThreshold(source))
        return null; 
      File file = getCacheFile(source, functionKey);
      try {
        return AccessController.<StoredScript>doPrivileged(() -> {
              ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
              try {
                out.writeObject(script);
                out.close();
              } catch (Throwable throwable) {
                try {
                  out.close();
                } catch (Throwable throwable1) {
                  throwable.addSuppressed(throwable1);
                } 
                throw throwable;
              } 
              getLogger().info(new Object[] { "stored ", source, "-", functionKey });
              return script;
            });
      } catch (PrivilegedActionException e) {
        getLogger().warning(new Object[] { "failed to store ", script, "-", functionKey, ": ", e.getException() });
        return null;
      } 
    }
    
    private File getCacheFile(Source source, String functionKey) {
      return new File(this.dir, source.getDigest() + "-" + source.getDigest());
    }
    
    private boolean belowThreshold(Source source) {
      if (source.getLength() < this.minSize) {
        getLogger().info(new Object[] { "below size threshold ", source });
        return true;
      } 
      return false;
    }
  }
}
