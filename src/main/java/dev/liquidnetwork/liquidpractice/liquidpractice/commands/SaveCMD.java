package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice save ", "liquidpractice update"}, permission="liquidpractice.admin")
public class SaveCMD {
    public void execute(Player p) {
        Profile.getProfiles().values().forEach(Profile::save);
        Profile.loadAllProfiles();
        Kit.getKits().forEach(Kit::save);
        Kit.getKits().forEach(Kit::updateKitLeaderboards);
        Profile.loadGlobalLeaderboards();
        Arena.getArenas().forEach(Arena::save);
        p.sendMessage(CC.translate("&7&oReloaded all Stats and Leaderboards!"));
    }
}
