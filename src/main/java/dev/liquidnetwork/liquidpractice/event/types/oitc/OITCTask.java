package dev.liquidnetwork.liquidpractice.event.types.oitc;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public abstract class OITCTask extends BukkitRunnable {

    private int ticks;
    private final OITC OITC;
    private final OITCState eventState;

    public OITCTask(OITC OITC, OITCState eventState) {
        this.OITC = OITC;
        this.eventState = eventState;
    }

    @Override
    public void run() {
        if (LiquidPractice.getInstance().getOITCManager().getActiveOITC() == null ||
           !LiquidPractice.getInstance().getOITCManager().getActiveOITC().equals(OITC) ||
            OITC.getState() != eventState) {
            cancel();
            return;
        }

        onRun();

        ticks++;
    }

    public int getSeconds() {
        return 3 - ticks;
    }

    public abstract void onRun();

}
