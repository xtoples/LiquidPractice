package dev.liquidnetwork.liquidpractice.event.types.spleef.task;

import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefState;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefTask;
import dev.liquidnetwork.liquidpractice.util.chat.CC;

public class SpleefRoundStartTask extends SpleefTask {

	public SpleefRoundStartTask(Spleef spleef) {
		super(spleef, SpleefState.ROUND_STARTING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 3) {
			this.getSpleef().broadcastMessage(CC.AQUA + "The round has started!");
			this.getSpleef().setEventTask(null);
			this.getSpleef().setState(SpleefState.ROUND_FIGHTING);

			((Spleef) this.getSpleef()).setRoundStart(System.currentTimeMillis());
		} else {
			int seconds = getSeconds();

			this.getSpleef().broadcastMessage("&b" + seconds + "...");
		}
	}

}
