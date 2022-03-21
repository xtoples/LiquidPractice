package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "OITC setspawn", permission = "liquidPractice.oitc")
public class OITCSetSpawnCommand {

    public void execute(Player player) {
        LiquidPractice.getInstance().getOITCManager().setOITCSpectator(player.getLocation());

        player.sendMessage(CC.GREEN + "Updated OITC's spawn location.");

        LiquidPractice.getInstance().getOITCManager().save();
    }

}
