package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit combo"}, permission = "liquidpractice.admin")
public class KitComboCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage(CC.translate("&7That kit does not exist."));
        } else {
            if (kit.getGameRules().isCombo()) {
                kit.getGameRules().setCombo(false);
            } else if (!kit.getGameRules().isCombo()) {
                kit.getGameRules().setCombo(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated combo mode for &b" + kit.getName() + " &7to &b" + (kit.getGameRules().isCombo() ? "true!" : "false!")));
            player.sendMessage(CC.translate("&8[&bLTIP&8] &7This will set the No-Damage Ticks to 2 and players will be able to hit faster!"));
        }
    }
}
