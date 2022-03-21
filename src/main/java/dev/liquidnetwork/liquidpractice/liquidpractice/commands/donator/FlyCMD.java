package dev.liquidnetwork.liquidpractice.liquidpractice.commands.donator;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "fly", permission = "liquidpractice.donator")
public class FlyCMD {

    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());

        if ((profile.isInLobby() || profile.isInQueue()) || profile.getPlayer().hasPermission("liquidpractice.staff")) {
            if (player.hasPermission("liquidpractice.donator") || profile.getPlayer().hasPermission("liquidpractice.staff")) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.updateInventory();
                    player.sendMessage(CC.RED + "You are no longer flying!");
                } else {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.updateInventory();
                    player.sendMessage(CC.GREEN + "You are now flying!");
                }
            }
        } else {
            player.sendMessage(CC.RED + "You cannot fly right now!");
        }
    }

}
