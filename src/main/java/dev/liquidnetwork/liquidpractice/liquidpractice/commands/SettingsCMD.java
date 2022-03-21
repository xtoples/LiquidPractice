package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.settings.SettingsMenu;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = {"options", "settings", "preferences"})
public class SettingsCMD {

    public void execute(Player player) {
        new SettingsMenu().openMenu(player);
    }

}