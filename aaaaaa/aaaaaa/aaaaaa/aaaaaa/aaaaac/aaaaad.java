package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaC;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaF;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaH;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak.aaaaac;
import cn.pixellive.mc.game.PixelLiveGameMod;
import cn.pixellive.mc.game.event.game.BlockPlaceEvent;
import cn.pixellive.mc.game.event.live.LiveEnterRoomEvent;
import cn.pixellive.mc.game.event.live.LiveGiftEvent;
import cn.pixellive.mc.game.event.live.LiveLikeEvent;
import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1352;
import net.minecraft.class_1355;
import net.minecraft.class_1400;
import net.minecraft.class_1510;
import net.minecraft.class_1541;
import net.minecraft.class_1642;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_2960;
import net.minecraft.class_3002;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nMonsterBattleStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,1605:1\n1855#2,2:1606\n1855#2,2:1608\n1855#2,2:1610\n1855#2,2:1612\n1855#2:1614\n1856#2:1622\n1855#2,2:1623\n1855#2,2:1626\n1855#2,2:1628\n766#2:1634\n857#2,2:1635\n1855#2,2:1637\n1774#2,4:1639\n800#2,11:1643\n1855#2,2:1654\n766#2:1656\n857#2,2:1657\n1855#2,2:1659\n1855#2,2:1661\n1855#2,2:1663\n1855#2,2:1665\n1054#2:1667\n1855#2,2:1668\n1054#2:1670\n1549#2:1671\n1620#2,3:1672\n1054#2:1675\n1549#2:1676\n1620#2,3:1677\n1855#2,2:1680\n1855#2,2:1682\n1855#2,2:1684\n1855#2,2:1686\n372#3,7:1615\n1#4:1625\n204#5,4:1630\n*S KotlinDebug\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage\n*L\n322#1:1606,2\n378#1:1608,2\n390#1:1610,2\n395#1:1612,2\n488#1:1614\n488#1:1622\n524#1:1623,2\n601#1:1626,2\n617#1:1628,2\n742#1:1634\n742#1:1635,2\n784#1:1637,2\n809#1:1639,4\n1047#1:1643,11\n1048#1:1654,2\n1053#1:1656\n1053#1:1657,2\n1054#1:1659,2\n1061#1:1661,2\n1074#1:1663,2\n1126#1:1665,2\n1134#1:1667\n1136#1:1668,2\n1155#1:1670\n1157#1:1671\n1157#1:1672,3\n1166#1:1675\n1168#1:1676\n1168#1:1677,3\n1535#1:1680,2\n1561#1:1682,2\n1583#1:1684,2\n896#1:1686,2\n494#1:1615,7\n729#1:1630,4\n*E\n"})
public final class aaaaad extends aaaabG {
  @NotNull
  public static final aaaaad 我草你怎么反编译我模组aaaaaa;
  
  @Nullable
  private static MinecraftServer 我草你怎么反编译我模组aaaaab;
  
  @NotNull
  private static final class_243 我草你怎么反编译我模组aaaaac;
  
  private static int 我草你怎么反编译我模组aaaaad;
  
  private static class_3002 我草你怎么反编译我模组aaaaae;
  
  @NotNull
  private static List<String> 我草你怎么反编译我模组aaaaaf;
  
  @NotNull
  private static final List<String> 我草你怎么反编译我模组aaaaag;
  
  private static int 我草你怎么反编译我模组aaaaah;
  
  @NotNull
  private static final ArrayDeque<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah> 我草你怎么反编译我模组aaaaai;
  
  private static int 我草你怎么反编译我模组aaaaaj;
  
  private static final int 我草你怎么反编译我模组aaaaak;
  
  @NotNull
  private static List<String> 我草你怎么反编译我模组aaaaal;
  
  @NotNull
  private static final ConcurrentHashMap<UUID, Long> 我草你怎么反编译我模组aaaaam;
  
  private static final long 我草你怎么反编译我模组aaaaan;
  
  private static final int 我草你怎么反编译我模组aaaaao;
  
  private static final int 我草你怎么反编译我模组aaaaap;
  
  @NotNull
  private static final List<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK> 我草你怎么反编译我模组aaaaaq;
  
  private static long 我草你怎么反编译我模组aaaaar;
  
  private static long 我草你怎么反编译我模组aaaaas;
  
  private static long 我草你怎么反编译我模组aaaaat;
  
  private static final long 我草你怎么反编译我模组aaaaau;
  
  private static final long 我草你怎么反编译我模组aaaaav;
  
  @NotNull
  private static final Map<UUID, Long> 我草你怎么反编译我模组aaaaaw;
  
  @NotNull
  private static final Set<UUID> 我草你怎么反编译我模组aaaaax;
  
  private static class_269 我草你怎么反编译我模组aaaaay;
  
  private static class_266 我草你怎么反编译我模组aaaaaz;
  
  @NotNull
  private static final Map<String, Integer> 我草你怎么反编译我模组aaaaaA;
  
  @NotNull
  private static final Map<String, String> 我草你怎么反编译我模组aaaaaB;
  
  @NotNull
  private static final ConcurrentHashMap<UUID, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk> 我草你怎么反编译我模组aaaaaC;
  
  private static ScheduledExecutorService 我草你怎么反编译我模组aaaaaD;
  
  static Object aaaaaE;
  
  public final int 你为什么要破解我的代码aaaaaa() {
    return 我草你怎么反编译我模组aaaaah;
  }
  
  public final void 你为什么要破解我的代码aaaaab(int paramInt) {
    我草你怎么反编译我模组aaaaah = paramInt;
  }
  
  @NotNull
  public final ConcurrentHashMap<UUID, Long> 你为什么要破解我的代码aaaaac() {
    return 我草你怎么反编译我模组aaaaam;
  }
  
  @NotNull
  public final Map<String, String> 你为什么要破解我的代码aaaaad() {
    return 我草你怎么反编译我模组aaaaaB;
  }
  
  @NotNull
  public String 你为什么要破解我的代码aaaaae() {
    return (String)aaaabe((char)1363869696);
  }
  
  @NotNull
  public String 你为什么要破解我的代码aaaaaf() {
    return (String)aaaabe((char)1322450945);
  }
  
  private final ScheduledExecutorService 你为什么要破解我的代码aaaaag() {
    return Executors.newSingleThreadScheduledExecutor(aaaaad::你为什么要破解我的代码aaaaaS);
  }
  
  public void 你为什么要破解我的代码aaaaah(@NotNull MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramMinecraftServer, (String)aaaabe((char)533331970));
    我草你怎么反编译我模组aaaaab = paramMinecraftServer;
    Intrinsics.checkNotNullExpressionValue(paramMinecraftServer.method_30002(), (String)aaaabe((char)645070851));
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaak(paramMinecraftServer.method_30002());
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().register(this);
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(你为什么要破解我的代码aaaaae());
    你为什么要破解我的代码aaaaaH(paramMinecraftServer);
    aaaaab.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
    ((class_1928.class_4310)paramMinecraftServer.method_30002().method_8450().method_20746(class_1928.field_19400)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_30002().method_8450().method_20746(class_1928.field_19392)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_30002().method_8450().method_20746(class_1928.field_19391)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_30002().method_8450().method_20746(class_1928.field_19393)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4312)paramMinecraftServer.method_30002().method_8450().method_20746(class_1928.field_19405)).method_35236(0, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_30002().method_8450().method_20746(class_1928.field_19388)).method_20758(false, paramMinecraftServer);
    aaaaaF aaaaaF = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaai();
    我草你怎么反编译我模组aaaaas = aaaaaF.你为什么要破解我的代码aaaaag();
    我草你怎么反编译我模组aaaaaf = aaaaaF.你为什么要破解我的代码aaaaae();
    我草你怎么反编译我模组aaaaal = aaaaaF.你为什么要破解我的代码aaaaak();
    class_3002 class_30021 = paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaabe((char)-1386414076), (String)aaaabe((char)-1777729531)), (class_2561)class_2561.method_43471((String)aaaabe((char)960167942)));
    class_3002 class_30022 = class_30021;
    boolean bool = false;
    class_30022.method_12956(aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaai().你为什么要破解我的代码aaaaaa());
    class_30022.method_12954(我草你怎么反编译我模组aaaaad);
    Intrinsics.checkNotNullExpressionValue(class_30021, (String)aaaabe((char)-949551097));
    我草你怎么反编译我模组aaaaae = class_30021;
    aaaaac.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
    aaaaac.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaak();
    你为什么要破解我的代码aaaaaI();
    ServerLivingEntityEvents.AFTER_DEATH.register(aaaaad::你为什么要破解我的代码aaaaaT);
    paramMinecraftServer.execute(aaaaad::你为什么要破解我的代码aaaaaU);
  }
  
  public void 你为什么要破解我的代码aaaaai(@NotNull MinecraftServer paramMinecraftServer) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 747765762
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: getstatic cn/pixellive/mc/game/PixelLiveGameMod.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa;
    //   17: invokevirtual 你为什么要破解我的代码aaaaal : ()V
    //   20: getstatic cn/pixellive/mc/game/PixelLiveGameMod.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa;
    //   23: invokevirtual 你为什么要破解我的代码aaaaaa : ()Lcom/google/common/eventbus/EventBus;
    //   26: aload_0
    //   27: invokevirtual unregister : (Ljava/lang/Object;)V
    //   30: aload_0
    //   31: invokevirtual 你为什么要破解我的代码aaaaaG : ()V
    //   34: aload_0
    //   35: invokespecial 你为什么要破解我的代码aaaaaQ : ()V
    //   38: aload_1
    //   39: invokevirtual method_3837 : ()Lnet/minecraft/class_3004;
    //   42: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   45: dup
    //   46: ifnonnull -> 64
    //   49: pop
    //   50: ldc_w -1578237944
    //   53: i2c
    //   54: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   57: checkcast java/lang/String
    //   60: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   63: aconst_null
    //   64: invokevirtual method_12973 : (Lnet/minecraft/class_3002;)V
    //   67: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac;
    //   70: invokevirtual 你为什么要破解我的代码aaaaal : ()V
    //   73: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac;
    //   76: invokevirtual 你为什么要破解我的代码aaaaam : ()V
    //   79: aload_1
    //   80: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   83: invokevirtual method_14571 : ()Ljava/util/List;
    //   86: dup
    //   87: ldc_w -1134559223
    //   90: i2c
    //   91: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   94: checkcast java/lang/String
    //   97: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   100: checkcast java/lang/Iterable
    //   103: astore_2
    //   104: iconst_0
    //   105: istore_3
    //   106: aload_2
    //   107: invokeinterface iterator : ()Ljava/util/Iterator;
    //   112: astore #4
    //   114: aload #4
    //   116: invokeinterface hasNext : ()Z
    //   121: ifeq -> 157
    //   124: aload #4
    //   126: invokeinterface next : ()Ljava/lang/Object;
    //   131: astore #5
    //   133: aload #5
    //   135: checkcast net/minecraft/class_3222
    //   138: astore #6
    //   140: iconst_0
    //   141: istore #7
    //   143: aload #6
    //   145: getstatic net/minecraft/class_1934.field_9220 : Lnet/minecraft/class_1934;
    //   148: invokevirtual method_7336 : (Lnet/minecraft/class_1934;)Z
    //   151: pop
    //   152: nop
    //   153: nop
    //   154: goto -> 114
    //   157: nop
    //   158: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab;
    //   161: iconst_0
    //   162: invokevirtual 你为什么要破解我的代码aaaaab : (Z)V
    //   165: aload_1
    //   166: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   169: invokevirtual method_8450 : ()Lnet/minecraft/class_1928;
    //   172: getstatic net/minecraft/class_1928.field_19392 : Lnet/minecraft/class_1928$class_4313;
    //   175: invokevirtual method_20746 : (Lnet/minecraft/class_1928$class_4313;)Lnet/minecraft/class_1928$class_4315;
    //   178: checkcast net/minecraft/class_1928$class_4310
    //   181: iconst_1
    //   182: aload_1
    //   183: invokevirtual method_20758 : (ZLnet/minecraft/server/MinecraftServer;)V
    //   186: aload_1
    //   187: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   190: invokevirtual method_8450 : ()Lnet/minecraft/class_1928;
    //   193: getstatic net/minecraft/class_1928.field_19391 : Lnet/minecraft/class_1928$class_4313;
    //   196: invokevirtual method_20746 : (Lnet/minecraft/class_1928$class_4313;)Lnet/minecraft/class_1928$class_4315;
    //   199: checkcast net/minecraft/class_1928$class_4310
    //   202: iconst_1
    //   203: aload_1
    //   204: invokevirtual method_20758 : (ZLnet/minecraft/server/MinecraftServer;)V
    //   207: aload_1
    //   208: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   211: invokevirtual method_8450 : ()Lnet/minecraft/class_1928;
    //   214: getstatic net/minecraft/class_1928.field_19393 : Lnet/minecraft/class_1928$class_4313;
    //   217: invokevirtual method_20746 : (Lnet/minecraft/class_1928$class_4313;)Lnet/minecraft/class_1928$class_4315;
    //   220: checkcast net/minecraft/class_1928$class_4310
    //   223: iconst_1
    //   224: aload_1
    //   225: invokevirtual method_20758 : (ZLnet/minecraft/server/MinecraftServer;)V
    //   228: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   231: dup
    //   232: ifnonnull -> 250
    //   235: pop
    //   236: ldc_w -1460273142
    //   239: i2c
    //   240: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   243: checkcast java/lang/String
    //   246: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   249: aconst_null
    //   250: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_266;
    //   253: dup
    //   254: ifnonnull -> 272
    //   257: pop
    //   258: ldc_w 1464533003
    //   261: i2c
    //   262: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   265: checkcast java/lang/String
    //   268: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   271: aconst_null
    //   272: invokevirtual method_1194 : (Lnet/minecraft/class_266;)V
    //   275: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaag : Ljava/util/List;
    //   278: invokeinterface clear : ()V
    //   283: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaA : Ljava/util/Map;
    //   286: invokeinterface clear : ()V
    //   291: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaB : Ljava/util/Map;
    //   294: invokeinterface clear : ()V
    //   299: return
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaaj(@NotNull BlockPlaceEvent paramBlockPlaceEvent) {
    Intrinsics.checkNotNullParameter(paramBlockPlaceEvent, (String)aaaabe((char)1855586316));
    paramBlockPlaceEvent.setResult(class_1269.field_5814);
  }
  
  @NotNull
  public class_243 你为什么要破解我的代码aaaaak() {
    return 我草你怎么反编译我模组aaaaac;
  }
  
  @NotNull
  public MinecraftServer 你为什么要破解我的代码aaaaal() {
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaab);
    return 我草你怎么反编译我模组aaaaab;
  }
  
  private final void 你为什么要破解我的代码aaaaam() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   6: invokevirtual 你为什么要破解我的代码aaaaai : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaF;
    //   9: astore_1
    //   10: aload_1
    //   11: invokevirtual 你为什么要破解我的代码aaaaaQ : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaC;
    //   14: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   17: astore_2
    //   18: aload_1
    //   19: invokevirtual 你为什么要破解我的代码aaaaam : ()Ljava/util/Map;
    //   22: aload_2
    //   23: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   28: checkcast java/lang/String
    //   31: dup
    //   32: ifnonnull -> 37
    //   35: pop
    //   36: aload_2
    //   37: astore_3
    //   38: aload_1
    //   39: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   42: istore #4
    //   44: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaad : I
    //   47: i2f
    //   48: iload #4
    //   50: i2f
    //   51: fdiv
    //   52: fstore #5
    //   54: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   57: dup
    //   58: ifnonnull -> 76
    //   61: pop
    //   62: ldc_w -598540280
    //   65: i2c
    //   66: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   69: checkcast java/lang/String
    //   72: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   75: aconst_null
    //   76: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaad : I
    //   79: invokevirtual method_12954 : (I)V
    //   82: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   85: dup
    //   86: ifnonnull -> 104
    //   89: pop
    //   90: ldc_w 324009992
    //   93: i2c
    //   94: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   97: checkcast java/lang/String
    //   100: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   103: aconst_null
    //   104: fload #5
    //   106: ldc_w 0.33
    //   109: fcmpg
    //   110: ifge -> 119
    //   113: getstatic net/minecraft/class_1259$class_1260.field_5784 : Lnet/minecraft/class_1259$class_1260;
    //   116: goto -> 137
    //   119: fload #5
    //   121: ldc_w 0.66
    //   124: fcmpg
    //   125: ifge -> 134
    //   128: getstatic net/minecraft/class_1259$class_1260.field_5782 : Lnet/minecraft/class_1259$class_1260;
    //   131: goto -> 137
    //   134: getstatic net/minecraft/class_1259$class_1260.field_5785 : Lnet/minecraft/class_1259$class_1260;
    //   137: invokevirtual method_5416 : (Lnet/minecraft/class_1259$class_1260;)V
    //   140: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   143: dup
    //   144: ifnonnull -> 162
    //   147: pop
    //   148: ldc_w -815267832
    //   151: i2c
    //   152: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   155: checkcast java/lang/String
    //   158: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   161: aconst_null
    //   162: ldc_w 784007174
    //   165: i2c
    //   166: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   169: checkcast java/lang/String
    //   172: iconst_3
    //   173: anewarray java/lang/Object
    //   176: astore #6
    //   178: aload #6
    //   180: iconst_0
    //   181: aload_3
    //   182: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   187: aastore
    //   188: aload #6
    //   190: iconst_1
    //   191: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaad : I
    //   194: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   197: aastore
    //   198: aload #6
    //   200: iconst_2
    //   201: iload #4
    //   203: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   206: aastore
    //   207: aload #6
    //   209: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   212: checkcast net/minecraft/class_2561
    //   215: invokevirtual method_5413 : (Lnet/minecraft/class_2561;)V
    //   218: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   221: dup
    //   222: ifnonnull -> 227
    //   225: pop
    //   226: return
    //   227: astore #6
    //   229: aload #6
    //   231: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   234: invokevirtual method_14571 : ()Ljava/util/List;
    //   237: dup
    //   238: ldc_w 647888905
    //   241: i2c
    //   242: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   245: checkcast java/lang/String
    //   248: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   251: checkcast java/lang/Iterable
    //   254: astore #7
    //   256: iconst_0
    //   257: istore #8
    //   259: aload #7
    //   261: invokeinterface iterator : ()Ljava/util/Iterator;
    //   266: astore #9
    //   268: aload #9
    //   270: invokeinterface hasNext : ()Z
    //   275: ifeq -> 329
    //   278: aload #9
    //   280: invokeinterface next : ()Ljava/lang/Object;
    //   285: astore #10
    //   287: aload #10
    //   289: checkcast net/minecraft/class_3222
    //   292: astore #11
    //   294: iconst_0
    //   295: istore #12
    //   297: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   300: dup
    //   301: ifnonnull -> 319
    //   304: pop
    //   305: ldc_w 1894383624
    //   308: i2c
    //   309: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   312: checkcast java/lang/String
    //   315: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   318: aconst_null
    //   319: aload #11
    //   321: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   324: nop
    //   325: nop
    //   326: goto -> 268
    //   329: nop
    //   330: return
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaan(@NotNull aaaaac paramaaaaac) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 2085224460
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaah : I
    //   17: istore_2
    //   18: iload_2
    //   19: iconst_1
    //   20: iadd
    //   21: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaah : I
    //   24: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaah : I
    //   27: bipush #20
    //   29: irem
    //   30: ifne -> 37
    //   33: aload_0
    //   34: invokespecial 你为什么要破解我的代码aaaaaA : ()V
    //   37: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaah : I
    //   40: iconst_2
    //   41: irem
    //   42: ifne -> 143
    //   45: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   48: dup
    //   49: ifnull -> 141
    //   52: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   55: dup
    //   56: ifnull -> 141
    //   59: invokevirtual method_14571 : ()Ljava/util/List;
    //   62: dup
    //   63: ifnull -> 141
    //   66: checkcast java/lang/Iterable
    //   69: astore_3
    //   70: iconst_0
    //   71: istore #4
    //   73: aload_3
    //   74: invokeinterface iterator : ()Ljava/util/Iterator;
    //   79: astore #5
    //   81: aload #5
    //   83: invokeinterface hasNext : ()Z
    //   88: ifeq -> 137
    //   91: aload #5
    //   93: invokeinterface next : ()Ljava/lang/Object;
    //   98: astore #6
    //   100: aload #6
    //   102: checkcast net/minecraft/class_3222
    //   105: astore #7
    //   107: iconst_0
    //   108: istore #8
    //   110: aload #7
    //   112: new net/minecraft/class_1293
    //   115: dup
    //   116: getstatic net/minecraft/class_1294.field_5925 : Lnet/minecraft/class_6880;
    //   119: sipush #10000
    //   122: iconst_0
    //   123: iconst_0
    //   124: iconst_0
    //   125: invokespecial <init> : (Lnet/minecraft/class_6880;IIZZ)V
    //   128: invokevirtual method_6092 : (Lnet/minecraft/class_1293;)Z
    //   131: pop
    //   132: nop
    //   133: nop
    //   134: goto -> 81
    //   137: nop
    //   138: goto -> 143
    //   141: pop
    //   142: nop
    //   143: aload_0
    //   144: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   147: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   150: astore_2
    //   151: aload_2
    //   152: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   155: dup
    //   156: ldc_w -1227620339
    //   159: i2c
    //   160: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   163: checkcast java/lang/String
    //   166: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   169: astore #9
    //   171: iconst_0
    //   172: istore #10
    //   174: aload #9
    //   176: invokeinterface iterator : ()Ljava/util/Iterator;
    //   181: astore_3
    //   182: aload_3
    //   183: invokeinterface hasNext : ()Z
    //   188: ifeq -> 560
    //   191: aload_3
    //   192: invokeinterface next : ()Ljava/lang/Object;
    //   197: astore #4
    //   199: aload #4
    //   201: checkcast net/minecraft/class_1297
    //   204: astore #5
    //   206: iconst_0
    //   207: istore #6
    //   209: aload #5
    //   211: instanceof net/minecraft/class_1308
    //   214: ifeq -> 555
    //   217: aload #5
    //   219: invokevirtual getClass : ()Ljava/lang/Class;
    //   222: invokevirtual getName : ()Ljava/lang/String;
    //   225: dup
    //   226: ldc_w -1966538738
    //   229: i2c
    //   230: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   233: checkcast java/lang/String
    //   236: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   239: ldc_w -1730936817
    //   242: i2c
    //   243: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   246: checkcast java/lang/String
    //   249: iconst_0
    //   250: iconst_2
    //   251: aconst_null
    //   252: invokestatic startsWith$default : (Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z
    //   255: ifeq -> 555
    //   258: nop
    //   259: nop
    //   260: ldc_w net/minecraft/class_1308
    //   263: ldc_w -190250992
    //   266: i2c
    //   267: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   270: checkcast java/lang/String
    //   273: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   276: astore #7
    //   278: goto -> 301
    //   281: astore #8
    //   283: ldc_w net/minecraft/class_1308
    //   286: ldc_w 1755447313
    //   289: i2c
    //   290: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   293: checkcast java/lang/String
    //   296: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   299: astore #7
    //   301: aload #7
    //   303: astore #11
    //   305: aload #11
    //   307: iconst_1
    //   308: invokevirtual setAccessible : (Z)V
    //   311: aload #11
    //   313: aload #5
    //   315: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   318: dup
    //   319: ldc_w -1704919022
    //   322: i2c
    //   323: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   326: checkcast java/lang/String
    //   329: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   332: checkcast net/minecraft/class_1355
    //   335: astore #7
    //   337: nop
    //   338: aload #7
    //   340: invokevirtual getClass : ()Ljava/lang/Class;
    //   343: ldc_w -542113773
    //   346: i2c
    //   347: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   350: checkcast java/lang/String
    //   353: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   356: astore #12
    //   358: goto -> 383
    //   361: astore #13
    //   363: aload #7
    //   365: invokevirtual getClass : ()Ljava/lang/Class;
    //   368: ldc_w -2140536812
    //   371: i2c
    //   372: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   375: checkcast java/lang/String
    //   378: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   381: astore #12
    //   383: aload #12
    //   385: astore #8
    //   387: aload #8
    //   389: iconst_1
    //   390: invokevirtual setAccessible : (Z)V
    //   393: aload #8
    //   395: aload #7
    //   397: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   400: dup
    //   401: ldc_w 1470103573
    //   404: i2c
    //   405: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   408: checkcast java/lang/String
    //   411: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   414: checkcast java/util/Set
    //   417: astore #12
    //   419: aload #12
    //   421: invokeinterface size : ()I
    //   426: iconst_1
    //   427: if_icmple -> 555
    //   430: aload #5
    //   432: checkcast net/minecraft/class_1308
    //   435: invokevirtual method_5797 : ()Lnet/minecraft/class_2561;
    //   438: dup
    //   439: ifnull -> 480
    //   442: invokeinterface getString : ()Ljava/lang/String;
    //   447: astore #14
    //   449: aload #14
    //   451: dup
    //   452: ifnull -> 480
    //   455: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   458: aload #14
    //   460: ldc_w -1838809066
    //   463: i2c
    //   464: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   467: checkcast java/lang/String
    //   470: checkcast java/lang/CharSequence
    //   473: invokestatic removePrefix : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   476: dup
    //   477: ifnonnull -> 484
    //   480: pop
    //   481: goto -> 556
    //   484: astore #13
    //   486: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   489: aload #5
    //   491: invokevirtual getClass : ()Ljava/lang/Class;
    //   494: invokevirtual getName : ()Ljava/lang/String;
    //   497: aload #12
    //   499: invokeinterface size : ()I
    //   504: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   509: invokeinterface info : (Ljava/lang/String;)V
    //   514: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   517: aload #5
    //   519: checkcast net/minecraft/class_1308
    //   522: aload #13
    //   524: invokespecial 你为什么要破解我的代码aaaaaN : (Lnet/minecraft/class_1308;Ljava/lang/String;)V
    //   527: goto -> 555
    //   530: astore #11
    //   532: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   535: ldc_w 358350871
    //   538: i2c
    //   539: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   542: checkcast java/lang/String
    //   545: aload #11
    //   547: checkcast java/lang/Throwable
    //   550: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   555: nop
    //   556: nop
    //   557: goto -> 182
    //   560: nop
    //   561: invokestatic currentTimeMillis : ()J
    //   564: lstore #15
    //   566: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaai : Ljava/util/ArrayDeque;
    //   569: checkcast java/util/Collection
    //   572: invokeinterface isEmpty : ()Z
    //   577: ifne -> 584
    //   580: iconst_1
    //   581: goto -> 585
    //   584: iconst_0
    //   585: ifeq -> 628
    //   588: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaj : I
    //   591: istore_3
    //   592: iload_3
    //   593: iconst_1
    //   594: iadd
    //   595: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaj : I
    //   598: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaj : I
    //   601: iconst_5
    //   602: if_icmplt -> 628
    //   605: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaai : Ljava/util/ArrayDeque;
    //   608: invokevirtual removeFirst : ()Ljava/lang/Object;
    //   611: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah
    //   614: astore_3
    //   615: aload_0
    //   616: aload_3
    //   617: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   620: aload_3
    //   621: invokespecial 你为什么要破解我的代码aaaaao : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah;)V
    //   624: iconst_0
    //   625: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaj : I
    //   628: lload #15
    //   630: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaar : J
    //   633: lsub
    //   634: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaas : J
    //   637: sipush #1000
    //   640: i2l
    //   641: lmul
    //   642: lcmp
    //   643: iflt -> 655
    //   646: aload_0
    //   647: invokespecial 你为什么要破解我的代码aaaaay : ()V
    //   650: lload #15
    //   652: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaar : J
    //   655: lload #15
    //   657: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaat : J
    //   660: lsub
    //   661: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaau : J
    //   664: lcmp
    //   665: iflt -> 677
    //   668: aload_0
    //   669: invokespecial 你为什么要破解我的代码aaaaap : ()V
    //   672: lload #15
    //   674: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaat : J
    //   677: return
    // Exception table:
    //   from	to	target	type
    //   258	527	530	java/lang/Exception
    //   259	278	281	java/lang/Exception
    //   337	358	361	java/lang/Exception
  }
  
  private final void 你为什么要破解我的代码aaaaao(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah) {
    class_1541 class_15411 = new class_1541((class_1937)paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaaa(), (paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaab()).field_1352, (paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaab()).field_1351, (paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaab()).field_1350, null);
    class_1541 class_15412 = class_15411;
    boolean bool = false;
    class_15412.method_6967(80);
    if ((paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaac().length() > 0)) {
      class_15412.method_5665((class_2561)class_2561.method_43470("§c" + paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaac()));
      class_15412.method_5880(true);
    } 
    class_1541 class_15413 = class_15411;
    paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah.你为什么要破解我的代码aaaaaa().method_8649((class_1297)class_15413);
  }
  
  private final void 你为什么要破解我的代码aaaaap() {
    // Byte code:
    //   0: invokestatic currentTimeMillis : ()J
    //   3: lstore_1
    //   4: aload_0
    //   5: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   8: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   11: astore_3
    //   12: nop
    //   13: aload_3
    //   14: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   17: dup
    //   18: ldc_w -1213267955
    //   21: i2c
    //   22: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   25: checkcast java/lang/String
    //   28: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   31: invokestatic toList : (Ljava/lang/Iterable;)Ljava/util/List;
    //   34: checkcast java/lang/Iterable
    //   37: astore #4
    //   39: iconst_0
    //   40: istore #5
    //   42: aload #4
    //   44: invokeinterface iterator : ()Ljava/util/Iterator;
    //   49: astore #6
    //   51: aload #6
    //   53: invokeinterface hasNext : ()Z
    //   58: ifeq -> 302
    //   61: aload #6
    //   63: invokeinterface next : ()Ljava/lang/Object;
    //   68: astore #7
    //   70: aload #7
    //   72: checkcast net/minecraft/class_1297
    //   75: astore #8
    //   77: iconst_0
    //   78: istore #9
    //   80: aload #8
    //   82: ifnull -> 297
    //   85: nop
    //   86: nop
    //   87: aload #8
    //   89: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   92: getstatic net/minecraft/class_1299.field_6052 : Lnet/minecraft/class_1299;
    //   95: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   98: ifeq -> 219
    //   101: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   104: astore #10
    //   106: aload #8
    //   108: invokevirtual method_5667 : ()Ljava/util/UUID;
    //   111: dup
    //   112: ldc_w 2009399320
    //   115: i2c
    //   116: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   119: checkcast java/lang/String
    //   122: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   125: astore #11
    //   127: iconst_0
    //   128: istore #12
    //   130: aload #10
    //   132: aload #11
    //   134: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   139: astore #13
    //   141: aload #13
    //   143: ifnonnull -> 172
    //   146: iconst_0
    //   147: istore #14
    //   149: lload_1
    //   150: invokestatic valueOf : (J)Ljava/lang/Long;
    //   153: astore #15
    //   155: aload #10
    //   157: aload #11
    //   159: aload #15
    //   161: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   166: pop
    //   167: aload #15
    //   169: goto -> 174
    //   172: aload #13
    //   174: nop
    //   175: checkcast java/lang/Number
    //   178: invokevirtual longValue : ()J
    //   181: lstore #16
    //   183: lload_1
    //   184: lload #16
    //   186: lsub
    //   187: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaav : J
    //   190: lcmp
    //   191: iflt -> 297
    //   194: aload #8
    //   196: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   199: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   202: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   205: aload #8
    //   207: invokevirtual method_5667 : ()Ljava/util/UUID;
    //   210: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   215: pop
    //   216: goto -> 297
    //   219: aload #8
    //   221: invokevirtual getClass : ()Ljava/lang/Class;
    //   224: invokevirtual getName : ()Ljava/lang/String;
    //   227: dup
    //   228: ldc_w 1614741518
    //   231: i2c
    //   232: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   235: checkcast java/lang/String
    //   238: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   241: checkcast java/lang/CharSequence
    //   244: ldc_w -1181024231
    //   247: i2c
    //   248: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   251: checkcast java/lang/String
    //   254: checkcast java/lang/CharSequence
    //   257: iconst_0
    //   258: iconst_2
    //   259: aconst_null
    //   260: invokestatic contains$default : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z
    //   263: ifeq -> 297
    //   266: aload #8
    //   268: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   271: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   274: goto -> 297
    //   277: astore #18
    //   279: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   282: aload #18
    //   284: invokevirtual getMessage : ()Ljava/lang/String;
    //   287: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   292: invokeinterface error : (Ljava/lang/String;)V
    //   297: nop
    //   298: nop
    //   299: goto -> 51
    //   302: nop
    //   303: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   306: invokeinterface entrySet : ()Ljava/util/Set;
    //   311: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaX
    //   314: dup
    //   315: aload_3
    //   316: invokespecial <init> : (Lnet/minecraft/class_3218;)V
    //   319: checkcast kotlin/jvm/functions/Function1
    //   322: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
    //   327: invokeinterface removeIf : (Ljava/util/function/Predicate;)Z
    //   332: pop
    //   333: goto -> 356
    //   336: astore #4
    //   338: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   341: aload #4
    //   343: invokevirtual getMessage : ()Ljava/lang/String;
    //   346: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   351: invokeinterface error : (Ljava/lang/String;)V
    //   356: return
    // Exception table:
    //   from	to	target	type
    //   12	333	336	java/lang/Exception
    //   85	274	277	java/lang/Exception
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaaq(@NotNull LiveEnterRoomEvent paramLiveEnterRoomEvent) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 166789132
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   17: dup
    //   18: ifnull -> 171
    //   21: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   24: dup
    //   25: ifnull -> 171
    //   28: invokevirtual method_14571 : ()Ljava/util/List;
    //   31: dup
    //   32: ifnull -> 171
    //   35: checkcast java/lang/Iterable
    //   38: astore_2
    //   39: iconst_0
    //   40: istore_3
    //   41: aload_2
    //   42: invokeinterface iterator : ()Ljava/util/Iterator;
    //   47: astore #4
    //   49: aload #4
    //   51: invokeinterface hasNext : ()Z
    //   56: ifeq -> 167
    //   59: aload #4
    //   61: invokeinterface next : ()Ljava/lang/Object;
    //   66: astore #5
    //   68: aload #5
    //   70: checkcast net/minecraft/class_3222
    //   73: astore #6
    //   75: iconst_0
    //   76: istore #7
    //   78: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   81: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   84: invokevirtual 你为什么要破解我的代码aaaaao : ()Z
    //   87: ifeq -> 97
    //   90: aload_1
    //   91: invokevirtual getUsername : ()Ljava/lang/String;
    //   94: goto -> 107
    //   97: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai;
    //   100: aload_1
    //   101: invokevirtual getUsername : ()Ljava/lang/String;
    //   104: invokevirtual 你为什么要破解我的代码aaaaaa : (Ljava/lang/String;)Ljava/lang/String;
    //   107: astore #8
    //   109: aload #6
    //   111: getfield field_13987 : Lnet/minecraft/class_3244;
    //   114: new net/minecraft/class_5894
    //   117: dup
    //   118: ldc_w 2004484122
    //   121: i2c
    //   122: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   125: checkcast java/lang/String
    //   128: iconst_1
    //   129: anewarray java/lang/Object
    //   132: astore #9
    //   134: aload #9
    //   136: iconst_0
    //   137: aload #8
    //   139: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   144: aastore
    //   145: aload #9
    //   147: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   150: checkcast net/minecraft/class_2561
    //   153: invokespecial <init> : (Lnet/minecraft/class_2561;)V
    //   156: checkcast net/minecraft/class_2596
    //   159: invokevirtual method_14364 : (Lnet/minecraft/class_2596;)V
    //   162: nop
    //   163: nop
    //   164: goto -> 49
    //   167: nop
    //   168: goto -> 173
    //   171: pop
    //   172: nop
    //   173: return
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaar(@NotNull LiveGiftEvent paramLiveGiftEvent) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 1604124684
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokevirtual getGiftCount : ()I
    //   18: istore_2
    //   19: aload_1
    //   20: invokevirtual getUsername : ()Ljava/lang/String;
    //   23: astore_3
    //   24: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaB : Ljava/util/Map;
    //   27: aload_3
    //   28: aload_1
    //   29: invokevirtual getAvatar : ()Ljava/lang/String;
    //   32: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   37: pop
    //   38: aload_3
    //   39: invokevirtual length : ()I
    //   42: iconst_2
    //   43: if_icmple -> 129
    //   46: aload_3
    //   47: iconst_0
    //   48: invokevirtual charAt : (I)C
    //   51: istore #4
    //   53: ldc_w -1519386597
    //   56: i2c
    //   57: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   60: checkcast java/lang/String
    //   63: checkcast java/lang/CharSequence
    //   66: aload_3
    //   67: invokevirtual length : ()I
    //   70: iconst_2
    //   71: isub
    //   72: invokestatic repeat : (Ljava/lang/CharSequence;I)Ljava/lang/String;
    //   75: astore #5
    //   77: new java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial <init> : ()V
    //   84: iload #4
    //   86: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   89: aload #5
    //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual toString : ()Ljava/lang/String;
    //   97: aload_3
    //   98: aload_3
    //   99: invokevirtual length : ()I
    //   102: iconst_1
    //   103: isub
    //   104: invokevirtual charAt : (I)C
    //   107: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;C)Ljava/lang/String;
    //   112: astore #6
    //   114: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaB : Ljava/util/Map;
    //   117: aload #6
    //   119: aload_1
    //   120: invokevirtual getAvatar : ()Ljava/lang/String;
    //   123: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   128: pop
    //   129: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   132: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaB : Ljava/util/Map;
    //   135: <illegal opcode> makeConcatWithConstants : (Ljava/util/Map;)Ljava/lang/String;
    //   140: invokeinterface info : (Ljava/lang/String;)V
    //   145: iload_2
    //   146: ifle -> 486
    //   149: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   152: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   155: invokestatic 你为什么要破解我的代码aaaaad : ()Lkotlin/enums/EnumEntries;
    //   158: checkcast java/lang/Iterable
    //   161: astore #4
    //   163: astore #7
    //   165: aload #4
    //   167: astore #5
    //   169: aload #5
    //   171: invokeinterface iterator : ()Ljava/util/Iterator;
    //   176: astore #8
    //   178: aload #8
    //   180: invokeinterface hasNext : ()Z
    //   185: ifeq -> 227
    //   188: aload #8
    //   190: invokeinterface next : ()Ljava/lang/Object;
    //   195: astore #9
    //   197: aload #9
    //   199: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF
    //   202: astore #10
    //   204: iconst_0
    //   205: istore #11
    //   207: aload #10
    //   209: invokevirtual 你为什么要破解我的代码aaaaaP : ()Ljava/lang/String;
    //   212: aload_1
    //   213: invokevirtual getLiveType : ()Ljava/lang/String;
    //   216: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   219: ifeq -> 178
    //   222: aload #9
    //   224: goto -> 228
    //   227: aconst_null
    //   228: aload #7
    //   230: swap
    //   231: dup
    //   232: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   235: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF
    //   238: invokevirtual 你为什么要破解我的代码aaaaac : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;)Ljava/util/List;
    //   241: astore #6
    //   243: aload #6
    //   245: invokeinterface isEmpty : ()Z
    //   250: ifeq -> 254
    //   253: return
    //   254: aload #6
    //   256: checkcast java/lang/Iterable
    //   259: astore #11
    //   261: aload #11
    //   263: invokeinterface iterator : ()Ljava/util/Iterator;
    //   268: astore #12
    //   270: aload #12
    //   272: invokeinterface hasNext : ()Z
    //   277: ifeq -> 321
    //   280: aload #12
    //   282: invokeinterface next : ()Ljava/lang/Object;
    //   287: astore #13
    //   289: aload #13
    //   291: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   294: astore #14
    //   296: iconst_0
    //   297: istore #15
    //   299: aload #14
    //   301: invokeinterface 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   306: aload_1
    //   307: invokevirtual getGiftName : ()Ljava/lang/String;
    //   310: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   313: ifeq -> 270
    //   316: aload #13
    //   318: goto -> 322
    //   321: aconst_null
    //   322: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   325: astore #9
    //   327: aload #9
    //   329: ifnull -> 342
    //   332: aload #9
    //   334: invokeinterface 你为什么要破解我的代码aaaaag : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabd;
    //   339: goto -> 343
    //   342: aconst_null
    //   343: astore #8
    //   345: aload #8
    //   347: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   350: ifeq -> 361
    //   353: aload #8
    //   355: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   358: goto -> 362
    //   361: aconst_null
    //   362: dup
    //   363: ifnonnull -> 368
    //   366: pop
    //   367: return
    //   368: astore #4
    //   370: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   373: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   376: invokevirtual 你为什么要破解我的代码aaaaao : ()Z
    //   379: ifeq -> 389
    //   382: aload_1
    //   383: invokevirtual getUsername : ()Ljava/lang/String;
    //   386: goto -> 399
    //   389: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai;
    //   392: aload_1
    //   393: invokevirtual getUsername : ()Ljava/lang/String;
    //   396: invokevirtual 你为什么要破解我的代码aaaaaa : (Ljava/lang/String;)Ljava/lang/String;
    //   399: astore #5
    //   401: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   404: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   407: aload #4
    //   409: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   412: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   417: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   420: aload_0
    //   421: aload_1
    //   422: invokevirtual getGiftName : ()Ljava/lang/String;
    //   425: aload #4
    //   427: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   430: iload_2
    //   431: imul
    //   432: aload #5
    //   434: aload #4
    //   436: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   439: invokespecial 你为什么要破解我的代码aaaaat : (Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   442: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   445: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   448: invokevirtual 你为什么要破解我的代码aaaaam : ()Z
    //   451: ifeq -> 468
    //   454: aload_0
    //   455: aload #5
    //   457: aload_1
    //   458: invokevirtual getGiftName : ()Ljava/lang/String;
    //   461: aload_1
    //   462: invokevirtual getGiftCount : ()I
    //   465: invokespecial 你为什么要破解我的代码aaaaau : (Ljava/lang/String;Ljava/lang/String;I)V
    //   468: aload_0
    //   469: aload #4
    //   471: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   474: aload #4
    //   476: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   479: iload_2
    //   480: imul
    //   481: aload #5
    //   483: invokespecial 你为什么要破解我的代码aaaaaB : (Ljava/lang/String;ILjava/lang/String;)V
    //   486: return
  }
  
  private final void 你为什么要破解我的代码aaaaas(class_3222 paramclass_3222, String paramString) {
    aaaaai aaaaai = new aaaaai(paramString);
    ServerPlayNetworking.send(paramclass_3222, (class_8710)aaaaai);
  }
  
  private final void 你为什么要破解我的代码aaaaat(String paramString1, int paramInt, String paramString2, String paramString3) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   6: invokevirtual 你为什么要破解我的代码aaaaai : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaF;
    //   9: astore #5
    //   11: aload #5
    //   13: invokevirtual 你为什么要破解我的代码aaaaam : ()Ljava/util/Map;
    //   16: aload #4
    //   18: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   23: checkcast java/lang/String
    //   26: dup
    //   27: ifnonnull -> 33
    //   30: pop
    //   31: aload #4
    //   33: astore #6
    //   35: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   38: dup
    //   39: ifnull -> 198
    //   42: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   45: dup
    //   46: ifnull -> 198
    //   49: invokevirtual method_14571 : ()Ljava/util/List;
    //   52: dup
    //   53: ifnull -> 198
    //   56: checkcast java/lang/Iterable
    //   59: astore #7
    //   61: iconst_0
    //   62: istore #8
    //   64: aload #7
    //   66: invokeinterface iterator : ()Ljava/util/Iterator;
    //   71: astore #9
    //   73: aload #9
    //   75: invokeinterface hasNext : ()Z
    //   80: ifeq -> 194
    //   83: aload #9
    //   85: invokeinterface next : ()Ljava/lang/Object;
    //   90: astore #10
    //   92: aload #10
    //   94: checkcast net/minecraft/class_3222
    //   97: astore #11
    //   99: iconst_0
    //   100: istore #12
    //   102: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   105: aload #11
    //   107: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   110: aload #11
    //   112: aload #6
    //   114: iload_2
    //   115: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   120: invokestatic method_43470 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   123: checkcast net/minecraft/class_2561
    //   126: ldc_w 1634336796
    //   129: i2c
    //   130: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   133: checkcast java/lang/String
    //   136: iconst_3
    //   137: anewarray java/lang/Object
    //   140: astore #13
    //   142: aload #13
    //   144: iconst_0
    //   145: aload_3
    //   146: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   151: aastore
    //   152: aload #13
    //   154: iconst_1
    //   155: aload_1
    //   156: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   161: aastore
    //   162: aload #13
    //   164: iconst_2
    //   165: iload_2
    //   166: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   171: aastore
    //   172: aload #13
    //   174: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   177: checkcast net/minecraft/class_2561
    //   180: bipush #10
    //   182: bipush #70
    //   184: bipush #20
    //   186: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   189: nop
    //   190: nop
    //   191: goto -> 73
    //   194: nop
    //   195: goto -> 200
    //   198: pop
    //   199: nop
    //   200: return
  }
  
  private final void 你为什么要破解我的代码aaaaau(String paramString1, String paramString2, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 126
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 126
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 126
    //   21: checkcast java/lang/Iterable
    //   24: astore #4
    //   26: iconst_0
    //   27: istore #5
    //   29: aload #4
    //   31: invokeinterface iterator : ()Ljava/util/Iterator;
    //   36: astore #6
    //   38: aload #6
    //   40: invokeinterface hasNext : ()Z
    //   45: ifeq -> 122
    //   48: aload #6
    //   50: invokeinterface next : ()Ljava/lang/Object;
    //   55: astore #7
    //   57: aload #7
    //   59: checkcast net/minecraft/class_3222
    //   62: astore #8
    //   64: iconst_0
    //   65: istore #9
    //   67: aload #8
    //   69: ldc_w -1899167715
    //   72: i2c
    //   73: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   76: checkcast java/lang/String
    //   79: iconst_2
    //   80: anewarray java/lang/Object
    //   83: astore #10
    //   85: aload #10
    //   87: iconst_0
    //   88: aload_1
    //   89: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   94: aastore
    //   95: aload #10
    //   97: iconst_1
    //   98: aload_2
    //   99: iload_3
    //   100: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   105: aastore
    //   106: aload #10
    //   108: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   111: checkcast net/minecraft/class_2561
    //   114: invokevirtual method_43496 : (Lnet/minecraft/class_2561;)V
    //   117: nop
    //   118: nop
    //   119: goto -> 38
    //   122: nop
    //   123: goto -> 128
    //   126: pop
    //   127: nop
    //   128: return
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaav(@NotNull LiveLikeEvent paramLiveLikeEvent) {
    Intrinsics.checkNotNullParameter(paramLiveLikeEvent, (String)aaaabe((char)797769740));
    aaaaaF aaaaaF = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaai();
    int i = aaaaaF.你为什么要破解我的代码aaaaaa();
    我草你怎么反编译我模组aaaaad += paramLiveLikeEvent.getLikeCount();
    while (我草你怎么反编译我模组aaaaad >= i) {
      aaaaaC aaaaaC = aaaaaF.你为什么要破解我的代码aaaaaQ();
      String str = 你为什么要破解我的代码aaaaaw(aaaaaC.你为什么要破解我的代码aaaaaa());
      你为什么要破解我的代码aaaaaB(aaaaaC.你为什么要破解我的代码aaaaaa(), aaaaaC.你为什么要破解我的代码aaaaab(), (String)aaaabe((char)75628574));
      你为什么要破解我的代码aaaaat(str, aaaaaC.你为什么要破解我的代码aaaaab(), (String)aaaabe((char)-1075707874), aaaaaC.你为什么要破解我的代码aaaaaa());
      我草你怎么反编译我模组aaaaad -= i;
    } 
  }
  
  private final String 你为什么要破解我的代码aaaaaw(String paramString) {
    // Byte code:
    //   0: invokestatic 你为什么要破解我的代码aaaaad : ()Lkotlin/enums/EnumEntries;
    //   3: invokeinterface iterator : ()Ljava/util/Iterator;
    //   8: astore_2
    //   9: aload_2
    //   10: invokeinterface hasNext : ()Z
    //   15: ifeq -> 154
    //   18: aload_2
    //   19: invokeinterface next : ()Ljava/lang/Object;
    //   24: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF
    //   27: astore_3
    //   28: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   31: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   34: aload_3
    //   35: invokevirtual 你为什么要破解我的代码aaaaac : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;)Ljava/util/List;
    //   38: astore #4
    //   40: aload #4
    //   42: checkcast java/lang/Iterable
    //   45: astore #5
    //   47: aload #5
    //   49: invokeinterface iterator : ()Ljava/util/Iterator;
    //   54: astore #6
    //   56: aload #6
    //   58: invokeinterface hasNext : ()Z
    //   63: ifeq -> 135
    //   66: aload #6
    //   68: invokeinterface next : ()Ljava/lang/Object;
    //   73: astore #7
    //   75: aload #7
    //   77: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   80: astore #8
    //   82: iconst_0
    //   83: istore #9
    //   85: aload #8
    //   87: invokeinterface 你为什么要破解我的代码aaaaag : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabd;
    //   92: astore #10
    //   94: aload #10
    //   96: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   99: ifeq -> 110
    //   102: aload #10
    //   104: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   107: goto -> 111
    //   110: aconst_null
    //   111: dup
    //   112: ifnull -> 121
    //   115: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   118: goto -> 123
    //   121: pop
    //   122: aconst_null
    //   123: aload_1
    //   124: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   127: ifeq -> 56
    //   130: aload #7
    //   132: goto -> 136
    //   135: aconst_null
    //   136: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   139: astore #11
    //   141: aload #11
    //   143: ifnull -> 9
    //   146: aload #11
    //   148: invokeinterface 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   153: areturn
    //   154: aload_1
    //   155: areturn
  }
  
  private final String 你为什么要破解我的代码aaaaax(String paramString) {
    return aaaaab.Companion.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaao() ? paramString : aaaaai.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(paramString);
  }
  
  private final void 你为什么要破解我的代码aaaaay() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaf : Ljava/util/List;
    //   3: checkcast java/util/Collection
    //   6: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   9: checkcast kotlin/random/Random
    //   12: invokestatic random : (Ljava/util/Collection;Lkotlin/random/Random;)Ljava/lang/Object;
    //   15: checkcast java/lang/String
    //   18: astore_1
    //   19: aload_0
    //   20: aload_1
    //   21: invokespecial 你为什么要破解我的代码aaaaax : (Ljava/lang/String;)Ljava/lang/String;
    //   24: astore_2
    //   25: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaal : Ljava/util/List;
    //   28: checkcast java/util/Collection
    //   31: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   34: checkcast kotlin/random/Random
    //   37: invokestatic random : (Ljava/util/Collection;Lkotlin/random/Random;)Ljava/lang/Object;
    //   40: checkcast java/lang/String
    //   43: astore_3
    //   44: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaq : Ljava/util/List;
    //   47: checkcast java/lang/Iterable
    //   50: astore #4
    //   52: aload #4
    //   54: invokeinterface iterator : ()Ljava/util/Iterator;
    //   59: astore #5
    //   61: aload #5
    //   63: invokeinterface hasNext : ()Z
    //   68: ifeq -> 107
    //   71: aload #5
    //   73: invokeinterface next : ()Ljava/lang/Object;
    //   78: astore #6
    //   80: aload #6
    //   82: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK
    //   85: astore #7
    //   87: iconst_0
    //   88: istore #8
    //   90: aload #7
    //   92: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   95: aload_3
    //   96: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   99: ifeq -> 61
    //   102: aload #6
    //   104: goto -> 108
    //   107: aconst_null
    //   108: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK
    //   111: astore #9
    //   113: aload #9
    //   115: ifnull -> 204
    //   118: aload_0
    //   119: aload_3
    //   120: invokespecial 你为什么要破解我的代码aaaaaz : (Ljava/lang/String;)I
    //   123: istore #10
    //   125: aload_0
    //   126: aload #9
    //   128: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   131: invokespecial 你为什么要破解我的代码aaaaaw : (Ljava/lang/String;)Ljava/lang/String;
    //   134: astore #4
    //   136: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   139: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   142: aload #9
    //   144: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   147: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   152: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   155: aload_0
    //   156: aload #4
    //   158: iload #10
    //   160: aload_2
    //   161: aload #9
    //   163: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   166: invokespecial 你为什么要破解我的代码aaaaat : (Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   169: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   172: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   175: invokevirtual 你为什么要破解我的代码aaaaam : ()Z
    //   178: ifeq -> 189
    //   181: aload_0
    //   182: aload_2
    //   183: aload #4
    //   185: iconst_1
    //   186: invokespecial 你为什么要破解我的代码aaaaau : (Ljava/lang/String;Ljava/lang/String;I)V
    //   189: aload_0
    //   190: aload #9
    //   192: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   195: iload #10
    //   197: aload_2
    //   198: invokespecial 你为什么要破解我的代码aaaaaB : (Ljava/lang/String;ILjava/lang/String;)V
    //   201: goto -> 218
    //   204: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   207: aload_3
    //   208: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   213: invokeinterface warn : (Ljava/lang/String;)V
    //   218: return
  }
  
  private final int 你为什么要破解我的代码aaaaaz(String paramString) {
    // Byte code:
    //   0: invokestatic 你为什么要破解我的代码aaaaad : ()Lkotlin/enums/EnumEntries;
    //   3: invokeinterface iterator : ()Ljava/util/Iterator;
    //   8: astore_2
    //   9: aload_2
    //   10: invokeinterface hasNext : ()Z
    //   15: ifeq -> 189
    //   18: aload_2
    //   19: invokeinterface next : ()Ljava/lang/Object;
    //   24: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF
    //   27: astore_3
    //   28: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   31: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   34: aload_3
    //   35: invokevirtual 你为什么要破解我的代码aaaaac : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;)Ljava/util/List;
    //   38: astore #4
    //   40: aload #4
    //   42: checkcast java/lang/Iterable
    //   45: astore #5
    //   47: aload #5
    //   49: invokeinterface iterator : ()Ljava/util/Iterator;
    //   54: astore #6
    //   56: aload #6
    //   58: invokeinterface hasNext : ()Z
    //   63: ifeq -> 135
    //   66: aload #6
    //   68: invokeinterface next : ()Ljava/lang/Object;
    //   73: astore #7
    //   75: aload #7
    //   77: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   80: astore #8
    //   82: iconst_0
    //   83: istore #9
    //   85: aload #8
    //   87: invokeinterface 你为什么要破解我的代码aaaaag : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabd;
    //   92: astore #10
    //   94: aload #10
    //   96: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   99: ifeq -> 110
    //   102: aload #10
    //   104: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   107: goto -> 111
    //   110: aconst_null
    //   111: dup
    //   112: ifnull -> 121
    //   115: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   118: goto -> 123
    //   121: pop
    //   122: aconst_null
    //   123: aload_1
    //   124: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   127: ifeq -> 56
    //   130: aload #7
    //   132: goto -> 136
    //   135: aconst_null
    //   136: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   139: astore #11
    //   141: aload #11
    //   143: ifnull -> 156
    //   146: aload #11
    //   148: invokeinterface 你为什么要破解我的代码aaaaag : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabd;
    //   153: goto -> 157
    //   156: aconst_null
    //   157: astore #12
    //   159: aload #12
    //   161: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   164: ifeq -> 175
    //   167: aload #12
    //   169: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabr
    //   172: goto -> 176
    //   175: aconst_null
    //   176: astore #13
    //   178: aload #13
    //   180: ifnull -> 9
    //   183: aload #13
    //   185: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   188: ireturn
    //   189: iconst_1
    //   190: ireturn
  }
  
  private final void 你为什么要破解我的代码aaaaaA() {
    // Byte code:
    //   0: invokestatic currentTimeMillis : ()J
    //   3: lstore_1
    //   4: aload_0
    //   5: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   8: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   11: astore_3
    //   12: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   15: ldc_w 1748566047
    //   18: i2c
    //   19: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: invokeinterface info : (Ljava/lang/String;)V
    //   30: aload_3
    //   31: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   34: dup
    //   35: ldc_w -1107558387
    //   38: i2c
    //   39: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   42: checkcast java/lang/String
    //   45: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   48: invokestatic count : (Ljava/lang/Iterable;)I
    //   51: istore #4
    //   53: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   56: checkcast java/util/Map
    //   59: astore #5
    //   61: iconst_0
    //   62: istore #6
    //   64: aload #5
    //   66: invokeinterface isEmpty : ()Z
    //   71: ifeq -> 78
    //   74: iconst_0
    //   75: goto -> 190
    //   78: iconst_0
    //   79: istore #7
    //   81: aload #5
    //   83: invokeinterface entrySet : ()Ljava/util/Set;
    //   88: invokeinterface iterator : ()Ljava/util/Iterator;
    //   93: astore #8
    //   95: aload #8
    //   97: invokeinterface hasNext : ()Z
    //   102: ifeq -> 188
    //   105: aload #8
    //   107: invokeinterface next : ()Ljava/lang/Object;
    //   112: checkcast java/util/Map$Entry
    //   115: astore #9
    //   117: aload #9
    //   119: astore #10
    //   121: iconst_0
    //   122: istore #11
    //   124: aload #10
    //   126: invokeinterface getKey : ()Ljava/lang/Object;
    //   131: checkcast java/util/UUID
    //   134: astore #12
    //   136: aload_3
    //   137: aload #12
    //   139: invokevirtual method_14190 : (Ljava/util/UUID;)Lnet/minecraft/class_1297;
    //   142: astore #13
    //   144: aload #13
    //   146: ifnull -> 177
    //   149: aload #13
    //   151: instanceof net/minecraft/class_1510
    //   154: ifne -> 177
    //   157: aload #13
    //   159: instanceof net/minecraft/class_1528
    //   162: ifne -> 177
    //   165: aload #13
    //   167: instanceof net/minecraft/class_1308
    //   170: ifeq -> 177
    //   173: iconst_1
    //   174: goto -> 178
    //   177: iconst_0
    //   178: nop
    //   179: ifeq -> 95
    //   182: iinc #7, 1
    //   185: goto -> 95
    //   188: iload #7
    //   190: istore #14
    //   192: iload #14
    //   194: sipush #500
    //   197: if_icmple -> 1038
    //   200: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   203: ldc_w 270336032
    //   206: i2c
    //   207: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   210: checkcast java/lang/String
    //   213: invokeinterface info : (Ljava/lang/String;)V
    //   218: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   221: invokevirtual entrySet : ()Ljava/util/Set;
    //   224: dup
    //   225: ldc_w -1656946655
    //   228: i2c
    //   229: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   232: checkcast java/lang/String
    //   235: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   238: checkcast java/lang/Iterable
    //   241: astore #6
    //   243: nop
    //   244: iconst_0
    //   245: istore #7
    //   247: aload #6
    //   249: astore #8
    //   251: new java/util/ArrayList
    //   254: dup
    //   255: invokespecial <init> : ()V
    //   258: checkcast java/util/Collection
    //   261: astore #9
    //   263: iconst_0
    //   264: istore #10
    //   266: aload #8
    //   268: invokeinterface iterator : ()Ljava/util/Iterator;
    //   273: astore #11
    //   275: aload #11
    //   277: invokeinterface hasNext : ()Z
    //   282: ifeq -> 489
    //   285: aload #11
    //   287: invokeinterface next : ()Ljava/lang/Object;
    //   292: astore #12
    //   294: aload #12
    //   296: checkcast java/util/Map$Entry
    //   299: astore #13
    //   301: iconst_0
    //   302: istore #15
    //   304: aload #13
    //   306: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   309: aload #13
    //   311: invokeinterface getKey : ()Ljava/lang/Object;
    //   316: checkcast java/util/UUID
    //   319: astore #16
    //   321: aload #13
    //   323: invokeinterface getValue : ()Ljava/lang/Object;
    //   328: checkcast java/lang/Long
    //   331: astore #17
    //   333: aload_3
    //   334: aload #16
    //   336: invokevirtual method_14190 : (Ljava/util/UUID;)Lnet/minecraft/class_1297;
    //   339: astore #18
    //   341: lload_1
    //   342: aload #17
    //   344: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   347: aload #17
    //   349: invokevirtual longValue : ()J
    //   352: lsub
    //   353: ldc2_w 600000
    //   356: lcmp
    //   357: iflt -> 364
    //   360: iconst_1
    //   361: goto -> 365
    //   364: iconst_0
    //   365: istore #19
    //   367: iload #19
    //   369: ifeq -> 433
    //   372: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   375: aload #16
    //   377: lload_1
    //   378: aload #17
    //   380: invokevirtual longValue : ()J
    //   383: lsub
    //   384: sipush #1000
    //   387: i2l
    //   388: ldiv
    //   389: aload #18
    //   391: dup
    //   392: ifnull -> 409
    //   395: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   398: dup
    //   399: ifnull -> 409
    //   402: invokevirtual toString : ()Ljava/lang/String;
    //   405: dup
    //   406: ifnonnull -> 420
    //   409: pop
    //   410: ldc_w -1643642846
    //   413: i2c
    //   414: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   417: checkcast java/lang/String
    //   420: <illegal opcode> makeConcatWithConstants : (Ljava/util/UUID;JLjava/lang/String;)Ljava/lang/String;
    //   425: invokestatic trimIndent : (Ljava/lang/String;)Ljava/lang/String;
    //   428: invokeinterface info : (Ljava/lang/String;)V
    //   433: aload #18
    //   435: ifnull -> 471
    //   438: aload #18
    //   440: instanceof net/minecraft/class_1510
    //   443: ifne -> 471
    //   446: aload #18
    //   448: instanceof net/minecraft/class_1528
    //   451: ifne -> 471
    //   454: aload #18
    //   456: instanceof net/minecraft/class_1308
    //   459: ifeq -> 471
    //   462: iload #19
    //   464: ifeq -> 471
    //   467: iconst_1
    //   468: goto -> 472
    //   471: iconst_0
    //   472: nop
    //   473: ifeq -> 275
    //   476: aload #9
    //   478: aload #12
    //   480: invokeinterface add : (Ljava/lang/Object;)Z
    //   485: pop
    //   486: goto -> 275
    //   489: aload #9
    //   491: checkcast java/util/List
    //   494: nop
    //   495: checkcast java/lang/Iterable
    //   498: invokestatic toList : (Ljava/lang/Iterable;)Ljava/util/List;
    //   501: astore #5
    //   503: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   506: aload #5
    //   508: invokeinterface size : ()I
    //   513: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   518: invokeinterface info : (Ljava/lang/String;)V
    //   523: iconst_5
    //   524: iload #14
    //   526: sipush #500
    //   529: isub
    //   530: bipush #100
    //   532: idiv
    //   533: iadd
    //   534: istore #6
    //   536: iload #6
    //   538: aload #5
    //   540: invokeinterface size : ()I
    //   545: invokestatic coerceAtMost : (II)I
    //   548: iload #14
    //   550: sipush #500
    //   553: isub
    //   554: invokestatic coerceAtMost : (II)I
    //   557: istore #7
    //   559: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   562: iload #14
    //   564: sipush #500
    //   567: isub
    //   568: bipush #100
    //   570: idiv
    //   571: iload #7
    //   573: <illegal opcode> makeConcatWithConstants : (II)Ljava/lang/String;
    //   578: invokestatic trimIndent : (Ljava/lang/String;)Ljava/lang/String;
    //   581: invokeinterface info : (Ljava/lang/String;)V
    //   586: iconst_0
    //   587: istore #8
    //   589: aload #5
    //   591: checkcast java/lang/Iterable
    //   594: iload #7
    //   596: invokestatic take : (Ljava/lang/Iterable;I)Ljava/util/List;
    //   599: checkcast java/lang/Iterable
    //   602: astore #9
    //   604: iconst_0
    //   605: istore #10
    //   607: aload #9
    //   609: invokeinterface iterator : ()Ljava/util/Iterator;
    //   614: astore #11
    //   616: aload #11
    //   618: invokeinterface hasNext : ()Z
    //   623: ifeq -> 867
    //   626: aload #11
    //   628: invokeinterface next : ()Ljava/lang/Object;
    //   633: astore #12
    //   635: aload #12
    //   637: checkcast java/util/Map$Entry
    //   640: astore #13
    //   642: iconst_0
    //   643: istore #15
    //   645: aload #13
    //   647: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   650: aload #13
    //   652: invokeinterface getKey : ()Ljava/lang/Object;
    //   657: checkcast java/util/UUID
    //   660: astore #16
    //   662: aload #13
    //   664: invokeinterface getValue : ()Ljava/lang/Object;
    //   669: checkcast java/lang/Long
    //   672: astore #17
    //   674: aload_3
    //   675: aload #16
    //   677: invokevirtual method_14190 : (Ljava/util/UUID;)Lnet/minecraft/class_1297;
    //   680: dup
    //   681: ifnull -> 860
    //   684: astore #18
    //   686: iconst_0
    //   687: istore #19
    //   689: aload #18
    //   691: instanceof net/minecraft/class_1308
    //   694: ifeq -> 856
    //   697: aload #18
    //   699: instanceof net/minecraft/class_1510
    //   702: ifne -> 856
    //   705: aload #18
    //   707: instanceof net/minecraft/class_1528
    //   710: ifne -> 856
    //   713: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   716: aload #16
    //   718: aload #18
    //   720: checkcast net/minecraft/class_1308
    //   723: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   726: aload #18
    //   728: checkcast net/minecraft/class_1308
    //   731: invokevirtual method_5797 : ()Lnet/minecraft/class_2561;
    //   734: dup
    //   735: ifnull -> 746
    //   738: invokeinterface getString : ()Ljava/lang/String;
    //   743: goto -> 748
    //   746: pop
    //   747: aconst_null
    //   748: astore #20
    //   750: aload #20
    //   752: dup
    //   753: ifnonnull -> 770
    //   756: pop
    //   757: ldc_w -360316893
    //   760: i2c
    //   761: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   764: checkcast java/lang/String
    //   767: goto -> 775
    //   770: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   773: aload #20
    //   775: lload_1
    //   776: aload #17
    //   778: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   781: aload #17
    //   783: invokevirtual longValue : ()J
    //   786: lsub
    //   787: sipush #1000
    //   790: i2l
    //   791: ldiv
    //   792: aload #18
    //   794: checkcast net/minecraft/class_1308
    //   797: invokevirtual method_23317 : ()D
    //   800: aload #18
    //   802: checkcast net/minecraft/class_1308
    //   805: invokevirtual method_23318 : ()D
    //   808: aload #18
    //   810: checkcast net/minecraft/class_1308
    //   813: invokevirtual method_23321 : ()D
    //   816: <illegal opcode> makeConcatWithConstants : (Ljava/util/UUID;Lnet/minecraft/class_1299;Ljava/lang/String;JDDD)Ljava/lang/String;
    //   821: invokestatic trimIndent : (Ljava/lang/String;)Ljava/lang/String;
    //   824: invokeinterface info : (Ljava/lang/String;)V
    //   829: aload #18
    //   831: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   834: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   837: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   840: pop
    //   841: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   844: aload #16
    //   846: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   849: pop
    //   850: iload #8
    //   852: iconst_1
    //   853: iadd
    //   854: istore #8
    //   856: nop
    //   857: goto -> 862
    //   860: pop
    //   861: nop
    //   862: nop
    //   863: nop
    //   864: goto -> 616
    //   867: nop
    //   868: aload_3
    //   869: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   872: dup
    //   873: ldc_w -1784086515
    //   876: i2c
    //   877: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   880: checkcast java/lang/String
    //   883: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   886: astore #10
    //   888: iconst_0
    //   889: istore #11
    //   891: aload #10
    //   893: instanceof java/util/Collection
    //   896: ifeq -> 916
    //   899: aload #10
    //   901: checkcast java/util/Collection
    //   904: invokeinterface isEmpty : ()Z
    //   909: ifeq -> 916
    //   912: iconst_0
    //   913: goto -> 1005
    //   916: iconst_0
    //   917: istore #12
    //   919: aload #10
    //   921: invokeinterface iterator : ()Ljava/util/Iterator;
    //   926: astore #13
    //   928: aload #13
    //   930: invokeinterface hasNext : ()Z
    //   935: ifeq -> 1003
    //   938: aload #13
    //   940: invokeinterface next : ()Ljava/lang/Object;
    //   945: astore #15
    //   947: aload #15
    //   949: checkcast net/minecraft/class_1297
    //   952: astore #16
    //   954: iconst_0
    //   955: istore #17
    //   957: aload #16
    //   959: instanceof net/minecraft/class_1308
    //   962: ifeq -> 985
    //   965: aload #16
    //   967: instanceof net/minecraft/class_1510
    //   970: ifne -> 985
    //   973: aload #16
    //   975: instanceof net/minecraft/class_1528
    //   978: ifne -> 985
    //   981: iconst_1
    //   982: goto -> 986
    //   985: iconst_0
    //   986: ifeq -> 928
    //   989: iinc #12, 1
    //   992: iload #12
    //   994: ifge -> 928
    //   997: invokestatic throwCountOverflow : ()V
    //   1000: goto -> 928
    //   1003: iload #12
    //   1005: istore #9
    //   1007: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   1010: iload #8
    //   1012: iload #9
    //   1014: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   1017: invokevirtual size : ()I
    //   1020: invokestatic currentTimeMillis : ()J
    //   1023: lload_1
    //   1024: lsub
    //   1025: <illegal opcode> makeConcatWithConstants : (IIIJ)Ljava/lang/String;
    //   1030: invokestatic trimIndent : (Ljava/lang/String;)Ljava/lang/String;
    //   1033: invokeinterface info : (Ljava/lang/String;)V
    //   1038: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   1041: invokevirtual size : ()I
    //   1044: istore #5
    //   1046: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   1049: invokevirtual entrySet : ()Ljava/util/Set;
    //   1052: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaar
    //   1055: dup
    //   1056: aload_3
    //   1057: invokespecial <init> : (Lnet/minecraft/class_3218;)V
    //   1060: checkcast kotlin/jvm/functions/Function1
    //   1063: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
    //   1068: invokeinterface removeIf : (Ljava/util/function/Predicate;)Z
    //   1073: pop
    //   1074: iload #5
    //   1076: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaam : Ljava/util/concurrent/ConcurrentHashMap;
    //   1079: invokevirtual size : ()I
    //   1082: isub
    //   1083: istore #6
    //   1085: iload #6
    //   1087: ifle -> 1105
    //   1090: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   1093: iload #6
    //   1095: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   1100: invokeinterface info : (Ljava/lang/String;)V
    //   1105: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   1108: ldc_w 576258084
    //   1111: i2c
    //   1112: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   1115: checkcast java/lang/String
    //   1118: invokeinterface info : (Ljava/lang/String;)V
    //   1123: return
  }
  
  private final void 你为什么要破解我的代码aaaaaB(String paramString1, int paramInt, String paramString2) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaq : Ljava/util/List;
    //   3: checkcast java/lang/Iterable
    //   6: astore #4
    //   8: aload #4
    //   10: invokeinterface iterator : ()Ljava/util/Iterator;
    //   15: astore #5
    //   17: aload #5
    //   19: invokeinterface hasNext : ()Z
    //   24: ifeq -> 63
    //   27: aload #5
    //   29: invokeinterface next : ()Ljava/lang/Object;
    //   34: astore #6
    //   36: aload #6
    //   38: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK
    //   41: astore #7
    //   43: iconst_0
    //   44: istore #8
    //   46: aload #7
    //   48: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   51: aload_1
    //   52: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   55: ifeq -> 17
    //   58: aload #6
    //   60: goto -> 64
    //   63: aconst_null
    //   64: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK
    //   67: astore #9
    //   69: aload #9
    //   71: ifnull -> 85
    //   74: aload_0
    //   75: aload #9
    //   77: iload_2
    //   78: aload_3
    //   79: invokespecial 你为什么要破解我的代码aaaaaC : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK;ILjava/lang/String;)V
    //   82: goto -> 99
    //   85: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   88: aload_1
    //   89: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   94: invokeinterface warn : (Ljava/lang/String;)V
    //   99: return
  }
  
  private final void 你为什么要破解我的代码aaaaaC(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK, int paramInt, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   4: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   7: dup
    //   8: ldc_w 1055195173
    //   11: i2c
    //   12: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   15: checkcast java/lang/String
    //   18: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   21: astore #4
    //   23: iconst_0
    //   24: istore #5
    //   26: iload #5
    //   28: iload_2
    //   29: if_icmpge -> 521
    //   32: iload #5
    //   34: istore #6
    //   36: iconst_0
    //   37: istore #7
    //   39: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   42: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   45: invokespecial 你为什么要破解我的代码aaaaaF : (Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
    //   48: astore #8
    //   50: nop
    //   51: aload_1
    //   52: invokevirtual 你为什么要破解我的代码aaaaag : ()Lkotlin/jvm/functions/Function3;
    //   55: ifnull -> 100
    //   58: aload_1
    //   59: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   62: ldc_w 511246374
    //   65: i2c
    //   66: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   69: checkcast java/lang/String
    //   72: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   75: ifeq -> 100
    //   78: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaai : Ljava/util/ArrayDeque;
    //   81: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah
    //   84: dup
    //   85: aload #4
    //   87: aload #8
    //   89: aload_3
    //   90: invokespecial <init> : (Lnet/minecraft/class_3218;Lnet/minecraft/class_243;Ljava/lang/String;)V
    //   93: invokevirtual add : (Ljava/lang/Object;)Z
    //   96: pop
    //   97: goto -> 514
    //   100: aload_1
    //   101: invokevirtual 你为什么要破解我的代码aaaaag : ()Lkotlin/jvm/functions/Function3;
    //   104: ifnull -> 225
    //   107: nop
    //   108: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   111: aload_1
    //   112: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   115: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   120: invokeinterface info : (Ljava/lang/String;)V
    //   125: aload_1
    //   126: invokevirtual 你为什么要破解我的代码aaaaag : ()Lkotlin/jvm/functions/Function3;
    //   129: aload #4
    //   131: aload #8
    //   133: aload_3
    //   134: invokeinterface invoke : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   139: checkcast net/minecraft/class_1308
    //   142: astore #9
    //   144: aload #9
    //   146: ifnull -> 178
    //   149: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   152: aload #9
    //   154: aload_3
    //   155: invokespecial 你为什么要破解我的代码aaaaaN : (Lnet/minecraft/class_1308;Ljava/lang/String;)V
    //   158: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   161: aload_1
    //   162: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   165: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   170: invokeinterface info : (Ljava/lang/String;)V
    //   175: goto -> 514
    //   178: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   181: aload_1
    //   182: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   185: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   190: invokeinterface warn : (Ljava/lang/String;)V
    //   195: goto -> 514
    //   198: astore #9
    //   200: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   203: aload_1
    //   204: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   207: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   212: aload #9
    //   214: checkcast java/lang/Throwable
    //   217: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   222: goto -> 514
    //   225: aload_1
    //   226: invokevirtual 你为什么要破解我的代码aaaaaf : ()Ljava/lang/String;
    //   229: ifnull -> 342
    //   232: nop
    //   233: aload_1
    //   234: invokevirtual 你为什么要破解我的代码aaaaaf : ()Ljava/lang/String;
    //   237: aload #8
    //   239: getfield field_1352 : D
    //   242: aload #8
    //   244: getfield field_1351 : D
    //   247: aload #8
    //   249: getfield field_1350 : D
    //   252: aload_3
    //   253: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;DDDLjava/lang/String;)Ljava/lang/String;
    //   258: astore #9
    //   260: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   263: aload #9
    //   265: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   270: invokeinterface info : (Ljava/lang/String;)V
    //   275: aload #4
    //   277: invokevirtual method_8503 : ()Lnet/minecraft/server/MinecraftServer;
    //   280: invokevirtual method_3734 : ()Lnet/minecraft/class_2170;
    //   283: aload #4
    //   285: invokevirtual method_8503 : ()Lnet/minecraft/server/MinecraftServer;
    //   288: invokevirtual method_3739 : ()Lnet/minecraft/class_2168;
    //   291: aload #9
    //   293: invokevirtual method_44252 : (Lnet/minecraft/class_2168;Ljava/lang/String;)V
    //   296: aload #4
    //   298: invokevirtual method_8503 : ()Lnet/minecraft/server/MinecraftServer;
    //   301: aload #4
    //   303: aload_3
    //   304: <illegal opcode> run : (Lnet/minecraft/class_3218;Ljava/lang/String;)Ljava/lang/Runnable;
    //   309: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   312: goto -> 514
    //   315: astore #9
    //   317: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   320: aload_1
    //   321: invokevirtual 你为什么要破解我的代码aaaaaf : ()Ljava/lang/String;
    //   324: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   329: aload #9
    //   331: checkcast java/lang/Throwable
    //   334: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   339: goto -> 514
    //   342: aload_1
    //   343: invokevirtual 你为什么要破解我的代码aaaaac : ()Lnet/minecraft/class_1299;
    //   346: ifnull -> 514
    //   349: aload_1
    //   350: invokevirtual 你为什么要破解我的代码aaaaac : ()Lnet/minecraft/class_1299;
    //   353: aload #4
    //   355: aload #8
    //   357: checkcast net/minecraft/class_2374
    //   360: invokestatic method_49638 : (Lnet/minecraft/class_2374;)Lnet/minecraft/class_2338;
    //   363: getstatic net/minecraft/class_3730.field_16462 : Lnet/minecraft/class_3730;
    //   366: invokevirtual method_47821 : (Lnet/minecraft/class_3218;Lnet/minecraft/class_2338;Lnet/minecraft/class_3730;)Lnet/minecraft/class_1297;
    //   369: checkcast net/minecraft/class_1308
    //   372: astore #9
    //   374: aload #9
    //   376: ifnull -> 514
    //   379: aload #9
    //   381: aload #8
    //   383: getfield field_1352 : D
    //   386: aload #8
    //   388: getfield field_1351 : D
    //   391: aload #8
    //   393: getfield field_1350 : D
    //   396: fconst_0
    //   397: fconst_0
    //   398: invokevirtual method_5808 : (DDDFF)V
    //   401: aload #9
    //   403: aload_3
    //   404: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   409: invokestatic method_43470 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   412: checkcast net/minecraft/class_2561
    //   415: invokevirtual method_5665 : (Lnet/minecraft/class_2561;)V
    //   418: aload #9
    //   420: iconst_1
    //   421: invokevirtual method_5880 : (Z)V
    //   424: aload #9
    //   426: instanceof net/minecraft/class_1510
    //   429: ifeq -> 443
    //   432: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   435: aload #9
    //   437: checkcast net/minecraft/class_1510
    //   440: invokespecial 你为什么要破解我的代码aaaaaO : (Lnet/minecraft/class_1510;)V
    //   443: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   446: aload #9
    //   448: aload_3
    //   449: invokespecial 你为什么要破解我的代码aaaaaN : (Lnet/minecraft/class_1308;Ljava/lang/String;)V
    //   452: aload #9
    //   454: instanceof net/minecraft/class_1642
    //   457: ifeq -> 514
    //   460: aload_1
    //   461: invokevirtual 你为什么要破解我的代码aaaaad : ()Z
    //   464: ifne -> 474
    //   467: aload_1
    //   468: invokevirtual 你为什么要破解我的代码aaaaae : ()Z
    //   471: ifeq -> 514
    //   474: nop
    //   475: aload_1
    //   476: invokevirtual 你为什么要破解我的代码aaaaad : ()Z
    //   479: ifeq -> 496
    //   482: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   485: aload #9
    //   487: checkcast net/minecraft/class_1642
    //   490: invokespecial 你为什么要破解我的代码aaaaaD : (Lnet/minecraft/class_1642;)V
    //   493: goto -> 514
    //   496: aload_1
    //   497: invokevirtual 你为什么要破解我的代码aaaaae : ()Z
    //   500: ifeq -> 514
    //   503: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   506: aload #9
    //   508: checkcast net/minecraft/class_1642
    //   511: invokespecial 你为什么要破解我的代码aaaaaE : (Lnet/minecraft/class_1642;)V
    //   514: nop
    //   515: iinc #5, 1
    //   518: goto -> 26
    //   521: return
    // Exception table:
    //   from	to	target	type
    //   107	195	198	java/lang/Exception
    //   232	312	315	java/lang/Exception
  }
  
  private final void 你为什么要破解我的代码aaaaaD(class_1642 paramclass_1642) {
    class_1642 class_16421 = paramclass_1642;
    class_1642 class_16422 = class_16421;
    boolean bool = false;
    class_16422.method_5673(class_1304.field_6169, new class_1799((class_1935)class_1802.field_8862));
    class_16422.method_5673(class_1304.field_6174, new class_1799((class_1935)class_1802.field_8678));
    class_16422.method_5673(class_1304.field_6172, new class_1799((class_1935)class_1802.field_8416));
    class_16422.method_5673(class_1304.field_6166, new class_1799((class_1935)class_1802.field_8753));
    class_16422.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_8845));
    class_16422.method_5946(class_1304.field_6169, 0.0F);
    class_16422.method_5946(class_1304.field_6174, 0.0F);
    class_16422.method_5946(class_1304.field_6172, 0.0F);
    class_16422.method_5946(class_1304.field_6166, 0.0F);
    class_16422.method_5946(class_1304.field_6173, 0.0F);
  }
  
  private final void 你为什么要破解我的代码aaaaaE(class_1642 paramclass_1642) {
    class_1642 class_16421 = paramclass_1642;
    class_1642 class_16422 = class_16421;
    boolean bool = false;
    class_16422.method_5673(class_1304.field_6169, new class_1799((class_1935)class_1802.field_8805));
    class_16422.method_5673(class_1304.field_6174, new class_1799((class_1935)class_1802.field_8058));
    class_16422.method_5673(class_1304.field_6172, new class_1799((class_1935)class_1802.field_8348));
    class_16422.method_5673(class_1304.field_6166, new class_1799((class_1935)class_1802.field_8285));
    class_16422.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_8802));
    class_16422.method_5946(class_1304.field_6169, 0.0F);
    class_16422.method_5946(class_1304.field_6174, 0.0F);
    class_16422.method_5946(class_1304.field_6172, 0.0F);
    class_16422.method_5946(class_1304.field_6166, 0.0F);
    class_16422.method_5946(class_1304.field_6173, 0.0F);
  }
  
  private final class_243 你为什么要破解我的代码aaaaaF(class_243 paramclass_243) {
    Random random = new Random();
    double d1 = -30.0D + random.nextDouble() * 60.0D;
    double d2 = -30.0D + random.nextDouble() * 60.0D;
    return new class_243(paramclass_243.field_1352 + d1, paramclass_243.field_1351, paramclass_243.field_1350 + d2);
  }
  
  public final void 你为什么要破解我的代码aaaaaG() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaai : Ljava/util/ArrayDeque;
    //   3: invokevirtual clear : ()V
    //   6: iconst_0
    //   7: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaj : I
    //   10: aload_0
    //   11: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   14: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   17: astore_1
    //   18: aload_0
    //   19: invokespecial 你为什么要破解我的代码aaaaaR : ()V
    //   22: aload_1
    //   23: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   26: dup
    //   27: ldc_w -1630601203
    //   30: i2c
    //   31: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   34: checkcast java/lang/String
    //   37: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   40: astore_2
    //   41: nop
    //   42: iconst_0
    //   43: istore_3
    //   44: aload_2
    //   45: astore #4
    //   47: new java/util/ArrayList
    //   50: dup
    //   51: invokespecial <init> : ()V
    //   54: checkcast java/util/Collection
    //   57: astore #5
    //   59: iconst_0
    //   60: istore #6
    //   62: aload #4
    //   64: invokeinterface iterator : ()Ljava/util/Iterator;
    //   69: astore #7
    //   71: aload #7
    //   73: invokeinterface hasNext : ()Z
    //   78: ifeq -> 111
    //   81: aload #7
    //   83: invokeinterface next : ()Ljava/lang/Object;
    //   88: astore #8
    //   90: aload #8
    //   92: instanceof net/minecraft/class_1308
    //   95: ifeq -> 71
    //   98: aload #5
    //   100: aload #8
    //   102: invokeinterface add : (Ljava/lang/Object;)Z
    //   107: pop
    //   108: goto -> 71
    //   111: aload #5
    //   113: checkcast java/util/List
    //   116: nop
    //   117: checkcast java/lang/Iterable
    //   120: astore_2
    //   121: nop
    //   122: iconst_0
    //   123: istore_3
    //   124: aload_2
    //   125: invokeinterface iterator : ()Ljava/util/Iterator;
    //   130: astore #4
    //   132: aload #4
    //   134: invokeinterface hasNext : ()Z
    //   139: ifeq -> 174
    //   142: aload #4
    //   144: invokeinterface next : ()Ljava/lang/Object;
    //   149: astore #5
    //   151: aload #5
    //   153: checkcast net/minecraft/class_1308
    //   156: astore #6
    //   158: iconst_0
    //   159: istore #7
    //   161: aload #6
    //   163: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   166: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   169: nop
    //   170: nop
    //   171: goto -> 132
    //   174: nop
    //   175: aload_1
    //   176: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   179: dup
    //   180: ldc_w -1361510387
    //   183: i2c
    //   184: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   187: checkcast java/lang/String
    //   190: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   193: astore_2
    //   194: nop
    //   195: iconst_0
    //   196: istore_3
    //   197: aload_2
    //   198: astore #4
    //   200: new java/util/ArrayList
    //   203: dup
    //   204: invokespecial <init> : ()V
    //   207: checkcast java/util/Collection
    //   210: astore #5
    //   212: iconst_0
    //   213: istore #6
    //   215: aload #4
    //   217: invokeinterface iterator : ()Ljava/util/Iterator;
    //   222: astore #7
    //   224: aload #7
    //   226: invokeinterface hasNext : ()Z
    //   231: ifeq -> 280
    //   234: aload #7
    //   236: invokeinterface next : ()Ljava/lang/Object;
    //   241: astore #8
    //   243: aload #8
    //   245: checkcast net/minecraft/class_1297
    //   248: astore #9
    //   250: iconst_0
    //   251: istore #10
    //   253: aload #9
    //   255: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   258: getstatic net/minecraft/class_1299.field_6052 : Lnet/minecraft/class_1299;
    //   261: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   264: ifeq -> 224
    //   267: aload #5
    //   269: aload #8
    //   271: invokeinterface add : (Ljava/lang/Object;)Z
    //   276: pop
    //   277: goto -> 224
    //   280: aload #5
    //   282: checkcast java/util/List
    //   285: nop
    //   286: checkcast java/lang/Iterable
    //   289: astore_2
    //   290: nop
    //   291: iconst_0
    //   292: istore_3
    //   293: aload_2
    //   294: invokeinterface iterator : ()Ljava/util/Iterator;
    //   299: astore #4
    //   301: aload #4
    //   303: invokeinterface hasNext : ()Z
    //   308: ifeq -> 343
    //   311: aload #4
    //   313: invokeinterface next : ()Ljava/lang/Object;
    //   318: astore #5
    //   320: aload #5
    //   322: checkcast net/minecraft/class_1297
    //   325: astore #6
    //   327: iconst_0
    //   328: istore #7
    //   330: aload #6
    //   332: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   335: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   338: nop
    //   339: nop
    //   340: goto -> 301
    //   343: nop
    //   344: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaax : Ljava/util/Set;
    //   347: invokeinterface clear : ()V
    //   352: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   355: invokeinterface clear : ()V
    //   360: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   363: dup
    //   364: ifnull -> 458
    //   367: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   370: dup
    //   371: ifnull -> 458
    //   374: invokevirtual method_14571 : ()Ljava/util/List;
    //   377: dup
    //   378: ifnull -> 458
    //   381: checkcast java/lang/Iterable
    //   384: astore #5
    //   386: iconst_0
    //   387: istore #6
    //   389: aload #5
    //   391: invokeinterface iterator : ()Ljava/util/Iterator;
    //   396: astore #7
    //   398: aload #7
    //   400: invokeinterface hasNext : ()Z
    //   405: ifeq -> 454
    //   408: aload #7
    //   410: invokeinterface next : ()Ljava/lang/Object;
    //   415: astore #8
    //   417: aload #8
    //   419: checkcast net/minecraft/class_3222
    //   422: astore #9
    //   424: iconst_0
    //   425: istore #10
    //   427: aload #9
    //   429: ldc_w 1448149031
    //   432: i2c
    //   433: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   436: checkcast java/lang/String
    //   439: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   442: checkcast net/minecraft/class_2561
    //   445: iconst_0
    //   446: invokevirtual method_7353 : (Lnet/minecraft/class_2561;Z)V
    //   449: nop
    //   450: nop
    //   451: goto -> 398
    //   454: nop
    //   455: goto -> 460
    //   458: pop
    //   459: nop
    //   460: return
  }
  
  private final void 你为什么要破解我的代码aaaaaH(MinecraftServer paramMinecraftServer) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual method_30611 : ()Lnet/minecraft/class_5455$class_6890;
    //   4: getstatic net/minecraft/class_7924.field_41254 : Lnet/minecraft/class_5321;
    //   7: invokeinterface method_30530 : (Lnet/minecraft/class_5321;)Lnet/minecraft/class_2378;
    //   12: astore_2
    //   13: iconst_0
    //   14: istore_3
    //   15: iconst_0
    //   16: istore #4
    //   18: aload_2
    //   19: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   22: aload_2
    //   23: checkcast java/lang/Iterable
    //   26: astore #5
    //   28: iconst_0
    //   29: istore #6
    //   31: aload #5
    //   33: invokeinterface iterator : ()Ljava/util/Iterator;
    //   38: astore #7
    //   40: aload #7
    //   42: invokeinterface hasNext : ()Z
    //   47: ifeq -> 185
    //   50: aload #7
    //   52: invokeinterface next : ()Ljava/lang/Object;
    //   57: astore #8
    //   59: aload #8
    //   61: checkcast net/minecraft/class_2248
    //   64: astore #9
    //   66: iconst_0
    //   67: istore #10
    //   69: nop
    //   70: ldc_w net/minecraft/class_4970
    //   73: astore #11
    //   75: nop
    //   76: aload #11
    //   78: ldc_w 1601699880
    //   81: i2c
    //   82: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   85: checkcast java/lang/String
    //   88: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   91: astore #12
    //   93: goto -> 115
    //   96: astore #13
    //   98: aload #11
    //   100: ldc_w 1013973033
    //   103: i2c
    //   104: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   107: checkcast java/lang/String
    //   110: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   113: astore #12
    //   115: aload #12
    //   117: astore #14
    //   119: aload #14
    //   121: iconst_1
    //   122: invokevirtual setAccessible : (Z)V
    //   125: aload #14
    //   127: aload #9
    //   129: ldc_w 3600000.0
    //   132: invokestatic valueOf : (F)Ljava/lang/Float;
    //   135: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   138: iload_3
    //   139: istore #12
    //   141: iload #12
    //   143: iconst_1
    //   144: iadd
    //   145: istore_3
    //   146: goto -> 180
    //   149: astore #11
    //   151: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   154: aload #9
    //   156: invokevirtual method_9539 : ()Ljava/lang/String;
    //   159: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   164: aload #11
    //   166: checkcast java/lang/Throwable
    //   169: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   174: iload #4
    //   176: iconst_1
    //   177: iadd
    //   178: istore #4
    //   180: nop
    //   181: nop
    //   182: goto -> 40
    //   185: nop
    //   186: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   189: iload_3
    //   190: iload #4
    //   192: <illegal opcode> makeConcatWithConstants : (II)Ljava/lang/String;
    //   197: invokeinterface info : (Ljava/lang/String;)V
    //   202: return
    // Exception table:
    //   from	to	target	type
    //   69	146	149	java/lang/Exception
    //   75	93	96	java/lang/NoSuchFieldException
  }
  
  private final void 你为什么要破解我的代码aaaaaI() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   4: astore_1
    //   5: aload_1
    //   6: invokevirtual method_3845 : ()Lnet/minecraft/class_2995;
    //   9: dup
    //   10: ldc_w 442236970
    //   13: i2c
    //   14: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   17: checkcast java/lang/String
    //   20: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   23: checkcast net/minecraft/class_269
    //   26: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   29: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   32: dup
    //   33: ifnonnull -> 51
    //   36: pop
    //   37: ldc_w -1402142710
    //   40: i2c
    //   41: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   44: checkcast java/lang/String
    //   47: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   50: aconst_null
    //   51: invokevirtual method_1151 : ()Ljava/util/Collection;
    //   54: dup
    //   55: ldc_w -1380515797
    //   58: i2c
    //   59: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   62: checkcast java/lang/String
    //   65: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   68: checkcast java/lang/Iterable
    //   71: astore_2
    //   72: aload_2
    //   73: invokeinterface iterator : ()Ljava/util/Iterator;
    //   78: astore_3
    //   79: aload_3
    //   80: invokeinterface hasNext : ()Z
    //   85: ifeq -> 132
    //   88: aload_3
    //   89: invokeinterface next : ()Ljava/lang/Object;
    //   94: astore #4
    //   96: aload #4
    //   98: checkcast net/minecraft/class_266
    //   101: astore #5
    //   103: iconst_0
    //   104: istore #6
    //   106: aload #5
    //   108: invokevirtual method_1113 : ()Ljava/lang/String;
    //   111: ldc_w -185270228
    //   114: i2c
    //   115: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   118: checkcast java/lang/String
    //   121: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   124: ifeq -> 79
    //   127: aload #4
    //   129: goto -> 133
    //   132: aconst_null
    //   133: checkcast net/minecraft/class_266
    //   136: dup
    //   137: ifnull -> 173
    //   140: astore_2
    //   141: iconst_0
    //   142: istore_3
    //   143: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   146: dup
    //   147: ifnonnull -> 165
    //   150: pop
    //   151: ldc_w 1564606474
    //   154: i2c
    //   155: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   158: checkcast java/lang/String
    //   161: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   164: aconst_null
    //   165: aload_2
    //   166: invokevirtual method_1194 : (Lnet/minecraft/class_266;)V
    //   169: nop
    //   170: goto -> 175
    //   173: pop
    //   174: nop
    //   175: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   178: dup
    //   179: ifnonnull -> 197
    //   182: pop
    //   183: ldc_w -2093416438
    //   186: i2c
    //   187: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   190: checkcast java/lang/String
    //   193: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   196: aconst_null
    //   197: ldc_w 1104019500
    //   200: i2c
    //   201: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   204: checkcast java/lang/String
    //   207: getstatic net/minecraft/class_274.field_1468 : Lnet/minecraft/class_274;
    //   210: ldc_w -490078163
    //   213: i2c
    //   214: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   217: checkcast java/lang/String
    //   220: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   223: checkcast net/minecraft/class_2561
    //   226: getstatic net/minecraft/class_274$class_275.field_1472 : Lnet/minecraft/class_274$class_275;
    //   229: iconst_1
    //   230: aconst_null
    //   231: invokevirtual method_1168 : (Ljava/lang/String;Lnet/minecraft/class_274;Lnet/minecraft/class_2561;Lnet/minecraft/class_274$class_275;ZLnet/minecraft/class_9022;)Lnet/minecraft/class_266;
    //   234: dup
    //   235: ldc_w -69337042
    //   238: i2c
    //   239: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   242: checkcast java/lang/String
    //   245: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   248: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_266;
    //   251: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   254: dup
    //   255: ifnonnull -> 273
    //   258: pop
    //   259: ldc_w -1601896438
    //   262: i2c
    //   263: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   266: checkcast java/lang/String
    //   269: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   272: aconst_null
    //   273: getstatic net/minecraft/class_8646.field_45157 : Lnet/minecraft/class_8646;
    //   276: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_266;
    //   279: dup
    //   280: ifnonnull -> 298
    //   283: pop
    //   284: ldc_w -1866268661
    //   287: i2c
    //   288: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   291: checkcast java/lang/String
    //   294: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   297: aconst_null
    //   298: invokevirtual method_1158 : (Lnet/minecraft/class_8646;Lnet/minecraft/class_266;)V
    //   301: return
  }
  
  private final void 你为什么要破解我的代码aaaaaJ() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaA : Ljava/util/Map;
    //   3: invokeinterface keySet : ()Ljava/util/Set;
    //   8: checkcast java/lang/Iterable
    //   11: astore_1
    //   12: iconst_0
    //   13: istore_2
    //   14: aload_1
    //   15: invokeinterface iterator : ()Ljava/util/Iterator;
    //   20: astore_3
    //   21: aload_3
    //   22: invokeinterface hasNext : ()Z
    //   27: ifeq -> 109
    //   30: aload_3
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: astore #4
    //   38: aload #4
    //   40: checkcast java/lang/String
    //   43: astore #5
    //   45: iconst_0
    //   46: istore #6
    //   48: aload #5
    //   50: invokestatic method_55422 : (Ljava/lang/String;)Lnet/minecraft/class_9015;
    //   53: astore #7
    //   55: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   58: dup
    //   59: ifnonnull -> 77
    //   62: pop
    //   63: ldc_w -1738735606
    //   66: i2c
    //   67: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   70: checkcast java/lang/String
    //   73: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   76: aconst_null
    //   77: aload #7
    //   79: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_266;
    //   82: dup
    //   83: ifnonnull -> 101
    //   86: pop
    //   87: ldc_w -1747058677
    //   90: i2c
    //   91: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   94: checkcast java/lang/String
    //   97: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   100: aconst_null
    //   101: invokevirtual method_1155 : (Lnet/minecraft/class_9015;Lnet/minecraft/class_266;)V
    //   104: nop
    //   105: nop
    //   106: goto -> 21
    //   109: nop
    //   110: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaA : Ljava/util/Map;
    //   113: invokeinterface entrySet : ()Ljava/util/Set;
    //   118: checkcast java/lang/Iterable
    //   121: astore_1
    //   122: nop
    //   123: iconst_0
    //   124: istore_2
    //   125: aload_1
    //   126: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaB
    //   129: dup
    //   130: invokespecial <init> : ()V
    //   133: checkcast java/util/Comparator
    //   136: invokestatic sortedWith : (Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;
    //   139: checkcast java/lang/Iterable
    //   142: bipush #10
    //   144: invokestatic take : (Ljava/lang/Iterable;I)Ljava/util/List;
    //   147: checkcast java/lang/Iterable
    //   150: astore_1
    //   151: nop
    //   152: iconst_0
    //   153: istore_2
    //   154: aload_1
    //   155: invokeinterface iterator : ()Ljava/util/Iterator;
    //   160: astore_3
    //   161: aload_3
    //   162: invokeinterface hasNext : ()Z
    //   167: ifeq -> 331
    //   170: aload_3
    //   171: invokeinterface next : ()Ljava/lang/Object;
    //   176: astore #4
    //   178: aload #4
    //   180: checkcast java/util/Map$Entry
    //   183: astore #5
    //   185: iconst_0
    //   186: istore #6
    //   188: aload #5
    //   190: invokeinterface getKey : ()Ljava/lang/Object;
    //   195: checkcast java/lang/String
    //   198: astore #7
    //   200: aload #5
    //   202: invokeinterface getValue : ()Ljava/lang/Object;
    //   207: checkcast java/lang/Number
    //   210: invokevirtual intValue : ()I
    //   213: istore #8
    //   215: aload #7
    //   217: invokevirtual length : ()I
    //   220: bipush #7
    //   222: if_icmple -> 255
    //   225: aload #7
    //   227: iconst_0
    //   228: bipush #7
    //   230: invokevirtual substring : (II)Ljava/lang/String;
    //   233: dup
    //   234: ldc_w 1835008047
    //   237: i2c
    //   238: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   241: checkcast java/lang/String
    //   244: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   247: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   252: goto -> 257
    //   255: aload #7
    //   257: astore #9
    //   259: aload #9
    //   261: invokestatic method_55422 : (Ljava/lang/String;)Lnet/minecraft/class_9015;
    //   264: astore #10
    //   266: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_269;
    //   269: dup
    //   270: ifnonnull -> 288
    //   273: pop
    //   274: ldc_w 1011417098
    //   277: i2c
    //   278: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   281: checkcast java/lang/String
    //   284: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   287: aconst_null
    //   288: aload #10
    //   290: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_266;
    //   293: dup
    //   294: ifnonnull -> 312
    //   297: pop
    //   298: ldc_w 1145503755
    //   301: i2c
    //   302: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   305: checkcast java/lang/String
    //   308: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   311: aconst_null
    //   312: invokevirtual method_1180 : (Lnet/minecraft/class_9015;Lnet/minecraft/class_266;)Lnet/minecraft/class_9014;
    //   315: astore #11
    //   317: aload #11
    //   319: iload #8
    //   321: invokeinterface method_55410 : (I)V
    //   326: nop
    //   327: nop
    //   328: goto -> 161
    //   331: nop
    //   332: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaA : Ljava/util/Map;
    //   335: invokeinterface entrySet : ()Ljava/util/Set;
    //   340: checkcast java/lang/Iterable
    //   343: astore_2
    //   344: nop
    //   345: iconst_0
    //   346: istore_3
    //   347: aload_2
    //   348: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaac
    //   351: dup
    //   352: invokespecial <init> : ()V
    //   355: checkcast java/util/Comparator
    //   358: invokestatic sortedWith : (Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;
    //   361: checkcast java/lang/Iterable
    //   364: iconst_3
    //   365: invokestatic take : (Ljava/lang/Iterable;I)Ljava/util/List;
    //   368: checkcast java/lang/Iterable
    //   371: astore_2
    //   372: nop
    //   373: iconst_0
    //   374: istore_3
    //   375: aload_2
    //   376: astore #4
    //   378: new java/util/ArrayList
    //   381: dup
    //   382: aload_2
    //   383: bipush #10
    //   385: invokestatic collectionSizeOrDefault : (Ljava/lang/Iterable;I)I
    //   388: invokespecial <init> : (I)V
    //   391: checkcast java/util/Collection
    //   394: astore #5
    //   396: iconst_0
    //   397: istore #6
    //   399: aload #4
    //   401: invokeinterface iterator : ()Ljava/util/Iterator;
    //   406: astore #7
    //   408: aload #7
    //   410: invokeinterface hasNext : ()Z
    //   415: ifeq -> 463
    //   418: aload #7
    //   420: invokeinterface next : ()Ljava/lang/Object;
    //   425: astore #8
    //   427: aload #5
    //   429: aload #8
    //   431: checkcast java/util/Map$Entry
    //   434: astore #9
    //   436: astore #12
    //   438: iconst_0
    //   439: istore #10
    //   441: aload #9
    //   443: invokeinterface getKey : ()Ljava/lang/Object;
    //   448: checkcast java/lang/String
    //   451: aload #12
    //   453: swap
    //   454: invokeinterface add : (Ljava/lang/Object;)Z
    //   459: pop
    //   460: goto -> 408
    //   463: aload #5
    //   465: checkcast java/util/List
    //   468: nop
    //   469: astore_1
    //   470: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac;
    //   473: aload_1
    //   474: invokevirtual 你为什么要破解我的代码aaaaab : (Ljava/util/List;)V
    //   477: return
  }
  
  private final List<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag> 你为什么要破解我的代码aaaaaK() {
    Set<Map.Entry<String, Integer>> set = 我草你怎么反编译我模组aaaaaA.entrySet();
    boolean bool1 = false;
    List list1 = CollectionsKt.take(CollectionsKt.sortedWith(set, new aaaabY()), 3);
    bool1 = false;
    List list2 = list1;
    ArrayList<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
    boolean bool2 = false;
    for (Map.Entry entry1 : list2) {
      Map.Entry entry2 = entry1;
      ArrayList<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag> arrayList1 = arrayList;
      boolean bool = false;
      if ((String)我草你怎么反编译我模组aaaaaB.get(entry2.getKey()) == null)
        (String)我草你怎么反编译我模组aaaaaB.get(entry2.getKey()); 
      super(我草你怎么反编译我模组aaaaaB.get(entry2.getKey()), (String)aaaabe((char)-221642704));
      arrayList1.add(new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag());
    } 
    return arrayList;
  }
  
  private final void 你为什么要破解我的代码aaaaaL(class_1309 paramclass_1309, class_1282 paramclass_1282) {
    paramclass_1282.method_5526().method_5864();
    aaaaaa.你为什么要破解我的代码aaaaaa().info("实体死亡，伤害类型: " + paramclass_1282.method_48792().comp_1242() + ", 伤害源: " + ((paramclass_1282.method_5526() != null && paramclass_1282.method_5526().method_5864() != null) ? paramclass_1282.method_5526().method_5864().toString() : null));
    class_1297 class_1297 = paramclass_1282.method_5529();
    if (class_1297 != null) {
      String str = (class_1297 instanceof class_1657) ? ((class_1657)class_1297).method_5477().getString() : class_1297.method_5477().getString();
      if (我草你怎么反编译我模组aaaaag.contains(str))
        return; 
      Intrinsics.checkNotNull(str);
      我草你怎么反编译我模组aaaaaA.put(str, Integer.valueOf(((Number)我草你怎么反编译我模组aaaaaA.getOrDefault(str, Integer.valueOf(0))).intValue() + 1));
      你为什么要破解我的代码aaaaaJ();
      aaaaaa.你为什么要破解我的代码aaaaaa().info(str + " 击杀了一个怪物，当前击杀数：" + str);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaaM() {
    我草你怎么反编译我模组aaaaag.clear();
    我草你怎么反编译我模组aaaaag.add((String)aaaabe((char)-382861263));
  }
  
  private final void 你为什么要破解我的代码aaaaaN(class_1308 paramclass_1308, String paramString) {
    try {
      Field field1;
      Field field4;
      int j;
      try {
        field1 = class_1308.class.getDeclaredField((String)aaaabe((char)740753424));
      } catch (Exception exception) {
        field1 = class_1308.class.getDeclaredField((String)aaaabe((char)-18153455));
      } 
      Field field3 = field1;
      field3.setAccessible(true);
      Intrinsics.checkNotNull(field3.get(paramclass_1308), (String)aaaabe((char)-153419758));
      class_1355 class_1355 = (class_1355)field3.get(paramclass_1308);
      try {
        field4 = class_1355.getClass().getDeclaredField((String)aaaabe((char)2011562003));
      } catch (Exception exception) {
        field4 = class_1355.getClass().getDeclaredField((String)aaaabe((char)-1042808812));
      } 
      Field field2 = field4;
      field2.setAccessible(true);
      Intrinsics.checkNotNull(field2.get(class_1355), (String)aaaabe((char)-2018443243));
      Set set = (Set)field2.get(class_1355);
      aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n            实体类型: " + paramclass_1308.getClass().getName() + "\n            目标AI清除前数量: " + set.size() + "\n        "));
      class_1355.method_35113(aaaaad::你为什么要破解我的代码aaaaaY);
      class_1308 class_13081 = paramclass_1308;
      if (class_13081 instanceof class_1510) {
        class_1355.method_6277(1, new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaK((class_1510)paramclass_1308, paramString));
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaabe((char)1192493106));
      } else if (class_13081 instanceof class_1314) {
        class_1355.method_6277(1, (class_1352)new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabm((class_1314)paramclass_1308, paramString));
        aaaaaa.你为什么要破解我的代码aaaaaa().info("为实体 " + paramclass_1308.getClass().getName() + " 添加自定义目标选择器");
      } 
      try {
        Field field = class_1355.getClass().getDeclaredField((String)aaaabe((char)644677651));
        boolean bool = false;
        field.setAccessible(true);
        Intrinsics.checkNotNull(field.get(class_1355), (String)aaaabe((char)-260833259));
        j = ((Set)field.get(class_1355)).size();
      } catch (Exception exception) {
        Field field = class_1355.getClass().getDeclaredField((String)aaaabe((char)1964834836));
        boolean bool = false;
        field.setAccessible(true);
        Intrinsics.checkNotNull(field.get(class_1355), (String)aaaabe((char)1473183765));
        j = ((Set)field.get(class_1355)).size();
      } 
      int i = j;
      aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n            添加AI后的状态:\n            目标AI数量: " + i + "\n        "));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaabe((char)-1219821517), exception);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaaO(class_1510 paramclass_1510) {
    // Byte code:
    //   0: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk
    //   3: dup
    //   4: aload_1
    //   5: lconst_0
    //   6: lconst_0
    //   7: aconst_null
    //   8: aconst_null
    //   9: bipush #30
    //   11: aconst_null
    //   12: invokespecial <init> : (Lnet/minecraft/class_1510;JJLjava/util/concurrent/ScheduledFuture;Ljava/util/concurrent/ScheduledFuture;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    //   15: astore_2
    //   16: aload_2
    //   17: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   20: aload_1
    //   21: aload_2
    //   22: <illegal opcode> run : (Lnet/minecraft/class_1510;Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk;)Ljava/lang/Runnable;
    //   27: lconst_1
    //   28: lconst_1
    //   29: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   32: invokeinterface scheduleAtFixedRate : (Ljava/lang/Runnable;JJLjava/util/concurrent/TimeUnit;)Ljava/util/concurrent/ScheduledFuture;
    //   37: invokevirtual 你为什么要破解我的代码aaaaag : (Ljava/util/concurrent/ScheduledFuture;)V
    //   40: aload_2
    //   41: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   44: aload_1
    //   45: aload_2
    //   46: <illegal opcode> run : (Lnet/minecraft/class_1510;Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk;)Ljava/lang/Runnable;
    //   51: lconst_0
    //   52: ldc2_w 5
    //   55: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   58: invokeinterface scheduleAtFixedRate : (Ljava/lang/Runnable;JJLjava/util/concurrent/TimeUnit;)Ljava/util/concurrent/ScheduledFuture;
    //   63: invokevirtual 你为什么要破解我的代码aaaaai : (Ljava/util/concurrent/ScheduledFuture;)V
    //   66: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaC : Ljava/util/concurrent/ConcurrentHashMap;
    //   69: checkcast java/util/Map
    //   72: astore_3
    //   73: aload_1
    //   74: invokevirtual method_5667 : ()Ljava/util/UUID;
    //   77: dup
    //   78: ldc_w 888406040
    //   81: i2c
    //   82: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   85: checkcast java/lang/String
    //   88: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   91: aload_3
    //   92: swap
    //   93: aload_2
    //   94: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: return
  }
  
  private final void 你为什么要破解我的代码aaaaaP(class_1510 paramclass_1510) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual method_37908 : ()Lnet/minecraft/class_1937;
    //   4: dup
    //   5: ldc_w 442761253
    //   8: i2c
    //   9: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   12: checkcast java/lang/String
    //   15: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   18: checkcast net/minecraft/class_3218
    //   21: astore_2
    //   22: aload_2
    //   23: aload_1
    //   24: checkcast net/minecraft/class_1297
    //   27: aload_1
    //   28: invokevirtual method_5829 : ()Lnet/minecraft/class_238;
    //   31: ldc2_w 128.0
    //   34: invokevirtual method_1014 : (D)Lnet/minecraft/class_238;
    //   37: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabn
    //   40: dup
    //   41: aload_1
    //   42: invokespecial <init> : (Lnet/minecraft/class_1510;)V
    //   45: checkcast kotlin/jvm/functions/Function1
    //   48: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
    //   53: invokevirtual method_8333 : (Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;
    //   56: astore_3
    //   57: aload_3
    //   58: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   61: aload_3
    //   62: checkcast java/lang/Iterable
    //   65: invokestatic shuffled : (Ljava/lang/Iterable;)Ljava/util/List;
    //   68: checkcast java/lang/Iterable
    //   71: iconst_3
    //   72: invokestatic take : (Ljava/lang/Iterable;I)Ljava/util/List;
    //   75: astore #4
    //   77: aload #4
    //   79: checkcast java/lang/Iterable
    //   82: astore #5
    //   84: iconst_0
    //   85: istore #6
    //   87: aload #5
    //   89: invokeinterface iterator : ()Ljava/util/Iterator;
    //   94: astore #7
    //   96: aload #7
    //   98: invokeinterface hasNext : ()Z
    //   103: ifeq -> 213
    //   106: aload #7
    //   108: invokeinterface next : ()Ljava/lang/Object;
    //   113: astore #8
    //   115: aload #8
    //   117: checkcast net/minecraft/class_1297
    //   120: astore #9
    //   122: iconst_0
    //   123: istore #10
    //   125: new net/minecraft/class_1538
    //   128: dup
    //   129: getstatic net/minecraft/class_1299.field_6112 : Lnet/minecraft/class_1299;
    //   132: aload_2
    //   133: checkcast net/minecraft/class_1937
    //   136: invokespecial <init> : (Lnet/minecraft/class_1299;Lnet/minecraft/class_1937;)V
    //   139: astore #11
    //   141: aload #11
    //   143: aload #9
    //   145: invokevirtual method_23317 : ()D
    //   148: aload #9
    //   150: invokevirtual method_23318 : ()D
    //   153: aload #9
    //   155: invokevirtual method_23321 : ()D
    //   158: fconst_0
    //   159: fconst_0
    //   160: invokevirtual method_5808 : (DDDFF)V
    //   163: aload #11
    //   165: iconst_1
    //   166: invokevirtual method_29498 : (Z)V
    //   169: aload_2
    //   170: aload #11
    //   172: checkcast net/minecraft/class_1297
    //   175: invokevirtual method_8649 : (Lnet/minecraft/class_1297;)Z
    //   178: pop
    //   179: aload_2
    //   180: invokevirtual method_48963 : ()Lnet/minecraft/class_8109;
    //   183: aload #11
    //   185: checkcast net/minecraft/class_1297
    //   188: aload_1
    //   189: checkcast net/minecraft/class_1297
    //   192: invokevirtual method_48815 : (Lnet/minecraft/class_1297;Lnet/minecraft/class_1297;)Lnet/minecraft/class_1282;
    //   195: astore #12
    //   197: aload #9
    //   199: aload #12
    //   201: ldc_w 20.0
    //   204: invokevirtual method_5643 : (Lnet/minecraft/class_1282;F)Z
    //   207: pop
    //   208: nop
    //   209: nop
    //   210: goto -> 96
    //   213: nop
    //   214: return
  }
  
  private final void 你为什么要破解我的代码aaaaaQ() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaC : Ljava/util/concurrent/ConcurrentHashMap;
    //   3: invokevirtual values : ()Ljava/util/Collection;
    //   6: dup
    //   7: ldc_w 35323956
    //   10: i2c
    //   11: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   14: checkcast java/lang/String
    //   17: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   20: checkcast java/lang/Iterable
    //   23: astore_1
    //   24: iconst_0
    //   25: istore_2
    //   26: aload_1
    //   27: invokeinterface iterator : ()Ljava/util/Iterator;
    //   32: astore_3
    //   33: aload_3
    //   34: invokeinterface hasNext : ()Z
    //   39: ifeq -> 105
    //   42: aload_3
    //   43: invokeinterface next : ()Ljava/lang/Object;
    //   48: astore #4
    //   50: aload #4
    //   52: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk
    //   55: astore #5
    //   57: iconst_0
    //   58: istore #6
    //   60: aload #5
    //   62: invokevirtual 你为什么要破解我的代码aaaaaf : ()Ljava/util/concurrent/ScheduledFuture;
    //   65: dup
    //   66: ifnull -> 79
    //   69: iconst_0
    //   70: invokeinterface cancel : (Z)Z
    //   75: pop
    //   76: goto -> 80
    //   79: pop
    //   80: aload #5
    //   82: invokevirtual 你为什么要破解我的代码aaaaah : ()Ljava/util/concurrent/ScheduledFuture;
    //   85: dup
    //   86: ifnull -> 99
    //   89: iconst_0
    //   90: invokeinterface cancel : (Z)Z
    //   95: pop
    //   96: goto -> 100
    //   99: pop
    //   100: nop
    //   101: nop
    //   102: goto -> 33
    //   105: nop
    //   106: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaC : Ljava/util/concurrent/ConcurrentHashMap;
    //   109: invokevirtual clear : ()V
    //   112: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   115: invokeinterface shutdown : ()V
    //   120: nop
    //   121: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   124: ldc2_w 5
    //   127: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   130: invokeinterface awaitTermination : (JLjava/util/concurrent/TimeUnit;)Z
    //   135: ifne -> 166
    //   138: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   141: invokeinterface shutdownNow : ()Ljava/util/List;
    //   146: pop
    //   147: goto -> 166
    //   150: astore_1
    //   151: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   154: invokeinterface shutdownNow : ()Ljava/util/List;
    //   159: pop
    //   160: invokestatic currentThread : ()Ljava/lang/Thread;
    //   163: invokevirtual interrupt : ()V
    //   166: return
    // Exception table:
    //   from	to	target	type
    //   120	147	150	java/lang/InterruptedException
  }
  
  private final void 你为什么要破解我的代码aaaaaR() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaC : Ljava/util/concurrent/ConcurrentHashMap;
    //   3: invokevirtual values : ()Ljava/util/Collection;
    //   6: dup
    //   7: ldc_w -1292697548
    //   10: i2c
    //   11: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   14: checkcast java/lang/String
    //   17: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   20: checkcast java/lang/Iterable
    //   23: astore_1
    //   24: iconst_0
    //   25: istore_2
    //   26: aload_1
    //   27: invokeinterface iterator : ()Ljava/util/Iterator;
    //   32: astore_3
    //   33: aload_3
    //   34: invokeinterface hasNext : ()Z
    //   39: ifeq -> 105
    //   42: aload_3
    //   43: invokeinterface next : ()Ljava/lang/Object;
    //   48: astore #4
    //   50: aload #4
    //   52: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk
    //   55: astore #5
    //   57: iconst_0
    //   58: istore #6
    //   60: aload #5
    //   62: invokevirtual 你为什么要破解我的代码aaaaaf : ()Ljava/util/concurrent/ScheduledFuture;
    //   65: dup
    //   66: ifnull -> 79
    //   69: iconst_0
    //   70: invokeinterface cancel : (Z)Z
    //   75: pop
    //   76: goto -> 80
    //   79: pop
    //   80: aload #5
    //   82: invokevirtual 你为什么要破解我的代码aaaaah : ()Ljava/util/concurrent/ScheduledFuture;
    //   85: dup
    //   86: ifnull -> 99
    //   89: iconst_0
    //   90: invokeinterface cancel : (Z)Z
    //   95: pop
    //   96: goto -> 100
    //   99: pop
    //   100: nop
    //   101: nop
    //   102: goto -> 33
    //   105: nop
    //   106: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaC : Ljava/util/concurrent/ConcurrentHashMap;
    //   109: invokevirtual clear : ()V
    //   112: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   115: invokeinterface shutdown : ()V
    //   120: nop
    //   121: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   124: ldc2_w 5
    //   127: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   130: invokeinterface awaitTermination : (JLjava/util/concurrent/TimeUnit;)Z
    //   135: ifne -> 166
    //   138: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   141: invokeinterface shutdownNow : ()Ljava/util/List;
    //   146: pop
    //   147: goto -> 166
    //   150: astore_1
    //   151: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   154: invokeinterface shutdownNow : ()Ljava/util/List;
    //   159: pop
    //   160: invokestatic currentThread : ()Ljava/lang/Thread;
    //   163: invokevirtual interrupt : ()V
    //   166: aload_0
    //   167: invokespecial 你为什么要破解我的代码aaaaag : ()Ljava/util/concurrent/ScheduledExecutorService;
    //   170: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaD : Ljava/util/concurrent/ScheduledExecutorService;
    //   173: return
    // Exception table:
    //   from	to	target	type
    //   120	147	150	java/lang/InterruptedException
  }
  
  private static final Thread 你为什么要破解我的代码aaaaaS(Runnable paramRunnable) {
    Thread thread1 = new Thread(paramRunnable, (String)aaaabe((char)719192117));
    Thread thread2 = thread1;
    boolean bool = false;
    thread2.setDaemon(true);
    return thread1;
  }
  
  private static final void 你为什么要破解我的代码aaaaaT(class_1309 paramclass_1309, class_1282 paramclass_1282) {
    Intrinsics.checkNotNull(paramclass_1309);
    Intrinsics.checkNotNull(paramclass_1282);
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaL(paramclass_1309, paramclass_1282);
  }
  
  private static final void 你为什么要破解我的代码aaaaaU() {
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaM();
  }
  
  private static final boolean 你为什么要破解我的代码aaaaaV(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaabe((char)162988086));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  private static final boolean 你为什么要破解我的代码aaaaaW(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaabe((char)-781844426));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  private static final void 你为什么要破解我的代码aaaaaX(class_3218 paramclass_3218, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 793313335
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: ldc_w 1350631480
    //   18: i2c
    //   19: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   28: nop
    //   29: aconst_null
    //   30: astore_2
    //   31: aload_0
    //   32: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   35: dup
    //   36: ldc_w -569114611
    //   39: i2c
    //   40: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   43: checkcast java/lang/String
    //   46: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   49: astore_3
    //   50: iconst_0
    //   51: istore #4
    //   53: aload_3
    //   54: invokeinterface iterator : ()Ljava/util/Iterator;
    //   59: astore #5
    //   61: aload #5
    //   63: invokeinterface hasNext : ()Z
    //   68: ifeq -> 158
    //   71: aload #5
    //   73: invokeinterface next : ()Ljava/lang/Object;
    //   78: astore #6
    //   80: aload #6
    //   82: checkcast net/minecraft/class_1297
    //   85: astore #7
    //   87: iconst_0
    //   88: istore #8
    //   90: aload #7
    //   92: invokevirtual method_5797 : ()Lnet/minecraft/class_2561;
    //   95: dup
    //   96: ifnull -> 107
    //   99: invokeinterface getString : ()Ljava/lang/String;
    //   104: goto -> 109
    //   107: pop
    //   108: aconst_null
    //   109: aload_1
    //   110: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   115: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   118: ifeq -> 153
    //   121: aload #7
    //   123: instanceof net/minecraft/class_1308
    //   126: ifeq -> 153
    //   129: aload #7
    //   131: astore_2
    //   132: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   135: aload #7
    //   137: invokevirtual getClass : ()Ljava/lang/Class;
    //   140: invokevirtual getName : ()Ljava/lang/String;
    //   143: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   148: invokeinterface info : (Ljava/lang/String;)V
    //   153: nop
    //   154: nop
    //   155: goto -> 61
    //   158: nop
    //   159: aload_2
    //   160: ifnull -> 197
    //   163: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   166: aload_2
    //   167: invokevirtual getClass : ()Ljava/lang/Class;
    //   170: invokevirtual getName : ()Ljava/lang/String;
    //   173: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   178: invokeinterface info : (Ljava/lang/String;)V
    //   183: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   186: aload_2
    //   187: checkcast net/minecraft/class_1308
    //   190: aload_1
    //   191: invokespecial 你为什么要破解我的代码aaaaaN : (Lnet/minecraft/class_1308;Ljava/lang/String;)V
    //   194: goto -> 237
    //   197: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   200: aload_1
    //   201: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   206: invokeinterface warn : (Ljava/lang/String;)V
    //   211: goto -> 237
    //   214: astore_2
    //   215: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   218: ldc_w 1212612665
    //   221: i2c
    //   222: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   225: checkcast java/lang/String
    //   228: aload_2
    //   229: checkcast java/lang/Throwable
    //   232: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   237: return
    // Exception table:
    //   from	to	target	type
    //   28	211	214	java/lang/Exception
  }
  
  private static final boolean 你为什么要破解我的代码aaaaaY(class_1352 paramclass_1352) {
    return true;
  }
  
  private static final void 你为什么要破解我的代码aaaaaZ(class_1510 paramclass_1510, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk) {
    Intrinsics.checkNotNullParameter(paramclass_1510, (String)aaaabe((char)1159331898));
    Intrinsics.checkNotNullParameter(paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk, (String)aaaabe((char)801636411));
    if (!paramclass_1510.method_5805()) {
      if (paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk.你为什么要破解我的代码aaaaaf() != null) {
        paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk.你为什么要破解我的代码aaaaaf().cancel(false);
      } else {
        paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk.你为什么要破解我的代码aaaaaf();
      } 
      return;
    } 
    float f1 = paramclass_1510.method_6032();
    float f2 = paramclass_1510.method_6063() * 0.01F;
    float f3 = RangesKt.coerceAtLeast(f1 - f2, 0.0F);
    paramclass_1510.method_6033(f3);
    if (f3 <= 0.0F)
      paramclass_1510.method_5768(); 
    aaaaaa.你为什么要破解我的代码aaaaaa().info("Dragon health update: current=" + f1 + ", loss=" + f2 + ", new=" + f3);
  }
  
  private static final void 你为什么要破解我的代码aaaaba(class_1510 paramclass_1510, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w -86376390
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: ldc_w 2051211323
    //   18: i2c
    //   19: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   28: nop
    //   29: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   32: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   35: aload_0
    //   36: aload_1
    //   37: <illegal opcode> run : (Lnet/minecraft/class_1510;Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk;)Ljava/lang/Runnable;
    //   42: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   45: goto -> 71
    //   48: astore_2
    //   49: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   52: ldc_w 270991420
    //   55: i2c
    //   56: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   59: checkcast java/lang/String
    //   62: aload_2
    //   63: checkcast java/lang/Throwable
    //   66: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   71: return
    // Exception table:
    //   from	to	target	type
    //   28	45	48	java/lang/Exception
  }
  
  private static final void 你为什么要破解我的代码aaaabb(class_1510 paramclass_1510, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk) {
    Intrinsics.checkNotNullParameter(paramclass_1510, (String)aaaabe((char)-871432134));
    Intrinsics.checkNotNullParameter(paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk, (String)aaaabe((char)860094523));
    if (!paramclass_1510.method_5805()) {
      if (paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk.你为什么要破解我的代码aaaaah() != null) {
        paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk.你为什么要破解我的代码aaaaah().cancel(false);
      } else {
        paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk.你为什么要破解我的代码aaaaah();
      } 
      return;
    } 
    我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaP(paramclass_1510);
  }
  
  private static final void 你为什么要破解我的代码aaaabc(class_1510 paramclass_1510, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 590020666
    //   4: i2c
    //   5: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: ldc_w -1781792709
    //   18: i2c
    //   19: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   28: nop
    //   29: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaad;
    //   32: invokevirtual 你为什么要破解我的代码aaaaal : ()Lnet/minecraft/server/MinecraftServer;
    //   35: aload_0
    //   36: aload_1
    //   37: <illegal opcode> run : (Lnet/minecraft/class_1510;Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk;)Ljava/lang/Runnable;
    //   42: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   45: goto -> 71
    //   48: astore_2
    //   49: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   52: ldc_w -171311043
    //   55: i2c
    //   56: invokestatic aaaabe : (C)Ljava/lang/Object;
    //   59: checkcast java/lang/String
    //   62: aload_2
    //   63: checkcast java/lang/Throwable
    //   66: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   71: return
    // Exception table:
    //   from	to	target	type
    //   28	45	48	java/lang/Exception
  }
  
  private static final boolean 你为什么要破解我的代码aaaabd(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaabe((char)-1963917258));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  static {
    byte[] arrayOfByte1 = "¤¨GÎ\b\016ñÇz\026²Ô\027{h]Ì·\024Âd\0240A\007¯95îSÂ\\%àý&ÊéÜ'¤´Ê°\027!Û°?@©\022G\007hÙÖ}6DXXÉP\003'ÿÏW½rDò\035=\"á-iòênv*ÿ£÷h?©\tZÂ\005MAðÀPfï(¶Ú\037äíÌK84M!\006Vø+²¤ÉuN(\034´{\"\025³*ÉLå`zvvÎO¬{¤´¾LØ\fµ`ìÌJ§H8Ý;%íÈî\025ÊIæüÁÀà}½ñL1³\036ãÄÛ.\026ðZ>Æ­ÜäÌ<:ØxXH\022ms·n°Ç4bCD¥\001±\021vÑ\007¯<»UPÀ¬\007?üûfWUeö¼\034ÁÇt\036òÌa6x>\030'à\003ÛT\033\031*\004ÿ\036Qð¢\004RøDJª¡bþxTø÷êër!æ2gÑ-±ì½½\013ÜÅàQåBÔAf>ó+\006aüÂ2Á%µ¹ðZBÂæs}Û­{pÑ.hé\000Û\007Y'_µÌê!ñÂ£\034ÄUçm@¤ÿ9ó[U\000%\007^Z·\007\020\026Ð<Ò.Ç<þ*B²\016\030\005u¨\032'\000¦b:¤Q\021\003oWÄz áPwe°¢íB$6Ö\017\"Ð\032Ï8\r6ÛC\f#n®Ä\fpfA+Ð\001vs¬Èo}±ÿqÊ]Óy'yø\004òç.fðµ?á*±\005#­{mjúÀrYE=\007ËÈÙI&,P³Ø'% ½ÔùîëBie5pß\037\020mMá/\017v#ã\013÷½\013r\022¯ççíÛ´KY6\0228\007>3Un&­ÑÍbq\013|+ \022\t,-ÀÈòöc;þ\fCÿ\035vS\001CÊsÙ[xC²1&|D[{ga¢¨¸i\006\036ä+XX4´ma!õª\034Íiµu;¸£´\017TÑc-]ág\026*5OSJÂ*\004÷\b\031(Ù0\0256Ü\t·\036¿Ô\0229çu\bñùí\rë=Îf¨\020vn,\034;Õ=uµ0¤¤\034Ó±pÓç?I5õu\031ð{ò\013\026áâB\034Øe¦vr>ýºTA@ ß!ó\034\002Ì%\017IyDpZRÎä¨gSÚ:æFN ã«¥à²XÞZMA.½:æMè\007ßãbíH\017ÁH\020)0·©LÿÓq¾Ò\020\016¬×+\013\026OæÆ»wÃ°MÉSµÏ=<*sWìÏÛÔ\017ó»\036*\006¯é(­dpÀpÌ\034Ú\016iuìù\031+ü\0058ÅA¢w\tÔÀ\023aX9µkñ·-Í¢q\035\032ÑI \tå\023\\zÓñeÖ§Ù%Ú(Q×J\036\\5íW]v\021z³ú«ßJíB9\033Å}\t\007Ed=Êü=Ü·^\034\027Õ=ÚéÐ>Å³üá)*\037fñü°xÓU\tÔ¯]6qpü_Ým\\\007½.°³Á¤^\001ê²btovò¡¯öÂ(rcm3Ár\034\032Ú¹§\037µ\rVf4å¤V®£í×ª<GÝ§vÄJÊj9ßsìQ}5Å¦\017D-\r\017ªÁ±@¾öÔ6ç\0223µ:v:\fg\rTUqØ¯ÌiøO\nµ{}IÃ±e¦\005_¶$ð _{^?\rL^éÙ\036c\"\030\017ö­ÍÕ\020dc\0171µ\025¹d\035U¦ÂCCéµ3°­Fq6\004HÖåÊu)]È\021à=Ø;`¿bR6SB×'ËÉÖnw¹/®ªóôès\031W+\037KÊfQìípÖ<\033_+º\032fV)\021vÉ}ù\033Áfá2#¥?À}~\nS~05\031ìxáH,ÔmÓ\013VÿÜ\n{WH<¥Î\034Wû×U\022n\032ÇÛ½âe\007é þ]¯- sÓ×ëé\024üO­-@Jò±+\013VôÕ§M\000´!U\034Sâ\tÂDØ¶?\"Lg¤vÄTo\t\003öjÏ\023s-s çå±÷\016ëæ\033¯g5»9ü»b«¤\002I2øvìVFÌ.X¶\\Zpé\bKRN\032nHÒ«R-¥5©tÃ=ÐË×3G]¥=öµ'ü7áoÎ\033ý]±BT³hø6JÉ°Ä\013\022\031RË*òszÃ\037¢IÎ¹8?®LQ°ÃÑï\032ø7Ì\037´á\005?mÁ|¢?1º\031¸S¦\001à6vgZ\031©F·R íC\tÌ@Ë\023c¶\032_iñNÞ7[\021L6æs.@ePw,\030¬\000úÚ{Ê÷Âß\rwÁ7\nS\000\030ÿ¹B\001lën+#¬úÃ&\007ûçCö\004qºXó\033££\023n<³ÿ/?|«½0E~n\022;\001ªxù¤Ì \026¡Mé\024RýìÔ(h\\¶Ëjú¨ö\033\023.ÙU¬[\001'£ª%\022,mÈ\023§8Az¯A\024{{¼hA=n2W8ü¶¡5V¶8õyßk\003U\003¢wï¹9Ü\031)É¹ò/2«º/z\037R7¨Ëù3þ(KPR%ÒÀ«xý\037/§\020D\004\036£<Ì\036çèt )yÅÎ¡cÄ»ÿ\037w'\033Ã,kéÈuÊ\037.A¹U<¬»\004½N,Á\020ÂÍfÕnK=îÅÏ\bJ+û¦áceëQò\001\037¯ûs¬rQö BVß\\Þö\001QÂåp5G(Ð\0133\026èØ®æ\037Ê©R)¡3\005]þ\tå=|§Dï½ª'Ò4_û5xì".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "§<zÿ\001Ââ".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-771168462;
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
              Object[] arrayOfObject = new Object[89];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaap = 5;
            我草你怎么反编译我模组aaaaao = 500;
            我草你怎么反编译我模组aaaaan = 600000L;
            我草你怎么反编译我模组aaaaak = 5;
            我草你怎么反编译我模组aaaaaa = new aaaaad();
            我草你怎么反编译我模组aaaaac = new class_243(1.0D, 4.0D, 0.0D);
            String[] arrayOfString = new String[10];
            arrayOfString[0] = (String)aaaabe((char)-1225064386);
            arrayOfString[1] = (String)aaaabe((char)1460273215);
            arrayOfString[2] = (String)aaaabe((char)1754267712);
            arrayOfString[3] = (String)aaaabe((char)-1213988799);
            arrayOfString[4] = (String)aaaabe((char)1317011522);
            arrayOfString[5] = (String)aaaabe((char)117506115);
            arrayOfString[6] = (String)aaaabe((char)-741081020);
            arrayOfString[7] = (String)aaaabe((char)575930437);
            arrayOfString[8] = (String)aaaabe((char)1597702214);
            arrayOfString[9] = (String)aaaabe((char)691798087);
            我草你怎么反编译我模组aaaaaf = CollectionsKt.listOf((Object[])arrayOfString);
            我草你怎么反编译我模组aaaaag = new ArrayList<>();
            我草你怎么反编译我模组aaaaai = new ArrayDeque<>();
            我草你怎么反编译我模组aaaaal = CollectionsKt.emptyList();
            我草你怎么反编译我模组aaaaam = new ConcurrentHashMap<>();
            aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[] arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[15];
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[0] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-2084372408), 1, class_1299.field_6051, false, false, null, null, 120, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[1] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-1559297975), 1, class_1299.field_6051, true, false, null, null, 112, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[2] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)565379146), 1, class_1299.field_6051, false, true, null, null, 96, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[3] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-1630404533), 1, class_1299.field_6137, false, false, null, null, 120, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[4] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-111869876), 1, class_1299.field_6147, false, false, null, null, 120, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[5] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-1996488627), 1, class_1299.field_38095, false, false, null, null, 120, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[6] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-830603186), 1, class_1299.field_6119, false, false, null, null, 120, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[7] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)1484390479), 1, class_1299.field_6116, false, false, null, null, 120, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[8] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)970063910), 1, null, false, false, null, aaaaba.我草你怎么反编译我模组aaaabG, 60, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[9] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)2141651024), 1, null, false, false, (String)aaaabe((char)1769996369), null, 92, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[10] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-1475542958), 1, null, false, false, (String)aaaabe((char)1870594131), null, 92, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[11] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)934084692), 1, null, false, false, (String)aaaabe((char)712573013), null, 92, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[12] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-1123090346), 1, null, false, false, null, aaaaaZ.我草你怎么反编译我模组aaaabF, 60, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[13] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)-1191116713), 1, null, false, false, null, aaaace.我草你怎么反编译我模组aaaacz, 60, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK[14] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK((String)aaaabe((char)1157824600), 1, null, false, false, null, aaaaaj.我草你怎么反编译我模组aaaaaw, 60, null);
            我草你怎么反编译我模组aaaaaq = CollectionsKt.listOf((Object[])arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK);
            我草你怎么反编译我模组aaaaas = 30L;
            我草你怎么反编译我模组aaaaau = 10000L;
            我草你怎么反编译我模组aaaaav = 10000L;
            我草你怎么反编译我模组aaaaaw = new LinkedHashMap<>();
            我草你怎么反编译我模组aaaaax = new LinkedHashSet<>();
            我草你怎么反编译我模组aaaaaA = new LinkedHashMap<>();
            我草你怎么反编译我模组aaaaaB = new LinkedHashMap<>();
            我草你怎么反编译我模组aaaaaC = new ConcurrentHashMap<>();
            我草你怎么反编译我模组aaaaaD = 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag();
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaabe(char paramChar) {
    return ((Object[])aaaaaE)[paramChar];
  }
  
  @SourceDebugExtension({"SMAP\nMonsterBattleStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage$CustomTargetGoal\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1605:1\n2333#2,14:1606\n*S KotlinDebug\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage$CustomTargetGoal\n*L\n1354#1:1606,14\n*E\n"})
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabm extends class_1400<class_1309> {
    @NotNull
    private final String 我草你怎么反编译我模组aaaaae;
    
    static Object aaaaaa;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabm(@NotNull class_1314 param1class_1314, @NotNull String param1String) {
      super((class_1308)param1class_1314, class_1309.class, 10, true, false, null);
      this.我草你怎么反编译我模组aaaaae = param1String;
    }
    
    private final boolean 你为什么要破解我的代码aaaaad(class_1309 param1class_1309) {
      if (!param1class_1309.method_5805())
        return false; 
      this.field_6660.method_5797();
      String str1 = (this.field_6660.method_5797() != null) ? this.field_6660.method_5797().getString() : null;
      param1class_1309.method_5797();
      String str2 = (param1class_1309.method_5797() != null) ? param1class_1309.method_5797().getString() : null;
      if (!Intrinsics.areEqual(str1, str2))
        if (((str2 != null) ? ((StringsKt.startsWith$default(str2, (String)aaaaaf((char)-1992359934), false, 2, null) == true)) : false) && (param1class_1309 instanceof class_1510 || !param1class_1309.method_5864().equals(class_1299.field_6116))); 
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
    
    private static final boolean 你为什么要破解我的代码aaaaab(Function1 param1Function1, Object param1Object) {
      Intrinsics.checkNotNullParameter(param1Function1, (String)aaaaaf((char)1377501187));
      return ((Boolean)param1Function1.invoke(param1Object)).booleanValue();
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
    
    private static Object aaaaaf(char param1Char) {
      return ((Object[])aaaaaa)[param1Char];
    }
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk {
    @NotNull
    private final class_1510 我草你怎么反编译我模组aaaaaa;
    
    private long 我草你怎么反编译我模组aaaaab;
    
    private long 我草你怎么反编译我模组aaaaac;
    
    @Nullable
    private ScheduledFuture<?> 我草你怎么反编译我模组aaaaad;
    
    @Nullable
    private ScheduledFuture<?> 我草你怎么反编译我模组aaaaae;
    
    static Object aaaaaf;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk(@NotNull class_1510 param1class_1510, long param1Long1, long param1Long2, @Nullable ScheduledFuture<?> param1ScheduledFuture1, @Nullable ScheduledFuture<?> param1ScheduledFuture2) {
      this.我草你怎么反编译我模组aaaaaa = param1class_1510;
      this.我草你怎么反编译我模组aaaaab = param1Long1;
      this.我草你怎么反编译我模组aaaaac = param1Long2;
      this.我草你怎么反编译我模组aaaaad = param1ScheduledFuture1;
      this.我草你怎么反编译我模组aaaaae = param1ScheduledFuture2;
    }
    
    @NotNull
    public final class_1510 你为什么要破解我的代码aaaaaa() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final long 你为什么要破解我的代码aaaaab() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    public final void 你为什么要破解我的代码aaaaac(long param1Long) {
      this.我草你怎么反编译我模组aaaaab = param1Long;
    }
    
    public final long 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final void 你为什么要破解我的代码aaaaae(long param1Long) {
      this.我草你怎么反编译我模组aaaaac = param1Long;
    }
    
    @Nullable
    public final ScheduledFuture<?> 你为什么要破解我的代码aaaaaf() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    public final void 你为什么要破解我的代码aaaaag(@Nullable ScheduledFuture<?> param1ScheduledFuture) {
      this.我草你怎么反编译我模组aaaaad = param1ScheduledFuture;
    }
    
    @Nullable
    public final ScheduledFuture<?> 你为什么要破解我的代码aaaaah() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    public final void 你为什么要破解我的代码aaaaai(@Nullable ScheduledFuture<?> param1ScheduledFuture) {
      this.我草你怎么反编译我模组aaaaae = param1ScheduledFuture;
    }
    
    @NotNull
    public final class_1510 你为什么要破解我的代码aaaaaj() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final long 你为什么要破解我的代码aaaaak() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    public final long 你为什么要破解我的代码aaaaal() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    @Nullable
    public final ScheduledFuture<?> 你为什么要破解我的代码aaaaam() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final ScheduledFuture<?> 你为什么要破解我的代码aaaaan() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk 你为什么要破解我的代码aaaaao(@NotNull class_1510 param1class_1510, long param1Long1, long param1Long2, @Nullable ScheduledFuture<?> param1ScheduledFuture1, @Nullable ScheduledFuture<?> param1ScheduledFuture2) {
      Intrinsics.checkNotNullParameter(param1class_1510, (String)aaaaaq((char)865468416));
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk(param1class_1510, param1Long1, param1Long2, param1ScheduledFuture1, param1ScheduledFuture2);
    }
    
    @NotNull
    public String toString() {
      return "DragonStatus(dragon=" + this.我草你怎么反编译我模组aaaaaa + ", lastHealthDrainTime=" + this.我草你怎么反编译我模组aaaaab + ", lastLightningTime=" + this.我草你怎么反编译我模组aaaaac + ", healthDrainTask=" + this.我草你怎么反编译我模组aaaaad + ", lightningTask=" + this.我草你怎么反编译我模组aaaaae + ")";
    }
    
    public int hashCode() {
      null = this.我草你怎么反编译我模组aaaaaa.hashCode();
      null = null * 31 + Long.hashCode(this.我草你怎么反编译我模组aaaaab);
      null = null * 31 + Long.hashCode(this.我草你怎么反编译我模组aaaaac);
      null = null * 31 + ((this.我草你怎么反编译我模组aaaaad == null) ? 0 : this.我草你怎么反编译我模组aaaaad.hashCode());
      return null * 31 + ((this.我草你怎么反编译我模组aaaaae == null) ? 0 : this.我草你怎么反编译我模组aaaaae.hashCode());
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk)param1Object;
      return !Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaa, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk1.我草你怎么反编译我模组aaaaaa) ? false : ((this.我草你怎么反编译我模组aaaaab != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk1.我草你怎么反编译我模组aaaaab) ? false : ((this.我草你怎么反编译我模组aaaaac != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk1.我草你怎么反编译我模组aaaaac) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaad, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk1.我草你怎么反编译我模组aaaaad) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaae, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabk1.我草你怎么反编译我模组aaaaae)))));
    }
    
    static {
      byte[] arrayOfByte1 = "Î\022%%¯%\001]¿ £ÓÒbtZ´ñ»\027\006î¾ÂÁ]U8\020\036\004<\025CÒª\032Àp\032\0167".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "\r£ÑÏØ§ýâ".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)-1831374066;
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
              return;
            } 
          } 
          break;
        } 
      } 
    }
    
    private static Object aaaaaq(char param1Char) {
      return ((Object[])aaaaaf)[param1Char];
    }
  }
  
  @SourceDebugExtension({"SMAP\nMonsterBattleStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage$DragonTargetGoal\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1605:1\n2333#2,14:1606\n*S KotlinDebug\n*F\n+ 1 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage$DragonTargetGoal\n*L\n1403#1:1606,14\n*E\n"})
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaK extends class_1352 {
    @NotNull
    private final class_1510 我草你怎么反编译我模组aaaaad;
    
    @NotNull
    private final String 我草你怎么反编译我模组aaaaae;
    
    @Nullable
    private class_1309 我草你怎么反编译我模组aaaaaf;
    
    private int 我草你怎么反编译我模组aaaaag;
    
    static Object aaaaah;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaK(@NotNull class_1510 param1class_1510, @NotNull String param1String) {
      this.我草你怎么反编译我模组aaaaad = param1class_1510;
      this.我草你怎么反编译我模组aaaaae = param1String;
    }
    
    public boolean method_6264() {
      // Byte code:
      //   0: aload_0
      //   1: aload_0
      //   2: getfield 我草你怎么反编译我模组aaaaag : I
      //   5: iconst_m1
      //   6: iadd
      //   7: putfield 我草你怎么反编译我模组aaaaag : I
      //   10: aload_0
      //   11: getfield 我草你怎么反编译我模组aaaaag : I
      //   14: ifle -> 19
      //   17: iconst_0
      //   18: ireturn
      //   19: aload_0
      //   20: bipush #20
      //   22: putfield 我草你怎么反编译我模组aaaaag : I
      //   25: aload_0
      //   26: getfield 我草你怎么反编译我模组aaaaad : Lnet/minecraft/class_1510;
      //   29: invokevirtual method_37908 : ()Lnet/minecraft/class_1937;
      //   32: aload_0
      //   33: getfield 我草你怎么反编译我模组aaaaad : Lnet/minecraft/class_1510;
      //   36: checkcast net/minecraft/class_1297
      //   39: aload_0
      //   40: getfield 我草你怎么反编译我模组aaaaad : Lnet/minecraft/class_1510;
      //   43: invokevirtual method_5829 : ()Lnet/minecraft/class_238;
      //   46: ldc2_w 128.0
      //   49: invokevirtual method_1014 : (D)Lnet/minecraft/class_238;
      //   52: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaI
      //   55: dup
      //   56: aload_0
      //   57: invokespecial <init> : (Ljava/lang/Object;)V
      //   60: checkcast kotlin/jvm/functions/Function1
      //   63: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
      //   68: invokevirtual method_8333 : (Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;
      //   71: astore_1
      //   72: aload_1
      //   73: invokeinterface isEmpty : ()Z
      //   78: ifeq -> 83
      //   81: iconst_0
      //   82: ireturn
      //   83: aload_0
      //   84: aload_1
      //   85: invokestatic checkNotNull : (Ljava/lang/Object;)V
      //   88: aload_1
      //   89: checkcast java/lang/Iterable
      //   92: astore_2
      //   93: astore_3
      //   94: iconst_0
      //   95: istore #4
      //   97: aload_2
      //   98: invokeinterface iterator : ()Ljava/util/Iterator;
      //   103: astore #5
      //   105: aload #5
      //   107: invokeinterface hasNext : ()Z
      //   112: ifne -> 119
      //   115: aconst_null
      //   116: goto -> 230
      //   119: aload #5
      //   121: invokeinterface next : ()Ljava/lang/Object;
      //   126: astore #6
      //   128: aload #5
      //   130: invokeinterface hasNext : ()Z
      //   135: ifne -> 143
      //   138: aload #6
      //   140: goto -> 230
      //   143: aload #6
      //   145: checkcast net/minecraft/class_1297
      //   148: astore #7
      //   150: iconst_0
      //   151: istore #8
      //   153: aload #7
      //   155: aload_0
      //   156: getfield 我草你怎么反编译我模组aaaaad : Lnet/minecraft/class_1510;
      //   159: checkcast net/minecraft/class_1297
      //   162: invokevirtual method_5858 : (Lnet/minecraft/class_1297;)D
      //   165: dstore #9
      //   167: aload #5
      //   169: invokeinterface next : ()Ljava/lang/Object;
      //   174: astore #8
      //   176: aload #8
      //   178: checkcast net/minecraft/class_1297
      //   181: astore #11
      //   183: iconst_0
      //   184: istore #12
      //   186: aload #11
      //   188: aload_0
      //   189: getfield 我草你怎么反编译我模组aaaaad : Lnet/minecraft/class_1510;
      //   192: checkcast net/minecraft/class_1297
      //   195: invokevirtual method_5858 : (Lnet/minecraft/class_1297;)D
      //   198: dstore #13
      //   200: dload #9
      //   202: dload #13
      //   204: invokestatic compare : (DD)I
      //   207: ifle -> 218
      //   210: aload #8
      //   212: astore #6
      //   214: dload #13
      //   216: dstore #9
      //   218: aload #5
      //   220: invokeinterface hasNext : ()Z
      //   225: ifne -> 167
      //   228: aload #6
      //   230: aload_3
      //   231: swap
      //   232: astore #15
      //   234: aload #15
      //   236: instanceof net/minecraft/class_1309
      //   239: ifeq -> 250
      //   242: aload #15
      //   244: checkcast net/minecraft/class_1309
      //   247: goto -> 251
      //   250: aconst_null
      //   251: putfield 我草你怎么反编译我模组aaaaaf : Lnet/minecraft/class_1309;
      //   254: aload_0
      //   255: getfield 我草你怎么反编译我模组aaaaaf : Lnet/minecraft/class_1309;
      //   258: ifnull -> 265
      //   261: iconst_1
      //   262: goto -> 266
      //   265: iconst_0
      //   266: ireturn
    }
    
    private final boolean 你为什么要破解我的代码aaaaaa(class_1297 param1class_1297) {
      if (!(param1class_1297 instanceof class_1309) || !((class_1309)param1class_1297).method_5805())
        return false; 
      this.我草你怎么反编译我模组aaaaad.method_5797();
      String str1 = (this.我草你怎么反编译我模组aaaaad.method_5797() != null) ? this.我草你怎么反编译我模组aaaaad.method_5797().getString() : null;
      ((class_1309)param1class_1297).method_5797();
      String str2 = (((class_1309)param1class_1297).method_5797() != null) ? ((class_1309)param1class_1297).method_5797().getString() : null;
      if (!Intrinsics.areEqual(str1, str2))
        if ((str2 != null) ? ((StringsKt.startsWith$default(str2, (String)aaaaad((char)-699006974), false, 2, null) == true)) : false); 
      return false;
    }
    
    public boolean method_6266() {
      class_1309 class_13091;
      return (this.我草你怎么反编译我模组aaaaaf == null) ? false : ((class_13091.method_5805() && 你为什么要破解我的代码aaaaaa((class_1297)class_13091)));
    }
    
    public void method_6269() {
      class_1309 class_13091 = this.我草你怎么反编译我模组aaaaaf;
      boolean bool = false;
      this.我草你怎么反编译我模组aaaaad.method_18408(class_13091.method_24515(), 64);
    }
    
    public void method_6270() {
      this.我草你怎么反编译我模组aaaaaf = null;
    }
    
    public void method_6268() {
      class_1309 class_13091 = this.我草你怎么反编译我模组aaaaaf;
      if (class_13091 != null && class_13091.method_5805()) {
        this.我草你怎么反编译我模组aaaaad.method_18408(class_13091.method_24515(), 64);
        double d = this.我草你怎么反编译我模组aaaaad.method_5858((class_1297)class_13091);
        if (d < 1024.0D)
          this.我草你怎么反编译我模组aaaaad.method_5980(class_13091); 
      } 
    }
    
    @NotNull
    public EnumSet<class_1352.class_4134> method_6271() {
      Intrinsics.checkNotNullExpressionValue(EnumSet.of((Enum)class_1352.class_4134.field_18405, (Enum)class_1352.class_4134.field_18406), (String)aaaaad((char)-1893269501));
      return (EnumSet)EnumSet.of((Enum)class_1352.class_4134.field_18405, (Enum)class_1352.class_4134.field_18406);
    }
    
    private static final boolean 你为什么要破解我的代码aaaaab(Function1 param1Function1, Object param1Object) {
      Intrinsics.checkNotNullParameter(param1Function1, (String)aaaaad((char)-1972043772));
      return ((Boolean)param1Function1.invoke(param1Object)).booleanValue();
    }
    
    static {
      byte[] arrayOfByte1 = "µ!. x\004îÉÅ,\005\t66ï_áU!L\026\034à>P`Í\fÑÒ\004ßs7ÄôënrÊ\003Íç`GzÛ\005ÜD÷Â¤Zÿ¤6[fr 8}1¶\034©à".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "ÛÞÄßÖ(".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)-351076703;
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
              return;
            } 
          } 
          break;
        } 
      } 
    }
    
    private static Object aaaaad(char param1Char) {
      return ((Object[])aaaaah)[param1Char];
    }
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK {
    @NotNull
    private final String 我草你怎么反编译我模组aaaaaa;
    
    private final int 我草你怎么反编译我模组aaaaab;
    
    @Nullable
    private final class_1299<? extends class_1308> 我草你怎么反编译我模组aaaaac;
    
    private final boolean 我草你怎么反编译我模组aaaaad;
    
    private final boolean 我草你怎么反编译我模组aaaaae;
    
    @Nullable
    private final String 我草你怎么反编译我模组aaaaaf;
    
    @Nullable
    private final Function3<class_3218, class_243, String, class_1308> 我草你怎么反编译我模组aaaaag;
    
    static Object aaaaah;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK(@NotNull String param1String1, int param1Int, @Nullable class_1299<? extends class_1308> param1class_1299, boolean param1Boolean1, boolean param1Boolean2, @Nullable String param1String2, @Nullable Function3<? super class_3218, ? super class_243, ? super String, ? extends class_1308> param1Function3) {
      this.我草你怎么反编译我模组aaaaaa = param1String1;
      this.我草你怎么反编译我模组aaaaab = param1Int;
      this.我草你怎么反编译我模组aaaaac = param1class_1299;
      this.我草你怎么反编译我模组aaaaad = param1Boolean1;
      this.我草你怎么反编译我模组aaaaae = param1Boolean2;
      this.我草你怎么反编译我模组aaaaaf = param1String2;
      this.我草你怎么反编译我模组aaaaag = (Function3)param1Function3;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaaa() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final int 你为什么要破解我的代码aaaaab() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @Nullable
    public final class_1299<? extends class_1308> 你为什么要破解我的代码aaaaac() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final boolean 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    public final boolean 你为什么要破解我的代码aaaaae() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaaf() {
      return this.我草你怎么反编译我模组aaaaaf;
    }
    
    @Nullable
    public final Function3<class_3218, class_243, String, class_1308> 你为什么要破解我的代码aaaaag() {
      return this.我草你怎么反编译我模组aaaaag;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaah() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final int 你为什么要破解我的代码aaaaai() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @Nullable
    public final class_1299<? extends class_1308> 你为什么要破解我的代码aaaaaj() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final boolean 你为什么要破解我的代码aaaaak() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    public final boolean 你为什么要破解我的代码aaaaal() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaam() {
      return this.我草你怎么反编译我模组aaaaaf;
    }
    
    @Nullable
    public final Function3<class_3218, class_243, String, class_1308> 你为什么要破解我的代码aaaaan() {
      return this.我草你怎么反编译我模组aaaaag;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK 你为什么要破解我的代码aaaaao(@NotNull String param1String1, int param1Int, @Nullable class_1299<? extends class_1308> param1class_1299, boolean param1Boolean1, boolean param1Boolean2, @Nullable String param1String2, @Nullable Function3<? super class_3218, ? super class_243, ? super String, ? extends class_1308> param1Function3) {
      Intrinsics.checkNotNullParameter(param1String1, (String)aaaaaq((char)444334080));
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK(param1String1, param1Int, param1class_1299, param1Boolean1, param1Boolean2, param1String2, param1Function3);
    }
    
    @NotNull
    public String toString() {
      return "EventEntry(name=" + this.我草你怎么反编译我模组aaaaaa + ", count=" + this.我草你怎么反编译我模组aaaaab + ", entityType=" + this.我草你怎么反编译我模组aaaaac + ", isGolden=" + this.我草你怎么反编译我模组aaaaad + ", isDiamond=" + this.我草你怎么反编译我模组aaaaae + ", summonCommand=" + this.我草你怎么反编译我模组aaaaaf + ", customSpawnHandler=" + this.我草你怎么反编译我模组aaaaag + ")";
    }
    
    public int hashCode() {
      null = this.我草你怎么反编译我模组aaaaaa.hashCode();
      null = null * 31 + Integer.hashCode(this.我草你怎么反编译我模组aaaaab);
      null = null * 31 + ((this.我草你怎么反编译我模组aaaaac == null) ? 0 : this.我草你怎么反编译我模组aaaaac.hashCode());
      null = null * 31 + Boolean.hashCode(this.我草你怎么反编译我模组aaaaad);
      null = null * 31 + Boolean.hashCode(this.我草你怎么反编译我模组aaaaae);
      null = null * 31 + ((this.我草你怎么反编译我模组aaaaaf == null) ? 0 : this.我草你怎么反编译我模组aaaaaf.hashCode());
      return null * 31 + ((this.我草你怎么反编译我模组aaaaag == null) ? 0 : this.我草你怎么反编译我模组aaaaag.hashCode());
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK)param1Object;
      return !Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaa, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaaa) ? false : ((this.我草你怎么反编译我模组aaaaab != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaab) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaac, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaac) ? false : ((this.我草你怎么反编译我模组aaaaad != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaad) ? false : ((this.我草你怎么反编译我模组aaaaae != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaae) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaf, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaaf) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaag, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabK1.我草你怎么反编译我模组aaaaag)))))));
    }
    
    static {
      byte[] arrayOfByte1 = "vÑm\035¡ßÅ2\016ñ\030y;ê9ÃH]È\020pt¸/X/¯á4Mµã_ÀA,0".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "\034Ò\"Ä\0372b".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)456126184;
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
              return;
            } 
          } 
          break;
        } 
      } 
    }
    
    private static Object aaaaaq(char param1Char) {
      return ((Object[])aaaaah)[param1Char];
    }
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag {
    @NotNull
    private final String 我草你怎么反编译我模组aaaaaa;
    
    @NotNull
    private final String 我草你怎么反编译我模组aaaaab;
    
    static Object aaaaac;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag(@NotNull String param1String1, @NotNull String param1String2) {
      this.我草你怎么反编译我模组aaaaaa = param1String1;
      this.我草你怎么反编译我模组aaaaab = param1String2;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaaa() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaab() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaac() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag 你为什么要破解我的代码aaaaae(@NotNull String param1String1, @NotNull String param1String2) {
      Intrinsics.checkNotNullParameter(param1String1, (String)aaaaag((char)1730805760));
      Intrinsics.checkNotNullParameter(param1String2, (String)aaaaag((char)1339752449));
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag(param1String1, param1String2);
    }
    
    @NotNull
    public String toString() {
      return "PlayerRankInfo(name=" + this.我草你怎么反编译我模组aaaaaa + ", avatarUrl=" + this.我草你怎么反编译我模组aaaaab + ")";
    }
    
    public int hashCode() {
      null = this.我草你怎么反编译我模组aaaaaa.hashCode();
      return null * 31 + this.我草你怎么反编译我模组aaaaab.hashCode();
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag)param1Object;
      return !Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaa, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag1.我草你怎么反编译我模组aaaaaa) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaab, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaag1.我草你怎么反编译我模组aaaaab));
    }
    
    static {
      byte[] arrayOfByte1 = "e\023<\t\023#R\003Ö{ïª1âíÎUy\032ÓSN4Ñ¶úp·ã\036°)7çÆ\016-\037#·ýv(´¬ºþb\032$".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "¢\032Ü_ÜúD".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)1324431819;
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
              return;
            } 
          } 
          break;
        } 
      } 
    }
    
    private static Object aaaaag(char param1Char) {
      return ((Object[])aaaaac)[param1Char];
    }
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah {
    @NotNull
    private final class_3218 我草你怎么反编译我模组aaaaaa;
    
    @NotNull
    private final class_243 我草你怎么反编译我模组aaaaab;
    
    @NotNull
    private final String 我草你怎么反编译我模组aaaaac;
    
    static Object aaaaad;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah(@NotNull class_3218 param1class_3218, @NotNull class_243 param1class_243, @NotNull String param1String) {
      this.我草你怎么反编译我模组aaaaaa = param1class_3218;
      this.我草你怎么反编译我模组aaaaab = param1class_243;
      this.我草你怎么反编译我模组aaaaac = param1String;
    }
    
    @NotNull
    public final class_3218 你为什么要破解我的代码aaaaaa() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    @NotNull
    public final class_243 你为什么要破解我的代码aaaaab() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaac() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    @NotNull
    public final class_3218 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    @NotNull
    public final class_243 你为什么要破解我的代码aaaaae() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaaf() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah 你为什么要破解我的代码aaaaag(@NotNull class_3218 param1class_3218, @NotNull class_243 param1class_243, @NotNull String param1String) {
      Intrinsics.checkNotNullParameter(param1class_3218, (String)aaaaai((char)436928512));
      Intrinsics.checkNotNullParameter(param1class_243, (String)aaaaai((char)558628865));
      Intrinsics.checkNotNullParameter(param1String, (String)aaaaai((char)-2048196606));
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah(param1class_3218, param1class_243, param1String);
    }
    
    @NotNull
    public String toString() {
      return "TNTSpawnRequest(world=" + this.我草你怎么反编译我模组aaaaaa + ", position=" + this.我草你怎么反编译我模组aaaaab + ", customName=" + this.我草你怎么反编译我模组aaaaac + ")";
    }
    
    public int hashCode() {
      null = this.我草你怎么反编译我模组aaaaaa.hashCode();
      null = null * 31 + this.我草你怎么反编译我模组aaaaab.hashCode();
      return null * 31 + this.我草你怎么反编译我模组aaaaac.hashCode();
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah)param1Object;
      return !Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaa, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah1.我草你怎么反编译我模组aaaaaa) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaab, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah1.我草你怎么反编译我模组aaaaab) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaac, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaah1.我草你怎么反编译我模组aaaaac)));
    }
    
    static {
      byte[] arrayOfByte1 = ")ç%mÓ)Tå´ÌnÙÐ¼hõÊ×4\027£|=ê\032´#HKb\031\b\005]ý\0337·ô\\\005Òø_¾jb}ãO¡'.Z".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "î^¸¯Ô\030î".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)720033701;
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
                Object[] arrayOfObject = new Object[3];
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
    
    private static Object aaaaai(char param1Char) {
      return ((Object[])aaaaad)[param1Char];
    }
  }
}
