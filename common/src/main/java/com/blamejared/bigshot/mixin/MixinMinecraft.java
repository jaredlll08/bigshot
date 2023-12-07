package com.blamejared.bigshot.mixin;

import com.blamejared.bigshot.BigShot;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(method = "useShaderTransparency", cancellable = true, at = @At("HEAD"))
    private static void useShaderTransparency(CallbackInfoReturnable<Boolean> cir) {
        
        if(BigShot.TAKING_SCREENSHOT) {
            cir.setReturnValue(false);
        }
    }
    
}
