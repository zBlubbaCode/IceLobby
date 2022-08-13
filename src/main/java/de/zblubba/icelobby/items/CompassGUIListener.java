package de.zblubba.icelobby.items;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CompassGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getCurrentItem() == null) return;
        //if the title equals the CompassGUITitle
        if(event.getView().getTitle().equals(MessageCollection.getCompassGUITitle(p))) {
            event.setCancelled(true);
            if(IceLobby.itemConfig.get("compass.items." + event.getRawSlot()) != null) {
                //perform the command of the clicked item
                p.performCommand(MessageCollection.getCompassItemCommand(event.getRawSlot()));
            }
        }
    }
}
