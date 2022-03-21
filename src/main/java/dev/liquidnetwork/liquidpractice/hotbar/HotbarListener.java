package dev.liquidnetwork.liquidpractice.hotbar;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.enums.HotbarType;
import dev.liquidnetwork.liquidpractice.enums.PartyMessageType;
import dev.liquidnetwork.liquidpractice.event.types.brackets.Brackets;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.party.Party;
import dev.liquidnetwork.liquidpractice.party.menu.ManagePartySettings;
import dev.liquidnetwork.liquidpractice.party.menu.OtherPartiesMenu;
import dev.liquidnetwork.liquidpractice.party.menu.PartyEventSelectEventMenu;
import dev.liquidnetwork.liquidpractice.queue.QueueType;
import dev.liquidnetwork.liquidpractice.queue.menu.QueueSelectKitMenu;
import dev.liquidnetwork.liquidpractice.event.menu.ActiveEventSelectEventMenu;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMS;
import dev.liquidnetwork.liquidpractice.profile.meta.ProfileRematchData;
import dev.liquidnetwork.liquidpractice.event.types.sumo.Sumo;
import dev.liquidnetwork.liquidpractice.kiteditor.menu.KitEditorSelectKitMenu;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.profile.ProfileState;
import dev.liquidnetwork.liquidpractice.profile.menu.PlayerMenu;
import dev.liquidnetwork.liquidpractice.settings.SettingsMenu;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.config.BasicConfigurationFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;

public class HotbarListener implements Listener
{

    BasicConfigurationFile config = LiquidPractice.getInstance().getMessagesConfig();

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getItem() != null && event.getAction().name().contains("RIGHT")) {
            final Player player = event.getPlayer();
            final Profile profile = Profile.getByUuid(player.getUniqueId());
            final HotbarType hotbarType= Hotbar.fromItemStack(event.getItem());
            if (hotbarType == null) {
                return;
            }
            event.setCancelled(true);
            switch (hotbarType) {
                case QUEUE_JOIN_RANKED: {
                    if (!player.hasPermission("liquidpractice.donator")) {
                        if (LiquidPractice.getInstance().getMainConfig().getBoolean("Ranked.Require-Kills")) {
                            if (profile.getTotalWins() < LiquidPractice.getInstance().getMainConfig().getInteger("Ranked.Required-Kills")) {
                                for ( String error : LiquidPractice.getInstance().getMessagesConfig().getStringList("Ranked.Required") ) {
                                    player.sendMessage(CC.translate(error));
                                }
                                break;
                            }
                        }
                    }
                    if (!LiquidPractice.getInstance().getMainConfig().getBoolean("Ranked.Enabled")) {
                        player.sendMessage(CC.translate(LiquidPractice.getInstance().getMessagesConfig().getString("Ranked.Disabled")));
                        break;
                    }
                    if (!profile.isBusy(player)) {
                        new QueueSelectKitMenu(QueueType.RANKED).openMenu(event.getPlayer());
                        break;
                    }
                    break;
                }
                case QUEUE_JOIN_UNRANKED: {
                    if (!profile.isBusy(player)) {
                        new QueueSelectKitMenu(QueueType.UNRANKED).openMenu(event.getPlayer());
                        break;
                    }
                    break;
                }
                case QUEUE_LEAVE: {
                    if (profile.isInQueue()) {
                        profile.getQueue().removePlayer(profile.getQueueProfile());
                        break;
                    }
                    break;
                }
                case PARTY_EVENTS: {
                    new PartyEventSelectEventMenu().openMenu(player);
                    break;
                }
                case OTHER_PARTIES: {
                    new OtherPartiesMenu().openMenu(event.getPlayer());
                    break;
                }
                case PARTY_INFO: {
                    profile.getParty().sendInformation(player);
                    break;
                }
                case PARTY_SETTINGS: {
                    new ManagePartySettings().openMenu(event.getPlayer());
                    break;
                }
                case SETTINGS_MENU: {
                    new SettingsMenu().openMenu(event.getPlayer());
                    break;
                }
                case LEADERBOARDS_MENU: {
                    new PlayerMenu().openMenu(event.getPlayer());
                    break;
                }
                case KIT_EDITOR: {
                    if (profile.isInLobby() || profile.isInQueue()) {
                        new KitEditorSelectKitMenu().openMenu(event.getPlayer());
                        break;
                    }
                    break;
                }
                case PARTY_CREATE: {
                    if (profile.getParty() != null) {
                        player.sendMessage(CC.translate(config.getString("Party.Already-Have-Party")));
                        return;
                    }
                    if (!profile.isInLobby()) {
                        player.sendMessage(CC.translate(config.getString("Party.Not-In-Lobby")));
                        return;
                    }
                    profile.setParty(new Party(player));
                    PlayerUtil.reset(player, false);
                    profile.refreshHotbar();
                    player.sendMessage(PartyMessageType.CREATED.format());
                    break;
                }
                case PARTY_DISBAND: {
                    if (profile.getParty() == null) {
                        player.sendMessage(CC.translate(config.getString("Party.Dont-Have-Party")));
                        return;
                    }
                    if (!profile.getParty().isLeader(player.getUniqueId())) {
                        player.sendMessage(CC.translate(config.getString("Party.Not-Leader")));
                        return;
                    }
                    profile.getParty().disband();
                    break;
                }
                case PARTY_INFORMATION: {
                    if (profile.getParty() == null) {
                        player.sendMessage(CC.translate(config.getString("Party.Dont-Have-Party")));
                        return;
                    }
                    profile.getParty().sendInformation(player);
                    break;
                }
                case PARTY_LEAVE: {
                    if (profile.getParty() == null) {
                        player.sendMessage(CC.translate(config.getString("Party.Dont-Have-Party")));
                        return;
                    }
                    if (profile.getParty().getLeader().getUuid().equals(player.getUniqueId())) {
                        profile.getParty().disband();
                        break;
                    }
                    profile.getParty().leave(player, false);
                    break;
                }
                case EVENT_JOIN: {
                 new ActiveEventSelectEventMenu().openMenu(player);
                 break;
                }
                case SUMO_LEAVE: {
                    final Sumo activeSumo = LiquidPractice.getInstance().getSumoManager().getActiveSumo();
                    if (activeSumo == null) {
                        player.sendMessage(CC.RED + "There is no active sumo.");
                        return;
                    }
                    if (!profile.isInSumo() || !activeSumo.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active sumo.");
                        return;
                    }
                    LiquidPractice.getInstance().getSumoManager().getActiveSumo().handleLeave(player);
                    break;
                }
                case BRACKETS_LEAVE: {
                    final Brackets activeBrackets = LiquidPractice.getInstance().getBracketsManager().getActiveBrackets();
                    if (activeBrackets == null) {
                        player.sendMessage(CC.RED + "There is no active brackets.");
                        return;
                    }
                    if (!profile.isInBrackets() || !activeBrackets.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active brackets.");
                        return;
                    }
                    LiquidPractice.getInstance().getBracketsManager().getActiveBrackets().handleLeave(player);
                    break;
                }
                case LMS_LEAVE: {
                    final LMS activeLMS = LiquidPractice.getInstance().getLMSManager().getActiveLMS();
                    if (activeLMS == null) {
                        player.sendMessage(CC.RED + "There is no active KoTH.");
                        return;
                    }
                    if (!profile.isInLMS() || !activeLMS.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active KoTH.");
                        return;
                    }
                    LiquidPractice.getInstance().getLMSManager().getActiveLMS().handleLeave(player);
                    break;
                }
                case PARKOUR_LEAVE: {
                    final Parkour activeParkour = LiquidPractice.getInstance().getParkourManager().getActiveParkour();
                    if (activeParkour == null) {
                        player.sendMessage(CC.RED + "There is no active Parkour.");
                        return;
                    }
                    if (!profile.isInParkour() || !activeParkour.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active Parkour.");
                        return;
                    }
                    LiquidPractice.getInstance().getParkourManager().getActiveParkour().handleLeave(player);
                    break;
                }
                case WIZARD_LEAVE: {
                    final Wizard activeWizard = LiquidPractice.getInstance().getWizardManager().getActiveWizard();
                    if (activeWizard == null) {
                        player.sendMessage(CC.RED + "There is no active Wizard.");
                        return;
                    }
                    if (!profile.isInParkour() || !activeWizard.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active Wizard.");
                        return;
                    }
                    LiquidPractice.getInstance().getWizardManager().getActiveWizard().handleLeave(player);
                    break;
                }
                case PARKOUR_SPAWN: {
                    final Parkour activeParkour = LiquidPractice.getInstance().getParkourManager().getActiveParkour();
                    if (activeParkour == null) {
                        player.sendMessage(CC.RED + "There is no active Parkour.");
                        return;
                    }
                    if (!profile.isInParkour() || !activeParkour.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active Parkour.");
                        return;
                    }
                    if (profile.getParkour().getEventPlayer(player).getLastLocation() != null) {
                        player.teleport(profile.getParkour().getEventPlayer(player).getLastLocation());
                        break;
                    } else {
                        player.teleport(LiquidPractice.getInstance().getParkourManager().getParkourSpawn());
                    }
                }
                case SPLEEF_LEAVE: {
                    final Spleef activeSpleef = LiquidPractice.getInstance().getSpleefManager().getActiveSpleef();
                    if (activeSpleef == null) {
                        player.sendMessage(CC.RED + "There is no active Spleef.");
                        return;
                    }
                    if (!profile.isInSpleef() || !activeSpleef.getEventPlayers().containsKey(player.getUniqueId())) {
                        player.sendMessage(CC.RED + "You are not apart of the active Spleef.");
                        return;
                    }
                    LiquidPractice.getInstance().getSpleefManager().getActiveSpleef().handleLeave(player);
                    break;
                }
                case SPECTATE_STOP: {
                    if (profile.isInFight() && !profile.getMatch().getTeamPlayer(player).isAlive()) {
                        profile.getMatch().getTeamPlayer(player).setDisconnected(true);
                        profile.setState(ProfileState.IN_LOBBY);
                        profile.setMatch(null);
                        break;
                    }
                    if (!profile.isSpectating()) {
                        player.sendMessage(CC.RED + "You are not spectating a match.");
                        break;
                    }
                    if (profile.getMatch() != null) {
                        profile.getMatch().removeSpectator(player);
                        break;
                    }
                    if (profile.getSumo() != null) {
                        profile.getSumo().removeSpectator(player);
                        break;
                    }
                    if (profile.getBrackets() != null) {
                        profile.getBrackets().removeSpectator(player);
                        break;
                    }
                    if (profile.getLms() != null) {
                        profile.getLms().removeSpectator(player);
                        break;
                    }
                    if (profile.getParkour() != null) {
                        profile.getParkour().removeSpectator(player);
                        break;
                    }
                    if (profile.getSpleef() != null) {
                        profile.getSpleef().removeSpectator(player);
                        break;
                    }
                    break;
                }
                case REMATCH_REQUEST:
                case REMATCH_ACCEPT: {
                    if (profile.getRematchData() == null) {
                        player.sendMessage(CC.RED + "You do not have anyone to re-match.");
                        return;
                    }
                    profile.checkForHotbarUpdate();
                    if (profile.getRematchData() == null) {
                        player.sendMessage(CC.RED + "That player is no longer available.");
                        return;
                    }
                    final ProfileRematchData profileRematchData = profile.getRematchData();
                    if (profileRematchData.isReceive()) {
                        profileRematchData.accept();
                    }
                    else {
                        if (profileRematchData.isSent()) {
                            player.sendMessage(CC.RED + "You have already sent a rematch request to that player.");
                            return;
                        }
                        profileRematchData.request();
                    }
                    break;
                }
                default: {}
            }
        }
    }
}

