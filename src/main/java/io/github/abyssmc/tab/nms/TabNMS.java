package io.github.abyssmc.tab.nms;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.entity.Player;

import io.github.abyssmc.tab.prefix.PlayerPrefix;

/**
 *
 * @param <V> Version of {@link IChatBaseComponent}.
 *
 */
public abstract class TabNMS<V> {

    protected V header;
    protected V footer;

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
    public abstract V createMessage(String text);
}