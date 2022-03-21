package dev.liquidnetwork.liquidpractice.event.types.brackets.command;

import dev.liquidnetwork.liquidpractice.event.menu.EventSelectKitMenu;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "brackets host" }, permission = "practice.host.brackets")
public class BracketsHostCommand {

	public static void execute(Player player) {
		new EventSelectKitMenu("Brackets").openMenu(player);
	}

}
