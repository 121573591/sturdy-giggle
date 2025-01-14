package org.openjdk.nashorn.internal.runtime.linker;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;

final class ClassAndLoader {
  static AccessControlContext createPermAccCtxt(String... permNames) {
    Permissions perms = new Permissions();
    for (String permName : permNames)
      perms.add(new RuntimePermission(permName)); 
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
  }
  
  private static final AccessControlContext GET_LOADER_ACC_CTXT = createPermAccCtxt(new String[] { "getClassLoader" });
  
  private final Class<?> representativeClass;
  
  private ClassLoader loader;
  
  private boolean loaderRetrieved;
  
  ClassAndLoader(Class<?> representativeClass, boolean retrieveLoader) {
    this.representativeClass = representativeClass;
    if (retrieveLoader)
      retrieveLoader(); 
  }
  
  Class<?> getRepresentativeClass() {
    return this.representativeClass;
  }
  
  boolean canSee(ClassAndLoader other) {
    try {
      Class<?> otherClass = other.getRepresentativeClass();
      return (Class.forName(otherClass.getName(), false, getLoader()) == otherClass);
    } catch (ClassNotFoundException e) {
      return false;
    } 
  }
  
  ClassLoader getLoader() {
    if (!this.loaderRetrieved)
      retrieveLoader(); 
    return getRetrievedLoader();
  }
  
  ClassLoader getRetrievedLoader() {
    assert this.loaderRetrieved;
    return this.loader;
  }
  
  private void retrieveLoader() {
    this.loader = this.representativeClass.getClassLoader();
    this.loaderRetrieved = true;
  }
  
  public boolean equals(Object obj) {
    return (obj instanceof ClassAndLoader && ((ClassAndLoader)obj).getRetrievedLoader() == getRetrievedLoader());
  }
  
  public int hashCode() {
    return System.identityHashCode(getRetrievedLoader());
  }
  
  static ClassAndLoader getDefiningClassAndLoader(Class<?>[] types) {
    if (types.length == 1)
      return new ClassAndLoader(types[0], false); 
    return AccessController.<ClassAndLoader>doPrivileged(() -> getDefiningClassAndLoaderPrivileged(types), GET_LOADER_ACC_CTXT);
  }
  
  static ClassAndLoader getDefiningClassAndLoaderPrivileged(Class<?>[] types) {
    Collection<ClassAndLoader> maximumVisibilityLoaders = getMaximumVisibilityLoaders(types);
    Iterator<ClassAndLoader> it = maximumVisibilityLoaders.iterator();
    if (maximumVisibilityLoaders.size() == 1)
      return it.next(); 
    assert maximumVisibilityLoaders.size() > 1;
    StringBuilder b = new StringBuilder();
    b.append(((ClassAndLoader)it.next()).getRepresentativeClass().getCanonicalName());
    while (it.hasNext())
      b.append(", ").append(((ClassAndLoader)it.next()).getRepresentativeClass().getCanonicalName()); 
    throw ECMAErrors.typeError("extend.ambiguous.defining.class", new String[] { b.toString() });
  }
  
  private static Collection<ClassAndLoader> getMaximumVisibilityLoaders(Class<?>[] types) {
    List<ClassAndLoader> maximumVisibilityLoaders = new LinkedList<>();
    label18: for (ClassAndLoader maxCandidate : getClassLoadersForTypes(types)) {
      Iterator<ClassAndLoader> it = maximumVisibilityLoaders.iterator();
      while (it.hasNext()) {
        ClassAndLoader existingMax = it.next();
        boolean candidateSeesExisting = maxCandidate.canSee(existingMax);
        boolean exitingSeesCandidate = existingMax.canSee(maxCandidate);
        if (candidateSeesExisting) {
          if (!exitingSeesCandidate)
            it.remove(); 
          continue;
        } 
        if (exitingSeesCandidate)
          continue label18; 
      } 
      maximumVisibilityLoaders.add(maxCandidate);
    } 
    return maximumVisibilityLoaders;
  }
  
  private static Collection<ClassAndLoader> getClassLoadersForTypes(Class<?>[] types) {
    Map<ClassAndLoader, ClassAndLoader> classesAndLoaders = new LinkedHashMap<>();
    for (Class<?> c : types) {
      ClassAndLoader cl = new ClassAndLoader(c, true);
      if (!classesAndLoaders.containsKey(cl))
        classesAndLoaders.put(cl, cl); 
    } 
    return classesAndLoaders.keySet();
  }
}
