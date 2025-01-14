package okhttp3.internal.http;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\002\b\n\030\000 \0172\0020\001:\001\017B\037\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tJ\017\020\n\032\0020\006H\026¢\006\004\b\n\020\013R\024\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\fR\024\020\007\032\0020\0068\006X\004¢\006\006\n\004\b\007\020\rR\024\020\003\032\0020\0028\006X\004¢\006\006\n\004\b\003\020\016¨\006\020"}, d2 = {"Lokhttp3/internal/http/StatusLine;", "", "Lokhttp3/Protocol;", "protocol", "", "code", "", "message", "<init>", "(Lokhttp3/Protocol;ILjava/lang/String;)V", "toString", "()Ljava/lang/String;", "I", "Ljava/lang/String;", "Lokhttp3/Protocol;", "Companion", "okhttp"})
public final class StatusLine {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @JvmField
  @NotNull
  public final Protocol protocol;
  
  @JvmField
  public final int code;
  
  @JvmField
  @NotNull
  public final String message;
  
  public static final int HTTP_TEMP_REDIRECT = 307;
  
  public static final int HTTP_PERM_REDIRECT = 308;
  
  public static final int HTTP_MISDIRECTED_REQUEST = 421;
  
  public static final int HTTP_CONTINUE = 100;
  
  public StatusLine(@NotNull Protocol protocol, int code, @NotNull String message) {
    this.protocol = protocol;
    this.code = code;
    this.message = message;
  }
  
  @NotNull
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder(), $this$toString_u24lambda_u240 = stringBuilder1;
    int $i$a$-buildString-StatusLine$toString$1 = 0;
    if (this.protocol == Protocol.HTTP_1_0) {
      $this$toString_u24lambda_u240.append("HTTP/1.0");
    } else {
      $this$toString_u24lambda_u240.append("HTTP/1.1");
    } 
    $this$toString_u24lambda_u240.append(' ').append(this.code);
    $this$toString_u24lambda_u240.append(' ').append(this.message);
    Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
    return stringBuilder1.toString();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020\b\n\002\b\006\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bJ\025\020\013\032\0020\0062\006\020\n\032\0020\t¢\006\004\b\013\020\fR\024\020\016\032\0020\r8\006XT¢\006\006\n\004\b\016\020\017R\024\020\020\032\0020\r8\006XT¢\006\006\n\004\b\020\020\017R\024\020\021\032\0020\r8\006XT¢\006\006\n\004\b\021\020\017R\024\020\022\032\0020\r8\006XT¢\006\006\n\004\b\022\020\017¨\006\023"}, d2 = {"Lokhttp3/internal/http/StatusLine$Companion;", "", "<init>", "()V", "Lokhttp3/Response;", "response", "Lokhttp3/internal/http/StatusLine;", "get", "(Lokhttp3/Response;)Lokhttp3/internal/http/StatusLine;", "", "statusLine", "parse", "(Ljava/lang/String;)Lokhttp3/internal/http/StatusLine;", "", "HTTP_CONTINUE", "I", "HTTP_MISDIRECTED_REQUEST", "HTTP_PERM_REDIRECT", "HTTP_TEMP_REDIRECT", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final StatusLine get(@NotNull Response response) {
      Intrinsics.checkNotNullParameter(response, "response");
      return new StatusLine(response.protocol(), response.code(), response.message());
    }
    
    @NotNull
    public final StatusLine parse(@NotNull String statusLine) throws IOException {
      // Byte code:
      //   0: aload_1
      //   1: ldc 'statusLine'
      //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   6: iconst_0
      //   7: istore_2
      //   8: aconst_null
      //   9: astore_3
      //   10: aload_1
      //   11: ldc 'HTTP/1.'
      //   13: iconst_0
      //   14: iconst_2
      //   15: aconst_null
      //   16: invokestatic startsWith$default : (Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z
      //   19: ifeq -> 151
      //   22: aload_1
      //   23: invokevirtual length : ()I
      //   26: bipush #9
      //   28: if_icmplt -> 42
      //   31: aload_1
      //   32: bipush #8
      //   34: invokevirtual charAt : (I)C
      //   37: bipush #32
      //   39: if_icmpeq -> 69
      //   42: new java/net/ProtocolException
      //   45: dup
      //   46: new java/lang/StringBuilder
      //   49: dup
      //   50: invokespecial <init> : ()V
      //   53: ldc 'Unexpected status line: '
      //   55: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   58: aload_1
      //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   62: invokevirtual toString : ()Ljava/lang/String;
      //   65: invokespecial <init> : (Ljava/lang/String;)V
      //   68: athrow
      //   69: aload_1
      //   70: bipush #7
      //   72: invokevirtual charAt : (I)C
      //   75: bipush #48
      //   77: isub
      //   78: istore #4
      //   80: bipush #9
      //   82: istore_2
      //   83: iload #4
      //   85: tableswitch default -> 120, 0 -> 108, 1 -> 114
      //   108: getstatic okhttp3/Protocol.HTTP_1_0 : Lokhttp3/Protocol;
      //   111: goto -> 147
      //   114: getstatic okhttp3/Protocol.HTTP_1_1 : Lokhttp3/Protocol;
      //   117: goto -> 147
      //   120: new java/net/ProtocolException
      //   123: dup
      //   124: new java/lang/StringBuilder
      //   127: dup
      //   128: invokespecial <init> : ()V
      //   131: ldc 'Unexpected status line: '
      //   133: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   136: aload_1
      //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   140: invokevirtual toString : ()Ljava/lang/String;
      //   143: invokespecial <init> : (Ljava/lang/String;)V
      //   146: athrow
      //   147: astore_3
      //   148: goto -> 199
      //   151: aload_1
      //   152: ldc 'ICY '
      //   154: iconst_0
      //   155: iconst_2
      //   156: aconst_null
      //   157: invokestatic startsWith$default : (Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z
      //   160: ifeq -> 172
      //   163: getstatic okhttp3/Protocol.HTTP_1_0 : Lokhttp3/Protocol;
      //   166: astore_3
      //   167: iconst_4
      //   168: istore_2
      //   169: goto -> 199
      //   172: new java/net/ProtocolException
      //   175: dup
      //   176: new java/lang/StringBuilder
      //   179: dup
      //   180: invokespecial <init> : ()V
      //   183: ldc 'Unexpected status line: '
      //   185: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   188: aload_1
      //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   192: invokevirtual toString : ()Ljava/lang/String;
      //   195: invokespecial <init> : (Ljava/lang/String;)V
      //   198: athrow
      //   199: aload_1
      //   200: invokevirtual length : ()I
      //   203: iload_2
      //   204: iconst_3
      //   205: iadd
      //   206: if_icmpge -> 236
      //   209: new java/net/ProtocolException
      //   212: dup
      //   213: new java/lang/StringBuilder
      //   216: dup
      //   217: invokespecial <init> : ()V
      //   220: ldc 'Unexpected status line: '
      //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   225: aload_1
      //   226: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   229: invokevirtual toString : ()Ljava/lang/String;
      //   232: invokespecial <init> : (Ljava/lang/String;)V
      //   235: athrow
      //   236: nop
      //   237: aload_1
      //   238: iload_2
      //   239: iload_2
      //   240: iconst_3
      //   241: iadd
      //   242: invokevirtual substring : (II)Ljava/lang/String;
      //   245: dup
      //   246: ldc 'this as java.lang.String…ing(startIndex, endIndex)'
      //   248: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
      //   251: invokestatic parseInt : (Ljava/lang/String;)I
      //   254: istore #5
      //   256: goto -> 288
      //   259: astore #6
      //   261: new java/net/ProtocolException
      //   264: dup
      //   265: new java/lang/StringBuilder
      //   268: dup
      //   269: invokespecial <init> : ()V
      //   272: ldc 'Unexpected status line: '
      //   274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   277: aload_1
      //   278: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   281: invokevirtual toString : ()Ljava/lang/String;
      //   284: invokespecial <init> : (Ljava/lang/String;)V
      //   287: athrow
      //   288: iload #5
      //   290: istore #4
      //   292: ldc ''
      //   294: astore #5
      //   296: aload_1
      //   297: invokevirtual length : ()I
      //   300: iload_2
      //   301: iconst_3
      //   302: iadd
      //   303: if_icmple -> 360
      //   306: aload_1
      //   307: iload_2
      //   308: iconst_3
      //   309: iadd
      //   310: invokevirtual charAt : (I)C
      //   313: bipush #32
      //   315: if_icmpeq -> 345
      //   318: new java/net/ProtocolException
      //   321: dup
      //   322: new java/lang/StringBuilder
      //   325: dup
      //   326: invokespecial <init> : ()V
      //   329: ldc 'Unexpected status line: '
      //   331: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   334: aload_1
      //   335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   338: invokevirtual toString : ()Ljava/lang/String;
      //   341: invokespecial <init> : (Ljava/lang/String;)V
      //   344: athrow
      //   345: aload_1
      //   346: iload_2
      //   347: iconst_4
      //   348: iadd
      //   349: invokevirtual substring : (I)Ljava/lang/String;
      //   352: dup
      //   353: ldc 'this as java.lang.String).substring(startIndex)'
      //   355: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
      //   358: astore #5
      //   360: new okhttp3/internal/http/StatusLine
      //   363: dup
      //   364: aload_3
      //   365: iload #4
      //   367: aload #5
      //   369: invokespecial <init> : (Lokhttp3/Protocol;ILjava/lang/String;)V
      //   372: areturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #62	-> 10
      //   #63	-> 22
      //   #64	-> 42
      //   #66	-> 69
      //   #67	-> 80
      //   #68	-> 83
      //   #69	-> 108
      //   #71	-> 114
      //   #73	-> 120
      //   #68	-> 147
      //   #75	-> 151
      //   #77	-> 163
      //   #78	-> 167
      //   #80	-> 172
      //   #84	-> 199
      //   #85	-> 209
      //   #87	-> 236
      //   #88	-> 237
      //   #88	-> 251
      //   #89	-> 259
      //   #90	-> 261
      //   #87	-> 288
      //   #95	-> 292
      //   #96	-> 296
      //   #97	-> 306
      //   #98	-> 318
      //   #100	-> 345
      //   #100	-> 358
      //   #103	-> 360
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   80	68	4	httpMinorVersion	I
      //   261	27	6	_	Ljava/lang/NumberFormatException;
      //   8	365	2	codeStart	I
      //   10	363	3	protocol	Lokhttp3/Protocol;
      //   292	81	4	code	I
      //   296	77	5	message	Ljava/lang/String;
      //   0	373	0	this	Lokhttp3/internal/http/StatusLine$Companion;
      //   0	373	1	statusLine	Ljava/lang/String;
      // Exception table:
      //   from	to	target	type
      //   236	256	259	java/lang/NumberFormatException
    }
  }
}
