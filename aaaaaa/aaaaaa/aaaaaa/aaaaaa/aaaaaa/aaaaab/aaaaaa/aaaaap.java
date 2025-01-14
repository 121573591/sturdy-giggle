package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import cn.pixellive.mc.game.PixelLiveGameMod;
import cn.pixellive.mc.game.event.live.LiveGiftEvent;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_2540;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class aaaaap implements class_8710 {
  @NotNull
  public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaam 我草你怎么反编译我模组aaaaaQ;
  
  @NotNull
  private final String 我草你怎么反编译我模组aaaaab;
  
  @NotNull
  private final String 我草你怎么反编译我模组aaaaaR;
  
  private final int 我草你怎么反编译我模组aaaaaS;
  
  @NotNull
  private final String 我草你怎么反编译我模组aaaaaT;
  
  @NotNull
  private final String 我草你怎么反编译我模组aaaaaU;
  
  @JvmField
  @NotNull
  public static final class_8710.class_9154<aaaaap> 我草你怎么反编译我模组aaaaad;
  
  @JvmField
  @NotNull
  public static final class_9139<class_2540, aaaaap> 我草你怎么反编译我模组aaaaae;
  
  static Object aaaaaV;
  
  public aaaaap(@NotNull String paramString1, @NotNull String paramString2, int paramInt, @NotNull String paramString3, @NotNull String paramString4) {
    this.我草你怎么反编译我模组aaaaab = paramString1;
    this.我草你怎么反编译我模组aaaaaR = paramString2;
    this.我草你怎么反编译我模组aaaaaS = paramInt;
    this.我草你怎么反编译我模组aaaaaT = paramString3;
    this.我草你怎么反编译我模组aaaaaU = paramString4;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaaa() {
    return this.我草你怎么反编译我模组aaaaab;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaacs() {
    return this.我草你怎么反编译我模组aaaaaR;
  }
  
  public final int 你为什么要破解我的代码aaaact() {
    return this.我草你怎么反编译我模组aaaaaS;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaacu() {
    return this.我草你怎么反编译我模组aaaaaT;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaacv() {
    return this.我草你怎么反编译我模组aaaaaU;
  }
  
  public final void 你为什么要破解我的代码aaaaac() {
    PayloadTypeRegistry.playC2S().register(我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaae);
    ServerPlayNetworking.registerGlobalReceiver(我草你怎么反编译我模组aaaaad, aaaaap::你为什么要破解我的代码aaaacA);
  }
  
  @NotNull
  public class_8710.class_9154<aaaaap> method_56479() {
    return 我草你怎么反编译我模组aaaaad;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaad() {
    return this.我草你怎么反编译我模组aaaaab;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaae() {
    return this.我草你怎么反编译我模组aaaaaR;
  }
  
  public final int 你为什么要破解我的代码aaaaaE() {
    return this.我草你怎么反编译我模组aaaaaS;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaacw() {
    return this.我草你怎么反编译我模组aaaaaT;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaacx() {
    return this.我草你怎么反编译我模组aaaaaU;
  }
  
  @NotNull
  public final aaaaap 你为什么要破解我的代码aaaacy(@NotNull String paramString1, @NotNull String paramString2, int paramInt, @NotNull String paramString3, @NotNull String paramString4) {
    Intrinsics.checkNotNullParameter(paramString1, (String)aaaacD((char)1918304256));
    Intrinsics.checkNotNullParameter(paramString2, (String)aaaacD((char)-2125463551));
    Intrinsics.checkNotNullParameter(paramString3, (String)aaaacD((char)-1435828222));
    Intrinsics.checkNotNullParameter(paramString4, (String)aaaacD((char)1689976835));
    return new aaaaap(paramString1, paramString2, paramInt, paramString3, paramString4);
  }
  
  @NotNull
  public String toString() {
    return "LiveGiftEventPayload(username=" + this.我草你怎么反编译我模组aaaaab + ", giftName=" + this.我草你怎么反编译我模组aaaaaR + ", giftAmount=" + this.我草你怎么反编译我模组aaaaaS + ", liveType=" + this.我草你怎么反编译我模组aaaaaT + ", avatar=" + this.我草你怎么反编译我模组aaaaaU + ")";
  }
  
  public int hashCode() {
    null = this.我草你怎么反编译我模组aaaaab.hashCode();
    null = null * 31 + this.我草你怎么反编译我模组aaaaaR.hashCode();
    null = null * 31 + Integer.hashCode(this.我草你怎么反编译我模组aaaaaS);
    null = null * 31 + this.我草你怎么反编译我模组aaaaaT.hashCode();
    return null * 31 + this.我草你怎么反编译我模组aaaaaU.hashCode();
  }
  
  public boolean equals(@Nullable Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof aaaaap))
      return false; 
    aaaaap aaaaap1 = (aaaaap)paramObject;
    return !Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaab, aaaaap1.我草你怎么反编译我模组aaaaab) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaR, aaaaap1.我草你怎么反编译我模组aaaaaR) ? false : ((this.我草你怎么反编译我模组aaaaaS != aaaaap1.我草你怎么反编译我模组aaaaaS) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaT, aaaaap1.我草你怎么反编译我模组aaaaaT) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaU, aaaaap1.我草你怎么反编译我模组aaaaaU)))));
  }
  
  private static final void 你为什么要破解我的代码aaaacA(aaaaap paramaaaaap, ServerPlayNetworking.Context paramContext) {
    String str1 = paramaaaaap.你为什么要破解我的代码aaaaad();
    String str2 = paramaaaaap.你为什么要破解我的代码aaaaae();
    int i = paramaaaaap.你为什么要破解我的代码aaaaaE();
    String str3 = paramaaaaap.你为什么要破解我的代码aaaacw();
    String str4 = paramaaaaap.你为什么要破解我的代码aaaacx();
    aaaaaa.你为什么要破解我的代码aaaaaa().info(str1 + " sent a gift: " + str1 + " x " + str2);
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().post(new LiveGiftEvent(str1, str2, i, str3, str4));
  }
  
  private static final void 你为什么要破解我的代码aaaacB(aaaaap paramaaaaap, class_2540 paramclass_2540) {
    paramclass_2540.method_10814(paramaaaaap.我草你怎么反编译我模组aaaaab);
    paramclass_2540.method_10814(paramaaaaap.我草你怎么反编译我模组aaaaaR);
    paramclass_2540.method_53002(paramaaaaap.我草你怎么反编译我模组aaaaaS);
    paramclass_2540.method_10814(paramaaaaap.我草你怎么反编译我模组aaaaaT);
    paramclass_2540.method_10814(paramaaaaap.我草你怎么反编译我模组aaaaaU);
  }
  
  private static final aaaaap 你为什么要破解我的代码aaaacC(class_2540 paramclass_2540) {
    Intrinsics.checkNotNullExpressionValue(paramclass_2540.method_19772(), (String)aaaacD((char)-1158021116));
    Intrinsics.checkNotNullExpressionValue(paramclass_2540.method_19772(), (String)aaaacD((char)199426052));
    Intrinsics.checkNotNullExpressionValue(paramclass_2540.method_19772(), (String)aaaacD((char)-1903624188));
    Intrinsics.checkNotNullExpressionValue(paramclass_2540.method_19772(), (String)aaaacD((char)510722052));
    return new aaaaap(paramclass_2540.method_19772(), paramclass_2540.method_19772(), paramclass_2540.readInt(), paramclass_2540.method_19772(), paramclass_2540.method_19772());
  }
  
  static {
    byte[] arrayOfByte1 = "ý¾öÄ!\\ðGã&îözÈ¶%·\031ñq'¥qÁ\005zB({ïÊµ\036ô³tunð\034åctWìÓ\001S·oÃñÉóp\0009\035«ÃÔç&ZldÍ×{å\037m_úôRFôHuªj]|æ¢SîûÕ©ïCGm8k7\027Ø2ùõcV".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "K°b8C\034i\"".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)1284327187;
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
              Object[] arrayOfObject = new Object[8];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaaQ = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaam(null);
            Intrinsics.checkNotNullExpressionValue(class_8710.method_56483((String)aaaacD((char)570163205)), (String)aaaacD((char)-2076573690));
            我草你怎么反编译我模组aaaaad = class_8710.method_56483((String)aaaacD((char)570163205));
            Intrinsics.checkNotNullExpressionValue(class_9139.method_56438(aaaaap::你为什么要破解我的代码aaaacB, aaaaap::你为什么要破解我的代码aaaacC), (String)aaaacD((char)-1996816377));
            我草你怎么反编译我模组aaaaae = class_9139.method_56438(aaaaap::你为什么要破解我的代码aaaacB, aaaaap::你为什么要破解我的代码aaaacC);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaacD(char paramChar) {
    return ((Object[])aaaaaV)[paramChar];
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaam {
    private aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaam() {}
    
    static {
    
    }
  }
}
