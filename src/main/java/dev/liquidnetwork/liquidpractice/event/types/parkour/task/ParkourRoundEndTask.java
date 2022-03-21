package dev.liquidnetwork.liquidpractice.event.types.parkour.task;

import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourTask;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourState;
import org.bukkit.entity.Player;

public class ParkourRoundEndTask extends ParkourTask {

	private Player winner;

	public ParkourRoundEndTask(Parkour parkour, Player winner) {
		super(parkour, ParkourState.ROUND_ENDING);
		this.winner = winner;
	}

	@Override
	public void onRun() {
		if (getTicks() >= 3) {
			this.getParkour().end(winner);
		}
	}

}
