package me.pajic.simple_ender_backpack.platform.neoforge;

//? neoforge {

/*import me.pajic.simple_ender_backpack.CompatFlags;
import me.pajic.simple_ender_backpack.SEB;
import me.pajic.simple_ender_backpack.accessories.EnderBackpackAccessory;
import me.pajic.simple_ender_backpack.item.ModItems;
import me.pajic.simple_ender_backpack.network.ModNetworking;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(SEB.MOD_ID)
@EventBusSubscriber(modid = SEB.MOD_ID)
public class NeoforgeEntrypoint {

	@SubscribeEvent
	private static void initRegistry(RegisterEvent event) {
		event.register(Registries.ITEM, registry -> registry.register(SEB.id("ender_backpack"), ModItems.ENDER_BACKPACK));
		if (CompatFlags.ACCESSORIES_LOADED) EnderBackpackAccessory.register();
	}

	@SubscribeEvent
	private static void initCreativeTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.insertAfter(
				Items.LEAD.getDefaultInstance(),
				ModItems.ENDER_BACKPACK.getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
		);
	}

	@SubscribeEvent
	public static void initNetworking(RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");
		registrar.playToServer(
				ModNetworking.C2SOpenEnderContainerPayload.TYPE,
				ModNetworking.C2SOpenEnderContainerPayload.CODEC,
				(payload, context) ->
						ModNetworking.handleOpenEnderContainer((ServerPlayer) context.player())
		);
	}
}
*///?}
