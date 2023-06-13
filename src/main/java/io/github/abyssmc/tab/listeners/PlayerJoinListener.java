package io.github.abyssmc.tab.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.abyssmc.tab.nms.TabNMS;

public class PlayerJoinListener implements Listener {

    private final TabNMS<?> nms;

    public PlayerJoinListener(TabNMS<?> nms) {
        this.nms = nms;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        nms.sendTab(event.getPlayer());
    }
}