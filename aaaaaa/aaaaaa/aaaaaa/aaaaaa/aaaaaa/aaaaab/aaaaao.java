package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaabW;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaal.aaaaaa;
import com.google.gson.JsonPrimitive;
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

public final class aaaaao implements class_8710 {
  @NotNull
  public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaad 我草你怎么反编译我模组aaaaaF;
  
  private final boolean 我草你怎么反编译我模组aaaaaG;
  
  private final int 我草你怎么反编译我模组aaaaaH;
  
  private final int 我草你怎么反编译我模组aaaaaI;
  
  private final int 我草你怎么反编译我模组aaaaak;
  
  @JvmField
  @NotNull
  public static final class_8710.class_9154<aaaaao> 我草你怎么反编译我模组aaaaad;
  
  @JvmField
  @NotNull
  public static final class_9139<class_2540, aaaaao> 我草你怎么反编译我模组aaaaae;
  
  static Object aaaaaJ;
  
  public aaaaao(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
    this.我草你怎么反编译我模组aaaaaG = paramBoolean;
    this.我草你怎么反编译我模组aaaaaH = paramInt1;
    this.我草你怎么反编译我模组aaaaaI = paramInt2;
    this.我草你怎么反编译我模组aaaaak = paramInt3;
  }
  
  public final boolean 你为什么要破解我的代码aaaabP() {
    return this.我草你怎么反编译我模组aaaaaG;
  }
  
  public final int 你为什么要破解我的代码aaaabQ() {
    return this.我草你怎么反编译我模组aaaaaH;
  }
  
  public final int 你为什么要破解我的代码aaaabR() {
    return this.我草你怎么反编译我模组aaaaaI;
  }
  
  public final int 你为什么要破解我的代码aaaaay() {
    return this.我草你怎么反编译我模组aaaaak;
  }
  
  public final void 你为什么要破解我的代码aaaaac() {
    PayloadTypeRegistry.playC2S().register(我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaae);
    ServerPlayNetworking.registerGlobalReceiver(我草你怎么反编译我模组aaaaad, this::你为什么要破解我的代码aaaaca);
  }
  
  private final void 你为什么要破解我的代码aaaabS(aaaaao paramaaaaao) {
    boolean bool = paramaaaaao.你为什么要破解我的代码aaaabW();
    int i = paramaaaaao.你为什么要破解我的代码aaaaaD();
    int j = paramaaaaao.你为什么要破解我的代码aaaaaE();
    int k = paramaaaaao.你为什么要破解我的代码aaaabX();
    if (bool) {
      你为什么要破解我的代码aaaabT();
      return;
    } 
    int m = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaad.你为什么要破解我的代码aaaaab(我草你怎么反编译我模组aaaaaF, (String)aaaacd((char)-1698562048), 23);
    int n = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaad.你为什么要破解我的代码aaaaab(我草你怎么反编译我模组aaaaaF, (String)aaaacd((char)-110493695), 23);
    int i1 = aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaca();
    boolean bool1 = (m != i || n != j) ? true : false;
    boolean bool2 = (i1 != k) ? true : false;
    if (!bool1 && !bool2) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaacd((char)355532802));
      return;
    } 
    if (bool1)
      你为什么要破解我的代码aaaabU(m, n, i, j); 
    if (bool2)
      你为什么要破解我的代码aaaabV(k); 
  }
  
  private final void 你为什么要破解我的代码aaaabT() {
    aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaafD(true);
    aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaabV(20);
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaacd((char)-578355197));
  }
  
  private final void 你为什么要破解我的代码aaaabU(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 != paramInt3) {
      aaaaaa.你为什么要破解我的代码aaaaad(aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaadf(), (String)aaaacd((char)-1967915008), Integer.valueOf(paramInt3));
      aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaafB(paramInt3);
      aaaaaa.你为什么要破解我的代码aaaaaa().info("更新区域X: " + paramInt1 + " -> " + paramInt3);
    } 
    if (paramInt2 != paramInt4) {
      aaaaaa.你为什么要破解我的代码aaaaad(aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaadf(), (String)aaaacd((char)757727233), Integer.valueOf(paramInt4));
      aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaafC(paramInt4);
      aaaaaa.你为什么要破解我的代码aaaaaa().info("更新区域Z: " + paramInt2 + " -> " + paramInt4);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaabV(int paramInt) {
    aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaabV(paramInt);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("更新挑战次数: " + aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaca() + " -> " + paramInt);
  }
  
  @NotNull
  public class_8710.class_9154<aaaaao> method_56479() {
    return 我草你怎么反编译我模组aaaaad;
  }
  
  public final boolean 你为什么要破解我的代码aaaabW() {
    return this.我草你怎么反编译我模组aaaaaG;
  }
  
  public final int 你为什么要破解我的代码aaaaaD() {
    return this.我草你怎么反编译我模组aaaaaH;
  }
  
  public final int 你为什么要破解我的代码aaaaaE() {
    return this.我草你怎么反编译我模组aaaaaI;
  }
  
  public final int 你为什么要破解我的代码aaaabX() {
    return this.我草你怎么反编译我模组aaaaak;
  }
  
  @NotNull
  public final aaaaao 你为什么要破解我的代码aaaabY(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
    return new aaaaao(paramBoolean, paramInt1, paramInt2, paramInt3);
  }
  
  @NotNull
  public String toString() {
    return "WheatGuardianSettingsPayload(isReset=" + this.我草你怎么反编译我模组aaaaaG + ", areaX=" + this.我草你怎么反编译我模组aaaaaH + ", areaZ=" + this.我草你怎么反编译我模组aaaaaI + ", challengeNum=" + this.我草你怎么反编译我模组aaaaak + ")";
  }
  
  public int hashCode() {
    null = Boolean.hashCode(this.我草你怎么反编译我模组aaaaaG);
    null = null * 31 + Integer.hashCode(this.我草你怎么反编译我模组aaaaaH);
    null = null * 31 + Integer.hashCode(this.我草你怎么反编译我模组aaaaaI);
    return null * 31 + Integer.hashCode(this.我草你怎么反编译我模组aaaaak);
  }
  
  public boolean equals(@Nullable Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof aaaaao))
      return false; 
    aaaaao aaaaao1 = (aaaaao)paramObject;
    return (this.我草你怎么反编译我模组aaaaaG != aaaaao1.我草你怎么反编译我模组aaaaaG) ? false : ((this.我草你怎么反编译我模组aaaaaH != aaaaao1.我草你怎么反编译我模组aaaaaH) ? false : ((this.我草你怎么反编译我模组aaaaaI != aaaaao1.我草你怎么反编译我模组aaaaaI) ? false : (!(this.我草你怎么反编译我模组aaaaak != aaaaao1.我草你怎么反编译我模组aaaaak))));
  }
  
  private static final void 你为什么要破解我的代码aaaaca(aaaaao paramaaaaao1, aaaaao paramaaaaao2, ServerPlayNetworking.Context paramContext) {
    Intrinsics.checkNotNullParameter(paramaaaaao1, (String)aaaacd((char)1039794180));
    Intrinsics.checkNotNull(paramaaaaao2);
    paramaaaaao1.你为什么要破解我的代码aaaabS(paramaaaaao2);
  }
  
  private static final void 你为什么要破解我的代码aaaacb(aaaaao paramaaaaao, class_2540 paramclass_2540) {
    paramclass_2540.method_52964(paramaaaaao.我草你怎么反编译我模组aaaaaG);
    paramclass_2540.method_53002(paramaaaaao.我草你怎么反编译我模组aaaaaH);
    paramclass_2540.method_53002(paramaaaaao.我草你怎么反编译我模组aaaaaI);
    paramclass_2540.method_53002(paramaaaaao.我草你怎么反编译我模组aaaaak);
  }
  
  private static final aaaaao 你为什么要破解我的代码aaaacc(class_2540 paramclass_2540) {
    return new aaaaao(paramclass_2540.readBoolean(), paramclass_2540.readInt(), paramclass_2540.readInt(), paramclass_2540.readInt());
  }
  
  static {
    byte[] arrayOfByte1 = "^z³G\002Ë\030uJfâåwD Ì9\f3®j~%Lü\"ñÃ.Îr¾\005àô\020i£²ÿ 6{<ë\f-ð]HØ*ï:ö\003Ù(pì\027ÜAÁQ\037nù46¸4\002ÅP¸HcZl=\017\002¼öªLTx\027\036M¦îî\027¾\022rî\021¥s\031*S0\r^\004G\bêWqÓatéiÞrü\017Wiu\022\\×dHÐüû¹ÔC\022".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "Ú9ýö".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-858414938;
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
            我草你怎么反编译我模组aaaaaF = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaad(null);
            Intrinsics.checkNotNullExpressionValue(class_8710.method_56483((String)aaaacd((char)14548997)), (String)aaaacd((char)50593798));
            我草你怎么反编译我模组aaaaad = class_8710.method_56483((String)aaaacd((char)14548997));
            Intrinsics.checkNotNullExpressionValue(class_9139.method_56438(aaaaao::你为什么要破解我的代码aaaacb, aaaaao::你为什么要破解我的代码aaaacc), (String)aaaacd((char)879362055));
            我草你怎么反编译我模组aaaaae = class_9139.method_56438(aaaaao::你为什么要破解我的代码aaaacb, aaaaao::你为什么要破解我的代码aaaacc);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaacd(char paramChar) {
    return ((Object[])aaaaaJ)[paramChar];
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaad {
    private aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaad() {}
    
    private final int 你为什么要破解我的代码aaaaaa(String param1String, int param1Int) {
      aaaaaa.你为什么要破解我的代码aaaaab(aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaadf());
      V v = (aaaaaa.你为什么要破解我的代码aaaaab(aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaadf()) != null) ? (V)aaaaaa.你为什么要破解我的代码aaaaab(aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaadf()).get(param1String) : null;
      return (v instanceof JsonPrimitive) ? ((JsonPrimitive)v).getAsInt() : param1Int;
    }
    
    static {
    
    }
  }
}
