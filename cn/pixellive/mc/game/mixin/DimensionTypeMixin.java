package cn.pixellive.mc.game.mixin;

import net.minecraft.class_2874;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_2874.class})
public class DimensionTypeMixin {
  @Inject(method = {"height"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetHeight(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(2000));
  }
  
  @Inject(method = {"logicalHeight"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetLogicalHeight(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(2000));
  }
  
  @Inject(method = {"minY"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetMinY(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(-64));
  }
  
  static {
  
  }
}
