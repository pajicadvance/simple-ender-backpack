package me.pajic.simpleenderbackpack.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import me.pajic.simpleenderbackpack.Main;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
//? if <= 1.21.1
import io.wispforest.accessories.api.AccessoriesAPI;
//? if > 1.21.1 < 1.21.8
/*import io.wispforest.accessories.api.AccessoryRegistry;*/
//? if < 1.21.8
import io.wispforest.accessories.api.Accessory;
//? if >= 1.21.8 {
/*import io.wispforest.accessories.api.core.Accessory;
import io.wispforest.accessories.api.core.AccessoryRegistry;
*///?}

import java.util.Optional;

public class EnderBackpackAccessory implements Accessory {

    public static void init() {
        //? if <= 1.21.1
        AccessoriesAPI.registerAccessory(Main.ENDER_BACKPACK, new EnderBackpackAccessory());
        //? if > 1.21.1
        /*AccessoryRegistry.register(Main.ENDER_BACKPACK, new EnderBackpackAccessory());*/
    }

    public static void tryOpenEnderBackpackAccessory(Player player) {
        Optional<AccessoriesCapability> optional = AccessoriesCapability.getOptionally(player);
        if (optional.isPresent() && optional.get().isEquipped(Main.ENDER_BACKPACK)) {
            if (player instanceof LocalPlayer) {
                player.playSound(SoundEvents.ENDER_CHEST_OPEN);
            }
            ClientPlayNetworking.send(new ModNetworking.C2SOpenEnderContainerPayload());
        }
    }
}
