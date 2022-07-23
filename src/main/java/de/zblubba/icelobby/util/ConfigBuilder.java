package de.zblubba.icelobby.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigBuilder {

    public static File configFile = new File("plugins/IceLobby", "config.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static File spawnFile = new File("plugins/IceLobby", "spawn.yml");
    static FileConfiguration spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    public static File itemFile = new File("plugins/IceLobby", "items.yml");
    static FileConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

    public static void checkConfigs() {
        if(config.get("messages") == null) {
            config.options().header(
              "#########################################################" + "\n" +
                      "           - IceLobby by zBlubba -                     #" + "\n" +
                      "#########################################################" + "\n" +
                      " Supported variables:" + "\n" +
                      " {prefix} - prefix; {user} - username;" + "\n" +
                      " {maxplayers} - slots of server; {onlineplayers} - online players;" + "\n" +
                      " more might come in newer versions." + "\n" +
                      " not every variable works in every case, just try it!" + "\n" +
                      " for the scoreboard, you must use other color-codes for the spacers" + "\n" +
                      " You can find a full guide on https://zblubba.de or on the spigot page :D" + "\n" +
                      "##########################################################"
            );

            config.addDefault("defaults.playergamemode", "ADVENTURE");
            config.addDefault("defaults.unlimitedfood", true);
            config.addDefault("defaults.nodamage", true);
            config.addDefault("defaults.lockweather", true);
            config.addDefault("defaults.noexplosions", true);
            config.addDefault("defaults.stopdespawningleavs", true);
            config.addDefault("defaults.stoppickupitems", true);
            config.addDefault("defaults.healonjoin", true);
            config.addDefault("messages.util.prefix", "&bIce&9Lobby &8| &7");
            config.addDefault("messages.util.nopermission", "&cYou don't have enough permission to perform this command!");
            config.addDefault("messages.util.mustbeaplayertoperform", "&cYou must be a player to perform this command!");
            config.addDefault("messages.util.motd", "&bIce&9Lobby &8- &cMinigames Network &8- &7[&a1.8&7-&a1.19&7]\n                 &a&lNEW Lobby Plugin!");
            config.addDefault("messages.util.actionbar.enabled", true);
            config.addDefault("messages.util.actionbar.message", "&bIce&9Lobby &8- &aNEW Lobby Plugin!");
            config.addDefault("messages.util.commanddoesnotexist", "§cThis command does not exist!");
            config.addDefault("messages.util.visibility.onlyvip", "{prefix} &7You can only see &dVIP's &7now!");
            config.addDefault("messages.util.visibility.nobody", "{prefix} &7You can only see &cno one &7now!");
            config.addDefault("messages.util.visibility.all", "{prefix} &7You can see &aeveryone &7now!");

            config.addDefault("messages.commands.fly.off", "{prefix} &7Fly &cdisabled&7!");
            config.addDefault("messages.commands.fly.on", "{prefix} &7Fly &aenabled&7!");
            config.addDefault("messages.commands.tpall.ownmessage", "{prefix} &7You teleportet &cevery &7player to you!");
            config.addDefault("messages.commands.tpall.players.enabled", true);
            config.addDefault("messages.commands.tpall.players.message", "{prefix} &7Your were teleported to &c{user}");
            config.addDefault("messages.commands.globalmute.broadcastmessage", "{prefix} &7The Chat is now &cclosed&7!");
            config.addDefault("messages.commands.globalmute.responseifclosed", "{prefix} &cThe chat is closed!");
            config.addDefault("messages.commands.warpcommand.teleported", "{prefix} &7You have been teleported to the warp &c{warpname}");
            config.addDefault("messages.commands.warpcommand.created", "{prefix} &7You created the warp &c{warpname}");
            config.addDefault("messages.commands.warpcommand.deleted", "{prefix} &7You deleted the warp &c{warpname}");
            config.addDefault("messages.commands.warpcommand.exists", "{prefix} &cThis warp already exists");
            config.addDefault("messages.commands.warpcommand.notexists", "{prefix} &cThis warp does not exist!");
            config.addDefault("messages.commands.build.on", "{prefix} &7Build mode &aactivated&7!");
            config.addDefault("messages.commands.build.off", "{prefix} &7Build mode &cdeactivated&7!");
            config.addDefault("messages.commands.build.addhotbaritemsonoff", true);


            ArrayList<String> helpList = new ArrayList<>();
            helpList.add("&8======================================================");
            helpList.add("             &b&lIce&9&lLobby");
            helpList.add("&7");
            helpList.add("&7You can find a full guide of this plugin");
            helpList.add("&7on https://zblubba.de or the spigot side!");
            helpList.add("&7");
            helpList.add("&7By the way you can modify this message in the config :D");
            helpList.add("&8======================================================");
            config.addDefault("messages.commands.help", helpList);

            config.addDefault("messages.events.join.enabled", true);
            config.addDefault("messages.events.join.message", "&7[&a+&7] {user}");
            config.addDefault("messages.events.join.title", "&a&lWelcome {user}");
            config.addDefault("messages.events.join.subtitle", "&7to &bYour&9Network!");
            config.addDefault("messages.events.join.playsound", true);
            config.addDefault("messages.events.quit.enabled", true);
            config.addDefault("messages.events.quit.message", "&7[&c-&7] {user}");

            config.addDefault("events.launchpads.enabled", true);
            config.addDefault("events.launchpads.block", "REDSTONE_BLOCK");
            config.addDefault("events.launchpads.multiply", 1.5);
            config.addDefault("events.launchpads.ymultiply", 1);
            config.addDefault("events.launchpads.effect", true);
            config.addDefault("events.launchpads.sound", true);
            //jumppads - enabled, multiplier, block

            config.addDefault("scoreboard.enabled", true);
            config.addDefault("scoreboard.title", "      &bIce&9Lobby");
            ArrayList<String> scoreboard = new ArrayList<>();
            scoreboard.add("&b");
            scoreboard.add("&3➥ &7Your Name");
            scoreboard.add("  &8● &9{user}");
            scoreboard.add("&a");
            scoreboard.add("&3➥ &7Coded by");
            scoreboard.add("  &8● &bzBlubba");
            scoreboard.add("&c");
            scoreboard.add("&3➥ &7visit my Website");
            scoreboard.add("  &8● &9https://zBlubba.de");
            scoreboard.add("&d");
            scoreboard.add("&3➥ &7set your");
            scoreboard.add("  &8● &btext here");
            scoreboard.add("&e");
            scoreboard.add("&f");
            scoreboard.add("       &BYour&9Server");
            config.addDefault("scoreboard.scores", scoreboard);

            config.options().copyHeader(true);
            config.options().copyDefaults(true);

            try {config.save(configFile);} catch (IOException e) {throw new RuntimeException(e);}
            Bukkit.getConsoleSender().sendMessage("§aConfig was successfully created!");
        }

        if(spawnConfig.get("settings") == null) {
            spawnConfig.addDefault("settings.enabled", true);
            spawnConfig.addDefault("settings.teleportonjoin", true);
            spawnConfig.addDefault("settings.teleportmessage", "{prefix} &7Your were teleported to the &aSpawn&7!");
            spawnConfig.addDefault("settings.spawnnotset", "{prefix} &cThere is no spawn.");
            spawnConfig.addDefault("settings.spawndisabled", "{prefix} &cThe spawn is disabled!");

            spawnConfig.options().copyDefaults(true);
            try {spawnConfig.save(spawnFile);} catch (IOException e) {throw new RuntimeException(e);}
            Bukkit.getConsoleSender().sendMessage("§aSpawn-Config was successfully created!");
        }

        if(itemConfig.get("items") == null) {
            itemConfig.options().header(
                    "#########################################################" + "\n" +
                            "           - IceLobby by zBlubba -                     #" + "\n" +
                            "#########################################################" + "\n" +
                            " You can find the supported variables in the config.yml" + "\n" +
                            " the slots in the hotbar are from 0 - 8. In the compass gui max. rows are 6" + "\n" +
                            " the OWN_HEAD type gives the head of the player" + "\n" +
                            " feel free to implement other plugins for the commands, ex. nick, shop" + "\n" +
                            " for the types try to use the 1.19 names :D" + "\n" +
                            "##########################################################"
            );

            itemConfig.addDefault("items.enabled", true);
            itemConfig.addDefault("items.canbemoved", false);
            itemConfig.addDefault("items.canbedropped", false);
            itemConfig.addDefault("items.addonjoin", true);
            itemConfig.addDefault("items.clearinvonjoin", true);

            itemConfig.addDefault("items.hotbar.1.enabled", true);
            itemConfig.addDefault("items.hotbar.1.slot", 0);
            itemConfig.addDefault("items.hotbar.1.name", "&6Friends &7- &arightclick");
            itemConfig.addDefault("items.hotbar.1.lore", "&7Click to open friends menu");
            itemConfig.addDefault("items.hotbar.1.type", "OWN_HEAD");
            itemConfig.addDefault("items.hotbar.1.command", "friends");

            itemConfig.addDefault("items.hotbar.2.enabled", true);
            itemConfig.addDefault("items.hotbar.2.slot", 1);
            itemConfig.addDefault("items.hotbar.2.name", "&cPrivate Lobby &7- &arightclick");
            itemConfig.addDefault("items.hotbar.2.lore", "&7Click to get your own lobby!");
            itemConfig.addDefault("items.hotbar.2.type", "TNT");
            itemConfig.addDefault("items.hotbar.2.command", "privatelobby");

            itemConfig.addDefault("items.hotbar.3.enabled", true);
            itemConfig.addDefault("items.hotbar.3.slot", 2);
            itemConfig.addDefault("items.hotbar.3.name", "&bPlayer Visibility &7- &arightclick");
            itemConfig.addDefault("items.hotbar.3.lore", "&7Click to show only chosen players!");
            itemConfig.addDefault("items.hotbar.3.type", "LIME_DYE");
            itemConfig.addDefault("items.hotbar.3.command", "visibility");

            itemConfig.addDefault("items.hotbar.4.enabled", true);
            itemConfig.addDefault("items.hotbar.4.slot", 3);
            itemConfig.addDefault("items.hotbar.4.name", "&c");
            itemConfig.addDefault("items.hotbar.4.lore", "");
            itemConfig.addDefault("items.hotbar.4.type", "BLACK_STAINED_GLASS_PANE");
            itemConfig.addDefault("items.hotbar.4.command", "nothing");

            itemConfig.addDefault("items.hotbar.5.enabled", true);
            itemConfig.addDefault("items.hotbar.5.slot", 4);
            itemConfig.addDefault("items.hotbar.5.name", "&aCompass");
            itemConfig.addDefault("items.hotbar.5.lore", "&7Click to open the compass menu!");
            itemConfig.addDefault("items.hotbar.5.type", "COMPASS");
            itemConfig.addDefault("items.hotbar.5.command", "navigator");

            itemConfig.addDefault("items.hotbar.6.enabled", true);
            itemConfig.addDefault("items.hotbar.6.slot", 5);
            itemConfig.addDefault("items.hotbar.6.name", "&c");
            itemConfig.addDefault("items.hotbar.6.lore", "");
            itemConfig.addDefault("items.hotbar.6.type", "BLACK_STAINED_GLASS_PANE");
            itemConfig.addDefault("items.hotbar.6.command", "nothing");

            itemConfig.addDefault("items.hotbar.7.enabled", true);
            itemConfig.addDefault("items.hotbar.7.slot", 6);
            itemConfig.addDefault("items.hotbar.7.name", "&eNick");
            itemConfig.addDefault("items.hotbar.7.lore", "&7Click to nick");
            itemConfig.addDefault("items.hotbar.7.type", "NAME_TAG");
            itemConfig.addDefault("items.hotbar.7.command", "nick");

            itemConfig.addDefault("items.hotbar.8.enabled", true);
            itemConfig.addDefault("items.hotbar.8.slot", 7);
            itemConfig.addDefault("items.hotbar.8.name", "&dShop");
            itemConfig.addDefault("items.hotbar.8.lore", "&7Click to open the shop!");
            itemConfig.addDefault("items.hotbar.8.type", "CHEST");
            itemConfig.addDefault("items.hotbar.8.command", "shop");

            itemConfig.addDefault("items.hotbar.9.enabled", true);
            itemConfig.addDefault("items.hotbar.9.slot", 8);
            itemConfig.addDefault("items.hotbar.9.name", "&9Lobbies");
            itemConfig.addDefault("items.hotbar.9.lore", "&7Click to switch lobby!");
            itemConfig.addDefault("items.hotbar.9.type", "NETHER_STAR");
            itemConfig.addDefault("items.hotbar.9.command", "lobbies");


            itemConfig.addDefault("compass.gui.enabled", true);
            itemConfig.addDefault("compass.gui.title", "&9Navigator");
            itemConfig.addDefault("compass.gui.rows", 6);
            itemConfig.addDefault("compass.gui.filledwithglass", true);



            itemConfig.addDefault("compass.items.4.type", "BARRIER");
            itemConfig.addDefault("compass.items.4.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.4.lore", "&8");
            itemConfig.addDefault("compass.items.4.command", "nothing");

            itemConfig.addDefault("compass.items.11.type", "BARRIER");
            itemConfig.addDefault("compass.items.11.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.11.lore", "&8");
            itemConfig.addDefault("compass.items.11.command", "nothing");

            itemConfig.addDefault("compass.items.15.type", "BARRIER");
            itemConfig.addDefault("compass.items.15.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.15.lore", "&8");
            itemConfig.addDefault("compass.items.15.command", "nothing");

            itemConfig.addDefault("compass.items.22.type", "NETHER_STAR");
            itemConfig.addDefault("compass.items.22.name", "&6Spawn");
            itemConfig.addDefault("compass.items.22.lore", "&7Click for teleporting to Spawn");
            itemConfig.addDefault("compass.items.22.command", "warp spawn");

            itemConfig.addDefault("compass.items.28.type", "BARRIER");
            itemConfig.addDefault("compass.items.28.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.28.lore", "&8");
            itemConfig.addDefault("compass.items.28.command", "nothing");

            itemConfig.addDefault("compass.items.34.type", "BARRIER");
            itemConfig.addDefault("compass.items.34.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.34.lore", "&8");
            itemConfig.addDefault("compass.items.34.command", "nothing");

            itemConfig.addDefault("compass.items.48.type", "BARRIER");
            itemConfig.addDefault("compass.items.48.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.48.lore", "&8");
            itemConfig.addDefault("compass.items.48.command", "nothing");

            itemConfig.addDefault("compass.items.50.type", "BARRIER");
            itemConfig.addDefault("compass.items.50.name", "&ccoming sooon!");
            itemConfig.addDefault("compass.items.50.lore", "&8");
            itemConfig.addDefault("compass.items.50.command", "nothing");




            itemConfig.options().copyHeader(true);
            itemConfig.options().copyDefaults(true);
            try {itemConfig.save(itemFile);} catch (IOException e) {throw new RuntimeException(e);}
            Bukkit.getConsoleSender().sendMessage("§aItem-Config was successfully created!");
        }
    }
}
