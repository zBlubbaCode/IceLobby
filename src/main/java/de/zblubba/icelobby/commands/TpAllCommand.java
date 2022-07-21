package de.zblubba.icelobby.commands;

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

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("icelobby.tpall")) {
                String ownMessage = config.getString("messages.commands.tpall.ownmessage"); ownMessage = ownMessage.replace('&', '§');
                ownMessage = ownMessage.replace("{user}", p.getName());
                String playersMessage = config.getString("messages.commands.tpall.players.message"); playersMessage = playersMessage.replace('&', '§');
                playersMessage = playersMessage.replace("{user}", p.getName());

                Location loc = p.getLocation();

                for(Player players : Bukkit.getOnlinePlayers()) {
                    players.teleport(loc);
                    if(config.getBoolean("messages.commands.tpall.players.enabled")) players.sendMessage(playersMessage);
                }

                p.sendMessage(ownMessage);
            }
        }
        return false;
    }
}
