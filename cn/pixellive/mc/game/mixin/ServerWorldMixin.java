package cn.pixellive.mc.game.mixin;

import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_3218.class})
public class ServerWorldMixin {
  @Inject(method = {"getLogicalHeight"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetLogicalHeight(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(2000));
  }
  
  static {
  
  }
}
