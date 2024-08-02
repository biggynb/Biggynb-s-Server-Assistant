package com.example.bserveressentials.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class CommandTpLegit {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(literal("tplegit")
                .requires(source -> source.hasPermissionLevel(2))  // Use the correct permission level method
                .then(argument("target", StringArgumentType.string())
                        .executes(CommandTpLegit::execute)));
    }

    private static int execute(CommandContext<CommandSource> context) {
        String targetName = StringArgumentType.getString(context, "target");
        ServerPlayerEntity player;
        try {
            player = context.getSource().asPlayer();
        } catch (Exception e) {
            context.getSource().sendErrorMessage(new StringTextComponent("You must be a player to use this command!"));
            return 1;
        }

        MinecraftServer server = context.getSource().getServer();
        PlayerList playerList = server.getPlayerList();
        ServerPlayerEntity target = playerList.getPlayerByUsername(targetName);

        if (target == null) {
            player.sendMessage(new StringTextComponent("Player not found"), player.getUUID());
            return 1;
        }

        ServerWorld targetWorld = (ServerWorld) target.level;
        player.teleportTo(targetWorld, target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());  // Use correct methods

        player.sendMessage(new StringTextComponent("Teleported to " + targetName), player.getUUID());
        return Command.SINGLE_SUCCESS;
    }
}
