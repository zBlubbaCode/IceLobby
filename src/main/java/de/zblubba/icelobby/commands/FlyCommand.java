package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class FlyCommand implements CommandExecutor {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String alias = "§cAlias: /fly <(on | off)> <(name)>   -   () = optional";
        String flyOff = config.getString("messages.commands.fly.off"); flyOff = ChatColor.translateAlternateColorCodes('&', flyOff);
        String flyOn = config.getString("messages.commands.fly.on"); flyOn = ChatColor.translateAlternateColorCodes('&', flyOn);

        String prefix = IceLobby.getPrefix; prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        String noPerms = IceLobby.getNoPermission; noPerms = ChatColor.translateAlternateColorCodes('&', noPerms);
        String mustbePlayer = IceLobby.getmustbeplayer; mustbePlayer = ChatColor.translateAlternateColorCodes('&', mustbePlayer);

        if(sender instanceof Player p) {
            if(p.hasPermission("icelobby.fly")) {
                if(args.length == 0) {
                    if(p.getAllowFlight()) {
                        toggleFly(p, false, flyOff, flyOn, prefix);
                    } else {
                        toggleFly(p, true, flyOff, flyOn, prefix);
                    }
                } else if(args.length >= 1) {
                    if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("on")) {
                            toggleFly(p, true, flyOff, flyOn, prefix);
                        } else if(args[0].equalsIgnoreCase("off")) {
                            toggleFly(p, false, flyOff, flyOn, prefix);
                        }
                    } else if(args.length == 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            if(args[0].equalsIgnoreCase("off")) {
                                toggleFly(p, false, flyOff, flyOn, prefix);
                            } else if(args[0].equalsIgnoreCase("on")){
                                toggleFly(p, true, flyOff, flyOn, prefix);
                            } else p.sendMessage(alias);
                        } else p.sendMessage(prefix + "§cInvalid Player!");
                    } else p.sendMessage(alias);
                } else p.sendMessage(alias);
            } else p.sendMessage(noPerms);
        } else sender.sendMessage(mustbePlayer);

        return false;
    }

    public void toggleFly(Player p, boolean allowFly, String flyOff, String flyOn, String prefix) {
        if(allowFly) {
            p.setAllowFlight(true);
            p.sendMessage(prefix + flyOn);
        } else {
            p.setAllowFlight(false);
            p.sendMessage(prefix + flyOff);
        }
    }
}