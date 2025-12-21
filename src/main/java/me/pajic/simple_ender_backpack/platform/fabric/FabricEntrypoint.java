package me.pajic.simple_ender_backpack.platform.fabric;

//? fabric {

import me.pajic.simple_ender_backpack.CompatFlags;
import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.accessories.EnderBackpackAccessory;
import me.pajic.simple_ender_backpack.item.ModItems;
import me.pajic.simple_ender_backpack.network.ModNetworking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

@SuppressWarnings("unused")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		initRegistry();
		initCreativeTabs();
		initNetworking();
	}

	private static void initRegistry() {
		Registry.register(BuiltInRegistries.ITEM, SEB.id("ender_backpack"), ModItems.ENDER_BACKPACK);
		if (CompatFlags.ACCESSORIES_LOADED) EnderBackpackAccessory.register();
	}

	private static void initCreativeTabs() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(contents ->
				contents.addAfter(Items.LEAD, ModItems.ENDER_BACKPACK)
		);
	}

	private static void initNetworking() {
		PayloadTypeRegistry.playC2S().register(
				ModNetworking.C2SOpenEnderContainerPayload.TYPE,
				ModNetworking.C2SOpenEnderContainerPayload.CODEC
		);
		ServerPlayNetworking.registerGlobalReceiver(
				ModNetworking.C2SOpenEnderContainerPayload.TYPE,
				(payload, context) -> ModNetworking.handleOpenEnderContainer(context.player())
		);
	}
}
//?}
