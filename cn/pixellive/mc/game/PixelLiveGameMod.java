package cn.pixellive.mc.game;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaaf;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaal;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaao;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaj;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaak;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaal;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaan;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaao;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaap;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaq;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaat;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaav;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaw;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaaf;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaag;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaab.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaab.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaab.aaaaaf;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaaN;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaaP;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaaY;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaax;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaabG;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaabW;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaae.aaaaaa.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaae.aaaaaa.aaaaaj;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaH;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaap;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaai.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaal.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaan.aaaaaa;
import cn.pixellive.mc.game.event.game.AttackBlockEvent;
import cn.pixellive.mc.game.event.game.BlockBreakEvent;
import cn.pixellive.mc.game.event.game.BlockPlaceEvent;
import cn.pixellive.mc.game.server.PixelLiveGameServerMod;
import com.google.common.eventbus.EventBus;
import java.nio.file.Path;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1282;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1541;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1934;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2556;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3244;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_4466;
import net.minecraft.class_5218;
import net.minecraft.class_634;
import net.minecraft.class_7471;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nPixelLiveGameMod.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PixelLiveGameMod.kt\ncn/pixellive/mc/game/PixelLiveGameMod\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,800:1\n1#2:801\n*E\n"})
public final class PixelLiveGameMod implements ModInitializer {
  @NotNull
  public static final aaaaaa 我草你怎么反编译我模组aaaaaa;
  
  public static final int 我草你怎么反编译我模组aaaaab;
  
  public static final int 我草你怎么反编译我模组aaaaac;
  
  @NotNull
  private static final EventBus 我草你怎么反编译我模组aaaaad;
  
  private static boolean 我草你怎么反编译我模组aaaaae;
  
  @Nullable
  private static aaaabG 我草你怎么反编译我模组aaaaaf;
  
  @Nullable
  private static class_3218 我草你怎么反编译我模组aaaaag;
  
  static Object aaaaah;
  
  public void onInitialize() {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-587071488));
    try {
      aaaaac.你为什么要破解我的代码aaaaaa();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1856176129));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)35913730));
      aaaaab.Companion.你为什么要破解我的代码aaaaad();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-1293484029));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)21954564));
      你为什么要破解我的代码aaaaaa();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)695402501));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)761987078));
      你为什么要破解我的代码aaaaac();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-547225593));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1431240712));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaaX((char)-29360119), exception);
      throw exception;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaaa() {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)820772868));
    try {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1583415306));
      aaaaae.我草你怎么反编译我模组aaaaag.你为什么要破解我的代码aaaaac();
      aaaaao.我草你怎么反编译我模组aaaaaP.你为什么要破解我的代码aaaaac();
      aaaaal.我草你怎么反编译我模组aaaaaw.你为什么要破解我的代码aaaaac();
      aaaaaa.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-2100690933));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1433927692));
      if (我草你怎么反编译我模组aaaaaf == null || 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() == null)
        我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae(); 
      super((String)aaaaaX((char)-610729971));
      (new aaaaad()).你为什么要破解我的代码aaaaac();
      (new aaaaae((String)aaaaaX((char)-927793139))).你为什么要破解我的代码aaaaac();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-691339250));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1655308303));
      (new aaaaaq(false, 7, 7, 1)).你为什么要破解我的代码aaaaac();
      (new aaaaat(0, 0, 500)).你为什么要破解我的代码aaaaac();
      (new aaaaao(false, 25, 25, 20)).你为什么要破解我的代码aaaaac();
      (new aaaaak(aaaaav.我草你怎么反编译我模组aaaacF, null, 2, null)).你为什么要破解我的代码aaaaac();
      (new aaaaai(aaaaaw.我草你怎么反编译我模组aaaacK, null, 2, null)).你为什么要破解我的代码aaaaac();
      (new aaaaae(20, -2800, 50)).你为什么要破解我的代码aaaaac();
      (new aaaaaj(aaaaan.我草你怎么反编译我模组aaaabY, null, 2, null)).你为什么要破解我的代码aaaaac();
      (new aaaaal(aaaaap.我草你怎么反编译我模组aaaaci, null, 2, null)).你为什么要破解我的代码aaaaac();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)426377232));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)558628881));
      (new aaaaaf((String)aaaaaX((char)-1749155827), 1.0D)).你为什么要破解我的代码aaaaac();
      aaaaaa.你为什么要破解我的代码aaaaab();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-1567096814));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaaX((char)-1595015149), exception);
      throw exception;
    } 
  }
  
  @Environment(EnvType.CLIENT)
  private final void 你为什么要破解我的代码aaaaab() {
    try {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)132841492));
      ClientTickEvents.START_CLIENT_TICK.register(PixelLiveGameMod::你为什么要破解我的代码aaaaam);
      ClientTickEvents.END_CLIENT_TICK.register(PixelLiveGameMod::你为什么要破解我的代码aaaaan);
      ClientPlayConnectionEvents.JOIN.register(PixelLiveGameMod::你为什么要破解我的代码aaaaao);
      ClientPlayConnectionEvents.DISCONNECT.register(PixelLiveGameMod::你为什么要破解我的代码aaaaap);
      ClientPlayNetworking.registerGlobalReceiver(aaaaaf.我草你怎么反编译我模组aaaaad, PixelLiveGameMod::你为什么要破解我的代码aaaaar);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-1557331947));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaaX((char)-238944234), exception);
      throw exception;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaac() {
    try {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)2103771159));
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
        你为什么要破解我的代码aaaaab(); 
      ServerWorldEvents.LOAD.register(PixelLiveGameMod::你为什么要破解我的代码aaaaas);
      ServerTickEvents.START_SERVER_TICK.register(PixelLiveGameMod::你为什么要破解我的代码aaaaat);
      ServerTickEvents.END_SERVER_TICK.register(PixelLiveGameMod::你为什么要破解我的代码aaaaau);
      ServerPlayConnectionEvents.JOIN.register(this::你为什么要破解我的代码aaaaav);
      ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaw);
      ServerLifecycleEvents.SERVER_STOPPED.register(PixelLiveGameMod::你为什么要破解我的代码aaaaax);
      UseBlockCallback.EVENT.register(PixelLiveGameMod::你为什么要破解我的代码aaaaay);
      PlayerBlockBreakEvents.AFTER.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaz);
      AttackBlockCallback.EVENT.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaA);
      ServerLivingEntityEvents.ALLOW_DEATH.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaB);
      UseBlockCallback.EVENT.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaC);
      UseItemCallback.EVENT.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaD);
      ServerLivingEntityEvents.ALLOW_DAMAGE.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaE);
      AttackEntityCallback.EVENT.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaF);
      ServerPlayerEvents.AFTER_RESPAWN.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaG);
      ServerEntityEvents.ENTITY_LOAD.register(PixelLiveGameMod::你为什么要破解我的代码aaaaaH);
      aaaaac.你为什么要破解我的代码aaaaaa();
      System.out.println(aaaaaX((char)245104664));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaaX((char)-745930727), exception);
      throw exception;
    } 
  }
  
  private final Path 你为什么要破解我的代码aaaaad(MinecraftServer paramMinecraftServer) {
    Path path = paramMinecraftServer.method_27050(class_5218.field_24188).getParent();
    Intrinsics.checkNotNull(path);
    path = FabricLoader.getInstance().getGameDir().toAbsolutePath().getParent().resolve((String)aaaaaX((char)917766170)).toAbsolutePath();
    Intrinsics.checkNotNull(path);
    return (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ? path : path;
  }
  
  private final void 你为什么要破解我的代码aaaaae(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222, Path paramPath) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 正在加入 TNT填充 游戏阶段...");
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaaaN.我草你怎么反编译我模组aaaack;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    if (paramMinecraftServer.method_3760().method_14571().isEmpty()) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1093402651));
      aaaaaN.我草你怎么反编译我模组aaaack.你为什么要破解我的代码aaaadg(paramPath);
      if (!PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
        aaaaaN.我草你怎么反编译我模组aaaack.你为什么要破解我的代码aaaaah(paramMinecraftServer);
        PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1976631324));
      } 
    } 
    class_243 class_243 = aaaaaN.我草你怎么反编译我模组aaaack.你为什么要破解我的代码aaaaak();
    paramclass_3222.method_6082(class_243.field_1352, class_243.field_1351, class_243.field_1350, true);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 已传送至出生点 (" + class_243.field_1352 + ", " + class_243.field_1351 + ", " + class_243.field_1350 + ")");
  }
  
  private final void 你为什么要破解我的代码aaaaaf(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 正在加入生存挑战游戏阶段...");
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaaaY.我草你怎么反编译我模组aaaacX;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    if (paramMinecraftServer.method_3760().method_14571().isEmpty() && !PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1589968925));
      aaaaaY.我草你怎么反编译我模组aaaacX.你为什么要破解我的代码aaaaah(paramMinecraftServer);
      PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-31850466));
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaag(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 正在加入勇攀高峰游戏阶段...");
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaaae.我草你怎么反编译我模组aaaaaE;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    if (paramMinecraftServer.method_3760().method_14571().isEmpty() && !PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-883097569));
      aaaaae.我草你怎么反编译我模组aaaaaE.你为什么要破解我的代码aaaaah(paramMinecraftServer);
      PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-664797152));
    } 
    class_243 class_243 = aaaaae.我草你怎么反编译我模组aaaaaE.你为什么要破解我的代码aaaaak();
    paramclass_3222.method_6082(class_243.field_1352, class_243.field_1351, class_243.field_1350, true);
    paramclass_3222.method_7336(class_1934.field_9216);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 已传送至出生点 (" + class_243.field_1352 + ", " + class_243.field_1351 + ", " + class_243.field_1350 + ")");
  }
  
  private final void 你为什么要破解我的代码aaaaah(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222, Path paramPath) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 正在加入保卫小麦游戏阶段...");
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaabW.我草你怎么反编译我模组aaaacY;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    if (paramMinecraftServer.method_3760().method_14571().isEmpty()) {
      aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaadg(paramPath);
      if (!PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-1333526495));
        aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaah(paramMinecraftServer);
        PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)509804578));
      } 
    } else {
      aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaiD(paramclass_3222);
      aaaaaa.你为什么要破解我的代码aaaaaa().info("已为玩家 " + paramclass_3222.method_5477().getString() + " 填充物品栏");
    } 
    class_243 class_243 = aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaak();
    paramclass_3222.method_6082(class_243.field_1352, class_243.field_1351, class_243.field_1350, true);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 已传送至出生点 (" + class_243.field_1352 + ", " + class_243.field_1351 + ", " + class_243.field_1350 + ")");
  }
  
  private final void 你为什么要破解我的代码aaaaai(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222, Path paramPath) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + paramclass_3222.method_5477().getString() + " 正在加入怪兽竞技场游戏阶段...");
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaaaP.我草你怎么反编译我模组aaaacW;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    if (paramMinecraftServer.method_3760().method_14571().isEmpty() && !PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1592131619));
      aaaaaP.我草你怎么反编译我模组aaaacW.你为什么要破解我的代码aaaaah(paramMinecraftServer);
      PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-1423769564));
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaaj(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222, Path paramPath) {
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaaax.我草你怎么反编译我模组aaaabk;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    aaaaax.我草你怎么反编译我模组aaaabk.你为什么要破解我的代码aaaadg(paramPath);
    if (paramMinecraftServer.method_3760().method_14571().isEmpty() && !PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
      aaaaax.我草你怎么反编译我模组aaaabk.你为什么要破解我的代码aaaaah(paramMinecraftServer);
      PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
    } 
    if (paramclass_3222 != null) {
      paramclass_3222.method_7336(class_1934.field_9216);
    } else {
    
    } 
    class_243 class_243 = aaaaax.我草你怎么反编译我模组aaaabk.你为什么要破解我的代码aaaaak();
    if (paramclass_3222 != null) {
      paramclass_3222.method_6082(class_243.field_1352, class_243.field_1351, class_243.field_1350, true);
    } else {
    
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaak(MinecraftServer paramMinecraftServer, class_3222 paramclass_3222, Path paramPath) {
    我草你怎么反编译我模组aaaaaf = (aaaabG)aaaaad.我草你怎么反编译我模组aaaaaa;
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae());
    if (paramMinecraftServer.method_3760().method_14571().isEmpty() && !PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa()) {
      aaaaad.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(paramMinecraftServer);
      PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
    } 
  }
  
  private final Pair<String, String> 你为什么要破解我的代码aaaaal() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   6: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac.我草你怎么反编译我模组aaaaaa : [I
    //   12: swap
    //   13: invokevirtual ordinal : ()I
    //   16: iaload
    //   17: tableswitch default -> 83, 1 -> 44, 2 -> 57, 3 -> 70
    //   44: ldc_w 331284517
    //   47: i2c
    //   48: invokestatic aaaaaX : (C)Ljava/lang/Object;
    //   51: checkcast java/lang/String
    //   54: goto -> 91
    //   57: ldc_w -227672026
    //   60: i2c
    //   61: invokestatic aaaaaX : (C)Ljava/lang/Object;
    //   64: checkcast java/lang/String
    //   67: goto -> 91
    //   70: ldc_w -297205721
    //   73: i2c
    //   74: invokestatic aaaaaX : (C)Ljava/lang/Object;
    //   77: checkcast java/lang/String
    //   80: goto -> 91
    //   83: new kotlin/NoWhenBranchMatchedException
    //   86: dup
    //   87: invokespecial <init> : ()V
    //   90: athrow
    //   91: astore_1
    //   92: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   95: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   98: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;
    //   101: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac.我草你怎么反编译我模组aaaaaa : [I
    //   104: swap
    //   105: invokevirtual ordinal : ()I
    //   108: iaload
    //   109: tableswitch default -> 172, 1 -> 136, 2 -> 148, 3 -> 160
    //   136: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   139: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   142: invokevirtual 你为什么要破解我的代码aaaaac : ()Ljava/lang/String;
    //   145: goto -> 180
    //   148: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   151: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   154: invokevirtual 你为什么要破解我的代码aaaaae : ()Ljava/lang/String;
    //   157: goto -> 180
    //   160: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   163: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   166: invokevirtual 你为什么要破解我的代码aaaaai : ()Ljava/lang/String;
    //   169: goto -> 180
    //   172: new kotlin/NoWhenBranchMatchedException
    //   175: dup
    //   176: invokespecial <init> : ()V
    //   179: athrow
    //   180: astore_2
    //   181: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   184: aload_1
    //   185: aload_2
    //   186: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   191: invokeinterface info : (Ljava/lang/String;)V
    //   196: new kotlin/Pair
    //   199: dup
    //   200: aload_1
    //   201: aload_2
    //   202: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   205: areturn
  }
  
  private static final void 你为什么要破解我的代码aaaaam(class_310 paramclass_310) {
    Intrinsics.checkNotNull(paramclass_310);
    我草你怎么反编译我模组aaaaad.post(new aaaaab(paramclass_310));
  }
  
  private static final void 你为什么要破解我的代码aaaaan(class_310 paramclass_310) {
    Intrinsics.checkNotNull(paramclass_310);
    我草你怎么反编译我模组aaaaad.post(new aaaaaa(paramclass_310));
  }
  
  private static final void 你为什么要破解我的代码aaaaao(class_634 paramclass_634, PacketSender paramPacketSender, class_310 paramclass_310) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-637534168));
    我草你怎么反编译我模组aaaaae = !paramclass_634.method_48296().method_10756();
    Intrinsics.checkNotNullExpressionValue(aaaaaj.我草你怎么反编译我模组aaaaaa, (String)aaaaaX((char)-61538263));
    ClientPlayNetworking.send((class_8710)new aaaaaf(aaaaaj.我草你怎么反编译我模组aaaaaa));
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)377028650));
  }
  
  private static final void 你为什么要破解我的代码aaaaap(class_634 paramclass_634, class_310 paramclass_310) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-1755250645));
    if (我草你怎么反编译我模组aaaaaf != null) {
      Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaaf);
      我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaai(我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaal());
    } else {
    
    } 
    我草你怎么反编译我模组aaaaaf = null;
    我草你怎么反编译我模组aaaaag = null;
    我草你怎么反编译我模组aaaaae = false;
  }
  
  private static final void 你为什么要破解我的代码aaaaaq(aaaaaf paramaaaaaf) {
    Intrinsics.checkNotNullParameter(paramaaaaaf, (String)aaaaaX((char)-1005191124));
    aaaaaa.你为什么要破解我的代码aaaaaa().info("播放音效: " + paramaaaaaf.你为什么要破解我的代码aaaack());
    aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaf(paramaaaaaf.你为什么要破解我的代码aaaack(), paramaaaaaf.你为什么要破解我的代码aaaacl());
  }
  
  private static final void 你为什么要破解我的代码aaaaar(aaaaaf paramaaaaaf, ClientPlayNetworking.Context paramContext) {
    Intrinsics.checkNotNullParameter(paramaaaaaf, (String)aaaaaX((char)1112014893));
    Intrinsics.checkNotNullParameter(paramContext, (String)aaaaaX((char)1750138926));
    class_310.method_1551().execute(paramaaaaaf::你为什么要破解我的代码aaaaaq);
  }
  
  private static final void 你为什么要破解我的代码aaaaas(MinecraftServer paramMinecraftServer, class_3218 paramclass_3218) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)-914030545));
    Intrinsics.checkNotNull(paramMinecraftServer);
    Intrinsics.checkNotNull(paramclass_3218);
    我草你怎么反编译我模组aaaaad.post(new aaaaad(paramMinecraftServer, (class_1937)paramclass_3218));
  }
  
  private static final void 你为什么要破解我的代码aaaaat(MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNull(paramMinecraftServer);
    我草你怎么反编译我模组aaaaad.post(new aaaaaa(paramMinecraftServer));
  }
  
  private static final void 你为什么要破解我的代码aaaaau(MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNull(paramMinecraftServer);
    我草你怎么反编译我模组aaaaad.post(new aaaaac(paramMinecraftServer));
  }
  
  private static final void 你为什么要破解我的代码aaaaav(PixelLiveGameMod paramPixelLiveGameMod, class_3244 paramclass_3244, PacketSender paramPacketSender, MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramPixelLiveGameMod, (String)aaaaaX((char)-1829175248));
    class_3222 class_3222 = paramclass_3244.field_14140;
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家 " + class_3222.method_5477().getString() + " 加入服务器");
    Intrinsics.checkNotNull(paramMinecraftServer);
    Path path = paramPixelLiveGameMod.你为什么要破解我的代码aaaaad(paramMinecraftServer);
    String str1 = aaaaaa.你为什么要破解我的代码aaaaaa(path);
    if (str1 != null) {
      Pair<String, String> pair = paramPixelLiveGameMod.你为什么要破解我的代码aaaaal();
      String str3 = (String)pair.component1();
      String str4 = (String)pair.component2();
      aaaaab.你为什么要破解我的代码aaaaac(str4, str3, str1);
      aaaaaa.你为什么要破解我的代码aaaaaa().info("游戏阶段记录已发送: " + str1);
    } 
    String str2 = str1;
    if (str2 != null)
      switch (str2.hashCode()) {
        case 916506880:
          if (!str2.equals(aaaaaX((char)754319409)))
            break; 
          Intrinsics.checkNotNull(class_3222);
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaaf(paramMinecraftServer, class_3222);
          break;
        case 1912904869:
          if (!str2.equals(aaaaaX((char)1412497458)))
            break; 
          Intrinsics.checkNotNull(class_3222);
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaai(paramMinecraftServer, class_3222, path);
          break;
        case 1121163920:
          if (!str2.equals(aaaaaX((char)1474101299)))
            break; 
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaaj(paramMinecraftServer, class_3222, path);
          break;
        case 630370341:
          if (!str2.equals(aaaaaX((char)-1673789388)))
            break; 
          Intrinsics.checkNotNull(class_3222);
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaah(paramMinecraftServer, class_3222, path);
          break;
        case 80702676:
          if (!str2.equals(aaaaaX((char)-1733754827)))
            break; 
          Intrinsics.checkNotNull(class_3222);
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaae(paramMinecraftServer, class_3222, path);
          break;
        case 1902430713:
          if (!str2.equals(aaaaaX((char)-1795686346)))
            break; 
          Intrinsics.checkNotNull(class_3222);
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaak(paramMinecraftServer, class_3222, path);
          break;
        case 657401329:
          if (!str2.equals(aaaaaX((char)139919415)))
            break; 
          Intrinsics.checkNotNull(class_3222);
          paramPixelLiveGameMod.你为什么要破解我的代码aaaaag(paramMinecraftServer, class_3222);
          break;
      }  
    if (str1 != null) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info("加载音乐配置: " + str1);
      aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(str1);
    } 
    if (我草你怎么反编译我模组aaaaaf == null || 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() == null)
      我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae(); 
    super((String)aaaaaX((char)1761738765));
    ServerPlayNetworking.send((class_3222)new aaaaad(), (class_8710)new aaaaad());
    Intrinsics.checkNotNull(paramclass_3244);
    Intrinsics.checkNotNull(paramPacketSender);
    我草你怎么反编译我模组aaaaad.post(new aaaaaf(paramclass_3244, paramPacketSender, paramMinecraftServer));
  }
  
  private static final boolean 你为什么要破解我的代码aaaaaw(class_7471 paramclass_7471, class_3222 paramclass_3222, class_2556.class_7602 paramclass_7602) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("聊天消息: " + paramclass_3222.method_5477().getString() + ": " + paramclass_7471);
    Intrinsics.checkNotNull(paramclass_7471);
    Intrinsics.checkNotNull(paramclass_3222);
    Intrinsics.checkNotNull(paramclass_7602);
    aaaaag aaaaag = new aaaaag(paramclass_7471, paramclass_3222, paramclass_7602);
    我草你怎么反编译我模组aaaaad.post(aaaaag);
    return !aaaaag.getCancelled();
  }
  
  private static final void 你为什么要破解我的代码aaaaax(MinecraftServer paramMinecraftServer) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)1062469688));
    if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
      if (我草你怎么反编译我模组aaaaaf != null) {
        Intrinsics.checkNotNull(paramMinecraftServer);
        我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaai(paramMinecraftServer);
      } else {
      
      } 
      我草你怎么反编译我模组aaaaaf = null;
      PixelLiveGameServerMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(false);
    } 
    Intrinsics.checkNotNull(paramMinecraftServer);
    我草你怎么反编译我模组aaaaad.post(new aaaaae(paramMinecraftServer));
  }
  
  private static final class_1269 你为什么要破解我的代码aaaaay(class_1657 paramclass_1657, class_1937 paramclass_1937, class_1268 paramclass_1268, class_3965 paramclass_3965) {
    class_1799 class_1799 = paramclass_1657.method_5998(paramclass_1268);
    if (class_1799.method_7909() instanceof net.minecraft.class_1747) {
      class_1750 class_1750 = new class_1750(paramclass_1657, paramclass_1268, class_1799, paramclass_3965);
      BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(class_1750, null, 2, null);
      我草你怎么反编译我模组aaaaad.post(blockPlaceEvent);
      aaaaaa.你为什么要破解我的代码aaaaaa().info("方块放置事件: " + paramclass_1657.method_5477().getString());
      if (blockPlaceEvent.getResult() == null)
        blockPlaceEvent.getResult(); 
      return class_1269.field_5811;
    } 
    return class_1269.field_5811;
  }
  
  private static final void 你为什么要破解我的代码aaaaaz(class_1937 paramclass_1937, class_1657 paramclass_1657, class_2338 paramclass_2338, class_2680 paramclass_2680, class_2586 paramclass_2586) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("方块破坏事件: " + paramclass_1657.method_5477().getString() + " at " + paramclass_2338);
    class_1937 class_19371 = paramclass_1937;
    boolean bool = false;
    if (paramclass_2680 != null) {
      class_2680 class_26801 = paramclass_2680;
      boolean bool1 = false;
      Intrinsics.checkNotNull(paramclass_1657);
      Intrinsics.checkNotNull(paramclass_2338);
      Intrinsics.checkNotNullExpressionValue(class_26801.method_26204(), (String)aaaaaX((char)599523385));
    } else {
      new BlockBreakEvent(paramclass_1657, class_19371, paramclass_2338, class_26801.method_26204());
    } 
    Object object = (paramclass_1937 != null) ? null : null;
    if (object != null)
      我草你怎么反编译我模组aaaaad.post(object); 
  }
  
  private static final class_1269 你为什么要破解我的代码aaaaaA(class_1657 paramclass_1657, class_1937 paramclass_1937, class_1268 paramclass_1268, class_2338 paramclass_2338, class_2350 paramclass_2350) {
    Intrinsics.checkNotNull(paramclass_1657);
    Intrinsics.checkNotNull(paramclass_1937);
    Intrinsics.checkNotNull(paramclass_2338);
    AttackBlockEvent attackBlockEvent = new AttackBlockEvent(paramclass_1657, paramclass_1937, paramclass_2338);
    我草你怎么反编译我模组aaaaad.post(attackBlockEvent);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("阻止方块破坏: " + paramclass_1657.method_5477().getString() + " at " + paramclass_2338);
    return !aaaaab.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaae(paramclass_1657, paramclass_1937, paramclass_2338) ? class_1269.field_5814 : class_1269.field_5811;
  }
  
  private static final boolean 你为什么要破解我的代码aaaaaB(class_1309 paramclass_1309, class_1282 paramclass_1282, float paramFloat) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家死亡事件: " + ((class_3222)paramclass_1309).method_5477().getString());
    String str = (我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null;
    if (str != null)
      switch (str.hashCode()) {
        case 1121163920:
          if (!str.equals(aaaaaX((char)1474101299)))
            break; 
          aaaaax.我草你怎么反编译我模组aaaabk.你为什么要破解我的代码aaaabF((class_3222)paramclass_1309);
        case 630370341:
          if (!str.equals(aaaaaX((char)-1884815308)))
            break; 
          aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaabF((class_3222)paramclass_1309);
        case 657401329:
          if (str.equals(aaaaaX((char)-105709513)))
            aaaaae.我草你怎么反编译我模组aaaaaE.你为什么要破解我的代码aaaabF((class_3222)paramclass_1309); 
          break;
      }  
    return (paramclass_1309 instanceof class_3222) ? true : true;
  }
  
  private static final class_1269 你为什么要破解我的代码aaaaaC(class_1657 paramclass_1657, class_1937 paramclass_1937, class_1268 paramclass_1268, class_3965 paramclass_3965) {
    if (StringsKt.equals$default((我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null, (String)aaaaaX((char)1127612468), false, 2, null)) {
      class_1799 class_1799 = paramclass_1657.method_5998(paramclass_1268);
      Intrinsics.checkNotNullExpressionValue(paramclass_3965.method_17777(), (String)aaaaaX((char)-1726480326));
      if (paramclass_1657 instanceof class_3222 && aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaiO(paramclass_3965.method_17777())) {
        class_1792 class_1792 = class_1799.method_7909();
        if (Intrinsics.areEqual(class_1792, class_1802.field_8436)) {
          aaaaaa.你为什么要破解我的代码aaaaaa().info("使用喷溅药水: " + ((class_3222)paramclass_1657).method_5477().getString());
          Intrinsics.checkNotNull(paramclass_3965);
          aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaiI((class_3222)paramclass_1657, paramclass_3965);
          return class_1269.field_5814;
        } 
        if (Intrinsics.areEqual(class_1792, class_1802.field_8317)) {
          aaaaaa.你为什么要破解我的代码aaaaaa().info("种植小麦: " + ((class_3222)paramclass_1657).method_5477().getString());
          Intrinsics.checkNotNull(paramclass_3965);
          aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaiJ((class_3222)paramclass_1657, paramclass_3965);
          return class_1269.field_5814;
        } 
        if (Intrinsics.areEqual(class_1792, class_1802.field_8324)) {
          aaaaaa.你为什么要破解我的代码aaaaaa().info("使用骨粉: " + ((class_3222)paramclass_1657).method_5477().getString());
          Intrinsics.checkNotNull(paramclass_3965);
          aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaiK((class_3222)paramclass_1657, paramclass_3965);
          return class_1269.field_5814;
        } 
      } 
    } 
    return class_1269.field_5811;
  }
  
  private static final class_1271 你为什么要破解我的代码aaaaaD(class_1657 paramclass_1657, class_1937 paramclass_1937, class_1268 paramclass_1268) {
    class_1799 class_1799 = paramclass_1657.method_5998(paramclass_1268);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("使用喷溅药水物品: " + paramclass_1657.method_5477().getString());
    return StringsKt.equals$default((我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null, (String)aaaaaX((char)643760180), false, 2, null) ? (Intrinsics.areEqual(class_1799.method_7909(), class_1802.field_8436) ? class_1271.method_22427(class_1799) : class_1271.method_22430(class_1799)) : class_1271.method_22430(class_1799);
  }
  
  private static final boolean 你为什么要破解我的代码aaaaaE(class_1309 paramclass_1309, class_1282 paramclass_1282, float paramFloat) {
    String str1 = (我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null;
    if (paramclass_1309 instanceof class_3222 && paramclass_1282.method_5526() instanceof class_4466) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info("蜜蜂攻击玩家: " + ((class_3222)paramclass_1309).method_5477().getString());
      paramclass_1309.method_6092(new class_1293(class_1294.field_5899, 200, 2));
      Intrinsics.checkNotNull(paramclass_1282.method_5526(), (String)aaaaaX((char)103809083));
      class_4466 class_4466 = (class_4466)paramclass_1282.method_5526();
      class_4466.method_5768();
    } 
    Intrinsics.checkNotNullExpressionValue(class_1299.method_5890(paramclass_1309.method_5864()).toString(), (String)aaaaaX((char)-41549764));
    String str2 = class_1299.method_5890(paramclass_1309.method_5864()).toString();
    boolean bool = (paramclass_1309 instanceof net.minecraft.class_1439 || Intrinsics.areEqual(str2, aaaaaX((char)-173539267))) ? true : false;
    if (bool && paramclass_1282.method_5529() instanceof class_3222)
      return false; 
    return Intrinsics.areEqual(str1, aaaaaX((char)-1647050700)) ? true : (Intrinsics.areEqual(str1, aaaaaX((char)927072307)) ? true : true);
  }
  
  private static final class_1269 你为什么要破解我的代码aaaaaF(class_1657 paramclass_1657, class_1937 paramclass_1937, class_1268 paramclass_1268, class_1297 paramclass_1297, class_3966 paramclass_3966) {
    Intrinsics.checkNotNullExpressionValue(class_1299.method_5890(paramclass_1297.method_5864()).toString(), (String)aaaaaX((char)696975420));
    String str = class_1299.method_5890(paramclass_1297.method_5864()).toString();
    aaaaaa.你为什么要破解我的代码aaaaaa().info("Entity ID: " + str);
    boolean bool = (paramclass_1297 instanceof net.minecraft.class_1439 || Intrinsics.areEqual(str, aaaaaX((char)-1532755907))) ? true : false;
    aaaaaa.你为什么要破解我的代码aaaaaa().info("Is Golem: " + bool);
    return (Intrinsics.areEqual((我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null, aaaaaX((char)1333067827)) && paramclass_1657 instanceof class_3222 && bool) ? class_1269.field_5814 : class_1269.field_5811;
  }
  
  private static final void 你为什么要破解我的代码aaaaaG(class_3222 paramclass_32221, class_3222 paramclass_32222, boolean paramBoolean) {
    if (StringsKt.equals$default((我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null, (String)aaaaaX((char)1602748465), false, 2, null) && !paramBoolean) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info("玩家重生: " + paramclass_32222.method_5477().getString());
      Intrinsics.checkNotNull(paramclass_32222);
      aaaaaY.我草你怎么反编译我模组aaaacX.你为什么要破解我的代码aaaaie(paramclass_32222);
    } 
  }
  
  private static final void 你为什么要破解我的代码aaaaaH(class_1297 paramclass_1297, class_3218 paramclass_3218) {
    if (StringsKt.equals$default((我草你怎么反编译我模组aaaaaf != null) ? 我草你怎么反编译我模组aaaaaf.你为什么要破解我的代码aaaaae() : null, (String)aaaaaX((char)-2037579727), false, 2, null) && Intrinsics.areEqual(paramclass_1297.method_5864(), class_1299.field_6063)) {
      Intrinsics.checkNotNull(paramclass_1297, (String)aaaaaX((char)162988094));
      class_1541 class_1541 = (class_1541)paramclass_1297;
      class_1541.method_6967(40);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaaX((char)2106654783));
    } 
  }
  
  @NotNull
  public static final EventBus 你为什么要破解我的代码aaaaaI() {
    return 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
  }
  
  public static final boolean 你为什么要破解我的代码aaaaaJ() {
    return 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac();
  }
  
  public static final void 你为什么要破解我的代码aaaaaK(boolean paramBoolean) {
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(paramBoolean);
  }
  
  @Nullable
  public static final aaaabG 你为什么要破解我的代码aaaaaL() {
    return 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf();
  }
  
  public static final void 你为什么要破解我的代码aaaaaM(@Nullable aaaabG paramaaaabG) {
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag(paramaaaabG);
  }
  
  @Nullable
  public static final class_3218 你为什么要破解我的代码aaaaaN() {
    return 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaai();
  }
  
  @JvmStatic
  public static final void 你为什么要破解我的代码aaaaaO(@NotNull class_3218 paramclass_3218) {
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaak(paramclass_3218);
  }
  
  @JvmStatic
  public static final void 你为什么要破解我的代码aaaaaP() {
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaal();
  }
  
  static {
    byte[] arrayOfByte1 = "·¤\026àÏBH ü¡\"ºiG\0011^¬\000zú}d\013ýéCe©¨6«`Þþáùåßðwå·'Ê¿Û¢K\n\022wê»b\030;\024\021 ¤U¦Y\030\000h_ Bì¡\036Ip&e0õÿ|°zV\025rm\030\033ß7\"Õ]&P\016ö\003D\033ÇÜ=¾ÉIDP9OÓÈ=\001\017ok-·Üö¯ÁO¹Z P¾³RÝÓ%ä`ßmioÆ1\036?Ä×*pôx\003óßkZ@fÙ%>}752é+\003ðA¿¥·Z1îªs²×\ttSù\017Ný\r~âÌÅã¸\034¥v['\037\006$Æ\026=*<kI¿»Wêhù5¬ÙÈ+mp¼1\n,äJvMjõxò3ù£\037O\026À1¼4\036^·;AýTJ¤Á²ãGÂÞ\0241°ë¼£ð[¯wr\003ênÿ\020r6=âàD(ê\016\017BßzAØá«{/¨\023Ü\016C@/ÀÈ\023¿lR{(\\WÈ{ä\023à1­éE«Ý+¼-M\016b^°Lö­ÑØ\031îC#Ó\000y/%\025\016\032\004}B:7 îh¡ÝèrF\037#\0316ÅÀ¼ÖE\016çòrS­ñö'\022½öÏ{ïÀù\003Z¢õB\034kgf1ÞpÄ,ÎYgõß^ËuDò;£\013[pSæÜÉà99\017\027éèÒ\006:tí£¼s\007+Ë\005ú\\ëÞÃÍ\035°\022)\0172\033\004G¯ÕØ<åå¸\006eýÚS¸ù\nÜkÕÀ~\030ÔÂÉÂ¤ëÇ\000àkÀ Ä;Íg#`%½øæ\001¼zËb\006\\ûO{4V½|«mÌõGã¬à·Å\"\b'c\\A\000L(^Aésë¤K:ftYø Z\006ÃKA?yr\037==°<7ü.\016dÞyÃÉ¥¬Õ1¸¬\rC÷\024\036þ\027=¿®ÿNlxêA«öp\004\007ÇDÙR2Bü*Øø¿.\007Åö¡¦Û\027é)vüäãe\023þ'×=JOVYYÑÕw«P·\034\027å8´¯¿\025\r[\004|mÙ øuá!¦\nç?s\016CdÓÑ¡Ñ\002Gñ¤+\026»\031õ\0319Õ} SFx²ehþ-û·ÍQöB\005ë\024)´v.'\036¼\f´Ö\005wï÷\001(Ùr/Oÿ=+haV´%¿\033oxüð\017¢¤Á\033A .nl°æ>\036\021Õ§½äd\035pË»î^@ûòÃzWi>.S;\022\006mÇLö\036 \017Lå@\000` îgð¼/\037\bl±ÎR\022\"\033$mG\033EÔ\b;é \025±½\016%J\0204\021\000îí_\034\035ln¥×ºa£Ò ØhÎ$\004Ó\020 öã/@½\017ÁðÇÌWÝ%SE>p_òÛ$÷fè'Â¯Ö¥QöÕøhÖð\037a+k\036CN\023Q¡¸ÂÆ\t·9[Ò(NµÌ{ï\036ú\000Úd÷géýç£áVU»Òt´|:\004x}Ê'_%N\022&Cm?ÎÞ\022¹O1\025\016j\001Q\020ï;±º°ÖÜ¸¤m³â&\022¨\017\rûpCÐ\0031'#þ\005O\037J\\\001ÑÜ|«^á\016\022EIY\025Z`ºHGHO¥OS4n\027\035i>µf \007ªMìée¡\031\004R)eÏ0ë\032¹\026#YÉs\n9Þ$À¢-Ì\002Ù}\r±³ô÷Üé1IÄRÊV»Ó@eÚÔLÙ§þÖÉ]Àíõ\b[#\007kIü¬Õ.§Ór\t&r¯ôoô¡Dº\027ñ¡BE£YÍ\027Ü\rÚrNðG;M÷p&rLÁN§¢YwÜÁ\fAé}§³&I4£J\037;lC7äb/Z\003·|Õ1æÎÄæ\004\004º³wºÔÿ$<E\035¸¼§ñØWù-`÷Oe¥QDÒQïUY\nìR\016æ\034,y[\f#Ècª<\r\025lü\002l@@rÁ¶ã¨­9Í$j¸H1\020çk\006©æsAÕ)ûÓ¹ÑÙ)Õï\r\023·:ØiÚY¶Â\013½9\013DáNÓ\007Ã°Ð¯U´M\032bAE#ûÖy$ÑµYãÓbîN½:º>\016\037ÝG\034Í363ô¹\016oåz8ã1Oõ¾Îc>r¨t÷Vp,F¼vOW&¬p)_Ùr¸K×ô3îæ]«\\ÿ\0242aâXÝOx«ä\b\003,km`¡½]#§ïëø¥À¦\023]ÄA/\\'dH\037 ÕÏ3ì¼ØÑPB¦~\016]ù®(k9¶\0032(Ö,\bêÈ¾l]¾nv·ô]\032Â\001\017½þ¤\020£\021¢\016énÎ/ú\\%·ý\026\027\025È\t:|ji¢\036ú%ï15Ãgì&B'\034\007\tße\001?¯^©1KB\nG\baÀ\032¢Ûþ/Òi\027\033g\003\033ØÜÖÔÚ¡ÔÛøõó¡·{¬ë×¦hÒ©g\000ÿ:Ê/+_ÉÝ¡õ\000Ê\t´Ç\017\024\004á^w`Þ¹Ëh¹.\035Kúô,\f«$³s\035\027\\".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "-è(B1\035".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)1535159288;
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
              Object[] arrayOfObject = new Object[64];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaac = -64;
            我草你怎么反编译我模组aaaaab = 2000;
            我草你怎么反编译我模组aaaaaa = new aaaaaa(null);
            我草你怎么反编译我模组aaaaad = new EventBus();
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaaX(char paramChar) {
    return ((Object[])aaaaah)[paramChar];
  }
  
  public static final class PixelLiveGameMod {}
  
  public static final class PixelLiveGameMod {}
}
