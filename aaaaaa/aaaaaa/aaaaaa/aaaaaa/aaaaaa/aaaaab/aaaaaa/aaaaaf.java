package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import cn.pixellive.mc.game.PixelLiveGameMod;
import java.io.File;
import java.security.MessageDigest;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_5218;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nLiveEventPayload.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LiveEventPayload.kt\ncn/pixellive/mc/game/client/network/common/AuthorizedGamesPayload\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,227:1\n1855#2,2:228\n*S KotlinDebug\n*F\n+ 1 LiveEventPayload.kt\ncn/pixellive/mc/game/client/network/common/AuthorizedGamesPayload\n*L\n174#1:228,2\n*E\n"})
public final class aaaaaf implements class_8710 {
  @NotNull
  public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaak 我草你怎么反编译我模组aaaaah;
  
  @NotNull
  private final List<String> 我草你怎么反编译我模组aaaaai;
  
  @JvmField
  @NotNull
  public static final class_8710.class_9154<aaaaaf> 我草你怎么反编译我模组aaaaad;
  
  @JvmField
  @NotNull
  public static final class_9139<class_2540, aaaaaf> 我草你怎么反编译我模组aaaaae;
  
  static Object aaaaaj;
  
  public aaaaaf(@NotNull List<String> paramList) {
    this.我草你怎么反编译我模组aaaaai = paramList;
  }
  
  @NotNull
  public final List<String> 你为什么要破解我的代码aaaaar() {
    return this.我草你怎么反编译我模组aaaaai;
  }
  
  public final void 你为什么要破解我的代码aaaaac() {
    PayloadTypeRegistry.playC2S().register(我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaae);
    ServerPlayNetworking.registerGlobalReceiver(我草你怎么反编译我模组aaaaad, aaaaaf::你为什么要破解我的代码aaaaav);
  }
  
  @NotNull
  public class_8710.class_9154<aaaaaf> method_56479() {
    return 我草你怎么反编译我模组aaaaad;
  }
  
  @NotNull
  public final List<String> 你为什么要破解我的代码aaaaas() {
    return this.我草你怎么反编译我模组aaaaai;
  }
  
  @NotNull
  public final aaaaaf 你为什么要破解我的代码aaaaat(@NotNull List<String> paramList) {
    Intrinsics.checkNotNullParameter(paramList, (String)aaaaay((char)1659830272));
    return new aaaaaf(paramList);
  }
  
  @NotNull
  public String toString() {
    return "AuthorizedGamesPayload(authorizedGames=" + this.我草你怎么反编译我模组aaaaai + ")";
  }
  
  public int hashCode() {
    return this.我草你怎么反编译我模组aaaaai.hashCode();
  }
  
  public boolean equals(@Nullable Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof aaaaaf))
      return false; 
    aaaaaf aaaaaf1 = (aaaaaf)paramObject;
    return !!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaai, aaaaaf1.我草你怎么反编译我模组aaaaai);
  }
  
  private static final void 你为什么要破解我的代码aaaaav(aaaaaf paramaaaaaf, ServerPlayNetworking.Context paramContext) {
    List<String> list = paramaaaaaf.我草你怎么反编译我模组aaaaai;
    aaaaaa.你为什么要破解我的代码aaaaaa().info("" + paramContext.player().method_5477() + " received authorized games: " + paramContext.player().method_5477());
    if (PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() || (new File((paramContext.player()).field_13995.method_27050(class_5218.field_24188).getParent().toFile(), (String)aaaaay((char)729415681))).exists()) {
      PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf();
      if (!CollectionsKt.contains(list, (PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) ? PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae() : null) && PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) {
        PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf();
        (paramContext.player()).field_13987.method_52396(class_2561.method_30163("该服务端是您未授权的游戏类型: " + ((PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) ? PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae() : null)));
      } 
    } 
  }
  
  private static final void 你为什么要破解我的代码aaaaaw(aaaaaf paramaaaaaf, class_2540 paramclass_2540) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield 我草你怎么反编译我模组aaaaai : Ljava/util/List;
    //   5: invokeinterface size : ()I
    //   10: invokevirtual method_53002 : (I)Lnet/minecraft/class_2540;
    //   13: pop
    //   14: aload_0
    //   15: getfield 我草你怎么反编译我模组aaaaai : Ljava/util/List;
    //   18: checkcast java/lang/Iterable
    //   21: astore_2
    //   22: iconst_0
    //   23: istore_3
    //   24: aload_2
    //   25: invokeinterface iterator : ()Ljava/util/Iterator;
    //   30: astore #4
    //   32: aload #4
    //   34: invokeinterface hasNext : ()Z
    //   39: ifeq -> 73
    //   42: aload #4
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: astore #5
    //   51: aload #5
    //   53: checkcast java/lang/String
    //   56: astore #6
    //   58: iconst_0
    //   59: istore #7
    //   61: aload_1
    //   62: aload #6
    //   64: invokevirtual method_10814 : (Ljava/lang/String;)Lnet/minecraft/class_2540;
    //   67: pop
    //   68: nop
    //   69: nop
    //   70: goto -> 32
    //   73: nop
    //   74: return
  }
  
  private static final aaaaaf 你为什么要破解我的代码aaaaax(class_2540 paramclass_2540) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual readInt : ()I
    //   4: istore_1
    //   5: new java/util/ArrayList
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: checkcast java/util/List
    //   15: astore_2
    //   16: iconst_0
    //   17: istore_3
    //   18: iload_3
    //   19: iload_1
    //   20: if_icmpge -> 61
    //   23: iload_3
    //   24: istore #4
    //   26: iconst_0
    //   27: istore #5
    //   29: aload_2
    //   30: aload_0
    //   31: invokevirtual method_19772 : ()Ljava/lang/String;
    //   34: dup
    //   35: ldc_w 1529085954
    //   38: i2c
    //   39: invokestatic aaaaay : (C)Ljava/lang/Object;
    //   42: checkcast java/lang/String
    //   45: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   48: invokeinterface add : (Ljava/lang/Object;)Z
    //   53: pop
    //   54: nop
    //   55: iinc #3, 1
    //   58: goto -> 18
    //   61: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaaf
    //   64: dup
    //   65: aload_2
    //   66: invokespecial <init> : (Ljava/util/List;)V
    //   69: areturn
  }
  
  static {
    byte[] arrayOfByte1 = "ö}· SE?\020ñ\tt=é}\024\027?e»BU+©Ç²Éå'ña<ãµï)\025\037\016ûSÑ*\b­\002v\tªÙæ\rDÉ\báÿ#B³Sínìiü#\007ÛÕ\032Å\025\0162cÔüãÜ'ª_Óå«·@cß)bãä\030qÈ!o·Rä|Îè\004%íâ®#Z/".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "\b\016\013¼UØ³".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-113096868;
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
              Object[] arrayOfObject = new Object[6];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaah = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaak(null);
            Intrinsics.checkNotNullExpressionValue(class_8710.method_56483((String)aaaaay((char)1084817411)), (String)aaaaay((char)-537198588));
            我草你怎么反编译我模组aaaaad = class_8710.method_56483((String)aaaaay((char)1084817411));
            Intrinsics.checkNotNullExpressionValue(class_9139.method_56438(aaaaaf::你为什么要破解我的代码aaaaaw, aaaaaf::你为什么要破解我的代码aaaaax), (String)aaaaay((char)-1061748731));
            我草你怎么反编译我模组aaaaae = class_9139.method_56438(aaaaaf::你为什么要破解我的代码aaaaaw, aaaaaf::你为什么要破解我的代码aaaaax);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaay(char paramChar) {
    return ((Object[])aaaaaj)[paramChar];
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaak {
    private aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaak() {}
    
    static {
    
    }
  }
}
