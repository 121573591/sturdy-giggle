package okhttp3.internal.ws;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\002\020\013\n\000\n\002\020\b\n\002\b\031\n\002\020\016\n\002\b\006\b\b\030\000 #2\0020\001:\001#BG\022\b\b\002\020\003\032\0020\002\022\n\b\002\020\005\032\004\030\0010\004\022\b\b\002\020\006\032\0020\002\022\n\b\002\020\007\032\004\030\0010\004\022\b\b\002\020\b\032\0020\002\022\b\b\002\020\t\032\0020\002¢\006\004\b\n\020\013J\020\020\f\032\0020\002HÆ\003¢\006\004\b\f\020\rJ\022\020\016\032\004\030\0010\004HÆ\003¢\006\004\b\016\020\017J\020\020\020\032\0020\002HÆ\003¢\006\004\b\020\020\rJ\022\020\021\032\004\030\0010\004HÆ\003¢\006\004\b\021\020\017J\020\020\022\032\0020\002HÆ\003¢\006\004\b\022\020\rJ\020\020\023\032\0020\002HÆ\003¢\006\004\b\023\020\rJP\020\024\032\0020\0002\b\b\002\020\003\032\0020\0022\n\b\002\020\005\032\004\030\0010\0042\b\b\002\020\006\032\0020\0022\n\b\002\020\007\032\004\030\0010\0042\b\b\002\020\b\032\0020\0022\b\b\002\020\t\032\0020\002HÆ\001¢\006\004\b\024\020\025J\032\020\027\032\0020\0022\b\020\026\032\004\030\0010\001HÖ\003¢\006\004\b\027\020\030J\020\020\031\032\0020\004HÖ\001¢\006\004\b\031\020\032J\025\020\034\032\0020\0022\006\020\033\032\0020\002¢\006\004\b\034\020\035J\020\020\037\032\0020\036HÖ\001¢\006\004\b\037\020 R\026\020\005\032\004\030\0010\0048\006X\004¢\006\006\n\004\b\005\020!R\024\020\006\032\0020\0028\006X\004¢\006\006\n\004\b\006\020\"R\024\020\003\032\0020\0028\006X\004¢\006\006\n\004\b\003\020\"R\026\020\007\032\004\030\0010\0048\006X\004¢\006\006\n\004\b\007\020!R\024\020\b\032\0020\0028\006X\004¢\006\006\n\004\b\b\020\"R\024\020\t\032\0020\0028\006X\004¢\006\006\n\004\b\t\020\"¨\006$"}, d2 = {"Lokhttp3/internal/ws/WebSocketExtensions;", "", "", "perMessageDeflate", "", "clientMaxWindowBits", "clientNoContextTakeover", "serverMaxWindowBits", "serverNoContextTakeover", "unknownValues", "<init>", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)V", "component1", "()Z", "component2", "()Ljava/lang/Integer;", "component3", "component4", "component5", "component6", "copy", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)Lokhttp3/internal/ws/WebSocketExtensions;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "clientOriginated", "noContextTakeover", "(Z)Z", "", "toString", "()Ljava/lang/String;", "Ljava/lang/Integer;", "Z", "Companion", "okhttp"})
public final class WebSocketExtensions {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @JvmField
  public final boolean perMessageDeflate;
  
  @JvmField
  @Nullable
  public final Integer clientMaxWindowBits;
  
  @JvmField
  public final boolean clientNoContextTakeover;
  
  @JvmField
  @Nullable
  public final Integer serverMaxWindowBits;
  
  @JvmField
  public final boolean serverNoContextTakeover;
  
  @JvmField
  public final boolean unknownValues;
  
  @NotNull
  private static final String HEADER_WEB_SOCKET_EXTENSION = "Sec-WebSocket-Extensions";
  
  public WebSocketExtensions(boolean perMessageDeflate, @Nullable Integer clientMaxWindowBits, boolean clientNoContextTakeover, @Nullable Integer serverMaxWindowBits, boolean serverNoContextTakeover, boolean unknownValues) {
    this.perMessageDeflate = perMessageDeflate;
    this.clientMaxWindowBits = clientMaxWindowBits;
    this.clientNoContextTakeover = clientNoContextTakeover;
    this.serverMaxWindowBits = serverMaxWindowBits;
    this.serverNoContextTakeover = serverNoContextTakeover;
    this.unknownValues = unknownValues;
  }
  
  public final boolean noContextTakeover(boolean clientOriginated) {
    return clientOriginated ? 
      this.clientNoContextTakeover : 
      
      this.serverNoContextTakeover;
  }
  
  public final boolean component1() {
    return this.perMessageDeflate;
  }
  
  @Nullable
  public final Integer component2() {
    return this.clientMaxWindowBits;
  }
  
  public final boolean component3() {
    return this.clientNoContextTakeover;
  }
  
  @Nullable
  public final Integer component4() {
    return this.serverMaxWindowBits;
  }
  
  public final boolean component5() {
    return this.serverNoContextTakeover;
  }
  
  public final boolean component6() {
    return this.unknownValues;
  }
  
  @NotNull
  public final WebSocketExtensions copy(boolean perMessageDeflate, @Nullable Integer clientMaxWindowBits, boolean clientNoContextTakeover, @Nullable Integer serverMaxWindowBits, boolean serverNoContextTakeover, boolean unknownValues) {
    return new WebSocketExtensions(perMessageDeflate, clientMaxWindowBits, clientNoContextTakeover, serverMaxWindowBits, serverNoContextTakeover, unknownValues);
  }
  
  @NotNull
  public String toString() {
    return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + ')';
  }
  
  public int hashCode() {
    if (this.perMessageDeflate);
    result = 1;
    result = result * 31 + ((this.clientMaxWindowBits == null) ? 0 : this.clientMaxWindowBits.hashCode());
    if (this.clientNoContextTakeover);
    result = result * 31 + 1;
    result = result * 31 + ((this.serverMaxWindowBits == null) ? 0 : this.serverMaxWindowBits.hashCode());
    if (this.serverNoContextTakeover);
    result = result * 31 + 1;
    if (this.unknownValues);
    return result * 31 + 1;
  }
  
  public boolean equals(@Nullable Object other) {
    if (this == other)
      return true; 
    if (!(other instanceof WebSocketExtensions))
      return false; 
    WebSocketExtensions webSocketExtensions = (WebSocketExtensions)other;
    return (this.perMessageDeflate != webSocketExtensions.perMessageDeflate) ? false : (!Intrinsics.areEqual(this.clientMaxWindowBits, webSocketExtensions.clientMaxWindowBits) ? false : ((this.clientNoContextTakeover != webSocketExtensions.clientNoContextTakeover) ? false : (!Intrinsics.areEqual(this.serverMaxWindowBits, webSocketExtensions.serverMaxWindowBits) ? false : ((this.serverNoContextTakeover != webSocketExtensions.serverNoContextTakeover) ? false : (!(this.unknownValues != webSocketExtensions.unknownValues))))));
  }
  
  public WebSocketExtensions() {
    this(false, null, false, null, false, false, 63, null);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bR\024\020\n\032\0020\t8\002XT¢\006\006\n\004\b\n\020\013¨\006\f"}, d2 = {"Lokhttp3/internal/ws/WebSocketExtensions$Companion;", "", "<init>", "()V", "Lokhttp3/Headers;", "responseHeaders", "Lokhttp3/internal/ws/WebSocketExtensions;", "parse", "(Lokhttp3/Headers;)Lokhttp3/internal/ws/WebSocketExtensions;", "", "HEADER_WEB_SOCKET_EXTENSION", "Ljava/lang/String;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final WebSocketExtensions parse(@NotNull Headers responseHeaders) throws IOException {
      Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
      boolean compressionEnabled = false;
      Integer clientMaxWindowBits = null;
      boolean clientNoContextTakeover = false;
      Integer serverMaxWindowBits = null;
      boolean serverNoContextTakeover = false;
      boolean unexpectedValues = false;
      for (int i = 0, j = responseHeaders.size(); i < j; i++) {
        if (StringsKt.equals(responseHeaders.name(i), "Sec-WebSocket-Extensions", true)) {
          String header = responseHeaders.value(i);
          int pos = 0;
          while (pos < header.length()) {
            int extensionEnd = Util.delimiterOffset$default(header, ',', pos, 0, 4, null);
            int extensionTokenEnd = Util.delimiterOffset(header, ';', pos, extensionEnd);
            String extensionToken = Util.trimSubstring(header, pos, extensionTokenEnd);
            pos = extensionTokenEnd + 1;
            if (StringsKt.equals(extensionToken, "permessage-deflate", true)) {
              if (compressionEnabled)
                unexpectedValues = true; 
              compressionEnabled = true;
              while (pos < extensionEnd) {
                int parameterEnd = Util.delimiterOffset(header, ';', pos, extensionEnd);
                int equals = Util.delimiterOffset(header, '=', pos, parameterEnd);
                String name = Util.trimSubstring(header, pos, equals);
                String value = (equals < parameterEnd) ? 
                  StringsKt.removeSurrounding(Util.trimSubstring(header, equals + 1, parameterEnd), "\"") : 
                  
                  null;
                pos = parameterEnd + 1;
                if (StringsKt.equals(name, "client_max_window_bits", true)) {
                  if (clientMaxWindowBits != null)
                    unexpectedValues = true; 
                  clientMaxWindowBits = (value != null) ? StringsKt.toIntOrNull(value) : null;
                  if (clientMaxWindowBits == null)
                    unexpectedValues = true; 
                  continue;
                } 
                if (StringsKt.equals(name, "client_no_context_takeover", true)) {
                  if (clientNoContextTakeover)
                    unexpectedValues = true; 
                  if (value != null)
                    unexpectedValues = true; 
                  clientNoContextTakeover = true;
                  continue;
                } 
                if (StringsKt.equals(name, "server_max_window_bits", true)) {
                  if (serverMaxWindowBits != null)
                    unexpectedValues = true; 
                  serverMaxWindowBits = (value != null) ? StringsKt.toIntOrNull(value) : null;
                  if (serverMaxWindowBits == null)
                    unexpectedValues = true; 
                  continue;
                } 
                if (StringsKt.equals(name, "server_no_context_takeover", true)) {
                  if (serverNoContextTakeover)
                    unexpectedValues = true; 
                  if (value != null)
                    unexpectedValues = true; 
                  serverNoContextTakeover = true;
                  continue;
                } 
                unexpectedValues = true;
              } 
              continue;
            } 
            unexpectedValues = true;
          } 
        } 
      } 
      return new WebSocketExtensions(
          compressionEnabled, 
          clientMaxWindowBits, 
          clientNoContextTakeover, 
          serverMaxWindowBits, 
          serverNoContextTakeover, 
          unexpectedValues);
    }
  }
}
