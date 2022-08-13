package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class TpAllCommand implements CommandExecutor {

    public static File messagesFiles = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration configMsg = YamlConfiguration.loadConfiguration(messagesFiles);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("icelobby.commands.teleportall")) {
                //get the current location of the player
                Location loc = p.getLocation();

                //for every player, teleport him to the command sender
                for(Player players : Bukkit.getOnlinePlayers()) {
                    players.teleport(loc);
                    if(configMsg.getBoolean("commands.tpall.players.enabled")) players.sendMessage(MessageCollection.tpAllPlayersMessage());
                }

                p.sendMessage(MessageCollection.tpAllOwnMessage());
            }
        }
        return false;
    }
}
