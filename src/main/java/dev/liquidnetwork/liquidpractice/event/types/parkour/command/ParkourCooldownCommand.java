package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "parkour cooldown", permission = "liquidpractice.staff")
public class ParkourCooldownCommand {

	public void execute(CommandSender sender) {
		if (LiquidPractice.getInstance().getParkourManager().getCooldown().hasExpired()) {
			sender.sendMessage(CC.RED + "There isn't a Parkour Event cooldown.");
			return;
		}

		sender.sendMessage(CC.GREEN + "You reset the Parkour Event cooldown.");

		LiquidPractice.getInstance().getParkourManager().setCooldown(new Cooldown(0));
	}

}
