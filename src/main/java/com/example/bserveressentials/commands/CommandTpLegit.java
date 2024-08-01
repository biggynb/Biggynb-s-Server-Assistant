package com.example.bserveressentials.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class CommandTpLegit {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tplegit")
            .requires(source -> source.hasPermissionLevel(0))
            .then(Commands.argument("target", StringArgumentType.string())
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().asPlayer();
                    String targetName = StringArgumentType.getString(context, "target");
                    ServerPlayerEntity target = context.getSource().getServer().getPlayerList().getPlayerByUsername(targetName);
                    if (target != null) {
                        player.teleport((ServerWorld) target.world, target.getPosX(), target.getPosY(), target.getPosZ(), target.rotationYaw, target.rotationPitch);
                        player.sendMessage(new StringTextComponent("Teleported to " + targetName), player.getUniqueID());
                    } else {
                        player.sendMessage(new StringTextComponent("Player not found"), player.getUniqueID());
                    }
                    return 1;
                }))
            .then(Commands.argument("x", DoubleArgumentType.doubleArg())
                .then(Commands.argument("y", DoubleArgumentType.doubleArg())
                    .then(Commands.argument("z", DoubleArgumentType.doubleArg())
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().asPlayer();
                            double x = DoubleArgumentType.getDouble(context, "x");
                            double y = DoubleArgumentType.getDouble(context, "y");
                            double z = DoubleArgumentType.getDouble(context, "z");
                            player.teleport((ServerWorld) player.world, x, y, z, player.rotationYaw, player.rotationPitch);
                            player.sendMessage(new StringTextComponent("Teleported to coordinates"), player.getUniqueID());
                            return 1;
                        })))));
    }
}
