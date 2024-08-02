package com.example.bserveressentials.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.server.management.PlayerList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;


public class CommandSpectator {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spectator")
                .requires(source -> source.hasPermission(2))  // Use the correct permission method
                .executes(CommandSpectator::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();  // Use correct method to get player
        player.setGameMode(GameType.SPECTATOR);
        player.sendSystemMessage(Component.literal("Changed to Spectator mode"));
        return Command.SINGLE_SUCCESS;
    }
}
