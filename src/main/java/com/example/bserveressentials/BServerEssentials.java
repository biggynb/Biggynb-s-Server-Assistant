package com.example.bserveressentials;

import com.example.bserveressentials.commands.CommandSpectator;
import com.example.bserveressentials.commands.CommandSurvival;
import com.example.bserveressentials.commands.CommandTpLegit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BServerEssentials.MODID)
public class BServerEssentials {
    public static final String MODID = "bserveressentials";
    private static final Logger LOGGER = LogManager.getLogger();

    public BServerEssentials() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onDedicatedServerSetup);
        MinecraftForge.EVENT_BUS.register(this); // Ensure this line is present to register events
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", net.minecraft.block.Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Got game settings");
    }

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        LOGGER.info("Load Complete");
    }

    private void onDedicatedServerSetup(final FMLDedicatedServerSetupEvent event) {
        LOGGER.info("Dedicated Server Setup");
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        CommandSpectator.register(event.getDispatcher());
        CommandSurvival.register(event.getDispatcher());
        CommandTpLegit.register(event.getDispatcher());
    }
}
