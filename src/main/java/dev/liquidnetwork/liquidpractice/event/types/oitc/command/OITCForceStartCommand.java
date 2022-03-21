package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="OITC forcestart", permission="OITC.forcestart")
public class OITCForceStartCommand {
    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        profile.getOITC().onRound();
    }
}
