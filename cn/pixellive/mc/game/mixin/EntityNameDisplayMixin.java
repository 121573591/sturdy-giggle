package cn.pixellive.mc.game.mixin;

import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public class EntityNameDisplayMixin {
  @Inject(method = {"isCustomNameVisible"}, at = {@At("RETURN")}, cancellable = true)
  private void alwaysShowCustomName(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    class_1297 class_1297 = (class_1297)this;
    if (class_1297.method_16914())
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true)); 
  }
  
  static {
  
  }
}
