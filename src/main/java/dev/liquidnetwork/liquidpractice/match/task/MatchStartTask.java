package dev.liquidnetwork.liquidpractice.match.task;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.match.MatchState;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MatchStartTask extends BukkitRunnable {

    private final Match match;
    private int ticks;

    public MatchStartTask(Match match) {
        this.match = match;
    }

    @Override
    public void run() {
        int seconds= 5 - ticks;
        if (match.isEnding()) {
            cancel();
            return;
        }

        if (match.isHCFMatch()) {
            if (seconds == 2) {
                match.setState(MatchState.FIGHTING);
                match.setStartTimestamp(System.currentTimeMillis());
                match.broadcastMessage(CC.GREEN + "Match Started!");
                if (LiquidPractice.getInstance().getMessagesConfig().getBoolean("Disclaimer-Message.Enabled")) {
                    match.broadcastMessage("");
                    for ( String string : LiquidPractice.getInstance().getMessagesConfig().getStringList("Disclaimer-Message.Message")) {
                        match.broadcastMessage(CC.translate(string));
                    }
                }
                match.broadcastSound(Sound.LEVEL_UP);
                match.getPlayers().forEach(PlayerUtil::allowMovement);
                cancel();
                return;
            }
            match.broadcastMessage(CC.WHITE + "Starting in " + CC.AQUA + (seconds - 2) + CC.WHITE +  "...");
            match.broadcastSound(Sound.NOTE_PLING);
        } else {
            if (match.getKit().getGameRules().isSumo() || match.getKit().getGameRules().isParkour()) {
                if (seconds == 2) {
                    match.getPlayers().forEach(PlayerUtil::allowMovement);
                    match.setState(MatchState.FIGHTING);
                    match.setStartTimestamp(System.currentTimeMillis());
                    match.broadcastMessage(CC.GREEN + "The round has started!");
                    match.broadcastSound(Sound.NOTE_BASS);
                    for (Player player : match.getPlayers()) {
                        player.getInventory().remove(Material.INK_SACK);
                        player.updateInventory();
                        for (Player oPlayer : match.getPlayers()) {
                            if (player.equals(oPlayer))
                                continue;
                            player.showPlayer(oPlayer);
                            oPlayer.showPlayer(player);
                        }
                    }
                    cancel();
                    return;
                }
                match.broadcastMessage(CC.AQUA + (seconds - 2) + "...");
                match.broadcastSound(Sound.NOTE_PLING);
            } else {
                if (seconds == 2) {
                    match.setState(MatchState.FIGHTING);
                    match.setStartTimestamp(System.currentTimeMillis());
                    match.broadcastMessage(CC.GREEN + "Match Started!");
                    if (LiquidPractice.getInstance().getMessagesConfig().getBoolean("Disclaimer-Message.Enabled")) {
                        match.broadcastMessage("");
                        for ( String string : LiquidPractice.getInstance().getMessagesConfig().getStringList("Disclaimer-Message.Message")) {
                            match.broadcastMessage(CC.translate(string));
                        }
                    }
                    match.broadcastSound(Sound.LEVEL_UP);
                    match.getPlayers().forEach(PlayerUtil::allowMovement);
                    match.getPlayers().forEach(player -> {
                        player.getInventory().remove(Material.INK_SACK);
                        player.updateInventory();
                    });
                    cancel();
                    return;
                }

                match.broadcastMessage(CC.WHITE + "Starting in " + CC.AQUA + (seconds - 2) +  "...");
                match.broadcastSound(Sound.NOTE_PLING);
            }
        }

        ticks++;
    }

}
