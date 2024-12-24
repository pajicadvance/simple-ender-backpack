package me.pajic.simpleenderbackpack;

import me.pajic.simpleenderbackpack.accessories.EnderBackpackAccessory;
import me.pajic.simpleenderbackpack.item.EnderBackpackItem;
import me.pajic.simpleenderbackpack.network.ModNetworking;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
//? if > 1.21.1 {
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
//?}

@Mod("simple_ender_backpack")
public class Main
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("simple_ender_backpack");
    //? if <= 1.21.1 {
    /*public static final DeferredHolder<Item, EnderBackpackItem> ENDER_BACKPACK = ITEMS.register(
            "ender_backpack",
            () -> new EnderBackpackItem(new Item.Properties().stacksTo(1))
    );
    *///?}
    //? if > 1.21.1 {
    public static final DeferredHolder<Item, EnderBackpackItem> ENDER_BACKPACK = ITEMS.register(
            "ender_backpack",
            () -> new EnderBackpackItem(new Item.Properties().setId(ResourceKey.create(
                    Registries.ITEM,
                    ResourceLocation.parse("simple_ender_backpack:ender_backpack"))).stacksTo(1)
            )
    );
    //?}

    public Main(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::registerAccessory);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(ModNetworking::initNetworking);
    }

    private void registerAccessory(RegisterEvent event) {
        if (ModList.get().isLoaded("accessories")) {
            event.register(Registries.ITEM, EnderBackpackAccessory::init);
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(Items.LEAD.getDefaultInstance(), ENDER_BACKPACK.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
