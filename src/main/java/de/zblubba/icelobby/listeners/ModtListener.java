package de.zblubba.icelobby.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class ModtListener implements Listener {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String motdMessageFirst = config.getString("messages.util.motd.firstLine"); motdMessageFirst = ChatColor.translateAlternateColorCodes('&', motdMessageFirst);
        String motdMessageSecond = config.getString("messages.util.motd.secondLine"); motdMessageSecond = ChatColor.translateAlternateColorCodes('&', motdMessageSecond);

        event.setMotd(motdMessageFirst + "\n" + motdMessageSecond);
    }
}
