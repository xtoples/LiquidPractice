package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "oitc cancel", permission = "liquidPractice.oitc")
public class OITCCancelCommand {

    public void execute(CommandSender sender) {
        if (LiquidPractice.getInstance().getOITCManager().getActiveOITC() == null) {
            sender.sendMessage(CC.RED + "There isn't an active OITC event.");
            return;
        }

        LiquidPractice.getInstance().getOITCManager().getActiveOITC().end();
    }

}
