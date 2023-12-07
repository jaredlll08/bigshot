package com.blamejared.bigshot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;

public class BigShotFabric implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        
        KeyBindingHelper.registerKeyBinding(BigShot.KEY_2X_SCREENSHOT);
        KeyBindingHelper.registerKeyBinding(BigShot.KEY_3X_SCREENSHOT);
        KeyBindingHelper.registerKeyBinding(BigShot.KEY_4X_SCREENSHOT);
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(BigShot.KEY_2X_SCREENSHOT.consumeClick()) {
                BigShot.takeScreenshot(2, component -> Minecraft.getInstance().gui.getChat().addMessage(component));
            }
            while(BigShot.KEY_3X_SCREENSHOT.consumeClick()) {
                BigShot.takeScreenshot(3, component -> Minecraft.getInstance().gui.getChat().addMessage(component));
            }
            while(BigShot.KEY_4X_SCREENSHOT.consumeClick()) {
                BigShot.takeScreenshot(4, component -> Minecraft.getInstance().gui.getChat().addMessage(component));
            }
        });
    }
    
}
