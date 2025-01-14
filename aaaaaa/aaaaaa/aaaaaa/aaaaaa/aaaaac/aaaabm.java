package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1400;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nMonsterBattleStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage$CustomTargetGoal\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1605:1\n2333#2,14:1606\n*S KotlinDebug\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage$CustomTargetGoal\n*L\n1354#1:1606,14\n*E\n"})
public final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabm extends class_1400<class_1309> {
  @NotNull
  private final String 我草你怎么反编译我模组aaaaae;
  
  static Object aaaaaa;
  
  public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabm(@NotNull class_1314 paramclass_1314, @NotNull String paramString) {
    super((class_1308)paramclass_1314, class_1309.class, 10, true, false, null);
    this.我草你怎么反编译我模组aaaaae = paramString;
  }
  
  private final boolean 你为什么要破解我的代码aaaaad(class_1309 paramclass_1309) {
    if (!paramclass_1309.method_5805())
      return false; 
    this.field_6660.method_5797();
    String str1 = (this.field_6660.method_5797() != null) ? this.field_6660.method_5797().getString() : null;
    paramclass_1309.method_5797();
    String str2 = (paramclass_1309.method_5797() != null) ? paramclass_1309.method_5797().getString() : null;
    if (!Intrinsics.areEqual(str1, str2))
      if (((str2 != null) ? ((StringsKt.startsWith$default(str2, (String)aaaaaf((char)-1992359934), false, 2, null) == true)) : false) && (paramclass_1309 instanceof net.minecraft.class_1510 || !paramclass_1309.method_5864().equals(class_1299.field_6116))); 
    return false;
  }
  
  public boolean method_6264() {
    // Byte code:
    //   0: aload_0
    //   1: getfield field_6660 : Lnet/minecraft/class_1308;
    //   4: invokevirtual method_5968 : ()Lnet/minecraft/class_1309;
    //   7: ifnull -> 30
    //   10: aload_0
    //   11: aload_0
    //   12: getfield field_6660 : Lnet/minecraft/class_1308;
    //   15: invokevirtual method_5968 : ()Lnet/minecraft/class_1309;
    //   18: dup
    //   19: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   22: invokespecial 你为什么要破解我的代码aaaaad : (Lnet/minecraft/class_1309;)Z
    //   25: ifeq -> 30
    //   28: iconst_1
    //   29: ireturn
    //   30: ldc2_w 128.0
    //   33: dstore_1
    //   34: aload_0
    //   35: getfield field_6660 : Lnet/minecraft/class_1308;
    //   38: invokevirtual method_37908 : ()Lnet/minecraft/class_1937;
    //   41: aload_0
    //   42: getfield field_6660 : Lnet/minecraft/class_1308;
    //   45: checkcast net/minecraft/class_1297
    //   48: aload_0
    //   49: getfield field_6660 : Lnet/minecraft/class_1308;
    //   52: invokevirtual method_5829 : ()Lnet/minecraft/class_238;
    //   55: dload_1
    //   56: invokevirtual method_1014 : (D)Lnet/minecraft/class_238;
    //   59: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabZ
    //   62: dup
    //   63: aload_0
    //   64: invokespecial <init> : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabm;)V
    //   67: checkcast kotlin/jvm/functions/Function1
    //   70: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
    //   75: invokevirtual method_8333 : (Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;
    //   78: astore_3
    //   79: aload_3
    //   80: invokeinterface isEmpty : ()Z
    //   85: ifeq -> 90
    //   88: iconst_0
    //   89: ireturn
    //   90: aload_0
    //   91: aload_3
    //   92: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   95: aload_3
    //   96: checkcast java/lang/Iterable
    //   99: astore #4
    //   101: astore #5
    //   103: iconst_0
    //   104: istore #6
    //   106: aload #4
    //   108: invokeinterface iterator : ()Ljava/util/Iterator;
    //   113: astore #7
    //   115: aload #7
    //   117: invokeinterface hasNext : ()Z
    //   122: ifne -> 129
    //   125: aconst_null
    //   126: goto -> 240
    //   129: aload #7
    //   131: invokeinterface next : ()Ljava/lang/Object;
    //   136: astore #8
    //   138: aload #7
    //   140: invokeinterface hasNext : ()Z
    //   145: ifne -> 153
    //   148: aload #8
    //   150: goto -> 240
    //   153: aload #8
    //   155: checkcast net/minecraft/class_1297
    //   158: astore #9
    //   160: iconst_0
    //   161: istore #10
    //   163: aload #9
    //   165: aload_0
    //   166: getfield field_6660 : Lnet/minecraft/class_1308;
    //   169: checkcast net/minecraft/class_1297
    //   172: invokevirtual method_5858 : (Lnet/minecraft/class_1297;)D
    //   175: dstore #11
    //   177: aload #7
    //   179: invokeinterface next : ()Ljava/lang/Object;
    //   184: astore #10
    //   186: aload #10
    //   188: checkcast net/minecraft/class_1297
    //   191: astore #13
    //   193: iconst_0
    //   194: istore #14
    //   196: aload #13
    //   198: aload_0
    //   199: getfield field_6660 : Lnet/minecraft/class_1308;
    //   202: checkcast net/minecraft/class_1297
    //   205: invokevirtual method_5858 : (Lnet/minecraft/class_1297;)D
    //   208: dstore #15
    //   210: dload #11
    //   212: dload #15
    //   214: invokestatic compare : (DD)I
    //   217: ifle -> 228
    //   220: aload #10
    //   222: astore #8
    //   224: dload #15
    //   226: dstore #11
    //   228: aload #7
    //   230: invokeinterface hasNext : ()Z
    //   235: ifne -> 177
    //   238: aload #8
    //   240: aload #5
    //   242: swap
    //   243: astore #17
    //   245: aload #17
    //   247: instanceof net/minecraft/class_1309
    //   250: ifeq -> 261
    //   253: aload #17
    //   255: checkcast net/minecraft/class_1309
    //   258: goto -> 262
    //   261: aconst_null
    //   262: putfield field_6644 : Lnet/minecraft/class_1309;
    //   265: aload_0
    //   266: getfield field_6644 : Lnet/minecraft/class_1309;
    //   269: ifnull -> 276
    //   272: iconst_1
    //   273: goto -> 277
    //   276: iconst_0
    //   277: ireturn
  }
  
  public boolean method_6266() {
    class_1309 class_1309;
    return (this.field_6644 == null) ? false : ((class_1309.method_5805() && 你为什么要破解我的代码aaaaad(class_1309)));
  }
  
  public void method_6269() {
    this.field_6660.method_5980(this.field_6644);
    super.method_6269();
  }
  
  public void method_6270() {
    this.field_6660.method_5980(null);
    this.field_6644 = null;
    super.method_6270();
  }
  
  private static final boolean 你为什么要破解我的代码aaaaab(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaaaf((char)1377501187));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  static {
    byte[] arrayOfByte1 = "éïª\023Õo< \017í\002GI½@V¥¢í¤g\036Á°äÄ©BÁ_\003ò«û§\fÁDßVfâZ®*½úÕÒa\022ßM\031K\004\007|\022\004:".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "QxÒ©zé".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)1195970619;
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
              Object[] arrayOfObject = new Object[4];
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
  
  private static Object aaaaaf(char paramChar) {
    return ((Object[])aaaaaa)[paramChar];
  }
}
