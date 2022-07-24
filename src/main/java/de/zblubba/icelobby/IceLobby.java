package de.zblubba.icelobby;

import de.zblubba.icelobby.commands.*;
import de.zblubba.icelobby.items.AddItemsOnJoin;
import de.zblubba.icelobby.items.CompassGUI;
import de.zblubba.icelobby.items.CompassGUIListener;
import de.zblubba.icelobby.items.WarpCommand;
import de.zblubba.icelobby.listeners.*;
import de.zblubba.icelobby.util.ConfigBuilder;
import de.zblubba.icelobby.util.Updater;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class IceLobby extends JavaPlugin {

    public static IceLobby instance;

    public static boolean isUpdateAvailable;
    public static boolean areConfigsLoaded = false;

    public static File file = new File("plugins/IceLobby", "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static File fileSpawn = new File("plugins/IceLobby", "spawn.yml");
    static FileConfiguration spawnConfig = YamlConfiguration.loadConfiguration(fileSpawn);

    public static File fileItem = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(fileItem);

    public static Updater updater;
    public static ArrayList<String> lobbyWorlds = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        createFiles();
        loadConfigFiles();

        //ConfigBuilder.checkConfigs();
        registerListeners();
        registerCommands();

        lobbyWorlds = (ArrayList<String>) config.getList("defaults.lobbyWorlds");

        if(areConfigsLoaded) {
            setWeather();
        }

        updater = new Updater(35799);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(IceLobby.getInstance(), () -> {
            updater.runCheck();
        }, 0L, 20 * 60 * 60 * 24);
        //run this every day

        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§7Product§8: §bIce§9Lobby");
        Bukkit.getConsoleSender().sendMessage("§7Status: §8: §aActive");
        Bukkit.getConsoleSender().sendMessage("§7Developer§8: §bzBlubba");
        Bukkit.getConsoleSender().sendMessage("§7Version§8: §b" + IceLobby.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§7OS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§7Java-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§7Product§8: §bIce§9Lobby");
        Bukkit.getConsoleSender().sendMessage("§7Status: §8: §cDeactivated");
        Bukkit.getConsoleSender().sendMessage("§7Developer§8: §bzBlubba");
        Bukkit.getConsoleSender().sendMessage("§7Version§8: §b" + IceLobby.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§7OS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§7Java-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ModtListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new AddItemsOnJoin(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new InteractEvent(), this);
        pm.registerEvents(new GeneralListeners(), this);
        pm.registerEvents(new CompassGUIListener(), this);
    }
    public void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("teleportall").setExecutor(new TpAllCommand());
        getCommand("globalmute").setExecutor(new GlobalMuteCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("navigator").setExecutor(new CompassGUI());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("nothing").setExecutor(new NothingCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("visibility").setExecutor(new VisibilityCommand());
    }

    public void createFiles() {
        if(!IceLobby.fileItem.exists() | !IceLobby.fileSpawn.exists()) {
            IceLobby.getInstance().getLogger().info("One or more files were not found. Creating...");
            if(!IceLobby.fileItem.exists()) {
                IceLobby.fileItem.getParentFile().mkdirs();
                IceLobby.getInstance().saveResource("items.yml", false);
            }
            if(!IceLobby.fileSpawn.exists()) {
                IceLobby.fileSpawn.getParentFile().mkdirs();
                IceLobby.getInstance().saveResource("spawn.yml", false);
            }
        }
    }

    public void loadConfigFiles() {
        IceLobby.getInstance().getLogger().info("Loading the config files.");
        try {
            IceLobby.config.load(file);
            IceLobby.spawnConfig.load(fileSpawn);
            IceLobby.itemConfig.load(fileItem);

            areConfigsLoaded = true;

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } catch (InvalidConfigurationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void saveFile(File file, FileConfiguration configuration) {
        try {
            configuration.save(file);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public static ArrayList<String> getLobbyWorlds() {return lobbyWorlds;}

    public static void setWeather() {
        String defaultWeather = config.getString("defaults.default_weather");
        if(lobbyWorlds == null) return;

        for(int i = 0; i < getLobbyWorlds().size(); i++) {
            World world = Bukkit.getWorld(getLobbyWorlds().get(i));
            switch(defaultWeather) {
                case "CLEAR" -> {
                    world.setThundering(false);
                    world.setStorm(false);
                }
                case "RAIN" -> {
                    world.setThundering(false);
                    world.setStorm(true);
                }
                case "THUNDER" -> {
                    world.setThundering(true);
                    world.setStorm(true);
                }
            }
        }
    }

    public static IceLobby getInstance() {
        return instance;
    }

}
//TODO: CHECK - MessageCollection add more variabled - {prefix}, {maxPlayers}, {onlineplayers}
//TODO: CHECK - rewrite commands to implement MessageCollection
//TODO: CHECK - add Compass
//TODO: CHECK - add own Player head to hotbar
//TODO: CHECK - help command
//TODO: CHECK - change unknown command message
//TODO: CHECK - interact with lobby items
//TODO: CHECK - Build mode
//TODO: CHECK - hotbarItems can be moved + Offhand
//TODO: CHECK - updater
//TODO: CHECK - only 1 world with name or all worlds option
//TODO: tablist
//TODO: besides OWN_HEAD - add PLAYERS_HEAD for other player's head
//TODO: Placeholder API


