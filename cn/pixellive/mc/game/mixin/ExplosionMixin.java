package cn.pixellive.mc.game.mixin;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaad.aaaaac;
import cn.pixellive.mc.game.PixelLiveGameMod;
import cn.pixellive.mc.game.event.game.ExplosionEvent;
import net.minecraft.class_1927;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1927.class})
public class ExplosionMixin {
  @Inject(method = {"affectWorld"}, at = {@At("HEAD")}, cancellable = true)
  public void affectWorldInject(boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    ExplosionEvent explosionEvent = new ExplosionEvent((class_1927)aaaaac.你为什么要破解我的代码aaaaaa(this), paramBoolean);
    PixelLiveGameMod.你为什么要破解我的代码aaaaaI().post(explosionEvent);
    if (explosionEvent.getCancelled())
      paramCallbackInfo.cancel(); 
  }
  
  static {
  
  }
}
