package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="lms forcestart", permission="lms.forcestart")
public class LMSForceStartCommand {
    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        profile.getLms().onRound();
    }
}
