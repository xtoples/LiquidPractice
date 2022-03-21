package dev.liquidnetwork.liquidpractice.event.types.parkour.task;

import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourTask;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourState;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import dev.liquidnetwork.liquidpractice.util.chat.CC;

public class ParkourRoundStartTask extends ParkourTask {

	public ParkourRoundStartTask(Parkour parkour) {
		super(parkour, ParkourState.ROUND_STARTING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 3) {
			this.getParkour().broadcastMessage(CC.AQUA + "The parkour has started!");
			this.getParkour().setEventTask(null);
			this.getParkour().setState(ParkourState.ROUND_FIGHTING);
			this.getParkour().getPlayers().forEach(PlayerUtil::allowMovement);

			((Parkour) this.getParkour()).setRoundStart(System.currentTimeMillis());
		} else {
			int seconds = getSeconds();

			this.getParkour().broadcastMessage("&b" + seconds + "...");
		}
	}

}
