package dev.liquidnetwork.liquidpractice;

import com.allatori.annotations.DoNotRename;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@DoNotRename
public class LiquidPracticeCache {

    @Getter
    private static final Map<String, UUID> playerCache = new HashMap<>();

    public static int getInQueues() {
        int inQueues = 0;

        for ( Player player : Bukkit.getOnlinePlayers()) {
            Profile profile = Profile.getByUuid(player.getUniqueId());

            if (profile.isInQueue()) {
                inQueues++;
            }
        }

        return inQueues;
    }

    public static int getInFights() {
        int inFights = 0;

        for (Player player : Bukkit.getOnlinePlayers()) {
            Profile profile = Profile.getByUuid(player.getUniqueId());

            if (profile.isInFight() || profile.isInEvent()) {
                inFights++;
            }
        }

        return inFights;
    }

    public static int getOnline() {
        return Bukkit.getOnlinePlayers().size();
    }

    public static UUID getUUID(String name) {
        UUID uuid = null;
        if (LiquidPracticeCache.getPlayerCache().containsKey(name)) {
            uuid = LiquidPracticeCache.getPlayerCache().get(name);
        }
        return uuid;
    }
}
