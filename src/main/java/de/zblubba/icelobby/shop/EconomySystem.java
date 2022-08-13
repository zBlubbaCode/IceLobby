package de.zblubba.icelobby.shop;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomySystem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (args.length == 0) {
            //if the command is just /coins
            p.sendMessage(MessageCollection.getCoinsCommand(p) + getMoney(p));
        }
        if(args.length == 1) {
            if(p.hasPermission("icelobby.coins.others")) {
                //gets the coins of the specified player
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    p.sendMessage(MessageCollection.getPlayerOffline());
                    return false;
                }

                //send the player the message
                p.sendMessage(MessageCollection.getCoinsOthers(target.getName()) + getMoney(target));
            }
        }

        //if the command is /coins <add | remove> <player> <value>
        if(args.length == 3) {
            if (p.hasPermission("icelobby.coins.manage")) {
                if (args[0].equalsIgnoreCase("add")) {
                    //get the target and amount of coins
                    Player target = Bukkit.getPlayer(args[1]);
                    Integer amount = Integer.valueOf(args[2]);

                    //if the player is offline
                    if(target == null) {
                        p.sendMessage(MessageCollection.getPlayerOffline());
                        return false;
                    }

                    addMoney(target, amount);

                    p.sendMessage(MessageCollection.coinsAdded(amount, target));
                } else if (args[0].equalsIgnoreCase("remove")) {
                    //get the target and amount of coins
                    Player target = Bukkit.getPlayer(args[1]);
                    Integer amount = Integer.valueOf(args[2]);

                    //if the player is offline
                    if(target == null) {
                        p.sendMessage(MessageCollection.getPlayerOffline());
                        return false;
                    }

                    removeMoney(target, amount);

                    p.sendMessage(MessageCollection.coinsRemoved(amount, target));
                } else if (args[0].equalsIgnoreCase("set")) {
                    //get the target and amount of coins
                    Player target = Bukkit.getPlayer(args[1]);
                    Integer amount = Integer.valueOf(args[2]);

                    //if the player is offline
                    if(target == null) {
                        p.sendMessage(MessageCollection.getPlayerOffline());
                        return false;
                    }

                    setMoney(target, amount);

                    p.sendMessage(MessageCollection.coinsSet(amount, target));
                }
            } else p.sendMessage(MessageCollection.getNoPerms());
        }

        return false;
    }

    public static Integer getMoney(Player player) {
        //get the money out of the money.yml
        int money = IceLobby.moneyConfig.getInt(player.getUniqueId() + ".money");
        return money;
    }

    public static void addMoney(Player player, int amount) {
        int money = getMoney(player);
        //get the money and add the new amount
        money = money+amount;
        IceLobby.moneyConfig.set(player.getUniqueId() + ".money", money);

        IceLobby.saveFile(IceLobby.fileMoney, IceLobby.moneyConfig);
    }

    public static void removeMoney(Player player, int amount) {
        int money = getMoney(player);
        //get the money and remove the amount
        money = money-amount;
        IceLobby.moneyConfig.set(player.getUniqueId() + ".money", money);

        IceLobby.saveFile(IceLobby.fileMoney, IceLobby.moneyConfig);
    }

    public static void setMoney(Player player, int amount) {
        IceLobby.moneyConfig.set(player.getUniqueId() + ".money", amount);
        IceLobby.saveFile(IceLobby.fileMoney, IceLobby.moneyConfig);
    }

    public static boolean hasEnoughMoney(Player player, int amount) {
        int money = getMoney(player);

        if(money >= amount) {
            return true;
        } else {
            return false;
        }
    }
}