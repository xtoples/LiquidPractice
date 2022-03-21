package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "sumo tp", permission = "practice.sumo.tp")
public class SumoTpCommand {

	public void execute(Player player) {
		player.teleport(LiquidPractice.getInstance().getSumoManager().getSumoSpectator());
		player.sendMessage(CC.GREEN + "Teleported to sumo's spawn location.");
	}

}
