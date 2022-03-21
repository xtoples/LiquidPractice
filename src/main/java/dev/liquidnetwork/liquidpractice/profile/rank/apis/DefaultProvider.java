package dev.liquidnetwork.liquidpractice.profile.rank.apis;

import dev.liquidnetwork.liquidpractice.profile.rank.RankType;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.OfflinePlayer;

public class DefaultProvider implements RankType {

    @Override
    public String getRankName(OfflinePlayer player) {
        return CC.translate("&a");
    }

    @Override
    public String getRankPrefix(OfflinePlayer player) {
        return CC.translate("&a");
    }

    @Override
    public String getRankSuffix(OfflinePlayer player) {
        return CC.translate("&a");
    }

    @Override
    public String getFullName(OfflinePlayer player) {
        return player.getPlayer().getDisplayName();
    }
}
