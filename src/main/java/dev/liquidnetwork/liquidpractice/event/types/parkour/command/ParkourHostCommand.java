package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = { "parkour host" }, permission = "practice.host.parkour")
public class ParkourHostCommand {

	public static void execute(Player player) {
		if (LiquidPractice.getInstance().getParkourManager().getActiveParkour() != null) {
			player.sendMessage(CC.RED + "There is already an active Parkour Event.");
			return;
		}

		if (!LiquidPractice.getInstance().getParkourManager().getCooldown().hasExpired()) {
			player.sendMessage(CC.RED + "There is an active cooldown for the Parkour Event.");
			return;
		}

		LiquidPractice.getInstance().getParkourManager().setActiveParkour(new Parkour(player));

		for (Player other : LiquidPractice.getInstance().getServer().getOnlinePlayers()) {
			Profile profile = Profile.getByUuid(other.getUniqueId());

			if (profile.isInLobby()) {
				if (!profile.getKitEditor().isActive()) {
					profile.refreshHotbar();
				}
			}
		}
	}

}
