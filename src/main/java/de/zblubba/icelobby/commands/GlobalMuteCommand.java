package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalMuteCommand implements CommandExecutor {

    public static boolean muteStateOn = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("icelobby.globalmute")) {
                setMuteStateOn(true);
                for(Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(MessageCollection.getGlobalmuteBroadcastMessage());
                }
            } else p.sendMessage(MessageCollection.getNoPerms());
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }

    public static boolean isMuteStateOn() {return muteStateOn;}
    public static void setMuteStateOn(boolean muteStateOn) {GlobalMuteCommand.muteStateOn = muteStateOn;}
}
