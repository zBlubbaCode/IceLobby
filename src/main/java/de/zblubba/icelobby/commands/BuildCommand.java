package de.zblubba.icelobby.commands;

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

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static ArrayList<Player> buildPlayers = new ArrayList<>();
    private HashMap<Player, ItemStack[]> playersBuildInventory = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("icelobby.commands.build")) {

                if(!buildPlayers.contains(p)) {
                    buildPlayers.add(p);

                    p.setGameMode(GameMode.CREATIVE);
                    playersBuildInventory.put(p, p.getInventory().getContents());
                    p.getInventory().clear();

                    p.sendMessage(MessageCollection.getBuildModeOn());
                } else {
                    buildPlayers.remove(p);

                    GameMode defaultGameMode = GameMode.valueOf(config.getString("defaults.playergamemode"));
                    p.setGameMode(defaultGameMode);
                    p.getInventory().setContents(playersBuildInventory.get(p));

                    //if(config.getBoolean("messages.commands.build.addhotbaritemsonoff")) {
                        //AddItemsOnJoin.addHotbarItems(p);
                    //}

                    p.sendMessage(MessageCollection.getBuildModeOff());
                }

            } else p.sendMessage(MessageCollection.getNoPerms());
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }

    public static ArrayList<Player> getBuildPlayers() {return buildPlayers;}
}
