package okio.internal;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\002\n\002\b\017\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\032K\020\013\032\0020\n*\b\022\004\022\0020\0010\0002\006\020\003\032\0020\0022\f\020\005\032\b\022\004\022\0020\0010\0042\006\020\006\032\0020\0012\006\020\b\032\0020\0072\006\020\t\032\0020\007H@ø\001\000¢\006\004\b\013\020\f\032#\020\017\032\0020\n*\0020\0022\006\020\r\032\0020\0012\006\020\016\032\0020\001H\000¢\006\004\b\017\020\020\032#\020\023\032\0020\n*\0020\0022\006\020\021\032\0020\0012\006\020\022\032\0020\007H\000¢\006\004\b\023\020\024\032#\020\027\032\0020\n*\0020\0022\006\020\025\032\0020\0012\006\020\026\032\0020\007H\000¢\006\004\b\027\020\024\032\033\020\030\032\0020\007*\0020\0022\006\020\006\032\0020\001H\000¢\006\004\b\030\020\031\032)\020\033\032\b\022\004\022\0020\0010\032*\0020\0022\006\020\021\032\0020\0012\006\020\b\032\0020\007H\000¢\006\004\b\033\020\034\032\033\020\036\032\0020\035*\0020\0022\006\020\006\032\0020\001H\000¢\006\004\b\036\020\037\032\035\020 \032\004\030\0010\001*\0020\0022\006\020\006\032\0020\001H\000¢\006\004\b \020!\002\004\n\002\b\031¨\006\""}, d2 = {"Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "Lokio/FileSystem;", "fileSystem", "Lkotlin/collections/ArrayDeque;", "stack", "path", "", "followSymlinks", "postorder", "", "collectRecursively", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "source", "target", "commonCopy", "(Lokio/FileSystem;Lokio/Path;Lokio/Path;)V", "dir", "mustCreate", "commonCreateDirectories", "(Lokio/FileSystem;Lokio/Path;Z)V", "fileOrDirectory", "mustExist", "commonDeleteRecursively", "commonExists", "(Lokio/FileSystem;Lokio/Path;)Z", "Lkotlin/sequences/Sequence;", "commonListRecursively", "(Lokio/FileSystem;Lokio/Path;Z)Lkotlin/sequences/Sequence;", "Lokio/FileMetadata;", "commonMetadata", "(Lokio/FileSystem;Lokio/Path;)Lokio/FileMetadata;", "symlinkTarget", "(Lokio/FileSystem;Lokio/Path;)Lokio/Path;", "okio"})
@JvmName(name = "-FileSystem")
@SourceDebugExtension({"SMAP\nFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystem.kt\nokio/internal/-FileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,155:1\n52#2,5:156\n52#2,21:161\n60#2,10:182\n57#2,2:192\n71#2,2:194\n*S KotlinDebug\n*F\n+ 1 FileSystem.kt\nokio/internal/-FileSystem\n*L\n65#1:156,5\n66#1:161,21\n65#1:182,10\n65#1:192,2\n65#1:194,2\n*E\n"})
public final class -FileSystem {
  @NotNull
  public static final FileMetadata commonMetadata(@NotNull FileSystem $this$commonMetadata, @NotNull Path path) throws IOException {
    Intrinsics.checkNotNullParameter($this$commonMetadata, "<this>");
    Intrinsics.checkNotNullParameter(path, "path");
    if ($this$commonMetadata.metadataOrNull(path) == null) {
      $this$commonMetadata.metadataOrNull(path);
      throw new FileNotFoundException("no such file: " + path);
    } 
    return $this$commonMetadata.metadataOrNull(path);
  }
  
  public static final boolean commonExists(@NotNull FileSystem $this$commonExists, @NotNull Path path) throws IOException {
    Intrinsics.checkNotNullParameter($this$commonExists, "<this>");
    Intrinsics.checkNotNullParameter(path, "path");
    return ($this$commonExists.metadataOrNull(path) != null);
  }
  
  public static final void commonCreateDirectories(@NotNull FileSystem $this$commonCreateDirectories, @NotNull Path dir, boolean mustCreate) throws IOException {
    Intrinsics.checkNotNullParameter($this$commonCreateDirectories, "<this>");
    Intrinsics.checkNotNullParameter(dir, "dir");
    ArrayDeque directories = new ArrayDeque();
    Path path = dir;
    while (path != null && !$this$commonCreateDirectories.exists(path)) {
      directories.addFirst(path);
      path = path.parent();
    } 
    if (mustCreate && directories.isEmpty())
      throw new IOException(dir + " already exists."); 
    for (Path toCreate : directories)
      $this$commonCreateDirectories.createDirectory(toCreate); 
  }
  
  public static final void commonDeleteRecursively(@NotNull FileSystem $this$commonDeleteRecursively, @NotNull Path fileOrDirectory, boolean mustExist) throws IOException {
    Intrinsics.checkNotNullParameter($this$commonDeleteRecursively, "<this>");
    Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
    Sequence sequence = SequencesKt.sequence(new -FileSystem$commonDeleteRecursively$sequence$1($this$commonDeleteRecursively, fileOrDirectory, null));
    Iterator<Path> iterator = sequence.iterator();
    while (iterator.hasNext()) {
      Path toDelete = iterator.next();
      $this$commonDeleteRecursively.delete(toDelete, (mustExist && !iterator.hasNext()));
    } 
  }
  
  @DebugMetadata(f = "FileSystem.kt", l = {116, 135, 145}, i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1"}, n = {"$this$collectRecursively", "fileSystem", "stack", "path", "followSymlinks", "postorder", "$this$collectRecursively", "fileSystem", "stack", "path", "followSymlinks", "postorder"}, m = "collectRecursively", c = "okio.internal.-FileSystem")
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
  static final class -FileSystem$collectRecursively$1 extends ContinuationImpl {
    Object L$0;
    
    Object L$1;
    
    Object L$2;
    
    Object L$3;
    
    Object L$4;
    
    boolean Z$0;
    
    boolean Z$1;
    
    int label;
    
    -FileSystem$collectRecursively$1(Continuation $completion) {
      super($completion);
    }
    
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
      this.result = $result;
      this.label |= Integer.MIN_VALUE;
      return -FileSystem.collectRecursively(null, null, null, null, false, false, (Continuation<? super Unit>)this);
    }
  }
  
  @DebugMetadata(f = "FileSystem.kt", l = {75}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "okio.internal.-FileSystem$commonDeleteRecursively$sequence$1")
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\002\020\003\032\0020\002*\b\022\004\022\0020\0010\000H@¢\006\004\b\003\020\004"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "", "<anonymous>", "(Lkotlin/sequences/SequenceScope;)V"})
  static final class -FileSystem$commonDeleteRecursively$sequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    int label;
    
    -FileSystem$commonDeleteRecursively$sequence$1(FileSystem $receiver, Path $fileOrDirectory, Continuation $completion) {
      super(2, $completion);
    }
    
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
      SequenceScope<? super Path> $this$sequence;
      Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch (this.label) {
        case 0:
          ResultKt.throwOnFailure(SYNTHETIC_LOCAL_VARIABLE_1);
          $this$sequence = (SequenceScope)this.L$0;
          this.label = 1;
          if (-FileSystem.collectRecursively($this$sequence, this.$this_commonDeleteRecursively, new ArrayDeque(), this.$fileOrDirectory, false, true, (Continuation<? super Unit>)this) == object)
            return object; 
          -FileSystem.collectRecursively($this$sequence, this.$this_commonDeleteRecursively, new ArrayDeque(), this.$fileOrDirectory, false, true, (Continuation<? super Unit>)this);
          return Unit.INSTANCE;
        case 1:
          ResultKt.throwOnFailure(SYNTHETIC_LOCAL_VARIABLE_1);
          return Unit.INSTANCE;
      } 
      throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
    
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<? super -FileSystem$commonDeleteRecursively$sequence$1> $completion) {
      -FileSystem$commonDeleteRecursively$sequence$1 -FileSystem$commonDeleteRecursively$sequence$11 = new -FileSystem$commonDeleteRecursively$sequence$1(this.$this_commonDeleteRecursively, this.$fileOrDirectory, $completion);
      -FileSystem$commonDeleteRecursively$sequence$11.L$0 = value;
      return (Continuation<Unit>)-FileSystem$commonDeleteRecursively$sequence$11;
    }
    
    @Nullable
    public final Object invoke(@NotNull SequenceScope p1, @Nullable Continuation<?> p2) {
      return ((-FileSystem$commonDeleteRecursively$sequence$1)create(p1, p2)).invokeSuspend(Unit.INSTANCE);
    }
  }
  
  @NotNull
  public static final Sequence<Path> commonListRecursively(@NotNull FileSystem $this$commonListRecursively, @NotNull Path dir, boolean followSymlinks) throws IOException {
    Intrinsics.checkNotNullParameter($this$commonListRecursively, "<this>");
    Intrinsics.checkNotNullParameter(dir, "dir");
    return SequencesKt.sequence(new -FileSystem$commonListRecursively$1(dir, $this$commonListRecursively, followSymlinks, null));
  }
  
  @DebugMetadata(f = "FileSystem.kt", l = {96}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"$this$sequence", "stack"}, m = "invokeSuspend", c = "okio.internal.-FileSystem$commonListRecursively$1")
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\002\020\003\032\0020\002*\b\022\004\022\0020\0010\000H@¢\006\004\b\003\020\004"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "", "<anonymous>", "(Lkotlin/sequences/SequenceScope;)V"})
  static final class -FileSystem$commonListRecursively$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    Object L$1;
    
    Object L$2;
    
    int label;
    
    -FileSystem$commonListRecursively$1(Path $dir, FileSystem $receiver, boolean $followSymlinks, Continuation $completion) {
      super(2, $completion);
    }
    
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
      // Byte code:
      //   0: invokestatic getCOROUTINE_SUSPENDED : ()Ljava/lang/Object;
      //   3: astore #6
      //   5: aload_0
      //   6: getfield label : I
      //   9: tableswitch default -> 188, 0 -> 32, 1 -> 150
      //   32: aload_1
      //   33: invokestatic throwOnFailure : (Ljava/lang/Object;)V
      //   36: aload_0
      //   37: getfield L$0 : Ljava/lang/Object;
      //   40: checkcast kotlin/sequences/SequenceScope
      //   43: astore_2
      //   44: new kotlin/collections/ArrayDeque
      //   47: dup
      //   48: invokespecial <init> : ()V
      //   51: astore_3
      //   52: aload_3
      //   53: aload_0
      //   54: getfield $dir : Lokio/Path;
      //   57: invokevirtual addLast : (Ljava/lang/Object;)V
      //   60: aload_0
      //   61: getfield $this_commonListRecursively : Lokio/FileSystem;
      //   64: aload_0
      //   65: getfield $dir : Lokio/Path;
      //   68: invokevirtual list : (Lokio/Path;)Ljava/util/List;
      //   71: invokeinterface iterator : ()Ljava/util/Iterator;
      //   76: astore #4
      //   78: aload #4
      //   80: invokeinterface hasNext : ()Z
      //   85: ifeq -> 184
      //   88: aload #4
      //   90: invokeinterface next : ()Ljava/lang/Object;
      //   95: checkcast okio/Path
      //   98: astore #5
      //   100: aload_2
      //   101: aload_0
      //   102: getfield $this_commonListRecursively : Lokio/FileSystem;
      //   105: aload_3
      //   106: aload #5
      //   108: aload_0
      //   109: getfield $followSymlinks : Z
      //   112: iconst_0
      //   113: aload_0
      //   114: checkcast kotlin/coroutines/Continuation
      //   117: aload_0
      //   118: aload_2
      //   119: putfield L$0 : Ljava/lang/Object;
      //   122: aload_0
      //   123: aload_3
      //   124: putfield L$1 : Ljava/lang/Object;
      //   127: aload_0
      //   128: aload #4
      //   130: putfield L$2 : Ljava/lang/Object;
      //   133: aload_0
      //   134: iconst_1
      //   135: putfield label : I
      //   138: invokestatic collectRecursively : (Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
      //   141: dup
      //   142: aload #6
      //   144: if_acmpne -> 180
      //   147: aload #6
      //   149: areturn
      //   150: aload_0
      //   151: getfield L$2 : Ljava/lang/Object;
      //   154: checkcast java/util/Iterator
      //   157: astore #4
      //   159: aload_0
      //   160: getfield L$1 : Ljava/lang/Object;
      //   163: checkcast kotlin/collections/ArrayDeque
      //   166: astore_3
      //   167: aload_0
      //   168: getfield L$0 : Ljava/lang/Object;
      //   171: checkcast kotlin/sequences/SequenceScope
      //   174: astore_2
      //   175: aload_1
      //   176: invokestatic throwOnFailure : (Ljava/lang/Object;)V
      //   179: aload_1
      //   180: pop
      //   181: goto -> 78
      //   184: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
      //   187: areturn
      //   188: new java/lang/IllegalStateException
      //   191: dup
      //   192: ldc 'call to 'resume' before 'invoke' with coroutine'
      //   194: invokespecial <init> : (Ljava/lang/String;)V
      //   197: athrow
      // Line number table:
      //   Java source line number -> byte code offset
      //   #92	-> 3
      //   #93	-> 44
      //   #94	-> 52
      //   #95	-> 60
      //   #96	-> 100
      //   #97	-> 101
      //   #98	-> 105
      //   #99	-> 106
      //   #100	-> 108
      //   #101	-> 112
      //   #96	-> 117
      //   #92	-> 147
      //   #96	-> 180
      //   #104	-> 184
      //   #92	-> 188
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   44	106	2	$this$sequence	Lkotlin/sequences/SequenceScope;
      //   175	13	2	$this$sequence	Lkotlin/sequences/SequenceScope;
      //   52	98	3	stack	Lkotlin/collections/ArrayDeque;
      //   167	21	3	stack	Lkotlin/collections/ArrayDeque;
      //   100	8	5	child	Lokio/Path;
      //   36	152	0	this	Lokio/internal/-FileSystem$commonListRecursively$1;
      //   36	152	1	$result	Ljava/lang/Object;
    }
    
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<? super -FileSystem$commonListRecursively$1> $completion) {
      -FileSystem$commonListRecursively$1 -FileSystem$commonListRecursively$11 = new -FileSystem$commonListRecursively$1(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, $completion);
      -FileSystem$commonListRecursively$11.L$0 = value;
      return (Continuation<Unit>)-FileSystem$commonListRecursively$11;
    }
    
    @Nullable
    public final Object invoke(@NotNull SequenceScope p1, @Nullable Continuation<?> p2) {
      return ((-FileSystem$commonListRecursively$1)create(p1, p2)).invokeSuspend(Unit.INSTANCE);
    }
  }
  
  @Nullable
  public static final Object collectRecursively(@NotNull SequenceScope $this$collectRecursively, @NotNull FileSystem fileSystem, @NotNull ArrayDeque stack, @NotNull Path path, boolean followSymlinks, boolean postorder, @NotNull Continuation<? super Unit> paramContinuation) {
    // Byte code:
    //   0: aload #6
    //   2: instanceof okio/internal/-FileSystem$collectRecursively$1
    //   5: ifeq -> 43
    //   8: aload #6
    //   10: checkcast okio/internal/-FileSystem$collectRecursively$1
    //   13: astore #13
    //   15: aload #13
    //   17: getfield label : I
    //   20: ldc_w -2147483648
    //   23: iand
    //   24: ifeq -> 43
    //   27: aload #13
    //   29: dup
    //   30: getfield label : I
    //   33: ldc_w -2147483648
    //   36: isub
    //   37: putfield label : I
    //   40: goto -> 54
    //   43: new okio/internal/-FileSystem$collectRecursively$1
    //   46: dup
    //   47: aload #6
    //   49: invokespecial <init> : (Lkotlin/coroutines/Continuation;)V
    //   52: astore #13
    //   54: aload #13
    //   56: getfield result : Ljava/lang/Object;
    //   59: astore #12
    //   61: invokestatic getCOROUTINE_SUSPENDED : ()Ljava/lang/Object;
    //   64: astore #14
    //   66: aload #13
    //   68: getfield label : I
    //   71: tableswitch default -> 636, 0 -> 100, 1 -> 170, 2 -> 473, 3 -> 620
    //   100: aload #12
    //   102: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   105: iload #5
    //   107: ifne -> 228
    //   110: aload_0
    //   111: aload_3
    //   112: aload #13
    //   114: aload #13
    //   116: aload_0
    //   117: putfield L$0 : Ljava/lang/Object;
    //   120: aload #13
    //   122: aload_1
    //   123: putfield L$1 : Ljava/lang/Object;
    //   126: aload #13
    //   128: aload_2
    //   129: putfield L$2 : Ljava/lang/Object;
    //   132: aload #13
    //   134: aload_3
    //   135: putfield L$3 : Ljava/lang/Object;
    //   138: aload #13
    //   140: iload #4
    //   142: putfield Z$0 : Z
    //   145: aload #13
    //   147: iload #5
    //   149: putfield Z$1 : Z
    //   152: aload #13
    //   154: iconst_1
    //   155: putfield label : I
    //   158: invokevirtual yield : (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   161: dup
    //   162: aload #14
    //   164: if_acmpne -> 227
    //   167: aload #14
    //   169: areturn
    //   170: aload #13
    //   172: getfield Z$1 : Z
    //   175: istore #5
    //   177: aload #13
    //   179: getfield Z$0 : Z
    //   182: istore #4
    //   184: aload #13
    //   186: getfield L$3 : Ljava/lang/Object;
    //   189: checkcast okio/Path
    //   192: astore_3
    //   193: aload #13
    //   195: getfield L$2 : Ljava/lang/Object;
    //   198: checkcast kotlin/collections/ArrayDeque
    //   201: astore_2
    //   202: aload #13
    //   204: getfield L$1 : Ljava/lang/Object;
    //   207: checkcast okio/FileSystem
    //   210: astore_1
    //   211: aload #13
    //   213: getfield L$0 : Ljava/lang/Object;
    //   216: checkcast kotlin/sequences/SequenceScope
    //   219: astore_0
    //   220: aload #12
    //   222: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   225: aload #12
    //   227: pop
    //   228: aload_1
    //   229: aload_3
    //   230: invokevirtual listOrNull : (Lokio/Path;)Ljava/util/List;
    //   233: dup
    //   234: ifnonnull -> 241
    //   237: pop
    //   238: invokestatic emptyList : ()Ljava/util/List;
    //   241: astore #7
    //   243: aload #7
    //   245: checkcast java/util/Collection
    //   248: invokeinterface isEmpty : ()Z
    //   253: ifne -> 260
    //   256: iconst_1
    //   257: goto -> 261
    //   260: iconst_0
    //   261: ifeq -> 563
    //   264: aload_3
    //   265: astore #8
    //   267: iconst_0
    //   268: istore #9
    //   270: nop
    //   271: iload #4
    //   273: ifeq -> 313
    //   276: aload_2
    //   277: aload #8
    //   279: invokevirtual contains : (Ljava/lang/Object;)Z
    //   282: ifeq -> 313
    //   285: new java/io/IOException
    //   288: dup
    //   289: new java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial <init> : ()V
    //   296: ldc_w 'symlink cycle at '
    //   299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: aload_3
    //   303: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   306: invokevirtual toString : ()Ljava/lang/String;
    //   309: invokespecial <init> : (Ljava/lang/String;)V
    //   312: athrow
    //   313: aload_1
    //   314: aload #8
    //   316: invokestatic symlinkTarget : (Lokio/FileSystem;Lokio/Path;)Lokio/Path;
    //   319: dup
    //   320: ifnonnull -> 327
    //   323: pop
    //   324: goto -> 335
    //   327: astore #8
    //   329: iinc #9, 1
    //   332: goto -> 270
    //   335: iload #4
    //   337: ifne -> 345
    //   340: iload #9
    //   342: ifne -> 563
    //   345: aload_2
    //   346: aload #8
    //   348: invokevirtual addLast : (Ljava/lang/Object;)V
    //   351: nop
    //   352: aload #7
    //   354: invokeinterface iterator : ()Ljava/util/Iterator;
    //   359: astore #10
    //   361: aload #10
    //   363: invokeinterface hasNext : ()Z
    //   368: ifeq -> 545
    //   371: aload #10
    //   373: invokeinterface next : ()Ljava/lang/Object;
    //   378: checkcast okio/Path
    //   381: astore #11
    //   383: aload_0
    //   384: aload_1
    //   385: aload_2
    //   386: aload #11
    //   388: iload #4
    //   390: ifeq -> 397
    //   393: iconst_1
    //   394: goto -> 398
    //   397: iconst_0
    //   398: iload #5
    //   400: ifeq -> 407
    //   403: iconst_1
    //   404: goto -> 408
    //   407: iconst_0
    //   408: aload #13
    //   410: aload #13
    //   412: aload_0
    //   413: putfield L$0 : Ljava/lang/Object;
    //   416: aload #13
    //   418: aload_1
    //   419: putfield L$1 : Ljava/lang/Object;
    //   422: aload #13
    //   424: aload_2
    //   425: putfield L$2 : Ljava/lang/Object;
    //   428: aload #13
    //   430: aload_3
    //   431: putfield L$3 : Ljava/lang/Object;
    //   434: aload #13
    //   436: aload #10
    //   438: putfield L$4 : Ljava/lang/Object;
    //   441: aload #13
    //   443: iload #4
    //   445: putfield Z$0 : Z
    //   448: aload #13
    //   450: iload #5
    //   452: putfield Z$1 : Z
    //   455: aload #13
    //   457: iconst_2
    //   458: putfield label : I
    //   461: invokestatic collectRecursively : (Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   464: dup
    //   465: aload #14
    //   467: if_acmpne -> 541
    //   470: aload #14
    //   472: areturn
    //   473: aload #13
    //   475: getfield Z$1 : Z
    //   478: istore #5
    //   480: aload #13
    //   482: getfield Z$0 : Z
    //   485: istore #4
    //   487: aload #13
    //   489: getfield L$4 : Ljava/lang/Object;
    //   492: checkcast java/util/Iterator
    //   495: astore #10
    //   497: aload #13
    //   499: getfield L$3 : Ljava/lang/Object;
    //   502: checkcast okio/Path
    //   505: astore_3
    //   506: aload #13
    //   508: getfield L$2 : Ljava/lang/Object;
    //   511: checkcast kotlin/collections/ArrayDeque
    //   514: astore_2
    //   515: aload #13
    //   517: getfield L$1 : Ljava/lang/Object;
    //   520: checkcast okio/FileSystem
    //   523: astore_1
    //   524: aload #13
    //   526: getfield L$0 : Ljava/lang/Object;
    //   529: checkcast kotlin/sequences/SequenceScope
    //   532: astore_0
    //   533: nop
    //   534: aload #12
    //   536: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   539: aload #12
    //   541: pop
    //   542: goto -> 361
    //   545: aload_2
    //   546: invokevirtual removeLast : ()Ljava/lang/Object;
    //   549: pop
    //   550: goto -> 563
    //   553: astore #10
    //   555: aload_2
    //   556: invokevirtual removeLast : ()Ljava/lang/Object;
    //   559: pop
    //   560: aload #10
    //   562: athrow
    //   563: iload #5
    //   565: ifeq -> 632
    //   568: aload_0
    //   569: aload_3
    //   570: aload #13
    //   572: aload #13
    //   574: aconst_null
    //   575: putfield L$0 : Ljava/lang/Object;
    //   578: aload #13
    //   580: aconst_null
    //   581: putfield L$1 : Ljava/lang/Object;
    //   584: aload #13
    //   586: aconst_null
    //   587: putfield L$2 : Ljava/lang/Object;
    //   590: aload #13
    //   592: aconst_null
    //   593: putfield L$3 : Ljava/lang/Object;
    //   596: aload #13
    //   598: aconst_null
    //   599: putfield L$4 : Ljava/lang/Object;
    //   602: aload #13
    //   604: iconst_3
    //   605: putfield label : I
    //   608: invokevirtual yield : (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   611: dup
    //   612: aload #14
    //   614: if_acmpne -> 627
    //   617: aload #14
    //   619: areturn
    //   620: aload #12
    //   622: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   625: aload #12
    //   627: pop
    //   628: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   631: areturn
    //   632: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   635: areturn
    //   636: new java/lang/IllegalStateException
    //   639: dup
    //   640: ldc_w 'call to 'resume' before 'invoke' with coroutine'
    //   643: invokespecial <init> : (Ljava/lang/String;)V
    //   646: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #107	-> 64
    //   #115	-> 105
    //   #116	-> 110
    //   #107	-> 167
    //   #119	-> 227
    //   #119	-> 241
    //   #120	-> 243
    //   #120	-> 261
    //   #122	-> 264
    //   #123	-> 267
    //   #124	-> 270
    //   #125	-> 271
    //   #126	-> 313
    //   #127	-> 329
    //   #131	-> 335
    //   #132	-> 345
    //   #133	-> 351
    //   #134	-> 352
    //   #135	-> 383
    //   #107	-> 470
    //   #135	-> 541
    //   #138	-> 545
    //   #139	-> 550
    //   #138	-> 553
    //   #144	-> 563
    //   #145	-> 568
    //   #107	-> 617
    //   #147	-> 627
    //   #107	-> 636
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   105	65	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   220	21	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   241	20	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   261	52	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   313	14	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   327	146	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   533	20	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   563	48	0	$this$collectRecursively	Lkotlin/sequences/SequenceScope;
    //   105	65	1	fileSystem	Lokio/FileSystem;
    //   211	30	1	fileSystem	Lokio/FileSystem;
    //   241	20	1	fileSystem	Lokio/FileSystem;
    //   261	52	1	fileSystem	Lokio/FileSystem;
    //   313	14	1	fileSystem	Lokio/FileSystem;
    //   327	146	1	fileSystem	Lokio/FileSystem;
    //   524	21	1	fileSystem	Lokio/FileSystem;
    //   105	65	2	stack	Lkotlin/collections/ArrayDeque;
    //   202	39	2	stack	Lkotlin/collections/ArrayDeque;
    //   241	20	2	stack	Lkotlin/collections/ArrayDeque;
    //   261	52	2	stack	Lkotlin/collections/ArrayDeque;
    //   313	14	2	stack	Lkotlin/collections/ArrayDeque;
    //   327	146	2	stack	Lkotlin/collections/ArrayDeque;
    //   515	35	2	stack	Lkotlin/collections/ArrayDeque;
    //   553	7	2	stack	Lkotlin/collections/ArrayDeque;
    //   105	65	3	path	Lokio/Path;
    //   193	48	3	path	Lokio/Path;
    //   241	20	3	path	Lokio/Path;
    //   261	52	3	path	Lokio/Path;
    //   313	14	3	path	Lokio/Path;
    //   327	146	3	path	Lokio/Path;
    //   506	47	3	path	Lokio/Path;
    //   563	48	3	path	Lokio/Path;
    //   105	65	4	followSymlinks	Z
    //   184	57	4	followSymlinks	Z
    //   241	20	4	followSymlinks	Z
    //   261	52	4	followSymlinks	Z
    //   313	14	4	followSymlinks	Z
    //   327	146	4	followSymlinks	Z
    //   487	58	4	followSymlinks	Z
    //   105	65	5	postorder	Z
    //   177	64	5	postorder	Z
    //   241	20	5	postorder	Z
    //   261	52	5	postorder	Z
    //   313	14	5	postorder	Z
    //   327	146	5	postorder	Z
    //   480	73	5	postorder	Z
    //   563	5	5	postorder	Z
    //   243	18	7	children	Ljava/util/List;
    //   261	52	7	children	Ljava/util/List;
    //   313	14	7	children	Ljava/util/List;
    //   327	34	7	children	Ljava/util/List;
    //   267	46	8	symlinkPath	Lokio/Path;
    //   313	10	8	symlinkPath	Lokio/Path;
    //   323	4	8	symlinkPath	Lokio/Path;
    //   329	22	8	symlinkPath	Lokio/Path;
    //   270	43	9	symlinkCount	I
    //   313	14	9	symlinkCount	I
    //   327	18	9	symlinkCount	I
    //   383	14	11	child	Lokio/Path;
    //   54	582	13	$continuation	Lkotlin/coroutines/Continuation;
    //   61	575	12	$result	Ljava/lang/Object;
    // Exception table:
    //   from	to	target	type
    //   351	464	553	finally
    //   533	545	553	finally
    //   553	555	553	finally
  }
  
  @Nullable
  public static final Path symlinkTarget(@NotNull FileSystem $this$symlinkTarget, @NotNull Path path) throws IOException {
    Path target;
    Intrinsics.checkNotNullParameter($this$symlinkTarget, "<this>");
    Intrinsics.checkNotNullParameter(path, "path");
    if ($this$symlinkTarget.metadata(path).getSymlinkTarget() == null) {
      $this$symlinkTarget.metadata(path).getSymlinkTarget();
      return null;
    } 
    Intrinsics.checkNotNull(path.parent());
    return path.parent().resolve(target);
  }
  
  public static final void commonCopy(@NotNull FileSystem $this$commonCopy, @NotNull Path source, @NotNull Path target) throws IOException {
    Intrinsics.checkNotNullParameter($this$commonCopy, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    Closeable $this$use$iv = (Closeable)$this$commonCopy.source(source);
    int $i$f$use = 0;
    Object result$iv = null;
    Throwable thrown$iv = null;
    try {
      Source bytesIn = (Source)$this$use$iv;
      int $i$a$-use--FileSystem$commonCopy$1 = 0;
      Closeable closeable = (Closeable)Okio.buffer($this$commonCopy.sink(target));
      int i = 0;
      Object object = null;
      Throwable throwable = null;
      try {
        BufferedSink bytesOut = (BufferedSink)closeable;
        int $i$a$-use--FileSystem$commonCopy$1$1 = 0;
        object = Long.valueOf(bytesOut.writeAll(bytesIn));
        try {
          if (closeable != null) {
            closeable.close();
          } else {
          
          } 
        } catch (Throwable t$iv) {
          throwable = t$iv;
        } 
      } catch (Throwable t$iv) {
        throwable = t$iv;
      } finally {
        try {
          if (closeable != null) {
            closeable.close();
          } else {
          
          } 
        } catch (Throwable t$iv) {
          throwable = t$iv;
        } 
      } 
      Intrinsics.checkNotNull(object);
      result$iv = Long.valueOf(((Number)object).longValue());
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
    } catch (Throwable t$iv) {
      thrown$iv = t$iv;
    } finally {
      try {
        if ($this$use$iv != null) {
          $this$use$iv.close();
        } else {
        
        } 
      } catch (Throwable t$iv) {
        thrown$iv = t$iv;
      } 
    } 
    Intrinsics.checkNotNull(result$iv);
  }
}
