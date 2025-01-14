package okhttp3.internal.connection;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000g\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\013\n\002\b\006\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\007\n\002\030\002\n\002\b\002\n\002\b\003\n\002\030\002\n\002\b\007*\001+\030\000 42\0020\001:\0014B'\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\n\020\013J5\020\025\032\0020\0232\006\020\r\032\0020\f2\006\020\017\032\0020\0162\016\020\022\032\n\022\004\022\0020\021\030\0010\0202\006\020\024\032\0020\023¢\006\004\b\025\020\026J\025\020\030\032\0020\0062\006\020\027\032\0020\006¢\006\004\b\030\020\031J\025\020\034\032\0020\0232\006\020\033\032\0020\032¢\006\004\b\034\020\035J\r\020\036\032\0020\004¢\006\004\b\036\020\037J\r\020!\032\0020 ¢\006\004\b!\020\"J\r\020#\032\0020\004¢\006\004\b#\020\037J\037\020$\032\0020\0042\006\020\033\032\0020\0322\006\020\027\032\0020\006H\002¢\006\004\b$\020%J\025\020&\032\0020 2\006\020\033\032\0020\032¢\006\004\b&\020'R\024\020)\032\0020(8\002X\004¢\006\006\n\004\b)\020*R\024\020,\032\0020+8\002X\004¢\006\006\n\004\b,\020-R\032\020/\032\b\022\004\022\0020\0320.8\002X\004¢\006\006\n\004\b/\0200R\024\0201\032\0020\0068\002X\004¢\006\006\n\004\b1\0202R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\0203¨\0065"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", "", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "", "maxIdleConnections", "", "keepAliveDuration", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;IJLjava/util/concurrent/TimeUnit;)V", "Lokhttp3/Address;", "address", "Lokhttp3/internal/connection/RealCall;", "call", "", "Lokhttp3/Route;", "routes", "", "requireMultiplexed", "callAcquirePooledConnection", "(Lokhttp3/Address;Lokhttp3/internal/connection/RealCall;Ljava/util/List;Z)Z", "now", "cleanup", "(J)J", "Lokhttp3/internal/connection/RealConnection;", "connection", "connectionBecameIdle", "(Lokhttp3/internal/connection/RealConnection;)Z", "connectionCount", "()I", "", "evictAll", "()V", "idleConnectionCount", "pruneAndGetAllocationCount", "(Lokhttp3/internal/connection/RealConnection;J)I", "put", "(Lokhttp3/internal/connection/RealConnection;)V", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "okhttp3/internal/connection/RealConnectionPool.cleanupTask.1", "cleanupTask", "Lokhttp3/internal/connection/RealConnectionPool$cleanupTask$1;", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "connections", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "keepAliveDurationNs", "J", "I", "Companion", "okhttp"})
@SourceDebugExtension({"SMAP\nRealConnectionPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealConnectionPool.kt\nokhttp3/internal/connection/RealConnectionPool\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Util.kt\nokhttp3/internal/Util\n*L\n1#1,250:1\n1#2:251\n1774#3,4:252\n608#4,4:256\n608#4,4:260\n608#4,4:264\n*S KotlinDebug\n*F\n+ 1 RealConnectionPool.kt\nokhttp3/internal/connection/RealConnectionPool\n*L\n60#1:252,4\n95#1:256,4\n106#1:260,4\n215#1:264,4\n*E\n"})
public final class RealConnectionPool {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private final int maxIdleConnections;
  
  private final long keepAliveDurationNs;
  
  @NotNull
  private final TaskQueue cleanupQueue;
  
  @NotNull
  private final RealConnectionPool$cleanupTask$1 cleanupTask;
  
  @NotNull
  private final ConcurrentLinkedQueue<RealConnection> connections;
  
  public RealConnectionPool(@NotNull TaskRunner taskRunner, int maxIdleConnections, long keepAliveDuration, @NotNull TimeUnit timeUnit) {
    this.maxIdleConnections = maxIdleConnections;
    this.keepAliveDurationNs = timeUnit.toNanos(keepAliveDuration);
    this.cleanupQueue = taskRunner.newQueue();
    String str = Util.okHttpName + " ConnectionPool";
    this.cleanupTask = new RealConnectionPool$cleanupTask$1(str);
    this.connections = new ConcurrentLinkedQueue<>();
    if (!((keepAliveDuration > 0L) ? 1 : 0)) {
      int $i$a$-require-RealConnectionPool$1 = 0;
      String str1 = "keepAliveDuration <= 0: " + keepAliveDuration;
      throw new IllegalArgumentException(str1.toString());
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\t\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/connection/RealConnectionPool.cleanupTask.1", "Lokhttp3/internal/concurrent/Task;", "", "runOnce", "()J", "okhttp"})
  public static final class RealConnectionPool$cleanupTask$1 extends Task {
    RealConnectionPool$cleanupTask$1(String $super_call_param$1) {
      super($super_call_param$1, false, 2, null);
    }
    
    public long runOnce() {
      return RealConnectionPool.this.cleanup(System.nanoTime());
    }
  }
  
  public final int idleConnectionCount() {
    Iterable<RealConnection> $this$count$iv = this.connections;
    int $i$f$count = 0;
    int count$iv = 0;
    for (RealConnection element$iv : $this$count$iv) {
      RealConnection it = element$iv;
      int $i$a$-count-RealConnectionPool$idleConnectionCount$1 = 0;
      Intrinsics.checkNotNullExpressionValue(it, "it");
      RealConnection realConnection1 = it;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */
    } 
    return ($this$count$iv instanceof Collection && ((Collection)$this$count$iv).isEmpty()) ? 0 : count$iv;
  }
  
  public final int connectionCount() {
    return this.connections.size();
  }
  
  public final boolean callAcquirePooledConnection(@NotNull Address address, @NotNull RealCall call, @Nullable List<Route> routes, boolean requireMultiplexed) {
    Intrinsics.checkNotNullParameter(address, "address");
    Intrinsics.checkNotNullParameter(call, "call");
    for (RealConnection connection : this.connections) {
      Intrinsics.checkNotNullExpressionValue(connection, "connection");
      synchronized (connection) {
        int $i$a$-synchronized-RealConnectionPool$callAcquirePooledConnection$1 = 0;
        if ((!requireMultiplexed || connection.isMultiplexed$okhttp()) && connection.isEligible$okhttp(address, routes)) {
          call.acquireConnectionNoEvents(connection);
          return true;
        } 
        Unit unit = Unit.INSTANCE;
      } 
    } 
    return false;
  }
  
  public final void put(@NotNull RealConnection connection) {
    Intrinsics.checkNotNullParameter(connection, "connection");
    Object $this$assertThreadHoldsLock$iv = connection;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    this.connections.add(connection);
    TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
  }
  
  public final boolean connectionBecameIdle(@NotNull RealConnection connection) {
    Intrinsics.checkNotNullParameter(connection, "connection");
    Object $this$assertThreadHoldsLock$iv = connection;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    connection.setNoNewExchanges(true);
    this.connections.remove(connection);
    if (this.connections.isEmpty())
      this.cleanupQueue.cancelAll(); 
    TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
    return (connection.getNoNewExchanges() || this.maxIdleConnections == 0);
  }
  
  public final void evictAll() {
    Intrinsics.checkNotNullExpressionValue(this.connections.iterator(), "connections.iterator()");
    Iterator<RealConnection> i = this.connections.iterator();
    while (i.hasNext()) {
      RealConnection connection = i.next();
      Intrinsics.checkNotNullExpressionValue(connection, "connection");
      synchronized (connection) {
        int $i$a$-synchronized-RealConnectionPool$evictAll$socketToClose$1 = 0;
        i.remove();
        connection.setNoNewExchanges(true);
        Socket socket = connection.getCalls().isEmpty() ? connection.socket() : null;
      } 
      Socket socketToClose = socket;
      if (socketToClose != null)
        Util.closeQuietly(socketToClose); 
    } 
    if (this.connections.isEmpty())
      this.cleanupQueue.cancelAll(); 
  }
  
  public final long cleanup(long now) {
    int inUseConnectionCount = 0;
    int idleConnectionCount = 0;
    Object longestIdleConnection = null;
    long longestIdleDurationNs = 0L;
    longestIdleDurationNs = Long.MIN_VALUE;
    for (RealConnection connection : this.connections) {
      Intrinsics.checkNotNullExpressionValue(connection, "connection");
      synchronized (connection) {
        int $i$a$-synchronized-RealConnectionPool$cleanup$1 = 0;
        int i = inUseConnectionCount;
        inUseConnectionCount = i + 1;
        idleConnectionCount++;
        long idleDurationNs = now - connection.getIdleAtNs$okhttp();
        if (idleDurationNs > longestIdleDurationNs) {
          longestIdleDurationNs = idleDurationNs;
          longestIdleConnection = connection;
        } else {
        
        } 
        $i$a$-synchronized-RealConnectionPool$cleanup$1 = (pruneAndGetAllocationCount(connection, now) > 0) ? Integer.valueOf(i) : Unit.INSTANCE;
      } 
    } 
    if (longestIdleDurationNs >= this.keepAliveDurationNs || idleConnectionCount > this.maxIdleConnections) {
      Intrinsics.checkNotNull(longestIdleConnection);
      Object object = longestIdleConnection;
      synchronized (object) {
        int $i$a$-synchronized-RealConnectionPool$cleanup$2 = 0;
        if (!object.getCalls().isEmpty())
          return 0L; 
        if (object.getIdleAtNs$okhttp() + longestIdleDurationNs != now)
          return 0L; 
        object.setNoNewExchanges(true);
        boolean bool = this.connections.remove(longestIdleConnection);
      } 
      Util.closeQuietly(object.socket());
      if (this.connections.isEmpty())
        this.cleanupQueue.cancelAll(); 
      return 0L;
    } 
    if (idleConnectionCount > 0)
      return this.keepAliveDurationNs - longestIdleDurationNs; 
    if (inUseConnectionCount > 0)
      return this.keepAliveDurationNs; 
    return -1L;
  }
  
  private final int pruneAndGetAllocationCount(RealConnection connection, long now) {
    Object $this$assertThreadHoldsLock$iv = connection;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    List<Reference<RealCall>> references = connection.getCalls();
    int i = 0;
    while (i < references.size()) {
      Reference reference = references.get(i);
      if (reference.get() != null) {
        i++;
        continue;
      } 
      Intrinsics.checkNotNull(reference, "null cannot be cast to non-null type okhttp3.internal.connection.RealCall.CallReference");
      RealCall.CallReference callReference = (RealCall.CallReference)reference;
      String message = "A connection to " + connection.route().address().url() + " was leaked. Did you forget to close a response body?";
      Platform.Companion.get().logCloseableLeak(message, callReference.getCallStackTrace());
      references.remove(i);
      connection.setNoNewExchanges(true);
      if (references.isEmpty()) {
        connection.setIdleAtNs$okhttp(now - this.keepAliveDurationNs);
        return 0;
      } 
    } 
    return references.size();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "<init>", "()V", "Lokhttp3/ConnectionPool;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "get", "(Lokhttp3/ConnectionPool;)Lokhttp3/internal/connection/RealConnectionPool;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final RealConnectionPool get(@NotNull ConnectionPool connectionPool) {
      Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
      return connectionPool.getDelegate$okhttp();
    }
  }
}
