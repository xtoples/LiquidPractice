package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandMeta(label={"arena reload", "arenas reload"}, permission="liquidpractice.admin")
public class ArenaReloadCommand {
    public void execute(Player player) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "WARNING! Reloading is not recommended. It may cause the system to malfunction!");
        long st = System.currentTimeMillis();
        Match.cleanup();
        Arena.getArenas().clear();
        Arena.preload();
        long et = System.currentTimeMillis();
        player.sendMessage(CC.translate("&7Arenas were reloaded in &b" + (et - st) + " ms&7."));
    }

}
