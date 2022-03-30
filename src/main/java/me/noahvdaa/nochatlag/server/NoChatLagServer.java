package me.noahvdaa.nochatlag.server;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class NoChatLagServer extends JavaPlugin implements Listener {

	// Technically not an invisible character, but a greek tonos is the closest we'll get in Minecraft.
	private static final String INVISIBLE_CHARACTER = "\u0384";

	@Override
	public void onEnable() {
		saveDefaultConfig();

		ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

		// Some very naughty plugins are making changes in the MONITOR priority, so we unfortunately have to too. >:(
		protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.MONITOR, PacketType.Play.Server.CHAT) {
			@Override
			public void onPacketSending(PacketEvent event) {
				if (event.isCancelled()) return;
				event.setReadOnly(false);
				PacketContainer packet = event.getPacket();
				packet.getUUIDs().write(0, new UUID(0L, 0L));
				event.setPacket(packet);
			}
		});

		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		if (!getConfig().getBoolean("invisibleNameCharacter", true)) return;

		String format = e.getFormat();
		// They aren't using the vanilla format!
		if (!format.contains("<%1$s>")) return;

		format = format.replaceAll("<%1\\$s>", "<" + INVISIBLE_CHARACTER + "%1\\$s>");
		e.setFormat(format);
	}

}
