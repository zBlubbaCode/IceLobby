package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.io.File;

public class InteractEvent implements Listener {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

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

                if(block == launchblockMat && p.getLocation().getBlock().getType() == Material.OAK_PRESSURE_PLATE) {
                    Vector v = p.getLocation().getDirection().multiply(multiply).setY(ymutliply);
                    p.setVelocity(v);

                    if(config.getBoolean("events.launchpads.effect")) p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
                    if(config.getBoolean("events.launchpads.sound")) p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 255, 1);

                    p.setFallDistance(-999F);
                }
            }, 3);
        }
    }
}
