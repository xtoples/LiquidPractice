package dev.liquidnetwork.liquidpractice.duel.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.arena.impl.StandaloneArena;
import dev.liquidnetwork.liquidpractice.enums.ArenaType;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.match.team.Team;
import dev.liquidnetwork.liquidpractice.match.team.TeamPlayer;
import dev.liquidnetwork.liquidpractice.match.types.*;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.profile.rank.RankType;
import dev.liquidnetwork.liquidpractice.queue.QueueType;
import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.duel.DuelRequest;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "duel accept")
public class DuelAcceptCommand {

    RankType rank = LiquidPractice.getInstance().getRankManager();

    public void execute(Player player, @CPL("player") Player target) {
        if (target == null) {
            player.sendMessage(CC.RED + "That player is no longer online.");
            return;
        }

        if (player.hasMetadata("frozen")) {
            player.sendMessage(CC.RED + "You cannot duel a player while being frozen.");
            return;
        }

        if (target.hasMetadata("frozen")) {
            player.sendMessage(CC.RED + "You cannot duel a player who's frozen.");
            return;
        }

        Profile senderProfile = Profile.getByUuid(player.getUniqueId());

        if (senderProfile.isBusy(player)) {
            player.sendMessage(CC.RED + "You cannot duel anyone right now.");
            return;
        }

        Profile receiverProfile = Profile.getByUuid(target.getUniqueId());

        if (!receiverProfile.isPendingDuelRequest(player)) {
            player.sendMessage(CC.RED + "You do not have a pending duel request from " + target.getName() + CC.RED + ".");
            return;
        }

        if (receiverProfile.isBusy(target)) {
            player.sendMessage(CC.translate(CC.RED + target.getDisplayName()) + CC.RED + " is currently busy.");
            return;
        }

        DuelRequest request = receiverProfile.getSentDuelRequests().get(player.getUniqueId());

        if (request == null) {
            return;
        }

        if (request.isParty()) {
            if (senderProfile.getParty() == null) {
                player.sendMessage(CC.RED + "You do not have a party to duel with.");
                return;
            } else if (receiverProfile.getParty() == null) {
                player.sendMessage(CC.RED + "That player does not have a party to duel with.");
                return;
            }
        } else {
            if (senderProfile.getParty() != null) {
                player.sendMessage(CC.RED + "You cannot duel whilst in a party.");
                return;
            } else if (receiverProfile.getParty() != null) {
                player.sendMessage(CC.RED + "That player is in a party and cannot duel right now.");
                return;
            }
        }

        Arena arena = request.getArena();

        if (arena == null) {
            player.sendMessage(CC.RED + "Tried to start a match but the arena was invalid.");
            return;
        }

        if (arena.isActive()) {
            if (arena.getType().equals(ArenaType.STANDALONE)) {
                StandaloneArena sarena = (StandaloneArena) arena;
                if (sarena.getDuplicates() != null) {
                    boolean foundarena = false;
                    for (Arena darena : sarena.getDuplicates()) {
                        if (!darena.isActive()) {
                            arena = darena;
                            foundarena = true;
                            break;
                        }
                    }
                    if (!foundarena) {
                        player.sendMessage(CC.RED + "The arena you were dueled was a build match and there were no arenas found.");
                        return;
                    }
                }
            } else {
                player.sendMessage(CC.RED + "The arena you were dueled was a build match and there were no arenas found.");
                return;
            }
        }
        if (!arena.getType().equals(ArenaType.SHARED)) {
            arena.setActive(true);
        }

        Match match;

        if (request.isParty()) {
            if (request.getKit().getName().equals("HCFTeamFight")) {
                Team teamA = new Team(new TeamPlayer(player));

                for (Player partyMember : senderProfile.getParty().getPlayers()) {
                    if (!partyMember.getPlayer().equals(player)) {
                        teamA.getTeamPlayers().add(new TeamPlayer(partyMember));
                    }
                }

                Team teamB = new Team(new TeamPlayer(target));

                for (Player partyMember : receiverProfile.getParty().getPlayers()) {
                    if (!partyMember.getPlayer().equals(target)) {
                        teamB.getTeamPlayers().add(new TeamPlayer(partyMember));
                    }
                }

                match = new HCFMatch(teamA, teamB, arena);
            } else {
                Team teamA = new Team(new TeamPlayer(player));

                for (Player partyMember : senderProfile.getParty().getPlayers()) {
                    if (!partyMember.getPlayer().equals(player)) {
                        teamA.getTeamPlayers().add(new TeamPlayer(partyMember));
                    }
                }

                Team teamB = new Team(new TeamPlayer(target));

                for (Player partyMember : receiverProfile.getParty().getPlayers()) {
                    if (!partyMember.getPlayer().equals(target)) {
                        teamB.getTeamPlayers().add(new TeamPlayer(partyMember));
                    }
                }

                if (request.getKit().getGameRules().isSumo()) {
                    match = new SumoTeamMatch(teamA, teamB, request.getKit(), arena);
                } else {
                    match = new TeamMatch(teamA, teamB, request.getKit(), arena);
                }

            }
        } else {
            if(request.getKit().getGameRules().isSumo()) {
                match = new SumoMatch(null, new TeamPlayer(player), new TeamPlayer(target), request.getKit(), arena, QueueType.UNRANKED);
            } else {
                match = new SoloMatch(null, new TeamPlayer(player), new TeamPlayer(target), request.getKit(), arena,
                        QueueType.UNRANKED, 0, 0);
            }
        }
        if (!request.isParty()) {
            for ( String string : LiquidPractice.getInstance().getMessagesConfig().getStringList("Match.Start-Message.Solo") ) {
                if (rank.getFullName(player) != null && rank.getFullName(target) != null) {
                    final String opponentMessages=this.formatMessages(string, rank.getFullName(player), rank.getFullName(target));
                    final String message=CC.translate(opponentMessages).replace("{kit}", request.getKit().getName().replace("{arena}", request.getArena().getName()));
                    match.broadcastMessage(message);
                } else {
                    final String opponentMessages=this.formatMessages(string, player.getDisplayName(), target.getDisplayName());
                    final String message=CC.translate(opponentMessages).replace("{kit}", request.getKit().getName().replace("{arena}", request.getArena().getName()));
                    match.broadcastMessage(message);
                }
            }
        }
        match.start();
    }

    private String formatMessages(final String string, final String player1, final String player2) {
        String player1Format;
        String player2Format;
        player1Format = player1;
        player2Format = player2;
        return string.replace("{player1}", player1Format).replace("{player2}", player2Format);
    }


}
