package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena setdisplayname", permission = "liquidpractice.admin")
public class ArenaSetDisplayNameCommand {
     public void execute(Player player, @CPL("arena") String arenaname, @CPL("displayname") String displayname) {
         Arena arena = Arena.getByName(arenaname);
         if (arena == null) {
             player.sendMessage(CC.translate("&7Arena does not exist"));
             return;
         }
         arena.setDisplayName(displayname);
         arena.save();
         player.sendMessage(CC.translate("&7Successfully updated the arena &b" + arena.getName() + "'s &7display name."));


     }
}
