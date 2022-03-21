package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "spleef cooldown", permission = "liquidpractice.staff")
public class SpleefCooldownCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getSpleefManager().getCooldown().hasExpired()) {
			sender.sendMessage(CC.RED + "There isn't a Spleef Event cooldown.");
			return;
		}

		sender.sendMessage(CC.GREEN + "You reset the Spleef Event cooldown.");

		LiquidPractice.getInstance().getSpleefManager().setCooldown(new Cooldown(0));
	}

}
