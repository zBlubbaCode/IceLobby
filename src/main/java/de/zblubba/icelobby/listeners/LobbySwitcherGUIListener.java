package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LobbySwitcherGUIListener implements Listener {

    Configuration config = IceLobby.itemConfig;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getCurrentItem() == null) return;
        if(event.getView().getTitle().equals(MessageCollection.getSwitcherTitle())) {
            event.setCancelled(true);

            String prefix = config.getString("lobby_switcher.world_name_prefix");
            int prefixLength = prefix.length();

            String worldNameRAW = event.getCurrentItem().getItemMeta().getDisplayName();
            worldNameRAW = ChatColor.stripColor(worldNameRAW);
            String worldName = removeChars(worldNameRAW, prefixLength);

            IceLobby.getInstance().getLogger().info(worldName);

            World world = Bukkit.getWorld(worldName);
            if(world == p.getWorld()) return;
            if(world == null) return;

            p.teleport(world.getSpawnLocation());
        }
    }

    public String removeChars(String input, int amount) {
        if(input == null | input.length() == 0) return input;
        return input.substring(amount - 2);
    }

}