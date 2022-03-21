package dev.liquidnetwork.liquidpractice.arena.command;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "arena save", permission = "liquidpractice.admin")
public class ArenaSaveCommand {

    public void execute(CommandSender sender) {
        for ( Arena arena : Arena.getArenas() ) {
            arena.save();
        }
        sender.sendMessage(CC.translate("&7Successfully saved &b" + Arena.getArenas().size() + " &7arenas!"));
    }
}
