package dev.liquidnetwork.liquidpractice.event.menu;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.event.types.brackets.Brackets;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.menu.Button;
import dev.liquidnetwork.liquidpractice.util.external.menu.Menu;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMS;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class EventSelectKitMenu extends Menu {

    private final String event;

    @Override
    public String getTitle(Player player) {
        return "&7Select a kit to host " + event;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for (Kit kit : Kit.getKits()) {
            if (kit.isEnabled() && !kit.getGameRules().isNoitems() && !kit.getGameRules().isLavakill() && !kit.getGameRules().isWaterkill() && !kit.getGameRules().isSpleef() && !kit.getGameRules().isBuild() && !kit.getGameRules().isSumo()) {
                buttons.put(buttons.size(), new SelectKitButton(event, kit));
            }
        }
        return buttons;
    }

    @AllArgsConstructor
    private static class SelectKitButton extends Button {

        private final String event;
        private final Kit kit;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(kit.getDisplayIcon())
                    .name("&b" + kit.getName())
                    .clearFlags()
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (event.equals("Brackets")) {
                if (LiquidPractice.getInstance().getBracketsManager().getActiveBrackets() != null) {
                    player.sendMessage(CC.RED + "There is already an active Brackets Event.");
                    return;
                }

                if (!LiquidPractice.getInstance().getBracketsManager().getCooldown().hasExpired()) {
                    player.sendMessage(CC.RED + "There is an active cooldown for the Brackets Event.");
                    return;
                }

                LiquidPractice.getInstance().getBracketsManager().setActiveBrackets(new Brackets(player, kit));

                for (Player other : LiquidPractice.getInstance().getServer().getOnlinePlayers()) {
                    Profile profile = Profile.getByUuid(other.getUniqueId());

                    if (profile.isInLobby()) {
                        if (!profile.getKitEditor().isActive()) {
                        PlayerUtil.reset(player, false);
                        profile.refreshHotbar();
                        }
                    }
                }
            } else {
                if (LiquidPractice.getInstance().getLMSManager().getActiveLMS() != null) {
                    player.sendMessage(CC.RED + "There is already an active LMS Event.");
                    return;
                }

                if (!LiquidPractice.getInstance().getLMSManager().getCooldown().hasExpired()) {
                    player.sendMessage(CC.RED + "There is an active cooldown for the LMS Event.");
                    return;
                }

                LiquidPractice.getInstance().getLMSManager().setActiveLMS(new LMS(player, kit));

                for (Player other : LiquidPractice.getInstance().getServer().getOnlinePlayers()) {
                    Profile profile = Profile.getByUuid(other.getUniqueId());

                    if (profile.isInLobby()) {
                        if (!profile.getKitEditor().isActive()) {
                        PlayerUtil.reset(player, false);
                        profile.refreshHotbar();
                        }
                    }
                }
            }
            Menu.currentlyOpenedMenus.get(player.getName()).setClosedByMenu(true);
            player.closeInventory();
        }

    }

}
