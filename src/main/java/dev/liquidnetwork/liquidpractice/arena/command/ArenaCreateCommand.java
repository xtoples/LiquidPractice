package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.arena.impl.SharedArena;
import dev.liquidnetwork.liquidpractice.arena.impl.StandaloneArena;
import dev.liquidnetwork.liquidpractice.arena.impl.TheBridgeArena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena create", permission = "liquidpractice.admin")
public class ArenaCreateCommand {

    public void execute(Player player, @CPL("name") String name, @CPL("[shared|standalone|bridge]") String type) {
        if (!type.equalsIgnoreCase("standalone") && !type.equalsIgnoreCase("shared") && !type.equalsIgnoreCase("bridge")) {
            player.sendMessage(CC.translate("&7Invalid Type."));
            return;
        }

        if (name == null) {
            player.sendMessage(CC.translate("&7Please provide a name."));
            return;
        }

        Arena arena;

        if (Arena.getArenas().contains(Arena.getByName(name))) {
            if (type.equalsIgnoreCase("shared")) {
                player.sendMessage(CC.translate("&7You can't convert a Shared arena to a duped one."));
                return;
            }
            arena = new Arena(name);

            Location loc1 =
            new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(),
                         player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
            arena.setSpawn1(loc1);
            arena.setSpawn2(loc1);
            StandaloneArena sarena = (StandaloneArena) Arena.getByName(name);
            assert sarena != null;
            sarena.getDuplicates().add(arena);
            player.sendMessage(CC.translate("&7Saved a duplicate arena from &b" + name + "&8(&7#&b" + sarena.getDuplicates().size() + "&8)"));
        } else {
            if (type.equalsIgnoreCase("shared")){
                arena = new SharedArena(name);
            } else if (type.equalsIgnoreCase("bridge")) {
                arena = new TheBridgeArena(name);
                player.sendMessage(CC.translate("&8[&bLTIP&8] &7Please note that 'Red' is set to Spawn 1 and 'Blue' is set to Spawn 2."));
            } else if (type.equalsIgnoreCase("standalone")){
               arena = new StandaloneArena(name);
            } else {
                arena = new StandaloneArena(name);
            }

            Location loc1 = new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(),
                    player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());

            arena.setSpawn1(loc1);
            arena.setSpawn2(loc1);
            player.sendMessage(CC.translate("&7Successfully created an Arena called &b" + name + "&7 of type &b" + type));
        }
        Arena.getArenas().add(arena);
        Arena.getArenas().forEach(Arena::save);
    }

}