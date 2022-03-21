package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "lms cancel", permission = "practice.lms")
public class LMSCancelCommand {

    public void execute(CommandSender sender) {
        if (LiquidPractice.getInstance().getLMSManager().getActiveLMS() == null) {
            sender.sendMessage(CC.RED + "There isn't an active LMS event.");
            return;
        }

        LiquidPractice.getInstance().getLMSManager().getActiveLMS().end();
    }

}
