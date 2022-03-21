package dev.liquidnetwork.liquidpractice.register;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.liquidpractice.listener.GoldenHeads;
import dev.liquidnetwork.liquidpractice.liquidpractice.listener.MOTDListener;
import dev.liquidnetwork.liquidpractice.liquidpractice.listener.ToggleSprintFix;
import dev.liquidnetwork.liquidpractice.event.types.brackets.BracketsListener;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMSListener;
import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourListener;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefListener;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardListener;
import dev.liquidnetwork.liquidpractice.hotbar.HotbarListener;
import dev.liquidnetwork.liquidpractice.party.PartyListener;
import dev.liquidnetwork.liquidpractice.profile.ProfileListener;
import dev.liquidnetwork.liquidpractice.queue.QueueListener;
import dev.liquidnetwork.liquidpractice.util.external.menu.MenuListener;
import dev.liquidnetwork.liquidpractice.arena.selection.ArenaSelectionListener;
import dev.liquidnetwork.liquidpractice.event.types.sumo.SumoListener;
import dev.liquidnetwork.liquidpractice.kiteditor.KitEditorListener;
import dev.liquidnetwork.liquidpractice.match.MatchListener;
import dev.liquidnetwork.liquidpractice.util.events.WorldListener;
import org.bukkit.event.Listener;

import java.util.Arrays;

public class RegisterListeners {

    public static void register() {
        LiquidPractice.logger("&bRegistering Listeners....");
        for ( Listener listener : Arrays.asList(
                new ProfileListener(),
                new MenuListener(LiquidPractice.getInstance()),
                new SumoListener(),
                new WizardListener(),
                new BracketsListener(),
                new LMSListener(),
                new ParkourListener(),
                new SpleefListener(),
                new ArenaSelectionListener(),
                new KitEditorListener(),
                new MOTDListener(),
                new PartyListener(),
                new HotbarListener(),
                new MatchListener(),
                new WorldListener(),
                new GoldenHeads(),
                new ToggleSprintFix(),
                new QueueListener()
        )) {
            LiquidPractice.getInstance().getServer().getPluginManager().registerEvents(listener, LiquidPractice.getInstance());
        }
    }
}
