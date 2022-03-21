package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "lms tp", permission = "practice.lms")
public class LMSTpCommand {

    public void execute(Player player) {
        player.teleport(LiquidPractice.getInstance().getLMSManager().getLmsSpectator());
        player.sendMessage(CC.GREEN + "Teleported to lms's spawn location.");
    }

}
