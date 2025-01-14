package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaae.aaaaaa.aaaaaj;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabF;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

public final class aaaaab {
  @NotNull
  public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa Companion;
  
  @NotNull
  private aaaabF liveType = aaaabF.我草你怎么反编译我模组aaaacB;
  
  @NotNull
  private String douyinID = (String)aaaaat((char)-1093533696);
  
  @NotNull
  private String kuaishouID = (String)aaaaat((char)-1973157888);
  
  @NotNull
  private String kuaishouCookie = (String)aaaaat((char)-1353973760);
  
  @NotNull
  private String tiktokID = (String)aaaaat((char)-1426522112);
  
  @NotNull
  private String tiktokCookie = (String)aaaaat((char)1196883968);
  
  private boolean isGiftMsgDisplay = true;
  
  private boolean isNameDisplay;
  
  public static aaaaab instance;
  
  static Object aaaaaa;
  
  @NotNull
  public final aaaabF 你为什么要破解我的代码aaaaaa() {
    return this.liveType;
  }
  
  public final void 你为什么要破解我的代码aaaaab(@NotNull aaaabF paramaaaabF) {
    Intrinsics.checkNotNullParameter(paramaaaabF, (String)aaaaat((char)-749862911));
    this.liveType = paramaaaabF;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaac() {
    return this.douyinID;
  }
  
  public final void 你为什么要破解我的代码aaaaad(@NotNull String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)1430323201));
    this.douyinID = paramString;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaae() {
    return this.kuaishouID;
  }
  
  public final void 你为什么要破解我的代码aaaaaf(@NotNull String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)-1330774015));
    this.kuaishouID = paramString;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaag() {
    return this.kuaishouCookie;
  }
  
  public final void 你为什么要破解我的代码aaaaah(@NotNull String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)-630390783));
    this.kuaishouCookie = paramString;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaai() {
    return this.tiktokID;
  }
  
  public final void 你为什么要破解我的代码aaaaaj(@NotNull String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)-301924351));
    this.tiktokID = paramString;
  }
  
  @NotNull
  public final String 你为什么要破解我的代码aaaaak() {
    return this.tiktokCookie;
  }
  
  public final void 你为什么要破解我的代码aaaaal(@NotNull String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)-645201919));
    this.tiktokCookie = paramString;
  }
  
  public final boolean 你为什么要破解我的代码aaaaam() {
    return this.isGiftMsgDisplay;
  }
  
  public final void 你为什么要破解我的代码aaaaan(boolean paramBoolean) {
    this.isGiftMsgDisplay = paramBoolean;
  }
  
  public final boolean 你为什么要破解我的代码aaaaao() {
    return this.isNameDisplay;
  }
  
  public final void 你为什么要破解我的代码aaaaap(boolean paramBoolean) {
    this.isNameDisplay = paramBoolean;
  }
  
  @NotNull
  public static final aaaaab 你为什么要破解我的代码aaaaaq() {
    return Companion.你为什么要破解我的代码aaaaaa();
  }
  
  public static final void 你为什么要破解我的代码aaaaar(@NotNull aaaaab paramaaaaab) {
    Companion.你为什么要破解我的代码aaaaab(paramaaaaab);
  }
  
  @JvmStatic
  public static final void 你为什么要破解我的代码aaaaas() {
    Companion.你为什么要破解我的代码aaaaae();
  }
  
  static {
    byte[] arrayOfByte1 = "¤¼\013\032l·eãÝÛÿNÔô¨§µYÊÙêÇâdéyÎ]\021à@âF~ Õ¸\023<BõpCJ¹".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "ðéÐ|`ãkÁ".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)535993270;
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
            Companion = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa(null);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaat(char paramChar) {
    return ((Object[])aaaaaa)[paramChar];
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa {
    static Object aaaaaa;
    
    private aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa() {}
    
    @NotNull
    public final aaaaab 你为什么要破解我的代码aaaaaa() {
      if (aaaaab.instance != null)
        return aaaaab.instance; 
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaaf((char)-1764163584));
      return null;
    }
    
    public final void 你为什么要破解我的代码aaaaab(@NotNull aaaaab param1aaaaab) {
      Intrinsics.checkNotNullParameter(param1aaaaab, (String)aaaaaf((char)-1674051583));
      aaaaab.instance = param1aaaaab;
    }
    
    public final void 你为什么要破解我的代码aaaaad() {
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)-1312423934));
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)-536346621));
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)-887554044));
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)1512112133));
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)1306853382));
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)1435762695));
        aaaaaj.我草你怎么反编译我模组aaaaaa.add((String)aaaaaf((char)-360120312));
      } 
      File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), (String)aaaaaf((char)2044526601));
      if (!file.exists()) {
        你为什么要破解我的代码aaaaab(new aaaaab());
        你为什么要破解我的代码aaaaae();
        return;
      } 
      try {
        InputStream inputStream = Files.newInputStream(file.toPath(), new java.nio.file.OpenOption[0]);
        Throwable throwable = null;
        try {
          InputStream inputStream1 = inputStream;
          boolean bool = false;
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream1, StandardCharsets.UTF_8);
          Throwable throwable1 = null;
          try {
            InputStreamReader inputStreamReader1 = inputStreamReader;
            boolean bool1 = false;
            Gson gson = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().create();
            Intrinsics.checkNotNullExpressionValue(gson.fromJson(inputStreamReader1, aaaaab.class), (String)aaaaaf((char)-203489270));
            aaaaab.Companion.你为什么要破解我的代码aaaaab((aaaaab)gson.fromJson(inputStreamReader1, aaaaab.class));
            Unit unit1 = Unit.INSTANCE;
          } catch (Throwable throwable2) {
            throwable1 = throwable2;
            throw throwable2;
          } finally {
            CloseableKt.closeFinally(inputStreamReader, throwable1);
          } 
          Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable1) {
          throwable = throwable1;
          throw throwable1;
        } finally {
          CloseableKt.closeFinally(inputStream, throwable);
        } 
      } catch (Exception exception) {
        aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaaf((char)-473104373), exception);
      } 
    }
    
    @JvmStatic
    public final void 你为什么要破解我的代码aaaaae() {
      File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), (String)aaaaaf((char)98435081));
      try {
        OutputStream outputStream = Files.newOutputStream(file.toPath(), new java.nio.file.OpenOption[0]);
        Throwable throwable = null;
        try {
          OutputStream outputStream1 = outputStream;
          boolean bool = false;
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream1, StandardCharsets.UTF_8);
          Throwable throwable1 = null;
          try {
            OutputStreamWriter outputStreamWriter1 = outputStreamWriter;
            boolean bool1 = false;
            Gson gson = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().create();
            gson.toJson(aaaaab.Companion.你为什么要破解我的代码aaaaaa(), outputStreamWriter1);
            Unit unit1 = Unit.INSTANCE;
          } catch (Throwable throwable2) {
            throwable1 = throwable2;
            throw throwable2;
          } finally {
            CloseableKt.closeFinally(outputStreamWriter, throwable1);
          } 
          Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable1) {
          throwable = throwable1;
          throw throwable1;
        } finally {
          CloseableKt.closeFinally(outputStream, throwable);
        } 
      } catch (Exception exception) {
        aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaaf((char)-563347444), exception);
      } 
    }
    
    static {
      byte[] arrayOfByte1 = "Ã\bfh¸]µØ\022Pµ\\\0311\013MÊÌ\030W_ÈJw\031ùéGçº{\007rI\bí!:AIuõò!ëgB\022\036\013ÆÇþ8d\030g@\026¦òÒÍ\035Ü¾\fäÎM\023\027KFU\026\030§[Î\035«\"l\032?]tÁ¢¾1?\n\025\022Í$åå\036Q÷\"CÒa:&y±0\024ûöÃDèx¢ÔÃ\031_®ÿ°$Â<¦AB×\023î\006u\020J;z§a²\bB4~m¤,G\036;Ök^tG¥»¾\003¼Û£¤\b\020'd¿F«â42PÏÀR²\022\0016\007\004p\032Â×¦\003Êô·çëË¶".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "KMôJrÇ_".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)-307866387;
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
                Object[] arrayOfObject = new Object[13];
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
    
    private static Object aaaaaf(char param1Char) {
      return ((Object[])aaaaaa)[param1Char];
    }
  }
}
