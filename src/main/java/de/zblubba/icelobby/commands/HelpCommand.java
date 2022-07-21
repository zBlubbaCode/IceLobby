package de.zblubba.icelobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;

public class HelpCommand implements CommandExecutor {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String message = "";
        ArrayList<String> helpLineList = (ArrayList<String>) config.getList("messages.commands.help");
        for(int i = 0; i < helpLineList.size(); i++) {
            message = message + "\n" + helpLineList.get(i);
            message = message.replace('&', '§');
        }
        sender.sendMessage(message.toString());

        return false;
    }
}
