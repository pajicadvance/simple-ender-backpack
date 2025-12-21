package me.pajic.simple_ender_backpack.platform.fabric;

//? fabric {

import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.keybind.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

@SuppressWarnings("unused")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		initKeybinds();
	}

	private static void initKeybinds() {
		KeyMapping.Category.register(SEB.id("keys"));
		KeyBindingHelper.registerKeyBinding(ModKeybinds.OPEN_ENDER_BACKPACK);
		ClientTickEvents.END_CLIENT_TICK.register(ModKeybinds::onClientTick);
	}
}
//?}
