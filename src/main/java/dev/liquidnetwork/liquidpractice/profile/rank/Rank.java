package dev.liquidnetwork.liquidpractice.profile.rank;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.profile.rank.apis.*;
import org.bukkit.Bukkit;

public class Rank {

    public Rank() {
     preLoad();
    }

    public static void preLoad() {
        if (Bukkit.getPluginManager().getPlugin("AquaCore") != null) {
            LiquidPractice.getInstance().setRankManager(new AquaCore());
            LiquidPractice.logger("&bFound AquaCore! Hooking in...");
        } else if (Bukkit.getPluginManager().getPlugin("MizuCore") != null) {
            LiquidPractice.getInstance().setRankManager(new MizuCore());
            LiquidPractice.logger("&bFound MizuCore! Hooking in...");
        } else if (Bukkit.getPluginManager().getPlugin("HestiaCore") != null) {
            LiquidPractice.getInstance().setRankManager(new HestiaCore());
            LiquidPractice.logger("&bFound HestiaCore! Hooking in...");
        } else if (Bukkit.getPluginManager().getPlugin("ZoomCore") != null) {
            LiquidPractice.getInstance().setRankManager(new ZoomCore());
            LiquidPractice.logger("&bFound ZoomCore! Hooking in...");
        } else {
            LiquidPractice.getInstance().setRankManager(new DefaultProvider());
        }
    }
}
