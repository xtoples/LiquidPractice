package dev.liquidnetwork.liquidpractice.event.types.lms.task;

import dev.liquidnetwork.liquidpractice.event.types.lms.LMSState;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMSTask;

public class LMSRoundEndTask extends LMSTask {

    public LMSRoundEndTask(dev.liquidnetwork.liquidpractice.event.types.lms.LMS LMS) {
        super(LMS, LMSState.ROUND_ENDING);
    }

    @Override
    public void onRun() {
        if (getTicks() >= 3) {
            if (this.getLMS().canEnd()) {
                this.getLMS().end();
            }
        }
    }

}
