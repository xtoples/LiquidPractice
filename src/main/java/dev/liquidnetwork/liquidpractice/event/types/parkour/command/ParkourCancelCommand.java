package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "parkour cancel", permission = "liquidpractice.staff")
public class ParkourCancelCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getParkourManager().getActiveParkour() == null) {
			sender.sendMessage(CC.RED + "There isn't an active Parkour event.");
			return;
		}

		LiquidPractice.getInstance().getParkourManager().getActiveParkour().end(null);
	}

}
