package me.pajic.simpleenderbackpack.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import me.pajic.simpleenderbackpack.accessories.EnderBackpackAccessory;
import me.pajic.simpleenderbackpack.item.EnderBackpackItem;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.sounds.SoundEvents;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

    private static final KeyMapping OPEN_ENDER_BACKPACK = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                    "key.simple_ender_backpack.open_ender_backpack",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    "category.simple_ender_backpack.keybindings"
            )
    );

    public static void initKeybinds() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (OPEN_ENDER_BACKPACK.consumeClick() && client.player != null && client.level != null) {
                if (client.player.getInventory().hasAnyMatching(itemStack -> itemStack.getItem() instanceof EnderBackpackItem)) {
                    client.player.playSound(SoundEvents.ENDER_CHEST_OPEN);
                    ClientPlayNetworking.send(new ModNetworking.C2SOpenEnderContainerPayload());
                }
                else if (FabricLoader.getInstance().isModLoaded("accessories")) {
                    EnderBackpackAccessory.tryOpenEnderBackpackAccessory(client.player);
                }
            }
        });
    }
}
