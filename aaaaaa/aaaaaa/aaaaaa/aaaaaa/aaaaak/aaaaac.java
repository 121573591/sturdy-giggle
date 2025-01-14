package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaak;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import com.mojang.blaze3d.systems.RenderSystem;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nLeaderboardBannerRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LeaderboardBannerRenderer.kt\ncn/pixellive/mc/game/render/LeaderboardBannerRenderer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,442:1\n1855#2,2:443\n1855#2,2:445\n215#3,2:447\n*S KotlinDebug\n*F\n+ 1 LeaderboardBannerRenderer.kt\ncn/pixellive/mc/game/render/LeaderboardBannerRenderer\n*L\n189#1:443,2\n281#1:445,2\n342#1:447,2\n*E\n"})
public final class aaaaac {
  @NotNull
  public static final aaaaac 我草你怎么反编译我模组aaaaaa;
  
  private static final float 我草你怎么反编译我模组aaaaab;
  
  private static final float 我草你怎么反编译我模组aaaaac;
  
  private static final float 我草你怎么反编译我模组aaaaad;
  
  private static final float 我草你怎么反编译我模组aaaaae;
  
  private static final float 我草你怎么反编译我模组aaaaaf;
  
  private static final float 我草你怎么反编译我模组aaaaag;
  
  private static final float 我草你怎么反编译我模组aaaaah;
  
  private static final float 我草你怎么反编译我模组aaaaai;
  
  private static final float 我草你怎么反编译我模组aaaaaj;
  
  private static final float 我草你怎么反编译我模组aaaaak;
  
  private static final float 我草你怎么反编译我模组aaaaal;
  
  private static final float 我草你怎么反编译我模组aaaaam;
  
  private static final float 我草你怎么反编译我模组aaaaan;
  
  private static final float 我草你怎么反编译我模组aaaaao;
  
  private static final float 我草你怎么反编译我模组aaaaap;
  
  private static final float 我草你怎么反编译我模组aaaaaq;
  
  private static final float 我草你怎么反编译我模组aaaaar;
  
  private static final float 我草你怎么反编译我模组aaaaas;
  
  private static final float 我草你怎么反编译我模组aaaaat;
  
  private static final float 我草你怎么反编译我模组aaaaau;
  
  @NotNull
  private static final Map<String, class_2960> 我草你怎么反编译我模组aaaaav;
  
  @NotNull
  private static final Map<String, CompletableFuture<?>> 我草你怎么反编译我模组aaaaaw;
  
  @Nullable
  private static class_2960 我草你怎么反编译我模组aaaaax;
  
  @Nullable
  private static class_2960 我草你怎么反编译我模组aaaaay;
  
  @Nullable
  private static class_2960 我草你怎么反编译我模组aaaaaz;
  
  private static boolean 我草你怎么反编译我模组aaaaaA;
  
  @NotNull
  private static List<String> 我草你怎么反编译我模组aaaaaB;
  
  static Object aaaaaC;
  
  public final void 你为什么要破解我的代码aaaaaa() {
    try {
      我草你怎么反编译我模组aaaaax = class_2960.method_60655((String)aaaaat((char)-1433337856), (String)aaaaat((char)-1509294079));
      我草你怎么反编译我模组aaaaay = class_2960.method_60655((String)aaaaat((char)2046164992), (String)aaaaat((char)-407044094));
      我草你怎么反编译我模组aaaaaz = class_2960.method_60655((String)aaaaat((char)26279936), (String)aaaaat((char)-1354170365));
      我草你怎么反编译我模组aaaaaA = true;
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaat((char)1293746180), exception);
    } 
  }
  
  public final void 你为什么要破解我的代码aaaaab(@NotNull List<String> paramList) {
    Intrinsics.checkNotNullParameter(paramList, (String)aaaaat((char)73400325));
    我草你怎么反编译我模组aaaaaB.clear();
    我草你怎么反编译我模组aaaaaB.addAll(paramList);
  }
  
  public final void 你为什么要破解我的代码aaaaac(@NotNull class_332 paramclass_332) {
    Intrinsics.checkNotNullParameter(paramclass_332, (String)aaaaat((char)-1145438202));
    if (!我草你怎么反编译我模组aaaaaA)
      return; 
    class_310 class_310 = class_310.method_1551();
    int i = class_310.method_22683().method_4486();
    int j = class_310.method_22683().method_4502();
    int k = (int)(j * 0.32F);
    int m = (int)(k * 2.0F);
    int n = (i - m) / 2;
    boolean bool1 = false;
    RenderSystem.enableBlend();
    RenderSystem.defaultBlendFunc();
    class_2960 class_29601 = 我草你怎么反编译我模组aaaaax;
    boolean bool2 = false;
    paramclass_332.method_25290(class_29601, n, bool1, 0.0F, 0.0F, m, k, m, k);
    if (!我草你怎么反编译我模组aaaaaB.isEmpty()) {
      你为什么要破解我的代码aaaaaf(paramclass_332, n, bool1, m, k);
      class_29601 = 我草你怎么反编译我模组aaaaay;
      bool2 = false;
      paramclass_332.method_25290(class_29601, n, bool1, 0.0F, 0.0F, m, k, m, k);
      你为什么要破解我的代码aaaaai(paramclass_332, n, bool1, m, k);
    } 
    RenderSystem.disableBlend();
  }
  
  private final void 你为什么要破解我的代码aaaaad(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    class_2960 class_29601 = 我草你怎么反编译我模组aaaaax;
    boolean bool = false;
    paramclass_332.method_25290(class_29601, paramInt1, paramInt2, 0.0F, 0.0F, paramInt3, paramInt4, paramInt3, paramInt4);
  }
  
  private final void 你为什么要破解我的代码aaaaae(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    class_2960 class_29601 = 我草你怎么反编译我模组aaaaay;
    boolean bool = false;
    paramclass_332.method_25290(class_29601, paramInt1, paramInt2, 0.0F, 0.0F, paramInt3, paramInt4, paramInt3, paramInt4);
  }
  
  private final void 你为什么要破解我的代码aaaaaf(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: iconst_3
    //   1: anewarray aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad
    //   4: astore #6
    //   6: aload #6
    //   8: iconst_0
    //   9: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad
    //   12: dup
    //   13: iconst_1
    //   14: ldc 0.16
    //   16: ldc 0.2
    //   18: ldc 0.35
    //   20: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   23: iconst_1
    //   24: invokestatic getOrNull : (Ljava/util/List;I)Ljava/lang/Object;
    //   27: checkcast java/lang/String
    //   30: invokespecial <init> : (IFFFLjava/lang/String;)V
    //   33: aastore
    //   34: aload #6
    //   36: iconst_1
    //   37: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad
    //   40: dup
    //   41: iconst_0
    //   42: ldc 0.51
    //   44: ldc 0.2
    //   46: ldc 0.4
    //   48: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   51: iconst_0
    //   52: invokestatic getOrNull : (Ljava/util/List;I)Ljava/lang/Object;
    //   55: checkcast java/lang/String
    //   58: invokespecial <init> : (IFFFLjava/lang/String;)V
    //   61: aastore
    //   62: aload #6
    //   64: iconst_2
    //   65: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad
    //   68: dup
    //   69: iconst_2
    //   70: ldc 0.85
    //   72: ldc 0.2
    //   74: ldc 0.3
    //   76: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   79: iconst_2
    //   80: invokestatic getOrNull : (Ljava/util/List;I)Ljava/lang/Object;
    //   83: checkcast java/lang/String
    //   86: invokespecial <init> : (IFFFLjava/lang/String;)V
    //   89: aastore
    //   90: aload #6
    //   92: invokestatic listOf : ([Ljava/lang/Object;)Ljava/util/List;
    //   95: astore #7
    //   97: aload #7
    //   99: checkcast java/lang/Iterable
    //   102: astore #6
    //   104: iconst_0
    //   105: istore #8
    //   107: aload #6
    //   109: invokeinterface iterator : ()Ljava/util/Iterator;
    //   114: astore #9
    //   116: aload #9
    //   118: invokeinterface hasNext : ()Z
    //   123: ifeq -> 230
    //   126: aload #9
    //   128: invokeinterface next : ()Ljava/lang/Object;
    //   133: astore #10
    //   135: aload #10
    //   137: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad
    //   140: astore #11
    //   142: iconst_0
    //   143: istore #12
    //   145: aload #11
    //   147: invokevirtual 你为什么要破解我的代码aaaaae : ()Ljava/lang/String;
    //   150: dup
    //   151: ifnull -> 223
    //   154: astore #13
    //   156: iconst_0
    //   157: istore #14
    //   159: iload #5
    //   161: i2f
    //   162: aload #11
    //   164: invokevirtual 你为什么要破解我的代码aaaaad : ()F
    //   167: fmul
    //   168: f2i
    //   169: istore #15
    //   171: iload_2
    //   172: iload #4
    //   174: i2f
    //   175: aload #11
    //   177: invokevirtual 你为什么要破解我的代码aaaaab : ()F
    //   180: fmul
    //   181: f2i
    //   182: iadd
    //   183: iload #15
    //   185: iconst_2
    //   186: idiv
    //   187: isub
    //   188: istore #16
    //   190: iload_3
    //   191: iload #5
    //   193: i2f
    //   194: aload #11
    //   196: invokevirtual 你为什么要破解我的代码aaaaac : ()F
    //   199: fmul
    //   200: f2i
    //   201: iadd
    //   202: istore #17
    //   204: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac;
    //   207: aload_1
    //   208: aload #13
    //   210: iload #16
    //   212: iload #17
    //   214: iload #15
    //   216: invokespecial 你为什么要破解我的代码aaaaag : (Lnet/minecraft/class_332;Ljava/lang/String;III)V
    //   219: nop
    //   220: goto -> 225
    //   223: pop
    //   224: nop
    //   225: nop
    //   226: nop
    //   227: goto -> 116
    //   230: nop
    //   231: return
  }
  
  private final void 你为什么要破解我的代码aaaaag(class_332 paramclass_332, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    String str1 = StringsKt.removePrefix(paramString, (String)aaaaat((char)-547946489));
    String str2 = (String)aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaaad.我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaad().get(str1);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("正在渲染玩家 " + paramString + " 的头像, URL: " + str2);
    if (str2 != null) {
      if (!我草你怎么反编译我模组aaaaav.containsKey(paramString) && !我草你怎么反编译我模组aaaaaw.containsKey(paramString))
        你为什么要破解我的代码aaaaao(paramString, str2); 
      class_2960 class_29601 = 我草你怎么反编译我模组aaaaav.get(paramString);
      boolean bool = false;
      paramclass_332.method_25290(class_29601, paramInt1, paramInt2, 0.0F, 0.0F, paramInt3, paramInt3, paramInt3, paramInt3);
      (class_2960)我草你怎么反编译我模组aaaaav.get(paramString);
      if ((((class_2960)我草你怎么反编译我模组aaaaav.get(paramString) != null) ? Unit.INSTANCE : null) == null) {
        aaaaac aaaaac1 = this;
        boolean bool1 = false;
        aaaaac1.你为什么要破解我的代码aaaaah(paramclass_332, paramInt1, paramInt2, paramInt3);
      } 
    } else {
      if (!我草你怎么反编译我模组aaaaav.containsKey(paramString) && !我草你怎么反编译我模组aaaaaw.containsKey(paramString))
        你为什么要破解我的代码aaaaao(paramString, (String)aaaaat((char)-955711480)); 
      class_2960 class_29601 = 我草你怎么反编译我模组aaaaav.get(paramString);
      boolean bool2 = false;
      paramclass_332.method_25290(class_29601, paramInt1, paramInt2, 0.0F, 0.0F, paramInt3, paramInt3, paramInt3, paramInt3);
      (class_2960)我草你怎么反编译我模组aaaaav.get(paramString);
      aaaaac aaaaac1 = this;
      boolean bool1 = false;
      aaaaac1.你为什么要破解我的代码aaaaah(paramclass_332, paramInt1, paramInt2, paramInt3);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaah(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3) {
    try {
      class_2960 class_29601 = 我草你怎么反编译我模组aaaaaz;
      boolean bool = false;
      paramclass_332.method_25290(class_29601, paramInt1, paramInt2, 0.0F, 0.0F, paramInt3, paramInt3, paramInt3, paramInt3);
      if (((我草你怎么反编译我模组aaaaaz != null) ? Unit.INSTANCE : null) == null) {
        aaaaac aaaaac1 = this;
        boolean bool1 = false;
        aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaat((char)-28966903));
      } 
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error((String)aaaaat((char)268173322), exception);
    } 
  }
  
  private final void 你为什么要破解我的代码aaaaai(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: invokestatic method_1551 : ()Lnet/minecraft/class_310;
    //   3: getfield field_1772 : Lnet/minecraft/class_327;
    //   6: astore #6
    //   8: iconst_3
    //   9: anewarray aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab
    //   12: astore #7
    //   14: aload #7
    //   16: iconst_0
    //   17: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab
    //   20: dup
    //   21: iconst_0
    //   22: ldc_w 0.52
    //   25: ldc_w 0.6
    //   28: ldc_w 0.9
    //   31: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   34: iconst_0
    //   35: invokestatic getOrNull : (Ljava/util/List;I)Ljava/lang/Object;
    //   38: checkcast java/lang/String
    //   41: dup
    //   42: ifnull -> 64
    //   45: ldc_w 1916731399
    //   48: i2c
    //   49: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   52: checkcast java/lang/String
    //   55: checkcast java/lang/CharSequence
    //   58: invokestatic removePrefix : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   61: goto -> 66
    //   64: pop
    //   65: aconst_null
    //   66: invokespecial <init> : (IFFFLjava/lang/String;)V
    //   69: aastore
    //   70: aload #7
    //   72: iconst_1
    //   73: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab
    //   76: dup
    //   77: iconst_1
    //   78: ldc_w 0.17
    //   81: ldc_w 0.6
    //   84: ldc_w 0.8
    //   87: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   90: iconst_1
    //   91: invokestatic getOrNull : (Ljava/util/List;I)Ljava/lang/Object;
    //   94: checkcast java/lang/String
    //   97: dup
    //   98: ifnull -> 120
    //   101: ldc_w -1498808313
    //   104: i2c
    //   105: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   108: checkcast java/lang/String
    //   111: checkcast java/lang/CharSequence
    //   114: invokestatic removePrefix : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   117: goto -> 122
    //   120: pop
    //   121: aconst_null
    //   122: invokespecial <init> : (IFFFLjava/lang/String;)V
    //   125: aastore
    //   126: aload #7
    //   128: iconst_2
    //   129: new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab
    //   132: dup
    //   133: iconst_2
    //   134: ldc 0.85
    //   136: ldc_w 0.6
    //   139: ldc_w 0.7
    //   142: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   145: iconst_2
    //   146: invokestatic getOrNull : (Ljava/util/List;I)Ljava/lang/Object;
    //   149: checkcast java/lang/String
    //   152: dup
    //   153: ifnull -> 175
    //   156: ldc_w -1929510905
    //   159: i2c
    //   160: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   163: checkcast java/lang/String
    //   166: checkcast java/lang/CharSequence
    //   169: invokestatic removePrefix : (Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   172: goto -> 177
    //   175: pop
    //   176: aconst_null
    //   177: invokespecial <init> : (IFFFLjava/lang/String;)V
    //   180: aastore
    //   181: aload #7
    //   183: invokestatic listOf : ([Ljava/lang/Object;)Ljava/util/List;
    //   186: astore #8
    //   188: aload #8
    //   190: checkcast java/lang/Iterable
    //   193: astore #7
    //   195: iconst_0
    //   196: istore #9
    //   198: aload #7
    //   200: invokeinterface iterator : ()Ljava/util/Iterator;
    //   205: astore #10
    //   207: aload #10
    //   209: invokeinterface hasNext : ()Z
    //   214: ifeq -> 331
    //   217: aload #10
    //   219: invokeinterface next : ()Ljava/lang/Object;
    //   224: astore #11
    //   226: aload #11
    //   228: checkcast aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab
    //   231: astore #12
    //   233: iconst_0
    //   234: istore #13
    //   236: aload #12
    //   238: invokevirtual 你为什么要破解我的代码aaaaae : ()Ljava/lang/String;
    //   241: dup
    //   242: ifnull -> 324
    //   245: astore #14
    //   247: iconst_0
    //   248: istore #15
    //   250: iload_2
    //   251: iload #4
    //   253: i2f
    //   254: aload #12
    //   256: invokevirtual 你为什么要破解我的代码aaaaab : ()F
    //   259: fmul
    //   260: f2i
    //   261: iadd
    //   262: istore #16
    //   264: iload_3
    //   265: iload #5
    //   267: i2f
    //   268: aload #12
    //   270: invokevirtual 你为什么要破解我的代码aaaaac : ()F
    //   273: fmul
    //   274: f2i
    //   275: iadd
    //   276: istore #17
    //   278: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaa : Laaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac;
    //   281: aload_1
    //   282: aload #6
    //   284: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   287: aload #6
    //   289: aload #14
    //   291: invokestatic method_30163 : (Ljava/lang/String;)Lnet/minecraft/class_2561;
    //   294: dup
    //   295: ldc_w -1615986677
    //   298: i2c
    //   299: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   302: checkcast java/lang/String
    //   305: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   308: iload #16
    //   310: iload #17
    //   312: aload #12
    //   314: invokevirtual 你为什么要破解我的代码aaaaad : ()F
    //   317: invokespecial 你为什么要破解我的代码aaaaaj : (Lnet/minecraft/class_332;Lnet/minecraft/class_327;Lnet/minecraft/class_2561;IIF)V
    //   320: nop
    //   321: goto -> 326
    //   324: pop
    //   325: nop
    //   326: nop
    //   327: nop
    //   328: goto -> 207
    //   331: nop
    //   332: return
  }
  
  private final void 你为什么要破解我的代码aaaaaj(class_332 paramclass_332, class_327 paramclass_327, class_2561 paramclass_2561, int paramInt1, int paramInt2, float paramFloat) {
    paramclass_332.method_51448().method_22903();
    int i = paramclass_327.method_1727(paramclass_2561.getString());
    float f1 = (paramInt1 - i * paramFloat / 2) / paramFloat;
    float f2 = paramInt2 / paramFloat;
    paramclass_332.method_51448().method_22905(paramFloat, paramFloat, 1.0F);
    paramclass_332.method_27535(paramclass_327, paramclass_2561, (int)f1, (int)f2, 16766720);
    paramclass_332.method_51448().method_22909();
  }
  
  public final void 你为什么要破解我的代码aaaaak() {
    我草你怎么反编译我模组aaaaaA = true;
  }
  
  public final void 你为什么要破解我的代码aaaaal() {
    我草你怎么反编译我模组aaaaaA = false;
  }
  
  public final void 你为什么要破解我的代码aaaaam() {
    // Byte code:
    //   0: iconst_0
    //   1: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaA : Z
    //   4: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaav : Ljava/util/Map;
    //   7: astore_1
    //   8: iconst_0
    //   9: istore_2
    //   10: aload_1
    //   11: invokeinterface entrySet : ()Ljava/util/Set;
    //   16: invokeinterface iterator : ()Ljava/util/Iterator;
    //   21: astore_3
    //   22: aload_3
    //   23: invokeinterface hasNext : ()Z
    //   28: ifeq -> 77
    //   31: aload_3
    //   32: invokeinterface next : ()Ljava/lang/Object;
    //   37: checkcast java/util/Map$Entry
    //   40: astore #4
    //   42: aload #4
    //   44: astore #5
    //   46: iconst_0
    //   47: istore #6
    //   49: aload #5
    //   51: invokeinterface getValue : ()Ljava/lang/Object;
    //   56: checkcast net/minecraft/class_2960
    //   59: astore #7
    //   61: invokestatic method_1551 : ()Lnet/minecraft/class_310;
    //   64: invokevirtual method_1531 : ()Lnet/minecraft/class_1060;
    //   67: aload #7
    //   69: invokevirtual method_4615 : (Lnet/minecraft/class_2960;)V
    //   72: nop
    //   73: nop
    //   74: goto -> 22
    //   77: nop
    //   78: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaav : Ljava/util/Map;
    //   81: invokeinterface clear : ()V
    //   86: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   89: invokeinterface clear : ()V
    //   94: aconst_null
    //   95: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaax : Lnet/minecraft/class_2960;
    //   98: aconst_null
    //   99: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaay : Lnet/minecraft/class_2960;
    //   102: aconst_null
    //   103: putstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaz : Lnet/minecraft/class_2960;
    //   106: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaB : Ljava/util/List;
    //   109: invokeinterface clear : ()V
    //   114: return
  }
  
  private final String 你为什么要破解我的代码aaaaan(String paramString) {
    long l = System.currentTimeMillis() % 10000L;
    return "leaderboard_avatar_" + l;
  }
  
  private final void 你为什么要破解我的代码aaaaao(String paramString1, String paramString2) {
    // Byte code:
    //   0: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   3: aload_1
    //   4: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   9: ifeq -> 13
    //   12: return
    //   13: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaav : Ljava/util/Map;
    //   16: aload_1
    //   17: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   22: ifeq -> 26
    //   25: return
    //   26: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   29: aload_1
    //   30: aload_2
    //   31: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   36: invokeinterface info : (Ljava/lang/String;)V
    //   41: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   44: astore_3
    //   45: aload_2
    //   46: aload_1
    //   47: <illegal opcode> run : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Runnable;
    //   52: invokestatic runAsync : (Ljava/lang/Runnable;)Ljava/util/concurrent/CompletableFuture;
    //   55: dup
    //   56: ldc_w -397475828
    //   59: i2c
    //   60: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   63: checkcast java/lang/String
    //   66: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   69: astore #4
    //   71: aload_3
    //   72: aload_1
    //   73: aload #4
    //   75: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   80: pop
    //   81: return
  }
  
  private static final void 你为什么要破解我的代码aaaaap(String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)-661127155));
    class_2960 class_29601 = 我草你怎么反编译我模组aaaaaz;
    boolean bool = false;
    String str = 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaan(paramString);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("使用默认头像，注册纹理: " + str);
    我草你怎么反编译我模组aaaaav.put(paramString, class_29601);
  }
  
  private static final void 你为什么要破解我的代码aaaaaq(class_1011 paramclass_1011, String paramString) {
    Intrinsics.checkNotNullParameter(paramclass_1011, (String)aaaaat((char)739442702));
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)1490288653));
    class_1043 class_1043 = new class_1043(paramclass_1011);
    String str = 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaan(paramString);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("注册纹理: " + str);
    Map<String, class_2960> map = 我草你怎么反编译我模组aaaaav;
    Intrinsics.checkNotNullExpressionValue(class_310.method_1551().method_1531().method_4617(str, class_1043), (String)aaaaat((char)-290979825));
    class_2960 class_29601 = class_310.method_1551().method_1531().method_4617(str, class_1043);
    map.put(paramString, class_29601);
  }
  
  private static final void 你为什么要破解我的代码aaaaar(String paramString) {
    Intrinsics.checkNotNullParameter(paramString, (String)aaaaat((char)-912719859));
    class_2960 class_29601 = 我草你怎么反编译我模组aaaaaz;
    boolean bool = false;
    String str = 我草你怎么反编译我模组aaaaaa.你为什么要破解我的代码aaaaan(paramString);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("加载失败，使用默认头像，注册纹理: " + str);
    我草你怎么反编译我模组aaaaav.put(paramString, class_29601);
  }
  
  private static final void 你为什么要破解我的代码aaaaas(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w -636092400
    //   4: i2c
    //   5: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   8: checkcast java/lang/String
    //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   14: aload_1
    //   15: ldc_w -865402867
    //   18: i2c
    //   19: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   28: nop
    //   29: aload_0
    //   30: checkcast java/lang/CharSequence
    //   33: invokestatic isBlank : (Ljava/lang/CharSequence;)Z
    //   36: ifeq -> 63
    //   39: invokestatic method_1551 : ()Lnet/minecraft/class_310;
    //   42: aload_1
    //   43: <illegal opcode> run : (Ljava/lang/String;)Ljava/lang/Runnable;
    //   48: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   51: nop
    //   52: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   55: aload_1
    //   56: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   61: pop
    //   62: return
    //   63: new java/net/URI
    //   66: dup
    //   67: aload_0
    //   68: invokespecial <init> : (Ljava/lang/String;)V
    //   71: invokevirtual toURL : ()Ljava/net/URL;
    //   74: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   77: astore_2
    //   78: aload_2
    //   79: ldc_w 1637875729
    //   82: i2c
    //   83: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   86: checkcast java/lang/String
    //   89: ldc_w -1138491374
    //   92: i2c
    //   93: invokestatic aaaaat : (C)Ljava/lang/Object;
    //   96: checkcast java/lang/String
    //   99: invokevirtual setRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload_2
    //   103: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   106: invokestatic read : (Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;
    //   109: astore_3
    //   110: new net/minecraft/class_1011
    //   113: dup
    //   114: getstatic net/minecraft/class_1011$class_1012.field_4997 : Lnet/minecraft/class_1011$class_1012;
    //   117: aload_3
    //   118: invokevirtual getWidth : ()I
    //   121: aload_3
    //   122: invokevirtual getHeight : ()I
    //   125: iconst_1
    //   126: invokespecial <init> : (Lnet/minecraft/class_1011$class_1012;IIZ)V
    //   129: astore #4
    //   131: iconst_0
    //   132: istore #5
    //   134: aload_3
    //   135: invokevirtual getWidth : ()I
    //   138: istore #6
    //   140: iload #5
    //   142: iload #6
    //   144: if_icmpge -> 259
    //   147: iconst_0
    //   148: istore #7
    //   150: aload_3
    //   151: invokevirtual getHeight : ()I
    //   154: istore #8
    //   156: iload #7
    //   158: iload #8
    //   160: if_icmpge -> 253
    //   163: aload_3
    //   164: iload #5
    //   166: iload #7
    //   168: invokevirtual getRGB : (II)I
    //   171: istore #9
    //   173: iload #9
    //   175: bipush #24
    //   177: ishr
    //   178: sipush #255
    //   181: iand
    //   182: istore #10
    //   184: iload #9
    //   186: bipush #16
    //   188: ishr
    //   189: sipush #255
    //   192: iand
    //   193: istore #11
    //   195: iload #9
    //   197: bipush #8
    //   199: ishr
    //   200: sipush #255
    //   203: iand
    //   204: istore #12
    //   206: iload #9
    //   208: sipush #255
    //   211: iand
    //   212: istore #13
    //   214: iload #10
    //   216: bipush #24
    //   218: ishl
    //   219: iload #13
    //   221: bipush #16
    //   223: ishl
    //   224: ior
    //   225: iload #12
    //   227: bipush #8
    //   229: ishl
    //   230: ior
    //   231: iload #11
    //   233: ior
    //   234: istore #14
    //   236: aload #4
    //   238: iload #5
    //   240: iload #7
    //   242: iload #14
    //   244: invokevirtual method_4305 : (III)V
    //   247: iinc #7, 1
    //   250: goto -> 156
    //   253: iinc #5, 1
    //   256: goto -> 140
    //   259: invokestatic method_1551 : ()Lnet/minecraft/class_310;
    //   262: aload #4
    //   264: aload_1
    //   265: <illegal opcode> run : (Lnet/minecraft/class_1011;Ljava/lang/String;)Ljava/lang/Runnable;
    //   270: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   273: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   276: aload_1
    //   277: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   282: pop
    //   283: goto -> 343
    //   286: astore_2
    //   287: invokestatic 你为什么要破解我的代码aaaaaa : ()Lorg/slf4j/Logger;
    //   290: aload_0
    //   291: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
    //   296: aload_2
    //   297: checkcast java/lang/Throwable
    //   300: invokeinterface error : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   305: invokestatic method_1551 : ()Lnet/minecraft/class_310;
    //   308: aload_1
    //   309: <illegal opcode> run : (Ljava/lang/String;)Ljava/lang/Runnable;
    //   314: invokevirtual execute : (Ljava/lang/Runnable;)V
    //   317: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   320: aload_1
    //   321: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   326: pop
    //   327: goto -> 343
    //   330: astore_2
    //   331: getstatic aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaac.我草你怎么反编译我模组aaaaaw : Ljava/util/Map;
    //   334: aload_1
    //   335: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   340: pop
    //   341: aload_2
    //   342: athrow
    //   343: return
    // Exception table:
    //   from	to	target	type
    //   28	52	286	java/lang/Exception
    //   28	52	330	finally
    //   63	273	286	java/lang/Exception
    //   63	273	330	finally
    //   286	317	330	finally
    //   330	331	330	finally
  }
  
  static {
    byte[] arrayOfByte1 = "\021\013\t3£¦ç=~.ò¾G\bëÛGx\\XgyOF\002Y1«Ó¤©iJôkF\004s\035ãx¹jÝÿ¾öA\tmØ\030ä®­#1«û¡v`ÈÞ\032ØypÒò\0060'g7ÀÔoÀaÞ\néÆjÜÃiãé6În,±\n\030¯Å.Mà7g!ß­9¨\007!áÔ4&=XÒlpy>Ç¼\0377Þ­ßôGîÀF6\b´Ý`­u\\_ØÈún«Ñ;»aÄ'¤â/KÈÀ\001\026Zç×i\0231¿Òd\tºÔ­üùL´\002*§öUE½É\f!,¡\025ðsó«\tc-¼7-¨ü\tØd\013Ü9]\f6ìÏ¹Ms'c©\005Ø¶â4ÊæÒ³Õ\020þÆ´|ÏL×oí\021\006k#:hxÚó²)GµLT|=UkD\037¸ÓÖz\f\013·G=IÞ;xÎY\tIO²kÏ\035äÛªþ+Áú»Ã£û¯ã\037ðü\001p_9Ù\002ó«XuêTáuIoAmJc)[Þ2\\\n&ñk«ºZ\032pâðS".getBytes("ISO_8859_1");
    byte[] arrayOfByte2 = "lU\0260s'K".getBytes("ISO_8859_1");
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
      arrayOfByte1[i++] = (byte)-1285576319;
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
              Object[] arrayOfObject = new Object[19];
              j = i;
              i = 0;
              do {
                byte[] arrayOfByte = new byte[k = arrayOfByte1[i++] & 0xFF | (arrayOfByte1[i++] & 0xFF) << 8];
                System.arraycopy(arrayOfByte1, i, arrayOfByte, 0, k);
                i += k;
                arrayOfObject[b++] = (new String(arrayOfByte, "UTF-8")).intern();
              } while (i != j);
            } 
            我草你怎么反编译我模组aaaaau = 0.85F;
            我草你怎么反编译我模组aaaaat = 0.17F;
            我草你怎么反编译我模组aaaaas = 0.52F;
            我草你怎么反编译我模组aaaaar = 0.6F;
            我草你怎么反编译我模组aaaaaq = 0.6F;
            我草你怎么反编译我模组aaaaap = 0.6F;
            我草你怎么反编译我模组aaaaao = 0.7F;
            我草你怎么反编译我模组aaaaan = 0.8F;
            我草你怎么反编译我模组aaaaam = 0.9F;
            我草你怎么反编译我模组aaaaal = 0.2F;
            我草你怎么反编译我模组aaaaak = 0.2F;
            我草你怎么反编译我模组aaaaaj = 0.2F;
            我草你怎么反编译我模组aaaaai = 0.85F;
            我草你怎么反编译我模组aaaaah = 0.16F;
            我草你怎么反编译我模组aaaaag = 0.51F;
            我草你怎么反编译我模组aaaaaf = 0.3F;
            我草你怎么反编译我模组aaaaae = 0.35F;
            我草你怎么反编译我模组aaaaad = 0.4F;
            我草你怎么反编译我模组aaaaac = 2.0F;
            我草你怎么反编译我模组aaaaab = 0.32F;
            我草你怎么反编译我模组aaaaaa = new aaaaac();
            我草你怎么反编译我模组aaaaav = new LinkedHashMap<>();
            我草你怎么反编译我模组aaaaaw = new LinkedHashMap<>();
            我草你怎么反编译我模组aaaaaB = new ArrayList<>();
            return;
          } 
        } 
        break;
      } 
    } 
  }
  
  private static Object aaaaat(char paramChar) {
    return ((Object[])aaaaaC)[paramChar];
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad {
    private final int 我草你怎么反编译我模组aaaaaa;
    
    private final float 我草你怎么反编译我模组aaaaab;
    
    private final float 我草你怎么反编译我模组aaaaac;
    
    private final float 我草你怎么反编译我模组aaaaad;
    
    @Nullable
    private final String 我草你怎么反编译我模组aaaaae;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad(int param1Int, float param1Float1, float param1Float2, float param1Float3, @Nullable String param1String) {
      this.我草你怎么反编译我模组aaaaaa = param1Int;
      this.我草你怎么反编译我模组aaaaab = param1Float1;
      this.我草你怎么反编译我模组aaaaac = param1Float2;
      this.我草你怎么反编译我模组aaaaad = param1Float3;
      this.我草你怎么反编译我模组aaaaae = param1String;
    }
    
    public final int 你为什么要破解我的代码aaaaaa() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final float 你为什么要破解我的代码aaaaab() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    public final float 你为什么要破解我的代码aaaaac() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final float 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaae() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    public final int 你为什么要破解我的代码aaaaaf() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final float 你为什么要破解我的代码aaaaag() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    public final float 你为什么要破解我的代码aaaaah() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final float 你为什么要破解我的代码aaaaai() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaaj() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad 你为什么要破解我的代码aaaaak(int param1Int, float param1Float1, float param1Float2, float param1Float3, @Nullable String param1String) {
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad(param1Int, param1Float1, param1Float2, param1Float3, param1String);
    }
    
    @NotNull
    public String toString() {
      return "AvatarPosition(index=" + this.我草你怎么反编译我模组aaaaaa + ", xRatio=" + this.我草你怎么反编译我模组aaaaab + ", yRatio=" + this.我草你怎么反编译我模组aaaaac + ", sizeRatio=" + this.我草你怎么反编译我模组aaaaad + ", playerName=" + this.我草你怎么反编译我模组aaaaae + ")";
    }
    
    public int hashCode() {
      null = Integer.hashCode(this.我草你怎么反编译我模组aaaaaa);
      null = null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaab);
      null = null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaac);
      null = null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaad);
      return null * 31 + ((this.我草你怎么反编译我模组aaaaae == null) ? 0 : this.我草你怎么反编译我模组aaaaae.hashCode());
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad)param1Object;
      return (this.我草你怎么反编译我模组aaaaaa != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad1.我草你怎么反编译我模组aaaaaa) ? false : ((Float.compare(this.我草你怎么反编译我模组aaaaab, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad1.我草你怎么反编译我模组aaaaab) != 0) ? false : ((Float.compare(this.我草你怎么反编译我模组aaaaac, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad1.我草你怎么反编译我模组aaaaac) != 0) ? false : ((Float.compare(this.我草你怎么反编译我模组aaaaad, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad1.我草你怎么反编译我模组aaaaad) != 0) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaae, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaad1.我草你怎么反编译我模组aaaaae)))));
    }
  }
  
  private static final class aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab {
    private final int 我草你怎么反编译我模组aaaaaa;
    
    private final float 我草你怎么反编译我模组aaaaab;
    
    private final float 我草你怎么反编译我模组aaaaac;
    
    private final float 我草你怎么反编译我模组aaaaad;
    
    @Nullable
    private final String 我草你怎么反编译我模组aaaaae;
    
    public aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab(int param1Int, float param1Float1, float param1Float2, float param1Float3, @Nullable String param1String) {
      this.我草你怎么反编译我模组aaaaaa = param1Int;
      this.我草你怎么反编译我模组aaaaab = param1Float1;
      this.我草你怎么反编译我模组aaaaac = param1Float2;
      this.我草你怎么反编译我模组aaaaad = param1Float3;
      this.我草你怎么反编译我模组aaaaae = param1String;
    }
    
    public final int 你为什么要破解我的代码aaaaaa() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final float 你为什么要破解我的代码aaaaab() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    public final float 你为什么要破解我的代码aaaaac() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final float 你为什么要破解我的代码aaaaad() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaae() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    public final int 你为什么要破解我的代码aaaaaf() {
      return this.我草你怎么反编译我模组aaaaaa;
    }
    
    public final float 你为什么要破解我的代码aaaaag() {
      return this.我草你怎么反编译我模组aaaaab;
    }
    
    public final float 你为什么要破解我的代码aaaaah() {
      return this.我草你怎么反编译我模组aaaaac;
    }
    
    public final float 你为什么要破解我的代码aaaaai() {
      return this.我草你怎么反编译我模组aaaaad;
    }
    
    @Nullable
    public final String 你为什么要破解我的代码aaaaaj() {
      return this.我草你怎么反编译我模组aaaaae;
    }
    
    @NotNull
    public final aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab 你为什么要破解我的代码aaaaak(int param1Int, float param1Float1, float param1Float2, float param1Float3, @Nullable String param1String) {
      return new aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab(param1Int, param1Float1, param1Float2, param1Float3, param1String);
    }
    
    @NotNull
    public String toString() {
      return "TextPosition(index=" + this.我草你怎么反编译我模组aaaaaa + ", xRatio=" + this.我草你怎么反编译我模组aaaaab + ", yRatio=" + this.我草你怎么反编译我模组aaaaac + ", scale=" + this.我草你怎么反编译我模组aaaaad + ", playerName=" + this.我草你怎么反编译我模组aaaaae + ")";
    }
    
    public int hashCode() {
      null = Integer.hashCode(this.我草你怎么反编译我模组aaaaaa);
      null = null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaab);
      null = null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaac);
      null = null * 31 + Float.hashCode(this.我草你怎么反编译我模组aaaaad);
      return null * 31 + ((this.我草你怎么反编译我模组aaaaae == null) ? 0 : this.我草你怎么反编译我模组aaaaae.hashCode());
    }
    
    public boolean equals(@Nullable Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab))
        return false; 
      aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab1 = (aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab)param1Object;
      return (this.我草你怎么反编译我模组aaaaaa != aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab1.我草你怎么反编译我模组aaaaaa) ? false : ((Float.compare(this.我草你怎么反编译我模组aaaaab, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab1.我草你怎么反编译我模组aaaaab) != 0) ? false : ((Float.compare(this.我草你怎么反编译我模组aaaaac, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab1.我草你怎么反编译我模组aaaaac) != 0) ? false : ((Float.compare(this.我草你怎么反编译我模组aaaaad, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab1.我草你怎么反编译我模组aaaaad) != 0) ? false : (!!Intrinsics.areEqual(this.我草你怎么反编译我模组aaaaae, aaaaaa/aaaaaa/aaaaaa/aaaaaa/aaaaak/aaaaab1.我草你怎么反编译我模组aaaaae)))));
    }
  }
}
