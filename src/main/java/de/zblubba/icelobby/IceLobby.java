package de.zblubba.icelobby;

import de.zblubba.icelobby.commands.*;
import de.zblubba.icelobby.items.AddItemsOnJoin;
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

    public static String getPrefix = config.getString("messages.util.prefix");
    public static String getNoPermission = config.getString("messages.util.nopermission");
    public static String getmustbeplayer = config.getString("messages.util.mustbeaplayertoperform");

    @Override
    public void onEnable() {
        ConfigBuilder.checkConfigs();
        registerListeners();
        registerCommands();

        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§bProduct§8: §bIce§9Lobby");
        Bukkit.getConsoleSender().sendMessage("§bStatus: §8: §aActive");
        Bukkit.getConsoleSender().sendMessage("§bDeveloper§8: §bzBlubba");
        Bukkit.getConsoleSender().sendMessage("§bVersion§8: §b" + Main.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bOS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§bJava-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§bProduct§8: §bIce§9Lobby");
        Bukkit.getConsoleSender().sendMessage("§bStatus: §8: §cDeactivated");
        Bukkit.getConsoleSender().sendMessage("§bDeveloper§8: §bzBlubba");
        Bukkit.getConsoleSender().sendMessage("§bVersion§8: §b" + Main.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bOS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§bJava-Version§8: §b" + System.getProperty("java.version"));
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
    }
    public void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("tpall").setExecutor(new TpAllCommand());
        getCommand("globalmute").setExecutor(new GlobalMuteCommand());
        getCommand("help").setExecutor(new HelpCommand());
    }

}
//TODO: MessageCollection add more variabled - {prefix}, {maxPlayers}, {onlineplayers}
//TODO: rewrite commands to implement MessageCollection
//TODO: add Compass
//TODO: add own Player head to hotbar
//TODO: help command
//TODO: change unknown command message

