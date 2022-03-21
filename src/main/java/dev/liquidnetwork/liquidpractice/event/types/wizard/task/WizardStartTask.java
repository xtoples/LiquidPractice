package dev.liquidnetwork.liquidpractice.event.types.wizard.task;

import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardState;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardTask;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;

public class WizardStartTask extends WizardTask {

	public WizardStartTask(Wizard wizard) {
		super(wizard, WizardState.WAITING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 120) {
			this.getWizard().end();
			return;
		}

		if (this.getWizard().getPlayers().size() <= 1 && this.getWizard().getCooldown() != null) {
			this.getWizard().setCooldown(null);
			this.getWizard().broadcastMessage("&cThere are not enough players for the brackets to start.");
		}

		if (this.getWizard().getPlayers().size() == Wizard.getMaxPlayers() || (getTicks() >= 30 && this.getWizard().getPlayers().size() >= 2)) {
			if (this.getWizard().getCooldown() == null) {
				this.getWizard().setCooldown(new Cooldown(11_000));
				this.getWizard().broadcastMessage("&fThe Wizard will start in &b10 seconds&f...");
			} else {
				if (this.getWizard().getCooldown().hasExpired()) {
					this.getWizard().setState(WizardState.ROUND_STARTING);
					this.getWizard().onRound();
					this.getWizard().setTotalPlayers(this.getWizard().getPlayers().size());
					this.getWizard().setEventTask(new WizardRoundStartTask(this.getWizard()));
				}
			}
		}

		if (getTicks() % 10 == 0) {
			this.getWizard().announce();
		}
	}

}
