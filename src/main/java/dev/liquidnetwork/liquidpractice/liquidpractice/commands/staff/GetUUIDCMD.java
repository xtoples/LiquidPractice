package dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff;

import dev.liquidnetwork.liquidpractice.LiquidPracticeCache;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandMeta(label="uuid", permission="liquidpractice.admin")
public class GetUUIDCMD {
    public void execute(Player player, @CPL("name") String name) {
        UUID uuid = LiquidPracticeCache.getUUID(name);

        if (uuid == null) {
            player.sendMessage(CC.RED + "Error!");
        }

        if (uuid != null) {
            player.sendMessage(CC.GREEN + uuid.toString());
        }

    }
}
