package de.zblubba.icelobby.util;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MessageCollection {

    static Configuration config = IceLobby.messagesConfig;
    static Configuration itemConfig = IceLobby.itemConfig;

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

    public static String getShopGUITitle(Player p) {
        String title = itemConfig.getString("shop.gui.title");
        title = replaceWithVariables(title);
        title = title.replace("%player%", p.getName());
        return title;
    }
    public static String getShopItemName(int number) {
        String name = itemConfig.getString("shop.items." + number + ".name");
        name = replaceWithVariables(name);
        return name;
    }
    public static String getShopItemLore(int number) {
        String lore = itemConfig.getString("shop.items." + number + ".lore");
        lore = replaceWithVariables(lore);
        return lore;
    }
    public static String getShopItemCommand(int number) {
        String command = itemConfig.getString("shop.items." + number + ".command");
        return command;
    }
    public static Material getShopItemType(int number) {
        Material type = Material.valueOf(itemConfig.getString("shop.items." + number + ".type"));
        return type;
    }
    public static int getShopItemCost(int number) {
        int value = itemConfig.getInt("shop.items." + number + ".cost");
        return value;
    }
    public static String getShopItemAction(int number) {
        String action = itemConfig.getString("shop.items." + number + ".action");
        return action;
    }
    public static String getActionItemName(String action) {
        String name = itemConfig.getString("shop.action." + action + ".get_item.name");
        name = replaceWithVariables(name);
        return name;
    }
    public static String getActionItemLore(String action) {
        String lore = itemConfig.getString("shop.action." + action + ".get_item.lore");
        lore = replaceWithVariables(lore);
        return lore;
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

    //economy
    public static String getCoinsCommand(Player p) {
        String coins = config.getString("commands.economy.coins");
        coins = replaceWithVariables(coins);
        coins = coins.replace("%player%", p.getName());
        return coins;
    }
    public static String getCoinsOthers(String name) {
        String coinsOthers = config.getString("commands.economy.coins_others");
        coinsOthers = replaceWithVariables(coinsOthers);
        coinsOthers = coinsOthers.replace("%player%", name);
        return coinsOthers;
    }
    public static String getPlayerOffline() {
        String offlineMsg = config.getString("commands.economy.player_offline");
        offlineMsg = replaceWithVariables(offlineMsg);
        return offlineMsg;
    }
    public static String coinsAdded(int coins, Player p) {
        String coinsAdded = config.getString("commands.economy.coins_added");
        coinsAdded = replaceWithVariables(coinsAdded);
        coinsAdded = coinsAdded.replace("%coins%", String.valueOf(coins));
        coinsAdded = coinsAdded.replace("%player%", p.getName());
        return coinsAdded;
    }
    public static String coinsRemoved(int coins, Player p) {
        String coinsRemoved = config.getString("commands.economy.coins_removed");
        coinsRemoved = replaceWithVariables(coinsRemoved);
        coinsRemoved = coinsRemoved.replace("%coins%", String.valueOf(coins));
        coinsRemoved = coinsRemoved.replace("%player%", p.getName());
        return coinsRemoved;
    }
    public static String coinsSet(int coins, Player p) {
        String coinsSet = config.getString("commands.economy.coins_set");
        coinsSet = replaceWithVariables(coinsSet);
        coinsSet = coinsSet.replace("%coins%", String.valueOf(coins));
        coinsSet = coinsSet.replace("%player%", p.getName());
        return coinsSet;
    }

    public static String notEnoughCoins() {
        String notEnoughCoins = config.getString("commands.economy.not_enough_coins");
        notEnoughCoins = replaceWithVariables(notEnoughCoins);
        return notEnoughCoins;
    }

    public static String getSwitcherTitle() {
        String title = itemConfig.getString("lobby_switcher.title");
        title = replaceWithVariables(title);
        return title;
    }

    public static String getWorldNamePrefix(int number) {
        String prefix = itemConfig.getString("lobby_switcher.world_name_prefix");
        prefix = replaceWithVariables(prefix);
        prefix = prefix.replace("%number%", String.valueOf(number + 1));
        return prefix;
    }


    public static String replaceWithVariables(String input) {
        if(input == null) {
            IceLobby.getInstance().getLogger().info("ERROR | On Message Send");
            return "§cERROR";
        }
        String prefix = config.getString("prefix"); prefix = prefix.replace("&", "§");
        int onlinePlayersInt = Bukkit.getServer().getOnlinePlayers().size();String onlinePlayers = String.valueOf(onlinePlayersInt);
        int maxPlayersInt = Bukkit.getMaxPlayers();String maxPlayers = String.valueOf(maxPlayersInt);

        input = input.replace('&', '§');
        input = input.replace("%prefix%", prefix);
        input = input.replace("%onlineplayers%", onlinePlayers);
        input = input.replace("%maxplayers%", maxPlayers);
        input = input.replace("%n%", "\n");

        return input;
    }
}
