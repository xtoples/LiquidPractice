package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit build"}, permission = "liquidpractice.admin")
public class KitBuildCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
} else {
            if (kit.getGameRules().isBuild()) {
                kit.getGameRules().setBuild(false);
            } else if (!kit.getGameRules().isBuild()) {
                kit.getGameRules().setBuild(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated build mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isBuild() ? "true!" : "false!")));
        }
    }
}
