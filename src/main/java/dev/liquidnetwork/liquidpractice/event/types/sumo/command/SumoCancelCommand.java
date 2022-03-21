package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "sumo cancel", permission = "practice.sumo.cancel")
public class SumoCancelCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getSumoManager().getActiveSumo() == null) {
			sender.sendMessage(CC.RED + "There isn't an active Sumo Event.");
			return;
		}

		LiquidPractice.getInstance().getSumoManager().getActiveSumo().end();
	}

}
