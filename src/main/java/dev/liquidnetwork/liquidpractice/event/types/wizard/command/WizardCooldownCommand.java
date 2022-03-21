package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "wizard cooldown", permission = "liquidpractice.staff")
public class WizardCooldownCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getWizardManager().getCooldown().hasExpired()) {
			sender.sendMessage(CC.RED + "There isn't a Wizard Event cooldown.");
			return;
		}

		sender.sendMessage(CC.GREEN + "You reset the Wizard Event cooldown.");

		LiquidPractice.getInstance().getWizardManager().setCooldown(new Cooldown(0));
	}

}
