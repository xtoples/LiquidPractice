package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.oitc.OITC;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "OITC leave")
public class OITCLeaveCommand {

    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        OITC activeOITC = LiquidPractice.getInstance().getOITCManager().getActiveOITC();

        if (activeOITC == null) {
            player.sendMessage(CC.RED + "There isn't any active OITC Events.");
            return;
        }

        if (!profile.isInOITC() || !activeOITC.getEventPlayers().containsKey(player.getUniqueId())) {
            player.sendMessage(CC.RED + "You are not apart of the active OITC Event.");
            return;
        }

        LiquidPractice.getInstance().getOITCManager().getActiveOITC().handleLeave(player);
    }

}
