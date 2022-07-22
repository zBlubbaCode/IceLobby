package de.zblubba.icelobby.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.io.File;

public class GeneralListeners implements Listener {

    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(!itemConfig.getBoolean("items.canbedropped")) {
            if(!event.getPlayer().hasPermission("icelobby.items.drop")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory() == event.getWhoClicked().getInventory()) {
            if(!event.getWhoClicked().hasPermission("icelobby.items.move")) {
                event.setCancelled(true);
            }
        }
    }
}
