package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefState;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "spleef join")
public class SpleefJoinCommand {

	public static void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Spleef activeSpleef = LiquidPractice.getInstance().getSpleefManager().getActiveSpleef();

		if (profile.isBusy(player) || profile.getParty() != null) {
			player.sendMessage(CC.RED + "You cannot join the spleef right now.");
			return;
		}

		if (activeSpleef == null) {
			player.sendMessage(CC.RED + "There isn't any active Spleef Events right now.");
			return;
		}

		if (activeSpleef.getState() != SpleefState.WAITING) {
			player.sendMessage(CC.RED + "This Spleef Event is currently on-going and cannot be joined.");
			return;
		}

		LiquidPractice.getInstance().getSpleefManager().getActiveSpleef().handleJoin(player);
	}

}
