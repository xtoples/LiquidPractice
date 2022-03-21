package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardManager;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "wizard setspawn", permission = "liquidpractice.admin")
public class WizardSetSpawnCommand {

	public void execute(Player player, @CPL("[one|two|spec]") String position) {
		WizardManager wizard = LiquidPractice.getInstance().getWizardManager();
		if (!(position.equals("one") || position.equals("two") || position.equals("spec"))) {
			player.sendMessage(CC.RED + "The position must be one/two/spec.");
		} else {
			if (position.equals("one")) {
				wizard.setWizardSpawn1(player.getLocation());
			} else if (position.equals("two")){
				wizard.setWizardSpawn2(player.getLocation());
			} else {
				wizard.setWizardSpectator(player.getLocation());
			}

			player.sendMessage(CC.GREEN + "Updated wizard's spawn location " + position + ".");

			wizard.save();
		}
	}

}
