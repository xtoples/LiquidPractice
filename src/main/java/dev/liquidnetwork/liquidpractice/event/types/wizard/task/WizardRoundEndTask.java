package dev.liquidnetwork.liquidpractice.event.types.wizard.task;

import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardState;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardTask;

public class WizardRoundEndTask extends WizardTask {

	public WizardRoundEndTask(Wizard wizard) {
		super(wizard, WizardState.ROUND_ENDING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 3) {
			if (this.getWizard().canEnd()) {
				this.getWizard().end();
			} else {
				this.getWizard().onRound();
			}
		}
	}

}
