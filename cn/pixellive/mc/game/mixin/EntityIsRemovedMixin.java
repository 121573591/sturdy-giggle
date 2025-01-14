package cn.pixellive.mc.game.mixin;

import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public abstract class EntityIsRemovedMixin {
  @Inject(method = {"isRemoved"}, at = {@At("HEAD")}, cancellable = true)
  private void onIsRemoved(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    try {
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf((((class_1297)this).method_35049() != null)));
    } catch (Exception exception) {
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
    } 
  }
  
  @Inject(method = {"getRemovalReason"}, at = {@At("HEAD")}, cancellable = true)
  private void onGetRemovalReason(CallbackInfoReturnable<class_1297.class_5529> paramCallbackInfoReturnable) {}
  
  static {
  
  }
}
