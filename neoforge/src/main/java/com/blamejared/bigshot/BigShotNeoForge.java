package com.blamejared.bigshot;


import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;

@Mod("bigshot")
public class BigShotNeoForge {
    
    public BigShotNeoForge() {
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(BigShotNeoForge::registerBindings);
        NeoForge.EVENT_BUS.addListener(BigShotNeoForge::onClientTick);
    }
    
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        
        event.register(BigShot.KEY_2X_SCREENSHOT);
        event.register(BigShot.KEY_3X_SCREENSHOT);
        event.register(BigShot.KEY_4X_SCREENSHOT);
    }
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        
        if(event.phase == TickEvent.Phase.END) {
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
    
}
