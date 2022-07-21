package de.zblubba.icelobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SpawnCommand implements CommandExecutor {

    public static File configFile = new File("plugins/IceLobby", "spawn.yml");
    static FileConfiguration spawnConfig = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String spawnNotSet = spawnConfig.getString("spawnnotset"); spawnNotSet =  spawnNotSet.replace("&", "§");
        String spawnDisabled = spawnConfig.getString("spawndisabled"); spawnDisabled = spawnDisabled.replace("&", "§");
        String teleportMessage = spawnConfig.getString("teleportmessage"); teleportMessage = teleportMessage.replace('&', '§');

        if(sender instanceof Player p) {
            if(args.length == 0) {
                if(spawnConfig.getBoolean("enabled")) {
                    if(spawnConfig.getString("spawn.world") != null) {

                        World world = Bukkit.getWorld(spawnConfig.getString("spawn.world"));
                        double x = spawnConfig.getDouble("spawn.x");
                        double y = spawnConfig.getDouble("spawn.y");
                        double z = spawnConfig.getDouble("spawn.z");
                        float yaw = (float) spawnConfig.getDouble("spawn.yaw");
                        float pitch = (float) spawnConfig.getDouble("spawn.pitch");
                        p.teleport(new Location(world, x, y, z, yaw, pitch));
                        p.sendMessage(teleportMessage);

                    } else p.sendMessage(spawnNotSet);
                } else p.sendMessage(spawnDisabled);
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("set")) {
                    if(sender.hasPermission("icelobby.setspawn")) {
                        Location loc = p.getLocation();

                        spawnConfig.set("spawn.world", loc.getWorld().getName());
                        spawnConfig.set("spawn.x", loc.getX());
                        spawnConfig.set("spawn.y", loc.getY());
                        spawnConfig.set("spawn.z", loc.getZ());
                        spawnConfig.set("spawn.yaw", loc.getYaw());
                        spawnConfig.set("spawn.pitch", loc.getPitch());

                        try {
                            spawnConfig.save(configFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        p.sendMessage("§cSpawn §7was §asuccessfully §7set!");
                    }
                }
            }
        }
        return false;
    }
}
