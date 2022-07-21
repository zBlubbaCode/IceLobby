package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String noPerms = IceLobby.getNoPermission; noPerms = noPerms.replace("&", "§");
        int amount = 50;

        if(sender.hasPermission("icelobby.chat.clear")) {
            //I use for every players, because broadcastmessage might produce errors
            for(Player players : Bukkit.getOnlinePlayers()) {
                for(int i = 0; i <= amount; i++) {
                    players.sendMessage("§b");
                }
            }
        } else {
            sender.sendMessage(noPerms);
        }
        return false;
    }
}
