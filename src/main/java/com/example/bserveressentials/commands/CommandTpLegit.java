package com.example.bserveressentials.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.server.management.PlayerList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;


public class CommandTpLegit {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tplegit")
                .requires(source -> source.hasPermission(2))  // Use the correct permission method
                .then(Commands.argument("targetName", StringArgumentType.word())
                        .executes(CommandTpLegit::execute)));
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String targetName = StringArgumentType.getString(context, "targetName");
        ServerPlayer player = context.getSource().getPlayerOrException();  // Use correct method to get player
        PlayerList playerList = context.getSource().getServer().getPlayerList();
        ServerPlayer target = playerList.getPlayerByName(targetName);  // Use correct method to get player by name

        if (target != null) {
            Level targetWorld = target.getLevel();
            player.teleportTo(targetWorld, target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());
            player.sendSystemMessage(Component.literal("Teleported to " + targetName));
        } else {
            player.sendSystemMessage(Component.literal("Player not found"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
