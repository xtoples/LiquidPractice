package dev.liquidnetwork.liquidpractice.hcf;

import dev.liquidnetwork.liquidpractice.LiquidPractice;
import dev.liquidnetwork.liquidpractice.enums.HotbarType;
import dev.liquidnetwork.liquidpractice.hotbar.Hotbar;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.hcf.events.ArmorClassEquipEvent;
import dev.liquidnetwork.liquidpractice.hcf.events.ArmorClassUnequipEvent;
import dev.liquidnetwork.liquidpractice.hcf.classes.Archer;
import dev.liquidnetwork.liquidpractice.hcf.classes.Bard;
import dev.liquidnetwork.liquidpractice.hcf.classes.Rogue;
import dev.liquidnetwork.liquidpractice.util.events.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class HCFManager implements Listener {

    protected Map<UUID, HCFClasses> classWarmups = new HashMap<>();
    // Mapping to getInstance the PVP Class a player has equipped.
    private final Map<UUID, HCFClasses> equippedClassMap = new HashMap<>();
    private final List<HCFClasses> pvpClasses = new ArrayList<>();

    public HCFManager(LiquidPractice plugin) {
        pvpClasses.add(new Bard(plugin));
        pvpClasses.add(new Archer(plugin));
        pvpClasses.add(new Rogue(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        for ( HCFClasses pvpClass : pvpClasses) {
            if (pvpClass instanceof Listener) {
                plugin.getServer().getPluginManager().registerEvents((Listener) pvpClass, plugin);
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Profile profile = Profile.getByUuid(player.getUniqueId());
                    Match match = profile.getMatch();
                    if (match != null && match.isHCFMatch()) {
                        Bukkit.getScheduler().runTask(LiquidPractice.getInstance(), () -> {
                            attemptEquip(player);
                        });
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 20, 20);
    }

    public void onDisable() {
        for (Map.Entry<UUID, HCFClasses> entry : new HashMap<>(equippedClassMap).entrySet()) {
            this.setEquippedClass(Bukkit.getPlayer(entry.getKey()), null);
        }

        this.pvpClasses.clear();
        this.equippedClassMap.clear();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (Profile.getByUuid(event.getEntity().getUniqueId()).isInMatch() && Profile.getByUuid(event.getEntity().getUniqueId()).getMatch().isHCFMatch()) {
            setEquippedClass(event.getEntity(), null);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onArmorChange(ArmorEquipEvent event) {
        Profile profile = Profile.getByUuid(event.getPlayer().getUniqueId());
        Match match = profile.getMatch();
        if (match != null && match.isHCFMatch()) {
            Bukkit.getScheduler().runTask(LiquidPractice.getInstance(), () -> {
                this.attemptEquip(event.getPlayer());
            });
        }
    }

    public void attemptEquip(Player player) {
        HCFClasses current = LiquidPractice.getInstance().getHCFManager().getEquippedClass(player);
        if (current != null) {
            if (current.isApplicableFor(player)) {
                return;
            }

            LiquidPractice.getInstance().getHCFManager().setEquippedClass(player, null);
        } else if ((current = classWarmups.get(player.getUniqueId())) != null) {
            if (current.isApplicableFor(player)) {
                return;
            }

        }

        Collection<HCFClasses> pvpClasses = LiquidPractice.getInstance().getHCFManager().getPvpClasses();
        for ( HCFClasses pvpClass : pvpClasses) {
            if (pvpClass.isApplicableFor(player)) {
                LiquidPractice.getInstance().getHCFManager().setEquippedClass(player, pvpClass);
                break;
            }
        }
    }

    /**
     * Gets the {@link HCFClasses}es held by this manager
     *
     * @return set of {@link HCFClasses}es
     */
    public Collection<HCFClasses> getPvpClasses() {
        return pvpClasses;
    }

    /**
     * Gets the equipped {@link HCFClasses} of a {@link Player}.
     *
     * @param player the {@link Player} to getInstance for
     * @return the equipped {@link HCFClasses}
     */
    public HCFClasses getEquippedClass(Player player) {
        synchronized (equippedClassMap) {
            return equippedClassMap.get(player.getUniqueId());
        }
    }

    public boolean hasClassEquipped(Player player, HCFClasses pvpClass) {
        return getEquippedClass(player) == pvpClass;
    }

    /**
     * Sets the equipped {@link HCFClasses} of a {@link Player}.
     *
     * @param player   the {@link Player} to set for
     * @param pvpClass the class to equip or null to un-equip active
     */
    public void setEquippedClass(Player player, HCFClasses pvpClass) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        Match match = profile.getMatch();
        if (match != null && match.isHCFMatch() ) {
            if (pvpClass == null) {
                HCFClasses equipped = this.equippedClassMap.remove(player.getUniqueId());
                if (equipped != null) {
                    equipped.onUnequip(player);
                    Bukkit.getPluginManager().callEvent(new ArmorClassUnequipEvent(player, equipped));
                }
            } else if (pvpClass.onEquip(player) && pvpClass != this.getEquippedClass(player)) {
                equippedClassMap.put(player.getUniqueId(), pvpClass);
                Bukkit.getPluginManager().callEvent(new ArmorClassEquipEvent(player, pvpClass));
            }
        }
    }

    public static List<ItemStack> getHCFKitItems() {
        List<ItemStack> toReturn = new ArrayList<>();
        toReturn.add(Hotbar.getItems().get(HotbarType.DIAMOND_KIT));
        toReturn.add(Hotbar.getItems().get(HotbarType.BARD_KIT));
        toReturn.add(Hotbar.getItems().get(HotbarType.ARCHER_KIT));
        toReturn.add(Hotbar.getItems().get(HotbarType.ROGUE_KIT));
        return toReturn;
    }
}
