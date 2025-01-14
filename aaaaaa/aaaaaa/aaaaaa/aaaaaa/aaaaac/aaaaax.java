package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaah;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaH;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaap;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabE;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabL;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabm;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaam.aaaaaa;
import cn.pixellive.mc.game.PixelLiveGameMod;
import cn.pixellive.mc.game.event.live.LiveGiftEvent;
import cn.pixellive.mc.game.event.live.LiveLikeEvent;
import com.google.common.eventbus.Subscribe;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import net.minecraft.class_1259;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1541;
import net.minecraft.class_1928;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2675;
import net.minecraft.class_2680;
import net.minecraft.class_3002;
import net.minecraft.class_3004;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nBreakOutStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BreakOutStage.kt\ncn/pixellive/mc/game/stage/BreakOutStage\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,2063:1\n1855#2,2:2064\n1855#2,2:2066\n1855#2,2:2069\n1855#2,2:2071\n2333#2,14:2073\n1855#2,2:2087\n1855#2,2:2089\n1855#2,2:2091\n1855#2,2:2093\n1855#2,2:2095\n1855#2,2:2097\n1747#2,3:2099\n1855#2,2:2102\n1855#2,2:2104\n1855#2,2:2106\n1855#2,2:2108\n1855#2,2:2110\n1855#2:2112\n1855#2,2:2113\n1856#2:2115\n766#2:2116\n857#2,2:2117\n1855#2,2:2119\n766#2:2121\n857#2,2:2122\n1855#2,2:2124\n1855#2,2:2126\n1855#2,2:2128\n1855#2,2:2130\n766#2:2132\n857#2,2:2133\n1855#2,2:2135\n1855#2,2:2137\n1855#2:2139\n766#2:2140\n857#2,2:2141\n1855#2,2:2143\n1856#2:2145\n1#3:2068\n*S KotlinDebug\n*F\n+ 1 BreakOutStage.kt\ncn/pixellive/mc/game/stage/BreakOutStage\n*L\n370#1:2064,2\n381#1:2066,2\n462#1:2069,2\n511#1:2071,2\n517#1:2073,14\n909#1:2087,2\n952#1:2089,2\n1217#1:2091,2\n1242#1:2093,2\n1252#1:2095,2\n1495#1:2097,2\n1514#1:2099,3\n1627#1:2102,2\n1675#1:2104,2\n1691#1:2106,2\n1710#1:2108,2\n1726#1:2110,2\n1749#1:2112\n1752#1:2113,2\n1749#1:2115\n1812#1:2116\n1812#1:2117,2\n1813#1:2119,2\n1830#1:2121\n1830#1:2122,2\n1831#1:2124,2\n1908#1:2126,2\n1938#1:2128,2\n1970#1:2130,2\n2030#1:2132\n2030#1:2133,2\n2033#1:2135,2\n2058#1:2137,2\n1854#1:2139\n1856#1:2140\n1856#1:2141,2\n1857#1:2143,2\n1854#1:2145\n*E\n"})
public final class aaaaax extends aaaabG {
  @NotNull
  public static final aaaaax 我草你怎么反编译我模组aaaabk;
  
  @NotNull
  private static Path 我草你怎么反编译我模组aaaabl;
  
  private static int 我草你怎么反编译我模组aaaabm;
  
  @Nullable
  private static MinecraftServer 我草你怎么反编译我模组aaaaab;
  
  private static int 我草你怎么反编译我模组aaaaah;
  
  private static int 我草你怎么反编译我模组aaaabn;
  
  @NotNull
  private static final class_243 我草你怎么反编译我模组aaaaac;
  
  private static class_3002 我草你怎么反编译我模组aaaaaF;
  
  private static class_3002 我草你怎么反编译我模组aaaabo;
  
  private static class_3002 我草你怎么反编译我模组aaaaae;
  
  private static int 我草你怎么反编译我模组aaaaaK;
  
  private static int 我草你怎么反编译我模组aaaaaL;
  
  private static int 我草你怎么反编译我模组aaaabp;
  
  private static int 我草你怎么反编译我模组aaaaad;
  
  private static int 我草你怎么反编译我模组aaaaaW;
  
  @NotNull
  private static List<String> 我草你怎么反编译我模组aaaaaf;
  
  private static long 我草你怎么反编译我模组aaaaas;
  
  private static long 我草你怎么反编译我模组aaaaar;
  
  @NotNull
  private static List<String> 我草你怎么反编译我模组aaaaal;
  
  private static int 我草你怎么反编译我模组aaaabq;
  
  private static int 我草你怎么反编译我模组aaaabr;
  
  private static boolean 我草你怎么反编译我模组aaaabs;
  
  private static int 我草你怎么反编译我模组aaaabt;
  
  private static int 我草你怎么反编译我模组aaaabu;
  
  private static final int 我草你怎么反编译我模组aaaabv;
  
  private static boolean 我草你怎么反编译我模组aaaabw;
  
  private static int 我草你怎么反编译我模组aaaabx;
  
  private static final int 我草你怎么反编译我模组aaaaby;
  
  private static final int 我草你怎么反编译我模组aaaabz;
  
  private static final int 我草你怎么反编译我模组aaaabA;
  
  private static final int 我草你怎么反编译我模组aaaabB;
  
  private static final int 我草你怎么反编译我模组aaaabC;
  
  private static int 我草你怎么反编译我模组aaaabD;
  
  private static boolean 我草你怎么反编译我模组aaaabE;
  
  private static boolean 我草你怎么反编译我模组aaaabF;
  
  private static boolean 我草你怎么反编译我模组aaaabG;
  
  @Nullable
  private static Float 我草你怎么反编译我模组aaaabH;
  
  private static long 我草你怎么反编译我模组aaaabI;
  
  @NotNull
  private static String 我草你怎么反编译我模组aaaabJ;
  
  @NotNull
  private static String 我草你怎么反编译我模组aaaabK;
  
  private static boolean 我草你怎么反编译我模组aaaabL;
  
  private static long 我草你怎么反编译我模组aaaabM;
  
  private static boolean 我草你怎么反编译我模组aaaabN;
  
  private static long 我草你怎么反编译我模组aaaabO;
  
  private static User32 我草你怎么反编译我模组aaaabP;
  
  private static final int 我草你怎么反编译我模组aaaabQ;
  
  private static final int 我草你怎么反编译我模组aaaabR;
  
  private static final int 我草你怎么反编译我模组aaaabS;
  
  private static final int 我草你怎么反编译我模组aaaabT;
  
  private static final int 我草你怎么反编译我模组aaaabU;
  
  private static final int 我草你怎么反编译我模组aaaabV;
  
  private static final int 我草你怎么反编译我模组aaaabW;
  
  private static final int 我草你怎么反编译我模组aaaabX;
  
  @NotNull
  private static final WinDef.LPARAM 我草你怎么反编译我模组aaaabY;
  
  @Nullable
  private static WinDef.HWND 我草你怎么反编译我模组aaaabZ;
  
  private static final long 我草你怎么反编译我模组aaaaca;
  
  private static final long 我草你怎么反编译我模组aaaacb;
  
  @NotNull
  private static String 我草你怎么反编译我模组aaaacc;
  
  private static boolean 我草你怎么反编译我模组aaaacd;
  
  private static boolean 我草你怎么反编译我模组aaaace;
  
  @NotNull
  private static final HashSet<Job> 我草你怎么反编译我模组aaaacf;
  
  @NotNull
  private static String 我草你怎么反编译我模组aaaacg;
  
  private static double 我草你怎么反编译我模组aaaach;
  
  private static long 我草你怎么反编译我模组aaaaci;
  
  private static final long 我草你怎么反编译我模组aaaacj;
  
  @NotNull
  private static final List<aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal> 我草你怎么反编译我模组aaaaaq;
  
  static Object aaaack;
  
  @NotNull
  public final Path 你为什么要破解我的代码aaaadf() {
    return 我草你怎么反编译我模组aaaabl;
  }
  
  public final void 你为什么要破解我的代码aaaadg(@NotNull Path paramPath) {
    Intrinsics.checkNotNullParameter(paramPath, (String)aaaaeR((char)2119172096));
    我草你怎么反编译我模组aaaabl = paramPath;
  }
  
  public final int 你为什么要破解我的代码aaaadh() {
    return 我草你怎么反编译我模组aaaabm;
  }
  
  public final void 你为什么要破解我的代码aaaadi(int paramInt) {
    我草你怎么反编译我模组aaaabm = paramInt;
  }
  
  @NotNull
  public final class_243 你为什么要破解我的代码aaaadj() {
    return 我草你怎么反编译我模组aaaaac;
  }
  
  public final int 你为什么要破解我的代码aaaaca() {
    return 我草你怎么反编译我模组aaaaaL;
  }
  
  public final void 你为什么要破解我的代码aaaabV(int paramInt) {
    我草你怎么反编译我模组aaaaaL = paramInt;
  }
  
  public final int 你为什么要破解我的代码aaaadk() {
    return 我草你怎么反编译我模组aaaabp;
  }
  
  public final void 你为什么要破解我的代码aaaadl(int paramInt) {
    我草你怎么反编译我模组aaaabp = paramInt;
  }
  
  @NotNull
  public String 你为什么要破解我的代码aaaaae() {
    return (String)aaaaeR((char)54525953);
  }
  
  @NotNull
  public String 你为什么要破解我的代码aaaaaf() {
    return (String)aaaaeR((char)263192578);
  }
  
  public void 你为什么要破解我的代码aaaaah(@NotNull MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramMinecraftServer, (String)aaaaeR((char)-1540292605));
    我草你怎么反编译我模组aaaaab = paramMinecraftServer;
    Intrinsics.checkNotNullExpressionValue(paramMinecraftServer.method_30002(), (String)aaaaeR((char)-689307644));
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaak(paramMinecraftServer.method_30002());
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaab);
    class_3218 class_32181 = 我草你怎么反编译我模组aaaaab.method_30002();
    class_3218 class_32182 = class_32181;
    boolean bool = false;
    ((class_1928.class_4310)class_32182.method_8450().method_20746(class_1928.field_19400)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)class_32182.method_8450().method_20746(class_1928.field_19392)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)class_32182.method_8450().method_20746(class_1928.field_19393)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)class_32182.method_8450().method_20746(class_1928.field_19391)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)class_32182.method_8450().method_20746(class_1928.field_19388)).method_20758(false, paramMinecraftServer);
    aaaabW.我草你怎么反编译我模组aaaacY.你为什么要破解我的代码aaaaaH(paramMinecraftServer);
    aaaaab.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().register(this);
    我草你怎么反编译我模组aaaabq = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj().你为什么要破解我的代码aaaaav().你为什么要破解我的代码aaaaaa();
    你为什么要破解我的代码aaaabM(paramMinecraftServer);
    我草你怎么反编译我模组aaaaaf = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj().你为什么要破解我的代码aaaaae();
    我草你怎么反编译我模组aaaaas = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj().你为什么要破解我的代码aaaaag();
    我草你怎么反编译我模组aaaaal = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj().你为什么要破解我的代码aaaaak();
    我草你怎么反编译我模组aaaaah = 0;
    我草你怎么反编译我模组aaaabp = -2800;
    你为什么要破解我的代码aaaaei();
  }
  
  public void 你为什么要破解我的代码aaaaai(@NotNull MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramMinecraftServer, (String)aaaaeR((char)1616379907));
    你为什么要破解我的代码aaaaek(paramMinecraftServer);
    我草你怎么反编译我模组aaaaah = 0;
    我草你怎么反编译我模组aaaabu = 144;
    我草你怎么反编译我模组aaaabw = false;
    我草你怎么反编译我模组aaaabx = 354;
    我草你怎么反编译我模组aaaabD = 144;
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaal();
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().unregister(this);
    我草你怎么反编译我模组aaaabn = 0;
    你为什么要破解我的代码aaaabN(paramMinecraftServer);
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaan(@NotNull aaaaac paramaaaaac) {
    Intrinsics.checkNotNullParameter(paramaaaaac, (String)aaaaeR((char)-1393295355));
    int i = 我草你怎么反编译我模组aaaaah;
    我草你怎么反编译我模组aaaaah = i + 1;
    long l = System.currentTimeMillis();
    你为什么要破解我的代码aaaadB();
    你为什么要破解我的代码aaaadv();
    你为什么要破解我的代码aaaadq();
    if (我草你怎么反编译我模组aaaaah % 2 == 0)
      你为什么要破解我的代码aaaado(); 
    if (我草你怎么反编译我模组aaaaah % 4 == 0 && 我草你怎么反编译我模组aaaaaW > 0) {
      你为什么要破解我的代码aaaacg();
      int j = 我草你怎么反编译我模组aaaaaW;
      我草你怎么反编译我模组aaaaaW = j + -1;
    } 
    if (l - 我草你怎么反编译我模组aaaaar >= 我草你怎么反编译我模组aaaaas * 1000L && 我草你怎么反编译我模组aaaaah > 200) {
      你为什么要破解我的代码aaaaay();
      我草你怎么反编译我模组aaaaar = l;
    } 
    你为什么要破解我的代码aaaadD();
    你为什么要破解我的代码aaaael();
    你为什么要破解我的代码aaaaea();
    你为什么要破解我的代码aaaaee();
  }
  
  private final void 你为什么要破解我的代码aaaabM(MinecraftServer paramMinecraftServer) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual method_3837 : ()Lnet/minecraft/class_3004;
    //   4: ldc_w 904134662
    //   7: i2c
    //   8: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   11: checkcast java/lang/String
    //   14: ldc_w 1344864263
    //   17: i2c
    //   18: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   21: checkcast java/lang/String
    //   24: invokestatic method_60655 : (Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/class_2960;
    //   27: ldc_w 534249480
    //   30: i2c
    //   31: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   34: checkcast java/lang/String
    //   37: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   40: checkcast net/minecraft/class_2561
    //   43: invokevirtual method_12970 : (Lnet/minecraft/class_2960;Lnet/minecraft/class_2561;)Lnet/minecraft/class_3002;
    //   46: astore_2
    //   47: aload_2
    //   48: astore_3
    //   49: iconst_0
    //   50: istore #4
    //   52: aload_3
    //   53: getstatic net/minecraft/class_1259$class_1260.field_5782 : Lnet/minecraft/class_1259$class_1260;
    //   56: invokevirtual method_5416 : (Lnet/minecraft/class_1259$class_1260;)V
    //   59: aload_3
    //   60: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   63: pop
    //   64: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaL : I
    //   67: invokevirtual method_12956 : (I)V
    //   70: nop
    //   71: aload_2
    //   72: dup
    //   73: ldc_w 867958793
    //   76: i2c
    //   77: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   80: checkcast java/lang/String
    //   83: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   86: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaF : Lnet/minecraft/class_3002;
    //   89: aload_1
    //   90: invokevirtual method_3837 : ()Lnet/minecraft/class_3004;
    //   93: ldc_w 2099118086
    //   96: i2c
    //   97: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   100: checkcast java/lang/String
    //   103: ldc_w -861208566
    //   106: i2c
    //   107: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   110: checkcast java/lang/String
    //   113: invokestatic method_60655 : (Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/class_2960;
    //   116: ldc_w 677838859
    //   119: i2c
    //   120: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   123: checkcast java/lang/String
    //   126: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   129: checkcast net/minecraft/class_2561
    //   132: invokevirtual method_12970 : (Lnet/minecraft/class_2960;Lnet/minecraft/class_2561;)Lnet/minecraft/class_3002;
    //   135: astore_2
    //   136: aload_2
    //   137: astore_3
    //   138: iconst_0
    //   139: istore #4
    //   141: aload_3
    //   142: getstatic net/minecraft/class_1259$class_1260.field_5782 : Lnet/minecraft/class_1259$class_1260;
    //   145: invokevirtual method_5416 : (Lnet/minecraft/class_1259$class_1260;)V
    //   148: aload_3
    //   149: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   152: pop
    //   153: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabp : I
    //   156: invokevirtual method_12956 : (I)V
    //   159: nop
    //   160: aload_2
    //   161: dup
    //   162: ldc_w 616038409
    //   165: i2c
    //   166: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   169: checkcast java/lang/String
    //   172: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   175: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   178: aload_1
    //   179: invokevirtual method_3837 : ()Lnet/minecraft/class_3004;
    //   182: ldc_w 563281926
    //   185: i2c
    //   186: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   189: checkcast java/lang/String
    //   192: ldc_w 1821442060
    //   195: i2c
    //   196: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   199: checkcast java/lang/String
    //   202: invokestatic method_60655 : (Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/class_2960;
    //   205: ldc_w -993787891
    //   208: i2c
    //   209: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   212: checkcast java/lang/String
    //   215: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   218: checkcast net/minecraft/class_2561
    //   221: invokevirtual method_12970 : (Lnet/minecraft/class_2960;Lnet/minecraft/class_2561;)Lnet/minecraft/class_3002;
    //   224: astore_2
    //   225: aload_2
    //   226: astore_3
    //   227: iconst_0
    //   228: istore #4
    //   230: aload_3
    //   231: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   234: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   237: invokevirtual 你为什么要破解我的代码aaaaaj : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaab;
    //   240: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   243: invokevirtual method_12956 : (I)V
    //   246: aload_3
    //   247: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaad : I
    //   250: invokevirtual method_12954 : (I)V
    //   253: nop
    //   254: aload_2
    //   255: dup
    //   256: ldc_w -1149173751
    //   259: i2c
    //   260: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   263: checkcast java/lang/String
    //   266: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   269: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   272: aload_1
    //   273: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   276: invokevirtual method_14571 : ()Ljava/util/List;
    //   279: dup
    //   280: ldc_w 787808270
    //   283: i2c
    //   284: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   287: checkcast java/lang/String
    //   290: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   293: checkcast java/lang/Iterable
    //   296: astore #5
    //   298: iconst_0
    //   299: istore_2
    //   300: aload #5
    //   302: invokeinterface iterator : ()Ljava/util/Iterator;
    //   307: astore_3
    //   308: aload_3
    //   309: invokeinterface hasNext : ()Z
    //   314: ifeq -> 421
    //   317: aload_3
    //   318: invokeinterface next : ()Ljava/lang/Object;
    //   323: astore #4
    //   325: aload #4
    //   327: checkcast net/minecraft/class_3222
    //   330: astore #6
    //   332: iconst_0
    //   333: istore #7
    //   335: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaF : Lnet/minecraft/class_3002;
    //   338: dup
    //   339: ifnonnull -> 357
    //   342: pop
    //   343: ldc_w 927858703
    //   346: i2c
    //   347: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   350: checkcast java/lang/String
    //   353: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   356: aconst_null
    //   357: aload #6
    //   359: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   362: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   365: dup
    //   366: ifnonnull -> 384
    //   369: pop
    //   370: ldc_w 1543307280
    //   373: i2c
    //   374: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   377: checkcast java/lang/String
    //   380: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   383: aconst_null
    //   384: aload #6
    //   386: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   389: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   392: dup
    //   393: ifnonnull -> 411
    //   396: pop
    //   397: ldc_w 1843265553
    //   400: i2c
    //   401: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   404: checkcast java/lang/String
    //   407: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   410: aconst_null
    //   411: aload #6
    //   413: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   416: nop
    //   417: nop
    //   418: goto -> 308
    //   421: nop
    //   422: return
  }
  
  private final void 你为什么要破解我的代码aaaadm() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 你为什么要破解我的代码aaaabA : ()V
    //   4: aload_0
    //   5: invokespecial 你为什么要破解我的代码aaaadn : ()V
    //   8: aload_0
    //   9: invokespecial 你为什么要破解我的代码aaaaam : ()V
    //   12: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   15: dup
    //   16: ifnull -> 163
    //   19: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   22: dup
    //   23: ifnull -> 163
    //   26: invokevirtual method_14571 : ()Ljava/util/List;
    //   29: dup
    //   30: ifnull -> 163
    //   33: checkcast java/lang/Iterable
    //   36: astore_1
    //   37: iconst_0
    //   38: istore_2
    //   39: aload_1
    //   40: invokeinterface iterator : ()Ljava/util/Iterator;
    //   45: astore_3
    //   46: aload_3
    //   47: invokeinterface hasNext : ()Z
    //   52: ifeq -> 159
    //   55: aload_3
    //   56: invokeinterface next : ()Ljava/lang/Object;
    //   61: astore #4
    //   63: aload #4
    //   65: checkcast net/minecraft/class_3222
    //   68: astore #5
    //   70: iconst_0
    //   71: istore #6
    //   73: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaF : Lnet/minecraft/class_3002;
    //   76: dup
    //   77: ifnonnull -> 95
    //   80: pop
    //   81: ldc_w -188022769
    //   84: i2c
    //   85: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   88: checkcast java/lang/String
    //   91: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   94: aconst_null
    //   95: aload #5
    //   97: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   100: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   103: dup
    //   104: ifnonnull -> 122
    //   107: pop
    //   108: ldc_w 728039440
    //   111: i2c
    //   112: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   115: checkcast java/lang/String
    //   118: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   121: aconst_null
    //   122: aload #5
    //   124: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   127: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   130: dup
    //   131: ifnonnull -> 149
    //   134: pop
    //   135: ldc_w -84672495
    //   138: i2c
    //   139: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   142: checkcast java/lang/String
    //   145: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   148: aconst_null
    //   149: aload #5
    //   151: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   154: nop
    //   155: nop
    //   156: goto -> 46
    //   159: nop
    //   160: goto -> 165
    //   163: pop
    //   164: nop
    //   165: return
  }
  
  private final void 你为什么要破解我的代码aaaabA() {
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)-1787363313)); 
    null.method_12954(我草你怎么反编译我模组aaaaaK);
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)846921743)); 
    null.method_12956(我草你怎么反编译我模组aaaaaL);
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)-1990393841)); 
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(我草你怎么反编译我模组aaaaaK);
    arrayOfObject[1] = Integer.valueOf(我草你怎么反编译我模组aaaaaL);
    null.method_5413((class_2561)class_2561.method_43469((String)aaaaeR((char)443940872), arrayOfObject));
  }
  
  private final void 你为什么要破解我的代码aaaadn() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 118
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 118
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 118
    //   21: checkcast java/lang/Iterable
    //   24: invokeinterface iterator : ()Ljava/util/Iterator;
    //   29: astore_1
    //   30: aload_1
    //   31: invokeinterface hasNext : ()Z
    //   36: ifne -> 43
    //   39: aconst_null
    //   40: goto -> 108
    //   43: aload_1
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: checkcast net/minecraft/class_3222
    //   52: astore_2
    //   53: iconst_0
    //   54: istore_3
    //   55: aload_2
    //   56: invokevirtual method_23317 : ()D
    //   59: dstore #4
    //   61: aload_1
    //   62: invokeinterface hasNext : ()Z
    //   67: ifeq -> 103
    //   70: aload_1
    //   71: invokeinterface next : ()Ljava/lang/Object;
    //   76: checkcast net/minecraft/class_3222
    //   79: astore #6
    //   81: iconst_0
    //   82: istore #7
    //   84: aload #6
    //   86: invokevirtual method_23317 : ()D
    //   89: dstore #8
    //   91: dload #4
    //   93: dload #8
    //   95: invokestatic max : (DD)D
    //   98: dstore #4
    //   100: goto -> 61
    //   103: dload #4
    //   105: invokestatic valueOf : (D)Ljava/lang/Double;
    //   108: dup
    //   109: ifnull -> 118
    //   112: invokevirtual doubleValue : ()D
    //   115: goto -> 125
    //   118: pop
    //   119: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   122: getfield field_1352 : D
    //   125: dstore #10
    //   127: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   130: dup
    //   131: ifnonnull -> 149
    //   134: pop
    //   135: ldc_w -1991049200
    //   138: i2c
    //   139: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   142: checkcast java/lang/String
    //   145: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   148: aconst_null
    //   149: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   152: getfield field_1352 : D
    //   155: dload #10
    //   157: dsub
    //   158: d2i
    //   159: invokevirtual method_12954 : (I)V
    //   162: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   165: dup
    //   166: ifnonnull -> 184
    //   169: pop
    //   170: ldc_w -643432432
    //   173: i2c
    //   174: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   177: checkcast java/lang/String
    //   180: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   183: aconst_null
    //   184: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   187: getfield field_1352 : D
    //   190: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabp : I
    //   193: i2d
    //   194: dsub
    //   195: d2i
    //   196: invokevirtual method_12956 : (I)V
    //   199: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   202: getfield field_1352 : D
    //   205: dload #10
    //   207: dsub
    //   208: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   211: getfield field_1352 : D
    //   214: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabp : I
    //   217: i2d
    //   218: dsub
    //   219: ddiv
    //   220: dstore #12
    //   222: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   225: dup
    //   226: ifnonnull -> 244
    //   229: pop
    //   230: ldc_w -785448944
    //   233: i2c
    //   234: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   237: checkcast java/lang/String
    //   240: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   243: aconst_null
    //   244: dload #12
    //   246: ldc2_w 0.33000001311302185
    //   249: dcmpg
    //   250: ifge -> 259
    //   253: getstatic net/minecraft/class_1259$class_1260.field_5784 : Lnet/minecraft/class_1259$class_1260;
    //   256: goto -> 277
    //   259: dload #12
    //   261: ldc2_w 0.6600000262260437
    //   264: dcmpg
    //   265: ifge -> 274
    //   268: getstatic net/minecraft/class_1259$class_1260.field_5782 : Lnet/minecraft/class_1259$class_1260;
    //   271: goto -> 277
    //   274: getstatic net/minecraft/class_1259$class_1260.field_5785 : Lnet/minecraft/class_1259$class_1260;
    //   277: invokevirtual method_5416 : (Lnet/minecraft/class_1259$class_1260;)V
    //   280: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   283: dup
    //   284: ifnonnull -> 302
    //   287: pop
    //   288: ldc_w -934543344
    //   291: i2c
    //   292: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   295: checkcast java/lang/String
    //   298: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   301: aconst_null
    //   302: ldc_w -1403191285
    //   305: i2c
    //   306: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   309: checkcast java/lang/String
    //   312: iconst_2
    //   313: anewarray java/lang/Object
    //   316: astore #14
    //   318: aload #14
    //   320: iconst_0
    //   321: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   324: dup
    //   325: ifnonnull -> 343
    //   328: pop
    //   329: ldc_w 369557520
    //   332: i2c
    //   333: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   336: checkcast java/lang/String
    //   339: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   342: aconst_null
    //   343: invokevirtual method_12955 : ()I
    //   346: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   349: aastore
    //   350: aload #14
    //   352: iconst_1
    //   353: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabo : Lnet/minecraft/class_3002;
    //   356: dup
    //   357: ifnonnull -> 375
    //   360: pop
    //   361: ldc_w -1629683696
    //   364: i2c
    //   365: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   368: checkcast java/lang/String
    //   371: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   374: aconst_null
    //   375: invokevirtual method_12960 : ()I
    //   378: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   381: aastore
    //   382: aload #14
    //   384: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   387: checkcast net/minecraft/class_2561
    //   390: invokevirtual method_5413 : (Lnet/minecraft/class_2561;)V
    //   393: return
  }
  
  private final void 你为什么要破解我的代码aaaaam() {
    aaaaab aaaaab = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj();
    String str1 = aaaaab.你为什么要破解我的代码aaaaac().你为什么要破解我的代码aaaaaa();
    if ((String)aaaaab.你为什么要破解我的代码aaaaam().get(str1) == null)
      (String)aaaaab.你为什么要破解我的代码aaaaam().get(str1); 
    String str2 = str1;
    int i = aaaaab.你为什么要破解我的代码aaaaaa();
    float f = 我草你怎么反编译我模组aaaaad / i;
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)-202440687)); 
    null.method_12954(我草你怎么反编译我模组aaaaad);
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)788135953)); 
    null.method_5416((f < 0.33F) ? class_1259.class_1260.field_5784 : ((f < 0.66F) ? class_1259.class_1260.field_5782 : class_1259.class_1260.field_5785));
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)-1844117487)); 
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = "§6§l" + str2;
    arrayOfObject[1] = Integer.valueOf(我草你怎么反编译我模组aaaaad);
    arrayOfObject[2] = Integer.valueOf(i);
    null.method_5413((class_2561)class_2561.method_43469((String)aaaaeR((char)-500498419), arrayOfObject));
  }
  
  private final void 你为什么要破解我的代码aaaabN(MinecraftServer paramMinecraftServer) {
    class_3004 class_30041 = paramMinecraftServer.method_3837();
    class_3004 class_30042 = class_30041;
    boolean bool = false;
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)-1627586545)); 
    我草你怎么反编译我模组aaaaaF.method_12973(null);
    if (我草你怎么反编译我模组aaaabo == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)-2140864496)); 
    我草你怎么反编译我模组aaaabo.method_12973(null);
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaaeR((char)1996357649)); 
    我草你怎么反编译我模组aaaaae.method_12973(null);
  }
  
  private final void 你为什么要破解我的代码aaaado() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 142
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 142
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 142
    //   21: checkcast java/lang/Iterable
    //   24: astore_1
    //   25: iconst_0
    //   26: istore_2
    //   27: aload_1
    //   28: invokeinterface iterator : ()Ljava/util/Iterator;
    //   33: astore_3
    //   34: aload_3
    //   35: invokeinterface hasNext : ()Z
    //   40: ifeq -> 138
    //   43: aload_3
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: astore #4
    //   51: aload #4
    //   53: checkcast net/minecraft/class_3222
    //   56: astore #5
    //   58: iconst_0
    //   59: istore #6
    //   61: aload #5
    //   63: invokevirtual method_7344 : ()Lnet/minecraft/class_1702;
    //   66: bipush #20
    //   68: invokevirtual method_7580 : (I)V
    //   71: aload #5
    //   73: new net/minecraft/class_1293
    //   76: dup
    //   77: getstatic net/minecraft/class_1294.field_5925 : Lnet/minecraft/class_6880;
    //   80: sipush #10000
    //   83: iconst_0
    //   84: iconst_0
    //   85: iconst_0
    //   86: invokespecial <init> : (Lnet/minecraft/class_6880;IIZZ)V
    //   89: invokevirtual method_6092 : (Lnet/minecraft/class_1293;)Z
    //   92: pop
    //   93: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabn : I
    //   96: ifne -> 120
    //   99: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   102: aload #5
    //   104: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   107: aload #5
    //   109: invokespecial 你为什么要破解我的代码aaaaeo : (Lnet/minecraft/class_3222;)V
    //   112: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   115: pop
    //   116: iconst_1
    //   117: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabn : I
    //   120: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabW.我草你怎么反编译我模组aaaacY : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabW;
    //   123: aload #5
    //   125: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   128: aload #5
    //   130: invokevirtual 你为什么要破解我的代码aaaakg : (Lnet/minecraft/class_3222;)V
    //   133: nop
    //   134: nop
    //   135: goto -> 34
    //   138: nop
    //   139: goto -> 144
    //   142: pop
    //   143: nop
    //   144: aload_0
    //   145: invokespecial 你为什么要破解我的代码aaaadm : ()V
    //   148: aload_0
    //   149: invokespecial 你为什么要破解我的代码aaaaes : ()V
    //   152: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaace : Z
    //   155: ifne -> 162
    //   158: aload_0
    //   159: invokespecial 你为什么要破解我的代码aaaadA : ()V
    //   162: aload_0
    //   163: invokespecial 你为什么要破解我的代码aaaaej : ()V
    //   166: return
  }
  
  private final Pair<class_3222, class_1308> 你为什么要破解我的代码aaaadp(double paramDouble) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabt : I
    //   3: iconst_1
    //   4: if_icmpeq -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   12: dup
    //   13: ifnull -> 40
    //   16: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   19: dup
    //   20: ifnull -> 40
    //   23: invokevirtual method_14571 : ()Ljava/util/List;
    //   26: dup
    //   27: ifnull -> 40
    //   30: invokestatic firstOrNull : (Ljava/util/List;)Ljava/lang/Object;
    //   33: checkcast net/minecraft/class_3222
    //   36: dup
    //   37: ifnonnull -> 43
    //   40: pop
    //   41: aconst_null
    //   42: areturn
    //   43: astore_3
    //   44: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   47: dup
    //   48: ifnull -> 57
    //   51: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   54: goto -> 59
    //   57: pop
    //   58: aconst_null
    //   59: dup
    //   60: ifnonnull -> 66
    //   63: pop
    //   64: aconst_null
    //   65: areturn
    //   66: astore #4
    //   68: new java/util/concurrent/CopyOnWriteArrayList
    //   71: dup
    //   72: invokespecial <init> : ()V
    //   75: astore #5
    //   77: nop
    //   78: aload #4
    //   80: astore #6
    //   82: aload #6
    //   84: monitorenter
    //   85: nop
    //   86: iconst_0
    //   87: istore #7
    //   89: aload #4
    //   91: ldc_w net/minecraft/class_1308
    //   94: aload_3
    //   95: invokevirtual method_19538 : ()Lnet/minecraft/class_243;
    //   98: dload_1
    //   99: ldc2_w 80.0
    //   102: ldc2_w 40.0
    //   105: invokestatic method_30048 : (Lnet/minecraft/class_243;DDD)Lnet/minecraft/class_238;
    //   108: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaV.我草你怎么反编译我模组aaaabs : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaV;
    //   111: checkcast kotlin/jvm/functions/Function1
    //   114: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
    //   119: invokevirtual method_8390 : (Ljava/lang/Class;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;
    //   122: astore #8
    //   124: aload #8
    //   126: ldc_w -1788739566
    //   129: i2c
    //   130: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   133: checkcast java/lang/String
    //   136: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   139: aload #8
    //   141: checkcast java/lang/Iterable
    //   144: astore #8
    //   146: nop
    //   147: iconst_0
    //   148: istore #9
    //   150: aload #8
    //   152: invokeinterface iterator : ()Ljava/util/Iterator;
    //   157: astore #10
    //   159: aload #10
    //   161: invokeinterface hasNext : ()Z
    //   166: ifeq -> 200
    //   169: aload #10
    //   171: invokeinterface next : ()Ljava/lang/Object;
    //   176: astore #11
    //   178: aload #11
    //   180: checkcast net/minecraft/class_1308
    //   183: astore #12
    //   185: iconst_0
    //   186: istore #13
    //   188: aload #5
    //   190: aload #12
    //   192: invokevirtual add : (Ljava/lang/Object;)Z
    //   195: pop
    //   196: nop
    //   197: goto -> 159
    //   200: nop
    //   201: nop
    //   202: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   205: astore #7
    //   207: aload #6
    //   209: monitorexit
    //   210: goto -> 221
    //   213: astore #7
    //   215: aload #6
    //   217: monitorexit
    //   218: aload #7
    //   220: athrow
    //   221: aload #5
    //   223: checkcast java/lang/Iterable
    //   226: astore #7
    //   228: nop
    //   229: iconst_0
    //   230: istore #8
    //   232: aload #7
    //   234: invokeinterface iterator : ()Ljava/util/Iterator;
    //   239: astore #9
    //   241: aload #9
    //   243: invokeinterface hasNext : ()Z
    //   248: ifne -> 255
    //   251: aconst_null
    //   252: goto -> 360
    //   255: aload #9
    //   257: invokeinterface next : ()Ljava/lang/Object;
    //   262: astore #10
    //   264: aload #9
    //   266: invokeinterface hasNext : ()Z
    //   271: ifne -> 279
    //   274: aload #10
    //   276: goto -> 360
    //   279: aload #10
    //   281: checkcast net/minecraft/class_1308
    //   284: astore #11
    //   286: iconst_0
    //   287: istore #12
    //   289: aload #11
    //   291: aload_3
    //   292: checkcast net/minecraft/class_1297
    //   295: invokevirtual method_5858 : (Lnet/minecraft/class_1297;)D
    //   298: dstore #14
    //   300: aload #9
    //   302: invokeinterface next : ()Ljava/lang/Object;
    //   307: astore #12
    //   309: aload #12
    //   311: checkcast net/minecraft/class_1308
    //   314: astore #13
    //   316: iconst_0
    //   317: istore #16
    //   319: aload #13
    //   321: aload_3
    //   322: checkcast net/minecraft/class_1297
    //   325: invokevirtual method_5858 : (Lnet/minecraft/class_1297;)D
    //   328: dstore #17
    //   330: dload #14
    //   332: dload #17
    //   334: invokestatic compare : (DD)I
    //   337: ifle -> 348
    //   340: aload #12
    //   342: astore #10
    //   344: dload #17
    //   346: dstore #14
    //   348: aload #9
    //   350: invokeinterface hasNext : ()Z
    //   355: ifne -> 300
    //   358: aload #10
    //   360: checkcast net/minecraft/class_1308
    //   363: astore #6
    //   365: aload #6
    //   367: ifnull -> 390
    //   370: aload #6
    //   372: astore #8
    //   374: iconst_0
    //   375: istore #9
    //   377: new kotlin/Pair
    //   380: dup
    //   381: aload_3
    //   382: aload #8
    //   384: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   387: goto -> 391
    //   390: aconst_null
    //   391: areturn
    //   392: astore #6
    //   394: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   397: ldc_w 1165033491
    //   400: i2c
    //   401: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   404: checkcast java/lang/String
    //   407: aload #6
    //   409: checkcast java/lang/Throwable
    //   412: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   417: aconst_null
    //   418: areturn
    // Exception table:
    //   from	to	target	type
    //   77	392	392	java/lang/Exception
    //   85	207	213	finally
    //   213	215	213	finally
  }
  
  private final void 你为什么要破解我的代码aaaadq() {
    if (我草你怎么反编译我模组aaaabt != 1)
      return; 
    try {
      Pair<class_3222, class_1308> pair1 = 你为什么要破解我的代码aaaadp(30.0D);
      Pair<class_3222, class_1308> pair2 = 你为什么要破解我的代码aaaadp(50.0D);
      long l = System.currentTimeMillis();
      String str1 = (pair2 != null && pair1 == null) ? (String)aaaaeR((char)-918487020) : ((pair2 == null) ? (String)aaaaeR((char)-1557069803) : (String)aaaaeR((char)-1940062186));
      synchronized (this) {
        boolean bool = false;
        if (!Intrinsics.areEqual(str1, 我草你怎么反编译我模组aaaabJ) && l - 我草你怎么反编译我模组aaaabI > 300L) {
          我草你怎么反编译我模组aaaabJ = str1;
          我草你怎么反编译我模组aaaabI = l;
        } 
        Unit unit = Unit.INSTANCE;
      } 
      String str2 = 我草你怎么反编译我模组aaaabJ;
      switch (str2.hashCode()) {
        case -1390363677:
          if (!str2.equals(aaaaeR((char)-1728053226)))
            break; 
          if (pair1 != null) {
            Pair<class_3222, class_1308> pair = pair1;
            boolean bool = false;
            我草你怎么反编译我模组aaaabk.你为什么要破解我的代码aaaads((class_3222)pair.getFirst());
            break;
          } 
          break;
        case 2555906:
          if (!str2.equals(aaaaeR((char)-1311309804)))
            break; 
          你为什么要破解我的代码aaaadt();
          break;
        case 40836773:
          if (!str2.equals(aaaaeR((char)959971349)))
            break; 
          你为什么要破解我的代码aaaadr();
          break;
      } 
      我草你怎么反编译我模组aaaabK = 我草你怎么反编译我模组aaaabJ;
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaeR((char)-721223657), exception);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadr() {
    class_3222 class_3222;
    if (我草你怎么反编译我模组aaaaab == null || 我草你怎么反编译我模组aaaaab.method_3760() == null || 我草你怎么反编译我模组aaaaab.method_3760().method_14571() == null || (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571()) == null) {
      (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571());
      return;
    } 
    long l = System.currentTimeMillis();
    if ((我草你怎么反编译我模组aaaach == 0.0D)) {
      我草你怎么反编译我模组aaaach = class_3222.method_23317();
      我草你怎么反编译我模组aaaaci = l;
    } else if (Math.abs(class_3222.method_23317() - 我草你怎么反编译我模组aaaach) < 0.01D) {
      if (l - 我草你怎么反编译我模组aaaaci > 我草你怎么反编译我模组aaaacj) {
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)-1020067816));
        aaaaab.你为什么要破解我的代码aaaaaa(class_3222, class_3222.method_23317(), -28.0D, 29.0D);
        我草你怎么反编译我模组aaaach = class_3222.method_23317();
        我草你怎么反编译我模组aaaaci = l;
        return;
      } 
    } else {
      我草你怎么反编译我模组aaaach = class_3222.method_23317();
      我草你怎么反编译我模组aaaaci = l;
    } 
    if (!我草你怎么反编译我模组aaaabE) {
      class_3222.field_13987.method_14363(class_3222.method_23317(), class_3222.method_23318(), class_3222.method_23321(), 90.0F, 0.0F);
      我草你怎么反编译我模组aaaabE = true;
    } 
    if (!我草你怎么反编译我模组aaaabG)
      try {
        我草你怎么反编译我模组aaaabP.EnumWindows(aaaaax::你为什么要破解我的代码aaaaeu, null);
      } catch (Exception exception) {
        aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaeR((char)1213595673), exception);
      }  
    if (你为什么要破解我的代码aaaadp(60.0D) == null) {
      float f = (class_3222.method_23321() > 30.0D) ? 95.0F : ((class_3222.method_23321() < 28.0D) ? 85.0F : 90.0F);
      if (!Intrinsics.areEqual(我草你怎么反编译我模组aaaabH, f)) {
        aaaaab.你为什么要破解我的代码aaaaab(我草你怎么反编译我模组aaaaab, class_3222, f, 0.0F);
        我草你怎么反编译我模组aaaabH = Float.valueOf(f);
      } 
    } 
  }
  
  private final void 你为什么要破解我的代码aaaads(class_3222 paramclass_3222) {
    if (我草你怎么反编译我模组aaaabE) {
      我草你怎么反编译我模组aaaabE = false;
      你为什么要破解我的代码aaaadt();
    } 
    我草你怎么反编译我模组aaaabF = true;
    if (paramclass_3222.method_23317() < 350.0D && ((paramclass_3222.method_23318() == -29.0D))) {
      paramclass_3222.method_18800(0.11D, 0.0D, 0.0D);
      paramclass_3222.field_6007 = true;
      paramclass_3222.field_6037 = true;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadt() {
    try {
      我草你怎么反编译我模组aaaabP.EnumWindows(aaaaax::你为什么要破解我的代码aaaaev, null);
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaeR((char)1284964377), exception);
    } 
  }
  
  public final void 你为什么要破解我的代码aaaadu() {
    if (我草你怎么反编译我模组aaaabt == 0) {
      我草你怎么反编译我模组aaaabt = 1;
      你为什么要破解我的代码aaaacF((String)aaaaeR((char)338493466));
    } else {
      我草你怎么反编译我模组aaaabt = 0;
      我草你怎么反编译我模组aaaabG = false;
      你为什么要破解我的代码aaaacF((String)aaaaeR((char)-1823801317));
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadv() {
    // Byte code:
    //   0: nop
    //   1: aload_0
    //   2: ldc2_w 130.0
    //   5: invokespecial 你为什么要破解我的代码aaaadp : (D)Lkotlin/Pair;
    //   8: astore_1
    //   9: aload_1
    //   10: ifnull -> 250
    //   13: aload_1
    //   14: invokevirtual component1 : ()Ljava/lang/Object;
    //   17: checkcast net/minecraft/class_3222
    //   20: astore_2
    //   21: aload_1
    //   22: invokevirtual component2 : ()Ljava/lang/Object;
    //   25: checkcast net/minecraft/class_1308
    //   28: astore_3
    //   29: aload_2
    //   30: astore #4
    //   32: aload #4
    //   34: monitorenter
    //   35: nop
    //   36: iconst_0
    //   37: istore #5
    //   39: aload_2
    //   40: invokevirtual method_6047 : ()Lnet/minecraft/class_1799;
    //   43: dup
    //   44: ifnull -> 104
    //   47: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
    //   50: dup
    //   51: ifnull -> 104
    //   54: invokevirtual method_7876 : ()Ljava/lang/String;
    //   57: astore #6
    //   59: aload #6
    //   61: dup
    //   62: ifnull -> 104
    //   65: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   68: aload #6
    //   70: checkcast java/lang/CharSequence
    //   73: ldc_w 334561308
    //   76: i2c
    //   77: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   80: checkcast java/lang/String
    //   83: checkcast java/lang/CharSequence
    //   86: iconst_0
    //   87: iconst_2
    //   88: aconst_null
    //   89: invokestatic contains$default : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z
    //   92: iconst_1
    //   93: if_icmpne -> 100
    //   96: iconst_1
    //   97: goto -> 106
    //   100: iconst_0
    //   101: goto -> 106
    //   104: pop
    //   105: iconst_0
    //   106: ifeq -> 115
    //   109: ldc2_w 0.6
    //   112: goto -> 118
    //   115: ldc2_w 0.8
    //   118: dstore #7
    //   120: aload_3
    //   121: invokevirtual method_23317 : ()D
    //   124: aload_2
    //   125: invokevirtual method_23317 : ()D
    //   128: dsub
    //   129: dstore #9
    //   131: aload_3
    //   132: invokevirtual method_23318 : ()D
    //   135: aload_3
    //   136: invokevirtual method_17682 : ()F
    //   139: f2d
    //   140: dload #7
    //   142: dmul
    //   143: dadd
    //   144: aload_2
    //   145: invokevirtual method_23318 : ()D
    //   148: aload_2
    //   149: invokevirtual method_5751 : ()F
    //   152: f2d
    //   153: dadd
    //   154: dsub
    //   155: dstore #11
    //   157: aload_3
    //   158: invokevirtual method_23321 : ()D
    //   161: aload_2
    //   162: invokevirtual method_23321 : ()D
    //   165: dsub
    //   166: dstore #13
    //   168: dload #9
    //   170: dload #9
    //   172: dmul
    //   173: dload #13
    //   175: dload #13
    //   177: dmul
    //   178: dadd
    //   179: invokestatic sqrt : (D)D
    //   182: dstore #15
    //   184: dload #9
    //   186: dneg
    //   187: dload #13
    //   189: invokestatic atan2 : (DD)D
    //   192: invokestatic toDegrees : (D)D
    //   195: d2f
    //   196: fstore #17
    //   198: dload #11
    //   200: dload #15
    //   202: invokestatic atan2 : (DD)D
    //   205: dneg
    //   206: invokestatic toDegrees : (D)D
    //   209: d2f
    //   210: fstore #18
    //   212: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   215: aload_2
    //   216: fload #17
    //   218: fload #18
    //   220: invokestatic 你为什么要破解我的代码aaaaab : (Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/class_3222;FF)V
    //   223: nop
    //   224: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   227: astore #5
    //   229: aload #4
    //   231: monitorexit
    //   232: goto -> 243
    //   235: astore #5
    //   237: aload #4
    //   239: monitorexit
    //   240: aload #5
    //   242: athrow
    //   243: aload_0
    //   244: invokespecial 你为什么要破解我的代码aaaadw : ()V
    //   247: goto -> 315
    //   250: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabF : Z
    //   253: ifeq -> 315
    //   256: iconst_0
    //   257: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabF : Z
    //   260: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabP : Lcom/sun/jna/platform/win32/User32;
    //   263: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabZ : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   266: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabV : I
    //   269: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   272: dup
    //   273: lconst_0
    //   274: invokespecial <init> : (J)V
    //   277: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabY : Lcom/sun/jna/platform/win32/WinDef$LPARAM;
    //   280: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   285: iconst_0
    //   286: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabL : Z
    //   289: goto -> 315
    //   292: astore_1
    //   293: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   296: ldc_w 596967453
    //   299: i2c
    //   300: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   303: checkcast java/lang/String
    //   306: aload_1
    //   307: checkcast java/lang/Throwable
    //   310: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   315: return
    // Exception table:
    //   from	to	target	type
    //   0	289	292	java/lang/Exception
    //   35	229	235	finally
    //   235	237	235	finally
  }
  
  private final void 你为什么要破解我的代码aaaadw() {
    long l = System.currentTimeMillis();
    我草你怎么反编译我模组aaaaab.method_3760().method_14571();
    class_3222 class_3222 = (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null && 我草你怎么反编译我模组aaaaab.method_3760().method_14571() != null) ? (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571()) : null;
    class_3222.method_6047().method_7909();
    if (((class_3222 != null && class_3222.method_6047() != null && class_3222.method_6047().method_7909() != null) ? class_3222.method_6047().method_7909().method_7876() : null) == null)
      (class_3222 != null && class_3222.method_6047() != null && class_3222.method_6047().method_7909() != null) ? class_3222.method_6047().method_7909().method_7876() : null; 
    String str = (String)aaaaeR((char)-2105540578);
    boolean bool = StringsKt.contains$default(str, (String)aaaaeR((char)1129709599), false, 2, null) ? true : (StringsKt.contains$default(str, (String)aaaaeR((char)1136656416), false, 2, null) ? true : false);
    if (我草你怎么反编译我模组aaaabN)
      return; 
    if (bool) {
      if (我草你怎么反编译我模组aaaacd) {
        if (我草你怎么反编译我模组aaaabM == 0L) {
          我草你怎么反编译我模组aaaabM = l;
          aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)779026465));
          return;
        } 
        switch (bool) {
          case true:
          
          case true:
          
          default:
            break;
        } 
        long l1 = 0L;
        if (l - 我草你怎么反编译我模组aaaabM < l1) {
          aaaaaa.你为什么要破解我的代码aaaaaa().info("武器首次准备中... 剩余时间: " + l1 - l - 我草你怎么反编译我模组aaaabM + "ms");
          return;
        } 
        我草你怎么反编译我模组aaaacd = false;
        我草你怎么反编译我模组aaaabM = 0L;
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)-706150366));
      } 
      if (我草你怎么反编译我模组aaaace) {
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)1688272931));
        return;
      } 
      if (l - 我草你怎么反编译我模组aaaabO < 101L && Intrinsics.areEqual(我草你怎么反编译我模组aaaacg, str)) {
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)1988755492));
        return;
      } 
    } 
    我草你怎么反编译我模组aaaacc = str;
    aaaaaa.你为什么要破解我的代码aaaaaa().info("开始模拟鼠标点击, 持有武器: " + str + ", 是否重型武器: " + bool);
    try {
      User32 user32 = User32.INSTANCE;
      if (我草你怎么反编译我模组aaaabZ == null) {
        Intrinsics.checkNotNull(user32);
        你为什么要破解我的代码aaaadx(user32);
      } 
      WinDef.HWND hWND = 我草你怎么反编译我模组aaaabZ;
      boolean bool1 = false;
      Job job = BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, null, null, new aaaabu(l, user32, hWND, bool, null), 3, null);
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaeR((char)1292501029), exception);
      我草你怎么反编译我模组aaaabN = false;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadx(User32 paramUser32) {
    paramUser32.EnumWindows(paramUser32::你为什么要破解我的代码aaaaew, null);
  }
  
  private final void 你为什么要破解我的代码aaaady(User32 paramUser32, WinDef.HWND paramHWND) {
    paramUser32.PostMessage(paramHWND, 我草你怎么反编译我模组aaaabX, new WinDef.WPARAM(0L), 我草你怎么反编译我模组aaaabY);
    我草你怎么反编译我模组aaaabN = false;
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)-1940717530));
  }
  
  private final void 你为什么要破解我的代码aaaadz() {
    synchronized (我草你怎么反编译我模组aaaacf) {
      boolean bool = false;
      我草你怎么反编译我模组aaaacf.clear();
      Unit unit = Unit.INSTANCE;
    } 
    我草你怎么反编译我模组aaaabN = false;
  }
  
  private final void 你为什么要破解我的代码aaaadA() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabN : Z
    //   3: ifeq -> 12
    //   6: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabZ : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   9: ifnonnull -> 13
    //   12: return
    //   13: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   16: dup
    //   17: ifnull -> 44
    //   20: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   23: dup
    //   24: ifnull -> 44
    //   27: invokevirtual method_14571 : ()Ljava/util/List;
    //   30: dup
    //   31: ifnull -> 44
    //   34: invokestatic firstOrNull : (Ljava/util/List;)Ljava/lang/Object;
    //   37: checkcast net/minecraft/class_3222
    //   40: dup
    //   41: ifnonnull -> 46
    //   44: pop
    //   45: return
    //   46: astore_1
    //   47: aload_1
    //   48: invokevirtual method_6047 : ()Lnet/minecraft/class_1799;
    //   51: dup
    //   52: ifnull -> 68
    //   55: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
    //   58: dup
    //   59: ifnull -> 68
    //   62: invokevirtual method_7876 : ()Ljava/lang/String;
    //   65: goto -> 70
    //   68: pop
    //   69: aconst_null
    //   70: dup
    //   71: ifnonnull -> 85
    //   74: pop
    //   75: ldc_w -93061090
    //   78: i2c
    //   79: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   82: checkcast java/lang/String
    //   85: astore_2
    //   86: aload_2
    //   87: checkcast java/lang/CharSequence
    //   90: ldc_w 1652555807
    //   93: i2c
    //   94: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   97: checkcast java/lang/String
    //   100: checkcast java/lang/CharSequence
    //   103: iconst_0
    //   104: iconst_2
    //   105: aconst_null
    //   106: invokestatic contains$default : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z
    //   109: ifne -> 138
    //   112: aload_2
    //   113: checkcast java/lang/CharSequence
    //   116: ldc_w 992084000
    //   119: i2c
    //   120: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   123: checkcast java/lang/String
    //   126: checkcast java/lang/CharSequence
    //   129: iconst_0
    //   130: iconst_2
    //   131: aconst_null
    //   132: invokestatic contains$default : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z
    //   135: ifeq -> 142
    //   138: iconst_1
    //   139: goto -> 143
    //   142: iconst_0
    //   143: istore_3
    //   144: aload_0
    //   145: ldc2_w 150.0
    //   148: invokespecial 你为什么要破解我的代码aaaadp : (D)Lkotlin/Pair;
    //   151: astore #4
    //   153: iload_3
    //   154: ifeq -> 289
    //   157: aload #4
    //   159: ifnonnull -> 289
    //   162: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacf : Ljava/util/HashSet;
    //   165: astore #5
    //   167: aload #5
    //   169: monitorenter
    //   170: nop
    //   171: iconst_0
    //   172: istore #6
    //   174: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacf : Ljava/util/HashSet;
    //   177: checkcast java/lang/Iterable
    //   180: astore #7
    //   182: iconst_0
    //   183: istore #8
    //   185: aload #7
    //   187: invokeinterface iterator : ()Ljava/util/Iterator;
    //   192: astore #9
    //   194: aload #9
    //   196: invokeinterface hasNext : ()Z
    //   201: ifeq -> 236
    //   204: aload #9
    //   206: invokeinterface next : ()Ljava/lang/Object;
    //   211: astore #10
    //   213: aload #10
    //   215: checkcast kotlinx/coroutines/Job
    //   218: astore #11
    //   220: iconst_0
    //   221: istore #12
    //   223: aload #11
    //   225: aconst_null
    //   226: iconst_1
    //   227: aconst_null
    //   228: invokestatic cancel$default : (Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
    //   231: nop
    //   232: nop
    //   233: goto -> 194
    //   236: nop
    //   237: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacf : Ljava/util/HashSet;
    //   240: invokevirtual clear : ()V
    //   243: nop
    //   244: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   247: astore #6
    //   249: aload #5
    //   251: monitorexit
    //   252: goto -> 263
    //   255: astore #6
    //   257: aload #5
    //   259: monitorexit
    //   260: aload #6
    //   262: athrow
    //   263: getstatic kotlinx/coroutines/GlobalScope.INSTANCE : Lkotlinx/coroutines/GlobalScope;
    //   266: checkcast kotlinx/coroutines/CoroutineScope
    //   269: aconst_null
    //   270: aconst_null
    //   271: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabV
    //   274: dup
    //   275: aload_2
    //   276: aconst_null
    //   277: invokespecial <init> : (Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    //   280: checkcast kotlin/jvm/functions/Function2
    //   283: iconst_3
    //   284: aconst_null
    //   285: invokestatic launch$default : (Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;
    //   288: pop
    //   289: return
    // Exception table:
    //   from	to	target	type
    //   170	249	255	finally
    //   255	257	255	finally
  }
  
  private final void 你为什么要破解我的代码aaaadB() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabt : I
    //   3: ifne -> 7
    //   6: return
    //   7: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   10: dup
    //   11: ifnull -> 38
    //   14: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   17: dup
    //   18: ifnull -> 38
    //   21: invokevirtual method_14571 : ()Ljava/util/List;
    //   24: dup
    //   25: ifnull -> 38
    //   28: invokestatic firstOrNull : (Ljava/util/List;)Ljava/lang/Object;
    //   31: checkcast net/minecraft/class_3222
    //   34: dup
    //   35: ifnonnull -> 40
    //   38: pop
    //   39: return
    //   40: astore_1
    //   41: aload_1
    //   42: invokevirtual method_6047 : ()Lnet/minecraft/class_1799;
    //   45: dup
    //   46: ifnull -> 62
    //   49: invokevirtual method_7909 : ()Lnet/minecraft/class_1792;
    //   52: dup
    //   53: ifnull -> 62
    //   56: invokevirtual method_7876 : ()Ljava/lang/String;
    //   59: goto -> 64
    //   62: pop
    //   63: aconst_null
    //   64: dup
    //   65: ifnonnull -> 79
    //   68: pop
    //   69: ldc_w -1025769442
    //   72: i2c
    //   73: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   76: checkcast java/lang/String
    //   79: astore_2
    //   80: aload_2
    //   81: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacg : Ljava/lang/String;
    //   84: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   87: ifne -> 271
    //   90: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacf : Ljava/util/HashSet;
    //   93: astore_3
    //   94: aload_3
    //   95: monitorenter
    //   96: nop
    //   97: iconst_0
    //   98: istore #4
    //   100: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacf : Ljava/util/HashSet;
    //   103: checkcast java/lang/Iterable
    //   106: astore #5
    //   108: iconst_0
    //   109: istore #6
    //   111: aload #5
    //   113: invokeinterface iterator : ()Ljava/util/Iterator;
    //   118: astore #7
    //   120: aload #7
    //   122: invokeinterface hasNext : ()Z
    //   127: ifeq -> 162
    //   130: aload #7
    //   132: invokeinterface next : ()Ljava/lang/Object;
    //   137: astore #8
    //   139: aload #8
    //   141: checkcast kotlinx/coroutines/Job
    //   144: astore #9
    //   146: iconst_0
    //   147: istore #10
    //   149: aload #9
    //   151: aconst_null
    //   152: iconst_1
    //   153: aconst_null
    //   154: invokestatic cancel$default : (Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V
    //   157: nop
    //   158: nop
    //   159: goto -> 120
    //   162: nop
    //   163: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacf : Ljava/util/HashSet;
    //   166: invokevirtual clear : ()V
    //   169: nop
    //   170: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   173: astore #4
    //   175: aload_3
    //   176: monitorexit
    //   177: goto -> 187
    //   180: astore #4
    //   182: aload_3
    //   183: monitorexit
    //   184: aload #4
    //   186: athrow
    //   187: iconst_1
    //   188: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacd : Z
    //   191: aload_2
    //   192: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaacg : Ljava/lang/String;
    //   195: iconst_0
    //   196: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabL : Z
    //   199: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabP : Lcom/sun/jna/platform/win32/User32;
    //   202: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabZ : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   205: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabV : I
    //   208: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   211: dup
    //   212: lconst_0
    //   213: invokespecial <init> : (J)V
    //   216: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabY : Lcom/sun/jna/platform/win32/WinDef$LPARAM;
    //   219: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   224: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabP : Lcom/sun/jna/platform/win32/User32;
    //   227: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabZ : Lcom/sun/jna/platform/win32/WinDef$HWND;
    //   230: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabX : I
    //   233: new com/sun/jna/platform/win32/WinDef$WPARAM
    //   236: dup
    //   237: lconst_0
    //   238: invokespecial <init> : (J)V
    //   241: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabY : Lcom/sun/jna/platform/win32/WinDef$LPARAM;
    //   244: invokeinterface PostMessage : (Lcom/sun/jna/platform/win32/WinDef$HWND;ILcom/sun/jna/platform/win32/WinDef$WPARAM;Lcom/sun/jna/platform/win32/WinDef$LPARAM;)V
    //   249: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   252: ldc_w -2033516505
    //   255: i2c
    //   256: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   259: checkcast java/lang/String
    //   262: invokeinterface info : (Ljava/lang/String;)V
    //   267: lconst_0
    //   268: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabM : J
    //   271: return
    // Exception table:
    //   from	to	target	type
    //   96	175	180	finally
    //   180	182	180	finally
  }
  
  private final boolean 你为什么要破解我的代码aaaadC(class_1308 paramclass_1308) {
    Intrinsics.checkNotNullExpressionValue(class_1299.method_5890(paramclass_1308.method_5864()).toString(), (String)aaaaeR((char)-765132760));
    String str = class_1299.method_5890(paramclass_1308.method_5864()).toString();
    switch (str.hashCode()) {
      case -1237731462:
        if (!str.equals(aaaaeR((char)995688489)))
          break; 
      case 1912691396:
        if (!str.equals(aaaaeR((char)-861208534)))
          break; 
      case -596960109:
        if (!str.equals(aaaaeR((char)1686437931)))
          break; 
      case -185046879:
        if (!str.equals(aaaaeR((char)-1650851796)))
          break; 
      case -688174496:
        if (str.equals(aaaaeR((char)-1608777683)));
        break;
    } 
    return false;
  }
  
  private final void 你为什么要破解我的代码aaaadD() {
    if (我草你怎么反编译我模组aaaaah % 2 == 0)
      if (!我草你怎么反编译我模组aaaabw) {
        你为什么要破解我的代码aaaadE();
      } else {
        你为什么要破解我的代码aaaadG();
      }  
  }
  
  private final void 你为什么要破解我的代码aaaadE() {
    class_3222 class_3222 = (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571());
    boolean bool = false;
    double d = class_3222.method_23317() - 我草你怎么反编译我模组aaaabu;
    if (d < 100.0D) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaeR((char)-1006567378));
      我草你怎么反编译我模组aaaabk.你为什么要破解我的代码aaaadF();
    } 
    (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571());
  }
  
  private final void 你为什么要破解我的代码aaaadF() {
    我草你怎么反编译我模组aaaabw = true;
    aaaaaa.你为什么要破解我的代码aaaaaa().info("开始扩展边界: 从X=" + 我草你怎么反编译我模组aaaabx + " 开始复制");
  }
  
  private final void 你为什么要破解我的代码aaaadG() {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i;
    for (i = -34; i < 3; i++) {
      for (byte b = 15; b < 43; b++) {
        class_2338 class_23381 = new class_2338(我草你怎么反编译我模组aaaabx, i, b);
        class_2338 class_23382 = new class_2338(我草你怎么反编译我模组aaaabx - 210, i, b);
        你为什么要破解我的代码aaaadH((class_3218)object, class_23381, class_23382);
      } 
    } 
    i = 我草你怎么反编译我模组aaaabx;
    我草你怎么反编译我模组aaaabx = i + -1;
    if (我草你怎么反编译我模组aaaabx < 我草你怎么反编译我模组aaaabD) {
      我草你怎么反编译我模组aaaabw = false;
      i = 我草你怎么反编译我模组aaaabu;
      我草你怎么反编译我模组aaaabD -= 210;
      我草你怎么反编译我模组aaaabu -= 210;
      aaaaaa.你为什么要破解我的代码aaaaaa().info("完成边界扩展: 边界从" + i + " 更新到" + 我草你怎么反编译我模组aaaabu);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadH(class_3218 paramclass_3218, class_2338 paramclass_23381, class_2338 paramclass_23382) {
    try {
      class_2680 class_2680 = paramclass_3218.method_8320(paramclass_23381);
      paramclass_3218.method_8652(paramclass_23382, class_2680, 3);
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaeR((char)1207566383), exception);
    } 
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaar(@NotNull LiveGiftEvent paramLiveGiftEvent) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w -55836667
    //   4: i2c
    //   5: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokevirtual getGiftCount : ()I
    //   18: istore_2
    //   19: iload_2
    //   20: ifle -> 331
    //   23: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   26: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   29: invokestatic 你为什么要破解我的代码aaaaad : ()Lkotlin/enums/EnumEntries;
    //   32: checkcast java/lang/Iterable
    //   35: astore_3
    //   36: astore #4
    //   38: aload_3
    //   39: astore #5
    //   41: aload #5
    //   43: invokeinterface iterator : ()Ljava/util/Iterator;
    //   48: astore #6
    //   50: aload #6
    //   52: invokeinterface hasNext : ()Z
    //   57: ifeq -> 99
    //   60: aload #6
    //   62: invokeinterface next : ()Ljava/lang/Object;
    //   67: astore #7
    //   69: aload #7
    //   71: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF
    //   74: astore #8
    //   76: iconst_0
    //   77: istore #9
    //   79: aload #8
    //   81: invokevirtual 你为什么要破解我的代码aaaaaP : ()Ljava/lang/String;
    //   84: aload_1
    //   85: invokevirtual getLiveType : ()Ljava/lang/String;
    //   88: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   91: ifeq -> 50
    //   94: aload #7
    //   96: goto -> 100
    //   99: aconst_null
    //   100: aload #4
    //   102: swap
    //   103: dup
    //   104: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   107: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF
    //   110: invokevirtual 你为什么要破解我的代码aaaaac : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;)Ljava/util/List;
    //   113: astore #10
    //   115: aload #10
    //   117: invokeinterface isEmpty : ()Z
    //   122: ifeq -> 126
    //   125: return
    //   126: aload #10
    //   128: checkcast java/lang/Iterable
    //   131: astore #9
    //   133: aload #9
    //   135: invokeinterface iterator : ()Ljava/util/Iterator;
    //   140: astore #11
    //   142: aload #11
    //   144: invokeinterface hasNext : ()Z
    //   149: ifeq -> 193
    //   152: aload #11
    //   154: invokeinterface next : ()Ljava/lang/Object;
    //   159: astore #12
    //   161: aload #12
    //   163: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   166: astore #13
    //   168: iconst_0
    //   169: istore #14
    //   171: aload #13
    //   173: invokeinterface 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   178: aload_1
    //   179: invokevirtual getGiftName : ()Ljava/lang/String;
    //   182: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   185: ifeq -> 142
    //   188: aload #12
    //   190: goto -> 194
    //   193: aconst_null
    //   194: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabK
    //   197: dup
    //   198: ifnull -> 209
    //   201: invokeinterface 你为什么要破解我的代码aaaaag : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabd;
    //   206: goto -> 211
    //   209: pop
    //   210: aconst_null
    //   211: astore #6
    //   213: aload #6
    //   215: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
    //   218: ifeq -> 229
    //   221: aload #6
    //   223: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
    //   226: goto -> 230
    //   229: aconst_null
    //   230: dup
    //   231: ifnonnull -> 236
    //   234: pop
    //   235: return
    //   236: astore_3
    //   237: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   240: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   243: invokevirtual 你为什么要破解我的代码aaaaao : ()Z
    //   246: ifeq -> 256
    //   249: aload_1
    //   250: invokevirtual getUsername : ()Ljava/lang/String;
    //   253: goto -> 266
    //   256: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai;
    //   259: aload_1
    //   260: invokevirtual getUsername : ()Ljava/lang/String;
    //   263: invokevirtual 你为什么要破解我的代码aaaaaa : (Ljava/lang/String;)Ljava/lang/String;
    //   266: astore #5
    //   268: aload_3
    //   269: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   272: iload_2
    //   273: imul
    //   274: istore #6
    //   276: aload_0
    //   277: aload_3
    //   278: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   281: iload #6
    //   283: aload #5
    //   285: aload_1
    //   286: invokevirtual getGiftName : ()Ljava/lang/String;
    //   289: invokespecial 你为什么要破解我的代码aaaabs : (Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   292: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaK : I
    //   295: aload_3
    //   296: invokevirtual 你为什么要破解我的代码aaaaag : ()I
    //   299: iload_2
    //   300: imul
    //   301: iadd
    //   302: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaK : I
    //   305: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   308: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   311: invokevirtual 你为什么要破解我的代码aaaaam : ()Z
    //   314: ifeq -> 331
    //   317: aload_0
    //   318: aload #5
    //   320: aload_1
    //   321: invokevirtual getGiftName : ()Ljava/lang/String;
    //   324: aload_1
    //   325: invokevirtual getGiftCount : ()I
    //   328: invokespecial 你为什么要破解我的代码aaaaau : (Ljava/lang/String;Ljava/lang/String;I)V
    //   331: return
  }
  
  private final void 你为什么要破解我的代码aaaabs(String paramString1, int paramInt, String paramString2, String paramString3) {
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac(paramString1 + "_事件音效");
    switch (paramString1.hashCode()) {
      case 687755:
        if (!paramString1.equals(aaaaeR((char)-1377959888)))
          break; 
        你为什么要破解我的代码aaaadR(paramString2, paramInt);
        break;
      case 666083:
        if (!paramString1.equals(aaaaeR((char)-706478031)))
          break; 
        你为什么要破解我的代码aaaadO(paramString2, paramInt);
        break;
      case 974368:
        if (!paramString1.equals(aaaaeR((char)-727842766)))
          break; 
        你为什么要破解我的代码aaaabu(paramString2, paramInt);
        break;
      case 955568538:
        if (!paramString1.equals(aaaaeR((char)-988282829)))
          break; 
        你为什么要破解我的代码aaaadQ(paramString2, paramInt);
        break;
      case 1061758:
        if (!paramString1.equals(aaaaeR((char)-1380777932)))
          break; 
        你为什么要破解我的代码aaaabm(paramString2, paramInt);
        break;
      case 1164461369:
        if (!paramString1.equals(aaaaeR((char)351797301)))
          break; 
        你为什么要破解我的代码aaaadU(paramString2, paramInt);
        break;
      case 385942000:
        if (!paramString1.equals(aaaaeR((char)512491574)))
          break; 
        你为什么要破解我的代码aaaadL(paramString2, paramInt);
        break;
      case 694408400:
        if (!paramString1.equals(aaaaeR((char)-1120796617)))
          break; 
        你为什么要破解我的代码aaaadW(paramString2, paramInt);
        break;
      case 385920819:
        if (!paramString1.equals(aaaaeR((char)-470548424)))
          break; 
        你为什么要破解我的代码aaaadM(paramString2, paramInt);
        break;
      case 32883264:
        if (!paramString1.equals(aaaaeR((char)129957945)))
          break; 
        你为什么要破解我的代码aaaadP(paramString2, paramInt);
        break;
      case 1247358190:
        if (!paramString1.equals(aaaaeR((char)-1218314182)))
          break; 
        你为什么要破解我的代码aaaadV(paramString2, paramInt);
        break;
      case 37255458:
        if (!paramString1.equals(aaaaeR((char)-297402309)))
          break; 
        你为什么要破解我的代码aaaadT(paramString2, paramInt);
        break;
      case 83226:
        if (!paramString1.equals(aaaaeR((char)-1521287108)))
          break; 
        你为什么要破解我的代码aaaadZ(paramString2, paramInt);
        break;
      case 1267438:
        if (!paramString1.equals(aaaaeR((char)1572667453)))
          break; 
        你为什么要破解我的代码aaaadS(paramString2, paramInt);
        break;
      case -425008076:
        if (!paramString1.equals(aaaaeR((char)1012072510)))
          break; 
        你为什么要破解我的代码aaaadX(paramString2, paramInt);
        break;
    } 
    if (!Intrinsics.areEqual(paramString1, aaaaeR((char)1899888690)))
      你为什么要破解我的代码aaaaat(paramString3, paramInt, paramString2, paramString1); 
  }
  
  private final void 你为什么要破解我的代码aaaabu(String paramString, int paramInt) {
    int i = paramInt;
    Intrinsics.checkNotNull(aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaa(), (String)aaaaeR((char)-730791873));
    List<aaaabL> list = ((aaaaab)aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaa()).你为什么要破解我的代码aaaaai();
    if (list.isEmpty())
      return; 
    aaaabj aaaabj = new aaaabj(0, 0, null, 7, null);
    byte b = 30;
    if (我草你怎么反编译我模组aaaaab != null) {
      你为什么要破解我的代码aaaacV(我草你怎么反编译我模组aaaaab, new aaaaau(aaaabj, b, list, paramString, i));
    } else {
    
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadJ(aaaabL paramaaaabL, String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   4: astore_3
    //   5: aload_1
    //   6: invokevirtual 你为什么要破解我的代码aaaaap : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaai;
    //   9: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   12: istore #4
    //   14: aload_1
    //   15: invokevirtual 你为什么要破解我的代码aaaaap : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaai;
    //   18: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   21: istore #5
    //   23: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaK : I
    //   26: iload #5
    //   28: iadd
    //   29: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaK : I
    //   32: aload_0
    //   33: aload_3
    //   34: iload #4
    //   36: aload_2
    //   37: aconst_null
    //   38: bipush #8
    //   40: aconst_null
    //   41: invokestatic 你为什么要破解我的代码aaaadI : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/Object;)V
    //   44: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   47: dup
    //   48: ifnull -> 237
    //   51: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   54: dup
    //   55: ifnull -> 237
    //   58: invokevirtual method_14571 : ()Ljava/util/List;
    //   61: dup
    //   62: ifnull -> 237
    //   65: checkcast java/lang/Iterable
    //   68: astore #6
    //   70: iconst_0
    //   71: istore #7
    //   73: aload #6
    //   75: invokeinterface iterator : ()Ljava/util/Iterator;
    //   80: astore #8
    //   82: aload #8
    //   84: invokeinterface hasNext : ()Z
    //   89: ifeq -> 233
    //   92: aload #8
    //   94: invokeinterface next : ()Ljava/lang/Object;
    //   99: astore #9
    //   101: aload #9
    //   103: checkcast net/minecraft/class_3222
    //   106: astore #10
    //   108: iconst_0
    //   109: istore #11
    //   111: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   114: aload #10
    //   116: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   119: aload #10
    //   121: ldc_w 911671360
    //   124: i2c
    //   125: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   128: checkcast java/lang/String
    //   131: iconst_1
    //   132: anewarray java/lang/Object
    //   135: astore #12
    //   137: aload #12
    //   139: iconst_0
    //   140: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   143: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   146: aload_3
    //   147: invokevirtual 你为什么要破解我的代码aaaaak : (Ljava/lang/String;)Ljava/lang/String;
    //   150: iload #4
    //   152: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   157: aastore
    //   158: aload #12
    //   160: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   163: checkcast net/minecraft/class_2561
    //   166: ldc_w 1430126657
    //   169: i2c
    //   170: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   173: checkcast java/lang/String
    //   176: iconst_2
    //   177: anewarray java/lang/Object
    //   180: astore #12
    //   182: aload #12
    //   184: iconst_0
    //   185: aload_2
    //   186: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   191: aastore
    //   192: aload #12
    //   194: iconst_1
    //   195: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   198: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   201: aload_3
    //   202: invokevirtual 你为什么要破解我的代码aaaaak : (Ljava/lang/String;)Ljava/lang/String;
    //   205: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   210: aastore
    //   211: aload #12
    //   213: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   216: checkcast net/minecraft/class_2561
    //   219: bipush #10
    //   221: bipush #70
    //   223: bipush #20
    //   225: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   228: nop
    //   229: nop
    //   230: goto -> 82
    //   233: nop
    //   234: goto -> 239
    //   237: pop
    //   238: nop
    //   239: aload_0
    //   240: aload_1
    //   241: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   244: aload_2
    //   245: invokespecial 你为什么要破解我的代码aaaabw : (Ljava/lang/String;Ljava/lang/String;)V
    //   248: return
  }
  
  private final void 你为什么要破解我的代码aaaabw(String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc_w -2064252862
    //   3: i2c
    //   4: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   7: checkcast java/lang/String
    //   10: iconst_2
    //   11: anewarray java/lang/Object
    //   14: astore_3
    //   15: aload_3
    //   16: iconst_0
    //   17: aload_2
    //   18: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   23: aastore
    //   24: aload_3
    //   25: iconst_1
    //   26: aload_1
    //   27: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   32: aastore
    //   33: aload_3
    //   34: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   37: astore #4
    //   39: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   42: dup
    //   43: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   46: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   49: invokevirtual method_14571 : ()Ljava/util/List;
    //   52: dup
    //   53: ldc_w -1140654066
    //   56: i2c
    //   57: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   60: checkcast java/lang/String
    //   63: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   66: checkcast java/lang/Iterable
    //   69: astore_3
    //   70: iconst_0
    //   71: istore #5
    //   73: aload_3
    //   74: invokeinterface iterator : ()Ljava/util/Iterator;
    //   79: astore #6
    //   81: aload #6
    //   83: invokeinterface hasNext : ()Z
    //   88: ifeq -> 125
    //   91: aload #6
    //   93: invokeinterface next : ()Ljava/lang/Object;
    //   98: astore #7
    //   100: aload #7
    //   102: checkcast net/minecraft/class_3222
    //   105: astore #8
    //   107: iconst_0
    //   108: istore #9
    //   110: aload #8
    //   112: aload #4
    //   114: checkcast net/minecraft/class_2561
    //   117: invokevirtual method_43496 : (Lnet/minecraft/class_2561;)V
    //   120: nop
    //   121: nop
    //   122: goto -> 81
    //   125: nop
    //   126: return
  }
  
  public final void 你为什么要破解我的代码aaaaat(@NotNull String paramString1, int paramInt, @NotNull String paramString2, @NotNull String paramString3) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 828899395
    //   4: i2c
    //   5: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_3
    //   15: ldc_w 2111963204
    //   18: i2c
    //   19: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   28: aload #4
    //   30: ldc_w -67108795
    //   33: i2c
    //   34: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   37: checkcast java/lang/String
    //   40: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   43: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   46: dup
    //   47: ifnull -> 137
    //   50: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   53: dup
    //   54: ifnull -> 137
    //   57: invokevirtual method_14571 : ()Ljava/util/List;
    //   60: dup
    //   61: ifnull -> 137
    //   64: checkcast java/lang/Iterable
    //   67: astore #5
    //   69: iconst_0
    //   70: istore #6
    //   72: aload #5
    //   74: invokeinterface iterator : ()Ljava/util/Iterator;
    //   79: astore #7
    //   81: aload #7
    //   83: invokeinterface hasNext : ()Z
    //   88: ifeq -> 133
    //   91: aload #7
    //   93: invokeinterface next : ()Ljava/lang/Object;
    //   98: astore #8
    //   100: aload #8
    //   102: checkcast net/minecraft/class_3222
    //   105: astore #9
    //   107: iconst_0
    //   108: istore #10
    //   110: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   113: aload #9
    //   115: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   118: aload #9
    //   120: aload_1
    //   121: iload_2
    //   122: aload_3
    //   123: aload #4
    //   125: invokevirtual 你为什么要破解我的代码aaaadK : (Lnet/minecraft/class_3222;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   128: nop
    //   129: nop
    //   130: goto -> 81
    //   133: nop
    //   134: goto -> 139
    //   137: pop
    //   138: nop
    //   139: return
  }
  
  public final void 你为什么要破解我的代码aaaadK(@NotNull class_3222 paramclass_3222, @NotNull String paramString1, int paramInt, @NotNull String paramString2, @NotNull String paramString3) {
    Intrinsics.checkNotNullParameter(paramclass_3222, (String)aaaaeR((char)-1794310074));
    Intrinsics.checkNotNullParameter(paramString1, (String)aaaaeR((char)-248512445));
    Intrinsics.checkNotNullParameter(paramString2, (String)aaaaeR((char)668467268));
    Intrinsics.checkNotNullParameter(paramString3, (String)aaaaeR((char)-1685782459));
    BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, null, null, new aaaaab(paramString3, paramString2, paramString1, paramInt, paramclass_3222, null), 3, null);
  }
  
  private final void 你为什么要破解我的代码aaaadL(String paramString, int paramInt) {
    我草你怎么反编译我模组aaaabp -= paramInt;
  }
  
  private final void 你为什么要破解我的代码aaaadM(String paramString, int paramInt) {
    if (我草你怎么反编译我模组aaaabp + paramInt >= 150) {
      我草你怎么反编译我模组aaaabp = 150;
      你为什么要破解我的代码aaaacF((String)aaaaeR((char)-1918500793));
    } else {
      我草你怎么反编译我模组aaaabp += paramInt;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaadN(String paramString1, int paramInt, String paramString2) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaq : Ljava/util/List;
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
    //   38: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal
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
    //   64: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal
    //   67: astore #9
    //   69: aload #9
    //   71: ifnull -> 85
    //   74: aload_0
    //   75: aload_3
    //   76: iload_2
    //   77: aload #9
    //   79: invokespecial 你为什么要破解我的代码aaaadY : (Ljava/lang/String;ILaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal;)V
    //   82: goto -> 99
    //   85: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   88: aload_1
    //   89: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   94: invokeinterface warn : (Ljava/lang/String;)V
    //   99: return
  }
  
  private final void 你为什么要破解我的代码aaaadO(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)-685309903), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadP(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)566165561), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadQ(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)-337903565), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadR(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)-1293025232), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadS(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)1351483453), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadT(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)-1396637637), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadU(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)995491893), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadV(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)1102708794), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadW(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)-563806153), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadX(String paramString, int paramInt) {
    你为什么要破解我的代码aaaadN((String)aaaaeR((char)-1866596290), paramInt, paramString);
  }
  
  private final void 你为什么要破解我的代码aaaadY(String paramString, int paramInt, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal) {
    class_3222 class_3222;
    Intrinsics.checkNotNull(你为什么要破解我的代码aaaaal().method_30002(), (String)aaaaeR((char)944504904));
    class_3218 class_3218 = 你为什么要破解我的代码aaaaal().method_30002();
    if (我草你怎么反编译我模组aaaaab == null || 我草你怎么反编译我模组aaaaab.method_3760() == null || 我草你怎么反编译我模组aaaaab.method_3760().method_14571() == null || (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571()) == null) {
      (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571());
      return;
    } 
    double d1 = Math.toRadians(class_3222.method_36454());
    class_243 class_2431 = new class_243(-Math.sin(d1), 0.0D, Math.cos(d1));
    double d2 = (new Random()).nextDouble(27.5D, 30.5D);
    double d3 = (new Random()).nextDouble(14.5D, 15.5D);
    Timer timer = new Timer();
    Ref.IntRef intRef = new Ref.IntRef();
    timer.scheduleAtFixedRate(new aaaach(intRef, paramInt, timer, paramaaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal, class_3222, d3, d2, class_2431, class_3218, paramString), 0L, 40L);
  }
  
  private final void 你为什么要破解我的代码aaaadZ(String paramString, int paramInt) {
    我草你怎么反编译我模组aaaaaW += paramInt;
  }
  
  private final void 你为什么要破解我的代码aaaacg() {
    Object object1;
    Object object2;
    class_3222 class_3222;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    我草你怎么反编译我模组aaaaab.method_3760();
    if (((我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null) ? 我草你怎么反编译我模组aaaaab.method_3760().method_14571() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null) ? 我草你怎么反编译我模组aaaaab.method_3760().method_14571() : null;
      return;
    } 
    if ((class_3222)CollectionsKt.firstOrNull((List)object2) == null) {
      (class_3222)CollectionsKt.firstOrNull((List)object2);
      return;
    } 
    List list1 = object1.method_8390(class_1308.class, class_238.method_30048(class_3222.method_19538(), 60.0D, 50.0D, 40.0D), aaaabe.我草你怎么反编译我模组aaaabJ::你为什么要破解我的代码aaaaex);
    Intrinsics.checkNotNullExpressionValue(list1, (String)aaaaeR((char)631373842));
    List list2 = CollectionsKt.toList(list1);
    if (!list2.isEmpty()) {
      class_1308 class_1308 = (class_1308)CollectionsKt.random(list2, (Random)Random.Default);
      class_243 class_2431 = class_1308.method_19538();
      class_1541 class_1541 = new class_1541((class_1937)object1, class_2431.field_1352, class_2431.field_1351 + class_1308.method_17682(), class_2431.field_1350, null);
      class_1541.method_6967(30);
      object1.method_8649((class_1297)class_1541);
    } else {
      class_1541 class_1541 = new class_1541((class_1937)object1, class_3222.method_23317() - 10.0D, class_3222.method_23318() + 3, class_3222.method_23321(), null);
      class_1541.method_6967(30);
      object1.method_8649((class_1297)class_1541);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaabm(String paramString, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 95
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 95
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 95
    //   21: checkcast java/lang/Iterable
    //   24: astore_3
    //   25: iconst_0
    //   26: istore #4
    //   28: aload_3
    //   29: invokeinterface iterator : ()Ljava/util/Iterator;
    //   34: astore #5
    //   36: aload #5
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 91
    //   46: aload #5
    //   48: invokeinterface next : ()Ljava/lang/Object;
    //   53: astore #6
    //   55: aload #6
    //   57: checkcast net/minecraft/class_3222
    //   60: astore #7
    //   62: iconst_0
    //   63: istore #8
    //   65: aload #7
    //   67: new net/minecraft/class_1293
    //   70: dup
    //   71: getstatic net/minecraft/class_1294.field_5919 : Lnet/minecraft/class_6880;
    //   74: iload_2
    //   75: bipush #20
    //   77: imul
    //   78: iconst_0
    //   79: invokespecial <init> : (Lnet/minecraft/class_6880;II)V
    //   82: invokevirtual method_6092 : (Lnet/minecraft/class_1293;)Z
    //   85: pop
    //   86: nop
    //   87: nop
    //   88: goto -> 36
    //   91: nop
    //   92: goto -> 97
    //   95: pop
    //   96: nop
    //   97: return
  }
  
  private final void 你为什么要破解我的代码aaaaay() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaf : Ljava/util/List;
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
    //   25: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaal : Ljava/util/List;
    //   28: checkcast java/util/Collection
    //   31: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   34: checkcast kotlin/random/Random
    //   37: invokestatic random : (Ljava/util/Collection;Lkotlin/random/Random;)Ljava/lang/Object;
    //   40: checkcast java/lang/String
    //   43: astore_3
    //   44: iconst_5
    //   45: anewarray java/lang/String
    //   48: astore #4
    //   50: aload #4
    //   52: iconst_0
    //   53: ldc_w -1039466446
    //   56: i2c
    //   57: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   60: checkcast java/lang/String
    //   63: aastore
    //   64: aload #4
    //   66: iconst_1
    //   67: ldc_w -672858060
    //   70: i2c
    //   71: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   74: checkcast java/lang/String
    //   77: aastore
    //   78: aload #4
    //   80: iconst_2
    //   81: ldc_w 1006370870
    //   84: i2c
    //   85: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   88: checkcast java/lang/String
    //   91: aastore
    //   92: aload #4
    //   94: iconst_3
    //   95: ldc_w 1074921528
    //   98: i2c
    //   99: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   102: checkcast java/lang/String
    //   105: aastore
    //   106: aload #4
    //   108: iconst_4
    //   109: ldc_w -1412956100
    //   112: i2c
    //   113: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   116: checkcast java/lang/String
    //   119: aastore
    //   120: aload #4
    //   122: invokestatic listOf : ([Ljava/lang/Object;)Ljava/util/List;
    //   125: aload_3
    //   126: invokeinterface contains : (Ljava/lang/Object;)Z
    //   131: istore #5
    //   133: iload #5
    //   135: ifne -> 232
    //   138: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaq : Ljava/util/List;
    //   141: checkcast java/lang/Iterable
    //   144: astore #4
    //   146: iconst_0
    //   147: istore #6
    //   149: aload #4
    //   151: instanceof java/util/Collection
    //   154: ifeq -> 174
    //   157: aload #4
    //   159: checkcast java/util/Collection
    //   162: invokeinterface isEmpty : ()Z
    //   167: ifeq -> 174
    //   170: iconst_0
    //   171: goto -> 229
    //   174: aload #4
    //   176: invokeinterface iterator : ()Ljava/util/Iterator;
    //   181: astore #7
    //   183: aload #7
    //   185: invokeinterface hasNext : ()Z
    //   190: ifeq -> 228
    //   193: aload #7
    //   195: invokeinterface next : ()Ljava/lang/Object;
    //   200: astore #8
    //   202: aload #8
    //   204: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal
    //   207: astore #9
    //   209: iconst_0
    //   210: istore #10
    //   212: aload #9
    //   214: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   217: aload_3
    //   218: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   221: ifeq -> 183
    //   224: iconst_1
    //   225: goto -> 229
    //   228: iconst_0
    //   229: ifeq -> 296
    //   232: aload_0
    //   233: aload_3
    //   234: invokespecial 你为什么要破解我的代码aaaaaz : (Ljava/lang/String;)I
    //   237: istore #4
    //   239: aload_0
    //   240: aload_3
    //   241: invokespecial 你为什么要破解我的代码aaaaaw : (Ljava/lang/String;)Ljava/lang/String;
    //   244: astore #6
    //   246: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   249: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   252: aload_3
    //   253: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   258: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   261: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   264: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   267: invokevirtual 你为什么要破解我的代码aaaaam : ()Z
    //   270: ifeq -> 281
    //   273: aload_0
    //   274: aload_2
    //   275: aload #6
    //   277: iconst_1
    //   278: invokespecial 你为什么要破解我的代码aaaaau : (Ljava/lang/String;Ljava/lang/String;I)V
    //   281: aload_0
    //   282: aload_3
    //   283: iload #4
    //   285: aload_2
    //   286: aconst_null
    //   287: bipush #8
    //   289: aconst_null
    //   290: invokestatic 你为什么要破解我的代码aaaadI : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/Object;)V
    //   293: goto -> 310
    //   296: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   299: aload_3
    //   300: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   305: invokeinterface warn : (Ljava/lang/String;)V
    //   310: return
  }
  
  private final String 你为什么要破解我的代码aaaaax(String paramString) {
    return aaaaab.Companion.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaao() ? paramString : aaaaai.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(paramString);
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
    //   96: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
    //   99: ifeq -> 110
    //   102: aload #10
    //   104: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
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
    //   161: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
    //   164: ifeq -> 175
    //   167: aload #12
    //   169: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
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
    //   96: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
    //   99: ifeq -> 110
    //   102: aload #10
    //   104: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaN
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
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaav(@NotNull LiveLikeEvent paramLiveLikeEvent) {
    Intrinsics.checkNotNullParameter(paramLiveLikeEvent, (String)aaaaeR((char)-657260539));
    aaaaab aaaaab = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj();
    int i = aaaaab.你为什么要破解我的代码aaaaaa();
    我草你怎么反编译我模组aaaaad += paramLiveLikeEvent.getLikeCount();
    while (我草你怎么反编译我模组aaaaad >= i) {
      aaaabE aaaabE = aaaaab.你为什么要破解我的代码aaaaac();
      你为什么要破解我的代码aaaadI(this, aaaabE.你为什么要破解我的代码aaaaaa(), aaaabE.你为什么要破解我的代码aaaaab(), (String)aaaaeR((char)-810155959), null, 8, null);
      我草你怎么反编译我模组aaaaaK += aaaabE.你为什么要破解我的代码aaaaac();
      我草你怎么反编译我模组aaaaad -= i;
    } 
    你为什么要破解我的代码aaaaam();
  }
  
  private final void 你为什么要破解我的代码aaaaau(String paramString1, String paramString2, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
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
    //   69: ldc_w 735969354
    //   72: i2c
    //   73: invokestatic aaaaeR : (C)Ljava/lang/Object;
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
  
  private final void 你为什么要破解我的代码aaaaea() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 118
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 118
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 118
    //   21: checkcast java/lang/Iterable
    //   24: invokeinterface iterator : ()Ljava/util/Iterator;
    //   29: astore_1
    //   30: aload_1
    //   31: invokeinterface hasNext : ()Z
    //   36: ifne -> 43
    //   39: aconst_null
    //   40: goto -> 108
    //   43: aload_1
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: checkcast net/minecraft/class_3222
    //   52: astore_2
    //   53: iconst_0
    //   54: istore_3
    //   55: aload_2
    //   56: invokevirtual method_23317 : ()D
    //   59: dstore #4
    //   61: aload_1
    //   62: invokeinterface hasNext : ()Z
    //   67: ifeq -> 103
    //   70: aload_1
    //   71: invokeinterface next : ()Ljava/lang/Object;
    //   76: checkcast net/minecraft/class_3222
    //   79: astore #6
    //   81: iconst_0
    //   82: istore #7
    //   84: aload #6
    //   86: invokevirtual method_23317 : ()D
    //   89: dstore #8
    //   91: dload #4
    //   93: dload #8
    //   95: invokestatic max : (DD)D
    //   98: dstore #4
    //   100: goto -> 61
    //   103: dload #4
    //   105: invokestatic valueOf : (D)Ljava/lang/Double;
    //   108: dup
    //   109: ifnull -> 118
    //   112: invokevirtual doubleValue : ()D
    //   115: goto -> 125
    //   118: pop
    //   119: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   122: getfield field_1352 : D
    //   125: dstore #10
    //   127: dload #10
    //   129: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabp : I
    //   132: i2d
    //   133: dcmpg
    //   134: ifgt -> 147
    //   137: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabs : Z
    //   140: ifne -> 147
    //   143: aload_0
    //   144: invokespecial 你为什么要破解我的代码aaaaeb : ()V
    //   147: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabs : Z
    //   150: ifeq -> 280
    //   153: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaah : I
    //   156: bipush #20
    //   158: irem
    //   159: ifne -> 268
    //   162: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   165: dup
    //   166: ifnull -> 192
    //   169: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   172: dup
    //   173: ifnull -> 192
    //   176: invokevirtual method_14571 : ()Ljava/util/List;
    //   179: dup
    //   180: ifnull -> 192
    //   183: invokestatic firstOrNull : (Ljava/util/List;)Ljava/lang/Object;
    //   186: checkcast net/minecraft/class_3222
    //   189: goto -> 194
    //   192: pop
    //   193: aconst_null
    //   194: astore #12
    //   196: aload #12
    //   198: ifnull -> 221
    //   201: aload #12
    //   203: invokevirtual method_23317 : ()D
    //   206: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabp : I
    //   209: i2d
    //   210: dcmpl
    //   211: ifle -> 221
    //   214: aload_0
    //   215: invokespecial 你为什么要破解我的代码aaaaec : ()V
    //   218: goto -> 268
    //   221: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   224: ifle -> 268
    //   227: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   230: istore #13
    //   232: iload #13
    //   234: iconst_m1
    //   235: iadd
    //   236: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   239: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   242: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   245: ldc_w -1189806005
    //   248: i2c
    //   249: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   252: checkcast java/lang/String
    //   255: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   258: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   261: ifne -> 268
    //   264: aload_0
    //   265: invokespecial 你为什么要破解我的代码aaaaed : ()V
    //   268: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaah : I
    //   271: iconst_5
    //   272: irem
    //   273: ifne -> 280
    //   276: aload_0
    //   277: invokespecial 你为什么要破解我的代码aaaaef : ()V
    //   280: return
  }
  
  private final void 你为什么要破解我的代码aaaaeb() {
    // Byte code:
    //   0: iconst_1
    //   1: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabs : Z
    //   4: bipush #10
    //   6: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   12: dup
    //   13: ifnull -> 130
    //   16: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   19: dup
    //   20: ifnull -> 130
    //   23: invokevirtual method_14571 : ()Ljava/util/List;
    //   26: dup
    //   27: ifnull -> 130
    //   30: checkcast java/lang/Iterable
    //   33: astore_1
    //   34: iconst_0
    //   35: istore_2
    //   36: aload_1
    //   37: invokeinterface iterator : ()Ljava/util/Iterator;
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface hasNext : ()Z
    //   49: ifeq -> 126
    //   52: aload_3
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: astore #4
    //   60: aload #4
    //   62: checkcast net/minecraft/class_3222
    //   65: astore #5
    //   67: iconst_0
    //   68: istore #6
    //   70: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   73: aload #5
    //   75: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   78: aload #5
    //   80: ldc_w 1401815116
    //   83: i2c
    //   84: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   87: checkcast java/lang/String
    //   90: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   93: checkcast net/minecraft/class_2561
    //   96: ldc_w -1999568819
    //   99: i2c
    //   100: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   103: checkcast java/lang/String
    //   106: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   109: checkcast net/minecraft/class_2561
    //   112: bipush #10
    //   114: bipush #70
    //   116: bipush #20
    //   118: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   121: nop
    //   122: nop
    //   123: goto -> 43
    //   126: nop
    //   127: goto -> 132
    //   130: pop
    //   131: nop
    //   132: return
  }
  
  private final void 你为什么要破解我的代码aaaaec() {
    // Byte code:
    //   0: iconst_0
    //   1: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabs : Z
    //   4: bipush #10
    //   6: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   12: dup
    //   13: ifnull -> 130
    //   16: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   19: dup
    //   20: ifnull -> 130
    //   23: invokevirtual method_14571 : ()Ljava/util/List;
    //   26: dup
    //   27: ifnull -> 130
    //   30: checkcast java/lang/Iterable
    //   33: astore_1
    //   34: iconst_0
    //   35: istore_2
    //   36: aload_1
    //   37: invokeinterface iterator : ()Ljava/util/Iterator;
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface hasNext : ()Z
    //   49: ifeq -> 126
    //   52: aload_3
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: astore #4
    //   60: aload #4
    //   62: checkcast net/minecraft/class_3222
    //   65: astore #5
    //   67: iconst_0
    //   68: istore #6
    //   70: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   73: aload #5
    //   75: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   78: aload #5
    //   80: ldc_w -273481650
    //   83: i2c
    //   84: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   87: checkcast java/lang/String
    //   90: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   93: checkcast net/minecraft/class_2561
    //   96: ldc_w -1883504561
    //   99: i2c
    //   100: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   103: checkcast java/lang/String
    //   106: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   109: checkcast net/minecraft/class_2561
    //   112: bipush #10
    //   114: bipush #70
    //   116: bipush #20
    //   118: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   121: nop
    //   122: nop
    //   123: goto -> 43
    //   126: nop
    //   127: goto -> 132
    //   130: pop
    //   131: nop
    //   132: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   135: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   138: ldc_w -1081868208
    //   141: i2c
    //   142: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   145: checkcast java/lang/String
    //   148: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   151: return
  }
  
  private final void 你为什么要破解我的代码aaaaed() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 你为什么要破解我的代码aaaaer : ()Lnet/minecraft/class_3222;
    //   4: dup
    //   5: ifnull -> 14
    //   8: invokevirtual method_37908 : ()Lnet/minecraft/class_1937;
    //   11: goto -> 16
    //   14: pop
    //   15: aconst_null
    //   16: astore_1
    //   17: iconst_0
    //   18: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabs : Z
    //   21: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   24: dup
    //   25: ifnull -> 113
    //   28: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   31: dup
    //   32: ifnull -> 113
    //   35: invokevirtual method_14571 : ()Ljava/util/List;
    //   38: dup
    //   39: ifnull -> 113
    //   42: checkcast java/lang/Iterable
    //   45: astore_2
    //   46: iconst_0
    //   47: istore_3
    //   48: aload_2
    //   49: invokeinterface iterator : ()Ljava/util/Iterator;
    //   54: astore #4
    //   56: aload #4
    //   58: invokeinterface hasNext : ()Z
    //   63: ifeq -> 109
    //   66: aload #4
    //   68: invokeinterface next : ()Ljava/lang/Object;
    //   73: astore #5
    //   75: aload #5
    //   77: checkcast net/minecraft/class_3222
    //   80: astore #6
    //   82: iconst_0
    //   83: istore #7
    //   85: aload_1
    //   86: dup
    //   87: ifnull -> 103
    //   90: aload #6
    //   92: checkcast net/minecraft/class_1297
    //   95: bipush #35
    //   97: invokevirtual method_8421 : (Lnet/minecraft/class_1297;B)V
    //   100: goto -> 104
    //   103: pop
    //   104: nop
    //   105: nop
    //   106: goto -> 56
    //   109: nop
    //   110: goto -> 115
    //   113: pop
    //   114: nop
    //   115: aload_0
    //   116: invokespecial 你为什么要破解我的代码aaaaen : ()V
    //   119: aload_0
    //   120: invokevirtual 你为什么要破解我的代码aaaaei : ()V
    //   123: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaK : I
    //   126: istore #8
    //   128: iload #8
    //   130: iconst_1
    //   131: iadd
    //   132: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaaK : I
    //   135: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   138: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   141: ldc_w 1292435537
    //   144: i2c
    //   145: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   148: checkcast java/lang/String
    //   151: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   154: return
  }
  
  private final void 你为什么要破解我的代码aaaaee() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaah : I
    //   3: bipush #20
    //   5: irem
    //   6: ifne -> 181
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   12: dup
    //   13: ifnull -> 179
    //   16: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   19: dup
    //   20: ifnull -> 179
    //   23: invokevirtual method_14571 : ()Ljava/util/List;
    //   26: dup
    //   27: ifnull -> 179
    //   30: checkcast java/lang/Iterable
    //   33: astore_1
    //   34: iconst_0
    //   35: istore_2
    //   36: aload_1
    //   37: invokeinterface iterator : ()Ljava/util/Iterator;
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface hasNext : ()Z
    //   49: ifeq -> 175
    //   52: aload_3
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: astore #4
    //   60: aload #4
    //   62: checkcast net/minecraft/class_3222
    //   65: astore #5
    //   67: iconst_0
    //   68: istore #6
    //   70: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabs : Z
    //   73: ifeq -> 128
    //   76: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   79: aload #5
    //   81: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   84: aload #5
    //   86: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabr : I
    //   89: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   94: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   97: checkcast net/minecraft/class_2561
    //   100: ldc_w 1308557394
    //   103: i2c
    //   104: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   107: checkcast java/lang/String
    //   110: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   113: checkcast net/minecraft/class_2561
    //   116: bipush #10
    //   118: bipush #70
    //   120: bipush #20
    //   122: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   125: goto -> 170
    //   128: aload #5
    //   130: ldc_w -1704263597
    //   133: i2c
    //   134: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   137: checkcast java/lang/String
    //   140: iconst_1
    //   141: anewarray java/lang/Object
    //   144: astore #7
    //   146: aload #7
    //   148: iconst_0
    //   149: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabq : I
    //   152: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   157: aastore
    //   158: aload #7
    //   160: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   163: checkcast net/minecraft/class_2561
    //   166: iconst_1
    //   167: invokevirtual method_7353 : (Lnet/minecraft/class_2561;Z)V
    //   170: nop
    //   171: nop
    //   172: goto -> 43
    //   175: nop
    //   176: goto -> 181
    //   179: pop
    //   180: nop
    //   181: return
  }
  
  private final void 你为什么要破解我的代码aaaaef() {
    Iterator iterator;
    if (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null && 我草你怎么反编译我模组aaaaab.method_3760().method_14571() != null) {
      List list = 我草你怎么反编译我模组aaaaab.method_3760().method_14571();
      boolean bool = false;
      iterator = list.iterator();
    } else {
      我草你怎么反编译我模组aaaaab.method_3760().method_14571();
      return;
    } 
    if (iterator.hasNext());
  }
  
  private final List<class_243> 你为什么要破解我的代码aaaaeg(class_243 paramclass_243, double paramDouble) {
    ArrayList<class_243> arrayList = new ArrayList();
    byte b1 = 8;
    for (byte b2 = 0; b2 < b1; b2++) {
      double d1 = b2 / b1 * 2.0D * Math.PI;
      double d2 = paramclass_243.field_1352 + paramDouble * Math.cos(d1);
      double d3 = paramclass_243.field_1350 + paramDouble * Math.sin(d1);
      arrayList.add(new class_243(d2, paramclass_243.field_1351 + 0.5D, d3));
    } 
    return arrayList;
  }
  
  private final void 你为什么要破解我的代码aaaaeh(class_1937 paramclass_1937, class_2338 paramclass_2338, class_2394 paramclass_2394) {
    if (paramclass_1937 instanceof class_3218)
      for (class_3222 class_3222 : ((class_3218)paramclass_1937).method_18456()) {
        if (class_3222.method_5649(paramclass_2338.method_10263(), paramclass_2338.method_10264(), paramclass_2338.method_10260()) < 4096.0D)
          for (byte b = 0; b < 5; b++) {
            double d1 = (Math.random() - 0.5D) * 0.5D;
            double d2 = (Math.random() - 0.5D) * 0.5D;
            double d3 = (Math.random() - 0.5D) * 0.5D;
            double d4 = paramclass_2338.method_10263() + 0.5D + d1;
            double d5 = paramclass_2338.method_10264() + 0.5D + d2;
            double d6 = paramclass_2338.method_10260() + 0.5D + d3;
            class_2675 class_2675 = new class_2675(paramclass_2394, false, d4, d5, d6, 0.0F, 0.0F, 0.0F, 0.0F, 1);
            Intrinsics.checkNotNull(class_3222, (String)aaaaeR((char)-621412265));
            class_3222.field_13987.method_14364((class_2596)class_2675);
          }  
      }  
  }
  
  public final void 你为什么要破解我的代码aaaaei() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 280
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: dup
    //   11: ifnull -> 280
    //   14: astore_1
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_1
    //   18: astore_3
    //   19: aload_3
    //   20: monitorenter
    //   21: nop
    //   22: iconst_0
    //   23: istore #4
    //   25: aload_1
    //   26: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   29: dup
    //   30: ldc_w -1530396584
    //   33: i2c
    //   34: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   37: checkcast java/lang/String
    //   40: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   43: invokestatic toList : (Ljava/lang/Iterable;)Ljava/util/List;
    //   46: astore #5
    //   48: aload #5
    //   50: checkcast java/lang/Iterable
    //   53: invokestatic filterNotNull : (Ljava/lang/Iterable;)Ljava/util/List;
    //   56: checkcast java/lang/Iterable
    //   59: astore #6
    //   61: nop
    //   62: iconst_0
    //   63: istore #7
    //   65: aload #6
    //   67: astore #8
    //   69: new java/util/ArrayList
    //   72: dup
    //   73: invokespecial <init> : ()V
    //   76: checkcast java/util/Collection
    //   79: astore #9
    //   81: iconst_0
    //   82: istore #10
    //   84: aload #8
    //   86: invokeinterface iterator : ()Ljava/util/Iterator;
    //   91: astore #11
    //   93: aload #11
    //   95: invokeinterface hasNext : ()Z
    //   100: ifeq -> 151
    //   103: aload #11
    //   105: invokeinterface next : ()Ljava/lang/Object;
    //   110: astore #12
    //   112: aload #12
    //   114: checkcast net/minecraft/class_1297
    //   117: astore #13
    //   119: iconst_0
    //   120: istore #14
    //   122: aload #13
    //   124: instanceof net/minecraft/class_3222
    //   127: ifne -> 134
    //   130: iconst_1
    //   131: goto -> 135
    //   134: iconst_0
    //   135: ifeq -> 93
    //   138: aload #9
    //   140: aload #12
    //   142: invokeinterface add : (Ljava/lang/Object;)Z
    //   147: pop
    //   148: goto -> 93
    //   151: aload #9
    //   153: checkcast java/util/List
    //   156: nop
    //   157: checkcast java/lang/Iterable
    //   160: astore #6
    //   162: nop
    //   163: iconst_0
    //   164: istore #7
    //   166: aload #6
    //   168: invokeinterface iterator : ()Ljava/util/Iterator;
    //   173: astore #8
    //   175: aload #8
    //   177: invokeinterface hasNext : ()Z
    //   182: ifeq -> 257
    //   185: aload #8
    //   187: invokeinterface next : ()Ljava/lang/Object;
    //   192: astore #9
    //   194: aload #9
    //   196: checkcast net/minecraft/class_1297
    //   199: astore #10
    //   201: iconst_0
    //   202: istore #11
    //   204: nop
    //   205: aload #10
    //   207: invokevirtual method_31481 : ()Z
    //   210: ifne -> 252
    //   213: aload #10
    //   215: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   218: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   221: goto -> 252
    //   224: astore #12
    //   226: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   229: aload #10
    //   231: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   234: invokevirtual method_5882 : ()Ljava/lang/String;
    //   237: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   242: aload #12
    //   244: checkcast java/lang/Throwable
    //   247: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   252: nop
    //   253: nop
    //   254: goto -> 175
    //   257: nop
    //   258: nop
    //   259: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   262: astore #4
    //   264: aload_3
    //   265: monitorexit
    //   266: goto -> 276
    //   269: astore #4
    //   271: aload_3
    //   272: monitorexit
    //   273: aload #4
    //   275: athrow
    //   276: nop
    //   277: goto -> 282
    //   280: pop
    //   281: nop
    //   282: return
    // Exception table:
    //   from	to	target	type
    //   21	264	269	finally
    //   204	221	224	java/lang/Exception
    //   269	271	269	finally
  }
  
  private final void 你为什么要破解我的代码aaaaej() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 300
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: dup
    //   11: ifnull -> 300
    //   14: astore_1
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_1
    //   18: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   21: dup
    //   22: ldc_w 719716440
    //   25: i2c
    //   26: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   29: checkcast java/lang/String
    //   32: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   35: invokestatic filterNotNull : (Ljava/lang/Iterable;)Ljava/util/List;
    //   38: checkcast java/lang/Iterable
    //   41: astore_3
    //   42: nop
    //   43: iconst_0
    //   44: istore #4
    //   46: aload_3
    //   47: astore #5
    //   49: new java/util/ArrayList
    //   52: dup
    //   53: invokespecial <init> : ()V
    //   56: checkcast java/util/Collection
    //   59: astore #6
    //   61: iconst_0
    //   62: istore #7
    //   64: aload #5
    //   66: invokeinterface iterator : ()Ljava/util/Iterator;
    //   71: astore #8
    //   73: aload #8
    //   75: invokeinterface hasNext : ()Z
    //   80: ifeq -> 131
    //   83: aload #8
    //   85: invokeinterface next : ()Ljava/lang/Object;
    //   90: astore #9
    //   92: aload #9
    //   94: checkcast net/minecraft/class_1297
    //   97: astore #10
    //   99: iconst_0
    //   100: istore #11
    //   102: aload #10
    //   104: instanceof net/minecraft/class_3222
    //   107: ifne -> 114
    //   110: iconst_1
    //   111: goto -> 115
    //   114: iconst_0
    //   115: ifeq -> 73
    //   118: aload #6
    //   120: aload #9
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: goto -> 73
    //   131: aload #6
    //   133: checkcast java/util/List
    //   136: nop
    //   137: checkcast java/lang/Iterable
    //   140: astore_3
    //   141: nop
    //   142: iconst_0
    //   143: istore #4
    //   145: aload_3
    //   146: invokeinterface iterator : ()Ljava/util/Iterator;
    //   151: astore #5
    //   153: aload #5
    //   155: invokeinterface hasNext : ()Z
    //   160: ifeq -> 295
    //   163: aload #5
    //   165: invokeinterface next : ()Ljava/lang/Object;
    //   170: astore #6
    //   172: aload #6
    //   174: checkcast net/minecraft/class_1297
    //   177: astore #7
    //   179: iconst_0
    //   180: istore #8
    //   182: nop
    //   183: aload #7
    //   185: instanceof net/minecraft/class_1576
    //   188: ifne -> 215
    //   191: aload #7
    //   193: invokevirtual method_23321 : ()D
    //   196: ldc2_w 36.0
    //   199: dcmpl
    //   200: ifgt -> 215
    //   203: aload #7
    //   205: invokevirtual method_23321 : ()D
    //   208: ldc2_w 23.0
    //   211: dcmpg
    //   212: ifge -> 290
    //   215: aload #7
    //   217: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   220: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   223: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   226: aload #7
    //   228: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   231: invokevirtual method_5882 : ()Ljava/lang/String;
    //   234: aload #7
    //   236: invokevirtual method_23317 : ()D
    //   239: aload #7
    //   241: invokevirtual method_23318 : ()D
    //   244: aload #7
    //   246: invokevirtual method_23321 : ()D
    //   249: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;DDD)Ljava/lang/String;
    //   254: invokeinterface info : (Ljava/lang/String;)V
    //   259: goto -> 290
    //   262: astore #9
    //   264: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   267: aload #7
    //   269: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   272: invokevirtual method_5882 : ()Ljava/lang/String;
    //   275: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   280: aload #9
    //   282: checkcast java/lang/Throwable
    //   285: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   290: nop
    //   291: nop
    //   292: goto -> 153
    //   295: nop
    //   296: nop
    //   297: goto -> 302
    //   300: pop
    //   301: nop
    //   302: return
    // Exception table:
    //   from	to	target	type
    //   182	259	262	java/lang/Exception
  }
  
  private final void 你为什么要破解我的代码aaaaek(MinecraftServer paramMinecraftServer) {
    paramMinecraftServer.execute(paramMinecraftServer::你为什么要破解我的代码aaaaey);
  }
  
  public final void 你为什么要破解我的代码aaaabF(@NotNull class_3222 paramclass_3222) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 2012414022
    //   4: i2c
    //   5: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokevirtual method_19538 : ()Lnet/minecraft/class_243;
    //   18: astore_2
    //   19: aload_0
    //   20: invokevirtual 你为什么要破解我的代码aaaaak : ()Lnet/minecraft/class_243;
    //   23: astore_3
    //   24: aload_2
    //   25: getfield field_1352 : D
    //   28: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabm : I
    //   31: i2d
    //   32: dadd
    //   33: aload_3
    //   34: getfield field_1352 : D
    //   37: dcmpl
    //   38: ifle -> 48
    //   41: aload_3
    //   42: getfield field_1352 : D
    //   45: goto -> 57
    //   48: aload_2
    //   49: getfield field_1352 : D
    //   52: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabm : I
    //   55: i2d
    //   56: dadd
    //   57: dstore #4
    //   59: new net/minecraft/class_243
    //   62: dup
    //   63: dload #4
    //   65: aload_3
    //   66: getfield field_1351 : D
    //   69: aload_3
    //   70: getfield field_1350 : D
    //   73: invokespecial <init> : (DDD)V
    //   76: astore #6
    //   78: aload_1
    //   79: aload #6
    //   81: getfield field_1352 : D
    //   84: aload #6
    //   86: getfield field_1351 : D
    //   89: aload #6
    //   91: getfield field_1350 : D
    //   94: invokestatic 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;DDD)V
    //   97: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   100: aload_1
    //   101: ldc_w 90.0
    //   104: fconst_0
    //   105: invokestatic 你为什么要破解我的代码aaaaab : (Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/class_3222;FF)V
    //   108: aload_1
    //   109: aload_1
    //   110: invokevirtual method_6063 : ()F
    //   113: invokevirtual method_6033 : (F)V
    //   116: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   119: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   122: ldc_w 1807286361
    //   125: i2c
    //   126: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   129: checkcast java/lang/String
    //   132: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   135: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   138: dup
    //   139: ifnull -> 286
    //   142: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   145: dup
    //   146: ifnull -> 286
    //   149: invokevirtual method_14571 : ()Ljava/util/List;
    //   152: dup
    //   153: ifnull -> 286
    //   156: checkcast java/lang/Iterable
    //   159: astore #7
    //   161: iconst_0
    //   162: istore #8
    //   164: aload #7
    //   166: invokeinterface iterator : ()Ljava/util/Iterator;
    //   171: astore #9
    //   173: aload #9
    //   175: invokeinterface hasNext : ()Z
    //   180: ifeq -> 282
    //   183: aload #9
    //   185: invokeinterface next : ()Ljava/lang/Object;
    //   190: astore #10
    //   192: aload #10
    //   194: checkcast net/minecraft/class_3222
    //   197: astore #11
    //   199: iconst_0
    //   200: istore #12
    //   202: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   205: aload #11
    //   207: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   210: aload #11
    //   212: ldc_w -2010251174
    //   215: i2c
    //   216: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   219: checkcast java/lang/String
    //   222: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   225: checkcast net/minecraft/class_2561
    //   228: ldc_w -1280049061
    //   231: i2c
    //   232: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   235: checkcast java/lang/String
    //   238: iconst_1
    //   239: anewarray java/lang/Object
    //   242: astore #13
    //   244: aload #13
    //   246: iconst_0
    //   247: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   250: pop
    //   251: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabm : I
    //   254: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   259: aastore
    //   260: aload #13
    //   262: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   265: checkcast net/minecraft/class_2561
    //   268: bipush #10
    //   270: bipush #70
    //   272: bipush #20
    //   274: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   277: nop
    //   278: nop
    //   279: goto -> 173
    //   282: nop
    //   283: goto -> 288
    //   286: pop
    //   287: nop
    //   288: return
  }
  
  private final void 你为什么要破解我的代码aaaael() {
    aaaabm aaaabm = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaj().你为什么要破解我的代码aaaaav();
    if (我草你怎么反编译我模组aaaaah % 20 == 0 && 我草你怎么反编译我模组aaaabq > 0) {
      int i = 我草你怎么反编译我模组aaaabq;
      我草你怎么反编译我模组aaaabq = i + -1;
      if (我草你怎么反编译我模组aaaabq == 0)
        你为什么要破解我的代码aaaaem(aaaabm); 
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaem(aaaabm paramaaaabm) {
    List list = paramaaaabm.你为什么要破解我的代码aaaaac();
    boolean bool1 = false;
    Iterator iterator = list.iterator();
    if (iterator.hasNext());
    class_3222 class_3222 = 你为什么要破解我的代码aaaaer();
    boolean bool2 = false;
    aaaaah.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(class_3222, (class_2561)class_2561.method_43471((String)aaaaeR((char)-1957953443)), class_2561.method_30163((String)aaaaeR((char)423165982)), 10, 70, 20);
    你为什么要破解我的代码aaaaer();
    我草你怎么反编译我模组aaaabq = paramaaaabm.你为什么要破解我的代码aaaaaa();
  }
  
  private final void 你为什么要破解我的代码aaaaen() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 110
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 110
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 110
    //   21: checkcast java/lang/Iterable
    //   24: astore_1
    //   25: iconst_0
    //   26: istore_2
    //   27: aload_1
    //   28: invokeinterface iterator : ()Ljava/util/Iterator;
    //   33: astore_3
    //   34: aload_3
    //   35: invokeinterface hasNext : ()Z
    //   40: ifeq -> 106
    //   43: aload_3
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: astore #4
    //   51: aload #4
    //   53: checkcast net/minecraft/class_3222
    //   56: astore #5
    //   58: iconst_0
    //   59: istore #6
    //   61: aload #5
    //   63: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   66: aload #5
    //   68: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   71: pop
    //   72: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   75: getfield field_1352 : D
    //   78: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   81: pop
    //   82: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   85: getfield field_1351 : D
    //   88: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaabk : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax;
    //   91: pop
    //   92: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaac : Lnet/minecraft/class_243;
    //   95: getfield field_1350 : D
    //   98: invokestatic 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;DDD)V
    //   101: nop
    //   102: nop
    //   103: goto -> 34
    //   106: nop
    //   107: goto -> 112
    //   110: pop
    //   111: nop
    //   112: return
  }
  
  private final void 你为什么要破解我的代码aaaaeo(class_3222 paramclass_3222) {
    for (aaaaaa aaaaaa : aaaaaa.你为什么要破解我的代码aaaaad())
      你为什么要破解我的代码aaaaep(paramclass_3222, aaaaaa); 
  }
  
  private final void 你为什么要破解我的代码aaaaep(class_3222 paramclass_3222, aaaaaa paramaaaaaa) {
    你为什么要破解我的代码aaaaeq(paramclass_3222, paramaaaaaa.你为什么要破解我的代码aaaaau());
  }
  
  private final void 你为什么要破解我的代码aaaaeq(class_3222 paramclass_3222, String paramString) {
    String str = "/give " + paramclass_3222.method_5477().getString() + " pointblank:" + paramString + " 1";
    if (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3734() != null) {
      Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaab);
      我草你怎么反编译我模组aaaaab.method_3734().method_44252(我草你怎么反编译我模组aaaaab.method_3739(), str);
    } else {
      我草你怎么反编译我模组aaaaab.method_3734();
    } 
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
  
  private final class_3222 你为什么要破解我的代码aaaaer() {
    我草你怎么反编译我模组aaaaab.method_3760().method_14571();
    return (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null && 我草你怎么反编译我模组aaaaab.method_3760().method_14571() != null) ? (class_3222)CollectionsKt.firstOrNull(我草你怎么反编译我模组aaaaab.method_3760().method_14571()) : null;
  }
  
  private final int 你为什么要破解我的代码aaaacV(MinecraftServer paramMinecraftServer, Function1<? super MinecraftServer, ? extends TimerTask> paramFunction1) {
    int i = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaq.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
    paramFunction1.invoke(paramMinecraftServer);
    return i;
  }
  
  private final void 你为什么要破解我的代码aaaaes() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 13
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: goto -> 15
    //   13: pop
    //   14: aconst_null
    //   15: astore_1
    //   16: nop
    //   17: aload_1
    //   18: ifnull -> 122
    //   21: aload_1
    //   22: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull -> 122
    //   30: aload_2
    //   31: astore_3
    //   32: nop
    //   33: iconst_0
    //   34: istore #4
    //   36: aload_3
    //   37: astore #5
    //   39: new java/util/ArrayList
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: checkcast java/util/Collection
    //   49: astore #6
    //   51: iconst_0
    //   52: istore #7
    //   54: aload #5
    //   56: invokeinterface iterator : ()Ljava/util/Iterator;
    //   61: astore #8
    //   63: aload #8
    //   65: invokeinterface hasNext : ()Z
    //   70: ifeq -> 113
    //   73: aload #8
    //   75: invokeinterface next : ()Ljava/lang/Object;
    //   80: astore #9
    //   82: aload #9
    //   84: checkcast net/minecraft/class_1297
    //   87: astore #10
    //   89: iconst_0
    //   90: istore #11
    //   92: aload #10
    //   94: instanceof net/minecraft/class_1542
    //   97: ifeq -> 63
    //   100: aload #6
    //   102: aload #9
    //   104: invokeinterface add : (Ljava/lang/Object;)Z
    //   109: pop
    //   110: goto -> 63
    //   113: aload #6
    //   115: checkcast java/util/List
    //   118: nop
    //   119: goto -> 123
    //   122: aconst_null
    //   123: astore #12
    //   125: aload #12
    //   127: dup
    //   128: ifnull -> 235
    //   131: checkcast java/lang/Iterable
    //   134: astore_2
    //   135: iconst_0
    //   136: istore_3
    //   137: aload_2
    //   138: invokeinterface iterator : ()Ljava/util/Iterator;
    //   143: astore #4
    //   145: aload #4
    //   147: invokeinterface hasNext : ()Z
    //   152: ifeq -> 231
    //   155: aload #4
    //   157: invokeinterface next : ()Ljava/lang/Object;
    //   162: astore #5
    //   164: aload #5
    //   166: checkcast net/minecraft/class_1297
    //   169: astore #6
    //   171: iconst_0
    //   172: istore #7
    //   174: nop
    //   175: aload #6
    //   177: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   180: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   183: goto -> 226
    //   186: astore #8
    //   188: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   191: aload #6
    //   193: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   196: aload #6
    //   198: invokevirtual method_23317 : ()D
    //   201: aload #6
    //   203: invokevirtual method_23318 : ()D
    //   206: aload #6
    //   208: invokevirtual method_23321 : ()D
    //   211: <illegal opcode> makeConcatWithConstants : (Lnet/minecraft/class_1299;DDD)Ljava/lang/String;
    //   216: aload #8
    //   218: checkcast java/lang/Throwable
    //   221: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   226: nop
    //   227: nop
    //   228: goto -> 145
    //   231: nop
    //   232: goto -> 265
    //   235: pop
    //   236: nop
    //   237: goto -> 265
    //   240: astore #12
    //   242: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   245: ldc_w -398720930
    //   248: i2c
    //   249: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   252: checkcast java/lang/String
    //   255: aload #12
    //   257: checkcast java/lang/Throwable
    //   260: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   265: return
    // Exception table:
    //   from	to	target	type
    //   16	237	240	java/lang/Exception
    //   174	183	186	java/lang/Exception
  }
  
  public final void 你为什么要破解我的代码aaaacF(@NotNull String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w -956497825
    //   4: i2c
    //   5: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   18: astore_2
    //   19: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaax.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   22: dup
    //   23: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   26: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   29: invokevirtual method_14571 : ()Ljava/util/List;
    //   32: dup
    //   33: ldc_w 1589116942
    //   36: i2c
    //   37: invokestatic aaaaeR : (C)Ljava/lang/Object;
    //   40: checkcast java/lang/String
    //   43: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   46: checkcast java/lang/Iterable
    //   49: astore_3
    //   50: iconst_0
    //   51: istore #4
    //   53: aload_3
    //   54: invokeinterface iterator : ()Ljava/util/Iterator;
    //   59: astore #5
    //   61: aload #5
    //   63: invokeinterface hasNext : ()Z
    //   68: ifeq -> 104
    //   71: aload #5
    //   73: invokeinterface next : ()Ljava/lang/Object;
    //   78: astore #6
    //   80: aload #6
    //   82: checkcast net/minecraft/class_3222
    //   85: astore #7
    //   87: iconst_0
    //   88: istore #8
    //   90: aload #7
    //   92: aload_2
    //   93: checkcast net/minecraft/class_2561
    //   96: invokevirtual method_43496 : (Lnet/minecraft/class_2561;)V
    //   99: nop
    //   100: nop
    //   101: goto -> 61
    //   104: nop
    //   105: return
  }
  
  private static final boolean 你为什么要破解我的代码aaaaet(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaaeR((char)-207421344));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  private static final boolean 你为什么要破解我的代码aaaaeu(WinDef.HWND paramHWND, Pointer paramPointer) {
    int i = 我草你怎么反编译我模组aaaabP.GetWindowTextLength(paramHWND) + 1;
    char[] arrayOfChar = new char[i];
    我草你怎么反编译我模组aaaabP.GetWindowText(paramHWND, arrayOfChar, i);
    String str = StringsKt.trim(new String(arrayOfChar)).toString();
    if (StringsKt.contains$default(str, (String)aaaaeR((char)1759576161), false, 2, null) && StringsKt.contains$default(str, (String)aaaaeR((char)-690093982), false, 2, null)) {
      我草你怎么反编译我模组aaaabP.PostMessage(paramHWND, 256, new WinDef.WPARAM(87L), new WinDef.LPARAM(0L));
      我草你怎么反编译我模组aaaabG = true;
      return false;
    } 
    return true;
  }
  
  private static final boolean 你为什么要破解我的代码aaaaev(WinDef.HWND paramHWND, Pointer paramPointer) {
    int i = 我草你怎么反编译我模组aaaabP.GetWindowTextLength(paramHWND) + 1;
    char[] arrayOfChar = new char[i];
    我草你怎么反编译我模组aaaabP.GetWindowText(paramHWND, arrayOfChar, i);
    String str = StringsKt.trim(new String(arrayOfChar)).toString();
    if (StringsKt.contains$default(str, (String)aaaaeR((char)1739456609), false, 2, null) && StringsKt.contains$default(str, (String)aaaaeR((char)1074069602), false, 2, null)) {
      我草你怎么反编译我模组aaaabP.PostMessage(paramHWND, 257, new WinDef.WPARAM(87L), new WinDef.LPARAM(3221225472L));
      我草你怎么反编译我模组aaaabG = false;
      return false;
    } 
    return true;
  }
  
  private static final boolean 你为什么要破解我的代码aaaaew(User32 paramUser32, WinDef.HWND paramHWND, Pointer paramPointer) {
    Intrinsics.checkNotNullParameter(paramUser32, (String)aaaaeR((char)615907427));
    int i = paramUser32.GetWindowTextLength(paramHWND) + 1;
    char[] arrayOfChar = new char[i];
    paramUser32.GetWindowText(paramHWND, arrayOfChar, i);
    String str = StringsKt.trim(new String(arrayOfChar)).toString();
    if (StringsKt.contains$default(str, (String)aaaaeR((char)-73596831), false, 2, null) && StringsKt.contains$default(str, (String)aaaaeR((char)-235011998), false, 2, null)) {
      我草你怎么反编译我模组aaaabZ = paramHWND;
      return false;
    } 
    return true;
  }
  
  private static final boolean 你为什么要破解我的代码aaaaex(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaaeR((char)-1637220256));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  private static final void 你为什么要破解我的代码aaaaey(MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramMinecraftServer, (String)aaaaeR((char)909508708));
    try {
      class_3218 class_3218;
      if (paramMinecraftServer.method_30002() == null) {
        paramMinecraftServer.method_30002();
        return;
      } 
      synchronized (class_3218) {
        boolean bool1 = false;
        Intrinsics.checkNotNullExpressionValue(class_3218.method_27909(), (String)aaaaeR((char)736362584));
        List list1 = CollectionsKt.toList(class_3218.method_27909());
        List list2 = CollectionsKt.chunked(list1, 50);
        boolean bool2 = false;
        Iterator iterator = list2.iterator();
        if (iterator.hasNext());
        Unit unit = Unit.INSTANCE;
      } 
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaeR((char)-1598291867), exception);
    } 
  }
  
  static {
    byte[] arrayOfByte1 = "7õ\024ð/NF1Õ·\027\023Ã\026µ\031Æ¬ÑÓ5C±çÖZÁtD\000@y7táDÔ\032òXærÃpêº9o½RMbÇ¥Cà¶qnPËØ\035KÔe£\017\002Ì`#¤Ïcõ«X~sU»K!\036\004ÌC\016ÃgÇ\033\006[=\024­h\027ºËhÙÊ:£xy\024]'æ\031\021ûInXu6ÙMªxÙ\002\017,XÖjÝ÷î\boj:'\025æÛ>3Ûá|®q4\035îHÓÙ4Z¥eÃó³»Ä\bZAÒ8äÇ¢ih¼²Ðír\n]â-ÂZ¾DËOúò\020¼2óçDM^Y\017JÅôÔÒr¨Úý!þ¾Æ>+Û´Õ;¸Ì4Ñzó\b¥C£Uú¢ø[\025ÈòeMÚGpÔéÓ_Â±²òé3\017ó\023áw[Î¨Fà\022¹Úªâ.8äCw8X¥jÄ?Æq3ÊÍQÝ\031ú°èâc\002æ\035¸õ.ú2Ò\006Ê;îÿ/SÇï\030R,\037µ%ÁÝgfZ\nûß=;E¿ÜÎl¨@ú¿F]ø\035Ô\002½Ç®ºMôSH%\rþ[Ìèâ\035¹\037ç\026/=9ÞÿYÐþYcíÊìÉüq.·Ù^ÙI½¡¾§½<i\bÝÀÙt\032ÊßÐ©äbÞ,í\017¼{\002zµ\032HSÓ*r^unÇ\0077ün}\"yð!îéÿ¹KÍ-PTÃý\000y[\036½R)?c|p  oW¼ÔÐ\bØç¦ ÐÓâIÎ{\033pãÅûø4Ã±ÿyl Xxï¶f/\027¡þÎ32Ø×\030¸ªa\"'ÓWö¤{-ÛÔÇ³\037pê+éMâéÚ£ÄS¡þÎÃv¦ê\034Þá¬¦\0250X¿ãr÷Zç\032\n\tô³¾ë(¦ö¡òPÊ³\035´\025ÖÆv+î¤KlF¨\rÙW\031mää/Í\nEÖ;9dª7vxê\\NMÞúNjEl{qe¾\\56@¨\007AÙÃâQÝ\003ò;Ü\n.çw0¡¢\n\026q¿Æ7i\b¯ïõPÉ)c\025©½ñp\016¸ìuÔqF6;\031ÚÄG½´<-!G­\034K(£M3Õ\035´ëÑ\002É\034Ü¦E3¾\b.(ò¾\022\032æ\020RâªE§ ?àa¹\013U\036-²Dàb6fÀ&h`þ·®ò°¥Ðbc«¸ß£¦=Àõ`Ô\f\034h!BÞÂ. ;Då0YÍëßy7\026ãF>À`\002êÉ¤*Õ>J£øç\000©\006GéËè#ÊuËÛiÃ%²h\037\032$\026Á¹2·\"(H·q\"­,CÅ\0226h1Ø$HæíÙ½²ÚªÂéz<ôú\r\000¸2R®:ìô&%T$½ö1ÉOv·<HÑkÁ=/\fc\016¦_®\026\fËý(Ój?5è²\036\016¤ø$¸ì`Âî«l\030\023\016¹ê\005WÅ\020îÇ\022\016uÑ7újxP\016ëª\016Zµ>k\037\035¼;ñÑ¤ðÇ\001$µ'NùÒaÎ\031Ù\035$)ñ\021Â°Xì¡#èö\034\0363|¡ïÜmÉj>@\nyï\fK,îØn½#¹ZglESÀÀØé¢nâñ\002À\007ì·í\036\037ÑÞÂíLø}\0001Êô³\021\025K¸Z\t|`¡ÏRí,¤·ÃuL0¦¼{\030,_¶e0úJX¾+³5N³×2IS«Ò:2\027GÁ5Çw®J1/b(ÁÁ8\031g«º?¬**.cdbÿczW8>ÌA4Q·5qqUø_ÍuP\026~e\027]³¯ð\023ÒN ÿî\b\025«ÄÝð êõ\002ÿ\024\\×ðW]KH6î\020Ìü¹öpµH\007eøµaÜ¦R-½î\027«×\n²I¢\013(Þ4ºµ|Ü\\X=_úòUFµ5\023\fªUM5G\000¸Àûÿ¿\007J·ß*TêïQ­Öß#º­Lö\026öð%C5`W|÷¡+¨îÎõiE\024r\"BÃa\b÷!èþ'\033±\n\035B9Mk~á©ô\013\006cª@>º){¡tµcä\035\026wÍ£|s¾ÍÊ©Ñ\013\005êr\023Ö\n{4\023-Mb;\023àê|®Ó\026\\\034CÌ«ltr264¡\0341æq_é5ö\013LîÏÈ\\\034.&Ø¡'xØã6×\001\030nvknû| q3°×Ðº²ó¡5ªåÈÃ 8ösz¬Ø\013ÈÌähI*¶ÿÓµÂkìw)ìÌ_ùUG=¶Á6¢/áì\001ü­®\0358µp ³ª\030Ñ4÷AB5z\005Óë]\033X³P«¶,#Ô9#õq]Lý#×\022 \003û¾w_T\007]\\\tÁ÷­æÏ® Ð\025AoÉ¨v\fnâéU+Ô\037ñ\007ë£pÖ×}®\017\000.¨tÁ\000ä©\000¢i\022Îwü5Fô)ø!,>që=þµ,\006ÌÓ´]¬ÅæÃ\033µXð\021ãæS·ÆNmú\021)/\f½\032¾uEØg¼á\033Z5\f(³Ì¼vx-P®¯ÿ(¹'æ\031ÒAµõ\nÕÏ\023M~Äúä\036\035Y(}w]ß\"ýíÐwÈ?7§p.\024\031(äWÓD=ëùC\006w\026[\002m«\nñ?º\007sæW®Qê§á^l}F:ÖÔÎ4\017àÈ%ì$q\016ê ¶÷\025cÈéÛ[5$~¾T\016äu<6±cïµq¸@Î»Û$4®Ý<\003¾¯#o¢xî£Áß\021eÂ'¿â&\031<£Rú`öû¬dQ£\023µD\003\001\031Ûí\000*Ç­_ïýj`Wi£Ì\017Ã\t3º\034keÝnÌ1ñUz»\0271TP_ÑËp&X\037)saûÚ9äÞ´ðåÇìÆA_¾'iíP\037\025yÁGý¥_\n#ú\031£çV\013D f{÷hç\017O\\¹\022¦ç=B-â÷ÊÛ\013$öo6´z\030»7®b©TÕLF\036ïoèÊ\006Bì¬®VY\034íù¸rÅ®~C¢Sº<\0166CõvÕþ²\021Ý\005\bù`I¸4DIï±ð8\016R=Ï]\023¯oéE¹N=Çúy?\034?'R\tn\027æÁF`Ñi3Ëú[­ ®RÅ9_\034ÓÁ>·\030¹ý­LÉ%l|ûJ\033\036Ç4k\032ö£Ï!&^É3d3xômøæÕ\b\035\005ïÕæY(¸ÎÏ®Ô;\003\005(ÉlÛ\nWy\006µ\\ælHu\007}©¼nm¤82ñ\000¥qÑ*¿\022X\000Þ\007\023´\030JýÐÂ¦»e}Ye\023%44Q~¢_\nPNåª\031o\037\020:Ø~©R­bÙÂ#Ñ)Ñ¢ä£=,uÈLN\036\022@þ«ÛXQá·ÂØÁ=CyÅ­½GÜ|å;,wÙ£èUVËÅsäIõÕþP'ùÝ$áY=KogV(ís$½ÓÍ|<ü{¬¯3×L=¸W\023qCl¢Bò5)\031Qv´FÈTyAô§L­à|øJj+çxék^ÉÄë¥H6\nAFí)9ôq»dòKzg¥ô¦óÅÛ±\027k·¥Aü\030¼M@hÑþ.l\007¦ÃgÉæn\023$«6PÂ¨´¤ÏW¶\002\t¸TÐ\023\027Pi\0328gÂÌ\0259T©Ñù*\026ûþ*=~~YêªÃ³_:¥\0341{\016N\tv´\037\007^\036rOR#O+¿EnSÈÔæï=íñ×\003°ëjZt¤{í\034I\020RKmg¾\rÐL9TÄ;Ní1O".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "Û!'¶Zn\000".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)339446386;
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
              Object[] arrayOfObject = new Object[116];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaacb = 23500L;
            我草你怎么反编译我模组aaaaca = 12600L;
            我草你怎么反编译我模组aaaabT = 82;
            我草你怎么反编译我模组aaaabS = 87;
            我草你怎么反编译我模组aaaabR = 257;
            我草你怎么反编译我模组aaaabQ = 256;
            我草你怎么反编译我模组aaaabC = 42;
            我草你怎么反编译我模组aaaabB = 15;
            我草你怎么反编译我模组aaaabA = 2;
            我草你怎么反编译我模组aaaabz = -34;
            我草你怎么反编译我模组aaaaby = 210;
            我草你怎么反编译我模组aaaabv = 100;
            我草你怎么反编译我模组aaaabk = new aaaaax();
            Intrinsics.checkNotNullExpressionValue(Paths.get((String)aaaaeR((char)-709099490), new String[0]), (String)aaaaeR((char)-113180570));
            我草你怎么反编译我模组aaaabl = Paths.get((String)aaaaeR((char)-709099490), new String[0]);
            我草你怎么反编译我模组aaaabm = 50;
            我草你怎么反编译我模组aaaaac = new class_243(200.0D, -29.0D, 29.0D);
            我草你怎么反编译我模组aaaaaL = 20;
            我草你怎么反编译我模组aaaabp = -2800;
            String[] arrayOfString = new String[10];
            arrayOfString[0] = (String)aaaaeR((char)-440729497);
            arrayOfString[1] = (String)aaaaeR((char)-2099576728);
            arrayOfString[2] = (String)aaaaeR((char)1894449257);
            arrayOfString[3] = (String)aaaaeR((char)-1612316566);
            arrayOfString[4] = (String)aaaaeR((char)-1090453397);
            arrayOfString[5] = (String)aaaaeR((char)-50462612);
            arrayOfString[6] = (String)aaaaeR((char)1766326381);
            arrayOfString[7] = (String)aaaaeR((char)1832648814);
            arrayOfString[8] = (String)aaaaeR((char)1519124591);
            arrayOfString[9] = (String)aaaaeR((char)355532912);
            我草你怎么反编译我模组aaaaaf = CollectionsKt.listOf((Object[])arrayOfString);
            我草你怎么反编译我模组aaaaas = 30L;
            我草你怎么反编译我模组aaaaal = CollectionsKt.emptyList();
            我草你怎么反编译我模组aaaabq = 60;
            我草你怎么反编译我模组aaaabr = 10;
            我草你怎么反编译我模组aaaabu = 144;
            我草你怎么反编译我模组aaaabx = 354;
            我草你怎么反编译我模组aaaabD = 144;
            我草你怎么反编译我模组aaaabJ = (String)aaaaeR((char)-372572139);
            我草你怎么反编译我模组aaaabK = (String)aaaaeR((char)523370609);
            我草你怎么反编译我模组aaaabP = User32.INSTANCE;
            我草你怎么反编译我模组aaaabU = 516;
            我草你怎么反编译我模组aaaabV = 517;
            我草你怎么反编译我模组aaaabW = 513;
            我草你怎么反编译我模组aaaabX = 514;
            我草你怎么反编译我模组aaaabY = new WinDef.LPARAM(3221225472L);
            我草你怎么反编译我模组aaaacc = (String)aaaaeR((char)-1299185634);
            我草你怎么反编译我模组aaaacf = new HashSet<>();
            我草你怎么反编译我模组aaaacg = (String)aaaaeR((char)1644101662);
            我草你怎么反编译我模组aaaacj = 3000L;
            aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[] arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[10];
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[0] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)415825969), 1, class_1299.field_6051, null, null, 24, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[1] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)1203437625), 1, class_1299.field_6046, null, null, 24, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[2] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)12517437), 1, class_1299.field_6137, null, null, 24, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[3] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)-1496252368), 1, class_1299.field_6119, null, null, 24, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[4] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)574160947), 1, null, (String)aaaaeR((char)801898610), null, 20, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[5] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)1854013502), 1, null, (String)aaaaeR((char)-219217805), null, 20, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[6] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)-1810366405), 1, class_1299.field_6147, null, null, 24, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[7] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)1019215925), 1, null, null, aaaaaT.我草你怎么反编译我模组aaaabq, 12, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[8] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)-708050886), 1, null, null, aaaabT.我草你怎么反编译我模组aaaacu, 12, null);
            arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal[9] = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal((String)aaaaeR((char)1714356279), 1, null, null, aaaabc.我草你怎么反编译我模组aaaabH, 12, null);
            我草你怎么反编译我模组aaaaaq = CollectionsKt.listOf((Object[])arrayOfAaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaeR(char paramChar) {
    return ((Object[])aaaack)[paramChar];
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal {
    @NotNull
    private final String 我草你怎么反编译我模组aaaaaa;
    
    private final int 我草你怎么反编译我模组aaaaab;
    
    @Nullable
    private final class_1299<? extends class_1308> 我草你怎么反编译我模组aaaaac;
    
    @Nullable
    private final String 我草你怎么反编译我模组aaaaad;
    
    @Nullable
    private final Function3<class_3218, class_243, String, class_1308> 我草你怎么反编译我模组aaaaae;
    
    static Object aaaaaf;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal(@NotNull String param1String1, int param1Int, @Nullable class_1299<? extends class_1308> param1class_1299, @Nullable String param1String2, @Nullable Function3<? super class_3218, ? super class_243, ? super String, ? extends class_1308> param1Function3) {
      this.我草你怎么反编译我模组aaaaaa = param1String1;
      this.我草你怎么反编译我模组aaaaab = param1Int;
      this.我草你怎么反编译我模组aaaaac = param1class_1299;
      this.我草你怎么反编译我模组aaaaad = param1String2;
      this.我草你怎么反编译我模组aaaaae = (Function3)param1Function3;
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
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final Function3<class_3218, class_243, String, class_1308> 你为什么要破解我的代码aaaaae() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @NotNull
    public final String 你为什么要破解我的代码aaaaaf() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final int 你为什么要破解我的代码aaaaag() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    @Nullable
    public final class_1299<? extends class_1308> 你为什么要破解我的代码aaaaah() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaai() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final Function3<class_3218, class_243, String, class_1308> 你为什么要破解我的代码aaaaaj() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal 你为什么要破解我的代码aaaaak(@NotNull String param1String1, int param1Int, @Nullable class_1299<? extends class_1308> param1class_1299, @Nullable String param1String2, @Nullable Function3<? super class_3218, ? super class_243, ? super String, ? extends class_1308> param1Function3) {
      Intrinsics.checkNotNullParameter(param1String1, (String)aaaaam((char)-1515126784));
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal(param1String1, param1Int, param1class_1299, param1String2, param1Function3);
    }
    
    @NotNull
    public String toString() {
      return "EventEntry(name=" + this.我草你怎么反编译我模组aaaaaa + ", count=" + this.我草你怎么反编译我模组aaaaab + ", entityType=" + this.我草你怎么反编译我模组aaaaac + ", summonCommand=" + this.我草你怎么反编译我模组aaaaad + ", customSpawnHandler=" + this.我草你怎么反编译我模组aaaaae + ")";
    }
    
    public int hashCode() {
      null = this.我草你怎么反编译我模组aaaaaa.hashCode();
      null = null * 31 + Integer.hashCode(this.我草你怎么反编译我模组aaaaab);
      null = null * 31 + ((this.我草你怎么反编译我模组aaaaac == null) ? 0 : this.我草你怎么反编译我模组aaaaac.hashCode());
      null = null * 31 + ((this.我草你怎么反编译我模组aaaaad == null) ? 0 : this.我草你怎么反编译我模组aaaaad.hashCode());
      return null * 31 + ((this.我草你怎么反编译我模组aaaaae == null) ? 0 : this.我草你怎么反编译我模组aaaaae.hashCode());
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal)param1Object;
      return !Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaaa, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal1.我草你怎么反编译我模组aaaaaa) ? false : ((this.我草你怎么反编译我模组aaaaab != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal1.我草你怎么反编译我模组aaaaab) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaac, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal1.我草你怎么反编译我模组aaaaac) ? false : (!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaad, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal1.我草你怎么反编译我模组aaaaad) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaae, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaal1.我草你怎么反编译我模组aaaaae)))));
    }
    
    static {
      byte[] arrayOfByte1 = "s4¡¬z=¤:tf\fó´}\003Û¤²E¨$`WýÏ¼×Ìx_çLãeäýc{".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "2Øì=ç¤ü".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)1499892375;
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
    
    private static Object aaaaam(char param1Char) {
      return ((Object[])aaaaaf)[param1Char];
    }
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaq {
    @NotNull
    public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaq 我草你怎么反编译我模组aaaaaa = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaq();
    
    private static int 我草你怎么反编译我模组aaaaab;
    
    public final int 你为什么要破解我的代码aaaaaa() {
      我草你怎么反编译我模组aaaaab++;
      return 我草你怎么反编译我模组aaaaab;
    }
  }
}
