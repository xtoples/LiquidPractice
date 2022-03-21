package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="liquidpractice setelo", permission="liquidpractice.admin")
public class SetEloCMD {
    public void execute(Player player, @CPL("profile") String name, @CPL("[global|kit]") String type, @CPL("amount") String inter ) {
        Player target= PlayerUtil.getPlayer(name);
        int elo = Integer.parseInt(inter);
        if (target != null) {
            Profile profile=Profile.getByUuid(target);

            if (type.equalsIgnoreCase("global")) {
                profile.setGlobalElo(elo);
                player.sendMessage(CC.translate("&7Updated Global elo for &b" + name));
                return;
            }

            if (Kit.getByName(type) == null) {
                player.sendMessage(CC.translate("&7Kit &b" + name + " &7doesn't exist!"));
            } else {
                Kit kit=Kit.getByName(type);
                profile.getStatisticsData().get(kit).setElo(elo);
                player.sendMessage(CC.translate("&7Updated &b" + type + "'s&7 elo for &b" + name));
                profile.calculateGlobalElo();
            }
        } else {
            player.sendMessage(CC.translate("&7Player not found or is not online!"));
        }
    }
}
