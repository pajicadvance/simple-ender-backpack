package me.pajic.simpleenderbackpack;

import me.pajic.simpleenderbackpack.keybind.ModKeybinds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = "simple_ender_backpack", dist = Dist.CLIENT)
public class ClientMain {

    public ClientMain(IEventBus modEventBus) {
        modEventBus.addListener(ModKeybinds::registerKeybinds);
    }
}
