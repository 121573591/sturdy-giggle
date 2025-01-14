package cn.pixellive.mc.game.mixin;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.class_3898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_3898.class})
public class ServerChunkLoadingManagerMixin {
  @Shadow
  private Int2ObjectMap<Int2ObjectOpenHashMap<Void>> field_18242;
  
  @Inject(method = {"tickEntityMovement"}, at = {@At("HEAD")})
  private void onTickEntityMovementStart(CallbackInfo paramCallbackInfo) {
    try {
      if (this.field_18242 == null)
        this.field_18242 = (Int2ObjectMap<Int2ObjectOpenHashMap<Void>>)new Int2ObjectOpenHashMap(); 
      this.field_18242.int2ObjectEntrySet().forEach(paramEntry -> {
            if (paramEntry.getValue() == null || ((Int2ObjectOpenHashMap)paramEntry.getValue()).isEmpty())
              paramEntry.setValue(new Int2ObjectOpenHashMap()); 
          });
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  @Inject(method = {"tickEntityMovement"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getPlayers()Ljava/util/List;", ordinal = 0)})
  private void onEntityIteration(CallbackInfo paramCallbackInfo) {
    try {
      this.field_18242.values().removeIf(paramInt2ObjectOpenHashMap -> (paramInt2ObjectOpenHashMap == null || paramInt2ObjectOpenHashMap.isEmpty()));
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  @Inject(method = {"tickEntityMovement"}, at = {@At("RETURN")})
  private void onTickEntityMovementEnd(CallbackInfo paramCallbackInfo) {
    try {
      this.field_18242.values().removeIf(paramInt2ObjectOpenHashMap -> (paramInt2ObjectOpenHashMap == null || paramInt2ObjectOpenHashMap.isEmpty()));
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}
