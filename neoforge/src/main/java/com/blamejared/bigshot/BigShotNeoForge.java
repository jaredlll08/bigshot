package com.blamejared.bigshot;


import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = "bigshot", dist = Dist.CLIENT)
public class BigShotNeoForge {
    
    public BigShotNeoForge(IEventBus eventBus) {
        
        eventBus.addListener(BigShotNeoForge::registerBindings);
        NeoForge.EVENT_BUS.addListener(BigShotNeoForge::onClientTick);
    }
    
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        
        event.register(BigShot.KEY_2X_SCREENSHOT);
        event.register(BigShot.KEY_3X_SCREENSHOT);
        event.register(BigShot.KEY_4X_SCREENSHOT);
    }
    
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        
        while(BigShot.KEY_2X_SCREENSHOT.consumeClick()) {
            BigShot.takeScreenshot(2, component -> Minecraft.getInstance().gui.getChat().addMessage(component));
        }
        while(BigShot.KEY_3X_SCREENSHOT.consumeClick()) {
            BigShot.takeScreenshot(3, component -> Minecraft.getInstance().gui.getChat().addMessage(component));
        }
        while(BigShot.KEY_4X_SCREENSHOT.consumeClick()) {
            BigShot.takeScreenshot(4, component -> Minecraft.getInstance().gui.getChat().addMessage(component));
        }
    }
    
}
