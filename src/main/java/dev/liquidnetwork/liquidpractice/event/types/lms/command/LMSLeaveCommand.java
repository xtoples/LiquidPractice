package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMS;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "lms leave")
public class LMSLeaveCommand {

    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        LMS activeLMS = LiquidPractice.getInstance().getLMSManager().getActiveLMS();

        if (activeLMS == null) {
            player.sendMessage(CC.RED + "There isn't any active LMS Events.");
            return;
        }

        if (!profile.isInLMS() || !activeLMS.getEventPlayers().containsKey(player.getUniqueId())) {
            player.sendMessage(CC.RED + "You are not apart of the active LMS Event.");
            return;
        }

        LiquidPractice.getInstance().getLMSManager().getActiveLMS().handleLeave(player);
    }

}
