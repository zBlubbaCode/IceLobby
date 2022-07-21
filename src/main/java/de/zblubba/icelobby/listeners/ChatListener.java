package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.commands.GlobalMuteCommand;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if(GlobalMuteCommand.isMuteStateOn()) {
            if(event.getPlayer().hasPermission("icelobby.chat.bypass")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MessageCollection.getGlobalmuteRespone());
            }
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String msg = event.getMessage();
        String[] args = msg.split(" ");
        Player p = event.getPlayer();

        if(Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
            p.sendMessage(MessageCollection.commandNotExist());
            event.setCancelled(true);
        }
    }
}
