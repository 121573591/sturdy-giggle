package cn.pixellive.mc.game.mixin;

import cn.pixellive.mc.game.PixelLiveGameMod;
import net.minecraft.class_3572;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_3572.class})
public class ChunkSkyLightMixin {
  @Inject(method = {"method_51586"}, at = {@At("HEAD")}, cancellable = true)
  private void onSkyLightUpdate(CallbackInfo paramCallbackInfo) {
    if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null && PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("勇攀高峰"))
      paramCallbackInfo.cancel(); 
  }
  
  static {
  
  }
}
