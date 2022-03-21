package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "wizard leave")
public class WizardLeaveCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Wizard activeWizard= LiquidPractice.getInstance().getWizardManager().getActiveWizard();

		if (activeWizard == null) {
			player.sendMessage(CC.RED + "There isn't any active Wizard Events.");
			return;
		}

		if (!profile.isInWizard() || !activeWizard.getEventPlayers().containsKey(player.getUniqueId())) {
			player.sendMessage(CC.RED + "You are not apart of the active Wizard Event.");
			return;
		}

		activeWizard.handleLeave(player);
	}

}
