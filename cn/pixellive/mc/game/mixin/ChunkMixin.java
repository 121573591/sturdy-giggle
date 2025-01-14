package cn.pixellive.mc.game.mixin;

import net.minecraft.class_2791;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_2791.class})
public class ChunkMixin {
  @Inject(method = {"getHeight"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetHeight(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(2000));
  }
  
  static {
  
  }
}
