package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.commands.BuildCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.io.File;

public class GeneralListeners implements Listener {

    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);
    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(!itemConfig.getBoolean("items.canbedropped")) {
            if(!BuildCommand.getBuildPlayers().contains(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!itemConfig.getBoolean("canbemoved")) {
            if(!BuildCommand.getBuildPlayers().contains(event.getWhoClicked())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if(!itemConfig.getBoolean("canbemoved")) {
            if(!BuildCommand.getBuildPlayers().contains(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if(!BuildCommand.getBuildPlayers().contains(p)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(event.getEntity() instanceof Player) {
            if(config.getBoolean("defaults.unlimitedfood")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            if(config.getBoolean("defaults.nodamage")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if(config.getBoolean("defaults.lockweather")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if(config.getBoolean("defaults.noexplosions")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        if(config.getBoolean("defauls.stopdespawningleavs")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player p) {
            if(config.getBoolean("defaults.stoppickupitems") && !BuildCommand.getBuildPlayers().contains(p)) {
                event.setCancelled(true);
            }
        }
    }
}
