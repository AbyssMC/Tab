package io.github.abyssmc.tab.utils;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

public class MessageUtil {

    public static String transform(Object object) {
        if (object == null) {
            return "";
        }

        if (!(object instanceof List)) {
            return color(object.toString());
        }

        final List<?> lines = (List<?>)object;
        final StringBuilder builder = new StringBuilder();
        final int size = lines.size();

        int count = 0;

        for (final Object line : lines) {
            final String value = line.toString();

            builder.append(color(value));
            if (++count != size) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static String color(String text) {
        return (text == null) ? "" : ChatColor.translateAlternateColorCodes('&', text);
    }
}
