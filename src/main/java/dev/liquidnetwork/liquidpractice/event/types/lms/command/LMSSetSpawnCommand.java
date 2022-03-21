package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "lms setspawn", permission = "practice.lms")
public class LMSSetSpawnCommand {

    public void execute(Player player) {
        LiquidPractice.getInstance().getLMSManager().setLmsSpectator(player.getLocation());

        player.sendMessage(CC.GREEN + "Updated lms's spawn location.");

        LiquidPractice.getInstance().getLMSManager().save();
    }

}
