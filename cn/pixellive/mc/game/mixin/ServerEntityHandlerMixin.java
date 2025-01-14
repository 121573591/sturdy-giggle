package cn.pixellive.mc.game.mixin;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_1308;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = {"net.minecraft.server.world.ServerWorld$ServerEntityHandler"})
public class ServerEntityHandlerMixin {
  @Redirect(method = {"stopTracking(Lnet/minecraft/entity/Entity;)V"}, at = @At(value = "INVOKE", target = "Ljava/util/Set;remove(Ljava/lang/Object;)Z"))
  private boolean onRemove(Set<class_1308> paramSet, Object paramObject) {
    if (paramSet != null && paramObject instanceof class_1308)
      try {
        return paramSet.remove(paramObject);
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        HashSet<class_1308> hashSet = new HashSet<>(paramSet);
        hashSet.remove(paramObject);
        paramSet.clear();
        paramSet.addAll(hashSet);
        return true;
      }  
    return false;
  }
  
  static {
  
  }
}
