package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.RealBufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000p\n\002\030\002\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\005\n\000\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\004\n\002\020\022\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\025\n\002\020\n\n\002\b\003\n\002\020\016\n\002\b\013\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\032\024\020\002\032\0020\001*\0020\000H\b¢\006\004\b\002\020\003\032\024\020\005\032\0020\004*\0020\000H\b¢\006\004\b\005\020\006\032,\020\f\032\0020\t*\0020\0002\006\020\b\032\0020\0072\006\020\n\032\0020\t2\006\020\013\032\0020\tH\b¢\006\004\b\f\020\r\032$\020\f\032\0020\t*\0020\0002\006\020\017\032\0020\0162\006\020\n\032\0020\tH\b¢\006\004\b\f\020\020\032$\020\022\032\0020\t*\0020\0002\006\020\021\032\0020\0162\006\020\n\032\0020\tH\b¢\006\004\b\022\020\020\032\024\020\024\032\0020\023*\0020\000H\b¢\006\004\b\024\020\025\0324\020\032\032\0020\004*\0020\0002\006\020\026\032\0020\t2\006\020\017\032\0020\0162\006\020\030\032\0020\0272\006\020\031\032\0020\027H\b¢\006\004\b\032\020\033\032,\020\036\032\0020\027*\0020\0002\006\020\035\032\0020\0342\006\020\026\032\0020\0272\006\020\031\032\0020\027H\b¢\006\004\b\036\020\037\032$\020\036\032\0020\t*\0020\0002\006\020\035\032\0020 2\006\020\031\032\0020\tH\b¢\006\004\b\036\020!\032\034\020#\032\0020\t*\0020\0002\006\020\035\032\0020\"H\b¢\006\004\b#\020$\032\024\020%\032\0020\007*\0020\000H\b¢\006\004\b%\020&\032\024\020'\032\0020\034*\0020\000H\b¢\006\004\b'\020(\032\034\020'\032\0020\034*\0020\0002\006\020\031\032\0020\tH\b¢\006\004\b'\020)\032\024\020*\032\0020\016*\0020\000H\b¢\006\004\b*\020+\032\034\020*\032\0020\016*\0020\0002\006\020\031\032\0020\tH\b¢\006\004\b*\020,\032\024\020-\032\0020\t*\0020\000H\b¢\006\004\b-\020.\032\034\020/\032\0020\001*\0020\0002\006\020\035\032\0020\034H\b¢\006\004\b/\0200\032$\020/\032\0020\001*\0020\0002\006\020\035\032\0020 2\006\020\031\032\0020\tH\b¢\006\004\b/\0201\032\024\0202\032\0020\t*\0020\000H\b¢\006\004\b2\020.\032\024\0203\032\0020\027*\0020\000H\b¢\006\004\b3\0204\032\024\0205\032\0020\027*\0020\000H\b¢\006\004\b5\0204\032\024\0206\032\0020\t*\0020\000H\b¢\006\004\b6\020.\032\024\0207\032\0020\t*\0020\000H\b¢\006\004\b7\020.\032\024\0209\032\00208*\0020\000H\b¢\006\004\b9\020:\032\024\020;\032\00208*\0020\000H\b¢\006\004\b;\020:\032\024\020=\032\0020<*\0020\000H\b¢\006\004\b=\020>\032\034\020=\032\0020<*\0020\0002\006\020\031\032\0020\tH\b¢\006\004\b=\020?\032\024\020@\032\0020\027*\0020\000H\b¢\006\004\b@\0204\032\026\020A\032\004\030\0010<*\0020\000H\b¢\006\004\bA\020>\032\034\020C\032\0020<*\0020\0002\006\020B\032\0020\tH\b¢\006\004\bC\020?\032\034\020D\032\0020\004*\0020\0002\006\020\031\032\0020\tH\b¢\006\004\bD\020E\032\034\020F\032\0020\001*\0020\0002\006\020\031\032\0020\tH\b¢\006\004\bF\020G\032\034\020J\032\0020\027*\0020\0002\006\020I\032\0020HH\b¢\006\004\bJ\020K\032\034\020L\032\0020\001*\0020\0002\006\020\031\032\0020\tH\b¢\006\004\bL\020G\032\024\020N\032\0020M*\0020\000H\b¢\006\004\bN\020O\032\024\020P\032\0020<*\0020\000H\b¢\006\004\bP\020>¨\006Q"}, d2 = {"Lokio/RealBufferedSource;", "", "commonClose", "(Lokio/RealBufferedSource;)V", "", "commonExhausted", "(Lokio/RealBufferedSource;)Z", "", "b", "", "fromIndex", "toIndex", "commonIndexOf", "(Lokio/RealBufferedSource;BJJ)J", "Lokio/ByteString;", "bytes", "(Lokio/RealBufferedSource;Lokio/ByteString;J)J", "targetBytes", "commonIndexOfElement", "Lokio/BufferedSource;", "commonPeek", "(Lokio/RealBufferedSource;)Lokio/BufferedSource;", "offset", "", "bytesOffset", "byteCount", "commonRangeEquals", "(Lokio/RealBufferedSource;JLokio/ByteString;II)Z", "", "sink", "commonRead", "(Lokio/RealBufferedSource;[BII)I", "Lokio/Buffer;", "(Lokio/RealBufferedSource;Lokio/Buffer;J)J", "Lokio/Sink;", "commonReadAll", "(Lokio/RealBufferedSource;Lokio/Sink;)J", "commonReadByte", "(Lokio/RealBufferedSource;)B", "commonReadByteArray", "(Lokio/RealBufferedSource;)[B", "(Lokio/RealBufferedSource;J)[B", "commonReadByteString", "(Lokio/RealBufferedSource;)Lokio/ByteString;", "(Lokio/RealBufferedSource;J)Lokio/ByteString;", "commonReadDecimalLong", "(Lokio/RealBufferedSource;)J", "commonReadFully", "(Lokio/RealBufferedSource;[B)V", "(Lokio/RealBufferedSource;Lokio/Buffer;J)V", "commonReadHexadecimalUnsignedLong", "commonReadInt", "(Lokio/RealBufferedSource;)I", "commonReadIntLe", "commonReadLong", "commonReadLongLe", "", "commonReadShort", "(Lokio/RealBufferedSource;)S", "commonReadShortLe", "", "commonReadUtf8", "(Lokio/RealBufferedSource;)Ljava/lang/String;", "(Lokio/RealBufferedSource;J)Ljava/lang/String;", "commonReadUtf8CodePoint", "commonReadUtf8Line", "limit", "commonReadUtf8LineStrict", "commonRequest", "(Lokio/RealBufferedSource;J)Z", "commonRequire", "(Lokio/RealBufferedSource;J)V", "Lokio/Options;", "options", "commonSelect", "(Lokio/RealBufferedSource;Lokio/Options;)I", "commonSkip", "Lokio/Timeout;", "commonTimeout", "(Lokio/RealBufferedSource;)Lokio/Timeout;", "commonToString", "okio"})
@JvmName(name = "-RealBufferedSource")
@SourceDebugExtension({"SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,402:1\n1#2:403\n62#3:404\n62#3:405\n62#3:406\n62#3:407\n62#3:408\n62#3:409\n62#3:410\n62#3:411\n62#3:412\n62#3:413\n62#3:414\n62#3:415\n62#3:416\n62#3:417\n62#3:418\n62#3:419\n62#3:420\n62#3:421\n62#3:422\n62#3:423\n62#3:424\n62#3:425\n62#3:426\n62#3:428\n62#3:429\n62#3:430\n62#3:431\n62#3:432\n62#3:433\n62#3:434\n62#3:435\n62#3:436\n62#3:437\n62#3:438\n62#3:439\n62#3:440\n62#3:441\n62#3:442\n62#3:443\n62#3:444\n62#3:445\n62#3:446\n62#3:447\n62#3:449\n62#3:450\n62#3:451\n62#3:452\n62#3:453\n62#3:454\n62#3:455\n62#3:456\n62#3:457\n62#3:458\n62#3:459\n62#3:460\n62#3:461\n62#3:462\n62#3:463\n62#3:464\n62#3:465\n62#3:466\n62#3:467\n62#3:468\n62#3:469\n62#3:470\n62#3:471\n62#3:472\n62#3:473\n62#3:474\n62#3:475\n89#4:427\n89#4:448\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n*L\n41#1:404\n42#1:405\n46#1:406\n47#1:407\n52#1:408\n62#1:409\n63#1:410\n70#1:411\n74#1:412\n75#1:413\n80#1:414\n87#1:415\n94#1:416\n99#1:417\n107#1:418\n108#1:419\n113#1:420\n122#1:421\n123#1:422\n130#1:423\n136#1:424\n137#1:425\n141#1:426\n142#1:428\n150#1:429\n154#1:430\n159#1:431\n160#1:432\n163#1:433\n166#1:434\n167#1:435\n168#1:436\n174#1:437\n175#1:438\n180#1:439\n187#1:440\n188#1:441\n193#1:442\n201#1:443\n203#1:444\n204#1:445\n206#1:446\n209#1:447\n211#1:449\n219#1:450\n226#1:451\n231#1:452\n236#1:453\n241#1:454\n246#1:455\n251#1:456\n256#1:457\n264#1:458\n275#1:459\n283#1:460\n297#1:461\n304#1:462\n307#1:463\n308#1:464\n319#1:465\n324#1:466\n325#1:467\n338#1:468\n341#1:469\n342#1:470\n354#1:471\n357#1:472\n358#1:473\n383#1:474\n396#1:475\n141#1:427\n209#1:448\n*E\n"})
public final class -RealBufferedSource {
  public static final long commonRead(@NotNull RealBufferedSource $this$commonRead, @NotNull Buffer sink, long byteCount) {
    // Byte code:
    //   0: aload_0
    //   1: ldc '<this>'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 'sink'
    //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   12: iconst_0
    //   13: istore #4
    //   15: lload_2
    //   16: lconst_0
    //   17: lcmp
    //   18: iflt -> 25
    //   21: iconst_1
    //   22: goto -> 26
    //   25: iconst_0
    //   26: ifne -> 66
    //   29: iconst_0
    //   30: istore #6
    //   32: new java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial <init> : ()V
    //   39: ldc 'byteCount < 0: '
    //   41: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: lload_2
    //   45: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   48: invokevirtual toString : ()Ljava/lang/String;
    //   51: astore #6
    //   53: new java/lang/IllegalArgumentException
    //   56: dup
    //   57: aload #6
    //   59: invokevirtual toString : ()Ljava/lang/String;
    //   62: invokespecial <init> : (Ljava/lang/String;)V
    //   65: athrow
    //   66: aload_0
    //   67: getfield closed : Z
    //   70: ifne -> 77
    //   73: iconst_1
    //   74: goto -> 78
    //   77: iconst_0
    //   78: ifne -> 101
    //   81: iconst_0
    //   82: istore #6
    //   84: ldc 'closed'
    //   86: astore #6
    //   88: new java/lang/IllegalStateException
    //   91: dup
    //   92: aload #6
    //   94: invokevirtual toString : ()Ljava/lang/String;
    //   97: invokespecial <init> : (Ljava/lang/String;)V
    //   100: athrow
    //   101: aload_0
    //   102: astore #5
    //   104: iconst_0
    //   105: istore #6
    //   107: aload #5
    //   109: getfield bufferField : Lokio/Buffer;
    //   112: invokevirtual size : ()J
    //   115: lconst_0
    //   116: lcmp
    //   117: ifne -> 158
    //   120: aload_0
    //   121: getfield source : Lokio/Source;
    //   124: aload_0
    //   125: astore #7
    //   127: iconst_0
    //   128: istore #8
    //   130: aload #7
    //   132: getfield bufferField : Lokio/Buffer;
    //   135: ldc2_w 8192
    //   138: invokeinterface read : (Lokio/Buffer;J)J
    //   143: lstore #5
    //   145: lload #5
    //   147: ldc2_w -1
    //   150: lcmp
    //   151: ifne -> 158
    //   154: ldc2_w -1
    //   157: lreturn
    //   158: aload_0
    //   159: astore #7
    //   161: iconst_0
    //   162: istore #8
    //   164: aload #7
    //   166: getfield bufferField : Lokio/Buffer;
    //   169: invokevirtual size : ()J
    //   172: lstore #7
    //   174: lload_2
    //   175: lload #7
    //   177: invokestatic min : (JJ)J
    //   180: lstore #5
    //   182: aload_0
    //   183: astore #7
    //   185: iconst_0
    //   186: istore #8
    //   188: aload #7
    //   190: getfield bufferField : Lokio/Buffer;
    //   193: aload_1
    //   194: lload #5
    //   196: invokevirtual read : (Lokio/Buffer;J)J
    //   199: lreturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #38	-> 15
    //   #403	-> 29
    //   #38	-> 32
    //   #38	-> 51
    //   #39	-> 66
    //   #403	-> 81
    //   #39	-> 84
    //   #39	-> 86
    //   #41	-> 101
    //   #404	-> 107
    //   #41	-> 112
    //   #42	-> 120
    //   #405	-> 130
    //   #42	-> 135
    //   #43	-> 145
    //   #46	-> 158
    //   #406	-> 164
    //   #46	-> 169
    //   #46	-> 180
    //   #47	-> 182
    //   #407	-> 188
    //   #47	-> 193
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   32	19	6	$i$a$-require--RealBufferedSource$commonRead$1	I
    //   84	2	6	$i$a$-check--RealBufferedSource$commonRead$2	I
    //   107	5	6	$i$f$getBuffer	I
    //   104	8	5	this_$iv	Lokio/RealBufferedSource;
    //   130	5	8	$i$f$getBuffer	I
    //   127	8	7	this_$iv	Lokio/RealBufferedSource;
    //   145	13	5	read	J
    //   164	5	8	$i$f$getBuffer	I
    //   161	8	7	this_$iv	Lokio/RealBufferedSource;
    //   188	5	8	$i$f$getBuffer	I
    //   185	8	7	this_$iv	Lokio/RealBufferedSource;
    //   15	185	4	$i$f$commonRead	I
    //   182	18	5	toRead	J
    //   0	200	0	$this$commonRead	Lokio/RealBufferedSource;
    //   0	200	1	sink	Lokio/Buffer;
    //   0	200	2	byteCount	J
  }
  
  public static final boolean commonExhausted(@NotNull RealBufferedSource $this$commonExhausted) {
    Intrinsics.checkNotNullParameter($this$commonExhausted, "<this>");
    int $i$f$commonExhausted = 0;
    if (!(!$this$commonExhausted.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonExhausted$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSource this_$iv = $this$commonExhausted;
    int $i$f$getBuffer = 0;
    if (this_$iv.bufferField.exhausted()) {
      this_$iv = $this$commonExhausted;
      $i$f$getBuffer = 0;
      if ($this$commonExhausted.source.read(this_$iv.bufferField, 8192L) == -1L);
    } 
    return false;
  }
  
  public static final void commonRequire(@NotNull RealBufferedSource $this$commonRequire, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRequire, "<this>");
    int $i$f$commonRequire = 0;
    if (!$this$commonRequire.request(byteCount))
      throw new EOFException(); 
  }
  
  public static final boolean commonRequest(@NotNull RealBufferedSource $this$commonRequest, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRequest, "<this>");
    int $i$f$commonRequest = 0;
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require--RealBufferedSource$commonRequest$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!$this$commonRequest.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonRequest$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (true) {
      RealBufferedSource this_$iv = $this$commonRequest;
      int $i$f$getBuffer = 0;
      if (this_$iv.bufferField.size() < byteCount) {
        this_$iv = $this$commonRequest;
        $i$f$getBuffer = 0;
        if ($this$commonRequest.source.read(this_$iv.bufferField, 8192L) == -1L)
          return false; 
        continue;
      } 
      break;
    } 
    return true;
  }
  
  public static final byte commonReadByte(@NotNull RealBufferedSource $this$commonReadByte) {
    Intrinsics.checkNotNullParameter($this$commonReadByte, "<this>");
    int $i$f$commonReadByte = 0;
    $this$commonReadByte.require(1L);
    RealBufferedSource this_$iv = $this$commonReadByte;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readByte();
  }
  
  @NotNull
  public static final ByteString commonReadByteString(@NotNull RealBufferedSource $this$commonReadByteString) {
    Intrinsics.checkNotNullParameter($this$commonReadByteString, "<this>");
    int $i$f$commonReadByteString = 0;
    RealBufferedSource this_$iv = $this$commonReadByteString;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeAll($this$commonReadByteString.source);
    this_$iv = $this$commonReadByteString;
    $i$f$getBuffer = 0;
    return this_$iv.bufferField.readByteString();
  }
  
  @NotNull
  public static final ByteString commonReadByteString(@NotNull RealBufferedSource $this$commonReadByteString, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadByteString, "<this>");
    int $i$f$commonReadByteString = 0;
    $this$commonReadByteString.require(byteCount);
    RealBufferedSource this_$iv = $this$commonReadByteString;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readByteString(byteCount);
  }
  
  public static final int commonSelect(@NotNull RealBufferedSource $this$commonSelect, @NotNull Options options) {
    int index;
    Intrinsics.checkNotNullParameter($this$commonSelect, "<this>");
    Intrinsics.checkNotNullParameter(options, "options");
    int $i$f$commonSelect = 0;
    if (!(!$this$commonSelect.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonSelect$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (true) {
      RealBufferedSource realBufferedSource2;
      int j;
      RealBufferedSource realBufferedSource1 = $this$commonSelect;
      int i = 0;
      index = -Buffer.selectPrefix(realBufferedSource1.bufferField, options, true);
      switch (index) {
        case -1:
          return -1;
        case -2:
          realBufferedSource2 = $this$commonSelect;
          j = 0;
          if ($this$commonSelect.source.read(realBufferedSource2.bufferField, 8192L) == -1L)
            return -1; 
          continue;
      } 
      break;
    } 
    int selectedSize = options.getByteStrings$okio()[index].size();
    RealBufferedSource this_$iv = $this$commonSelect;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.skip(selectedSize);
    return index;
  }
  
  @NotNull
  public static final byte[] commonReadByteArray(@NotNull RealBufferedSource $this$commonReadByteArray) {
    Intrinsics.checkNotNullParameter($this$commonReadByteArray, "<this>");
    int $i$f$commonReadByteArray = 0;
    RealBufferedSource this_$iv = $this$commonReadByteArray;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeAll($this$commonReadByteArray.source);
    this_$iv = $this$commonReadByteArray;
    $i$f$getBuffer = 0;
    return this_$iv.bufferField.readByteArray();
  }
  
  @NotNull
  public static final byte[] commonReadByteArray(@NotNull RealBufferedSource $this$commonReadByteArray, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadByteArray, "<this>");
    int $i$f$commonReadByteArray = 0;
    $this$commonReadByteArray.require(byteCount);
    RealBufferedSource this_$iv = $this$commonReadByteArray;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readByteArray(byteCount);
  }
  
  public static final void commonReadFully(@NotNull RealBufferedSource $this$commonReadFully, @NotNull byte[] sink) {
    Intrinsics.checkNotNullParameter($this$commonReadFully, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonReadFully = 0;
    try {
      $this$commonReadFully.require(sink.length);
    } catch (EOFException e) {
      int offset = 0;
      while (true) {
        RealBufferedSource realBufferedSource = $this$commonReadFully;
        int i = 0;
        if (realBufferedSource.bufferField.size() > 0L) {
          RealBufferedSource realBufferedSource1 = $this$commonReadFully;
          int j = 0;
          realBufferedSource1 = $this$commonReadFully;
          j = 0;
        } 
        break;
      } 
      throw e;
    } 
    RealBufferedSource this_$iv = $this$commonReadFully;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.readFully(sink);
  }
  
  public static final int commonRead(@NotNull RealBufferedSource $this$commonRead, @NotNull byte[] sink, int offset, int byteCount) {
    // Byte code:
    //   0: aload_0
    //   1: ldc '<this>'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 'sink'
    //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   12: iconst_0
    //   13: istore #4
    //   15: aload_1
    //   16: arraylength
    //   17: i2l
    //   18: iload_2
    //   19: i2l
    //   20: iload_3
    //   21: i2l
    //   22: invokestatic checkOffsetAndCount : (JJJ)V
    //   25: aload_0
    //   26: astore #5
    //   28: iconst_0
    //   29: istore #6
    //   31: aload #5
    //   33: getfield bufferField : Lokio/Buffer;
    //   36: invokevirtual size : ()J
    //   39: lconst_0
    //   40: lcmp
    //   41: ifne -> 80
    //   44: aload_0
    //   45: getfield source : Lokio/Source;
    //   48: aload_0
    //   49: astore #7
    //   51: iconst_0
    //   52: istore #8
    //   54: aload #7
    //   56: getfield bufferField : Lokio/Buffer;
    //   59: ldc2_w 8192
    //   62: invokeinterface read : (Lokio/Buffer;J)J
    //   67: lstore #5
    //   69: lload #5
    //   71: ldc2_w -1
    //   74: lcmp
    //   75: ifne -> 80
    //   78: iconst_m1
    //   79: ireturn
    //   80: aload_0
    //   81: astore #6
    //   83: iconst_0
    //   84: istore #7
    //   86: aload #6
    //   88: getfield bufferField : Lokio/Buffer;
    //   91: invokevirtual size : ()J
    //   94: lstore #6
    //   96: iconst_0
    //   97: istore #8
    //   99: iload_3
    //   100: i2l
    //   101: lload #6
    //   103: invokestatic min : (JJ)J
    //   106: nop
    //   107: l2i
    //   108: istore #5
    //   110: aload_0
    //   111: astore #6
    //   113: iconst_0
    //   114: istore #7
    //   116: aload #6
    //   118: getfield bufferField : Lokio/Buffer;
    //   121: aload_1
    //   122: iload_2
    //   123: iload #5
    //   125: invokevirtual read : ([BII)I
    //   128: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #134	-> 15
    //   #136	-> 25
    //   #424	-> 31
    //   #136	-> 36
    //   #137	-> 44
    //   #425	-> 54
    //   #137	-> 59
    //   #138	-> 69
    //   #141	-> 80
    //   #426	-> 86
    //   #141	-> 91
    //   #427	-> 99
    //   #427	-> 106
    //   #141	-> 107
    //   #142	-> 110
    //   #428	-> 116
    //   #142	-> 121
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   31	5	6	$i$f$getBuffer	I
    //   28	8	5	this_$iv	Lokio/RealBufferedSource;
    //   54	5	8	$i$f$getBuffer	I
    //   51	8	7	this_$iv	Lokio/RealBufferedSource;
    //   69	11	5	read	J
    //   86	5	7	$i$f$getBuffer	I
    //   83	8	6	this_$iv	Lokio/RealBufferedSource;
    //   99	8	8	$i$f$minOf	I
    //   96	11	6	b$iv	J
    //   116	5	7	$i$f$getBuffer	I
    //   113	8	6	this_$iv	Lokio/RealBufferedSource;
    //   15	114	4	$i$f$commonRead	I
    //   110	19	5	toRead	I
    //   0	129	0	$this$commonRead	Lokio/RealBufferedSource;
    //   0	129	1	sink	[B
    //   0	129	2	offset	I
    //   0	129	3	byteCount	I
  }
  
  public static final void commonReadFully(@NotNull RealBufferedSource $this$commonReadFully, @NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadFully, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonReadFully = 0;
    try {
      $this$commonReadFully.require(byteCount);
    } catch (EOFException e) {
      RealBufferedSource realBufferedSource = $this$commonReadFully;
      int i = 0;
      sink.writeAll((Source)realBufferedSource.bufferField);
      throw e;
    } 
    RealBufferedSource this_$iv = $this$commonReadFully;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.readFully(sink, byteCount);
  }
  
  public static final long commonReadAll(@NotNull RealBufferedSource $this$commonReadAll, @NotNull Sink sink) {
    Intrinsics.checkNotNullParameter($this$commonReadAll, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonReadAll = 0;
    long totalBytesWritten = 0L;
    while (true) {
      RealBufferedSource realBufferedSource = $this$commonReadAll;
      int i = 0;
      if ($this$commonReadAll.source.read(realBufferedSource.bufferField, 8192L) != -1L) {
        RealBufferedSource realBufferedSource1 = $this$commonReadAll;
        int j = 0;
        long emitByteCount = realBufferedSource1.bufferField.completeSegmentByteCount();
      } 
      break;
    } 
    RealBufferedSource this_$iv = $this$commonReadAll;
    int $i$f$getBuffer = 0;
    if (this_$iv.bufferField.size() > 0L) {
      this_$iv = $this$commonReadAll;
      $i$f$getBuffer = 0;
      totalBytesWritten += this_$iv.bufferField.size();
      this_$iv = $this$commonReadAll;
      $i$f$getBuffer = 0;
    } 
    return totalBytesWritten;
  }
  
  @NotNull
  public static final String commonReadUtf8(@NotNull RealBufferedSource $this$commonReadUtf8) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8, "<this>");
    int $i$f$commonReadUtf8 = 0;
    RealBufferedSource this_$iv = $this$commonReadUtf8;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeAll($this$commonReadUtf8.source);
    this_$iv = $this$commonReadUtf8;
    $i$f$getBuffer = 0;
    return this_$iv.bufferField.readUtf8();
  }
  
  @NotNull
  public static final String commonReadUtf8(@NotNull RealBufferedSource $this$commonReadUtf8, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8, "<this>");
    int $i$f$commonReadUtf8 = 0;
    $this$commonReadUtf8.require(byteCount);
    RealBufferedSource this_$iv = $this$commonReadUtf8;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readUtf8(byteCount);
  }
  
  @Nullable
  public static final String commonReadUtf8Line(@NotNull RealBufferedSource $this$commonReadUtf8Line) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8Line, "<this>");
    int $i$f$commonReadUtf8Line = 0;
    long newline = $this$commonReadUtf8Line.indexOf((byte)10);
    RealBufferedSource this_$iv = $this$commonReadUtf8Line;
    int $i$f$getBuffer = 0;
    if (this_$iv.bufferField.size() != 0L) {
      this_$iv = $this$commonReadUtf8Line;
      $i$f$getBuffer = 0;
    } 
    this_$iv = $this$commonReadUtf8Line;
    $i$f$getBuffer = 0;
    return (newline == -1L) ? null : -Buffer.readUtf8Line(this_$iv.bufferField, newline);
  }
  
  @NotNull
  public static final String commonReadUtf8LineStrict(@NotNull RealBufferedSource $this$commonReadUtf8LineStrict, long limit) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8LineStrict, "<this>");
    int $i$f$commonReadUtf8LineStrict = 0;
    if (!((limit >= 0L) ? 1 : 0)) {
      int $i$a$-require--RealBufferedSource$commonReadUtf8LineStrict$1 = 0;
      String str = "limit < 0: " + limit;
      throw new IllegalArgumentException(str.toString());
    } 
    long scanLength = (limit == Long.MAX_VALUE) ? Long.MAX_VALUE : (limit + 1L);
    long newline = $this$commonReadUtf8LineStrict.indexOf((byte)10, 0L, scanLength);
    if (newline != -1L) {
      RealBufferedSource realBufferedSource = $this$commonReadUtf8LineStrict;
      int k = 0;
      return -Buffer.readUtf8Line(realBufferedSource.bufferField, newline);
    } 
    if (scanLength < Long.MAX_VALUE && $this$commonReadUtf8LineStrict.request(scanLength)) {
      RealBufferedSource realBufferedSource = $this$commonReadUtf8LineStrict;
      int k = 0;
      if (realBufferedSource.bufferField.getByte(scanLength - 1L) == 13 && $this$commonReadUtf8LineStrict.request(scanLength + 1L)) {
        realBufferedSource = $this$commonReadUtf8LineStrict;
        k = 0;
        if (realBufferedSource.bufferField.getByte(scanLength) == 10) {
          realBufferedSource = $this$commonReadUtf8LineStrict;
          k = 0;
          return -Buffer.readUtf8Line(realBufferedSource.bufferField, scanLength);
        } 
      } 
    } 
    Buffer data = new Buffer();
    RealBufferedSource realBufferedSource1 = $this$commonReadUtf8LineStrict;
    int i = 0;
    byte b = 32;
    RealBufferedSource realBufferedSource2 = $this$commonReadUtf8LineStrict;
    int j = 0;
    long b$iv = realBufferedSource2.bufferField.size();
    int $i$f$minOf = 0;
    realBufferedSource1.bufferField.copyTo(data, 0L, 
        Math.min(b, b$iv));
    RealBufferedSource this_$iv = $this$commonReadUtf8LineStrict;
    int $i$f$getBuffer = 0;
    throw new EOFException("\\n not found: limit=" + Math.min(this_$iv.bufferField.size(), limit) + " content=" + data.readByteString().hex() + '…');
  }
  
  public static final int commonReadUtf8CodePoint(@NotNull RealBufferedSource $this$commonReadUtf8CodePoint) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8CodePoint, "<this>");
    int $i$f$commonReadUtf8CodePoint = 0;
    $this$commonReadUtf8CodePoint.require(1L);
    RealBufferedSource this_$iv = $this$commonReadUtf8CodePoint;
    int $i$f$getBuffer = 0, b0 = this_$iv.bufferField.getByte(0L);
    if ((b0 & 0xE0) == 192) {
      $this$commonReadUtf8CodePoint.require(2L);
    } else if ((b0 & 0xF0) == 224) {
      $this$commonReadUtf8CodePoint.require(3L);
    } else if ((b0 & 0xF8) == 240) {
      $this$commonReadUtf8CodePoint.require(4L);
    } 
    this_$iv = $this$commonReadUtf8CodePoint;
    $i$f$getBuffer = 0;
    return this_$iv.bufferField.readUtf8CodePoint();
  }
  
  public static final short commonReadShort(@NotNull RealBufferedSource $this$commonReadShort) {
    Intrinsics.checkNotNullParameter($this$commonReadShort, "<this>");
    int $i$f$commonReadShort = 0;
    $this$commonReadShort.require(2L);
    RealBufferedSource this_$iv = $this$commonReadShort;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readShort();
  }
  
  public static final short commonReadShortLe(@NotNull RealBufferedSource $this$commonReadShortLe) {
    Intrinsics.checkNotNullParameter($this$commonReadShortLe, "<this>");
    int $i$f$commonReadShortLe = 0;
    $this$commonReadShortLe.require(2L);
    RealBufferedSource this_$iv = $this$commonReadShortLe;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readShortLe();
  }
  
  public static final int commonReadInt(@NotNull RealBufferedSource $this$commonReadInt) {
    Intrinsics.checkNotNullParameter($this$commonReadInt, "<this>");
    int $i$f$commonReadInt = 0;
    $this$commonReadInt.require(4L);
    RealBufferedSource this_$iv = $this$commonReadInt;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readInt();
  }
  
  public static final int commonReadIntLe(@NotNull RealBufferedSource $this$commonReadIntLe) {
    Intrinsics.checkNotNullParameter($this$commonReadIntLe, "<this>");
    int $i$f$commonReadIntLe = 0;
    $this$commonReadIntLe.require(4L);
    RealBufferedSource this_$iv = $this$commonReadIntLe;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readIntLe();
  }
  
  public static final long commonReadLong(@NotNull RealBufferedSource $this$commonReadLong) {
    Intrinsics.checkNotNullParameter($this$commonReadLong, "<this>");
    int $i$f$commonReadLong = 0;
    $this$commonReadLong.require(8L);
    RealBufferedSource this_$iv = $this$commonReadLong;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readLong();
  }
  
  public static final long commonReadLongLe(@NotNull RealBufferedSource $this$commonReadLongLe) {
    Intrinsics.checkNotNullParameter($this$commonReadLongLe, "<this>");
    int $i$f$commonReadLongLe = 0;
    $this$commonReadLongLe.require(8L);
    RealBufferedSource this_$iv = $this$commonReadLongLe;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readLongLe();
  }
  
  public static final long commonReadDecimalLong(@NotNull RealBufferedSource $this$commonReadDecimalLong) {
    Intrinsics.checkNotNullParameter($this$commonReadDecimalLong, "<this>");
    int $i$f$commonReadDecimalLong = 0;
    $this$commonReadDecimalLong.require(1L);
    long pos = 0L;
    while ($this$commonReadDecimalLong.request(pos + 1L)) {
      RealBufferedSource realBufferedSource = $this$commonReadDecimalLong;
      int i = 0;
      byte b = realBufferedSource.bufferField.getByte(pos);
      if ((b < 48 || b > 57) && (pos != 0L || b != 45)) {
        if (pos == 0L) {
          Intrinsics.checkNotNullExpressionValue(Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16))), "toString(this, checkRadix(radix))");
          throw new NumberFormatException("Expected a digit or '-' but was 0x" + Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16))));
        } 
        break;
      } 
      long l = pos;
      pos = l + 1L;
    } 
    RealBufferedSource this_$iv = $this$commonReadDecimalLong;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readDecimalLong();
  }
  
  public static final long commonReadHexadecimalUnsignedLong(@NotNull RealBufferedSource $this$commonReadHexadecimalUnsignedLong) {
    Intrinsics.checkNotNullParameter($this$commonReadHexadecimalUnsignedLong, "<this>");
    int $i$f$commonReadHexadecimalUnsignedLong = 0;
    $this$commonReadHexadecimalUnsignedLong.require(1L);
    int pos = 0;
    while ($this$commonReadHexadecimalUnsignedLong.request((pos + 1))) {
      RealBufferedSource realBufferedSource = $this$commonReadHexadecimalUnsignedLong;
      int i = 0;
      byte b = realBufferedSource.bufferField.getByte(pos);
      if ((b < 48 || b > 57) && (b < 97 || b > 102) && (b < 65 || b > 70)) {
        if (pos == 0) {
          Intrinsics.checkNotNullExpressionValue(Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16))), "toString(this, checkRadix(radix))");
          throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16))));
        } 
        break;
      } 
      pos++;
    } 
    RealBufferedSource this_$iv = $this$commonReadHexadecimalUnsignedLong;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readHexadecimalUnsignedLong();
  }
  
  public static final void commonSkip(@NotNull RealBufferedSource $this$commonSkip, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonSkip, "<this>");
    int $i$f$commonSkip = 0;
    long l = byteCount;
    if (!(!$this$commonSkip.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonSkip$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (l > 0L) {
      RealBufferedSource this_$iv = $this$commonSkip;
      int $i$f$getBuffer = 0;
      if (this_$iv.bufferField.size() == 0L) {
        this_$iv = $this$commonSkip;
        $i$f$getBuffer = 0;
        if ($this$commonSkip.source.read(this_$iv.bufferField, 8192L) == -1L)
          throw new EOFException(); 
      } 
      RealBufferedSource realBufferedSource2 = $this$commonSkip;
      int i = 0;
      long l1 = realBufferedSource2.bufferField.size(), toSkip = Math.min(l, l1);
      RealBufferedSource realBufferedSource1 = $this$commonSkip;
      i = 0;
      realBufferedSource1.bufferField.skip(toSkip);
      l -= toSkip;
    } 
  }
  
  public static final long commonIndexOf(@NotNull RealBufferedSource $this$commonIndexOf, byte b, long fromIndex, long toIndex) {
    Intrinsics.checkNotNullParameter($this$commonIndexOf, "<this>");
    int $i$f$commonIndexOf = 0;
    long l = 0L;
    l = fromIndex;
    if (!(!$this$commonIndexOf.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonIndexOf$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    if (!((0L <= l) ? ((l <= toIndex) ? 1 : 0) : 0)) {
      int $i$a$-require--RealBufferedSource$commonIndexOf$2 = 0;
      String str = "fromIndex=" + l + " toIndex=" + toIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    while (l < toIndex) {
      RealBufferedSource this_$iv = $this$commonIndexOf;
      int $i$f$getBuffer = 0;
      long result = this_$iv.bufferField.indexOf(b, l, toIndex);
      if (result != -1L)
        return result; 
      RealBufferedSource realBufferedSource1 = $this$commonIndexOf;
      int i = 0;
      long lastBufferSize = realBufferedSource1.bufferField.size();
      if (lastBufferSize < toIndex) {
        realBufferedSource1 = $this$commonIndexOf;
        i = 0;
        if ($this$commonIndexOf.source.read(realBufferedSource1.bufferField, 8192L) != -1L) {
          l = Math.max(l, lastBufferSize);
          continue;
        } 
      } 
      return -1L;
    } 
    return -1L;
  }
  
  public static final long commonIndexOf(@NotNull RealBufferedSource $this$commonIndexOf, @NotNull ByteString bytes, long fromIndex) {
    Intrinsics.checkNotNullParameter($this$commonIndexOf, "<this>");
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    int $i$f$commonIndexOf = 0;
    long l = fromIndex;
    if (!(!$this$commonIndexOf.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonIndexOf$3 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (true) {
      RealBufferedSource this_$iv = $this$commonIndexOf;
      int $i$f$getBuffer = 0;
      long result = this_$iv.bufferField.indexOf(bytes, l);
      if (result != -1L)
        return result; 
      RealBufferedSource realBufferedSource1 = $this$commonIndexOf;
      int i = 0;
      long lastBufferSize = realBufferedSource1.bufferField.size();
      realBufferedSource1 = $this$commonIndexOf;
      i = 0;
      if ($this$commonIndexOf.source.read(realBufferedSource1.bufferField, 8192L) == -1L)
        return -1L; 
      l = Math.max(l, lastBufferSize - bytes.size() + 1L);
    } 
  }
  
  public static final long commonIndexOfElement(@NotNull RealBufferedSource $this$commonIndexOfElement, @NotNull ByteString targetBytes, long fromIndex) {
    Intrinsics.checkNotNullParameter($this$commonIndexOfElement, "<this>");
    Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
    int $i$f$commonIndexOfElement = 0;
    long l = fromIndex;
    if (!(!$this$commonIndexOfElement.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonIndexOfElement$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (true) {
      RealBufferedSource this_$iv = $this$commonIndexOfElement;
      int $i$f$getBuffer = 0;
      long result = this_$iv.bufferField.indexOfElement(targetBytes, l);
      if (result != -1L)
        return result; 
      RealBufferedSource realBufferedSource1 = $this$commonIndexOfElement;
      int i = 0;
      long lastBufferSize = realBufferedSource1.bufferField.size();
      realBufferedSource1 = $this$commonIndexOfElement;
      i = 0;
      if ($this$commonIndexOfElement.source.read(realBufferedSource1.bufferField, 8192L) == -1L)
        return -1L; 
      l = Math.max(l, lastBufferSize);
    } 
  }
  
  public static final boolean commonRangeEquals(@NotNull RealBufferedSource $this$commonRangeEquals, long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    int $i$f$commonRangeEquals = 0;
    if (!(!$this$commonRangeEquals.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonRangeEquals$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    if (offset < 0L || bytesOffset < 0 || byteCount < 0 || bytes.size() - bytesOffset < byteCount)
      return false; 
    for (int i = 0; i < byteCount; i++) {
      long bufferOffset = offset + i;
      if (!$this$commonRangeEquals.request(bufferOffset + 1L))
        return false; 
      RealBufferedSource this_$iv = $this$commonRangeEquals;
      int $i$f$getBuffer = 0;
      if (this_$iv.bufferField.getByte(bufferOffset) != bytes.getByte(bytesOffset + i))
        return false; 
    } 
    return true;
  }
  
  @NotNull
  public static final BufferedSource commonPeek(@NotNull RealBufferedSource $this$commonPeek) {
    Intrinsics.checkNotNullParameter($this$commonPeek, "<this>");
    int $i$f$commonPeek = 0;
    return Okio.buffer((Source)new PeekSource((BufferedSource)$this$commonPeek));
  }
  
  public static final void commonClose(@NotNull RealBufferedSource $this$commonClose) {
    Intrinsics.checkNotNullParameter($this$commonClose, "<this>");
    int $i$f$commonClose = 0;
    if ($this$commonClose.closed)
      return; 
    $this$commonClose.closed = true;
    $this$commonClose.source.close();
    RealBufferedSource this_$iv = $this$commonClose;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.clear();
  }
  
  @NotNull
  public static final Timeout commonTimeout(@NotNull RealBufferedSource $this$commonTimeout) {
    Intrinsics.checkNotNullParameter($this$commonTimeout, "<this>");
    int $i$f$commonTimeout = 0;
    return $this$commonTimeout.source.timeout();
  }
  
  @NotNull
  public static final String commonToString(@NotNull RealBufferedSource $this$commonToString) {
    Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
    int $i$f$commonToString = 0;
    return "buffer(" + $this$commonToString.source + ')';
  }
}
