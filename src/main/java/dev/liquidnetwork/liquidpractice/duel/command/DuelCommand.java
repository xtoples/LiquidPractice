package dev.liquidnetwork.liquidpractice.duel.command;

import dev.liquidnetwork.liquidpractice.duel.DuelProcedure;
import dev.liquidnetwork.liquidpractice.duel.menu.DuelSelectKitMenu;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = { "duel" })
public class DuelCommand {
    public void execute(final Player player, @CPL("player") final Player target) {
        if (target == null) {
            player.sendMessage(CC.RED + "A player with that name could not be found.");
            return;
        }
        if (player.hasMetadata("frozen")) {
            player.sendMessage(CC.RED + "You cannot duel a player while being frozen.");
            return;
        }
        if (target.hasMetadata("frozen")) {
            player.sendMessage(CC.RED + "You cannot duel a player who's frozen.");
            return;
        }
        if (player.getUniqueId().equals(target.getUniqueId())) {
            player.sendMessage(CC.RED + "You cannot duel yourself.");
            return;
        }
        final Profile senderProfile=Profile.getByUuid(player.getUniqueId());
        final Profile receiverProfile=Profile.getByUuid(target.getUniqueId());
        if (senderProfile.isBusy(player)) {
            player.sendMessage(CC.RED + "You cannot duel anyone right now.");
            return;
        }
        if (receiverProfile.isBusy(target)) {
            player.sendMessage(CC.translate(CC.RED + target.getDisplayName()) + CC.RED + " is currently busy.");
            return;
        }
        if (!receiverProfile.getSettings().isReceiveDuelRequests()) {
            player.sendMessage(CC.RED + "That player is not accepting any duel requests at the moment.");
            return;
        }
        if (!senderProfile.canSendDuelRequest(player)) {
            player.sendMessage(CC.RED + "You have already sent that player a duel request.");
            return;
        }
        if (senderProfile.getParty() != null && receiverProfile.getParty() == null) {
            player.sendMessage(CC.RED + "That player is not in a party.");
            return;
        }
        final DuelProcedure procedure=new DuelProcedure(player, target, senderProfile.getParty() != null);
        senderProfile.setDuelProcedure(procedure);
        new DuelSelectKitMenu("normal").openMenu(player);
    }
}

