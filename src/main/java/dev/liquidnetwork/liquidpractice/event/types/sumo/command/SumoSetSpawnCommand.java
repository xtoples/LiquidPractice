package dev.liquidnetwork.liquidpractice.event.types.sumo.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "sumo setspawn", permission = "liquidpractice.staff")
public class SumoSetSpawnCommand {

	public void execute(Player player, @CPL("[one|two|spec]") String position) {
		if (!(position.equals("one") || position.equals("two") || position.equals("spec"))) {
			player.sendMessage(CC.RED + "The position must be 1 or 2.");
		} else {
			if (position.equals("one")) {
				LiquidPractice.getInstance().getSumoManager().setSumoSpawn1(player.getLocation());
			} else if (position.equals("two")) {
				LiquidPractice.getInstance().getSumoManager().setSumoSpawn2(player.getLocation());
			} else {
				LiquidPractice.getInstance().getSumoManager().setSumoSpectator(player.getLocation());
			}

			player.sendMessage(CC.GREEN + "Updated sumo's spawn location " + position + ".");

			LiquidPractice.getInstance().getSumoManager().save();
		}
	}

}
