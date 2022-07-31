package de.zblubba.icelobby.items;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class CompassGUI implements CommandExecutor {

    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {

            if(!itemConfig.getBoolean("compass.gui.enabled")) return false;

            int rows = itemConfig.getInt("compass.gui.rows");
            Inventory inv = Bukkit.createInventory(null, rows * 9, MessageCollection.getCompassGUITitle(p));

            if(itemConfig.getBoolean("compass.gui.filledwithglass")) {
                for(int i = 0; i < rows*9; i++) {
                    inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§8").setLore("§a").build());
                }
            }

            for(int i = 0; i < rows*9; i++) {
                if(itemConfig.get("compass.items." + i) != null) {
                    if(!Objects.equals(itemConfig.getString("compass.items." + i + ".type"), "CUSTOM_HEAD")) {
                        inv.setItem(i, new ItemBuilder(Material.valueOf(MessageCollection.getCompassItemType(i))).setName(MessageCollection.getCompassItemName(i)).setLore(MessageCollection.getCompassItemLore(i)).build());
                    } else {
                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                        SkullMeta meta = (SkullMeta) skull.getItemMeta();
                        String owner = IceLobby.itemConfig.getString("compass.items." + i + ".head_owner");
                        owner = owner.replace("%player%", p.getName());
                        OfflinePlayer ownerPlayer = Bukkit.getOfflinePlayer(owner);
                        meta.setOwningPlayer(ownerPlayer);
                        meta.setDisplayName(MessageCollection.getHotbarItemName(i));
                        meta.setLore(Arrays.asList(MessageCollection.getHotbarItemLore(i)));
                        skull.setItemMeta(meta);
                        inv.setItem(i, skull);
                    }
                }
            }

            p.openInventory(inv);

        }
        return false;
    }
}
