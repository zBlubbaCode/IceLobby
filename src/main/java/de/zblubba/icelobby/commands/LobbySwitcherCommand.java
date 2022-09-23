package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class LobbySwitcherCommand implements CommandExecutor {

    Configuration config = IceLobby.itemConfig;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            int rows = config.getInt("lobby_switcher.rows");
            Inventory inv = Bukkit.createInventory(null, rows*9, MessageCollection.getSwitcherTitle());
            // create the switcher inventory

            //if the option is enabled, fill the hole inventory with black glass panes
            if(IceLobby.isNewerThanVersion1_9()) {
                if(config.getBoolean("lobby_switcher.filled_with_glass")) {
                    for(int i = 0; i < rows*9; i++) {
                        inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("&c").setLore("&c").build());
                    }
                }
            }

            //get the list of the worlds, which should be shown in the gui
            List<String> worlds = (List<String>) config.getList("lobby_switcher.shown_worlds");

            for(int i = 0; i < worlds.size(); i++) {
                World world = Bukkit.getWorld(worlds.get(i));
                //if the world exists
                if(world != null) {
                    //get the number of players in the world
                    int players = world.getPlayers().size();
                    //if the player is in the world, set a "you are here" lore
                    if (p.getLocation().getWorld() != world) {
                        inv.setItem(i, new ItemBuilder(Material.valueOf(config.getString("lobby_switcher.item_type"))).setName(MessageCollection.getWorldNamePrefix(i) + world.getName()).setLore("§a" + players + " online").build());
                    } else
                        inv.setItem(i, new ItemBuilder(Material.valueOf(config.getString("lobby_switcher.item_type"))).setName(MessageCollection.getWorldNamePrefix(i) + world.getName()).setLore("§8• §7You are §chere", "§a" + players + " online").build());
                }
            }

            //open the inventory for the player
            p.openInventory(inv);

        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }
}
