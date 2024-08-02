package com.example.bserveressentials.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;

public class CommandSpectator {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("spectator")
                .requires(cs -> cs.hasPermission(0))
                .executes(context -> execute(context)));
    }

    private static int execute(CommandContext<CommandSource> context) throws CommandSyntaxException {
        CommandSource source = context.getSource();
        Entity entity = source.getEntity();

        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            player.setGameMode(GameType.SPECTATOR);
            player.sendMessage(new StringTextComponent("Changed to Spectator mode"), player.getUUID());
            return 1;
        } else {
            source.sendFailure(new StringTextComponent("This command can only be executed by a player"));
            return 0;
        }
    }
}
