package io.github.abyssmc.tab.nms.versions;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import io.github.abyssmc.tab.nms.TabNMS;
import io.github.abyssmc.tab.prefix.PlayerPrefix;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class v1_8R3 extends TabNMS<IChatBaseComponent> {

    private final List<EntityPlayer> players;

    public v1_8R3(String header, String footer, PlayerPrefix prefix) {
        super(header, footer, prefix);
        this.players = MinecraftServer.getServer().getPlayerList().players;
    }

    @Override
    public void sendTab(Player player) {
        String nameWithPrefix = prefix.getPrefix(player.getUniqueId()) + player.getName();
        try {
            final PacketPlayOutPlayerListHeaderFooter listPacket = new PacketPlayOutPlayerListHeaderFooter(header);
            final Field footerField = listPacket.getClass().getDeclaredField("b");

            footerField.setAccessible(true);
            footerField.set(listPacket, footer);

            final EntityPlayer entityCraftPlayer = ((CraftPlayer)player).getHandle();

            entityCraftPlayer.playerConnection.sendPacket(listPacket);
            entityCraftPlayer.listName = CraftChatMessage.fromString(nameWithPrefix)[0];

            final PacketPlayOutPlayerInfo playerInfoPacket = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, entityCraftPlayer);

            for (final EntityPlayer connectedPlayer : players) {
                connectedPlayer.playerConnection.sendPacket(playerInfoPacket);
            }

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IChatBaseComponent createMessage(String text) {
        return IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\"}");
    }
}