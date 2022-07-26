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

    int level = 0;
    Plugin plugin = IceLobby.getPlugin(IceLobby.class);
    public static HashMap<Player, String> playerHider = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {

            switch(level) {
                //only VIP
                case 0 -> {
                    setLevel(1);
                    p.sendMessage(MessageCollection.visibilityOnlyVIP());
                    playEffect(p);

                    playerHider.put(p, "vip");
                    changeColorItem("purple", p);

                    for(Player players : Bukkit.getOnlinePlayers()) {
                        if(!JoinListener.vipPlayerList.contains(players.getName())) {
                            p.hidePlayer(plugin, players);
                        }
                    }
                }

                //none
                case 1 -> {
                    setLevel(2);
                    playEffect(p);

                    playerHider.put(p, "none");
                    changeColorItem("red", p);

                    for(Player players : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(plugin, players);
                    }

                    p.sendMessage(MessageCollection.visibilityNobody());
                }

                //all
                case 2 -> {
                    setLevel(0);
                    playEffect(p);

                    playerHider.put(p, "all");
                    changeColorItem("lime", p);

                    for(Player players :  Bukkit.getOnlinePlayers()) {
                        p.showPlayer(plugin, players);
                    }
                    p.sendMessage(MessageCollection.visibilityAll());
                }

            }

        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }

    public void playEffect(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 255));
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 255, 1);
    }

    public void changeColorItem(String color, Player p) {
        for(int i = 1; i < 10; i++) {
            if(IceLobby.itemConfig.getString("items.hotbar." + i + ".type").equals("LIME_DYE")) {

                switch(color) {
                    case "lime" -> p.getInventory().setItem(IceLobby.itemConfig.getInt("items.hotbar." + i + ".slot"), new ItemBuilder(Material.LIME_DYE).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build());
                    case "purple" -> p.getInventory().setItem(IceLobby.itemConfig.getInt("items.hotbar." + i + ".slot"), new ItemBuilder(Material.PURPLE_DYE).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build());
                    case "red" -> p.getInventory().setItem(IceLobby.itemConfig.getInt("items.hotbar." + i + ".slot"), new ItemBuilder(Material.RED_DYE).setName(MessageCollection.getHotbarItemName(i)).setLore(MessageCollection.getHotbarItemLore(i)).build());
                }

            }
        }
    }

    public void setLevel(int level) {this.level = level;}
    public static HashMap<Player, String> getPlayerHider() {return playerHider;}
}
