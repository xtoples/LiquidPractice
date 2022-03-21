package dev.liquidnetwork.liquidpractice.event.menu;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.enums.EventType;
import dev.liquidnetwork.liquidpractice.event.types.parkour.command.ParkourJoinCommand;
import dev.liquidnetwork.liquidpractice.event.types.spleef.command.SpleefJoinCommand;
import dev.liquidnetwork.liquidpractice.event.types.sumo.command.SumoJoinCommand;
import dev.liquidnetwork.liquidpractice.event.types.wizard.command.WizardJoinCommand;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.menu.Button;
import dev.liquidnetwork.liquidpractice.util.external.menu.Menu;
import dev.liquidnetwork.liquidpractice.event.types.brackets.command.BracketsJoinCommand;
import dev.liquidnetwork.liquidpractice.event.types.lms.command.LMSJoinCommand;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveEventSelectEventMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "&7Select an active Event";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        int i = 0;
        for ( EventType eventType : EventType.values()) {
            if (eventType.getTitle().equals("&b&lLMS")) {
                if (LiquidPractice.getInstance().getLMSManager().getActiveLMS() != null && LiquidPractice.getInstance().getLMSManager().getActiveLMS().isWaiting()) {
                    buttons.put(i, new SelectEventButton(EventType.LMS));
                    i++;
                }
            }
            if (eventType.getTitle().equals("&b&lBrackets")) {
                if (LiquidPractice.getInstance().getBracketsManager().getActiveBrackets() != null && LiquidPractice.getInstance().getBracketsManager().getActiveBrackets().isWaiting()) {
                    buttons.put(i, new SelectEventButton(EventType.BRACKETS));
                    i++;
                }
            }
            if (eventType.getTitle().equals("&b&lSumo")) {
                if (LiquidPractice.getInstance().getSumoManager().getActiveSumo() != null && LiquidPractice.getInstance().getSumoManager().getActiveSumo().isWaiting()) {
                    buttons.put(i, new SelectEventButton(EventType.SUMO));
                    i++;
                }
            }
            if (eventType.getTitle().equals("&b&lParkour")) {
                if (LiquidPractice.getInstance().getParkourManager().getActiveParkour() != null && LiquidPractice.getInstance().getParkourManager().getActiveParkour().isWaiting()) {
                    buttons.put(i, new SelectEventButton(EventType.PARKOUR));
                    i++;
                }
            }
            if (eventType.getTitle().equals("&b&lSpleef")) {
                if (LiquidPractice.getInstance().getSpleefManager().getActiveSpleef() != null && LiquidPractice.getInstance().getSpleefManager().getActiveSpleef().isWaiting()) {
                    buttons.put(i, new SelectEventButton(EventType.SPLEEF));
                    i++;
                }
            }
            if (eventType.getTitle().equals("&b&lWizard")) {
                if (LiquidPractice.getInstance().getWizardManager().getActiveWizard() != null && LiquidPractice.getInstance().getWizardManager().getActiveWizard().isWaiting()) {
                    buttons.put(i, new SelectEventButton(EventType.WIZARD));
                    i++;
                }
            }
        }
        return buttons;
    }


    @AllArgsConstructor
    private static class SelectEventButton extends Button {

        private final EventType eventType;

        @Override
        public ItemStack getButtonItem(Player player) {
            List<String> lore = new ArrayList<>();

            switch (eventType.getTitle()) {
                case "&b&lBrackets":
                    lore= LiquidPractice.getInstance().getBracketsManager().getActiveBrackets().getLore();
                    break;
                case "&b&lSumo":
                    lore= LiquidPractice.getInstance().getSumoManager().getActiveSumo().getLore();
                    break;
                case "&b&lLMS":
                    lore= LiquidPractice.getInstance().getLMSManager().getActiveLMS().getLore();
                    break;
                case "&b&lParkour":
                    lore= LiquidPractice.getInstance().getParkourManager().getActiveParkour().getLore();
                    break;
                case "&b&lSpleef":
                    lore= LiquidPractice.getInstance().getSpleefManager().getActiveSpleef().getLore();
                    break;
                case "&b&lWizard":
                    lore= LiquidPractice.getInstance().getWizardManager().getActiveWizard().getLore();
                    break;
            }
            lore.add("&bClick to join");
            lore.add(CC.MENU_BAR);


            return new ItemBuilder(eventType.getMaterial())
                    .name(eventType.getTitle())
                    .lore(lore)
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            Menu.currentlyOpenedMenus.get(player.getName()).setClosedByMenu(true);
            player.closeInventory();
            switch (eventType.getTitle()) {
                case "&b&lBrackets":
                    BracketsJoinCommand.execute(player);
                    break;
                case "&b&lSumo":
                    SumoJoinCommand.execute(player);
                    break;
                case "&b&lLMS":
                    LMSJoinCommand.execute(player);
                    break;
                case "&b&lParkour":
                    ParkourJoinCommand.execute(player);
                    break;
                case "&b&lSpleef":
                    SpleefJoinCommand.execute(player);
                    break;
                case "&b&lWizard":
                    WizardJoinCommand.execute(player);
                    break;
            }
        }

    }

}
