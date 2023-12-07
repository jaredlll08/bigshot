package com.blamejared.bigshot;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;

import java.io.File;
import java.util.function.Consumer;

public class BigShot {
    
    public static final KeyMapping KEY_2X_SCREENSHOT = Util.make(() -> new KeyMapping(
            "key.bigshot.take_screenshot_2x",
            InputConstants.UNKNOWN.getValue(),
            "key.categories.misc"
    ));
    public static final KeyMapping KEY_3X_SCREENSHOT = Util.make(() -> new KeyMapping(
            "key.bigshot.take_screenshot_3x",
            InputConstants.UNKNOWN.getValue(),
            "key.categories.misc"
    ));
    public static final KeyMapping KEY_4X_SCREENSHOT = Util.make(() -> new KeyMapping(
            "key.bigshot.take_screenshot_4x",
            InputConstants.UNKNOWN.getValue(),
            "key.categories.misc"
    ));
    
    public static boolean TAKING_SCREENSHOT = false;
    
    public static void takeScreenshot(int scale, Consumer<Component> consumer) {
        
        Minecraft mc = Minecraft.getInstance();
        if(!RenderSystem.isOnRenderThread()) {
            RenderSystem.recordRenderCall(() -> takeScreenshot(mc.gameDirectory, scale, consumer));
        } else {
            takeScreenshot(mc.gameDirectory, scale, consumer);
        }
    }
    
    public static void takeScreenshot(File folder, int scale, Consumer<Component> consumer) {
        
        Minecraft mc = Minecraft.getInstance();
        int oldWidth = mc.getWindow().getWidth();
        int oldHeight = mc.getWindow().getHeight();
        RenderTarget newTarget = new TextureTarget(oldWidth * scale, oldHeight * scale, true, Minecraft.ON_OSX);
        try {
            BigShot.TAKING_SCREENSHOT = true;
            mc.levelRenderer.graphicsChanged();
            mc.getWindow().setWidth(oldWidth * scale);
            mc.getWindow().setHeight(oldHeight * scale);
            newTarget.bindWrite(true);
            mc.gameRenderer.render(1.0F, 0L, true);
            Screenshot.grab(folder, newTarget, consumer);
        } catch(Exception var18) {
            consumer.accept(Component.translatable("screenshot.failure", var18.getMessage()));
            var18.printStackTrace();
        } finally {
            BigShot.TAKING_SCREENSHOT = false;
            mc.getWindow().setWidth(oldWidth);
            mc.getWindow().setHeight(oldHeight);
            mc.levelRenderer.graphicsChanged();
            newTarget.destroyBuffers();
            mc.getMainRenderTarget().bindWrite(true);
            
        }
        
    }
    
}

