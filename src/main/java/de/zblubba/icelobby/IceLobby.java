package de.zblubba.icelobby;

import de.zblubba.icelobby.commands.*;
import de.zblubba.icelobby.items.AddItemsOnJoin;
import de.zblubba.icelobby.items.CompassGUI;
import de.zblubba.icelobby.items.CompassGUIListener;
import de.zblubba.icelobby.items.WarpCommand;
import de.zblubba.icelobby.listeners.*;
import de.zblubba.icelobby.util.ConfigBuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class IceLobby extends JavaPlugin {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public void onEnable() {
        instance = this;
        ConfigBuilder.checkConfigs();
        registerListeners();
        registerCommands();

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

    public static IceLobby instance;
    public static IceLobby getInstance() { return instance; }

}
//TODO: CHECK - MessageCollection add more variabled - {prefix}, {maxPlayers}, {onlineplayers}
//TODO: CHECK - rewrite commands to implement MessageCollection
//TODO: CHECK - add Compass
//TODO: CHECK - add own Player head to hotbar
//TODO: CHECK - help command
//TODO: CHECK - change unknown command message
//TODO: CHECK - interact with lobby items
//TODO: CHECK - Build mode
//TODO: only 1 world with name or all worlds option
//TODO: updater
//TODO: tablist
//TODO: besides OWN_HEAD - add PLAYERS_HEAD for other player's head
//TODO: hotbarItems can be moved + Offhand
//TODO: Placeholder API


