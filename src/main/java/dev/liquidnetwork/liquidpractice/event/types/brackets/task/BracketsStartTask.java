package dev.liquidnetwork.liquidpractice.event.types.brackets.task;

import dev.liquidnetwork.liquidpractice.event.types.brackets.Brackets;
import dev.liquidnetwork.liquidpractice.event.types.brackets.BracketsState;
import dev.liquidnetwork.liquidpractice.event.types.brackets.BracketsTask;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;

public class BracketsStartTask extends BracketsTask {

	public BracketsStartTask(Brackets brackets) {
		super(brackets, BracketsState.WAITING);
	}

	@Override
	public void onRun() {
		if (getTicks() >= 120) {
			this.getBrackets().end();
			return;
		}

		if (this.getBrackets().getPlayers().size() <= 1 && this.getBrackets().getCooldown() != null) {
			this.getBrackets().setCooldown(null);
			this.getBrackets().broadcastMessage("&cThere are not enough players for the brackets to start.");
		}

		if (this.getBrackets().getPlayers().size() == Brackets.getMaxPlayers() || (getTicks() >= 30 && this.getBrackets().getPlayers().size() >= 2)) {
			if (this.getBrackets().getCooldown() == null) {
				this.getBrackets().setCooldown(new Cooldown(11_000));
				this.getBrackets().broadcastMessage("&fThe brackets will start in &b10 seconds&f...");
			} else {
				if (this.getBrackets().getCooldown().hasExpired()) {
					this.getBrackets().setState(BracketsState.ROUND_STARTING);
					this.getBrackets().onRound();
					this.getBrackets().setTotalPlayers(this.getBrackets().getPlayers().size());
					this.getBrackets().setEventTask(new BracketsRoundStartTask(this.getBrackets()));
				}
			}
		}

		if (getTicks() % 10 == 0) {
			this.getBrackets().announce();
		}
	}

}
