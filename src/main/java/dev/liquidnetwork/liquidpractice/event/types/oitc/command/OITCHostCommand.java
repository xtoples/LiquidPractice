package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.event.menu.EventSelectKitMenu;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"OITC host"}, permission = "liquidPractice.host.oitc")
public class OITCHostCommand {

    public static void execute(Player player) {
        new EventSelectKitMenu("OITC").openMenu(player);
    }

}
