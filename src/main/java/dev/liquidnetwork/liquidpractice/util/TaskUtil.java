package dev.liquidnetwork.liquidpractice.util;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {
    public TaskUtil() {
    }

    public static void run(Runnable runnable) {
        LiquidPractice.getInstance().getServer().getScheduler().runTask(LiquidPractice.getInstance(), runnable);
    }

    public static void runTimer(Runnable runnable, long delay, long timer) {
        LiquidPractice.getInstance().getServer().getScheduler().runTaskTimer(LiquidPractice.getInstance(), runnable, delay, timer);
    }

    public static void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(LiquidPractice.getInstance(), delay, timer);
    }

    public static void runLater(Runnable runnable, long delay) {
        LiquidPractice.getInstance().getServer().getScheduler().runTaskLater(LiquidPractice.getInstance(), runnable, delay);
    }

    public static void runSync(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            runnable.run();
        else
            Bukkit.getScheduler().runTask(LiquidPractice.getInstance(), runnable);
    }

    public static void runAsync(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            Bukkit.getScheduler().runTaskAsynchronously(LiquidPractice.getInstance(), runnable);
        else
            runnable.run();
    }
}
