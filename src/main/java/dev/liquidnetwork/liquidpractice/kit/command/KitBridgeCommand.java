package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit bridge"}, permission = "liquidpractice.admin")
public class KitBridgeCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage((CC.translate("&c")) + "Kit does not exist");
        } else {
            if (kit.getGameRules().isBridge()) {
                kit.getGameRules().setBridge(false);
            } else if (!kit.getGameRules().isBridge()) {
                kit.getGameRules().setBridge(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Updated build mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isBridge() ? "true!" : "false!")));
        }
    }
}

