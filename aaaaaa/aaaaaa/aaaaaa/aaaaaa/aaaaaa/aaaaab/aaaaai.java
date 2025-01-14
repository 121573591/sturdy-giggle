package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaaP;
import java.security.MessageDigest;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_2540;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nMonsterArenaControlPayload.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MonsterArenaControlPayload.kt\ncn/pixellive/mc/game/client/network/MonsterArenaControlPayload\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,76:1\n215#2,2:77\n*S KotlinDebug\n*F\n+ 1 MonsterArenaControlPayload.kt\ncn/pixellive/mc/game/client/network/MonsterArenaControlPayload\n*L\n38#1:77,2\n*E\n"})
public final class aaaaai implements class_8710 {
  @NotNull
  public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaau 我草你怎么反编译我模组aaaaar;
  
  @NotNull
  private final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw 我草你怎么反编译我模组aaaaas;
  
  @NotNull
  private final Map<String, String> 我草你怎么反编译我模组aaaaat;
  
  @JvmField
  @NotNull
  public static final class_8710.class_9154<aaaaai> 我草你怎么反编译我模组aaaaad;
  
  @JvmField
  @NotNull
  public static final class_9139<class_2540, aaaaai> 我草你怎么反编译我模组aaaaae;
  
  static Object aaaaau;
  
  public aaaaai(@NotNull aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw, @NotNull Map<String, String> paramMap) {
    this.我草你怎么反编译我模组aaaaas = paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw;
    this.我草你怎么反编译我模组aaaaat = paramMap;
  }
  
  @NotNull
  public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw 你为什么要破解我的代码aaaaaV() {
    return this.我草你怎么反编译我模组aaaaas;
  }
  
  @NotNull
  public final Map<String, String> 你为什么要破解我的代码aaaaaW() {
    return this.我草你怎么反编译我模组aaaaat;
  }
  
  @NotNull
  public class_8710.class_9154<aaaaai> method_56479() {
    return 我草你怎么反编译我模组aaaaad;
  }
  
  public final void 你为什么要破解我的代码aaaaac() {
    PayloadTypeRegistry.playC2S().register(我草你怎么反编译我模组aaaaad, 我草你怎么反编译我模组aaaaae);
    ServerPlayNetworking.registerGlobalReceiver(我草你怎么反编译我模组aaaaad, aaaaai::你为什么要破解我的代码aaaabc);
  }
  
  @NotNull
  public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw 你为什么要破解我的代码aaaaaX() {
    return this.我草你怎么反编译我模组aaaaas;
  }
  
  @NotNull
  public final Map<String, String> 你为什么要破解我的代码aaaaaY() {
    return this.我草你怎么反编译我模组aaaaat;
  }
  
  @NotNull
  public final aaaaai 你为什么要破解我的代码aaaaaZ(@NotNull aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw, @NotNull Map<String, String> paramMap) {
    Intrinsics.checkNotNullParameter(paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw, (String)aaaabf((char)309723136));
    Intrinsics.checkNotNullParameter(paramMap, (String)aaaabf((char)497156097));
    return new aaaaai(paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw, paramMap);
  }
  
  @NotNull
  public String toString() {
    return "MonsterArenaControlPayload(controlType=" + this.我草你怎么反编译我模组aaaaas + ", data=" + this.我草你怎么反编译我模组aaaaat + ")";
  }
  
  public int hashCode() {
    null = this.我草你怎么反编译我模组aaaaas.hashCode();
    return null * 31 + this.我草你怎么反编译我模组aaaaat.hashCode();
  }
  
  public boolean equals(@Nullable Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof aaaaai))
      return false; 
    aaaaai aaaaai1 = (aaaaai)paramObject;
    return (this.我草你怎么反编译我模组aaaaas != aaaaai1.我草你怎么反编译我模组aaaaas) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaat, aaaaai1.我草你怎么反编译我模组aaaaat));
  }
  
  private static final void 你为什么要破解我的代码aaaabb(aaaaai paramaaaaai) {
    if (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaab.我草你怎么反编译我模组aaaaaa[paramaaaaai.我草你怎么反编译我模组aaaaas.ordinal()] == 1)
      aaaaaP.我草你怎么反编译我模组aaaacW.你为什么要破解我的代码aaaaaG(); 
  }
  
  private static final void 你为什么要破解我的代码aaaabc(aaaaai paramaaaaai, ServerPlayNetworking.Context paramContext) {
    MinecraftServer minecraftServer = (paramContext.player()).field_13995;
    minecraftServer.execute(paramaaaaai::你为什么要破解我的代码aaaabb);
  }
  
  private static final void 你为什么要破解我的代码aaaabd(aaaaai paramaaaaai, class_2540 paramclass_2540) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield 我草你怎么反编译我模组aaaaas : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw;
    //   5: checkcast java/lang/Enum
    //   8: invokevirtual method_10817 : (Ljava/lang/Enum;)Lnet/minecraft/class_2540;
    //   11: pop
    //   12: aload_1
    //   13: aload_0
    //   14: getfield 我草你怎么反编译我模组aaaaat : Ljava/util/Map;
    //   17: invokeinterface size : ()I
    //   22: invokevirtual method_53002 : (I)Lnet/minecraft/class_2540;
    //   25: pop
    //   26: aload_0
    //   27: getfield 我草你怎么反编译我模组aaaaat : Ljava/util/Map;
    //   30: astore_2
    //   31: iconst_0
    //   32: istore_3
    //   33: aload_2
    //   34: invokeinterface entrySet : ()Ljava/util/Set;
    //   39: invokeinterface iterator : ()Ljava/util/Iterator;
    //   44: astore #4
    //   46: aload #4
    //   48: invokeinterface hasNext : ()Z
    //   53: ifeq -> 118
    //   56: aload #4
    //   58: invokeinterface next : ()Ljava/lang/Object;
    //   63: checkcast java/util/Map$Entry
    //   66: astore #5
    //   68: aload #5
    //   70: astore #6
    //   72: iconst_0
    //   73: istore #7
    //   75: aload #6
    //   77: invokeinterface getKey : ()Ljava/lang/Object;
    //   82: checkcast java/lang/String
    //   85: astore #8
    //   87: aload #6
    //   89: invokeinterface getValue : ()Ljava/lang/Object;
    //   94: checkcast java/lang/String
    //   97: astore #9
    //   99: aload_1
    //   100: aload #8
    //   102: invokevirtual method_10814 : (Ljava/lang/String;)Lnet/minecraft/class_2540;
    //   105: pop
    //   106: aload_1
    //   107: aload #9
    //   109: invokevirtual method_10814 : (Ljava/lang/String;)Lnet/minecraft/class_2540;
    //   112: pop
    //   113: nop
    //   114: nop
    //   115: goto -> 46
    //   118: nop
    //   119: return
  }
  
  private static final aaaaai 你为什么要破解我的代码aaaabe(class_2540 paramclass_2540) {
    // Byte code:
    //   0: aload_0
    //   1: ldc aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw
    //   3: invokevirtual method_10818 : (Ljava/lang/Class;)Ljava/lang/Enum;
    //   6: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw
    //   9: astore_1
    //   10: aload_0
    //   11: invokevirtual readInt : ()I
    //   14: istore_2
    //   15: new java/util/LinkedHashMap
    //   18: dup
    //   19: invokespecial <init> : ()V
    //   22: checkcast java/util/Map
    //   25: astore_3
    //   26: iconst_0
    //   27: istore #4
    //   29: iload #4
    //   31: iload_2
    //   32: if_icmpge -> 82
    //   35: iload #4
    //   37: istore #5
    //   39: iconst_0
    //   40: istore #6
    //   42: aload_0
    //   43: invokevirtual method_19772 : ()Ljava/lang/String;
    //   46: astore #7
    //   48: aload_0
    //   49: invokevirtual method_19772 : ()Ljava/lang/String;
    //   52: astore #8
    //   54: aload_3
    //   55: aload #7
    //   57: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   60: aload #7
    //   62: aload #8
    //   64: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   67: aload #8
    //   69: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   74: pop
    //   75: nop
    //   76: iinc #4, 1
    //   79: goto -> 29
    //   82: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaai
    //   85: dup
    //   86: aload_1
    //   87: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   90: aload_1
    //   91: aload_3
    //   92: invokespecial <init> : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw;Ljava/util/Map;)V
    //   95: areturn
  }
  
  static {
    byte[] arrayOfByte1 = "Ð[x}R\021µþ:ï!¶\fÎ\027eÀ\fÙ\024ýx1Çá¶UÐ`t\nÛW.\r)\022k9\036íjl&¶Coå£\005¬\006¥£ö;MÔ\033Ñ\"ù3ûÒhÑ\022\0337g¹6g\027Ê\033¾\001+".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "¹-J\032rÿ".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-2068997063;
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
            我草你怎么反编译我模组aaaaar = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaau(null);
            Intrinsics.checkNotNullExpressionValue(class_8710.method_56483((String)aaaabf((char)810549250)), (String)aaaabf((char)-1011482621));
            我草你怎么反编译我模组aaaaad = class_8710.method_56483((String)aaaabf((char)810549250));
            Intrinsics.checkNotNullExpressionValue(class_9139.method_56438(aaaaai::你为什么要破解我的代码aaaabd, aaaaai::你为什么要破解我的代码aaaabe), (String)aaaabf((char)1856045060));
            我草你怎么反编译我模组aaaaae = class_9139.method_56438(aaaaai::你为什么要破解我的代码aaaabd, aaaaai::你为什么要破解我的代码aaaabe);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaabf(char paramChar) {
    return ((Object[])aaaaau)[paramChar];
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaau {
    private aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaau() {}
    
    static {
    
    }
  }
  
  public enum aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw {
    我草你怎么反编译我模组aaaacK;
    
    static Object aaaacM;
    
    @NotNull
    public static EnumEntries<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw> 你为什么要破解我的代码aaaaad() {
      return 我草你怎么反编译我模组aaaaaq;
    }
    
    static {
      byte[] arrayOfByte1 = "¡ËÖj§|zM2Êè ;þÆF5¸÷dfÖÞjö0R\022\023s\0071íâÒÉÐUó\027ÓM\005ñ\0375Ì±\f".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = " ÎäùÔy".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)-75974902;
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
                Object[] arrayOfObject = new Object[1];
                j = i;
                i = 0;
                do {
                  byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                  System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                  i += k;
                  arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
                } while (i != j);
              } 
              我草你怎么反编译我模组aaaacK = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaw((String)aaaaaT((char)-1329659904), 0);
              我草你怎么反编译我模组aaaacL = 你为什么要破解我的代码aaaaaS();
              我草你怎么反编译我模组aaaaaq = EnumEntriesKt.enumEntries((Enum[])我草你怎么反编译我模组aaaacL);
              return;
            } 
          } 
          break;
        } 
      } 
    }
    
    private static Object aaaaaT(char param1Char) {
      return ((Object[])aaaacM)[param1Char];
    }
  }
}
