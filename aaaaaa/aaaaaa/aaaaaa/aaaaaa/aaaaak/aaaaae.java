package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.concurrent.CompletableFuture;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class aaaaae {
  @NotNull
  public static final aaaaae 我草你怎么反编译我模组aaaaaa;
  
  @Nullable
  private static class_2960 我草你怎么反编译我模组aaaaab;
  
  @Nullable
  private static String 我草你怎么反编译我模组aaaaac;
  
  private static final int 我草你怎么反编译我模组aaaaad;
  
  static Object aaaaae;
  
  public final void 你为什么要破解我的代码aaaaaa(@NotNull String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaae((char)-1456734208));
    if (Intrinsics.areEqual(paramString, 我草你怎么反编译我模组aaaaac))
      return; 
    我草你怎么反编译我模组aaaaac = paramString;
    CompletableFuture.runAsync(paramString::你为什么要破解我的代码aaaaad);
  }
  
  public final void 你为什么要破解我的代码aaaaab(@NotNull class_332 paramclass_332) {
    Intrinsics.checkNotNullParameter(paramclass_332, (String)aaaaae((char)2027618305));
    class_2960 class_29601 = 我草你怎么反编译我模组aaaaab;
    boolean bool = false;
    int i = class_310.method_1551().method_22683().method_4486();
    RenderSystem.enableBlend();
    paramclass_332.method_25290(class_29601, i - 我草你怎么反编译我模组aaaaad - 5, 5, 0.0F, 0.0F, 我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaad);
    RenderSystem.disableBlend();
  }
  
  private static final void 你为什么要破解我的代码aaaaac(class_1011 paramclass_1011) {
    Intrinsics.checkNotNullParameter(paramclass_1011, (String)aaaaae((char)-2047410174));
    class_1043 class_1043 = new class_1043(paramclass_1011);
    我草你怎么反编译我模组aaaaab = class_310.method_1551().method_1531().method_4617((String)aaaaae((char)-691470333), class_1043);
  }
  
  private static final void 你为什么要破解我的代码aaaaad(String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaae((char)-354418684));
    try {
      URLConnection uRLConnection = (new URL(paramString)).openConnection();
      uRLConnection.setRequestProperty((String)aaaaae((char)193200133), (String)aaaaae((char)-1192886266));
      BufferedImage bufferedImage = ImageIO.read(uRLConnection.getInputStream());
      class_1011 class_1011 = new class_1011(class_1011.class_1012.field_4997, bufferedImage.getWidth(), bufferedImage.getHeight(), true);
      byte b = 0;
      int i = bufferedImage.getWidth();
      while (b < i) {
        byte b1 = 0;
        int j = bufferedImage.getHeight();
        while (b1 < j) {
          int k = bufferedImage.getRGB(b, b1);
          int m = k >> 24 & 0xFF;
          int n = k >> 16 & 0xFF;
          int i1 = k >> 8 & 0xFF;
          int i2 = k & 0xFF;
          int i3 = m << 24 | i2 << 16 | i1 << 8 | n;
          class_1011.method_4305(b, b1, i3);
          b1++;
        } 
        b++;
      } 
      class_310.method_1551().execute(class_1011::你为什么要破解我的代码aaaaac);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  static {
    byte[] arrayOfByte1 = "èÔÖÁÛï¸óoùyo.Ù2\001MGÿàòñ>^\020Pêä-Õ\004Ç¬©·,Aüð¹Ü\026¨Ä'\020ªVF\036tÊÇNp@éJ\"® <Óa3a!°¾ÚHã]\020»FýïÔÄ:È§ÏÁÛ)õêæUsùÔ\016×øäû".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "U-ÀPp\032¶À".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)1039279772;
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
            我草你怎么反编译我模组aaaaaa = new aaaaae();
            我草你怎么反编译我模组aaaaad = 32;
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaae(char paramChar) {
    return ((Object[])aaaaae)[paramChar];
  }
}
