package org.openjdk.nashorn.internal.runtime;

import java.security.AccessControlContext;
import java.security.Permission;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.util.function.Function;
import java.util.stream.Stream;

public final class AccessControlContextFactory {
  public static AccessControlContext createAccessControlContext() {
    return createAccessControlContext(new Permission[0]);
  }
  
  public static AccessControlContext createAccessControlContext(Permission... permissions) {
    Permissions perms = new Permissions();
    for (Permission permission : permissions)
      perms.add(permission); 
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
  }
  
  public static AccessControlContext createAccessControlContext(String... runtimePermissionNames) {
    return createAccessControlContext(makeRuntimePermissions(runtimePermissionNames));
  }
  
  private static Permission[] makeRuntimePermissions(String... runtimePermissionNames) {
    return (Permission[])Stream.<String>of(runtimePermissionNames).map(RuntimePermission::new).toArray(x$0 -> new Permission[x$0]);
  }
}
