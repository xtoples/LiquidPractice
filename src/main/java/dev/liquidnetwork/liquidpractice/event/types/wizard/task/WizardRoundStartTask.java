package dev.liquidnetwork.liquidpractice.event.types.wizard.task;

import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardState;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardTask;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WizardRoundStartTask extends WizardTask {

	public WizardRoundStartTask(Wizard wizard) {
		super(wizard, WizardState.ROUND_STARTING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 3) {
			this.getWizard().broadcastMessage(CC.AQUA + "The round has started!");
			this.getWizard().setEventTask(null);
			this.getWizard().setState(WizardState.ROUND_FIGHTING);

			Player playerA = this.getWizard().getRoundPlayerA().getPlayer();
			Player playerB = this.getWizard().getRoundPlayerB().getPlayer();

			if (playerA != null) {
				playerA.playSound(playerA.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				PlayerUtil.allowMovement(playerA);
			}

			if (playerB != null) {
				playerB.playSound(playerB.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				PlayerUtil.allowMovement(playerB);
			}

			(this.getWizard()).setRoundStart(System.currentTimeMillis());
		} else {
			int seconds = getSeconds();
			Player playerA = this.getWizard().getRoundPlayerA().getPlayer();
			Player playerB = this.getWizard().getRoundPlayerB().getPlayer();

			if (playerA != null) {
				playerA.playSound(playerA.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
			}

			if (playerB != null) {
				playerB.playSound(playerB.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
			}

			this.getWizard().broadcastMessage("&b" + seconds + "...");
		}
	}

}
