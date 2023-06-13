package io.github.abyssmc.tab.prefix.types;

import java.util.UUID;

import io.github.abyssmc.tab.prefix.PlayerPrefix;

import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;

public class LuckPermsPrefix implements PlayerPrefix {

    private final UserManager users;

    public LuckPermsPrefix(UserManager user) {
        this.users = user;
    }

    @Override
    public String getPrefix(UUID uuid) {
        final User user = users.getUser(uuid);
        if (user == null) {
            return "";
        }

        final String prefix = user.getCachedData().getMetaData().getPrefix();
        return (prefix == null) ? "" : prefix;
    }
}