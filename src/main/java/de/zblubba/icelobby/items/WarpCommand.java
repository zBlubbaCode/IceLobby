package de.zblubba.icelobby.items;

import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class WarpCommand implements CommandExecutor {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
            if (!(sender instanceof Player)) {
                return false;
            }
            if (args.length == 1) {
                if (WarpManager.getWarp(args[0]) != null) {
                    p.teleport(WarpManager.getWarp(args[0]));
                    if(config.getBoolean("warp.play_sound")) p.playSound(p.getLocation(),Sound.valueOf(config.getString("warp.sound")), 255, 1);
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(MessageCollection.getWarpTeleported(args[0]));
                } else {
                    p.sendMessage(MessageCollection.getWarpNotExist(args[0]));
                }
            } else if (args.length == 2) {
                if (!p.hasPermission("icelobby.warp.manage")) {
                    p.sendMessage(MessageCollection.getNoPerms());
                    return false;
                }
                if (args[0].equalsIgnoreCase("add")) {
                    if (WarpManager.getWarp(args[1]) == null) {
                        WarpManager.createWarp(args[1], p.getLocation());
                        p.sendMessage(MessageCollection.getWarpCreated(args[1]));
                    } else {
                        p.sendMessage(MessageCollection.getWarpExists(args[1]));
                    }
                } else if (args[0].equalsIgnoreCase("delete")) {
                    if (WarpManager.getWarp(args[1]) != null) {
                        WarpManager.deleteWarp(args[1]);
                        p.sendMessage(MessageCollection.getWarpDeleted(args[1]));
                    } else {
                        p.sendMessage(MessageCollection.getWarpNotExist(args[1]));
                    }
                }
            }
        return false;
    }
}