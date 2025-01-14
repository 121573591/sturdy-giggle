package cn.pixellive.mc.game.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1948;
import net.minecraft.class_2338;
import net.minecraft.class_6540;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1948.class})
public class SpawnHelperMixin {
  private static int spawnCount = 0;
  
  @Redirect(method = {"setupSpawn"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getBlockPos()Lnet/minecraft/util/math/BlockPos;"))
  private static class_2338 redirectGetBlockPos(class_1297 paramclass_1297) {
    try {
      return (paramclass_1297 == null) ? null : paramclass_1297.method_24515();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  @Inject(method = {"setupSpawn"}, at = {@At("HEAD")})
  private static void onSetupSpawnStart(int paramInt, Iterable<class_1297> paramIterable, class_1948.class_5260 paramclass_5260, class_6540 paramclass_6540, CallbackInfoReturnable<class_1948.class_5262> paramCallbackInfoReturnable) {
    try {
      spawnCount = 0;
    } catch (Exception exception) {}
  }
  
  @Inject(method = {"setupSpawn"}, at = {@At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;")}, cancellable = true)
  private static void onEntityIteration(int paramInt, Iterable<class_1297> paramIterable, class_1948.class_5260 paramclass_5260, class_6540 paramclass_6540, CallbackInfoReturnable<class_1948.class_5262> paramCallbackInfoReturnable) {
    try {
      spawnCount++;
      if (paramclass_6540 == null || paramclass_5260 == null)
        paramCallbackInfoReturnable.setReturnValue(null); 
    } catch (Exception exception) {}
  }
  
  @Inject(method = {"setupSpawn"}, at = {@At("RETURN")})
  private static void onSetupSpawnEnd(int paramInt, Iterable<class_1297> paramIterable, class_1948.class_5260 paramclass_5260, class_6540 paramclass_6540, CallbackInfoReturnable<class_1948.class_5262> paramCallbackInfoReturnable) {
    try {
      spawnCount = 0;
    } catch (Exception exception) {}
  }
}
