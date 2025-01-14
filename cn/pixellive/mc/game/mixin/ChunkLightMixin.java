package cn.pixellive.mc.game.mixin;

import net.minecraft.class_2338;
import net.minecraft.class_3558;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_3558.class})
public class ChunkLightMixin {
  @Inject(method = {"checkBlock"}, at = {@At("HEAD")}, cancellable = true)
  private void onCheckBlock(class_2338 paramclass_2338, CallbackInfo paramCallbackInfo) {
    if (paramclass_2338.method_10264() > 1700)
      paramCallbackInfo.cancel(); 
  }
  
  static {
  
  }
}
