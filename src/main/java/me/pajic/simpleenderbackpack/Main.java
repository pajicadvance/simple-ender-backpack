package me.pajic.simpleenderbackpack;

import me.pajic.simpleenderbackpack.accessories.EnderBackpackAccessory;
import me.pajic.simpleenderbackpack.item.EnderBackpackItem;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
//? if > 1.21.1 {
/*import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
*///?}

public class Main implements ModInitializer {

    //? if <= 1.21.1
    public static final Item ENDER_BACKPACK = new EnderBackpackItem(new Item.Properties().stacksTo(1));
    //? if > 1.21.1 {
    /*public static final Item ENDER_BACKPACK = new EnderBackpackItem(
            new Item.Properties().setId(ResourceKey.create(
                    Registries.ITEM,
                    ResourceLocation.parse("simple_ender_backpack:ender_backpack")
            )).stacksTo(1));
    *///?}

    @Override
    public void onInitialize() {
        ModNetworking.initNetworking();

        Registry.register(
                BuiltInRegistries.ITEM,
                ResourceLocation.parse("simple_ender_backpack:ender_backpack"),
                ENDER_BACKPACK
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(contents -> contents.addAfter(
                Items.LEAD,
                ENDER_BACKPACK
        ));

        if (FabricLoader.getInstance().isModLoaded("accessories")) {
            EnderBackpackAccessory.init();
        }
    }
}
