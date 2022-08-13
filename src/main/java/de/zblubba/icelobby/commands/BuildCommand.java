package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.items.AddItemsOnJoin;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class BuildCommand implements CommandExecutor {

    public static ArrayList<Player> buildPlayers = new ArrayList<>();
    private HashMap<Player, ItemStack[]> playersBuildInventory = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // if the sender is a player
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("icelobby.commands.build")) {
                //if in the config is set that only in specific worlds, you can use the build mode
                if(IceLobby.config.getBoolean("build_only_lobby_world")) {
                    //if the player is not in one of the lobby worlds
                    if(!IceLobby.getLobbyWorlds().contains(p.getLocation().getWorld().getName())) {
                        sender.sendMessage("§cBuild Command is only available in the lobby-worlds!");
                        return false;
                    }
                }

                //if the player is not in the build mode
                if(!buildPlayers.contains(p)) {
                    buildPlayers.add(p);

                    p.setGameMode(GameMode.CREATIVE);
                    //save the inventory of the player in a HashMap
                    playersBuildInventory.put(p, p.getInventory().getContents());
                    p.getInventory().clear();

                    p.sendMessage(MessageCollection.getBuildModeOn());
                } else {
                    buildPlayers.remove(p);

                    //get the defaultGameMode set in the config
                    GameMode defaultGameMode = GameMode.valueOf(IceLobby.config.getString("default_gamemode"));
                    p.setGameMode(defaultGameMode);
                    //set the inventory of the player from the HashMap
                    p.getInventory().setContents(playersBuildInventory.get(p));

                    p.sendMessage(MessageCollection.getBuildModeOff());
                }

            } else p.sendMessage(MessageCollection.getNoPerms());
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }

    public static ArrayList<Player> getBuildPlayers() {return buildPlayers;}
}
