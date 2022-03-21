package dev.liquidnetwork.liquidpractice.kit.command;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="kit enable", permission = "liquidpractice.admin")
public class KitEnableCommand {
    public void execute(Player player, @CPL("kit") String kit) {
         Kit kits = Kit.getByName(kit);
         if (kits == null) {
             player.sendMessage(CC.translate("&cThat Kit does not exist!"));
             return;
         }
         kits.setEnabled(true);
         player.sendMessage(CC.translate("&aEnabled the kit " + kit));
    }
}
