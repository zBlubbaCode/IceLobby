package de.zblubba.icelobby;

import de.zblubba.icelobby.commands.*;
import de.zblubba.icelobby.items.*;
import de.zblubba.icelobby.listeners.*;
import de.zblubba.icelobby.listeners.V1_9.GeneralListenersV1_9;
import de.zblubba.icelobby.shop.EconomySystem;
import de.zblubba.icelobby.shop.ShopCommand;
import de.zblubba.icelobby.shop.ShopGUIListener;
import de.zblubba.icelobby.util.Updater;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public final class IceLobby extends JavaPlugin {

    public static IceLobby instance;
    public static int taskid;

    public static boolean isUpdateAvailable;
    public static boolean areConfigsLoaded = false;

    public static File file = new File("plugins/IceLobby", "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static File fileItem = new File("plugins/IceLobby", "items.yml");
    public static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(fileItem);

    public static File fileMessages = new File("plugins/IceLobby", "messages.yml");
    public static FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(fileMessages);

    public static File fileMoney = new File("plugins/IceLobby", "money.yml");
    public static FileConfiguration moneyConfig = YamlConfiguration.loadConfiguration(fileMessages);

    public static File fileWarps = new File("plugins/IceLobby", "warps.yml");
    public static FileConfiguration warpConfig = YamlConfiguration.loadConfiguration(fileWarps);

    public static Updater updater;
    public static ArrayList<String> lobbyWorlds = new ArrayList<>();

    public static String server = Bukkit.getServer().getClass().getPackage().getName();
    public static String version = server.substring(server.lastIndexOf('.') + 1);

    @Override
    public void onEnable() {
        instance = this;

        //load all the stuff needed
        saveDefaultConfig();
        createFiles();
        loadConfigFiles();

        registerListeners();
        registerCommands();

        //get all the worlds, which are marked as lobby
        lobbyWorlds = (ArrayList<String>) config.getList("lobby_worlds");

        if(areConfigsLoaded) {
            setWeather();
            startOneSecondInterval();
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
        Bukkit.getConsoleSender().sendMessage("§7Server-Version§8: §b" + version);
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
        Bukkit.getConsoleSender().sendMessage("§7Server-Version§8: §b" + version);
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
        pm.registerEvents(new CompassGUIListener(), this);
        pm.registerEvents(new ShopGUIListener(), this);
        pm.registerEvents(new LobbySwitcherGUIListener(), this);

        //register the commands only available in 1.9 or higher
        String versionStr = version.substring(1); // Remove v prefix
        int versionInt = Integer.parseInt(versionStr.split("_")[0]) * 1000 + Integer.parseInt(versionStr.split("_")[1]) * 10;
        if(versionInt >= 1090) {
            pm.registerEvents(new GeneralListenersV1_9(), this);
        } else {
            pm.registerEvents(new GeneralListeners(), this);
        }
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
        getCommand("icelobby").setExecutor(new IceLobbyCommand());
        getCommand("coins").setExecutor(new EconomySystem());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("lobbies").setExecutor(new LobbySwitcherCommand());
        getCommand("removecosmetics").setExecutor(new RemovecosmeticsCommand());
    }

    public static void createFiles() {
        if(!IceLobby.fileItem.exists() || !IceLobby.fileMessages.exists() || !IceLobby.fileMoney.exists() || !IceLobby.fileWarps.exists()) {
            IceLobby.getInstance().getLogger().info("One or more files were not found. Creating...");
            if(!IceLobby.fileItem.exists()) {
                IceLobby.fileItem.getParentFile().mkdirs();
                IceLobby.getInstance().saveResource("items.yml", false);
            }
            if(!IceLobby.fileMessages.exists()) {
                IceLobby.fileMessages.getParentFile().mkdirs();
                IceLobby.getInstance().saveResource("messages.yml", false);
            }
            if(!IceLobby.fileMoney.exists()) {
                IceLobby.fileMoney.getParentFile().mkdirs();
                IceLobby.getInstance().saveResource("money.yml", false);
            }
            if(!IceLobby.fileWarps.exists()) {
                IceLobby.fileWarps.getParentFile().mkdirs();
                IceLobby.getInstance().saveResource("warps.yml", false);
            }
        }
    }

    public static void loadConfigFiles() {
        IceLobby.getInstance().getLogger().info("Loading the config files.");
        try {
            IceLobby.config.load(file);
            IceLobby.itemConfig.load(fileItem);
            IceLobby.messagesConfig.load(fileMessages);
            IceLobby.moneyConfig.load(fileMoney);
            IceLobby.warpConfig.load(fileWarps);

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
        String defaultWeather = config.getString("default_weather");
        if(lobbyWorlds == null) return;

        for(int i = 0; i < getLobbyWorlds().size(); i++) {
            if(Bukkit.getWorld(getLobbyWorlds().get(i)) == null) return;
            World world = Bukkit.getWorld(getLobbyWorlds().get(i));
            switch(defaultWeather) {
                case "CLEAR":
                    world.setThundering(false);
                    world.setStorm(false);
                    break;
                case "RAIN":
                    world.setThundering(false);
                    world.setStorm(true);
                    break;
                case "THUNDER":
                    world.setThundering(true);
                    world.setStorm(true);
                    break;
            }
        }
    }

    public static void startOneSecondInterval() {
        taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(IceLobby.getPlugin(IceLobby.class), () -> {

            for(Player players : Bukkit.getOnlinePlayers()) {
                if(!config.getBoolean("void.teleport_on_fall")) return;
                if(!IceLobby.getLobbyWorlds().contains(players.getLocation().getWorld().getName())) return;
                int yborder = config.getInt("void.yborder");
                if(players.getLocation().getY() <= yborder) {
                    players.teleport(WarpManager.getWarp(config.getString("void.warp_to_teleport")));
                }
            }

            for(int i = 0; i < getLobbyWorlds().size(); i++) {
                World world = Bukkit.getWorld(getLobbyWorlds().get(i));
                if(world == null) return;
                if(config.getBoolean("time.locked_time")) world.setTime(config.getInt("time.time"));
                if(!config.getBoolean("time.locked_time") && config.getBoolean("time.same_as_real")) {
                    ZonedDateTime nowZoned = ZonedDateTime.now();
                    Instant midnight = nowZoned.toLocalDate().atStartOfDay(nowZoned.getZone()).toInstant();
                    Duration duration = Duration.between(midnight, Instant.now());
                    long seconds = duration.getSeconds();
                    int secondsToMc = (int) (seconds / 3.6 - 6000);
                    world.setTime(secondsToMc);
                }
            }
        }, 20, 20);
    }

    public static void stopInterval() {
        Bukkit.getScheduler().cancelTask(taskid);
    }

    static String versionStr = version.substring(1); // Remove v prefix
    static int versionInt = Integer.parseInt(versionStr.split("_")[0]) * 1000 + Integer.parseInt(versionStr.split("_")[1]) * 10;

    public static int getVersion() {return versionInt;}
    public static boolean isNewerThanVersion1_9() {
        if(getVersion() >= 1090) {return true;}
        else return false;
    }

    public static IceLobby getInstance() {
        return instance;
    }







    //TODO: set the gadget slot
    //TODO: implement Vault API + Placeholder API




}