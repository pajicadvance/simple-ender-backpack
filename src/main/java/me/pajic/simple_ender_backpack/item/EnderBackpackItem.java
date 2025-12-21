package me.pajic.simple_ender_backpack.item;

import me.pajic.simple_ender_backpack.SEB;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.InteractionResult;

public class EnderBackpackItem extends Item {
    public EnderBackpackItem() {
        super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, SEB.id("ender_backpack"))));
    }

    @Override
    public @NotNull InteractionResult use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (level.isClientSide()) player.playSound(SoundEvents.ENDER_CHEST_OPEN);
        if (!level.isClientSide()) openEnderBackpack(player);
        return InteractionResult.SUCCESS;
    }

	public static void openEnderBackpack(Player player) {
		PlayerEnderChestContainer container = player.getEnderChestInventory();
		player.openMenu(new SimpleMenuProvider((i, inventory, player1) ->
				ChestMenu.threeRows(i, inventory, container), Component.translatable("container.enderchest")
		));
		player.awardStat(Stats.OPEN_ENDERCHEST);
	}
}
