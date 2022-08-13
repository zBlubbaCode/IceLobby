package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RemovecosmeticsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            //get the inventory of the player and remove the armor-slots and the gadget slot
            Inventory inv = p.getInventory();
            inv.setItem(3, null);
            inv.setItem(39, null);
            inv.setItem(38, null);
            inv.setItem(37, null);
            inv.setItem(36, null);

        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }
}
