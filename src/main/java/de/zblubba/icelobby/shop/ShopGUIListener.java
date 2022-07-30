package de.zblubba.icelobby.shop;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
                if(EconomySystem.hasEnoughMoney(p, price)) {
                    EconomySystem.removeMoney(p, price);
                    IceLobby.saveFile(IceLobby.fileMoney, IceLobby.moneyConfig);
                    IceLobby.getInstance().getLogger().info(price + "");
                    if (config.getBoolean(path + ".enabled")) {
                        ItemStack item = new ItemStack(Material.valueOf(config.getString(path + ".type")));
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(MessageCollection.getActionItemName(action));
                        meta.setLore(Arrays.asList(MessageCollection.getActionItemLore(action)));
                        item.setItemMeta(meta);
                        p.getInventory().setItem(config.getInt(path + ".slot"), item);
                    }
                    if (config.getString("shop.action." + action + ".perform_command") != null) {
                        p.performCommand(config.getString("shop.action." + action + ".perform_command"));
                    }
                } else p.sendMessage(MessageCollection.notEnoughCoins());
            }
        }
    }

}
