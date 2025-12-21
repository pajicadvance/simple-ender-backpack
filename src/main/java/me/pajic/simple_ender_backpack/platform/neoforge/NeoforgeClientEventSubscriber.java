package me.pajic.simple_ender_backpack.platform.neoforge;

//? neoforge {

/*import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.keybind.ModKeybinds;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = SEB.MOD_ID, value = Dist.CLIENT)
public class NeoforgeClientEventSubscriber {

	@SubscribeEvent
	private static void initKeybinds(RegisterKeyMappingsEvent event) {
		event.registerCategory(ModKeybinds.KEYS);
		event.register(ModKeybinds.OPEN_ENDER_BACKPACK);
	}

	@SubscribeEvent
	private static void onClientTick(ClientTickEvent.Post event) {
		ModKeybinds.onClientTick(Minecraft.getInstance());
	}
}
*///?}
