package dev.liquidnetwork.liquidpractice.match.task;

import dev.liquidnetwork.liquidpractice.match.MatchSnapshot;
import org.bukkit.scheduler.BukkitRunnable;

public class MatchSnapshotCleanupTask extends BukkitRunnable {

    @Override
    public void run() {
        MatchSnapshot.getSnapshots().entrySet().removeIf(entry -> System.currentTimeMillis() - entry.getValue().getCreated() >= 45_000);
    }

}
