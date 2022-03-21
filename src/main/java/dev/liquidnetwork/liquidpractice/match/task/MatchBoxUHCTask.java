package dev.liquidnetwork.liquidpractice.match.task;

import dev.liquidnetwork.liquidpractice.match.Match;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class MatchBoxUHCTask extends BukkitRunnable {

    private final Match match;

    @Override
    public void run() {
        if (match.getKit().getGameRules().isBoxuhc() && match.getBrokenBlocks().size() > 0) {
            match.getBrokenBlocks().forEach(location -> location.getBlock().setType(Material.WOOD));
            match.getBrokenBlocks().clear();
        }
    }
}
