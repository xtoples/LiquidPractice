package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"arena remove", "arena delete"}, permission = "liquidpractice.admin")
public class ArenaRemoveCommand {

    public void execute(Player player, @CPL("name") String name) {
        if (name == null) {
            player.sendMessage(CC.translate("&7Please provide a valid name."));
            return;
        }
        Arena arena = Arena.getByName(name);

        if (arena != null) {
            arena.delete();
            Arena.getArenas().remove(arena);
            player.sendMessage(CC.translate("&7Successfully removed the arena &b" + name));
        }
    }

}