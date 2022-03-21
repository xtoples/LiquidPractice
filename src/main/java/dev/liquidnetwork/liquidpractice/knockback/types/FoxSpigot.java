package dev.liquidnetwork.liquidpractice.knockback.types;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.knockback.KnockbackType;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pt.foxspigot.jar.knockback.KnockbackModule;
import pt.foxspigot.jar.knockback.KnockbackProfile;

public class FoxSpigot implements KnockbackType {

    public FoxSpigot() {
        LiquidPractice.logger("&bFound FoxSpigot! Hooking in....");
    }

    @Override
    public void applyKnockback(Player p, String s) {
      KnockbackProfile knockbackProfile = KnockbackModule.INSTANCE.profiles.get(s);
      ((CraftPlayer)p).getHandle().setKnockback(knockbackProfile);
    }

    @Override
    public void appleKitKnockback(Player p, Kit kit) {
        KnockbackProfile knockbackProfile;
        if (kit.getKnockbackProfile() != null) {
            knockbackProfile = KnockbackModule.INSTANCE.profiles.get(kit.getKnockbackProfile());
        } else {
            knockbackProfile = KnockbackModule.getDefault();
        }
        ((CraftPlayer) p).getHandle().setKnockback(knockbackProfile);
    }

    @Override
    public void applyDefaultKnockback(Player p) {
        KnockbackProfile knockbackProfile = KnockbackModule.getDefault();
        ((CraftPlayer) p).getHandle().setKnockback(knockbackProfile);
    }
}
