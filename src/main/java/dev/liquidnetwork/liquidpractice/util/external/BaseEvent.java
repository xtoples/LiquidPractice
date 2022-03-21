package dev.liquidnetwork.liquidpractice.util.external;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BaseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public void call() {
        LiquidPractice.getInstance().getServer().getPluginManager().callEvent(this);
    }

}
