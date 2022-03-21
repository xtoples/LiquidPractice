package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.oitc.OITC;
import dev.liquidnetwork.liquidpractice.event.types.oitc.OITCState;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "OITC join")
public class OITCJoinCommand {

    public static void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        OITC activeOITC = LiquidPractice.getInstance().getOITCManager().getActiveOITC();

        if (profile.isBusy(player) || profile.getParty() != null) {
            player.sendMessage(CC.RED + "You cannot join the OITC right now.");
            return;
        }

        if (activeOITC == null) {
            player.sendMessage(CC.RED + "There isn't any active KoTH Events right now.");
            return;
        }

        if (activeOITC.getState() != OITCState.WAITING) {
            player.sendMessage(CC.RED + "This KoTH Event is currently on-going and cannot be joined.");
            return;
        }

        LiquidPractice.getInstance().getOITCManager().getActiveOITC().handleJoin(player);
    }

}
