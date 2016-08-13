package me.nexciak.xjointp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.nexciak.xjointp.manager.Manager;

public class PlayerJoinListener implements Listener {
	
    @EventHandler
    private void onJoin(final PlayerJoinEvent e) {
        if (e.getPlayer().hasPlayedBefore()) {
            return;
        }
        Manager.randomTP(e.getPlayer());
    }
}

