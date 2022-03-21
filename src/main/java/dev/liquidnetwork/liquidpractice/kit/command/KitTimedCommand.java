package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit timed"}, permission = "liquidpractice.admin")
public class KitTimedCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isTimed()) {
                kit.getGameRules().setTimed(false);
            } else if (!kit.getGameRules().isTimed()) {
                kit.getGameRules().setTimed(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated timed mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isTimed() ? "true!" : "false!")));
        }
    }
}
