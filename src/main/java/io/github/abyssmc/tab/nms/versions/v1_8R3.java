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
        try {
            final PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(header);
            final Field f = packet.getClass().getDeclaredField("b");

            f.setAccessible(true);
            f.set(packet, footer);

            final EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();

            entityPlayer.playerConnection.sendPacket(packet);
            entityPlayer.listName = CraftChatMessage.fromString(prefix.getPrefix(entityPlayer.getUniqueID()) + player.getName())[0];

            final PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, entityPlayer);

            for (final EntityPlayer other : players) {
                other.playerConnection.sendPacket(info);
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