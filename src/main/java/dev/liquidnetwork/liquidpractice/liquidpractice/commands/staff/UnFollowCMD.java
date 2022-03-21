package dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.*;

@CommandMeta(label = { "unfollow" }, permission = "liquidpractice.staff")
public class UnFollowCMD
{
    public void execute(final Player player) {
        final Profile profile = Profile.getByUuid(player.getUniqueId());
        if (!profile.isFollowMode()) {
            player.sendMessage(CC.translate("&cYou aren't following anybody!"));
            return;
        }

        Profile.getByUuid(profile.getFollowing().getUniqueId()).getFollower().remove(player);
        profile.setFollowMode(false);
        profile.setSilent(false);
        profile.setFollowing(null);

        player.sendMessage(CC.translate("&cYou have &cexited &7follow mode!"));
    }
}
