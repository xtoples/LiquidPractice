package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "parkour leave")
public class ParkourLeaveCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Parkour activeParkour = LiquidPractice.getInstance().getParkourManager().getActiveParkour();

		if (activeParkour == null) {
			player.sendMessage(CC.RED + "There isn't any active Parkour Events.");
			return;
		}

		if (!profile.isInParkour() || !activeParkour.getEventPlayers().containsKey(player.getUniqueId())) {
			player.sendMessage(CC.RED + "You are not apart of the active Parkour Event.");
			return;
		}

		LiquidPractice.getInstance().getParkourManager().getActiveParkour().handleLeave(player);
	}

}
