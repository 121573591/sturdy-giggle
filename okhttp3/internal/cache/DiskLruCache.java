package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\001\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\020\013\n\002\b\005\n\002\020\016\n\002\b\006\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\007\n\002\020)\n\002\b\007\n\002\030\002\n\002\b\002\n\002\b\024\n\002\030\002\n\002\b\024*\001I\030\000 m2\0020\0012\0020\002:\004mnopB9\b\000\022\006\020\004\032\0020\003\022\006\020\006\032\0020\005\022\006\020\b\032\0020\007\022\006\020\t\032\0020\007\022\006\020\013\032\0020\n\022\006\020\r\032\0020\f¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\002¢\006\004\b\021\020\022J\017\020\023\032\0020\020H\026¢\006\004\b\023\020\022J#\020\032\032\0020\0202\n\020\025\032\0060\024R\0020\0002\006\020\027\032\0020\026H\000¢\006\004\b\030\020\031J\r\020\033\032\0020\020¢\006\004\b\033\020\022J'\020\037\032\b\030\0010\024R\0020\0002\006\020\035\032\0020\0342\b\b\002\020\036\032\0020\nH\007¢\006\004\b\037\020 J\r\020!\032\0020\020¢\006\004\b!\020\022J\017\020\"\032\0020\020H\026¢\006\004\b\"\020\022J\036\020$\032\b\030\0010#R\0020\0002\006\020\035\032\0020\034H\002¢\006\004\b$\020%J\r\020&\032\0020\020¢\006\004\b&\020\022J\r\020'\032\0020\026¢\006\004\b'\020(J\017\020)\032\0020\026H\002¢\006\004\b)\020(J\017\020+\032\0020*H\002¢\006\004\b+\020,J\017\020-\032\0020\020H\002¢\006\004\b-\020\022J\017\020.\032\0020\020H\002¢\006\004\b.\020\022J\027\0200\032\0020\0202\006\020/\032\0020\034H\002¢\006\004\b0\0201J\017\0203\032\0020\020H\000¢\006\004\b2\020\022J\025\0204\032\0020\0262\006\020\035\032\0020\034¢\006\004\b4\0205J\033\020:\032\0020\0262\n\0207\032\00606R\0020\000H\000¢\006\004\b8\0209J\017\020;\032\0020\026H\002¢\006\004\b;\020(J\r\020<\032\0020\n¢\006\004\b<\020=J\027\020?\032\f\022\b\022\0060#R\0020\0000>¢\006\004\b?\020@J\r\020A\032\0020\020¢\006\004\bA\020\022J\027\020B\032\0020\0202\006\020\035\032\0020\034H\002¢\006\004\bB\0201R\024\020\b\032\0020\0078\002X\004¢\006\006\n\004\b\b\020CR\026\020D\032\0020\0268\002@\002X\016¢\006\006\n\004\bD\020ER\024\020G\032\0020F8\002X\004¢\006\006\n\004\bG\020HR\024\020J\032\0020I8\002X\004¢\006\006\n\004\bJ\020KR\"\020L\032\0020\0268\000@\000X\016¢\006\022\n\004\bL\020E\032\004\bM\020(\"\004\bN\020OR\027\020\006\032\0020\0058\006¢\006\f\n\004\b\006\020P\032\004\bQ\020RR\032\020\004\032\0020\0038\000X\004¢\006\f\n\004\b\004\020S\032\004\bT\020UR\026\020V\032\0020\0268\002@\002X\016¢\006\006\n\004\bV\020ER\026\020W\032\0020\0268\002@\002X\016¢\006\006\n\004\bW\020ER\024\020X\032\0020\0058\002X\004¢\006\006\n\004\bX\020PR\024\020Y\032\0020\0058\002X\004¢\006\006\n\004\bY\020PR\024\020Z\032\0020\0058\002X\004¢\006\006\n\004\bZ\020PR\030\020[\032\004\030\0010*8\002@\002X\016¢\006\006\n\004\b[\020\\R*\020^\032\022\022\004\022\0020\034\022\b\022\00606R\0020\0000]8\000X\004¢\006\f\n\004\b^\020_\032\004\b`\020aR*\020\013\032\0020\n2\006\020b\032\0020\n8F@FX\016¢\006\022\n\004\b\013\020c\032\004\bd\020=\"\004\be\020fR\026\020g\032\0020\0268\002@\002X\016¢\006\006\n\004\bg\020ER\026\020h\032\0020\0268\002@\002X\016¢\006\006\n\004\bh\020ER\026\020i\032\0020\n8\002@\002X\016¢\006\006\n\004\bi\020cR\026\020j\032\0020\0078\002@\002X\016¢\006\006\n\004\bj\020CR\026\020<\032\0020\n8\002@\002X\016¢\006\006\n\004\b<\020cR\032\020\t\032\0020\0078\000X\004¢\006\f\n\004\b\t\020C\032\004\bk\020l¨\006q"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "Lokhttp3/internal/io/FileSystem;", "fileSystem", "Ljava/io/File;", "directory", "", "appVersion", "valueCount", "", "maxSize", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "<init>", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "", "checkNotClosed", "()V", "close", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "editor", "", "success", "completeEdit$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;Z)V", "completeEdit", "delete", "", "key", "expectedSequenceNumber", "edit", "(Ljava/lang/String;J)Lokhttp3/internal/cache/DiskLruCache$Editor;", "evictAll", "flush", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "get", "(Ljava/lang/String;)Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "()Z", "journalRebuildRequired", "Lokio/BufferedSink;", "newJournalWriter", "()Lokio/BufferedSink;", "processJournal", "readJournal", "line", "readJournalLine", "(Ljava/lang/String;)V", "rebuildJournal$okhttp", "rebuildJournal", "remove", "(Ljava/lang/String;)Z", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "entry", "removeEntry$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Entry;)Z", "removeEntry", "removeOldestEntry", "size", "()J", "", "snapshots", "()Ljava/util/Iterator;", "trimToSize", "validateKey", "I", "civilizedFileSystem", "Z", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "okhttp3/internal/cache/DiskLruCache.cleanupTask.1", "cleanupTask", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "closed", "getClosed$okhttp", "setClosed$okhttp", "(Z)V", "Ljava/io/File;", "getDirectory", "()Ljava/io/File;", "Lokhttp3/internal/io/FileSystem;", "getFileSystem$okhttp", "()Lokhttp3/internal/io/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "Ljava/util/LinkedHashMap;", "lruEntries", "Ljava/util/LinkedHashMap;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "J", "getMaxSize", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "getValueCount$okhttp", "()I", "Companion", "Editor", "Entry", "Snapshot", "okhttp"})
@SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokhttp3/internal/Util\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,1065:1\n1#2:1066\n608#3,4:1067\n37#4,2:1071\n37#4,2:1073\n*S KotlinDebug\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache\n*L\n215#1:1067,4\n672#1:1071,2\n721#1:1073,2\n*E\n"})
public final class DiskLruCache implements Closeable, Flushable {
  public DiskLruCache(@NotNull FileSystem fileSystem, @NotNull File directory, int appVersion, int valueCount, long maxSize, @NotNull TaskRunner taskRunner) {
    this.fileSystem = fileSystem;
    this.directory = directory;
    this.appVersion = appVersion;
    this.valueCount = valueCount;
    this.maxSize = maxSize;
    this.lruEntries = new LinkedHashMap<>(0, 0.75F, true);
    this.cleanupQueue = taskRunner.newQueue();
    String str = Util.okHttpName + " Cache";
    this.cleanupTask = new DiskLruCache$cleanupTask$1(str);
    if (!((maxSize > 0L) ? 1 : 0)) {
      int $i$a$-require-DiskLruCache$1 = 0;
      String str1 = "maxSize <= 0";
      throw new IllegalArgumentException(str1.toString());
    } 
    if (!((this.valueCount > 0) ? 1 : 0)) {
      int $i$a$-require-DiskLruCache$2 = 0;
      String str1 = "valueCount <= 0";
      throw new IllegalArgumentException(str1.toString());
    } 
    this.journalFile = new File(this.directory, JOURNAL_FILE);
    this.journalFileTmp = new File(this.directory, JOURNAL_FILE_TEMP);
    this.journalFileBackup = new File(this.directory, JOURNAL_FILE_BACKUP);
  }
  
  @NotNull
  public final FileSystem getFileSystem$okhttp() {
    return this.fileSystem;
  }
  
  @NotNull
  public final File getDirectory() {
    return this.directory;
  }
  
  public final int getValueCount$okhttp() {
    return this.valueCount;
  }
  
  public final synchronized long getMaxSize() {
    return this.maxSize;
  }
  
  public final synchronized void setMaxSize(long value) {
    this.maxSize = value;
    if (this.initialized)
      TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); 
  }
  
  @NotNull
  public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
    return this.lruEntries;
  }
  
  public final boolean getClosed$okhttp() {
    return this.closed;
  }
  
  public final void setClosed$okhttp(boolean <set-?>) {
    this.closed = <set-?>;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\021\n\000\n\002\030\002\n\002\020\t\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/cache/DiskLruCache.cleanupTask.1", "Lokhttp3/internal/concurrent/Task;", "", "runOnce", "()J", "okhttp"})
  public static final class DiskLruCache$cleanupTask$1 extends Task {
    DiskLruCache$cleanupTask$1(String $super_call_param$1) {
      super($super_call_param$1, false, 2, null);
    }
    
    public long runOnce() {
      DiskLruCache diskLruCache1 = DiskLruCache.this, diskLruCache2 = DiskLruCache.this;
      synchronized (diskLruCache1) {
        int $i$a$-synchronized-DiskLruCache$cleanupTask$1$runOnce$1 = 0;
        if (!diskLruCache2.initialized || diskLruCache2.getClosed$okhttp())
          return -1L; 
        try {
          diskLruCache2.trimToSize();
        } catch (IOException _) {
          diskLruCache2.mostRecentTrimFailed = true;
        } 
        try {
          if (diskLruCache2.journalRebuildRequired()) {
            diskLruCache2.rebuildJournal$okhttp();
            diskLruCache2.redundantOpCount = 0;
          } 
        } catch (IOException _) {
          diskLruCache2.mostRecentRebuildFailed = true;
          diskLruCache2.journalWriter = Okio.buffer(Okio.blackhole());
        } 
        return -1L;
      } 
    }
  }
  
  public final synchronized void initialize() throws IOException {
    Object $this$assertThreadHoldsLock$iv = this;
    int $i$f$assertThreadHoldsLock = 0;
    if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
    if (this.initialized)
      return; 
    if (this.fileSystem.exists(this.journalFileBackup))
      if (this.fileSystem.exists(this.journalFile)) {
        this.fileSystem.delete(this.journalFileBackup);
      } else {
        this.fileSystem.rename(this.journalFileBackup, this.journalFile);
      }  
    this.civilizedFileSystem = Util.isCivilized(this.fileSystem, this.journalFileBackup);
    if (this.fileSystem.exists(this.journalFile))
      try {
        readJournal();
        processJournal();
        this.initialized = true;
        return;
      } catch (IOException journalIsCorrupt) {
        Platform.Companion.get().log("DiskLruCache " + this.directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing", 5, journalIsCorrupt);
        try {
          delete();
        } finally {
          this.closed = false;
        } 
      }  
    rebuildJournal$okhttp();
    this.initialized = true;
  }
  
  private final void readJournal() throws IOException {
    Closeable closeable = (Closeable)Okio.buffer(this.fileSystem.source(this.journalFile));
    Throwable throwable = null;
    try {
      BufferedSource source = (BufferedSource)closeable;
      int $i$a$-use-DiskLruCache$readJournal$1 = 0;
      String magic = source.readUtf8LineStrict();
      String version = source.readUtf8LineStrict();
      String appVersionString = source.readUtf8LineStrict();
      String valueCountString = source.readUtf8LineStrict();
      String blank = source.readUtf8LineStrict();
      if (!Intrinsics.areEqual(MAGIC, magic) || !Intrinsics.areEqual(VERSION_1, version) || !Intrinsics.areEqual(String.valueOf(this.appVersion), appVersionString) || !Intrinsics.areEqual(String.valueOf(this.valueCount), valueCountString) || ((blank.length() > 0)))
        throw new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']'); 
      int lineCount = 0;
      while (true) {
        try {
          readJournalLine(source.readUtf8LineStrict());
          lineCount++;
        } catch (EOFException _) {
          break;
        } 
      } 
      this.redundantOpCount = lineCount - this.lruEntries.size();
      if (!source.exhausted()) {
        rebuildJournal$okhttp();
      } else {
        this.journalWriter = newJournalWriter();
      } 
      Unit unit = Unit.INSTANCE;
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
  }
  
  private final BufferedSink newJournalWriter() throws FileNotFoundException {
    Sink fileSink = this.fileSystem.appendingSink(this.journalFile);
    FaultHidingSink faultHidingSink = new FaultHidingSink(fileSink, new DiskLruCache$newJournalWriter$faultHidingSink$1());
    return Okio.buffer((Sink)faultHidingSink);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\016\n\002\030\002\n\000\n\002\020\002\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Ljava/io/IOException;", "it", "", "invoke", "(Ljava/io/IOException;)V", "<anonymous>"})
  @SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$newJournalWriter$faultHidingSink$1\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,1065:1\n608#2,4:1066\n*S KotlinDebug\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$newJournalWriter$faultHidingSink$1\n*L\n304#1:1066,4\n*E\n"})
  static final class DiskLruCache$newJournalWriter$faultHidingSink$1 extends Lambda implements Function1<IOException, Unit> {
    public final void invoke(@NotNull IOException it) {
      Intrinsics.checkNotNullParameter(it, "it");
      Object $this$assertThreadHoldsLock$iv = DiskLruCache.this;
      int $i$f$assertThreadHoldsLock = 0;
      if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
      DiskLruCache.this.hasJournalErrors = true;
    }
    
    DiskLruCache$newJournalWriter$faultHidingSink$1() {
      super(1);
    }
  }
  
  private final void readJournalLine(String line) throws IOException {
    int firstSpace = StringsKt.indexOf$default(line, ' ', 0, false, 6, null);
    if (firstSpace == -1)
      throw new IOException("unexpected journal line: " + line); 
    int keyBegin = firstSpace + 1;
    int secondSpace = StringsKt.indexOf$default(line, ' ', keyBegin, false, 4, null);
    String key = null;
    if (secondSpace == -1) {
      Intrinsics.checkNotNullExpressionValue(line.substring(keyBegin), "this as java.lang.String).substring(startIndex)");
      key = line.substring(keyBegin);
      if (firstSpace == REMOVE.length() && StringsKt.startsWith$default(line, REMOVE, false, 2, null)) {
        this.lruEntries.remove(key);
        return;
      } 
    } else {
      Intrinsics.checkNotNullExpressionValue(line.substring(keyBegin, secondSpace), "this as java.lang.String…ing(startIndex, endIndex)");
      key = line.substring(keyBegin, secondSpace);
    } 
    Entry entry = this.lruEntries.get(key);
    if (entry == null) {
      entry = new Entry(key);
      this.lruEntries.put(key, entry);
    } 
    if (secondSpace != -1 && firstSpace == CLEAN.length() && StringsKt.startsWith$default(line, CLEAN, false, 2, null)) {
      Intrinsics.checkNotNullExpressionValue(line.substring(secondSpace + 1), "this as java.lang.String).substring(startIndex)");
      char[] arrayOfChar = new char[1];
      arrayOfChar[0] = ' ';
      List<String> parts = StringsKt.split$default(line.substring(secondSpace + 1), arrayOfChar, false, 0, 6, null);
      entry.setReadable$okhttp(true);
      entry.setCurrentEditor$okhttp(null);
      entry.setLengths$okhttp(parts);
    } else if (secondSpace == -1 && firstSpace == DIRTY.length() && StringsKt.startsWith$default(line, DIRTY, false, 2, null)) {
      entry.setCurrentEditor$okhttp(new Editor(entry));
    } else if (secondSpace != -1 || firstSpace != READ.length() || !StringsKt.startsWith$default(line, READ, false, 2, null)) {
      throw new IOException("unexpected journal line: " + line);
    } 
  }
  
  private final void processJournal() throws IOException {
    this.fileSystem.delete(this.journalFileTmp);
    Iterator i = this.lruEntries.values().iterator();
    while (i.hasNext()) {
      Intrinsics.checkNotNullExpressionValue(i.next(), "i.next()");
      Entry entry = (Entry)i.next();
      if (entry.getCurrentEditor$okhttp() == null) {
        for (int k = 0, m = this.valueCount; k < m; k++)
          this.size += entry.getLengths$okhttp()[k]; 
        continue;
      } 
      entry.setCurrentEditor$okhttp(null);
      for (int t = 0, j = this.valueCount; t < j; t++) {
        this.fileSystem.delete(entry.getCleanFiles$okhttp().get(t));
        this.fileSystem.delete(entry.getDirtyFiles$okhttp().get(t));
      } 
      i.remove();
    } 
  }
  
  public final synchronized void rebuildJournal$okhttp() throws IOException {
    if (this.journalWriter != null) {
      this.journalWriter.close();
    } else {
    
    } 
    Closeable closeable = (Closeable)Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
    Throwable throwable = null;
    try {
      BufferedSink sink = (BufferedSink)closeable;
      int $i$a$-use-DiskLruCache$rebuildJournal$1 = 0;
      sink.writeUtf8(MAGIC).writeByte(10);
      sink.writeUtf8(VERSION_1).writeByte(10);
      sink.writeDecimalLong(this.appVersion).writeByte(10);
      sink.writeDecimalLong(this.valueCount).writeByte(10);
      sink.writeByte(10);
      for (Entry entry : this.lruEntries.values()) {
        if (entry.getCurrentEditor$okhttp() != null) {
          sink.writeUtf8(DIRTY).writeByte(32);
          sink.writeUtf8(entry.getKey$okhttp());
          sink.writeByte(10);
          continue;
        } 
        sink.writeUtf8(CLEAN).writeByte(32);
        sink.writeUtf8(entry.getKey$okhttp());
        entry.writeLengths$okhttp(sink);
        sink.writeByte(10);
      } 
      Unit unit = Unit.INSTANCE;
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
    if (this.fileSystem.exists(this.journalFile))
      this.fileSystem.rename(this.journalFile, this.journalFileBackup); 
    this.fileSystem.rename(this.journalFileTmp, this.journalFile);
    this.fileSystem.delete(this.journalFileBackup);
    this.journalWriter = newJournalWriter();
    this.hasJournalErrors = false;
    this.mostRecentRebuildFailed = false;
  }
  
  @Nullable
  public final synchronized Snapshot get(@NotNull String key) throws IOException {
    Entry entry;
    Snapshot snapshot;
    Intrinsics.checkNotNullParameter(key, "key");
    initialize();
    checkNotClosed();
    validateKey(key);
    if ((Entry)this.lruEntries.get(key) == null) {
      (Entry)this.lruEntries.get(key);
      return null;
    } 
    if (entry.snapshot$okhttp() == null) {
      entry.snapshot$okhttp();
      return null;
    } 
    int i = this.redundantOpCount;
    this.redundantOpCount = i + 1;
    Intrinsics.checkNotNull(this.journalWriter);
    this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(key).writeByte(10);
    if (journalRebuildRequired())
      TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); 
    return snapshot;
  }
  
  @JvmOverloads
  @Nullable
  public final synchronized Editor edit(@NotNull String key, long expectedSequenceNumber) throws IOException {
    Intrinsics.checkNotNullParameter(key, "key");
    initialize();
    checkNotClosed();
    validateKey(key);
    Entry entry = this.lruEntries.get(key);
    if (expectedSequenceNumber != ANY_SEQUENCE_NUMBER && (entry == null || entry.getSequenceNumber$okhttp() != expectedSequenceNumber))
      return null; 
    if (((entry != null) ? entry.getCurrentEditor$okhttp() : null) != null)
      return null; 
    if (entry != null && entry.getLockingSourceCount$okhttp() != 0)
      return null; 
    if (this.mostRecentTrimFailed || this.mostRecentRebuildFailed) {
      TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
      return null;
    } 
    Intrinsics.checkNotNull(this.journalWriter);
    BufferedSink journalWriter = this.journalWriter;
    journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(key).writeByte(10);
    journalWriter.flush();
    if (this.hasJournalErrors)
      return null; 
    if (entry == null) {
      entry = new Entry(key);
      this.lruEntries.put(key, entry);
    } 
    Editor editor = new Editor(entry);
    entry.setCurrentEditor$okhttp(editor);
    return editor;
  }
  
  public final synchronized long size() throws IOException {
    initialize();
    return this.size;
  }
  
  public final synchronized void completeEdit$okhttp(@NotNull Editor editor, boolean success) throws IOException {
    Intrinsics.checkNotNullParameter(editor, "editor");
    Entry entry = editor.getEntry$okhttp();
    if (!Intrinsics.areEqual(entry.getCurrentEditor$okhttp(), editor)) {
      String str = "Check failed.";
      throw new IllegalStateException(str.toString());
    } 
    if (success && !entry.getReadable$okhttp())
      for (int k = 0, m = this.valueCount; k < m; k++) {
        Intrinsics.checkNotNull(editor.getWritten$okhttp());
        if (!editor.getWritten$okhttp()[k]) {
          editor.abort();
          throw new IllegalStateException("Newly created entry didn't create value for index " + k);
        } 
        if (!this.fileSystem.exists(entry.getDirtyFiles$okhttp().get(k))) {
          editor.abort();
          return;
        } 
      }  
    int i, j;
    for (i = 0, j = this.valueCount; i < j; i++) {
      File dirty = entry.getDirtyFiles$okhttp().get(i);
      if (success && !entry.getZombie$okhttp()) {
        if (this.fileSystem.exists(dirty)) {
          File clean = entry.getCleanFiles$okhttp().get(i);
          this.fileSystem.rename(dirty, clean);
          long oldLength = entry.getLengths$okhttp()[i];
          long newLength = this.fileSystem.size(clean);
          entry.getLengths$okhttp()[i] = newLength;
          this.size = this.size - oldLength + newLength;
        } 
      } else {
        this.fileSystem.delete(dirty);
      } 
    } 
    entry.setCurrentEditor$okhttp(null);
    if (entry.getZombie$okhttp()) {
      removeEntry$okhttp(entry);
      return;
    } 
    i = this.redundantOpCount;
    this.redundantOpCount = i + 1;
    Intrinsics.checkNotNull(this.journalWriter);
    BufferedSink bufferedSink1 = this.journalWriter, $this$completeEdit_u24lambda_u244 = bufferedSink1;
    int $i$a$-apply-DiskLruCache$completeEdit$1 = 0;
    if (entry.getReadable$okhttp() || success) {
      entry.setReadable$okhttp(true);
      $this$completeEdit_u24lambda_u244.writeUtf8(CLEAN).writeByte(32);
      $this$completeEdit_u24lambda_u244.writeUtf8(entry.getKey$okhttp());
      entry.writeLengths$okhttp($this$completeEdit_u24lambda_u244);
      $this$completeEdit_u24lambda_u244.writeByte(10);
      if (success) {
        long l = this.nextSequenceNumber;
        this.nextSequenceNumber = l + 1L;
        entry.setSequenceNumber$okhttp(l);
      } 
    } else {
      this.lruEntries.remove(entry.getKey$okhttp());
      $this$completeEdit_u24lambda_u244.writeUtf8(REMOVE).writeByte(32);
      $this$completeEdit_u24lambda_u244.writeUtf8(entry.getKey$okhttp());
      $this$completeEdit_u24lambda_u244.writeByte(10);
    } 
    $this$completeEdit_u24lambda_u244.flush();
    if (this.size > this.maxSize || journalRebuildRequired())
      TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); 
  }
  
  private final boolean journalRebuildRequired() {
    int redundantOpCompactThreshold = 2000;
    return (this.redundantOpCount >= redundantOpCompactThreshold && this.redundantOpCount >= this.lruEntries.size());
  }
  
  public final synchronized boolean remove(@NotNull String key) throws IOException {
    Entry entry;
    Intrinsics.checkNotNullParameter(key, "key");
    initialize();
    checkNotClosed();
    validateKey(key);
    if ((Entry)this.lruEntries.get(key) == null) {
      (Entry)this.lruEntries.get(key);
      return false;
    } 
    boolean removed = removeEntry$okhttp(entry);
    if (removed && this.size <= this.maxSize)
      this.mostRecentTrimFailed = false; 
    return removed;
  }
  
  public final boolean removeEntry$okhttp(@NotNull Entry entry) throws IOException {
    Intrinsics.checkNotNullParameter(entry, "entry");
    if (!this.civilizedFileSystem) {
      if (entry.getLockingSourceCount$okhttp() > 0) {
        BufferedSink it = this.journalWriter;
        int $i$a$-let-DiskLruCache$removeEntry$1 = 0;
        it.writeUtf8(DIRTY);
        it.writeByte(32);
        it.writeUtf8(entry.getKey$okhttp());
        it.writeByte(10);
        it.flush();
      } 
      if (entry.getLockingSourceCount$okhttp() > 0 || entry.getCurrentEditor$okhttp() != null) {
        entry.setZombie$okhttp(true);
        return true;
      } 
    } 
    if (entry.getCurrentEditor$okhttp() != null) {
      entry.getCurrentEditor$okhttp().detach$okhttp();
    } else {
      entry.getCurrentEditor$okhttp();
    } 
    int i, j;
    for (i = 0, j = this.valueCount; i < j; i++) {
      this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i));
      this.size -= entry.getLengths$okhttp()[i];
      entry.getLengths$okhttp()[i] = 0L;
    } 
    i = this.redundantOpCount;
    this.redundantOpCount = i + 1;
    if (this.journalWriter != null) {
      BufferedSink it = this.journalWriter;
      int $i$a$-let-DiskLruCache$removeEntry$2 = 0;
      it.writeUtf8(REMOVE);
      it.writeByte(32);
      it.writeUtf8(entry.getKey$okhttp());
      it.writeByte(10);
    } else {
    
    } 
    this.lruEntries.remove(entry.getKey$okhttp());
    if (journalRebuildRequired())
      TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); 
    return true;
  }
  
  private final synchronized void checkNotClosed() {
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-DiskLruCache$checkNotClosed$1 = 0;
      String str = "cache is closed";
      throw new IllegalStateException(str.toString());
    } 
  }
  
  public synchronized void flush() throws IOException {
    if (!this.initialized)
      return; 
    checkNotClosed();
    trimToSize();
    Intrinsics.checkNotNull(this.journalWriter);
    this.journalWriter.flush();
  }
  
  public final synchronized boolean isClosed() {
    return this.closed;
  }
  
  public synchronized void close() throws IOException {
    if (!this.initialized || this.closed) {
      this.closed = true;
      return;
    } 
    Intrinsics.checkNotNullExpressionValue(this.lruEntries.values(), "lruEntries.values");
    Collection<Entry> $this$toTypedArray$iv = this.lruEntries.values();
    int $i$f$toTypedArray = 0;
    Collection<Entry> thisCollection$iv = $this$toTypedArray$iv;
    Entry[] arrayOfEntry;
    byte b;
    for (arrayOfEntry = thisCollection$iv.<Entry>toArray(new Entry[0]), b = 0, $i$f$toTypedArray = arrayOfEntry.length; b < $i$f$toTypedArray; ) {
      Entry entry = arrayOfEntry[b];
      if (entry.getCurrentEditor$okhttp() != null)
        if (entry.getCurrentEditor$okhttp() != null) {
          entry.getCurrentEditor$okhttp().detach$okhttp();
        } else {
          entry.getCurrentEditor$okhttp();
        }  
      b++;
    } 
    trimToSize();
    Intrinsics.checkNotNull(this.journalWriter);
    this.journalWriter.close();
    this.journalWriter = null;
    this.closed = true;
  }
  
  public final void trimToSize() throws IOException {
    while (this.size > this.maxSize) {
      if (!removeOldestEntry())
        return; 
    } 
    this.mostRecentTrimFailed = false;
  }
  
  private final boolean removeOldestEntry() {
    for (Entry toEvict : this.lruEntries.values()) {
      if (!toEvict.getZombie$okhttp()) {
        Intrinsics.checkNotNullExpressionValue(toEvict, "toEvict");
        removeEntry$okhttp(toEvict);
        return true;
      } 
    } 
    return false;
  }
  
  public final void delete() throws IOException {
    close();
    this.fileSystem.deleteContents(this.directory);
  }
  
  public final synchronized void evictAll() throws IOException {
    initialize();
    Intrinsics.checkNotNullExpressionValue(this.lruEntries.values(), "lruEntries.values");
    Collection<Entry> $this$toTypedArray$iv = this.lruEntries.values();
    int $i$f$toTypedArray = 0;
    Collection<Entry> thisCollection$iv = $this$toTypedArray$iv;
    Entry[] arrayOfEntry;
    byte b;
    for (arrayOfEntry = thisCollection$iv.<Entry>toArray(new Entry[0]), b = 0, $i$f$toTypedArray = arrayOfEntry.length; b < $i$f$toTypedArray; ) {
      Entry entry = arrayOfEntry[b];
      Intrinsics.checkNotNullExpressionValue(entry, "entry");
      removeEntry$okhttp(entry);
      b++;
    } 
    this.mostRecentTrimFailed = false;
  }
  
  private final void validateKey(String key) {
    if (!LEGAL_KEY_PATTERN.matches(key)) {
      int $i$a$-require-DiskLruCache$validateKey$1 = 0;
      String str = "keys must match regex [a-z0-9_-]{1,120}: \"" + key + '"';
      throw new IllegalArgumentException(str.toString());
    } 
  }
  
  @NotNull
  public final synchronized Iterator<Snapshot> snapshots() throws IOException {
    initialize();
    return new DiskLruCache$snapshots$1();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000)\n\000\n\002\020)\n\002\030\002\n\002\030\002\n\002\020\013\n\002\b\004\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\007*\001\000\b\n\030\0002\f\022\b\022\0060\002R\0020\0030\001J\020\020\005\032\0020\004H\002¢\006\004\b\005\020\006J\024\020\007\032\0060\002R\0020\003H\002¢\006\004\b\007\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013R*\020\016\032\030\022\024\022\022 \r*\b\030\0010\fR\0020\0030\fR\0020\0030\0018\002X\004¢\006\006\n\004\b\016\020\017R\034\020\020\032\b\030\0010\002R\0020\0038\002@\002X\016¢\006\006\n\004\b\020\020\021R\034\020\022\032\b\030\0010\002R\0020\0038\002@\002X\016¢\006\006\n\004\b\022\020\021¨\006\023"}, d2 = {"okhttp3/internal/cache/DiskLruCache.snapshots.1", "", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "", "hasNext", "()Z", "next", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "", "remove", "()V", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "kotlin.jvm.PlatformType", "delegate", "Ljava/util/Iterator;", "nextSnapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "removeSnapshot", "okhttp"})
  @SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$snapshots$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1065:1\n1#2:1066\n*E\n"})
  public static final class DiskLruCache$snapshots$1 implements Iterator<Snapshot>, KMutableIterator {
    @NotNull
    private final Iterator<DiskLruCache.Entry> delegate;
    
    @Nullable
    private DiskLruCache.Snapshot nextSnapshot;
    
    @Nullable
    private DiskLruCache.Snapshot removeSnapshot;
    
    DiskLruCache$snapshots$1() {
      Intrinsics.checkNotNullExpressionValue((new ArrayList($receiver.getLruEntries$okhttp().values())).iterator(), "ArrayList(lruEntries.values).iterator()");
      this.delegate = (Iterator)(new ArrayList($receiver.getLruEntries$okhttp().values())).iterator();
    }
    
    public boolean hasNext() {
      if (this.nextSnapshot != null)
        return true; 
      DiskLruCache diskLruCache1 = DiskLruCache.this, diskLruCache2 = DiskLruCache.this;
      synchronized (diskLruCache1) {
        int $i$a$-synchronized-DiskLruCache$snapshots$1$hasNext$1 = 0;
        if (diskLruCache2.getClosed$okhttp())
          return false; 
        while (this.delegate.hasNext()) {
          if ((DiskLruCache.Entry)this.delegate.next() == null || ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp() == null) {
            ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp();
            ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp();
            continue;
          } 
          ((DiskLruCache$snapshots$1)((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp()).nextSnapshot = ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp();
          return true;
        } 
        Unit unit = Unit.INSTANCE;
      } 
      return false;
    }
    
    @NotNull
    public DiskLruCache.Snapshot next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      this.removeSnapshot = this.nextSnapshot;
      this.nextSnapshot = null;
      Intrinsics.checkNotNull(this.removeSnapshot);
      return this.removeSnapshot;
    }
    
    public void remove() {
      DiskLruCache.Snapshot removeSnapshot = this.removeSnapshot;
      if (removeSnapshot == null) {
        int $i$a$-checkNotNull-DiskLruCache$snapshots$1$remove$1 = 0;
        String str = "remove() before next()";
        throw new IllegalStateException(str.toString());
      } 
      try {
        DiskLruCache.this.remove(removeSnapshot.key());
      } catch (IOException iOException) {
      
      } finally {
        this.removeSnapshot = null;
      } 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\030\002\n\002\020\016\n\000\n\002\020\t\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\026\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\013\b\004\030\0002\0020\001B/\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\f\020\b\032\b\022\004\022\0020\0070\006\022\006\020\n\032\0020\t¢\006\004\b\013\020\fJ\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017J\023\020\022\032\b\030\0010\020R\0020\021¢\006\004\b\022\020\023J\025\020\026\032\0020\0042\006\020\025\032\0020\024¢\006\004\b\026\020\027J\025\020\030\032\0020\0072\006\020\025\032\0020\024¢\006\004\b\030\020\031J\r\020\003\032\0020\002¢\006\004\b\003\020\032R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\033R\024\020\n\032\0020\t8\002X\004¢\006\006\n\004\b\n\020\034R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020\035R\032\020\b\032\b\022\004\022\0020\0070\0068\002X\004¢\006\006\n\004\b\b\020\036¨\006\037"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "", "key", "", "sequenceNumber", "", "Lokio/Source;", "sources", "", "lengths", "<init>", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "", "close", "()V", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "edit", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "index", "getLength", "(I)J", "getSource", "(I)Lokio/Source;", "()Ljava/lang/String;", "Ljava/lang/String;", "[J", "J", "Ljava/util/List;", "okhttp"})
  public final class Snapshot implements Closeable {
    @NotNull
    private final String key;
    
    private final long sequenceNumber;
    
    @NotNull
    private final List<Source> sources;
    
    @NotNull
    private final long[] lengths;
    
    public Snapshot(String key, @NotNull long sequenceNumber, @NotNull List<Source> sources, long[] lengths) {
      this.key = key;
      this.sequenceNumber = sequenceNumber;
      this.sources = sources;
      this.lengths = lengths;
    }
    
    @NotNull
    public final String key() {
      return this.key;
    }
    
    @Nullable
    public final DiskLruCache.Editor edit() throws IOException {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }
    
    @NotNull
    public final Source getSource(int index) {
      return this.sources.get(index);
    }
    
    public final long getLength(int index) {
      return this.lengths[index];
    }
    
    public void close() {
      for (Source source : this.sources)
        Util.closeQuietly((Closeable)source); 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\020\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\005\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\005\n\002\020\030\n\002\b\005\b\004\030\0002\0020\001B\025\b\000\022\n\020\004\032\0060\002R\0020\003¢\006\004\b\005\020\006J\r\020\b\032\0020\007¢\006\004\b\b\020\tJ\r\020\n\032\0020\007¢\006\004\b\n\020\tJ\017\020\f\032\0020\007H\000¢\006\004\b\013\020\tJ\025\020\020\032\0020\0172\006\020\016\032\0020\r¢\006\004\b\020\020\021J\027\020\023\032\004\030\0010\0222\006\020\016\032\0020\r¢\006\004\b\023\020\024R\026\020\026\032\0020\0258\002@\002X\016¢\006\006\n\004\b\026\020\027R\036\020\004\032\0060\002R\0020\0038\000X\004¢\006\f\n\004\b\004\020\030\032\004\b\031\020\032R\034\020\034\032\004\030\0010\0338\000X\004¢\006\f\n\004\b\034\020\035\032\004\b\036\020\037¨\006 "}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "entry", "<init>", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "", "abort", "()V", "commit", "detach$okhttp", "detach", "", "index", "Lokio/Sink;", "newSink", "(I)Lokio/Sink;", "Lokio/Source;", "newSource", "(I)Lokio/Source;", "", "done", "Z", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "written", "[Z", "getWritten$okhttp", "()[Z", "okhttp"})
  public final class Editor {
    @NotNull
    private final DiskLruCache.Entry entry;
    
    @Nullable
    private final boolean[] written;
    
    private boolean done;
    
    public Editor(DiskLruCache.Entry entry) {
      this.entry = entry;
      this.written = this.entry.getReadable$okhttp() ? null : new boolean[DiskLruCache.this.getValueCount$okhttp()];
    }
    
    @NotNull
    public final DiskLruCache.Entry getEntry$okhttp() {
      return this.entry;
    }
    
    @Nullable
    public final boolean[] getWritten$okhttp() {
      return this.written;
    }
    
    public final void detach$okhttp() {
      if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
        if (DiskLruCache.this.civilizedFileSystem) {
          DiskLruCache.this.completeEdit$okhttp(this, false);
        } else {
          this.entry.setZombie$okhttp(true);
        }  
    }
    
    @Nullable
    public final Source newSource(int index) {
      DiskLruCache diskLruCache1 = DiskLruCache.this, diskLruCache2 = DiskLruCache.this;
      synchronized (diskLruCache1) {
        Source source;
        int $i$a$-synchronized-DiskLruCache$Editor$newSource$1 = 0;
        if (!(!this.done ? 1 : 0)) {
          String str = "Check failed.";
          throw new IllegalStateException(str.toString());
        } 
        if (!this.entry.getReadable$okhttp() || !Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) || this.entry.getZombie$okhttp())
          return null; 
        try {
          source = diskLruCache2.getFileSystem$okhttp().source(this.entry.getCleanFiles$okhttp().get(index));
        } catch (FileNotFoundException _) {
          source = null;
        } 
        return source;
      } 
    }
    
    @NotNull
    public final Sink newSink(int index) {
      DiskLruCache diskLruCache1 = DiskLruCache.this, diskLruCache2 = DiskLruCache.this;
      synchronized (diskLruCache1) {
        int $i$a$-synchronized-DiskLruCache$Editor$newSink$1 = 0;
        if (!(!this.done ? 1 : 0)) {
          String str = "Check failed.";
          throw new IllegalStateException(str.toString());
        } 
        if (!Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
          return Okio.blackhole(); 
        if (!this.entry.getReadable$okhttp()) {
          Intrinsics.checkNotNull(this.written);
          this.written[index] = true;
        } 
        File dirtyFile = this.entry.getDirtyFiles$okhttp().get(index);
        Sink sink = null;
        try {
          sink = diskLruCache2.getFileSystem$okhttp().sink(dirtyFile);
        } catch (FileNotFoundException _) {
          return Okio.blackhole();
        } 
        return (Sink)new FaultHidingSink(sink, new DiskLruCache$Editor$newSink$1$1(this));
      } 
    }
    
    @Metadata(mv = {1, 8, 0}, k = 3, xi = 48, d1 = {"\000\016\n\002\030\002\n\000\n\002\020\002\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Ljava/io/IOException;", "it", "", "invoke", "(Ljava/io/IOException;)V", "<anonymous>"})
    static final class DiskLruCache$Editor$newSink$1$1 extends Lambda implements Function1<IOException, Unit> {
      public final void invoke(@NotNull IOException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        DiskLruCache diskLruCache = this.this$0;
        DiskLruCache.Editor editor = DiskLruCache.Editor.this;
        synchronized (diskLruCache) {
          int $i$a$-synchronized-DiskLruCache$Editor$newSink$1$1$1 = 0;
          editor.detach$okhttp();
          Unit unit = Unit.INSTANCE;
        } 
      }
      
      DiskLruCache$Editor$newSink$1$1(DiskLruCache.Editor editor) {
        super(1);
      }
    }
    
    public final void commit() throws IOException {
      DiskLruCache diskLruCache1 = DiskLruCache.this, diskLruCache2 = DiskLruCache.this;
      synchronized (diskLruCache1) {
        int $i$a$-synchronized-DiskLruCache$Editor$commit$1 = 0;
        if (!(!this.done ? 1 : 0)) {
          String str = "Check failed.";
          throw new IllegalStateException(str.toString());
        } 
        if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
          diskLruCache2.completeEdit$okhttp(this, true); 
        this.done = true;
        Unit unit = Unit.INSTANCE;
      } 
    }
    
    public final void abort() throws IOException {
      DiskLruCache diskLruCache1 = DiskLruCache.this, diskLruCache2 = DiskLruCache.this;
      synchronized (diskLruCache1) {
        int $i$a$-synchronized-DiskLruCache$Editor$abort$1 = 0;
        if (!(!this.done ? 1 : 0)) {
          String str = "Check failed.";
          throw new IllegalStateException(str.toString());
        } 
        if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
          diskLruCache2.completeEdit$okhttp(this, false); 
        this.done = true;
        Unit unit = Unit.INSTANCE;
      } 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000t\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\003\n\002\020 \n\000\n\002\020\001\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020!\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\013\n\002\020\026\n\002\b\n\n\002\020\013\n\002\b\006\n\002\020\t\n\002\b\n\b\004\030\0002\0020\001B\021\b\000\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\035\020\t\032\0020\b2\f\020\007\032\b\022\004\022\0020\0020\006H\002¢\006\004\b\t\020\nJ\027\020\016\032\0020\r2\006\020\f\032\0020\013H\002¢\006\004\b\016\020\017J\035\020\023\032\0020\0202\f\020\007\032\b\022\004\022\0020\0020\006H\000¢\006\004\b\021\020\022J\025\020\030\032\b\030\0010\024R\0020\025H\000¢\006\004\b\026\020\027J\027\020\035\032\0020\0202\006\020\032\032\0020\031H\000¢\006\004\b\033\020\034R \020 \032\b\022\004\022\0020\0370\0368\000X\004¢\006\f\n\004\b \020!\032\004\b\"\020#R(\020%\032\b\030\0010$R\0020\0258\000@\000X\016¢\006\022\n\004\b%\020&\032\004\b'\020(\"\004\b)\020*R \020+\032\b\022\004\022\0020\0370\0368\000X\004¢\006\f\n\004\b+\020!\032\004\b,\020#R\032\020\003\032\0020\0028\000X\004¢\006\f\n\004\b\003\020-\032\004\b.\020/R\032\0201\032\002008\000X\004¢\006\f\n\004\b1\0202\032\004\b3\0204R\"\0205\032\0020\0138\000@\000X\016¢\006\022\n\004\b5\0206\032\004\b7\0208\"\004\b9\020:R\"\020<\032\0020;8\000@\000X\016¢\006\022\n\004\b<\020=\032\004\b>\020?\"\004\b@\020AR\"\020C\032\0020B8\000@\000X\016¢\006\022\n\004\bC\020D\032\004\bE\020F\"\004\bG\020HR\"\020I\032\0020;8\000@\000X\016¢\006\022\n\004\bI\020=\032\004\bJ\020?\"\004\bK\020A¨\006L"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "", "key", "<init>", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "", "strings", "", "invalidLengths", "(Ljava/util/List;)Ljava/lang/Void;", "", "index", "Lokio/Source;", "newSource", "(I)Lokio/Source;", "", "setLengths$okhttp", "(Ljava/util/List;)V", "setLengths", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "snapshot$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot", "Lokio/BufferedSink;", "writer", "writeLengths$okhttp", "(Lokio/BufferedSink;)V", "writeLengths", "", "Ljava/io/File;", "cleanFiles", "Ljava/util/List;", "getCleanFiles$okhttp", "()Ljava/util/List;", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "Ljava/lang/String;", "getKey$okhttp", "()Ljava/lang/String;", "", "lengths", "[J", "getLengths$okhttp", "()[J", "lockingSourceCount", "I", "getLockingSourceCount$okhttp", "()I", "setLockingSourceCount$okhttp", "(I)V", "", "readable", "Z", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "", "sequenceNumber", "J", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "zombie", "getZombie$okhttp", "setZombie$okhttp", "okhttp"})
  @SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$Entry\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,1065:1\n608#2,4:1066\n*S KotlinDebug\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$Entry\n*L\n1001#1:1066,4\n*E\n"})
  public final class Entry {
    @NotNull
    private final String key;
    
    @NotNull
    private final long[] lengths;
    
    @NotNull
    private final List<File> cleanFiles;
    
    @NotNull
    private final List<File> dirtyFiles;
    
    private boolean readable;
    
    private boolean zombie;
    
    @Nullable
    private DiskLruCache.Editor currentEditor;
    
    private int lockingSourceCount;
    
    private long sequenceNumber;
    
    public Entry(String key) {
      this.key = key;
      this.lengths = new long[DiskLruCache.this.getValueCount$okhttp()];
      this.cleanFiles = new ArrayList<>();
      this.dirtyFiles = new ArrayList<>();
      StringBuilder fileBuilder = (new StringBuilder(this.key)).append('.');
      int truncateTo = fileBuilder.length();
      for (int i = 0, j = DiskLruCache.this.getValueCount$okhttp(); i < j; i++) {
        fileBuilder.append(i);
        this.cleanFiles.add(new File(DiskLruCache.this.getDirectory(), fileBuilder.toString()));
        fileBuilder.append(".tmp");
        this.dirtyFiles.add(new File(DiskLruCache.this.getDirectory(), fileBuilder.toString()));
        fileBuilder.setLength(truncateTo);
      } 
    }
    
    @NotNull
    public final String getKey$okhttp() {
      return this.key;
    }
    
    @NotNull
    public final long[] getLengths$okhttp() {
      return this.lengths;
    }
    
    @NotNull
    public final List<File> getCleanFiles$okhttp() {
      return this.cleanFiles;
    }
    
    @NotNull
    public final List<File> getDirtyFiles$okhttp() {
      return this.dirtyFiles;
    }
    
    public final boolean getReadable$okhttp() {
      return this.readable;
    }
    
    public final void setReadable$okhttp(boolean <set-?>) {
      this.readable = <set-?>;
    }
    
    public final boolean getZombie$okhttp() {
      return this.zombie;
    }
    
    public final void setZombie$okhttp(boolean <set-?>) {
      this.zombie = <set-?>;
    }
    
    @Nullable
    public final DiskLruCache.Editor getCurrentEditor$okhttp() {
      return this.currentEditor;
    }
    
    public final void setCurrentEditor$okhttp(@Nullable DiskLruCache.Editor <set-?>) {
      this.currentEditor = <set-?>;
    }
    
    public final int getLockingSourceCount$okhttp() {
      return this.lockingSourceCount;
    }
    
    public final void setLockingSourceCount$okhttp(int <set-?>) {
      this.lockingSourceCount = <set-?>;
    }
    
    public final long getSequenceNumber$okhttp() {
      return this.sequenceNumber;
    }
    
    public final void setSequenceNumber$okhttp(long <set-?>) {
      this.sequenceNumber = <set-?>;
    }
    
    public final void setLengths$okhttp(@NotNull List<String> strings) throws IOException {
      Intrinsics.checkNotNullParameter(strings, "strings");
      if (strings.size() != DiskLruCache.this.getValueCount$okhttp()) {
        invalidLengths(strings);
        throw new KotlinNothingValueException();
      } 
      try {
        for (int i = 0, j = strings.size(); i < j; i++)
          this.lengths[i] = Long.parseLong((String)strings.get(i)); 
      } catch (NumberFormatException _) {
        invalidLengths(strings);
        throw new KotlinNothingValueException();
      } 
    }
    
    public final void writeLengths$okhttp(@NotNull BufferedSink writer) throws IOException {
      Intrinsics.checkNotNullParameter(writer, "writer");
      long[] arrayOfLong;
      byte b;
      int i;
      for (arrayOfLong = this.lengths, b = 0, i = arrayOfLong.length; b < i; ) {
        long length = arrayOfLong[b];
        writer.writeByte(32).writeDecimalLong(length);
        b++;
      } 
    }
    
    private final Void invalidLengths(List strings) throws IOException {
      throw new IOException("unexpected journal line: " + strings);
    }
    
    @Nullable
    public final DiskLruCache.Snapshot snapshot$okhttp() {
      Object $this$assertThreadHoldsLock$iv = DiskLruCache.this;
      int $i$f$assertThreadHoldsLock = 0;
      if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); 
      if (!this.readable)
        return null; 
      if (!DiskLruCache.this.civilizedFileSystem && (this.currentEditor != null || this.zombie))
        return null; 
      List<Source> sources = new ArrayList();
      long[] lengths = (long[])this.lengths.clone();
      try {
        for (int i = 0, j = DiskLruCache.this.getValueCount$okhttp(); i < j; i++)
          sources.add(newSource(i)); 
        return new DiskLruCache.Snapshot(this.key, this.sequenceNumber, sources, lengths);
      } catch (FileNotFoundException _) {
        for (Source source : sources)
          Util.closeQuietly((Closeable)source); 
        try {
          DiskLruCache.this.removeEntry$okhttp(this);
        } catch (IOException iOException) {}
        return null;
      } 
    }
    
    private final Source newSource(int index) {
      Source fileSource = DiskLruCache.this.getFileSystem$okhttp().source(this.cleanFiles.get(index));
      if (DiskLruCache.this.civilizedFileSystem)
        return fileSource; 
      int i = this.lockingSourceCount;
      this.lockingSourceCount = i + 1;
      return (Source)new DiskLruCache$Entry$newSource$1(DiskLruCache.this, this);
    }
    
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\031\n\000\n\002\030\002\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004R\026\020\006\032\0020\0058\002@\002X\016¢\006\006\n\004\b\006\020\007¨\006\b"}, d2 = {"okhttp3/internal/cache/DiskLruCache.Entry.newSource.1", "Lokio/ForwardingSource;", "", "close", "()V", "", "closed", "Z", "okhttp"})
    public static final class DiskLruCache$Entry$newSource$1 extends ForwardingSource {
      private boolean closed;
      
      DiskLruCache$Entry$newSource$1(DiskLruCache $receiver, DiskLruCache.Entry entry) {
        super($fileSource);
      }
      
      public void close() {
        super.close();
        if (!this.closed) {
          this.closed = true;
          DiskLruCache diskLruCache1 = this.this$0;
          DiskLruCache.Entry entry = DiskLruCache.Entry.this;
          DiskLruCache diskLruCache2 = this.this$0;
          synchronized (diskLruCache1) {
            int $i$a$-synchronized-DiskLruCache$Entry$newSource$1$close$1 = 0;
            int i = entry.getLockingSourceCount$okhttp();
            entry.setLockingSourceCount$okhttp(i + -1);
            if (entry.getLockingSourceCount$okhttp() == 0 && entry.getZombie$okhttp())
              diskLruCache2.removeEntry$okhttp(entry); 
            Unit unit = Unit.INSTANCE;
          } 
        } 
      }
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\002\n\002\020\016\n\002\b\006\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006XD¢\006\006\n\004\b\005\020\006R\024\020\b\032\0020\0078\006XD¢\006\006\n\004\b\b\020\tR\024\020\n\032\0020\0078\006XD¢\006\006\n\004\b\n\020\tR\024\020\013\032\0020\0078\006XD¢\006\006\n\004\b\013\020\tR\024\020\f\032\0020\0078\006XD¢\006\006\n\004\b\f\020\tR\024\020\r\032\0020\0078\006XD¢\006\006\n\004\b\r\020\tR\024\020\017\032\0020\0168\006X\004¢\006\006\n\004\b\017\020\020R\024\020\021\032\0020\0078\006XD¢\006\006\n\004\b\021\020\tR\024\020\022\032\0020\0078\006XD¢\006\006\n\004\b\022\020\tR\024\020\023\032\0020\0078\006XD¢\006\006\n\004\b\023\020\tR\024\020\024\032\0020\0078\006XD¢\006\006\n\004\b\024\020\t¨\006\025"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "<init>", "()V", "", "ANY_SEQUENCE_NUMBER", "J", "", "CLEAN", "Ljava/lang/String;", "DIRTY", "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "Lkotlin/text/Regex;", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final FileSystem fileSystem;
  
  @NotNull
  private final File directory;
  
  private final int appVersion;
  
  private final int valueCount;
  
  private long maxSize;
  
  @NotNull
  private final File journalFile;
  
  @NotNull
  private final File journalFileTmp;
  
  @NotNull
  private final File journalFileBackup;
  
  private long size;
  
  @Nullable
  private BufferedSink journalWriter;
  
  @NotNull
  private final LinkedHashMap<String, Entry> lruEntries;
  
  private int redundantOpCount;
  
  private boolean hasJournalErrors;
  
  private boolean civilizedFileSystem;
  
  private boolean initialized;
  
  private boolean closed;
  
  private boolean mostRecentTrimFailed;
  
  private boolean mostRecentRebuildFailed;
  
  private long nextSequenceNumber;
  
  @NotNull
  private final TaskQueue cleanupQueue;
  
  @NotNull
  private final DiskLruCache$cleanupTask$1 cleanupTask;
  
  @JvmField
  @NotNull
  public static final String JOURNAL_FILE = "journal";
  
  @JvmField
  @NotNull
  public static final String JOURNAL_FILE_TEMP = "journal.tmp";
  
  @JvmField
  @NotNull
  public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  
  @JvmField
  @NotNull
  public static final String MAGIC = "libcore.io.DiskLruCache";
  
  @JvmField
  @NotNull
  public static final String VERSION_1 = "1";
  
  @JvmField
  public static final long ANY_SEQUENCE_NUMBER = -1L;
  
  @JvmField
  @NotNull
  public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
  
  @JvmField
  @NotNull
  public static final String CLEAN = "CLEAN";
  
  @JvmField
  @NotNull
  public static final String DIRTY = "DIRTY";
  
  @JvmField
  @NotNull
  public static final String REMOVE = "REMOVE";
  
  @JvmField
  @NotNull
  public static final String READ = "READ";
  
  @JvmOverloads
  @Nullable
  public final Editor edit(@NotNull String key) throws IOException {
    Intrinsics.checkNotNullParameter(key, "key");
    return edit$default(this, key, 0L, 2, null);
  }
}
