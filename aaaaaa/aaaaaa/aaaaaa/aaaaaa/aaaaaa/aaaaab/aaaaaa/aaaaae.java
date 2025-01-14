package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_243;
import net.minecraft.class_2540;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import org.jetbrains.annotations.NotNull;

public final class aaaaae implements class_8710 {
  @NotNull
  public static final aaaaae 我草你怎么反编译我模组aaaaag;
  
  @NotNull
  private static final class_8710.class_9154<aaaaae> 我草你怎么反编译我模组aaaaad;
  
  @NotNull
  private static final class_9139<class_2540, aaaaae> 我草你怎么反编译我模组aaaaae;
  
  static Object aaaaah;
  
  @NotNull
  public class_8710.class_9154<? extends class_8710> method_56479() {
    return (class_8710.class_9154)我草你怎么反编译我模组aaaaad;
  }
  
  public final void 你为什么要破解我的代码aaaaac() {
    PayloadTypeRegistry.playC2S().register(我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaae);
    ServerPlayNetworking.registerGlobalReceiver(我草你怎么反编译我模组aaaaad, aaaaae::你为什么要破解我的代码aaaaaq);
  }
  
  private static final void 你为什么要破解我的代码aaaaap(class_3222 paramclass_3222, class_243 paramclass_243) {
    Intrinsics.checkNotNullParameter(paramclass_243, (String)aaaaar((char)1308819456));
    Intrinsics.checkNotNull(paramclass_3222);
    aaaaab.你为什么要破解我的代码aaaaaa(paramclass_3222, paramclass_243.field_1352, paramclass_243.field_1351, paramclass_243.field_1350);
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaar((char)-213450751), paramclass_3222.method_5477().getString());
  }
  
  private static final void 你为什么要破解我的代码aaaaaq(aaaaae paramaaaaae, ServerPlayNetworking.Context paramContext) {
    // Byte code:
    //   0: getstatic cn/pixellive/mc/game/PixelLiveGameMod.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaf : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabG;
    //   6: dup
    //   7: ifnonnull -> 12
    //   10: pop
    //   11: return
    //   12: astore_2
    //   13: aload_1
    //   14: invokeinterface player : ()Lnet/minecraft/class_3222;
    //   19: astore_3
    //   20: aload_2
    //   21: invokevirtual 你为什么要破解我的代码aaaaak : ()Lnet/minecraft/class_243;
    //   24: astore #4
    //   26: aload_2
    //   27: invokevirtual 你为什么要破解我的代码aaaaae : ()Ljava/lang/String;
    //   30: astore #5
    //   32: aload #5
    //   34: ldc -1674182654
    //   36: i2c
    //   37: invokestatic aaaaar : (C)Ljava/lang/Object;
    //   40: checkcast java/lang/String
    //   43: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   46: ifeq -> 58
    //   49: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaN.我草你怎么反编译我模组aaaack : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaN;
    //   52: invokevirtual 你为什么要破解我的代码aaaafR : ()V
    //   55: goto -> 82
    //   58: aload #5
    //   60: ldc -511180797
    //   62: i2c
    //   63: invokestatic aaaaar : (C)Ljava/lang/Object;
    //   66: checkcast java/lang/String
    //   69: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   72: ifeq -> 82
    //   75: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabW.我草你怎么反编译我模组aaaacY : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabW;
    //   78: invokevirtual 你为什么要破解我的代码aaaaiR : ()I
    //   81: pop
    //   82: aload_3
    //   83: getfield field_13995 : Lnet/minecraft/server/MinecraftServer;
    //   86: aload_3
    //   87: aload #4
    //   89: <illegal opcode> run : (Lnet/minecraft/class_3222;Lnet/minecraft/class_243;)Ljava/lang/Runnable;
    //   94: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   97: return
  }
  
  static {
    byte[] arrayOfByte1 = "Æç\nG\f¨\022\026ÄæÃ:c2VG(RTh¡â·És_ã±b<§\t\030\025E`l;w?ú,)B[Ö8`M2jÎêÔðA\bÏ|³3\f5ÅK¬kA\\\031&Ï¦»\002æv g%Å¤ç\023\035L\030\bM\f\032¾IÌto[\016ì:U]e£\017ª\r±£ÿûÌ»ÀÒ³ý#d::Óc½÷g".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "?\006O>Ý.".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)1287511178;
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
              Object[] arrayOfObject = new Object[7];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaag = new aaaaae();
            Intrinsics.checkNotNullExpressionValue(class_8710.method_56483((String)aaaaar((char)390922244)), (String)aaaaar((char)-520224763));
            我草你怎么反编译我模组aaaaad = class_8710.method_56483((String)aaaaar((char)390922244));
            Intrinsics.checkNotNullExpressionValue(class_9139.method_56431(我草你怎么反编译我模组aaaaag), (String)aaaaar((char)629604358));
            我草你怎么反编译我模组aaaaae = class_9139.method_56431(我草你怎么反编译我模组aaaaag);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaar(char paramChar) {
    return ((Object[])aaaaah)[paramChar];
  }
}
