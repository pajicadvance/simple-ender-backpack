package me.pajic.simple_ender_backpack.platform.neoforge;

//? neoforge {

/*import me.pajic.simple_ender_backpack.platform.Platform;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class NeoforgePlatform implements Platform {

	@Override
	public boolean isModLoaded(String modId) {
		return ModList.get().isLoaded(modId);
	}

	@Override
	public ModLoader loader() {
		return ModLoader.NEOFORGE;
	}

	@Override
	public String mcVersion() {
		return FMLLoader.getCurrent().getVersionInfo().mcVersion();
	}

	@Override
	public boolean isDebug() {
		return !FMLLoader/^? if > 1.21.1 {^/.getCurrent()/^?}^/.isProduction();
	}

	@Override
	public void sendToServer(CustomPacketPayload payload) {
		ClientPacketDistributor.sendToServer(payload);
	}
}
*///?}
