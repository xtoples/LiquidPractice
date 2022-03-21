package dev.liquidnetwork.liquidpractice.liquidpractice.commands;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.PlayerUtil;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import dev.liquidnetwork.liquidpractice.util.command.command.CPL;
import dev.liquidnetwork.liquidpractice.util.command.command.CommandMeta;
import org.bson.Document;
import org.bukkit.entity.Player;

@CommandMeta(label={"liquidpractice reset", "liquidpractice resetstats"}, permission="liquidpractice.admin")
public class ResetStatsCMD {
    public void execute(Player p, @CPL("profile") String name) {
        if (name == null) {
            p.sendMessage(CC.RED + "Either that player does not exist or you did not specify a name!");
        }
        try {
            LiquidPractice.getInstance().getMongoDatabase().getCollection("profiles").deleteOne(new Document("name", name));
            PlayerUtil.getPlayer(name).kickPlayer("Your stats has been deleted by Staff, please rejoin!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
