package me.pajic.simpleenderbackpack;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.stats.Stats;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
//? if < 1.21.8
import net.neoforged.neoforge.network.PacketDistributor;
//? if >= 1.21.8
/*import net.neoforged.neoforge.client.network.ClientPacketDistributor;*/

public class Util {

    public static void openEnderBackpack(Player player) {
        PlayerEnderChestContainer container = player.getEnderChestInventory();
        player.openMenu(new SimpleMenuProvider((i, inventory, player1) ->
                ChestMenu.threeRows(i, inventory, container), Component.translatable("container.enderchest")
        ));
        player.awardStat(Stats.OPEN_ENDERCHEST);
    }

    public static void sendToServer(CustomPacketPayload payload) {
        //? if 1.21.1
        PacketDistributor.sendToServer(payload);
        //? if >= 1.21.8
        /*ClientPacketDistributor.sendToServer(payload);*/
    }
}
