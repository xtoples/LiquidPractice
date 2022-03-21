package dev.liquidnetwork.liquidpractice.placeholders;

import com.allatori.annotations.DoNotRename;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.LiquidPracticeCache;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import org.bukkit.entity.Player;

import java.util.UUID;

@DoNotRename
public class Placeholders extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "liquidpractice";
    }

    @Override
    public String getAuthor() {
        return "Yzzird & Toples";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "&7";
        }
        Profile profile = Profile.getByUuid(player);

        /**
         * [Originally coded by Yzzird and designed/modificated by Toples]
         * This placeholder is used to display the viewer's rank
         * name from the Core-Hook.
         *
         * Note: The option for "Core-Hook" must be on in the config
         *
         * @param identifier - %liquidpractice_opponent%
         */

        if (identifier.contains("displayname")) {
           return LiquidPractice.getInstance().getRankManager().getFullName(player);
        }

        /**
         * [Originally coded by Yzzird and designed/modificated by Toples]
         * This placeholder is used to display the viewer's
         * global elo from their kitdata.
         *
         * @param identifier - %liquidpractice_globalelo%
         */

        if (identifier.contains("globalelo")) {
            return String.valueOf(profile.getGlobalElo());
        }

        /**
         * [Originally coded by Yzzird and designed/modificated by Toples]
         * This placeholder is used to display the viewer's elo
         * for a certain kit mentioned in the identifier.
         *
         * @param identifier - %liquidpractice_opponent%
         */

        if (identifier.contains("opponent")) {
            Match match = profile.getMatch();
            if (match == null) {
                return "&7";
            }
            return match.getOpponentPlayer(player).getName();
        }

        /**
         * [Originally coded by Yzzird and designed/modificated by Toples]
         * This placeholder is used to display the viewer's elo
         * for a certain kit mentioned in the identifier.
         *
         * @param identifier - %liquidpractice_<kit>_elo%
         */
        if (identifier.contains("elo") && !identifier.contains("global")) {
            String[] splitString = identifier.split("_");
            String kitName = splitString[0];
            Kit kit = Kit.getByName(kitName);
            if (kit == null) {
                return "&7";
            }
            int elo = profile.getStatisticsData().get(kit).getElo();
            return String.valueOf(elo);
        }

        /**
         * [Originally coded by Yzzird and designed/modificated by Toples]
         * This placeholder is used to display the viewer's elo
         * for a certain kit mentioned in the identifier.
         *
         * @param identifier - %liquidpractice_<player>_globalelo%
         */
        if (identifier.contains("globalelo")) {
            String[] splitString = identifier.split("_");
            String playerName = splitString[0];
            UUID uuid = LiquidPracticeCache.getUUID(playerName);
            Profile target = Profile.getByUuid(uuid);
            if (target == null) {
                return "&7";
            }
            int elo = target.getGlobalElo();
            return String.valueOf(elo);
        }

        /**
         * [Originally coded by Yzzird and designed/modificated by Toples]
         * This placeholder is used to display a player's elo
         * for a certain kit mentioned in the identifier.
         *
         * @param identifier - %liquidpractice_<player>_<kit>_elo%
         */
        if (identifier.contains("elo") && !identifier.contains("global")) {
            String[] splitString = identifier.split("_");
            String kitName = splitString[1];
            String playerName = splitString[0];
            Kit kit = Kit.getByName(kitName);
            if (kit == null) {
                return "&7";
            }
            UUID uuid = LiquidPracticeCache.getUUID(playerName);
            Profile target = Profile.getByUuid(uuid);
            if (target == null) {
                return "&7";
            }
            int elo = target.getStatisticsData().get(kit).getElo();
            return String.valueOf(elo);
        }


        return null;
    }
}
