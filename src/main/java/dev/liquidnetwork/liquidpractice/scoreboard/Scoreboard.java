package dev.liquidnetwork.liquidpractice.scoreboard;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.LiquidPracticeCache;
import dev.liquidnetwork.liquidpractice.event.types.brackets.Brackets;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMS;
import dev.liquidnetwork.liquidpractice.event.types.parkour.Parkour;
import dev.liquidnetwork.liquidpractice.event.types.spleef.Spleef;
import dev.liquidnetwork.liquidpractice.event.types.wizard.Wizard;
import dev.liquidnetwork.liquidpractice.hcf.HCFClasses;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.match.team.Team;
import dev.liquidnetwork.liquidpractice.match.team.TeamPlayer;
import dev.liquidnetwork.liquidpractice.party.Party;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.queue.Queue;
import dev.liquidnetwork.liquidpractice.queue.QueueType;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import dev.liquidnetwork.liquidpractice.util.external.TimeUtil;
import dev.liquidnetwork.liquidpractice.util.scoreboard.scoreboard.Board;
import dev.liquidnetwork.liquidpractice.util.scoreboard.scoreboard.BoardAdapter;
import dev.liquidnetwork.liquidpractice.util.scoreboard.scoreboard.cooldown.BoardCooldown;
import dev.liquidnetwork.liquidpractice.event.types.sumo.Sumo;
import dev.liquidnetwork.liquidpractice.hcf.classes.Bard;
import dev.liquidnetwork.liquidpractice.tournament.Tournament;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Scoreboard implements BoardAdapter {

    @Override
    public String getTitle(Player player) {
        return "&b&lLiquid &7&l┃ &fPractice";
    }

    @Override
    public List<String> getScoreboard(Player player, Board board, Set<BoardCooldown> cooldowns) {
        Profile profile=Profile.getByUuid(player.getUniqueId());

        if (!profile.getSettings().isShowScoreboard()) {
            return null;
        }

        final List<String> lines=new ArrayList<>();
        lines.add(CC.SB_BAR);
        if (profile.isInLobby() || profile.isInQueue()) {
            lines.add(CC.translate("&fOnline: &b" + LiquidPracticeCache.getOnline()));
            lines.add(CC.translate("&fIn Fights: &b" + LiquidPracticeCache.getInFights()));
            lines.add(CC.translate("&fIn Queue: &b" + LiquidPracticeCache.getInQueues()));
            lines.add("");
            lines.add("&fELO: &b" + profile.getGlobalElo());
            lines.add("&fLeague: &b" + profile.getEloLeague());
            if (profile.getParty() != null && Tournament.CURRENT_TOURNAMENT == null) {
                final Party party=profile.getParty();
                lines.add("");
                lines.add("&bYour Party");
                int added=0;
                for ( final TeamPlayer teamPlayer : party.getTeamPlayers() ) {
                    ++added;
                    lines.add("&b" + (party.getLeader().equals(teamPlayer) ? "*" : "-") + " &r" + teamPlayer.getUsername());
                    if (added >= 4) {
                        break;
                    }
                }
            }
            if (profile.isInQueue()) {
                final Queue queue=profile.getQueue();
                lines.add(CC.SB_BAR);
                lines.add("&b" + queue.getQueueName());
                lines.add("&fDuration: &b" + queue.getDuration(player));
                if (queue.getQueueType().equals(QueueType.RANKED)) {
                    lines.add("&fRange: &b" + profile.getQueueProfile().getMinRange() + " -> " + profile.getQueueProfile().getMaxRange());
                }
            } else if (Tournament.CURRENT_TOURNAMENT != null && profile.getParty() != null) {
                final Tournament tournament=Tournament.CURRENT_TOURNAMENT;
                final String round=(tournament.getRound() > 0) ? Integer.toString(tournament.getRound()) : "&fStarting";
                lines.add("");
                lines.add("&b&lTournament: &f");
                lines.add("&fKit: &b" + tournament.getLadder().getName() + " &7(" + tournament.getTeamCount() + "v" + tournament.getTeamCount() + ")");
                lines.add("&fRound: &b" + round);
                lines.add(((tournament.getTeamCount() > 1) ? "&fParties: &b" : "&fPlayers: &b") + tournament.getParticipatingCount() + "/" + tournament.getParticipants().size());
            }
        } else if (profile.isInFight()) {
            final Match match=profile.getMatch();
            if (match != null) {
                if (match.isSoloMatch()) {
                    final TeamPlayer self=match.getTeamPlayer(player);
                    final TeamPlayer opponent=match.getOpponentTeamPlayer(player);
                    lines.add("&fEnemy: &b" + opponent.getUsername());
                    lines.add("&fDuration: &b" + match.getDuration());
                    if (profile.getSettings().isPingScoreboard()) {
                        lines.add("");
                        lines.add("&fYour Ping: &b" + self.getPing() + "ms");
                        lines.add("&fEnemy Ping: &b" + opponent.getPing() + "ms");
                    }
                } else if (match.isSumoMatch()) {
                    TeamPlayer self=match.getTeamPlayer(player);
                    TeamPlayer opponent=match.getOpponentTeamPlayer(player);

                    Profile targetProfile=Profile.getByUuid(opponent.getUuid());

                    int selfPoints=profile.getSumoRounds();
                    int opPoints=targetProfile.getSumoRounds();

                    lines.add("&fEnemy: &b" + opponent.getUsername());
                    lines.add("&fPoints: &b" + selfPoints + " &7┃ &b" + opPoints + "");
                    lines.add("");
                    lines.add("&fYour Ping: &b" + self.getPing() + "ms");
                    lines.add("&fEnemy Ping: &b" + opponent.getPing() + "ms");
                } else if (match.isTheBridgeMatch()) {
                    TeamPlayer self=match.getTeamPlayer(player);
                    TeamPlayer opponent=match.getOpponentTeamPlayer(player);

                    Profile targetProfile=Profile.getByUuid(opponent.getUuid());

                    int selfPoints=profile.getBridgeRounds();
                    int opPoints=targetProfile.getBridgeRounds();

                    lines.add("&fEnemy: &b" + opponent.getUsername());
                    lines.add("&fPoints: &b" + selfPoints + " &7┃ &b" + opPoints + "");
                    lines.add("");
                    lines.add("&fYour Ping: &b" + self.getPing() + "ms");
                    lines.add("&fEnemy Ping: &b" + opponent.getPing() + "ms");
                } else if (match.isSumoTeamMatch()) {
                    Team team=match.getTeam(player);
                    Team opponentTeam=match.getOpponentTeam(player);

                    if ((team.getPlayers().size() + opponentTeam.getPlayers().size()) == 2) {
                        Team self=match.getTeam(player);
                        Team opponent=match.getOpponentTeam(self);

                        int selfPoints=self.getSumoRounds();
                        int opPoints=opponent.getSumoRounds();

                        lines.add("&fEnemy: &b" + opponent.getLeader().getUsername() + "'s Party");
                        lines.add("&fPoints: &b" + selfPoints + " &7┃ &b" + opPoints + "");
                        lines.add("");
                        lines.add("&fYour Ping: &b" + PlayerUtil.getPing(player) + "ms");
                        lines.add("&fEnemy Ping: &b" + opponent.getTeamPlayers().get(0).getPing() + "ms");
                    } else {
                        int selfPoints=team.getSumoRounds();
                        int opPoints=opponentTeam.getSumoRounds();

                        lines.add(" &fYour Team: &b" + team.getAliveCount() + "/" + team.getTeamPlayers().size());
                        lines.add(" &fEnemy Team: &b" + opponentTeam.getAliveCount() + "/" + opponentTeam.getTeamPlayers().size());
                        lines.add(" &fPoints: &b" + selfPoints + " &f┃ &b" + opPoints);
                    }

                } else if (match.isTeamMatch() || match.isHCFMatch()) {
                    final Team team=match.getTeam(player);
                    final Team opponentTeam=match.getOpponentTeam(player);
                    lines.add("&fDuration: &b" + match.getDuration());
                    lines.add("&fYour Team: &a" + team.getAliveCount() + "/" + team.getTeamPlayers().size());
                    lines.add("&fEnemy Team: &c" + opponentTeam.getAliveCount() + "/" + opponentTeam.getTeamPlayers().size());

                    if (match.isHCFMatch()) {
                        final HCFClasses pvpClass= LiquidPractice.getInstance().getHCFManager().getEquippedClass(player);
                        if (pvpClass instanceof Bard) {
                            final Bard bardClass=(Bard) pvpClass;
                            lines.add("&fBard Energy: &b" + bardClass.getEnergy(player));
                        }
                    }
                } else if (match.isFreeForAllMatch()) {
                    final Team team=match.getTeam(player);
                    lines.add("&fPlayers: &b" + team.getAliveCount() + "/" + team.getTeamPlayers().size());
                    lines.add("&fDuration: &b" + match.getDuration());
                }
            }
        } else if (profile.isSpectating()) {
            final Match match=profile.getMatch();
            final Sumo sumo=profile.getSumo();
            final LMS ffa=profile.getLms();
            final Brackets brackets=profile.getBrackets();
            final Parkour parkour=profile.getParkour();
            final Spleef spleef=profile.getSpleef();
            final Wizard wizard=profile.getWizard();
            if (match != null) {
                if (!match.isHCFMatch()) {
                    lines.add("&fKit: &b" + match.getKit().getName());
                }
                lines.add("&fDuration: &b" + match.getDuration());
                lines.add("");
                if (match.isSoloMatch() || match.isSumoMatch() || match.isTheBridgeMatch()) {
                    int playera=PlayerUtil.getPing(match.getTeamPlayerA().getPlayer());
                    int playerb=PlayerUtil.getPing(match.getTeamPlayerB().getPlayer());
                    lines.add(CC.GREEN + match.getTeamPlayerA().getUsername() + CC.translate(" &8(&a" + playera + "&8)"));
                    lines.add("&7vs");
                    lines.add(CC.RED + match.getTeamPlayerB().getUsername() + CC.translate(" &8(&c" + playerb + "&8)"));
                } else if (match.isTeamMatch() || match.isHCFMatch() || match.isSumoTeamMatch()) {
                    lines.add("&b" + match.getTeamA().getLeader().getUsername() + "'s Team &8(&f" + match.getTeamA().getPlayers().size() + "&8)");
                    lines.add("&7vs");
                    lines.add("&b" + match.getTeamB().getLeader().getUsername() + "'s Team &8(&f" + match.getTeamB().getPlayers().size() + "&8)");
                } else {
                    final Team team2=match.getTeam(player);
                    lines.add("&fAlive: &b" + team2.getAliveCount() + "/" + team2.getTeamPlayers().size());
                }
            } else if (sumo != null) {
                if (sumo.isWaiting()) {
                    lines.add("&b&lSumo Event");
                    lines.add("");
                    lines.add(CC.translate("&fHost: &b" + sumo.getName()));
                    lines.add("&fPlayers: &b" + sumo.getEventPlayers().size() + "/" + Sumo.getMaxPlayers());
                    lines.add("");
                    if (sumo.getCooldown() == null) {
                        lines.add(CC.translate("&fWaiting for players..."));
                    } else {
                        String remaining= TimeUtil.millisToSeconds(sumo.getCooldown().getRemaining());
                        if (remaining.startsWith("-")) {
                            remaining="0";
                        }
                        lines.add("&fStarting in " + CC.AQUA + remaining + "&fs");
                    }
                } else {
                    int playera=PlayerUtil.getPing(sumo.getRoundPlayerA().getPlayer());
                    int playerb=PlayerUtil.getPing(sumo.getRoundPlayerB().getPlayer());
                    lines.add("&b&lSumo Event");
                    lines.add("");
                    lines.add("&fPlayers: &b" + sumo.getRemainingPlayers().size() + "/" + Sumo.getMaxPlayers());
                    lines.add("&fDuration: &b" + sumo.getRoundDuration());
                    lines.add("");
                    lines.add("&b" + sumo.getRoundPlayerA().getUsername() + " &8(&b" + playera + "&8)");
                    lines.add("&7vs");
                    lines.add("&b" + sumo.getRoundPlayerB().getUsername() + " &8(&b" + playerb + "&8)");
                }
            } else if (wizard != null) {
                if (wizard.isWaiting()) {
                    lines.add("&b&lWizard Event");
                    lines.add("");
                    lines.add(CC.translate("&fHost: &b" + wizard.getName()));
                    lines.add("&fPlayers: &b" + wizard.getEventPlayers().size() + "/" + Wizard.getMaxPlayers());
                    lines.add("");
                    if (wizard.getCooldown() == null) {
                        lines.add(CC.translate("&fWaiting for players..."));
                    } else {
                        String remaining=TimeUtil.millisToSeconds(wizard.getCooldown().getRemaining());
                        if (remaining.startsWith("-")) {
                            remaining="0";
                        }
                        lines.add(CC.translate("&fStarting in " + CC.AQUA + remaining + "&fs"));
                    }
                } else {
                    int playera=PlayerUtil.getPing(wizard.getRoundPlayerA().getPlayer());
                    int playerb=PlayerUtil.getPing(wizard.getRoundPlayerB().getPlayer());
                    lines.add("&b&lWizard Event");
                    lines.add("");
                    lines.add("&fPlayers: &b" + wizard.getRemainingPlayers().size() + "/" + Wizard.getMaxPlayers());
                    lines.add("&fDuration: &b" + wizard.getRoundDuration());
                    lines.add("");
                    lines.add("&b" + wizard.getRoundPlayerA().getUsername() + " &8(&b" + playera + "&8)");
                    lines.add("&7vs");
                    lines.add("&b" + wizard.getRoundPlayerB().getUsername() + " &8(&b" + playerb + "&8)");
                }
            } else if (ffa != null) {
                if (ffa.isWaiting()) {
                    lines.add("&b&lLMS Event");
                    lines.add("");
                    lines.add(CC.translate("&fHost: &b" + ffa.getName()));
                    lines.add("&fPlayers: &b" + ffa.getEventPlayers().size() + "/" + LMS.getMaxPlayers());
                    lines.add("");
                    if (ffa.getCooldown() == null) {
                        lines.add(CC.translate("&fWaiting for players..."));
                    } else {
                        String remaining=TimeUtil.millisToSeconds(ffa.getCooldown().getRemaining());
                        if (remaining.startsWith("-")) {
                            remaining="0.0";
                        }
                        lines.add(CC.translate("&fStarting in " + remaining + "s"));
                    }
                } else {
                    lines.add("&bLMS Event");
                    lines.add("");
                    lines.add("&fPlayers: &b" + ffa.getRemainingPlayers().size() + "/" + LMS.getMaxPlayers());
                    lines.add("&fDuration: &b" + ffa.getRoundDuration());
                }
            } else if (brackets != null) {
                if (brackets.isWaiting()) {
                    lines.add("&b&lBrackets Event");
                    lines.add("");
                    lines.add(CC.translate("&fHost: &b" + brackets.getName()));
                    lines.add("&fPlayers: &b" + brackets.getEventPlayers().size() + "/" + Brackets.getMaxPlayers());
                    lines.add("");
                    if (brackets.getCooldown() == null) {
                        lines.add(CC.translate("&fWaiting for players..."));
                    } else {
                        String remaining=TimeUtil.millisToSeconds(brackets.getCooldown().getRemaining());
                        if (remaining.startsWith("-")) {
                            remaining="0.0";
                        }
                        lines.add(CC.translate("&fStarting in " + remaining + "s"));
                    }
                } else {
                    int playera=PlayerUtil.getPing(brackets.getRoundPlayerA().getPlayer());
                    int playerb=PlayerUtil.getPing(brackets.getRoundPlayerB().getPlayer());
                    lines.add("&b&lBrackets Event");
                    lines.add("");
                    lines.add("&fPlayers: &b" + brackets.getRemainingPlayers().size() + "/" + Brackets.getMaxPlayers());
                    lines.add("&fDuration: &b" + brackets.getRoundDuration());
                    lines.add("");
                    lines.add("&b" + brackets.getRoundPlayerA().getUsername() + " &8(&b" + playera + "&8)");
                    lines.add("&7vs");
                    lines.add("&b" + brackets.getRoundPlayerB().getUsername() + " &8(&b" + playerb + "&8)");
                }
            } else if (parkour != null) {
                if (parkour.isWaiting()) {
                    lines.add("&b&lParkour Event");
                    lines.add("");
                    lines.add(CC.translate("&fHost: &b" + parkour.getName()));
                    lines.add("&fPlayers: &b" + parkour.getEventPlayers().size() + "/" + Parkour.getMaxPlayers());
                    lines.add("");
                    if (parkour.getCooldown() == null) {
                        lines.add(CC.translate("&fWaiting for players..."));
                    } else {
                        String remaining=TimeUtil.millisToSeconds(parkour.getCooldown().getRemaining());
                        if (remaining.startsWith("-")) {
                            remaining="0.0";
                        }
                        lines.add(CC.translate("&fStarting in " + remaining + "s"));
                    }
                } else {
                    lines.add("&b&lParkour Event");
                    lines.add("");
                    lines.add("&fPlayers: &b" + parkour.getRemainingPlayers().size() + "/" + Parkour.getMaxPlayers());
                    lines.add("&fDuration: &b" + parkour.getRoundDuration());
                }
            } else if (spleef != null) {
                if (spleef.isWaiting()) {
                    lines.add("&b&lSpleef Event");
                    lines.add("");
                    lines.add(CC.translate("&fHost: &b" + spleef.getName()));
                    lines.add("&fPlayers: &b" + spleef.getEventPlayers().size() + "/" + Spleef.getMaxPlayers());
                    lines.add("");
                    if (spleef.getCooldown() == null) {
                        lines.add(CC.translate("&fWaiting for players..."));
                    } else {
                        String remaining=TimeUtil.millisToSeconds(spleef.getCooldown().getRemaining());
                        if (remaining.startsWith("-")) {
                            remaining="0.0";
                        }
                        lines.add(CC.translate("&fStarting in " + remaining + "s"));
                    }
                } else {
                    lines.add("&b&lSpleef Event");
                    lines.add("");
                    lines.add("&fPlayers: &b" + spleef.getRemainingPlayers().size() + "/" + Spleef.getMaxPlayers());
                    lines.add("&fDuration: &b" + spleef.getRoundDuration());
                }
            }
        } else if (profile.isInSumo()) {
            final Sumo sumo2=profile.getSumo();
            if (sumo2.isWaiting()) {
                lines.add("&b&lSumo Event");
                lines.add("");
                lines.add(CC.translate("&fHost: &b" + sumo2.getName()));
                lines.add("&fPlayers: &b" + sumo2.getEventPlayers().size() + "/" + Sumo.getMaxPlayers());
                lines.add("");
                if (sumo2.getCooldown() == null) {
                    lines.add(CC.translate("&fWaiting for players..."));
                } else {
                    String remaining=TimeUtil.millisToSeconds(sumo2.getCooldown().getRemaining());
                    if (remaining.startsWith("-")) {
                        remaining="0";
                    }
                    lines.add(CC.translate("&fStarting in " + CC.AQUA + remaining + "&fs"));
                }
            } else {
                int playera=PlayerUtil.getPing(sumo2.getRoundPlayerA().getPlayer());
                int playerb=PlayerUtil.getPing(sumo2.getRoundPlayerB().getPlayer());
                lines.add("&b&lSumo Event");
                lines.add("");
                lines.add("&fPlayers: &b" + sumo2.getRemainingPlayers().size() + "/" + Sumo.getMaxPlayers());
                lines.add("&fDuration: &b" + sumo2.getRoundDuration());
                lines.add("");
                lines.add("&b" + sumo2.getRoundPlayerA().getUsername() + " &8(&b" + playera + "&8)");
                lines.add("&7vs");
                lines.add("&b" + sumo2.getRoundPlayerB().getUsername() + " &8(&b" + playerb + "&8)");
            }
        } else if (profile.isInWizard()) {
            final Wizard wizard2=profile.getWizard();
            if (wizard2.isWaiting()) {
                lines.add("&b&lWizard Event");
                lines.add("");
                lines.add(CC.translate("&fHost: &b" + wizard2.getName()));
                lines.add("&fPlayers: &b" + wizard2.getEventPlayers().size() + "/" + Wizard.getMaxPlayers());
                lines.add("");
                if (wizard2.getCooldown() == null) {
                    lines.add(CC.translate("&fWaiting for players..."));
                } else {
                    String remaining=TimeUtil.millisToSeconds(wizard2.getCooldown().getRemaining());
                    if (remaining.startsWith("-")) {
                        remaining="0";
                    }
                    lines.add(CC.translate("&fStarting in " + CC.AQUA + remaining + "&fs"));
                }
            } else {
                int playera=PlayerUtil.getPing(wizard2.getRoundPlayerA().getPlayer());
                int playerb=PlayerUtil.getPing(wizard2.getRoundPlayerB().getPlayer());
                lines.add("&b&lWizard Event");
                lines.add("");
                lines.add("&fPlayers: &b" + wizard2.getRemainingPlayers().size() + "/" + Wizard.getMaxPlayers());
                lines.add("&fDuration: &b" + wizard2.getRoundDuration());
                lines.add("");
                lines.add("&b" + wizard2.getRoundPlayerA().getUsername() + " &8(&b" + playera + "&8)");
                lines.add("&7vs");
                lines.add("&b" + wizard2.getRoundPlayerB().getUsername() + " &8(&b" + playerb + "&8)");
            }
        } else if (profile.isInBrackets()) {
            final Brackets brackets2=profile.getBrackets();
            if (brackets2.isWaiting()) {
                lines.add("&b&lBrackets Event");
                lines.add("");
                lines.add(CC.translate("&fHost: &b" + brackets2.getName()));
                lines.add("&fPlayers: &b" + brackets2.getEventPlayers().size() + "/" + Brackets.getMaxPlayers());
                lines.add("");
                if (brackets2.getCooldown() == null) {
                    lines.add(CC.translate("&fWaiting for players..."));
                } else {
                    String remaining=TimeUtil.millisToSeconds(brackets2.getCooldown().getRemaining());
                    if (remaining.startsWith("-")) {
                        remaining="0.0";
                    }
                    lines.add(CC.translate("&fStarting in " + remaining + "s"));
                }
            } else {
                int playera=PlayerUtil.getPing(brackets2.getRoundPlayerA().getPlayer());
                int playerb=PlayerUtil.getPing(brackets2.getRoundPlayerB().getPlayer());
                lines.add("&b&lBrackets Event");
                lines.add("");
                lines.add("&fPlayers: &b" + brackets2.getRemainingPlayers().size() + "/" + Brackets.getMaxPlayers());
                lines.add("&fDuration: &b" + brackets2.getRoundDuration());
                lines.add("");
                lines.add("&a" + brackets2.getRoundPlayerA().getUsername() + " &8(" + playera + "&8)");
                lines.add("&7vs");
                lines.add("&c" + brackets2.getRoundPlayerB().getUsername() + " &8(" + playerb + "&8)");
            }
        } else if (profile.isInLMS()) {
            final LMS ffa2=profile.getLms();
            if (ffa2.isWaiting()) {
                lines.add("&b&lLMS Event");
                lines.add("");
                lines.add(CC.translate("&fHost: &b" + ffa2.getName()));
                lines.add("&fPlayers: &b" + ffa2.getEventPlayers().size() + "/" + LMS.getMaxPlayers());
                lines.add("");
                if (ffa2.getCooldown() == null) {
                    lines.add(CC.translate("&fWaiting for players..."));
                } else {
                    String remaining=TimeUtil.millisToSeconds(ffa2.getCooldown().getRemaining());
                    if (remaining.startsWith("-")) {
                        remaining="0.0";
                    }
                    lines.add(CC.translate("&fStarting in " + remaining + "s"));
                }
            } else {
                lines.add("&bLMS Event");
                lines.add("");
                lines.add("&fPlayers: &b" + ffa2.getRemainingPlayers().size() + "/" + LMS.getMaxPlayers());
                lines.add("&fDuration: &b" + ffa2.getRoundDuration());
            }
        } else if (profile.isInParkour()) {
            final Parkour parkour2=profile.getParkour();
            if (parkour2.isWaiting()) {
                lines.add("&b&lParkour Event");
                lines.add("");
                lines.add(CC.translate("&fHost: &b" + parkour2.getName()));
                lines.add("&fPlayers: &b" + parkour2.getEventPlayers().size() + "/" + Parkour.getMaxPlayers());
                lines.add("");
                if (parkour2.getCooldown() == null) {
                    lines.add(CC.translate("&fWaiting for players..."));
                } else {
                    String remaining=TimeUtil.millisToSeconds(parkour2.getCooldown().getRemaining());
                    if (remaining.startsWith("-")) {
                        remaining="0.0";
                    }
                    lines.add(CC.translate("&fStarting in " + remaining + "s"));
                }
            } else {
                lines.add("&b&lParkour Event");
                lines.add("");
                lines.add("&fPlayers: &b" + parkour2.getRemainingPlayers().size() + "/" + Parkour.getMaxPlayers());
                lines.add("&fDuration: &b" + parkour2.getRoundDuration());
            }
        } else if (profile.isInSpleef()) {
            final Spleef spleef2=profile.getSpleef();
            if (spleef2.isWaiting()) {
                lines.add("&b&lSpleef Event");
                lines.add("");
                lines.add(CC.translate("&fHost: &b" + spleef2.getName()));
                lines.add("&fPlayers: &b" + spleef2.getEventPlayers().size() + "/" + Spleef.getMaxPlayers());
                lines.add("");
                if (spleef2.getCooldown() == null) {
                    lines.add(CC.translate("&fWaiting for players..."));
                } else {
                    String remaining=TimeUtil.millisToSeconds(spleef2.getCooldown().getRemaining());
                    if (remaining.startsWith("-")) {
                        remaining="0.0";
                    }
                    lines.add(CC.translate("&fStarting in " + remaining + "s"));
                }
            } else {
                lines.add("&b&lSpleef Event");
                lines.add("");
                lines.add("&fPlayers: &b" + spleef2.getRemainingPlayers().size() + "/" + Spleef.getMaxPlayers());
                lines.add("&fDuration: &b" + spleef2.getRoundDuration());
            }
        }
        if (profile.isFollowMode()) {
            lines.add("&7");
            lines.add(" &fFollowing: &4" + profile.getFollowing().getName());
        }
        if (profile.isSilent()) {
            lines.add("&7");
            lines.add(" &7&lSilent Mode");
        }
        lines.add("");
        lines.add(CC.translate("&7&oliquidpvp.club"));
        lines.add(CC.SB_BAR);
        return lines;
    }
}
