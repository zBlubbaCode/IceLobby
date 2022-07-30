package de.zblubba.icelobby.shop;

import de.zblubba.icelobby.IceLobby;
import de.zblubba.icelobby.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomySystem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) return false;
        if (args.length == 0) {
            p.sendMessage(MessageCollection.getCoinsCommand(p) + getMoney(p));
        }
        if(args.length == 1) {
            if(p.hasPermission("icelobby.coins.others")) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    p.sendMessage(MessageCollection.getPlayerOffline());
                    return false;
                }

                p.sendMessage(MessageCollection.getCoinsOthers(target.getName()) + getMoney(target));
            }
        }

        if(args.length == 3) {
            if (p.hasPermission("icelobby.coins.manage")) {
                if (args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    Integer amount = Integer.valueOf(args[2]);

                    if(target == null) {
                        p.sendMessage(MessageCollection.getPlayerOffline());
                        return false;
                    }

                    addMoney(target, amount);

                    p.sendMessage(MessageCollection.coinsAdded(amount, target));
                } else if (args[0].equalsIgnoreCase("remove")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    Integer amount = Integer.valueOf(args[2]);

                    if(target == null) {
                        p.sendMessage(MessageCollection.getPlayerOffline());
                        return false;
                    }

                    removeMoney(target, amount);

                    p.sendMessage(MessageCollection.coinsRemoved(amount, target));
                } else if (args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    Integer amount = Integer.valueOf(args[2]);

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
        int money = IceLobby.moneyConfig.getInt(player.getUniqueId() + ".money");
        return money;
    }

    public static void addMoney(Player player, int amount) {
        int money = getMoney(player);
        money = money+amount;
        IceLobby.moneyConfig.set(player.getUniqueId() + ".money", money);

        IceLobby.saveFile(IceLobby.fileMoney, IceLobby.moneyConfig);
    }

    public static void removeMoney(Player player, int amount) {
        int money = getMoney(player);
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