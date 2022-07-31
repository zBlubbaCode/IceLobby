package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(MessageCollection.getHelpList());
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("friends")) {

                sender.sendMessage("§8----------=======----------");
                sender.sendMessage("§7A §9Bungeecord §7Friend-System is currently");
                sender.sendMessage("§7in progress. Make shure you check out my spigot side :D");
                sender.sendMessage("§8----------=======----------");

            } else sender.sendMessage(MessageCollection.getHelpList());
        }
        return false;
    }
}
