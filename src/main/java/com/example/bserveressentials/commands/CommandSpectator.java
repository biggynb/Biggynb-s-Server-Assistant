import com.mojang.brigadier.CommandDispatcher;
// Import the appropriate Forge class for permission checks
import net.minecraftforge.fml.commands.UserCommand; // Adjust based on your Forge version
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;

public class CommandSpectator {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("spectator")
                .requires(source -> source instanceof UserCommand && ((UserCommand) source).hasPermissionLevel(0)) // Adjust based on your Forge class
                .executes(context -> {
                    // Check if source is a player
                    if (source instanceof ServerPlayerEntity) {
                        ServerPlayerEntity player = (ServerPlayerEntity) source;
                        player.setGameType(GameType.SPECTATOR);
                        player.sendMessage(new StringTextComponent("Changed to Spectator mode"), player.getEntityId()); // Consider using getEntityId() or a custom method
                        return 1;
                    }
                    return 0; // No action if not a player
                }));
    }
}
