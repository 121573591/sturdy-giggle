package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\006\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\006\b\020\030\0002\0020\001B#\022\006\020\003\032\0020\002\022\022\020\007\032\016\022\004\022\0020\005\022\004\022\0020\0060\004¢\006\004\b\b\020\tJ\017\020\n\032\0020\006H\026¢\006\004\b\n\020\013J\017\020\f\032\0020\006H\026¢\006\004\b\f\020\013J\037\020\021\032\0020\0062\006\020\016\032\0020\r2\006\020\020\032\0020\017H\026¢\006\004\b\021\020\022R\026\020\024\032\0020\0238\002@\002X\016¢\006\006\n\004\b\024\020\025R#\020\007\032\016\022\004\022\0020\005\022\004\022\0020\0060\0048\006¢\006\f\n\004\b\007\020\026\032\004\b\027\020\030¨\006\031"}, d2 = {"Lokhttp3/internal/cache/FaultHidingSink;", "Lokio/ForwardingSink;", "Lokio/Sink;", "delegate", "Lkotlin/Function1;", "Ljava/io/IOException;", "", "onException", "<init>", "(Lokio/Sink;Lkotlin/jvm/functions/Function1;)V", "close", "()V", "flush", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "", "hasErrors", "Z", "Lkotlin/jvm/functions/Function1;", "getOnException", "()Lkotlin/jvm/functions/Function1;", "okhttp"})
public class FaultHidingSink extends ForwardingSink {
  @NotNull
  private final Function1<IOException, Unit> onException;
  
  private boolean hasErrors;
  
  @NotNull
  public final Function1<IOException, Unit> getOnException() {
    return this.onException;
  }
  
  public FaultHidingSink(@NotNull Sink delegate, @NotNull Function1<IOException, Unit> onException) {
    super(delegate);
    this.onException = onException;
  }
  
  public void write(@NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    if (this.hasErrors) {
      source.skip(byteCount);
      return;
    } 
    try {
      super.write(source, byteCount);
    } catch (IOException e) {
      this.hasErrors = true;
      this.onException.invoke(e);
    } 
  }
  
  public void flush() {
    if (this.hasErrors)
      return; 
    try {
      super.flush();
    } catch (IOException e) {
      this.hasErrors = true;
      this.onException.invoke(e);
    } 
  }
  
  public void close() {
    if (this.hasErrors)
      return; 
    try {
      super.close();
    } catch (IOException e) {
      this.hasErrors = true;
      this.onException.invoke(e);
    } 
  }
}
