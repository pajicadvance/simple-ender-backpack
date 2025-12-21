package me.pajic.simple_ender_backpack.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.core.Accessory;
import io.wispforest.accessories.api.core.AccessoryRegistry;
import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.item.ModItems;
import me.pajic.simple_ender_backpack.network.ModNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class EnderBackpackAccessory implements Accessory {

    public static void register() {
        AccessoryRegistry.register(ModItems.ENDER_BACKPACK, new EnderBackpackAccessory());
    }

    public static void tryOpenEnderBackpackAccessory(Player player) {
        Optional<AccessoriesCapability> optional = AccessoriesCapability.getOptionally(player);
        if (optional.isPresent() && optional.get().isEquipped(ModItems.ENDER_BACKPACK)) {
            if (player instanceof LocalPlayer) {
                player.playSound(SoundEvents.ENDER_CHEST_OPEN);
            }
			SEB.xplat().sendToServer(new ModNetworking.C2SOpenEnderContainerPayload());
        }
    }
}
