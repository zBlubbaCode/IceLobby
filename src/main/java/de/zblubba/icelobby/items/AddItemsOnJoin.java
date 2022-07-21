package de.zblubba.icelobby.items;

import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class AddItemsOnJoin implements Listener {

    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(itemConfig.getBoolean("items.clearinvonjoin")) p.getInventory().clear();

        if(itemConfig.getBoolean("items.enabled") && itemConfig.getBoolean("items.addonjoin")) {

            ItemStack item1 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem1type())).setName(MessageCollection.getHotbarItem1Name()).setLore(MessageCollection.getHotbarItem1Lore()).build();
            ItemStack item2 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem2type())).setName(MessageCollection.getHotbarItem2Name()).setLore(MessageCollection.getHotbarItem2Lore()).build();
            ItemStack item3 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem3type())).setName(MessageCollection.getHotbarItem3Name()).setLore(MessageCollection.getHotbarItem3Lore()).build();
            ItemStack item4 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem4type())).setName(MessageCollection.getHotbarItem4Name()).setLore(MessageCollection.getHotbarItem4Lore()).build();
            ItemStack item5 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem5type())).setName(MessageCollection.getHotbarItem5Name()).setLore(MessageCollection.getHotbarItem5Lore()).build();
            ItemStack item6 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem6type())).setName(MessageCollection.getHotbarItem6Name()).setLore(MessageCollection.getHotbarItem6Lore()).build();
            ItemStack item7 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem7type())).setName(MessageCollection.getHotbarItem7Name()).setLore(MessageCollection.getHotbarItem7Lore()).build();
            ItemStack item8 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem8type())).setName(MessageCollection.getHotbarItem8Name()).setLore(MessageCollection.getHotbarItem8Lore()).build();
            ItemStack item9 = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItem9type())).setName(MessageCollection.getHotbarItem9Name()).setLore(MessageCollection.getHotbarItem9Lore()).build();

            Inventory inv = p.getInventory();
            if(itemConfig.getBoolean("items.hotbar.1.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.1.slot"), item1);
            if(itemConfig.getBoolean("items.hotbar.2.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.2.slot"), item2);
            if(itemConfig.getBoolean("items.hotbar.3.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.3.slot"), item3);
            if(itemConfig.getBoolean("items.hotbar.4.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.4.slot"), item4);
            if(itemConfig.getBoolean("items.hotbar.5.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.5.slot"), item5);
            if(itemConfig.getBoolean("items.hotbar.6.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.6.slot"), item6);
            if(itemConfig.getBoolean("items.hotbar.7.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.7.slot"), item7);
            if(itemConfig.getBoolean("items.hotbar.8.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.8.slot"), item8);
            if(itemConfig.getBoolean("items.hotbar.9.enabled")) inv.setItem(itemConfig.getInt("items.hotbar.9.slot"), item9);
        }

    }
}
