package dev.liquidnetwork.liquidpractice.event.types.brackets.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "brackets cooldown", permission = "liquidpractice.staff")
public class BracketsCooldownCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getBracketsManager().getCooldown().hasExpired()) {
			sender.sendMessage(CC.RED + "There isn't a Brackets Event cooldown.");
			return;
		}

		sender.sendMessage(CC.GREEN + "You reset the Brackets Event cooldown.");

		LiquidPractice.getInstance().getBracketsManager().setCooldown(new Cooldown(0));
	}

}
