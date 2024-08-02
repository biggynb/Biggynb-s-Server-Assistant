package com.example.bserveressentials;

import com.example.bserveressentials.commands.CommandSpectator;
import com.example.bserveressentials.commands.CommandSurvival;
import com.example.bserveressentials.commands.CommandTpLegit;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod("bserveressentials")
@EventBusSubscriber(modid = "bserveressentials", bus = Bus.FORGE)
public class BServerEssentials {

    public BServerEssentials() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Initialization code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Client-side code
    }

    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {
        CommandSpectator.register(event.getDispatcher());
        CommandSurvival.register(event.getDispatcher());
        CommandTpLegit.register(event.getDispatcher());
    }
}
