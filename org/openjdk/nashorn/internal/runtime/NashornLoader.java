package org.openjdk.nashorn.internal.runtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.SecureClassLoader;
import java.util.Arrays;
import java.util.function.Function;

abstract class NashornLoader extends SecureClassLoader {
  protected static final String OBJECTS_PKG = "org.openjdk.nashorn.internal.objects";
  
  protected static final String RUNTIME_PKG = "org.openjdk.nashorn.internal.runtime";
  
  protected static final String RUNTIME_ARRAYS_PKG = "org.openjdk.nashorn.internal.runtime.arrays";
  
  protected static final String RUNTIME_LINKER_PKG = "org.openjdk.nashorn.internal.runtime.linker";
  
  protected static final String SCRIPTS_PKG = "org.openjdk.nashorn.internal.scripts";
  
  static final Module NASHORN_MODULE = Context.class.getModule();
  
  private static final Permission[] SCRIPT_PERMISSIONS;
  
  private static final String MODULE_MANIPULATOR_NAME = "org.openjdk.nashorn.internal.scripts.ModuleGraphManipulator";
  
  private static final byte[] MODULE_MANIPULATOR_BYTES = readModuleManipulatorBytes();
  
  private Method addModuleExport;
  
  static {
    SCRIPT_PERMISSIONS = new Permission[] { new RuntimePermission("accessClassInPackage.org.openjdk.nashorn.internal.runtime"), new RuntimePermission("accessClassInPackage.org.openjdk.nashorn.internal.runtime.linker"), new RuntimePermission("accessClassInPackage.org.openjdk.nashorn.internal.objects"), new RuntimePermission("accessClassInPackage.org.openjdk.nashorn.internal.scripts"), new RuntimePermission("accessClassInPackage.org.openjdk.nashorn.internal.runtime.arrays") };
  }
  
  NashornLoader(ClassLoader parent) {
    super(parent);
  }
  
  void loadModuleManipulator() {
    Class<?> clazz = defineClass("org.openjdk.nashorn.internal.scripts.ModuleGraphManipulator", MODULE_MANIPULATOR_BYTES, 0, MODULE_MANIPULATOR_BYTES.length);
    try {
      Class.forName("org.openjdk.nashorn.internal.scripts.ModuleGraphManipulator", true, this);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } 
    PrivilegedAction<Void> pa = () -> {
        try {
          this.addModuleExport = clazz.getDeclaredMethod("addExport", new Class[] { Module.class });
          this.addModuleExport.setAccessible(true);
        } catch (NoSuchMethodException|SecurityException ex) {
          throw new RuntimeException(ex);
        } 
        return null;
      };
    AccessController.doPrivileged(pa);
  }
  
  final void addModuleExport(Module to) {
    try {
      this.addModuleExport.invoke(null, new Object[] { to });
    } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException ex) {
      throw new RuntimeException(ex);
    } 
  }
  
  static boolean isInNamedModule() {
    return NASHORN_MODULE.isNamed();
  }
  
  protected static void checkPackageAccess(String name) {
    int i = name.lastIndexOf('.');
    if (i != -1) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        String pkgName = name.substring(0, i);
        switch (pkgName) {
          case "org.openjdk.nashorn.internal.runtime":
          case "org.openjdk.nashorn.internal.runtime.arrays":
          case "org.openjdk.nashorn.internal.runtime.linker":
          case "org.openjdk.nashorn.internal.objects":
          case "org.openjdk.nashorn.internal.scripts":
            return;
        } 
        sm.checkPackageAccess(pkgName);
      } 
    } 
  }
  
  protected PermissionCollection getPermissions(CodeSource codesource) {
    Permissions permCollection = new Permissions();
    for (Permission perm : SCRIPT_PERMISSIONS)
      permCollection.add(perm); 
    return permCollection;
  }
  
  static ClassLoader createClassLoader(String classPath, ClassLoader parent) {
    URL[] urls = pathToURLs(classPath);
    return URLClassLoader.newInstance(urls, parent);
  }
  
  private static URL[] pathToURLs(String path) {
    String[] components = path.split(File.pathSeparator);
    return (URL[])Arrays.<String>stream(components)
      .map(File::new)
      .map(NashornLoader::fileToURL)
      .toArray(x$0 -> new URL[x$0]);
  }
  
  private static URL fileToURL(File file) {
    try {
      name = file.getCanonicalPath();
    } catch (IOException e) {
      name = file.getAbsolutePath();
    } 
    String name = name.replace(File.separatorChar, '/');
    if (!name.startsWith("/"))
      name = "/" + name; 
    if (!file.isFile())
      name = name + "/"; 
    try {
      return new URL("file", "", name);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("file");
    } 
  }
  
  private static byte[] readModuleManipulatorBytes() {
    PrivilegedAction<byte[]> pa = () -> {
        String res = "/" + "org.openjdk.nashorn.internal.scripts.ModuleGraphManipulator".replace('.', '/') + ".class";
        try {
          InputStream in = NashornLoader.class.getResourceAsStream(res);
          try {
            byte[] arrayOfByte = in.readAllBytes();
            if (in != null)
              in.close(); 
            return arrayOfByte;
          } catch (Throwable throwable) {
            if (in != null)
              try {
                in.close();
              } catch (Throwable throwable1) {
                throwable.addSuppressed(throwable1);
              }  
            throw throwable;
          } 
        } catch (IOException exp) {
          throw new UncheckedIOException(exp);
        } 
      };
    return AccessController.<byte[]>doPrivileged((PrivilegedAction)pa);
  }
}
