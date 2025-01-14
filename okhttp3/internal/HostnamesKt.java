package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000&\n\002\020\016\n\000\n\002\020\b\n\002\b\002\n\002\020\022\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\t\0327\020\t\032\0020\b2\006\020\001\032\0020\0002\006\020\003\032\0020\0022\006\020\004\032\0020\0022\006\020\006\032\0020\0052\006\020\007\032\0020\002H\002¢\006\004\b\t\020\n\032)\020\f\032\004\030\0010\0132\006\020\001\032\0020\0002\006\020\003\032\0020\0022\006\020\004\032\0020\002H\002¢\006\004\b\f\020\r\032\027\020\016\032\0020\0002\006\020\006\032\0020\005H\002¢\006\004\b\016\020\017\032\023\020\020\032\0020\b*\0020\000H\002¢\006\004\b\020\020\021\032\023\020\022\032\004\030\0010\000*\0020\000¢\006\004\b\022\020\023¨\006\024"}, d2 = {"", "input", "", "pos", "limit", "", "address", "addressOffset", "", "decodeIpv4Suffix", "(Ljava/lang/String;II[BI)Z", "Ljava/net/InetAddress;", "decodeIpv6", "(Ljava/lang/String;II)Ljava/net/InetAddress;", "inet6AddressToAscii", "([B)Ljava/lang/String;", "containsInvalidHostnameAsciiCodes", "(Ljava/lang/String;)Z", "toCanonicalHost", "(Ljava/lang/String;)Ljava/lang/String;", "okhttp"})
public final class HostnamesKt {
  @Nullable
  public static final String toCanonicalHost(@NotNull String $this$toCanonicalHost) {
    Intrinsics.checkNotNullParameter($this$toCanonicalHost, "<this>");
    String host = $this$toCanonicalHost;
    if (StringsKt.contains$default(host, ":", false, 2, null)) {
      Object object;
      if (((StringsKt.startsWith$default(host, "[", false, 2, null) && StringsKt.endsWith$default(host, "]", false, 2, null)) ? 
        decodeIpv6(host, 1, host.length() - 1) : 
        
        decodeIpv6(host, 0, host.length())) == null) {
        (StringsKt.startsWith$default(host, "[", false, 2, null) && StringsKt.endsWith$default(host, "]", false, 2, null)) ? decodeIpv6(host, 1, host.length() - 1) : decodeIpv6(host, 0, host.length());
        return null;
      } 
      byte[] address = object.getAddress();
      if (address.length == 16) {
        Intrinsics.checkNotNullExpressionValue(address, "address");
        return inet6AddressToAscii(address);
      } 
      if (address.length == 4)
        return object.getHostAddress(); 
      throw new AssertionError("Invalid IPv6 address: '" + host + '\'');
    } 
    try {
      Intrinsics.checkNotNullExpressionValue(IDN.toASCII(host), "toASCII(host)");
      String str1 = IDN.toASCII(host);
      Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
      Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
      String result = str1.toLowerCase(Locale.US);
      if ((((CharSequence)result).length() == 0))
        return null; 
      return containsInvalidHostnameAsciiCodes(result) ? 
        null : 
        
        result;
    } catch (IllegalArgumentException _) {
      return null;
    } 
  }
  
  private static final boolean containsInvalidHostnameAsciiCodes(String $this$containsInvalidHostnameAsciiCodes) {
    for (int i = 0, j = $this$containsInvalidHostnameAsciiCodes.length(); i < j; i++) {
      char c = $this$containsInvalidHostnameAsciiCodes.charAt(i);
      if (Intrinsics.compare(c, 31) <= 0 || Intrinsics.compare(c, 127) >= 0)
        return true; 
      if (StringsKt.indexOf$default(" #%/:?@[\\]", c, 0, false, 6, null) != -1)
        return true; 
    } 
    return false;
  }
  
  private static final InetAddress decodeIpv6(String input, int pos, int limit) {
    // Byte code:
    //   0: bipush #16
    //   2: newarray byte
    //   4: astore_3
    //   5: iconst_0
    //   6: istore #4
    //   8: iconst_m1
    //   9: istore #5
    //   11: iconst_m1
    //   12: istore #6
    //   14: iload_1
    //   15: istore #7
    //   17: iload #7
    //   19: iload_2
    //   20: if_icmpge -> 246
    //   23: iload #4
    //   25: aload_3
    //   26: arraylength
    //   27: if_icmpne -> 32
    //   30: aconst_null
    //   31: areturn
    //   32: iload #7
    //   34: iconst_2
    //   35: iadd
    //   36: iload_2
    //   37: if_icmpgt -> 82
    //   40: aload_0
    //   41: ldc '::'
    //   43: iload #7
    //   45: iconst_0
    //   46: iconst_4
    //   47: aconst_null
    //   48: invokestatic startsWith$default : (Ljava/lang/String;Ljava/lang/String;IZILjava/lang/Object;)Z
    //   51: ifeq -> 82
    //   54: iload #5
    //   56: iconst_m1
    //   57: if_icmpeq -> 62
    //   60: aconst_null
    //   61: areturn
    //   62: iinc #7, 2
    //   65: iinc #4, 2
    //   68: nop
    //   69: iload #4
    //   71: istore #5
    //   73: iload #7
    //   75: iload_2
    //   76: if_icmpne -> 147
    //   79: goto -> 246
    //   82: iload #4
    //   84: ifeq -> 147
    //   87: aload_0
    //   88: ldc ':'
    //   90: iload #7
    //   92: iconst_0
    //   93: iconst_4
    //   94: aconst_null
    //   95: invokestatic startsWith$default : (Ljava/lang/String;Ljava/lang/String;IZILjava/lang/Object;)Z
    //   98: ifeq -> 107
    //   101: iinc #7, 1
    //   104: goto -> 147
    //   107: aload_0
    //   108: ldc '.'
    //   110: iload #7
    //   112: iconst_0
    //   113: iconst_4
    //   114: aconst_null
    //   115: invokestatic startsWith$default : (Ljava/lang/String;Ljava/lang/String;IZILjava/lang/Object;)Z
    //   118: ifeq -> 145
    //   121: aload_0
    //   122: iload #6
    //   124: iload_2
    //   125: aload_3
    //   126: iload #4
    //   128: iconst_2
    //   129: isub
    //   130: invokestatic decodeIpv4Suffix : (Ljava/lang/String;II[BI)Z
    //   133: ifne -> 138
    //   136: aconst_null
    //   137: areturn
    //   138: iinc #4, 2
    //   141: nop
    //   142: goto -> 246
    //   145: aconst_null
    //   146: areturn
    //   147: iconst_0
    //   148: istore #8
    //   150: iload #7
    //   152: istore #6
    //   154: iload #7
    //   156: iload_2
    //   157: if_icmpge -> 192
    //   160: aload_0
    //   161: iload #7
    //   163: invokevirtual charAt : (I)C
    //   166: invokestatic parseHexDigit : (C)I
    //   169: istore #9
    //   171: iload #9
    //   173: iconst_m1
    //   174: if_icmpeq -> 192
    //   177: iload #8
    //   179: iconst_4
    //   180: ishl
    //   181: iload #9
    //   183: iadd
    //   184: istore #8
    //   186: iinc #7, 1
    //   189: goto -> 154
    //   192: iload #7
    //   194: iload #6
    //   196: isub
    //   197: istore #9
    //   199: iload #9
    //   201: ifeq -> 210
    //   204: iload #9
    //   206: iconst_4
    //   207: if_icmple -> 212
    //   210: aconst_null
    //   211: areturn
    //   212: aload_3
    //   213: iload #4
    //   215: iinc #4, 1
    //   218: iload #8
    //   220: bipush #8
    //   222: iushr
    //   223: sipush #255
    //   226: iand
    //   227: i2b
    //   228: bastore
    //   229: aload_3
    //   230: iload #4
    //   232: iinc #4, 1
    //   235: iload #8
    //   237: sipush #255
    //   240: iand
    //   241: i2b
    //   242: bastore
    //   243: goto -> 17
    //   246: iload #4
    //   248: aload_3
    //   249: arraylength
    //   250: if_icmpeq -> 296
    //   253: iload #5
    //   255: iconst_m1
    //   256: if_icmpne -> 261
    //   259: aconst_null
    //   260: areturn
    //   261: aload_3
    //   262: iload #5
    //   264: aload_3
    //   265: aload_3
    //   266: arraylength
    //   267: iload #4
    //   269: iload #5
    //   271: isub
    //   272: isub
    //   273: iload #4
    //   275: iload #5
    //   277: isub
    //   278: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   281: aload_3
    //   282: iload #5
    //   284: iload #5
    //   286: aload_3
    //   287: arraylength
    //   288: iload #4
    //   290: isub
    //   291: iadd
    //   292: iconst_0
    //   293: invokestatic fill : ([BIIB)V
    //   296: aload_3
    //   297: invokestatic getByAddress : ([B)Ljava/net/InetAddress;
    //   300: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #85	-> 0
    //   #86	-> 5
    //   #87	-> 8
    //   #88	-> 11
    //   #90	-> 14
    //   #91	-> 17
    //   #92	-> 23
    //   #95	-> 32
    //   #97	-> 54
    //   #98	-> 65
    //   #99	-> 68
    //   #100	-> 69
    //   #101	-> 73
    //   #102	-> 82
    //   #104	-> 87
    //   #105	-> 101
    //   #106	-> 107
    //   #108	-> 121
    //   #109	-> 141
    //   #110	-> 142
    //   #112	-> 145
    //   #117	-> 147
    //   #118	-> 150
    //   #119	-> 154
    //   #120	-> 160
    //   #121	-> 171
    //   #122	-> 177
    //   #123	-> 186
    //   #125	-> 192
    //   #126	-> 199
    //   #129	-> 212
    //   #130	-> 229
    //   #142	-> 246
    //   #143	-> 253
    //   #144	-> 261
    //   #145	-> 281
    //   #148	-> 296
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   171	18	9	hexDigit	I
    //   150	93	8	value	I
    //   199	44	9	groupLength	I
    //   5	296	3	address	[B
    //   8	293	4	b	I
    //   11	290	5	compress	I
    //   14	287	6	groupOffset	I
    //   17	284	7	i	I
    //   0	301	0	input	Ljava/lang/String;
    //   0	301	1	pos	I
    //   0	301	2	limit	I
  }
  
  private static final boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
    int b = addressOffset;
    int i = pos;
    while (i < limit) {
      if (b == address.length)
        return false; 
      if (b != addressOffset) {
        if (input.charAt(i) != '.')
          return false; 
        i++;
      } 
      int value = 0;
      int groupOffset = i;
      while (i < limit) {
        char c = input.charAt(i);
        if (Intrinsics.compare(c, 48) < 0 || Intrinsics.compare(c, 57) > 0)
          break; 
        if (value == 0 && groupOffset != i)
          return false; 
        value = value * 10 + c - 48;
        if (value > 255)
          return false; 
        i++;
      } 
      int groupLength = i - groupOffset;
      if (groupLength == 0)
        return false; 
      address[b++] = (byte)value;
    } 
    return (b == addressOffset + 4);
  }
  
  private static final String inet6AddressToAscii(byte[] address) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: iconst_m1
    //   3: istore_1
    //   4: iconst_0
    //   5: istore_2
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore #4
    //   11: iload #4
    //   13: aload_0
    //   14: arraylength
    //   15: if_icmpge -> 83
    //   18: iload #4
    //   20: istore #5
    //   22: iload #4
    //   24: bipush #16
    //   26: if_icmpge -> 51
    //   29: aload_0
    //   30: iload #4
    //   32: baload
    //   33: ifne -> 51
    //   36: aload_0
    //   37: iload #4
    //   39: iconst_1
    //   40: iadd
    //   41: baload
    //   42: ifne -> 51
    //   45: iinc #4, 2
    //   48: goto -> 22
    //   51: iload #4
    //   53: iload #5
    //   55: isub
    //   56: istore #6
    //   58: iload #6
    //   60: iload_2
    //   61: if_icmple -> 76
    //   64: iload #6
    //   66: iconst_4
    //   67: if_icmplt -> 76
    //   70: iload #5
    //   72: istore_1
    //   73: iload #6
    //   75: istore_2
    //   76: iinc #4, 2
    //   79: nop
    //   80: goto -> 11
    //   83: nop
    //   84: nop
    //   85: new okio/Buffer
    //   88: dup
    //   89: invokespecial <init> : ()V
    //   92: astore_3
    //   93: iconst_0
    //   94: istore #4
    //   96: iload #4
    //   98: aload_0
    //   99: arraylength
    //   100: if_icmpge -> 194
    //   103: iload #4
    //   105: iload_1
    //   106: if_icmpne -> 139
    //   109: aload_3
    //   110: bipush #58
    //   112: invokevirtual writeByte : (I)Lokio/Buffer;
    //   115: pop
    //   116: iload #4
    //   118: iload_2
    //   119: iadd
    //   120: istore #4
    //   122: iload #4
    //   124: bipush #16
    //   126: if_icmpne -> 96
    //   129: aload_3
    //   130: bipush #58
    //   132: invokevirtual writeByte : (I)Lokio/Buffer;
    //   135: pop
    //   136: goto -> 96
    //   139: iload #4
    //   141: ifle -> 151
    //   144: aload_3
    //   145: bipush #58
    //   147: invokevirtual writeByte : (I)Lokio/Buffer;
    //   150: pop
    //   151: aload_0
    //   152: iload #4
    //   154: baload
    //   155: sipush #255
    //   158: invokestatic and : (BI)I
    //   161: bipush #8
    //   163: ishl
    //   164: aload_0
    //   165: iload #4
    //   167: iconst_1
    //   168: iadd
    //   169: baload
    //   170: sipush #255
    //   173: invokestatic and : (BI)I
    //   176: ior
    //   177: istore #5
    //   179: aload_3
    //   180: iload #5
    //   182: i2l
    //   183: invokevirtual writeHexadecimalUnsignedLong : (J)Lokio/Buffer;
    //   186: pop
    //   187: iinc #4, 2
    //   190: nop
    //   191: goto -> 96
    //   194: aload_3
    //   195: invokevirtual readUtf8 : ()Ljava/lang/String;
    //   198: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #198	-> 0
    //   #199	-> 4
    //   #200	-> 6
    //   #201	-> 8
    //   #202	-> 11
    //   #203	-> 18
    //   #204	-> 22
    //   #205	-> 48
    //   #207	-> 51
    //   #208	-> 58
    //   #209	-> 70
    //   #210	-> 73
    //   #212	-> 79
    //   #214	-> 83
    //   #200	-> 84
    //   #217	-> 85
    //   #218	-> 93
    //   #219	-> 96
    //   #220	-> 103
    //   #221	-> 109
    //   #222	-> 116
    //   #223	-> 122
    //   #225	-> 139
    //   #226	-> 151
    //   #227	-> 179
    //   #228	-> 190
    //   #231	-> 194
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   22	58	5	currentRunOffset	I
    //   58	22	6	currentRunLength	I
    //   8	76	3	$i$a$-run-HostnamesKt$inet6AddressToAscii$1	I
    //   11	73	4	i	I
    //   179	12	5	group	I
    //   2	197	1	longestRunOffset	I
    //   6	193	2	longestRunLength	I
    //   93	106	3	result	Lokio/Buffer;
    //   96	103	4	i	I
    //   0	199	0	address	[B
  }
}
