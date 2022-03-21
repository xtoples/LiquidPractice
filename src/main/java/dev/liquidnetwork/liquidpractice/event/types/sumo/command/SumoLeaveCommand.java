package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.sumo.Sumo;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "sumo leave")
public class SumoLeaveCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Sumo activeSumo = LiquidPractice.getInstance().getSumoManager().getActiveSumo();

		if (activeSumo == null) {
			player.sendMessage(CC.RED + "There isn't an active Sumo Event.");
			return;
		}

		if (!profile.isInSumo() || !activeSumo.getEventPlayers().containsKey(player.getUniqueId())) {
			player.sendMessage(CC.RED + "You are not apart of the active Sumo Event.");
			return;
		}

		LiquidPractice.getInstance().getSumoManager().getActiveSumo().handleLeave(player);
	}

}
