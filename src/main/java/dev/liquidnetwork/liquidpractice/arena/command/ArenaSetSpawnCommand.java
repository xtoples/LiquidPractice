package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena setspawn", permission = "liquidpractice.admin")
public class ArenaSetSpawnCommand {

    public void execute(Player player, @CPL("arena") Arena arena, @CPL("[1|2]") Integer pos) {
        if (arena == null) {
            player.sendMessage(CC.translate("&7An arena with that name does not exist."));
            return;
        }

        Location loc=new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(),
                player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());

        if (pos.equals(1)) {
            arena.setSpawn1(loc);
        } else if (pos.equals(2)) {
            arena.setSpawn2(loc);
        }
        player.sendMessage(CC.translate("&7Successfully updated the position of &b" + arena.getName() + "&8&o (&7&oPosition: " + pos + "&8&o)"));
        arena.save();

    }

}