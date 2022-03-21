package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "spleef host" }, permission = "practice.host.spleef")
public class SpleefHostCommand {

	public static void execute(Player player) {
		if (LiquidPractice.getInstance().getSpleefManager().getActiveSpleef() != null) {
			player.sendMessage(CC.RED + "There is already an active Spleef Event.");
			return;
		}

		if (!LiquidPractice.getInstance().getSpleefManager().getCooldown().hasExpired()) {
			player.sendMessage(CC.RED + "There is an active cooldown for the Spleef Event.");
			return;
		}

		LiquidPractice.getInstance().getSpleefManager().setActiveSpleef(new Spleef(player));

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
