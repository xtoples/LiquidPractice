package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourState;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "parkour join")
public class ParkourJoinCommand {

	public static void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Parkour activeParkour = LiquidPractice.getInstance().getParkourManager().getActiveParkour();

		if (profile.isBusy(player) || profile.getParty() != null) {
			player.sendMessage(CC.RED + "You cannot join the parkour right now.");
			return;
		}

		if (activeParkour == null) {
			player.sendMessage(CC.RED + "There isn't any active Parkour Events right now.");
			return;
		}

		if (activeParkour.getState() != ParkourState.WAITING) {
			player.sendMessage(CC.RED + "This Parkour Event is currently on-going and cannot be joined.");
			return;
		}

		LiquidPractice.getInstance().getParkourManager().getActiveParkour().handleJoin(player);
	}

}
