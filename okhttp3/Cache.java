package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000n\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\t\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\006\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b\t\n\002\020)\n\002\020\016\n\002\b\t\n\002\020\013\n\002\b\f\030\000 N2\0020\0012\0020\002:\004ONPQB\031\b\026\022\006\020\004\032\0020\003\022\006\020\006\032\0020\005¢\006\004\b\007\020\bB!\b\000\022\006\020\004\032\0020\003\022\006\020\006\032\0020\005\022\006\020\n\032\0020\t¢\006\004\b\007\020\013J\035\020\020\032\0020\0172\f\020\016\032\b\030\0010\fR\0020\rH\002¢\006\004\b\020\020\021J\017\020\022\032\0020\017H\026¢\006\004\b\022\020\023J\r\020\024\032\0020\017¢\006\004\b\024\020\023J\017\020\004\032\0020\003H\007¢\006\004\b\025\020\026J\r\020\027\032\0020\017¢\006\004\b\027\020\023J\017\020\030\032\0020\017H\026¢\006\004\b\030\020\023J\031\020\036\032\004\030\0010\0332\006\020\032\032\0020\031H\000¢\006\004\b\034\020\035J\r\020 \032\0020\037¢\006\004\b \020!J\r\020\"\032\0020\017¢\006\004\b\"\020\023J\r\020\006\032\0020\005¢\006\004\b\006\020#J\r\020$\032\0020\037¢\006\004\b$\020!J\031\020)\032\004\030\0010&2\006\020%\032\0020\033H\000¢\006\004\b'\020(J\027\020,\032\0020\0172\006\020\032\032\0020\031H\000¢\006\004\b*\020+J\r\020-\032\0020\037¢\006\004\b-\020!J\r\020.\032\0020\005¢\006\004\b.\020#J\017\0200\032\0020\017H\000¢\006\004\b/\020\023J\027\0205\032\0020\0172\006\0202\032\00201H\000¢\006\004\b3\0204J\037\020:\032\0020\0172\006\0206\032\0020\0332\006\0207\032\0020\033H\000¢\006\004\b8\0209J\023\020=\032\b\022\004\022\0020<0;¢\006\004\b=\020>J\r\020?\032\0020\037¢\006\004\b?\020!J\r\020@\032\0020\037¢\006\004\b@\020!R\032\020A\032\0020\r8\000X\004¢\006\f\n\004\bA\020B\032\004\bC\020DR\021\020\004\032\0020\0038G¢\006\006\032\004\b\004\020\026R\026\020 \032\0020\0378\002@\002X\016¢\006\006\n\004\b \020ER\021\020G\032\0020F8F¢\006\006\032\004\bG\020HR\026\020$\032\0020\0378\002@\002X\016¢\006\006\n\004\b$\020ER\026\020-\032\0020\0378\002@\002X\016¢\006\006\n\004\b-\020ER\"\020?\032\0020\0378\000@\000X\016¢\006\022\n\004\b?\020E\032\004\bI\020!\"\004\bJ\020KR\"\020@\032\0020\0378\000@\000X\016¢\006\022\n\004\b@\020E\032\004\bL\020!\"\004\bM\020K¨\006R"}, d2 = {"Lokhttp3/Cache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "Ljava/io/File;", "directory", "", "maxSize", "<init>", "(Ljava/io/File;J)V", "Lokhttp3/internal/io/FileSystem;", "fileSystem", "(Ljava/io/File;JLokhttp3/internal/io/FileSystem;)V", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "editor", "", "abortQuietly", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "close", "()V", "delete", "-deprecated_directory", "()Ljava/io/File;", "evictAll", "flush", "Lokhttp3/Request;", "request", "Lokhttp3/Response;", "get$okhttp", "(Lokhttp3/Request;)Lokhttp3/Response;", "get", "", "hitCount", "()I", "initialize", "()J", "networkCount", "response", "Lokhttp3/internal/cache/CacheRequest;", "put$okhttp", "(Lokhttp3/Response;)Lokhttp3/internal/cache/CacheRequest;", "put", "remove$okhttp", "(Lokhttp3/Request;)V", "remove", "requestCount", "size", "trackConditionalCacheHit$okhttp", "trackConditionalCacheHit", "Lokhttp3/internal/cache/CacheStrategy;", "cacheStrategy", "trackResponse$okhttp", "(Lokhttp3/internal/cache/CacheStrategy;)V", "trackResponse", "cached", "network", "update$okhttp", "(Lokhttp3/Response;Lokhttp3/Response;)V", "update", "", "", "urls", "()Ljava/util/Iterator;", "writeAbortCount", "writeSuccessCount", "cache", "Lokhttp3/internal/cache/DiskLruCache;", "getCache$okhttp", "()Lokhttp3/internal/cache/DiskLruCache;", "I", "", "isClosed", "()Z", "getWriteAbortCount$okhttp", "setWriteAbortCount$okhttp", "(I)V", "getWriteSuccessCount$okhttp", "setWriteSuccessCount$okhttp", "Companion", "CacheResponseBody", "Entry", "RealCacheRequest", "okhttp"})
public final class Cache implements Closeable, Flushable {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final DiskLruCache cache;
  
  private int writeSuccessCount;
  
  private int writeAbortCount;
  
  private int networkCount;
  
  private int hitCount;
  
  private int requestCount;
  
  private static final int VERSION = 201105;
  
  private static final int ENTRY_METADATA = 0;
  
  private static final int ENTRY_BODY = 1;
  
  private static final int ENTRY_COUNT = 2;
  
  public Cache(@NotNull File directory, long maxSize, @NotNull FileSystem fileSystem) {
    this.cache = new DiskLruCache(
        fileSystem, 
        directory, 
        201105, 
        2, 
        maxSize, 
        TaskRunner.INSTANCE);
  }
  
  @NotNull
  public final DiskLruCache getCache$okhttp() {
    return this.cache;
  }
  
  public final int getWriteSuccessCount$okhttp() {
    return this.writeSuccessCount;
  }
  
  public final void setWriteSuccessCount$okhttp(int <set-?>) {
    this.writeSuccessCount = <set-?>;
  }
  
  public final int getWriteAbortCount$okhttp() {
    return this.writeAbortCount;
  }
  
  public final void setWriteAbortCount$okhttp(int <set-?>) {
    this.writeAbortCount = <set-?>;
  }
  
  public final boolean isClosed() {
    return this.cache.isClosed();
  }
  
  public Cache(@NotNull File directory, long maxSize) {
    this(directory, maxSize, FileSystem.SYSTEM);
  }
  
  @Nullable
  public final Response get$okhttp(@NotNull Request request) {
    DiskLruCache.Snapshot snapshot1;
    Entry entry1;
    Intrinsics.checkNotNullParameter(request, "request");
    String key = Companion.key(request.url());
    try {
      if (this.cache.get(key) == null) {
        this.cache.get(key);
        return null;
      } 
    } catch (IOException _) {
      return null;
    } 
    DiskLruCache.Snapshot snapshot = snapshot1;
    try {
      entry1 = new Entry(snapshot.getSource(0));
    } catch (IOException _) {
      Util.closeQuietly((Closeable)snapshot);
      return null;
    } 
    Entry entry = entry1;
    Response response = entry.response(snapshot);
    if (!entry.matches(request, response)) {
      if (response.body() != null) {
        Util.closeQuietly(response.body());
      } else {
        response.body();
      } 
      return null;
    } 
    return response;
  }
  
  @Nullable
  public final CacheRequest put$okhttp(@NotNull Response response) {
    Intrinsics.checkNotNullParameter(response, "response");
    String requestMethod = response.request().method();
    if (HttpMethod.INSTANCE.invalidatesCache(response.request().method())) {
      try {
        remove$okhttp(response.request());
      } catch (IOException iOException) {}
      return null;
    } 
    if (!Intrinsics.areEqual(requestMethod, "GET"))
      return null; 
    if (Companion.hasVaryAll(response))
      return null; 
    Entry entry = new Entry(response);
    DiskLruCache.Editor editor = null;
    try {
      if (DiskLruCache.edit$default(this.cache, Companion.key(response.request().url()), 0L, 2, null) == null) {
        DiskLruCache.edit$default(this.cache, Companion.key(response.request().url()), 0L, 2, null);
        return null;
      } 
      entry.writeTo(editor);
      return new RealCacheRequest(editor);
    } catch (IOException _) {
      abortQuietly(editor);
      return null;
    } 
  }
  
  public final void remove$okhttp(@NotNull Request request) throws IOException {
    Intrinsics.checkNotNullParameter(request, "request");
    this.cache.remove(Companion.key(request.url()));
  }
  
  public final void update$okhttp(@NotNull Response cached, @NotNull Response network) {
    Intrinsics.checkNotNullParameter(cached, "cached");
    Intrinsics.checkNotNullParameter(network, "network");
    Entry entry = new Entry(network);
    Intrinsics.checkNotNull(cached.body(), "null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");
    DiskLruCache.Snapshot snapshot = ((CacheResponseBody)cached.body()).getSnapshot();
    DiskLruCache.Editor editor = null;
    try {
      if (snapshot.edit() == null) {
        snapshot.edit();
        return;
      } 
      entry.writeTo(editor);
      editor.commit();
    } catch (IOException _) {
      abortQuietly(editor);
    } 
  }
  
  private final void abortQuietly(DiskLruCache.Editor editor) {
    try {
      if (editor != null) {
        editor.abort();
      } else {
      
      } 
    } catch (IOException iOException) {}
  }
  
  public final void initialize() throws IOException {
    this.cache.initialize();
  }
  
  public final void delete() throws IOException {
    this.cache.delete();
  }
  
  public final void evictAll() throws IOException {
    this.cache.evictAll();
  }
  
  @NotNull
  public final Iterator<String> urls() throws IOException {
    return new Cache$urls$1(this);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000)\n\000\n\002\020)\n\002\020\016\n\002\020\013\n\002\b\004\n\002\020\002\n\002\b\004\n\002\030\002\n\002\030\002\n\002\b\005*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001J\020\020\004\032\0020\003H\002¢\006\004\b\004\020\005J\020\020\006\032\0020\002H\002¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nR\026\020\013\032\0020\0038\002@\002X\016¢\006\006\n\004\b\013\020\fR\036\020\017\032\f\022\b\022\0060\rR\0020\0160\0018\002X\004¢\006\006\n\004\b\017\020\020R\030\020\021\032\004\030\0010\0028\002@\002X\016¢\006\006\n\004\b\021\020\022¨\006\023"}, d2 = {"okhttp3/Cache.urls.1", "", "", "", "hasNext", "()Z", "next", "()Ljava/lang/String;", "", "remove", "()V", "canRemove", "Z", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "delegate", "Ljava/util/Iterator;", "nextUrl", "Ljava/lang/String;", "okhttp"})
  @SourceDebugExtension({"SMAP\nCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cache.kt\nokhttp3/Cache$urls$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,788:1\n1#2:789\n*E\n"})
  public static final class Cache$urls$1 implements Iterator<String>, KMutableIterator {
    @NotNull
    private final Iterator<DiskLruCache.Snapshot> delegate;
    
    @Nullable
    private String nextUrl;
    
    private boolean canRemove;
    
    Cache$urls$1(Cache $receiver) {
      this.delegate = $receiver.getCache$okhttp().snapshots();
    }
    
    public boolean hasNext() {
      if (this.nextUrl != null)
        return true; 
      this.canRemove = false;
      while (this.delegate.hasNext()) {
        try {
          Closeable closeable = (Closeable)this.delegate.next();
          Throwable throwable = null;
          try {
            DiskLruCache.Snapshot snapshot = (DiskLruCache.Snapshot)closeable;
            int $i$a$-use-Cache$urls$1$hasNext$1 = 0;
            BufferedSource metadata = Okio.buffer(snapshot.getSource(0));
            this.nextUrl = metadata.readUtf8LineStrict();
            return true;
          } catch (Throwable throwable1) {
            throwable = throwable1 = null;
            throw throwable1;
          } finally {
            CloseableKt.closeFinally(closeable, throwable);
          } 
        } catch (IOException iOException) {}
      } 
      return false;
    }
    
    @NotNull
    public String next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      Intrinsics.checkNotNull(this.nextUrl);
      String result = this.nextUrl;
      this.nextUrl = null;
      this.canRemove = true;
      return result;
    }
    
    public void remove() {
      if (!this.canRemove) {
        int $i$a$-check-Cache$urls$1$remove$1 = 0;
        String str = "remove() before next()";
        throw new IllegalStateException(str.toString());
      } 
      this.delegate.remove();
    }
  }
  
  public final synchronized int writeAbortCount() {
    return this.writeAbortCount;
  }
  
  public final synchronized int writeSuccessCount() {
    return this.writeSuccessCount;
  }
  
  public final long size() throws IOException {
    return this.cache.size();
  }
  
  public final long maxSize() {
    return this.cache.getMaxSize();
  }
  
  public void flush() throws IOException {
    this.cache.flush();
  }
  
  public void close() throws IOException {
    this.cache.close();
  }
  
  @JvmName(name = "directory")
  @NotNull
  public final File directory() {
    return this.cache.getDirectory();
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "directory", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_directory")
  @NotNull
  public final File -deprecated_directory() {
    return this.cache.getDirectory();
  }
  
  public final synchronized void trackResponse$okhttp(@NotNull CacheStrategy cacheStrategy) {
    Intrinsics.checkNotNullParameter(cacheStrategy, "cacheStrategy");
    int i = this.requestCount;
    this.requestCount = i + 1;
    if (cacheStrategy.getNetworkRequest() != null) {
      i = this.networkCount;
      this.networkCount = i + 1;
    } else if (cacheStrategy.getCacheResponse() != null) {
      i = this.hitCount;
      this.hitCount = i + 1;
    } 
  }
  
  public final synchronized void trackConditionalCacheHit$okhttp() {
    int i = this.hitCount;
    this.hitCount = i + 1;
  }
  
  public final synchronized int networkCount() {
    return this.networkCount;
  }
  
  public final synchronized int hitCount() {
    return this.hitCount;
  }
  
  public final synchronized int requestCount() {
    return this.requestCount;
  }
  
  @JvmStatic
  @NotNull
  public static final String key(@NotNull HttpUrl url) {
    return Companion.key(url);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\b\b\004\030\0002\0020\001B\023\022\n\020\004\032\0060\002R\0020\003¢\006\004\b\005\020\006J\017\020\b\032\0020\007H\026¢\006\004\b\b\020\tJ\017\020\013\032\0020\nH\026¢\006\004\b\013\020\fR\024\020\013\032\0020\n8\002X\004¢\006\006\n\004\b\013\020\rR\024\020\016\032\0020\n8\002X\004¢\006\006\n\004\b\016\020\rR\"\020\020\032\0020\0178\006@\006X\016¢\006\022\n\004\b\020\020\021\032\004\b\022\020\023\"\004\b\024\020\025R\030\020\004\032\0060\002R\0020\0038\002X\004¢\006\006\n\004\b\004\020\026¨\006\027"}, d2 = {"Lokhttp3/Cache$RealCacheRequest;", "Lokhttp3/internal/cache/CacheRequest;", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "editor", "<init>", "(Lokhttp3/Cache;Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "", "abort", "()V", "Lokio/Sink;", "body", "()Lokio/Sink;", "Lokio/Sink;", "cacheOut", "", "done", "Z", "getDone", "()Z", "setDone", "(Z)V", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "okhttp"})
  private final class RealCacheRequest implements CacheRequest {
    @NotNull
    private final DiskLruCache.Editor editor;
    
    @NotNull
    private final Sink cacheOut;
    
    @NotNull
    private final Sink body;
    
    private boolean done;
    
    public RealCacheRequest(DiskLruCache.Editor editor) {
      this.editor = editor;
      this.cacheOut = this.editor.newSink(1);
      Sink sink = this.cacheOut;
      this.body = (Sink)new ForwardingSink(this, sink) {
          public void close() throws IOException {
            Cache cache1 = Cache.this;
            Cache.RealCacheRequest realCacheRequest = Cache.RealCacheRequest.this;
            Cache cache2 = Cache.this;
            synchronized (cache1) {
              int $i$a$-synchronized-Cache$RealCacheRequest$1$close$1 = 0;
              if (realCacheRequest.getDone())
                return; 
              realCacheRequest.setDone(true);
              int i = cache2.getWriteSuccessCount$okhttp();
              cache2.setWriteSuccessCount$okhttp(i + 1);
              $i$a$-synchronized-Cache$RealCacheRequest$1$close$1 = i;
            } 
            super.close();
            Cache.RealCacheRequest.this.editor.commit();
          }
        };
    }
    
    public final boolean getDone() {
      return this.done;
    }
    
    public final void setDone(boolean <set-?>) {
      this.done = <set-?>;
    }
    
    public void abort() {
      Cache cache1 = Cache.this, cache2 = Cache.this;
      synchronized (cache1) {
        int $i$a$-synchronized-Cache$RealCacheRequest$abort$1 = 0;
        if (this.done)
          return; 
        this.done = true;
        int i = cache2.getWriteAbortCount$okhttp();
        cache2.setWriteAbortCount$okhttp(i + 1);
        $i$a$-synchronized-Cache$RealCacheRequest$abort$1 = i;
      } 
      Util.closeQuietly((Closeable)this.cacheOut);
      try {
        this.editor.abort();
      } catch (IOException iOException) {}
    }
    
    @NotNull
    public Sink body() {
      return this.body;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\001\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\b\002\030\000 <2\0020\001:\001<B\021\b\026\022\006\020\003\032\0020\002¢\006\004\b\004\020\005B\021\b\026\022\006\020\007\032\0020\006¢\006\004\b\004\020\bJ\035\020\f\032\0020\0132\006\020\n\032\0020\t2\006\020\007\032\0020\006¢\006\004\b\f\020\rJ\035\020\022\032\b\022\004\022\0020\0210\0202\006\020\017\032\0020\016H\002¢\006\004\b\022\020\023J\031\020\007\032\0020\0062\n\020\026\032\0060\024R\0020\025¢\006\004\b\007\020\027J%\020\034\032\0020\0332\006\020\031\032\0020\0302\f\020\032\032\b\022\004\022\0020\0210\020H\002¢\006\004\b\034\020\035J\031\020 \032\0020\0332\n\020\037\032\0060\036R\0020\025¢\006\004\b \020!R\024\020#\032\0020\"8\002X\004¢\006\006\n\004\b#\020$R\026\020&\032\004\030\0010%8\002X\004¢\006\006\n\004\b&\020'R\024\020(\032\0020\0138BX\004¢\006\006\032\004\b(\020)R\024\020+\032\0020*8\002X\004¢\006\006\n\004\b+\020,R\024\020.\032\0020-8\002X\004¢\006\006\n\004\b.\020/R\024\0201\032\002008\002X\004¢\006\006\n\004\b1\0202R\024\0203\032\0020*8\002X\004¢\006\006\n\004\b3\020,R\024\0205\032\002048\002X\004¢\006\006\n\004\b5\0206R\024\0207\032\002008\002X\004¢\006\006\n\004\b7\0202R\024\0209\032\002088\002X\004¢\006\006\n\004\b9\020:R\024\020;\032\002048\002X\004¢\006\006\n\004\b;\0206¨\006="}, d2 = {"Lokhttp3/Cache$Entry;", "", "Lokio/Source;", "rawSource", "<init>", "(Lokio/Source;)V", "Lokhttp3/Response;", "response", "(Lokhttp3/Response;)V", "Lokhttp3/Request;", "request", "", "matches", "(Lokhttp3/Request;Lokhttp3/Response;)Z", "Lokio/BufferedSource;", "source", "", "Ljava/security/cert/Certificate;", "readCertificateList", "(Lokio/BufferedSource;)Ljava/util/List;", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "snapshot", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;)Lokhttp3/Response;", "Lokio/BufferedSink;", "sink", "certificates", "", "writeCertList", "(Lokio/BufferedSink;Ljava/util/List;)V", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "editor", "writeTo", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "", "code", "I", "Lokhttp3/Handshake;", "handshake", "Lokhttp3/Handshake;", "isHttps", "()Z", "", "message", "Ljava/lang/String;", "Lokhttp3/Protocol;", "protocol", "Lokhttp3/Protocol;", "", "receivedResponseMillis", "J", "requestMethod", "Lokhttp3/Headers;", "responseHeaders", "Lokhttp3/Headers;", "sentRequestMillis", "Lokhttp3/HttpUrl;", "url", "Lokhttp3/HttpUrl;", "varyHeaders", "Companion", "okhttp"})
  private static final class Entry {
    private final boolean isHttps() {
      return Intrinsics.areEqual(this.url.scheme(), "https");
    }
    
    public Entry(@NotNull Source rawSource) throws IOException {
      Closeable closeable = (Closeable)rawSource;
      Throwable throwable = null;
      try {
        Source it = (Source)closeable;
        int $i$a$-use-Cache$Entry$1 = 0;
        BufferedSource source = Okio.buffer(rawSource);
        String urlLine = source.readUtf8LineStrict();
        if (HttpUrl.Companion.parse(urlLine) == null) {
          HttpUrl.Companion.parse(urlLine);
          IOException iOException1 = new IOException("Cache corruption for " + urlLine), iOException2 = iOException1;
          HttpUrl httpUrl = HttpUrl.Companion.parse(urlLine);
          int $i$a$-also-Cache$Entry$1$1 = 0;
          Platform.Companion.get().log("cache corruption", 5, iOException2);
          throw iOException1;
        } 
        ((Entry)HttpUrl.Companion.parse(urlLine)).url = HttpUrl.Companion.parse(urlLine);
        this.requestMethod = source.readUtf8LineStrict();
        Headers.Builder varyHeadersBuilder = new Headers.Builder();
        int varyRequestHeaderLineCount = Cache.Companion.readInt$okhttp(source);
        for (int i = 0; i < varyRequestHeaderLineCount; i++)
          varyHeadersBuilder.addLenient$okhttp(source.readUtf8LineStrict()); 
        this.varyHeaders = varyHeadersBuilder.build();
        StatusLine statusLine = StatusLine.Companion.parse(source.readUtf8LineStrict());
        this.protocol = statusLine.protocol;
        this.code = statusLine.code;
        this.message = statusLine.message;
        Headers.Builder responseHeadersBuilder = new Headers.Builder();
        int responseHeaderLineCount = Cache.Companion.readInt$okhttp(source);
        for (int j = 0; j < responseHeaderLineCount; j++)
          responseHeadersBuilder.addLenient$okhttp(source.readUtf8LineStrict()); 
        String sendRequestMillisString = responseHeadersBuilder.get(SENT_MILLIS);
        String receivedResponseMillisString = responseHeadersBuilder.get(RECEIVED_MILLIS);
        responseHeadersBuilder.removeAll(SENT_MILLIS);
        responseHeadersBuilder.removeAll(RECEIVED_MILLIS);
        this.sentRequestMillis = (sendRequestMillisString != null) ? Long.parseLong(sendRequestMillisString) : 0L;
        this.receivedResponseMillis = (receivedResponseMillisString != null) ? Long.parseLong(receivedResponseMillisString) : 0L;
        this.responseHeaders = responseHeadersBuilder.build();
        if (isHttps()) {
          String blank = source.readUtf8LineStrict();
          if ((((CharSequence)blank).length() > 0))
            throw new IOException("expected \"\" but was \"" + blank + '"'); 
          String cipherSuiteString = source.readUtf8LineStrict();
          CipherSuite cipherSuite = CipherSuite.Companion.forJavaName(cipherSuiteString);
          List<Certificate> peerCertificates = readCertificateList(source);
          List<Certificate> localCertificates = readCertificateList(source);
          TlsVersion tlsVersion = !source.exhausted() ? TlsVersion.Companion.forJavaName(source.readUtf8LineStrict()) : TlsVersion.SSL_3_0;
          this.handshake = Handshake.Companion.get(tlsVersion, cipherSuite, peerCertificates, localCertificates);
        } else {
          this.handshake = null;
        } 
        Unit unit = Unit.INSTANCE;
      } catch (Throwable throwable1) {
        throwable = throwable1 = null;
        throw throwable1;
      } finally {
        CloseableKt.closeFinally(closeable, throwable);
      } 
    }
    
    public Entry(@NotNull Response response) {
      this.url = response.request().url();
      this.varyHeaders = Cache.Companion.varyHeaders(response);
      this.requestMethod = response.request().method();
      this.protocol = response.protocol();
      this.code = response.code();
      this.message = response.message();
      this.responseHeaders = response.headers();
      this.handshake = response.handshake();
      this.sentRequestMillis = response.sentRequestAtMillis();
      this.receivedResponseMillis = response.receivedResponseAtMillis();
    }
    
    public final void writeTo(@NotNull DiskLruCache.Editor editor) throws IOException {
      Intrinsics.checkNotNullParameter(editor, "editor");
      Closeable closeable = (Closeable)Okio.buffer(editor.newSink(0));
      Throwable throwable = null;
      try {
        BufferedSink sink = (BufferedSink)closeable;
        int $i$a$-use-Cache$Entry$writeTo$1 = 0;
        sink.writeUtf8(this.url.toString()).writeByte(10);
        sink.writeUtf8(this.requestMethod).writeByte(10);
        sink.writeDecimalLong(this.varyHeaders.size()).writeByte(10);
        int i, j;
        for (i = 0, j = this.varyHeaders.size(); i < j; i++)
          sink.writeUtf8(this.varyHeaders.name(i)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i)).writeByte(10); 
        sink.writeUtf8((new StatusLine(this.protocol, this.code, this.message)).toString()).writeByte(10);
        sink.writeDecimalLong((this.responseHeaders.size() + 2)).writeByte(10);
        for (i = 0, j = this.responseHeaders.size(); i < j; i++)
          sink.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10); 
        sink.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
        sink.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
        if (isHttps()) {
          sink.writeByte(10);
          Intrinsics.checkNotNull(this.handshake);
          sink.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
          writeCertList(sink, this.handshake.peerCertificates());
          writeCertList(sink, this.handshake.localCertificates());
          sink.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
        } 
        Unit unit = Unit.INSTANCE;
      } catch (Throwable throwable1) {
        throwable = throwable1 = null;
        throw throwable1;
      } finally {
        CloseableKt.closeFinally(closeable, throwable);
      } 
    }
    
    private final List<Certificate> readCertificateList(BufferedSource source) throws IOException {
      int length = Cache.Companion.readInt$okhttp(source);
      if (length == -1)
        return CollectionsKt.emptyList(); 
      try {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        ArrayList<Certificate> result = new ArrayList(length);
        for (int i = 0; i < length; i++) {
          ByteString certificateBytes;
          String line = source.readUtf8LineStrict();
          Buffer bytes = new Buffer();
          if (ByteString.Companion.decodeBase64(line) == null) {
            ByteString.Companion.decodeBase64(line);
            throw new IOException("Corrupt certificate in cache entry");
          } 
          bytes.write(certificateBytes);
          result.add(certificateFactory.generateCertificate(bytes.inputStream()));
        } 
        return result;
      } catch (CertificateException e) {
        throw new IOException(e.getMessage());
      } 
    }
    
    private final void writeCertList(BufferedSink sink, List certificates) throws IOException {
      try {
        sink.writeDecimalLong(certificates.size()).writeByte(10);
        for (Certificate element : certificates) {
          byte[] bytes = element.getEncoded();
          Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
          String line = ByteString.Companion.of$default(ByteString.Companion, bytes, 0, 0, 3, null).base64();
          sink.writeUtf8(line).writeByte(10);
        } 
      } catch (CertificateEncodingException e) {
        throw new IOException(e.getMessage());
      } 
    }
    
    public final boolean matches(@NotNull Request request, @NotNull Response response) {
      Intrinsics.checkNotNullParameter(request, "request");
      Intrinsics.checkNotNullParameter(response, "response");
      return (Intrinsics.areEqual(this.url, request.url()) && Intrinsics.areEqual(this.requestMethod, request.method()) && Cache.Companion.varyMatches(response, this.varyHeaders, request));
    }
    
    @NotNull
    public final Response response(@NotNull DiskLruCache.Snapshot snapshot) {
      Intrinsics.checkNotNullParameter(snapshot, "snapshot");
      String contentType = this.responseHeaders.get("Content-Type");
      String contentLength = this.responseHeaders.get("Content-Length");
      Request cacheRequest = (new Request.Builder()).url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build();
      return (new Response.Builder()).request(cacheRequest).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new Cache.CacheResponseBody(snapshot, contentType, contentLength)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\006R\024\020\007\032\0020\0048\002X\004¢\006\006\n\004\b\007\020\006¨\006\b"}, d2 = {"Lokhttp3/Cache$Entry$Companion;", "", "<init>", "()V", "", "RECEIVED_MILLIS", "Ljava/lang/String;", "SENT_MILLIS", "okhttp"})
    public static final class Companion {
      private Companion() {}
    }
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    
    @NotNull
    private final HttpUrl url;
    
    @NotNull
    private final Headers varyHeaders;
    
    @NotNull
    private final String requestMethod;
    
    @NotNull
    private final Protocol protocol;
    
    private final int code;
    
    @NotNull
    private final String message;
    
    @NotNull
    private final Headers responseHeaders;
    
    @Nullable
    private final Handshake handshake;
    
    private final long sentRequestMillis;
    
    private final long receivedResponseMillis;
    
    @NotNull
    private static final String SENT_MILLIS = Platform.Companion.get().getPrefix() + "-Sent-Millis";
    
    @NotNull
    private static final String RECEIVED_MILLIS = Platform.Companion.get().getPrefix() + "-Received-Millis";
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000.\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\004\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\t\b\002\030\0002\0020\001B'\022\n\020\004\032\0060\002R\0020\003\022\b\020\006\032\004\030\0010\005\022\b\020\007\032\004\030\0010\005¢\006\004\b\b\020\tJ\017\020\007\032\0020\nH\026¢\006\004\b\007\020\013J\021\020\006\032\004\030\0010\fH\026¢\006\004\b\006\020\rJ\017\020\017\032\0020\016H\026¢\006\004\b\017\020\020R\024\020\021\032\0020\0168\002X\004¢\006\006\n\004\b\021\020\022R\026\020\007\032\004\030\0010\0058\002X\004¢\006\006\n\004\b\007\020\023R\026\020\006\032\004\030\0010\0058\002X\004¢\006\006\n\004\b\006\020\023R\033\020\004\032\0060\002R\0020\0038\006¢\006\f\n\004\b\004\020\024\032\004\b\025\020\026¨\006\027"}, d2 = {"Lokhttp3/Cache$CacheResponseBody;", "Lokhttp3/ResponseBody;", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "snapshot", "", "contentType", "contentLength", "<init>", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;Ljava/lang/String;Ljava/lang/String;)V", "", "()J", "Lokhttp3/MediaType;", "()Lokhttp3/MediaType;", "Lokio/BufferedSource;", "source", "()Lokio/BufferedSource;", "bodySource", "Lokio/BufferedSource;", "Ljava/lang/String;", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "getSnapshot", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "okhttp"})
  private static final class CacheResponseBody extends ResponseBody {
    @NotNull
    private final DiskLruCache.Snapshot snapshot;
    
    @Nullable
    private final String contentType;
    
    @Nullable
    private final String contentLength;
    
    @NotNull
    private final BufferedSource bodySource;
    
    @NotNull
    public final DiskLruCache.Snapshot getSnapshot() {
      return this.snapshot;
    }
    
    public CacheResponseBody(@NotNull DiskLruCache.Snapshot snapshot, @Nullable String contentType, @Nullable String contentLength) {
      this.snapshot = snapshot;
      this.contentType = contentType;
      this.contentLength = contentLength;
      Source source = this.snapshot.getSource(1);
      this.bodySource = Okio.buffer((Source)new ForwardingSource(this) {
            public void close() throws IOException {
              Cache.CacheResponseBody.this.getSnapshot().close();
              super.close();
            }
          });
    }
    
    @Nullable
    public MediaType contentType() {
      return (this.contentType != null) ? MediaType.Companion.parse(this.contentType) : null;
    }
    
    public long contentLength() {
      return (this.contentLength != null) ? Util.toLongOrDefault(this.contentLength, -1L) : -1L;
    }
    
    @NotNull
    public BufferedSource source() {
      return this.bodySource;
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\004\n\002\020\"\n\002\b\t\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\027\020\016\032\0020\0132\006\020\n\032\0020\tH\000¢\006\004\b\f\020\rJ\037\020\022\032\0020\0172\006\020\020\032\0020\0172\006\020\021\032\0020\017H\002¢\006\004\b\022\020\023J%\020\032\032\0020\0312\006\020\025\032\0020\0242\006\020\026\032\0020\0172\006\020\030\032\0020\027¢\006\004\b\032\020\033J\021\020\034\032\0020\031*\0020\024¢\006\004\b\034\020\035J\031\020\037\032\b\022\004\022\0020\0060\036*\0020\017H\002¢\006\004\b\037\020 J\021\020\022\032\0020\017*\0020\024¢\006\004\b\022\020!R\024\020\"\032\0020\0138\002XT¢\006\006\n\004\b\"\020#R\024\020$\032\0020\0138\002XT¢\006\006\n\004\b$\020#R\024\020%\032\0020\0138\002XT¢\006\006\n\004\b%\020#R\024\020&\032\0020\0138\002XT¢\006\006\n\004\b&\020#¨\006'"}, d2 = {"Lokhttp3/Cache$Companion;", "", "<init>", "()V", "Lokhttp3/HttpUrl;", "url", "", "key", "(Lokhttp3/HttpUrl;)Ljava/lang/String;", "Lokio/BufferedSource;", "source", "", "readInt$okhttp", "(Lokio/BufferedSource;)I", "readInt", "Lokhttp3/Headers;", "requestHeaders", "responseHeaders", "varyHeaders", "(Lokhttp3/Headers;Lokhttp3/Headers;)Lokhttp3/Headers;", "Lokhttp3/Response;", "cachedResponse", "cachedRequest", "Lokhttp3/Request;", "newRequest", "", "varyMatches", "(Lokhttp3/Response;Lokhttp3/Headers;Lokhttp3/Request;)Z", "hasVaryAll", "(Lokhttp3/Response;)Z", "", "varyFields", "(Lokhttp3/Headers;)Ljava/util/Set;", "(Lokhttp3/Response;)Lokhttp3/Headers;", "ENTRY_BODY", "I", "ENTRY_COUNT", "ENTRY_METADATA", "VERSION", "okhttp"})
  @SourceDebugExtension({"SMAP\nCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cache.kt\nokhttp3/Cache$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,788:1\n2624#2,3:789\n*S KotlinDebug\n*F\n+ 1 Cache.kt\nokhttp3/Cache$Companion\n*L\n729#1:789,3\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final String key(@NotNull HttpUrl url) {
      Intrinsics.checkNotNullParameter(url, "url");
      return ByteString.Companion.encodeUtf8(url.toString()).md5().hex();
    }
    
    public final int readInt$okhttp(@NotNull BufferedSource source) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      try {
        long result = source.readDecimalLong();
        String line = source.readUtf8LineStrict();
        if (result < 0L || result > 2147483647L || ((((CharSequence)line).length() > 0)))
          throw new IOException("expected an int but was \"" + result + line + '"'); 
        return (int)result;
      } catch (NumberFormatException e) {
        throw new IOException(e.getMessage());
      } 
    }
    
    public final boolean varyMatches(@NotNull Response cachedResponse, @NotNull Headers cachedRequest, @NotNull Request newRequest) {
      // Byte code:
      //   0: aload_1
      //   1: ldc 'cachedResponse'
      //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   6: aload_2
      //   7: ldc 'cachedRequest'
      //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   12: aload_3
      //   13: ldc 'newRequest'
      //   15: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   18: aload_0
      //   19: aload_1
      //   20: invokevirtual headers : ()Lokhttp3/Headers;
      //   23: invokespecial varyFields : (Lokhttp3/Headers;)Ljava/util/Set;
      //   26: checkcast java/lang/Iterable
      //   29: astore #4
      //   31: iconst_0
      //   32: istore #5
      //   34: aload #4
      //   36: instanceof java/util/Collection
      //   39: ifeq -> 59
      //   42: aload #4
      //   44: checkcast java/util/Collection
      //   47: invokeinterface isEmpty : ()Z
      //   52: ifeq -> 59
      //   55: iconst_1
      //   56: goto -> 128
      //   59: aload #4
      //   61: invokeinterface iterator : ()Ljava/util/Iterator;
      //   66: astore #6
      //   68: aload #6
      //   70: invokeinterface hasNext : ()Z
      //   75: ifeq -> 127
      //   78: aload #6
      //   80: invokeinterface next : ()Ljava/lang/Object;
      //   85: astore #7
      //   87: aload #7
      //   89: checkcast java/lang/String
      //   92: astore #8
      //   94: iconst_0
      //   95: istore #9
      //   97: aload_2
      //   98: aload #8
      //   100: invokevirtual values : (Ljava/lang/String;)Ljava/util/List;
      //   103: aload_3
      //   104: aload #8
      //   106: invokevirtual headers : (Ljava/lang/String;)Ljava/util/List;
      //   109: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   112: ifne -> 119
      //   115: iconst_1
      //   116: goto -> 120
      //   119: iconst_0
      //   120: ifeq -> 68
      //   123: iconst_0
      //   124: goto -> 128
      //   127: iconst_1
      //   128: ireturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #729	-> 18
      //   #789	-> 34
      //   #790	-> 59
      //   #730	-> 97
      //   #790	-> 120
      //   #791	-> 127
      //   #729	-> 128
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   97	23	9	$i$a$-none-Cache$Companion$varyMatches$1	I
      //   94	26	8	it	Ljava/lang/String;
      //   87	40	7	element$iv	Ljava/lang/Object;
      //   34	94	5	$i$f$none	I
      //   31	97	4	$this$none$iv	Ljava/lang/Iterable;
      //   0	129	0	this	Lokhttp3/Cache$Companion;
      //   0	129	1	cachedResponse	Lokhttp3/Response;
      //   0	129	2	cachedRequest	Lokhttp3/Headers;
      //   0	129	3	newRequest	Lokhttp3/Request;
    }
    
    public final boolean hasVaryAll(@NotNull Response $this$hasVaryAll) {
      Intrinsics.checkNotNullParameter($this$hasVaryAll, "<this>");
      return varyFields($this$hasVaryAll.headers()).contains("*");
    }
    
    private final Set<String> varyFields(Headers $this$varyFields) {
      Set<String> result = null;
      for (int i = 0, j = $this$varyFields.size(); i < j; i++) {
        if (StringsKt.equals("Vary", $this$varyFields.name(i), true)) {
          String value = $this$varyFields.value(i);
          if (result == null)
            result = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE)); 
          char[] arrayOfChar = new char[1];
          arrayOfChar[0] = ',';
          for (String varyField : StringsKt.split$default(value, arrayOfChar, false, 0, 6, null))
            result.add(StringsKt.trim(varyField).toString()); 
        } 
      } 
      if (result == null);
      return SetsKt.emptySet();
    }
    
    @NotNull
    public final Headers varyHeaders(@NotNull Response $this$varyHeaders) {
      Intrinsics.checkNotNullParameter($this$varyHeaders, "<this>");
      Intrinsics.checkNotNull($this$varyHeaders.networkResponse());
      Headers requestHeaders = $this$varyHeaders.networkResponse().request().headers();
      Headers responseHeaders = $this$varyHeaders.headers();
      return varyHeaders(requestHeaders, responseHeaders);
    }
    
    private final Headers varyHeaders(Headers requestHeaders, Headers responseHeaders) {
      Set<String> varyFields = varyFields(responseHeaders);
      if (varyFields.isEmpty())
        return Util.EMPTY_HEADERS; 
      Headers.Builder result = new Headers.Builder();
      for (int i = 0, j = requestHeaders.size(); i < j; i++) {
        String fieldName = requestHeaders.name(i);
        if (varyFields.contains(fieldName))
          result.add(fieldName, requestHeaders.value(i)); 
      } 
      return result.build();
    }
  }
}
