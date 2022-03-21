package dev.liquidnetwork.liquidpractice.party.command;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"party chat", "p chat"})
public class PartyChatCommand {
    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player);
        if (profile.getParty() == null) {
            player.sendMessage(CC.translate("&8[&b&lParty&8] &7You don't have a party."));
            return;
        }
        profile.getSettings().setPartyChat(!profile.getSettings().isPartyChat());
        player.sendMessage(CC.translate((profile.getSettings().isPartyChat() ? "&aYou are now speaking in party chat!" : "&aYou are now speaking in Global Chat")));
    }
}
