package dev.liquidnetwork.liquidpractice.event.types.brackets;

import lombok.Getter;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public abstract class BracketsTask extends BukkitRunnable {

	private int ticks;
	private Brackets brackets;
	private BracketsState eventState;

	public BracketsTask(Brackets brackets, BracketsState eventState) {
		this.brackets = brackets;
		this.eventState = eventState;
	}

	@Override
	public void run() {
		if (LiquidPractice.getInstance().getBracketsManager().getActiveBrackets() == null ||
		    !LiquidPractice.getInstance().getBracketsManager().getActiveBrackets().equals(brackets) || brackets.getState() != eventState) {
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
