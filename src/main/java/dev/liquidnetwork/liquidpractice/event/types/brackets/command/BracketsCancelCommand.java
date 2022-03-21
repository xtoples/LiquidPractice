package dev.liquidnetwork.liquidpractice.event.types.brackets.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "brackets cancel", permission = "liquidpractice.staff")
public class BracketsCancelCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getBracketsManager().getActiveBrackets() == null) {
			sender.sendMessage(CC.RED + "There isn't an active Brackets event.");
			return;
		}

		LiquidPractice.getInstance().getBracketsManager().getActiveBrackets().end();
	}

}
