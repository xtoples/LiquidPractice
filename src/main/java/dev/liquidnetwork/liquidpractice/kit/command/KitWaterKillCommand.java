package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit waterkill"}, permission = "liquidpractice.admin")
public class KitWaterKillCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isWaterkill()) {
                kit.getGameRules().setWaterkill(false);
            } else if (!kit.getGameRules().isWaterkill()) {
                kit.getGameRules().setWaterkill(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated water kill mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isWaterkill() ? "true!" : "false!")));
        }
    }
}
