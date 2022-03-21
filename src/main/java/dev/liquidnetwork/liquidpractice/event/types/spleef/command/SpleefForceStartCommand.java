package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import org.bukkit.entity.Player;

@CommandMeta(label={"spleef forcestart"}, permission="spleef.forcestart")
public class SpleefForceStartCommand {
    public void execute(Player player) {
        Profile profile =Profile.getByUuid(player);
        profile.getSpleef().onRound();
    }
}
