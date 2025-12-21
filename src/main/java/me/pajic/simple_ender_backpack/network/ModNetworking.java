package me.pajic.simple_ender_backpack.network;

import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.item.EnderBackpackItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.NotNull;

public class ModNetworking {

    public static final Identifier OPEN_ENDER_CONTAINER = SEB.id("open_ender_container");

    public record C2SOpenEnderContainerPayload() implements CustomPacketPayload {
        public static final Type<C2SOpenEnderContainerPayload> TYPE = new Type<>(OPEN_ENDER_CONTAINER);
        public static final StreamCodec<RegistryFriendlyByteBuf, C2SOpenEnderContainerPayload> CODEC =
                StreamCodec.of(C2SOpenEnderContainerPayload::encode, C2SOpenEnderContainerPayload::decode);

        @Override
        public @NotNull Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }

        private static void encode(FriendlyByteBuf buf, C2SOpenEnderContainerPayload payload) {
        }

        private static C2SOpenEnderContainerPayload decode(FriendlyByteBuf buf) {
            return new C2SOpenEnderContainerPayload();
        }
    }

	public static void handleOpenEnderContainer(ServerPlayer player) {
		player.playSound(SoundEvents.ENDER_CHEST_OPEN);
		EnderBackpackItem.openEnderBackpack(player);
	}
}
