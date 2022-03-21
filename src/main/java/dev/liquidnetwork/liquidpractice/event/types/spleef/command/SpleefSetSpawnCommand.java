package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "spleef setspawn", permission = "liquidpractice.staff")
public class SpleefSetSpawnCommand {

	public void execute(Player player) {
		LiquidPractice.getInstance().getSpleefManager().setSpleefSpectator(player.getLocation());

		player.sendMessage(CC.GREEN + "Set spleef's spawn location.");

		LiquidPractice.getInstance().getSpleefManager().save();
	}

}
