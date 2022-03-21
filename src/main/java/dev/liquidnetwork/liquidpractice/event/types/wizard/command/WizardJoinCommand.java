package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardState;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "wizard join")
public class WizardJoinCommand {

	public static void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Wizard activeWizard= LiquidPractice.getInstance().getWizardManager().getActiveWizard();

		if (profile.isBusy(player) || profile.getParty() != null) {
			player.sendMessage(CC.RED + "You cannot join the wizard right now.");
			return;
		}

		if (activeWizard == null) {
			player.sendMessage(CC.RED + "There isn't any active Wizard Events right now.");
			return;
		}

		if (activeWizard.getState() != WizardState.WAITING) {
			player.sendMessage(CC.RED + "This Wizard Event is currently on-going and cannot be joined.");
			return;
		}

		activeWizard.handleJoin(player);
	}

}
