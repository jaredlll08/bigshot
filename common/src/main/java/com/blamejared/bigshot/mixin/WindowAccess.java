package com.blamejared.bigshot.mixin;

import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Window.class)
public interface WindowAccess {

    @Invoker("onResize")
    void bigshot$onResize(long l, int width, int height);
}
