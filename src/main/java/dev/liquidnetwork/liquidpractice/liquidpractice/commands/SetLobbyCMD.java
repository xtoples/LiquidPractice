package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.liquidpractice.essentials.Essentials;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice setlobby", "liquidpractice setspawn"}, permission="liquidpractice.admin")
public class SetLobbyCMD {
    public void execute(Player player) {
        Essentials.setSpawn(player.getLocation());
        player.sendMessage(CC.translate("&7You have set the &bnew &7lobby &bspawn &7!"));
    }
}
