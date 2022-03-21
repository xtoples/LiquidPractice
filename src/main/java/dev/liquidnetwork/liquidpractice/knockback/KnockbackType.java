package dev.liquidnetwork.liquidpractice.knockback;

import dev.liquidnetwork.liquidpractice.kit.Kit;
import org.bukkit.entity.Player;

public interface KnockbackType {

    void applyKnockback(Player p, String s);

    void appleKitKnockback(Player p, Kit kit);

    void applyDefaultKnockback(Player p);
}
