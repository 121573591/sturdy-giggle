package org.openjdk.nashorn.internal.runtime;

import java.lang.module.ModuleDescriptor;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Set;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.ObjectClassGenerator;

final class StructureLoader extends NashornLoader {
  private static final String SINGLE_FIELD_PREFIX = Compiler.binaryName("org/openjdk/nashorn/internal/scripts") + "." + Compiler.binaryName("org/openjdk/nashorn/internal/scripts");
  
  private static final String DUAL_FIELD_PREFIX = Compiler.binaryName("org/openjdk/nashorn/internal/scripts") + "." + Compiler.binaryName("org/openjdk/nashorn/internal/scripts");
  
  private final Module structuresModule;
  
  StructureLoader(ClassLoader parent) {
    super(parent);
    if (isInNamedModule()) {
      this.structuresModule = createModule();
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.scripts", this.structuresModule);
      NASHORN_MODULE.addExports("org.openjdk.nashorn.internal.runtime", this.structuresModule);
      NASHORN_MODULE.addReads(this.structuresModule);
    } else {
      this.structuresModule = null;
    } 
  }
  
  private Module createModule() {
    ModuleDescriptor descriptor = ModuleDescriptor.newModule("org.openjdk.nashorn.structures", Set.of(ModuleDescriptor.Modifier.SYNTHETIC)).requires(NASHORN_MODULE.getName()).packages(Set.of("org.openjdk.nashorn.internal.scripts")).build();
    Module mod = Context.createModuleTrusted(NASHORN_MODULE.getLayer(), descriptor, this);
    loadModuleManipulator();
    return mod;
  }
  
  private static boolean isDualFieldStructure(String name) {
    return name.startsWith(DUAL_FIELD_PREFIX);
  }
  
  static boolean isSingleFieldStructure(String name) {
    return name.startsWith(SINGLE_FIELD_PREFIX);
  }
  
  static boolean isStructureClass(String name) {
    return (isDualFieldStructure(name) || isSingleFieldStructure(name));
  }
  
  Module getModule() {
    return this.structuresModule;
  }
  
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    if (isDualFieldStructure(name))
      return generateClass(name, name.substring(DUAL_FIELD_PREFIX.length()), true); 
    if (isSingleFieldStructure(name))
      return generateClass(name, name.substring(SINGLE_FIELD_PREFIX.length()), false); 
    return super.findClass(name);
  }
  
  private Class<?> generateClass(String name, String descriptor, boolean dualFields) {
    Context context = Context.getContextTrusted();
    byte[] code = (new ObjectClassGenerator(context, dualFields)).generate(descriptor);
    return defineClass(name, code, 0, code.length, new ProtectionDomain(null, getPermissions((CodeSource)null)));
  }
}
