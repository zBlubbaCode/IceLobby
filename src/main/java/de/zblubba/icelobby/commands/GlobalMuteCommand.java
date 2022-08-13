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
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("icelobby.commands.globalmute")) {
                //if the command is just "/globalmute"
                if(args.length == 0) {
                    // if the mute mode is off
                    if(!isMuteStateOn()) {
                        //set the mute mode to on
                        setMuteStateOn(true);
                        //for every onlineplayer, send a message that the chat is closed
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(MessageCollection.getGlobalmuteBroadcastMessage());
                        }
                    } else setMuteStateOn(false);
                    //if the player uses more arguments such as "on" or "off"
                } else if(args.length >= 1)
                    if(args[0].equalsIgnoreCase("on")) {
                        setMuteStateOn(true);
                        //for every onlineplayer, send a message that the chat is closed
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(MessageCollection.getGlobalmuteBroadcastMessage());
                        }
                    } else setMuteStateOn(false);
            } else p.sendMessage(MessageCollection.getNoPerms());
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }

    public static boolean isMuteStateOn() {return muteStateOn;}
    public static void setMuteStateOn(boolean muteStateOn) {GlobalMuteCommand.muteStateOn = muteStateOn;}
}
