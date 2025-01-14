package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaj;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaac.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaae.aaaaaa.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaae.aaaaaa.aaaaam;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabF;
import cn.hutool.core.util.RandomUtil;
import cn.pixellive.mc.game.PixelLiveGameMod;
import cn.pixellive.mc.game.event.live.LiveChatEvent;
import cn.pixellive.mc.game.event.live.LiveRoomConnectFailedEvent;
import cn.pixellive.mc.game.live.douyin.DouyinConnectListener;
import cn.pixellive.mc.game.live.douyin.DouyinListener;
import cn.pixellive.mc.game.live.kuaishou.KuaishouConnectListener;
import cn.pixellive.mc.game.live.kuaishou.KuaishouListener;
import cn.pixellive.mc.game.live.tiktok.TikTokConnectListener;
import cn.pixellive.mc.game.live.tiktok.TikTokListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.Subscribe;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_310;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tech.ordinaryroad.live.chat.client.codec.tiktok.api.TiktokApis;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.douyin.client.DouyinLiveChatClient;
import tech.ordinaryroad.live.chat.client.douyin.config.DouyinLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.douyin.listener.IDouyinConnectionListener;
import tech.ordinaryroad.live.chat.client.douyin.listener.IDouyinMsgListener;
import tech.ordinaryroad.live.chat.client.kuaishou.client.KuaishouLiveChatClient;
import tech.ordinaryroad.live.chat.client.kuaishou.config.KuaishouLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouConnectionListener;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouMsgListener;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.tiktok.client.TiktokLiveChatClient;
import tech.ordinaryroad.live.chat.client.tiktok.config.TiktokLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.tiktok.listener.ITiktokConnectionListener;
import tech.ordinaryroad.live.chat.client.tiktok.listener.ITiktokMsgListener;

public class aaaaab {
  public static BaseNettyClient<?, ?, ?, ?, ?, ?> 我草你怎么反编译我模组aaaaaa;
  
  private static final OkHttpClient 我草你怎么反编译我模组aaaaab;
  
  private static volatile ScheduledExecutorService 我草你怎么反编译我模组aaaaac;
  
  private static final AtomicInteger 我草你怎么反编译我模组aaaaad;
  
  private static final int 我草你怎么反编译我模组aaaaae;
  
  private static final int 我草你怎么反编译我模组aaaaaf;
  
  private static final int 我草你怎么反编译我模组aaaaag;
  
  private static final Function<TiktokApis.TiktokSignatureRequest, TiktokApis.TiktokSignatureResult> 我草你怎么反编译我模组aaaaah;
  
  static Object aaaaai;
  
  private aaaaab() {
    PixelLiveGameMod.你为什么要破解我的代码aaaaaI().register(this);
  }
  
  public static void 你为什么要破解我的代码aaaaaa() {
    aaaaad.你为什么要破解我的代码aaaaaa();
  }
  
  private static void 你为什么要破解我的代码aaaaab() {
    if (我草你怎么反编译我模组aaaaac != null && !我草你怎么反编译我模组aaaaac.isShutdown())
      我草你怎么反编译我模组aaaaac.shutdown(); 
    我草你怎么反编译我模组aaaaac = Executors.newSingleThreadScheduledExecutor();
    我草你怎么反编译我模组aaaaac.scheduleAtFixedRate(() -> {
          if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null)
            你为什么要破解我的代码aaaaag(); 
        }15L, 15L, TimeUnit.SECONDS);
  }
  
  private static int 你为什么要破解我的代码aaaaac() {
    aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab aaaaab1 = aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab.instance;
    return (aaaaab1 != null && aaaaab1.你为什么要破解我的代码aaaaaa() == aaaabF.我草你怎么反编译我模组aaaacD) ? 48 : 16;
  }
  
  public static void 你为什么要破解我的代码aaaaad(aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab paramaaaaab) {
    DouyinLiveChatClientConfig douyinLiveChatClientConfig;
    KuaishouLiveChatClientConfig kuaishouLiveChatClientConfig;
    TiktokLiveChatClientConfig tiktokLiveChatClientConfig;
    if (我草你怎么反编译我模组aaaaaa != null) {
      我草你怎么反编译我模组aaaaaa.disconnect();
      我草你怎么反编译我模组aaaaaa.destroy();
    } 
    switch (aaaaaa.我草你怎么反编译我模组aaaaaa[paramaaaaab.你为什么要破解我的代码aaaaaa().ordinal()]) {
      case 1:
        douyinLiveChatClientConfig = ((DouyinLiveChatClientConfig.DouyinLiveChatClientConfigBuilder)DouyinLiveChatClientConfig.builder().roomId(paramaaaaab.你为什么要破解我的代码aaaaac())).build();
        我草你怎么反编译我模组aaaaaa = (BaseNettyClient<?, ?, ?, ?, ?, ?>)new DouyinLiveChatClient(douyinLiveChatClientConfig, (IDouyinMsgListener)DouyinListener.INSTANCE, (IDouyinConnectionListener)DouyinConnectListener.INSTANCE);
        我草你怎么反编译我模组aaaaaa.connect();
        break;
      case 2:
        kuaishouLiveChatClientConfig = ((KuaishouLiveChatClientConfig.KuaishouLiveChatClientConfigBuilder)((KuaishouLiveChatClientConfig.KuaishouLiveChatClientConfigBuilder)KuaishouLiveChatClientConfig.builder().roomId(paramaaaaab.你为什么要破解我的代码aaaaae())).cookie(paramaaaaab.你为什么要破解我的代码aaaaag())).build();
        我草你怎么反编译我模组aaaaaa = (BaseNettyClient<?, ?, ?, ?, ?, ?>)new KuaishouLiveChatClient(kuaishouLiveChatClientConfig, (IKuaishouMsgListener)KuaishouListener.INSTANCE, (IKuaishouConnectionListener)KuaishouConnectListener.INSTANCE);
        我草你怎么反编译我模组aaaaaa.connect();
        break;
      case 3:
        TiktokApis.setSignatureProvider(我草你怎么反编译我模组aaaaah);
        tiktokLiveChatClientConfig = ((TiktokLiveChatClientConfig.TiktokLiveChatClientConfigBuilder)((TiktokLiveChatClientConfig.TiktokLiveChatClientConfigBuilder)TiktokLiveChatClientConfig.builder().cookie(paramaaaaab.你为什么要破解我的代码aaaaak())).roomId(paramaaaaab.你为什么要破解我的代码aaaaai())).build();
        我草你怎么反编译我模组aaaaaa = (BaseNettyClient<?, ?, ?, ?, ?, ?>)new TiktokLiveChatClient(tiktokLiveChatClientConfig, (ITiktokMsgListener)TikTokListener.INSTANCE, (ITiktokConnectionListener)TikTokConnectListener.INSTANCE);
        我草你怎么反编译我模组aaaaaa.connect();
        break;
    } 
  }
  
  public static boolean 你为什么要破解我的代码aaaaae() {
    return (我草你怎么反编译我模组aaaaaa != null && 我草你怎么反编译我模组aaaaaa.getStatus() == ClientStatusEnums.CONNECTED);
  }
  
  public static void 你为什么要破解我的代码aaaaaf() {
    if (我草你怎么反编译我模组aaaaaa != null) {
      我草你怎么反编译我模组aaaaaa.disconnect();
      我草你怎么反编译我模组aaaaaa.destroy();
    } 
    我草你怎么反编译我模组aaaaaa = null;
  }
  
  private static void 你为什么要破解我的代码aaaaag() {
    try {
      String str1;
      String str2;
      aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab aaaaab1 = aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab.instance;
      if (aaaaab1 == null) {
        你为什么要破解我的代码aaaaah();
        return;
      } 
      switch (aaaaaa.我草你怎么反编译我模组aaaaaa[aaaaab1.你为什么要破解我的代码aaaaaa().ordinal()]) {
        case 1:
          str1 = aaaaab1.你为什么要破解我的代码aaaaac();
          str2 = (String)aaaaat((char)-1924136960);
          break;
        case 2:
          str1 = aaaaab1.你为什么要破解我的代码aaaaae();
          str2 = (String)aaaaat((char)-330498047);
          break;
        case 3:
          str1 = aaaaab1.你为什么要破解我的代码aaaaai();
          str2 = (String)aaaaat((char)-1806303230);
          break;
        default:
          你为什么要破解我的代码aaaaah();
          return;
      } 
      aaaaad aaaaad = aaaaam.你为什么要破解我的代码aaaaac(str1, str2);
      if (aaaaad == null || !aaaaad.你为什么要破解我的代码aaaaaa()) {
        System.out.println("false" + RandomUtil.randomInt(10, 50));
        你为什么要破解我的代码aaaaah();
        return;
      } 
      if (!aaaaaa.你为什么要破解我的代码aaaaag(aaaaad.你为什么要破解我的代码aaaaab(), str2, str1)) {
        System.out.println("true" + RandomUtil.randomInt(10, 50));
        你为什么要破解我的代码aaaaah();
        return;
      } 
      if (我草你怎么反编译我模组aaaaad.get() > 0) {
        System.out.println("true" + RandomUtil.randomInt(100, 500));
        我草你怎么反编译我模组aaaaad.decrementAndGet();
      } 
    } catch (Exception exception) {
      你为什么要破解我的代码aaaaah();
    } 
  }
  
  public static void 你为什么要破解我的代码aaaaah() {
    System.out.println("" + 我草你怎么反编译我模组aaaaad.get() + 我草你怎么反编译我模组aaaaad.get());
    int i = 你为什么要破解我的代码aaaaac();
    int j = 我草你怎么反编译我模组aaaaad.get();
    if (j < i * 2)
      我草你怎么反编译我模组aaaaad.incrementAndGet(); 
    if (我草你怎么反编译我模组aaaaad.get() > Math.min(你为什么要破解我的代码aaaaac(), 48)) {
      你为什么要破解我的代码aaaaaf();
      PixelLiveGameMod.你为什么要破解我的代码aaaaaI().post(new LiveRoomConnectFailedEvent());
      System.out.println(aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaf.aaaaab.我草你怎么反编译我模组aaaaae + aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaf.aaaaab.我草你怎么反编译我模组aaaaae + aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaf.aaaaab.我草你怎么反编译我模组aaaaaf);
      class_310.method_1551().method_1576().execute(() -> {
            while (true) {
              try {
                while (true)
                  Thread.sleep(100000L); 
                break;
              } catch (InterruptedException interruptedException) {}
            } 
          });
    } 
  }
  
  public static DouyinLiveChatClient 你为什么要破解我的代码aaaaai() {
    return (DouyinLiveChatClient)我草你怎么反编译我模组aaaaaa;
  }
  
  public static KuaishouLiveChatClient 你为什么要破解我的代码aaaaaj() {
    return (KuaishouLiveChatClient)我草你怎么反编译我模组aaaaaa;
  }
  
  public static TiktokLiveChatClient 你为什么要破解我的代码aaaaak() {
    return (TiktokLiveChatClient)我草你怎么反编译我模组aaaaaa;
  }
  
  @Subscribe
  public void 你为什么要破解我的代码aaaaal(LiveChatEvent paramLiveChatEvent) {
    (new Thread(() -> {
          try {
            Thread.sleep(30000L);
            aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaf.aaaaab.你为什么要破解我的代码aaaaab(paramLiveChatEvent.getUsername(), paramLiveChatEvent.getMessage());
          } catch (InterruptedException interruptedException) {}
        })).start();
  }
  
  public static OkHttpClient 你为什么要破解我的代码aaaaam() {
    return 我草你怎么反编译我模组aaaaab;
  }
  
  public static void 你为什么要破解我的代码aaaaan() {
    if (我草你怎么反编译我模组aaaaac != null) {
      我草你怎么反编译我模组aaaaac.shutdown();
      try {
        if (!我草你怎么反编译我模组aaaaac.awaitTermination(5L, TimeUnit.SECONDS))
          我草你怎么反编译我模组aaaaac.shutdownNow(); 
      } catch (InterruptedException interruptedException) {
        我草你怎么反编译我模组aaaaac.shutdownNow();
      } 
    } 
    你为什么要破解我的代码aaaaaf();
  }
  
  static {
    byte[] arrayOfByte1 = "§l÷3w\026¹`Y ûaî<ß:x§ó%\020b\006îYTÁ±D©û\016Òjß:²Xá\034S«º¦¤^¶\013¶\0350oßV#Ï#6Å\002µóm©\020Ö2ÖY\\CvÍl½¾Ê§ú\032\021Ú±jÒ\"hóa(Í~á\007eïíôá<­¾,\016tÔª­'oÌÌ\007'®tT\rÊB¾®ÂZÀú{\rì°Ü=-ù!cÇÉþR6\030¼¤M\022Ý¦<v.nâ>ÁG²©>µ +ï¼Ï­ýä\003¥ÙùÆX\013É(ÈM\022\ne:¢jEG]Þg\007ÖÎÙ\025\006`µ\027w:Uåh¤Æã|èópÊâJÖ\r»+«»w{b#@\000\n¨\037¨tí§lÙb\021%¦Kn\027µÂ}K\017d]ôc\007È\032ûÄ!6\007\034G°A<¬PCK/®1\004@<=HÐñëÌK8Üu¬Å\000\030\025\b®ÇÎa¢îÏ\017& å¤|­ÂÉ\r\013\"fèO@ésn¸\035O«\t\033v\027uni®Jmkü\020Ô¡\rÈ­þóú\002ñP§ö çWycç\007\024µUß)%cjþ\032ðvýÀÅ;ÔåXÝ\026@-$^\024Îµ%UAk\006gRäy\026\004¿èÈ\036+\n\031è´ÂuK\000Äý&1ùX\027ß¤Ï\007Þ[~w¥\033\001\006¸¯±Ûb\031Þ\006¼XßãµµÙ°ÆW]q_´áa\\'Î$\023jûÁ\002ÐM+\të4Î\0177´Ø)ïÂ¿\020Âc\030h¤r\005¦=ÔKJÂmÐé}¹ÁÕý«Eê\rÃ\030\006¿p%8àETUDÈøQ\034 K£TèíÑ.r\005ôYNÍ<ÐÚÉ\f\b×ùÜ\005W-\000í»0F%TÂxçºÍ\bwÄlx D^-Ãtt2]ÝÿÉ¬«(Ü5ñFP*×\016\001Y\033G\t5\026_«¨01vÌ\f¥¼\035mÆþf\020ufa9Å".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "¡Ò|w4×\023".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-1971396668;
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
              Object[] arrayOfObject = new Object[57];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaag = 15;
            我草你怎么反编译我模组aaaaaf = 48;
            我草你怎么反编译我模组aaaaae = 16;
            我草你怎么反编译我模组aaaaab = new OkHttpClient();
            我草你怎么反编译我模组aaaaad = new AtomicInteger(0);
            我草你怎么反编译我模组aaaaah = (paramTiktokSignatureRequest -> {
                String str1 = (String)aaaaat((char)1138425859);
                String str2 = paramTiktokSignatureRequest.getBrowserVersion();
                Long long_ = paramTiktokSignatureRequest.getRealRoomId();
                String str3 = (String)aaaaat((char)-97517564);
                LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put(aaaaat((char)-1915813883), aaaaat((char)935591942));
                linkedHashMap.put(aaaaat((char)-609091577), aaaaat((char)1534394376));
                linkedHashMap.put(aaaaat((char)262602761), aaaaat((char)1081868298));
                linkedHashMap.put(aaaaat((char)1401225227), aaaaat((char)-547749880));
                linkedHashMap.put(aaaaat((char)413859852), aaaaat((char)-1375993843));
                linkedHashMap.put(aaaaat((char)-1937768434), aaaaat((char)-1341259761));
                linkedHashMap.put(aaaaat((char)-951975920), aaaaat((char)549781521));
                linkedHashMap.put(aaaaat((char)2076311570), str2);
                linkedHashMap.put(aaaaat((char)668401683), aaaaat((char)1930625039));
                linkedHashMap.put(aaaaat((char)-1241972716), aaaaat((char)-1745813483));
                linkedHashMap.put(aaaaat((char)-866779114), aaaaat((char)-2017394665));
                linkedHashMap.put(aaaaat((char)-190447592), aaaaat((char)-1296105451));
                linkedHashMap.put(aaaaat((char)875429913), aaaaat((char)-1465516006));
                linkedHashMap.put(aaaaat((char)-1139212261), aaaaat((char)-318111716));
                linkedHashMap.put(aaaaat((char)-1824587747), aaaaat((char)-123797474));
                linkedHashMap.put(aaaaat((char)1371537439), aaaaat((char)1661992992));
                linkedHashMap.put(aaaaat((char)962592801), aaaaat((char)1532755989));
                linkedHashMap.put(aaaaat((char)-889061342), str3);
                linkedHashMap.put(aaaaat((char)-1697710045), aaaaat((char)1713176612));
                linkedHashMap.put(aaaaat((char)-1472331739), aaaaat((char)766902293));
                linkedHashMap.put(aaaaat((char)1579745318), aaaaat((char)968818727));
                linkedHashMap.put(aaaaat((char)-55902168), aaaaat((char)-717094871));
                linkedHashMap.put(aaaaat((char)2103246890), String.valueOf(long_));
                linkedHashMap.put(aaaaat((char)-1117781973), aaaaat((char)705429548));
                linkedHashMap.put(aaaaat((char)-1868431315), aaaaat((char)1903427630));
                linkedHashMap.put(aaaaat((char)1632895023), aaaaat((char)1522008112));
                linkedHashMap.put(aaaaat((char)132841521), aaaaat((char)1820131358));
                linkedHashMap.put(aaaaat((char)-1611726798), aaaaat((char)1968111667));
                linkedHashMap.put(aaaaat((char)399835188), aaaaat((char)1590624309));
                String str4 = str3 + "/webcast/im/fetch/?" + str3;
                try {
                  Request request = (new Request.Builder()).url(str1).post(RequestBody.create(MediaType.parse((String)aaaaat((char)-1998061514)), str4)).build();
                  Response response = 我草你怎么反编译我模组aaaaab.newCall(request).execute();
                  try {
                    if (response.isSuccessful() && response.body() != null) {
                      JsonNode jsonNode = (new ObjectMapper()).readTree(response.body().string());
                      TiktokApis.TiktokSignatureResult tiktokSignatureResult = (TiktokApis.TiktokSignatureResult)(new ObjectMapper()).readValue(jsonNode.get((String)aaaaat((char)-1071185865)).toString(), TiktokApis.TiktokSignatureResult.class);
                      if (response != null)
                        response.close(); 
                      return (Function)tiktokSignatureResult;
                    } 
                    if (response != null)
                      response.close(); 
                  } catch (Throwable throwable) {
                    if (response != null)
                      try {
                        response.close();
                      } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                      }  
                    throw throwable;
                  } 
                } catch (Exception exception) {
                  aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaat((char)-382992328), exception);
                } 
                return null;
              });
            new aaaaab();
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
              你为什么要破解我的代码aaaaab();
              (new Thread(() -> {
                    while (true) {
                      try {
                        Thread.sleep(180000L);
                        if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null)
                          aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaf.aaaaab.你为什么要破解我的代码aaaaaa(); 
                      } catch (InterruptedException interruptedException) {}
                    } 
                  })).start();
            } 
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaat(char paramChar) {
    return ((Object[])aaaaai)[paramChar];
  }
}
