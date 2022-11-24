package de.immernochnoah.listeners;

import de.immernochnoah.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class OnPlayerPickupItemEvent implements Listener {

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (!Main.isStarted) {
            event.setCancelled(true);
        } else {
            Item item = event.getItem();
            if (item.getItemStack().getType().equals(Material.DIAMOND)) {
                Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                    onlinePlayer.sendMessage(player.getName() + "§a hat einen Diamant gefunden!");
                    onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                    onlinePlayer.sendTitle("§aChallenge", "§ageschaft!");
                });
                Main.isStarted = false;
            }
        }
    }
}
