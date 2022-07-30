package de.zblubba.icelobby.items;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class AddItemsOnJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(IceLobby.itemConfig.getBoolean("items.clearinvonjoin")) p.getInventory().clear();

        addHotbarItems(p);
    }

    public static void addHotbarItems(Player p) {
        if(IceLobby.itemConfig.getBoolean("items.enabled") && IceLobby.itemConfig.getBoolean("items.addonjoin")) {
            for(int i = 1; i < 10; i++) {
                if(IceLobby.itemConfig.getConfigurationSection("items.hotbar." + i) == null) return;
                if(!MessageCollection.getHotbarItemType(i).equals("OWN_HEAD")) {
                    ItemStack item = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItemType(i))).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build();
                    Inventory inv = p.getInventory();
                    if(IceLobby.itemConfig.getBoolean("items.hotbar." + i + ".enabled")) inv.setItem(IceLobby.itemConfig.getInt("items.hotbar." + i + ".slot"), item);
                } else {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwningPlayer(p);
                    meta.setDisplayName(MessageCollection.getHotbarItemName(i));
                    meta.setLore(Arrays.asList(MessageCollection.getHotbarItemLore(i)));
                    skull.setItemMeta(meta);
                    Inventory inv = p.getInventory();
                    if(IceLobby.itemConfig.getBoolean("items.hotbar." + i + ".enabled")) inv.setItem(IceLobby.itemConfig.getInt("items.hotbar." + i + ".slot"), skull);
                }
            }
        }
    }
}
