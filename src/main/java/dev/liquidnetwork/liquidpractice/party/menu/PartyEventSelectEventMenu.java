package dev.liquidnetwork.liquidpractice.party.menu;

import java.beans.ConstructorProperties;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.enums.PartyEventType;
import dev.liquidnetwork.liquidpractice.match.team.Team;
import dev.liquidnetwork.liquidpractice.match.team.TeamPlayer;
import dev.liquidnetwork.liquidpractice.match.types.HCFMatch;
import dev.liquidnetwork.liquidpractice.party.Party;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.menu.Button;
import dev.liquidnetwork.liquidpractice.util.external.menu.Menu;
import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.util.chat.CC;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class PartyEventSelectEventMenu extends Menu
{
    @Override
    public String getTitle(final Player player) {
        return "&bSelect a party event";
    }

    @Override
    public Map<Integer, Button> getButtons(final Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();
        if (LiquidPractice.getInstance().getMainConfig().getBoolean("LiquidPractice.HCF-Enabled")) {
            buttons.put(2, new SelectEventButton(PartyEventType.SPLIT));
            buttons.put(4, new SelectEventButton(PartyEventType.FFA));
            buttons.put(6, new SelectEventButton(PartyEventType.HCF));
        } else {
            buttons.put(3, new SelectEventButton(PartyEventType.SPLIT));
            buttons.put(5, new SelectEventButton(PartyEventType.FFA));
        }
        return buttons;
    }

    private static class SelectEventButton extends Button
    {
        private final PartyEventType partyEventType;

        @Override
        public ItemStack getButtonItem(final Player player) {
            List<String> lore = new ArrayList<>();
            lore.add(CC.GRAY + CC.STRIKE_THROUGH + "------------------------");
            if (this.partyEventType == PartyEventType.FFA) {
                lore.add(CC.translate("&7Unleash your party in"));
                lore.add(CC.translate("&7an &b&lFFA Match&7, Let them"));
                lore.add(CC.translate("&7fight for themselves"));
                lore.add(CC.translate("&7Last player standing wins!"));
            }
            if (this.partyEventType == PartyEventType.SPLIT) {
                lore.add(CC.translate("&7Split your party into"));
                lore.add(CC.translate("&b&lTwo Teams &7and let them"));
                lore.add(CC.translate("&7duel as Team vs Team,"));
                lore.add(CC.translate("&7Last Team standing wins!"));
            }
            if (this.partyEventType == PartyEventType.HCF) {
                lore.add(CC.translate("&7Split your party into"));
                lore.add(CC.translate("&7team teams and let them duel"));
                lore.add(CC.translate("&7with the usual &b&lHCF Kits"));
                lore.add(CC.translate("&7Last Team standing wins!"));
            }
            lore.add(CC.GRAY + CC.STRIKE_THROUGH + "------------------------");
            return new ItemBuilder(this.partyEventType.getMaterial()).name("&b&l" + this.partyEventType.getName()).lore(lore).build();
        }

        @Override
        public void clicked(final Player player, final ClickType clickType) {
            final Profile profile = Profile.getByUuid(player.getUniqueId());
            if (profile.getParty() == null) {
                player.sendMessage(CC.RED + "You are not in a party.");
                return;
            }
            if (this.partyEventType == PartyEventType.FFA || this.partyEventType == PartyEventType.SPLIT) {
                Menu.currentlyOpenedMenus.get(player.getName()).setClosedByMenu(true);
                new PartyEventSelectKitMenu(this.partyEventType).openMenu(player);
            }
            else {
                Menu.currentlyOpenedMenus.get(player.getName()).setClosedByMenu(true);
                player.closeInventory();
                if (profile.getParty() == null) {
                    player.sendMessage(CC.RED + "You are not in a party.");
                    return;
                }
                if (profile.getParty().getTeamPlayers().size() <= 1) {
                    player.sendMessage(CC.RED + "You do not have enough players in your party to start a party event.");
                    return;
                }
                final Party party = profile.getParty();
                Arena arena;
                if (this.partyEventType.equals(PartyEventType.HCF)) {
                    arena = Arena.getRandom(Kit.getByName("HCFTeamFight"));
                }
                else {
                    player.sendMessage(CC.RED + "There are no available arenas.");
                    return;
                }
                if (arena == null) {
                    player.sendMessage(CC.RED + "There are no available arenas.");
                    return;
                }
                arena.setActive(true);
                final Team teamA = new Team(new TeamPlayer(party.getPlayers().get(0)));
                final Team teamB = new Team(new TeamPlayer(party.getPlayers().get(1)));
                final List<Player> players = new ArrayList<>(party.getPlayers());
                Collections.shuffle(players);
                Match match;
                match = new HCFMatch(teamA, teamB, arena);
                for (final Player otherPlayer : players) {
                    if (!teamA.getLeader().getUuid().equals(otherPlayer.getUniqueId())) {
                        if (teamB.getLeader().getUuid().equals(otherPlayer.getUniqueId())) {
                            continue;
                        }
                        if (teamA.getTeamPlayers().size() > teamB.getTeamPlayers().size()) {
                            teamB.getTeamPlayers().add(new TeamPlayer(otherPlayer));
                        }
                        else {
                            teamA.getTeamPlayers().add(new TeamPlayer(otherPlayer));
                        }
                    }
                }
                match.start();
            }
        }

        @ConstructorProperties({ "partyEventType" })
        public SelectEventButton(final PartyEventType partyEventType) {
            this.partyEventType=partyEventType;
        }
    }
}
