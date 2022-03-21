package dev.liquidnetwork.liquidpractice.event.types.oitc.task;

import dev.liquidnetwork.liquidpractice.event.types.oitc.OITCState;
import dev.liquidnetwork.liquidpractice.event.types.oitc.OITCTask;

public class OITCRoundEndTask extends OITCTask {

    public OITCRoundEndTask(dev.liquidnetwork.liquidpractice.event.types.oitc.OITC OITC) {
        super(OITC, OITCState.ROUND_ENDING);
    }

    @Override
    public void onRun() {
        if (getTicks() >= 3) {
            if (this.getOITC().canEnd()) {
                this.getOITC().end();
            }
        }
    }

}
