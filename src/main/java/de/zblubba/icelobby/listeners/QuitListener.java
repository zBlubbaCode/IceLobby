package de.zblubba.icelobby.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class QuitListener implements Listener {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        String quitMessage = config.getString("messages.events.quit"); quitMessage = ChatColor.translateAlternateColorCodes('&', quitMessage);
        quitMessage = quitMessage.replace("{user}", p.getName());

        if(config.getBoolean("messages.events.quit.enabled")) {
            event.setQuitMessage(quitMessage);
        }
    }
}
