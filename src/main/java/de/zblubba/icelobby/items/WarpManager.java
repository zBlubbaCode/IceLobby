package de.zblubba.icelobby.items;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpManager {

    public static Location getWarp(String name) { return IceLobby.warpConfig.getLocation(name);}

    static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static void createWarp(String name  , Location location) {
        IceLobby.warpConfig.set(name  , location);
        IceLobby.saveFile(configFile, config);
    }

    public static void deleteWarp(String name) {
        IceLobby.warpConfig.set(name  , null);
        IceLobby.saveFile(configFile, config);
    }
}
