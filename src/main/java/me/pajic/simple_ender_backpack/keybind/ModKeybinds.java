package me.pajic.simple_ender_backpack.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import me.pajic.simple_ender_backpack.CompatFlags;
import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.accessories.EnderBackpackAccessory;
import me.pajic.simple_ender_backpack.item.EnderBackpackItem;
import me.pajic.simple_ender_backpack.network.ModNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

	public static final KeyMapping.Category KEYS = new KeyMapping.Category(SEB.id("keys"));

	public static final KeyMapping OPEN_ENDER_BACKPACK = new KeyMapping(
			"key.simple_ender_backpack.open_ender_backpack",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_B,
			KEYS
	);

	public static void onClientTick(Minecraft client) {
		if (OPEN_ENDER_BACKPACK.consumeClick() && client.player != null && client.level != null) {
			if (client.player.getInventory().hasAnyMatching(itemStack -> itemStack.getItem() instanceof EnderBackpackItem)) {
				client.player.playSound(SoundEvents.ENDER_CHEST_OPEN);
				SEB.xplat().sendToServer(new ModNetworking.C2SOpenEnderContainerPayload());
			}
			else if (CompatFlags.ACCESSORIES_LOADED) {
				EnderBackpackAccessory.tryOpenEnderBackpackAccessory(client.player);
			}
		}
	}
}
