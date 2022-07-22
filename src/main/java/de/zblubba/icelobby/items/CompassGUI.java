package de.zblubba.icelobby.items;

import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.Arrays;

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
                    inv.setItem(i, new ItemBuilder(Material.valueOf(MessageCollection.getCompassItemType(i))).setName(MessageCollection.getCompassItemName(i)).setLore(MessageCollection.getCompassItemLore(i)).build());
                }
            }

            p.openInventory(inv);

        }
        return false;
    }
}
