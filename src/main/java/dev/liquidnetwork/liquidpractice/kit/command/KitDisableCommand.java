package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.queue.Queue;
import dev.liquidnetwork.liquidpractice.queue.QueueType;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit disable"}, permission = "liquidpractice.admin")
public class KitDisableCommand {
    public void execute(Player player, @CPL("kit") String kit) {
        Kit kits = Kit.getByName(kit);
        if (kits == null) {
            player.sendMessage(CC.translate("&cThat Kit does not exist!"));
            return;
        }
        kits.setEnabled(false);
        Queue.getQueues().remove(new Queue(kits, QueueType.RANKED));
        Queue.getQueues().remove(new Queue(kits, QueueType.UNRANKED));
        player.sendMessage(CC.translate("&cDisabled the kit " + kit));
    }
}
