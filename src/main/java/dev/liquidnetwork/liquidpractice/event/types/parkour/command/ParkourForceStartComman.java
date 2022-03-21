package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import org.bukkit.entity.Player;

@CommandMeta(label={"parkour forcestart"}, permission="parkour.forcestart")
public class ParkourForceStartComman {
    public void execute(Player player) {
        Profile profile =Profile.getByUuid(player);
        profile.getParkour().onRound();
    }
}
