package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.listeners.JoinListener;
import de.zblubba.icelobby.util.ItemBuilder;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class VisibilityCommand implements CommandExecutor {

    Plugin plugin = IceLobby.getPlugin(IceLobby.class);
    public static HashMap<Player, String> playerHider = new HashMap<>();
    public static HashMap<Player, Integer> playerLevel = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            //get the current visibility mode
            switch(playerLevel.get(p)) {

                //only VIP mode
                case 0:
                    setLevel(1, p);
                    p.sendMessage(MessageCollection.visibilityOnlyVIP());
                    //play the effect and sound for the player if enabled
                    playEffect(p);

                    playerHider.put(p, "vip");
                    //change the item in the players inventory
                    changeColorItem("purple", p);

                    //hide every player without a permission
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        if(!JoinListener.vipPlayerList.contains(players.getName())) {
                            p.hidePlayer(plugin, players);
                        }
                    }
                break;

                //none
                case 1:
                    setLevel(2, p);
                    playEffect(p);

                    playerHider.put(p, "none");
                    changeColorItem("red", p);

                    for(Player players : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(plugin, players);
                    }

                    p.sendMessage(MessageCollection.visibilityNobody());
                break;

                //all
                case 2:
                    setLevel(0, p);
                    playEffect(p);

                    playerHider.put(p, "all");
                    changeColorItem("lime", p);

                    for(Player players :  Bukkit.getOnlinePlayers()) {
                        p.showPlayer(plugin, players);
                    }
                    p.sendMessage(MessageCollection.visibilityAll());
                break;

            }

        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }

    public void playEffect(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 255));
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 255, 1);
    }

    public void changeColorItem(String color, Player p) {
        //go through the hotbar of the player
        for(int i = 1; i < 10; i++) {
            //if the item in the hotbar is the switcher item
            if(IceLobby.itemConfig.getConfigurationSection("items.hotbar." + i) == null) return;
            if(IceLobby.itemConfig.getString("items.hotbar." + i + ".type").equals("LIME_DYE")) {

                //check the input and replace the item
                switch(color) {
                    case "lime": p.getInventory().setItem(i, new ItemBuilder(Material.LIME_DYE).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build());break;
                    case "purple": p.getInventory().setItem(i, new ItemBuilder(Material.PURPLE_DYE).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build());break;
                    case "red": p.getInventory().setItem(i, new ItemBuilder(Material.RED_DYE).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build());break;
                }

            }
        }
    }

    public static void setLevel(int level, Player player) {
        getPlayerLevel().put(player, level);
    }
    public static HashMap<Player, String> getPlayerHider() {return playerHider;}
    public static HashMap<Player, Integer> getPlayerLevel() {return playerLevel;}
}
