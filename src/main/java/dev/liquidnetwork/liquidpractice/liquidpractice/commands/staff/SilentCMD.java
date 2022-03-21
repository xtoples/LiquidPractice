package dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "silent", permission = "liquidpractice.staff")
public class SilentCMD {


    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());

        if (profile.isFollowMode()) {
            player.sendMessage(CC.translate("&cYou are currently following somebody!"));
            return;
        }

        profile.setSilent(!profile.isSilent());

        player.sendMessage(CC.translate("&7You have " + (profile.isSilent() ? "&aenabled" : "&cdisabled") + " &7silent mode."));
    }

    public void execute(Player player, @CPL("player") Player target) {
        Profile profile = Profile.getByUuid(target.getUniqueId());

        if (profile.isFollowMode()) {
            player.sendMessage(CC.translate("&cThat person is currently following somebody!"));
            return;
        }

        profile.setSilent(!profile.isSilent());

        player.sendMessage(CC.translate("&7You have " + (profile.isSilent() ? "&aenabled" : "&cdisabled") + " &7silent mode for " + target.getName() + "."));
    }

}
