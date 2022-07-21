package de.zblubba.icelobby.items;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WarpManager {

    static File configFile = new File("plugins/IceLobby", "warps.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static Location getWarp(String name) { return config.getLocation(name);}

    public static void createWarp(String name  , Location location) {
        config.set(name  , location);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteWarp(String name) {
        config.set(name  , null);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
