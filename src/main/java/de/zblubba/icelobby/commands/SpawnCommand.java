package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.items.WarpManager;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p) {
            if(args.length == 0) {
                if(IceLobby.config.getBoolean("spawn.enabled")) {
                    if(WarpManager.getWarp(IceLobby.config.getString("spawn.spawn_warp_name")) != null) {
                        p.teleport(WarpManager.getWarp(IceLobby.config.getString("spawn.spawn_warp_name")));
                        p.sendMessage(MessageCollection.teleportMessage());
                    } else p.sendMessage(MessageCollection.spawnNotSet());
                }
            }
        }
        return false;
    }
}
