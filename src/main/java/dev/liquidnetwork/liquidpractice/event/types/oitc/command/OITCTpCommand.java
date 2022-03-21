package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "OITC tp", permission = "liquidPractice.oitc")
public class OITCTpCommand {

    public void execute(Player player) {
        player.teleport(LiquidPractice.getInstance().getOITCManager().getOITCSpectator());
        player.sendMessage(CC.GREEN + "Teleported to OITC's spawn location.");
    }

}
