package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"kit netheruhc"}, permission = "liquidpractice.admin")
public class KitNetherUHCCommand {
    public void execute(Player player, @CPL("kit") Kit kit) {
        if (kit == null) {
            player.sendMessage((CC.translate("&8[&b&lArray&8] &c")) + "Kit does not exist");
        } else {
            if (kit.getGameRules().isNetheruhc()) {
                kit.getGameRules().setNetheruhc(false);
            } else if (!kit.getGameRules().isNetheruhc()) {
                kit.getGameRules().setNetheruhc(true);
            }
            kit.save();
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Updated NetherUHC mode for &b" + kit.getName() +  " &7to &b" + (kit.getGameRules().isNetheruhc() ? "true!" : "false!")));
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Please include at least 3 beds in the kit to make it a NetherUHC kit!"));
        }
    }
}
