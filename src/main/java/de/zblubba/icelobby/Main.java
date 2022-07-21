package de.zblubba.icelobby;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Main extends JavaPlugin {

    private Configuration msgConfig;

    @Override
    public void onEnable() {
        instance = this;
        msgConfig = loadYml("messages.yml");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§bProduct§8: §bGameMode");
        Bukkit.getConsoleSender().sendMessage("§bStatus: §8: §aActivate");
        Bukkit.getConsoleSender().sendMessage("§bDeveloper§8: §bEinfachDev");
        Bukkit.getConsoleSender().sendMessage("§bVersion§8: §b" + Main.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bOS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§bJava-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        register();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§bProduct§8: §bGameMode");
        Bukkit.getConsoleSender().sendMessage("§bStatus: §8: §cDeactivate");
        Bukkit.getConsoleSender().sendMessage("§bDeveloper§8: §bEinfachDev");
        Bukkit.getConsoleSender().sendMessage("§bVersion§8: §b" + Main.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bOS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§bJava-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
    }

    private void register() {

    }

    private FileConfiguration loadYml(String name) {
        File file = new File(getDataFolder(), name);
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        if(!file.exists()) {
            try (InputStream inputStream = getResource(name)) {
                Files.copy(inputStream, file.toPath());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public Configuration getMsgConfig() {
        return msgConfig;
    }

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }
}

