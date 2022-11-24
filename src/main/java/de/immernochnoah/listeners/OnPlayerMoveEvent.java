package de.immernochnoah.listeners;

import de.immernochnoah.Main;
import de.immernochnoah.api.ChangeBlocks;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class OnPlayerMoveEvent implements Listener {
    public static Map<String, Long> cooldown = new HashMap<String, Long>();

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (Main.isStarted) {
            Player p = event.getPlayer();
            if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
                if (cooldown.containsKey(p.getName())) {
                    if (cooldown.get(p.getName()) > System.currentTimeMillis()) {
                        return;
                    }
                }
                OnPlayerMoveEvent.cooldown.put(p.getName(), System.currentTimeMillis() + (100));
                ChangeBlocks.changeBlocks(p);
            }
        }
    }
}
