package com.blamejared.bigshot;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("bigshot")
public class BigShotForge {
    
    public BigShotForge() {
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(BigShotForge::registerBindings);
        MinecraftForge.EVENT_BUS.addListener(BigShotForge::onClientTick);
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
