package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice reload", "practice reload"}, permission="liquidpractice.admin")
public class ReloadCMD {
    public void execute(Player p) {
        p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "WARNING! Reloading is not recommended. It may cause the system to malfunction!");
        long st=System.currentTimeMillis();
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(CC.RED + "Reloading Practice plugin, please rejoin in 1 minute!"));
        LiquidPractice.getInstance().getPluginLoader().disablePlugin(LiquidPractice.getInstance());
        LiquidPractice.getInstance().getPluginLoader().enablePlugin(LiquidPractice.getInstance());
        long et=System.currentTimeMillis();
        p.sendMessage(ChatColor.AQUA + "LiquidPractice was reloaded in " + (et - st) + " ms.");
    }

}
