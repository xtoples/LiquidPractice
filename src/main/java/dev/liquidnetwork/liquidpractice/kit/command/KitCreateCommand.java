package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.queue.Queue;
import dev.liquidnetwork.liquidpractice.queue.QueueType;
import dev.liquidnetwork.liquidpractice.statistics.StatisticsData;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "kit create", permission = "liquidpractice.admin")
public class KitCreateCommand {

    public void execute(Player player, String kitName) {
        if (Kit.getByName(kitName) != null) {
            player.sendMessage(CC.translate("&7A kit with that name already exists."));
            return;
        }
        Kit kit = new Kit(kitName);
        kit.save();
        Kit.getKits().add(kit);
        kit.setEnabled(true);
        kit.getGameRules().setRanked(true);
        for ( Profile profile : Profile.getProfiles().values() ) {
            profile.getStatisticsData().put(kit, new StatisticsData());
        }
        if (kit.isEnabled()) {
            Queue unRanked = new Queue(kit, QueueType.UNRANKED);
            Queue ranked = new Queue(kit, QueueType.RANKED);
            kit.setUnrankedQueue(unRanked);
            kit.setRankedQueue(ranked);
        }

        player.sendMessage(CC.translate("&7Successfully created a new kit &b" + kit.getDisplayName() + "."));
    }

}
