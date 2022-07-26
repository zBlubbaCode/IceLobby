package de.zblubba.icelobby.util;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MessageCollection {

    static FileConfiguration config = IceLobby.messagesConfig;
    static FileConfiguration itemConfig = IceLobby.itemConfig;

    //util
    public static String getPrefix() {String prefix = config.getString("prefix"); prefix = replaceWithVariables(prefix);return prefix;}
    public static String getNoPerms() {String noPerms = config.getString("no_permission"); noPerms = replaceWithVariables(noPerms);return noPerms;}
    public static String mustbePlayer() {String mustbePlayer = config.getString("must_be_a_player"); mustbePlayer = replaceWithVariables(mustbePlayer);return mustbePlayer;}
    public static String commandNotExist() {String notExist = config.getString("command_does_not_exist");notExist = replaceWithVariables(notExist);return notExist;}
    public static String visibilityOnlyVIP() {String onlyVIP = config.getString("visibility.onlyvip"); onlyVIP = replaceWithVariables(onlyVIP); return onlyVIP;}
    public static String visibilityNobody() {String nobody = config.getString("visibility.nobody"); nobody = replaceWithVariables(nobody); return nobody;}
    public static String visibilityAll() {String all = config.getString("visibility.all"); all = replaceWithVariables(all); return all;}

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

    //join
    public static String joinMessage(Player p) {String joinMessage = config.getString("join.message");joinMessage = replaceWithVariables(joinMessage); joinMessage = joinMessage.replace("%player%", p.getName()); return joinMessage;}
    public static String title(Player p) {String title = config.getString("join.title"); title = replaceWithVariables(title); title = title.replace("%player%", p.getName()); return title;}
    public static String subtitle(Player p){String subtitle = config.getString("join.subtitle"); subtitle = replaceWithVariables(subtitle); subtitle = subtitle.replace("%player%", p.getName()); return subtitle;}

    //quit
    public static String quitMessage(Player p) {String quitMessage = config.getString("quit.message"); quitMessage = replaceWithVariables(quitMessage); quitMessage = quitMessage.replace("%player%", p.getName()); return quitMessage;}

    //actionbar
    public static String actionbarMessage(Player p) {String actionbarMessage = config.getString("actionbar.message"); actionbarMessage = replaceWithVariables(actionbarMessage); actionbarMessage = actionbarMessage.replace("%player%", p.getName()); return actionbarMessage;}

    public static String spawnNotSet() {String noSpawn = config.getString("spawn.spawnnotset");noSpawn = replaceWithVariables(noSpawn);return noSpawn;}
    public static String spawnDisabled() {String spawnDisabled = config.getString("spawn.spawndisabled"); spawnDisabled = replaceWithVariables(spawnDisabled); return spawnDisabled;}
    public static String teleportMessage() {String tpMsg = config.getString("spawm.teleportmessage"); tpMsg = replaceWithVariables(tpMsg); return tpMsg;}

    //compass gui
    public static String getCompassGUITitle(Player p) {String compassTitle = itemConfig.getString("compass.gui.title"); compassTitle = compassTitle.replace('&', '§');compassTitle = compassTitle.replace("%player%", p.getName());return compassTitle;}
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
    public static String getGlobalmuteBroadcastMessage() {String broadcastMessage = config.getString("commands.globalmute.broadcast_message");broadcastMessage = replaceWithVariables(broadcastMessage);return broadcastMessage;}
    public static String getGlobalmuteRespone() {String respone = config.getString("commands.globalmute.response_if_closed");respone = replaceWithVariables(respone);return respone;}
    //warpcommand
    public static String getWarpTeleported(String warpName) {String warpTeleport = config.getString("commands.warpcommand.teleported");warpTeleport = replaceWithVariables(warpTeleport); warpTeleport = warpTeleport.replace("%warpname%", warpName);return warpTeleport;}
    public static String getWarpCreated(String warpName) {String warpCreated = config.getString("commands.warpcommand.created"); warpCreated = replaceWithVariables(warpCreated);warpCreated = warpCreated.replace("%warpname%", warpName);return warpCreated;}
    public static String getWarpDeleted(String warpName) {String warpDeleted = config.getString("commands.warpcommand.deleted"); warpDeleted = replaceWithVariables(warpDeleted);warpDeleted = warpDeleted.replace("%warpname%", warpName);return warpDeleted;}
    public static String getWarpExists(String warpName) {String warpExists = config.getString("commands.warpcommand.exists"); warpExists = replaceWithVariables(warpExists);warpExists = warpExists.replace("%warpname%", warpName);return warpExists;}
    public static String getWarpNotExist(String warpName) {String warpNotExist = config.getString("commands.warpcommand.not_exists");warpNotExist = replaceWithVariables(warpNotExist);return warpNotExist;}
    //fly
    public static String getFlyOffMsg() {String flyOff = config.getString("commands.fly.deactivated");flyOff = replaceWithVariables(flyOff);return flyOff;}
    public static String getFlyOnMsg() {String flyOn = config.getString("commands.fly.activated");flyOn = replaceWithVariables(flyOn);return flyOn;}
    //build
    public static String getBuildModeOn() {String buildOn = config.getString("commands.build.activated");buildOn = replaceWithVariables(buildOn);return buildOn;}
    public static String getBuildModeOff() {String buildOff = config.getString("commands.build.deactivated");buildOff = replaceWithVariables(buildOff);return buildOff;}
    //tpall
    public static String tpAllOwnMessage() {String ownMessage = config.getString("commands.tpall.own_message");ownMessage = replaceWithVariables(ownMessage);return ownMessage;}
    public static String tpAllPlayersMessage() {String message = config.getString("commands.tpall.players.message"); message = replaceWithVariables(message); return message;}
    //help
    public static String getHelpList() {
        String message = "";
        ArrayList<String> helpLineList = (ArrayList<String>) config.getList("commands.help");
        for(int i = 0; i < helpLineList.size(); i++) {
            message = message + "\n" + helpLineList.get(i);
            message = replaceWithVariables(message);
        }
        return message;
    }


    public static String replaceWithVariables(String input) {
        String prefix = config.getString("prefix"); prefix = prefix.replace("&", "§");
        int onlinePlayersInt = Bukkit.getServer().getOnlinePlayers().size();String onlinePlayers = String.valueOf(onlinePlayersInt);
        int maxPlayersInt = Bukkit.getMaxPlayers();String maxPlayers = String.valueOf(maxPlayersInt);

        input = input.replace("&", "§");
        input = input.replace("%prefix%", prefix);
        input = input.replace("%onlineplayers%", onlinePlayers);
        input = input.replace("%maxplayers%", maxPlayers);
        input = input.replace("%n%", "\n");

        return input;
    }
}
