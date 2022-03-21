package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"tsb", "togglesb", "sb toggle", "togglesidebar"})
public class ToggleScoreboardCMD {

    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        boolean scoreboardstate = profile.getSettings().isShowScoreboard();
        player.sendMessage(CC.translate("&7Scoreboard: " + (!scoreboardstate ? "&aOn" : "&cOff")));
        profile.getSettings().setShowScoreboard(!scoreboardstate);
    }

}
