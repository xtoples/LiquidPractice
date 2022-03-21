package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandMeta(label={"arena seticon"}, permission="liquidpractice.admin")
public class ArenaSetIconCommand {
    public void execute(Player player, @CPL("arena") Arena arena) {
    ItemStack item = player.getItemInHand();
    if (item == null) {
        player.sendMessage(CC.translate("&7Please hold a valid item in your hand!"));
    }
    else if (arena == null) {
        player.sendMessage(CC.translate("&7An arena with that name does not exist."));
    } else {
        arena.setDisplayIcon(item);
        arena.save();
        player.sendMessage(CC.translate("&7Successfully set the &barena icon &7to the &bitem&7 in your hand!"));
    }
  }
}
