package cn.pixellive.mc.game.mixin;

import net.minecraft.class_1269;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1747.class})
public class BlockItemMixin {
  @Inject(method = {"place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/item/BlockItem;place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z")}, cancellable = true)
  public void placeInject(class_1750 paramclass_1750, CallbackInfoReturnable<class_1269> paramCallbackInfoReturnable) {}
  
  static {
  
  }
}
