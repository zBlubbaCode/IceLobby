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
    public static String getPrefix() {String prefix = config.getString("messages.util.prefix"); prefix = replaceWithVariables(prefix);return prefix;}
    public static String getNoPerms() {String noPerms = config.getString("messages.util.nopermission"); noPerms = replaceWithVariables(noPerms);return noPerms;}
    public static String mustbePlayer() {String mustbePlayer = config.getString("messages.util.must_be_a_player_to_perform"); mustbePlayer = replaceWithVariables(mustbePlayer);return mustbePlayer;}
    public static String commandNotExist() {String notExist = config.getString("messages.util.command_does_not_exist");notExist = replaceWithVariables(notExist);return notExist;}
    public static String visibilityOnlyVIP() {String onlyVIP = config.getString("messages.util.visibility.onlyvip"); onlyVIP = replaceWithVariables(onlyVIP); return onlyVIP;}
    public static String visibilityNobody() {String nobody = config.getString("messages.util.visibility.nobody"); nobody = replaceWithVariables(nobody); return nobody;}
    public static String visibilityAll() {String all = config.getString("messages.util.visibility.all"); all = replaceWithVariables(all); return all;}

    //add items on Join
    public static String getHotbarItemName(int number) {
        String itemName = itemConfig.getString("items.hotbar." + number + ".name");
        itemName = replaceWithVariables(itemName);
        return itemName;
    }
    public static String getHotbarItemLore(int number) {
        String itemLore = itemConfig.getString("items.hotbar." + number + ".lore");
        itemLore = replaceWithVariables(itemLore);
        return itemLore;
    }
    public static String getHotbarItemType(int number) {
        String itemType = itemConfig.getString("items.hotbar." + number + ".type");
        itemType = replaceWithVariables(itemType);
        return itemType;
    }

    //spawn

    public static String spawnNotSet() {String noSpawn = spawnConfig.getString("settings.spawnnotset");noSpawn = replaceWithVariables(noSpawn);return noSpawn;}
    public static String spawnDisabled() {String spawnDisabled = spawnConfig.getString("settings.spawndisabled"); spawnDisabled = replaceWithVariables(spawnDisabled); return spawnDisabled;}
    public static String teleportMessage() {String tpMsg = spawnConfig.getString("settings.teleportmessage"); tpMsg = replaceWithVariables(tpMsg); return tpMsg;}

    //compass gui
    public static String getCompassGUITitle(Player p) {String compassTitle = itemConfig.getString("compass.gui.title"); compassTitle = compassTitle.replace('&', '§');compassTitle = compassTitle.replace("{user}", p.getName());return compassTitle;}
    public static String getCompassItemType(int number) {
        String type = itemConfig.getString("compass.items." + number + ".type");
        return type;
    }
    public static String getCompassItemName(int number) {
        String name = itemConfig.getString("compass.items." + number + ".name");
        name = replaceWithVariables(name);
        return name;
    }
    public static String getCompassItemLore(int number) {
        String lore = itemConfig.getString("compass.items." + number + ".lore");
        lore = replaceWithVariables(lore);
        return lore;
    }
    public static String getCompassItemCommand(int number) {
        String command = itemConfig.getString("compass.items." + number + ".command");
        return command;
    }

    //commands

    //globalmute
    public static String getGlobalmuteBroadcastMessage() {String broadcastMessage = config.getString("messages.commands.globalmute.broadcast_message");broadcastMessage = replaceWithVariables(broadcastMessage);return broadcastMessage;}
    public static String getGlobalmuteRespone() {String respone = config.getString("messages.commands.globalmute.response_if_closed");respone = replaceWithVariables(respone);return respone;}
    //warpcommand
    public static String getWarpTeleported(String warpName) {String warpTeleport = config.getString("messages.commands.warpcommand.teleported");warpTeleport = replaceWithVariables(warpTeleport); warpTeleport = warpTeleport.replace("{warpname}", warpName);return warpTeleport;}
    public static String getWarpCreated(String warpName) {String warpCreated = config.getString("messages.commands.warpcommand.created"); warpCreated = replaceWithVariables(warpCreated);warpCreated = warpCreated.replace("{warpname}", warpName);return warpCreated;}
    public static String getWarpDeleted(String warpName) {String warpDeleted = config.getString("messages.commands.warpcommand.deleted"); warpDeleted = replaceWithVariables(warpDeleted);warpDeleted = warpDeleted.replace("{warpname}", warpName);return warpDeleted;}
    public static String getWarpExists(String warpName) {String warpExists = config.getString("messages.commands.warpcommand.exists"); warpExists = replaceWithVariables(warpExists);warpExists = warpExists.replace("{warpname}", warpName);return warpExists;}
    public static String getWarpNotExist(String warpName) {String warpNotExist = config.getString("messages.commands.warpcommand.not_exists");warpNotExist = replaceWithVariables(warpNotExist);return warpNotExist;}
    //fly
    public static String getFlyOffMsg() {String flyOff = config.getString("messages.commands.fly.off");flyOff = replaceWithVariables(flyOff);return flyOff;}
    public static String getFlyOnMsg() {String flyOn = config.getString("messages.commands.fly.on");flyOn = replaceWithVariables(flyOn);return flyOn;}
    //build
    public static String getBuildModeOn() {String buildOn = config.getString("messages.commands.build.on");buildOn = replaceWithVariables(buildOn);return buildOn;}
    public static String getBuildModeOff() {String buildOff = config.getString("messages.commands.build.off");buildOff = replaceWithVariables(buildOff);return buildOff;}
    //tpall
    public static String tpAllOwnMessage() {String ownMessage = config.getString("messages.commands.tpall.own_message");ownMessage = replaceWithVariables(ownMessage);return ownMessage;}
    public static String tpAllPlayersMessage() {String message = config.getString("messages.commands.tpall.players.message"); message = replaceWithVariables(message); return message;}


    public static String replaceWithVariables(String input) {
        String prefix = config.getString("messages.util.prefix"); prefix = prefix.replace("&", "§");
        int onlinePlayersInt = Bukkit.getServer().getOnlinePlayers().size();String onlinePlayers = String.valueOf(onlinePlayersInt);
        int maxPlayersInt = Bukkit.getMaxPlayers();String maxPlayers = String.valueOf(maxPlayersInt);

        input = input.replace("&", "§");
        input = input.replace("{prefix}", prefix);
        input = input.replace("{onlineplayers}", onlinePlayers);
        input = input.replace("{maxplayers", maxPlayers);

        return input;
    }
}
