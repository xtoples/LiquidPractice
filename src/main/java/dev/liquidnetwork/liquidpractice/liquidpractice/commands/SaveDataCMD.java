package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice save", "liquidpractice savedata"}, permission="liquidpractice.admin")
public class SaveDataCMD {
    public void execute(Player player) {
        Profile.getProfiles().values().forEach(Profile::save);
        player.sendMessage(CC.translate("&bSaving profiles..."));
    }
}
