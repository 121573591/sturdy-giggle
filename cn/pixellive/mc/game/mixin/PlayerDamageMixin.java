package cn.pixellive.mc.game.mixin;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaab.aaaaaa;
import cn.pixellive.mc.game.PixelLiveGameMod;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin({class_1309.class})
public class PlayerDamageMixin {
  @ModifyArg(method = {"damage"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"), index = 1)
  private float modifyDamage(float paramFloat) {
    if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null && PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("勇攀高峰")) {
      float f = paramFloat * 0.5F;
      f = Math.max(f, 1.0F);
      aaaaaa.你为什么要破解我的代码aaaaaa().info(String.format("玩家伤害减半:\n- 原始伤害: %f\n- 修改后伤害: %f\n", new Object[] { Float.valueOf(paramFloat), Float.valueOf(f) }));
      return f;
    } 
    return paramFloat;
  }
  
  static {
  
  }
}
