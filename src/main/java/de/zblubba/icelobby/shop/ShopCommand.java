package de.zblubba.icelobby.shop;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ShopCommand implements CommandExecutor {

    Configuration config = IceLobby.itemConfig;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(config.getBoolean("shop.gui.enabled")) {

                int rows = config.getInt("shop.gui.rows");
                Inventory inv = Bukkit.createInventory(null, rows * 9, MessageCollection.getShopGUITitle(p));

                if(config.getBoolean("shop.gui.filled_with_glass")) {
                    for(int i = 0; i < rows * 9; i++) {
                        inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§8").build());
                    }
                }

                for(int i = 0; i < rows * 9; i++) {
                    if(config.get("shop.items." + i) != null) {
                        if(!config.getString("shop.items." + i + ".type").equals("CUSTOM_HEAD")) {
                            inv.setItem(i, new ItemBuilder(MessageCollection.getShopItemType(i)).setName(MessageCollection.getShopItemName(i)).setLore(MessageCollection.getShopItemLore(i)).setLocalizedName(MessageCollection.getShopItemAction(i)).build());
                        } else {
                            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                            SkullMeta meta = (SkullMeta) skull.getItemMeta();
                            String owner = IceLobby.itemConfig.getString("shop.items." + i + ".head_owner");
                            owner = owner.replace("%player%", p.getName());
                            OfflinePlayer ownerPlayer = Bukkit.getOfflinePlayer(owner);
                            meta.setOwningPlayer(ownerPlayer);
                            meta.setDisplayName(MessageCollection.getShopItemName(i));
                            meta.setLore(Arrays.asList(MessageCollection.getShopItemLore(i)));
                            meta.setLocalizedName(MessageCollection.getShopItemAction(i));
                            skull.setItemMeta(meta);
                            inv.setItem(i, skull);
                        }
                    }
                }
                
                p.openInventory(inv);

            }
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }
}
