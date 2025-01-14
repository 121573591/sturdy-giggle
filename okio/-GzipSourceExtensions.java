package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\"\n\002\020\b\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\007\n\002\020\005\n\002\b\006\032\034\020\003\032\0020\002*\0020\0002\006\020\001\032\0020\000H\b¢\006\004\b\003\020\004\032\024\020\007\032\0020\006*\0020\005H\b¢\006\004\b\007\020\b\"\024\020\t\032\0020\0008\002XT¢\006\006\n\004\b\t\020\n\"\024\020\013\032\0020\0008\002XT¢\006\006\n\004\b\013\020\n\"\024\020\f\032\0020\0008\002XT¢\006\006\n\004\b\f\020\n\"\024\020\r\032\0020\0008\002XT¢\006\006\n\004\b\r\020\n\"\024\020\017\032\0020\0168\002XT¢\006\006\n\004\b\017\020\020\"\024\020\021\032\0020\0168\002XT¢\006\006\n\004\b\021\020\020\"\024\020\022\032\0020\0168\002XT¢\006\006\n\004\b\022\020\020\"\024\020\023\032\0020\0168\002XT¢\006\006\n\004\b\023\020\020¨\006\024"}, d2 = {"", "bit", "", "getBit", "(II)Z", "Lokio/Source;", "Lokio/GzipSource;", "gzip", "(Lokio/Source;)Lokio/GzipSource;", "FCOMMENT", "I", "FEXTRA", "FHCRC", "FNAME", "", "SECTION_BODY", "B", "SECTION_DONE", "SECTION_HEADER", "SECTION_TRAILER", "okio"})
@JvmName(name = "-GzipSourceExtensions")
public final class -GzipSourceExtensions {
  private static final int FHCRC = 1;
  
  private static final int FEXTRA = 2;
  
  private static final int FNAME = 3;
  
  private static final int FCOMMENT = 4;
  
  private static final byte SECTION_HEADER = 0;
  
  private static final byte SECTION_BODY = 1;
  
  private static final byte SECTION_TRAILER = 2;
  
  private static final byte SECTION_DONE = 3;
  
  private static final boolean getBit(int $this$getBit, int bit) {
    int $i$f$getBit = 0;
    return (($this$getBit >> bit & 0x1) == 1);
  }
  
  @NotNull
  public static final GzipSource gzip(@NotNull Source $this$gzip) {
    Intrinsics.checkNotNullParameter($this$gzip, "<this>");
    int $i$f$gzip = 0;
    return new GzipSource($this$gzip);
  }
}
