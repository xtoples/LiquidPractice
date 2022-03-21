package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="arena disablepearls", permission="liquidpractice.admin")
public class ArenaDisablePearlsCommand {
    public void execute(Player player, @CPL("arena") Arena arena) {
        if (arena == null) {
            player.sendMessage(CC.translate("&7That arena does not exist."));
            return;
        }
        if (arena.isDisablePearls()) {
            arena.setDisablePearls(false);
            player.sendMessage(CC.translate("&7Successfully &benabled &7pearls in the arena &b" + arena.getName()));
        } else {
            arena.setDisablePearls(true);
            player.sendMessage(CC.translate("&7Successfully &cdisabled &7pearls in the arena &b" + arena.getName()));
        }
    }
}
