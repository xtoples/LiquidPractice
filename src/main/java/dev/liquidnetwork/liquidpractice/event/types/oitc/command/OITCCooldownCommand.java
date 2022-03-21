package dev.liquidnetwork.liquidpractice.event.types.oitc.command;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "OITC cooldown", permission = "liquidPractice.oitc")
public class OITCCooldownCommand {

    public void execute(CommandSender sender) {
        if (LiquidPractice.getInstance().getOITCManager().getCooldown().hasExpired()) {
            sender.sendMessage(CC.RED + "There isn't a OITC Event cooldown.");
            return;
        }

        sender.sendMessage(CC.GREEN + "Successfully reset the OITC Event cooldown.");

        LiquidPractice.getInstance().getOITCManager().setCooldown(new Cooldown(0));
    }

}
