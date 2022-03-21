package dev.liquidnetwork.liquidpractice.event.types.brackets.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.brackets.Brackets;
import dev.liquidnetwork.liquidpractice.event.types.brackets.BracketsState;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "brackets join")
public class BracketsJoinCommand {

	public static void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		Brackets activeBrackets = LiquidPractice.getInstance().getBracketsManager().getActiveBrackets();

		if (profile.isBusy(player) || profile.getParty() != null) {
			player.sendMessage(CC.RED + "You cannot join the brackets right now.");
			return;
		}

		if (activeBrackets == null) {
			player.sendMessage(CC.RED + "There isn't any active Brackets Events right now.");
			return;
		}

		if (activeBrackets.getState() != BracketsState.WAITING) {
			player.sendMessage(CC.RED + "This Brackets Event is currently on-going and cannot be joined.");
			return;
		}

		LiquidPractice.getInstance().getBracketsManager().getActiveBrackets().handleJoin(player);
	}

}
