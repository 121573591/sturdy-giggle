package cn.pixellive.mc.game.mixin;

import net.minecraft.class_1757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin({class_1757.class})
public class ChorusFruitMixin {
  @ModifyConstant(method = {"finishUsing"}, constant = {@Constant(doubleValue = 16.0D)})
  private double modifyTeleportRange(double paramDouble) {
    return 500.0D;
  }
  
  @ModifyConstant(method = {"finishUsing"}, constant = {@Constant(intValue = 16, ordinal = 0)})
  private int modifyAttempts(int paramInt) {
    return 64;
  }
  
  @ModifyConstant(method = {"finishUsing"}, constant = {@Constant(intValue = 16, ordinal = 1)})
  private int modifyVerticalRange(int paramInt) {
    return 100;
  }
  
  @ModifyConstant(method = {"finishUsing"}, constant = {@Constant(intValue = 8)})
  private int modifyVerticalOffset(int paramInt) {
    return 50;
  }
  
  static {
  
  }
}
