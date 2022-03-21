package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "sumo cooldown", permission = "practice.sumo.cooldown")
public class SumoCooldownCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getSumoManager().getCooldown().hasExpired()) {
			sender.sendMessage(CC.RED + "There isn't any cooldown for the Sumo Event.");
			return;
		}

		sender.sendMessage(CC.GREEN + "You reset the Sumo Event cooldown.");

		LiquidPractice.getInstance().getSumoManager().setCooldown(new Cooldown(0));
	}

}
