package de.zblubba.icelobby.items;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;

public class CompassGUIListener implements Listener {

    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getCurrentItem() == null) return;
        if(event.getView().getTitle().equals(MessageCollection.getCompassGUITitle(p))) {
            event.setCancelled(true);
            if(itemConfig.get("compass.items." + event.getRawSlot()) != null) {
                p.performCommand(MessageCollection.getCompassItemCommand(event.getRawSlot()));
            }
        }
    }
}
