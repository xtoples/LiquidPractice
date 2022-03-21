package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandMeta(label={"liquidpractice rename"}, permission="liquidpractice.admin")
public class RenameCMD {
    public void execute(Player p, @CPL("name") String name) {
        if (p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)) {
            p.sendMessage(ChatColor.RED + "Hold something in your hand.");
            return;
        }
        ItemStack hand=p.getItemInHand();
        ItemMeta meta=hand.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name.replace("_", " ")));
        hand.setItemMeta(meta);
        p.getInventory().setItemInHand(hand);
        p.updateInventory();
        p.sendMessage(CC.translate("&bThe Item in your hand has been renamed!"));
    }
}
