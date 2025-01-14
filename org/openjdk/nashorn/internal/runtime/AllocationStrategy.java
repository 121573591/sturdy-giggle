package org.openjdk.nashorn.internal.runtime;

import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.ref.WeakReference;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.openjdk.nashorn.internal.lookup.Lookup;

public final class AllocationStrategy implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private final int fieldCount;
  
  private final boolean dualFields;
  
  private transient String allocatorClassName;
  
  private transient MethodHandle allocator;
  
  private transient AllocatorMap lastMap;
  
  public AllocationStrategy(int fieldCount, boolean dualFields) {
    this.fieldCount = fieldCount;
    this.dualFields = dualFields;
  }
  
  private String getAllocatorClassName() {
    if (this.allocatorClassName == null)
      this.allocatorClassName = Compiler.binaryName(ObjectClassGenerator.getClassName(this.fieldCount, this.dualFields)).intern(); 
    return this.allocatorClassName;
  }
  
  synchronized PropertyMap getAllocatorMap(ScriptObject prototype) {
    assert prototype != null;
    PropertyMap protoMap = prototype.getMap();
    if (this.lastMap != null) {
      if (!this.lastMap.hasSharedProtoMap()) {
        if (this.lastMap.hasSamePrototype(prototype))
          return this.lastMap.allocatorMap; 
        if (this.lastMap.hasSameProtoMap(protoMap) && this.lastMap.hasUnchangedProtoMap()) {
          PropertyMap propertyMap = PropertyMap.newMap(null, getAllocatorClassName(), 0, this.fieldCount, 0);
          SharedPropertyMap sharedProtoMap = new SharedPropertyMap(protoMap);
          propertyMap.setSharedProtoMap(sharedProtoMap);
          prototype.setMap(sharedProtoMap);
          this.lastMap = new AllocatorMap(prototype, protoMap, propertyMap);
          return propertyMap;
        } 
      } 
      if (this.lastMap.hasValidSharedProtoMap() && this.lastMap.hasSameProtoMap(protoMap)) {
        prototype.setMap(this.lastMap.getSharedProtoMap());
        return this.lastMap.allocatorMap;
      } 
    } 
    PropertyMap allocatorMap = PropertyMap.newMap(null, getAllocatorClassName(), 0, this.fieldCount, 0);
    this.lastMap = new AllocatorMap(prototype, protoMap, allocatorMap);
    return allocatorMap;
  }
  
  ScriptObject allocate(PropertyMap map) {
    try {
      if (this.allocator == null)
        this.allocator = Lookup.MH.findStatic(LOOKUP, Context.forStructureClass(getAllocatorClassName()), CompilerConstants.ALLOCATE
            .symbolName(), Lookup.MH.type(ScriptObject.class, new Class[] { PropertyMap.class })); 
      return this.allocator.invokeExact(map);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public String toString() {
    return "AllocationStrategy[fieldCount=" + this.fieldCount + "]";
  }
  
  static class AllocatorMap {
    private final WeakReference<ScriptObject> prototype;
    
    private final WeakReference<PropertyMap> prototypeMap;
    
    private final PropertyMap allocatorMap;
    
    AllocatorMap(ScriptObject prototype, PropertyMap protoMap, PropertyMap allocMap) {
      this.prototype = new WeakReference<>(prototype);
      this.prototypeMap = new WeakReference<>(protoMap);
      this.allocatorMap = allocMap;
    }
    
    boolean hasSamePrototype(ScriptObject proto) {
      return (this.prototype.get() == proto);
    }
    
    boolean hasSameProtoMap(PropertyMap protoMap) {
      return (this.prototypeMap.get() == protoMap || this.allocatorMap.getSharedProtoMap() == protoMap);
    }
    
    boolean hasUnchangedProtoMap() {
      ScriptObject proto = this.prototype.get();
      return (proto != null && proto.getMap() == this.prototypeMap.get());
    }
    
    boolean hasSharedProtoMap() {
      return (getSharedProtoMap() != null);
    }
    
    boolean hasValidSharedProtoMap() {
      return (hasSharedProtoMap() && getSharedProtoMap().isValidSharedProtoMap());
    }
    
    PropertyMap getSharedProtoMap() {
      return this.allocatorMap.getSharedProtoMap();
    }
  }
}
