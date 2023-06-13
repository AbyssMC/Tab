package io.github.abyssmc.tab.prefix.types;

import java.util.UUID;

import io.github.abyssmc.tab.prefix.PlayerPrefix;

public class DefaultPrefix implements PlayerPrefix {

    private final String prefix;

    public DefaultPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getPrefix(UUID uuid) {
        return prefix;
    }
}