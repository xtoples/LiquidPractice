package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit showhealth"}, permission = "liquidpractice.admin")
public class KitShowHealthCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isShowHealth()) {
                kit.getGameRules().setShowHealth(false);
            } else if (!kit.getGameRules().isShowHealth()) {
                kit.getGameRules().setShowHealth(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated show-health mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isShowHealth() ? "true!" : "false!")));
        }
    }
}
