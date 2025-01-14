package cn.pixellive.mc.game.mixin;

import aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaar;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_525;
import net.minecraft.class_7845;
import net.minecraft.class_8021;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = {"net.minecraft.client.gui.screen.world.CreateWorldScreen$GameTab"})
public class CreateWorldScreenMixin {
  private class_7845.class_7939 adder;
  
  private class_525 createWorldScreen;
  
  @Redirect(method = {"<init>"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget;createAdder(I)Lnet/minecraft/client/gui/widget/GridWidget$Adder;"))
  private class_7845.class_7939 injectButton(class_7845 paramclass_7845, int paramInt) {
    class_7845.class_7939 class_79391 = paramclass_7845.method_48636(8).method_47610(paramInt);
    this.adder = class_79391;
    return class_79391;
  }
  
  @Inject(method = {"<init>"}, at = {@At("RETURN")})
  private void captureCreateWorldScreen(class_525 paramclass_525, CallbackInfo paramCallbackInfo) {
    this.createWorldScreen = paramclass_525;
  }
  
  @Inject(method = {"<init>"}, at = {@At("TAIL")})
  private void injectButton(CallbackInfo paramCallbackInfo) {
    class_4185 class_4185 = class_4185.method_46430((class_2561)class_2561.method_43471("pixellivegame.choose_game.title"), paramclass_4185 -> class_310.method_1551().execute(())).method_46432(210).method_46431();
    this.adder.method_47612((class_8021)class_4185);
  }
}
