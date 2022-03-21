package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit elo", "kit ranked"}, permission = "liquidpractice.admin")
public class KitSetRankedCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isRanked()) {
                kit.getGameRules().setRanked(false);
            } else if (!kit.getGameRules().isRanked()) {
                kit.getGameRules().setRanked(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated ranked mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isRanked() ? "true!" : "false!")));
        }
    }
}
