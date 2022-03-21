package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.event.menu.EventSelectKitMenu;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"lms host"}, permission = "practice.host.lms")
public class LMSHostCommand {

    public static void execute(Player player) {
        new EventSelectKitMenu("LMS").openMenu(player);
    }

}
