package org.openjdk.nashorn.internal.runtime.linker;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import jdk.dynalink.beans.StaticClass;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.DumpBytecode;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

final class JavaAdapterClassLoader {
  private static final Module NASHORN_MODULE = Context.class.getModule();
  
  private static final AccessControlContext CREATE_LOADER_ACC_CTXT = ClassAndLoader.createPermAccCtxt(new String[] { "createClassLoader" });
  
  private static final AccessControlContext GET_CONTEXT_ACC_CTXT = ClassAndLoader.createPermAccCtxt(new String[] { "nashorn.getContext" });
  
  private static final Collection<String> VISIBLE_INTERNAL_CLASS_NAMES = Collections.unmodifiableCollection(new HashSet<>(
        Arrays.asList(new String[] { JavaAdapterServices.class.getName(), ScriptObject.class.getName(), ScriptFunction.class.getName(), JSType.class.getName() })));
  
  private final String className;
  
  private final byte[] classBytes;
  
  JavaAdapterClassLoader(String className, byte[] classBytes) {
    this.className = className.replace('/', '.');
    this.classBytes = classBytes;
  }
  
  StaticClass generateClass(ClassLoader parentLoader, ProtectionDomain protectionDomain) {
    assert protectionDomain != null;
    return AccessController.<StaticClass>doPrivileged(() -> {
          try {
            return StaticClass.forClass(Class.forName(this.className, true, createClassLoader(parentLoader, protectionDomain)));
          } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
          } 
        }CREATE_LOADER_ACC_CTXT);
  }
  
  private ClassLoader createClassLoader(ClassLoader parentLoader, final ProtectionDomain protectionDomain) {
    return new SecureClassLoader(parentLoader) {
        private final ClassLoader myLoader;
        
        private final Module adapterModule;
        
        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
          try {
            int i = name.lastIndexOf('.');
            if (i != -1) {
              String pkgName = name.substring(0, i);
              Context.checkPackageAccess(pkgName);
            } 
            return super.loadClass(name, resolve);
          } catch (SecurityException se) {
            if (JavaAdapterClassLoader.VISIBLE_INTERNAL_CLASS_NAMES.contains(name))
              return (this.myLoader != null) ? this.myLoader.loadClass(name) : Class.forName(name, false, (ClassLoader)null); 
            throw se;
          } 
        }
        
        protected Class<?> findClass(String name) throws ClassNotFoundException {
          if (name.equals(JavaAdapterClassLoader.this.className)) {
            assert JavaAdapterClassLoader.this.classBytes != null : "what? already cleared .class bytes!!";
            Context ctx = AccessController.<Context>doPrivileged(Context::getContext, JavaAdapterClassLoader.GET_CONTEXT_ACC_CTXT);
            DumpBytecode.dumpBytecode(ctx.getEnv(), ctx.getLogger(Compiler.class), JavaAdapterClassLoader.this.classBytes, name);
            return defineClass(name, JavaAdapterClassLoader.this.classBytes, 0, JavaAdapterClassLoader.this.classBytes.length, protectionDomain);
          } 
          throw new ClassNotFoundException(name);
        }
      };
  }
}
