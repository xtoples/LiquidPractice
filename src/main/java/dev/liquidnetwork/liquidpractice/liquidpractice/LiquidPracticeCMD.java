package dev.liquidnetwork.liquidpractice.liquidpractice;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice", "liquidpractice help", "practice", "lprac"})
public class LiquidPracticeCMD {
    public void execute(Player p) {
        if (p.hasPermission("liquidpractice.staff")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
            p.sendMessage(CC.translate("&bLiquidPractice &7» LiquidPractice Commands"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice setlobby &8- &8&o(&7&oSets the lobby to player's location&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice savekits &8- &8&o(&7&oSave all Kits&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice savearenas &8- &8&o(&7&oSave all Arenas&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice savedata &8- &8&o(&7&oSave all Profiles&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice goldenhead &8- &8&o(&7&oReceive a pre-made G-Head&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice update &8- &8&o(&7&oUpdate all leaderboards&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice savedata &8- &8&o(&7&oSave all Profiles&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice savearenas &8- &8&o(&7&oSave all Arenas&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice savekits &8- &8&o(&7&oSave all Kits&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice hcf &8- &8&o(&7&oHelp on how to setup HCF&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice resetstats &8<&7name&8> &8- &8&o(&7&oResets a profile&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice rename &8<&7name&8> &8- &8&o(&7&oRenames item in hand&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/liquidpractice spawn &8- &8&o(&7&oRefresh Profile & Teleport to spawn&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/kit help &8- &8&o(&7&oView kit commands&8&o)"));
            p.sendMessage(CC.translate(" &8• &b/arena help &8- &8&o(&7&oView arena commands&8&o)"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));
            p.sendMessage(CC.translate("&7This server is running &bLiquidPractice!"));
            p.sendMessage(CC.translate("&7LiquidPractice is made By &dYzzird & Toples"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------&7&m" + StringUtils.repeat("-", 37) + "&b&m--------"));

        }
    }
}
