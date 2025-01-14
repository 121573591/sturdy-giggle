package org.openjdk.nashorn.internal.runtime;

import java.lang.module.ModuleDescriptor;
import java.security.CodeSource;
import java.util.Objects;
import java.util.Set;

final class ScriptLoader extends NashornLoader {
  private static final String NASHORN_PKG_PREFIX = "org.openjdk.nashorn.internal.";
  
  private volatile boolean structureAccessAdded;
  
  private final Context context;
  
  private final Module scriptModule;
  
  Context getContext() {
    return this.context;
  }
  
  ScriptLoader(Context context) {
    super(context.getStructLoader());
    this.context = context;
    if (isInNamedModule()) {
      this.scriptModule = createModule();
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.objects", this.scriptModule);
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.runtime", this.scriptModule);
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.runtime.arrays", this.scriptModule);
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.runtime.linker", this.scriptModule);
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.scripts", this.scriptModule);
      NASHORN_MODULE.addReads(this.scriptModule);
    } else {
      this.scriptModule = null;
    } 
  }
  
  private Module createModule() {
    Module structMod = this.context.getStructLoader().getModule();
    ModuleDescriptor.Builder builder = ModuleDescriptor.newModule("org.openjdk.nashorn.scripts", Set.of(ModuleDescriptor.Modifier.SYNTHETIC)).requires("java.logging").requires(NASHORN_MODULE.getName()).requires(structMod.getName()).packages(Set.of("org.openjdk.nashorn.internal.scripts"));
    if (Context.javaSqlFound)
      builder.requires("java.sql"); 
    if (Context.javaSqlRowsetFound)
      builder.requires("java.sql.rowset"); 
    ModuleDescriptor descriptor = builder.build();
    Module mod = Context.createModuleTrusted(structMod.getLayer(), descriptor, this);
    loadModuleManipulator();
    return mod;
  }
  
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    checkPackageAccess(name);
    Class<?> cl = super.loadClass(name, resolve);
    if (!this.structureAccessAdded && isInNamedModule()) {
      StructureLoader structLoader = this.context.getStructLoader();
      if (cl.getClassLoader() == structLoader) {
        this.structureAccessAdded = true;
        structLoader.addModuleExport(this.scriptModule);
      } 
    } 
    return cl;
  }
  
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    ClassLoader appLoader = this.context.getAppLoader();
    if (appLoader == null || name.startsWith("org.openjdk.nashorn.internal."))
      throw new ClassNotFoundException(name); 
    return appLoader.loadClass(name);
  }
  
  synchronized Class<?> installClass(String name, byte[] data, CodeSource cs) {
    return defineClass(name, data, 0, data.length, Objects.<CodeSource>requireNonNull(cs));
  }
}
