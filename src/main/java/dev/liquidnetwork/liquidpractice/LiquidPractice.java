package dev.liquidnetwork.liquidpractice;

import dev.liquidnetwork.liquidpractice.arena.Arena;
import dev.liquidnetwork.liquidpractice.arena.ArenaTypeAdapter;
import dev.liquidnetwork.liquidpractice.arena.ArenaTypeTypeAdapter;
import dev.liquidnetwork.liquidpractice.liquidpractice.essentials.Essentials;
import dev.liquidnetwork.liquidpractice.enums.ArenaType;
import dev.liquidnetwork.liquidpractice.event.types.brackets.BracketsManager;
import dev.liquidnetwork.liquidpractice.event.types.lms.LMSManager;
import dev.liquidnetwork.liquidpractice.event.types.parkour.ParkourManager;
import dev.liquidnetwork.liquidpractice.event.types.spleef.SpleefManager;
import dev.liquidnetwork.liquidpractice.event.types.wizard.WizardManager;
import dev.liquidnetwork.liquidpractice.hcf.HCFManager;
import dev.liquidnetwork.liquidpractice.hotbar.Hotbar;
import dev.liquidnetwork.liquidpractice.match.Match;
import dev.liquidnetwork.liquidpractice.party.Party;
import dev.liquidnetwork.liquidpractice.profile.Profile;
import dev.liquidnetwork.liquidpractice.profile.rank.Rank;
import dev.liquidnetwork.liquidpractice.profile.rank.RankType;
import dev.liquidnetwork.liquidpractice.profile.rank.apis.DefaultProvider;
import dev.liquidnetwork.liquidpractice.queue.QueueThread;
import dev.liquidnetwork.liquidpractice.register.RegisterCommands;
import dev.liquidnetwork.liquidpractice.register.RegisterListeners;
import dev.liquidnetwork.liquidpractice.util.Description;
import dev.liquidnetwork.liquidpractice.util.EntityHider;
import dev.liquidnetwork.liquidpractice.util.InventoryUtil;
import dev.liquidnetwork.liquidpractice.util.TaskUtil;
import dev.liquidnetwork.liquidpractice.util.external.ItemBuilder;
import dev.liquidnetwork.liquidpractice.util.external.duration.Duration;
import dev.liquidnetwork.liquidpractice.util.external.duration.DurationTypeAdapter;
import dev.liquidnetwork.liquidpractice.util.scoreboard.Aether;
import lombok.Setter;
import dev.liquidnetwork.liquidpractice.event.types.oitc.OITCManager;
import dev.liquidnetwork.liquidpractice.knockback.KnockbackManager;
import dev.liquidnetwork.liquidpractice.hcf.bard.EffectRestorer;
import dev.liquidnetwork.liquidpractice.util.chat.CC;
import org.bukkit.World;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.error.YAMLException;
import dev.liquidnetwork.liquidpractice.util.config.BasicConfigurationFile;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import dev.liquidnetwork.liquidpractice.util.command.Honcho;
import me.allen.ziggurat.Ziggurat;
import dev.liquidnetwork.liquidpractice.event.types.sumo.SumoManager;
import dev.liquidnetwork.liquidpractice.kit.Kit;
import dev.liquidnetwork.liquidpractice.kit.KitTypeAdapter;
import dev.liquidnetwork.liquidpractice.scoreboard.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import lombok.Getter;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class LiquidPractice extends JavaPlugin {

    private static LiquidPractice LiquidPractice;
    private BasicConfigurationFile mainConfig;
    private BasicConfigurationFile arenasConfig;
    private BasicConfigurationFile kitsConfig;
    private BasicConfigurationFile eventsConfig;
    private BasicConfigurationFile messagesConfig;
    public static Random random;
    @Setter private RankType rankManager;
    @Setter private Rank rankSystem;
    private Kit hcfKit;
    private MongoDatabase mongoDatabase;
    private Aether scoreboard;
    private Ziggurat tab;
    private SumoManager sumoManager;
    private BracketsManager bracketsManager;
    private dev.liquidnetwork.liquidpractice.event.types.lms.LMSManager LMSManager;
    private ParkourManager parkourManager;
    private SpleefManager spleefManager;
    private OITCManager OITCManager;
    private WizardManager wizardManager;
    private KnockbackManager knockbackManager;
    private dev.liquidnetwork.liquidpractice.hcf.HCFManager HCFManager;
    private EffectRestorer effectRestorer;
    private Essentials essentials;
    @Getter
    private static Honcho honcho;
    private boolean disabling = false;
    private EntityHider entityHider;

    public static LiquidPractice getInstance() {
        return LiquidPractice;
    }

    @Override
    public void onEnable() {
        // Disable spam logger mongodb
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver.cluster");
        mongoLogger.setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("JULLogger").setLevel(Level.OFF);

        LiquidPractice = this;
        random = new Random();
        honcho = new Honcho(this);

        // Setup All Configs
        mainConfig = new BasicConfigurationFile(this, "config");
        arenasConfig = new BasicConfigurationFile(this, "arenas");
        kitsConfig = new BasicConfigurationFile(this, "kits");
        eventsConfig = new BasicConfigurationFile(this, "events");
        messagesConfig = new BasicConfigurationFile(this, "messages");

        // To Prevent Stealing and Renaming (Skidding)
        if (!Description.getAuthor().contains("Yzzird") && !Description.getAuthor().contains("Toples")) {
            logger("------------------------------------------------");
            logger("&cYou edited the plugin.yml, please don't do that");
            logger( "&cPlease check your plugin.yml and try again.");
            logger("            &cDisabling LiquidPractice");
            logger("------------------------------------------------");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // To Prevent Stealing and Renaming (Skidding)
        if (!Description.getName().contains("LiquidPractice")) {
            logger("------------------------------------------------");
            logger("&cYou edited the plugin.yml, please don't do that");
            logger(" &cPlease check your plugin.yml and try again.");
            logger("            &cDisabling LiquidPractice");
            logger("------------------------------------------------");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (this.mainConfig.getBoolean("Performance-mode")) {
            TaskUtil.runAsync(() -> {
            registerAll();
            RegisterCommands.register();
          });
        } else {
            registerAll();
            RegisterCommands.register();
        }
        knockbackManager = new KnockbackManager();
        sumoManager = new SumoManager();
        bracketsManager = new BracketsManager();
        LMSManager = new LMSManager();
        parkourManager = new ParkourManager();
        spleefManager = new SpleefManager();
        wizardManager = new WizardManager();
        OITCManager = new OITCManager();

        if (mainConfig.getBoolean("LiquidPractice.Core-Hook")) {
            // Core API Support
            rankSystem = new Rank();
        } else {
            setRankManager(new DefaultProvider());
        }
        this.entityHider = EntityHider.enable();
        this.effectRestorer = new EffectRestorer(this);
        this.HCFManager= new HCFManager(this);

        if (mainConfig.getBoolean("LiquidPractice.HCF-Enabled")) {
            // Create HCF's Duel Kit
            this.hcfKit = new Kit("HCFTeamFight");
            this.hcfKit.setDisplayIcon(new ItemBuilder(Material.BEACON).clearEnchantments().clearFlags().build());
        }

        Arrays.asList(Material.WORKBENCH,
                      Material.STICK,
                      Material.WOOD_PLATE,
                      Material.WOOD_BUTTON,
                      Material.SNOW_BLOCK
        ).forEach(InventoryUtil::removeCrafting);

        for ( World world : this.getServer().getWorlds() ) {
            world.setDifficulty(Difficulty.EASY);
         }

        if (this.mainConfig.getBoolean("Performance-mode")) {
            TaskUtil.runAsync(() -> {
                RegisterListeners.register();
                this.registerEssentials();
            });
        } else {
            RegisterListeners.register();
            this.registerEssentials();
        }
    }

    @Override
    public void onDisable() {
        disabling = true;
        // Stop all matches and Remove the placed Block
        Match.cleanup();
        // Save Everything before disabling to prevent data loss
        Kit.getKits().forEach(Kit::save);
        Arena.getArenas().forEach(Arena::save);
        Profile.getProfiles().values().forEach(Profile::save);
        // Clear out the PlayerList for Vanilla Tab
        Profile.getPlayerList().clear();
    }

    public MetadataValue getMetadata(Metadatable m, String tag) {
        for (MetadataValue mv : m.getMetadata(tag))
            if (mv != null && mv.getOwningPlugin() != null && mv.getOwningPlugin() == this) {
                return mv;
            }
        return null;
    }

    private void registerAll() {
        try {
            preLoadMongo();
        } catch (NullPointerException | MongoInterruptedException | MongoInternalException | MongoCommandException | MongoClientException e) {
            logger(CC.CHAT_BAR);
            logger("            &4&lMongo Internal Error");
            logger("        &cMongo is not setup correctly!");
            logger(     "&cPlease check your mongo and try again.");
            logger("              &4&lDisabling LiquidPractice");
            logger(CC.CHAT_BAR);
            this.shutDown();
            return;
        }

        logger("&bLoading Profiles!");
        Profile.preload();
        logger("&aLoaded Profiles!");

        try {
            logger("&bLoading Kits!");
            Kit.preload();
            logger("&aLoaded Kits!");
        } catch (YAMLException e) {
            logger(CC.CHAT_BAR);
            logger("       &cError Loading Kits: &cYAML Error");
            logger("    &cThis means your configuration was wrong.");
            logger("  &cPlease check your Kits config and try again!");
            logger("               &4&lDisabling LiquidPractice");
            logger(CC.CHAT_BAR);
            this.shutDown();
            return;
        }
        try {
            logger("&bLoading Arenas!");
            Arena.preload();
        } catch (YAMLException e) {
            logger(CC.CHAT_BAR);
            logger("      &cError Loading Kits: &cYAML Error");
            logger("   &cThis means your configuration was wrong.");
            logger(" &cPlease check your Arenas config and try again!");
            logger("              &4&lDisabling LiquidPractice");
            logger(CC.CHAT_BAR);
            this.shutDown();
            return;
        }
        new Hotbar();
        Match.preload();
        Party.preload();

        essentials = new Essentials();

        honcho.registerTypeAdapter(Arena.class, new ArenaTypeAdapter());
        honcho.registerTypeAdapter(ArenaType.class, new ArenaTypeTypeAdapter());
        honcho.registerTypeAdapter(Kit.class, new KitTypeAdapter());
        honcho.registerTypeAdapter(Duration.class, new DurationTypeAdapter());
    }

    private void registerEssentials() {

        this.scoreboard = new Aether(this, new Scoreboard());
        this.scoreboard.getOptions().hook(true);
        // Start the Queue Thread
        new QueueThread().start();

        // Load the Global Leaderboards (Also a bug fix for leaderboards being blank on start)
        Profile.loadGlobalLeaderboards();
    }

    public static void logger(String message) {
        String msg = CC.translate("&r" + message);
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    private void preLoadMongo() {
        MongoClient client = new MongoClient(new MongoClientURI(mainConfig.getString("Mongo.URL")));
        this.mongoDatabase = client.getDatabase(mainConfig.getString("Mongo.Database"));

        // Disable spam logger mongodb
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("JULLogger").setLevel(Level.OFF);
    }

    public void shutDown() {
        this.onDisable();
        logger("Shutting down LiquidPractice!");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
