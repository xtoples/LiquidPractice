package dev.liquidnetwork.liquidpractice.event.types.brackets;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.external.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import dev.liquidnetwork.liquidpractice.event.types.brackets.task.BracketsStartTask;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public class BracketsManager {

	@Getter private Brackets activeBrackets;
	@Getter @Setter private Cooldown cooldown = new Cooldown(0);
	@Getter @Setter private Location bracketsSpectator;
	@Getter @Setter private Location bracketsSpawn1;
	@Getter @Setter private Location bracketsSpawn2;
	@Getter @Setter private String bracketsKnockbackProfile;

	public BracketsManager() {
		load();
	}

	public void setActiveBrackets(Brackets brackets) {
		if (activeBrackets != null) {
			activeBrackets.setEventTask(null);
		}

		if (brackets == null) {
			activeBrackets = null;
			return;
		}

		activeBrackets = brackets;
		activeBrackets.setEventTask(new BracketsStartTask(brackets));
	}

	public void load() {
		FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

		if (configuration.contains("events.brackets.spectator")) {
			bracketsSpectator = LocationUtil.deserialize(configuration.getString("events.brackets.spectator"));
		}

		if (configuration.contains("events.brackets.spawn1")) {
			bracketsSpawn1 = LocationUtil.deserialize(configuration.getString("events.brackets.spawn1"));
		}

		if (configuration.contains("events.brackets.spawn2")) {
			bracketsSpawn2 = LocationUtil.deserialize(configuration.getString("events.brackets.spawn2"));
		}

		if (configuration.contains("events.brackets.knockback-profile")) {
			bracketsKnockbackProfile = configuration.getString("events.brackets.knockback-profile");
		}
	}

	public void save() {
		FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

		if (bracketsSpectator != null) {
			configuration.set("events.brackets.spectator", LocationUtil.serialize(bracketsSpectator));
		}

		if (bracketsSpawn1 != null) {
			configuration.set("events.brackets.spawn1", LocationUtil.serialize(bracketsSpawn1));
		}

		if (bracketsSpawn2 != null) {
			configuration.set("events.brackets.spawn2", LocationUtil.serialize(bracketsSpawn2));
		}

		if (bracketsKnockbackProfile != null) {
			configuration.set("events.brackets.knockback-profile", bracketsKnockbackProfile);
		}

		try {
			configuration.save(LiquidPractice.getInstance().getEventsConfig().getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
