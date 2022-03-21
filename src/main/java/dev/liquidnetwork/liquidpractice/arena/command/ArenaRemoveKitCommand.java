package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label ={"arena removekit", "arena deletekit"}, permission = "liquidpractice.admin")
public class ArenaRemoveKitCommand {

    public void execute(Player player, @CPL("arena") Arena arena, @CPL("kit") Kit kit) {
        if (arena == null) {
            player.sendMessage(CC.translate("&7An arena with that name does not exist"));
            return;
        }

        if (kit == null) {
            player.sendMessage(CC.translate("&7A kit with that name does not exist."));
            return;
        }

        if (arena.getKits().contains(kit.getName())) {
            arena.getKits().remove(kit.getName());

            player.sendMessage(CC.translate("&7Successfully removed the kit &b" + kit.getName() + " &7from &b" + arena.getName()));
            arena.save();
        }
    }

}