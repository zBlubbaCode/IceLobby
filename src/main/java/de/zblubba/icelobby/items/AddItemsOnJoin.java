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
        //clear the player's inventory if enabled
        if(IceLobby.itemConfig.getBoolean("items.clearinvonjoin")) p.getInventory().clear();

        //method, because it's used more than 1 time
        addHotbarItems(p);
    }

    public static void addHotbarItems(Player p) {
        //if the items are enabled in the items.yml config
        if(IceLobby.itemConfig.getBoolean("items.enabled") && IceLobby.itemConfig.getBoolean("items.addonjoin")) {
            //for every slot of the player's inventory
            for(int i = 0; i < 150; i++) {
                //if the slot in the config is a item
                if(IceLobby.itemConfig.getConfigurationSection("items.hotbar." + i) != null) {
                    //if the type is not "CUSTOM_HEAD"
                    if(!MessageCollection.getHotbarItemType(i).equals("CUSTOM_HEAD")) {
                        //Create the item and add it to the inventory if enabled
                        try {
                            ItemStack item = new ItemBuilder(Material.valueOf(MessageCollection.getHotbarItemType(i))).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build();
                            Inventory inv = p.getInventory();
                            if(IceLobby.itemConfig.getBoolean("items.hotbar." + i + ".enabled")) inv.setItem(i, item);
                        } catch(Exception e) {
                            IceLobby.getInstance().getLogger().info("!!! Item does not exists! Error happend in HotbarItems");
                        }
                    } else {
                        //create a skull with a specific skullOwner and add it to the inventory if enabled
                        try {
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
                        } catch (Exception e) {
                            IceLobby.getInstance().getLogger().info("!!! Custom Heads do not exists in lower versions of minecraft. A fix will probably released soon");
                        }
                    }
                }
            }
        }
    }
}
