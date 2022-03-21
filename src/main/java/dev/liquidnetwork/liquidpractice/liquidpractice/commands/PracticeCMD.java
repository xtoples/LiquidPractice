package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandMeta(label = { "help", "practice"})
public class PracticeCMD {
    public void execute(final Player player) {
        player.sendMessage(CC.CHAT_BAR);
        player.sendMessage(CC.translate("         &b&lLiquid &3&lNetwork " + ChatColor.GRAY + "(Practice)"));
        player.sendMessage(CC.CHAT_BAR);
        player.sendMessage(CC.translate("&bLiquidPractice Commands:"));
        player.sendMessage(CC.translate("&7/duel <Name> &8- &7Send a player a duel request"));
        player.sendMessage(CC.translate("&7/spec <Name> &8- &7Spectate a player's ongoing match"));
        player.sendMessage(CC.translate("&7/settings &8- &7Edit preferences on the server"));
        player.sendMessage(CC.translate("&7/party &8- &7See all party related commands"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&7To send a message to your party do &7'&d@&7' <message>:"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&bServer Links:"));
        player.sendMessage(CC.translate("&7Website &8- &bliquidpvp.club"));
        player.sendMessage(CC.translate("&7Discord &8- &bdiscord.liquidpvp.club"));
        player.sendMessage(CC.translate("&7Store &8- &bstore.liquidpvp.club"));
        player.sendMessage("");
        player.sendMessage(CC.translate("&7Welcome to the &b&lLiquid &3&lPractice!"));
        player.sendMessage(CC.CHAT_BAR);
    }
}
