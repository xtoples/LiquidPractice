package dev.liquidnetwork.liquidpractice.event.types.lms.task;

import dev.liquidnetwork.liquidpractice.event.types.lms.LMSState;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMSTask;
import dev.liquidnetwork.liquidpractice.util.chat.CC;

public class LMSRoundStartTask extends LMSTask {

    public LMSRoundStartTask(dev.liquidnetwork.liquidpractice.event.types.lms.LMS LMS) {
        super(LMS, LMSState.ROUND_STARTING);
    }

    @Override
    public void onRun() {
        if (getTicks() >= 3) {
            this.getLMS().broadcastMessage(CC.GREEN + "The LMS has started!");
            this.getLMS().setEventTask(null);
            this.getLMS().setState(LMSState.ROUND_FIGHTING);

            this.getLMS().setRoundStart(System.currentTimeMillis());
        } else {
            int seconds = getSeconds();

            this.getLMS().broadcastMessage("&f" + seconds + "...");
        }
    }

}
