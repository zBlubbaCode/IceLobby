package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.Scoreboard;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class JoinListener implements Listener {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static File spawnFile = new File("plugins/IceLobby", "spawn.yml");
    static FileConfiguration spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        String joinMessage = config.getString("messages.events.join.message"); joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);
        joinMessage = joinMessage.replace("{user}", p.getName());
        String title = config.getString("messages.events.join.title"); title = ChatColor.translateAlternateColorCodes('&', title);
        title = title.replace("{user}", p.getName());
        String subtitle = config.getString("messages.events.join.subtitle"); subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        subtitle = subtitle.replace("{user}", p.getName());

        if(config.getBoolean("messages.events.join.enabled")) {event.setJoinMessage(joinMessage);}

        GameMode defaultGameMode = GameMode.valueOf(config.getString("defaults.playergamemode"));
        p.setGameMode(defaultGameMode);

        p.sendTitle(title, subtitle);
        if(config.getBoolean("scoreboard.enabled")) {Scoreboard.setScoreboard(p);}

        if(spawnConfig.getBoolean("teleportonjoin") && spawnConfig.getString("spawn.world") != null && spawnConfig.getBoolean("enabled")) {
            World world = Bukkit.getWorld(spawnConfig.getString("spawn.world"));
            double x = spawnConfig.getDouble("spawn.x");
            double y = spawnConfig.getDouble("spawn.y");
            double z = spawnConfig.getDouble("spawn.z");
            float yaw = (float) spawnConfig.getDouble("spawn.yaw");
            float pitch = (float) spawnConfig.getDouble("spawn.pitch");
            p.teleport(new Location(world, x, y, z, yaw, pitch));
        }

        if(config.getBoolean("messages.util.actionbar.enabled")) {
            String message = config.getString("messages.util.actionbar.message"); message = message.replace("&", "§");
            final String finalMessage = message.replace("{user}", p.getName());

            Bukkit.getScheduler().scheduleSyncRepeatingTask(IceLobby.getPlugin(IceLobby.class), () -> {
                for(Player players : Bukkit.getOnlinePlayers()) {
                    players.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(finalMessage));
                }
            }, 0, 40);
        }

        if(config.getBoolean("messages.events.join.playsound")) {
            Bukkit.getScheduler().runTaskLater(IceLobby.getPlugin(IceLobby.class), () -> {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 255, 1);
            }, 40);
        }
    }
}
