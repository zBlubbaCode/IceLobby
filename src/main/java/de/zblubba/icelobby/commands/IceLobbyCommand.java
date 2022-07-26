package de.zblubba.icelobby.commands;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.listeners.JoinListener;
import de.zblubba.icelobby.util.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IceLobbyCommand implements CommandExecutor {

    String prefix = "§bIce§9Lobby §8| §7";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("icelobby.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    if (sender.hasPermission("icelobby.reload")) {
                        try {
                            long timerStart = System.currentTimeMillis();
                            sender.sendMessage(prefix + "reloading...");

                            if(!IceLobby.file.exists()) {
                                IceLobby.getInstance().saveDefaultConfig();
                            }

                            if (!IceLobby.fileItem.exists() || !IceLobby.fileMessages.exists()) {
                                IceLobby.getInstance().getLogger().info("One or more files were not found. Creating...");
                                if (!IceLobby.fileItem.exists()) {
                                    IceLobby.fileItem.getParentFile().mkdirs();
                                    IceLobby.getInstance().saveResource("items.yml", false);
                                }
                                if (!IceLobby.fileMessages.exists()) {
                                    IceLobby.fileMessages.getParentFile().mkdirs();
                                    IceLobby.getInstance().saveResource("messages.yml", false);
                                }
                            }

                            IceLobby.config.load(IceLobby.file);
                            IceLobby.messagesConfig.load(IceLobby.fileMessages);
                            IceLobby.itemConfig.load(IceLobby.fileItem);

                            IceLobby.createFiles();
                            IceLobby.loadConfigFiles();

                            JoinListener.clearActionbar();
                            JoinListener.sendActionbar();

                            IceLobby.startOneSecondInterval();
                            IceLobby.startOneSecondInterval();
                            IceLobby.setWeather();

                            if (IceLobby.config.getBoolean("scoreboard.enabled")) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    Scoreboard.setScoreboard(players);
                                }
                            }

                            long timerEnd = System.currentTimeMillis();
                            long duration = timerEnd - timerStart;

                            sender.sendMessage(prefix + "§aReload complete! Duration: " + duration + "ms");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                } else sender.sendMessage(prefix + "§cAlias: /il <reload | rl>");
            } else sender.sendMessage(prefix + "§cAlias: /il <reload | rl>");
        }
        return false;
    }
}
