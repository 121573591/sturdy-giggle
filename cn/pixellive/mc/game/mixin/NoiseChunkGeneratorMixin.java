package cn.pixellive.mc.game.mixin;

import net.minecraft.class_3754;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_3754.class})
public class NoiseChunkGeneratorMixin {
  @Inject(method = {"getWorldHeight"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetWorldHeight(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(2000));
  }
  
  static {
  
  }
}
