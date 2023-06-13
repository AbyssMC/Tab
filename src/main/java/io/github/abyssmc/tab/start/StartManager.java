package io.github.abyssmc.tab.start;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.abyssmc.tab.TabPlugin;
import io.github.abyssmc.tab.nms.TabNMS;
import io.github.abyssmc.tab.utils.MessageUtil;

public class StartManager {

    private final TabPlugin plugin;
    private final StartPluginHook pluginHook;
    private final TabNMS<?> nms;

    public StartManager(TabPlugin plugin, TabNMS<?> nms, StartPluginHook pluginHook) {
        this.plugin = plugin;
        this.nms = nms;
        this.pluginHook = pluginHook;
    }

    public void reload() {
        plugin.reloadConfig();

        final FileConfiguration config = plugin.getConfig();

        nms.setPrefix(pluginHook.getPlayerPrefix(config));
        nms.setValues(
            MessageUtil.transform(config.get("header")),
            MessageUtil.transform(config.get("footer")));
    }
}