package dev.liquidnetwork.liquidpractice.event.types.oitc;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.external.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import dev.liquidnetwork.liquidpractice.event.types.oitc.task.OITCStartTask;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public class OITCManager {

    @Getter private OITC activeOITC;
    @Getter @Setter private Cooldown cooldown = new Cooldown(0);
    @Getter @Setter private Location OITCSpectator;
    @Getter @Setter private String OITCKnockbackProfile;

    public OITCManager() {
        load();
    }

    public void setActiveOITC(OITC OITC) {
        if (activeOITC != null) {
            activeOITC.setEventTask(null);
        }

        if (OITC == null) {
            activeOITC = null;
            return;
        }

        activeOITC = OITC;
        activeOITC.setEventTask(new OITCStartTask(OITC));
    }

    public void load() {
        FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

        if (configuration.contains("events.oitc.spectator")) {
            OITCSpectator = LocationUtil.deserialize(configuration.getString("events.oitc.spectator"));
        }

        if (configuration.contains("events.oitc.knockback-profile")) {
            OITCKnockbackProfile = configuration.getString("events.oitc.knockback-profile");
        }
    }

    public void save() {
        FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

        if (OITCSpectator != null) {
            configuration.set("events.oitc.spectator", LocationUtil.serialize(OITCSpectator));
        }

        if (OITCKnockbackProfile != null) {
            configuration.set("events.oitc.knockback-profile", OITCKnockbackProfile);
        }

        try {
            configuration.save(LiquidPractice.getInstance().getEventsConfig().getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
