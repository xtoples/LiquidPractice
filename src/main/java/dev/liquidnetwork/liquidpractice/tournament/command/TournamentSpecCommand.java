package dev.liquidnetwork.liquidpractice.tournament.command;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.tournament.Tournament;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"tournament spec"}, permission = "")
public class TournamentSpecCommand {
    public void execute(Player player) {
        if (Tournament.CURRENT_TOURNAMENT == null) {
            player.sendMessage(CC.RED + "There is no current tournament available!");
            return;
        }
        Profile profile = Profile.getByUuid(Tournament.CURRENT_TOURNAMENT.getParticipants().get(1).getLeader().getPlayer());
        if (Tournament.getTournamentMatch() != null && profile.getMatch() !=null) {
            Tournament.getTournamentMatch().addSpectator(player, profile.getPlayer());
        }
    }
}
