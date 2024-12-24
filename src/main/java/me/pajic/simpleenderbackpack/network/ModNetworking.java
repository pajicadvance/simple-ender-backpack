package me.pajic.simpleenderbackpack.network;

import me.pajic.simpleenderbackpack.Util;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.NotNull;

public class ModNetworking {

    public static final ResourceLocation OPEN_ENDER_CONTAINER = ResourceLocation.parse("simple_ender_backpack:open_ender_container");

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

    public static void initNetworking() {
        PayloadTypeRegistry.playC2S().register(C2SOpenEnderContainerPayload.TYPE, C2SOpenEnderContainerPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(C2SOpenEnderContainerPayload.TYPE, (payload, context) -> context.server().execute(() -> {
            context.player().playSound(SoundEvents.ENDER_CHEST_OPEN);
            Util.openEnderBackpack(context.player());
        }));
    }
}
