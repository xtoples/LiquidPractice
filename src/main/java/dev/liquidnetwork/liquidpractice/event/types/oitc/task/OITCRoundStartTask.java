package dev.liquidnetwork.liquidpractice.event.types.oitc.task;

import dev.liquidnetwork.liquidpractice.event.types.oitc.OITCState;
import dev.liquidnetwork.liquidpractice.event.types.oitc.OITCTask;
import dev.liquidnetwork.liquidpractice.util.chat.CC;

public class OITCRoundStartTask extends OITCTask {

    public OITCRoundStartTask(dev.liquidnetwork.liquidpractice.event.types.oitc.OITC OITC) {
        super(OITC, OITCState.ROUND_STARTING);
    }

    @Override
    public void onRun() {
        if (getTicks() >= 3) {
            this.getOITC().broadcastMessage(CC.GREEN + "The OITC has started!");
            this.getOITC().setEventTask(null);
            this.getOITC().setState(OITCState.ROUND_FIGHTING);

            this.getOITC().setRoundStart(System.currentTimeMillis());
        } else {
            int seconds = getSeconds();

            this.getOITC().broadcastMessage("&f" + seconds + "...");
        }
    }

}
