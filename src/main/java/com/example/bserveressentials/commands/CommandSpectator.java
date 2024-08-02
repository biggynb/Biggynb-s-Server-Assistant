package com.example.bserveressentials.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;

public class CommandSpectator {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("spectator")
            .requires(source -> source.hasPermissionLevel(0))
            .executes(context -> {
                ServerPlayerEntity player = context.getSource().asPlayer();
                if (player != null) {
                    player.setGameType(GameType.SPECTATOR);
                    player.sendMessage(new StringTextComponent("Changed to Spectator mode"), player.getUniqueID());
                }
                return 1;
            }));
    }
}
