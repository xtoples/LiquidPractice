package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "kit save", permission = "liquidpractice.admin")
public class KitSaveCommand {

    public void execute(CommandSender sender) {
        for ( Kit kit : Kit.getKits() ) {
            kit.save();
        }
        sender.sendMessage(CC.translate("&8[&b&lArray&8] &7You saved &b" + Kit.getKits().size() +  " the kits!"));
    }
}
