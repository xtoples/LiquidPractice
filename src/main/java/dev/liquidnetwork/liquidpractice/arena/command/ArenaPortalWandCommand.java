package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.selection.Selection;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"arena wand", "arena portalwand", "arena selection", "arena portal"}, permission = "liquidpractice.admin")
public class ArenaPortalWandCommand {
    public void execute(Player player) {
        if (player.getInventory().first(Selection.SELECTION_WAND) != -1) {
            player.getInventory().remove(Selection.SELECTION_WAND);
        } else {
            player.getInventory().addItem(Selection.SELECTION_WAND);
            player.sendMessage(CC.translate("&8[&bLTIP&8] &7&oLeft-Click to select first position and Right-Click to select second position."));
        }

        player.updateInventory();
    }
}
