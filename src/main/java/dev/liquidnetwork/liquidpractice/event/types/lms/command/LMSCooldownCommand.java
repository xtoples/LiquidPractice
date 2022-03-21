package dev.liquidnetwork.liquidpractice.event.types.lms.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "lms cooldown", permission = "practice.lms")
public class LMSCooldownCommand {

    public void execute(CommandSender sender) {
        if (LiquidPractice.getInstance().getLMSManager().getCooldown().hasExpired()) {
            sender.sendMessage(CC.RED + "There isn't a LMS Event cooldown.");
            return;
        }

        sender.sendMessage(CC.GREEN + "You reset the LMS Event cooldown.");

        LiquidPractice.getInstance().getLMSManager().setCooldown(new Cooldown(0));
    }

}
