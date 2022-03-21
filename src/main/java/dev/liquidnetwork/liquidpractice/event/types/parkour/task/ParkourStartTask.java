package dev.liquidnetwork.liquidpractice.event.types.parkour.task;

import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourTask;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourState;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;

public class ParkourStartTask extends ParkourTask {

	public ParkourStartTask(Parkour parkour) {
		super(parkour, ParkourState.WAITING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 120) {
			this.getParkour().end(null);
			return;
		}

		if (this.getParkour().getPlayers().size() <= 1 && this.getParkour().getCooldown() != null) {
			this.getParkour().setCooldown(null);
			this.getParkour().broadcastMessage("&cThere are not enough players for the parkour to start.");
		}

		if (this.getParkour().getPlayers().size() == this.getParkour().getMaxPlayers() || (getTicks() >= 30 && this.getParkour().getPlayers().size() >= 2)) {
			if (this.getParkour().getCooldown() == null) {
				this.getParkour().setCooldown(new Cooldown(11_000));
				this.getParkour().broadcastMessage("&fThe parkour will start in &b10 seconds&f...");
			} else {
				if (this.getParkour().getCooldown().hasExpired()) {
					this.getParkour().setState(ParkourState.ROUND_STARTING);
					this.getParkour().onRound();
					this.getParkour().setTotalPlayers(this.getParkour().getPlayers().size());
					this.getParkour().setEventTask(new ParkourRoundStartTask(this.getParkour()));
					this.getParkour().getPlayers().forEach(PlayerUtil::denyMovement);
				}
			}
		}

		if (getTicks() % 10 == 0) {
			this.getParkour().announce();
		}
	}

}
