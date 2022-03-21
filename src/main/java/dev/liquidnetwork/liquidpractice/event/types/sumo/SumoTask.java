package dev.liquidnetwork.liquidpractice.event.types.sumo;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public abstract class SumoTask extends BukkitRunnable {

	private int ticks;
	private Sumo sumo;
	private SumoState eventState;

	public SumoTask(Sumo sumo, SumoState eventState) {
		this.sumo = sumo;
		this.eventState = eventState;
	}

	@Override
	public void run() {
		if (LiquidPractice.getInstance().getSumoManager().getActiveSumo() == null ||
		    !LiquidPractice.getInstance().getSumoManager().getActiveSumo().equals(sumo) || sumo.getState() != eventState) {
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
