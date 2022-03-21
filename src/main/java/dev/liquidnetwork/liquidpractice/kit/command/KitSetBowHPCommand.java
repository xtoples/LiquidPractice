package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit bowhp"}, permission = "liquidpractice.admin")
public class KitSetBowHPCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7That kit does not exist."));
        } else {
            if (kit.getGameRules().isBowhp()) {
                kit.getGameRules().setBowhp(false);
            } else if (!kit.getGameRules().isBowhp()) {
                kit.getGameRules().setBowhp(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Updated bow-hp mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isBowhp() ? "true!" : "false!")));
        }
    }
}
