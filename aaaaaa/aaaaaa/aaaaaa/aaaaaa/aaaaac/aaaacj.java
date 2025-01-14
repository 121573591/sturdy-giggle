package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabl;
import java.security.MessageDigest;
import java.util.TimerTask;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.server.MinecraftServer;

@SourceDebugExtension({"SMAP\nSurvivalChallengeStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SurvivalChallengeStage.kt\ncn/pixellive/mc/game/stage/SurvivalChallengeStage$openBlindBox$1$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,564:1\n1855#2,2:565\n*S KotlinDebug\n*F\n+ 1 SurvivalChallengeStage.kt\ncn/pixellive/mc/game/stage/SurvivalChallengeStage$openBlindBox$1$1\n*L\n245#1:565,2\n*E\n"})
public final class aaaacj extends TimerTask {
  static Object aaaaaI;
  
  aaaacj(MinecraftServer paramMinecraftServer, aaaaaD paramaaaaaD, int paramInt1, aaaabl paramaaaabl, String paramString, int paramInt2) {}
  
  public void run() {
    // Byte code:
    //   0: nop
    //   1: aload_0
    //   2: getfield 我草你怎么反编译我模组aaaaaf : Lnet/minecraft/server/MinecraftServer;
    //   5: aload_0
    //   6: getfield 我草你怎么反编译我模组aaaaaG : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaD;
    //   9: aload_0
    //   10: getfield 我草你怎么反编译我模组aaaaah : I
    //   13: aload_0
    //   14: getfield 我草你怎么反编译我模组aaaaaH : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabl;
    //   17: aload_0
    //   18: getfield 我草你怎么反编译我模组aaaaaf : Lnet/minecraft/server/MinecraftServer;
    //   21: aload_0
    //   22: getfield 我草你怎么反编译我模组aaaaaj : Ljava/lang/String;
    //   25: aload_0
    //   26: getfield 我草你怎么反编译我模组aaaaaA : I
    //   29: aload_0
    //   30: <illegal opcode> run : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaD;ILaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabl;Lnet/minecraft/server/MinecraftServer;Ljava/lang/String;ILaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaacj;)Ljava/lang/Runnable;
    //   35: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   38: goto -> 68
    //   41: astore_1
    //   42: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   45: ldc -1966735360
    //   47: i2c
    //   48: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   51: checkcast java/lang/String
    //   54: aload_1
    //   55: checkcast java/lang/Throwable
    //   58: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   63: aload_0
    //   64: invokevirtual cancel : ()Z
    //   67: pop
    //   68: return
    // Exception table:
    //   from	to	target	type
    //   0	38	41	java/lang/Exception
  }
  
  private static final void 你为什么要破解我的代码aaaaao(aaaaaD paramaaaaaD, int paramInt1, aaaabl paramaaaabl, MinecraftServer paramMinecraftServer, String paramString, int paramInt2, aaaacj paramaaaacj) {
    // Byte code:
    //   0: aload_0
    //   1: ldc -751173631
    //   3: i2c
    //   4: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   7: checkcast java/lang/String
    //   10: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   13: aload_2
    //   14: ldc 1416036354
    //   16: i2c
    //   17: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   20: checkcast java/lang/String
    //   23: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   26: aload_3
    //   27: ldc -160628733
    //   29: i2c
    //   30: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   33: checkcast java/lang/String
    //   36: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   39: aload #4
    //   41: ldc -1891041276
    //   43: i2c
    //   44: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   47: checkcast java/lang/String
    //   50: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   53: aload #6
    //   55: ldc 1086324741
    //   57: i2c
    //   58: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   61: checkcast java/lang/String
    //   64: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   67: nop
    //   68: aload_0
    //   69: invokevirtual 你为什么要破解我的代码aaaaac : ()I
    //   72: iload_1
    //   73: if_icmpge -> 284
    //   76: aload_2
    //   77: invokevirtual 你为什么要破解我的代码aaaaao : ()Ljava/util/List;
    //   80: checkcast java/util/Collection
    //   83: invokestatic getIndices : (Ljava/util/Collection;)Lkotlin/ranges/IntRange;
    //   86: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   89: checkcast kotlin/random/Random
    //   92: invokestatic random : (Lkotlin/ranges/IntRange;Lkotlin/random/Random;)I
    //   95: istore #7
    //   97: aload_2
    //   98: invokevirtual 你为什么要破解我的代码aaaaav : ()Ljava/util/List;
    //   101: invokeinterface size : ()I
    //   106: iload #7
    //   108: if_icmple -> 128
    //   111: aload_2
    //   112: invokevirtual 你为什么要破解我的代码aaaaav : ()Ljava/util/List;
    //   115: iload #7
    //   117: invokeinterface get : (I)Ljava/lang/Object;
    //   122: checkcast java/lang/String
    //   125: goto -> 132
    //   128: aload_2
    //   129: invokevirtual 你为什么要破解我的代码aaaaas : ()Ljava/lang/String;
    //   132: astore #8
    //   134: aload_3
    //   135: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   138: invokevirtual method_14571 : ()Ljava/util/List;
    //   141: dup
    //   142: ldc -548536314
    //   144: i2c
    //   145: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   148: checkcast java/lang/String
    //   151: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   154: checkcast java/lang/Iterable
    //   157: astore #9
    //   159: iconst_0
    //   160: istore #10
    //   162: aload #9
    //   164: invokeinterface iterator : ()Ljava/util/Iterator;
    //   169: astore #11
    //   171: aload #11
    //   173: invokeinterface hasNext : ()Z
    //   178: ifeq -> 270
    //   181: aload #11
    //   183: invokeinterface next : ()Ljava/lang/Object;
    //   188: astore #12
    //   190: aload #12
    //   192: checkcast net/minecraft/class_3222
    //   195: astore #13
    //   197: iconst_0
    //   198: istore #14
    //   200: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   203: aload #13
    //   205: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   208: aload #13
    //   210: ldc -1372520441
    //   212: i2c
    //   213: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   216: checkcast java/lang/String
    //   219: iconst_1
    //   220: anewarray java/lang/Object
    //   223: astore #15
    //   225: aload #15
    //   227: iconst_0
    //   228: aload #8
    //   230: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   235: aastore
    //   236: aload #15
    //   238: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   241: checkcast net/minecraft/class_2561
    //   244: ldc -2112946168
    //   246: i2c
    //   247: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   250: checkcast java/lang/String
    //   253: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   256: checkcast net/minecraft/class_2561
    //   259: iconst_0
    //   260: iconst_5
    //   261: iconst_0
    //   262: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   265: nop
    //   266: nop
    //   267: goto -> 171
    //   270: nop
    //   271: aload_0
    //   272: aload_0
    //   273: invokevirtual 你为什么要破解我的代码aaaaac : ()I
    //   276: iconst_3
    //   277: iadd
    //   278: invokevirtual 你为什么要破解我的代码aaaaad : (I)V
    //   281: goto -> 524
    //   284: aload_0
    //   285: invokevirtual 你为什么要破解我的代码aaaaae : ()Lkotlin/Pair;
    //   288: ifnonnull -> 524
    //   291: aload_2
    //   292: invokevirtual 你为什么要破解我的代码aaaaao : ()Ljava/util/List;
    //   295: checkcast java/util/Collection
    //   298: invokestatic getIndices : (Ljava/util/Collection;)Lkotlin/ranges/IntRange;
    //   301: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   304: checkcast kotlin/random/Random
    //   307: invokestatic random : (Lkotlin/ranges/IntRange;Lkotlin/random/Random;)I
    //   310: istore #7
    //   312: aload_2
    //   313: invokevirtual 你为什么要破解我的代码aaaaao : ()Ljava/util/List;
    //   316: iload #7
    //   318: invokeinterface get : (I)Ljava/lang/Object;
    //   323: checkcast java/lang/String
    //   326: astore #8
    //   328: aload_2
    //   329: invokevirtual 你为什么要破解我的代码aaaaav : ()Ljava/util/List;
    //   332: invokeinterface size : ()I
    //   337: iload #7
    //   339: if_icmple -> 359
    //   342: aload_2
    //   343: invokevirtual 你为什么要破解我的代码aaaaav : ()Ljava/util/List;
    //   346: iload #7
    //   348: invokeinterface get : (I)Ljava/lang/Object;
    //   353: checkcast java/lang/String
    //   356: goto -> 363
    //   359: aload_2
    //   360: invokevirtual 你为什么要破解我的代码aaaaas : ()Ljava/lang/String;
    //   363: astore #9
    //   365: aload_0
    //   366: new kotlin/Pair
    //   369: dup
    //   370: aload #8
    //   372: aload #9
    //   374: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   377: invokevirtual 你为什么要破解我的代码aaaaaf : (Lkotlin/Pair;)V
    //   380: aload_3
    //   381: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   384: invokevirtual method_14571 : ()Ljava/util/List;
    //   387: dup
    //   388: ldc_w 1905590278
    //   391: i2c
    //   392: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   395: checkcast java/lang/String
    //   398: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   401: checkcast java/util/Collection
    //   404: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   407: checkcast kotlin/random/Random
    //   410: invokestatic random : (Ljava/util/Collection;Lkotlin/random/Random;)Ljava/lang/Object;
    //   413: checkcast net/minecraft/class_3222
    //   416: astore #10
    //   418: invokestatic getDefault : ()Lkotlinx/coroutines/CoroutineDispatcher;
    //   421: checkcast kotlin/coroutines/CoroutineContext
    //   424: invokestatic CoroutineScope : (Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;
    //   427: aconst_null
    //   428: aconst_null
    //   429: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabt
    //   432: dup
    //   433: aload #10
    //   435: aload #8
    //   437: aload #4
    //   439: aload_2
    //   440: aconst_null
    //   441: invokespecial <init> : (Lnet/minecraft/class_3222;Ljava/lang/String;Ljava/lang/String;Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabl;Lkotlin/coroutines/Continuation;)V
    //   444: checkcast kotlin/jvm/functions/Function2
    //   447: iconst_3
    //   448: aconst_null
    //   449: invokestatic launch$default : (Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;
    //   452: pop
    //   453: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaY.我草你怎么反编译我模组aaaacX : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaY;
    //   456: aload #9
    //   458: aload #4
    //   460: invokestatic 你为什么要破解我的代码aaaaik : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaY;Ljava/lang/String;Ljava/lang/String;)V
    //   463: aload_0
    //   464: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   467: istore #11
    //   469: aload_0
    //   470: iload #11
    //   472: iconst_1
    //   473: iadd
    //   474: invokevirtual 你为什么要破解我的代码aaaaab : (I)V
    //   477: aload_0
    //   478: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   481: iload #5
    //   483: if_icmpge -> 518
    //   486: aload_0
    //   487: iconst_0
    //   488: invokevirtual 你为什么要破解我的代码aaaaad : (I)V
    //   491: aload_0
    //   492: aconst_null
    //   493: invokevirtual 你为什么要破解我的代码aaaaaf : (Lkotlin/Pair;)V
    //   496: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   499: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   502: ldc_w 546766857
    //   505: i2c
    //   506: invokestatic aaaaap : (C)Ljava/lang/Object;
    //   509: checkcast java/lang/String
    //   512: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   515: goto -> 524
    //   518: aload #6
    //   520: invokevirtual cancel : ()Z
    //   523: pop
    //   524: return
  }
  
  static {
    byte[] arrayOfByte1 = "ÍC[\026ü¨ù¸WELà#)Î³5;&Éf²j\016ë\034h \031õF¯\026Põ\021¯á!\013Éw-¹Æ¹\\s\030Á\016¼Cìsô6»\002Kñs\f\037Nòo;pVIz(»7ó­¶8\033 }ø¡\020M_Ô\037\034ööèÿ\013slÇh®,ÒXRsË{qæ;R?Q*ó¢*\024uáYÜh®û¤hx~? Q\005µûsÚWÔG\007¯¯^hÅ·ê(\037]FRDÏD¸Áo±V}^³)zçöÇtÁ¶×Æ¢ïA(¼èZt¢ËjÉºIëÇñ\017\bìßÖ otÊ¬õ°An\"ÔSzô9\031".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "¬EP!\036l".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)132396083;
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
              Object[] arrayOfObject = new Object[10];
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
  
  private static Object aaaaap(char paramChar) {
    return ((Object[])aaaaaI)[paramChar];
  }
}
