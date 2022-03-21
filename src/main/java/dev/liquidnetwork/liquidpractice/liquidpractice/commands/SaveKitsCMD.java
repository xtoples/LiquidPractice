package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice savekits", "liquidpractice kits save"}, permission="liquidpractice.admin")
public class SaveKitsCMD {
    public void execute(Player p) {
        Kit.getKits().forEach(Kit::save);
        p.sendMessage(CC.translate("&bSaving Kits..."));
    }
}
