package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\020\n\002\030\002\n\002\020\020\n\002\020\b\n\002\b\026\b\001\030\000 \t2\b\022\004\022\0020\0000\001:\001\tB\021\b\002\022\006\020\003\032\0020\002¢\006\004\b\004\020\005R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\006\032\004\b\007\020\bj\002\b\nj\002\b\013j\002\b\fj\002\b\rj\002\b\016j\002\b\017j\002\b\020j\002\b\021j\002\b\022j\002\b\023j\002\b\024j\002\b\025j\002\b\026j\002\b\027¨\006\030"}, d2 = {"Lokhttp3/internal/http2/ErrorCode;", "", "", "httpCode", "<init>", "(Ljava/lang/String;II)V", "I", "getHttpCode", "()I", "Companion", "NO_ERROR", "PROTOCOL_ERROR", "INTERNAL_ERROR", "FLOW_CONTROL_ERROR", "SETTINGS_TIMEOUT", "STREAM_CLOSED", "FRAME_SIZE_ERROR", "REFUSED_STREAM", "CANCEL", "COMPRESSION_ERROR", "CONNECT_ERROR", "ENHANCE_YOUR_CALM", "INADEQUATE_SECURITY", "HTTP_1_1_REQUIRED", "okhttp"})
public enum ErrorCode {
  NO_ERROR(0),
  PROTOCOL_ERROR(1),
  INTERNAL_ERROR(2),
  FLOW_CONTROL_ERROR(3),
  SETTINGS_TIMEOUT(4),
  STREAM_CLOSED(5),
  FRAME_SIZE_ERROR(6),
  REFUSED_STREAM(7),
  CANCEL(8),
  COMPRESSION_ERROR(9),
  CONNECT_ERROR(10),
  ENHANCE_YOUR_CALM(11),
  INADEQUATE_SECURITY(12),
  HTTP_1_1_REQUIRED(13);
  
  @NotNull
  public static final Companion Companion;
  
  private final int httpCode;
  
  ErrorCode(int httpCode) {
    this.httpCode = httpCode;
  }
  
  public final int getHttpCode() {
    return this.httpCode;
  }
  
  static {
    Companion = new Companion(null);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\004\030\0010\0062\006\020\005\032\0020\004¢\006\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/http2/ErrorCode$Companion;", "", "<init>", "()V", "", "code", "Lokhttp3/internal/http2/ErrorCode;", "fromHttp2", "(I)Lokhttp3/internal/http2/ErrorCode;", "okhttp"})
  @SourceDebugExtension({"SMAP\nErrorCode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ErrorCode.kt\nokhttp3/internal/http2/ErrorCode$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,53:1\n1#2:54\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @Nullable
    public final ErrorCode fromHttp2(int code) {
      // Byte code:
      //   0: invokestatic values : ()[Lokhttp3/internal/http2/ErrorCode;
      //   3: astore_2
      //   4: iconst_0
      //   5: istore_3
      //   6: aload_2
      //   7: arraylength
      //   8: istore #4
      //   10: iload_3
      //   11: iload #4
      //   13: if_icmpge -> 56
      //   16: aload_2
      //   17: iload_3
      //   18: aaload
      //   19: astore #5
      //   21: aload #5
      //   23: astore #6
      //   25: iconst_0
      //   26: istore #7
      //   28: aload #6
      //   30: invokevirtual getHttpCode : ()I
      //   33: iload_1
      //   34: if_icmpne -> 41
      //   37: iconst_1
      //   38: goto -> 42
      //   41: iconst_0
      //   42: ifeq -> 50
      //   45: aload #5
      //   47: goto -> 57
      //   50: iinc #3, 1
      //   53: goto -> 10
      //   56: aconst_null
      //   57: areturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #50	-> 0
      //   #54	-> 25
      //   #50	-> 28
      //   #50	-> 42
      //   #50	-> 57
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   28	14	7	$i$a$-find-ErrorCode$Companion$fromHttp2$1	I
      //   25	17	6	it	Lokhttp3/internal/http2/ErrorCode;
      //   0	58	0	this	Lokhttp3/internal/http2/ErrorCode$Companion;
      //   0	58	1	code	I
    }
  }
}
