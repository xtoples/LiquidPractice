package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandMeta(label={"kit seticon"}, permission = "liquidpractice.admin")
public class KitSetIconCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        ItemStack item=player.getItemInHand();
        if (item == null) {
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Please hold a valid item in your hand!"));
        } else if (kit == null) {
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7That kit does not exist."));
        } else {
            kit.setDisplayIcon(item);
            kit.save();
            player.sendMessage(CC.translate("&8[&b&lArray&8] &aKit Icon set!"));
        }
    }
}
