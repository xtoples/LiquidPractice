package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit boxuhc"}, permission = "liquidpractice.admin")
public class KitBoxUHCCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage((CC.translate("&c")) + "Kit does not exist");
        } else {
            if (kit.getGameRules().isBoxuhc()) {
                kit.getGameRules().setBoxuhc(false);
            } else if (!kit.getGameRules().isBoxuhc()) {
                kit.getGameRules().setBoxuhc(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&7Updated BoxUHC mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isBoxuhc() ? "true!" : "false!")));
            player.sendMessage(CC.translate("&8[&bLTIP&8] &7Please make the Arena a Barrier Box with Wood filled inside it except on the spawn points!"));
        }
    }
}
