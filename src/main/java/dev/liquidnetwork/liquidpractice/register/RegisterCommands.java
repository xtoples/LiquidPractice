package dev.liquidnetwork.liquidpractice.register;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.arena.command.*;
import dev.liquidnetwork.liquidpractice.liquidpractice.commands.*;
import dev.liquidnetwork.liquidpractice.event.EventCommand;
import dev.liquidnetwork.liquidpractice.event.EventHelpCommand;
import dev.liquidnetwork.liquidpractice.event.types.brackets.command.*;
import dev.liquidnetwork.liquidpractice.event.types.lms.command.*;
import dev.liquidnetwork.liquidpractice.event.types.parkour.command.*;
import dev.liquidnetwork.liquidpractice.event.types.spleef.command.*;
import dev.liquidnetwork.liquidpractice.event.types.sumo.command.*;
import dev.liquidnetwork.liquidpractice.event.types.wizard.command.*;
import dev.liquidnetwork.liquidpractice.kit.command.*;
import dev.liquidnetwork.liquidpractice.match.command.MatchStatusCommand;
import dev.liquidnetwork.liquidpractice.match.command.SpectateCommand;
import dev.liquidnetwork.liquidpractice.match.command.StopSpectatingCommand;
import dev.liquidnetwork.liquidpractice.match.command.ViewInventoryCommand;
import dev.liquidnetwork.liquidpractice.party.command.*;
import dev.liquidnetwork.liquidpractice.tournament.command.*;
import dev.liquidnetwork.liquidpractice.liquidpractice.LiquidPracticeCMD;
import dev.liquidnetwork.liquidpractice.liquidpractice.commands.donator.FlyCMD;
import dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff.FollowCMD;
import dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff.GetUUIDCMD;
import dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff.SilentCMD;
import dev.liquidnetwork.liquidpractice.liquidpractice.commands.staff.UnFollowCMD;
import dev.liquidnetwork.liquidpractice.duel.command.DuelAcceptCommand;
import dev.liquidnetwork.liquidpractice.duel.command.DuelCommand;
import dev.liquidnetwork.liquidpractice.duel.command.RematchCommand;
import dev.liquidnetwork.liquidpractice.statistics.command.LeaderboardsCommand;
import dev.liquidnetwork.liquidpractice.statistics.command.StatsCommand;
import org.bukkit.Bukkit;

import java.util.Arrays;

public class RegisterCommands {
    
    public static void register() {
        LiquidPractice.logger("&bRegistering Commands...");
        for (Object command : Arrays.asList(
                // Staff Commands
                new SilentCMD(),
                new FollowCMD(),
                new UnFollowCMD(),

                // LiquidPractice Commands
                new LiquidPracticeCMD(),
                new SetLobbyCMD(),
                new SetEloCMD(),
                new GoldenHeadCMD(),
                new ReloadCMD(),
                new RenameCMD(),
                new RefillCMD(),
                new ResetStatsCMD(),
                new SaveArenasCMD(),
                new SaveCMD(),
                new SaveDataCMD(),
                new SaveKitsCMD(),
                new PracticeCMD(),
                new HCFCMD(),
                new SpawnCMD(),
                new ToggleScoreboardCMD(),
                new ToggleDuelCMD(),
                new VersionCMD(),
                new WorldCMD(),
                new TestCMD(),

                // Player Command
                new LeaderboardsCommand(),
                new SettingsCMD(),
                new EventCommand(),
                new GetUUIDCMD(),
                new EventHelpCommand(),
                new FlyCMD(),

                // Arena Commands
                new ArenaAddKitCommand(),
                new ArenaRemoveKitCommand(),
                new ArenaAddBuildKitsCommand(),
                new ArenaDisablePearlsCommand(),
                new ArenaSetPortalCommand(),
                new ArenaPortalWandCommand(),
                new ArenaSetSpawnCommand(),
                new ArenaCreateCommand(),
                new ArenaAddNormalKitCommand(),
                new ArenaRemoveCommand(),
                new ArenasCommand(),
                new ArenaTpCommand(),
                new ArenaCommand(),
                new ArenasCommand(),
                new ArenaKitListCommand(),
                new ArenaSaveCommand(),
                new ArenaReloadCommand(),
                new ArenaSetIconCommand(),
                new ArenaSetDisplayNameCommand(),

                // Duel Commands
                new DuelCommand(),
                new DuelAcceptCommand(),
                new RematchCommand(),
                new ViewInventoryCommand(),
                new SpectateCommand(),
                new StopSpectatingCommand(),
                new MatchStatusCommand(),

                // Party Commands
                new PartyCloseCommand(),
                new PartyCreateCommand(),
                new PartyDisbandCommand(),
                new PartyHelpCommand(),
                new PartyInfoCommand(),
                new PartyInviteCommand(),
                new PartyJoinCommand(),
                new PartyKickCommand(),
                new PartyLeaveCommand(),
                new PartyChatCommand(),
                new PartyOpenCommand(),
                new PartyLeaderCommand(),
                new PartyUnbanCommand(),
                new PartyBanCommand(),

                // Kit Commands
                new KitCreateCommand(),
                new KitGetInvCommand(),
                new KitSetInvCommand(),
                new KitSetKnockbackProfileCommand(),
                new KitListCommand(),
                new KitCommand(),
                new KitSaveCommand(),
                new KitRemoveCommand(),
                new KitSetIconCommand(),
                new KitSetHitDelayCommand(),
                new KitBridgeCommand(),
                new KitSetRankedCommand(),
                new KitSumoCommand(),
                new KitBuildCommand(),
                new KitBoxUHCCommand(),
                new KitSetAntiFoodLossCommand(),
                new KitSetBowHPCommand(),
                new KitSetHealthRegenenerationCommand(),
                new KitSetInfiniteSpeedCommand(),
                new KitSetInfiniteStrengthCommand(),
                new KitComboCommand(),
                new KitSpleefCommand(),
                new KitTimedCommand(),
                new KitParkourCommand(),
                new KitPartyFFACommand(),
                new KitPartySplitCommand(),
                new KitDisableCommand(),
                new KitLavaKillCommand(),
                new KitWaterKillCommand(),
                new KitSetNoItemsCommand(),
                new KitShowHealthCommand(),
                new KitDisableCommand(),
                new KitEnableCommand(),
                new KitVoidSpawnCommand(),
                new KitStickSpawnCommand(),
                new KitSetDisplayNameCommand(),

                // Brackets Commands
                new BracketsLeaveCommand(),
                new BracketsCancelCommand(),
                new BracketsCooldownCommand(),
                new BracketsJoinCommand(),
                new BracketsSetSpawnCommand(),
                new BracketsHostCommand(),
                new BracketsTpCommand(),
                new BracketsHelpCommand(),
                new BracketsKnockbackCommand(),
                new BracketsForceStartCommand(),

                // Wizard Commands
                new WizardLeaveCommand(),
                new WizardCancelCommand(),
                new WizardCooldownCommand(),
                new WizardJoinCommand(),
                new WizardForceStartCommand(),
                new WizardHostCommand(),
                new WizardHelpCommand(),
                new WizardSetSpawnCommand(),
                new WizardTpCommand(),
                new WizardKnockbackCommand(),

                // Sumo Commands
                new SumoCancelCommand(),
                new SumoCooldownCommand(),
                new SumoHostCommand(),
                new SumoJoinCommand(),
                new SumoLeaveCommand(),
                new SumoSetSpawnCommand(),
                new SumoTpCommand(),
                new SumoHelpCommand(),
                new SumoKnockbackCommand(),
                new SumoForceStartCommand(),

                // LMS Commands
                new LMSCancelCommand(),
                new LMSCooldownCommand(),
                new LMSHostCommand(),
                new LMSJoinCommand(),
                new LMSLeaveCommand(),
                new LMSSetSpawnCommand(),
                new LMSTpCommand(),
                new LMSHelpCommand(),
                new LMSForceStartCommand(),

                // Parkour Commands
                new ParkourCancelCommand(),
                new ParkourCooldownCommand(),
                new ParkourHostCommand(),
                new ParkourJoinCommand(),
                new ParkourLeaveCommand(),
                new ParkourSetSpawnCommand(),
                new ParkourTpCommand(),
                new ParkourHelpCommand(),
                new ParkourForceStartComman(),

                // Spleef Commands
                new SpleefCancelCommand(),
                new SpleefCooldownCommand(),
                new SpleefHostCommand(),
                new SpleefJoinCommand(),
                new SpleefLeaveCommand(),
                new SpleefSetSpawnCommand(),
                new SpleefTpCommand(),
                new SpleefHelpCommand(),
                new SpleefForceStartCommand(),

                // Tournament Commands
                new TournamentCommand(),
                new TournamentLeaveCommand(),
                new TournamentJoinCommand(),
                new TournamentHostCommand(),
                new TournamentCancelCommand(),
                new TournamentListCommand()
        ))
            LiquidPractice.getHoncho().registerCommand(command);
        Bukkit.getCommandMap().register("stats", new StatsCommand());
    }
}
