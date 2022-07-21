package de.zblubba.icelobby.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.io.File;
import java.util.ArrayList;

public class Scoreboard {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static void setScoreboard(Player p) {
        String title = config.getString("scoreboard.title"); title = title.replace("&", "§"); title = title.replace("{user}", p.getName());
        ArrayList<String> scores = (ArrayList<String>) config.getList("scoreboard.scores");

        org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("IceLobby", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.setDisplayName(title);

        for(int i = 0; i < scores.size(); i++) {
            String score = scores.get(scores.size() - i - 1); score = score.replace("&", "§"); score = score.replace("{user}", p.getName());
            obj.getScore(score).setScore(i);
        }

        p.setScoreboard(board);
    }
}
