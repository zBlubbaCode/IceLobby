package de.zblubba.icelobby.items;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
            for(int i = 0; i < 150; i++) {
                if(IceLobby.itemConfig.getConfigurationSection("items.hotbar." + i) != null) {
                    if(!MessageCollection.getHotbarItemType(i).equals("CUSTOM_HEAD")) {
                        ItemStack item = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItemType(i))).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build();
                        Inventory inv = p.getInventory();
                        if(IceLobby.itemConfig.getBoolean("items.hotbar." + i + ".enabled")) inv.setItem(i, item);
                    } else {
                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                        SkullMeta meta = (SkullMeta) skull.getItemMeta();
                        String owner = IceLobby.itemConfig.getString("items.hotbar." + i + ".head_owner");
                        owner = owner.replace("%player%", p.getName());
                        OfflinePlayer ownerPlayer = Bukkit.getOfflinePlayer(owner);
                        meta.setOwningPlayer(ownerPlayer);
                        meta.setDisplayName(MessageCollection.getHotbarItemName(i));
                        meta.setLore(Arrays.asList(MessageCollection.getHotbarItemLore(i)));
                        skull.setItemMeta(meta);
                        Inventory inv = p.getInventory();
                        if(IceLobby.itemConfig.getBoolean("items.hotbar." + i + ".enabled")) inv.setItem(i, skull);
                    }
                }
            }
        }
    }
}
