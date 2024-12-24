package me.pajic.simpleenderbackpack.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import me.pajic.simpleenderbackpack.accessories.EnderBackpackAccessory;
import me.pajic.simpleenderbackpack.item.EnderBackpackItem;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = "simple_ender_backpack", value = Dist.CLIENT)
public class ModKeybinds {

    public static final Lazy<KeyMapping> OPEN_ENDER_BACKPACK = Lazy.of(() ->
            new KeyMapping(
                    "key.simple_ender_backpack.open_ender_backpack",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    "category.simple_ender_backpack.keybindings"
            )
    );

    public static void registerKeybinds(RegisterKeyMappingsEvent event) {
        event.register(OPEN_ENDER_BACKPACK.get());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
        if (OPEN_ENDER_BACKPACK.get().consumeClick() && client.player != null && client.level != null) {
            if (client.player.getInventory().hasAnyMatching(itemStack -> itemStack.getItem() instanceof EnderBackpackItem)) {
                client.player.playSound(SoundEvents.ENDER_CHEST_OPEN);
                PacketDistributor.sendToServer(new ModNetworking.C2SOpenEnderContainerPayload());
            }
            else if (ModList.get().isLoaded("accessories")) {
                client.player.playSound(SoundEvents.ENDER_CHEST_OPEN);
                EnderBackpackAccessory.tryOpenEnderBackpackAccessory(client.player);
            }
        }
    }
}
