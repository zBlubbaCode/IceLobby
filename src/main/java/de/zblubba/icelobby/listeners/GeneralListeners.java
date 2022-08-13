package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.commands.BuildCommand;
import de.zblubba.icelobby.items.AddItemsOnJoin;
import de.zblubba.icelobby.items.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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
        if(!IceLobby.getLobbyWorlds().contains(event.getPlayer().getLocation().getWorld().getName())) return;
        if(!itemConfig.getBoolean("items.canbedropped")) {
            if(!BuildCommand.getBuildPlayers().contains(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getWhoClicked().getLocation().getWorld().getName())) return;
        if(!itemConfig.getBoolean("canbemoved")) {
            if(!BuildCommand.getBuildPlayers().contains(event.getWhoClicked())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getPlayer().getLocation().getWorld().getName())) return;
        if(!itemConfig.getBoolean("canbemoved")) {
            if(!BuildCommand.getBuildPlayers().contains(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getPlayer().getLocation().getWorld().getName())) return;
        Player p = event.getPlayer();
        if(!BuildCommand.getBuildPlayers().contains(p)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getPlayer().getLocation().getWorld().getName())) return;
        Player p = event.getPlayer();
        if(!BuildCommand.getBuildPlayers().contains(p)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(!IceLobby.getLobbyWorlds().contains(p.getLocation().getWorld().getName())) return;
            if(config.getBoolean("unlimited_food")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(!IceLobby.getLobbyWorlds().contains(p.getLocation().getWorld().getName())) return;
            if(config.getBoolean("no_damage")) {
                if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    if(p.getInventory().getItemInMainHand().getType() != Material.PUFFERFISH) {
                        event.setCancelled(false);
                    } else event.setCancelled(true);
                } else event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getWorld().getName())) return;
        if(config.getBoolean("lock_weather")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getEntity().getLocation().getWorld().getName())) return;
        if(config.getBoolean("no_explosions")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getBlock().getLocation().getWorld().getName())) return;
        if(config.getBoolean("stop_despawning_leaves")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(!IceLobby.getLobbyWorlds().contains(p.getLocation().getWorld().getName())) return;
            if(config.getBoolean("stop_pickup_items") && !BuildCommand.getBuildPlayers().contains(p)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player p = event.getPlayer();
        String[] lines = event.getLines();
        if(p.hasPermission("icelobby.admin")) {
            for(int i = 0; i <= 3; i++) {
                event.setLine(i, ChatColor.translateAlternateColorCodes('&', lines[i]));
            }
        }
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        if(!IceLobby.getLobbyWorlds().contains(event.getPlayer().getLocation().getWorld().getName())) {
            if(config.getBoolean("world_change.change_gamemode")) {
                String gamemode = config.getString("world_change.gamemode");
                event.getPlayer().setGameMode(GameMode.valueOf(gamemode));
                if(config.getBoolean("world_change.clear_hotbar")) {
                    for(int i = 0; i < 9; i++) {
                        event.getPlayer().getInventory().setItem(i, null);
                    }
                }
            }
        } else {
            event.getPlayer().setGameMode(GameMode.valueOf(config.getString("default_gamemode")));
            AddItemsOnJoin.addHotbarItems(event.getPlayer());
        }
    }
}
