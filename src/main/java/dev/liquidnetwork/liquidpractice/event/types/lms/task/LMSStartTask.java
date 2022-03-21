package dev.liquidnetwork.liquidpractice.event.types.lms.task;

import dev.liquidnetwork.liquidpractice.event.types.lms.LMSState;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMSTask;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;

public class LMSStartTask extends LMSTask {

    public LMSStartTask(dev.liquidnetwork.liquidpractice.event.types.lms.LMS LMS) {
        super(LMS, LMSState.WAITING);
    }

    @Override
    public void onRun() {
        if (getTicks() >= 120) {
            this.getLMS().end();
            return;
        }

        if (this.getLMS().getPlayers().size() <= 1 && this.getLMS().getCooldown() != null) {
            this.getLMS().setCooldown(null);
            this.getLMS().broadcastMessage("&cThere are not enough players for the ffa to start.");
        }

        if (this.getLMS().getPlayers().size() == this.getLMS().getMaxPlayers() || (getTicks() >= 30 && this.getLMS().getPlayers().size() >= 2)) {
            if (this.getLMS().getCooldown() == null) {
                this.getLMS().setCooldown(new Cooldown(11_000));
                this.getLMS().broadcastMessage("&7The LMS will start in &b10 seconds&e7...");
            } else {
                if (this.getLMS().getCooldown().hasExpired()) {
                    this.getLMS().setState(LMSState.ROUND_STARTING);
                    this.getLMS().onRound();
                    this.getLMS().setTotalPlayers(this.getLMS().getPlayers().size());
                    this.getLMS().setEventTask(new LMSRoundStartTask(this.getLMS()));
                }
            }
        }

        if (getTicks() % 20 == 0) {
            this.getLMS().announce();
        }
    }

}
