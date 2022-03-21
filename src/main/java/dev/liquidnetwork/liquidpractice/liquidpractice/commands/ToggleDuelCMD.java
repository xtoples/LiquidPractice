package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"tduel", "toggleduel", "dueltoggle"})
public class ToggleDuelCMD {

    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        boolean duelstate = profile.getSettings().isReceiveDuelRequests();
        player.sendMessage(CC.translate("&7Receiving Duels: " + (!duelstate ? "&aOn" : "&cOff")));
        profile.getSettings().setReceiveDuelRequests(!duelstate);
    }

}
