package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"kit setkb", "kit setknockback"}, permission = "liquidpractice.admin")
public class KitSetKnockbackProfileCommand {

    public void execute(Player player, Kit kit, @CPL("KnockbackProfile") String knockbackProfile) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
            return;
        }

        kit.setKnockbackProfile(knockbackProfile);
        kit.save();

        player.sendMessage(CC.translate("&7Updated knockback profile for &b" + kit.getName() +  " &7to &b" + knockbackProfile));
    }

}
