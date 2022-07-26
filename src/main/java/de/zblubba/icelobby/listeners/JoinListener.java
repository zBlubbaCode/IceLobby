package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.commands.VisibilityCommand;
import de.zblubba.icelobby.items.WarpManager;
import de.zblubba.icelobby.util.MessageCollection;
import de.zblubba.icelobby.util.Scoreboard;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class JoinListener implements Listener {

    static int taskid;

    public static ArrayList<String> allPlayerList = new ArrayList<>();
    public static ArrayList<String> vipPlayerList = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        allPlayerList.add(p.getName());
        if(p.hasPermission("icelobby.vip")) vipPlayerList.add(p.getName());

        sendActionbar();

        if(IceLobby.config.getBoolean("join.enabled")) {event.setJoinMessage(MessageCollection.joinMessage(p));}

        GameMode defaultGameMode = GameMode.valueOf(IceLobby.config.getString("default_gamemode"));
        p.setGameMode(defaultGameMode);

        p.sendTitle(MessageCollection.title(p), MessageCollection.subtitle(p));
        if(IceLobby.config.getBoolean("scoreboard.enabled")) {Scoreboard.setScoreboard(p);}
        if(IceLobby.config.getBoolean("heal_on_join")) p.setHealth(20);p.setFoodLevel(20);
        if(IceLobby.config.getBoolean("send_welcome_message")) p.sendMessage(IceLobby.messagesConfig.getString("join.welcome_message"));

        if(IceLobby.config.getBoolean("spawn.enabled") && IceLobby.config.getBoolean("join.teleport_on_join")) {
            String spawnWarpName = IceLobby.config.getString("spawn.spawn_warp_name");
            if(WarpManager.getWarp(spawnWarpName) != null) {
                p.teleport(WarpManager.getWarp(IceLobby.config.getString("spawn.spawn_warp_name")));
            } else p.sendMessage(MessageCollection.spawnNotSet());
        }


        if(IceLobby.config.getBoolean("join.playsound")) {
            Bukkit.getScheduler().runTaskLater(IceLobby.getPlugin(IceLobby.class), () -> {
                p.playSound(p.getLocation(), Sound.valueOf(IceLobby.config.getString("join.sound")), 255, 1);
            }, 40);
        }

        for(int i = 0; i < VisibilityCommand.getPlayerHider().size(); i++) {
            Player player = (Player) VisibilityCommand.playerHider.keySet().toArray()[0];
            String type = VisibilityCommand.getPlayerHider().get(player);

            switch(type) {
                case "vip" -> {
                    if(!p.hasPermission("icelobby.vip")) {
                        player.hidePlayer(IceLobby.getPlugin(IceLobby.class), p);
                    }
                }
                case "none" -> player.hidePlayer(IceLobby.getPlugin(IceLobby.class), p);
            }
        }
    }

    public static void sendActionbar() {
        if(IceLobby.messagesConfig.getBoolean("actionbar.enabled")) {

            taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(IceLobby.getPlugin(IceLobby.class), () -> {
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(IceLobby.getLobbyWorlds().contains(players.getLocation().getWorld().getName())) {
                        players.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(MessageCollection.actionbarMessage(players)));
                    }
                }
            }, 0, 40);
        }
    }

    public static void clearActionbar() {
        Bukkit.getScheduler().cancelTask(taskid);
    }
}
