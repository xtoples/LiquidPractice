package dev.liquidnetwork.liquidpractice.event.types.spleef.task;

import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefState;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefTask;

public class SpleefRoundEndTask extends SpleefTask {

	public SpleefRoundEndTask(Spleef spleef) {
		super(spleef, SpleefState.ROUND_ENDING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 3) {
			if (this.getSpleef().canEnd()) {
				this.getSpleef().end();
			}
		}
	}

}
