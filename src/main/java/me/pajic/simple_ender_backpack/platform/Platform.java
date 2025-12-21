package me.pajic.simple_ender_backpack.platform;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public interface Platform {
	boolean isModLoaded(String modId);

	boolean isDebug();

	ModLoader loader();

	String mcVersion();

	void sendToServer(CustomPacketPayload payload);

	enum ModLoader {
		FABRIC, NEOFORGE, FORGE
	}
}
