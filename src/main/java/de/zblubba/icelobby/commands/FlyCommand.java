package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Messages set, because i need them more than 1 time
        String alias = "§cAlias: /fly <(on | off)> <(name)>   -   () = optional";
        String flyOff = MessageCollection.getFlyOffMsg();
        String flyOn = MessageCollection.getFlyOnMsg();

        String prefix = MessageCollection.getPrefix();
        String noPerms = MessageCollection.getNoPerms();
        String mustbePlayer = MessageCollection.mustbePlayer();

        //if the sender is a player
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("icelobby.commands.fly")) {
                //if the command is just "/fly"
                if(args.length == 0) {
                    if(p.getAllowFlight()) {
                        toggleFly(p, false, flyOff, flyOn, prefix);
                    } else {
                        toggleFly(p, true, flyOff, flyOn, prefix);
                    }
                    //the player can also add arguments such as "on" or "off"
                } else if(args.length >= 1) {
                    if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("on")) {
                            toggleFly(p, true, flyOff, flyOn, prefix);
                        } else if(args[0].equalsIgnoreCase("off")) {
                            toggleFly(p, false, flyOff, flyOn, prefix);
                        }
                        //if the player toggles the flight mode for another player
                    } else if(args.length == 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            if(args[0].equalsIgnoreCase("off")) {
                                toggleFly(p, false, flyOff, flyOn, prefix);
                            } else if(args[0].equalsIgnoreCase("on")){
                                toggleFly(p, true, flyOff, flyOn, prefix);
                            } else p.sendMessage(alias);
                        } else p.sendMessage(prefix + "§cInvalid Player!");
                        //all "alias" is a wrong syntax
                    } else p.sendMessage(alias);
                } else p.sendMessage(alias);
            } else p.sendMessage(noPerms);
        } else sender.sendMessage(mustbePlayer);

        return false;
    }

    public void toggleFly(Player p, boolean allowFly, String flyOff, String flyOn, String prefix) {
        //changes the fly mode of the player
        if(allowFly) {
            p.setAllowFlight(true);
            p.sendMessage(prefix + flyOn);
        } else {
            p.setAllowFlight(false);
            p.sendMessage(prefix + flyOff);
        }
    }
}
