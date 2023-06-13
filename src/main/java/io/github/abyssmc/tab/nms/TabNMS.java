package io.github.abyssmc.tab.nms;

import org.bukkit.entity.Player;

import io.github.abyssmc.tab.prefix.PlayerPrefix;

public abstract class TabNMS<C> {

    protected C header;
    protected C footer;

    protected PlayerPrefix prefix;

    public TabNMS(String header, String footer, PlayerPrefix prefix) {
        this.prefix = prefix;
        setValues(header, footer);
    }

    public void setPrefix(PlayerPrefix prefix) {
        this.prefix = prefix;
    }

    public void setValues(String header, String footer) {
        this.header = createMessage(header);
        this.footer = createMessage(footer);
    }

    public abstract void sendTab(Player player);
    public abstract C createMessage(String text);
}