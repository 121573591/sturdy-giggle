package cn.pixellive.mc.game.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({MinecraftServer.class})
public class MinecraftServerSaveMixin {
  @Overwrite
  public boolean method_3723(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    return false;
  }
  
  @Overwrite
  public boolean method_39218(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    return false;
  }
  
  static {
  
  }
}
