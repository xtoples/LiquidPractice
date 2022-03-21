package dev.liquidnetwork.liquidpractice.event.types.wizard.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "wizard tp", permission = "liquidpractice.staff")
public class WizardTpCommand {

	public void execute(Player player) {
		player.teleport(LiquidPractice.getInstance().getWizardManager().getWizardSpectator());
		player.sendMessage(CC.GREEN + "Teleported to wizard's spawn location.");
	}

}
