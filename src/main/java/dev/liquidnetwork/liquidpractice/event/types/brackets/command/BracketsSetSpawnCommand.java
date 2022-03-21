package dev.liquidnetwork.liquidpractice.event.types.brackets.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "brackets setspawn", permission = "liquidpractice.staff")
public class BracketsSetSpawnCommand {

	public void execute(Player player, @CPL("[one|two|spec]") String position) {
		if (!(position.equals("one") || position.equals("two") || position.equals("spec"))) {
			player.sendMessage(CC.RED + "The position must be one/two/spec.");
		} else {
			if (position.equals("one")) {
				LiquidPractice.getInstance().getBracketsManager().setBracketsSpawn1(player.getLocation());
			} else if (position.equals("two")){
				LiquidPractice.getInstance().getBracketsManager().setBracketsSpawn2(player.getLocation());
			} else {
				LiquidPractice.getInstance().getBracketsManager().setBracketsSpectator(player.getLocation());
			}

			player.sendMessage(CC.GREEN + "Updated brackets's spawn location " + position + ".");

			LiquidPractice.getInstance().getBracketsManager().save();
		}
	}

}
