package dev.liquidnetwork.liquidpractice.kiteditor.menu;

import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.menu.Button;
import dev.liquidnetwork.liquidpractice.util.external.menu.Menu;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitEditorSelectKitMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "&7Select a kit";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        Kit.getKits().forEach(kit -> {
            if (kit.isEnabled()) {
                if (kit.getGameRules().isEditable()) {
                    buttons.put(buttons.size(), new KitDisplayButton(kit));
                }
            }
        });

        return buttons;
    }

    @AllArgsConstructor
    private static class KitDisplayButton extends Button {

        private final Kit kit;

        @Override
        public ItemStack getButtonItem(Player player) {
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&bClick to edit this kit.");
            return new ItemBuilder(kit.getDisplayIcon())
                    .name(kit.getDisplayName()).lore(lore)
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.closeInventory();

            Profile profile = Profile.getByUuid(player.getUniqueId());
            profile.getKitEditor().setSelectedKit(kit);
            profile.getKitEditor().setPreviousState(profile.getState());

            new KitManagementMenu(kit).openMenu(player);
        }

    }
}
