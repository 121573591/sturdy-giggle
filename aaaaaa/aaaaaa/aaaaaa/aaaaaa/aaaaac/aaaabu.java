package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class aaaabu extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
  int 我草你怎么反编译我模组aaaaaf;
  
  static Object aaaacf;
  
  aaaabu(long paramLong, User32 paramUser32, WinDef.HWND paramHWND, int paramInt, Continuation<? super aaaabu> paramContinuation) {
    super(2, paramContinuation);
  }
  
  @Nullable
  public final Object invokeSuspend(@NotNull Object paramObject) {
    // Byte code:
    //   0: invokestatic getCOROUTINE_SUSPENDED : ()Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_0
    //   5: getfield 我草你怎么反编译我模组aaaaaf : I
    //   8: tableswitch default -> 549, 0 -> 36, 1 -> 268, 2 -> 385
    //   36: aload_1
    //   37: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   40: aload_0
    //   41: getfield 我草你怎么反编译我模组aaaaac : Ljava/lang/Object;
    //   44: checkcast kotlinx/coroutines/CoroutineScope
    //   47: astore_3
    //   48: nop
    //   49: invokestatic 你为什么要破解我的代码aaaaeI : ()Ljava/util/HashSet;
    //   52: astore #4
    //   54: aload #4
    //   56: monitorenter
    //   57: nop
    //   58: iconst_0
    //   59: istore #5
    //   61: invokestatic 你为什么要破解我的代码aaaaeI : ()Ljava/util/HashSet;
    //   64: checkcast java/util/Collection
    //   67: invokeinterface isEmpty : ()Z
    //   72: ifne -> 79
    //   75: iconst_1
    //   76: goto -> 80
    //   79: iconst_0
    //   80: ifeq -> 121
    //   83: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   86: ldc 1534918656
    //   88: i2c
    //   89: invokestatic aaaaaa : (C)Ljava/lang/Object;
    //   92: checkcast java/lang/String
    //   95: invokeinterface info : (Ljava/lang/String;)V
    //   100: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   103: astore #6
    //   105: aload #4
    //   107: monitorexit
    //   108: aload #6
    //   110: astore #7
    //   112: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   115: invokestatic 你为什么要破解我的代码aaaaeQ : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;)V
    //   118: aload #7
    //   120: areturn
    //   121: invokestatic 你为什么要破解我的代码aaaaeI : ()Ljava/util/HashSet;
    //   124: aload_3
    //   125: invokeinterface getCoroutineContext : ()Lkotlin/coroutines/CoroutineContext;
    //   130: getstatic kotlinx/coroutines/Job.Key : Lkotlinx/coroutines/Job$Key;
    //   133: checkcast kotlin/coroutines/CoroutineContext$Key
    //   136: invokeinterface get : (Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;
    //   141: dup
    //   142: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   145: invokevirtual add : (Ljava/lang/Object;)Z
    //   148: istore #5
    //   150: aload #4
    //   152: monitorexit
    //   153: goto -> 164
    //   156: astore #5
    //   158: aload #4
    //   160: monitorexit
    //   161: aload #5
    //   163: athrow
    //   164: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   167: pop
    //   168: iconst_1
    //   169: invokestatic 你为什么要破解我的代码aaaaeF : (Z)V
    //   172: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   175: pop
    //   176: aload_0
    //   177: getfield 我草你怎么反编译我模组aaaacb : J
    //   180: invokestatic 你为什么要破解我的代码aaaaeJ : (J)V
    //   183: invokestatic 你为什么要破解我的代码aaaaeK : ()Z
    //   186: ifne -> 275
    //   189: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   192: ldc 591265793
    //   194: i2c
    //   195: invokestatic aaaaaa : (C)Ljava/lang/Object;
    //   198: checkcast java/lang/String
    //   201: invokeinterface info : (Ljava/lang/String;)V
    //   206: aload_0
    //   207: getfield 我草你怎么反编译我模组aaaacc : Lcom/sun/jna/platform/win32/User32;
    //   210: aload_0
    //   211: getfield 我草你怎么反编译我模组aaaacd : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   214: invokestatic 你为什么要破解我的代码aaaaeL : ()I
    //   217: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   220: dup
    //   221: lconst_0
    //   222: invokespecial <init> : (J)V
    //   225: new com/sun/jna/platform/win32/WinDef$LPARAM
    //   228: dup
    //   229: lconst_0
    //   230: invokespecial <init> : (J)V
    //   233: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   238: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   241: pop
    //   242: iconst_1
    //   243: invokestatic 你为什么要破解我的代码aaaaeM : (Z)V
    //   246: ldc2_w 100
    //   249: aload_0
    //   250: checkcast kotlin/coroutines/Continuation
    //   253: aload_0
    //   254: iconst_1
    //   255: putfield 我草你怎么反编译我模组aaaaaf : I
    //   258: invokestatic delay : (JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   261: dup
    //   262: aload_2
    //   263: if_acmpne -> 274
    //   266: aload_2
    //   267: areturn
    //   268: nop
    //   269: aload_1
    //   270: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   273: aload_1
    //   274: pop
    //   275: aload_0
    //   276: getfield 我草你怎么反编译我模组aaaace : I
    //   279: tableswitch default -> 312, 1 -> 300, 2 -> 306
    //   300: ldc2_w 12600
    //   303: goto -> 315
    //   306: ldc2_w 23500
    //   309: goto -> 315
    //   312: ldc2_w 51
    //   315: lstore #8
    //   317: aload_0
    //   318: getfield 我草你怎么反编译我模组aaaacc : Lcom/sun/jna/platform/win32/User32;
    //   321: aload_0
    //   322: getfield 我草你怎么反编译我模组aaaacd : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   325: invokestatic 你为什么要破解我的代码aaaaeN : ()I
    //   328: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   331: dup
    //   332: lconst_0
    //   333: invokespecial <init> : (J)V
    //   336: new com/sun/jna/platform/win32/WinDef$LPARAM
    //   339: dup
    //   340: lconst_0
    //   341: invokespecial <init> : (J)V
    //   344: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   349: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   352: lload #8
    //   354: <illegal opcode> makeConcatWithConstants : (J)Ljava/lang/String;
    //   359: invokeinterface info : (Ljava/lang/String;)V
    //   364: lload #8
    //   366: aload_0
    //   367: checkcast kotlin/coroutines/Continuation
    //   370: aload_0
    //   371: iconst_2
    //   372: putfield 我草你怎么反编译我模组aaaaaf : I
    //   375: invokestatic delay : (JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   378: dup
    //   379: aload_2
    //   380: if_acmpne -> 391
    //   383: aload_2
    //   384: areturn
    //   385: nop
    //   386: aload_1
    //   387: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   390: aload_1
    //   391: pop
    //   392: aload_0
    //   393: getfield 我草你怎么反编译我模组aaaacc : Lcom/sun/jna/platform/win32/User32;
    //   396: aload_0
    //   397: getfield 我草你怎么反编译我模组aaaacd : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   400: invokestatic 你为什么要破解我的代码aaaaeC : ()I
    //   403: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   406: dup
    //   407: lconst_0
    //   408: invokespecial <init> : (J)V
    //   411: invokestatic 你为什么要破解我的代码aaaaeD : ()Lcom/sun/jna/platform/win32/WinDef$LPARAM;
    //   414: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   419: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   422: ldc -1802764286
    //   424: i2c
    //   425: invokestatic aaaaaa : (C)Ljava/lang/Object;
    //   428: checkcast java/lang/String
    //   431: invokeinterface info : (Ljava/lang/String;)V
    //   436: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   439: pop
    //   440: invokestatic currentTimeMillis : ()J
    //   443: invokestatic 你为什么要破解我的代码aaaaeJ : (J)V
    //   446: invokestatic 你为什么要破解我的代码aaaaeO : ()Z
    //   449: ifne -> 487
    //   452: aload_0
    //   453: getfield 我草你怎么反编译我模组aaaacc : Lcom/sun/jna/platform/win32/User32;
    //   456: aload_0
    //   457: getfield 我草你怎么反编译我模组aaaacd : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   460: invokestatic 你为什么要破解我的代码aaaaeE : ()I
    //   463: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   466: dup
    //   467: lconst_0
    //   468: invokespecial <init> : (J)V
    //   471: invokestatic 你为什么要破解我的代码aaaaeD : ()Lcom/sun/jna/platform/win32/WinDef$LPARAM;
    //   474: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   479: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   482: pop
    //   483: iconst_0
    //   484: invokestatic 你为什么要破解我的代码aaaaeM : (Z)V
    //   487: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   490: invokestatic 你为什么要破解我的代码aaaaeQ : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;)V
    //   493: goto -> 545
    //   496: astore #10
    //   498: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   501: aload_0
    //   502: getfield 我草你怎么反编译我模组aaaacc : Lcom/sun/jna/platform/win32/User32;
    //   505: dup
    //   506: ldc -2069889021
    //   508: i2c
    //   509: invokestatic aaaaaa : (C)Ljava/lang/Object;
    //   512: checkcast java/lang/String
    //   515: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   518: aload_0
    //   519: getfield 我草你怎么反编译我模组aaaacd : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   522: invokestatic 你为什么要破解我的代码aaaaeP : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;Lcom/sun/jna/platform/win32/User32;Lcom/sun/jna/platform/win32/WinDef$HWND;)V
    //   525: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   528: invokestatic 你为什么要破解我的代码aaaaeQ : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;)V
    //   531: goto -> 545
    //   534: astore #10
    //   536: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   539: invokestatic 你为什么要破解我的代码aaaaeQ : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;)V
    //   542: aload #10
    //   544: athrow
    //   545: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   548: areturn
    //   549: new java/lang/IllegalStateException
    //   552: dup
    //   553: ldc -533856252
    //   555: i2c
    //   556: invokestatic aaaaaa : (C)Ljava/lang/Object;
    //   559: checkcast java/lang/String
    //   562: invokespecial <init> : (Ljava/lang/String;)V
    //   565: athrow
    // Exception table:
    //   from	to	target	type
    //   48	112	496	java/util/concurrent/CancellationException
    //   48	112	534	finally
    //   57	105	156	finally
    //   121	150	156	finally
    //   121	261	496	java/util/concurrent/CancellationException
    //   121	261	534	finally
    //   156	158	156	finally
    //   268	378	496	java/util/concurrent/CancellationException
    //   268	378	534	finally
    //   385	487	496	java/util/concurrent/CancellationException
    //   385	487	534	finally
    //   496	525	534	finally
    //   534	536	534	finally
  }
  
  @NotNull
  public final Continuation<Unit> create(@Nullable Object paramObject, @NotNull Continuation<?> paramContinuation) {
    aaaabu aaaabu1 = new aaaabu(this.我草你怎么反编译我模组aaaacb, this.我草你怎么反编译我模组aaaacc, this.我草你怎么反编译我模组aaaacd, this.我草你怎么反编译我模组aaaace, (Continuation)paramContinuation);
    aaaabu1.我草你怎么反编译我模组aaaaac = paramObject;
    return (Continuation<Unit>)aaaabu1;
  }
  
  @Nullable
  public final Object 你为什么要破解我的代码aaaaab(@NotNull CoroutineScope paramCoroutineScope, @Nullable Continuation<? super Unit> paramContinuation) {
    return ((aaaabu)create(paramCoroutineScope, paramContinuation)).invokeSuspend(Unit.INSTANCE);
  }
  
  static {
    byte[] arrayOfByte1 = "ú÷@ýÉ\013j¢y?\017bg¢V\"=pxµk$þáñ+\036Î\035iö\034Ê}C\031°W½jvÛÞ\0313\026à^Ý ÐÏfÇºç¥Ùï\b\031ÂKÜº¢ê®òÄ>%©\035\nf/gáè½\026\b\\\026OuRL¾Î©äarÏÜ0jÃe|\nL±ä00Ã¬\0024igíêxÔ\007<¥CÝ?*\020\037:ÆçÍØí%\020gÈé;_N¤\006*\022é,wTâFÇ½pê´­pß".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "RqqÂ«\005".getBytes("ISO_8859_1");
    int i = 0;
    int j = arrayOfByte1.length;
    while (true) {
      switch (arrayOfByte1[i] & 0xFF) {
        case 0:
        
        case 1:
        
        case 2:
        
        case 3:
        
        case 4:
        
        case 5:
        
        case 6:
        
        case 7:
        
        case 8:
        
        case 9:
        
        case 10:
        
        case 11:
        
        case 12:
        
        case 13:
        
        case 14:
        
        case 15:
        
        case 16:
        
        case 17:
        
        case 18:
        
        case 19:
        
        case 20:
        
        case 21:
        
        case 22:
        
        case 23:
        
        case 24:
        
        case 25:
        
        case 26:
        
        case 27:
        
        case 28:
        
        case 29:
        
        case 30:
        
        case 31:
        
        case 32:
        
        case 33:
        
        case 34:
        
        case 35:
        
        case 36:
        
        case 37:
        
        case 38:
        
        case 39:
        
        case 40:
        
        case 41:
        
        case 42:
        
        case 43:
        
        case 44:
        
        case 45:
        
        case 46:
        
        case 47:
        
        case 48:
        
        case 49:
        
        case 50:
        
        case 51:
        
        case 52:
        
        case 53:
        
        case 54:
        
        case 55:
        
        case 56:
        
        case 57:
        
        case 58:
        
        case 59:
        
        case 60:
        
        case 61:
        
        case 62:
        
        case 63:
        
        case 64:
        
        case 65:
        
        case 66:
        
        case 67:
        
        case 68:
        
        case 69:
        
        case 70:
        
        case 71:
        
        case 72:
        
        case 73:
        
        case 74:
        
        case 75:
        
        case 76:
        
        case 77:
        
        case 78:
        
        case 79:
        
        case 80:
        
        case 81:
        
        case 82:
        
        case 83:
        
        case 84:
        
        case 85:
        
        case 86:
        
        case 87:
        
        case 88:
        
        case 89:
        
        case 90:
        
        case 91:
        
        case 92:
        
        case 93:
        
        case 94:
        
        case 95:
        
        case 96:
        
        case 97:
        
        case 98:
        
        case 99:
        
        case 100:
        
        case 101:
        
        case 102:
        
        case 103:
        
        case 104:
        
        case 105:
        
        case 106:
        
        case 107:
        
        case 108:
        
        case 109:
        
        case 110:
        
        case 111:
        
        case 112:
        
        case 113:
        
        case 114:
        
        case 115:
        
        case 116:
        
        case 117:
        
        case 118:
        
        case 119:
        
        case 120:
        
        case 121:
        
        case 122:
        
        case 123:
        
        case 124:
        
        case 125:
        
        case 126:
        
        case 127:
        
        case 128:
        
        case 129:
        
        case 130:
        
        case 131:
        
        case 132:
        
        case 133:
        
        case 134:
        
        case 135:
        
        case 136:
        
        case 137:
        
        case 138:
        
        case 139:
        
        case 140:
        
        case 141:
        
        case 142:
        
        case 143:
        
        case 144:
        
        case 145:
        
        case 146:
        
        case 147:
        
        case 148:
        
        case 149:
        
        case 150:
        
        case 151:
        
        case 152:
        
        case 153:
        
        case 154:
        
        case 155:
        
        case 156:
        
        case 157:
        
        case 158:
        
        case 159:
        
        case 160:
        
        case 161:
        
        case 162:
        
        case 163:
        
        case 164:
        
        case 165:
        
        case 166:
        
        case 167:
        
        case 168:
        
        case 169:
        
        case 170:
        
        case 171:
        
        case 172:
        
        case 173:
        
        case 174:
        
        case 175:
        
        case 176:
        
        case 177:
        
        case 178:
        
        case 179:
        
        case 180:
        
        case 181:
        
        case 182:
        
        case 183:
        
        case 184:
        
        case 185:
        
        case 186:
        
        case 187:
        
        case 188:
        
        case 189:
        
        case 190:
        
        case 191:
        
        case 192:
        
        case 193:
        
        case 194:
        
        case 195:
        
        case 196:
        
        case 197:
        
        case 198:
        
        case 199:
        
        case 200:
        
        case 201:
        
        case 202:
        
        case 203:
        
        case 204:
        
        case 205:
        
        case 206:
        
        case 207:
        
        case 208:
        
        case 209:
        
        case 210:
        
        case 211:
        
        case 212:
        
        case 213:
        
        case 214:
        
        case 215:
        
        case 216:
        
        case 217:
        
        case 218:
        
        case 219:
        
        case 220:
        
        case 221:
        
        case 222:
        
        case 223:
        
        case 224:
        
        case 225:
        
        case 226:
        
        case 227:
        
        case 228:
        
        case 229:
        
        case 230:
        
        case 231:
        
        case 232:
        
        case 233:
        
        case 234:
        
        case 235:
        
        case 236:
        
        case 237:
        
        case 238:
        
        case 239:
        
        case 240:
        
        case 241:
        
        case 242:
        
        case 243:
        
        case 244:
        
        case 245:
        
        case 246:
        
        case 247:
        
        case 248:
        
        case 249:
        
        case 250:
        
        case 251:
        
        case 252:
        
        case 253:
        
        case 254:
        
        case 255:
        
        default:
          break;
      } 
      arrayOfByte1[i++] = (byte)-335608494;
      if (i == j) {
        Cipher.getInstance("DES/CBC/PKCS5Padding").init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(arrayOfByte2)), new IvParameterSpec(new byte[8]));
        j = (arrayOfByte1 = Cipher.getInstance("DES/CBC/PKCS5Padding").doFinal(arrayOfByte1)).length;
        MessageDigest.getInstance("SHA-256").reset();
        MessageDigest.getInstance("SHA-256").update(arrayOfByte1, 0, i = j - 32);
        arrayOfByte2 = MessageDigest.getInstance("SHA-256").digest();
        int k = 0;
        int m = 0;
        byte b = 0;
        while (true) {
          m |= arrayOfByte1[i + k] ^ arrayOfByte2[k];
          if (++k == 32) {
            if (m == 0) {
              Object[] arrayOfObject = new Object[5];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaaa(char paramChar) {
    return ((Object[])aaaacf)[paramChar];
  }
}
