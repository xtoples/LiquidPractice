package dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandMeta(label = {"follow"}, permission = "liquidpractice.staff")
public class FollowCMD {
    public void execute(final Player player, @CPL("player") final Player target) {
        final Profile profile = Profile.getByUuid(player.getUniqueId());
        if (profile.isFollowMode()) {
            player.sendMessage(CC.translate("&c[&b&lStaff&c] &7You are already following somebody, /unfollow before following other player again."));
            return;
        }
        profile.setFollowMode(true);
        profile.setSilent(true);
        profile.setFollowing(target);
        Profile.getByUuid(target.getUniqueId()).getFollower().add(player);
        player.sendMessage(CC.translate("&c[&b&lStaff&c] &7You have &bstarted &7following &b" + target.getName() + "&7."));

        Profile targetProfile = Profile.getByUuid(target.getUniqueId());
        if (targetProfile.isInSomeSortOfFight()) {
            if (targetProfile.isInMatch()) {
                Bukkit.getScheduler().runTaskLaterAsynchronously(LiquidPractice.getInstance(), () -> player.chat("/spec " + target.getName()), 20L);
            }
        }
    }
}
