package dev.liquidnetwork.liquidpractice.liquidpractice.essentials;

import dev.liquidnetwork.liquidpractice.util.external.LocationUtil;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.liquidpractice.essentials.event.SpawnTeleportEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spigotmc.AsyncCatcher;

import java.io.IOException;

public class Essentials {

    public static Location spawn;

    public Essentials() {

        spawn = LocationUtil.deserialize(LiquidPractice.getInstance().getMainConfig().getStringOrDefault("LiquidPractice.Spawn", null));
    }

    public static void setSpawn(Location location) {
        spawn = location;

        LiquidPractice.getInstance().getMainConfig().getConfiguration().set("LiquidPractice.Spawn", LocationUtil.serialize(spawn));

        try {
            LiquidPractice.getInstance().getMainConfig().getConfiguration().save(LiquidPractice.getInstance().getMainConfig().getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void teleportToSpawn(Player player) {
        Location location = spawn;
        AsyncCatcher.enabled=false;
        SpawnTeleportEvent event = new SpawnTeleportEvent(player, location);
        event.call();

        if (!event.isCancelled() && event.getLocation() != null) {
            player.teleport(event.getLocation());
        }
    }
}
