package dev.liquidnetwork.liquidpractice.event.types.parkour;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.external.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import dev.liquidnetwork.liquidpractice.event.types.parkour.task.ParkourStartTask;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public class ParkourManager {

	@Getter private Parkour activeParkour;
	@Getter @Setter private Cooldown cooldown = new Cooldown(0);
	@Getter @Setter private Location parkourSpawn;

	public ParkourManager() {
		load();
	}

	public void setActiveParkour(Parkour parkour) {
		if (activeParkour != null) {
			activeParkour.setEventTask(null);
		}

		if (parkour == null) {
			activeParkour = null;
			return;
		}

		activeParkour = parkour;
		activeParkour.setEventTask(new ParkourStartTask(parkour));
	}

	public void load() {
		FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

		if (configuration.contains("events.parkour.spectator")) {
			parkourSpawn = LocationUtil.deserialize(configuration.getString("events.parkour.spectator"));
		}
	}

	public void save() {
		FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

		if (parkourSpawn != null) {
			configuration.set("events.parkour.spectator", LocationUtil.serialize(parkourSpawn));
		}

		try {
			configuration.save(LiquidPractice.getInstance().getEventsConfig().getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
