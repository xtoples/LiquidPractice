package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandMeta(label = {"refill", "more"}, permission = "liquidpractice.staff")
public class RefillCMD {
    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player);
        if (profile.isInFight()) {
            if (player.getInventory().contains(Material.POTION)) {
                while (player.getInventory().firstEmpty() != -1) {
                    player.getInventory().addItem(this.getPotion());
                }
            }
            if (player.getInventory().contains(Material.MUSHROOM_SOUP)) {
                while (player.getInventory().firstEmpty() != -1) {
                    player.getInventory().addItem(this.getSoup());
                }
            }
        }
    }



    public ItemStack getPotion() {
        return new ItemBuilder(Material.POTION).durability(16421).build();
    }

    public ItemStack getSoup() {
        return new ItemBuilder(Material.MUSHROOM_SOUP).build();
    }
}
