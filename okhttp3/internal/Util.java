package okhttp3.internal;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import okhttp3.internal.io.FileSystem;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Options;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000È\002\n\002\020\016\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\005\n\002\020\002\n\002\b\003\n\002\020\021\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020 \n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020!\n\002\b\003\n\002\020\005\n\002\b\004\n\002\020\n\n\000\n\002\030\002\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\f\n\002\b\007\n\002\030\002\n\002\b\004\n\002\020\034\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\r\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\016\n\002\020$\n\002\b\t\n\002\030\002\n\002\030\002\n\000\n\002\020\003\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\022\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\b\032'\020\007\032\0020\0062\006\020\001\032\0020\0002\006\020\003\032\0020\0022\b\020\005\032\004\030\0010\004¢\006\004\b\007\020\b\032%\020\r\032\0020\f2\006\020\t\032\0020\0022\006\020\n\032\0020\0022\006\020\013\032\0020\002¢\006\004\b\r\020\016\032)\020\017\032\0020\0002\006\020\017\032\0020\0002\022\020\022\032\n\022\006\b\001\022\0020\0210\020\"\0020\021¢\006\004\b\017\020\023\032!\020\026\032\0020\f2\f\020\025\032\b\022\004\022\0020\f0\024H\bø\001\000¢\006\004\b\026\020\027\032/\020\033\032\b\022\004\022\0028\0000\032\"\004\b\000\020\0302\022\020\031\032\n\022\006\b\001\022\0028\0000\020\"\0028\000H\007¢\006\004\b\033\020\034\032\025\020\036\032\0020\0352\006\020\001\032\0020\000¢\006\004\b\036\020\037\0323\020$\032\004\030\0018\000\"\004\b\000\020\0302\006\020 \032\0020\0212\f\020\"\032\b\022\004\022\0028\0000!2\006\020#\032\0020\000¢\006\004\b$\020%\032\035\020(\032\0020'2\006\020\001\032\0020\0002\006\020&\032\0020\035¢\006\004\b(\020)\032)\020*\032\0020\f2\006\020\001\032\0020\0002\f\020\025\032\b\022\004\022\0020\f0\024H\bø\001\000¢\006\004\b*\020+\032'\020/\032\0020\f\"\004\b\000\020,*\b\022\004\022\0028\0000-2\006\020.\032\0028\000H\000¢\006\004\b/\0200\032\034\0203\032\0020\006*\002012\006\0202\032\0020\006H\004¢\006\004\b3\0204\032\034\0203\032\0020\002*\0020\0062\006\0202\032\0020\002H\004¢\006\004\b3\0205\032\034\0203\032\0020\006*\002062\006\0202\032\0020\006H\004¢\006\004\b3\0207\032\021\020:\032\00209*\00208¢\006\004\b:\020;\032\024\020<\032\0020\f*\0020\021H\b¢\006\004\b<\020=\032\024\020>\032\0020\f*\0020\021H\b¢\006\004\b>\020=\032\021\020?\032\0020\035*\0020\000¢\006\004\b?\020\037\032\031\020B\032\0020\035*\0020@2\006\020A\032\0020@¢\006\004\bB\020C\032\021\020E\032\0020\f*\0020D¢\006\004\bE\020F\032\021\020E\032\0020\f*\0020G¢\006\004\bE\020H\032\021\020E\032\0020\f*\0020I¢\006\004\bE\020J\032%\020L\032\b\022\004\022\0020\0000\020*\b\022\004\022\0020\0000\0202\006\020K\032\0020\000¢\006\004\bL\020M\032-\020R\032\0020\006*\0020\0002\006\020O\032\0020N2\b\b\002\020P\032\0020\0062\b\b\002\020Q\032\0020\006¢\006\004\bR\020S\032-\020R\032\0020\006*\0020\0002\006\020T\032\0020\0002\b\b\002\020P\032\0020\0062\b\b\002\020Q\032\0020\006¢\006\004\bR\020U\032!\020Y\032\0020\035*\0020V2\006\020W\032\0020\0062\006\020X\032\0020\004¢\006\004\bY\020Z\032B\020_\032\b\022\004\022\0028\0000\032\"\004\b\000\020\030*\b\022\004\022\0028\0000[2\027\020^\032\023\022\004\022\0028\000\022\004\022\0020\0350\\¢\006\002\b]H\bø\001\000¢\006\004\b_\020`\0327\020c\032\0020\035*\b\022\004\022\0020\0000\0202\016\020A\032\n\022\004\022\0020\000\030\0010\0202\016\020b\032\n\022\006\b\000\022\0020\0000a¢\006\004\bc\020d\032\021\020f\032\0020\002*\0020e¢\006\004\bf\020g\032-\020h\032\0020\006*\b\022\004\022\0020\0000\0202\006\020K\032\0020\0002\f\020b\032\b\022\004\022\0020\0000a¢\006\004\bh\020i\032\021\020j\032\0020\006*\0020\000¢\006\004\bj\020k\032%\020l\032\0020\006*\0020\0002\b\b\002\020P\032\0020\0062\b\b\002\020Q\032\0020\006¢\006\004\bl\020m\032%\020n\032\0020\006*\0020\0002\b\b\002\020P\032\0020\0062\b\b\002\020Q\032\0020\006¢\006\004\bn\020m\032\033\020o\032\0020\006*\0020\0002\b\b\002\020P\032\0020\006¢\006\004\bo\020p\032;\020q\032\b\022\004\022\0020\0000\020*\b\022\004\022\0020\0000\0202\f\020A\032\b\022\004\022\0020\0000\0202\016\020b\032\n\022\006\b\000\022\0020\0000a¢\006\004\bq\020r\032\031\020v\032\0020\035*\0020s2\006\020u\032\0020t¢\006\004\bv\020w\032\031\020z\032\0020\035*\0020I2\006\020y\032\0020x¢\006\004\bz\020{\032\024\020|\032\0020\f*\0020\021H\b¢\006\004\b|\020=\032\024\020}\032\0020\f*\0020\021H\b¢\006\004\b}\020=\032\021\020~\032\0020\006*\0020N¢\006\004\b~\020\032\024\020\001\032\0020\000*\0020I¢\006\006\b\001\020\001\032\037\020\001\032\0030\001*\0020x2\b\020\001\032\0030\001¢\006\006\b\001\020\001\032\024\020\001\032\0020\006*\0020x¢\006\006\b\001\020\001\032\036\020\001\032\0020\006*\0030\0012\007\020\001\032\00201¢\006\006\b\001\020\001\032#\020\001\032\0020\035*\0020V2\006\020\003\032\0020\0062\006\020X\032\0020\004¢\006\005\b\001\020Z\032\034\020\001\032\t\022\005\022\0030\0010\032*\0030\001¢\006\006\b\001\020\001\032\034\020\001\032\0030\001*\t\022\005\022\0030\0010\032¢\006\006\b\001\020\001\032\024\020\001\032\0020\000*\0020\006¢\006\006\b\001\020\001\032\024\020\001\032\0020\000*\0020\002¢\006\006\b\001\020\001\032\037\020\001\032\0020\000*\0020@2\t\b\002\020\001\032\0020\035¢\006\006\b\001\020\001\032&\020\001\032\b\022\004\022\0028\0000\032\"\004\b\000\020\030*\b\022\004\022\0028\0000\032¢\006\006\b\001\020\001\032<\020\001\032\017\022\004\022\0028\000\022\004\022\0028\0010\001\"\005\b\000\020\001\"\005\b\001\020\001*\017\022\004\022\0028\000\022\004\022\0028\0010\001¢\006\006\b\001\020\001\032\035\020 \001\032\0020\002*\0020\0002\007\020\001\032\0020\002¢\006\006\b \001\020¡\001\032\036\020¢\001\032\0020\006*\004\030\0010\0002\007\020\001\032\0020\006¢\006\005\b¢\001\020p\032(\020£\001\032\0020\000*\0020\0002\b\b\002\020P\032\0020\0062\b\b\002\020Q\032\0020\006¢\006\006\b£\001\020¤\001\032\026\020¥\001\032\0020\f*\0020\021H\b¢\006\005\b¥\001\020=\0320\020ª\001\032\0030©\001*\b0¦\001j\003`§\0012\023\020¨\001\032\016\022\n\022\b0¦\001j\003`§\0010\032¢\006\006\bª\001\020«\001\032\036\020®\001\032\0020\f*\0030¬\0012\007\020­\001\032\0020\006¢\006\006\b®\001\020¯\001\"\030\020±\001\032\0030°\0018\006X\004¢\006\b\n\006\b±\001\020²\001\"\030\020³\001\032\0030\0018\006X\004¢\006\b\n\006\b³\001\020´\001\"\030\020¶\001\032\0030µ\0018\006X\004¢\006\b\n\006\b¶\001\020·\001\"\030\020¹\001\032\0030¸\0018\006X\004¢\006\b\n\006\b¹\001\020º\001\"\030\020¼\001\032\0030»\0018\002X\004¢\006\b\n\006\b¼\001\020½\001\"\030\020¿\001\032\0030¾\0018\006X\004¢\006\b\n\006\b¿\001\020À\001\"\030\020Â\001\032\0030Á\0018\002X\004¢\006\b\n\006\bÂ\001\020Ã\001\"\027\020Ä\001\032\0020\0358\000X\004¢\006\b\n\006\bÄ\001\020Å\001\"\027\020Æ\001\032\0020\0008\000X\004¢\006\b\n\006\bÆ\001\020Ç\001\"\027\020È\001\032\0020\0008\006XT¢\006\b\n\006\bÈ\001\020Ç\001\002\007\n\005\b20\001¨\006É\001"}, d2 = {"", "name", "", "duration", "Ljava/util/concurrent/TimeUnit;", "unit", "", "checkDuration", "(Ljava/lang/String;JLjava/util/concurrent/TimeUnit;)I", "arrayLength", "offset", "count", "", "checkOffsetAndCount", "(JJJ)V", "format", "", "", "args", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "Lkotlin/Function0;", "block", "ignoreIoExceptions", "(Lkotlin/jvm/functions/Function0;)V", "T", "elements", "", "immutableListOf", "([Ljava/lang/Object;)Ljava/util/List;", "", "isSensitiveHeader", "(Ljava/lang/String;)Z", "instance", "Ljava/lang/Class;", "fieldType", "fieldName", "readFieldOrNull", "(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "daemon", "Ljava/util/concurrent/ThreadFactory;", "threadFactory", "(Ljava/lang/String;Z)Ljava/util/concurrent/ThreadFactory;", "threadName", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "E", "", "element", "addIfAbsent", "(Ljava/util/List;Ljava/lang/Object;)V", "", "mask", "and", "(BI)I", "(IJ)J", "", "(SI)I", "Lokhttp3/EventListener;", "Lokhttp3/EventListener$Factory;", "asFactory", "(Lokhttp3/EventListener;)Lokhttp3/EventListener$Factory;", "assertThreadDoesntHoldLock", "(Ljava/lang/Object;)V", "assertThreadHoldsLock", "canParseAsIpAddress", "Lokhttp3/HttpUrl;", "other", "canReuseConnectionFor", "(Lokhttp3/HttpUrl;Lokhttp3/HttpUrl;)Z", "Ljava/io/Closeable;", "closeQuietly", "(Ljava/io/Closeable;)V", "Ljava/net/ServerSocket;", "(Ljava/net/ServerSocket;)V", "Ljava/net/Socket;", "(Ljava/net/Socket;)V", "value", "concat", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "", "delimiter", "startIndex", "endIndex", "delimiterOffset", "(Ljava/lang/String;CII)I", "delimiters", "(Ljava/lang/String;Ljava/lang/String;II)I", "Lokio/Source;", "timeout", "timeUnit", "discard", "(Lokio/Source;ILjava/util/concurrent/TimeUnit;)Z", "", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "predicate", "filterList", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "Ljava/util/Comparator;", "comparator", "hasIntersection", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)Z", "Lokhttp3/Response;", "headersContentLength", "(Lokhttp3/Response;)J", "indexOf", "([Ljava/lang/String;Ljava/lang/String;Ljava/util/Comparator;)I", "indexOfControlOrNonAscii", "(Ljava/lang/String;)I", "indexOfFirstNonAsciiWhitespace", "(Ljava/lang/String;II)I", "indexOfLastNonAsciiWhitespace", "indexOfNonWhitespace", "(Ljava/lang/String;I)I", "intersect", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)[Ljava/lang/String;", "Lokhttp3/internal/io/FileSystem;", "Ljava/io/File;", "file", "isCivilized", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;)Z", "Lokio/BufferedSource;", "source", "isHealthy", "(Ljava/net/Socket;Lokio/BufferedSource;)Z", "notify", "notifyAll", "parseHexDigit", "(C)I", "peerName", "(Ljava/net/Socket;)Ljava/lang/String;", "Ljava/nio/charset/Charset;", "default", "readBomAsCharset", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)Ljava/nio/charset/Charset;", "readMedium", "(Lokio/BufferedSource;)I", "Lokio/Buffer;", "b", "skipAll", "(Lokio/Buffer;B)I", "Lokhttp3/Headers;", "Lokhttp3/internal/http2/Header;", "toHeaderList", "(Lokhttp3/Headers;)Ljava/util/List;", "toHeaders", "(Ljava/util/List;)Lokhttp3/Headers;", "toHexString", "(I)Ljava/lang/String;", "(J)Ljava/lang/String;", "includeDefaultPort", "toHostHeader", "(Lokhttp3/HttpUrl;Z)Ljava/lang/String;", "toImmutableList", "(Ljava/util/List;)Ljava/util/List;", "K", "V", "", "toImmutableMap", "(Ljava/util/Map;)Ljava/util/Map;", "defaultValue", "toLongOrDefault", "(Ljava/lang/String;J)J", "toNonNegativeInt", "trimSubstring", "(Ljava/lang/String;II)Ljava/lang/String;", "wait", "Ljava/lang/Exception;", "Lkotlin/Exception;", "suppressed", "", "withSuppressed", "(Ljava/lang/Exception;Ljava/util/List;)Ljava/lang/Throwable;", "Lokio/BufferedSink;", "medium", "writeMedium", "(Lokio/BufferedSink;I)V", "", "EMPTY_BYTE_ARRAY", "[B", "EMPTY_HEADERS", "Lokhttp3/Headers;", "Lokhttp3/RequestBody;", "EMPTY_REQUEST", "Lokhttp3/RequestBody;", "Lokhttp3/ResponseBody;", "EMPTY_RESPONSE", "Lokhttp3/ResponseBody;", "Lokio/Options;", "UNICODE_BOMS", "Lokio/Options;", "Ljava/util/TimeZone;", "UTC", "Ljava/util/TimeZone;", "Lkotlin/text/Regex;", "VERIFY_AS_IP_ADDRESS", "Lkotlin/text/Regex;", "assertionsEnabled", "Z", "okHttpName", "Ljava/lang/String;", "userAgent", "okhttp"})
@JvmName(name = "Util")
@SourceDebugExtension({"SMAP\nUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Util.kt\nokhttp3/internal/Util\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,636:1\n37#2,2:637\n1627#3,6:639\n1#4:645\n1549#5:646\n1620#5,3:647\n*S KotlinDebug\n*F\n+ 1 Util.kt\nokhttp3/internal/Util\n*L\n127#1:637,2\n167#1:639,6\n300#1:646\n300#1:647,3\n*E\n"})
public final class Util {
  @JvmField
  @NotNull
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  
  @JvmField
  @NotNull
  public static final Headers EMPTY_HEADERS = Headers.Companion.of(new String[0]);
  
  @JvmField
  @NotNull
  public static final ResponseBody EMPTY_RESPONSE = ResponseBody.Companion.create$default(ResponseBody.Companion, EMPTY_BYTE_ARRAY, null, 1, null);
  
  @JvmField
  @NotNull
  public static final RequestBody EMPTY_REQUEST = RequestBody.Companion.create$default(RequestBody.Companion, EMPTY_BYTE_ARRAY, null, 0, 0, 7, null);
  
  @NotNull
  private static final Options UNICODE_BOMS;
  
  static {
    ByteString[] arrayOfByteString = new ByteString[5];
    arrayOfByteString[0] = ByteString.Companion.decodeHex("efbbbf");
    arrayOfByteString[1] = 
      ByteString.Companion.decodeHex("feff");
    arrayOfByteString[2] = ByteString.Companion.decodeHex("fffe");
    arrayOfByteString[3] = ByteString.Companion.decodeHex("0000ffff");
    arrayOfByteString[4] = ByteString.Companion.decodeHex("ffff0000");
    UNICODE_BOMS = Options.Companion.of(arrayOfByteString);
    Intrinsics.checkNotNull(TimeZone.getTimeZone("GMT"));
  }
  
  @JvmField
  @NotNull
  public static final TimeZone UTC = TimeZone.getTimeZone("GMT");
  
  @NotNull
  private static final Regex VERIFY_AS_IP_ADDRESS = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
  
  public static final void checkOffsetAndCount(long arrayLength, long offset, long count) {
    if ((offset | count) < 0L || offset > arrayLength || arrayLength - offset < count)
      throw new ArrayIndexOutOfBoundsException(); 
  }
  
  @NotNull
  public static final ThreadFactory threadFactory(@NotNull String name, boolean daemon) {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'name'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: iload_1
    //   8: <illegal opcode> newThread : (Ljava/lang/String;Z)Ljava/util/concurrent/ThreadFactory;
    //   13: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #104	-> 6
    //   #108	-> 13
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	14	0	name	Ljava/lang/String;
    //   0	14	1	daemon	Z
  }
  
  private static final Thread threadFactory$lambda$1(String $name, boolean $daemon, Runnable runnable) {
    Intrinsics.checkNotNullParameter($name, "$name");
    Thread thread1 = new Thread(runnable, $name), $this$threadFactory_u24lambda_u241_u24lambda_u240 = thread1;
    int $i$a$-apply-Util$threadFactory$1$1 = 0;
    $this$threadFactory_u24lambda_u241_u24lambda_u240.setDaemon($daemon);
    return thread1;
  }
  
  @NotNull
  public static final String[] intersect(@NotNull String[] $this$intersect, @NotNull String[] other, @NotNull Comparator<String> comparator) {
    Intrinsics.checkNotNullParameter($this$intersect, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    Intrinsics.checkNotNullParameter(comparator, "comparator");
    List<String> result = new ArrayList();
    byte b;
    int i;
    for (b = 0, i = $this$intersect.length; b < i; ) {
      String a = $this$intersect[b];
      byte b1;
      int j;
      for (b1 = 0, j = other.length; b1 < j; ) {
        String str = other[b1];
        if (comparator.compare(a, str) == 0) {
          result.add(a);
          break;
        } 
        b1++;
      } 
      b++;
    } 
    Collection<String> $this$toTypedArray$iv = result;
    int $i$f$toTypedArray = 0;
    Collection<String> thisCollection$iv = $this$toTypedArray$iv;
    return thisCollection$iv.<String>toArray(new String[0]);
  }
  
  public static final boolean hasIntersection(@NotNull String[] $this$hasIntersection, @Nullable String[] other, @NotNull Comparator<String> comparator) {
    Intrinsics.checkNotNullParameter($this$hasIntersection, "<this>");
    Intrinsics.checkNotNullParameter(comparator, "comparator");
    if ((($this$hasIntersection.length == 0)) || other == null || ((other.length == 0)))
      return false; 
    byte b;
    int i;
    for (b = 0, i = $this$hasIntersection.length; b < i; ) {
      String a = $this$hasIntersection[b];
      for (Iterator<String> iterator = ArrayIteratorKt.iterator((Object[])other); iterator.hasNext(); ) {
        String str = iterator.next();
        if (comparator.compare(a, str) == 0)
          return true; 
      } 
      b++;
    } 
    return false;
  }
  
  @NotNull
  public static final String toHostHeader(@NotNull HttpUrl $this$toHostHeader, boolean includeDefaultPort) {
    Intrinsics.checkNotNullParameter($this$toHostHeader, "<this>");
    String host = StringsKt.contains$default($this$toHostHeader.host(), ":", false, 2, null) ? ('[' + $this$toHostHeader.host() + ']') : $this$toHostHeader.host();
    return (includeDefaultPort || $this$toHostHeader.port() != HttpUrl.Companion.defaultPort($this$toHostHeader.scheme())) ? (host + ':' + $this$toHostHeader.port()) : host;
  }
  
  public static final int indexOf(@NotNull String[] $this$indexOf, @NotNull String value, @NotNull Comparator<String> comparator) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w '<this>'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_1
    //   8: ldc_w 'value'
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_2
    //   15: ldc_w 'comparator'
    //   18: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   21: aload_0
    //   22: astore_3
    //   23: iconst_0
    //   24: istore #4
    //   26: iconst_0
    //   27: istore #5
    //   29: aload_3
    //   30: arraylength
    //   31: istore #6
    //   33: iload #5
    //   35: iload #6
    //   37: if_icmpge -> 80
    //   40: aload_3
    //   41: iload #5
    //   43: aaload
    //   44: astore #7
    //   46: iconst_0
    //   47: istore #8
    //   49: aload_2
    //   50: aload #7
    //   52: aload_1
    //   53: invokeinterface compare : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   58: ifne -> 65
    //   61: iconst_1
    //   62: goto -> 66
    //   65: iconst_0
    //   66: ifeq -> 74
    //   69: iload #5
    //   71: goto -> 81
    //   74: iinc #5, 1
    //   77: goto -> 33
    //   80: iconst_m1
    //   81: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #167	-> 21
    //   #639	-> 26
    //   #640	-> 40
    //   #167	-> 49
    //   #640	-> 66
    //   #641	-> 69
    //   #639	-> 74
    //   #644	-> 80
    //   #167	-> 81
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   49	17	8	$i$a$-indexOfFirst-Util$indexOf$1	I
    //   46	20	7	it	Ljava/lang/String;
    //   29	51	5	index$iv	I
    //   26	55	4	$i$f$indexOfFirst	I
    //   23	58	3	$this$indexOfFirst$iv	[Ljava/lang/Object;
    //   0	82	0	$this$indexOf	[Ljava/lang/String;
    //   0	82	1	value	Ljava/lang/String;
    //   0	82	2	comparator	Ljava/util/Comparator;
  }
  
  @NotNull
  public static final String[] concat(@NotNull String[] $this$concat, @NotNull String value) {
    Intrinsics.checkNotNullParameter($this$concat, "<this>");
    Intrinsics.checkNotNullParameter(value, "value");
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf($this$concat, $this$concat.length + 1), "copyOf(this, newSize)");
    String[] result = Arrays.copyOf($this$concat, $this$concat.length + 1);
    result[ArraysKt.getLastIndex((Object[])result)] = value;
    return result;
  }
  
  public static final int indexOfFirstNonAsciiWhitespace(@NotNull String $this$indexOfFirstNonAsciiWhitespace, int startIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$indexOfFirstNonAsciiWhitespace, "<this>");
    for (int i = startIndex; i < endIndex; i++) {
      char c = $this$indexOfFirstNonAsciiWhitespace.charAt(i);
      if (!(((((c == '\t') ? true : ((c == '\n'))) ? true : ((c == '\f'))) ? true : ((c == '\r'))) ? 1 : ((c == ' ') ? 1 : 0)))
        return i; 
    } 
    return endIndex;
  }
  
  public static final int indexOfLastNonAsciiWhitespace(@NotNull String $this$indexOfLastNonAsciiWhitespace, int startIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$indexOfLastNonAsciiWhitespace, "<this>");
    int i = endIndex - 1;
    if (startIndex <= i)
      while (true) {
        char c = $this$indexOfLastNonAsciiWhitespace.charAt(i);
        if (!(((((c == '\t') ? true : ((c == '\n'))) ? true : ((c == '\f'))) ? true : ((c == '\r'))) ? 1 : ((c == ' ') ? 1 : 0)))
          return i + 1; 
        if (i != startIndex) {
          i--;
          continue;
        } 
        break;
      }  
    return startIndex;
  }
  
  @NotNull
  public static final String trimSubstring(@NotNull String $this$trimSubstring, int startIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$trimSubstring, "<this>");
    int start = indexOfFirstNonAsciiWhitespace($this$trimSubstring, startIndex, endIndex);
    int end = indexOfLastNonAsciiWhitespace($this$trimSubstring, start, endIndex);
    Intrinsics.checkNotNullExpressionValue($this$trimSubstring.substring(start, end), "this as java.lang.String…ing(startIndex, endIndex)");
    return $this$trimSubstring.substring(start, end);
  }
  
  public static final int delimiterOffset(@NotNull String $this$delimiterOffset, @NotNull String delimiters, int startIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$delimiterOffset, "<this>");
    Intrinsics.checkNotNullParameter(delimiters, "delimiters");
    for (int i = startIndex; i < endIndex; i++) {
      if (StringsKt.contains$default(delimiters, $this$delimiterOffset.charAt(i), false, 2, null))
        return i; 
    } 
    return endIndex;
  }
  
  public static final int delimiterOffset(@NotNull String $this$delimiterOffset, char delimiter, int startIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$delimiterOffset, "<this>");
    for (int i = startIndex; i < endIndex; i++) {
      if ($this$delimiterOffset.charAt(i) == delimiter)
        return i; 
    } 
    return endIndex;
  }
  
  public static final int indexOfControlOrNonAscii(@NotNull String $this$indexOfControlOrNonAscii) {
    Intrinsics.checkNotNullParameter($this$indexOfControlOrNonAscii, "<this>");
    for (int i = 0, j = $this$indexOfControlOrNonAscii.length(); i < j; i++) {
      char c = $this$indexOfControlOrNonAscii.charAt(i);
      if (Intrinsics.compare(c, 31) <= 0 || Intrinsics.compare(c, 127) >= 0)
        return i; 
    } 
    return -1;
  }
  
  public static final boolean canParseAsIpAddress(@NotNull String $this$canParseAsIpAddress) {
    Intrinsics.checkNotNullParameter($this$canParseAsIpAddress, "<this>");
    return VERIFY_AS_IP_ADDRESS.matches($this$canParseAsIpAddress);
  }
  
  public static final boolean isSensitiveHeader(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    return (StringsKt.equals(name, "Authorization", true) || StringsKt.equals(name, "Cookie", true) || StringsKt.equals(name, "Proxy-Authorization", true) || StringsKt.equals(name, "Set-Cookie", true));
  }
  
  @NotNull
  public static final String format(@NotNull String format, @NotNull Object... args) {
    Intrinsics.checkNotNullParameter(format, "format");
    Intrinsics.checkNotNullParameter(args, "args");
    Locale locale = Locale.US;
    Object[] arrayOfObject = Arrays.copyOf(args, args.length);
    Intrinsics.checkNotNullExpressionValue(String.format(locale, format, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "format(locale, format, *args)");
    return String.format(locale, format, Arrays.copyOf(arrayOfObject, arrayOfObject.length));
  }
  
  @NotNull
  public static final Charset readBomAsCharset(@NotNull BufferedSource $this$readBomAsCharset, @NotNull Charset default) throws IOException {
    Intrinsics.checkNotNullParameter($this$readBomAsCharset, "<this>");
    Intrinsics.checkNotNullParameter(default, "default");
    switch ($this$readBomAsCharset.select(UNICODE_BOMS)) {
      case 0:
        Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_8, "UTF_8");
      case 1:
        Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_16BE, "UTF_16BE");
      case 2:
        Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_16LE, "UTF_16LE");
      case 3:
      
      case 4:
      
      case -1:
      
    } 
    throw new AssertionError();
  }
  
  public static final int checkDuration(@NotNull String name, long duration, @Nullable TimeUnit unit) {
    Intrinsics.checkNotNullParameter(name, "name");
    if (!((duration >= 0L) ? 1 : 0)) {
      int $i$a$-check-Util$checkDuration$1 = 0;
      String str = name + " < 0";
      throw new IllegalStateException(str.toString());
    } 
    if (!((unit != null) ? 1 : 0)) {
      int $i$a$-check-Util$checkDuration$2 = 0;
      String str = "unit == null";
      throw new IllegalStateException(str.toString());
    } 
    long millis = unit.toMillis(duration);
    if (!((millis <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require-Util$checkDuration$3 = 0;
      String str = name + " too large.";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((millis != 0L || duration <= 0L) ? 1 : 0)) {
      int $i$a$-require-Util$checkDuration$4 = 0;
      String str = name + " too small.";
      throw new IllegalArgumentException(str.toString());
    } 
    return (int)millis;
  }
  
  public static final int parseHexDigit(char $this$parseHexDigit) {
    char c = $this$parseHexDigit;
    return (('0' <= c) ? ((c < ':')) : false) ? ($this$parseHexDigit - 48) : ((('a' <= c) ? ((c < 'g')) : false) ? ($this$parseHexDigit - 97 + 10) : ((('A' <= c) ? ((c < 'G')) : false) ? ($this$parseHexDigit - 65 + 10) : -1));
  }
  
  @NotNull
  public static final Headers toHeaders(@NotNull List $this$toHeaders) {
    Intrinsics.checkNotNullParameter($this$toHeaders, "<this>");
    Headers.Builder builder = new Headers.Builder();
    for (Header header : $this$toHeaders) {
      ByteString name = header.component1(), value = header.component2();
      builder.addLenient$okhttp(name.utf8(), value.utf8());
    } 
    return builder.build();
  }
  
  @NotNull
  public static final List<Header> toHeaderList(@NotNull Headers $this$toHeaderList) {
    Intrinsics.checkNotNullParameter($this$toHeaderList, "<this>");
    Iterable $this$map$iv = (Iterable)RangesKt.until(0, $this$toHeaderList.size());
    int $i$f$map = 0;
    Iterable iterable1 = $this$map$iv;
    Collection<Header> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
    int $i$f$mapTo = 0;
    for (Iterator iterator = iterable1.iterator(); iterator.hasNext(); ) {
      int item$iv$iv = ((IntIterator)iterator).nextInt();
      int i = item$iv$iv;
      Collection<Header> collection = destination$iv$iv;
      int $i$a$-map-Util$toHeaderList$1 = 0;
      collection.add(new Header($this$toHeaderList.name(i), $this$toHeaderList.value(i)));
    } 
    return (List<Header>)destination$iv$iv;
  }
  
  public static final boolean canReuseConnectionFor(@NotNull HttpUrl $this$canReuseConnectionFor, @NotNull HttpUrl other) {
    Intrinsics.checkNotNullParameter($this$canReuseConnectionFor, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    return (Intrinsics.areEqual($this$canReuseConnectionFor.host(), other.host()) && $this$canReuseConnectionFor.port() == other.port() && Intrinsics.areEqual($this$canReuseConnectionFor.scheme(), other.scheme()));
  }
  
  @NotNull
  public static final EventListener.Factory asFactory(@NotNull EventListener $this$asFactory) {
    Intrinsics.checkNotNullParameter($this$asFactory, "<this>");
    return $this$asFactory::asFactory$lambda$8;
  }
  
  private static final EventListener asFactory$lambda$8(EventListener $this_asFactory, Call it) {
    Intrinsics.checkNotNullParameter($this_asFactory, "$this_asFactory");
    Intrinsics.checkNotNullParameter(it, "it");
    return $this_asFactory;
  }
  
  public static final int and(byte $this$and, int mask) {
    return $this$and & mask;
  }
  
  public static final int and(short $this$and, int mask) {
    return $this$and & mask;
  }
  
  public static final long and(int $this$and, long mask) {
    return $this$and & mask;
  }
  
  public static final void writeMedium(@NotNull BufferedSink $this$writeMedium, int medium) throws IOException {
    Intrinsics.checkNotNullParameter($this$writeMedium, "<this>");
    $this$writeMedium.writeByte(medium >>> 16 & 0xFF);
    $this$writeMedium.writeByte(medium >>> 8 & 0xFF);
    $this$writeMedium.writeByte(medium & 0xFF);
  }
  
  public static final int readMedium(@NotNull BufferedSource $this$readMedium) throws IOException {
    Intrinsics.checkNotNullParameter($this$readMedium, "<this>");
    return and($this$readMedium.readByte(), 255) << 16 | and($this$readMedium.readByte(), 255) << 8 | and($this$readMedium.readByte(), 255);
  }
  
  public static final boolean skipAll(@NotNull Source $this$skipAll, int duration, @NotNull TimeUnit timeUnit) throws IOException {
    boolean bool;
    Intrinsics.checkNotNullParameter($this$skipAll, "<this>");
    Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
    long nowNs = System.nanoTime();
    long originalDurationNs = $this$skipAll.timeout().hasDeadline() ? ($this$skipAll.timeout().deadlineNanoTime() - nowNs) : Long.MAX_VALUE;
    $this$skipAll.timeout().deadlineNanoTime(nowNs + Math.min(originalDurationNs, timeUnit.toNanos(duration)));
    try {
      Buffer skipBuffer = new Buffer();
      while ($this$skipAll.read(skipBuffer, 8192L) != -1L)
        skipBuffer.clear(); 
      bool = true;
    } catch (InterruptedIOException _) {
      bool = false;
    } finally {
      if (originalDurationNs == Long.MAX_VALUE) {
        $this$skipAll.timeout().clearDeadline();
      } else {
        $this$skipAll.timeout().deadlineNanoTime(nowNs + originalDurationNs);
      } 
    } 
    return bool;
  }
  
  public static final boolean discard(@NotNull Source $this$discard, int timeout, @NotNull TimeUnit timeUnit) {
    boolean bool;
    Intrinsics.checkNotNullParameter($this$discard, "<this>");
    Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
    try {
      bool = skipAll($this$discard, timeout, timeUnit);
    } catch (IOException _) {
      bool = false;
    } 
    return bool;
  }
  
  @NotNull
  public static final String peerName(@NotNull Socket $this$peerName) {
    Intrinsics.checkNotNullParameter($this$peerName, "<this>");
    SocketAddress address = $this$peerName.getRemoteSocketAddress();
    Intrinsics.checkNotNullExpressionValue(((InetSocketAddress)address).getHostName(), "address.hostName");
    return (address instanceof InetSocketAddress) ? ((InetSocketAddress)address).getHostName() : address.toString();
  }
  
  public static final boolean isHealthy(@NotNull Socket $this$isHealthy, @NotNull BufferedSource source) {
    boolean bool;
    Intrinsics.checkNotNullParameter($this$isHealthy, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    try {
      boolean bool1;
      bool = $this$isHealthy.getSoTimeout();
      try {
        $this$isHealthy.setSoTimeout(1);
        bool1 = !source.exhausted() ? true : false;
      } finally {
        $this$isHealthy.setSoTimeout(bool);
      } 
      bool = bool1;
    } catch (SocketTimeoutException _) {
      bool = true;
    } catch (IOException _) {
      bool = false;
    } 
    return bool;
  }
  
  public static final void ignoreIoExceptions(@NotNull Function0 block) {
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$ignoreIoExceptions = 0;
    try {
      block.invoke();
    } catch (IOException iOException) {}
  }
  
  public static final void threadName(@NotNull String name, @NotNull Function0 block) {
    Intrinsics.checkNotNullParameter(name, "name");
    Intrinsics.checkNotNullParameter(block, "block");
    int $i$f$threadName = 0;
    Thread currentThread = Thread.currentThread();
    String oldName = currentThread.getName();
    currentThread.setName(name);
    try {
      block.invoke();
    } finally {
      InlineMarker.finallyStart(1);
      currentThread.setName(oldName);
      InlineMarker.finallyEnd(1);
    } 
  }
  
  public static final int skipAll(@NotNull Buffer $this$skipAll, byte b) {
    Intrinsics.checkNotNullParameter($this$skipAll, "<this>");
    int count = 0;
    while (!$this$skipAll.exhausted() && $this$skipAll.getByte(0L) == b) {
      count++;
      $this$skipAll.readByte();
    } 
    return count;
  }
  
  public static final int indexOfNonWhitespace(@NotNull String $this$indexOfNonWhitespace, int startIndex) {
    Intrinsics.checkNotNullParameter($this$indexOfNonWhitespace, "<this>");
    for (int i = startIndex, j = $this$indexOfNonWhitespace.length(); i < j; i++) {
      char c = $this$indexOfNonWhitespace.charAt(i);
      if (c != ' ' && c != '\t')
        return i; 
    } 
    return $this$indexOfNonWhitespace.length();
  }
  
  public static final long headersContentLength(@NotNull Response $this$headersContentLength) {
    Intrinsics.checkNotNullParameter($this$headersContentLength, "<this>");
    $this$headersContentLength.headers().get("Content-Length");
    return ($this$headersContentLength.headers().get("Content-Length") != null) ? toLongOrDefault($this$headersContentLength.headers().get("Content-Length"), -1L) : -1L;
  }
  
  public static final long toLongOrDefault(@NotNull String $this$toLongOrDefault, long defaultValue) {
    long l;
    Intrinsics.checkNotNullParameter($this$toLongOrDefault, "<this>");
    try {
      l = Long.parseLong($this$toLongOrDefault);
    } catch (NumberFormatException _) {
      l = defaultValue;
    } 
    return l;
  }
  
  public static final int toNonNegativeInt(@Nullable String $this$toNonNegativeInt, int defaultValue) {
    // Byte code:
    //   0: nop
    //   1: aload_0
    //   2: dup
    //   3: ifnull -> 12
    //   6: invokestatic parseLong : (Ljava/lang/String;)J
    //   9: goto -> 15
    //   12: pop
    //   13: iload_1
    //   14: ireturn
    //   15: lstore_2
    //   16: nop
    //   17: lload_2
    //   18: ldc2_w 2147483647
    //   21: lcmp
    //   22: ifle -> 31
    //   25: ldc_w 2147483647
    //   28: goto -> 43
    //   31: lload_2
    //   32: lconst_0
    //   33: lcmp
    //   34: ifge -> 41
    //   37: iconst_0
    //   38: goto -> 43
    //   41: lload_2
    //   42: l2i
    //   43: ireturn
    //   44: astore_2
    //   45: iload_1
    //   46: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #460	-> 0
    //   #461	-> 1
    //   #461	-> 9
    //   #462	-> 16
    //   #463	-> 17
    //   #464	-> 31
    //   #465	-> 41
    //   #462	-> 43
    //   #467	-> 44
    //   #468	-> 45
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   16	28	2	value	J
    //   45	2	2	_	Ljava/lang/NumberFormatException;
    //   0	47	0	$this$toNonNegativeInt	Ljava/lang/String;
    //   0	47	1	defaultValue	I
    // Exception table:
    //   from	to	target	type
    //   0	44	44	java/lang/NumberFormatException
  }
  
  @NotNull
  public static final <T> List<T> toImmutableList(@NotNull List $this$toImmutableList) {
    Intrinsics.checkNotNullParameter($this$toImmutableList, "<this>");
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(CollectionsKt.toMutableList($this$toImmutableList)), "unmodifiableList(toMutableList())");
    return (List)Collections.unmodifiableList(CollectionsKt.toMutableList($this$toImmutableList));
  }
  
  @SafeVarargs
  @NotNull
  public static final <T> List<T> immutableListOf(@NotNull Object... elements) {
    Intrinsics.checkNotNullParameter(elements, "elements");
    Object[] arrayOfObject = (Object[])elements.clone();
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(CollectionsKt.listOf(Arrays.copyOf(arrayOfObject, arrayOfObject.length))), "unmodifiableList(listOf(*elements.clone()))");
    return (List)Collections.unmodifiableList(CollectionsKt.listOf(Arrays.copyOf(arrayOfObject, arrayOfObject.length)));
  }
  
  @NotNull
  public static final <K, V> Map<K, V> toImmutableMap(@NotNull Map<?, ?> $this$toImmutableMap) {
    Intrinsics.checkNotNullParameter($this$toImmutableMap, "<this>");
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableMap(new LinkedHashMap<>($this$toImmutableMap)), "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
    return $this$toImmutableMap.isEmpty() ? MapsKt.emptyMap() : (Map)Collections.unmodifiableMap(new LinkedHashMap<>($this$toImmutableMap));
  }
  
  public static final void closeQuietly(@NotNull Closeable $this$closeQuietly) {
    Intrinsics.checkNotNullParameter($this$closeQuietly, "<this>");
    try {
      $this$closeQuietly.close();
    } catch (RuntimeException rethrown) {
      throw rethrown;
    } catch (Exception exception) {}
  }
  
  public static final void closeQuietly(@NotNull Socket $this$closeQuietly) {
    Intrinsics.checkNotNullParameter($this$closeQuietly, "<this>");
    try {
      $this$closeQuietly.close();
    } catch (AssertionError e) {
      throw e;
    } catch (RuntimeException rethrown) {
      if (Intrinsics.areEqual(rethrown.getMessage(), "bio == null"))
        return; 
      throw rethrown;
    } catch (Exception exception) {}
  }
  
  public static final void closeQuietly(@NotNull ServerSocket $this$closeQuietly) {
    Intrinsics.checkNotNullParameter($this$closeQuietly, "<this>");
    try {
      $this$closeQuietly.close();
    } catch (RuntimeException rethrown) {
      throw rethrown;
    } catch (Exception exception) {}
  }
  
  public static final boolean isCivilized(@NotNull FileSystem $this$isCivilized, @NotNull File file) {
    Intrinsics.checkNotNullParameter($this$isCivilized, "<this>");
    Intrinsics.checkNotNullParameter(file, "file");
    Closeable closeable = (Closeable)$this$isCivilized.sink(file);
    Throwable throwable = null;
    try {
      Sink it = (Sink)closeable;
      int $i$a$-use-Util$isCivilized$1 = 0;
      try {
        $this$isCivilized.delete(file);
        return true;
      } catch (IOException iOException) {}
      Unit unit = Unit.INSTANCE;
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
    $this$isCivilized.delete(file);
    return false;
  }
  
  @NotNull
  public static final String toHexString(long $this$toHexString) {
    Intrinsics.checkNotNullExpressionValue(Long.toHexString($this$toHexString), "toHexString(this)");
    return Long.toHexString($this$toHexString);
  }
  
  @NotNull
  public static final String toHexString(int $this$toHexString) {
    Intrinsics.checkNotNullExpressionValue(Integer.toHexString($this$toHexString), "toHexString(this)");
    return Integer.toHexString($this$toHexString);
  }
  
  public static final void wait(@NotNull Object $this$wait) {
    Intrinsics.checkNotNullParameter($this$wait, "<this>");
    int $i$f$wait = 0;
    $this$wait.wait();
  }
  
  public static final void notify(@NotNull Object $this$notify) {
    Intrinsics.checkNotNullParameter($this$notify, "<this>");
    int $i$f$notify = 0;
    $this$notify.notify();
  }
  
  public static final void notifyAll(@NotNull Object $this$notifyAll) {
    Intrinsics.checkNotNullParameter($this$notifyAll, "<this>");
    int $i$f$notifyAll = 0;
    $this$notifyAll.notifyAll();
  }
  
  @Nullable
  public static final <T> T readFieldOrNull(@NotNull Object instance, @NotNull Class<T> fieldType, @NotNull String fieldName) {
    Intrinsics.checkNotNullParameter(instance, "instance");
    Intrinsics.checkNotNullParameter(fieldType, "fieldType");
    Intrinsics.checkNotNullParameter(fieldName, "fieldName");
    Class<?> c = instance.getClass();
    while (!Intrinsics.areEqual(c, Object.class)) {
      try {
        Field field = c.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object value = field.get(instance);
        return !fieldType.isInstance(value) ? null : fieldType.cast(value);
      } catch (NoSuchFieldException noSuchFieldException) {
        Intrinsics.checkNotNullExpressionValue(c.getSuperclass(), "c.superclass");
        c = c.getSuperclass();
      } 
    } 
    if (!Intrinsics.areEqual(fieldName, "delegate")) {
      Object delegate = readFieldOrNull(instance, Object.class, "delegate");
      if (delegate != null)
        return readFieldOrNull(delegate, fieldType, fieldName); 
    } 
    return null;
  }
  
  public static final <E> void addIfAbsent(@NotNull List<Object> $this$addIfAbsent, Object element) {
    Intrinsics.checkNotNullParameter($this$addIfAbsent, "<this>");
    if (!$this$addIfAbsent.contains(element))
      $this$addIfAbsent.add(element); 
  }
  
  @JvmField
  public static final boolean assertionsEnabled = OkHttpClient.class.desiredAssertionStatus();
  
  @JvmField
  @NotNull
  public static final String okHttpName = StringsKt.removeSuffix(StringsKt.removePrefix(OkHttpClient.class.getName(), "okhttp3."), "Client");
  
  @NotNull
  public static final String userAgent = "okhttp/4.12.0";
  
  static {
    Intrinsics.checkNotNullExpressionValue(OkHttpClient.class.getName(), "OkHttpClient::class.java.name");
  }
  
  public static final void assertThreadHoldsLock(@NotNull Object $this$assertThreadHoldsLock) {
    Intrinsics.checkNotNullParameter($this$assertThreadHoldsLock, "<this>");
    int $i$f$assertThreadHoldsLock = 0;
    if (assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock); 
  }
  
  public static final void assertThreadDoesntHoldLock(@NotNull Object $this$assertThreadDoesntHoldLock) {
    Intrinsics.checkNotNullParameter($this$assertThreadDoesntHoldLock, "<this>");
    int $i$f$assertThreadDoesntHoldLock = 0;
    if (assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock))
      throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock); 
  }
  
  @NotNull
  public static final Throwable withSuppressed(@NotNull Exception $this$withSuppressed, @NotNull List suppressed) {
    Intrinsics.checkNotNullParameter($this$withSuppressed, "<this>");
    Intrinsics.checkNotNullParameter(suppressed, "suppressed");
    Exception exception1 = $this$withSuppressed, $this$withSuppressed_u24lambda_u2410 = exception1;
    int $i$a$-apply-Util$withSuppressed$1 = 0;
    for (Exception e : suppressed)
      ExceptionsKt.addSuppressed($this$withSuppressed_u24lambda_u2410, e); 
    return exception1;
  }
  
  @NotNull
  public static final <T> List<T> filterList(@NotNull Iterable $this$filterList, @NotNull Function1 predicate) {
    Intrinsics.checkNotNullParameter($this$filterList, "<this>");
    Intrinsics.checkNotNullParameter(predicate, "predicate");
    int $i$f$filterList = 0;
    List<T> result = CollectionsKt.emptyList();
    for (Object i : $this$filterList) {
      if (((Boolean)predicate.invoke(i)).booleanValue()) {
        if (result.isEmpty())
          result = new ArrayList(); 
        Intrinsics.checkNotNull(result, "null cannot be cast to non-null type kotlin.collections.MutableList<T of okhttp3.internal.Util.filterList>");
        TypeIntrinsics.asMutableList(result).add(i);
      } 
    } 
    return result;
  }
}
