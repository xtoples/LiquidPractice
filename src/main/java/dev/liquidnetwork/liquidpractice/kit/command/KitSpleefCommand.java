package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit spleef"}, permission = "liquidpractice.admin")
public class KitSpleefCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isSpleef()) {
                kit.getGameRules().setSpleef(false);
            } else if (!kit.getGameRules().isSpleef()) {
                kit.getGameRules().setSpleef(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated spleef mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isSpleef() ? "true!" : "false!")));
        }
    }
}
