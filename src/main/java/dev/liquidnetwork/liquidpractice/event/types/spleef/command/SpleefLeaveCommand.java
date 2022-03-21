package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "spleef leave")
public class SpleefLeaveCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Spleef activeSpleef = LiquidPractice.getInstance().getSpleefManager().getActiveSpleef();

		if (activeSpleef == null) {
			player.sendMessage(CC.RED + "There isn't any active Spleef Events.");
			return;
		}

		if (!profile.isInSpleef() || !activeSpleef.getEventPlayers().containsKey(player.getUniqueId())) {
			player.sendMessage(CC.RED + "You are not apart of the active Spleef Event.");
			return;
		}

		LiquidPractice.getInstance().getSpleefManager().getActiveSpleef().handleLeave(player);
	}

}
