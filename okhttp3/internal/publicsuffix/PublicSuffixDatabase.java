package okhttp3.internal.publicsuffix;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020 \n\002\020\016\n\002\b\006\n\002\020\002\n\002\b\002\n\002\020\022\n\002\b\006\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\030\000 \0352\0020\001:\001\035B\007¢\006\004\b\002\020\003J#\020\007\032\b\022\004\022\0020\0050\0042\f\020\006\032\b\022\004\022\0020\0050\004H\002¢\006\004\b\007\020\bJ\027\020\n\032\004\030\0010\0052\006\020\t\032\0020\005¢\006\004\b\n\020\013J\017\020\r\032\0020\fH\002¢\006\004\b\r\020\003J\017\020\016\032\0020\fH\002¢\006\004\b\016\020\003J\035\020\022\032\0020\f2\006\020\020\032\0020\0172\006\020\021\032\0020\017¢\006\004\b\022\020\023J\035\020\024\032\b\022\004\022\0020\0050\0042\006\020\t\032\0020\005H\002¢\006\004\b\024\020\025R\024\020\027\032\0020\0268\002X\004¢\006\006\n\004\b\027\020\030R\026\020\021\032\0020\0178\002@\002X.¢\006\006\n\004\b\021\020\031R\026\020\020\032\0020\0178\002@\002X.¢\006\006\n\004\b\020\020\031R\024\020\033\032\0020\0328\002X\004¢\006\006\n\004\b\033\020\034¨\006\036"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "<init>", "()V", "", "", "domainLabels", "findMatchingRule", "(Ljava/util/List;)Ljava/util/List;", "domain", "getEffectiveTldPlusOne", "(Ljava/lang/String;)Ljava/lang/String;", "", "readTheList", "readTheListUninterruptibly", "", "publicSuffixListBytes", "publicSuffixExceptionListBytes", "setListBytes", "([B[B)V", "splitDomain", "(Ljava/lang/String;)Ljava/util/List;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "[B", "Ljava/util/concurrent/CountDownLatch;", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "Companion", "okhttp"})
public final class PublicSuffixDatabase {
  @NotNull
  private final AtomicBoolean listRead = new AtomicBoolean(false);
  
  @NotNull
  private final CountDownLatch readCompleteLatch = new CountDownLatch(1);
  
  @Nullable
  public final String getEffectiveTldPlusOne(@NotNull String domain) {
    Intrinsics.checkNotNullParameter(domain, "domain");
    String unicodeDomain = IDN.toUnicode(domain);
    Intrinsics.checkNotNullExpressionValue(unicodeDomain, "unicodeDomain");
    List<String> domainLabels = splitDomain(unicodeDomain);
    List<String> rule = findMatchingRule(domainLabels);
    if (domainLabels.size() == rule.size() && ((String)rule.get(0)).charAt(0) != '!')
      return null; 
    int firstLabelOffset = (((String)rule.get(0)).charAt(0) == '!') ? (
      
      domainLabels.size() - rule.size()) : (
      
      domainLabels.size() - rule.size() + 1);
    return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(domain)), firstLabelOffset), ".", null, null, 0, null, null, 62, null);
  }
  
  private final List<String> splitDomain(String domain) {
    char[] arrayOfChar = new char[1];
    arrayOfChar[0] = '.';
    List<String> domainLabels = StringsKt.split$default(domain, arrayOfChar, false, 0, 6, null);
    if (Intrinsics.areEqual(CollectionsKt.last(domainLabels), ""))
      return CollectionsKt.dropLast(domainLabels, 1); 
    return domainLabels;
  }
  
  private final List<String> findMatchingRule(List domainLabels) {
    // Byte code:
    //   0: aload_0
    //   1: getfield listRead : Ljava/util/concurrent/atomic/AtomicBoolean;
    //   4: invokevirtual get : ()Z
    //   7: ifne -> 29
    //   10: aload_0
    //   11: getfield listRead : Ljava/util/concurrent/atomic/AtomicBoolean;
    //   14: iconst_0
    //   15: iconst_1
    //   16: invokevirtual compareAndSet : (ZZ)Z
    //   19: ifeq -> 29
    //   22: aload_0
    //   23: invokespecial readTheListUninterruptibly : ()V
    //   26: goto -> 47
    //   29: nop
    //   30: aload_0
    //   31: getfield readCompleteLatch : Ljava/util/concurrent/CountDownLatch;
    //   34: invokevirtual await : ()V
    //   37: goto -> 47
    //   40: astore_2
    //   41: invokestatic currentThread : ()Ljava/lang/Thread;
    //   44: invokevirtual interrupt : ()V
    //   47: aload_0
    //   48: getfield publicSuffixListBytes : [B
    //   51: ifnull -> 58
    //   54: iconst_1
    //   55: goto -> 59
    //   58: iconst_0
    //   59: ifne -> 79
    //   62: iconst_0
    //   63: istore_3
    //   64: ldc 'Unable to load publicsuffixes.gz resource from the classpath.'
    //   66: astore_3
    //   67: new java/lang/IllegalStateException
    //   70: dup
    //   71: aload_3
    //   72: invokevirtual toString : ()Ljava/lang/String;
    //   75: invokespecial <init> : (Ljava/lang/String;)V
    //   78: athrow
    //   79: iconst_0
    //   80: istore_3
    //   81: aload_1
    //   82: invokeinterface size : ()I
    //   87: istore #4
    //   89: iload #4
    //   91: anewarray [B
    //   94: astore #5
    //   96: iload_3
    //   97: iload #4
    //   99: if_icmpge -> 150
    //   102: iload_3
    //   103: istore #6
    //   105: aload #5
    //   107: iload #6
    //   109: aload_1
    //   110: iload #6
    //   112: invokeinterface get : (I)Ljava/lang/Object;
    //   117: checkcast java/lang/String
    //   120: astore #7
    //   122: getstatic java/nio/charset/StandardCharsets.UTF_8 : Ljava/nio/charset/Charset;
    //   125: dup
    //   126: ldc 'UTF_8'
    //   128: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   131: aload #7
    //   133: swap
    //   134: invokevirtual getBytes : (Ljava/nio/charset/Charset;)[B
    //   137: dup
    //   138: ldc 'this as java.lang.String).getBytes(charset)'
    //   140: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   143: aastore
    //   144: iinc #3, 1
    //   147: goto -> 96
    //   150: aload #5
    //   152: astore_2
    //   153: aconst_null
    //   154: astore_3
    //   155: iconst_0
    //   156: istore #4
    //   158: aload_2
    //   159: checkcast [Ljava/lang/Object;
    //   162: arraylength
    //   163: istore #5
    //   165: iload #4
    //   167: iload #5
    //   169: if_icmpge -> 215
    //   172: getstatic okhttp3/internal/publicsuffix/PublicSuffixDatabase.Companion : Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;
    //   175: aload_0
    //   176: getfield publicSuffixListBytes : [B
    //   179: dup
    //   180: ifnonnull -> 190
    //   183: pop
    //   184: ldc 'publicSuffixListBytes'
    //   186: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   189: aconst_null
    //   190: aload_2
    //   191: iload #4
    //   193: invokestatic access$binarySearch : (Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;[B[[BI)Ljava/lang/String;
    //   196: astore #6
    //   198: aload #6
    //   200: ifnull -> 209
    //   203: aload #6
    //   205: astore_3
    //   206: goto -> 215
    //   209: iinc #4, 1
    //   212: goto -> 165
    //   215: aconst_null
    //   216: astore #4
    //   218: aload_2
    //   219: checkcast [Ljava/lang/Object;
    //   222: arraylength
    //   223: iconst_1
    //   224: if_icmple -> 312
    //   227: aload_2
    //   228: checkcast [Ljava/lang/Object;
    //   231: invokevirtual clone : ()Ljava/lang/Object;
    //   234: checkcast [[B
    //   237: astore #5
    //   239: iconst_0
    //   240: istore #6
    //   242: aload #5
    //   244: checkcast [Ljava/lang/Object;
    //   247: arraylength
    //   248: iconst_1
    //   249: isub
    //   250: istore #7
    //   252: iload #6
    //   254: iload #7
    //   256: if_icmpge -> 312
    //   259: aload #5
    //   261: iload #6
    //   263: getstatic okhttp3/internal/publicsuffix/PublicSuffixDatabase.WILDCARD_LABEL : [B
    //   266: aastore
    //   267: getstatic okhttp3/internal/publicsuffix/PublicSuffixDatabase.Companion : Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;
    //   270: aload_0
    //   271: getfield publicSuffixListBytes : [B
    //   274: dup
    //   275: ifnonnull -> 285
    //   278: pop
    //   279: ldc 'publicSuffixListBytes'
    //   281: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   284: aconst_null
    //   285: aload #5
    //   287: iload #6
    //   289: invokestatic access$binarySearch : (Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;[B[[BI)Ljava/lang/String;
    //   292: astore #8
    //   294: aload #8
    //   296: ifnull -> 306
    //   299: aload #8
    //   301: astore #4
    //   303: goto -> 312
    //   306: iinc #6, 1
    //   309: goto -> 252
    //   312: aconst_null
    //   313: astore #5
    //   315: aload #4
    //   317: ifnull -> 383
    //   320: iconst_0
    //   321: istore #6
    //   323: aload_2
    //   324: checkcast [Ljava/lang/Object;
    //   327: arraylength
    //   328: iconst_1
    //   329: isub
    //   330: istore #7
    //   332: iload #6
    //   334: iload #7
    //   336: if_icmpge -> 383
    //   339: getstatic okhttp3/internal/publicsuffix/PublicSuffixDatabase.Companion : Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;
    //   342: aload_0
    //   343: getfield publicSuffixExceptionListBytes : [B
    //   346: dup
    //   347: ifnonnull -> 357
    //   350: pop
    //   351: ldc 'publicSuffixExceptionListBytes'
    //   353: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   356: aconst_null
    //   357: aload_2
    //   358: iload #6
    //   360: invokestatic access$binarySearch : (Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;[B[[BI)Ljava/lang/String;
    //   363: astore #8
    //   365: aload #8
    //   367: ifnull -> 377
    //   370: aload #8
    //   372: astore #5
    //   374: goto -> 383
    //   377: iinc #6, 1
    //   380: goto -> 332
    //   383: aload #5
    //   385: ifnull -> 437
    //   388: new java/lang/StringBuilder
    //   391: dup
    //   392: invokespecial <init> : ()V
    //   395: bipush #33
    //   397: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   400: aload #5
    //   402: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: invokevirtual toString : ()Ljava/lang/String;
    //   408: astore #5
    //   410: aload #5
    //   412: checkcast java/lang/CharSequence
    //   415: iconst_1
    //   416: newarray char
    //   418: astore #6
    //   420: aload #6
    //   422: iconst_0
    //   423: bipush #46
    //   425: castore
    //   426: aload #6
    //   428: iconst_0
    //   429: iconst_0
    //   430: bipush #6
    //   432: aconst_null
    //   433: invokestatic split$default : (Ljava/lang/CharSequence;[CZIILjava/lang/Object;)Ljava/util/List;
    //   436: areturn
    //   437: aload_3
    //   438: ifnonnull -> 450
    //   441: aload #4
    //   443: ifnonnull -> 450
    //   446: getstatic okhttp3/internal/publicsuffix/PublicSuffixDatabase.PREVAILING_RULE : Ljava/util/List;
    //   449: areturn
    //   450: aload_3
    //   451: dup
    //   452: ifnull -> 483
    //   455: checkcast java/lang/CharSequence
    //   458: iconst_1
    //   459: newarray char
    //   461: astore #9
    //   463: aload #9
    //   465: iconst_0
    //   466: bipush #46
    //   468: castore
    //   469: aload #9
    //   471: iconst_0
    //   472: iconst_0
    //   473: bipush #6
    //   475: aconst_null
    //   476: invokestatic split$default : (Ljava/lang/CharSequence;[CZIILjava/lang/Object;)Ljava/util/List;
    //   479: dup
    //   480: ifnonnull -> 487
    //   483: pop
    //   484: invokestatic emptyList : ()Ljava/util/List;
    //   487: astore #6
    //   489: aload #4
    //   491: dup
    //   492: ifnull -> 523
    //   495: checkcast java/lang/CharSequence
    //   498: iconst_1
    //   499: newarray char
    //   501: astore #10
    //   503: aload #10
    //   505: iconst_0
    //   506: bipush #46
    //   508: castore
    //   509: aload #10
    //   511: iconst_0
    //   512: iconst_0
    //   513: bipush #6
    //   515: aconst_null
    //   516: invokestatic split$default : (Ljava/lang/CharSequence;[CZIILjava/lang/Object;)Ljava/util/List;
    //   519: dup
    //   520: ifnonnull -> 527
    //   523: pop
    //   524: invokestatic emptyList : ()Ljava/util/List;
    //   527: astore #7
    //   529: aload #6
    //   531: invokeinterface size : ()I
    //   536: aload #7
    //   538: invokeinterface size : ()I
    //   543: if_icmple -> 551
    //   546: aload #6
    //   548: goto -> 553
    //   551: aload #7
    //   553: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #100	-> 0
    //   #101	-> 22
    //   #103	-> 29
    //   #104	-> 30
    //   #105	-> 40
    //   #106	-> 41
    //   #110	-> 47
    //   #111	-> 64
    //   #110	-> 66
    //   #115	-> 79
    //   #115	-> 147
    //   #119	-> 153
    //   #120	-> 155
    //   #121	-> 172
    //   #122	-> 198
    //   #123	-> 203
    //   #124	-> 206
    //   #120	-> 209
    //   #133	-> 215
    //   #134	-> 218
    //   #135	-> 227
    //   #136	-> 239
    //   #137	-> 259
    //   #138	-> 267
    //   #139	-> 294
    //   #140	-> 299
    //   #141	-> 303
    //   #136	-> 306
    //   #147	-> 312
    //   #148	-> 315
    //   #149	-> 320
    //   #150	-> 339
    //   #151	-> 357
    //   #150	-> 360
    //   #152	-> 365
    //   #153	-> 370
    //   #154	-> 374
    //   #149	-> 377
    //   #159	-> 383
    //   #161	-> 388
    //   #162	-> 410
    //   #163	-> 437
    //   #164	-> 446
    //   #167	-> 450
    //   #167	-> 487
    //   #168	-> 489
    //   #168	-> 527
    //   #170	-> 529
    //   #171	-> 546
    //   #173	-> 551
    //   #170	-> 553
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   41	6	2	_	Ljava/lang/InterruptedException;
    //   64	2	3	$i$a$-check-PublicSuffixDatabase$findMatchingRule$2	I
    //   198	11	6	rule	Ljava/lang/String;
    //   158	57	4	i	I
    //   294	12	8	rule	Ljava/lang/String;
    //   242	70	6	labelIndex	I
    //   239	73	5	labelsWithWildcard	[[B
    //   365	12	8	rule	Ljava/lang/String;
    //   323	60	6	labelIndex	I
    //   153	401	2	domainLabelsUtf8Bytes	[[B
    //   155	399	3	exactMatch	Ljava/lang/String;
    //   218	336	4	wildcardMatch	Ljava/lang/String;
    //   315	239	5	exception	Ljava/lang/String;
    //   489	65	6	exactRuleLabels	Ljava/util/List;
    //   529	25	7	wildcardRuleLabels	Ljava/util/List;
    //   0	554	0	this	Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;
    //   0	554	1	domainLabels	Ljava/util/List;
    // Exception table:
    //   from	to	target	type
    //   29	37	40	java/lang/InterruptedException
  }
  
  private final void readTheListUninterruptibly() {
    boolean interrupted = false;
    try {
      while (true) {
        try {
          readTheList();
          return;
        } catch (InterruptedIOException _) {
          Thread.interrupted();
          interrupted = true;
        } catch (IOException e) {
          Platform.Companion.get().log("Failed to read public suffix list", 5, e);
          return;
        } 
      } 
    } finally {
      if (interrupted)
        Thread.currentThread().interrupt(); 
    } 
  }
  
  private final void readTheList() throws IOException {
    try {
      InputStream resource;
      Ref.ObjectRef publicSuffixListBytes = new Ref.ObjectRef();
      Ref.ObjectRef publicSuffixExceptionListBytes = new Ref.ObjectRef();
      if (PublicSuffixDatabase.class
        
        .getResourceAsStream("publicsuffixes.gz") == null) {
        PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
        return;
      } 
      Closeable closeable = (Closeable)Okio.buffer((Source)new GzipSource(Okio.source(resource)));
      null = null;
      try {
        BufferedSource bufferedSource = (BufferedSource)closeable;
        int $i$a$-use-PublicSuffixDatabase$readTheList$1 = 0;
        int totalBytes = bufferedSource.readInt();
        publicSuffixListBytes.element = bufferedSource.readByteArray(totalBytes);
        int totalExceptionBytes = bufferedSource.readInt();
        publicSuffixExceptionListBytes.element = bufferedSource.readByteArray(totalExceptionBytes);
        Unit unit = Unit.INSTANCE;
      } catch (Throwable throwable) {
        null = throwable = null;
        throw throwable;
      } finally {
        CloseableKt.closeFinally(closeable, null);
      } 
      synchronized (this) {
        int $i$a$-synchronized-PublicSuffixDatabase$readTheList$2 = 0;
        Intrinsics.checkNotNull(publicSuffixListBytes.element);
        this.publicSuffixListBytes = (byte[])publicSuffixListBytes.element;
        Intrinsics.checkNotNull(publicSuffixExceptionListBytes.element);
        this.publicSuffixExceptionListBytes = (byte[])publicSuffixExceptionListBytes.element;
        Unit unit = Unit.INSTANCE;
      } 
    } finally {
      this.readCompleteLatch.countDown();
    } 
  }
  
  public final void setListBytes(@NotNull byte[] publicSuffixListBytes, @NotNull byte[] publicSuffixExceptionListBytes) {
    Intrinsics.checkNotNullParameter(publicSuffixListBytes, "publicSuffixListBytes");
    Intrinsics.checkNotNullParameter(publicSuffixExceptionListBytes, "publicSuffixExceptionListBytes");
    this.publicSuffixListBytes = publicSuffixListBytes;
    this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
    this.listRead.set(true);
    this.readCompleteLatch.countDown();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\022\n\002\020\021\n\000\n\002\020\b\n\000\n\002\020\016\n\002\b\002\n\002\020\f\n\002\b\002\n\002\020 \n\002\b\t\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\006J+\020\r\032\004\030\0010\f*\0020\0072\f\020\t\032\b\022\004\022\0020\0070\b2\006\020\013\032\0020\nH\002¢\006\004\b\r\020\016R\024\020\020\032\0020\0178\002XT¢\006\006\n\004\b\020\020\021R\032\020\023\032\b\022\004\022\0020\f0\0228\002X\004¢\006\006\n\004\b\023\020\024R\024\020\025\032\0020\f8\006XT¢\006\006\n\004\b\025\020\026R\024\020\027\032\0020\0078\002X\004¢\006\006\n\004\b\027\020\030R\024\020\031\032\0020\0048\002X\004¢\006\006\n\004\b\031\020\032¨\006\033"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", "", "<init>", "()V", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "()Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "", "labels", "", "labelIndex", "", "binarySearch", "([B[[BI)Ljava/lang/String;", "", "EXCEPTION_MARKER", "C", "", "PREVAILING_RULE", "Ljava/util/List;", "PUBLIC_SUFFIX_RESOURCE", "Ljava/lang/String;", "WILDCARD_LABEL", "[B", "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final PublicSuffixDatabase get() {
      return PublicSuffixDatabase.instance;
    }
    
    private final String binarySearch(byte[] $this$binarySearch, byte[][] labels, int labelIndex) {
      int low = 0;
      int high = $this$binarySearch.length;
      String match = null;
      while (low < high) {
        int mid = (low + high) / 2;
        while (mid > -1 && $this$binarySearch[mid] != 10)
          mid--; 
        mid++;
        int end = 1;
        while ($this$binarySearch[mid + end] != 10)
          end++; 
        int publicSuffixLength = mid + end - mid, compareResult = 0;
        int currentLabelIndex = labelIndex;
        int currentLabelByteIndex = 0;
        int publicSuffixByteIndex = 0;
        boolean expectDot = false;
        while (true) {
          int byte0 = 0;
          if (expectDot) {
            byte0 = 46;
            expectDot = false;
          } else {
            byte0 = Util.and(labels[currentLabelIndex][currentLabelByteIndex], 255);
          } 
          int byte1 = Util.and($this$binarySearch[mid + publicSuffixByteIndex], 255);
          compareResult = byte0 - byte1;
          if (compareResult == 0) {
            publicSuffixByteIndex++;
            currentLabelByteIndex++;
            if (publicSuffixByteIndex != publicSuffixLength) {
              if ((labels[currentLabelIndex]).length == currentLabelByteIndex) {
                if (currentLabelIndex == ((Object[])labels).length - 1)
                  break; 
                currentLabelIndex++;
                currentLabelByteIndex = -1;
                expectDot = true;
              } 
              continue;
            } 
          } 
          break;
        } 
        if (compareResult < 0) {
          high = mid - 1;
          continue;
        } 
        if (compareResult > 0) {
          low = mid + end + 1;
          continue;
        } 
        int publicSuffixBytesLeft = publicSuffixLength - publicSuffixByteIndex;
        int labelBytesLeft = (labels[currentLabelIndex]).length - currentLabelByteIndex;
        for (int i = currentLabelIndex + 1, j = ((Object[])labels).length; i < j; i++)
          labelBytesLeft += (labels[i]).length; 
        if (labelBytesLeft < publicSuffixBytesLeft) {
          high = mid - 1;
          continue;
        } 
        if (labelBytesLeft > publicSuffixBytesLeft) {
          low = mid + end + 1;
          continue;
        } 
        Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_8, "UTF_8");
        Charset charset = StandardCharsets.UTF_8;
        match = new String($this$binarySearch, mid, publicSuffixLength, charset);
      } 
      return match;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  static {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = 42;
    WILDCARD_LABEL = arrayOfByte;
  }
  
  @NotNull
  private static final List<String> PREVAILING_RULE = CollectionsKt.listOf("*");
  
  @NotNull
  private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
  
  private byte[] publicSuffixListBytes;
  
  private byte[] publicSuffixExceptionListBytes;
  
  @NotNull
  public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
  
  @NotNull
  private static final byte[] WILDCARD_LABEL;
  
  private static final char EXCEPTION_MARKER = '!';
}
