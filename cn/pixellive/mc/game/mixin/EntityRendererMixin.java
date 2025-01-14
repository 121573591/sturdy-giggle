package cn.pixellive.mc.game.mixin;

import cn.pixellive.mc.game.PixelLiveGameMod;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_4587;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_897.class})
public abstract class EntityRendererMixin<T extends class_1297> {
  @Redirect(method = {"renderLabelIfPresent"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"))
  private void redirectScale(class_4587 paramclass_4587, float paramFloat1, float paramFloat2, float paramFloat3, T paramT) {
    class_238 class_238 = paramT.method_5829();
    double d1 = (class_238.field_1320 - class_238.field_1323) * (class_238.field_1325 - class_238.field_1322) * (class_238.field_1324 - class_238.field_1321);
    double d2 = 1.0D;
    float f1 = 1.0F;
    float f2 = 10.0F;
    float f3 = (float)Math.min(f2, Math.max(f1, f1 + (d1 / d2 - 1.0D) * 0.33000001311302185D));
    if ((PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null && PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("怪兽竞技场")) || PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("怪兽争霸赛")) {
      paramclass_4587.method_22905(paramFloat1 * f3, paramFloat2 * f3, paramFloat3 * f3);
    } else {
      paramclass_4587.method_22905(paramFloat1, paramFloat2, paramFloat3);
    } 
  }
  
  static {
  
  }
}
