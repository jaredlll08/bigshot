package com.blamejared.bigshot.mixin;

import com.blamejared.bigshot.BigShot;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {
    
    
    @Inject(method = "shouldShowEntityOutlines", cancellable = true, at = @At("HEAD"))
    public void shouldShowEntityOutlines(CallbackInfoReturnable<Boolean> cir) {
        
        if(BigShot.TAKING_SCREENSHOT) {
            cir.setReturnValue(false);
        }
    }
    
}
