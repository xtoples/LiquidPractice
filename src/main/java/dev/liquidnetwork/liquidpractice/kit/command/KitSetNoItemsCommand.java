package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit noitems"}, permission = "liquidpractice.admin")
public class KitSetNoItemsCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isNoitems()) {
                kit.getGameRules().setNoitems(false);
            } else if (!kit.getGameRules().isNoitems()) {
                kit.getGameRules().setNoitems(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated no-items mode for &b" + kit.getName() + " &7to &b" + (kit.getGameRules().isNoitems() ? "true!" : "false!")));
        }
    }
}
