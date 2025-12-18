package com.blamejared.bigshot;

import com.blamejared.bigshot.mixin.WindowAccess;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;

import java.io.File;
import java.util.function.Consumer;

public class BigShot {
    
    public static final KeyMapping KEY_2X_SCREENSHOT = Util.make(() -> new KeyMapping(
            "key.bigshot.take_screenshot_2x",
            InputConstants.UNKNOWN.getValue(),
            KeyMapping.Category.MISC
    ));
    public static final KeyMapping KEY_3X_SCREENSHOT = Util.make(() -> new KeyMapping(
            "key.bigshot.take_screenshot_3x",
            InputConstants.UNKNOWN.getValue(),
            KeyMapping.Category.MISC
    ));
    public static final KeyMapping KEY_4X_SCREENSHOT = Util.make(() -> new KeyMapping(
            "key.bigshot.take_screenshot_4x",
            InputConstants.UNKNOWN.getValue(),
            KeyMapping.Category.MISC
    ));
    
    public static boolean TAKING_SCREENSHOT = false;
    
    public static void takeScreenshot(int scale, Consumer<Component> consumer) {
        
        Minecraft mc = Minecraft.getInstance();
        takeScreenshot(mc.gameDirectory, scale, consumer);
    }
    
    public static void takeScreenshot(File folder, int scale, Consumer<Component> consumer) {
        
        Minecraft mc = Minecraft.getInstance();
        Window window = mc.getWindow();
        WindowAccess windowAccess = (WindowAccess) (Object) window;
        
        int oldWidth = window.getWidth();
        int oldHeight = window.getHeight();
        int newWidth = oldWidth * scale;
        int newHeight = oldHeight * scale;
        int oldGuiScale = window.getGuiScale();
        RenderTarget target = mc.getMainRenderTarget();
        try {
            BigShot.TAKING_SCREENSHOT = true;
            window.setWidth(newWidth);
            window.setHeight(newHeight);
            target.resize(newWidth, newHeight);
            windowAccess.bigshot$onResize(0, newWidth, newHeight);
            window.setGuiScale((oldGuiScale * scale));
            mc.gameRenderer.render(DeltaTracker.ONE, true);
            Screenshot.grab(folder, target, consumer);
        } catch(Exception var18) {
            consumer.accept(Component.translatable("screenshot.failure", var18.getMessage()));
            var18.printStackTrace();
        } finally {
            BigShot.TAKING_SCREENSHOT = false;
            window.setWidth(oldWidth);
            window.setHeight(oldHeight);
            target.resize(oldWidth, oldHeight);
            windowAccess.bigshot$onResize(0, oldWidth, oldHeight);
            window.setGuiScale(oldGuiScale);
        }
        
    }
    
}

