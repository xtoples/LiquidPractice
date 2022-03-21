package dev.liquidnetwork.liquidpractice.knockback;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.knockback.types.FoxSpigot;
import lombok.Getter;

public class KnockbackManager {

    @Getter public KnockbackType knockbackType;

    public KnockbackManager() {
        preload();
    }

    public void preload() {
        try {
            Class.forName("pt.foxspigot.jar.knockback.KnockbackModule");
            this.knockbackType = new FoxSpigot();
        } catch(Exception e) {
            LiquidPractice.logger("&cSpigotX/FoxSpigot not found...");
        }
        /* if (Package.getPackage("org.imanity") !=null) {
            this.knockbackType = new ImanitySpigot();
           */
        }
    }

