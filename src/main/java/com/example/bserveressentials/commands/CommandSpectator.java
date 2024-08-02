package com.example.bserveressentials.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class CommandSpectator {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(literal("spectator")
                .requires(source -> source.hasPermissionLevel(2))  // Use the correct permission level method
                .executes(CommandSpectator::execute));
    }

    private static int execute(CommandContext<CommandSource> context) {
        ServerPlayerEntity player;
        try {
            player = context.getSource().asPlayer();
        } catch (Exception e) {
            context.getSource().sendErrorMessage(new StringTextComponent("You must be a player to use this command!"));
            return 1;
        }

        player.setGameType(GameType.SPECTATOR);
        player.sendMessage(new StringTextComponent("Changed to Spectator mode"), player.getUUID());  // Use correct method for UUID
        return Command.SINGLE_SUCCESS;
    }
}
