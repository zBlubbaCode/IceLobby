package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.Objects;

public class InteractEvent implements Listener {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    @EventHandler
    public void onEntityInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if(config.getBoolean("events.launchpads.enabled")) {
            Bukkit.getScheduler().runTaskLater(IceLobby.getPlugin(IceLobby.class), () -> {
                Material block = p.getLocation().subtract(0D, 2D, 0D).getBlock().getType();
                String launchblock = config.getString("events.launchpads.block");
                Material launchblockMat = Material.valueOf(launchblock);
                double multiply = config.getDouble("events.launchpads.multiply");
                double ymutliply = config.getDouble("events.launchpads.ymultiply");

                if(block == launchblockMat && (p.getLocation().getBlock().getType() == Material.OAK_PRESSURE_PLATE || p.getLocation().getBlock().getType() == Material.STONE_PRESSURE_PLATE)) {
                    Vector v = p.getLocation().getDirection().multiply(multiply).setY(ymutliply);
                    p.setVelocity(v);

                    if(config.getBoolean("events.launchpads.effect")) p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
                    if(config.getBoolean("events.launchpads.sound")) p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 255, 1);

                    p.setFallDistance(-999F);
                }
            }, 3);
        }

        int slot = p.getInventory().getHeldItemSlot() + 1;
        if(itemConfig.get("items.hotbar." + slot) != null) {
            if(p.getInventory().getItemInMainHand() == null) return;
            if(!itemConfig.getBoolean("items.hotbar." + slot + ".enabled")) return;
            if(itemConfig.getInt("items.hotbar." + slot + ".slot") == p.getInventory().getHeldItemSlot()) {
                String itemName = itemConfig.getString("items.hotbar." + slot + ".name");
                String[] itemNameSplitted = itemName.split("&");
                for(int i = 0; i < itemNameSplitted.length; i++) {
                    itemNameSplitted[i] = removeFirstChar(itemNameSplitted[i]);
                }
                String itemNameTogether = "";
                for(int i = 0; i < itemNameSplitted.length; i++) {
                    itemNameTogether = itemNameTogether + itemNameSplitted[i];
                }
                String itemNameInHand;
                if(p.getInventory().getItemInMainHand().hasItemMeta()) {
                    itemNameInHand = ChatColor.stripColor(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                } else return;

                if(itemNameTogether.equals(itemNameInHand)) {
                    p.performCommand(itemConfig.getString("items.hotbar." + slot + ".command"));
                    event.setCancelled(true);
                }
            }
        }
    }

    public String removeFirstChar(String input) {
        if(input == null | input.length() == 0) return input;
        return input.substring(1);
    }
}
