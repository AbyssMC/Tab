package io.github.abyssmc.tab;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.abyssmc.tab.commands.TabCommand;
import io.github.abyssmc.tab.listeners.PlayerJoinListener;
import io.github.abyssmc.tab.nms.TabNMS;
import io.github.abyssmc.tab.nms.versions.v1_8R3;
import io.github.abyssmc.tab.start.StartManager;
import io.github.abyssmc.tab.start.StartPluginHook;
import io.github.abyssmc.tab.utils.MessageUtil;

public class TabPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        final PluginManager pluginManager = getServer().getPluginManager();
        final StartPluginHook pluginHook = new StartPluginHook(pluginManager);
        final FileConfiguration config = getConfig();

        final TabNMS<?> tabNMS = new v1_8R3(
            MessageUtil.transform(config.get("header")),
            MessageUtil.transform(config.get("footer")),
            pluginHook.getPlayerPrefix(config));

        final StartManager manager = new StartManager(this, tabNMS, pluginHook);

        pluginManager.registerEvents(new PlayerJoinListener(tabNMS), this);
        getCommand("qtab").setExecutor(new TabCommand(manager));
    }
}