package dev.liquidnetwork.liquidpractice.event.types.spleef.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "spleef tp", permission = "liquidpractice.staff")
public class SpleefTpCommand {

	public void execute(Player player) {
		player.teleport(LiquidPractice.getInstance().getSpleefManager().getSpleefSpectator());
		player.sendMessage(CC.GREEN + "Teleported to spleef's spawn location.");
	}

}
