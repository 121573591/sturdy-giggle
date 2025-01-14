package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\f\n\002\030\002\n\002\030\002\n\002\b\003\032\024\020\002\032\0020\001*\0020\000H\b¢\006\004\b\002\020\003¨\006\004"}, d2 = {"Lokio/Sink;", "Lokio/GzipSink;", "gzip", "(Lokio/Sink;)Lokio/GzipSink;", "okio"})
@JvmName(name = "-GzipSinkExtensions")
public final class -GzipSinkExtensions {
  @NotNull
  public static final GzipSink gzip(@NotNull Sink $this$gzip) {
    Intrinsics.checkNotNullParameter($this$gzip, "<this>");
    int $i$f$gzip = 0;
    return new GzipSink($this$gzip);
  }
}
