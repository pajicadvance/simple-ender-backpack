package me.pajic.simpleenderbackpack.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.Accessory;
import me.pajic.simpleenderbackpack.Main;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.RegisterEvent;
//? if <= 1.21.1
import io.wispforest.accessories.api.AccessoriesAPI;
//? if > 1.21.1
/*import io.wispforest.accessories.api.AccessoryRegistry;*/

import java.util.Optional;

public class EnderBackpackAccessory implements Accessory {

    public static void init(RegisterEvent.RegisterHelper<Item> helper) {
        //? if <= 1.21.1
        AccessoriesAPI.registerAccessory(Main.ENDER_BACKPACK.get(), new EnderBackpackAccessory());
        //? if > 1.21.1
        /*AccessoryRegistry.register(Main.ENDER_BACKPACK.get(), new EnderBackpackAccessory());*/
    }

    public static void tryOpenEnderBackpackAccessory(Player player) {
        Optional<AccessoriesCapability> optional = AccessoriesCapability.getOptionally(player);
        if (optional.isPresent() && optional.get().isEquipped(Main.ENDER_BACKPACK.get())) {
            if (player instanceof LocalPlayer) {
                player.playSound(SoundEvents.ENDER_CHEST_OPEN);
            }
            PacketDistributor.sendToServer(new ModNetworking.C2SOpenEnderContainerPayload());
        }
    }
}
