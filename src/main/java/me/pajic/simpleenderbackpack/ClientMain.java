package me.pajic.simpleenderbackpack;

import me.pajic.simpleenderbackpack.keybind.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;

public class ClientMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModKeybinds.initKeybinds();
    }
}
