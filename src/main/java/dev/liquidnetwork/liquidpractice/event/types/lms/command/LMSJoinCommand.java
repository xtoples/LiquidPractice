package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMS;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMSState;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "lms join")
public class LMSJoinCommand {

    public static void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        LMS activeLMS = LiquidPractice.getInstance().getLMSManager().getActiveLMS();

        if (profile.isBusy(player) || profile.getParty() != null) {
            player.sendMessage(CC.RED + "You cannot join the lms right now.");
            return;
        }

        if (activeLMS == null) {
            player.sendMessage(CC.RED + "There isn't any active LMS Events right now.");
            return;
        }

        if (activeLMS.getState() != LMSState.WAITING) {
            player.sendMessage(CC.RED + "This LMS Event is currently on-going and cannot be joined.");
            return;
        }

        LiquidPractice.getInstance().getLMSManager().getActiveLMS().handleJoin(player);
    }

}
