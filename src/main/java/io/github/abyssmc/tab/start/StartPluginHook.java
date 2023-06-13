package io.github.abyssmc.tab.start;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import io.github.abyssmc.tab.prefix.PlayerPrefix;
import io.github.abyssmc.tab.prefix.types.DefaultPrefix;
import io.github.abyssmc.tab.prefix.types.LuckPermsPrefix;
import io.github.abyssmc.tab.utils.MessageUtil;

import net.luckperms.api.LuckPerms;

public class StartPluginHook {

    private LuckPerms luckPerms;

    public StartPluginHook(PluginManager pluginManager) {
        luckPerms = hookLuckPerms();
    }

    public LuckPerms hookLuckPerms() {
        final RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        return (provider == null) ? null : provider.getProvider();
    }

    public PlayerPrefix getPlayerPrefix(FileConfiguration config) {
        if (luckPerms == null) {
            final String line = config.getString("default-prefix");
            return new DefaultPrefix((line == null) ? "" : MessageUtil.color(line));
        }
        return new LuckPermsPrefix(luckPerms.getUserManager());
    }
}