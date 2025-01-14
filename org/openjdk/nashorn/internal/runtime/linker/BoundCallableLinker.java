package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Guards;

final class BoundCallableLinker implements TypeBasedGuardingDynamicLinker {
  public boolean canLinkType(Class<?> type) {
    return (type == BoundCallable.class);
  }
  
  public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
    boolean isCall;
    int firstArgIndex;
    MethodHandle droppingHandle;
    Object objBoundCallable = linkRequest.getReceiver();
    if (!(objBoundCallable instanceof BoundCallable))
      return null; 
    CallSiteDescriptor descriptor = linkRequest.getCallSiteDescriptor();
    Operation operation = NamedOperation.getBaseOperation(descriptor.getOperation());
    if (operation == StandardOperation.NEW) {
      isCall = false;
    } else if (operation == StandardOperation.CALL) {
      isCall = true;
    } else {
      return null;
    } 
    BoundCallable boundCallable = (BoundCallable)objBoundCallable;
    Object callable = boundCallable.getCallable();
    Object boundThis = boundCallable.getBoundThis();
    Object[] args = linkRequest.getArguments();
    Object[] boundArgs = boundCallable.getBoundArgs();
    int argsLen = args.length;
    int boundArgsLen = boundArgs.length;
    Object[] newArgs = new Object[argsLen + boundArgsLen];
    newArgs[0] = callable;
    if (isCall) {
      newArgs[1] = boundThis;
      firstArgIndex = 2;
    } else {
      firstArgIndex = 1;
    } 
    System.arraycopy(boundArgs, 0, newArgs, firstArgIndex, boundArgsLen);
    System.arraycopy(args, firstArgIndex, newArgs, firstArgIndex + boundArgsLen, argsLen - firstArgIndex);
    MethodType type = descriptor.getMethodType();
    MethodType newMethodType = descriptor.getMethodType().changeParameterType(0, callable.getClass());
    if (isCall)
      newMethodType = newMethodType.changeParameterType(1, (boundThis == null) ? Object.class : boundThis.getClass()); 
    for (int i = boundArgs.length; i-- > 0;) {
      newMethodType = newMethodType.insertParameterTypes(firstArgIndex, new Class[] { (boundArgs[i] == null) ? Object.class : boundArgs[i].getClass() });
    } 
    CallSiteDescriptor newDescriptor = descriptor.changeMethodType(newMethodType);
    GuardedInvocation inv = linkerServices.getGuardedInvocation(linkRequest.replaceArguments(newDescriptor, newArgs));
    if (inv == null)
      return null; 
    MethodHandle boundHandle = MethodHandles.insertArguments(inv.getInvocation(), 0, 
        Arrays.copyOf(newArgs, firstArgIndex + boundArgs.length));
    Class<?> p0Type = type.parameterType(0);
    if (isCall) {
      droppingHandle = MethodHandles.dropArguments(boundHandle, 0, new Class[] { p0Type, type.parameterType(1) });
    } else {
      droppingHandle = MethodHandles.dropArguments(boundHandle, 0, new Class[] { p0Type });
    } 
    MethodHandle newGuard = Guards.getIdentityGuard(boundCallable);
    return inv.replaceMethods(droppingHandle, newGuard.asType(newGuard.type().changeParameterType(0, p0Type)));
  }
}
