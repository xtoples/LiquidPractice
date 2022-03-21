package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "spleef cancel", permission = "liquidpractice.staff")
public class SpleefCancelCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getSpleefManager().getActiveSpleef() == null) {
			sender.sendMessage(CC.RED + "There isn't an active Spleef event.");
			return;
		}

		LiquidPractice.getInstance().getSpleefManager().getActiveSpleef().end();
	}

}
