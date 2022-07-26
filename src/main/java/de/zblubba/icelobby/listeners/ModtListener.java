package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;


public class ModtListener implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String motdMessageFirst = IceLobby.config.getString("motd.firstLine"); motdMessageFirst = ChatColor.translateAlternateColorCodes('&', motdMessageFirst);
        String motdMessageSecond = IceLobby.config.getString("motd.secondLine"); motdMessageSecond = ChatColor.translateAlternateColorCodes('&', motdMessageSecond);

        event.setMotd(motdMessageFirst + "\n" + motdMessageSecond);
    }
}
