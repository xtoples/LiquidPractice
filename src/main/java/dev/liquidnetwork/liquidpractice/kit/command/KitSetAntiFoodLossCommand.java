package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit antifoodloss"}, permission = "liquidpractice.admin")
public class KitSetAntiFoodLossCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7That kit does not exist."));
} else {
            if (kit.getGameRules().isAntifoodloss()) {
                kit.getGameRules().setAntifoodloss(false);
            } else if (!kit.getGameRules().isAntifoodloss()) {
                kit.getGameRules().setAntifoodloss(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Updated anti-food-loss mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isAntifoodloss() ? "true!" : "false!")));
        }
    }
}
