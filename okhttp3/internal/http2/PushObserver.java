package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\020\002\n\002\b\004\bf\030\000 \0302\0020\001:\001\030J/\020\t\032\0020\0072\006\020\003\032\0020\0022\006\020\005\032\0020\0042\006\020\006\032\0020\0022\006\020\b\032\0020\007H&¢\006\004\b\t\020\nJ-\020\016\032\0020\0072\006\020\003\032\0020\0022\f\020\r\032\b\022\004\022\0020\f0\0132\006\020\b\032\0020\007H&¢\006\004\b\016\020\017J%\020\021\032\0020\0072\006\020\003\032\0020\0022\f\020\020\032\b\022\004\022\0020\f0\013H&¢\006\004\b\021\020\022J\037\020\026\032\0020\0252\006\020\003\032\0020\0022\006\020\024\032\0020\023H&¢\006\004\b\026\020\027¨\006\031"}, d2 = {"Lokhttp3/internal/http2/PushObserver;", "", "", "streamId", "Lokio/BufferedSource;", "source", "byteCount", "", "last", "onData", "(ILokio/BufferedSource;IZ)Z", "", "Lokhttp3/internal/http2/Header;", "responseHeaders", "onHeaders", "(ILjava/util/List;Z)Z", "requestHeaders", "onRequest", "(ILjava/util/List;)Z", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "", "onReset", "(ILokhttp3/internal/http2/ErrorCode;)V", "Companion", "okhttp"})
public interface PushObserver {
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001:\001\007B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\001¨\006\b"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion;", "", "<init>", "()V", "Lokhttp3/internal/http2/PushObserver;", "CANCEL", "Lokhttp3/internal/http2/PushObserver;", "PushObserverCancel", "okhttp"})
  public static final class Companion {
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\020\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J/\020\013\032\0020\t2\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\0042\006\020\n\032\0020\tH\026¢\006\004\b\013\020\fJ-\020\020\032\0020\t2\006\020\005\032\0020\0042\f\020\017\032\b\022\004\022\0020\0160\r2\006\020\n\032\0020\tH\026¢\006\004\b\020\020\021J%\020\023\032\0020\t2\006\020\005\032\0020\0042\f\020\022\032\b\022\004\022\0020\0160\rH\026¢\006\004\b\023\020\024J\037\020\030\032\0020\0272\006\020\005\032\0020\0042\006\020\026\032\0020\025H\026¢\006\004\b\030\020\031¨\006\032"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion$PushObserverCancel;", "Lokhttp3/internal/http2/PushObserver;", "<init>", "()V", "", "streamId", "Lokio/BufferedSource;", "source", "byteCount", "", "last", "onData", "(ILokio/BufferedSource;IZ)Z", "", "Lokhttp3/internal/http2/Header;", "responseHeaders", "onHeaders", "(ILjava/util/List;Z)Z", "requestHeaders", "onRequest", "(ILjava/util/List;)Z", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "", "onReset", "(ILokhttp3/internal/http2/ErrorCode;)V", "okhttp"})
    private static final class PushObserverCancel implements PushObserver {
      public boolean onRequest(int streamId, @NotNull List requestHeaders) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        return true;
      }
      
      public boolean onHeaders(int streamId, @NotNull List responseHeaders, boolean last) {
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        return true;
      }
      
      public boolean onData(int streamId, @NotNull BufferedSource source, int byteCount, boolean last) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        source.skip(byteCount);
        return true;
      }
      
      public void onReset(int streamId, @NotNull ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
      }
    }
  }
  
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  @JvmField
  @NotNull
  public static final PushObserver CANCEL = new Companion.PushObserverCancel();
  
  boolean onRequest(int paramInt, @NotNull List<Header> paramList);
  
  boolean onHeaders(int paramInt, @NotNull List<Header> paramList, boolean paramBoolean);
  
  boolean onData(int paramInt1, @NotNull BufferedSource paramBufferedSource, int paramInt2, boolean paramBoolean) throws IOException;
  
  void onReset(int paramInt, @NotNull ErrorCode paramErrorCode);
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\020\002\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J/\020\013\032\0020\t2\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\b\032\0020\0042\006\020\n\032\0020\tH\026¢\006\004\b\013\020\fJ-\020\020\032\0020\t2\006\020\005\032\0020\0042\f\020\017\032\b\022\004\022\0020\0160\r2\006\020\n\032\0020\tH\026¢\006\004\b\020\020\021J%\020\023\032\0020\t2\006\020\005\032\0020\0042\f\020\022\032\b\022\004\022\0020\0160\rH\026¢\006\004\b\023\020\024J\037\020\030\032\0020\0272\006\020\005\032\0020\0042\006\020\026\032\0020\025H\026¢\006\004\b\030\020\031¨\006\032"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion$PushObserverCancel;", "Lokhttp3/internal/http2/PushObserver;", "<init>", "()V", "", "streamId", "Lokio/BufferedSource;", "source", "byteCount", "", "last", "onData", "(ILokio/BufferedSource;IZ)Z", "", "Lokhttp3/internal/http2/Header;", "responseHeaders", "onHeaders", "(ILjava/util/List;Z)Z", "requestHeaders", "onRequest", "(ILjava/util/List;)Z", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "", "onReset", "(ILokhttp3/internal/http2/ErrorCode;)V", "okhttp"})
  private static final class PushObserverCancel implements PushObserver {
    public boolean onRequest(int streamId, @NotNull List requestHeaders) {
      Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
      return true;
    }
    
    public boolean onHeaders(int streamId, @NotNull List responseHeaders, boolean last) {
      Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
      return true;
    }
    
    public boolean onData(int streamId, @NotNull BufferedSource source, int byteCount, boolean last) throws IOException {
      Intrinsics.checkNotNullParameter(source, "source");
      source.skip(byteCount);
      return true;
    }
    
    public void onReset(int streamId, @NotNull ErrorCode errorCode) {
      Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    }
  }
}
