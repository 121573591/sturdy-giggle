package cn.pixellive.mc.game.mixin;

import java.util.function.Predicate;
import net.minecraft.class_1299;
import net.minecraft.class_1439;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1439.class})
public abstract class IronGolemTargetMixin {
  @Inject(method = {"canTarget"}, at = {@At("HEAD")}, cancellable = true)
  private void onCanTarget(class_1299<?> paramclass_1299, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (((class_1439)this).method_6496() && paramclass_1299 == class_1299.field_6097) {
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
      return;
    } 
    paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
  }
  
  @ModifyArg(method = {"initGoals"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/ActiveTargetGoal;<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;IZZLjava/util/function/Predicate;)V"), index = 5)
  private Predicate<?> modifyTargetPredicate(Predicate<?> paramPredicate) {
    return paramObject -> paramObject instanceof net.minecraft.class_1569;
  }
  
  static {
  
  }
}
