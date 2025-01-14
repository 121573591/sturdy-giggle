package okhttp3.internal.ws;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020\b\n\002\b\003\n\002\030\002\n\000\n\002\020\022\n\002\020\002\n\002\b\017\n\002\020\t\n\002\b\017\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\025\020\006\032\0020\0042\006\020\005\032\0020\004¢\006\004\b\006\020\007J\027\020\n\032\004\030\0010\0042\006\020\t\032\0020\b¢\006\004\b\n\020\013J\035\020\020\032\0020\0172\006\020\r\032\0020\f2\006\020\005\032\0020\016¢\006\004\b\020\020\021J\025\020\022\032\0020\0172\006\020\t\032\0020\b¢\006\004\b\022\020\023R\024\020\024\032\0020\0048\000XT¢\006\006\n\004\b\024\020\025R\024\020\026\032\0020\b8\000XT¢\006\006\n\004\b\026\020\027R\024\020\030\032\0020\b8\000XT¢\006\006\n\004\b\030\020\027R\024\020\031\032\0020\b8\000XT¢\006\006\n\004\b\031\020\027R\024\020\032\032\0020\b8\000XT¢\006\006\n\004\b\032\020\027R\024\020\033\032\0020\b8\000XT¢\006\006\n\004\b\033\020\027R\024\020\034\032\0020\b8\000XT¢\006\006\n\004\b\034\020\027R\024\020\035\032\0020\b8\000XT¢\006\006\n\004\b\035\020\027R\024\020\036\032\0020\b8\000XT¢\006\006\n\004\b\036\020\027R\024\020 \032\0020\0378\000XT¢\006\006\n\004\b \020!R\024\020\"\032\0020\b8\000XT¢\006\006\n\004\b\"\020\027R\024\020#\032\0020\b8\000XT¢\006\006\n\004\b#\020\027R\024\020$\032\0020\b8\000XT¢\006\006\n\004\b$\020\027R\024\020%\032\0020\b8\000XT¢\006\006\n\004\b%\020\027R\024\020&\032\0020\b8\000XT¢\006\006\n\004\b&\020\027R\024\020'\032\0020\b8\000XT¢\006\006\n\004\b'\020\027R\024\020(\032\0020\b8\000XT¢\006\006\n\004\b(\020\027R\024\020)\032\0020\b8\000XT¢\006\006\n\004\b)\020\027R\024\020*\032\0020\0378\000XT¢\006\006\n\004\b*\020!R\024\020+\032\0020\b8\000XT¢\006\006\n\004\b+\020\027R\024\020,\032\0020\b8\000XT¢\006\006\n\004\b,\020\027R\024\020-\032\0020\0378\000XT¢\006\006\n\004\b-\020!¨\006."}, d2 = {"Lokhttp3/internal/ws/WebSocketProtocol;", "", "<init>", "()V", "", "key", "acceptHeader", "(Ljava/lang/String;)Ljava/lang/String;", "", "code", "closeCodeExceptionMessage", "(I)Ljava/lang/String;", "Lokio/Buffer$UnsafeCursor;", "cursor", "", "", "toggleMask", "(Lokio/Buffer$UnsafeCursor;[B)V", "validateCloseCode", "(I)V", "ACCEPT_MAGIC", "Ljava/lang/String;", "B0_FLAG_FIN", "I", "B0_FLAG_RSV1", "B0_FLAG_RSV2", "B0_FLAG_RSV3", "B0_MASK_OPCODE", "B1_FLAG_MASK", "B1_MASK_LENGTH", "CLOSE_CLIENT_GOING_AWAY", "", "CLOSE_MESSAGE_MAX", "J", "CLOSE_NO_STATUS_CODE", "OPCODE_BINARY", "OPCODE_CONTINUATION", "OPCODE_CONTROL_CLOSE", "OPCODE_CONTROL_PING", "OPCODE_CONTROL_PONG", "OPCODE_FLAG_CONTROL", "OPCODE_TEXT", "PAYLOAD_BYTE_MAX", "PAYLOAD_LONG", "PAYLOAD_SHORT", "PAYLOAD_SHORT_MAX", "okhttp"})
@SourceDebugExtension({"SMAP\nWebSocketProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketProtocol.kt\nokhttp3/internal/ws/WebSocketProtocol\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,141:1\n1#2:142\n*E\n"})
public final class WebSocketProtocol {
  @NotNull
  public static final WebSocketProtocol INSTANCE = new WebSocketProtocol();
  
  @NotNull
  public static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
  
  public static final int B0_FLAG_FIN = 128;
  
  public static final int B0_FLAG_RSV1 = 64;
  
  public static final int B0_FLAG_RSV2 = 32;
  
  public static final int B0_FLAG_RSV3 = 16;
  
  public static final int B0_MASK_OPCODE = 15;
  
  public static final int OPCODE_FLAG_CONTROL = 8;
  
  public static final int B1_FLAG_MASK = 128;
  
  public static final int B1_MASK_LENGTH = 127;
  
  public static final int OPCODE_CONTINUATION = 0;
  
  public static final int OPCODE_TEXT = 1;
  
  public static final int OPCODE_BINARY = 2;
  
  public static final int OPCODE_CONTROL_CLOSE = 8;
  
  public static final int OPCODE_CONTROL_PING = 9;
  
  public static final int OPCODE_CONTROL_PONG = 10;
  
  public static final long PAYLOAD_BYTE_MAX = 125L;
  
  public static final long CLOSE_MESSAGE_MAX = 123L;
  
  public static final int PAYLOAD_SHORT = 126;
  
  public static final long PAYLOAD_SHORT_MAX = 65535L;
  
  public static final int PAYLOAD_LONG = 127;
  
  public static final int CLOSE_CLIENT_GOING_AWAY = 1001;
  
  public static final int CLOSE_NO_STATUS_CODE = 1005;
  
  public final void toggleMask(@NotNull Buffer.UnsafeCursor cursor, @NotNull byte[] key) {
    Intrinsics.checkNotNullParameter(cursor, "cursor");
    Intrinsics.checkNotNullParameter(key, "key");
    int keyIndex = 0;
    int keyLength = key.length;
    do {
      byte[] buffer = cursor.data;
      int i = cursor.start;
      int end = cursor.end;
      if (buffer == null)
        continue; 
      while (i < end) {
        keyIndex %= keyLength;
        int bufferInt = buffer[i];
        int keyInt = key[keyIndex];
        buffer[i] = (byte)(bufferInt ^ keyInt);
        i++;
        keyIndex++;
      } 
    } while (cursor.next() != -1);
  }
  
  @Nullable
  public final String closeCodeExceptionMessage(int code) {
    if (code < 1000 || code >= 5000) {
    
    } else if (!((1004 <= code) ? ((code < 1007) ? 1 : 0) : 0)) {
      if ((1015 <= code) ? ((code < 3000)) : false);
      return null;
    } 
    return (String)new StringBuilder();
  }
  
  public final void validateCloseCode(int code) {
    String message = closeCodeExceptionMessage(code);
    if (!((message == null) ? 1 : 0)) {
      int $i$a$-require-WebSocketProtocol$validateCloseCode$1 = 0;
      Intrinsics.checkNotNull(message);
      String str = message;
      throw new IllegalArgumentException(str.toString());
    } 
  }
  
  @NotNull
  public final String acceptHeader(@NotNull String key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return ByteString.Companion.encodeUtf8(key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
  }
}
