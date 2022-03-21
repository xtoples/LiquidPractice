package dev.liquidnetwork.liquidpractice.util.scoreboard.scoreboard;

import dev.liquidnetwork.liquidpractice.util.scoreboard.scoreboard.cooldown.BoardCooldown;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public interface BoardAdapter {

    String getTitle(Player player);
    List<String> getScoreboard(Player player, Board board, Set<BoardCooldown> cooldowns);

}
