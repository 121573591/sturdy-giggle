package okhttp3.internal.ws;

import kotlin.Metadata;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\003\"\024\020\001\032\0020\0008\002X\004¢\006\006\n\004\b\001\020\002\"\024\020\004\032\0020\0038\002XT¢\006\006\n\004\b\004\020\005¨\006\006"}, d2 = {"Lokio/ByteString;", "EMPTY_DEFLATE_BLOCK", "Lokio/ByteString;", "", "LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION", "I", "okhttp"})
public final class MessageDeflaterKt {
  @NotNull
  private static final ByteString EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
  
  private static final int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;
}
