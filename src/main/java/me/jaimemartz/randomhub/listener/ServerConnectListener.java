package me.jaimemartz.randomhub.listener;

import me.jaimemartz.randomhub.ConnectionAttempt;
import me.jaimemartz.randomhub.RandomHub;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectListener implements Listener {
    private final RandomHub plugin;

    public ServerConnectListener(final RandomHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ServerInfo target = event.getTarget();

        if (plugin.getServers().contains(target)) {
            event.setCancelled(true);
            new ConnectionAttempt(plugin, player) {
                @Override
                public void connect(ServerInfo server) {
                    event.setTarget(server);
                }
            };
        }
    }
}