package dev.liquidnetwork.liquidpractice.event.types.wizard;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public abstract class WizardTask extends BukkitRunnable {

	private int ticks;
	private Wizard wizard;
	private WizardState eventState;

	public WizardTask(Wizard wizard, WizardState eventState) {
		this.wizard=wizard;
		this.eventState = eventState;
	}

	@Override
	public void run() {
		if (LiquidPractice.getInstance().getWizardManager().getActiveWizard() == null ||
		    !LiquidPractice.getInstance().getWizardManager().getActiveWizard().equals(wizard) || wizard.getState() != eventState) {
			cancel();
			return;
		}

		onRun();

		ticks++;
	}

	public int getSeconds() {
		return 3 - ticks;
	}

	public abstract void onRun();

}
