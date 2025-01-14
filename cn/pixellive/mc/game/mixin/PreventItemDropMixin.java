package cn.pixellive.mc.game.mixin;

import cn.pixellive.mc.game.PixelLiveGameMod;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1657.class})
public class PreventItemDropMixin {
  @Inject(method = {"dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"}, at = {@At("HEAD")}, cancellable = true)
  private void onDropItem(class_1799 paramclass_1799, boolean paramBoolean1, boolean paramBoolean2, CallbackInfoReturnable<?> paramCallbackInfoReturnable) {
    if (PixelLiveGameMod.你为什么要破解我的代码aaaaaL() != null && PixelLiveGameMod.你为什么要破解我的代码aaaaaL().你为什么要破解我的代码aaaaae().equalsIgnoreCase("保卫小麦")) {
      class_1657 class_1657 = (class_1657)this;
      if (!paramclass_1799.method_7909().method_7876().startsWith("item.pointblank")) {
        paramCallbackInfoReturnable.setReturnValue(null);
        paramCallbackInfoReturnable.cancel();
        class_1657.method_31548().method_7394(paramclass_1799.method_7972());
      } 
    } 
  }
  
  static {
  
  }
}
