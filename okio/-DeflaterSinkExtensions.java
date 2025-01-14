package okio;

import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\032\036\020\004\032\0020\003*\0020\0002\b\b\002\020\002\032\0020\001H\b¢\006\004\b\004\020\005¨\006\006"}, d2 = {"Lokio/Sink;", "Ljava/util/zip/Deflater;", "deflater", "Lokio/DeflaterSink;", "deflate", "(Lokio/Sink;Ljava/util/zip/Deflater;)Lokio/DeflaterSink;", "okio"})
@JvmName(name = "-DeflaterSinkExtensions")
public final class -DeflaterSinkExtensions {
  @NotNull
  public static final DeflaterSink deflate(@NotNull Sink $this$deflate, @NotNull Deflater deflater) {
    Intrinsics.checkNotNullParameter($this$deflate, "<this>");
    Intrinsics.checkNotNullParameter(deflater, "deflater");
    int $i$f$deflate = 0;
    return new DeflaterSink($this$deflate, deflater);
  }
}
