package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.api.scripting.AbstractJSObject;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.linker.NashornGuards;

public final class WithObject extends Scope {
  private static final MethodHandle WITHEXPRESSIONGUARD = findOwnMH("withExpressionGuard", boolean.class, new Class[] { Object.class, PropertyMap.class, SwitchPoint[].class });
  
  private static final MethodHandle WITHEXPRESSIONFILTER = findOwnMH("withFilterExpression", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle WITHSCOPEFILTER = findOwnMH("withFilterScope", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle BIND_TO_EXPRESSION_OBJ = findOwnMH("bindToExpression", Object.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle BIND_TO_EXPRESSION_FN = findOwnMH("bindToExpression", Object.class, new Class[] { ScriptFunction.class, Object.class });
  
  private final ScriptObject expression;
  
  WithObject(ScriptObject scope, ScriptObject expression) {
    super(scope, (PropertyMap)null);
    this.expression = expression;
    setIsInternal();
  }
  
  public boolean delete(Object key, boolean strict) {
    ScriptObject self = this.expression;
    String propName = JSType.toString(key);
    FindProperty find = self.findProperty(propName, true);
    if (find != null)
      return self.delete(propName, strict); 
    return false;
  }
  
  public GuardedInvocation lookup(CallSiteDescriptor desc, LinkRequest request) {
    String fallBack;
    if (request.isCallSiteUnstable())
      return super.lookup(desc, request); 
    GuardedInvocation link = null;
    Operation op = desc.getOperation();
    assert op instanceof NamedOperation;
    String name = ((NamedOperation)op).getName().toString();
    FindProperty find = this.expression.findProperty(name, true);
    if (find != null) {
      link = this.expression.lookup(desc, request);
      if (link != null)
        return fixExpressionCallSite(desc, link); 
    } 
    ScriptObject scope = getProto();
    find = scope.findProperty(name, true);
    if (find != null)
      return fixScopeCallSite(scope.lookup(desc, request), name, find.getOwner()); 
    Operation firstOp = NashornCallSiteDescriptor.getBaseOperation(desc);
    if (firstOp == StandardOperation.GET) {
      if (NashornCallSiteDescriptor.isMethodFirstOperation(desc)) {
        fallBack = "__noSuchMethod__";
      } else {
        fallBack = "__noSuchProperty__";
      } 
    } else {
      fallBack = null;
    } 
    if (fallBack != null) {
      find = this.expression.findProperty(fallBack, true);
      if (find != null)
        if ("__noSuchMethod__".equals(fallBack)) {
          link = this.expression.noSuchMethod(desc, request).addSwitchPoint(getProtoSwitchPoint(name));
        } else if ("__noSuchProperty__".equals(fallBack)) {
          link = this.expression.noSuchProperty(desc, request).addSwitchPoint(getProtoSwitchPoint(name));
        }  
    } 
    if (link != null)
      return fixExpressionCallSite(desc, link); 
    link = scope.lookup(desc, request);
    if (link != null)
      return fixScopeCallSite(link, name, (ScriptObject)null); 
    return null;
  }
  
  protected FindProperty findProperty(Object key, boolean deep, boolean isScope, ScriptObject start) {
    FindProperty exprProperty = this.expression.findProperty(key, true, false, this.expression);
    if (exprProperty != null)
      return exprProperty; 
    return super.findProperty(key, deep, isScope, start);
  }
  
  protected Object invokeNoSuchProperty(Object key, boolean isScope, int programPoint) {
    FindProperty find = this.expression.findProperty("__noSuchProperty__", true);
    if (find != null) {
      Object func = find.getObjectValue();
      if (func instanceof ScriptFunction) {
        ScriptFunction sfunc = (ScriptFunction)func;
        Object self = (isScope && sfunc.isStrict()) ? ScriptRuntime.UNDEFINED : this.expression;
        return ScriptRuntime.apply(sfunc, self, new Object[] { key });
      } 
    } 
    return getProto().invokeNoSuchProperty(key, isScope, programPoint);
  }
  
  public void setSplitState(int state) {
    ((Scope)getNonWithParent()).setSplitState(state);
  }
  
  public int getSplitState() {
    return ((Scope)getNonWithParent()).getSplitState();
  }
  
  public void addBoundProperties(ScriptObject source, Property[] properties) {
    getNonWithParent().addBoundProperties(source, properties);
  }
  
  private ScriptObject getNonWithParent() {
    ScriptObject proto = getProto();
    while (proto != null && proto instanceof WithObject)
      proto = proto.getProto(); 
    return proto;
  }
  
  private static GuardedInvocation fixReceiverType(GuardedInvocation link, MethodHandle filter) {
    MethodType invType = link.getInvocation().type();
    MethodType newInvType = invType.changeParameterType(0, filter.type().returnType());
    return link.asType(newInvType);
  }
  
  private static GuardedInvocation fixExpressionCallSite(CallSiteDescriptor desc, GuardedInvocation link) {
    if (NashornCallSiteDescriptor.getBaseOperation(desc) != StandardOperation.GET || !NashornCallSiteDescriptor.isMethodFirstOperation(desc))
      return fixReceiverType(link, WITHEXPRESSIONFILTER).filterArguments(0, new MethodHandle[] { WITHEXPRESSIONFILTER }); 
    MethodHandle linkInvocation = link.getInvocation();
    MethodType linkType = linkInvocation.type();
    boolean linkReturnsFunction = ScriptFunction.class.isAssignableFrom(linkType.returnType());
    return link.replaceMethods(Lookup.MH
        
        .foldArguments(
          linkReturnsFunction ? 
          BIND_TO_EXPRESSION_FN : 
          BIND_TO_EXPRESSION_OBJ, 
          filterReceiver(linkInvocation
            .asType(linkType
              .changeReturnType(
                linkReturnsFunction ? 
                ScriptFunction.class : 
                Object.class)
              .changeParameterType(0, Object.class)), WITHEXPRESSIONFILTER)), 
        
        filterGuardReceiver(link, WITHEXPRESSIONFILTER));
  }
  
  private GuardedInvocation fixScopeCallSite(GuardedInvocation link, String name, ScriptObject owner) {
    GuardedInvocation newLink = fixReceiverType(link, WITHSCOPEFILTER);
    MethodHandle expressionGuard = expressionGuard(name, owner);
    MethodHandle filteredGuard = filterGuardReceiver(newLink, WITHSCOPEFILTER);
    return link.replaceMethods(
        filterReceiver(newLink
          .getInvocation(), WITHSCOPEFILTER), 
        
        NashornGuards.combineGuards(expressionGuard, filteredGuard));
  }
  
  private static MethodHandle filterGuardReceiver(GuardedInvocation link, MethodHandle receiverFilter) {
    MethodHandle test = link.getGuard();
    if (test == null)
      return null; 
    Class<?> receiverType = test.type().parameterType(0);
    MethodHandle filter = Lookup.MH.asType(receiverFilter, receiverFilter
        .type().changeParameterType(0, receiverType)
        .changeReturnType(receiverType));
    return filterReceiver(test, filter);
  }
  
  private static MethodHandle filterReceiver(MethodHandle mh, MethodHandle receiverFilter) {
    return Lookup.MH.filterArguments(mh, 0, new MethodHandle[] { receiverFilter.asType(receiverFilter.type().changeReturnType(mh.type().parameterType(0))) });
  }
  
  public static Object withFilterExpression(Object receiver) {
    return ((WithObject)receiver).expression;
  }
  
  private static Object bindToExpression(Object fn, final Object receiver) {
    if (fn instanceof ScriptFunction)
      return bindToExpression((ScriptFunction)fn, receiver); 
    if (fn instanceof ScriptObjectMirror) {
      final ScriptObjectMirror mirror = (ScriptObjectMirror)fn;
      if (mirror.isFunction())
        return new AbstractJSObject() {
            public Object call(Object thiz, Object... args) {
              return mirror.call(WithObject.withFilterExpression(receiver), args);
            }
          }; 
    } 
    return fn;
  }
  
  private static Object bindToExpression(ScriptFunction fn, Object receiver) {
    return fn.createBound(withFilterExpression(receiver), ScriptRuntime.EMPTY_ARRAY);
  }
  
  private MethodHandle expressionGuard(String name, ScriptObject owner) {
    PropertyMap map = this.expression.getMap();
    SwitchPoint[] sp = this.expression.getProtoSwitchPoints(name, owner);
    return Lookup.MH.insertArguments(WITHEXPRESSIONGUARD, 1, new Object[] { map, sp });
  }
  
  private static boolean withExpressionGuard(Object receiver, PropertyMap map, SwitchPoint[] sp) {
    return (((WithObject)receiver).expression.getMap() == map && !hasBeenInvalidated(sp));
  }
  
  private static boolean hasBeenInvalidated(SwitchPoint[] switchPoints) {
    if (switchPoints != null)
      for (SwitchPoint switchPoint : switchPoints) {
        if (switchPoint.hasBeenInvalidated())
          return true; 
      }  
    return false;
  }
  
  public static Object withFilterScope(Object receiver) {
    return ((WithObject)receiver).getProto();
  }
  
  public ScriptObject getExpression() {
    return this.expression;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), WithObject.class, name, Lookup.MH.type(rtype, types));
  }
}
