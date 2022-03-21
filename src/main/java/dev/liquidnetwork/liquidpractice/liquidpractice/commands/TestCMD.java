package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.queue.Queue;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

@CommandMeta(label="arraysecretcommand",permission="liquidpractice.admin")
public class TestCMD {
    public void execute(Player player) {
        Queue queue = Queue.getByKit(Kit.getByName("NoDebuff"));
        if (queue !=null) {
            player.sendMessage(CC.GREEN + "Queue works!");
        } else {
            player.sendMessage(CC.RED + "Queue failed!");
        }
        player.setMetadata("LiquidPracticeTest", new FixedMetadataValue(LiquidPractice.getInstance(), "nibber"));
    }
}
