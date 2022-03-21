package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="kit hitdelay", permission = "liquidpractice.admin")
public class KitSetHitDelayCommand {
    public void execute(Player player, @CPL("kit") Kit kit, @CPL("delay") int delay) {
        if (kit == null) {
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7That kit does not exist."));
        } else {
            kit.getGameRules().setHitDelay(delay);
            kit.save();
            player.sendMessage(CC.translate("&8[&b&lArray&8] &7Updated &b" + kit.getName() + " &7hitdelay set to &b" + delay));
        }
    }
}