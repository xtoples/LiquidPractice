package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "wizard cancel", permission = "liquidpractice.staff")
public class WizardCancelCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getWizardManager().getActiveWizard() == null) {
			sender.sendMessage(CC.RED + "There isn't an active Wizard event.");
			return;
		}

		LiquidPractice.getInstance().getWizardManager().getActiveWizard().end();
	}

}
