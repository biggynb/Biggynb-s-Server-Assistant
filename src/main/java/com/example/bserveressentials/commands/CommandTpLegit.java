package com.example.bserveressentials.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class CommandTpLegit {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tplegit")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("targetName", StringArgumentType.word())
                        .executes(context -> execute(context))));
    }

    private static int execute(CommandContext<CommandSource> context) throws CommandSyntaxException {
        CommandSource source = context.getSource();
        Entity entity = source.getEntity();

        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            String targetName = StringArgumentType.getString(context, "targetName");
            PlayerList playerList = source.getServer().getPlayerList();
            ServerPlayerEntity target = playerList.getPlayerByName(targetName);

            if (target != null) {
                player.teleportTo((ServerWorld) target.getCommandSenderWorld(), target.getX(), target.getY(), target.getZ(), 0.0f, 0.0f);
                player.sendMessage(new StringTextComponent("Teleported to " + targetName), player.getUUID());
            } else {
                player.sendMessage(new StringTextComponent("Player not found"), player.getUUID());
            }
            return 1;
        } else {
            source.sendFailure(new StringTextComponent("This command can only be executed by a player"));
            return 0;
        }
    }
}
