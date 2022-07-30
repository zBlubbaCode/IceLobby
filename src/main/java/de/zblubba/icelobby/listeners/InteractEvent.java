package de.zblubba.icelobby.listeners;

import de.zblubba.icelobby.IceLobby;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class InteractEvent implements Listener {

    @EventHandler
    public void onEntityInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if(!IceLobby.getLobbyWorlds().contains(p.getLocation().getWorld().getName())) return;

        if(IceLobby.config.getBoolean("launchpads.enabled")) {
            Bukkit.getScheduler().runTaskLater(IceLobby.getPlugin(IceLobby.class), () -> {
                Material block = p.getLocation().subtract(0D, 2D, 0D).getBlock().getType();
                String launchblock = IceLobby.config.getString("launchpads.block");
                Material launchblockMat = Material.valueOf(launchblock);
                double multiply = IceLobby.config.getDouble("launchpads.multiply");
                double ymutliply = IceLobby.config.getDouble("launchpads.ymultiply");

                if(block == launchblockMat && (p.getLocation().getBlock().getType() == Material.OAK_PRESSURE_PLATE || p.getLocation().getBlock().getType() == Material.STONE_PRESSURE_PLATE)) {
                    Vector v = p.getLocation().getDirection().multiply(multiply).setY(ymutliply);
                    p.setVelocity(v);

                    if(IceLobby.config.getBoolean("launchpads.effect")) p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
                    if(IceLobby.config.getBoolean("launchpads.sound")) p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 255, 1);

                    p.setFallDistance(-999F);
                }
            }, 3);
        }

        int slot = p.getInventory().getHeldItemSlot();
        IceLobby.getInstance().getLogger().info("0");
        if(IceLobby.itemConfig.get("items.hotbar." + slot) != null) {
            IceLobby.getInstance().getLogger().info("1");
            if(p.getInventory().getItemInMainHand() == null) return;
            if(!IceLobby.itemConfig.getBoolean("items.hotbar." + slot + ".enabled")) return;
            IceLobby.getInstance().getLogger().info(slot + " - Slot");
            IceLobby.getInstance().getLogger().info(IceLobby.itemConfig.get("items.hotbar." + slot + ".slot") + " - " + p.getInventory().getHeldItemSlot());
            IceLobby.getInstance().getLogger().info("3");
            String itemName = IceLobby.itemConfig.getString("items.hotbar." + slot + ".name");
            String[] itemNameSplitted = itemName.split("&");
            for(int i = 0; i < itemNameSplitted.length; i++) {
                itemNameSplitted[i] = removeFirstChar(itemNameSplitted[i]);
            }
            String itemNameTogether = "";
            for(int i = 0; i < itemNameSplitted.length; i++) {
                itemNameTogether = itemNameTogether + itemNameSplitted[i];
            }
            IceLobby.getInstance().getLogger().info("4");
            String itemNameInHand;
            if(p.getInventory().getItemInMainHand().hasItemMeta()) {
                IceLobby.getInstance().getLogger().info("5");
                itemNameInHand = ChatColor.stripColor(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
            } else return;

            if(itemNameTogether.equals(itemNameInHand)) {
                IceLobby.getInstance().getLogger().info("6");
                if(IceLobby.itemConfig.getConfigurationSection("items.hotbar." + slot) == null) return;
                IceLobby.getInstance().getLogger().info("7");
                p.performCommand(IceLobby.itemConfig.getString("items.hotbar." + slot + ".command"));
                event.setCancelled(true);
            }

        }
    }

    public String removeFirstChar(String input) {
        if(input == null | input.length() == 0) return input;
        return input.substring(1);
    }
}
