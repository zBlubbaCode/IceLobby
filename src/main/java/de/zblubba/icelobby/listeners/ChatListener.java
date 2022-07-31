package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.commands.GlobalMuteCommand;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setMessage(event.getPlayer().hasPermission("icelobby.chat.colored") ? ChatColor.translateAlternateColorCodes('&', event.getMessage()) : event.getMessage());

        if(GlobalMuteCommand.isMuteStateOn()) {
            if(event.getPlayer().hasPermission("icelobby.chat.bypass")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MessageCollection.getGlobalmuteRespone());
            }
        }

        String msg = event.getMessage();
        msg = msg.replace("<3", "❤");
        msg = msg.replace(":)", "☻");
        msg = msg.replace(":D", "☻");
        msg = msg.replace(":(", "☹");
        msg = msg.replace("(c)", "©");
        msg = msg.replace("->", "⇨");
        msg = msg.replace("<-", "⇦");
        event.setMessage(msg);
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

        List<String> blockedCommands = (List<String>) IceLobby.config.getList("blocked_commands");
        if(blockedCommands.contains(args[0])) {
            if(p.hasPermission("icelobby.admin")) {
                p.sendMessage(MessageCollection.getNoPerms());
                event.setCancelled(true);
            }
        }
    }
}
