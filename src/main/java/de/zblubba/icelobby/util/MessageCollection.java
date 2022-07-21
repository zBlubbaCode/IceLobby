package de.zblubba.icelobby.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class MessageCollection {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static File spawnFile = new File("plugins/IceLobby", "spawn.yml");
    static FileConfiguration spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    //util
    public static String getPrefix() {String prefix = config.getString("messages.util.prefix"); prefix = prefix.replace('&', '§');return prefix;}
    public static String getNoPerms() {String noPerms = config.getString("messages.util.nopermission"); noPerms = noPerms.replace('&', '§');return noPerms;}
    public static String mustbePlayer() {String mustbePlayer = config.getString("messages.util.mustbeaplayertoperform"); mustbePlayer = mustbePlayer.replace('&', '§');return mustbePlayer;}
    public static String commandNotExist() {String notExist = config.getString("messages.util.commanddoesnotexist");notExist = notExist.replace("&", "§");return notExist;}

    //items
    public static String getHotbarItem1Name() {String hotbarItem1Name = itemConfig.getString("items.hotbar.1.name");hotbarItem1Name = hotbarItem1Name.replace('&', '§');return hotbarItem1Name;}
    public static String getHotbarItem1Lore() {String hotbarItem1lore = itemConfig.getString("items.hotbar.1.lore"); hotbarItem1lore = hotbarItem1lore.replace('&', '§'); return hotbarItem1lore;}
    public static String getHotbarItem1type() {String hotbarItem1Type = itemConfig.getString("items.hotbar.1.type"); hotbarItem1Type = hotbarItem1Type.replace('&', '§'); return hotbarItem1Type;}

    public static String getHotbarItem2Name() {String hotbarItem2Name = itemConfig.getString("items.hotbar.2.name");hotbarItem2Name = hotbarItem2Name.replace('&', '§');return hotbarItem2Name;}
    public static String getHotbarItem2Lore() {String hotbarItem2lore = itemConfig.getString("items.hotbar.2.lore"); hotbarItem2lore = hotbarItem2lore.replace('&', '§'); return hotbarItem2lore;}
    public static String getHotbarItem2type() {String hotbarItem2Type = itemConfig.getString("items.hotbar.2.type"); hotbarItem2Type = hotbarItem2Type.replace('&', '§'); return hotbarItem2Type;}

    public static String getHotbarItem3Name() {String hotbarItem3Name = itemConfig.getString("items.hotbar.3.name");hotbarItem3Name = hotbarItem3Name.replace('&', '§');return hotbarItem3Name;}
    public static String getHotbarItem3Lore() {String hotbarItem3lore = itemConfig.getString("items.hotbar.3.lore"); hotbarItem3lore = hotbarItem3lore.replace('&', '§'); return hotbarItem3lore;}
    public static String getHotbarItem3type() {String hotbarItem3Type = itemConfig.getString("items.hotbar.3.type"); hotbarItem3Type = hotbarItem3Type.replace('&', '§'); return hotbarItem3Type;}

    public static String getHotbarItem4Name() {String hotbarItem4Name = itemConfig.getString("items.hotbar.4.name");hotbarItem4Name = hotbarItem4Name.replace('&', '§');return hotbarItem4Name;}
    public static String getHotbarItem4Lore() {String hotbarItem4lore = itemConfig.getString("items.hotbar.4.lore"); hotbarItem4lore = hotbarItem4lore.replace('&', '§'); return hotbarItem4lore;}
    public static String getHotbarItem4type() {String hotbarItem4Type = itemConfig.getString("items.hotbar.4.type"); hotbarItem4Type = hotbarItem4Type.replace('&', '§'); return hotbarItem4Type;}

    public static String getHotbarItem5Name() {String hotbarItem5Name = itemConfig.getString("items.hotbar.5.name");hotbarItem5Name = hotbarItem5Name.replace('&', '§');return hotbarItem5Name;}
    public static String getHotbarItem5Lore() {String hotbarItem5lore = itemConfig.getString("items.hotbar.5.lore"); hotbarItem5lore = hotbarItem5lore.replace('&', '§'); return hotbarItem5lore;}
    public static String getHotbarItem5type() {String hotbarItem5Type = itemConfig.getString("items.hotbar.5.type"); hotbarItem5Type = hotbarItem5Type.replace('&', '§'); return hotbarItem5Type;}

    public static String getHotbarItem6Name() {String hotbarItem6Name = itemConfig.getString("items.hotbar.6.name");hotbarItem6Name = hotbarItem6Name.replace('&', '§');return hotbarItem6Name;}
    public static String getHotbarItem6Lore() {String hotbarItem6lore = itemConfig.getString("items.hotbar.6.lore"); hotbarItem6lore = hotbarItem6lore.replace('&', '§'); return hotbarItem6lore;}
    public static String getHotbarItem6type() {String hotbarItem6Type = itemConfig.getString("items.hotbar.6.type"); hotbarItem6Type = hotbarItem6Type.replace('&', '§'); return hotbarItem6Type;}

    public static String getHotbarItem7Name() {String hotbarItem7Name = itemConfig.getString("items.hotbar.7.name");hotbarItem7Name = hotbarItem7Name.replace('&', '§');return hotbarItem7Name;}
    public static String getHotbarItem7Lore() {String hotbarItem7lore = itemConfig.getString("items.hotbar.7.lore"); hotbarItem7lore = hotbarItem7lore.replace('&', '§'); return hotbarItem7lore;}
    public static String getHotbarItem7type() {String hotbarItem7Type = itemConfig.getString("items.hotbar.7.type"); hotbarItem7Type = hotbarItem7Type.replace('&', '§'); return hotbarItem7Type;}

    public static String getHotbarItem8Name() {String hotbarItem8Name = itemConfig.getString("items.hotbar.8.name");hotbarItem8Name = hotbarItem8Name.replace('&', '§');return hotbarItem8Name;}
    public static String getHotbarItem8Lore() {String hotbarItem8lore = itemConfig.getString("items.hotbar.8.lore"); hotbarItem8lore = hotbarItem8lore.replace('&', '§'); return hotbarItem8lore;}
    public static String getHotbarItem8type() {String hotbarItem8Type = itemConfig.getString("items.hotbar.8.type"); hotbarItem8Type = hotbarItem8Type.replace('&', '§'); return hotbarItem8Type;}

    public static String getHotbarItem9Name() {String hotbarItem9Name = itemConfig.getString("items.hotbar.9.name");hotbarItem9Name = hotbarItem9Name.replace('&', '§');return hotbarItem9Name;}
    public static String getHotbarItem9Lore() {String hotbarItem9lore = itemConfig.getString("items.hotbar.9.lore"); hotbarItem9lore = hotbarItem9lore.replace('&', '§'); return hotbarItem9lore;}
    public static String getHotbarItem9type() {String hotbarItem9Type = itemConfig.getString("items.hotbar.9.type"); hotbarItem9Type = hotbarItem9Type.replace('&', '§'); return hotbarItem9Type;}

    //compass gui
    public static String getCompassGUITitle(Player p) {String compassTitle = itemConfig.getString("compass.gui.title"); compassTitle = compassTitle.replace('&', '§');compassTitle = compassTitle.replace("{user}", p.getName());return compassTitle;}

    //commands

    //globalmute
    public static String getGlobalmuteBroadcastMessage() {String broadcastMessage = config.getString("messages.commands.globalmute.broadcastmessage");broadcastMessage = broadcastMessage.replace('&', '§');return broadcastMessage;}
    public static String getGlobalmuteRespone() {String respone = config.getString("messages.commands.globalmute.responseifclosed");respone = respone.replace('&', '§');return respone;}
    //warpcommand
    public static String getWarpTeleported(String warpName) {String warpTeleport = config.getString("messages.commands.warpcommand.teleported");warpTeleport = warpTeleport.replace('&', '§'); warpTeleport = warpTeleport.replace("{warpname}", warpName);return warpTeleport;}
    public static String getWarpCreated(String warpName) {String warpCreated = config.getString("messages.commands.warpcommand.created"); warpCreated = warpCreated.replace('&', '§');warpCreated = warpCreated.replace("{warpname}", warpName);return warpCreated;}
    public static String getWarpDeleted(String warpName) {String warpDeleted = config.getString("messages.commands.warpcommand.deleted"); warpDeleted = warpDeleted.replace('&', '§');warpDeleted = warpDeleted.replace("{warpname}", warpName);return warpDeleted;}
    public static String getWarpExists(String warpName) {String warpExists = config.getString("messages.commands.warpcommand.exists"); warpExists = warpExists.replace('&', '§');warpExists = warpExists.replace("{warpname}", warpName);return warpExists;}






    public static String replaceWithVariables(String input) {
        String prefix = getPrefix();
        int onlinePlayersInt = Bukkit.getServer().getOnlinePlayers().size();String onlinePlayers = String.valueOf(onlinePlayersInt);
        int maxPlayersInt = Bukkit.getMaxPlayers();String maxPlayers = String.valueOf(maxPlayersInt);

        input = input.replace("&", "§");
        input = input.replace("{prefix}", prefix);
        input = input.replace("{onlineplayers}", onlinePlayers);
        input = input.replace("{maxplayers", maxPlayers);

        return input;
    }
}
