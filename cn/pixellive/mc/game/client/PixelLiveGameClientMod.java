package cn.pixellive.mc.game.client;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaj;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaam;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaan;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaay;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaai;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaaa.aaaaal;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaab.aaaaad;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaab.aaaaab.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaaH;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaaap;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaaa.aaaabD;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaag.aaaaab;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaai.aaaaac;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak.aaaaaa;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak.aaaaae;
import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak.aaaaaf;
import cn.pixellive.mc.game.PixelLiveGameMod;
import java.security.MessageDigest;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_1299;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_5617;
import net.minecraft.class_8710;
import net.minecraft.class_897;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nPixelLiveGameClientMod.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PixelLiveGameClientMod.kt\ncn/pixellive/mc/game/client/PixelLiveGameClientMod\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,353:1\n1855#2,2:354\n1#3:356\n215#4,2:357\n*S KotlinDebug\n*F\n+ 1 PixelLiveGameClientMod.kt\ncn/pixellive/mc/game/client/PixelLiveGameClientMod\n*L\n112#1:354,2\n291#1:357,2\n*E\n"})
public final class PixelLiveGameClientMod implements ClientModInitializer {
  @NotNull
  public static final aaaaaa 我草你怎么反编译我模组aaaaaa;
  
  @NotNull
  private final Map<String, class_304> 我草你怎么反编译我模组aaaaab;
  
  @NotNull
  private static final String 我草你怎么反编译我模组aaaaac;
  
  static Object aaaaad;
  
  public PixelLiveGameClientMod() {
    Pair[] arrayOfPair = new Pair[6];
    arrayOfPair[0] = TuplesKt.to(aaaaau((char)-2101870592), new class_304((String)aaaaau((char)-1334378495), 66, (String)aaaaau((char)-2133590014)));
    arrayOfPair[1] = TuplesKt.to(aaaaau((char)-1686962173), new class_304((String)aaaaau((char)-1988362236), 86, (String)aaaaau((char)991363074)));
    arrayOfPair[2] = TuplesKt.to(aaaaau((char)-764870651), new class_304((String)aaaaau((char)-729284602), 75, (String)aaaaau((char)-1442447358)));
    arrayOfPair[3] = TuplesKt.to(aaaaau((char)1111031815), new class_304((String)aaaaau((char)1499660296), 90, (String)aaaaau((char)1383399426)));
    arrayOfPair[4] = TuplesKt.to(aaaaau((char)1936261129), new class_304((String)aaaaau((char)-59310070), 78, (String)aaaaau((char)1442840578)));
    arrayOfPair[5] = TuplesKt.to(aaaaau((char)-1800798197), new class_304((String)aaaaau((char)1659830284), 74, (String)aaaaau((char)-281870334)));
    this.我草你怎么反编译我模组aaaaab = MapsKt.mapOf(arrayOfPair);
  }
  
  public void onInitializeClient() {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-2026635251));
    try {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1243611122));
      你为什么要破解我的代码aaaaaa();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1019478031));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1306263536));
      你为什么要破解我的代码aaaaab();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)910295057));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-938344430));
      你为什么要破解我的代码aaaaaj();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)871104531));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-2096300012));
      你为什么要破解我的代码aaaaak();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1880096747));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1773600746));
      你为什么要破解我的代码aaaaam();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1552547863));
      ClientPlayNetworking.registerGlobalReceiver(aaaaai.我草你怎么反编译我模组aaaaad, PixelLiveGameClientMod::你为什么要破解我的代码aaaaan);
      aaaaaa.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa();
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1213136920));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaau((char)-1726152679), exception);
      throw exception;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaaa() {
    // Byte code:
    //   0: nop
    //   1: aload_0
    //   2: getfield 我草你怎么反编译我模组aaaaab : Ljava/util/Map;
    //   5: invokeinterface values : ()Ljava/util/Collection;
    //   10: checkcast java/lang/Iterable
    //   13: astore_1
    //   14: iconst_0
    //   15: istore_2
    //   16: aload_1
    //   17: invokeinterface iterator : ()Ljava/util/Iterator;
    //   22: astore_3
    //   23: aload_3
    //   24: invokeinterface hasNext : ()Z
    //   29: ifeq -> 79
    //   32: aload_3
    //   33: invokeinterface next : ()Ljava/lang/Object;
    //   38: astore #4
    //   40: aload #4
    //   42: checkcast net/minecraft/class_304
    //   45: astore #5
    //   47: iconst_0
    //   48: istore #6
    //   50: aload #5
    //   52: invokestatic registerKeyBinding : (Lnet/minecraft/class_304;)Lnet/minecraft/class_304;
    //   55: pop
    //   56: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   59: aload #5
    //   61: invokevirtual method_1431 : ()Ljava/lang/String;
    //   64: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   69: invokeinterface info : (Ljava/lang/String;)V
    //   74: nop
    //   75: nop
    //   76: goto -> 23
    //   79: nop
    //   80: goto -> 107
    //   83: astore_1
    //   84: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   87: ldc -1573584870
    //   89: i2c
    //   90: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   93: checkcast java/lang/String
    //   96: aload_1
    //   97: checkcast java/lang/Throwable
    //   100: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   105: aload_1
    //   106: athrow
    //   107: return
    // Exception table:
    //   from	to	target	type
    //   0	80	83	java/lang/Exception
  }
  
  private final void 你为什么要破解我的代码aaaaab() {
    try {
      ClientTickEvents.END_CLIENT_TICK.register(this::你为什么要破解我的代码aaaaao);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-794165221));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaau((char)296484892), exception);
      throw exception;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaac(class_310 paramclass_310) {
    try {
      Intrinsics.checkNotNull(this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)1986985984)));
      if (this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)1986985984)).method_1436()) {
        aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-2037120995));
        你为什么要破解我的代码aaaaad();
      } else {
        Intrinsics.checkNotNull(this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)752746499)));
        if (this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)752746499)).method_1436()) {
          aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)973930526));
          你为什么要破解我的代码aaaaae(paramclass_310);
        } else {
          Intrinsics.checkNotNull(this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)907608069)));
          if (this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)907608069)).method_1436()) {
            aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1966669855));
            你为什么要破解我的代码aaaaaf(paramclass_310);
          } else {
            Intrinsics.checkNotNull(this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)80674825)));
            if (this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)80674825)).method_1436()) {
              aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-913113056));
              你为什么要破解我的代码aaaaag(paramclass_310);
            } else {
              Intrinsics.checkNotNull(this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)792526855)));
              if (this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)792526855)).method_1436()) {
                aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-237305823));
                你为什么要破解我的代码aaaaah();
              } else {
                Intrinsics.checkNotNull(this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)190185483)));
                if (this.我草你怎么反编译我模组aaaaab.get(aaaaau((char)190185483)).method_1436()) {
                  aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1913257950));
                  你为什么要破解我的代码aaaaai(paramclass_310);
                } 
              } 
            } 
          } 
        } 
      } 
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaau((char)83296291), exception);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaad() {
    // Byte code:
    //   0: getstatic cn/pixellive/mc/game/PixelLiveGameMod.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaf : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaac/aaaabG;
    //   6: dup
    //   7: ifnull -> 16
    //   10: invokevirtual 你为什么要破解我的代码aaaaae : ()Ljava/lang/String;
    //   13: goto -> 18
    //   16: pop
    //   17: aconst_null
    //   18: astore_1
    //   19: aload_1
    //   20: ifnull -> 144
    //   23: aload_1
    //   24: invokevirtual hashCode : ()I
    //   27: lookupswitch default -> 144, 80702676 -> 80, 630370341 -> 60, 657401329 -> 100
    //   60: aload_1
    //   61: ldc_w 2053898276
    //   64: i2c
    //   65: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   68: checkcast java/lang/String
    //   71: invokevirtual equals : (Ljava/lang/Object;)Z
    //   74: ifne -> 117
    //   77: goto -> 144
    //   80: aload_1
    //   81: ldc_w -293928923
    //   84: i2c
    //   85: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   88: checkcast java/lang/String
    //   91: invokevirtual equals : (Ljava/lang/Object;)Z
    //   94: ifne -> 117
    //   97: goto -> 144
    //   100: aload_1
    //   101: ldc_w 395509798
    //   104: i2c
    //   105: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   108: checkcast java/lang/String
    //   111: invokevirtual equals : (Ljava/lang/Object;)Z
    //   114: ifeq -> 144
    //   117: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   120: ldc_w -1001127897
    //   123: i2c
    //   124: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   127: checkcast java/lang/String
    //   130: invokeinterface info : (Ljava/lang/String;)V
    //   135: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaae.我草你怎么反编译我模组aaaaag : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab/aaaaaa/aaaaae;
    //   138: checkcast net/minecraft/class_8710
    //   141: invokestatic send : (Lnet/minecraft/class_8710;)V
    //   144: return
  }
  
  private final void 你为什么要破解我的代码aaaaae(class_310 paramclass_310) {
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf();
    aaaaaa.你为什么要破解我的代码aaaaaa().info("打开设置界面: " + ((PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) ? PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae() : null));
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf();
    String str = (PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) ? PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae() : null;
    if (str != null)
      switch (str.hashCode()) {
        case 916506880:
          if (!str.equals(aaaaau((char)-451674072)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaab());
          break;
        case 1912904869:
          if (!str.equals(aaaaau((char)-1828388823)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaam());
          break;
        case 1121163920:
          if (!str.equals(aaaaau((char)1974468650)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaac());
          break;
        case 630370341:
          if (!str.equals(aaaaau((char)988610596)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaaj());
          break;
        case 80702676:
          if (!str.equals(aaaaau((char)1968242725)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaad());
          break;
        case 1902430713:
          if (!str.equals(aaaaau((char)36438059)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaag());
          break;
        case 657401329:
          if (!str.equals(aaaaau((char)1603731494)))
            break; 
          paramclass_310.method_1507((class_437)new aaaaak());
          break;
      }  
  }
  
  private final void 你为什么要破解我的代码aaaaaf(class_310 paramclass_310) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1273626580));
    paramclass_310.method_1507((class_437)new aaaaay(null, 1, null));
  }
  
  private final void 你为什么要破解我的代码aaaaag(class_310 paramclass_310) {
    if (PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-379584467));
      if (PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null && PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae() != null) {
        String str = PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae();
        boolean bool = false;
        aaaaap.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad(str);
      } else {
        PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae();
      } 
      paramclass_310.method_1507((class_437)new aaaaai((String)aaaaau((char)187957294), 1, aaaaab.Companion.你为什么要破解我的代码aaaaaa().你为什么要破解我的代码aaaaaa(), false, 8, null));
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaah() {
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf();
    if (Intrinsics.areEqual((PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) ? PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae() : null, aaaaau((char)-433848283))) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)385220655));
      ClientPlayNetworking.send((class_8710)aaaaal.我草你怎么反编译我模组aaaaaw);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaai(class_310 paramclass_310) {
    if (PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf() != null) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1977483312));
      String str = PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae();
      boolean bool = false;
      aaaabD.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac(str);
      paramclass_310.method_1507((class_437)new aaaaan());
      PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaf().你为什么要破解我的代码aaaaae();
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaaj() {
    try {
      ClientPlayNetworking.registerGlobalReceiver(aaaaad.我草你怎么反编译我模组aaaaad, PixelLiveGameClientMod::你为什么要破解我的代码aaaaap);
      ClientPlayNetworking.registerGlobalReceiver(aaaaae.我草你怎么反编译我模组aaaaad, PixelLiveGameClientMod::你为什么要破解我的代码aaaaaq);
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaau((char)621543473), exception);
      throw exception;
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaak() {
    // Byte code:
    //   0: nop
    //   1: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   4: ldc_w 430702642
    //   7: i2c
    //   8: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   11: checkcast java/lang/String
    //   14: invokeinterface info : (Ljava/lang/String;)V
    //   19: aload_0
    //   20: invokespecial 你为什么要破解我的代码aaaaal : ()Lkotlin/Pair;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual component1 : ()Ljava/lang/Object;
    //   28: checkcast java/lang/String
    //   31: astore_2
    //   32: aload_1
    //   33: invokevirtual component2 : ()Ljava/lang/Object;
    //   36: checkcast java/lang/String
    //   39: astore_3
    //   40: aload_3
    //   41: aload_2
    //   42: iconst_1
    //   43: ldc_w 535363635
    //   46: i2c
    //   47: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   50: checkcast java/lang/String
    //   53: invokestatic 你为什么要破解我的代码aaaaad : (Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaae/aaaaaa/aaaaaf;
    //   56: dup
    //   57: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   60: getfield 我草你怎么反编译我模组aaaaaa : Ljava/util/HashMap;
    //   63: astore #4
    //   65: aload #4
    //   67: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   70: aload #4
    //   72: checkcast java/util/Map
    //   75: astore #5
    //   77: iconst_0
    //   78: istore #6
    //   80: aload #5
    //   82: invokeinterface entrySet : ()Ljava/util/Set;
    //   87: invokeinterface iterator : ()Ljava/util/Iterator;
    //   92: astore #7
    //   94: aload #7
    //   96: invokeinterface hasNext : ()Z
    //   101: ifeq -> 167
    //   104: aload #7
    //   106: invokeinterface next : ()Ljava/lang/Object;
    //   111: checkcast java/util/Map$Entry
    //   114: astore #8
    //   116: aload #8
    //   118: astore #9
    //   120: iconst_0
    //   121: istore #10
    //   123: aload #9
    //   125: invokeinterface getKey : ()Ljava/lang/Object;
    //   130: checkcast java/lang/String
    //   133: astore #11
    //   135: aload #9
    //   137: invokeinterface getValue : ()Ljava/lang/Object;
    //   142: checkcast java/lang/String
    //   145: astore #12
    //   147: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   150: aload #11
    //   152: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   157: invokeinterface info : (Ljava/lang/String;)V
    //   162: nop
    //   163: nop
    //   164: goto -> 94
    //   167: nop
    //   168: new java/util/ArrayList
    //   171: dup
    //   172: aload #4
    //   174: invokevirtual keySet : ()Ljava/util/Set;
    //   177: dup
    //   178: ifnull -> 187
    //   181: checkcast java/util/Collection
    //   184: goto -> 194
    //   187: pop
    //   188: invokestatic emptySet : ()Ljava/util/Set;
    //   191: checkcast java/util/Collection
    //   194: invokespecial <init> : (Ljava/util/Collection;)V
    //   197: checkcast java/util/List
    //   200: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaae/aaaaaa/aaaaaj.我草你怎么反编译我模组aaaaaa : Ljava/util/List;
    //   203: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   206: ldc_w 2124414997
    //   209: i2c
    //   210: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   213: checkcast java/lang/String
    //   216: invokeinterface info : (Ljava/lang/String;)V
    //   221: goto -> 249
    //   224: astore_1
    //   225: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   228: ldc_w 894042164
    //   231: i2c
    //   232: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   235: checkcast java/lang/String
    //   238: aload_1
    //   239: checkcast java/lang/Throwable
    //   242: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   247: aload_1
    //   248: athrow
    //   249: return
    // Exception table:
    //   from	to	target	type
    //   0	221	224	java/lang/Exception
  }
  
  private final Pair<String, String> 你为什么要破解我的代码aaaaal() {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab.Companion : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa;
    //   3: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaab;
    //   6: invokevirtual 你为什么要破解我的代码aaaaaa : ()Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaag/aaaaaa/aaaabF;
    //   9: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab.我草你怎么反编译我模组aaaaaa : [I
    //   12: swap
    //   13: invokevirtual ordinal : ()I
    //   16: iaload
    //   17: tableswitch default -> 83, 1 -> 44, 2 -> 57, 3 -> 70
    //   44: ldc_w -1828650955
    //   47: i2c
    //   48: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   51: checkcast java/lang/String
    //   54: goto -> 91
    //   57: ldc_w 773914678
    //   60: i2c
    //   61: invokestatic aaaaau : (C)Ljava/lang/Object;
    //   64: checkcast java/lang/String
    //   67: goto -> 91
    //   70: ldc_w 363724855
    //   73: i2c
    //   74: invokestatic aaaaau : (C)Ljava/lang/Object;
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
    //   101: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaab.我草你怎么反编译我模组aaaaaa : [I
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
  
  private final void 你为什么要破解我的代码aaaaam() {
    try {
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)-1349648328));
      class_1299 class_1299 = aaaaac.我草你怎么反编译我模组aaaaad;
      boolean bool = false;
      EntityRendererRegistry.register(class_1299, PixelLiveGameClientMod::你为什么要破解我的代码aaaaar);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1123483705));
      class_1299 = aaaaac.我草你怎么反编译我模组aaaaae;
      bool = false;
      EntityRendererRegistry.register(class_1299, PixelLiveGameClientMod::你为什么要破解我的代码aaaaas);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1774977082));
      class_1299 = aaaaac.我草你怎么反编译我模组aaaaaf;
      bool = false;
      EntityRendererRegistry.register(class_1299, PixelLiveGameClientMod::你为什么要破解我的代码aaaaat);
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)884080699));
      aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)18874428));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaau((char)780206141), exception);
      throw exception;
    } 
  }
  
  private static final void 你为什么要破解我的代码aaaaan(aaaaai paramaaaaai, ClientPlayNetworking.Context paramContext) {
    aaaaae.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaaa(paramaaaaai.你为什么要破解我的代码aaaaaK());
  }
  
  private static final void 你为什么要破解我的代码aaaaao(PixelLiveGameClientMod paramPixelLiveGameClientMod, class_310 paramclass_310) {
    Intrinsics.checkNotNullParameter(paramPixelLiveGameClientMod, (String)aaaaau((char)297336894));
    Intrinsics.checkNotNull(paramclass_310);
    paramPixelLiveGameClientMod.你为什么要破解我的代码aaaaac(paramclass_310);
  }
  
  private static final void 你为什么要破解我的代码aaaaap(aaaaad paramaaaaad, ClientPlayNetworking.Context paramContext) {
    String str1 = paramaaaaad.你为什么要破解我的代码aaaaad();
    aaaaaa.你为什么要破解我的代码aaaaaa().info("收到游戏阶段更新: " + str1);
    String str2 = str1;
    switch (str2.hashCode()) {
      case 916506880:
        if (str2.equals(aaaaau((char)1392312360)));
      case 1912904869:
        if (str2.equals(aaaaau((char)1696923689)));
      case 1121163920:
        if (str2.equals(aaaaau((char)1765081130)));
      case 630370341:
        if (str2.equals(aaaaau((char)-898564060)));
      case 80702676:
        if (str2.equals(aaaaau((char)-296550363)));
      case 1902430713:
        if (str2.equals(aaaaau((char)1188429867)));
      case 657401329:
        if (str2.equals(aaaaau((char)-1215561690)));
      default:
        break;
    } 
    PixelLiveGameMod.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaag(null);
  }
  
  private static final void 你为什么要破解我的代码aaaaaq(aaaaae paramaaaaae, ClientPlayNetworking.Context paramContext) {
    aaaaaa.你为什么要破解我的代码aaaaaa().info((String)aaaaau((char)1782448191));
    String str = paramaaaaae.你为什么要破解我的代码aaaaad();
    Intrinsics.checkNotNullExpressionValue(aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac().fromJson(str, aaaaaH.class), (String)aaaaau((char)-997785536));
    aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaab((aaaaaH)aaaaaH.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaac().fromJson(str, aaaaaH.class));
  }
  
  private static final class_897 你为什么要破解我的代码aaaaar(class_5617.class_5618 paramclass_5618) {
    Intrinsics.checkNotNull(paramclass_5618);
    return (class_897)new aaaaaf(paramclass_5618);
  }
  
  private static final class_897 你为什么要破解我的代码aaaaas(class_5617.class_5618 paramclass_5618) {
    Intrinsics.checkNotNull(paramclass_5618);
    return (class_897)new aaaaaf(paramclass_5618);
  }
  
  private static final class_897 你为什么要破解我的代码aaaaat(class_5617.class_5618 paramclass_5618) {
    Intrinsics.checkNotNull(paramclass_5618);
    return (class_897)new aaaaaf(paramclass_5618);
  }
  
  static {
    byte[] arrayOfByte1 = "§y=º\022ª\005Å?ýRñ½+ËÖ\005P7z£Ð%\006`-û§««s/ê&\037Âõ¦%\026*ÿõø¥¿7\017õáaÇûâö±½.½i\032³ç\032\006\036/\030\030A!\005jØù\rO¸,\033©æ\033õÇSÌ\003yZ\003\020\027ÿnàzÇÂå\013\0320+ÊéÍÝVýfé×Éîïè¿\025 úÃ±w)þ³HP°aC¢\003sÍ1½³º1>¬ðªZ\003Èx\006\fÊWìxÜóÑïÿJ\003\024¦Z?Ý\006Ò\0218üor¤ñó£®I>#a\023\016à1\0275\nDó=¹'ï>äÝÑogSó5\001ñ\001tsPSËÞW-¨§Æ\034õy§]?eDé¥­³ñgaÀó0ì\022-ù<¿)!{FPËù¯äYñæ_\031nF@5±¹øôP¾xÉu³\036+Jñæ0¼ßèR¾.?*6$7u\022¾ÕÙ÷Tmôý\013j 1fòE)|õÃµûó§\000=G7Îb§_D¥\004tgÅÑQ'\0359^ºÐ¡Ê¡NC\025ØòuÛÖ¦\024\030á\036qãçhû^>\b)ÁpÓ\030P\f\0252ü\007«Ï¼­WÑD«Ø7\001\r¹m\"Óäâ\t\022l\017!9oz}\016<nJdÈ\002s\007\0323Ö\022J\033®\006îktóJÆz\r\003~.h°\\~O&ßIs1Ñ­.\f\003bÍýw$MöÁºø\005'æô÷É\022tZÍ\\ÌÜ\ré0Y)\r\nÓ-\027õheÔõ\r­\016h,\013ë&w`ÂÇ\023<ïV.HdiÜÇ¬\016ß<\034 üÄ®\036WÛ'ªáý£Ñ\021®Ôã'®\037ÞS]@\034Jß¨ùÍ\006ê-íSN¢;aØP@[\000M\002¦!´s-(\0363eÉ$»¨êâÐF#äCÐÝ3\004ÍM\035syä÷E>ÛnX%y\rÛQ­ºÁ®«\005\000rdQ><\003ip\027ñ'ÉNù¯C#ÝR\b~_!\005\036&bLLÌ<×)æ`\tÔÔ\006½PO§«(\n,¹ÂýDÂ®¹{üÇÐòâZP(SêÑú\037\n­`ïI¦=nCfJc\004ÌÇ\n\032\001µÒ¸ëWHÍmÇz\003Ò`@acá°\003úå`ÖÃ\0066t\007þÏÓÈàBÁ²ÈñQR¸¸¯hjMx¢¨6JäYI<Q\030¡C\tÐ¬£±\024AN\023gÕº³ñÅ;%÷TÕD¬âò\007Ð\032±@æS\026Ó¬\001Í¸\001qÀC_\rn|\016³KvÍýÚÅ×¢\r\004/#ÿ³âSºÕÓM\\4¶Æ_jW§x%Y\t£\002½#\016V{.Y\020Ó¼\034aLûù\tý Q^áâ'qj§\025è²\037ºö#òõ{©ï\003ft*\030î\027:ê_ûñ\031Á^¥ ©j±Kc\001îÆ8¥|w\027i¬|\\µE¿§ò¹\021°û¯hô^ô½çµØ à©N/uS.y ­ë(PwùÆ5÷\033·)GN©Õ)«õ¬Fv*\016Âî9\026´¨hþ\"ÑvØÑí\030®µ¢`+¡\017Ä|£êÂ±O\000U1\t\017»¯ÊJTë\007ÄE¥Ø!v8¦Y*À\004Ú\023ÔIÕ(DäØ%3\017}C÷¯©Ä}\000Ú\022Ø\0237¶êl{¸@ÂßU§Yüæo\0179;\017zA:hb^¹-w\033JG£/\016+\båõªÀ)qdÑÇ\022Ï,ê$:iQÖ&S£ø9\035h\021~ÏoúWab[ru:tFuÒñ\nÊ¢ \023!\nÊ]ßZÄÔnHÑ'qq3ñ^P\026\022ÿ\034¸¦\002\004Ò\030q1A[Yc¡\021\037\016Ð¦;\034)ÀNj¿h|\032]ãeÂbÅH;«¯]\016ÏN%F³Îu¹ã\"!*Ôgèöï\013Z\006å/¸<íIjH!\023\006\001ð<{Y0DG²Ö´]Zw3Î ßåûd·+([Æ©ñÝ0Áÿ«À\0012Â£±&»\006Êi%7}\036OX&JÉÒMWv8\03444Z]¦ÁT-\022_ß}r$ÎmsëT\0072y\fS'vef#°Cx¬¤é!¹\027¸T\002d.Í:\003¨uQÐi\027@åâCî½D9; V\r¾\006rá1>\001É=ø/TÜ<Ü\r(\004n¦ØICY%Y.p\013eo7 \023ê®QþõHK!eØa_¤\032bDè² KRW`b«Û\n¶qÌ\000R}«&tàDÕ1Åä÷#AÈlþôaÝ®9ZÎ'/äY·m\006ÅSFSõÈ½È¥}¯º9a¸²Ý\0349¹ï$¢qsËpz>ZÀvQ\020ëÌ®¬EU".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "\n*\016\002óduñ".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)1895121813;
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
              Object[] arrayOfObject = new Object[65];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaac = (String)aaaaau((char)1285750786);
            我草你怎么反编译我模组aaaaaa = new aaaaaa(null);
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaau(char paramChar) {
    return ((Object[])aaaaad)[paramChar];
  }
  
  public static final class PixelLiveGameClientMod {}
}
