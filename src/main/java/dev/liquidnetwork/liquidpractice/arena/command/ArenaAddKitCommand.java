package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.enums.ArenaType;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena addkit", permission = "liquidpractice.admin")
public class ArenaAddKitCommand {

    public void execute(Player player, @CPL("arena") Arena arena, @CPL("kit") Kit kit) {
        if (arena == null) {
            player.sendMessage(CC.translate("&7Arena does not exist"));
            return;
        }

        if (kit == null) {
            player.sendMessage(CC.translate("&7Kit does not exist"));
            return;
        }

        if (arena.getType() == ArenaType.SHARED && kit.getGameRules().isBuild()) {
            player.sendMessage(CC.translate("&7The arena is set to type shared and you can't add build kits to it!"));
            return;
        }

        if (!arena.getKits().contains(kit.getName()))
            arena.getKits().add(kit.getName());

        player.sendMessage(CC.translate("&7Successfully added the kit &b" + kit.getName() + "&7 to &b" + arena.getName()));
        arena.save();
    }

}