package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "wizard host" }, permission = "liquidPractice.host.wizard")
public class WizardHostCommand {

	public static void execute(Player player) {
		if (LiquidPractice.getInstance().getWizardManager().getActiveWizard() != null) {
			player.sendMessage(CC.RED + "There is already an active Wizard Event.");
			return;
		}

		if (!LiquidPractice.getInstance().getWizardManager().getCooldown().hasExpired()) {
			player.sendMessage(CC.RED + "There is a Wizard Event cooldown active.");
			return;
		}

		LiquidPractice.getInstance().getWizardManager().setActiveWizard(new Wizard(player));

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
