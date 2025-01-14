package cn.pixellive.mc.game.mixin;

import net.minecraft.class_238;
import net.minecraft.class_5568;
import net.minecraft.class_5572;
import net.minecraft.class_5575;
import net.minecraft.class_7927;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_5572.class})
public class EntityTrackingSectionMixin<T extends class_5568> {
  @Inject(method = {"forEach(Lnet/minecraft/util/math/Box;Lnet/minecraft/util/function/LazyIterationConsumer;)Lnet/minecraft/util/function/LazyIterationConsumer$NextIteration;"}, at = {@At("HEAD")}, cancellable = true)
  private void onForEach(class_238 paramclass_238, class_7927<T> paramclass_7927, CallbackInfoReturnable<class_7927.class_7928> paramCallbackInfoReturnable) {
    try {
      synchronized (this) {
        class_5572 class_5572 = (class_5572)this;
        for (class_5568 class_5568 : class_5572.method_31766()) {
          if (class_5568 != null && class_5568.method_5829().method_994(paramclass_238))
            try {
              if (paramclass_7927.accept(class_5568).method_47543()) {
                paramCallbackInfoReturnable.setReturnValue(class_7927.class_7928.field_41284);
                return;
              } 
            } catch (Exception exception) {} 
        } 
        paramCallbackInfoReturnable.setReturnValue(class_7927.class_7928.field_41283);
      } 
    } catch (Exception exception) {
      paramCallbackInfoReturnable.setReturnValue(class_7927.class_7928.field_41283);
    } 
  }
  
  @Inject(method = {"forEach(Lnet/minecraft/util/TypeFilter;Lnet/minecraft/util/math/Box;Lnet/minecraft/util/function/LazyIterationConsumer;)Lnet/minecraft/util/function/LazyIterationConsumer$NextIteration;"}, at = {@At("HEAD")}, cancellable = true)
  private <U extends T> void onForEachOfType(class_5575<T, U> paramclass_5575, class_238 paramclass_238, class_7927<? super U> paramclass_7927, CallbackInfoReturnable<class_7927.class_7928> paramCallbackInfoReturnable) {
    try {
      synchronized (this) {
        class_5572 class_5572 = (class_5572)this;
        for (class_5568 class_5568 : class_5572.method_31766()) {
          if (class_5568 != null) {
            class_5568 class_55681 = (class_5568)paramclass_5575.method_31796(class_5568);
            if (class_55681 != null && class_55681.method_5829().method_994(paramclass_238))
              try {
                if (paramclass_7927.accept(class_55681).method_47543()) {
                  paramCallbackInfoReturnable.setReturnValue(class_7927.class_7928.field_41284);
                  return;
                } 
              } catch (Exception exception) {} 
          } 
        } 
        paramCallbackInfoReturnable.setReturnValue(class_7927.class_7928.field_41283);
      } 
    } catch (Exception exception) {
      paramCallbackInfoReturnable.setReturnValue(class_7927.class_7928.field_41283);
    } 
  }
  
  static {
  
  }
}
