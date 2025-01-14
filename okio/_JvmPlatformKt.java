package okio;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000H\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\020\022\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\032\023\020\002\032\0060\000j\002`\001H\000¢\006\004\b\002\020\003\032\023\020\006\032\0020\005*\0020\004H\000¢\006\004\b\006\020\007\032\023\020\b\032\0020\004*\0020\005H\000¢\006\004\b\b\020\t\032/\020\r\032\0028\000\"\004\b\000\020\n*\0060\000j\002`\0012\f\020\f\032\b\022\004\022\0028\0000\013H\bø\001\000¢\006\004\b\r\020\016*\n\020\020\"\0020\0172\0020\017*\n\020\022\"\0020\0212\0020\021*\n\020\024\"\0020\0232\0020\023*\n\020\026\"\0020\0252\0020\025*\n\020\030\"\0020\0272\0020\027*\n\020\031\"\0020\0002\0020\000*\n\020\033\"\0020\0322\0020\032\002\007\n\005\b20\001¨\006\034"}, d2 = {"Ljava/util/concurrent/locks/ReentrantLock;", "Lokio/Lock;", "newLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "", "", "asUtf8ToByteArray", "(Ljava/lang/String;)[B", "toUtf8String", "([B)Ljava/lang/String;", "T", "Lkotlin/Function0;", "action", "withLock", "(Ljava/util/concurrent/locks/ReentrantLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Ljava/lang/ArrayIndexOutOfBoundsException;", "ArrayIndexOutOfBoundsException", "Ljava/io/Closeable;", "Closeable", "Ljava/io/EOFException;", "EOFException", "Ljava/io/FileNotFoundException;", "FileNotFoundException", "Ljava/io/IOException;", "IOException", "Lock", "Ljava/net/ProtocolException;", "ProtocolException", "okio"})
public final class _JvmPlatformKt {
  @NotNull
  public static final String toUtf8String(@NotNull byte[] $this$toUtf8String) {
    Intrinsics.checkNotNullParameter($this$toUtf8String, "<this>");
    return new String($this$toUtf8String, Charsets.UTF_8);
  }
  
  @NotNull
  public static final byte[] asUtf8ToByteArray(@NotNull String $this$asUtf8ToByteArray) {
    Intrinsics.checkNotNullParameter($this$asUtf8ToByteArray, "<this>");
    Intrinsics.checkNotNullExpressionValue($this$asUtf8ToByteArray.getBytes(Charsets.UTF_8), "this as java.lang.String).getBytes(charset)");
    return $this$asUtf8ToByteArray.getBytes(Charsets.UTF_8);
  }
  
  @NotNull
  public static final ReentrantLock newLock() {
    return new ReentrantLock();
  }
  
  public static final <T> T withLock(@NotNull ReentrantLock $this$withLock, @NotNull Function0 action) {
    Intrinsics.checkNotNullParameter($this$withLock, "<this>");
    Intrinsics.checkNotNullParameter(action, "action");
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock = $this$withLock;
    reentrantLock.lock();
    try {
      Object object = action.invoke();
    } finally {
      InlineMarker.finallyStart(1);
      reentrantLock.unlock();
      InlineMarker.finallyEnd(1);
    } 
    return (T)object;
  }
}
