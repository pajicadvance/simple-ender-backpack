package me.pajic.simpleenderbackpack.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.Accessory;
import me.pajic.simpleenderbackpack.Main;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.player.Player;
//? if <= 1.21.1
/*import io.wispforest.accessories.api.AccessoriesAPI;*/
//? if > 1.21.1
import io.wispforest.accessories.api.AccessoryRegistry;

public class EnderBackpackAccessory implements Accessory {

    public static void init() {
        //? if <= 1.21.1
        /*AccessoriesAPI.registerAccessory(Main.ENDER_BACKPACK, new EnderBackpackAccessory());*/
        //? if > 1.21.1
        AccessoryRegistry.register(Main.ENDER_BACKPACK, new EnderBackpackAccessory());
    }

    public static void tryOpenEnderBackpackAccessory(Player player) {
        if (AccessoriesCapability.get(player).isEquipped(Main.ENDER_BACKPACK)) {
            ClientPlayNetworking.send(new ModNetworking.C2SOpenEnderContainerPayload());
        }
    }
}
