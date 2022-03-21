package dev.liquidnetwork.liquidpractice.liquidpractice.listener;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import dev.liquidnetwork.liquidpractice.util.chat.CC;

public class MOTDListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (LiquidPractice.getInstance().getMainConfig().getBoolean("MOTD.enabled")) {
            Player player=event.getPlayer();
            for ( String string : LiquidPractice.getInstance().getMainConfig().getStringList("MOTD.lines")) {
                player.sendMessage(CC.translate(string));
            }
        }
    }
}
