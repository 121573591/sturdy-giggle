package cn.pixellive.mc.game.mixin;

import cn.pixellive.mc.game.PixelLiveGameMod;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_922.class})
public abstract class LivingEntityRendererMixin<T extends class_1309> {
  @Inject(method = {"hasLabel*"}, at = {@At("HEAD")}, cancellable = true)
  private void onHasLabel(T paramT, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (paramT.method_16914())
      if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null) {
        if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("怪兽竞技场") || PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("怪兽争霸赛")) {
          paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
        } else if (!(class_310.method_1551()).field_1690.field_1842) {
          paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
        } 
      } else if (!(class_310.method_1551()).field_1690.field_1842) {
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
      }  
  }
  
  static {
  
  }
}
