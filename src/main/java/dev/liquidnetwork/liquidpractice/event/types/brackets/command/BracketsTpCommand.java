package dev.liquidnetwork.liquidpractice.event.types.brackets.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "brackets tp", permission = "liquidpractice.staff")
public class BracketsTpCommand {

	public void execute(Player player) {
		player.teleport(LiquidPractice.getInstance().getBracketsManager().getBracketsSpectator());
		player.sendMessage(CC.GREEN + "Teleported to brackets's spawn location.");
	}

}
