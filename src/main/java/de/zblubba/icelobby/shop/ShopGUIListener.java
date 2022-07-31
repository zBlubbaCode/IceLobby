package de.zblubba.icelobby.shop;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ShopGUIListener implements Listener {

    Configuration config = IceLobby.itemConfig;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getCurrentItem() == null) return;
        if(event.getView().getTitle().equals(MessageCollection.getShopGUITitle(p))) {
            event.setCancelled(true);

            if(config.get("shop.items." + event.getRawSlot()) != null) {
                String action = event.getCurrentItem().getItemMeta().getLocalizedName();
                String path = "shop.action." + action + ".get_item";
                int price = config.getInt(path + ".price");

                if(!IceLobby.moneyConfig.getBoolean(p.getUniqueId() + "." + action)) {
                    if(EconomySystem.hasEnoughMoney(p, price)) {
                        EconomySystem.removeMoney(p, price);
                        IceLobby.moneyConfig.set(p.getUniqueId() + "." + action, true);
                        IceLobby.saveFile(IceLobby.fileMoney, IceLobby.moneyConfig);
                    } else {
                        p.sendMessage(MessageCollection.notEnoughCoins());
                        return;
                    }
                }

                if (config.getBoolean(path + ".enabled")) {
                    if (!config.getString(path + ".type").equals("CUSTOM_HEAD")) {
                        ItemStack item = new ItemStack(Material.valueOf(config.getString(path + ".type")));

                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(MessageCollection.getActionItemName(action));
                        meta.setLore(Arrays.asList(MessageCollection.getActionItemLore(action)));
                        item.setItemMeta(meta);

                        p.getInventory().setItem(config.getInt(path + ".slot"), item);
                    } else {
                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                        SkullMeta meta = (SkullMeta) skull.getItemMeta();
                        String owner = config.getString(path + ".head_owner");
                        owner = owner.replace("%player%", p.getName());
                        OfflinePlayer ownerPlayer = Bukkit.getOfflinePlayer(owner);
                        meta.setOwningPlayer(ownerPlayer);
                        meta.setDisplayName(MessageCollection.getActionItemName(action));
                        meta.setLore(Arrays.asList(MessageCollection.getActionItemLore(action)));
                        skull.setItemMeta(meta);
                        p.getInventory().setItem(config.getInt(path + ".slot"), skull);
                    }
                }
                if (config.getString("shop.action." + action + ".perform_command") != null) {
                    p.performCommand(config.getString("shop.action." + action + ".perform_command"));
                }

            }
        }
    }

}
