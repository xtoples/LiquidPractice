package dev.liquidnetwork.liquidpractice.statistics.command;

import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.statistics.menu.LeaderboardsMenu;
import org.bukkit.entity.Player;

@CommandMeta(label = {"leaderboards", "lb"})
public class LeaderboardsCommand {

    public void execute(Player player) {
        new LeaderboardsMenu().openMenu(player);
    }

}
