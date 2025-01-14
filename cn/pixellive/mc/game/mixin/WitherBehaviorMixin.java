package cn.pixellive.mc.game.mixin;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import cn.pixellive.mc.game.PixelLiveGameMod;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1528;
import net.minecraft.class_2561;
import net.minecraft.class_4051;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1528.class})
public abstract class WitherBehaviorMixin {
  @Shadow
  private static class_4051 field_18125;
  
  @Shadow
  private int field_7082;
  
  @Shadow
  private final int[] field_7092 = new int[2];
  
  @Shadow
  protected abstract void method_6876(int paramInt1, int paramInt2);
  
  private boolean isMonsterBattleStage() {
    return (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null && PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("怪兽争霸赛"));
  }
  
  @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
  private static void modifyTargetPredicate(CallbackInfo paramCallbackInfo) {
    try {
      Field field = class_4051.class.getDeclaredField("predicate");
      field.setAccessible(true);
      field.set(field_18125, paramclass_1309 -> (paramclass_1309.method_5805() && paramclass_1309.method_5864() != class_1299.field_6097));
    } catch (Exception exception) {
      aaaaaa.你为什么要破解我的代码aaaaaa().error("Failed to modify Wither target predicate", exception);
    } 
  }
  
  @Redirect(method = {"damage"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/WitherEntity;getInvulnerableTimer()I"))
  private int redirectInvulnerableTimer(class_1528 paramclass_1528) {
    return isMonsterBattleStage() ? 0 : paramclass_1528.method_6884();
  }
  
  @Shadow
  public abstract int method_6884();
  
  @Shadow
  public abstract boolean method_6872();
  
  @Inject(method = {"onSummoned"}, at = {@At("HEAD")}, cancellable = true)
  private void onEntitySummoned(CallbackInfo paramCallbackInfo) {
    if (isMonsterBattleStage()) {
      class_1528 class_1528 = (class_1528)this;
      class_1528.method_6875(0);
      class_1528.method_6033(class_1528.method_6063());
      paramCallbackInfo.cancel();
    } 
  }
  
  @Inject(method = {"setTrackedEntityId"}, at = {@At("HEAD")}, cancellable = true)
  private void onSetTrackedEntityId(int paramInt1, int paramInt2, CallbackInfo paramCallbackInfo) {
    if (!isMonsterBattleStage())
      return; 
    class_1528 class_1528 = (class_1528)this;
    if (paramInt2 <= 0)
      return; 
    class_1297 class_1297 = class_1528.method_37908().method_8469(paramInt2);
    if (class_1297 != null) {
      class_2561 class_25611 = class_1297.method_5797();
      class_2561 class_25612 = class_1528.method_5797();
      if (class_25611 != null && class_25612 != null && class_25611.getString().equals(class_25612.getString()))
        paramCallbackInfo.cancel(); 
    } 
  }
  
  @Inject(method = {"mobTick"}, at = {@At("HEAD")})
  private void onMobTick(CallbackInfo paramCallbackInfo) {
    if (!isMonsterBattleStage())
      return; 
    class_1528 class_1528 = (class_1528)this;
    class_2561 class_2561 = class_1528.method_5797();
    List<class_1309> list = class_1528.method_37908().method_8390(class_1309.class, class_1528.method_5829().method_1009(20.0D, 8.0D, 20.0D), paramclass_1309 -> {
          if (paramclass_1309 == paramclass_1528)
            return false; 
          class_2561 class_25611 = paramclass_1309.method_5797();
          return (paramclass_1309.method_5805() && paramclass_1309.method_5864() != class_1299.field_6097 && class_25611 != null && paramclass_2561 != null && !class_25611.getString().equals(paramclass_2561.getString()));
        });
    if (!list.isEmpty()) {
      class_1309 class_1309 = list.get(class_1528.method_59922().method_43048(list.size()));
      for (byte b = 0; b < 3; b++)
        method_6876(b, class_1309.method_5628()); 
    } 
  }
  
  @ModifyVariable(method = {"tickMovement"}, at = @At("STORE"), ordinal = 0)
  private double modifyVerticalMovement(double paramDouble) {
    class_1528 class_1528 = (class_1528)this;
    return (isMonsterBattleStage() && class_1528.method_5968() instanceof class_1528) ? 0.0D : paramDouble;
  }
  
  @Inject(method = {"damage"}, at = {@At("HEAD")}, cancellable = true)
  private void modifyDamageLogic(class_1282 paramclass_1282, float paramFloat, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    class_1528 class_1528 = (class_1528)this;
    class_1297 class_12971 = paramclass_1282.method_5526();
    class_1297 class_12972 = paramclass_1282.method_5529();
    aaaaaa.你为什么要破解我的代码aaaaaa().info("Wither受到伤害:");
    aaaaaa.你为什么要破解我的代码aaaaaa().info("- 伤害值: " + paramFloat);
    aaaaaa.你为什么要破解我的代码aaaaaa().info("- 伤害类型: " + paramclass_1282.method_5525());
    if (class_12972 != null) {
      aaaaaa.你为什么要破解我的代码aaaaaa().info("- 攻击者: " + class_12972.method_5864().toString());
      if (class_12972.method_5797() != null)
        aaaaaa.你为什么要破解我的代码aaaaaa().info("- 攻击者名称: " + class_12972.method_5797().getString()); 
    } 
    if (class_12971 != null)
      aaaaaa.你为什么要破解我的代码aaaaaa().info("- 直接伤害源: " + class_12971.method_5864().toString()); 
    aaaaaa.你为什么要破解我的代码aaaaaa().info("- 目标Wither名称: " + ((class_1528.method_5797() != null) ? class_1528.method_5797().getString() : "无名称"));
    aaaaaa.你为什么要破解我的代码aaaaaa().info("- 是否处于防护罩状态: " + ((class_1528.method_6884() > 0) ? 1 : 0));
    if (isMonsterBattleStage()) {
      String str1 = (class_12972 != null && class_12972.method_5797() != null) ? class_12972.method_5797().getString() : "";
      String str2 = (class_1528.method_5797() != null) ? class_1528.method_5797().getString() : "";
      boolean bool = false;
      if (class_12971 instanceof net.minecraft.class_1687) {
        if (!str1.isEmpty() && !str2.isEmpty() && !str1.equals(str2))
          bool = true; 
      } else if (class_12971 != null) {
        String str = class_12971.method_5864().toString();
        if (str.contains("arrow") && !str1.isEmpty() && !str2.isEmpty() && !str1.equals(str2))
          bool = true; 
      } 
      if (bool) {
        float f1 = class_1528.method_6032();
        float f2 = Math.max(0.0F, f1 - paramFloat);
        class_1528.method_6033(f2);
        aaaaaa.你为什么要破解我的代码aaaaaa().info("实际扣除血量: " + paramFloat);
        aaaaaa.你为什么要破解我的代码aaaaaa().info("当前血量: " + f2);
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
      } 
    } 
  }
}
