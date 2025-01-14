package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaap;
import java.security.MessageDigest;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nPeakChallengeStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PeakChallengeStage.kt\ncn/pixellive/mc/game/stage/PeakChallengeStage$randomTeleportAllPlayers$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,2800:1\n1855#2,2:2801\n*S KotlinDebug\n*F\n+ 1 PeakChallengeStage.kt\ncn/pixellive/mc/game/stage/PeakChallengeStage$randomTeleportAllPlayers$1\n*L\n1594#1:2801,2\n*E\n"})
final class aaaaby extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
  int 我草你怎么反编译我模组aaaaae;
  
  int 我草你怎么反编译我模组aaaaau;
  
  Object 我草你怎么反编译我模组aaaaac;
  
  Object 我草你怎么反编译我模组aaaaad;
  
  Object 我草你怎么反编译我模组aaaaar;
  
  int 我草你怎么反编译我模组aaaaaf;
  
  static Object aaaach;
  
  aaaaby(List<class_3222> paramList, int paramInt, class_3218 paramclass_3218, Continuation<? super aaaaby> paramContinuation) {
    super(2, paramContinuation);
  }
  
  @Nullable
  public final Object invokeSuspend(@NotNull Object paramObject) {
    // Byte code:
    //   0: invokestatic getCOROUTINE_SUSPENDED : ()Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_0
    //   5: getfield 我草你怎么反编译我模组aaaaaf : I
    //   8: tableswitch default -> 382, 0 -> 36, 1 -> 164, 2 -> 294
    //   36: aload_1
    //   37: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   40: aload_0
    //   41: getfield 我草你怎么反编译我模组aaaacf : Ljava/util/List;
    //   44: checkcast java/lang/Iterable
    //   47: astore_3
    //   48: aload_0
    //   49: getfield 我草你怎么反编译我模组aaaacg : I
    //   52: istore #4
    //   54: aload_0
    //   55: getfield 我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_3218;
    //   58: astore #5
    //   60: iconst_0
    //   61: istore #6
    //   63: aload_3
    //   64: invokeinterface iterator : ()Ljava/util/Iterator;
    //   69: astore #7
    //   71: aload #7
    //   73: invokeinterface hasNext : ()Z
    //   78: ifeq -> 358
    //   81: aload #7
    //   83: invokeinterface next : ()Ljava/lang/Object;
    //   88: astore #8
    //   90: aload #8
    //   92: checkcast net/minecraft/class_3222
    //   95: astore #9
    //   97: iconst_0
    //   98: istore #10
    //   100: invokestatic 你为什么要破解我的代码aaaada : ()Lnet/minecraft/server/MinecraftServer;
    //   103: dup
    //   104: ifnull -> 120
    //   107: aload #9
    //   109: <illegal opcode> run : (Lnet/minecraft/class_3222;)Ljava/lang/Runnable;
    //   114: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   117: goto -> 121
    //   120: pop
    //   121: ldc2_w 3000
    //   124: aload_0
    //   125: aload_0
    //   126: aload #5
    //   128: putfield 我草你怎么反编译我模组aaaaac : Ljava/lang/Object;
    //   131: aload_0
    //   132: aload #7
    //   134: putfield 我草你怎么反编译我模组aaaaad : Ljava/lang/Object;
    //   137: aload_0
    //   138: aload #9
    //   140: putfield 我草你怎么反编译我模组aaaaar : Ljava/lang/Object;
    //   143: aload_0
    //   144: iload #4
    //   146: putfield 我草你怎么反编译我模组aaaaae : I
    //   149: aload_0
    //   150: iconst_1
    //   151: putfield 我草你怎么反编译我模组aaaaaf : I
    //   154: invokestatic delay : (JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   157: dup
    //   158: aload_2
    //   159: if_acmpne -> 208
    //   162: aload_2
    //   163: areturn
    //   164: iconst_0
    //   165: istore #6
    //   167: iconst_0
    //   168: istore #10
    //   170: aload_0
    //   171: getfield 我草你怎么反编译我模组aaaaae : I
    //   174: istore #4
    //   176: aload_0
    //   177: getfield 我草你怎么反编译我模组aaaaar : Ljava/lang/Object;
    //   180: checkcast net/minecraft/class_3222
    //   183: astore #9
    //   185: aload_0
    //   186: getfield 我草你怎么反编译我模组aaaaad : Ljava/lang/Object;
    //   189: checkcast java/util/Iterator
    //   192: astore #7
    //   194: aload_0
    //   195: getfield 我草你怎么反编译我模组aaaaac : Ljava/lang/Object;
    //   198: checkcast net/minecraft/class_3218
    //   201: astore #5
    //   203: aload_1
    //   204: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   207: aload_1
    //   208: pop
    //   209: iconst_0
    //   210: istore #11
    //   212: iload #11
    //   214: iload #4
    //   216: if_icmpge -> 354
    //   219: iconst_0
    //   220: istore #12
    //   222: invokestatic 你为什么要破解我的代码aaaada : ()Lnet/minecraft/server/MinecraftServer;
    //   225: dup
    //   226: ifnull -> 244
    //   229: aload #5
    //   231: aload #9
    //   233: <illegal opcode> run : (Lnet/minecraft/class_3218;Lnet/minecraft/class_3222;)Ljava/lang/Runnable;
    //   238: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   241: goto -> 245
    //   244: pop
    //   245: ldc2_w 1000
    //   248: aload_0
    //   249: aload_0
    //   250: aload #5
    //   252: putfield 我草你怎么反编译我模组aaaaac : Ljava/lang/Object;
    //   255: aload_0
    //   256: aload #7
    //   258: putfield 我草你怎么反编译我模组aaaaad : Ljava/lang/Object;
    //   261: aload_0
    //   262: aload #9
    //   264: putfield 我草你怎么反编译我模组aaaaar : Ljava/lang/Object;
    //   267: aload_0
    //   268: iload #4
    //   270: putfield 我草你怎么反编译我模组aaaaae : I
    //   273: aload_0
    //   274: iload #11
    //   276: putfield 我草你怎么反编译我模组aaaaau : I
    //   279: aload_0
    //   280: iconst_2
    //   281: putfield 我草你怎么反编译我模组aaaaaf : I
    //   284: invokestatic delay : (JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    //   287: dup
    //   288: aload_2
    //   289: if_acmpne -> 347
    //   292: aload_2
    //   293: areturn
    //   294: iconst_0
    //   295: istore #6
    //   297: iconst_0
    //   298: istore #10
    //   300: iconst_0
    //   301: istore #12
    //   303: aload_0
    //   304: getfield 我草你怎么反编译我模组aaaaau : I
    //   307: istore #11
    //   309: aload_0
    //   310: getfield 我草你怎么反编译我模组aaaaae : I
    //   313: istore #4
    //   315: aload_0
    //   316: getfield 我草你怎么反编译我模组aaaaar : Ljava/lang/Object;
    //   319: checkcast net/minecraft/class_3222
    //   322: astore #9
    //   324: aload_0
    //   325: getfield 我草你怎么反编译我模组aaaaad : Ljava/lang/Object;
    //   328: checkcast java/util/Iterator
    //   331: astore #7
    //   333: aload_0
    //   334: getfield 我草你怎么反编译我模组aaaaac : Ljava/lang/Object;
    //   337: checkcast net/minecraft/class_3218
    //   340: astore #5
    //   342: aload_1
    //   343: invokestatic throwOnFailure : (Ljava/lang/Object;)V
    //   346: aload_1
    //   347: pop
    //   348: iinc #11, 1
    //   351: goto -> 212
    //   354: nop
    //   355: goto -> 71
    //   358: nop
    //   359: invokestatic 你为什么要破解我的代码aaaada : ()Lnet/minecraft/server/MinecraftServer;
    //   362: dup
    //   363: ifnull -> 377
    //   366: <illegal opcode> run : ()Ljava/lang/Runnable;
    //   371: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   374: goto -> 378
    //   377: pop
    //   378: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   381: areturn
    //   382: new java/lang/IllegalStateException
    //   385: dup
    //   386: ldc 1343684608
    //   388: i2c
    //   389: invokestatic aaaaaK : (C)Ljava/lang/Object;
    //   392: checkcast java/lang/String
    //   395: invokespecial <init> : (Ljava/lang/String;)V
    //   398: athrow
  }
  
  @NotNull
  public final Continuation<Unit> create(@Nullable Object paramObject, @NotNull Continuation<?> paramContinuation) {
    return (Continuation<Unit>)new aaaaby(this.我草你怎么反编译我模组aaaacf, this.我草你怎么反编译我模组aaaacg, this.我草你怎么反编译我模组aaaaaz, (Continuation)paramContinuation);
  }
  
  @Nullable
  public final Object 你为什么要破解我的代码aaaaab(@NotNull CoroutineScope paramCoroutineScope, @Nullable Continuation<? super Unit> paramContinuation) {
    return ((aaaaby)create(paramCoroutineScope, paramContinuation)).invokeSuspend(Unit.INSTANCE);
  }
  
  private static final void 你为什么要破解我的代码aaaaaH(class_3222 paramclass_3222) {
    paramclass_3222.method_6092(new class_1293(class_1294.field_5902, 60, 1));
  }
  
  private static final void 你为什么要破解我的代码aaaaaI(class_3218 paramclass_3218, class_3222 paramclass_3222) {
    int i = RangesKt.random(new IntRange(1, 12), (Random)Random.Default);
    int j = RangesKt.random(new IntRange(1, (int)aaaaae.你为什么要破解我的代码aaaadb()), (Random)Random.Default);
    class_2338 class_2338 = aaaaae.我草你怎么反编译我模组aaaaaE.你为什么要破解我的代码aaaacb(i, j);
    paramclass_3218.method_8421((class_1297)paramclass_3222, (byte)35);
    Intrinsics.checkNotNull(paramclass_3222);
    aaaaab.你为什么要破解我的代码aaaaaa(paramclass_3222, class_2338.method_10263() + 0.5D, class_2338.method_10264(), class_2338.method_10260() + 0.5D);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaaaK((char)-303497215));
  }
  
  private static final void 你为什么要破解我的代码aaaaaJ() {
    aaaaae.你为什么要破解我的代码aaaadc(aaaaae.我草你怎么反编译我模组aaaaaE);
  }
  
  static {
    byte[] arrayOfByte1 = "Îá\\\020!¸¹U\0013È3dÉl\020ç!\005/\006\016¿,ÑýäÐÓ¶\013¶6zcìh@ ©Du\032\016Õ\036=\034dGthù\032ff\005¥\005å\033\037\r\013!ú}Èº´5hdï²#®NÛRær\fË\002\013G\002GXD>î\016Ó\0327Í¢j".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "\021Øÿ<;#$¯".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)952705601;
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
              Object[] arrayOfObject = new Object[2];
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
  
  private static Object aaaaaK(char paramChar) {
    return ((Object[])aaaach)[paramChar];
  }
}
