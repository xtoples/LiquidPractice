package dev.liquidnetwork.liquidpractice.event;

import dev.liquidnetwork.liquidpractice.event.menu.EventSelectEventMenu;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"events", "host", "host event"})
public class EventCommand {

    public void execute(Player player) {
        new EventSelectEventMenu().openMenu(player);
    }
}
