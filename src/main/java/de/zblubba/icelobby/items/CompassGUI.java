package de.zblubba.icelobby.items;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class CompassGUI implements CommandExecutor {

    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {

            int rows = itemConfig.getInt("compass.gui.rows");
            Inventory inv = Bukkit.createInventory(null, rows * 9, MessageCollection.getCompassGUITitle(p));



        }
        return false;
    }
}
