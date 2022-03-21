package dev.liquidnetwork.liquidpractice.event.types.wizard;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.util.external.Cooldown;
import dev.liquidnetwork.liquidpractice.util.external.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import dev.liquidnetwork.liquidpractice.event.types.wizard.task.WizardStartTask;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

@Getter
public class WizardManager {

	private Wizard activeWizard;
	@Setter private Cooldown cooldown = new Cooldown(0);
	@Setter private Location wizardSpectator;
	@Setter private Location wizardSpawn1;
	@Setter private Location wizardSpawn2;
	@Setter private String wizardKnockbackProfile;

	public WizardManager() {
		load();
	}

	public void setActiveWizard(Wizard wizard) {
		if (activeWizard != null) {
			activeWizard.setEventTask(null);
		}

		if (wizard == null) {
			activeWizard= null;
			return;
		}

		activeWizard=wizard;
		activeWizard.setEventTask(new WizardStartTask(wizard));
	}

	public void load() {
		FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

		if (configuration.contains("events.wizard.spectator")) {
			wizardSpectator = LocationUtil.deserialize(configuration.getString("events.wizard.spectator"));
		}

		if (configuration.contains("events.wizard.spawn1")) {
			wizardSpawn1 = LocationUtil.deserialize(configuration.getString("events.wizard.spawn1"));
		}

		if (configuration.contains("events.wizard.spawn2")) {
			wizardSpawn2 = LocationUtil.deserialize(configuration.getString("events.wizard.spawn2"));
		}

		if (configuration.contains("events.wizard.knockback-profile")) {
			wizardKnockbackProfile = configuration.getString("events.wizard.knockback-profile");
		}
	}

	public void save() {
		FileConfiguration configuration = LiquidPractice.getInstance().getEventsConfig().getConfiguration();

		if (wizardSpectator != null) {
			configuration.set("events.wizard.spectator", LocationUtil.serialize(wizardSpectator));
		}

		if (wizardSpawn1 != null) {
			configuration.set("events.wizard.spawn1", LocationUtil.serialize(wizardSpawn1));
		}

		if (wizardSpawn2 != null) {
			configuration.set("events.wizard.spawn2", LocationUtil.serialize(wizardSpawn2));
		}

		if (wizardKnockbackProfile != null) {
			configuration.set("events.wizard.knockback-profile", wizardKnockbackProfile);
		}

		try {
			configuration.save(LiquidPractice.getInstance().getEventsConfig().getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
