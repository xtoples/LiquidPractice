package dev.liquidnetwork.liquidpractice.event.types.parkour.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "parkour setspawn", permission = "liquidpractice.staff")
public class ParkourSetSpawnCommand {

	public void execute(Player player) {
		LiquidPractice.getInstance().getParkourManager().setParkourSpawn(player.getLocation());

		player.sendMessage(CC.GREEN + "Updated parkour's spawn location.");

		LiquidPractice.getInstance().getParkourManager().save();
	}

}
