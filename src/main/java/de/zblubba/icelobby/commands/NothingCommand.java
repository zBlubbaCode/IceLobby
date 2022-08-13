package de.zblubba.icelobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NothingCommand implements CommandExecutor {

    /*
    This command is for GUIs and items that have no command. And on click there should not be this message "unknown command"
    This command does literally nothing and exists just for that reason
     */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}
