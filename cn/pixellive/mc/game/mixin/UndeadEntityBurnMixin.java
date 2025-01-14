package cn.pixellive.mc.game.mixin;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac.aaaabG;
import cn.pixellive.mc.game.PixelLiveGameMod;
import net.minecraft.class_1308;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1308.class})
public class UndeadEntityBurnMixin {
  @Inject(method = {"isAffectedByDaylight"}, at = {@At("HEAD")}, cancellable = true)
  private void preventDaylightBurning(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    aaaabG aaaabG = PixelLiveGameMod.你为什么要破解我的代码aaaaaL();
    if (aaaabG != null) {
      String str = aaaabG.你为什么要破解我的代码aaaaae();
      if (str != null && (str.equalsIgnoreCase("逆境突围") || str.equalsIgnoreCase("怪兽争霸赛")))
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
    } 
  }
  
  static {
  
  }
}
