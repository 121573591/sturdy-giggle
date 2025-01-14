package cn.pixellive.mc.game.mixin;

import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_4970.class_2251.class})
public interface BlockSettingsAccessor {
  @Accessor("resistance")
  void setResistance(float paramFloat);
  
  @Accessor("hardness")
  void setHardness(float paramFloat);
  
  static {
  
  }
}
