package cn.pixellive.mc.game.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2344;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2344.class})
public class FarmlandBlockMixin {
  @Inject(method = {"onLandedUpon"}, at = {@At("HEAD")}, cancellable = true)
  private void onLandedUpon(class_1937 paramclass_1937, class_2680 paramclass_2680, class_2338 paramclass_2338, class_1297 paramclass_1297, float paramFloat, CallbackInfo paramCallbackInfo) {
    if (paramclass_1297 instanceof net.minecraft.class_3222)
      paramCallbackInfo.cancel(); 
  }
  
  static {
  
  }
}
