package uk.co.xsc.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@org.spongepowered.asm.mixin.Mixin(MinecraftClient.class)
public class Mixin {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        System.out.println("This line is printed by mixin!");
    }

}