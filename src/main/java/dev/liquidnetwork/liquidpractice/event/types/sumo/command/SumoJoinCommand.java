package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.sumo.Sumo;
import dev.liquidnetwork.liquidpractice.event.types.sumo.SumoState;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "sumo join")
public class SumoJoinCommand {

	public static void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Sumo activeSumo = LiquidPractice.getInstance().getSumoManager().getActiveSumo();

		if (profile.isBusy(player) || profile.getParty() != null) {
			player.sendMessage(CC.RED + "You cannot join the Sumo Event right now.");
			return;
		}

		if (activeSumo == null) {
			player.sendMessage(CC.RED + "There isn't an active Sumo Event.");
			return;
		}

		if (activeSumo.getState() != SumoState.WAITING) {
			player.sendMessage(CC.RED + "That Sumo Event is currently on-going and cannot be joined.");
			return;
		}

		LiquidPractice.getInstance().getSumoManager().getActiveSumo().handleJoin(player);
	}

}
