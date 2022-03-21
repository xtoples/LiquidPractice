package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit editable", "kit enable editable"}, permission = "liquidpractice.admin")
public class KitEditableCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
} else {
            if (kit.getGameRules().isEditable()) {
                kit.getGameRules().setEditable(false);
            } else if (!kit.getGameRules().isEditable()) {
                kit.getGameRules().setEditable(true);
            }
            kit.save();
            player.sendMessage((CC.translate("&a")) + "Kit set editable mode to " + (kit.getGameRules().isBuild() ? "true!" : "false!"));
        }
    }
}
