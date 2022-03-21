package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label="arena addnormalkits", permission="liquidpractice.admin")
public class ArenaAddNormalKitCommand {
    public void execute(Player player, @CPL("Arena") Arena arena) {

        if (arena == null) {
            player.sendMessage(CC.translate("&7Arena does not exist."));
            return;
        }

        for ( Kit kit : Kit.getKits() ) {
            if (kit == null) {
                player.sendMessage(CC.translate("&7There are no kits."));
                return;
            }
        if (kit.getGameRules().isBuild() || kit.getGameRules().isBoxuhc() || kit.getGameRules().isSpleef() || kit.getGameRules().isSumo() || kit.getGameRules().isParkour() || kit.getGameRules().isNoitems() || kit.getGameRules().isWaterkill()) {
            return;
        }

        if (!arena.getKits().contains(kit.getName()))
            arena.getKits().add(kit.getName());
        player.sendMessage(CC.translate("&7Successfully added the kit &b" + kit.getName() + "&7 to &b" + arena.getName() + "&7."));
    }
        arena.save();

    }
}
