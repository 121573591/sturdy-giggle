package okio;

import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\032\036\020\004\032\0020\003*\0020\0002\b\b\002\020\002\032\0020\001H\b¢\006\004\b\004\020\005¨\006\006"}, d2 = {"Lokio/Source;", "Ljava/util/zip/Inflater;", "inflater", "Lokio/InflaterSource;", "inflate", "(Lokio/Source;Ljava/util/zip/Inflater;)Lokio/InflaterSource;", "okio"})
@JvmName(name = "-InflaterSourceExtensions")
public final class -InflaterSourceExtensions {
  @NotNull
  public static final InflaterSource inflate(@NotNull Source $this$inflate, @NotNull Inflater inflater) {
    Intrinsics.checkNotNullParameter($this$inflate, "<this>");
    Intrinsics.checkNotNullParameter(inflater, "inflater");
    int $i$f$inflate = 0;
    return new InflaterSource($this$inflate, inflater);
  }
}
