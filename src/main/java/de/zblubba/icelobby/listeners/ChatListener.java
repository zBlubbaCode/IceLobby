package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.commands.GlobalMuteCommand;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
}
