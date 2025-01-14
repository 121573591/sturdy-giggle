package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaah;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaH;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaQ;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaap;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabn;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab;
import cn.pixellive.mc.game.PixelLiveGameMod;
import cn.pixellive.mc.game.event.live.LiveChatEvent;
import cn.pixellive.mc.game.event.live.LiveEnterRoomEvent;
import cn.pixellive.mc.game.event.live.LiveGiftEvent;
import cn.pixellive.mc.game.event.live.LiveLikeEvent;
import com.google.common.eventbus.Subscribe;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import net.minecraft.class_1259;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1428;
import net.minecraft.class_1541;
import net.minecraft.class_1695;
import net.minecraft.class_1928;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2442;
import net.minecraft.class_2555;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2768;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_3002;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5134;
import net.minecraft.class_5575;
import net.minecraft.class_6880;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nPeakChallengeStage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PeakChallengeStage.kt\ncn/pixellive/mc/game/stage/PeakChallengeStage\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,2800:1\n1855#2,2:2801\n1855#2,2:2803\n1855#2,2:2805\n1855#2,2:2807\n1855#2,2:2809\n1855#2,2:2811\n1855#2,2:2813\n1855#2,2:2816\n1855#2,2:2818\n1855#2,2:2820\n1855#2,2:2822\n1855#2,2:2824\n1855#2,2:2826\n1855#2,2:2828\n1855#2,2:2830\n1855#2,2:2832\n1726#2,3:2834\n1855#2,2:2837\n1855#2,2:2839\n1855#2,2:2841\n1855#2,2:2843\n1855#2,2:2845\n766#2:2847\n857#2,2:2848\n1855#2,2:2850\n1855#2,2:2852\n766#2:2854\n857#2,2:2855\n766#2:2857\n857#2,2:2858\n1855#2,2:2860\n1855#2,2:2862\n766#2:2864\n857#2,2:2865\n1855#2,2:2867\n1855#2,2:2869\n1855#2,2:2871\n1855#2,2:2873\n1855#2,2:2875\n1855#2,2:2877\n1864#2,3:2879\n1855#2,2:2882\n1#3:2815\n*S KotlinDebug\n*F\n+ 1 PeakChallengeStage.kt\ncn/pixellive/mc/game/stage/PeakChallengeStage\n*L\n186#1:2801,2\n269#1:2803,2\n303#1:2805,2\n317#1:2807,2\n395#1:2809,2\n411#1:2811,2\n425#1:2813,2\n523#1:2816,2\n541#1:2818,2\n686#1:2820,2\n712#1:2822,2\n727#1:2824,2\n743#1:2826,2\n760#1:2828,2\n774#1:2830,2\n851#1:2832,2\n978#1:2834,3\n1001#1:2837,2\n1020#1:2839,2\n1043#1:2841,2\n1067#1:2843,2\n1087#1:2845,2\n1243#1:2847\n1243#1:2848,2\n1246#1:2850,2\n1447#1:2852,2\n1462#1:2854\n1462#1:2855,2\n1463#1:2857\n1463#1:2858,2\n1464#1:2860,2\n1503#1:2862,2\n1540#1:2864\n1540#1:2865,2\n1541#1:2867,2\n2241#1:2869,2\n2247#1:2871,2\n2404#1:2873,2\n2428#1:2875,2\n2498#1:2877,2\n2543#1:2879,3\n2624#1:2882,2\n*E\n"})
public final class aaaaae extends aaaabG {
  @NotNull
  public static final aaaaae 我草你怎么反编译我模组aaaaaE;
  
  @Nullable
  private static MinecraftServer 我草你怎么反编译我模组aaaaab;
  
  private static class_3002 我草你怎么反编译我模组aaaaaF;
  
  private static class_3002 我草你怎么反编译我模组aaaaaG;
  
  private static class_3002 我草你怎么反编译我模组aaaaaH;
  
  private static class_3002 我草你怎么反编译我模组aaaaae;
  
  private static int 我草你怎么反编译我模组aaaaaI;
  
  private static int 我草你怎么反编译我模组aaaaaJ;
  
  private static int 我草你怎么反编译我模组aaaaaK;
  
  private static int 我草你怎么反编译我模组aaaaaL;
  
  private static final double 我草你怎么反编译我模组aaaaaM;
  
  private static final int 我草你怎么反编译我模组aaaaaN;
  
  public static final int 我草你怎么反编译我模组aaaaaO;
  
  private static double 我草你怎么反编译我模组aaaaaP;
  
  private static double 我草你怎么反编译我模组aaaaaQ;
  
  private static double 我草你怎么反编译我模组aaaaaR;
  
  private static int 我草你怎么反编译我模组aaaaaS;
  
  private static boolean 我草你怎么反编译我模组aaaaaT;
  
  private static int 我草你怎么反编译我模组aaaaad;
  
  @NotNull
  private static final AtomicInteger 我草你怎么反编译我模组aaaaaU;
  
  @NotNull
  private static final AtomicInteger 我草你怎么反编译我模组aaaaaV;
  
  private static int 我草你怎么反编译我模组aaaaaW;
  
  private static final int 我草你怎么反编译我模组aaaaaX;
  
  private static final int 我草你怎么反编译我模组aaaaaY;
  
  private static final int 我草你怎么反编译我模组aaaaaZ;
  
  private static final int 我草你怎么反编译我模组aaaaba;
  
  private static final int 我草你怎么反编译我模组aaaabb;
  
  private static final int 我草你怎么反编译我模组aaaabc;
  
  private static final int 我草你怎么反编译我模组aaaabd;
  
  private static final int 我草你怎么反编译我模组aaaabe;
  
  @NotNull
  private static final Set<class_6880<class_1291>> 我草你怎么反编译我模组aaaabf;
  
  private static boolean 我草你怎么反编译我模组aaaabg;
  
  @NotNull
  private static final Map<UUID, List<class_1428>> 我草你怎么反编译我模组aaaabh;
  
  @NotNull
  private static final Map<UUID, Long> 我草你怎么反编译我模组aaaabi;
  
  private static int 我草你怎么反编译我模组aaaabj;
  
  static Object aaaabk;
  
  @NotNull
  public String 你为什么要破解我的代码aaaaae() {
    return (String)aaaadf((char)844234752);
  }
  
  @NotNull
  public String 你为什么要破解我的代码aaaaaf() {
    return (String)aaaadf((char)-2093547519);
  }
  
  public void 你为什么要破解我的代码aaaaah(@NotNull MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramMinecraftServer, (String)aaaadf((char)231079938));
    我草你怎么反编译我模组aaaaab = paramMinecraftServer;
    Intrinsics.checkNotNullExpressionValue(paramMinecraftServer.method_30002(), (String)aaaadf((char)1529217027));
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaak(paramMinecraftServer.method_30002());
    我草你怎么反编译我模组aaaaaJ = 我草你怎么反编译我模组aaaaaI;
    你为什么要破解我的代码aaaacS();
    ((class_1928.class_4310)paramMinecraftServer.method_3767().method_20746(class_1928.field_19400)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_3767().method_20746(class_1928.field_19392)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_3767().method_20746(class_1928.field_19393)).method_20758(false, paramMinecraftServer);
    ((class_1928.class_4310)paramMinecraftServer.method_3767().method_20746(class_1928.field_19391)).method_20758(false, paramMinecraftServer);
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaai();
    你为什么要破解我的代码aaaabM(paramMinecraftServer);
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().register(this);
    你为什么要破解我的代码aaaacB(this, 500, 0, true, 2, null);
    你为什么要破解我的代码aaaabz();
    你为什么要破解我的代码aaaaaH(paramMinecraftServer);
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
    //   47: ifeq -> 192
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
    //   78: ldc_w -721747964
    //   81: i2c
    //   82: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   85: checkcast java/lang/String
    //   88: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   91: astore #12
    //   93: goto -> 115
    //   96: astore #13
    //   98: aload #11
    //   100: ldc_w -809893883
    //   103: i2c
    //   104: invokestatic aaaadf : (C)Ljava/lang/Object;
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
    //   138: aload #9
    //   140: invokevirtual method_9520 : ()F
    //   143: fstore #12
    //   145: iload_3
    //   146: istore #13
    //   148: iload #13
    //   150: iconst_1
    //   151: iadd
    //   152: istore_3
    //   153: goto -> 187
    //   156: astore #11
    //   158: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   161: aload #9
    //   163: invokevirtual method_9539 : ()Ljava/lang/String;
    //   166: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   171: aload #11
    //   173: checkcast java/lang/Throwable
    //   176: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   181: iload #4
    //   183: iconst_1
    //   184: iadd
    //   185: istore #4
    //   187: nop
    //   188: nop
    //   189: goto -> 40
    //   192: nop
    //   193: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   196: iload_3
    //   197: iload #4
    //   199: <illegal opcode> makeConcatWithConstants : (II)Ljava/lang/String;
    //   204: invokeinterface info : (Ljava/lang/String;)V
    //   209: return
    // Exception table:
    //   from	to	target	type
    //   69	153	156	java/lang/Exception
    //   75	93	96	java/lang/NoSuchFieldException
  }
  
  public void 你为什么要破解我的代码aaaaai(@NotNull MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullParameter(paramMinecraftServer, (String)aaaadf((char)-12058622));
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaal();
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().unregister(this);
    你为什么要破解我的代码aaaabN(paramMinecraftServer);
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaai();
    我草你怎么反编译我模组aaaaaT = false;
    if (我草你怎么反编译我模组aaaaaH == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)1002307590)); 
    null.method_14091(false);
    我草你怎么反编译我模组aaaaaS = 10;
  }
  
  @NotNull
  public class_243 你为什么要破解我的代码aaaaak() {
    return new class_243(-2.0D, 4.0D, 72.0D);
  }
  
  @NotNull
  public MinecraftServer 你为什么要破解我的代码aaaaal() {
    Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaab);
    return 我草你怎么反编译我模组aaaaab;
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaan(@NotNull aaaaac paramaaaaac) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 589299719
    //   4: i2c
    //   5: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_0
    //   15: invokespecial 你为什么要破解我的代码aaaack : ()V
    //   18: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   21: dup
    //   22: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   25: invokevirtual method_3780 : ()I
    //   28: iconst_2
    //   29: irem
    //   30: ifne -> 228
    //   33: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   36: invokevirtual 你为什么要破解我的代码aaaaae : ()Z
    //   39: ifeq -> 47
    //   42: aload_0
    //   43: invokespecial 你为什么要破解我的代码aaaacm : ()Z
    //   46: pop
    //   47: aload_0
    //   48: invokespecial 你为什么要破解我的代码aaaacO : ()V
    //   51: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   54: dup
    //   55: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   58: invokevirtual method_3780 : ()I
    //   61: bipush #20
    //   63: irem
    //   64: ifne -> 129
    //   67: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   70: invokevirtual 你为什么要破解我的代码aaaaae : ()Z
    //   73: ifeq -> 129
    //   76: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   79: invokevirtual 你为什么要破解我的代码aaaaag : ()I
    //   82: invokestatic getSign : (I)I
    //   85: ifle -> 110
    //   88: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   91: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   94: ldc_w -511049720
    //   97: i2c
    //   98: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   101: checkcast java/lang/String
    //   104: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   107: goto -> 129
    //   110: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   113: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   116: ldc_w 858980361
    //   119: i2c
    //   120: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   123: checkcast java/lang/String
    //   126: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   129: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabj : I
    //   132: ifle -> 228
    //   135: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   138: dup
    //   139: ifnull -> 214
    //   142: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   145: dup
    //   146: ifnull -> 214
    //   149: invokevirtual method_14571 : ()Ljava/util/List;
    //   152: dup
    //   153: ifnull -> 214
    //   156: checkcast java/lang/Iterable
    //   159: astore_2
    //   160: iconst_0
    //   161: istore_3
    //   162: aload_2
    //   163: invokeinterface iterator : ()Ljava/util/Iterator;
    //   168: astore #4
    //   170: aload #4
    //   172: invokeinterface hasNext : ()Z
    //   177: ifeq -> 210
    //   180: aload #4
    //   182: invokeinterface next : ()Ljava/lang/Object;
    //   187: astore #5
    //   189: aload #5
    //   191: checkcast net/minecraft/class_3222
    //   194: astore #6
    //   196: iconst_0
    //   197: istore #7
    //   199: aload #6
    //   201: iconst_0
    //   202: invokevirtual method_20803 : (I)V
    //   205: nop
    //   206: nop
    //   207: goto -> 170
    //   210: nop
    //   211: goto -> 216
    //   214: pop
    //   215: nop
    //   216: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabj : I
    //   219: istore #8
    //   221: iload #8
    //   223: iconst_m1
    //   224: iadd
    //   225: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabj : I
    //   228: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   231: dup
    //   232: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   235: invokevirtual method_3780 : ()I
    //   238: iconst_3
    //   239: irem
    //   240: ifne -> 255
    //   243: aload_0
    //   244: invokespecial 你为什么要破解我的代码aaaabz : ()V
    //   247: aload_0
    //   248: invokespecial 你为什么要破解我的代码aaaabG : ()V
    //   251: aload_0
    //   252: invokevirtual 你为什么要破解我的代码aaaacT : ()V
    //   255: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   258: dup
    //   259: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   262: invokevirtual method_3780 : ()I
    //   265: iconst_5
    //   266: irem
    //   267: ifne -> 282
    //   270: aload_0
    //   271: invokespecial 你为什么要破解我的代码aaaace : ()V
    //   274: aload_0
    //   275: invokespecial 你为什么要破解我的代码aaaacj : ()V
    //   278: aload_0
    //   279: invokespecial 你为什么要破解我的代码aaaacf : ()V
    //   282: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   285: dup
    //   286: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   289: invokevirtual method_3780 : ()I
    //   292: bipush #8
    //   294: irem
    //   295: ifne -> 320
    //   298: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaW : I
    //   301: ifle -> 320
    //   304: aload_0
    //   305: invokespecial 你为什么要破解我的代码aaaacg : ()V
    //   308: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaW : I
    //   311: istore #8
    //   313: iload #8
    //   315: iconst_m1
    //   316: iadd
    //   317: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaW : I
    //   320: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   323: dup
    //   324: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   327: invokevirtual method_3780 : ()I
    //   330: bipush #20
    //   332: irem
    //   333: ifne -> 482
    //   336: aload_0
    //   337: invokespecial 你为什么要破解我的代码aaaach : ()V
    //   340: aload_0
    //   341: invokespecial 你为什么要破解我的代码aaaaby : ()V
    //   344: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabg : Z
    //   347: ifeq -> 354
    //   350: aload_0
    //   351: invokespecial 你为什么要破解我的代码aaaabD : ()V
    //   354: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   357: ifle -> 372
    //   360: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   363: istore #8
    //   365: iload #8
    //   367: iconst_m1
    //   368: iadd
    //   369: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   372: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   375: ifeq -> 382
    //   378: aload_0
    //   379: invokespecial 你为什么要破解我的代码aaaabJ : ()V
    //   382: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   385: dup
    //   386: ifnull -> 476
    //   389: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   392: dup
    //   393: ifnull -> 476
    //   396: invokevirtual method_14571 : ()Ljava/util/List;
    //   399: dup
    //   400: ifnull -> 476
    //   403: checkcast java/lang/Iterable
    //   406: astore_2
    //   407: iconst_0
    //   408: istore_3
    //   409: aload_2
    //   410: invokeinterface iterator : ()Ljava/util/Iterator;
    //   415: astore #4
    //   417: aload #4
    //   419: invokeinterface hasNext : ()Z
    //   424: ifeq -> 472
    //   427: aload #4
    //   429: invokeinterface next : ()Ljava/lang/Object;
    //   434: astore #5
    //   436: aload #5
    //   438: checkcast net/minecraft/class_3222
    //   441: astore #6
    //   443: iconst_0
    //   444: istore #7
    //   446: aload #6
    //   448: invokevirtual method_7344 : ()Lnet/minecraft/class_1702;
    //   451: bipush #20
    //   453: invokevirtual method_7580 : (I)V
    //   456: aload #6
    //   458: invokevirtual method_7344 : ()Lnet/minecraft/class_1702;
    //   461: ldc_w 5.0
    //   464: invokevirtual method_7581 : (F)V
    //   467: nop
    //   468: nop
    //   469: goto -> 417
    //   472: nop
    //   473: goto -> 478
    //   476: pop
    //   477: nop
    //   478: aload_0
    //   479: invokespecial 你为什么要破解我的代码aaaabe : ()V
    //   482: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   485: dup
    //   486: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   489: invokevirtual method_3780 : ()I
    //   492: bipush #10
    //   494: irem
    //   495: ifne -> 502
    //   498: aload_0
    //   499: invokespecial 你为什么要破解我的代码aaaabL : ()V
    //   502: return
  }
  
  private final void 你为什么要破解我的代码aaaabe() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx;
    //   3: invokevirtual 你为什么要破解我的代码aaaaag : ()Z
    //   6: ifeq -> 144
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx;
    //   12: invokevirtual 你为什么要破解我的代码aaaaac : ()J
    //   15: invokestatic currentTimeMillis : ()J
    //   18: lsub
    //   19: sipush #1000
    //   22: i2l
    //   23: ldiv
    //   24: l2i
    //   25: istore_1
    //   26: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   29: dup
    //   30: ifnull -> 142
    //   33: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   36: dup
    //   37: ifnull -> 142
    //   40: invokevirtual method_14571 : ()Ljava/util/List;
    //   43: dup
    //   44: ifnull -> 142
    //   47: checkcast java/lang/Iterable
    //   50: astore_2
    //   51: iconst_0
    //   52: istore_3
    //   53: aload_2
    //   54: invokeinterface iterator : ()Ljava/util/Iterator;
    //   59: astore #4
    //   61: aload #4
    //   63: invokeinterface hasNext : ()Z
    //   68: ifeq -> 138
    //   71: aload #4
    //   73: invokeinterface next : ()Ljava/lang/Object;
    //   78: astore #5
    //   80: aload #5
    //   82: checkcast net/minecraft/class_3222
    //   85: astore #6
    //   87: iconst_0
    //   88: istore #7
    //   90: aload #6
    //   92: ldc_w -605618166
    //   95: i2c
    //   96: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   99: checkcast java/lang/String
    //   102: iconst_1
    //   103: anewarray java/lang/Object
    //   106: astore #8
    //   108: aload #8
    //   110: iconst_0
    //   111: iload_1
    //   112: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   117: invokestatic method_43470 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   120: aastore
    //   121: aload #8
    //   123: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   126: checkcast net/minecraft/class_2561
    //   129: iconst_1
    //   130: invokevirtual method_7353 : (Lnet/minecraft/class_2561;Z)V
    //   133: nop
    //   134: nop
    //   135: goto -> 61
    //   138: nop
    //   139: goto -> 144
    //   142: pop
    //   143: nop
    //   144: return
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaabf(@NotNull LiveChatEvent paramLiveChatEvent) {
    Intrinsics.checkNotNullParameter(paramLiveChatEvent, (String)aaaadf((char)-1149501433));
    if (Intrinsics.areEqual(paramLiveChatEvent.getMessage(), aaaadf((char)-439681013))) {
      String str = aaaaab.Companion.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaao() ? paramLiveChatEvent.getUsername() : aaaaai.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(paramLiveChatEvent.getUsername());
      你为什么要破解我的代码aaaabx(str, 1);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaabg(String paramString, int paramInt) {
    Intrinsics.checkNotNullExpressionValue(class_1299.field_6051, (String)aaaadf((char)-1070661620));
    你为什么要破解我的代码aaaacd(paramString, paramInt, class_1299.field_6051);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)-368771059));
  }
  
  private final void 你为什么要破解我的代码aaaabh(String paramString, int paramInt) {
    Intrinsics.checkNotNullExpressionValue(class_1299.field_6137, (String)aaaadf((char)-1181024242));
    你为什么要破解我的代码aaaacd(paramString, paramInt, class_1299.field_6137);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)-2112290801));
  }
  
  private final void 你为什么要破解我的代码aaaabi(String paramString, int paramInt) {
    Intrinsics.checkNotNullExpressionValue(class_1299.field_6076, (String)aaaadf((char)-631504880));
    你为什么要破解我的代码aaaacd(paramString, paramInt, class_1299.field_6076);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)867696657));
  }
  
  private final void 你为什么要破解我的代码aaaabj(String paramString, int paramInt) {
    Intrinsics.checkNotNullExpressionValue(class_1299.field_6090, (String)aaaadf((char)-1230241774));
    你为什么要破解我的代码aaaacd(paramString, paramInt, class_1299.field_6090);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)840957971));
  }
  
  private final void 你为什么要破解我的代码aaaabk(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 你为什么要破解我的代码aaaaak : ()Lnet/minecraft/class_243;
    //   4: astore_2
    //   5: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   8: dup
    //   9: ifnull -> 101
    //   12: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   15: dup
    //   16: ifnull -> 101
    //   19: invokevirtual method_14571 : ()Ljava/util/List;
    //   22: dup
    //   23: ifnull -> 101
    //   26: checkcast java/lang/Iterable
    //   29: astore_3
    //   30: iconst_0
    //   31: istore #4
    //   33: aload_3
    //   34: invokeinterface iterator : ()Ljava/util/Iterator;
    //   39: astore #5
    //   41: aload #5
    //   43: invokeinterface hasNext : ()Z
    //   48: ifeq -> 97
    //   51: aload #5
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: astore #6
    //   60: aload #6
    //   62: checkcast net/minecraft/class_3222
    //   65: astore #7
    //   67: iconst_0
    //   68: istore #8
    //   70: aload #7
    //   72: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   75: aload #7
    //   77: aload_2
    //   78: getfield field_1352 : D
    //   81: aload_2
    //   82: getfield field_1351 : D
    //   85: aload_2
    //   86: getfield field_1350 : D
    //   89: invokestatic 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;DDD)V
    //   92: nop
    //   93: nop
    //   94: goto -> 41
    //   97: nop
    //   98: goto -> 103
    //   101: pop
    //   102: nop
    //   103: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   106: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   109: ldc_w -77201388
    //   112: i2c
    //   113: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   116: checkcast java/lang/String
    //   119: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   122: return
  }
  
  private final void 你为什么要破解我的代码aaaabl(String paramString) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   6: bipush #10
    //   8: iadd
    //   9: i2d
    //   10: dstore_2
    //   11: new net/minecraft/class_243
    //   14: dup
    //   15: ldc2_w 7.5
    //   18: dload_2
    //   19: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaR : D
    //   22: iconst_2
    //   23: i2d
    //   24: dsub
    //   25: invokespecial <init> : (DDD)V
    //   28: astore #4
    //   30: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   33: dup
    //   34: ifnull -> 131
    //   37: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   40: dup
    //   41: ifnull -> 131
    //   44: invokevirtual method_14571 : ()Ljava/util/List;
    //   47: dup
    //   48: ifnull -> 131
    //   51: checkcast java/lang/Iterable
    //   54: astore #5
    //   56: iconst_0
    //   57: istore #6
    //   59: aload #5
    //   61: invokeinterface iterator : ()Ljava/util/Iterator;
    //   66: astore #7
    //   68: aload #7
    //   70: invokeinterface hasNext : ()Z
    //   75: ifeq -> 127
    //   78: aload #7
    //   80: invokeinterface next : ()Ljava/lang/Object;
    //   85: astore #8
    //   87: aload #8
    //   89: checkcast net/minecraft/class_3222
    //   92: astore #9
    //   94: iconst_0
    //   95: istore #10
    //   97: aload #9
    //   99: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   102: aload #9
    //   104: aload #4
    //   106: getfield field_1352 : D
    //   109: aload #4
    //   111: getfield field_1351 : D
    //   114: aload #4
    //   116: getfield field_1350 : D
    //   119: invokestatic 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;DDD)V
    //   122: nop
    //   123: nop
    //   124: goto -> 68
    //   127: nop
    //   128: goto -> 133
    //   131: pop
    //   132: nop
    //   133: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   136: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   139: ldc_w 1212350485
    //   142: i2c
    //   143: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   146: checkcast java/lang/String
    //   149: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   152: return
  }
  
  private final void 你为什么要破解我的代码aaaabm(String paramString, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
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
    //   97: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   100: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   103: ldc_w 1194197014
    //   106: i2c
    //   107: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   110: checkcast java/lang/String
    //   113: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   116: return
  }
  
  private final void 你为什么要破解我的代码aaaabn(String paramString, int paramInt) {
    我草你怎么反编译我模组aaaaaW += paramInt;
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)-1215037417));
  }
  
  private final void 你为什么要破解我的代码aaaabo(String paramString, int paramInt) {
    我草你怎么反编译我模组aaaaaU.addAndGet(paramInt);
  }
  
  private final void 你为什么要破解我的代码aaaabp(String paramString, int paramInt) {
    我草你怎么反编译我模组aaaaaV.addAndGet(paramInt * 10);
  }
  
  private final void 你为什么要破解我的代码aaaabq(String paramString, int paramInt) {
    int i = RangesKt.coerceIn(paramInt, 1, 1490);
    你为什么要破解我的代码aaaacr(i);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)1768685576));
  }
  
  private final void 你为什么要破解我的代码aaaabr(String paramString, int paramInt) {
    int i = RangesKt.coerceIn(paramInt, 1, 1490);
    你为什么要破解我的代码aaaacs(i);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)1339359241));
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaar(@NotNull LiveGiftEvent paramLiveGiftEvent) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w -183959545
    //   4: i2c
    //   5: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokevirtual getGiftCount : ()I
    //   18: istore_2
    //   19: iload_2
    //   20: ifle -> 690
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
    //   215: instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabB
    //   218: ifeq -> 229
    //   221: aload #6
    //   223: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabB
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
    //   274: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx;
    //   277: invokevirtual 你为什么要破解我的代码aaaaae : ()I
    //   280: imul
    //   281: istore #6
    //   283: aload_0
    //   284: aload_3
    //   285: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   288: iload #6
    //   290: aload #5
    //   292: aconst_null
    //   293: bipush #8
    //   295: aconst_null
    //   296: invokestatic 你为什么要破解我的代码aaaabt : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/Object;)V
    //   299: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaI : I
    //   302: aload_3
    //   303: invokevirtual 你为什么要破解我的代码aaaaaG : ()I
    //   306: iload_2
    //   307: imul
    //   308: iadd
    //   309: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaI : I
    //   312: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   315: aload_3
    //   316: invokevirtual 你为什么要破解我的代码aaaaaG : ()I
    //   319: iload_2
    //   320: imul
    //   321: iadd
    //   322: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   325: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaK : I
    //   328: aload_3
    //   329: invokevirtual 你为什么要破解我的代码aaaaag : ()I
    //   332: iload_2
    //   333: imul
    //   334: iadd
    //   335: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaK : I
    //   338: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   341: dup
    //   342: ifnull -> 537
    //   345: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   348: dup
    //   349: ifnull -> 537
    //   352: invokevirtual method_14571 : ()Ljava/util/List;
    //   355: dup
    //   356: ifnull -> 537
    //   359: checkcast java/lang/Iterable
    //   362: astore #11
    //   364: iconst_0
    //   365: istore #12
    //   367: aload #11
    //   369: invokeinterface iterator : ()Ljava/util/Iterator;
    //   374: astore #13
    //   376: aload #13
    //   378: invokeinterface hasNext : ()Z
    //   383: ifeq -> 533
    //   386: aload #13
    //   388: invokeinterface next : ()Ljava/lang/Object;
    //   393: astore #14
    //   395: aload #14
    //   397: checkcast net/minecraft/class_3222
    //   400: astore #15
    //   402: iconst_0
    //   403: istore #16
    //   405: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   408: aload #15
    //   410: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   413: aload #15
    //   415: ldc_w -1234501608
    //   418: i2c
    //   419: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   422: checkcast java/lang/String
    //   425: iconst_1
    //   426: anewarray java/lang/Object
    //   429: astore #17
    //   431: aload #17
    //   433: iconst_0
    //   434: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   437: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   440: aload_3
    //   441: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   444: invokevirtual 你为什么要破解我的代码aaaaak : (Ljava/lang/String;)Ljava/lang/String;
    //   447: iload_2
    //   448: aload_3
    //   449: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   452: imul
    //   453: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   458: aastore
    //   459: aload #17
    //   461: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   464: checkcast net/minecraft/class_2561
    //   467: ldc_w 1835466777
    //   470: i2c
    //   471: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   474: checkcast java/lang/String
    //   477: iconst_2
    //   478: anewarray java/lang/Object
    //   481: astore #17
    //   483: aload #17
    //   485: iconst_0
    //   486: aload #5
    //   488: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   493: aastore
    //   494: aload #17
    //   496: iconst_1
    //   497: aload_1
    //   498: invokevirtual getGiftName : ()Ljava/lang/String;
    //   501: aload_1
    //   502: invokevirtual getGiftCount : ()I
    //   505: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   510: aastore
    //   511: aload #17
    //   513: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   516: checkcast net/minecraft/class_2561
    //   519: bipush #10
    //   521: bipush #60
    //   523: bipush #10
    //   525: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   528: nop
    //   529: nop
    //   530: goto -> 376
    //   533: nop
    //   534: goto -> 539
    //   537: pop
    //   538: nop
    //   539: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   542: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   545: invokevirtual 你为什么要破解我的代码aaaaam : ()Z
    //   548: ifeq -> 686
    //   551: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   554: dup
    //   555: ifnull -> 684
    //   558: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   561: dup
    //   562: ifnull -> 684
    //   565: invokevirtual method_14571 : ()Ljava/util/List;
    //   568: dup
    //   569: ifnull -> 684
    //   572: checkcast java/lang/Iterable
    //   575: astore #11
    //   577: iconst_0
    //   578: istore #12
    //   580: aload #11
    //   582: invokeinterface iterator : ()Ljava/util/Iterator;
    //   587: astore #13
    //   589: aload #13
    //   591: invokeinterface hasNext : ()Z
    //   596: ifeq -> 680
    //   599: aload #13
    //   601: invokeinterface next : ()Ljava/lang/Object;
    //   606: astore #14
    //   608: aload #14
    //   610: checkcast net/minecraft/class_3222
    //   613: astore #15
    //   615: iconst_0
    //   616: istore #16
    //   618: aload #15
    //   620: ldc_w 1892810778
    //   623: i2c
    //   624: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   627: checkcast java/lang/String
    //   630: iconst_2
    //   631: anewarray java/lang/Object
    //   634: astore #17
    //   636: aload #17
    //   638: iconst_0
    //   639: aload #5
    //   641: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   646: aastore
    //   647: aload #17
    //   649: iconst_1
    //   650: aload_1
    //   651: invokevirtual getGiftName : ()Ljava/lang/String;
    //   654: aload_1
    //   655: invokevirtual getGiftCount : ()I
    //   658: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   663: aastore
    //   664: aload #17
    //   666: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   669: checkcast net/minecraft/class_2561
    //   672: invokevirtual method_43496 : (Lnet/minecraft/class_2561;)V
    //   675: nop
    //   676: nop
    //   677: goto -> 589
    //   680: nop
    //   681: goto -> 686
    //   684: pop
    //   685: nop
    //   686: aload_0
    //   687: invokespecial 你为什么要破解我的代码aaaabz : ()V
    //   690: return
  }
  
  public final void 你为什么要破解我的代码aaaabs(@NotNull String paramString1, int paramInt, @NotNull String paramString2, @NotNull String paramString3) {
    Intrinsics.checkNotNullParameter(paramString1, (String)aaaadf((char)-2066612197));
    Intrinsics.checkNotNullParameter(paramString2, (String)aaaadf((char)-1275396068));
    Intrinsics.checkNotNullParameter(paramString3, (String)aaaadf((char)-719716323));
    String str = paramString1;
    switch (str.hashCode()) {
      case 662199993:
        if (!str.equals(aaaadf((char)-651624418)))
          break; 
        你为什么要破解我的代码aaaabi(paramString2, paramInt);
        break;
      case 761897:
        if (!str.equals(aaaadf((char)80871455)))
          break; 
        你为什么要破解我的代码aaaacH(paramString2, paramInt);
        break;
      case 883930921:
        if (!str.equals(aaaadf((char)-156368864)))
          break; 
        你为什么要破解我的代码aaaabn(paramString2, paramInt);
        break;
      case 831050670:
        if (!str.equals(aaaadf((char)414646305)))
          break; 
        你为什么要破解我的代码aaaabr(paramString2, paramInt);
        break;
      case 802528979:
        if (!str.equals(aaaadf((char)2068578338)))
          break; 
        你为什么要破解我的代码aaaacL(paramString2, paramInt);
        break;
      case 22211413:
        if (!str.equals(aaaadf((char)-1293352925)))
          break; 
        你为什么要破解我的代码aaaabj(paramString2, paramInt);
        break;
      case 36939092:
        if (!str.equals(aaaadf((char)1338376228)))
          break; 
        你为什么要破解我的代码aaaacK(paramString2, paramInt);
        break;
      case 666083:
        if (!str.equals(aaaadf((char)28508197)))
          break; 
        你为什么要破解我的代码aaaabg(paramString2, paramInt);
        break;
      case 717951744:
        if (!str.equals(aaaadf((char)-83165146)))
          break; 
        你为什么要破解我的代码aaaabo(paramString2, paramInt);
        break;
      case 831035300:
        if (!str.equals(aaaadf((char)1639252007)))
          break; 
        你为什么要破解我的代码aaaabq(paramString2, paramInt);
        break;
      case 1061758:
        if (!str.equals(aaaadf((char)1893793832)))
          break; 
        你为什么要破解我的代码aaaabm(paramString2, paramInt);
        break;
      case 974368:
        if (!str.equals(aaaadf((char)-567672791)))
          break; 
        你为什么要破解我的代码aaaabu(paramString2, paramInt);
        break;
      case 1172814967:
        if (!str.equals(aaaadf((char)1315373098)))
          break; 
        你为什么要破解我的代码aaaabl(paramString2);
        break;
      case 1174294700:
        if (!str.equals(aaaadf((char)-851115989)))
          break; 
        你为什么要破解我的代码aaaacl(paramString2, paramInt);
        break;
      case 718014488:
        if (!str.equals(aaaadf((char)751632428)))
          break; 
        你为什么要破解我的代码aaaabp(paramString2, paramInt);
        break;
      case 683863220:
        if (!str.equals(aaaadf((char)689504301)))
          break; 
        你为什么要破解我的代码aaaabk(paramString2);
        break;
      case 798946758:
        if (!str.equals(aaaadf((char)-558956498)))
          break; 
        你为什么要破解我的代码aaaacJ(paramString2, paramInt);
        break;
      case 831419700:
        if (!str.equals(aaaadf((char)-2135490513)))
          break; 
        你为什么要破解我的代码aaaacI(paramString2);
        break;
      case 1267438:
        if (!str.equals(aaaadf((char)291766320)))
          break; 
        你为什么要破解我的代码aaaabh(paramString2, paramInt);
        break;
      case 23182271:
        if (!str.equals(aaaadf((char)-1592983503)))
          break; 
        你为什么要破解我的代码aaaacQ(paramString2, paramInt);
        break;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaabu(String paramString, int paramInt) {
    int i = paramInt;
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)2036203571));
    Intrinsics.checkNotNull(aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaa(), (String)aaaadf((char)1642659892));
    List<aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabH> list = ((aaaabn)aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaa()).你为什么要破解我的代码aaaaai();
    if (list.isEmpty())
      return; 
    aaaaaQ aaaaaQ = new aaaaaQ(0, 0, null, 7, null);
    byte b = 30;
    Integer integer = null;
    integer = (我草你怎么反编译我模组aaaaab != null) ? Integer.valueOf(你为什么要破解我的代码aaaacV(我草你怎么反编译我模组aaaaab, new aaaacn(aaaaaQ, b, list, paramString, i))) : null;
  }
  
  private final void 你为什么要破解我的代码aaaabv(aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabH paramaaaabH, String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   4: astore_3
    //   5: aload_1
    //   6: invokevirtual 你为什么要破解我的代码aaaaal : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaU;
    //   9: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   12: istore #4
    //   14: aload_1
    //   15: invokevirtual 你为什么要破解我的代码aaaaal : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaU;
    //   18: invokevirtual 你为什么要破解我的代码aaaaab : ()I
    //   21: istore #5
    //   23: aload_1
    //   24: invokevirtual 你为什么要破解我的代码aaaaal : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaU;
    //   27: invokevirtual 你为什么要破解我的代码aaaaai : ()I
    //   30: istore #6
    //   32: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaK : I
    //   35: iload #5
    //   37: iadd
    //   38: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaK : I
    //   41: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaI : I
    //   44: iload #6
    //   46: iadd
    //   47: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaI : I
    //   50: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   53: iload #6
    //   55: iadd
    //   56: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   59: aload_0
    //   60: aload_3
    //   61: iload #4
    //   63: aload_2
    //   64: aconst_null
    //   65: bipush #8
    //   67: aconst_null
    //   68: invokestatic 你为什么要破解我的代码aaaabt : (Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/Object;)V
    //   71: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   74: dup
    //   75: ifnull -> 264
    //   78: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   81: dup
    //   82: ifnull -> 264
    //   85: invokevirtual method_14571 : ()Ljava/util/List;
    //   88: dup
    //   89: ifnull -> 264
    //   92: checkcast java/lang/Iterable
    //   95: astore #7
    //   97: iconst_0
    //   98: istore #8
    //   100: aload #7
    //   102: invokeinterface iterator : ()Ljava/util/Iterator;
    //   107: astore #9
    //   109: aload #9
    //   111: invokeinterface hasNext : ()Z
    //   116: ifeq -> 260
    //   119: aload #9
    //   121: invokeinterface next : ()Ljava/lang/Object;
    //   126: astore #10
    //   128: aload #10
    //   130: checkcast net/minecraft/class_3222
    //   133: astore #11
    //   135: iconst_0
    //   136: istore #12
    //   138: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   141: aload #11
    //   143: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   146: aload #11
    //   148: ldc_w 479133749
    //   151: i2c
    //   152: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   155: checkcast java/lang/String
    //   158: iconst_1
    //   159: anewarray java/lang/Object
    //   162: astore #13
    //   164: aload #13
    //   166: iconst_0
    //   167: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   170: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   173: aload_3
    //   174: invokevirtual 你为什么要破解我的代码aaaaak : (Ljava/lang/String;)Ljava/lang/String;
    //   177: iload #4
    //   179: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;I)Ljava/lang/String;
    //   184: aastore
    //   185: aload #13
    //   187: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   190: checkcast net/minecraft/class_2561
    //   193: ldc_w 1223098422
    //   196: i2c
    //   197: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   200: checkcast java/lang/String
    //   203: iconst_2
    //   204: anewarray java/lang/Object
    //   207: astore #13
    //   209: aload #13
    //   211: iconst_0
    //   212: aload_2
    //   213: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   218: aastore
    //   219: aload #13
    //   221: iconst_1
    //   222: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaJ;
    //   225: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaH;
    //   228: aload_3
    //   229: invokevirtual 你为什么要破解我的代码aaaaak : (Ljava/lang/String;)Ljava/lang/String;
    //   232: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   237: aastore
    //   238: aload #13
    //   240: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   243: checkcast net/minecraft/class_2561
    //   246: bipush #10
    //   248: bipush #70
    //   250: bipush #20
    //   252: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   255: nop
    //   256: nop
    //   257: goto -> 109
    //   260: nop
    //   261: goto -> 266
    //   264: pop
    //   265: nop
    //   266: aload_0
    //   267: aload_1
    //   268: invokevirtual 你为什么要破解我的代码aaaaaa : ()Ljava/lang/String;
    //   271: aload_2
    //   272: invokespecial 你为什么要破解我的代码aaaabw : (Ljava/lang/String;Ljava/lang/String;)V
    //   275: aload_0
    //   276: invokespecial 你为什么要破解我的代码aaaabz : ()V
    //   279: return
  }
  
  private final void 你为什么要破解我的代码aaaabw(String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc_w -1729626057
    //   3: i2c
    //   4: invokestatic aaaadf : (C)Ljava/lang/Object;
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
    //   39: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   42: dup
    //   43: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   46: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   49: invokevirtual method_14571 : ()Ljava/util/List;
    //   52: dup
    //   53: ldc_w 245628984
    //   56: i2c
    //   57: invokestatic aaaadf : (C)Ljava/lang/Object;
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
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaaq(@NotNull LiveEnterRoomEvent paramLiveEnterRoomEvent) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 2129854471
    //   4: i2c
    //   5: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   17: dup
    //   18: ifnull -> 176
    //   21: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   24: dup
    //   25: ifnull -> 176
    //   28: invokevirtual method_14571 : ()Ljava/util/List;
    //   31: dup
    //   32: ifnull -> 176
    //   35: checkcast java/lang/Iterable
    //   38: astore_2
    //   39: iconst_0
    //   40: istore_3
    //   41: aload_2
    //   42: invokeinterface iterator : ()Ljava/util/Iterator;
    //   47: astore #4
    //   49: aload #4
    //   51: invokeinterface hasNext : ()Z
    //   56: ifeq -> 172
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
    //   87: ifeq -> 102
    //   90: aload_1
    //   91: invokevirtual getUsername : ()Ljava/lang/String;
    //   94: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   99: goto -> 117
    //   102: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaai;
    //   105: aload_1
    //   106: invokevirtual getUsername : ()Ljava/lang/String;
    //   109: invokevirtual 你为什么要破解我的代码aaaaaa : (Ljava/lang/String;)Ljava/lang/String;
    //   112: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   117: astore #8
    //   119: aload #6
    //   121: getfield field_13987 : Lnet/minecraft/class_3244;
    //   124: new net/minecraft/class_5894
    //   127: dup
    //   128: ldc_w 114360377
    //   131: i2c
    //   132: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   135: checkcast java/lang/String
    //   138: iconst_1
    //   139: anewarray java/lang/Object
    //   142: astore #9
    //   144: aload #9
    //   146: iconst_0
    //   147: aload #8
    //   149: aastore
    //   150: aload #9
    //   152: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   155: checkcast net/minecraft/class_2561
    //   158: invokespecial <init> : (Lnet/minecraft/class_2561;)V
    //   161: checkcast net/minecraft/class_2596
    //   164: invokevirtual method_14364 : (Lnet/minecraft/class_2596;)V
    //   167: nop
    //   168: nop
    //   169: goto -> 49
    //   172: nop
    //   173: goto -> 178
    //   176: pop
    //   177: nop
    //   178: return
  }
  
  private final void 你为什么要破解我的代码aaaabx(String paramString, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 160
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 160
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 160
    //   21: checkcast java/lang/Iterable
    //   24: astore_3
    //   25: iconst_0
    //   26: istore #4
    //   28: aload_3
    //   29: invokeinterface iterator : ()Ljava/util/Iterator;
    //   34: astore #5
    //   36: aload #5
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 156
    //   46: aload #5
    //   48: invokeinterface next : ()Ljava/lang/Object;
    //   53: astore #6
    //   55: aload #6
    //   57: checkcast net/minecraft/class_3222
    //   60: astore #7
    //   62: iconst_0
    //   63: istore #8
    //   65: aload #7
    //   67: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
    //   70: new net/minecraft/class_1799
    //   73: dup
    //   74: getstatic net/minecraft/class_1802.field_8463 : Lnet/minecraft/class_1792;
    //   77: checkcast net/minecraft/class_1935
    //   80: iload_2
    //   81: invokespecial <init> : (Lnet/minecraft/class_1935;I)V
    //   84: invokevirtual method_7398 : (Lnet/minecraft/class_1799;)V
    //   87: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   90: aload #7
    //   92: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   95: aload #7
    //   97: ldc_w -892927942
    //   100: i2c
    //   101: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   104: checkcast java/lang/String
    //   107: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   110: checkcast net/minecraft/class_2561
    //   113: ldc_w -1076494277
    //   116: i2c
    //   117: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   120: checkcast java/lang/String
    //   123: iconst_1
    //   124: anewarray java/lang/Object
    //   127: astore #9
    //   129: aload #9
    //   131: iconst_0
    //   132: aload_1
    //   133: aastore
    //   134: aload #9
    //   136: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   139: checkcast net/minecraft/class_2561
    //   142: bipush #10
    //   144: bipush #70
    //   146: bipush #20
    //   148: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   151: nop
    //   152: nop
    //   153: goto -> 36
    //   156: nop
    //   157: goto -> 162
    //   160: pop
    //   161: nop
    //   162: return
  }
  
  private final void 你为什么要破解我的代码aaaaby() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 116
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 116
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 116
    //   21: checkcast java/lang/Iterable
    //   24: astore_1
    //   25: iconst_0
    //   26: istore_2
    //   27: aload_1
    //   28: invokeinterface iterator : ()Ljava/util/Iterator;
    //   33: astore_3
    //   34: aload_3
    //   35: invokeinterface hasNext : ()Z
    //   40: ifeq -> 112
    //   43: aload_3
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: astore #4
    //   51: aload #4
    //   53: checkcast net/minecraft/class_3222
    //   56: astore #5
    //   58: iconst_0
    //   59: istore #6
    //   61: aload #5
    //   63: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
    //   66: new net/minecraft/class_1799
    //   69: dup
    //   70: getstatic net/minecraft/class_1802.field_22022 : Lnet/minecraft/class_1792;
    //   73: checkcast net/minecraft/class_1935
    //   76: invokespecial <init> : (Lnet/minecraft/class_1935;)V
    //   79: invokevirtual method_7379 : (Lnet/minecraft/class_1799;)Z
    //   82: ifne -> 107
    //   85: aload #5
    //   87: invokevirtual method_31548 : ()Lnet/minecraft/class_1661;
    //   90: iconst_0
    //   91: new net/minecraft/class_1799
    //   94: dup
    //   95: getstatic net/minecraft/class_1802.field_22022 : Lnet/minecraft/class_1792;
    //   98: checkcast net/minecraft/class_1935
    //   101: invokespecial <init> : (Lnet/minecraft/class_1935;)V
    //   104: invokevirtual method_5447 : (ILnet/minecraft/class_1799;)V
    //   107: nop
    //   108: nop
    //   109: goto -> 34
    //   112: nop
    //   113: goto -> 118
    //   116: pop
    //   117: nop
    //   118: return
  }
  
  private final void 你为什么要破解我的代码aaaabz() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 你为什么要破解我的代码aaaabA : ()V
    //   4: aload_0
    //   5: invokespecial 你为什么要破解我的代码aaaabB : ()V
    //   8: aload_0
    //   9: invokevirtual 你为什么要破解我的代码aaaabC : ()V
    //   12: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   15: dup
    //   16: ifnull -> 226
    //   19: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   22: dup
    //   23: ifnull -> 226
    //   26: invokevirtual method_14571 : ()Ljava/util/List;
    //   29: dup
    //   30: ifnull -> 226
    //   33: checkcast java/lang/Iterable
    //   36: astore_1
    //   37: iconst_0
    //   38: istore_2
    //   39: aload_1
    //   40: invokeinterface iterator : ()Ljava/util/Iterator;
    //   45: astore_3
    //   46: aload_3
    //   47: invokeinterface hasNext : ()Z
    //   52: ifeq -> 222
    //   55: aload_3
    //   56: invokeinterface next : ()Ljava/lang/Object;
    //   61: astore #4
    //   63: aload #4
    //   65: checkcast net/minecraft/class_3222
    //   68: astore #5
    //   70: iconst_0
    //   71: istore #6
    //   73: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaF : Lnet/minecraft/class_3002;
    //   76: dup
    //   77: ifnonnull -> 95
    //   80: pop
    //   81: ldc_w -1759641540
    //   84: i2c
    //   85: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   88: checkcast java/lang/String
    //   91: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   94: aconst_null
    //   95: aload #5
    //   97: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   100: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaG : Lnet/minecraft/class_3002;
    //   103: dup
    //   104: ifnonnull -> 122
    //   107: pop
    //   108: ldc_w 65142845
    //   111: i2c
    //   112: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   115: checkcast java/lang/String
    //   118: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   121: aconst_null
    //   122: aload #5
    //   124: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   127: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaae : Lnet/minecraft/class_3002;
    //   130: dup
    //   131: ifnonnull -> 149
    //   134: pop
    //   135: ldc_w 1382285374
    //   138: i2c
    //   139: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   142: checkcast java/lang/String
    //   145: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   148: aconst_null
    //   149: aload #5
    //   151: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   154: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   157: ifeq -> 190
    //   160: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   163: dup
    //   164: ifnonnull -> 182
    //   167: pop
    //   168: ldc_w 1562181638
    //   171: i2c
    //   172: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   175: checkcast java/lang/String
    //   178: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   181: aconst_null
    //   182: aload #5
    //   184: invokevirtual method_14088 : (Lnet/minecraft/class_3222;)V
    //   187: goto -> 217
    //   190: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   193: dup
    //   194: ifnonnull -> 212
    //   197: pop
    //   198: ldc_w 436076550
    //   201: i2c
    //   202: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   205: checkcast java/lang/String
    //   208: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   211: aconst_null
    //   212: aload #5
    //   214: invokevirtual method_14089 : (Lnet/minecraft/class_3222;)V
    //   217: nop
    //   218: nop
    //   219: goto -> 46
    //   222: nop
    //   223: goto -> 228
    //   226: pop
    //   227: nop
    //   228: return
  }
  
  private final void 你为什么要破解我的代码aaaabA() {
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1817968580)); 
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "§f" + 我草你怎么反编译我模组aaaaaK;
    arrayOfObject[1] = "§f" + 我草你怎么反编译我模组aaaaaL;
    null.method_5413((class_2561)class_2561.method_43469((String)aaaadf((char)2080440383), arrayOfObject));
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)141164604)); 
    null.method_5408(我草你怎么反编译我模组aaaaaK / 我草你怎么反编译我模组aaaaaL);
  }
  
  private final void 你为什么要破解我的代码aaaabB() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
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
    //   56: invokevirtual method_23318 : ()D
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
    //   86: invokevirtual method_23318 : ()D
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
    //   115: goto -> 122
    //   118: pop
    //   119: ldc2_w 4.0
    //   122: dstore #10
    //   124: dload #10
    //   126: ldc2_w 4.0
    //   129: dsub
    //   130: dconst_0
    //   131: invokestatic coerceAtLeast : (DD)D
    //   134: dstore #12
    //   136: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   139: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   142: ifle -> 155
    //   145: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   148: invokevirtual 你为什么要破解我的代码aaaaaa : ()I
    //   151: i2d
    //   152: goto -> 158
    //   155: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaQ : D
    //   158: dstore #14
    //   160: dload #12
    //   162: dload #14
    //   164: ddiv
    //   165: dconst_0
    //   166: dconst_1
    //   167: invokestatic coerceIn : (DDD)D
    //   170: dstore #16
    //   172: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaG : Lnet/minecraft/class_3002;
    //   175: dup
    //   176: ifnonnull -> 194
    //   179: pop
    //   180: ldc_w -1691877315
    //   183: i2c
    //   184: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   187: checkcast java/lang/String
    //   190: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   193: aconst_null
    //   194: dload #16
    //   196: d2f
    //   197: invokevirtual method_5408 : (F)V
    //   200: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaG : Lnet/minecraft/class_3002;
    //   203: dup
    //   204: ifnonnull -> 222
    //   207: pop
    //   208: ldc_w 1428488253
    //   211: i2c
    //   212: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   215: checkcast java/lang/String
    //   218: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   221: aconst_null
    //   222: ldc_w -1020592064
    //   225: i2c
    //   226: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   229: checkcast java/lang/String
    //   232: iconst_3
    //   233: anewarray java/lang/Object
    //   236: astore_2
    //   237: aload_2
    //   238: iconst_0
    //   239: getstatic kotlin/jvm/internal/StringCompanionObject.INSTANCE : Lkotlin/jvm/internal/StringCompanionObject;
    //   242: pop
    //   243: ldc_w -1193934783
    //   246: i2c
    //   247: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   250: checkcast java/lang/String
    //   253: astore #18
    //   255: iconst_1
    //   256: anewarray java/lang/Object
    //   259: astore #19
    //   261: aload #19
    //   263: iconst_0
    //   264: dload #12
    //   266: dload #14
    //   268: invokestatic coerceAtMost : (DD)D
    //   271: invokestatic valueOf : (D)Ljava/lang/Double;
    //   274: aastore
    //   275: aload #19
    //   277: astore #19
    //   279: aload #18
    //   281: aload #19
    //   283: aload #19
    //   285: arraylength
    //   286: invokestatic copyOf : ([Ljava/lang/Object;I)[Ljava/lang/Object;
    //   289: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   292: dup
    //   293: ldc_w -348192702
    //   296: i2c
    //   297: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   300: checkcast java/lang/String
    //   303: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   306: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   311: aastore
    //   312: aload_2
    //   313: iconst_1
    //   314: getstatic kotlin/jvm/internal/StringCompanionObject.INSTANCE : Lkotlin/jvm/internal/StringCompanionObject;
    //   317: pop
    //   318: ldc_w -1597046719
    //   321: i2c
    //   322: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   325: checkcast java/lang/String
    //   328: astore #18
    //   330: iconst_1
    //   331: anewarray java/lang/Object
    //   334: astore #19
    //   336: aload #19
    //   338: iconst_0
    //   339: dload #14
    //   341: invokestatic valueOf : (D)Ljava/lang/Double;
    //   344: aastore
    //   345: aload #19
    //   347: astore #19
    //   349: aload #18
    //   351: aload #19
    //   353: aload #19
    //   355: arraylength
    //   356: invokestatic copyOf : ([Ljava/lang/Object;I)[Ljava/lang/Object;
    //   359: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   362: dup
    //   363: ldc_w 1320484930
    //   366: i2c
    //   367: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   370: checkcast java/lang/String
    //   373: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   376: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   381: aastore
    //   382: aload_2
    //   383: iconst_2
    //   384: getstatic kotlin/jvm/internal/StringCompanionObject.INSTANCE : Lkotlin/jvm/internal/StringCompanionObject;
    //   387: pop
    //   388: ldc_w 638451779
    //   391: i2c
    //   392: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   395: checkcast java/lang/String
    //   398: astore #18
    //   400: iconst_1
    //   401: anewarray java/lang/Object
    //   404: astore #19
    //   406: aload #19
    //   408: iconst_0
    //   409: dload #16
    //   411: bipush #100
    //   413: i2d
    //   414: dmul
    //   415: invokestatic valueOf : (D)Ljava/lang/Double;
    //   418: aastore
    //   419: aload #19
    //   421: astore #19
    //   423: aload #18
    //   425: aload #19
    //   427: aload #19
    //   429: arraylength
    //   430: invokestatic copyOf : ([Ljava/lang/Object;I)[Ljava/lang/Object;
    //   433: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   436: dup
    //   437: ldc_w -794755006
    //   440: i2c
    //   441: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   444: checkcast java/lang/String
    //   447: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   450: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   455: aastore
    //   456: aload_2
    //   457: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   460: checkcast net/minecraft/class_2561
    //   463: invokevirtual method_5413 : (Lnet/minecraft/class_2561;)V
    //   466: return
  }
  
  public final void 你为什么要破解我的代码aaaabC() {
    aaaabn aaaabn = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaf();
    int i = aaaabn.你为什么要破解我的代码aaaaaY();
    String str1 = aaaabn.你为什么要破解我的代码aaaaba().你为什么要破解我的代码aaaaaa();
    if ((String)aaaabn.你为什么要破解我的代码aaaaam().get(str1) == null)
      (String)aaaabn.你为什么要破解我的代码aaaaam().get(str1); 
    String str2 = str1;
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1806761922)); 
    null.method_12956(i);
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)800653374)); 
    null.method_12954(我草你怎么反编译我模组aaaaad);
    float f = 我草你怎么反编译我模组aaaaad / i;
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)1760755774)); 
    null.method_5416((f < 0.33F) ? class_1259.class_1260.field_5784 : ((f < 0.66F) ? class_1259.class_1260.field_5782 : class_1259.class_1260.field_5785));
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1952055234)); 
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = "§6§l" + str2;
    arrayOfObject[1] = Integer.valueOf(我草你怎么反编译我模组aaaaad);
    arrayOfObject[2] = Integer.valueOf(i);
    null.method_5413((class_2561)class_2561.method_43469((String)aaaadf((char)-1160183740), arrayOfObject));
  }
  
  private final void 你为什么要破解我的代码aaaabD() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   3: ifle -> 126
    //   6: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   9: dup
    //   10: ifnull -> 124
    //   13: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   16: dup
    //   17: ifnull -> 124
    //   20: invokevirtual method_14571 : ()Ljava/util/List;
    //   23: dup
    //   24: ifnull -> 124
    //   27: checkcast java/lang/Iterable
    //   30: astore_1
    //   31: iconst_0
    //   32: istore_2
    //   33: aload_1
    //   34: invokeinterface iterator : ()Ljava/util/Iterator;
    //   39: astore_3
    //   40: aload_3
    //   41: invokeinterface hasNext : ()Z
    //   46: ifeq -> 120
    //   49: aload_3
    //   50: invokeinterface next : ()Ljava/lang/Object;
    //   55: astore #4
    //   57: aload #4
    //   59: checkcast net/minecraft/class_3222
    //   62: astore #5
    //   64: iconst_0
    //   65: istore #6
    //   67: aload #5
    //   69: ldc_w -1213398971
    //   72: i2c
    //   73: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   76: checkcast java/lang/String
    //   79: iconst_1
    //   80: anewarray java/lang/Object
    //   83: astore #7
    //   85: aload #7
    //   87: iconst_0
    //   88: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   91: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaJ : I
    //   94: invokespecial 你为什么要破解我的代码aaaabE : (I)Ljava/lang/String;
    //   97: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   102: aastore
    //   103: aload #7
    //   105: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   108: checkcast net/minecraft/class_2561
    //   111: iconst_1
    //   112: invokevirtual method_7353 : (Lnet/minecraft/class_2561;Z)V
    //   115: nop
    //   116: nop
    //   117: goto -> 40
    //   120: nop
    //   121: goto -> 126
    //   124: pop
    //   125: nop
    //   126: return
  }
  
  private final String 你为什么要破解我的代码aaaabE(int paramInt) {
    int i = paramInt / 3600;
    int j = paramInt % 3600 / 60;
    int k = paramInt % 60;
    String str = (String)aaaadf((char)1490223174);
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(i);
    arrayOfObject[1] = Integer.valueOf(j);
    arrayOfObject[2] = Integer.valueOf(k);
    arrayOfObject = arrayOfObject;
    Intrinsics.checkNotNullExpressionValue(String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), (String)aaaadf((char)1630076994));
    str = (String)aaaadf((char)1993736263);
    arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(j);
    arrayOfObject[1] = Integer.valueOf(k);
    arrayOfObject = arrayOfObject;
    Intrinsics.checkNotNullExpressionValue(String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), (String)aaaadf((char)741736514));
    return (i > 0) ? String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)) : String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length));
  }
  
  public final void 你为什么要破解我的代码aaaabF(@NotNull class_3222 paramclass_3222) {
    Intrinsics.checkNotNullParameter(paramclass_3222, (String)aaaadf((char)-11403192));
    class_243 class_2431 = paramclass_3222.method_19538();
    class_243 class_2432 = 你为什么要破解我的代码aaaaak();
    double d1 = RangesKt.random(new IntRange(1, 13), (Random)Random.Default);
    double d2 = (class_2431.field_1350 + 50 > class_2432.field_1350) ? class_2432.field_1350 : (class_2431.field_1350 + 50.5D);
    double d3 = (d2 <= 65.0D) ? RangesKt.coerceAtLeast(65 - d2 + 8, class_2432.field_1351) : class_2432.field_1351;
    double d4 = (class_2431.field_1351 < class_2432.field_1351) ? class_2432.field_1351 : ((class_2431.field_1351 - 50 < class_2432.field_1351) ? class_2432.field_1351 : Math.min(class_2431.field_1351 - 50, d3));
    if ((d4 == class_2432.field_1351)) {
      d2 = class_2432.field_1350;
      d1 = class_2432.field_1352;
    } 
    class_243 class_2433 = new class_243(d1, d4, d2);
    aaaaab.你为什么要破解我的代码aaaaaa(paramclass_3222, class_2433.field_1352, class_2433.field_1351, class_2433.field_1350);
    paramclass_3222.method_6033(paramclass_3222.method_6063());
    paramclass_3222.method_20803(0);
    我草你怎么反编译我模组aaaabj = 5;
    Iterator<class_1293> iterator = paramclass_3222.method_6026().iterator();
    while (iterator.hasNext()) {
      class_1293 class_1293 = iterator.next();
      if (我草你怎么反编译我模组aaaabf.contains(class_1293.method_5579()))
        iterator.remove(); 
    } 
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)-1105985463));
    int i = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaabc();
    if (i > 0) {
      我草你怎么反编译我模组aaaaaJ += i;
      我草你怎么反编译我模组aaaaaI += i;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = "§6" + i;
      aaaaah.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(paramclass_3222, (class_2561)class_2561.method_43471((String)aaaadf((char)-339869622)), (class_2561)class_2561.method_43469((String)aaaadf((char)1129054283), arrayOfObject), 10, 70, 20);
    } 
    你为什么要破解我的代码aaaabz();
  }
  
  private final void 你为什么要破解我的代码aaaabG() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 175
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 175
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 175
    //   21: checkcast java/lang/Iterable
    //   24: astore_1
    //   25: iconst_0
    //   26: istore_2
    //   27: aload_1
    //   28: instanceof java/util/Collection
    //   31: ifeq -> 50
    //   34: aload_1
    //   35: checkcast java/util/Collection
    //   38: invokeinterface isEmpty : ()Z
    //   43: ifeq -> 50
    //   46: iconst_1
    //   47: goto -> 172
    //   50: aload_1
    //   51: invokeinterface iterator : ()Ljava/util/Iterator;
    //   56: astore_3
    //   57: aload_3
    //   58: invokeinterface hasNext : ()Z
    //   63: ifeq -> 171
    //   66: aload_3
    //   67: invokeinterface next : ()Ljava/lang/Object;
    //   72: astore #4
    //   74: aload #4
    //   76: checkcast net/minecraft/class_3222
    //   79: astore #5
    //   81: iconst_0
    //   82: istore #6
    //   84: aload #5
    //   86: invokevirtual method_23318 : ()D
    //   89: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaP : D
    //   92: dcmpl
    //   93: iflt -> 162
    //   96: aload #5
    //   98: invokevirtual method_23321 : ()D
    //   101: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaR : D
    //   104: dcmpg
    //   105: ifgt -> 162
    //   108: aload #5
    //   110: invokevirtual method_23321 : ()D
    //   113: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaR : D
    //   116: bipush #12
    //   118: i2d
    //   119: dsub
    //   120: dcmpl
    //   121: iflt -> 162
    //   124: aload #5
    //   126: invokevirtual method_23318 : ()D
    //   129: ldc2_w 13.0
    //   132: dcmpl
    //   133: ifle -> 162
    //   136: aload #5
    //   138: invokevirtual method_23317 : ()D
    //   141: dconst_1
    //   142: dcmpl
    //   143: iflt -> 162
    //   146: aload #5
    //   148: invokevirtual method_23317 : ()D
    //   151: ldc2_w 13.0
    //   154: dcmpg
    //   155: ifgt -> 162
    //   158: iconst_1
    //   159: goto -> 163
    //   162: iconst_0
    //   163: nop
    //   164: ifne -> 57
    //   167: iconst_0
    //   168: goto -> 172
    //   171: iconst_1
    //   172: goto -> 177
    //   175: pop
    //   176: iconst_0
    //   177: istore #7
    //   179: iload #7
    //   181: ifeq -> 197
    //   184: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   187: ifne -> 197
    //   190: aload_0
    //   191: invokespecial 你为什么要破解我的代码aaaabH : ()V
    //   194: goto -> 212
    //   197: iload #7
    //   199: ifne -> 212
    //   202: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   205: ifeq -> 212
    //   208: aload_0
    //   209: invokespecial 你为什么要破解我的代码aaaabI : ()V
    //   212: return
  }
  
  private final void 你为什么要破解我的代码aaaabH() {
    // Byte code:
    //   0: iconst_1
    //   1: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   4: bipush #10
    //   6: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   12: dup
    //   13: ifnonnull -> 31
    //   16: pop
    //   17: ldc_w -1635319802
    //   20: i2c
    //   21: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   24: checkcast java/lang/String
    //   27: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   30: aconst_null
    //   31: iconst_1
    //   32: invokevirtual method_14091 : (Z)V
    //   35: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   38: dup
    //   39: ifnull -> 156
    //   42: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   45: dup
    //   46: ifnull -> 156
    //   49: invokevirtual method_14571 : ()Ljava/util/List;
    //   52: dup
    //   53: ifnull -> 156
    //   56: checkcast java/lang/Iterable
    //   59: astore_1
    //   60: iconst_0
    //   61: istore_2
    //   62: aload_1
    //   63: invokeinterface iterator : ()Ljava/util/Iterator;
    //   68: astore_3
    //   69: aload_3
    //   70: invokeinterface hasNext : ()Z
    //   75: ifeq -> 152
    //   78: aload_3
    //   79: invokeinterface next : ()Ljava/lang/Object;
    //   84: astore #4
    //   86: aload #4
    //   88: checkcast net/minecraft/class_3222
    //   91: astore #5
    //   93: iconst_0
    //   94: istore #6
    //   96: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   99: aload #5
    //   101: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   104: aload #5
    //   106: ldc_w 775880780
    //   109: i2c
    //   110: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   113: checkcast java/lang/String
    //   116: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   119: checkcast net/minecraft/class_2561
    //   122: ldc_w -1543176115
    //   125: i2c
    //   126: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   129: checkcast java/lang/String
    //   132: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   135: checkcast net/minecraft/class_2561
    //   138: bipush #10
    //   140: bipush #70
    //   142: bipush #20
    //   144: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   147: nop
    //   148: nop
    //   149: goto -> 69
    //   152: nop
    //   153: goto -> 158
    //   156: pop
    //   157: nop
    //   158: return
  }
  
  private final void 你为什么要破解我的代码aaaabI() {
    // Byte code:
    //   0: iconst_0
    //   1: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   4: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   7: dup
    //   8: ifnonnull -> 26
    //   11: pop
    //   12: ldc_w -864026618
    //   15: i2c
    //   16: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   19: checkcast java/lang/String
    //   22: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   25: aconst_null
    //   26: iconst_0
    //   27: invokevirtual method_14091 : (Z)V
    //   30: bipush #10
    //   32: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   35: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   38: dup
    //   39: ifnull -> 156
    //   42: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   45: dup
    //   46: ifnull -> 156
    //   49: invokevirtual method_14571 : ()Ljava/util/List;
    //   52: dup
    //   53: ifnull -> 156
    //   56: checkcast java/lang/Iterable
    //   59: astore_1
    //   60: iconst_0
    //   61: istore_2
    //   62: aload_1
    //   63: invokeinterface iterator : ()Ljava/util/Iterator;
    //   68: astore_3
    //   69: aload_3
    //   70: invokeinterface hasNext : ()Z
    //   75: ifeq -> 152
    //   78: aload_3
    //   79: invokeinterface next : ()Ljava/lang/Object;
    //   84: astore #4
    //   86: aload #4
    //   88: checkcast net/minecraft/class_3222
    //   91: astore #5
    //   93: iconst_0
    //   94: istore #6
    //   96: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   99: aload #5
    //   101: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   104: aload #5
    //   106: ldc_w 2038497358
    //   109: i2c
    //   110: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   113: checkcast java/lang/String
    //   116: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   119: checkcast net/minecraft/class_2561
    //   122: ldc_w 83558479
    //   125: i2c
    //   126: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   129: checkcast java/lang/String
    //   132: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   135: checkcast net/minecraft/class_2561
    //   138: bipush #10
    //   140: bipush #70
    //   142: bipush #20
    //   144: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   147: nop
    //   148: nop
    //   149: goto -> 69
    //   152: nop
    //   153: goto -> 158
    //   156: pop
    //   157: nop
    //   158: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   161: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   164: ldc_w -353501104
    //   167: i2c
    //   168: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   171: checkcast java/lang/String
    //   174: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   177: return
  }
  
  private final void 你为什么要破解我的代码aaaabJ() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   3: istore_1
    //   4: iload_1
    //   5: iconst_m1
    //   6: iadd
    //   7: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   10: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   13: dup
    //   14: ifnonnull -> 32
    //   17: pop
    //   18: ldc_w -1173815290
    //   21: i2c
    //   22: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   25: checkcast java/lang/String
    //   28: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   31: aconst_null
    //   32: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   35: i2f
    //   36: ldc_w 10.0
    //   39: fdiv
    //   40: invokevirtual method_5408 : (F)V
    //   43: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   46: dup
    //   47: ifnonnull -> 65
    //   50: pop
    //   51: ldc_w -97779706
    //   54: i2c
    //   55: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   58: checkcast java/lang/String
    //   61: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   64: aconst_null
    //   65: ldc_w -861208495
    //   68: i2c
    //   69: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   72: checkcast java/lang/String
    //   75: iconst_1
    //   76: anewarray java/lang/Object
    //   79: astore_1
    //   80: aload_1
    //   81: iconst_0
    //   82: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   85: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   88: aastore
    //   89: aload_1
    //   90: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   93: checkcast net/minecraft/class_2561
    //   96: invokevirtual method_5413 : (Lnet/minecraft/class_2561;)V
    //   99: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   102: dup
    //   103: ifnull -> 235
    //   106: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   109: dup
    //   110: ifnull -> 235
    //   113: invokevirtual method_14571 : ()Ljava/util/List;
    //   116: dup
    //   117: ifnull -> 235
    //   120: checkcast java/lang/Iterable
    //   123: astore_2
    //   124: iconst_0
    //   125: istore_3
    //   126: aload_2
    //   127: invokeinterface iterator : ()Ljava/util/Iterator;
    //   132: astore #4
    //   134: aload #4
    //   136: invokeinterface hasNext : ()Z
    //   141: ifeq -> 231
    //   144: aload #4
    //   146: invokeinterface next : ()Ljava/lang/Object;
    //   151: astore #5
    //   153: aload #5
    //   155: checkcast net/minecraft/class_3222
    //   158: astore #6
    //   160: iconst_0
    //   161: istore #7
    //   163: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   166: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   169: ldc_w 209190994
    //   172: i2c
    //   173: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   176: checkcast java/lang/String
    //   179: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   182: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   185: aload #6
    //   187: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   190: aload #6
    //   192: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   195: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   200: invokestatic method_30163 : (Ljava/lang/String;)Lnet/minecraft/class_2561;
    //   203: ldc_w -1266810797
    //   206: i2c
    //   207: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   210: checkcast java/lang/String
    //   213: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   216: checkcast net/minecraft/class_2561
    //   219: iconst_0
    //   220: bipush #20
    //   222: iconst_5
    //   223: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   226: nop
    //   227: nop
    //   228: goto -> 134
    //   231: nop
    //   232: goto -> 237
    //   235: pop
    //   236: nop
    //   237: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaS : I
    //   240: ifgt -> 247
    //   243: aload_0
    //   244: invokespecial 你为什么要破解我的代码aaaabK : ()V
    //   247: return
  }
  
  private final void 你为什么要破解我的代码aaaabK() {
    // Byte code:
    //   0: iconst_0
    //   1: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaT : Z
    //   4: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaH : Lnet/minecraft/class_3002;
    //   7: dup
    //   8: ifnonnull -> 26
    //   11: pop
    //   12: ldc_w -936640506
    //   15: i2c
    //   16: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   19: checkcast java/lang/String
    //   22: invokestatic throwUninitializedPropertyAccessException : (Ljava/lang/String;)V
    //   25: aconst_null
    //   26: iconst_0
    //   27: invokevirtual method_14091 : (Z)V
    //   30: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaK : I
    //   33: istore_1
    //   34: iload_1
    //   35: iconst_1
    //   36: iadd
    //   37: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaK : I
    //   40: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   43: dup
    //   44: ifnull -> 215
    //   47: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   50: dup
    //   51: ifnull -> 215
    //   54: invokevirtual method_14571 : ()Ljava/util/List;
    //   57: dup
    //   58: ifnull -> 215
    //   61: checkcast java/lang/Iterable
    //   64: astore_2
    //   65: iconst_0
    //   66: istore_3
    //   67: aload_2
    //   68: invokeinterface iterator : ()Ljava/util/Iterator;
    //   73: astore #4
    //   75: aload #4
    //   77: invokeinterface hasNext : ()Z
    //   82: ifeq -> 211
    //   85: aload #4
    //   87: invokeinterface next : ()Ljava/lang/Object;
    //   92: astore #5
    //   94: aload #5
    //   96: checkcast net/minecraft/class_3222
    //   99: astore #6
    //   101: iconst_0
    //   102: istore #7
    //   104: aload #6
    //   106: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   109: aload #6
    //   111: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   114: invokevirtual 你为什么要破解我的代码aaaaak : ()Lnet/minecraft/class_243;
    //   117: getfield field_1352 : D
    //   120: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   123: invokevirtual 你为什么要破解我的代码aaaaak : ()Lnet/minecraft/class_243;
    //   126: getfield field_1351 : D
    //   129: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   132: invokevirtual 你为什么要破解我的代码aaaaak : ()Lnet/minecraft/class_243;
    //   135: getfield field_1350 : D
    //   138: invokestatic 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;DDD)V
    //   141: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   144: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   147: ldc_w -1446903724
    //   150: i2c
    //   151: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   154: checkcast java/lang/String
    //   157: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   160: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   163: aload #6
    //   165: ldc_w -1588920235
    //   168: i2c
    //   169: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   172: checkcast java/lang/String
    //   175: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   178: checkcast net/minecraft/class_2561
    //   181: ldc_w -564789162
    //   184: i2c
    //   185: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   188: checkcast java/lang/String
    //   191: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   194: checkcast net/minecraft/class_2561
    //   197: bipush #10
    //   199: bipush #70
    //   201: bipush #20
    //   203: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   206: nop
    //   207: nop
    //   208: goto -> 75
    //   211: nop
    //   212: goto -> 217
    //   215: pop
    //   216: nop
    //   217: aload_0
    //   218: invokespecial 你为什么要破解我的代码aaaabz : ()V
    //   221: return
  }
  
  private final void 你为什么要破解我的代码aaaabL() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaU : Ljava/util/concurrent/atomic/AtomicInteger;
    //   3: invokevirtual get : ()I
    //   6: ifle -> 210
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   12: dup
    //   13: ifnull -> 201
    //   16: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   19: dup
    //   20: ifnull -> 201
    //   23: invokevirtual method_14571 : ()Ljava/util/List;
    //   26: dup
    //   27: ifnull -> 201
    //   30: checkcast java/lang/Iterable
    //   33: astore_1
    //   34: iconst_0
    //   35: istore_2
    //   36: aload_1
    //   37: invokeinterface iterator : ()Ljava/util/Iterator;
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface hasNext : ()Z
    //   49: ifeq -> 197
    //   52: aload_3
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: astore #4
    //   60: aload #4
    //   62: checkcast net/minecraft/class_3222
    //   65: astore #5
    //   67: iconst_0
    //   68: istore #6
    //   70: aload #5
    //   72: invokevirtual method_37908 : ()Lnet/minecraft/class_1937;
    //   75: astore #7
    //   77: aload #5
    //   79: invokevirtual method_19538 : ()Lnet/minecraft/class_243;
    //   82: astore #8
    //   84: getstatic net/minecraft/class_1299.field_6112 : Lnet/minecraft/class_1299;
    //   87: aload #7
    //   89: invokevirtual method_5883 : (Lnet/minecraft/class_1937;)Lnet/minecraft/class_1297;
    //   92: dup
    //   93: ldc_w 245432407
    //   96: i2c
    //   97: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   100: checkcast java/lang/String
    //   103: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   106: checkcast net/minecraft/class_1538
    //   109: astore #9
    //   111: aload #9
    //   113: aload #8
    //   115: getfield field_1352 : D
    //   118: aload #8
    //   120: getfield field_1351 : D
    //   123: aload #8
    //   125: getfield field_1350 : D
    //   128: fconst_0
    //   129: fconst_0
    //   130: invokevirtual method_5808 : (DDDFF)V
    //   133: aload #9
    //   135: iconst_1
    //   136: invokevirtual method_29498 : (Z)V
    //   139: aload #7
    //   141: aload #9
    //   143: checkcast net/minecraft/class_1297
    //   146: invokevirtual method_8649 : (Lnet/minecraft/class_1297;)Z
    //   149: pop
    //   150: aload #5
    //   152: aload #5
    //   154: invokevirtual method_48923 : ()Lnet/minecraft/class_8109;
    //   157: invokevirtual method_48809 : ()Lnet/minecraft/class_1282;
    //   160: ldc_w 5.0
    //   163: invokevirtual method_5643 : (Lnet/minecraft/class_1282;F)Z
    //   166: pop
    //   167: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   170: aload #5
    //   172: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   175: aload #5
    //   177: ldc2_w 0.9
    //   180: ldc2_w 0.8
    //   183: invokespecial 你为什么要破解我的代码aaaaci : (Lnet/minecraft/class_3222;DD)V
    //   186: aload #5
    //   188: iconst_1
    //   189: putfield field_6037 : Z
    //   192: nop
    //   193: nop
    //   194: goto -> 43
    //   197: nop
    //   198: goto -> 203
    //   201: pop
    //   202: nop
    //   203: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaU : Ljava/util/concurrent/atomic/AtomicInteger;
    //   206: invokevirtual decrementAndGet : ()I
    //   209: pop
    //   210: return
  }
  
  private final void 你为什么要破解我的代码aaaabM(MinecraftServer paramMinecraftServer) {
    Intrinsics.checkNotNullExpressionValue(paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)228130904), (String)aaaadf((char)-1117257639)), (class_2561)class_2561.method_43471((String)aaaadf((char)-855506881))), (String)aaaadf((char)1984495706));
    我草你怎么反编译我模组aaaaaF = paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)228130904), (String)aaaadf((char)-1117257639)), (class_2561)class_2561.method_43471((String)aaaadf((char)-855506881)));
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1839267780)); 
    null.method_5416(class_1259.class_1260.field_5782);
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)2066612284)); 
    null.method_5409(class_1259.class_1261.field_5790);
    Intrinsics.checkNotNullExpressionValue(paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)682033240), (String)aaaadf((char)-349175717)), (class_2561)class_2561.method_43471((String)aaaadf((char)355139648))), (String)aaaadf((char)362283098));
    我草你怎么反编译我模组aaaaaG = paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)682033240), (String)aaaadf((char)-349175717)), (class_2561)class_2561.method_43471((String)aaaadf((char)355139648)));
    if (我草你怎么反编译我模组aaaaaG == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)1243217981)); 
    null.method_5416(class_1259.class_1260.field_5785);
    if (我草你怎么反编译我模组aaaaaG == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1621950403)); 
    null.method_5409(class_1259.class_1261.field_5790);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(10);
    Intrinsics.checkNotNullExpressionValue(paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)1055064152), (String)aaaadf((char)-622526372)), (class_2561)class_2561.method_43469((String)aaaadf((char)-261554095), arrayOfObject)), (String)aaaadf((char)-1878327206));
    我草你怎么反编译我模组aaaaaH = paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)1055064152), (String)aaaadf((char)-622526372)), (class_2561)class_2561.method_43469((String)aaaadf((char)-261554095), arrayOfObject));
    if (我草你怎么反编译我模组aaaaaH == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1957167098)); 
    null.method_5416(class_1259.class_1260.field_5785);
    if (我草你怎么反编译我模组aaaaaH == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-674103290)); 
    null.method_5409(class_1259.class_1261.field_5791);
    if (我草你怎么反编译我模组aaaaaH == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-650313722)); 
    null.method_14091(false);
    class_3002 class_30021 = paramMinecraftServer.method_3837().method_12970(class_2960.method_60655((String)aaaadf((char)-24706984), (String)aaaadf((char)-130613155)), (class_2561)class_2561.method_43471((String)aaaadf((char)417464388)));
    class_3002 class_30022 = class_30021;
    boolean bool = false;
    class_30022.method_12956(aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaaY());
    class_30022.method_12954(我草你怎么反编译我模组aaaaad);
    Intrinsics.checkNotNullExpressionValue(class_30021, (String)aaaadf((char)-488767394));
    我草你怎么反编译我模组aaaaae = class_30021;
  }
  
  private final void 你为什么要破解我的代码aaaabN(MinecraftServer paramMinecraftServer) {
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1555824580)); 
    null.method_14091(false);
    if (我草你怎么反编译我模组aaaaaG == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-800587715)); 
    null.method_14091(false);
    if (我草你怎么反编译我模组aaaaaH == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1926496250)); 
    null.method_14091(false);
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1455488962)); 
    null.method_14091(false);
    if (我草你怎么反编译我模组aaaaae == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1751056322)); 
    我草你怎么反编译我模组aaaaae.method_12973(null);
    if (我草你怎么反编译我模组aaaaaF == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)-1509752772)); 
    我草你怎么反编译我模组aaaaaF.method_12973(null);
    if (我草你怎么反编译我模组aaaaaG == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)403767357)); 
    我草你怎么反编译我模组aaaaaG.method_12973(null);
    if (我草你怎么反编译我模组aaaaaH == null)
      Intrinsics.throwUninitializedPropertyAccessException((String)aaaadf((char)604372998)); 
    我草你怎么反编译我模组aaaaaH.method_12973(null);
  }
  
  public final int 你为什么要破解我的代码aaaabO() {
    return 我草你怎么反编译我模组aaaaaI / 60;
  }
  
  public final int 你为什么要破解我的代码aaaabP() {
    return 我草你怎么反编译我模组aaaaaJ / 60;
  }
  
  public final void 你为什么要破解我的代码aaaabQ(int paramInt) {
    我草你怎么反编译我模组aaaaaJ = RangesKt.coerceIn(paramInt * 60, 0, 我草你怎么反编译我模组aaaaaI);
    你为什么要破解我的代码aaaabz();
  }
  
  public final void 你为什么要破解我的代码aaaabR(int paramInt) {
    我草你怎么反编译我模组aaaaaI = paramInt * 60;
    我草你怎么反编译我模组aaaaaJ = 我草你怎么反编译我模组aaaaaI;
    你为什么要破解我的代码aaaabz();
  }
  
  public final void 你为什么要破解我的代码aaaabS(int paramInt) {
    我草你怎么反编译我模组aaaaaJ = RangesKt.coerceIn(paramInt, 0, 我草你怎么反编译我模组aaaaaI);
    你为什么要破解我的代码aaaabz();
  }
  
  public final int 你为什么要破解我的代码aaaabT() {
    return 我草你怎么反编译我模组aaaaaJ;
  }
  
  public final void 你为什么要破解我的代码aaaabU(int paramInt) {
    我草你怎么反编译我模组aaaaaK = RangesKt.coerceIn(paramInt, 0, 我草你怎么反编译我模组aaaaaL);
    你为什么要破解我的代码aaaabz();
  }
  
  public final void 你为什么要破解我的代码aaaabV(int paramInt) {
    我草你怎么反编译我模组aaaaaL = RangesKt.coerceAtLeast(paramInt, 1);
    我草你怎么反编译我模组aaaaaK = RangesKt.coerceIn(我草你怎么反编译我模组aaaaaK, 0, 我草你怎么反编译我模组aaaaaL);
    你为什么要破解我的代码aaaabz();
  }
  
  public final void 你为什么要破解我的代码aaaabW(int paramInt) {
    你为什么要破解我的代码aaaabU(我草你怎么反编译我模组aaaaaK + paramInt);
  }
  
  public final void 你为什么要破解我的代码aaaabY(int paramInt) {
    你为什么要破解我的代码aaaabU(我草你怎么反编译我模组aaaaaK - paramInt);
  }
  
  public final int 你为什么要破解我的代码aaaaca() {
    return 我草你怎么反编译我模组aaaaaL;
  }
  
  @NotNull
  public final class_2338 你为什么要破解我的代码aaaacb(int paramInt1, int paramInt2) {
    int i = RangesKt.coerceIn(paramInt1, 0, 12) + 1;
    int j = paramInt2 + 4;
    int k = 71 - j;
    return new class_2338(i, j, k);
  }
  
  private final int 你为什么要破解我的代码aaaacc() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 13
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: goto -> 15
    //   13: pop
    //   14: aconst_null
    //   15: astore_1
    //   16: aload_1
    //   17: ifnonnull -> 47
    //   20: aload_0
    //   21: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae
    //   24: astore_2
    //   25: iconst_0
    //   26: istore_3
    //   27: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   30: ldc_w 6488159
    //   33: i2c
    //   34: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   37: checkcast java/lang/String
    //   40: invokeinterface error : (Ljava/lang/String;)V
    //   45: iconst_0
    //   46: ireturn
    //   47: aload_1
    //   48: astore #4
    //   50: iconst_0
    //   51: istore_1
    //   52: nop
    //   53: aload #4
    //   55: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   58: dup
    //   59: ldc_w -2055798688
    //   62: i2c
    //   63: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   66: checkcast java/lang/String
    //   69: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   72: astore_2
    //   73: nop
    //   74: iconst_0
    //   75: istore_3
    //   76: aload_2
    //   77: astore #5
    //   79: new java/util/ArrayList
    //   82: dup
    //   83: invokespecial <init> : ()V
    //   86: checkcast java/util/Collection
    //   89: astore #6
    //   91: iconst_0
    //   92: istore #7
    //   94: aload #5
    //   96: invokeinterface iterator : ()Ljava/util/Iterator;
    //   101: astore #8
    //   103: aload #8
    //   105: invokeinterface hasNext : ()Z
    //   110: ifeq -> 153
    //   113: aload #8
    //   115: invokeinterface next : ()Ljava/lang/Object;
    //   120: astore #9
    //   122: aload #9
    //   124: checkcast net/minecraft/class_1297
    //   127: astore #10
    //   129: iconst_0
    //   130: istore #11
    //   132: aload #10
    //   134: instanceof net/minecraft/class_1542
    //   137: ifeq -> 103
    //   140: aload #6
    //   142: aload #9
    //   144: invokeinterface add : (Ljava/lang/Object;)Z
    //   149: pop
    //   150: goto -> 103
    //   153: aload #6
    //   155: checkcast java/util/List
    //   158: nop
    //   159: astore #12
    //   161: aload #12
    //   163: checkcast java/lang/Iterable
    //   166: astore_2
    //   167: iconst_0
    //   168: istore_3
    //   169: aload_2
    //   170: invokeinterface iterator : ()Ljava/util/Iterator;
    //   175: astore #5
    //   177: aload #5
    //   179: invokeinterface hasNext : ()Z
    //   184: ifeq -> 267
    //   187: aload #5
    //   189: invokeinterface next : ()Ljava/lang/Object;
    //   194: astore #6
    //   196: aload #6
    //   198: checkcast net/minecraft/class_1297
    //   201: astore #7
    //   203: iconst_0
    //   204: istore #8
    //   206: nop
    //   207: aload #7
    //   209: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   212: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   215: iload_1
    //   216: iconst_1
    //   217: iadd
    //   218: istore_1
    //   219: goto -> 262
    //   222: astore #9
    //   224: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   227: aload #7
    //   229: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   232: aload #7
    //   234: invokevirtual method_23317 : ()D
    //   237: aload #7
    //   239: invokevirtual method_23318 : ()D
    //   242: aload #7
    //   244: invokevirtual method_23321 : ()D
    //   247: <illegal opcode> makeConcatWithConstants : (Lnet/minecraft/class_1299;DDD)Ljava/lang/String;
    //   252: aload #9
    //   254: checkcast java/lang/Throwable
    //   257: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   262: nop
    //   263: nop
    //   264: goto -> 177
    //   267: nop
    //   268: goto -> 296
    //   271: astore #12
    //   273: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   276: ldc_w -1782513567
    //   279: i2c
    //   280: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   283: checkcast java/lang/String
    //   286: aload #12
    //   288: checkcast java/lang/Throwable
    //   291: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   296: iload_1
    //   297: ireturn
    // Exception table:
    //   from	to	target	type
    //   52	268	271	java/lang/Exception
    //   206	219	222	java/lang/Exception
  }
  
  @Subscribe
  public final void 你为什么要破解我的代码aaaaav(@NotNull LiveLikeEvent paramLiveLikeEvent) {
    Intrinsics.checkNotNullParameter(paramLiveLikeEvent, (String)aaaadf((char)-1333723129));
    我草你怎么反编译我模组aaaaad += paramLiveLikeEvent.getLikeCount();
    aaaabn aaaabn = aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaf();
    String str = aaaaab.Companion.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaao() ? paramLiveLikeEvent.getUsername() : aaaaai.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(paramLiveLikeEvent.getUsername());
    if (我草你怎么反编译我模组aaaaad >= aaaabn.你为什么要破解我的代码aaaaaY()) {
      我草你怎么反编译我模组aaaaad = 0;
      aaaaaQ aaaaaQ = aaaabn.你为什么要破解我的代码aaaaba();
      你为什么要破解我的代码aaaabs(aaaaaQ.你为什么要破解我的代码aaaaaa(), aaaaaQ.你为什么要破解我的代码aaaaab(), (String)aaaadf((char)275906658), (String)aaaadf((char)1587740722));
      我草你怎么反编译我模组aaaaaK += aaaaaQ.你为什么要破解我的代码aaaaac();
    } 
  }
  
  private final void 你为什么要破解我的代码aaaacd(String paramString, int paramInt, class_1299<? extends class_1308> paramclass_1299) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    ArrayList<Timer> arrayList = new ArrayList();
    int i;
    for (i = paramInt; i > 0; i -= j) {
      int j = Math.min(50, i);
      long l = RangesKt.random(new IntRange(1, 8), (Random)Random.Default) * 50L;
      你为什么要破解我的代码aaaacW(arrayList, paramclass_1299, (class_3218)object, paramString, j, l);
    } 
    (new Timer()).schedule(new aaaabQ(arrayList), (paramInt * 8 * 50 + 60000));
  }
  
  private final void 你为什么要破解我的代码aaaace() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 13
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: goto -> 15
    //   13: pop
    //   14: aconst_null
    //   15: dup
    //   16: ifnonnull -> 21
    //   19: pop
    //   20: return
    //   21: astore_1
    //   22: aload_1
    //   23: getstatic net/minecraft/class_1299.field_6096 : Lnet/minecraft/class_1299;
    //   26: checkcast net/minecraft/class_5575
    //   29: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaM.我草你怎么反编译我模组aaaaaY : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaM;
    //   32: checkcast kotlin/jvm/functions/Function1
    //   35: <illegal opcode> test : (Lkotlin/jvm/functions/Function1;)Ljava/util/function/Predicate;
    //   40: invokevirtual method_18198 : (Lnet/minecraft/class_5575;Ljava/util/function/Predicate;)Ljava/util/List;
    //   43: astore_2
    //   44: aload_2
    //   45: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   48: aload_2
    //   49: checkcast java/lang/Iterable
    //   52: astore_3
    //   53: iconst_0
    //   54: istore #4
    //   56: aload_3
    //   57: invokeinterface iterator : ()Ljava/util/Iterator;
    //   62: astore #5
    //   64: aload #5
    //   66: invokeinterface hasNext : ()Z
    //   71: ifeq -> 114
    //   74: aload #5
    //   76: invokeinterface next : ()Ljava/lang/Object;
    //   81: astore #6
    //   83: aload #6
    //   85: checkcast net/minecraft/class_1695
    //   88: astore #7
    //   90: iconst_0
    //   91: istore #8
    //   93: aload #7
    //   95: invokevirtual method_5782 : ()Z
    //   98: ifne -> 109
    //   101: aload #7
    //   103: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   106: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   109: nop
    //   110: nop
    //   111: goto -> 64
    //   114: nop
    //   115: return
  }
  
  private final void 你为什么要破解我的代码aaaacf() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 395
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: dup
    //   11: ifnull -> 395
    //   14: astore_1
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_1
    //   18: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   21: dup
    //   22: ldc_w -1738014624
    //   25: i2c
    //   26: invokestatic aaaadf : (C)Ljava/lang/Object;
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
    //   104: instanceof net/minecraft/class_1657
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
    //   146: astore #5
    //   148: new java/util/ArrayList
    //   151: dup
    //   152: invokespecial <init> : ()V
    //   155: checkcast java/util/Collection
    //   158: astore #6
    //   160: iconst_0
    //   161: istore #7
    //   163: aload #5
    //   165: invokeinterface iterator : ()Ljava/util/Iterator;
    //   170: astore #8
    //   172: aload #8
    //   174: invokeinterface hasNext : ()Z
    //   179: ifeq -> 230
    //   182: aload #8
    //   184: invokeinterface next : ()Ljava/lang/Object;
    //   189: astore #9
    //   191: aload #9
    //   193: checkcast net/minecraft/class_1297
    //   196: astore #10
    //   198: iconst_0
    //   199: istore #11
    //   201: aload #10
    //   203: instanceof net/minecraft/class_1428
    //   206: ifne -> 213
    //   209: iconst_1
    //   210: goto -> 214
    //   213: iconst_0
    //   214: ifeq -> 172
    //   217: aload #6
    //   219: aload #9
    //   221: invokeinterface add : (Ljava/lang/Object;)Z
    //   226: pop
    //   227: goto -> 172
    //   230: aload #6
    //   232: checkcast java/util/List
    //   235: nop
    //   236: checkcast java/lang/Iterable
    //   239: astore_3
    //   240: nop
    //   241: iconst_0
    //   242: istore #4
    //   244: aload_3
    //   245: invokeinterface iterator : ()Ljava/util/Iterator;
    //   250: astore #5
    //   252: aload #5
    //   254: invokeinterface hasNext : ()Z
    //   259: ifeq -> 390
    //   262: aload #5
    //   264: invokeinterface next : ()Ljava/lang/Object;
    //   269: astore #6
    //   271: aload #6
    //   273: checkcast net/minecraft/class_1297
    //   276: astore #7
    //   278: iconst_0
    //   279: istore #8
    //   281: nop
    //   282: aload #7
    //   284: invokevirtual method_5765 : ()Z
    //   287: ifne -> 385
    //   290: aload #7
    //   292: instanceof net/minecraft/class_1309
    //   295: ifeq -> 385
    //   298: aload #7
    //   300: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   303: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   306: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   309: aload #7
    //   311: checkcast net/minecraft/class_1309
    //   314: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   317: invokevirtual method_5882 : ()Ljava/lang/String;
    //   320: aload #7
    //   322: checkcast net/minecraft/class_1309
    //   325: invokevirtual method_23317 : ()D
    //   328: aload #7
    //   330: checkcast net/minecraft/class_1309
    //   333: invokevirtual method_23318 : ()D
    //   336: aload #7
    //   338: checkcast net/minecraft/class_1309
    //   341: invokevirtual method_23321 : ()D
    //   344: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;DDD)Ljava/lang/String;
    //   349: invokeinterface info : (Ljava/lang/String;)V
    //   354: goto -> 385
    //   357: astore #9
    //   359: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   362: aload #7
    //   364: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   367: invokevirtual method_5882 : ()Ljava/lang/String;
    //   370: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   375: aload #9
    //   377: checkcast java/lang/Throwable
    //   380: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   385: nop
    //   386: nop
    //   387: goto -> 252
    //   390: nop
    //   391: nop
    //   392: goto -> 397
    //   395: pop
    //   396: nop
    //   397: return
    // Exception table:
    //   from	to	target	type
    //   281	354	357	java/lang/Exception
  }
  
  private final void 你为什么要破解我的代码aaaacg() {
    Object object1;
    Object object2;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    我草你怎么反编译我模组aaaaab.method_3760();
    if (((我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null) ? 我草你怎么反编译我模组aaaaab.method_3760().method_14571() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null) ? 我草你怎么反编译我模组aaaaab.method_3760().method_14571() : null;
      return;
    } 
    if (!((Collection)object2).isEmpty()) {
      class_3222 class_3222 = (class_3222)CollectionsKt.random((Collection)object2, (Random)Random.Default);
      double d1 = Math.random() * 4 - 2;
      double d2 = Math.random() * 4 - 2;
      class_243 class_243 = class_3222.method_19538().method_1031(d1, 6.0D, d2);
      class_1541 class_1541 = new class_1541((class_1937)object1, class_243.field_1352, class_243.field_1351, class_243.field_1350, null);
      class_1541.method_6967(40);
      object1.method_8649((class_1297)class_1541);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaach() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaV : Ljava/util/concurrent/atomic/AtomicInteger;
    //   3: invokevirtual get : ()I
    //   6: ifle -> 210
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   12: dup
    //   13: ifnull -> 201
    //   16: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   19: dup
    //   20: ifnull -> 201
    //   23: invokevirtual method_14571 : ()Ljava/util/List;
    //   26: dup
    //   27: ifnull -> 201
    //   30: checkcast java/lang/Iterable
    //   33: astore_1
    //   34: iconst_0
    //   35: istore_2
    //   36: aload_1
    //   37: invokeinterface iterator : ()Ljava/util/Iterator;
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface hasNext : ()Z
    //   49: ifeq -> 197
    //   52: aload_3
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: astore #4
    //   60: aload #4
    //   62: checkcast net/minecraft/class_3222
    //   65: astore #5
    //   67: iconst_0
    //   68: istore #6
    //   70: aload #5
    //   72: invokevirtual method_37908 : ()Lnet/minecraft/class_1937;
    //   75: astore #7
    //   77: aload #5
    //   79: invokevirtual method_19538 : ()Lnet/minecraft/class_243;
    //   82: astore #8
    //   84: getstatic net/minecraft/class_1299.field_6112 : Lnet/minecraft/class_1299;
    //   87: aload #7
    //   89: invokevirtual method_5883 : (Lnet/minecraft/class_1937;)Lnet/minecraft/class_1297;
    //   92: dup
    //   93: ldc_w 720371799
    //   96: i2c
    //   97: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   100: checkcast java/lang/String
    //   103: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
    //   106: checkcast net/minecraft/class_1538
    //   109: astore #9
    //   111: aload #9
    //   113: aload #8
    //   115: getfield field_1352 : D
    //   118: aload #8
    //   120: getfield field_1351 : D
    //   123: aload #8
    //   125: getfield field_1350 : D
    //   128: fconst_0
    //   129: fconst_0
    //   130: invokevirtual method_5808 : (DDDFF)V
    //   133: aload #9
    //   135: iconst_1
    //   136: invokevirtual method_29498 : (Z)V
    //   139: aload #7
    //   141: aload #9
    //   143: checkcast net/minecraft/class_1297
    //   146: invokevirtual method_8649 : (Lnet/minecraft/class_1297;)Z
    //   149: pop
    //   150: aload #5
    //   152: aload #5
    //   154: invokevirtual method_48923 : ()Lnet/minecraft/class_8109;
    //   157: invokevirtual method_48809 : ()Lnet/minecraft/class_1282;
    //   160: ldc_w 10.0
    //   163: invokevirtual method_5643 : (Lnet/minecraft/class_1282;F)Z
    //   166: pop
    //   167: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   170: aload #5
    //   172: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   175: aload #5
    //   177: ldc2_w 0.9
    //   180: ldc2_w 1.6
    //   183: invokespecial 你为什么要破解我的代码aaaaci : (Lnet/minecraft/class_3222;DD)V
    //   186: aload #5
    //   188: iconst_1
    //   189: putfield field_6037 : Z
    //   192: nop
    //   193: nop
    //   194: goto -> 43
    //   197: nop
    //   198: goto -> 203
    //   201: pop
    //   202: nop
    //   203: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaV : Ljava/util/concurrent/atomic/AtomicInteger;
    //   206: invokevirtual decrementAndGet : ()I
    //   209: pop
    //   210: return
  }
  
  private final void 你为什么要破解我的代码aaaaci(class_3222 paramclass_3222, double paramDouble1, double paramDouble2) {
    class_243 class_2431 = class_243.method_1030(0.0F, paramclass_3222.method_36454());
    class_243 class_2432 = class_2431.method_1021(-paramDouble1).method_1031(0.0D, paramDouble2, 0.0D);
    paramclass_3222.method_5762(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
    paramclass_3222.field_6037 = true;
  }
  
  private final void 你为什么要破解我的代码aaaacj() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 337
    //   7: invokevirtual method_30002 : ()Lnet/minecraft/class_3218;
    //   10: dup
    //   11: ifnull -> 337
    //   14: astore_1
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_1
    //   18: invokevirtual method_27909 : ()Ljava/lang/Iterable;
    //   21: dup
    //   22: ldc_w 779026528
    //   25: i2c
    //   26: invokestatic aaaadf : (C)Ljava/lang/Object;
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
    //   104: instanceof net/minecraft/class_1657
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
    //   160: ifeq -> 332
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
    //   185: invokevirtual method_23318 : ()D
    //   188: ldc2_w 4.0
    //   191: dcmpg
    //   192: ifge -> 240
    //   195: aload #7
    //   197: aload #7
    //   199: invokevirtual method_19538 : ()Lnet/minecraft/class_243;
    //   202: getfield field_1352 : D
    //   205: ldc2_w 4.0
    //   208: aload #7
    //   210: invokevirtual method_19538 : ()Lnet/minecraft/class_243;
    //   213: getfield field_1350 : D
    //   216: invokevirtual method_5814 : (DDD)V
    //   219: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   222: aload #7
    //   224: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   227: invokevirtual method_5882 : ()Ljava/lang/String;
    //   230: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   235: invokeinterface debug : (Ljava/lang/String;)V
    //   240: aload #7
    //   242: invokevirtual method_23321 : ()D
    //   245: ldc2_w 75.0
    //   248: dcmpl
    //   249: ifle -> 327
    //   252: aload #7
    //   254: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   257: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   260: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   263: aload #7
    //   265: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   268: invokevirtual method_5882 : ()Ljava/lang/String;
    //   271: aload #7
    //   273: invokevirtual method_23317 : ()D
    //   276: aload #7
    //   278: invokevirtual method_23318 : ()D
    //   281: aload #7
    //   283: invokevirtual method_23321 : ()D
    //   286: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;DDD)Ljava/lang/String;
    //   291: invokeinterface info : (Ljava/lang/String;)V
    //   296: goto -> 327
    //   299: astore #9
    //   301: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   304: aload #7
    //   306: invokevirtual method_5864 : ()Lnet/minecraft/class_1299;
    //   309: invokevirtual method_5882 : ()Ljava/lang/String;
    //   312: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   317: aload #9
    //   319: checkcast java/lang/Throwable
    //   322: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   327: nop
    //   328: nop
    //   329: goto -> 153
    //   332: nop
    //   333: nop
    //   334: goto -> 339
    //   337: pop
    //   338: nop
    //   339: return
    // Exception table:
    //   from	to	target	type
    //   182	296	299	java/lang/Exception
  }
  
  private final void 你为什么要破解我的代码aaaack() {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    List list1 = object.method_18198((class_5575)class_1299.field_6096, aaaabd.我草你怎么反编译我模组aaaabI::你为什么要破解我的代码aaaacY);
    List list2 = object.method_18456();
    for (class_1695 class_1695 : list1) {
      for (class_3222 class_3222 : list2) {
        long l = ((Long)我草你怎么反编译我模组aaaabi.get(class_3222.method_5667())).longValue();
        boolean bool1 = false;
        (Long)我草你怎么反编译我模组aaaabi.get(class_3222.method_5667());
        boolean bool2 = ((Long)我草你怎么反编译我模组aaaabi.get(class_3222.method_5667()) != null) ? ((System.currentTimeMillis() < l) ? true : false) : false;
        double d = class_1695.method_19538().method_1022(class_3222.method_19538());
        if (!class_3222.method_21751() && !bool2 && d < 1.0D) {
          Intrinsics.checkNotNull(class_3222);
          你为什么要破解我的代码aaaaci(class_3222, 0.29700000000000004D, 0.264D);
        } 
      } 
    } 
  }
  
  private final void 你为什么要破解我的代码aaaacl(String paramString, int paramInt) {
    Object object1;
    Object object2;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    我草你怎么反编译我模组aaaaab.method_3760();
    if (((我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null) ? 我草你怎么反编译我模组aaaaab.method_3760().method_14571() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null && 我草你怎么反编译我模组aaaaab.method_3760() != null) ? 我草你怎么反编译我模组aaaaab.method_3760().method_14571() : null;
      return;
    } 
    BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, null, null, new aaaaby((List<class_3222>)object2, paramInt, (class_3218)object1, null), 3, null);
  }
  
  private final boolean 你为什么要破解我的代码aaaacm() {
    if (!aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaae() || aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag() == 0)
      return false; 
    int i = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
    int j = MathKt.getSign(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag());
    int k = RangesKt.coerceIn(i + j, 10, 1500);
    if (k != i) {
      int m = 3 + i;
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(k);
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa;
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.你为什么要破解我的代码aaaaah(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.你为什么要破解我的代码aaaaag() - j);
      你为什么要破解我的代码aaaacn(m);
      if (j < 0) {
        你为什么要破解我的代码aaaacq(m);
        你为什么要破解我的代码aaaacx(1, 3 + k, 66 - k - 1);
        我草你怎么反编译我模组aaaaaR = 66 - k;
        你为什么要破解我的代码aaaacG();
      } else if (j > 0) {
        你为什么要破解我的代码aaaacU(k);
        你为什么要破解我的代码aaaaco(k);
        你为什么要破解我的代码aaaacx(1, 3 + k, 66 - k - 1);
        我草你怎么反编译我模组aaaaaR = 66 - k;
      } 
      if (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag() == 0) {
        aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(false);
        我草你怎么反编译我模组aaaaaP = (3 + k);
        我草你怎么反编译我模组aaaaaQ = k;
        我草你怎么反编译我模组aaaaaR = 66 - k;
      } 
      return true;
    } 
    if (我草你怎么反编译我模组aaaaab != null) {
      MinecraftServer minecraftServer = 我草你怎么反编译我模组aaaaab;
      boolean bool = false;
      我草你怎么反编译我模组aaaaaE.你为什么要破解我的代码aaaaaH(minecraftServer);
    } else {
    
    } 
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(0);
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(false);
    return false;
  }
  
  private final void 你为什么要破解我的代码aaaacn(int paramInt) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i = paramInt;
    int j = 66 - i - 3;
    int k = j - 12 + 1;
    aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n        清除平台:\n        - 高度: " + i + "\n        - Z范围: " + k + " 到 " + j - 1 + "\n    "));
    for (byte b = 1; b < 14; b++) {
      int m = k - 1;
      int n = j - 1;
      if (m <= n)
        while (true) {
          class_2338 class_2338 = new class_2338(b, i, m);
          class_2248 class_2248 = object.method_8320(class_2338).method_26204();
          if (!Intrinsics.areEqual(class_2248, class_2246.field_10124))
            object.method_8501(class_2338, class_2246.field_10124.method_9564()); 
          if (m != n) {
            m++;
            continue;
          } 
          break;
        }  
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaco(int paramInt) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i = 3 + paramInt;
    int j = 66 - paramInt;
    try {
      if (paramInt >= 1501) {
        你为什么要破解我的代码aaaacF((String)aaaadf((char)1845690467));
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaadf((char)678232164));
        return;
      } 
      class_2248 class_2248 = 你为什么要破解我的代码aaaacp(paramInt);
      for (byte b = 1; b < 14; b++) {
        class_2338 class_2338 = new class_2338(b, i, j);
        int k = b >> 4;
        int m = j >> 4;
        if (object.method_8393(k, m)) {
          switch (b - 1) {
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
            
            default:
              break;
          } 
          class_2248 class_22481 = class_2246.field_10542;
          object.method_8652(class_2338, class_22481.method_9564(), 2);
          class_2680 class_2680 = (class_2680)class_2246.field_10425.method_9564().method_11657((class_2769)class_2442.field_11365, (Comparable)class_2768.field_12665);
          object.method_8652(class_2338.method_10084(), class_2680, 2);
          if (paramInt % 10 == 0) {
            class_2338 class_23381 = class_2338.method_10074();
            object.method_8652(class_23381, (class_2680)class_2246.field_10301.method_9564().method_11657((class_2769)class_2555.field_11731, (Comparable)class_2350.field_11043), 2);
          } 
        } 
      } 
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error("生成坡道层时发生错误: 高度=" + paramInt, exception);
    } 
  }
  
  private final class_2248 你为什么要破解我的代码aaaacp(int paramInt) {
    Intrinsics.checkNotNullExpressionValue(class_2246.field_10242, (String)aaaadf((char)1936916581));
    switch ((paramInt - 21) / 20 % 4) {
      case 0:
      
      case 1:
      
      case 2:
      
      case 3:
      
      default:
        break;
    } 
    class_2248 class_2248 = class_2246.field_10085;
    Intrinsics.checkNotNull(class_2248);
    return (paramInt <= 20) ? class_2246.field_10242 : class_2248;
  }
  
  private final void 你为什么要破解我的代码aaaacq(int paramInt) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i = paramInt + 5;
    if (paramInt == 10)
      你为什么要破解我的代码aaaacF((String)aaaadf((char)1438122086)); 
    int j = paramInt + 1;
    if (j <= i)
      while (true) {
        int m = 66 - j - 3;
        for (byte b1 = 1; b1 < 14; b1++) {
          class_2338 class_23381 = new class_2338(b1, j, m);
          object.method_8501(class_23381, class_2246.field_10124.method_9564());
          class_2338 class_23382 = class_23381.method_10072();
          object.method_8501(class_23382, class_2246.field_10124.method_9564());
          object.method_8501(class_23382.method_10074(), class_2246.field_10124.method_9564());
        } 
        if (j != i) {
          j++;
          continue;
        } 
        break;
      }  
    j = paramInt;
    int k = 66 - j - 3;
    System.out.println("Last layer: " + j + ", " + k);
    for (byte b = 1; b < 14; b++) {
      class_2338 class_2338 = new class_2338(b, j, k + 1);
      object.method_8501(class_2338, (class_2680)class_2246.field_10425.method_9564().method_11657((class_2769)class_2442.field_11365, (Comparable)class_2768.field_12665));
    } 
  }
  
  public final void 你为什么要破解我的代码aaaacr(int paramInt) {
    int i = RangesKt.coerceIn(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() + paramInt, 10, 1500);
    if (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() >= 1500) {
      你为什么要破解我的代码aaaacF((String)aaaadf((char)1638334563));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaadf((char)-1239482268));
      return;
    } 
    aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n        开始扩展坡道:\n        - 当前高度: " + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() + "\n        - 增加高度: " + paramInt + "\n        - 目标高度: " + i + "\n    "));
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(i);
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() - aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa());
    你为什么要破解我的代码aaaacG();
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(true);
  }
  
  public final void 你为什么要破解我的代码aaaacs(int paramInt) {
    int i = RangesKt.coerceIn(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() - paramInt, 10, 1500);
    if (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() <= 10) {
      你为什么要破解我的代码aaaacF((String)aaaadf((char)864157798));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaadf((char)662241383));
      return;
    } 
    aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n        开始缩减坡道:\n        - 当前高度: " + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() + "\n        - 目标高度: " + i + "\n    "));
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(i);
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(i - aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa());
    你为什么要破解我的代码aaaacG();
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(true);
  }
  
  public final void 你为什么要破解我的代码aaaact(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i = 3 + paramInt1;
    int j = 3 + paramInt2;
    aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n        开始生成爬坡区域:\n        - 目标最大高度: " + i + " y轴\n        - 清理高度范围: " + j + "\n        - 是否完全清除: " + paramBoolean1 + "\n        - 是否为全部清除: " + paramBoolean2 + "\n    "));
    if (paramBoolean2) {
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(paramInt1);
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(paramInt1);
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(0);
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(false);
      你为什么要破解我的代码aaaacv(i, j);
      你为什么要破解我的代码aaaacw((class_3218)object, i);
      System.out.println(aaaadf((char)-639172504));
    } else if (paramInt1 > paramInt2) {
      System.out.println(aaaadf((char)1257898089));
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(paramInt1);
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() - paramInt2);
      System.out.println("targetHeight:" + paramInt1 + ",currentHeight:" + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() + ", pendingHeightChange:" + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag());
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(true);
    } else if (paramInt1 < paramInt2) {
      System.out.println(aaaadf((char)2086797418));
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(paramInt1);
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaah(paramInt1 - paramInt2);
      System.out.println("targetHeight:" + paramInt1 + ",currentHeight:" + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa() + ", pendingHeightChange:" + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag());
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf(true);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaacv(int paramInt1, int paramInt2) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i = RangesKt.coerceAtLeast(paramInt2, paramInt1);
    aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n        开始清理爬坡区域:\n        - 目标高度: " + paramInt1 + "\n        - 当前高度: " + paramInt2 + "\n        - 实际清理高度: " + i + "\n    "));
    if (i > paramInt1) {
      int j;
      for (j = 1; j < 14; j++) {
        int n = paramInt1;
        for (int i1 = 66 - paramInt1 - 3; n <= i + 5; i1--) {
          for (byte b = -1; b < 2; b++) {
            class_2338 class_2338 = new class_2338(j, n + b, i1);
            object.method_8501(class_2338, class_2246.field_10124.method_9564());
          } 
          n++;
        } 
      } 
      j = 66 - i - 3;
      int k = i;
      int m = i + 2;
      if (k <= m)
        while (true) {
          for (byte b = 1; b < 14; b++) {
            int n = j - 12;
            int i1 = j + 2;
            if (n <= i1)
              while (true) {
                object.method_8501(new class_2338(b, k, n), class_2246.field_10124.method_9564());
                if (n != i1) {
                  n++;
                  continue;
                } 
                break;
              }  
          } 
          if (k != m) {
            k++;
            continue;
          } 
          break;
        }  
    } 
    if (paramInt2 < paramInt1) {
      System.out.println(aaaadf((char)1215234155));
      for (byte b = 1; b < 14; b++) {
        int j = paramInt2;
        int k = paramInt2 + 2;
        if (j <= k)
          while (true) {
            int m = 66 - j - 13;
            int n = 66 - j + 1;
            if (m <= n)
              while (true) {
                object.method_8501(new class_2338(b, j, m), class_2246.field_10124.method_9564());
                if (m != n) {
                  m++;
                  continue;
                } 
                break;
              }  
            if (j != k) {
              j++;
              continue;
            } 
            break;
          }  
      } 
    } 
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaadf((char)353960044));
  }
  
  private final void 你为什么要破解我的代码aaaacw(class_3218 paramclass_3218, int paramInt) {
    byte b1 = 66;
    for (byte b2 = 1; b2 < 14; b2++) {
      byte b3 = 66;
      byte b4 = 3;
      byte b5 = 0;
      while (b4 <= paramInt) {
        class_2338 class_2338 = new class_2338(b2, b4, b3);
        switch (b2 - 1) {
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
          
          default:
            break;
        } 
        class_2248 class_2248 = class_2246.field_10542;
        paramclass_3218.method_8501(class_2338, class_2248.method_9564());
        paramclass_3218.method_8501(class_2338.method_10084(), class_2246.field_10124.method_9564());
        paramclass_3218.method_8501(class_2338.method_10084(), (class_2680)class_2246.field_10425.method_9564().method_11657((class_2769)class_2442.field_11365, (Comparable)class_2768.field_12665));
        if (b5 % 10 == 0)
          paramclass_3218.method_8501(class_2338.method_10074(), (class_2680)class_2246.field_10301.method_9564().method_11657((class_2769)class_2555.field_11731, (Comparable)class_2350.field_11043)); 
        b4++;
        b3--;
        b5++;
        b1 = b3;
      } 
    } 
    你为什么要破解我的代码aaaacx(1, paramInt, b1);
  }
  
  private final void 你为什么要破解我的代码aaaacx(int paramInt1, int paramInt2, int paramInt3) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    int i = paramInt1;
    int j = paramInt2;
    int k = paramInt3 - 12 + 1;
    你为什么要破解我的代码aaaacy(i, j, k);
  }
  
  private final void 你为什么要破解我的代码aaaacy(int paramInt1, int paramInt2, int paramInt3) {
    Object object;
    if (((我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null) == null) {
      (我草你怎么反编译我模组aaaaab != null) ? 我草你怎么反编译我模组aaaaab.method_30002() : null;
      return;
    } 
    byte b;
    for (b = 0; b < 13; b++) {
      for (byte b1 = 0; b1 < 12; b1++) {
        if (b == 0 || b == 12 || b1 == 0 || b1 == 11) {
          class_2338 class_2338 = new class_2338(paramInt1 + b, paramInt2, paramInt3 + b1);
          object.method_8501(class_2338, class_2246.field_10153.method_9564());
        } 
      } 
    } 
    for (b = 1; b < 12; b++) {
      for (byte b1 = 1; b1 < 11; b1++) {
        if (b == 1 || b == 11 || b1 == 1 || b1 == 10) {
          class_2338 class_2338 = new class_2338(paramInt1 + b, paramInt2, paramInt3 + b1);
          object.method_8501(class_2338, class_2246.field_10458.method_9564());
        } 
      } 
    } 
    for (b = 2; b < 11; b++) {
      for (byte b1 = 2; b1 < 10; b1++) {
        if (b == 2 || b == 10 || b1 == 2 || b1 == 9) {
          class_2338 class_2338 = new class_2338(paramInt1 + b, paramInt2, paramInt3 + b1);
          object.method_8501(class_2338, class_2246.field_10107.method_9564());
        } 
      } 
    } 
    for (b = 3; b < 10; b++) {
      for (byte b1 = 3; b1 < 9; b1++) {
        if (b == 3 || b == 9 || b1 == 3 || b1 == 8) {
          class_2338 class_2338 = new class_2338(paramInt1 + b, paramInt2, paramInt3 + b1);
          object.method_8501(class_2338, class_2246.field_10058.method_9564());
        } 
      } 
    } 
    for (b = 4; b < 9; b++) {
      for (byte b1 = 4; b1 < 8; b1++) {
        if (b == 4 || b == 8 || b1 == 4 || b1 == 7) {
          class_2338 class_2338 = new class_2338(paramInt1 + b, paramInt2, paramInt3 + b1);
          object.method_8501(class_2338, class_2246.field_10107.method_9564());
        } 
      } 
    } 
    for (b = 5; b < 8; b++) {
      for (byte b1 = 5; b1 < 7; b1++) {
        class_2338 class_2338 = new class_2338(paramInt1 + b, paramInt2, paramInt3 + b1);
        object.method_8501(class_2338, class_2246.field_10058.method_9564());
      } 
    } 
  }
  
  @NotNull
  public final class_2338 你为什么要破解我的代码aaaacz(int paramInt1, int paramInt2) {
    int i = RangesKt.coerceIn(paramInt1, 1, 13);
    int j = 3 + paramInt2;
    int k = 66 - paramInt2;
    return new class_2338(i, j, k);
  }
  
  public final void 你为什么要破解我的代码aaaacA(int paramInt1, int paramInt2, boolean paramBoolean) {
    你为什么要破解我的代码aaaact(paramInt1, paramInt2, true, paramBoolean);
  }
  
  public final void 你为什么要破解我的代码aaaacC(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info("直接设置新高度: " + paramInt + ", 当前高度: " + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa());
    int i = RangesKt.coerceIn(paramInt, 10, 1500);
    if (paramBoolean1) {
      int j = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
      我草你怎么反编译我模组aaaaaP = (3 + i);
      我草你怎么反编译我模组aaaaaQ = i;
      我草你怎么反编译我模组aaaaaR = 66 - i;
      你为什么要破解我的代码aaaacA(i, j, paramBoolean2);
    } else {
      我草你怎么反编译我模组aaaaaP = (3 + i);
      我草你怎么反编译我模组aaaaaQ = i;
      我草你怎么反编译我模组aaaaaR = 66 - i;
    } 
    aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n    高度更新完成:\n    - 新游戏高度: " + i + "\n    - 目标高度: " + 我草你怎么反编译我模组aaaaaP + "\n    - 总攀爬高度: " + 我草你怎么反编译我模组aaaaaQ + "\n    - 胜利点Z坐标: " + 我草你怎么反编译我模组aaaaaR + "\n    "));
  }
  
  public final int 你为什么要破解我的代码aaaacE() {
    return aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
  }
  
  public final void 你为什么要破解我的代码aaaacF(@NotNull String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 2109145197
    //   4: i2c
    //   5: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   18: astore_2
    //   19: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   22: dup
    //   23: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   26: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   29: invokevirtual method_14571 : ()Ljava/util/List;
    //   32: dup
    //   33: ldc_w -1155203016
    //   36: i2c
    //   37: invokestatic aaaadf : (C)Ljava/lang/Object;
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
  
  private final void 你为什么要破解我的代码aaaacG() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 289
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 289
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 289
    //   21: checkcast java/lang/Iterable
    //   24: astore_1
    //   25: iconst_0
    //   26: istore_2
    //   27: aload_1
    //   28: invokeinterface iterator : ()Ljava/util/Iterator;
    //   33: astore_3
    //   34: aload_3
    //   35: invokeinterface hasNext : ()Z
    //   40: ifeq -> 285
    //   43: aload_3
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: astore #4
    //   51: aload #4
    //   53: checkcast net/minecraft/class_3222
    //   56: astore #5
    //   58: iconst_0
    //   59: istore #6
    //   61: aload #5
    //   63: invokevirtual method_23321 : ()D
    //   66: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaR : D
    //   69: dcmpg
    //   70: ifgt -> 280
    //   73: aload #5
    //   75: invokevirtual method_23321 : ()D
    //   78: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaR : D
    //   81: bipush #13
    //   83: i2d
    //   84: dsub
    //   85: dcmpl
    //   86: iflt -> 280
    //   89: aload #5
    //   91: invokevirtual method_23318 : ()D
    //   94: d2i
    //   95: istore #7
    //   97: bipush #66
    //   99: i2d
    //   100: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaR : D
    //   103: dsub
    //   104: iconst_3
    //   105: i2d
    //   106: dadd
    //   107: dstore #8
    //   109: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   112: invokevirtual 你为什么要破解我的代码aaaaag : ()I
    //   115: invokestatic getSign : (I)I
    //   118: ifge -> 137
    //   121: dload #8
    //   123: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   126: invokevirtual 你为什么要破解我的代码aaaaag : ()I
    //   129: i2d
    //   130: dadd
    //   131: iconst_3
    //   132: i2d
    //   133: dadd
    //   134: goto -> 142
    //   137: dload #8
    //   139: iconst_3
    //   140: i2d
    //   141: dadd
    //   142: dstore #10
    //   144: bipush #66
    //   146: i2d
    //   147: dload #10
    //   149: iconst_3
    //   150: i2d
    //   151: dsub
    //   152: dsub
    //   153: iconst_2
    //   154: i2d
    //   155: dadd
    //   156: dstore #12
    //   158: new net/minecraft/class_2338
    //   161: dup
    //   162: new kotlin/ranges/IntRange
    //   165: dup
    //   166: iconst_1
    //   167: bipush #13
    //   169: invokespecial <init> : (II)V
    //   172: getstatic kotlin/random/Random.Default : Lkotlin/random/Random$Default;
    //   175: checkcast kotlin/random/Random
    //   178: invokestatic random : (Lkotlin/ranges/IntRange;Lkotlin/random/Random;)I
    //   181: dload #10
    //   183: d2i
    //   184: dload #12
    //   186: d2i
    //   187: invokespecial <init> : (III)V
    //   190: astore #14
    //   192: aload #5
    //   194: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   197: aload #5
    //   199: aload #14
    //   201: invokevirtual method_10263 : ()I
    //   204: i2d
    //   205: ldc2_w 0.5
    //   208: dadd
    //   209: aload #14
    //   211: invokevirtual method_10264 : ()I
    //   214: i2d
    //   215: iconst_5
    //   216: i2d
    //   217: dadd
    //   218: aload #14
    //   220: invokevirtual method_10260 : ()I
    //   223: i2d
    //   224: dconst_1
    //   225: dadd
    //   226: invokestatic 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;DDD)V
    //   229: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   232: aload #5
    //   234: invokevirtual method_5477 : ()Lnet/minecraft/class_2561;
    //   237: invokeinterface getString : ()Ljava/lang/String;
    //   242: iload #7
    //   244: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak;
    //   247: invokevirtual 你为什么要破解我的代码aaaaag : ()I
    //   250: dload #10
    //   252: aload #14
    //   254: invokevirtual method_10263 : ()I
    //   257: aload #14
    //   259: invokevirtual method_10264 : ()I
    //   262: aload #14
    //   264: invokevirtual method_10260 : ()I
    //   267: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;IIDIII)Ljava/lang/String;
    //   272: invokestatic trimIndent : (Ljava/lang/String;)Ljava/lang/String;
    //   275: invokeinterface info : (Ljava/lang/String;)V
    //   280: nop
    //   281: nop
    //   282: goto -> 34
    //   285: nop
    //   286: goto -> 291
    //   289: pop
    //   290: nop
    //   291: return
  }
  
  private final void 你为什么要破解我的代码aaaacH(String paramString, int paramInt) {
    boolean bool1 = true;
    long l = 10L;
    Ref.IntRef intRef1 = new Ref.IntRef();
    intRef1.element = paramInt;
    Ref.IntRef intRef2 = new Ref.IntRef();
    MinecraftServer minecraftServer = 我草你怎么反编译我模组aaaaab;
    boolean bool2 = false;
    (new Timer()).scheduleAtFixedRate(new aaaacf(intRef1, minecraftServer, bool1, intRef2), 0L, l * 50L);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)1886388334));
  }
  
  private final void 你为什么要破解我的代码aaaacI(String paramString) {
    你为什么要破解我的代码aaaacC(500, true, true);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)982515823));
  }
  
  private final void 你为什么要破解我的代码aaaacJ(String paramString, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx;
    //   3: iload_2
    //   4: invokevirtual 你为什么要破解我的代码aaaaaf : (I)V
    //   7: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   10: dup
    //   11: ifnull -> 148
    //   14: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   17: dup
    //   18: ifnull -> 148
    //   21: invokevirtual method_14571 : ()Ljava/util/List;
    //   24: dup
    //   25: ifnull -> 148
    //   28: checkcast java/lang/Iterable
    //   31: astore_3
    //   32: iconst_0
    //   33: istore #4
    //   35: aload_3
    //   36: invokeinterface iterator : ()Ljava/util/Iterator;
    //   41: astore #5
    //   43: aload #5
    //   45: invokeinterface hasNext : ()Z
    //   50: ifeq -> 144
    //   53: aload #5
    //   55: invokeinterface next : ()Ljava/lang/Object;
    //   60: astore #6
    //   62: aload #6
    //   64: checkcast net/minecraft/class_3222
    //   67: astore #7
    //   69: iconst_0
    //   70: istore #8
    //   72: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaad/aaaaah;
    //   75: aload #7
    //   77: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   80: aload #7
    //   82: ldc_w -168624016
    //   85: i2c
    //   86: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   89: checkcast java/lang/String
    //   92: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
    //   95: checkcast net/minecraft/class_2561
    //   98: ldc_w -1948057487
    //   101: i2c
    //   102: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   105: checkcast java/lang/String
    //   108: iconst_1
    //   109: anewarray java/lang/Object
    //   112: astore #9
    //   114: aload #9
    //   116: iconst_0
    //   117: iload_2
    //   118: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   121: aastore
    //   122: aload #9
    //   124: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
    //   127: checkcast net/minecraft/class_2561
    //   130: bipush #10
    //   132: bipush #70
    //   134: bipush #20
    //   136: invokevirtual 你为什么要破解我的代码aaaaaa : (Lnet/minecraft/class_3222;Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;III)V
    //   139: nop
    //   140: nop
    //   141: goto -> 43
    //   144: nop
    //   145: goto -> 150
    //   148: pop
    //   149: nop
    //   150: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   153: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   156: ldc_w 852033650
    //   159: i2c
    //   160: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   163: checkcast java/lang/String
    //   166: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   169: return
  }
  
  private final void 你为什么要破解我的代码aaaacK(String paramString, int paramInt) {
    你为什么要破解我的代码aaaabx(paramString, paramInt);
    aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaac((String)aaaadf((char)-1745616781));
  }
  
  private final void 你为什么要破解我的代码aaaacL(String paramString, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 88
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 88
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 88
    //   21: checkcast java/lang/Iterable
    //   24: astore_3
    //   25: iconst_0
    //   26: istore #4
    //   28: aload_3
    //   29: invokeinterface iterator : ()Ljava/util/Iterator;
    //   34: astore #5
    //   36: aload #5
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 84
    //   46: aload #5
    //   48: invokeinterface next : ()Ljava/lang/Object;
    //   53: astore #6
    //   55: aload #6
    //   57: checkcast net/minecraft/class_3222
    //   60: astore #7
    //   62: iconst_0
    //   63: istore #8
    //   65: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   68: aload #7
    //   70: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   73: aload #7
    //   75: iload_2
    //   76: invokespecial 你为什么要破解我的代码aaaacM : (Lnet/minecraft/class_3222;I)V
    //   79: nop
    //   80: nop
    //   81: goto -> 36
    //   84: nop
    //   85: goto -> 90
    //   88: pop
    //   89: nop
    //   90: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaaS;
    //   93: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaaap;
    //   96: ldc_w 1592262772
    //   99: i2c
    //   100: invokestatic aaaadf : (C)Ljava/lang/Object;
    //   103: checkcast java/lang/String
    //   106: invokevirtual 你为什么要破解我的代码aaaaac : (Ljava/lang/String;)V
    //   109: return
  }
  
  private final void 你为什么要破解我的代码aaaacM(class_3222 paramclass_3222, int paramInt) {
    class_1937 class_1937 = paramclass_3222.method_37908();
    UUID uUID = paramclass_3222.method_5667();
    int i = paramInt;
    long l1 = System.currentTimeMillis();
    (Long)我草你怎么反编译我模组aaaabi.get(uUID);
    long l2 = 我草你怎么反编译我模组aaaabi.containsKey(uUID) ? ((((Long)我草你怎么反编译我模组aaaabi.get(uUID) != null) ? ((Long)我草你怎么反编译我模组aaaabi.get(uUID)).longValue() : l1) + i * 1000L) : (l1 + i * 1000L);
    if (!我草你怎么反编译我模组aaaabh.containsKey(uUID)) {
      ArrayList<class_1428> arrayList = new ArrayList();
      for (byte b = 0; b < 4; b++) {
        Intrinsics.checkNotNull(class_1299.field_6132.method_5883(class_1937), (String)aaaadf((char)-266796939));
        class_1428 class_1428 = (class_1428)class_1299.field_6132.method_5883(class_1937);
        class_1428.method_5977(true);
        class_1428.method_5875(true);
        class_1428.method_5684(true);
        class_1428.method_5803(true);
        class_1937.method_8649((class_1297)class_1428);
        arrayList.add(class_1428);
      } 
      Intrinsics.checkNotNull(uUID);
      我草你怎么反编译我模组aaaabh.put(uUID, arrayList);
    } 
    Long long_ = Long.valueOf(l2);
    Intrinsics.checkNotNull(uUID);
    我草你怎么反编译我模组aaaabi.put(uUID, long_);
    int j = (int)((l2 - l1) / 1000L);
    paramclass_3222.method_6092(new class_1293(class_1294.field_5907, j * 20, 4, false, true, true));
    if (paramclass_3222.method_5996(class_5134.field_23718) == null) {
      paramclass_3222.method_5996(class_5134.field_23718);
    } else {
      paramclass_3222.method_5996(class_5134.field_23718).method_6192(1000.0D);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaacN(UUID paramUUID) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabh : Ljava/util/Map;
    //   3: aload_1
    //   4: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   9: checkcast java/util/List
    //   12: dup
    //   13: ifnull -> 76
    //   16: checkcast java/lang/Iterable
    //   19: astore_2
    //   20: iconst_0
    //   21: istore_3
    //   22: aload_2
    //   23: invokeinterface iterator : ()Ljava/util/Iterator;
    //   28: astore #4
    //   30: aload #4
    //   32: invokeinterface hasNext : ()Z
    //   37: ifeq -> 72
    //   40: aload #4
    //   42: invokeinterface next : ()Ljava/lang/Object;
    //   47: astore #5
    //   49: aload #5
    //   51: checkcast net/minecraft/class_1428
    //   54: astore #6
    //   56: iconst_0
    //   57: istore #7
    //   59: aload #6
    //   61: getstatic net/minecraft/class_1297$class_5529.field_26999 : Lnet/minecraft/class_1297$class_5529;
    //   64: invokevirtual method_5650 : (Lnet/minecraft/class_1297$class_5529;)V
    //   67: nop
    //   68: nop
    //   69: goto -> 30
    //   72: nop
    //   73: goto -> 78
    //   76: pop
    //   77: nop
    //   78: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabh : Ljava/util/Map;
    //   81: aload_1
    //   82: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   87: pop
    //   88: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabi : Ljava/util/Map;
    //   91: aload_1
    //   92: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   97: pop
    //   98: return
  }
  
  private final void 你为什么要破解我的代码aaaacO() {
    // Byte code:
    //   0: invokestatic currentTimeMillis : ()J
    //   3: lstore_1
    //   4: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabh : Ljava/util/Map;
    //   7: invokeinterface entrySet : ()Ljava/util/Set;
    //   12: invokeinterface iterator : ()Ljava/util/Iterator;
    //   17: astore_3
    //   18: aload_3
    //   19: invokeinterface hasNext : ()Z
    //   24: ifeq -> 414
    //   27: aload_3
    //   28: invokeinterface next : ()Ljava/lang/Object;
    //   33: checkcast java/util/Map$Entry
    //   36: astore #4
    //   38: aload #4
    //   40: invokeinterface getKey : ()Ljava/lang/Object;
    //   45: checkcast java/util/UUID
    //   48: astore #5
    //   50: aload #4
    //   52: invokeinterface getValue : ()Ljava/lang/Object;
    //   57: checkcast java/util/List
    //   60: astore #6
    //   62: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   65: dup
    //   66: ifnull -> 84
    //   69: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   72: dup
    //   73: ifnull -> 84
    //   76: aload #5
    //   78: invokevirtual method_14602 : (Ljava/util/UUID;)Lnet/minecraft/class_3222;
    //   81: goto -> 86
    //   84: pop
    //   85: aconst_null
    //   86: astore #7
    //   88: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabi : Ljava/util/Map;
    //   91: aload #5
    //   93: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   98: checkcast java/lang/Long
    //   101: dup
    //   102: ifnull -> 111
    //   105: invokevirtual longValue : ()J
    //   108: goto -> 113
    //   111: pop
    //   112: lconst_0
    //   113: lstore #8
    //   115: lload_1
    //   116: lload #8
    //   118: lcmp
    //   119: ifge -> 127
    //   122: aload #7
    //   124: ifnonnull -> 188
    //   127: aload #7
    //   129: dup
    //   130: ifnull -> 173
    //   133: astore #10
    //   135: iconst_0
    //   136: istore #11
    //   138: aload #10
    //   140: getstatic net/minecraft/class_1294.field_5907 : Lnet/minecraft/class_6880;
    //   143: invokevirtual method_6016 : (Lnet/minecraft/class_6880;)Z
    //   146: pop
    //   147: aload #10
    //   149: getstatic net/minecraft/class_5134.field_23718 : Lnet/minecraft/class_6880;
    //   152: invokevirtual method_5996 : (Lnet/minecraft/class_6880;)Lnet/minecraft/class_1324;
    //   155: dup
    //   156: ifnonnull -> 163
    //   159: pop
    //   160: goto -> 169
    //   163: ldc2_w 0.1
    //   166: invokevirtual method_6192 : (D)V
    //   169: nop
    //   170: goto -> 175
    //   173: pop
    //   174: nop
    //   175: aload_0
    //   176: aload #5
    //   178: invokespecial 你为什么要破解我的代码aaaacN : (Ljava/util/UUID;)V
    //   181: iconst_1
    //   182: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabg : Z
    //   185: goto -> 18
    //   188: lload #8
    //   190: lload_1
    //   191: lsub
    //   192: sipush #1000
    //   195: i2l
    //   196: ldiv
    //   197: l2i
    //   198: istore #12
    //   200: aload_0
    //   201: aload #7
    //   203: iload #12
    //   205: invokespecial 你为什么要破解我的代码aaaacP : (Lnet/minecraft/class_3222;I)V
    //   208: iconst_0
    //   209: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaabg : Z
    //   212: lload_1
    //   213: l2d
    //   214: ldc2_w 300.0
    //   217: ddiv
    //   218: dstore #13
    //   220: ldc2_w 1.4
    //   223: dstore #15
    //   225: dload #13
    //   227: ldc2_w 0.5
    //   230: dmul
    //   231: invokestatic sin : (D)D
    //   234: ldc2_w 0.3
    //   237: dmul
    //   238: dstore #17
    //   240: aload #6
    //   242: checkcast java/lang/Iterable
    //   245: astore #19
    //   247: iconst_0
    //   248: istore #20
    //   250: iconst_0
    //   251: istore #21
    //   253: aload #19
    //   255: invokeinterface iterator : ()Ljava/util/Iterator;
    //   260: astore #22
    //   262: aload #22
    //   264: invokeinterface hasNext : ()Z
    //   269: ifeq -> 410
    //   272: aload #22
    //   274: invokeinterface next : ()Ljava/lang/Object;
    //   279: astore #23
    //   281: iload #21
    //   283: iinc #21, 1
    //   286: istore #24
    //   288: iload #24
    //   290: ifge -> 296
    //   293: invokestatic throwIndexOverflow : ()V
    //   296: iload #24
    //   298: aload #23
    //   300: checkcast net/minecraft/class_1428
    //   303: astore #25
    //   305: istore #26
    //   307: iconst_0
    //   308: istore #27
    //   310: dload #13
    //   312: iload #26
    //   314: i2d
    //   315: ldc2_w 3.141592653589793
    //   318: dmul
    //   319: iconst_2
    //   320: i2d
    //   321: ddiv
    //   322: dadd
    //   323: dstore #28
    //   325: aload #7
    //   327: invokevirtual method_23317 : ()D
    //   330: dload #15
    //   332: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaaE : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae;
    //   335: dload #28
    //   337: invokespecial 你为什么要破解我的代码aaaacR : (D)D
    //   340: dmul
    //   341: dadd
    //   342: dstore #30
    //   344: aload #7
    //   346: invokevirtual method_23321 : ()D
    //   349: dload #15
    //   351: dload #28
    //   353: invokestatic sin : (D)D
    //   356: dmul
    //   357: dadd
    //   358: dstore #32
    //   360: aload #7
    //   362: invokevirtual method_23318 : ()D
    //   365: ldc2_w 0.6
    //   368: dadd
    //   369: dload #17
    //   371: dadd
    //   372: iload #26
    //   374: i2d
    //   375: ldc2_w 0.1
    //   378: dmul
    //   379: dadd
    //   380: dstore #34
    //   382: aload #25
    //   384: dload #30
    //   386: dload #34
    //   388: dload #32
    //   390: ldc_w 180.0
    //   393: fconst_0
    //   394: invokevirtual method_5808 : (DDDFF)V
    //   397: aload #25
    //   399: getstatic net/minecraft/class_243.field_1353 : Lnet/minecraft/class_243;
    //   402: invokevirtual method_18799 : (Lnet/minecraft/class_243;)V
    //   405: nop
    //   406: nop
    //   407: goto -> 262
    //   410: nop
    //   411: goto -> 18
    //   414: return
  }
  
  private final void 你为什么要破解我的代码aaaacP(class_3222 paramclass_3222, int paramInt) {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = class_2561.method_43470("§b" + paramInt + " s");
    paramclass_3222.method_7353((class_2561)class_2561.method_43469((String)aaaadf((char)1920467062), arrayOfObject), true);
  }
  
  private final void 你为什么要破解我的代码aaaacQ(String paramString, int paramInt) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaae.我草你怎么反编译我模组aaaaab : Lnet/minecraft/server/MinecraftServer;
    //   3: dup
    //   4: ifnull -> 102
    //   7: invokevirtual method_3760 : ()Lnet/minecraft/class_3324;
    //   10: dup
    //   11: ifnull -> 102
    //   14: invokevirtual method_14571 : ()Ljava/util/List;
    //   17: dup
    //   18: ifnull -> 102
    //   21: checkcast java/lang/Iterable
    //   24: astore_3
    //   25: iconst_0
    //   26: istore #4
    //   28: aload_3
    //   29: invokeinterface iterator : ()Ljava/util/Iterator;
    //   34: astore #5
    //   36: aload #5
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 98
    //   46: aload #5
    //   48: invokeinterface next : ()Ljava/lang/Object;
    //   53: astore #6
    //   55: aload #6
    //   57: checkcast net/minecraft/class_3222
    //   60: astore #7
    //   62: iconst_0
    //   63: istore #8
    //   65: getstatic kotlinx/coroutines/GlobalScope.INSTANCE : Lkotlinx/coroutines/GlobalScope;
    //   68: checkcast kotlinx/coroutines/CoroutineScope
    //   71: aconst_null
    //   72: aconst_null
    //   73: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaaH
    //   76: dup
    //   77: iload_2
    //   78: aload #7
    //   80: aconst_null
    //   81: invokespecial <init> : (ILnet/minecraft/class_3222;Lkotlin/coroutines/Continuation;)V
    //   84: checkcast kotlin/jvm/functions/Function2
    //   87: iconst_3
    //   88: aconst_null
    //   89: invokestatic launch$default : (Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;
    //   92: pop
    //   93: nop
    //   94: nop
    //   95: goto -> 36
    //   98: nop
    //   99: goto -> 104
    //   102: pop
    //   103: nop
    //   104: return
  }
  
  private final double 你为什么要破解我的代码aaaacR(double paramDouble) {
    return Math.cos(paramDouble);
  }
  
  public final void 你为什么要破解我的代码aaaacS() {
    aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(true);
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaadf((char)770703479));
  }
  
  public final void 你为什么要破解我的代码aaaacT() {
    if (!aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa())
      return; 
    class_3218 class_3218 = 你为什么要破解我的代码aaaaal().method_30002();
    boolean bool = false;
    byte b = 0;
    while (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() >= aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaae() && b < 3) {
      try {
        class_3218.method_17988(bool, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac(), true);
        aaaaaa.你为什么要破解我的代码aaaaaa().info("加载区块: (" + bool + ", " + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() + ")");
        b++;
      } catch (Exception exception) {
        aaaaaa.你为什么要破解我的代码aaaaaa().error("加载区块时发生错误: (" + bool + ", " + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() + ")", exception);
      } 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa;
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.你为什么要破解我的代码aaaaad(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.你为什么要破解我的代码aaaaac() - 2);
    } 
    if (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac() < aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaae()) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info(StringsKt.trimIndent("\n            区块加载完成:\n            - 范围: X=0, Z=5~" + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaae() + "\n            - 方块范围: X=0, Z=80~" + aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaae() * 16 + "\n        "));
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab(false);
    } 
  }
  
  public final void 你为什么要破解我的代码aaaacU(int paramInt) {
    class_3218 class_3218 = 你为什么要破解我的代码aaaaal().method_30002();
    boolean bool = false;
    int i = -(paramInt + 4);
    short s = -512;
    if (i < s)
      try {
        int j = s >> 4;
        int k = i >> 4;
        int m = j;
        int n = ProgressionUtilKt.getProgressionLastElement(j, k, -2);
        if (n <= m)
          while (true) {
            class_3218.method_17988(bool, m, true);
            if (m != n) {
              m -= 2;
              continue;
            } 
            break;
          }  
        aaaaaa.你为什么要破解我的代码aaaaaa().info("区块加载范围已扩展 Z(" + s + " ~ " + i + ")");
      } catch (Exception exception) {
        aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaadf((char)264241272), exception);
      }  
  }
  
  private final int 你为什么要破解我的代码aaaacV(MinecraftServer paramMinecraftServer, Function1<? super MinecraftServer, ? extends TimerTask> paramFunction1) {
    int i = aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaay.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
    paramFunction1.invoke(paramMinecraftServer);
    return i;
  }
  
  private static final void 你为什么要破解我的代码aaaacW(List<Timer> paramList, class_1299<? extends class_1308> paramclass_1299, class_3218 paramclass_3218, String paramString, int paramInt, long paramLong) {
    Timer timer = new Timer();
    Ref.IntRef intRef = new Ref.IntRef();
    timer.scheduleAtFixedRate(new aaaacd(intRef, paramInt, paramList, timer, paramclass_1299, paramclass_3218, paramString), 0L, paramLong);
    paramList.add(timer);
  }
  
  private static final boolean 你为什么要破解我的代码aaaacX(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaadf((char)1731919993));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  private static final boolean 你为什么要破解我的代码aaaacY(Function1 paramFunction1, Object paramObject) {
    Intrinsics.checkNotNullParameter(paramFunction1, (String)aaaadf((char)808648825));
    return ((Boolean)paramFunction1.invoke(paramObject)).booleanValue();
  }
  
  static {
    byte[] arrayOfByte1 = "M\002I»]DÚ§ÑÉ­q_[\nA\bWÆÕÈDdý\017©wónâ¹e-¾gèÇÎ\003y¯zµ9OÖæ¾°Oü »¨Ñ²¶½\037UþÚç²T\023[ó7Q¸\005ù÷\\S*Êu×S\027¾ëáÿ7¹J\003Q\035Æ2¡²ß®\016×ÜeEËää½b¯þ\034³\021Nu$e\002\030\f<iêPK|:$E\023h\006¼`(sEÜ×ÈòöQ+'aÀÊ#4¼¤Á\b«éºH\023*4Uv&fôxè[ýa¬Q'­\013Á2vxÎCrÌ\022<'\n~I&v²°FÄ\024¦9Áý\017\020wãvÐ\bx»öH`dÎ¾8vçú9$î¿v¾\000²Ê8hr\t\"Ï;nÎ5xÃG»|øëÐ%udqVbÞ?o±kú*ÚfF Öo[ÄÉ\016â\016\020Ä¶>Y¹S×PL\023:v³ÚeÇ`7\n^\026Ø]à½oë\bí\017AqgÁhÄ+Ehßv\004\036\031F%w éÐ\033éáµ¸\036Y3½\030íg²\002\\ã­Ø×å\035PaR,rÜ¸ô¼\037ÌJå½=É{}T\021XR\034¶Ç,¥q nM-\031Ø½åj<³éÖ\003)5oä¿V>1þ¯Xý\000&´·µª¹6ãÔ/À\024AÕ\006ÎÂ*\036\007bàoQ¹¡g.a¼úøl±ÈûU\\æ2\034¦Ù\016,¢÷ï\021\\]8îìÜÀÐHò8Â¤ýå~À'¥~Å¡ÊTÕ¡Çåñ¨é]zû4mjNcAÄ\013\036Æ \037g¯ CD±~\036´øÆ\031Â Ð/Z)wEH\017÷æ§ûÀóî\005q\036°tí{\r±ï$ ÜÈ\000\020öÉF<\023(vxþF tTdÙ\033ß\0308\\Q!pS¡ÆDòJ)X¹å\034Ût\fõIú¿Añç\002ËLõf$àOü4ãøÚY.\013MJóc~qq8áx° C²þµÆVìuA\035>- ?å°\022mI¤\f¡ª\f¼0ûnæqÊvæLYæ#Ú`\024¢r~µ_¦\0337B°$iâ\023H©ÖGHÇ\020½äÓ\027b¡\036Ø¦D,\033àã\027\b\013Ñ\026Rv\031[um;÷:ó\004øL\025IÇB5Ìgka-Py$eÝÉ®p\001ÜHT)ÜVð9\020Yð\024V\022¹ØJÖdÃ[\027\034¼l\007\025½D_ 0J\005å¨i\006C(Ã¢\031áÇ{YÂÃ±\ný?h X-E\023!°\033ey\024\035Ö¢_²\023¬\002ßÊ°#B}e:\023ðu\006\r\006X\026\027¥ ß:L¯\bå¼háUÙ\033}ë\000Õôó}|ÿ?Ý4ú\"5\006{ðñ\"\003Ø®ã4Àu\002ùè¦fÞIÅoîo e\035Ò\016È\001\016¶BLOþ½6\036 j:{\027u%ëjÆ\022ëô/U\025«+ÆhYìúÐ p\b#\022Ìß2¶N\033¸Y½k\000¹\036b]·©ÏeäO\0165nX\033&\002(}TF]±ëºõs+Ñ®U1²íýØ¿Tpì\034î\tDø\r\007í:ÿ1Î:<éØÜìqø¶\\~ÜÈr\036djÚ]³;ñ7§2¯ÎÑ¨.ßÛ¹¾Íüy&7Ï\007{l\\Æ\031L;÷ ÍÌp§©±,LbQ-ÖJãWPG¡ÍDø\003ªn('ýzX{\032]È3YR\007´t(/i\031e! 0á;°\023\016Tæ\035ëº\016«X\000](0ÖÑÚ\037i\021!µ¡¥lh¸\t÷±KQÞ?½.Ä\006Ùiä\\¤ç[e$z° Fë.V±ê}\032ïáÔ\026ädå\035ýÆq#\036~¹ûTÙ\013@ÂÁL4\036\026\bºmÔa¹]¦tVÃd`\031\030\002AÝ\021\026×\fÔ9`Á¨\026\023>)\003\026*AFÚÂ\\\0308êÆAyùÃ¢¿Óò\032b \\7ø\035\031\bU9°¨xª:ëhwk/v\025ÊvÔ¨ÖÊ^ìV¬PÛ\n\025½zd2êñ\027\035\021SUì[³\tK±¸\027@ZÒÖ,\027Ë¸ù\0068àñÃ\004f¬o²:\016FËàç7IX|ïÆ\0049\021Ì\007³U}\b\037yo\023~O~Ó÷\022µ\024Cª´\000£,û¾ÿÖ9+?SÃNÒ¥µ¶&»qy\030'Î¨\003?;9\t45\002OûRÊÂÍv#ÛÖO\034ÛM?Ù7Ð)£j~ImèàGJ!^zØc\022\004\022\002iÉ\026\033f\025o[.ñ\fdjó\022ùÆæ c(nèB\006v¡DF\017åíÔz\rû2ìT\032\023ù@´Û]\013\032ú¤\001ü\037Ô(5÷9QLÒËHÍ\027!É¶ë©k¼Ë5`8g-î\031ÛaU´»:\000k\016¿éOÊ±Þ\025`>\023j%\"\t\026¼}O»,\n¶§¯-h\007\026o\024É¨¦\021*\"=<U\037Lõ\025\005o04¢t·}ï®YW\016DbÇÇ´l\006¿oÒ\006\005ñë\005%MRñ\025w|k\013E\002ÿßìµÑ&ã&l\004VLH\025\030®Íã\031?:ÌÀö\005FéÑãG66ÔrÑç[àùmå/-âÝ\023Äêl¤çô\bq2¿>(üTÂ\r¾Å\003È¤f¸bêð\b*;\tÅÚô*\024·\027«@¥ÿ3S\016ÀÄKÀJë¥JéÒ\f.øN7<T¯ÿ»\020Ûiá¶J´TÀk\021\024\rB\032\003\0376]\002!ZÌjp£ÒÐO¹ 1+Á\021[*òLD%%ÒË\035â\027.&4a.Ýäð\000ÒoÑ#´Ó^g\016d#¬!CJðÝ|þä#¿\034ìr\020c:\021\nÌÀùÛ¥f6ü\016\t;VÍ\035xþÌÍ²\t¢G\006íîdKF\r¶Y5\027\024û9ê\013Õb`\003ç\\©zº\023¾¤;ò1^\002ÞÀ&v+2ïgü¡uüÄ{,È;-ÍÔ\030äY¨\bÜ\004î(Ì;ÌÃhW-ûmS¹Åe\013\036DMð\027\b; í¦]ò\031îðyLºÌ\017Â4©ÆY\005f$ÂUÓ\023\034IÌ(\024Ë·4á±NÀ4Õ­bâðD\026?LH+¥¹\002ûL\031+ ¾SÂ³ëÿ4Qt]ÿ=U!r9=\n\013¶ÎnßÜiQÅ(ü\004\036GE¸¦Ý³÷\020ÔÃá,s\b\021±Ú/?³@)3¯\\R¹FOaNÍì<XÎBÁ\\ø#Ü\002\005TÚ\001F\035mÛÞÁ,C\020§qöP*\024^4À»ìÒ¨úº/ÌLLD<Ï\"æëbÀPU\027e\t\022JÓ:·äE\022öÁ6{co?X¯kTSZ ¬ü$hÞ$½û/ôÇ±rW0E4ÿôÿq.ÐG\bý½\036%P³nûÔí²3ÍAãÿ\025Jr©\005\005\"6\013\022n\035]í5ïoÈÇ»é¤«ÜQø/\"©`\026ÊÖ\022.¬c\"jÉù ^sÅ8Õ\027\005ãÕW©ò\râþï\033¨S®\të\t\024MµL\002»«\006\022KåÔÑón\\8Qb+\013Ú»+úèy\fÊ×\021$æðB'f)\007©\000à\030[;(ªXk\013y\f»¶JWb¯øwWw6¹´o3xR¡.ÍµáÜÕñÂ\032¡lVOÊ\034\013â°·\rÅUõ.Åês¶\020%¬oÑoÀ\0347UÏã¿þôf«t¨ä\021m¬ \021î`ÛÁí\r¯\023³¶\006e\006\003bM}AO)8Ï\001=Á\006\035%1ðAä*ýæjoïÈ.ÞløV\n»\006Ú ØÑ5Üø=¢· d&>ãÐMAA¥\b\023ä­\024}þG\\mc³3°£zq\\\032Øæ î%sg$eù¯ÍÒÆfd\026Çß\035À#r\000X<5±_µ)æ´|us\b8&/zUÊçcï®Ða¬öH~9¤©5â÷9+ÿ¾wV:mÒ\036\034þ²°½rÚzaàü\030x\027\036Ye©²\033¾V8À\032Öx1}»±ñ`Pí\031v\t\005Ä\021-\020rí=Èx\füW=Ä¾or\023ºCýÎÒé¸®æ¦\001 \034e9ZÌCð\\µÝè×þÚâÊ\002öI¶##úÅäpÛ\021ZÚ®`PÓÚÅ\017ß\fõ:*_\020ÕAþ·òhv¹Ñ\rC\033Xµc#t\031SèHw*î!cc¬èW/.1·ÚI±·í{^¨¾ÄòÐEÛ\000bÍÍ\033Ê«gÄÝáê\017b\030\001ïÅã¡põko®)#5\003ì\023*½u³ÊkûD³î05óxNÿ\035@ðOÛÉ£Ï°­æZ¶FR\033åwT-\0236\bXjÉ-¨\016ÇOUsË\\\033u\032@èEfIWÔ\027ÞiÁ\030Z\021\005ªy/ü¸û¹¹\005b_èÆüUtà­ï)\035¤ÌP\001(\rÛ3 +ÞH4ÛvÂ½ô¸&$\005þ­üqÀhïq³`>o\026\020J\031\037ÅýlTâ\r\035·¿O¿mQ_eµRÚî\017\030S[þ&ù'idq¬fV1à=ñC\\G\030Î:\000£\027X¹3ã£F%¢L\002²²Â{ 4\007#ô9ý\007DâÜÐ¥+ûñ_ôÐn`Ù©z»»\017\002-\031²\020\003Ù".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "Výä¶m`t".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-599665873;
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
              Object[] arrayOfObject = new Object[122];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaabe = 12;
            我草你怎么反编译我模组aaaabd = 13;
            我草你怎么反编译我模组aaaabc = 66;
            我草你怎么反编译我模组aaaabb = 3;
            我草你怎么反编译我模组aaaaba = 13;
            我草你怎么反编译我模组aaaaaZ = 1;
            我草你怎么反编译我模组aaaaaY = 10;
            我草你怎么反编译我模组aaaaaX = 60;
            我草你怎么反编译我模组aaaaaO = 500;
            我草你怎么反编译我模组aaaaaN = 4;
            我草你怎么反编译我模组aaaaaM = 4.0D;
            我草你怎么反编译我模组aaaaaE = new aaaaae();
            我草你怎么反编译我模组aaaaaJ = 我草你怎么反编译我模组aaaaaI;
            我草你怎么反编译我模组aaaaaL = 20;
            我草你怎么反编译我模组aaaaaP = 504.0D;
            我草你怎么反编译我模组aaaaaQ = 我草你怎么反编译我模组aaaaaP - 4.0D;
            我草你怎么反编译我模组aaaaaR = -434.0D;
            我草你怎么反编译我模组aaaaaU = new AtomicInteger(0);
            我草你怎么反编译我模组aaaaaV = new AtomicInteger(0);
            class_6880[] arrayOfClass_6880 = new class_6880[9];
            arrayOfClass_6880[0] = class_1294.field_5899;
            arrayOfClass_6880[1] = class_1294.field_5920;
            arrayOfClass_6880[2] = class_1294.field_5901;
            arrayOfClass_6880[3] = class_1294.field_5911;
            arrayOfClass_6880[4] = class_1294.field_5909;
            arrayOfClass_6880[5] = class_1294.field_5919;
            arrayOfClass_6880[6] = class_1294.field_5916;
            arrayOfClass_6880[7] = class_1294.field_5903;
            arrayOfClass_6880[8] = class_1294.field_5902;
            我草你怎么反编译我模组aaaabf = SetsKt.setOf((Object[])arrayOfClass_6880);
            我草你怎么反编译我模组aaaabg = true;
            我草你怎么反编译我模组aaaabh = new LinkedHashMap<>();
            我草你怎么反编译我模组aaaabi = new LinkedHashMap<>();
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaadf(char paramChar) {
    return ((Object[])aaaabk)[paramChar];
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf {
    @NotNull
    public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf 我草你怎么反编译我模组aaaaaa = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabf();
    
    private static boolean 我草你怎么反编译我模组aaaaab;
    
    private static int 我草你怎么反编译我模组aaaaac = 5;
    
    private static int 我草你怎么反编译我模组aaaaad = -32;
    
    public final boolean 你为什么要破解我的代码aaaaaa() {
      return 我草你怎么反编译我模组aaaaab;
    }
    
    public final void 你为什么要破解我的代码aaaaab(boolean param1Boolean) {
      我草你怎么反编译我模组aaaaab = param1Boolean;
    }
    
    public final int 你为什么要破解我的代码aaaaac() {
      return 我草你怎么反编译我模组aaaaac;
    }
    
    public final void 你为什么要破解我的代码aaaaad(int param1Int) {
      我草你怎么反编译我模组aaaaac = param1Int;
    }
    
    public final int 你为什么要破解我的代码aaaaae() {
      return 我草你怎么反编译我模组aaaaad;
    }
    
    public final void 你为什么要破解我的代码aaaaaf(int param1Int) {
      我草你怎么反编译我模组aaaaad = param1Int;
    }
    
    public final void 你为什么要破解我的代码aaaaag() {
      我草你怎么反编译我模组aaaaab = false;
      我草你怎么反编译我模组aaaaac = 5;
    }
  }
  
  public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx {
    @NotNull
    public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx 我草你怎么反编译我模组aaaaaa = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabx();
    
    private static boolean 我草你怎么反编译我模组aaaaab;
    
    private static long 我草你怎么反编译我模组aaaaac;
    
    public final boolean 你为什么要破解我的代码aaaaaa() {
      return 我草你怎么反编译我模组aaaaab;
    }
    
    public final void 你为什么要破解我的代码aaaaab(boolean param1Boolean) {
      我草你怎么反编译我模组aaaaab = param1Boolean;
    }
    
    public final long 你为什么要破解我的代码aaaaac() {
      return 我草你怎么反编译我模组aaaaac;
    }
    
    public final void 你为什么要破解我的代码aaaaad(long param1Long) {
      我草你怎么反编译我模组aaaaac = param1Long;
    }
    
    public final int 你为什么要破解我的代码aaaaae() {
      return (我草你怎么反编译我模组aaaaab && System.currentTimeMillis() < 我草你怎么反编译我模组aaaaac) ? 2 : 1;
    }
    
    public final void 你为什么要破解我的代码aaaaaf(int param1Int) {
      long l = System.currentTimeMillis();
      我草你怎么反编译我模组aaaaab = true;
      if (l < 我草你怎么反编译我模组aaaaac) {
        long l1 = (我草你怎么反编译我模组aaaaac - l) / 1000L;
        我草你怎么反编译我模组aaaaac = l + (l1 + param1Int) * 1000L;
      } else {
        我草你怎么反编译我模组aaaaac = l + param1Int * 1000L;
      } 
    }
    
    public final boolean 你为什么要破解我的代码aaaaag() {
      if (我草你怎么反编译我模组aaaaab && System.currentTimeMillis() >= 我草你怎么反编译我模组aaaaac)
        我草你怎么反编译我模组aaaaab = false; 
      return 我草你怎么反编译我模组aaaaab;
    }
    
    public final int 你为什么要破解我的代码aaaaah() {
      return !你为什么要破解我的代码aaaaag() ? 0 : (int)((我草你怎么反编译我模组aaaaac - System.currentTimeMillis()) / 1000L);
    }
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak {
    @NotNull
    public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak 我草你怎么反编译我模组aaaaaa = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaak();
    
    private static int 我草你怎么反编译我模组aaaaab = 500;
    
    private static int 我草你怎么反编译我模组aaaaac = 500;
    
    private static boolean 我草你怎么反编译我模组aaaaad;
    
    private static int 我草你怎么反编译我模组aaaaae;
    
    public final int 你为什么要破解我的代码aaaaaa() {
      return 我草你怎么反编译我模组aaaaab;
    }
    
    public final void 你为什么要破解我的代码aaaaab(int param1Int) {
      我草你怎么反编译我模组aaaaab = param1Int;
    }
    
    public final int 你为什么要破解我的代码aaaaac() {
      return 我草你怎么反编译我模组aaaaac;
    }
    
    public final void 你为什么要破解我的代码aaaaad(int param1Int) {
      我草你怎么反编译我模组aaaaac = param1Int;
    }
    
    public final boolean 你为什么要破解我的代码aaaaae() {
      return 我草你怎么反编译我模组aaaaad;
    }
    
    public final void 你为什么要破解我的代码aaaaaf(boolean param1Boolean) {
      我草你怎么反编译我模组aaaaad = param1Boolean;
    }
    
    public final int 你为什么要破解我的代码aaaaag() {
      return 我草你怎么反编译我模组aaaaae;
    }
    
    public final void 你为什么要破解我的代码aaaaah(int param1Int) {
      我草你怎么反编译我模组aaaaae = param1Int;
    }
    
    public final void 你为什么要破解我的代码aaaaai() {
      我草你怎么反编译我模组aaaaab = 500;
      我草你怎么反编译我模组aaaaac = 500;
      aaaaae.你为什么要破解我的代码aaaadd(-434.0D);
      我草你怎么反编译我模组aaaaad = false;
      我草你怎么反编译我模组aaaaae = 0;
    }
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaay {
    @NotNull
    public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaay 我草你怎么反编译我模组aaaaaa = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaaay();
    
    private static int 我草你怎么反编译我模组aaaaab;
    
    public final int 你为什么要破解我的代码aaaaaa() {
      我草你怎么反编译我模组aaaaab++;
      return 我草你怎么反编译我模组aaaaab;
    }
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabb {
    @NotNull
    public static final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabb 我草你怎么反编译我模组aaaaaa;
    
    @NotNull
    private static Map<UUID, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH> 我草你怎么反编译我模组aaaaab;
    
    static Object aaaaac;
    
    @NotNull
    public final Map<UUID, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH> 你为什么要破解我的代码aaaaaa() {
      return 我草你怎么反编译我模组aaaaab;
    }
    
    public final void 你为什么要破解我的代码aaaaab(@NotNull Map<UUID, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH> param1Map) {
      Intrinsics.checkNotNullParameter(param1Map, (String)aaaaag((char)66977792));
      我草你怎么反编译我模组aaaaab = param1Map;
    }
    
    public final void 你为什么要破解我的代码aaaaac(@NotNull class_3222 param1class_3222, int param1Int) {
      Intrinsics.checkNotNullParameter(param1class_3222, (String)aaaaag((char)842792961));
      UUID uUID = param1class_3222.method_5667();
      long l = System.currentTimeMillis();
      if (我草你怎么反编译我模组aaaaab.containsKey(uUID)) {
        Intrinsics.checkNotNull(我草你怎么反编译我模组aaaaab.get(uUID));
        aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH = 我草你怎么反编译我模组aaaaab.get(uUID);
        aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH.你为什么要破解我的代码aaaaac(aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH.你为什么要破解我的代码aaaaab() + param1Int * 1000L);
      } else {
        Intrinsics.checkNotNull(uUID);
        我草你怎么反编译我模组aaaaab.put(uUID, new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH(l, l + param1Int * 1000L, param1class_3222.method_23318(), param1class_3222.method_23321(), param1class_3222.method_36454()));
      } 
    }
    
    @Nullable
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH 你为什么要破解我的代码aaaaad(@NotNull UUID param1UUID) {
      Intrinsics.checkNotNullParameter(param1UUID, (String)aaaaag((char)-634126335));
      return 我草你怎么反编译我模组aaaaab.get(param1UUID);
    }
    
    @Nullable
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH 你为什么要破解我的代码aaaaae(@NotNull UUID param1UUID) {
      Intrinsics.checkNotNullParameter(param1UUID, (String)aaaaag((char)-338034687));
      return 我草你怎么反编译我模组aaaaab.remove(param1UUID);
    }
    
    public final int 你为什么要破解我的代码aaaaaf(@NotNull UUID param1UUID) {
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH;
      Intrinsics.checkNotNullParameter(param1UUID, (String)aaaaag((char)-364707839));
      if ((aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH)我草你怎么反编译我模组aaaaab.get(param1UUID) == null) {
        (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH)我草你怎么反编译我模组aaaaab.get(param1UUID);
        return 0;
      } 
      return (int)((aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH.你为什么要破解我的代码aaaaab() - System.currentTimeMillis()) / 1000L);
    }
    
    static {
      byte[] arrayOfByte1 = "ðv\r Ù±ÿ>wè'¡\032ÏXËê±Â\002\026F\025ì6OåñZ*à¶÷\032§{HJK´\"ôt{sc=X\033e¡".getBytes("ISO_8859_1");
      byte[] arrayOfByte2 = "Ntm4ïã\007".getBytes("ISO_8859_1");
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
        arrayOfByte1[i++] = (byte)1893794868;
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
              我草你怎么反编译我模组aaaaaa = new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabb();
              我草你怎么反编译我模组aaaaab = new LinkedHashMap<>();
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
    
    public static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH {
      private final long 我草你怎么反编译我模组aaaaaa;
      
      private long 我草你怎么反编译我模组aaaaab;
      
      private final double 我草你怎么反编译我模组aaaaac;
      
      private final double 我草你怎么反编译我模组aaaaad;
      
      private final float 我草你怎么反编译我模组aaaaae;
      
      public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH(long param2Long1, long param2Long2, double param2Double1, double param2Double2, float param2Float) {
        this.我草你怎么反编译我模组aaaaaa = param2Long1;
        this.我草你怎么反编译我模组aaaaab = param2Long2;
        this.我草你怎么反编译我模组aaaaac = param2Double1;
        this.我草你怎么反编译我模组aaaaad = param2Double2;
        this.我草你怎么反编译我模组aaaaae = param2Float;
      }
      
      public final long 你为什么要破解我的代码aaaaaa() {
        return this.我草你怎么反编译我模组aaaaaa;
      }
      
      public final long 你为什么要破解我的代码aaaaab() {
        return this.我草你怎么反编译我模组aaaaab;
      }
      
      public final void 你为什么要破解我的代码aaaaac(long param2Long) {
        this.我草你怎么反编译我模组aaaaab = param2Long;
      }
      
      public final double 你为什么要破解我的代码aaaaad() {
        return this.我草你怎么反编译我模组aaaaac;
      }
      
      public final double 你为什么要破解我的代码aaaaae() {
        return this.我草你怎么反编译我模组aaaaad;
      }
      
      public final float 你为什么要破解我的代码aaaaaf() {
        return this.我草你怎么反编译我模组aaaaae;
      }
      
      public final long 你为什么要破解我的代码aaaaag() {
        return this.我草你怎么反编译我模组aaaaaa;
      }
      
      public final long 你为什么要破解我的代码aaaaah() {
        return this.我草你怎么反编译我模组aaaaab;
      }
      
      public final double 你为什么要破解我的代码aaaaai() {
        return this.我草你怎么反编译我模组aaaaac;
      }
      
      public final double 你为什么要破解我的代码aaaaaj() {
        return this.我草你怎么反编译我模组aaaaad;
      }
      
      public final float 你为什么要破解我的代码aaaaak() {
        return this.我草你怎么反编译我模组aaaaae;
      }
      
      @NotNull
      public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH 你为什么要破解我的代码aaaaal(long param2Long1, long param2Long2, double param2Double1, double param2Double2, float param2Float) {
        return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH(param2Long1, param2Long2, param2Double1, param2Double2, param2Float);
      }
      
      @NotNull
      public String toString() {
        return "WindmillEffect(startTime=" + this.我草你怎么反编译我模组aaaaaa + ", endTime=" + this.我草你怎么反编译我模组aaaaab + ", startY=" + this.我草你怎么反编译我模组aaaaac + ", startZ=" + this.我草你怎么反编译我模组aaaaad + ", startYaw=" + this.我草你怎么反编译我模组aaaaae + ")";
      }
      
      public int hashCode() {
        null = Long.hashCode(this.我草你怎么反编译我模组aaaaaa);
        null = null * 31 + Long.hashCode(this.我草你怎么反编译我模组aaaaab);
        null = null * 31 + Double.hashCode(this.我草你怎么反编译我模组aaaaac);
        null = null * 31 + Double.hashCode(this.我草你怎么反编译我模组aaaaad);
        return null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaae);
      }
      
      public boolean equals(@Nullable Object param2Object) {
        if (this == param2Object)
          return true; 
        if (!(param2Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH))
          return false; 
        aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH)param2Object;
        return (this.我草你怎么反编译我模组aaaaaa != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH1.我草你怎么反编译我模组aaaaaa) ? false : ((this.我草你怎么反编译我模组aaaaab != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH1.我草你怎么反编译我模组aaaaab) ? false : ((Double.compare(this.我草你怎么反编译我模组aaaaac, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH1.我草你怎么反编译我模组aaaaac) != 0) ? false : ((Double.compare(this.我草你怎么反编译我模组aaaaad, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH1.我草你怎么反编译我模组aaaaad) != 0) ? false : (!(Float.compare(this.我草你怎么反编译我模组aaaaae, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabH1.我草你怎么反编译我模组aaaaae) != 0)))));
      }
    }
  }
}
