package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.liquidpractice.listener.GoldenHeads;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice goldenhead"}, permission="liquidpractice.admin")
public class GoldenHeadCMD {
    public void execute(Player p, @CPL("[normal|bridge]") String type) {
        if (type.equalsIgnoreCase("bridge")) {
            p.sendMessage(CC.translate("&7You received a &bAdam's Apple&7."));
            p.getInventory().addItem(GoldenHeads.getBridgeApple());
            return;
        } else if (type.equalsIgnoreCase("normal")) {
            p.sendMessage(CC.translate("&7You received a &bGolden head&7."));
            p.getInventory().addItem(GoldenHeads.goldenHeadItem());
            return;
        }
        p.sendMessage(CC.translate("&7Please pick specify &b'normal' &7or &b'bridge' &7type."));
    }
}
